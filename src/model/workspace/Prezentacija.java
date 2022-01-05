package model.workspace;

import controller.observers.Subsriber;
import model.RuNode;
import model.RuNodeComposite;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prezentacija extends RuNodeComposite implements Serializable, Subsriber {
    private String autor = "unknown";
    private String urlPozadina;


    public Prezentacija(String name, RuNode parent) {
        super(name, parent);
        urlPozadina = "../../controller/images/background2.jpg";
    }

    @Serial
    private Object readResolve(){
        String ime = getName();
        if(ime.endsWith("*"))
            setName(ime.substring(0, ime.toCharArray().length - 1));

        setChanged(false);
        setShared(false);
        return this;
    }

    @Override
    public void addChild(RuNode child, int broj) {
        if(child instanceof Slide && !getChildren().contains(child)){
            super.addChild(child, broj);
            child.addSubscriber(this);
            changingAction();
        }
    }

    @Override
    public void setParent(RuNode parent) {
        if(parent instanceof Projekat){
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
            if(!(child instanceof Slide)){
                return;
            }
        }
        super.setChildren(children);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {

        this.autor = autor;
        notifySubcribers(this, "promena autora");
        changingAction();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notifySubcribers(this, "ime taba");
    }

    public String getUrlPozadina() {
        return urlPozadina;
    }

    public void setUrlPozadina(String urlPozadina) {
        this.urlPozadina = urlPozadina;
        this.notifySubcribers(urlPozadina, "promena pozadine");
        changingAction();
    }

    @Override
    public void updateSubsriber(Object notification, String message) {
        changingAction();
    }

    public void changingAction(){
        setChanged(true);
        if(!getName().endsWith("*"))
            setName(getName()+"*");
        notifySubcribers(this, "changing");
    }
}
