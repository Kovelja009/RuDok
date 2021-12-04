package view;

import javax.swing.*;
import java.awt.*;

public class MyToolBar extends JToolBar {


    public MyToolBar(){
        super(SwingConstants.HORIZONTAL);
        setFloatable(true);
        setPreferredSize(new Dimension(100, 35));


        add(MainFrame.getInstance().getActionManager().getNewAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getPopupAutorAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getPopupPozadinaAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getColorPickerAction());

    }
}
