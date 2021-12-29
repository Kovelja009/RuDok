package view.editor_dialogs;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class PhotoFileChooser extends JDialog {
    List<String> extensions = Arrays.asList("jpg", "png", "jpeg");
    private PictureDialog pictureDialog;
    public PhotoFileChooser(PictureDialog pictureDialog) {
        setSize(500, 500);
        setLocationRelativeTo(MainFrame.getInstance());
        setModalityType(ModalityType.APPLICATION_MODAL);
        JPanel p = new JPanel();
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        jfc.setControlButtonsAreShown(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
        jfc.setFileFilter(filter);
        setTitle("Choose photo");
        JButton ok = new JButton("Select");
        JButton cancel = new JButton("Cancel");
        this.pictureDialog = pictureDialog;

        p.add(jfc);
        p.add(ok);
        p.add(cancel);
        add(p);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jfc.getSelectedFile() == null)
                    System.out.println("nije odabrano nista, a kliknuto save");
                else {
                    String name = jfc.getSelectedFile().getAbsolutePath();
                    int index = name.lastIndexOf('.');
                    String extension = "";
                    if (index > 0) extension = name.substring(index + 1);
                    if (!extensions.contains(extension)) {
                        System.out.println(jfc.getSelectedFile());
                        ErrorFactory.getInstance().generateError("Selected invalid path", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        pictureDialog.setCurrentText(name);
                        dispose();
                    }
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

