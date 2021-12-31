package view;

import javax.swing.*;
import java.awt.*;

public class MyToolBar extends JToolBar {


    public MyToolBar(){
        super(SwingConstants.HORIZONTAL);
        setFloatable(true);
        setPreferredSize(new Dimension(100, 35));


        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getSharedAction());
        addSeparator(new Dimension(15,50));
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator(new Dimension(15,50));
        add(MainFrame.getInstance().getActionManager().getPopupAutorAction());
        add(MainFrame.getInstance().getActionManager().getPopupPozadinaAction());
        addSeparator(new Dimension(15,50));
        add(MainFrame.getInstance().getActionManager().getInfoAction());


    }
}
