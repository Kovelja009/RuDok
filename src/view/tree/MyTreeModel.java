package view.tree;

import model.Workspace;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class MyTreeModel extends DefaultTreeModel {

    public MyTreeModel() {
        super(new MyTreeNode(new Workspace("Workspace")));
    }


}
