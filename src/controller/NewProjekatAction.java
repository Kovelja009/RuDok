package controller;

import model.Projekat;
import model.RuNode;
import model.Workspace;
import view.MainFrame;
import view.tree.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjekatAction extends AbstractRudokAction{
    public NewProjekatAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/add_img.png"));
        putValue(NAME, "Dodaj projekat");
        putValue(SHORT_DESCRIPTION, "Dodavanje projekta");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = MainFrame.getInstance().getMyTreeModel().getRoot();
        if(o instanceof MyTreeNode){

            MyTreeNode workspaceTreeNode = (MyTreeNode)o;
            RuNode workspace = workspaceTreeNode.getNode();
            MyTreeNode deteProjekat = new MyTreeNode(new Projekat("Novi projekat", workspace));
            workspaceTreeNode.addChild(deteProjekat);
            deteProjekat.setParent(workspaceTreeNode);

            System.out.println("Dodato dete " + deteProjekat + " na " + workspaceTreeNode);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
            return;
        }
        System.out.println(o.getClass() + " nije instanca MyTreeNode i nije moguce dodavanje projekta!!!");
    }
}
