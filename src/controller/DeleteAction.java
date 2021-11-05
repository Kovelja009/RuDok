package controller;

import view.MainFrame;
import view.tree.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRudokAction{
    public DeleteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Deleting selected item");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(o instanceof MyTreeNode){
            MyTreeNode childTreeNode = (MyTreeNode) o;
            MyTreeNode parentTreeNode = (MyTreeNode) childTreeNode.getParent();
            if(parentTreeNode == null){
            System.out.println("Nije moguce izbrisati " + childTreeNode);
            return;
            }

            parentTreeNode.removeChild(childTreeNode);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());

        }
    }
}
