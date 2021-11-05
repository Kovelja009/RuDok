package controller;

import model.*;
import view.MainFrame;
import view.tree.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractRudokAction{
    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/add_img.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(o instanceof MyTreeNode){
            MyTreeNode roditeljTreeNode = (MyTreeNode)o;
            RuNode roditelj = roditeljTreeNode.getNode();

            int broj = roditeljTreeNode.getChildCount() + 1;
            if(roditelj instanceof Workspace){
                MyTreeNode deteProjekat = new MyTreeNode(new Projekat("Novi projekat " + broj, roditelj));
                roditeljTreeNode.addChild(deteProjekat);
                deteProjekat.setParent(roditeljTreeNode);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
                return;
            }
            if(roditelj instanceof Projekat){
                MyTreeNode detePrezentacija = new MyTreeNode(new Prezentacija("Nova prezentacija " + broj, roditelj));
                roditeljTreeNode.addChild(detePrezentacija);
                detePrezentacija.setParent(roditeljTreeNode);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
                return;
            }

            if(roditelj instanceof Prezentacija){
                MyTreeNode deteSlide = new MyTreeNode(new Slide("Novi slide " + broj, roditelj));
                roditeljTreeNode.addChild(deteSlide);
                deteSlide.setParent(roditeljTreeNode);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
                return;
            }
            if(roditelj instanceof Slide){
                System.out.println("Nije moguce nista dodati na " + roditelj.getName());
            }
            return;
        }
        if(o!= null){
            System.out.println(o + " nije instanca MyTreeNode ili ruNode " + ((MyTreeNode)o).getNode().getName() + " nije instanca projekta!!!");
        }
    }
}
