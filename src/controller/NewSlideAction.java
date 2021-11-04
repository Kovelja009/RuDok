package controller;

import controller.AbstractRudokAction;
import model.Prezentacija;
import model.Projekat;
import model.RuNode;
import model.Slide;
import view.MainFrame;
import view.tree.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewSlideAction extends AbstractRudokAction {
    public NewSlideAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/add_img.png"));
        putValue(NAME, "Dodaj slide");
        putValue(SHORT_DESCRIPTION, "Dodavanje slide-a");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(o instanceof MyTreeNode && ((MyTreeNode)o).getNode() instanceof Prezentacija){
            MyTreeNode prezentacijaTreeNode = (MyTreeNode)o;
            RuNode prezentacija = prezentacijaTreeNode.getNode();
            MyTreeNode deteSlide = new MyTreeNode(new Slide("Novi slide", prezentacija));
            prezentacijaTreeNode.addChild(deteSlide);
            deteSlide.setParent(prezentacijaTreeNode);

            System.out.println("Dodato dete " + deteSlide + " na " + prezentacijaTreeNode);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
            return;
        }
        if(o!= null){
            System.out.println(o + " nije instanca MyTreeNode ili ruNode " + ((MyTreeNode)o).getNode().getName() + " nije instanca prezentacije!!!");
        }
    }
}
