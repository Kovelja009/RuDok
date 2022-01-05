package view;

import controller.actions.ActionManager;
import controller.commands.CommandManager;
import controller.errorHandler.ErrorFactory;
import controller.errorHandler.MyError;
import controller.listeners.RuDokClosingListener;
import controller.observers.Subsriber;
import view.editor_dialogs.AbstractDialog;
import view.editor_dialogs.PictureDialog;
import view.editor_dialogs.TextDialog;
import view.serialization.dialogs.OpeningWorkspace;
import view.serialization.dialogs.SavingWorkspace;
import view.tree.view.MyTree;
import view.tree.model.MyTreeModel;
import view.workspaceView.ProjekatView;
import view.workspaceView.SlotView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements Subsriber {
    private static MainFrame instance = null;
    private ActionManager actionManager;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JScrollPane leviScrollPane;
    private JSplitPane centralniSplitPane;
    private ProjekatView mainProjectView;
    private JPanel editModePanel;
    private MyTreeModel myTreeModel;
    private MyTree myTree;
    private CommandManager commandManager;
    private PictureDialog pictureDialog;
    private TextDialog textDialog;
    private AbstractDialog currentDialog;
    private List<Character> charList;
    private SavingWorkspace sw;
    private OpeningWorkspace ow;

    private MainFrame(){}

    private void initialize(){

        initialiseMyTree();
        ErrorFactory.getInstance().addSubscriber(this);
        commandManager = new CommandManager();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        setSize(dim.width/2 + 450, dim.height/2 + 200);

        Image img = kit.getImage("src/controller/images/icon_main.jpg");
        setIconImage(img);
        setTitle("RuDok");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new RuDokClosingListener());

        charListInit();
        actionManager = new ActionManager();

        menuBar = new MyMenuBar();
        setJMenuBar(menuBar);

        makeEditPane();
        getContentPane().add(editModePanel);
        pictureDialog = new PictureDialog(this);
        textDialog = new TextDialog(this);

        sw = new SavingWorkspace();
        ow = new OpeningWorkspace();
    }

    private void charListInit(){
        charList = new ArrayList<>();
        charList.add('~');
        charList.add('$');
        charList.add('|');
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

    public MyTree getMyTree() {
        return myTree;
    }

    public ProjekatView getMainProjectView() {
        return mainProjectView;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public AbstractDialog getCurrentDialog() {
        return currentDialog;
    }

    public List<Character> getCharList() {
        return charList;
    }

    public void setCurrentDialog(SlotView selected) {
        if(selected.getSlot().getSlotType().equals("text"))
            currentDialog = textDialog;
        if(selected.getSlot().getSlotType().equals("picture"))
            currentDialog = pictureDialog;
    }

    public SavingWorkspace getSw() {
        return sw;
    }

    public OpeningWorkspace getOw() {
        return ow;
    }
}
