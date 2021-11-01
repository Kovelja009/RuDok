package view;

import controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private ActionManager actionManager;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JSplitPane centralniSplitPane;

    private MainFrame(){}

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        setSize(dim.width/2, dim.height/2);

        Image img = kit.getImage("src/controller/images/icon_main.jpg");
        setIconImage(img);
        setTitle("RuDok");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        actionManager = new ActionManager();

        menuBar = new MyMenuBar();
        setJMenuBar(menuBar);

        toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        centralniSplitPane = new CentralniSplitPanel();
        add(centralniSplitPane, BorderLayout.CENTER);

    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }
}
