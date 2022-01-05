package view.serialization.SerialExperimentsLain;

import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import view.tree.model.MyTreeNode;

public abstract class SaveFactory {
    public abstract void save(RuNode saveNode);
    public abstract void saveAs(RuNode saveNode);
    public abstract void open(MyTreeNode locTreeNode);

    protected void generateSlides(MyTreeNode presTreeNode){
        Prezentacija p = (Prezentacija) presTreeNode.getNode();
        for(RuNode s : p.getChildren()){
            MyTreeNode slideTree = new MyTreeNode(s);
            presTreeNode.getChildren().add(slideTree);
            slideTree.setParent(presTreeNode);
        }
    }

    protected void generatePresentations(MyTreeNode projTreeNode){
        Projekat p = (Projekat) projTreeNode.getNode();
        for(RuNode pr : p.getChildren()){
            MyTreeNode presTreeNode = new MyTreeNode(pr);
            projTreeNode.getChildren().add(presTreeNode);
            presTreeNode.setParent(projTreeNode);
            generateSlides(presTreeNode);
        }
    }
}
