package view.tree;

import model.RuNode;
import model.RuNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class MyTreeNode extends DefaultMutableTreeNode {
    private RuNode node;

    public MyTreeNode(RuNode ruNode){
        node = ruNode;
    }
// da li treba override-ovati sve metode iz DefaultMutableTreeNode tako da odgovaraju nasem modelu ili ne?

    @Override
    public boolean getAllowsChildren() {
        return node instanceof RuNodeComposite;
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
