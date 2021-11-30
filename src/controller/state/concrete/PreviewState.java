package controller.state.concrete;

import com.sun.tools.javac.Main;
import controller.state.State;
import model.RuNode;
import model.workspace.Slide;
import view.MainFrame;
import view.PreviewPane;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PreviewState implements State {
    @Override
    public void changeState(List<RuNode> slideList) {

        MainFrame.getInstance().getContentPane().removeAll();
        MainFrame.getInstance().getContentPane().revalidate();

        ((PreviewPane)MainFrame.getInstance().getPreviewModePanel()).setSlides(slideList);

        MainFrame.getInstance().setJMenuBar(null);
        MainFrame.getInstance().getContentPane().add(MainFrame.getInstance().getPreviewModePanel());

        MainFrame.getInstance().repaint();

    }
}
