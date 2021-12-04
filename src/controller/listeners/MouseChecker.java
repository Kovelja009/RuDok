package controller.listeners;

import view.workspaceView.SlideView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseChecker extends MouseAdapter {
   public SlideView sw;


    public MouseChecker(SlideView sw){
        this.sw = sw;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton()==MouseEvent.BUTTON1){
            sw.modify(e.getX(), e.getY());
            System.out.println(": Slajd " + sw.getSlideRuNode().getRedniBroj());
        }

    }
}
