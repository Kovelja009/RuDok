package view.editor_dialogs;

import controller.errorHandler.ErrorFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImagePanel extends JPanel {
    private PictureDialog pictureDialog;
    private Image image;
    private String path;
    public ImagePanel(PictureDialog pictureDialog) {
        this.pictureDialog = pictureDialog;
        path = pictureDialog.getCurrentText();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!path.equals(""))
            g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public boolean drawImage(String path){

        this.path = path;
        if(path.equals("")){
            return false;
        }
        try {
            image = ImageIO.read(new File(path));
            repaint();
            return true;
        }catch (Exception e){
            ErrorFactory.getInstance().generateError("Unable to load image", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
