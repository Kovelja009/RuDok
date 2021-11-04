package view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
        private JMenu FileMenu;
        private JMenu HelpMenu;
        private JMenu EditMenu;

        public MyMenuBar(){
            FileMenu = new JMenu("File");
            FileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
            FileMenu.setMnemonic(KeyEvent.VK_F);

            HelpMenu = new JMenu("Help");
            HelpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
            HelpMenu.setMnemonic(KeyEvent.VK_H);


            EditMenu = new JMenu("Edit");
            EditMenu.add(MainFrame.getInstance().getActionManager().getPopupAutorAction());
            EditMenu.add(MainFrame.getInstance().getActionManager().getPopupPozadinaAction());
            EditMenu.add(MainFrame.getInstance().getActionManager().getNewProjekatAction());
            EditMenu.add(MainFrame.getInstance().getActionManager().getNewPrezentacijaAction());
            EditMenu.setMnemonic(KeyEvent.VK_E);

            add(FileMenu);
            add(EditMenu);
            add(HelpMenu);
    }
}
