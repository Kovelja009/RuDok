package view.workspaceView;

import controller.observers.Subsriber;
import model.workspace.Projekat;

import javax.swing.*;
import java.awt.*;

public class ProjekatView extends JPanel implements Subsriber {
    private Projekat projekatRuNode;
    private JLabel imeProjekta = new JLabel();

    public ProjekatView(Projekat projekatRuNode){
        this.projekatRuNode = projekatRuNode;
        imeProjekta.setText(projekatRuNode.getName());
        this.add(imeProjekta);

        this.projekatRuNode.addSubscriber(this);
    }

    public ProjekatView(){
        imeProjekta = new JLabel();
        this.add(imeProjekta);
    }

    public Projekat getProjekatRuNode() {
        return projekatRuNode;
    }

    public void setProjekatRuNode(Projekat projekatRuNode) {
        this.projekatRuNode = projekatRuNode;
        this.imeProjekta.setText(projekatRuNode.getName());
        this.projekatRuNode.addSubscriber(this);
    }

    @Override
    public void updateSubsriber(Object notification) {
        this.imeProjekta.setText(projekatRuNode.getName());
//        this.validate();
    }
}
