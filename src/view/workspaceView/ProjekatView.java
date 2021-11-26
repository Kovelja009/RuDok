package view.workspaceView;

import controller.observers.Subsriber;
import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjekatView extends JPanel implements Subsriber {
    private Projekat projekatRuNode;
    private JLabel imeProjekta;
    private JTabbedPane prezentacijaTabbedPane; // ubaciti da radi
    private List<PrezentacijaView> prezentacijaViewList;


    public ProjekatView(Projekat projekatRuNode){
        this.projekatRuNode = projekatRuNode;
        imeProjekta.setText(projekatRuNode.getName());
        this.add(imeProjekta);

        this.projekatRuNode.addSubscriber(this);
    }

    public ProjekatView(){
        imeProjekta = new JLabel("No projects selected");
        imeProjekta.setPreferredSize(new Dimension(30,30));
        prezentacijaTabbedPane = new JTabbedPane();
        this.setLayout(new BorderLayout());
        this.add(imeProjekta, BorderLayout.NORTH);
        this.add(prezentacijaTabbedPane, BorderLayout.CENTER);

        prezentacijaViewList = new ArrayList<>();
    }

    public Projekat getProjekatRuNode() {
        return projekatRuNode;
    }

    public void setProjekatRuNode(Projekat projekatRuNode) {
        if(this.getProjekatRuNode() != null){
        this.getProjekatRuNode().removeAllSubcribers();
        }

        List<PrezentacijaView> brisanje = new ArrayList<>(prezentacijaViewList);
        prezentacijaViewList.removeAll(brisanje);

        this.projekatRuNode = projekatRuNode;
        prezentacijaTabbedPane.removeAll();

        if(projekatRuNode != null){
            napraviPrezentacije();

            this.projekatRuNode.addSubscriber(this);
            updateSubsriber(projekatRuNode, "ime");
        }else{
            updateSubsriber(null,"prazan");
        }
    }

    private void napraviPrezentacije(){

        for(RuNode p : projekatRuNode.getChildren()){
            if(p instanceof Prezentacija){
                ((Prezentacija)p).addSubscriber(this);
                PrezentacijaView prezView = new PrezentacijaView((Prezentacija) p);
                prezentacijaViewList.add(prezView);
                JScrollPane scrollPane = new JScrollPane(prezView, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                prezentacijaTabbedPane.addTab(prezView.getPrezentacijaRuNode().getName(), prezView);
            }
        }
    }


    private void checkDeleteing(Prezentacija bris){
        PrezentacijaView brisanje = null;

        for(PrezentacijaView p : prezentacijaViewList){
            if(p.getPrezentacijaRuNode().equals(bris)){
                (p.getPrezentacijaRuNode()).removeSubscriber(this);
                prezentacijaTabbedPane.removeTabAt(prezentacijaViewList.indexOf(p));
                System.out.println("Izbacena " + p.getPrezentacijaRuNode().getName());
                brisanje = p;
                break;
            }
        }
        if(brisanje != null){
        prezentacijaViewList.remove(brisanje);
        }

    }

    private void checkAdding(Prezentacija dodavanje){

        dodavanje.addSubscriber(this);
        PrezentacijaView prezView = new PrezentacijaView(dodavanje);
        JScrollPane pane = new JScrollPane(prezView,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        prezentacijaViewList.add(prezView);
        prezentacijaTabbedPane.addTab(prezView.getPrezentacijaRuNode().getName(), pane);
        System.out.println("Dodata " + dodavanje.getName());
    }


    @Override
    public void updateSubsriber(Object notification, String message) {
        if (notification == null && message.equals("prazan")) {
            this.imeProjekta.setText("No projects selected");
        }

        if (notification instanceof Projekat && message.equals("ime")) {
            this.imeProjekta.setText(((Projekat) notification).getName());
        }
        if (notification instanceof Prezentacija && message.equals("dodavanje")) {
            checkAdding((Prezentacija) notification);

        }
        if (notification instanceof Prezentacija && message.equals("brisanje")) {
            checkDeleteing((Prezentacija) notification);

        }

        if (notification instanceof Prezentacija && message.equals("ime taba")) {
            for (int i = 0; i < prezentacijaViewList.size(); i++) {
                if (prezentacijaViewList.get(i).getPrezentacijaRuNode().equals(notification)) {
                    prezentacijaTabbedPane.setTitleAt(i, ((Prezentacija) notification).getName());
                    break;
                }
            }

//        this.validate();
        }
    }


}
