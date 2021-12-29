package view.editor_dialogs;

import view.MainFrame;
import view.workspaceView.SlideView;
import view.workspaceView.SlotView;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractDialog extends JDialog {

    public AbstractDialog(MainFrame mainFrame) {
        setModal(true);
        setLocationRelativeTo(mainFrame);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public abstract void setModel(SlotView slotView);
    public abstract String getData();

}
