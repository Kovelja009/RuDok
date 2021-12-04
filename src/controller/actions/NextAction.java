package controller.actions;

import view.MainFrame;
import view.PreviewPane;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NextAction extends AbstractRudokAction{
    public NextAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/next.png"));
        putValue(NAME, "Next slide");
        putValue(SHORT_DESCRIPTION, "Next slide");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        ((PreviewPane) MainFrame.getInstance().getPreviewModePanel()).getCardLayout().next(((PreviewPane) MainFrame.getInstance().getPreviewModePanel()).getCenter());
        int indexOfTab = MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedIndex();

        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getComponentAt(indexOfTab));

        ((CardLayout)pw.getPreviewPanel().getLayout()).next(pw.getPreviewPanel());
    }
}
