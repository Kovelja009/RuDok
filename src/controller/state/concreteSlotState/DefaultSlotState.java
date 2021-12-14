package controller.state.concreteSlotState;

import controller.errorHandler.ErrorFactory;
import controller.state.StateSlot;
import model.workspace.Slide;
import view.workspaceView.SlideView;
import view.workspaceView.SlotView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefaultSlotState extends StateSlot {

    @Override
    public void mousePressed(int x, int y, int height, int widht, List<SlotView> slotViewList, SlideView slideView, Slide slide, int red, int green, int blue) {
        Point pos = new Point(x, y);

        slideView.setSelectedSlotView(null);

        for(SlotView sw : slotViewList){
            if(sw.elementAt(pos))
                slideView.setSelectedSlotView(sw);

        }
    }

    @Override
    public void mouseDragged(int x, int y, int height, int widht, List<SlotView> slotViewList, SlideView slideView, Slide slide) {
        if(slideView.getSelectedSlotView() == null)
            return;
        slideView.getSelectedSlotView().getSlot().setPos(x, y);
    }

}
