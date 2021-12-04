package controller.state;

import model.workspace.Slide;
import view.workspaceView.SlotView;

import java.util.List;

public interface StateSlot {
    void changeSlot(int x, int y, int height, int widht, List<SlotView> slotViewList, Slide slide, int red, int green, int blue);
}
