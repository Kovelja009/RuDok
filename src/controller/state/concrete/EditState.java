package controller.state.concrete;

import controller.state.State;
import model.RuNode;
import model.workspace.Slide;
import view.MainFrame;
import view.PreviewPane;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EditState implements State {

    @Override
    public void changeState(List<RuNode> slideList) {
        MainFrame.getInstance().getContentPane().removeAll();
        MainFrame.getInstance().getContentPane().revalidate();
        MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().menuBarGetter());
        MainFrame.getInstance().getContentPane().add(MainFrame.getInstance().getEditModePanel());

        MainFrame.getInstance().repaint();
    }
}
