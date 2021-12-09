package controller.state.concreteSlotState;

import controller.state.StateSlot;
import model.workspace.Slide;
import view.workspaceView.SlotView;

import java.util.List;

public class DefaultSlotState extends StateSlot {
    @Override
    public void mousePressed(int x, int y, int height, int widht, List<SlotView> slotViewList, Slide slide, int red, int green, int blue) {
        System.out.println("Default Slot State");
    }
}
