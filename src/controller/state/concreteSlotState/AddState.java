package controller.state.concreteSlotState;

import controller.state.StateSlot;
import model.workspace.Slide;
import model.workspace.Slot;
import view.workspaceView.SlotView;

import java.util.List;

public class AddState implements StateSlot {
    @Override
    public void changeSlot(int x, int y, int height, int widht, List<SlotView> slotViewList, Slide slide, int red, int green, int blue) {
        System.out.printf("Dodavanje slota");
        Slot slot = new Slot(x, y, height, widht, red, green, blue);
        slide.addSlot(slot);
    }
}
