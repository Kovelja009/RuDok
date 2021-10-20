package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractRudokAction{
    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/info_img.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "About me");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Treba implementirati InfoAction Performed");
    }
}
