package controller.actions;

import controller.errorHandler.ErrorFactory;
import model.workspace.Prezentacija;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PopupPozadinaAction extends AbstractRudokAction{
    public PopupPozadinaAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/background_popup.png"));
        putValue(NAME, "Set background");
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
            dialog.setTitle("Choose background");
            JButton selectBtn = new JButton("select");
            selectBtn.addActionListener(action -> {
                String url = "";
                if (tglBtn1.isSelected()) {
                    url = "../../controller/images/background1.jpg";
                } else if (tglBtn2.isSelected()) {
                    url = "../../controller/images/background2.jpg";
                } else if (tglBtn3.isSelected()) {
                    url = "../../controller/images/background3.jpg";
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
            System.out.println("prezentacija je: " + prez.getName() + " " + prez.getUrlPozadina());
            return;
        }
        ErrorFactory.getInstance().generateError("In order to add background, user must first select presentation", "Information", JOptionPane.INFORMATION_MESSAGE);


    }
}
