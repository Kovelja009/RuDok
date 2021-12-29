package view.editor_dialogs;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.SlotView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class PictureDialog extends AbstractDialog {
    private String currentText = "nesto"; // postaviti putanju trenutne slike i posle svakog JFileChooser-a
    private ImagePanel slikaPanel;
    private JToolBar toolbar;
    private JButton open;

    public PictureDialog(MainFrame mainFrame) {
        super(mainFrame);
        setTitle("Photo editor");
        slikaPanel = new ImagePanel(this);
        initializetoolbar();
        add(slikaPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.EAST);

    }

    public void setModel(SlotView slotView){
        currentText = slotView.getSlot().getText();
        slikaPanel.drawImage(slotView.getSlot().getText());
        pack();
    }

    public String getData(){
        return currentText;
    }

    private void initializetoolbar(){
        toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setFloatable(false);

        open = new JButton("Open");
        initializeOpen();
        toolbar.add(open);
        toolbar.add(MainFrame.getInstance().getActionManager().getSaveSlotChangeAction());

    }

    private void initializeOpen(){
        PictureDialog pictureDialog = this;
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhotoFileChooser pfc = new PhotoFileChooser(pictureDialog);
                pfc.setVisible(true);
            }
        });
    }
    public void setCurrentText(String currentText) {
            if(slikaPanel.drawImage(currentText))
                this.currentText = currentText;
    }

    public String getCurrentText() {
        return currentText;
    }
}
