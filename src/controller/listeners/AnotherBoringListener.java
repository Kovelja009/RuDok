package controller.listeners;

import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AnotherBoringListener extends FocusAdapter {

    @Override
    public void focusGained(FocusEvent e) {
        JTextField tf = ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getStrokeSizeTF();
        if (tf.getText().equals("Enter size of stroke"))
            tf.setText("");
    }
    @Override
    public void focusLost(FocusEvent e) {
        JTextField tf = ((PrezentacijaView)MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent()).getStrokeSizeTF();
        if (tf.getText().isEmpty())
            tf.setText("Enter size of stroke");
    }
}
