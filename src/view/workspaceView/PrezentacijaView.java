package view.workspaceView;

import controller.observers.Subsriber;
import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Slide;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrezentacijaView extends JPanel implements Subsriber {
    private Prezentacija prezentacijaRuNode;
    private JLabel autorlbl;
    private List<SlideView> slideViewList;
    private List<Component> verticalStrutlist;

    public PrezentacijaView(Prezentacija prezentacijaRuNode){
        slideViewList = new ArrayList<>();
        verticalStrutlist = new ArrayList<>();
        this.prezentacijaRuNode = prezentacijaRuNode;
        autorlbl = new JLabel(prezentacijaRuNode.getAutor());
        this.prezentacijaRuNode.addSubscriber(this);

        this.setPreferredSize(new Dimension(300, 500));
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(box);

        this.add(autorlbl);
        generisanjeSlajdova();

    }


    private void generisanjeSlajdova(){
        for(RuNode s : prezentacijaRuNode.getChildren()){
            if(s instanceof Slide){
                Slide slide = (Slide) s;
                SlideView slideView = new SlideView(slide);
                slideViewList.add(slideView);
                Component verticalStrut = Box.createVerticalStrut(20);
                verticalStrutlist.add(verticalStrut);
                this.add(verticalStrut);
                this.add(slideView);
            }
        }
    }

    private void brisanjeSlajda(Slide sl){
//        SlideView brisanje = null;
//        for(int i = 0; i< slideViewList.size(); i++){
//            if(slideViewList.get(i).getSlideRuNode().equals(sl)){
//                brisanje = slideViewList.get(i);
//                System.out.println("Obrisan " + slideViewList.get(i).getSlideRuNode().getName());
//                this.remove(verticalStrutlist.get(i));
//                this.remove(slideViewList.get(i));
//                this.validate();
//                this.repaint();
//                break;
//            }
//        }
//        slideViewList.remove(brisanje);


//        videti da li je okej ovako ili da primenim resenje iznad samo da nadjem nacim kako da remove verticalStrut
        this.removeAll();
        this.add(autorlbl);
        generisanjeSlajdova();
        this.validate();
        this.repaint();

    }

    private void dodavanjeSlajda(Slide sl){
        SlideView sw = new SlideView(sl);
        slideViewList.add(sw);
        this.add(Box.createVerticalStrut(20));
        this.add(sw);
        this.validate();
    }

    public void updateSubsriber(Object notification, String message) {
        if(notification instanceof Prezentacija && message.equals("promena autora")){
            autorlbl.setText(((Prezentacija) notification).getAutor());
            System.out.println("promena prezentacija autor");
        }

        if(notification instanceof Slide && message.equals("brisanje")){
            brisanjeSlajda((Slide) notification);

        }

        if(notification instanceof Slide && message.equals("dodavanje")){
            dodavanjeSlajda((Slide) notification);
//            proveriti da li se pozadina dodaje i na dodati slajd
            System.out.println("Dodavanje slajda");
        }

    }

    public Prezentacija getPrezentacijaRuNode() {
        return prezentacijaRuNode;
    }

    public void setPrezentacijaRuNode(Prezentacija prezentacijaRuNode) {
        this.prezentacijaRuNode.removeSubscriber(this);
        this.prezentacijaRuNode = prezentacijaRuNode;
        this.prezentacijaRuNode.addSubscriber(this);
    }

    public JLabel getAutorlbl() {
        return autorlbl;
    }

    public void setAutorlbl(JLabel autorlbl) {
        this.autorlbl = autorlbl;
    }
}
