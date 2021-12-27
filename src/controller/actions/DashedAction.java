package controller.actions;

import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DashedAction extends AbstractRudokAction{
    public DashedAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/dashed.png"));
        putValue(NAME, "Dashed stroke");
        putValue(SHORT_DESCRIPTION, "Chooses dashed stroke");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PrezentacijaView pw = ((PrezentacijaView) MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent());
        pw.setStrokeSize(pw.getSlider().getValue());
        pw.setStrokeType(1);
     }
}
