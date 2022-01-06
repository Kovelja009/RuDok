package model;

import controller.observers.Publisher;
import controller.observers.Subsriber;

import javax.swing.event.EventListenerList;
import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements Publisher, Serializable {
    private String name;
    private RuNode parent;
    private transient List<Subsriber> listaSubscribera;
    private boolean shared = false;
    private File file;
    private transient boolean changed;

    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
        listaSubscribera = new ArrayList<>();
        changed = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifySubcribers(this, "ime");
        System.out.println("poslata notif name");
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

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    @Override
    public void addSubscriber(Subsriber subsriber) {
        if(listaSubscribera == null)
            listaSubscribera = new ArrayList<>();
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
        if(listaSubscribera == null)
            listaSubscribera = new ArrayList<>();
        for(int i = 0; i < listaSubscribera.size(); i++)
            listaSubscribera.get(i).updateSubsriber(notification, message);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
