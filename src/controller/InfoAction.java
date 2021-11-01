package controller;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class InfoAction extends AbstractRudokAction{
    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/info_img.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "About me");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ImageIcon imageIcon = new ImageIcon("src/controller/images/about_me.png");
        String podaci = "Vanja Kovinic\n4220RN";

        JOptionPane.showMessageDialog(MainFrame.getInstance(), podaci, "Podaci o meni", JOptionPane.INFORMATION_MESSAGE,imageIcon);
    }
}
