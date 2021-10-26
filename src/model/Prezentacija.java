package model;

import java.util.List;

public class Prezentacija extends RuNodeComposite{

    public Prezentacija(String name, RuNodeComposite parent) {
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof  Slide && !getChildren().contains(child)){
            super.addChild(child);
        }
    }

    @Override
    public void setParent(RuNodeComposite parent) {
        if(parent instanceof Projekat){
            super.setParent(parent);
        }
    }

    @Override
    public void setChildren(List<RuNode> children) {
        for(RuNode child : children){
            if(!(child instanceof Slide)){
                return;
            }
        }
        super.setChildren(children);
    }
}
