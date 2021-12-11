package controller.listeners;

import view.workspaceView.PrezentacijaView;
import view.workspaceView.SlideView;

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
    public void mouseClicked(MouseEvent e) {

        if (e.getButton()==MouseEvent.BUTTON1){
            pw.mousePressed(e.getX(), e.getY(), sw);
            System.out.println(": " + pw.getName());
        }

    }
}
