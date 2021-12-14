package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DragSlotAction extends AbstractRudokAction{
    public DragSlotAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_7, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/movement.png"));
        putValue(NAME, "Mouse drag");
        putValue(SHORT_DESCRIPTION, "Enters drag slot mode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent());

        pw.startDragState();
    }
}
