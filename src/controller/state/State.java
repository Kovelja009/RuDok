package controller.state;

import model.RuNode;
import model.workspace.Slide;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.util.List;

public interface State {
    void changeState(List<RuNode> slideList);
}
