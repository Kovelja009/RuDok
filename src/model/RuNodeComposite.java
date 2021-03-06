package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode implements Serializable {
    private List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    public void addChild(RuNode child, int broj){
        if(!children.contains(child)){
            children.add(broj,child);
        }
        notifySubcribers(child, "dodavanje");
        System.out.println("poslata notif add");
    }

    public void removeChild(RuNode child){
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
