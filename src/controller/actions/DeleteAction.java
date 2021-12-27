package controller.actions;

import controller.commands.AddCommand;
import controller.commands.RemoveCommand;
import controller.errorHandler.ErrorFactory;
import model.workspace.Prezentacija;
import model.workspace.Slide;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRudokAction{
    public DeleteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/delete.png"));
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
                ErrorFactory.getInstance().generateError("Can't delete workspace", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            MainFrame.getInstance().getCommandManager().addCommand(new RemoveCommand(parentTreeNode, childTreeNode));
        }
    }
}
