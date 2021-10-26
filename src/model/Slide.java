package model;

public class Slide extends RuNode{

    public Slide(String name, RuNodeComposite parent) {
        super(name, parent);
    }

    @Override
    public void setParent(RuNodeComposite parent) {
        if(parent instanceof Prezentacija){
            super.setParent(parent);
        }
    }
}
