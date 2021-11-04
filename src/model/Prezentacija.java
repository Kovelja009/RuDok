package model;

import java.util.List;

public class Prezentacija extends RuNodeComposite{
    private String autor;
    private String urlPozadina;

    public Prezentacija(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof  Slide && !getChildren().contains(child)){
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
    }

    public String getUrlPozadina() {
        return urlPozadina;
    }

    public void setUrlPozadina(String urlPozadina) {
        this.urlPozadina = urlPozadina;
    }
}
