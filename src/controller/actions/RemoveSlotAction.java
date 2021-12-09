package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveSlotAction extends AbstractRudokAction{
    public RemoveSlotAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/deleteSlot.png"));
        putValue(NAME, "Remove Slot");
        putValue(SHORT_DESCRIPTION, "Enters Remove slot mode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int indexOfTab = MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedIndex();

        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getComponentAt(indexOfTab));
        if(pw.getSlideViewList().size() == 0){
            ErrorFactory.getInstance().generateError("Must select presentation with at least one slide", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
         pw.startRemoveState();
    }
}
