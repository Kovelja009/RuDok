package controller.state.concreteSlotState;

import controller.state.StateSlot;
import model.workspace.Slide;
import model.workspace.Slot;
import view.workspaceView.SlotView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveState implements StateSlot {
    @Override
    public void changeSlot(int x, int y, int height, int widht, List<SlotView> slotViewList, Slide slide, int red, int green, int blue) {

        Point p = new Point(x, y);

        Slot obrisi = null;
        for(SlotView sw : slotViewList){
            if(sw.elementAt(p)){
                obrisi = sw.getSlot();
                System.out.printf("brisanje slota ");
            }
        }

        slide.removeSlot(obrisi);
    }
}
