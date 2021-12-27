package controller.commands;

import model.RuNode;
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
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());

    }

    @Override
    public void undoCommand() {
        node.setName(oldName);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());

    }
}
