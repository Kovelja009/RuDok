package view.editor_dialogs;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.workspaceView.SlotView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PictureDialog extends AbstractDialog {
    private String currentText = "nesto"; // postaviti putanju trenutne slike i posle svakog JFileChooser-a
    private ImagePanel slikaPanel;
    private JToolBar toolbar;
    private JButton open;

    public PictureDialog(MainFrame mainFrame) {
        super(mainFrame);
        setTitle("Photo editor");
        slikaPanel = new ImagePanel(this);
        slikaPanel.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/ 4, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/ 4));
        initializetoolbar();
        add(slikaPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.EAST);

    }

    public void setModel(SlotView slotView){
        currentText = slotView.getSlotHandler().readContent();
        slikaPanel.drawImage(currentText);
        pack();
    }

    public Object getData(){
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
