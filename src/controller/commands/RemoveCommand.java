package controller.commands;

import model.RuNodeComposite;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
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
    private boolean safeDel = false;
    private MyTreeNode safeDelNode = null;

    public RemoveCommand(MyTreeNode parentTreeNode, MyTreeNode childTreeNode) {
        this.parentTreeNode = parentTreeNode;
        this.childTreeNode = childTreeNode;
        broj = ((RuNodeComposite)childTreeNode.getNode().getParent()).getChildren().indexOf(childTreeNode.getNode());
        removedShared = new ArrayList<>();
        presentations = new ArrayList<>();
        indexi = new ArrayList<>();
    }

    @Override
    public void doCommand() {
        if(childTreeNode.getNode().equals(MainFrame.getInstance().getMainProjectView().getProjekatRuNode()))
            MainFrame.getInstance().getMainProjectView().setProjekatRuNode(null);

        projCheck();
        if(childTreeNode.isShared())
            deleteShared();
        else{
            if(safeDel)
                safeDel();
            parentTreeNode.removeChild(childTreeNode);
        }

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
            for(MyTreeNode project : removedShared)
                project.addChild(presentations.get(removedShared.indexOf(project)), indexi.get(removedShared.indexOf(project)));
        }else{
            if(safeDel)
                safeRet();
            parentTreeNode.addChild(childTreeNode, broj);
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }

    private void deleteShared(){
        MyTreeNode workspaceTreeNode = (MyTreeNode)MainFrame.getInstance().getMyTree().getModel().getRoot();

        if(childTreeNode.getNode() instanceof Slide)
            removeSlide(workspaceTreeNode);
        else
            removePresentation(workspaceTreeNode);
    }

    private void removeSlide(MyTreeNode workspaceTreeNode){
        for(TreeNode project : workspaceTreeNode.getChildren()){
            for(TreeNode presentation : ((MyTreeNode)project).getChildren()){
                if(((MyTreeNode)presentation).containSharing(childTreeNode)){
                    presentations.add(((MyTreeNode) presentation).findShared(childTreeNode));
                    indexi.add(((MyTreeNode)presentation).getChildren().indexOf(((MyTreeNode) presentation).findShared(childTreeNode)));
                    removedShared.add((MyTreeNode) presentation);
                }
            }
        }

        for(TreeNode project : workspaceTreeNode.getChildren()){
            for(TreeNode presentation : ((MyTreeNode)project).getChildren()){
                if(((MyTreeNode)presentation).containSharing(childTreeNode)){
                    ((MyTreeNode) presentation).removeChild(((MyTreeNode) presentation).findShared(childTreeNode));
                }
            }
        }
    }

    private void removePresentation(MyTreeNode workspaceTreeNode){
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

    private void projCheck(){
        if(childTreeNode.getNode() instanceof Projekat){
            for(TreeNode prez : childTreeNode.getChildren()){
                if(((MyTreeNode)prez).isShared()){
                    safeDelNode = (MyTreeNode)prez;
                    safeDel = true;
                    return;
                }
            }
        }
        safeDel = false;
    }

    private void safeDel(){
        MyTreeNode workspaceTreeNode = (MyTreeNode)MainFrame.getInstance().getMyTree().getModel().getRoot();

        for(TreeNode project : workspaceTreeNode.getChildren()){
            if(((MyTreeNode)project).containSharing(safeDelNode)){
                presentations.add(((MyTreeNode) project).findShared(safeDelNode));
                indexi.add(((MyTreeNode) project).getChildren().indexOf(((MyTreeNode) project).findShared(safeDelNode)));
                removedShared.add((MyTreeNode) project);
            }
        }
        for(TreeNode project : workspaceTreeNode.getChildren()){
            if(((MyTreeNode)project).containSharing(safeDelNode)){
                ((MyTreeNode) project).removeChild(safeDelNode);
            }
        }
    }

    private void safeRet(){
        for(MyTreeNode project : removedShared)
            project.addChild(presentations.get(removedShared.indexOf(project)), indexi.get(removedShared.indexOf(project)));
    }
}


