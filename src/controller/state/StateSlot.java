package controller.state;

import view.workspaceView.SlideView;

import java.awt.*;

public abstract class StateSlot {
    private Color color = new Color(24, 35, 234, 150);

    public void mousePressed(int x, int y, int height, int widht, SlideView slideView){}
    public void mouseDragged(int x, int y, SlideView slideView){}

}
