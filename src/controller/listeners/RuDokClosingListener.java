package controller.listeners;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.serialization.dialogs.SavingWorkspace;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RuDokClosingListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Izlaz");
        MainFrame.getInstance().getSw().setVisible(true);
        if(MainFrame.getInstance().getSw().isShouldSave())
            System.exit(0);
        else{
            MainFrame.getInstance().getSw().dispose();
            ErrorFactory.getInstance().generateError("Must save all projects first!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        MainFrame.getInstance().getOw().setVisible(true);
    }
}
