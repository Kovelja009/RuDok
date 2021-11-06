package view.tree.model;

import model.workspace.Workspace;

import javax.swing.tree.DefaultTreeModel;

public class MyTreeModel extends DefaultTreeModel {

    public MyTreeModel() {
        super(new MyTreeNode(new Workspace("Workspace")));
    }


}
