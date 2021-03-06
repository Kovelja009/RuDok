package view.workspaceView;

import controller.listeners.AnotherBoringListener;
import controller.observers.Subsriber;
import controller.state.EditorStateManager;
import controller.state.SlotStateManager;
import controller.state.StateManager;
import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Slide;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrezentacijaView extends JPanel implements Subsriber {
    private Prezentacija prezentacijaRuNode;
    private JLabel autorlbl;
    private List<SlideView> slideViewList;
    private List<SlideView> malislideViewList;
    private List<SlideView> previewList;
    private JPanel nalepnica;
    private JPanel malaNalepnica;
    private JScrollPane desniScrollPane;
    private JScrollPane leviScrollPane;
    private JToolBar prezToolbar;
    private JPanel contentPane;
    private JPanel previewPanel;
    private JSlider slider;
    private StateManager stateManager;
    private SlotStateManager slotStateManager;
    private EditorStateManager editorStateManager;
    private SlotView selectedSlotView;
    private boolean should = false;

    public PrezentacijaView(Prezentacija prezentacijaRuNode){
        slideViewList = new ArrayList<>();
        malislideViewList = new ArrayList<>();
        previewList = new ArrayList<>();

        stateManager = new StateManager();
        slotStateManager = new SlotStateManager();
        editorStateManager = new EditorStateManager();

        this.prezentacijaRuNode = prezentacijaRuNode;
        this.prezentacijaRuNode.addSubscriber(this);
        setMinimumSize(new Dimension(500, 400));
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        initializeToolbar();
        generateEditToolbar();

        autorlbl = new JLabel("Author: " + prezentacijaRuNode.getAutor());

        generateMalaNalepnica();
        generateNalepnica();
        initializeContentPane();
        generateContentPaneEditMode();

        add(prezToolbar, BorderLayout.NORTH);
        add(contentPane, BorderLayout.CENTER);
        add(autorlbl, BorderLayout.SOUTH);

        generisanjeSlajdova();
        revalidate();
        repaint();

    }

    private void initializeSlider(){
        slider=new JSlider(1,10);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setMaximumSize(new Dimension(200,60));
        slider.setBackground(new Color(0x4D078D));
        slider.setForeground(new Color(0x44D745));
        AnotherBoringListener abl = new AnotherBoringListener();
        slider.addChangeListener(abl);
    }

    private void initializeToolbar(){
        prezToolbar = new JToolBar();
        prezToolbar.setOrientation(SwingConstants.HORIZONTAL);
        prezToolbar.setFloatable(false);
        initializeSlider();
    }

    public void generateEditToolbar(){
        prezToolbar.removeAll();
        prezToolbar.revalidate();

        prezToolbar.add(MainFrame.getInstance().getActionManager().getPreviewAction());
        prezToolbar.addSeparator();
        prezToolbar.addSeparator();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getAddSlotAction());
        prezToolbar.add(MainFrame.getInstance().getActionManager().getRemoveSlotAction());
        prezToolbar.addSeparator();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getSelectSlotAction());
        prezToolbar.add(MainFrame.getInstance().getActionManager().getDragSlotAction());
        prezToolbar.addSeparator();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getTextAction());
        prezToolbar.add(MainFrame.getInstance().getActionManager().getPictureAction());
        prezToolbar.addSeparator();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getEditorAction());
        prezToolbar.addSeparator();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getColorPickerAction());
        prezToolbar.add(MainFrame.getInstance().getActionManager().getDashedAction());
        prezToolbar.add(MainFrame.getInstance().getActionManager().getCircleAction());
        prezToolbar.addSeparator();
        prezToolbar.add(slider);


        prezToolbar.repaint();
    }

    public void generatePreviewToolbar(){
        prezToolbar.removeAll();
        prezToolbar.revalidate();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getPreviousAction());
        prezToolbar.addSeparator();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getEditAction());
        prezToolbar.addSeparator();
        prezToolbar.add(MainFrame.getInstance().getActionManager().getNextAction());
        prezToolbar.repaint();
    }

    private void initializeContentPane(){
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setMinimumSize(new Dimension(500, 400));
        contentPane.setBackground(Color.LIGHT_GRAY);

        previewPanel = new JPanel();
        previewPanel.setLayout(new CardLayout(120,10));
        previewPanel.setBackground(Color.LIGHT_GRAY);
    }

    public void generateContentPaneEditMode(){
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.add(desniScrollPane, BorderLayout.CENTER);
        contentPane.add(leviScrollPane, BorderLayout.WEST);
        contentPane.repaint();
    }

    public void generateContentPanePreviewMode(){
        contentPane.removeAll();
        contentPane.revalidate();
        contentPane.add(previewPanel);
        contentPane.repaint();
    }

    private void generateNalepnica(){
        nalepnica = new JPanel();
        nalepnica.setLayout(new BoxLayout(nalepnica, BoxLayout.Y_AXIS));
        nalepnica.setBackground(Color.LIGHT_GRAY);
        desniScrollPane = new JScrollPane(nalepnica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void generateMalaNalepnica(){
        malaNalepnica = new JPanel();
        malaNalepnica.setLayout(new BoxLayout(malaNalepnica, BoxLayout.Y_AXIS));
        malaNalepnica.setBackground(Color.LIGHT_GRAY);
        malaNalepnica.setMinimumSize(new Dimension(250, 200));
        leviScrollPane = new JScrollPane(malaNalepnica, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leviScrollPane.setPreferredSize(new Dimension(250, 200));
    }


    private void generisanjeSlajdova(){
        for(RuNode s : prezentacijaRuNode.getChildren()){
            if(s instanceof Slide){
                Slide slide = (Slide) s;
                SlideView slideView = new SlideView(slide, 1, this);
                slideViewList.add(slideView);
                nalepnica.add(slideView);

                SlideView malislideView = new SlideView(slide, 0, this);
                malislideViewList.add(malislideView);
                malaNalepnica.add(malislideView);

                SlideView preView = new SlideView(slide, 2, this);
                previewList.add(preView);
                previewPanel.add(preView);
            }
        }
        revalidate();
        repaint();
    }

    private void brisanjeSlajda(Slide sl){
        SlideView brisanje = null;
        SlideView brisanje2 = null;
        SlideView brisanje3 = null;
        for(int i = 0; i< slideViewList.size(); i++){
            if(slideViewList.get(i).getSlideRuNode().equals(sl)){
                brisanje = slideViewList.get(i);
                nalepnica.remove(slideViewList.get(i));
                nalepnica.revalidate();
                nalepnica.repaint();


                brisanje2 = malislideViewList.get(i);
                malaNalepnica.remove(malislideViewList.get(i));
                malaNalepnica.revalidate();
                malaNalepnica.repaint();

                brisanje3 = previewList.get(i);
                previewPanel.remove(previewList.get(i));
                previewPanel.revalidate();
                previewPanel.repaint();

                break;
            }
        }
        slideViewList.remove(brisanje);
        malislideViewList.remove(brisanje2);
        previewList.remove(brisanje3);
    }

    private void dodavanjeSlajda(Slide sl){
        SlideView sw = new SlideView(sl, 1, this);
        slideViewList.add(sw);
        nalepnica.add(sw);
        nalepnica.revalidate();
        nalepnica.repaint();

        SlideView sw1 = new SlideView(sl, 0, this);
        malislideViewList.add(sw1);
        malaNalepnica.add(sw1);
        malaNalepnica.revalidate();
        malaNalepnica.repaint();

        SlideView sw2 = new SlideView(sl, 2, this);
        previewList.add(sw2);
        previewPanel.add(sw2);
        previewPanel.revalidate();
        previewPanel.repaint();
    }

    public void updateSubsriber(Object notification, String message) {
        if(notification instanceof Prezentacija && message.equals("promena autora")){
            autorlbl.setText("Author: " + ((Prezentacija) notification).getAutor());
        }

        if(notification instanceof Slide && message.equals("brisanje")){
            brisanjeSlajda((Slide) notification);
        }

        if(notification instanceof Slide && message.equals("dodavanje")){
            dodavanjeSlajda((Slide) notification);
        }

    }

    public Prezentacija getPrezentacijaRuNode() {
        return prezentacijaRuNode;
    }

    public void setPrezentacijaRuNode(Prezentacija prezentacijaRuNode) {
        this.prezentacijaRuNode.removeSubscriber(this);
        this.prezentacijaRuNode = prezentacijaRuNode;
        this.prezentacijaRuNode.addSubscriber(this);
    }

    public void changeColor(Color color){
        slotStateManager.setColor(color);
    }

    public Color getColor(){return slotStateManager.getColor();}

    public int getStrokeSize(){return slotStateManager.getStrokeSize();}

    public void setStrokeSize(int strokeSize){slotStateManager.setStrokeSize(strokeSize);}

    public int getStrokeType(){return slotStateManager.getStrokeType();}

    public void setStrokeType(int strokeType){slotStateManager.setStrokeType(strokeType);}

    public void startEditState(){stateManager.setEditState();}

    public void startPreviewState(){stateManager.setPreviewState();}

    public void changeState(){
        stateManager.getCurr().changeState();
    }

    public void startTextState(){editorStateManager.setTextState();}

    public void startPictureState(){editorStateManager.setPictureState();}

    public String getType(){return editorStateManager.getCurr().getType();}

    public void startAddState(){
        slotStateManager.setAddState();
        should = false;
    }

    public void startRemoveState(){
        slotStateManager.setRemoveState();
        should = false;
    }

    public void startSelectState(){
        slotStateManager.setSelectState();
        should = false;
    }

    public void startDragState(){
        slotStateManager.setDragState();
        should = true;
    }

    public void mousePressed(int x, int y, SlideView sw){
        slotStateManager.getCurr().mousePressed(x,y,sw.getHeight()/7, sw.getWidth()/7, sw);
    }

    public void mouseDragged(int x, int y, SlideView sw){
        slotStateManager.getCurr().mouseDragged(x,y,sw);
    }


    public List<SlideView> getSlideViewList() {
        return slideViewList;
    }

    public JPanel getPreviewPanel() {
        return previewPanel;
    }

    public JSlider getSlider() {
        return slider;
    }

    public SlotView getSelectedSlotView() {
        return selectedSlotView;
    }

    public void setSelectedSlotView(SlotView selectedSlotView) {
        this.selectedSlotView = selectedSlotView;
    }

    public boolean isShould() {
        return should;
    }
}
