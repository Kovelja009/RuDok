package controller.state.concreteState;

import controller.state.State;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;

public class EditState implements State {

    @Override
    public void changeState() {
        int indexOfTab = MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedIndex();
        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getComponentAt(indexOfTab));

        pw.generateEditToolbar();
        pw.generateContentPaneEditMode();
    }
}
