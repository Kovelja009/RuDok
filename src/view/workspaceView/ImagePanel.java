package view.workspaceView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(String img) {
        URL imageURL = getClass().getResource(img);
        if(imageURL != null){
        this.img = new ImageIcon(imageURL).getImage();
        } else {
            System.err.println("Resources not found: " + img);
        }
    }

    public ImagePanel(Image img) {
        this.img = img;
    }

    /*
     * metoda paintComponent se redefinise iz nasledjene klase
     * i ona ce biti zaduzena da iscrta zadatu sliku na pozadini JPanel-a
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                (int)(this.getSize().getHeight()-img.getHeight(null))/2, null);
    }
}
