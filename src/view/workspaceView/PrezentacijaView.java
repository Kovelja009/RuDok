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

    public PrezentacijaView(Prezentacija prezentacijaRuNode){
        slideViewList = new ArrayList<>();

        this.prezentacijaRuNode = prezentacijaRuNode;
        this.prezentacijaRuNode.addSubscriber(this);

        autorlbl = new JLabel(prezentacijaRuNode.getAutor());

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.setMinimumSize(new Dimension(500, 400));
        setAlignmentX(CENTER_ALIGNMENT);



        this.add(autorlbl);
        generisanjeSlajdova();
        validate();
        repaint();

    }


    private void generisanjeSlajdova(){
        for(RuNode s : prezentacijaRuNode.getChildren()){
            if(s instanceof Slide){
                Slide slide = (Slide) s;
                SlideView slideView = new SlideView(slide);
                slideViewList.add(slideView);
                this.add(slideView);
            }
        }
        validate();
        repaint();
    }

    private void brisanjeSlajda(Slide sl){
        SlideView brisanje = null;
        for(int i = 0; i< slideViewList.size(); i++){
            if(slideViewList.get(i).getSlideRuNode().equals(sl)){
                brisanje = slideViewList.get(i);
                this.remove(slideViewList.get(i));
                this.validate();
                this.repaint();
                break;
            }
        }
        slideViewList.remove(brisanje);
    }

    private void dodavanjeSlajda(Slide sl){
        SlideView sw = new SlideView(sl);
        slideViewList.add(sw);
        this.add(sw);
        this.validate();
        this.repaint();
    }

    public void updateSubsriber(Object notification, String message) {
        if(notification instanceof Prezentacija && message.equals("promena autora")){
            autorlbl.setText(((Prezentacija) notification).getAutor());
        }

        if(notification instanceof Slide && message.equals("brisanje")){
            brisanjeSlajda((Slide) notification);
        }

        if(notification instanceof Slide && message.equals("dodavanje")){
            dodavanjeSlajda((Slide) notification);
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
