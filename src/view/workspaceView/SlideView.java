package view.workspaceView;

import controller.observers.Subsriber;
import model.RuNode;
import model.workspace.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel implements Subsriber {
    private Slide slideRuNode;

    public SlideView(Slide slideRuNode){
        this.slideRuNode = slideRuNode;

        this.setBackground(Color.RED);
        JLabel brojSlajda = new JLabel(String.valueOf(slideRuNode.getRedniBroj()));
        this.add(brojSlajda);
    }

    public Slide getSlideRuNode() {
        return slideRuNode;
    }

    public void setSlideRuNode(Slide slideRuNode) {
        this.slideRuNode = slideRuNode;
    }

    @Override
    public void updateSubsriber(Object notification) {
        System.out.println("Update SlideView");
    }
}
