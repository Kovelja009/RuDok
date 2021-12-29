package controller.actions;

import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import java.awt.event.ActionEvent;

public class TextAction extends AbstractRudokAction{
    public TextAction(){
        putValue(SMALL_ICON, loadIcon("../images/text.png"));
        putValue(NAME, "Text slot");
        putValue(SHORT_DESCRIPTION, "Starts text state");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((PrezentacijaView) MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).startTextState();
    }
}
