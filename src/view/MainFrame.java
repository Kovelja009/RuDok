package view;

import controller.actions.ActionManager;
import controller.errorHandler.ErrorFactory;
import controller.errorHandler.MyError;
import controller.observers.Subsriber;
import view.tree.view.MyTree;
import view.tree.model.MyTreeModel;
import view.workspaceView.ProjekatView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Subsriber {
    private static MainFrame instance = null;
    private ActionManager actionManager;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JScrollPane leviScrollPane;
    private JSplitPane centralniSplitPane;
    private ProjekatView mainProjectView;
    private JPanel editModePanel;
    private JPanel previewModePanel;


    private MyTreeModel myTreeModel;
    private MyTree myTree;

    private MainFrame(){}

    private void initialize(){

        initialiseMyTree();
        ErrorFactory.getInstance().addSubscriber(this);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        setSize(dim.width/2 + 450, dim.height/2 + 200);

        Image img = kit.getImage("src/controller/images/icon_main.jpg");
        setIconImage(img);
        setTitle("RuDok");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        actionManager = new ActionManager();

        menuBar = new MyMenuBar();
        setJMenuBar(menuBar);

        makeEditPane();
        getContentPane().add(editModePanel);

        previewModePanel = new PreviewPane();
        JScrollPane scrollPane = new JScrollPane(previewModePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void makeEditPane(){
        editModePanel = new JPanel();
        editModePanel.setLayout(new BorderLayout());

        toolBar = new MyToolBar();
        editModePanel.add(toolBar, BorderLayout.NORTH);


        leviScrollPane = new JScrollPane(myTree,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leviScrollPane.setMinimumSize(new Dimension(200,200));

        mainProjectView = new ProjekatView();
        mainProjectView.setBackground(Color.gray);

        centralniSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centralniSplitPane.add(leviScrollPane);
        centralniSplitPane.add(mainProjectView);

        centralniSplitPane.setOneTouchExpandable(true);

        editModePanel.add(centralniSplitPane, BorderLayout.CENTER);
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


    @Override
    public void updateSubsriber(Object notification, String message) {
        if(notification instanceof MyError){
            MyError error = (MyError)notification;
            JOptionPane.showMessageDialog(this, error.getMessage(), error.getTitle(), error.getType());
        }
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public MyTreeModel getMyTreeModel() {
        return myTreeModel;
    }

    public void setMyTreeModel(MyTreeModel myTreeModel) {
        this.myTreeModel = myTreeModel;
    }

    public MyTree getMyTree() {
        return myTree;
    }

    public void setMyTree(MyTree myTree) {
        this.myTree = myTree;
    }

    public ProjekatView getMainProjectView() {
        return mainProjectView;
    }

    public void setMainProjectView(ProjekatView mainProjectView) {
        this.mainProjectView = mainProjectView;
    }

    public JPanel getPreviewModePanel() {
        return previewModePanel;
    }

    public JPanel getEditModePanel() {
        return editModePanel;
    }

    public JMenuBar menuBarGetter(){return menuBar;}
}
