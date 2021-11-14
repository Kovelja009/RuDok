package view.workspaceView;

import controller.observers.Subsriber;
import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Slide;

import javax.imageio.ImageIO;
import javax.sql.rowset.BaseRowSet;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SlideView extends JPanel implements Subsriber {
    private Slide slideRuNode;
    private Image image;
    private JLabel brojSlajda;

    public SlideView(Slide slideRuNode){
        this.slideRuNode = slideRuNode;
        this.slideRuNode.addSubscriber(this);
        ((Prezentacija)this.slideRuNode.getParent()).addSubscriber(this);
        setLayout(new BorderLayout());

        brojSlajda = new JLabel(String.valueOf(slideRuNode.getRedniBroj()));
        this.setPreferredSize(new Dimension(700, 400));
        this.setMaximumSize(new Dimension(700, 400));
        this.add(brojSlajda, BorderLayout.SOUTH);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        URL imageURL = getClass().getResource(((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        ImageIcon icon = null;

        if(imageURL != null){
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resources not found: " + ((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        }
        image = icon.getImage();
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
    }

    private void loadImage(){
        URL imageURL = getClass().getResource(((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        ImageIcon icon = null;

        if(imageURL != null){
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resources not found: " + ((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        }
        image = icon.getImage();
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
                loadImage();
                this.validate();
                this.repaint();
        }

    }

}
