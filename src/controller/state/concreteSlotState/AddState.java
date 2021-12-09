package controller.state.concreteSlotState;

import controller.state.StateSlot;
import model.workspace.Slide;
import model.workspace.Slot;
import view.workspaceView.SlotView;

import java.util.List;

public class AddState extends StateSlot {
    @Override
    public void mousePressed(int x, int y, int height, int width, List<SlotView> slotViewList, Slide slide, int red, int green, int blue) {
        System.out.print("Dodavanje slota");
        Slot slot = new Slot(x, y, height, width, red, green, blue);
        slide.addSlot(slot);
    }
}
