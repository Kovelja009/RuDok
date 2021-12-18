package controller.state.concreteSlotState;

import controller.state.StateSlot;
import model.workspace.Slot;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlideView;

import java.awt.*;

public class AddState extends StateSlot {
    @Override
    public void mousePressed(int x, int y, int height, int width, SlideView slideView) {
Color c = ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getColor();

        int strokeSize = ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getStrokeSize();
        int strokeType = ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getStrokeType();


        Slot slot = new Slot(x, y, height, width, c.getRed(), c.getGreen(), c.getBlue());
        slot.setStrokeSize(strokeSize);
        slot.setStrokeType(strokeType);
        slideView.getSlideRuNode().addSlot(slot);
    }
}
