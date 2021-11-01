package controller;

import view.MainFrame;

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

        Object input = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesi autora", "Odabir autora", JOptionPane.QUESTION_MESSAGE);
        String autor = "";
        if(input != null){
            try {
                autor = input.toString();

            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
        System.out.println("Autor: " + autor);
    }
}
