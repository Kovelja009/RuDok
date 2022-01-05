package controller.actions;

import controller.errorHandler.ErrorFactory;
import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import view.MainFrame;
import view.serialization.PresentationFIleFilter;
import view.serialization.ProjectFileFilter;
import view.serialization.SerialExperimentsLain.MetaSerializationFactory;
import view.serialization.SerialExperimentsLain.SaveFactory;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class SaveAction extends AbstractRudokAction{
    public SaveAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/saveSlotChange.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Saving model");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(!(o instanceof MyTreeNode)){
            ErrorFactory.getInstance().generateError("Must select project or presentation from the tree", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        MyTreeNode myTreeNode = (MyTreeNode) o;

        SaveFactory saveFactory = MetaSerializationFactory.returnSaveFactory(myTreeNode);
        if(saveFactory == null){
            ErrorFactory.getInstance().generateError("Must select either project or presentation", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        saveFactory.save(myTreeNode.getNode());
    }

}
