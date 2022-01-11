package view.serialization.SerialExperimentsLain;

import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Slide;
import view.tree.model.MyTreeNode;

public abstract class SaveFactory {
    protected boolean shouldSave = true;

    public void save(RuNode saveNode, boolean isClosing){}
    public void saveAs(RuNode saveNode){}
    public void open(MyTreeNode locTreeNode, boolean isContext){}

    protected void generateSlides(MyTreeNode presTreeNode){
        Prezentacija p = (Prezentacija) presTreeNode.getNode();
        for(RuNode s : p.getChildren()){
            Slide slide = (Slide) s;
            MyTreeNode slideTree = new MyTreeNode(slide);
            slideTree.setParent(presTreeNode);
            presTreeNode.addChild(slideTree, presTreeNode.getChildCount());
        }
    }

    protected void generatePresentations(MyTreeNode projTreeNode){
        Projekat p = (Projekat) projTreeNode.getNode();
        for(RuNode pr : p.getChildren()){
            Prezentacija prez = (Prezentacija) pr;
            prez.setChanged(true);
            MyTreeNode presTreeNode = new MyTreeNode(prez);
            projTreeNode.addChild(presTreeNode, projTreeNode.getChildCount());
            presTreeNode.setParent(projTreeNode);
            generateSlides(presTreeNode);
        }
    }

    public boolean isShouldSave() {
        return shouldSave;
    }
}
