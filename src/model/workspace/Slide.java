package model.workspace;

import model.RuNode;

public class Slide extends RuNode {
    private int redniBroj;

    public Slide(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void setParent(RuNode parent) {
        if(parent instanceof Prezentacija){
            super.setParent(parent);
        }
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }
}
