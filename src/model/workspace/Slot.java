package model.workspace;

import controller.observers.Publisher;
import controller.observers.Subsriber;

import java.util.ArrayList;
import java.util.List;

public class Slot implements Publisher {
    private int x;
    private int y;
    private int height;
    private int width;
    private int red;
    private int green;
    private int blue;
    private int strokeSize;
    private int strokeType;
    private List<Subsriber> subsriberList;

    public Slot(int x, int y, int height, int width, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.red = red;
        this.green = green;
        this.blue = blue;
        subsriberList = new ArrayList<>();
    }

    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
        notifySubcribers(this, "Position changed");

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

    public int getStrokeType() {
        return strokeType;
    }

    public void setStrokeType(int strokeType) {
        this.strokeType = strokeType;
    }

    @Override
    public void addSubscriber(Subsriber subsriber) {
        subsriberList.add(subsriber);
    }

    @Override
    public void removeSubscriber(Subsriber subsriber) {
        subsriberList.remove(subsriber);
    }

    @Override
    public void removeAllSubcribers() {
        subsriberList.clear();
    }

    @Override
    public void notifySubcribers(Object notification, String message) {
        for(int i = 0; i < subsriberList.size(); i++)
            subsriberList.get(i).updateSubsriber(notification, message);
    }
}
