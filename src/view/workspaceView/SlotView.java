package view.workspaceView;

import model.workspace.Slot;

import java.awt.*;

public class SlotView {
    private Slot slot;
    private Shape shape;

    public SlotView(Slot slot) {
        this.slot = slot;
        shape = new Rectangle(slot.getX(), slot.getY(), slot.getWidth(), slot.getHeight());
    }

    public void paint(Graphics2D g){
        g.setPaint(new Color(slot.getRed(), slot.getGreen(), slot.getBlue()));
        g.setBackground(new Color(slot.getRed(), slot.getGreen(), slot.getBlue()));

        g.drawRect(slot.getX(), slot.getY(), slot.getWidth(), slot.getHeight());
    }

    public boolean elementAt(Point pos){
        return shape.contains(pos);
    }

    public Slot getSlot() {
        return slot;
    }
}
