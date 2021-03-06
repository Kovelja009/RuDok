package model.workspace;

import controller.observers.Subsriber;
import model.RuNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Slide extends RuNode implements Serializable, Subsriber {
    private int redniBroj;
    private List<Slot> slotList;

    public Slide(String name, RuNode parent) {
        super(name, parent);
        slotList = new ArrayList<>();
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
        notifySubcribers(this, "redni broj");
    }

    public void addSlot(Slot slot){
        slotList.add(slot);
        notifySubcribers(slot, "dodavanje slota");
        slot.addSubscriber(this);

    }

    public void removeSlot(Slot slot){
        slotList.remove(slot);
        notifySubcribers(slot, "brisanje slota");
    }

    public List<Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<Slot> slotList) {
        this.slotList = slotList;
    }

    @Override
    public void updateSubsriber(Object notification, String message) {
        if(message.equals("content change")){
            notifySubcribers(this, "content change");
        }
    }
}
