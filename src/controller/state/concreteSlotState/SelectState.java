package controller.state.concreteSlotState;

import controller.state.StateSlot;
import view.workspaceView.SlideView;
import view.workspaceView.SlotView;

import java.awt.*;

public class SelectState extends StateSlot {

    @Override
    public void mousePressed(int x, int y, int height, int width, SlideView slideView) {
        Point pos = new Point(x, y);

        for(SlotView sw : slideView.getSlotViewList()){
            if(sw.elementAt(pos))
                slideView.setSelectedSlotView(sw);
        }
    }

}
