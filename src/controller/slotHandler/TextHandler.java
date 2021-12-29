package controller.slotHandler;

import view.workspaceView.SlotView;

import java.awt.*;

public class TextHandler extends SlotHandler{

    public TextHandler(SlotView slotView) {
        super(slotView);
    }

    @Override
    public String readContent() {
        return slotView.getSlot().getText();
    }

    @Override
    public void writeContent(String content) {
        super.slotView.getSlot().setText(content);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(slotView.getSlot().getText(), slotView.getX() + 10, slotView.getY() + 10);
    }
}
