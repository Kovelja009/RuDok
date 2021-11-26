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
    private List<SlideView> malislideViewList;
    private JPanel nalepnica;
    private JPanel malaNalepnica;
    private JScrollPane desniScrollPane;
    private JScrollPane leviScrollPane;

    public PrezentacijaView(Prezentacija prezentacijaRuNode){
        slideViewList = new ArrayList<>();
        malislideViewList = new ArrayList<>();


        this.prezentacijaRuNode = prezentacijaRuNode;
        this.prezentacijaRuNode.addSubscriber(this);

        autorlbl = new JLabel(prezentacijaRuNode.getAutor());


        generateMalaNalepnica();
        generateNalepnica();

        this.setLayout(new BorderLayout());
        this.add(desniScrollPane, BorderLayout.CENTER);
        this.add(leviScrollPane, BorderLayout.WEST);
        this.setMinimumSize(new Dimension(500, 400));
        this.setBackground(Color.LIGHT_GRAY);
        this.add(autorlbl, BorderLayout.NORTH);
        generisanjeSlajdova();
        revalidate();
        repaint();

    }

    private void generateNalepnica(){
        nalepnica = new JPanel();
        nalepnica.setLayout(new BoxLayout(nalepnica, BoxLayout.Y_AXIS));
        nalepnica.setBackground(Color.LIGHT_GRAY);
        desniScrollPane = new JScrollPane(nalepnica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void generateMalaNalepnica(){
        malaNalepnica = new JPanel();
        malaNalepnica.setLayout(new BoxLayout(malaNalepnica, BoxLayout.Y_AXIS));
        malaNalepnica.setBackground(Color.LIGHT_GRAY);
        malaNalepnica.setMinimumSize(new Dimension(250, 200));
        leviScrollPane = new JScrollPane(malaNalepnica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leviScrollPane.setPreferredSize(new Dimension(250, 200));
    }


    private void generisanjeSlajdova(){
        for(RuNode s : prezentacijaRuNode.getChildren()){
            if(s instanceof Slide){
                Slide slide = (Slide) s;
                SlideView slideView = new SlideView(slide);
                slideViewList.add(slideView);
                nalepnica.add(slideView);

                SlideView malislideView = new SlideView(slide);
                malislideViewList.add(malislideView);
                malislideView.setPreferredSize(new Dimension(175,100));
                malislideView.setMaximumSize(new Dimension(175,100));
                malaNalepnica.add(malislideView);
            }
        }
        revalidate();
        repaint();
    }

    private void brisanjeSlajda(Slide sl){
        SlideView brisanje = null;
        SlideView brisanje2 = null;
        for(int i = 0; i< slideViewList.size(); i++){
            if(slideViewList.get(i).getSlideRuNode().equals(sl)){
                brisanje = slideViewList.get(i);
                nalepnica.remove(slideViewList.get(i));
                nalepnica.revalidate();
                nalepnica.repaint();


                brisanje2 = malislideViewList.get(i);
                malaNalepnica.remove(malislideViewList.get(i));
                malaNalepnica.revalidate();
                malaNalepnica.repaint();

                break;
            }
        }
        slideViewList.remove(brisanje);
        malislideViewList.remove(brisanje2);
    }

    private void dodavanjeSlajda(Slide sl){
        SlideView sw = new SlideView(sl);
        slideViewList.add(sw);
        nalepnica.add(sw);
        nalepnica.revalidate();
        nalepnica.repaint();

        SlideView sw1 = new SlideView(sl);
        malislideViewList.add(sw1);
        sw1.setPreferredSize(new Dimension(175,100));
        sw1.setMaximumSize(new Dimension(175,100));
        malaNalepnica.add(sw1);
        malaNalepnica.revalidate();
        malaNalepnica.repaint();
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
