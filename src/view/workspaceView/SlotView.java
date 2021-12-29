package view.workspaceView;

import controller.observers.Subsriber;
import controller.slotHandler.PhotoHandler;
import controller.slotHandler.SlotHandler;
import controller.slotHandler.TextHandler;
import model.workspace.Prezentacija;
import model.workspace.Slot;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SlotView implements Subsriber {
    private Slot slot;
    private Shape shape;
    private int height;
    private int width;
    private int x;
    private int y;
    private int strokeSize;
    private SlotHandler slotHandler;
    private boolean preview;
    private boolean right;

    public SlotView(Slot slot, boolean preview) {
        this.slot = slot;
        slot.addSubscriber(this);
        this.height = slot.getHeight();
        this.width = slot.getWidth();
        this.x = slot.getX();
        this.y = slot.getY();
        this.strokeSize = slot.getStrokeSize();
        this.preview = preview;
        shape = new Rectangle(x, y, width, height);
        if(slot.getSlotType().equals("picture"))
            slotHandler = new PhotoHandler(this);
        else
            slotHandler = new TextHandler(this);
    }

    public void paint(Graphics2D g){
        g.setPaint(new Color(slot.getRed(), slot.getGreen(), slot.getBlue(), 150));
        g.fill(new Rectangle(x, y, width, height));
        if(slot.getStrokeType() == 0)
            g.setStroke(new BasicStroke(strokeSize));
        else
            g.setStroke(new BasicStroke(strokeSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{9}, 0));
        g.setColor(Color.BLACK);
        if(right && selected())
            g.drawString("selected " + slot.getSlotType(), x, y);
        if(preview)
            slotHandler.paint(g);
        g.drawRect(x, y, width, height);
    }

    public boolean elementAt(Point pos){
        return shape.contains(pos);
    }

    public Slot getSlot() {
        return slot;
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    private boolean selected(){
        boolean selected = false;
        if(((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getSelectedSlotView() != null)
            selected = ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getSelectedSlotView().getSlot() == this.getSlot();
        return selected;
    }

    @Override
    public void updateSubsriber(Object notification, String message) {
        this.x = slot.getX();
        this.y = slot.getY();
        ((Rectangle)shape).setLocation(x, y);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SlotHandler getSlotHandler() {
        return slotHandler;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
