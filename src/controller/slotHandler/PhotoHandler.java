package controller.slotHandler;

import view.MainFrame;
import view.workspaceView.SlotView;

import javax.swing.*;
import java.awt.*;

public class PhotoHandler extends SlotHandler{
    public PhotoHandler(SlotView slotView) {
        super(slotView);
    }

    @Override
    public String readContent() {
        return slotView.getSlot().getText();
    }

    @Override
    public void writeContent(Object content) {
        super.slotView.getSlot().setText((String) content);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }

    @Override
    public void paint(Graphics g) {
        Image img = new ImageIcon(slotView.getSlot().getText()).getImage();
        g.drawImage(img, slotView.getX(), slotView.getY(), slotView.getWidth(), slotView.getHeight(), null);
    }
}
