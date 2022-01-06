package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.serialization.SerialExperimentsLain.MetaSerializationFactory;
import view.serialization.SerialExperimentsLain.SaveFactory;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveWorkspaceAction extends AbstractRudokAction{
    public SaveWorkspaceAction(){
        putValue(NAME, "yes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Saving..");
        SaveFactory sf = MetaSerializationFactory.returnSaveFactory((MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot());
        if(sf == null){
            return;
        }
            sf.save(((MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot()).getNode(), true);
            if(sf.isShouldSave())
                MainFrame.getInstance().getSw().setShouldSave(true);
            else
                MainFrame.getInstance().getSw().setShouldSave(false);
            MainFrame.getInstance().getSw().dispose();
    }
}
