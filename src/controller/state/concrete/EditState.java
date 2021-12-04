package controller.state.concrete;

import controller.state.State;
import model.RuNode;
import model.workspace.Slide;
import view.MainFrame;
import view.PreviewPane;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditState implements State {

    @Override
    public void changeState() {
        int indexOfTab = MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedIndex();
        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getComponentAt(indexOfTab));

        pw.generateEditToolbar();
        pw.generateContentPaneEditMode();
    }
}
