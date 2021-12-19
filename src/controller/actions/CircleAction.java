package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CircleAction extends AbstractRudokAction{
    public CircleAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/circle.png"));
        putValue(NAME, "Solid stroke");
        putValue(SHORT_DESCRIPTION, "Chooses solid stroke");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PrezentacijaView pw = ((PrezentacijaView) MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent());
        pw.setStrokeSize(pw.getSlider().getValue());
        pw.setStrokeType(0);

    }
}
