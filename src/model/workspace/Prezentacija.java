package model.workspace;

import model.RuNode;
import model.RuNodeComposite;

import java.util.List;

public class Prezentacija extends RuNodeComposite {
    private String autor = "Unknown author";
    private String urlPozadina;

    public Prezentacija(String name, RuNode parent) {
        super(name, parent);
        urlPozadina = "../../controller/images/background2.jpg";
    }

    @Override
    public void addChild(RuNode child) {
        if(child instanceof Slide && !getChildren().contains(child)){
            super.addChild(child);
            System.out.println("sdafsdf");
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
        notifySubcribers(this, "promena autora");
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
    }
}