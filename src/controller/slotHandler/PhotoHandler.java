package controller.slotHandler;

import model.workspace.Prezentacija;
import view.workspaceView.SlotView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PhotoHandler extends SlotHandler{
    public PhotoHandler(SlotView slotView) {
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
        Image img = new ImageIcon(slotView.getSlot().getText()).getImage();
        g.drawImage(img, slotView.getX(), slotView.getY(), slotView.getWidth(), slotView.getHeight(), null);
    }
}
