package controller.state.concreteSlotState;

import controller.state.StateSlot;
import model.workspace.Slide;
import model.workspace.Slot;
import view.workspaceView.SlideView;
import view.workspaceView.SlotView;

import java.awt.*;
import java.util.List;

public class RemoveState extends StateSlot {
    @Override
    public void mousePressed(int x, int y, int height, int widht, List<SlotView> slotViewList, SlideView slideView, Slide slide, int red, int green, int blue) {

        Point p = new Point(x, y);

        Slot obrisi = null;
        for(int i = slotViewList.size() - 1; i >= 0; i--){
            if(slotViewList.get(i).elementAt(p)){
                obrisi = slotViewList.get(i).getSlot();
                System.out.print("brisanje slota ");
                break;
            }
        }

        slide.removeSlot(obrisi);
    }
}
