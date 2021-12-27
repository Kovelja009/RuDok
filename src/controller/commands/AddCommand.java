package controller.commands;

import model.workspace.Prezentacija;
import model.workspace.Slide;
import view.MainFrame;
import view.factory.AbstractNodeFactory;
import view.factory.MetaFactory;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.TreePath;

public class AddCommand extends AbstractCommand{
    private MyTreeNode parentTreeNode;
    private int broj;
    private MyTreeNode childTreeNode;

    public AddCommand(MyTreeNode roditeljTreeNode, int broj) {
        this.parentTreeNode = roditeljTreeNode;
        this.broj = broj;
    }

    @Override
    public void doCommand() {
        if(childTreeNode == null){
            AbstractNodeFactory factory = MetaFactory.returnFactory(parentTreeNode.getNode());
            childTreeNode = new MyTreeNode(factory.getNode(parentTreeNode.getNode(), broj));
        }
        parentTreeNode.addChild(childTreeNode, broj - 1);
        childTreeNode.setParent(parentTreeNode);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }

    @Override
    public void undoCommand() {
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
}
