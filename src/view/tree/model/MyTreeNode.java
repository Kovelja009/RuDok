package view.tree.model;

import model.*;
import model.workspace.*;
import view.MainFrame;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MyTreeNode implements TreeNode {
    private RuNode node;
    private List<TreeNode> children;
    private TreeNode parent;
    private List<TreeNode> shared;
    private boolean sharedNode = false;


    public MyTreeNode(RuNode ruNode){
        node = ruNode;
        children = new ArrayList<>();
        shared = new ArrayList<>();
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        if(childIndex >= 0 && childIndex < children.size()){
            return children.get(childIndex);
        }
        return null;
    }

    public void addChild(TreeNode child, int broj){
        if(node instanceof RuNodeComposite){
            if(broj > getChildCount())
                broj = getChildCount();
            if(!children.contains(child))
                children.add(broj, child);
            ((RuNodeComposite)node).addChild(((MyTreeNode)child).getNode(), broj);
        }
    }

    public void removeChild(TreeNode child){
            children.remove(child);
        if(node instanceof RuNodeComposite)
            ((RuNodeComposite) node).removeChild(((MyTreeNode)child).getNode());
        if(((MyTreeNode)child).isShared()){
            safeDeleting((MyTreeNode) child);
        }
    }

    public void safeDeleting(MyTreeNode child){
        MyTreeNode workspaceTreeNode = (MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot();
        if(child.getNode() instanceof Prezentacija){
            for(TreeNode project : workspaceTreeNode.getChildren()){
                if(((MyTreeNode)project).containSharing((MyTreeNode) child)){
                    ((MyTreeNode) project).removeShared((MyTreeNode) child, (MyTreeNode) project);
                    ((RuNodeComposite)((MyTreeNode) project).getNode()).removeChild(child.getNode());
                }

            }
        }
        if(child.getNode() instanceof Slide){
            for(TreeNode project : workspaceTreeNode.getChildren()){
                for(TreeNode presentation : ((MyTreeNode)project).getChildren()){
                    if(((MyTreeNode)presentation).containSharing(child)){
                        ((MyTreeNode) presentation).removeShared(child, (MyTreeNode) presentation);
                        ((RuNodeComposite)((MyTreeNode) presentation).getNode()).removeChild(child.getNode());
                    }
                }
            }
        }
    }

    public void removeShared(MyTreeNode myTreeNode, MyTreeNode parent){
        for(TreeNode dete : children){
            if(((MyTreeNode)dete).getNode() == myTreeNode.getNode()){
                children.remove(dete);
                ((RuNodeComposite)parent.getNode()).removeChild(myTreeNode.getNode());
                System.out.println("uklonjen share-ovan");
                break;
            }
        }
    }

    public MyTreeNode findShared(MyTreeNode myTreeNode){
        for(TreeNode dete : children){
            if(((MyTreeNode)dete).getNode() == myTreeNode.getNode()){
                return ((MyTreeNode)dete);
            }
        }
        return null;
    }

    public boolean isShared(){
        return node.isShared();
    }

    public void setSharedNode(boolean sharedNode) {
        this.sharedNode = sharedNode;
        node.setShared(sharedNode);
    }

    public boolean containSharing(MyTreeNode treeNode){
        for(TreeNode child : children){
            if(((MyTreeNode)child).getNode() == treeNode.getNode())
                return true;
        }
        return false;
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parentt){
        if(node instanceof Workspace)
            parent = null;
        if( node instanceof Projekat && ((MyTreeNode)parentt).getNode() instanceof Workspace ||
            node instanceof Prezentacija && ((MyTreeNode)parentt).getNode() instanceof Projekat ||
            node instanceof Slide && ((MyTreeNode)parentt).getNode() instanceof Prezentacija){
                parent = parentt;
                node.setParent(((MyTreeNode)parentt).getNode());
        }
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return node instanceof RuNodeComposite;
    }

    @Override
    public boolean isLeaf() {
        return node instanceof Slide;
    }

    @Override
    public Enumeration children() {
        return (Enumeration) children;
    }

    @Override
    public String toString() {
        return node.getName();
    }

    public RuNode getNode() {
        return node;
    }

    public void setNode(RuNode node) {
        this.node = node;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public List<TreeNode> getShared() {
        return shared;
    }
}
