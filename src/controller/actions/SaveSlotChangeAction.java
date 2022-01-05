package controller.actions;

import view.MainFrame;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlotView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveSlotChangeAction extends AbstractRudokAction{
    public SaveSlotChangeAction(){
        putValue(SMALL_ICON, loadIcon("../images/saveSlotChange.png"));
        putValue(NAME, "Save change");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SlotView selected = ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getSelectedSlotView();
        selected.getSlotHandler().writeContent(MainFrame.getInstance().getCurrentDialog().getData());
        MainFrame.getInstance().getCurrentDialog().setVisible(false);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }
}
