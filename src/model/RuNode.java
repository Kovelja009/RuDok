package model;

public abstract class RuNode {
    private String name;
    private RuNodeComposite parent;

    public RuNode(String name, RuNodeComposite parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuNodeComposite getParent() {
        return parent;
    }

    public void setParent(RuNodeComposite parent) {
        this.parent = parent;
    }
}
