package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectSlotAction extends AbstractRudokAction{
    public SelectSlotAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/cursor.png"));
        putValue(NAME, "Mouse Click");
        putValue(SHORT_DESCRIPTION, "Enters select slot mode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int indexOfTab = MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedIndex();

        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getComponentAt(indexOfTab));
        if(pw.getSlideViewList().size() == 0){
            ErrorFactory.getInstance().generateError("Must select presentation with at least one slide", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
          pw.startSelectState();
    }
}
