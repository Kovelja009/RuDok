package model;

import controller.observers.Publisher;
import controller.observers.Subsriber;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements Publisher {
    private String name;
    private RuNode parent;
    private List<Subsriber> listaSubscribera;

    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
        listaSubscribera = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifySubcribers(this, "ime");
    }

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    public List<Subsriber> getListaSubscribera() {
        return listaSubscribera;
    }

    public void setListaSubscribera(List<Subsriber> listaSubscribera) {
        this.listaSubscribera = listaSubscribera;
    }

    @Override
    public void addSubscriber(Subsriber subsriber) {
        if(!listaSubscribera.contains(subsriber)){
            listaSubscribera.add(subsriber);
            return;
        }
        System.out.println("Nije moguce dodati " + subsriber);
    }

    @Override
    public void removeSubscriber(Subsriber subsriber) {
        if(listaSubscribera.contains(subsriber)){
            listaSubscribera.remove(subsriber);
            return;
        }
        System.out.println("Nije moguce brisanje " + subsriber);
    }

    @Override
    public void removeAllSubcribers() {
        List<Subsriber> brisanje = new ArrayList<>(listaSubscribera);
        listaSubscribera.removeAll(brisanje);
    }

    @Override
    public synchronized void notifySubcribers(Object notification, String message) {
        System.out.println("Subscriberi");
        for(int i = 0; i < listaSubscribera.size(); i++){
            System.out.print(listaSubscribera.get(i).toString() + " ");
            listaSubscribera.get(i).updateSubsriber(notification, message);
        }
        System.out.println("\n");

    }
}
