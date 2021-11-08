package controller;

import controller.errorHandler.ErrorFactory;
import model.workspace.Prezentacija;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PopupAutorAction extends AbstractRudokAction{
    public PopupAutorAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/add_author_popup.png"));
        putValue(NAME, "Set author");
        putValue(SHORT_DESCRIPTION, "Opens popup where you set author of your presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if (o instanceof MyTreeNode && ((MyTreeNode) o).getNode() instanceof Prezentacija) {

            Prezentacija prez = (Prezentacija) ((MyTreeNode) o).getNode();

            Object input = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesi autora", "Odabir autora", JOptionPane.QUESTION_MESSAGE);
            if (input != null) {
                    String autorStr = input.toString();
                    if(autorStr.equals("")){
                        ErrorFactory.getInstance().generateError("Can't leave name empty", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                prez.setAutor(autorStr);
            }

            return;
        }
        ErrorFactory.getInstance().generateError("Must select presentation for author", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

}
