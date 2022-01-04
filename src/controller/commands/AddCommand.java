package controller.commands;

import model.RuNodeComposite;
import model.workspace.Prezentacija;
import model.workspace.Slide;
import view.MainFrame;
import view.factory.AbstractNodeFactory;
import view.factory.MetaFactory;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class AddCommand extends AbstractCommand{
    private MyTreeNode parentTreeNode;
    private int broj;
    private MyTreeNode childTreeNode;
    private List<MyTreeNode> presentations;
    private List<MyTreeNode> slides;

    public AddCommand(MyTreeNode roditeljTreeNode, int broj) {
        this.parentTreeNode = roditeljTreeNode;
        this.broj = broj;
        presentations = new ArrayList<>();
        slides = new ArrayList<>();
    }

    @Override
    public void doCommand() {
        if(childTreeNode == null){
            AbstractNodeFactory factory = MetaFactory.returnFactory(parentTreeNode.getNode());
            childTreeNode = new MyTreeNode(factory.getNode(parentTreeNode.getNode(), broj));
        }
        parentTreeNode.addChild(childTreeNode, broj - 1);
        childTreeNode.setParent(parentTreeNode);
        if(parentTreeNode.isShared())
            sharedAdding();
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
        if(parentTreeNode.isShared())
            sharedRemoving();

        MainFrame.getInstance().getMyTree().clearSelection();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }

    private void sharedAdding(){
        MyTreeNode workspaceTreeNode = (MyTreeNode)MainFrame.getInstance().getMyTree().getModel().getRoot();
        childTreeNode.setSharedNode(true);
        for(TreeNode project : workspaceTreeNode.getChildren()){
            for(TreeNode presentation : ((MyTreeNode)project).getChildren()){
                RuNodeComposite pres = ((RuNodeComposite)((MyTreeNode)presentation).getNode());
                if(pres.getChildren().contains(childTreeNode.getNode()) && !presentation.equals(parentTreeNode)){
                    MyTreeNode childTree = new MyTreeNode(childTreeNode.getNode());
                    childTree.setSharedNode(true);
                    ((MyTreeNode)presentation).addChild(childTree, broj);
                    presentations.add((MyTreeNode) presentation);
                    slides.add(childTree);
                    break;
                }
            }
        }
    }

    private void sharedRemoving(){
        MyTreeNode workspaceTreeNode = (MyTreeNode)MainFrame.getInstance().getMyTree().getModel().getRoot();
        int counter = 0;
        for(TreeNode project : workspaceTreeNode.getChildren()){
            for(TreeNode presentation : ((MyTreeNode)project).getChildren()){
                if(presentations.contains(presentation) && ((MyTreeNode)presentation).containSharing(childTreeNode)){
                    ((MyTreeNode)presentation).removeChild(slides.get(counter));
                    counter++;
                    break;
                }
            }
        }
    }
}
