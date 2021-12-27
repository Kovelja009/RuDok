package controller.commands;

import model.RuNodeComposite;
import model.workspace.Prezentacija;
import model.workspace.Slide;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.TreePath;

public class RemoveCommand extends  AbstractCommand{
    private MyTreeNode parentTreeNode;
    private MyTreeNode childTreeNode;
    private int broj;

    public RemoveCommand(MyTreeNode parentTreeNode, MyTreeNode childTreeNode) {
        this.parentTreeNode = parentTreeNode;
        this.childTreeNode = childTreeNode;
        broj = ((RuNodeComposite)parentTreeNode.getNode()).getChildren().indexOf(childTreeNode.getNode());
    }

    @Override
    public void doCommand() {
        if(childTreeNode.getNode().equals(MainFrame.getInstance().getMainProjectView().getProjekatRuNode()))
            MainFrame.getInstance().getMainProjectView().setProjekatRuNode(null);

        parentTreeNode.removeChild(childTreeNode);

        if(childTreeNode.getNode() instanceof Slide){
            Prezentacija prez = (Prezentacija) parentTreeNode.getNode();
            for(int i = 0; i < prez.getChildren().size(); i++){
                if(prez.getChildren().get(i) instanceof Slide)
                    ((Slide)prez.getChildren().get(i)).setRedniBroj(i + 1);
            }
        }
        MainFrame.getInstance().getMyTree().clearSelection();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }

    @Override
    public void undoCommand() {
        parentTreeNode.addChild(childTreeNode, broj);
        childTreeNode.setParent(parentTreeNode);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }
}
