package controller.state.concreteSlotState;

import controller.state.StateSlot;
import view.workspaceView.SlideView;

import java.awt.*;

public class RemoveState extends StateSlot {
    @Override
    public void mousePressed(int x, int y, int height, int width, SlideView slideView) {

        Point p = new Point(x, y);

        for(int i = slideView.getSlotViewList().size() - 1; i >= 0; i--){
            if(slideView.getSlotViewList().get(i).elementAt(p)){
                slideView.getSlideRuNode().removeSlot(slideView.getSlotViewList().get(i).getSlot());
                break;
            }
        }
    }
}
