package controller.actions;

import controller.commands.AddCommand;
import controller.errorHandler.ErrorFactory;
import model.RuNode;
import model.workspace.*;
import view.MainFrame;
import view.factory.AbstractNodeFactory;
import view.factory.MetaFactory;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.TreePath;
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

            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());

            int broj = roditeljTreeNode.getChildCount() + 1;

            if(roditeljTreeNode.getNode() instanceof Slide){
                ErrorFactory.getInstance().generateError("Slide doesn't have elements to add", "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(roditeljTreeNode, broj));
        }
    }

}
