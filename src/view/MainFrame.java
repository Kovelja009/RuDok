package view;

import controller.ActionManager;
import view.tree.MyTree;
import view.tree.MyTreeModel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private ActionManager actionManager;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JScrollPane leviScrollPane;
    private JSplitPane centralniSplitPane;

    private MyTreeModel myTreeModel;
    private MyTree myTree;

    private MainFrame(){}

    private void initialize(){

        initialiseMyTree();

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


        leviScrollPane = new JScrollPane(myTree,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leviScrollPane.setMinimumSize(new Dimension(200,200));

        JPanel obican = new JPanel();
        obican.setBackground(Color.gray);

        centralniSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centralniSplitPane.add(leviScrollPane);
        centralniSplitPane.add(obican);

        centralniSplitPane.setOneTouchExpandable(true);

        add(centralniSplitPane, BorderLayout.CENTER);



//        try {
//            SwingUtilities.updateComponentTreeUI(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private void initialiseMyTree(){
        myTree = new MyTree();
        myTreeModel = new MyTreeModel();
        myTree.setModel(myTreeModel);
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
