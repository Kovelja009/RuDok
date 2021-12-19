package controller.listeners;

import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class AnotherBoringListener implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        PrezentacijaView pw = (PrezentacijaView) MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent();
        pw.setStrokeSize(pw.getSlider().getValue());
    }
}
