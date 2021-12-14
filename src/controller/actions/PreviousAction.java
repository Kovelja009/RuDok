package controller.actions;

import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PreviousAction extends AbstractRudokAction{
    public PreviousAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/previous.png"));
        putValue(NAME, "Previous slide");
        putValue(SHORT_DESCRIPTION, "Previous slide");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int indexOfTab = MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedIndex();

        PrezentacijaView pw =  (PrezentacijaView) (MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getComponentAt(indexOfTab));

        ((CardLayout)pw.getPreviewPanel().getLayout()).previous(pw.getPreviewPanel());
    }
}
