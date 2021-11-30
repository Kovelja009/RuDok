package controller.actions;

import controller.errorHandler.ErrorFactory;
import model.RuNode;
import model.workspace.*;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractRudokAction{
    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/add_img.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "Adding new item");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(o instanceof MyTreeNode){
            MyTreeNode roditeljTreeNode = (MyTreeNode)o;
            RuNode roditelj = roditeljTreeNode.getNode();

            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());

            int broj = roditeljTreeNode.getChildCount() + 1;
            if(roditelj instanceof Workspace){
                MyTreeNode deteProjekat = new MyTreeNode(new Projekat("New project " + broj, roditelj));
                roditeljTreeNode.addChild(deteProjekat);
                deteProjekat.setParent(roditeljTreeNode);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
                return;
            }
            if(roditelj instanceof Projekat){
                MyTreeNode detePrezentacija = new MyTreeNode(new Prezentacija("New presentation " + broj, roditelj));
                roditeljTreeNode.addChild(detePrezentacija);
                detePrezentacija.setParent(roditeljTreeNode);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
                return;
            }

            if(roditelj instanceof Prezentacija){
                Slide slide = new Slide("New slide " + broj, roditelj);
                slide.setRedniBroj(broj);
                MyTreeNode deteSlide = new MyTreeNode(slide);
                roditeljTreeNode.addChild(deteSlide);
                deteSlide.setParent(roditeljTreeNode);

                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
                return;
            }
            if(roditelj instanceof Slide){
                ErrorFactory.getInstance().generateError("Slide doesn't have elements to add", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
