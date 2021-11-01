package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PopupPozadinaAction extends AbstractRudokAction{
    public PopupPozadinaAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/background_popup.png"));
        putValue(NAME, "Presentation bckg");
        putValue(SHORT_DESCRIPTION, "Opens popup where you set background for your presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Implementirati PopupPozadinaAction");


    }
}
