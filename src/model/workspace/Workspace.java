package model.workspace;

import model.RuNode;
import model.RuNodeComposite;

import java.util.List;

public class Workspace extends RuNodeComposite {

    public Workspace(String name) {
        super(name, null);
    }

    @Override
    public void addChild(RuNode child, int broj) {
        if(child instanceof  Projekat && !getChildren().contains(child)){
        super.addChild(child, broj);
        }
    }

    @Override
    public void setParent(RuNode parent) {
        super.setParent(null);
    }

    @Override
    public void setChildren(List<RuNode> children) {
        for(RuNode child : children){
            if(!(child instanceof Projekat)){
                return;
            }
        }
        super.setChildren(children);
    }
}
