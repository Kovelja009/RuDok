package controller.state.concreteSlotState;

import controller.state.StateSlot;
import view.workspaceView.SlideView;
import view.workspaceView.SlotView;

import java.awt.*;
import java.util.List;

public class SelectState extends StateSlot {

    @Override
    public void mousePressed(int x, int y, int height, int width, SlideView slideView) {
        Point pos = new Point(x, y);

        List<SlotView> swList = slideView.getSlotViewList();
        for(int i = swList.size()-1; i >= 0; i--){
            if(swList.get(i).elementAt(pos)){
                slideView.setSelectedSlotView(swList.get(i), slideView);
                slideView.repaint();
                break;
            }
        }
    }

}
