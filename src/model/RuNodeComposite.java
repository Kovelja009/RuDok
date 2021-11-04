package model;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {
    private List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    public void addChild(RuNode child){
        if(!children.contains(child)){
            children.add(child);
        }
    }

    public void removeChild(RuNode child){
        children.remove(child);
    }

    public List<RuNode> getChildren() {
        return children;
    }

    public void setChildren(List<RuNode> children) {
        this.children = children;
    }
}
