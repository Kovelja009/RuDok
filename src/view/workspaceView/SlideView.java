package view.workspaceView;

import controller.observers.Subsriber;
import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel implements Subsriber {
    private Slide slideRuNode;
    private ImagePanel imgPanel;
    private JLabel brojSlajda;

    public SlideView(Slide slideRuNode){
        this.slideRuNode = slideRuNode;
        this.slideRuNode.addSubscriber(this);
        ((Prezentacija)this.slideRuNode.getParent()).addSubscriber(this);

        imgPanel = new ImagePanel(((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        this.add(imgPanel);
        brojSlajda = new JLabel(String.valueOf(slideRuNode.getRedniBroj()));
        this.add(brojSlajda);

        this.setMinimumSize(new Dimension(300, 300));

    }

    public Slide getSlideRuNode() {
        return slideRuNode;
    }

    public void setSlideRuNode(Slide slideRuNode) {
        this.slideRuNode.removeSubscriber(this);
        ((Prezentacija)this.slideRuNode.getParent()).removeSubscriber(this);
        this.slideRuNode = slideRuNode;
        this.slideRuNode.addSubscriber(this);
        ((Prezentacija)this.slideRuNode.getParent()).addSubscriber(this);
    }

    @Override
    public void updateSubsriber(Object notification, String message) {
        if(notification instanceof Slide && message.equals("redni broj")){
            brojSlajda.setText(String.valueOf(slideRuNode.getRedniBroj()));
        }

        if(notification instanceof String && message.equals("promena pozadine")){
//            System.out.println("SlideVIew update backgroud ");
                this.remove(imgPanel);
                imgPanel = new ImagePanel((String) notification);
                this.add(imgPanel);
                this.validate();
                this.repaint();
        }

    }

    public ImagePanel getImgPanel() {
        return imgPanel;
    }

    public void setImgPanel(ImagePanel imgPanel) {
        this.imgPanel = imgPanel;
    }
}
