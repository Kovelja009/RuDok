package view;

import javax.swing.*;
import java.awt.*;

public class MyToolBar extends JToolBar {


    public MyToolBar(){
        super(SwingConstants.HORIZONTAL);
        setFloatable(true);
        setPreferredSize(new Dimension(50, 25));

        add(MainFrame.getInstance().getActionManager().getNewAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getInfoAction());

    }
}
