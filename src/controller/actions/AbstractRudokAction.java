package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.PrezentacijaView;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractRudokAction extends AbstractAction {

    public Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null){
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resources not found: " + fileName);
        }

        return icon;
    }

    public void setStroke(){
        PrezentacijaView pw = ((PrezentacijaView) MainFrame.getInstance().getMainProjectView().getPrezentacijaTabbedPane().getSelectedComponent());
        int broj = 1;
        try {
            broj = Integer.parseInt(pw.getStrokeSizeTF().getText());
        }catch (NumberFormatException ex){
            pw.getStrokeSizeTF().setText("");
            ErrorFactory.getInstance().generateError("Must enter number!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(broj < 1 || broj > 10){
            pw.getStrokeSizeTF().setText("");
            ErrorFactory.getInstance().generateError("Must enter number between 1 and 10!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        pw.setStrokeSize(broj);
        pw.getStrokeSizeTF().setText("");
    }
}
