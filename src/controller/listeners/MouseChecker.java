package controller.listeners;

import view.MainFrame;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseChecker extends MouseAdapter {
   public PrezentacijaView pw;
   public SlideView sw;

    public MouseChecker(PrezentacijaView pw, SlideView sw){
        this.pw = pw;
        this.sw = sw;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton()==MouseEvent.BUTTON1){
            pw.mousePressed(e.getX(), e.getY(), sw);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        pw.mouseDragged(e.getX(), e.getY(), sw);
        if(pw.isShould())
            pw.getPrezentacijaRuNode().changingAction();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());

    }



}
