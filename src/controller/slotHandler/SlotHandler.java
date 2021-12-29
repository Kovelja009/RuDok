package controller.slotHandler;

import view.workspaceView.SlotView;

import java.awt.*;

public abstract class SlotHandler {
    protected SlotView slotView;

    public SlotHandler(SlotView slotView) {
        this.slotView = slotView;
    }

    public abstract String readContent();
    public abstract void writeContent(String content);
    public abstract void paint(Graphics g);
}
