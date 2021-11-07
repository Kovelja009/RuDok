package model.workspace;

import model.RuNode;
import model.RuNodeComposite;

import java.util.List;

public class Prezentacija extends RuNodeComposite {
    private String autor = "Unknown author";
    private String urlPozadina;

    public Prezentacija(String name, RuNode parent) {
        super(name, parent);
        urlPozadina = "../../controller/images/background1.jpg";
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Slide && !getChildren().contains(child)){
            super.addChild(child);
        }
    }

    @Override
    public void setParent(RuNode parent) {
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {

        this.autor = autor;
        notifySubscribers(this, "promena autora");
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        ((Projekat)(getParent())).sasvimObicnaEmisija(this);
    }

    public String getUrlPozadina() {
        return urlPozadina;
    }

    public void setUrlPozadina(String urlPozadina) {
        this.urlPozadina = urlPozadina;
        this.notifySubscribers(urlPozadina, "promena pozadine");
    }
}
