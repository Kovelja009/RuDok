package controller;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

// resiti problem images not found

public class InfoAction extends AbstractRudokAction{
    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/info_img.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "About me");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ImageIcon imageIcon = new ImageIcon("images/about_me.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(200, 120,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);  // transform it back

        String podaci = String.format("Vanja Kovinic\n4220RN");

        JOptionPane.showMessageDialog(MainFrame.getInstance(), podaci, "Podaci o meni", JOptionPane.INFORMATION_MESSAGE,imageIcon);
    }
}
