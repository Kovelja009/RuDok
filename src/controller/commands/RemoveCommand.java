package controller.commands;

import model.RuNodeComposite;
import model.workspace.Prezentacija;
import model.workspace.Slide;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class RemoveCommand extends  AbstractCommand{
    private MyTreeNode parentTreeNode;
    private MyTreeNode childTreeNode;
    private int broj;
    private List<MyTreeNode> removedShared;
    private List<MyTreeNode> presentations;
    private List<Integer> indexi;

    public RemoveCommand(MyTreeNode parentTreeNode, MyTreeNode childTreeNode) {
        this.parentTreeNode = parentTreeNode;
        this.childTreeNode = childTreeNode;
        broj = ((RuNodeComposite)childTreeNode.getNode().getParent()).getChildren().indexOf(childTreeNode.getNode());
        removedShared = new ArrayList<>();
        presentations = new ArrayList<>();
        indexi = new ArrayList<>();
    }
//  implementirati i kada obrisemo ceo projekat
    @Override
    public void doCommand() {
        if(childTreeNode.getNode().equals(MainFrame.getInstance().getMainProjectView().getProjekatRuNode()))
            MainFrame.getInstance().getMainProjectView().setProjekatRuNode(null);

        if(childTreeNode.isShared())
            deleteShared();
        else
            parentTreeNode.removeChild(childTreeNode);

        if(childTreeNode.getNode() instanceof Slide){
            Prezentacija prez = (Prezentacija) childTreeNode.getNode().getParent();
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
        if(childTreeNode.isShared()){
            MyTreeNode workspaceTreeNode = (MyTreeNode)MainFrame.getInstance().getMyTree().getModel().getRoot();
            for(MyTreeNode project : removedShared)
                project.addChild(presentations.get(removedShared.indexOf(project)), indexi.get(removedShared.indexOf(project)));
        }else
            parentTreeNode.addChild(childTreeNode, broj);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }

    private void deleteShared(){
        MyTreeNode workspaceTreeNode = (MyTreeNode)MainFrame.getInstance().getMyTree().getModel().getRoot();

        for(TreeNode project : workspaceTreeNode.getChildren()){
            if(((MyTreeNode)project).containSharing(childTreeNode)){
                presentations.add(((MyTreeNode) project).findShared(childTreeNode));
                indexi.add(((MyTreeNode) project).getChildren().indexOf(((MyTreeNode) project).findShared(childTreeNode)));
                removedShared.add((MyTreeNode) project);
            }
        }
        for(TreeNode project : workspaceTreeNode.getChildren()){
            if(((MyTreeNode)project).containSharing(childTreeNode)){
                ((MyTreeNode) project).removeChild(childTreeNode);
            }
        }
    }
}


