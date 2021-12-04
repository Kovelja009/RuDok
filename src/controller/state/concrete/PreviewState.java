package controller.state.concrete;

import com.sun.tools.javac.Main;
import controller.state.State;
import model.RuNode;
import model.workspace.Slide;
import view.MainFrame;
import view.PreviewPane;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PreviewState implements State {
    @Override
    public void changeState() {
        int indexOfTab = MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedIndex();
        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getComponentAt(indexOfTab));

        pw.generatePreviewToolbar();
        pw.generateContentPanePreviewMode();

    }
}
