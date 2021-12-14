package controller.state;

import model.workspace.Slide;
import view.workspaceView.SlideView;
import view.workspaceView.SlotView;

import java.util.List;

public abstract class StateSlot {
    public void mousePressed(int x, int y, int height, int widht, List<SlotView> slotViewList, SlideView slideView, Slide slide, int red, int green, int blue){}
    public void mouseDragged(int x, int y, int height, int widht, List<SlotView> slotViewList, SlideView slideView, Slide slide){}
}
