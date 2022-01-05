package controller.actions;

import view.MainFrame;

import java.awt.event.ActionEvent;

public class LoadWorkspaceAction extends AbstractRudokAction{
    public LoadWorkspaceAction(){
        putValue(NAME, "yes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Loading..");
        MainFrame.getInstance().getOw().dispose();
    }
}
