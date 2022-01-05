package model.workspace;

import model.RuNode;
import model.RuNodeComposite;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Projekat extends RuNodeComposite implements Serializable {
    public Projekat(String name, RuNode parent) {
        super(name, parent);
    }


    @Override
    public void addChild(RuNode child, int broj) {
        if(child instanceof Prezentacija && !getChildren().contains(child)){
            super.addChild(child, broj);
        }
    }

    @Override
    public void setParent(RuNode parent) {
        if(parent instanceof Workspace){
        super.setParent(parent);
        }
    }

    @Override
    public void setChildren(List<RuNode> children) {
        for(RuNode child : children){
            if(!(child instanceof Prezentacija)){
                return;
            }
        }
        super.setChildren(children);
    }
}
