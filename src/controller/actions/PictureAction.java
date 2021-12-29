package controller.actions;

import controller.errorHandler.ErrorFactory;
import model.workspace.Prezentacija;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PictureAction extends AbstractRudokAction{
    public PictureAction(){
        putValue(SMALL_ICON, loadIcon("../images/picture.png"));
        putValue(NAME, "Picture slot");
        putValue(SHORT_DESCRIPTION, "Starts picture state");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).startPictureState();
    }
}
