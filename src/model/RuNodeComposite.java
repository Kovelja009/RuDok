package model;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {
    private List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    public void addChild(RuNode child, int broj){
        if(!children.contains(child)){
            System.out.println("Dodavanje na nivou RUNODA: " + child.getName());
            children.add(broj,child);
        }
        notifySubcribers(child, "dodavanje");
    }

    public void removeChild(RuNode child){
        System.out.println("Brisanje na nivou RUNODA: " + child.getName());
        children.remove(child);
        notifySubcribers(child, "brisanje");
    }

    public List<RuNode> getChildren() {
        return children;
    }

    public void setChildren(List<RuNode> children) {
        this.children = children;
    }
}
