package model;

public class Slide extends RuNode{
    private int redniBroj;

    public Slide(String name, RuNodeComposite parent) {
        super(name, parent);
    }

    @Override
    public void setParent(RuNodeComposite parent) {
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
