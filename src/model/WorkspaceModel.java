package model;

public class WorkspaceModel {
    private Workspace root;

    public WorkspaceModel(Workspace root) {
        this.root = root;
    }

    public Workspace getRoot() {
        return root;
    }

    public void setRoot(Workspace root) {
        this.root = root;
    }
}
