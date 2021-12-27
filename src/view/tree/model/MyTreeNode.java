package view.tree.model;

import model.*;
import model.workspace.*;

import javax.swing.tree.DefaultMutableTreeNode;
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
            children.add(broj, child);
            ((RuNodeComposite)node).addChild(((MyTreeNode)child).getNode(), broj);
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
