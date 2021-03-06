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
            FileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
            FileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
            FileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
            FileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
            FileMenu.setMnemonic(KeyEvent.VK_F);

            HelpMenu = new JMenu("Help");
            HelpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
            HelpMenu.setMnemonic(KeyEvent.VK_H);


            EditMenu = new JMenu("Edit");
            EditMenu.add(MainFrame.getInstance().getActionManager().getPopupAutorAction());
            EditMenu.add(MainFrame.getInstance().getActionManager().getPopupPozadinaAction());
            EditMenu.setMnemonic(KeyEvent.VK_E);

            add(FileMenu);
            add(EditMenu);
            add(HelpMenu);
    }
}
