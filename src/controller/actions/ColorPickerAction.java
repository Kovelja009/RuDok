package controller.actions;


import view.MainFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ColorPickerAction extends AbstractRudokAction{
    public ColorPickerAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/color-palette.png"));
        putValue(NAME, "Choose color");
        putValue(SHORT_DESCRIPTION, "Chooses color for the slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color c = JColorChooser.showDialog(MainFrame.getInstance(), "Choose a color", Color.magenta);

        if(c != null)
            MainFrame.getInstance().setColor(c);
    }
}
