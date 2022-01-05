package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveWorkspaceAction extends AbstractRudokAction{
    public SaveWorkspaceAction(){
        putValue(NAME, "yes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Saving..");
        MainFrame.getInstance().getSw().dispose();
    }
}
