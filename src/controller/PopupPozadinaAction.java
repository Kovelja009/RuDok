package controller;

import model.Prezentacija;
import view.MainFrame;
import view.tree.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PopupPozadinaAction extends AbstractRudokAction{
    public PopupPozadinaAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/background_popup.png"));
        putValue(NAME, "Presentation bckg");
        putValue(SHORT_DESCRIPTION, "Opens popup where you set background for your presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();

        if (o instanceof MyTreeNode && ((MyTreeNode) o).getNode() instanceof Prezentacija) {

            Prezentacija prez = (Prezentacija) ((MyTreeNode) o).getNode();

            JToggleButton tglBtn1 = new JToggleButton(new ImageIcon("src/controller/images/background_settings1.png"));
            JToggleButton tglBtn2 = new JToggleButton(new ImageIcon("src/controller/images/background_settings2.png"));
            JToggleButton tglBtn3 = new JToggleButton(new ImageIcon("src/controller/images/background_settings3.png"));

            ButtonGroup toggleBtnGroup = new ButtonGroup();
            toggleBtnGroup.add(tglBtn1);
            toggleBtnGroup.add(tglBtn2);
            toggleBtnGroup.add(tglBtn3);
            tglBtn1.setSelected(true);

            JPanel panel = new JPanel();

            JDialog dialog = new JDialog(MainFrame.getInstance(), true);
            JButton selectBtn = new JButton("select");
            selectBtn.addActionListener(action -> {
                String url = "";
                if (tglBtn1.isSelected()) {
                    url = "images/background1.jpg";
                } else if (tglBtn2.isSelected()) {
                    url = "images/background2.jpg";
                } else if (tglBtn3.isSelected()) {
                    url = "images/background3.jpg";
                }

                prez.setUrlPozadina(url);
                dialog.dispose();
            });

            panel.add(tglBtn1);
            panel.add(tglBtn2);
            panel.add(tglBtn3);
            panel.add(selectBtn);

            dialog.add(panel);
            dialog.pack();
            dialog.setLocationRelativeTo(MainFrame.getInstance());
            dialog.setVisible(true);
            System.out.println("prezentacija je: " + prez.getAutor() + " " + prez.getUrlPozadina());
            return;
        }
        System.out.println("Mora biti selektovana prezentacija!!!");

    }
}
