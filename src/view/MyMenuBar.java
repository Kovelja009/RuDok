package view;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
        private JMenu FileMenu;
        private JMenu HelpMenu;

        public MyMenuBar(){
            FileMenu = new JMenu("File");
            FileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
            FileMenu.setMnemonic(KeyEvent.VK_F);

            HelpMenu = new JMenu("Help");
            HelpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
            HelpMenu.setMnemonic(KeyEvent.VK_H);


            add(FileMenu);
            add(HelpMenu);
    }
}
