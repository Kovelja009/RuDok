package controller.commands;

import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import view.MainFrame;

import javax.swing.*;

public class NameCommand extends AbstractCommand{
    private String oldName;
    private String newName;
    private RuNode node;

    public NameCommand(String oldName, String newName, RuNode node) {
        this.oldName = oldName;
        this.newName = newName;
        this.node = node;
    }

    @Override
    public void doCommand() {
        node.setName(newName);
        check(node);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());

    }

    @Override
    public void undoCommand() {
        node.setName(oldName);
        check(node);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());

    }

    private void check(RuNode node){
        if(node instanceof Projekat)
            ((Projekat)node).changingAction();
        if(node instanceof Prezentacija)
            ((Prezentacija)node).changingAction();
    }
}
