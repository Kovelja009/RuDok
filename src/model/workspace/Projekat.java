package model.workspace;

import controller.observers.Subsriber;
import model.RuNode;
import model.RuNodeComposite;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Projekat extends RuNodeComposite implements Serializable, Subsriber {
    public Projekat(String name, RuNode parent) {
        super(name, parent);
    }

    @Serial
    private Object readResolve(){
        setChanged(true);
        setShared(false);
        return this;
    }


    @Override
    public void addChild(RuNode child, int broj) {
        if(child instanceof Prezentacija && !getChildren().contains(child)){
            super.addChild(child, broj);
            child.addSubscriber(this);
            changingAction();
        }
    }

    @Override
    public void setParent(RuNode parent) {
        if(parent instanceof Workspace){
        super.setParent(parent);
        }
    }

    @Override
    public void removeChild(RuNode child) {
        super.removeChild(child);
        changingAction();
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

    @Override
    public void updateSubsriber(Object notification, String message) {
        if(!message.equals("ime taba") && (!message.equals("ime") && notification instanceof Prezentacija)){
            System.out.println("primljena poruka u projektu");
            changingAction();
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public void changingAction(){
        setChanged(true);
        if(!getName().endsWith("*"))
            setName(getName()+"*");
    }
}
