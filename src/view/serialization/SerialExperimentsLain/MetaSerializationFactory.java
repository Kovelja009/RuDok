package view.serialization.SerialExperimentsLain;

import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Workspace;
import view.tree.model.MyTreeNode;

public class MetaSerializationFactory {
    private static ProjectSaveFactory projFactory = new ProjectSaveFactory();
    private static PresentationSaveFactory presFactory = new PresentationSaveFactory();
    private static WorkspaceSaveFactory workFactory = new WorkspaceSaveFactory();

    public static SaveFactory returnSaveFactory(MyTreeNode saveTreeNode){
        if(saveTreeNode.getNode() instanceof Projekat)
            return projFactory;
        if(saveTreeNode.getNode() instanceof Prezentacija)
            return presFactory;
        if(saveTreeNode.getNode() instanceof Workspace)
            return workFactory;
        return null;
    }

    public static SaveFactory returnOpenFactory(MyTreeNode parentTree){
        if(parentTree == null)
            return workFactory;
        if(parentTree.getNode() instanceof Workspace)
            return projFactory;
        if(parentTree.getNode() instanceof  Projekat)
            return presFactory;
        return null;
    }
}
