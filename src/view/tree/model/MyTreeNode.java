package view.tree.model;

import model.*;
import model.workspace.*;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MyTreeNode implements TreeNode {
    private RuNode node;
    private List<TreeNode> children;
    private TreeNode parent;


    public MyTreeNode(RuNode ruNode){
        node = ruNode;
        children = new ArrayList<>();
    }
// da li treba override-ovati sve metode iz DefaultMutableTreeNode tako da odgovaraju nasem modelu ili ne?


    @Override
    public TreeNode getChildAt(int childIndex) {
        if(childIndex >= 0 && childIndex < children.size()){
            return children.get(childIndex);
        }

        System.out.println("Nije moguce pristupiti indexu " + childIndex + " TreeNoda " + this);
        return null;
    }

    public void addChild(TreeNode child){
        if(node instanceof RuNodeComposite){
            children.add(child);
            ((RuNodeComposite)node).addChild(((MyTreeNode)child).getNode());
        }
    }

    public void removeChild(TreeNode child){
            children.remove(child);
            if(node instanceof RuNodeComposite)
                ((RuNodeComposite) node).removeChild(((MyTreeNode)child).getNode());
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

//        else if(node instanceof Prezentacija && ((MyTreeNode)parentt).getNode() instanceof Projekat){
//            parent = parentt;
//            node.setParent(((MyTreeNode)parentt).getNode());
//        }
//
//        else if(node instanceof Slide && ((MyTreeNode)parentt).getNode() instanceof Prezentacija){
//            parent = parentt;
//            node.setParent(((MyTreeNode)parentt).getNode());
//        }
//        else{
//        System.out.println("Nije moguce setovati parenta " + parentt.toString() + " u MyTreeNode " + this);
//        }
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
}
