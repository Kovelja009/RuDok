package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlotView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditorAction extends AbstractRudokAction{
    public EditorAction(){
        putValue(SMALL_ICON, loadIcon("../images/editor.png"));
        putValue(NAME, "Edit slot");
        putValue(SHORT_DESCRIPTION, "Slot editor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Slot editor");

        SlotView selected = ((PrezentacijaView) MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getSelectedSlotView();
        if(selected == null){
            ErrorFactory.getInstance().generateError("Must select slot to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MainFrame.getInstance().setCurrentDialog(selected);
        MainFrame.getInstance().getCurrentDialog().setModel(selected);
        MainFrame.getInstance().getCurrentDialog().setVisible(true);

    }

}
