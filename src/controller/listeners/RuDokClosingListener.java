package controller.listeners;

import view.MainFrame;
import view.serialization.dialogs.SavingWorkspace;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RuDokClosingListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Izlaz");
        MainFrame.getInstance().getSw().setVisible(true);
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        MainFrame.getInstance().getOw().setVisible(true);
    }
}
