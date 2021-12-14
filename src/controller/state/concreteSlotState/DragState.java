package controller.state.concreteSlotState;

import controller.errorHandler.ErrorFactory;
import controller.state.StateSlot;
import view.workspaceView.SlideView;

import javax.swing.*;

public class DragState extends StateSlot {
    @Override
    public void mouseDragged(int x, int y, SlideView slideView) {
        if(slideView.getSelectedSlotView() == null){
            ErrorFactory.getInstance().generateError("Must select slot to drag!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        slideView.getSelectedSlotView().getSlot().setPos(x, y);
    }
}
