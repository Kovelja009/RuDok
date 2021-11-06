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

        if(node instanceof Workspace && ((MyTreeNode)child).getNode() instanceof Projekat && !children.contains(child)){
            children.add(child);
            ((Workspace) node).addChild(((MyTreeNode)child).getNode());
        }
        else if(node instanceof Projekat && ((MyTreeNode)child).getNode() instanceof Prezentacija && !children.contains(child)){
            children.add(child);
            ((Projekat) node).addChild(((MyTreeNode)child).getNode());
        }
        else if(node instanceof Prezentacija && ((MyTreeNode)child).getNode() instanceof Slide && !children.contains(child)){
            children.add(child);
            ((Prezentacija) node).addChild(((MyTreeNode)child).getNode());
        }
        else{
        System.out.println("Nije moguce dodati " + child.toString() + " u MyTreeNode " + this);
        }

    }

    public void removeChild(TreeNode child){
        if(children.contains(child)){
            children.remove(child);
            if(node instanceof Workspace){
                ((Workspace)node).removeChild(((MyTreeNode)child).getNode());
            }
            if(node instanceof Projekat){
                ((Projekat)node).removeChild(((MyTreeNode)child).getNode());
            }
            if(node instanceof Prezentacija){
                ((Prezentacija)node).removeChild(((MyTreeNode)child).getNode());
            }
            return;
        }
        System.out.println(child.toString() + "nije dete " + this);
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
        if(node instanceof Workspace){
            parent = null;
        }
        else if(node instanceof Projekat && ((MyTreeNode)parentt).getNode() instanceof Workspace){
            parent = parentt;
            node.setParent(((MyTreeNode)parentt).getNode());
        }

        else if(node instanceof Prezentacija && ((MyTreeNode)parentt).getNode() instanceof Projekat){
            parent = parentt;
            node.setParent(((MyTreeNode)parentt).getNode());
        }

        else if(node instanceof Slide && ((MyTreeNode)parentt).getNode() instanceof Prezentacija){
            parent = parentt;
            node.setParent(((MyTreeNode)parentt).getNode());
        }
        else{
        System.out.println("Nije moguce setovati parenta " + parentt.toString() + " u MyTreeNode " + this);
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
