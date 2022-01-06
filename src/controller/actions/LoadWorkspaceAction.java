package controller.actions;

import view.MainFrame;
import view.serialization.SerialExperimentsLain.MetaSerializationFactory;
import view.serialization.SerialExperimentsLain.SaveFactory;

import java.awt.event.ActionEvent;

public class LoadWorkspaceAction extends AbstractRudokAction{
    public LoadWorkspaceAction(){
        putValue(NAME, "from memory");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Loading..");
        SaveFactory sf = MetaSerializationFactory.returnOpenFactory(null);
        if(sf != null)
            sf.open(null, false);
        MainFrame.getInstance().getOw().dispose();
    }
}
