package controller;

import model.Prezentacija;
import model.RuNode;
import view.MainFrame;
import view.tree.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PopupAutorAction extends AbstractRudokAction{
    public PopupAutorAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/add_author_popup.png"));
        putValue(NAME, "Author settings");
        putValue(SHORT_DESCRIPTION, "Opens popup where you set author of your presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if (o instanceof MyTreeNode && ((MyTreeNode) o).getNode() instanceof Prezentacija) {

            Prezentacija prez = (Prezentacija) ((MyTreeNode) o).getNode();

            Object input = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesi autora", "Odabir autora", JOptionPane.QUESTION_MESSAGE);
            String autorStr = "";
            if (input != null) {
                    autorStr = input.toString();
            }
            System.out.println("Stari autor: " + prez.getAutor());
            if(!autorStr.equals("")){
                prez.setAutor(autorStr);
            }
            System.out.println("Novi autor: " + prez.getAutor());
            return;
        }
        System.out.println("Selektovan je " + ((MyTreeNode) o).getNode());

    }

}
