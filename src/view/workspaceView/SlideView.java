package view.workspaceView;

import controller.listeners.MouseChecker;
import controller.observers.Subsriber;
import model.workspace.Prezentacija;
import model.workspace.Slide;
import model.workspace.Slot;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

public class SlideView extends JPanel implements Subsriber {
    private Slide slideRuNode;
    private Image image;
    private JLabel brojSlajda;
    private List<SlotView> slotViewList;
    private int velicina;
    private SlotView selectedSlotView;
    private PrezentacijaView parent;

    public SlideView(Slide slideRuNode, int velicina, PrezentacijaView pw){
        slotViewList = new ArrayList<>();
        this.slideRuNode = slideRuNode;
        this.slideRuNode.addSubscriber(this);
        this.slideRuNode.getParent().addSubscriber(this);
        setLayout(new BorderLayout());
        this.velicina = velicina;
        this.parent = pw;

        brojSlajda = new JLabel(String.valueOf(slideRuNode.getRedniBroj()));
        setup();

        for(Slot slot: slideRuNode.getSlotList()){
            SlotView sw = makeSlotView(slot);
            slotViewList.add(sw);
            sw.getSlot().addSubscriber(this);
        }

        this.add(brojSlajda, BorderLayout.SOUTH);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,10));
    }

    private void setup(){
        if(velicina == 1){
            MouseChecker mouseChecker = new MouseChecker(parent, this);
            addMouseListener(mouseChecker);
            addMouseMotionListener(mouseChecker);
            this.setPreferredSize(new Dimension(700, 400));
            this.setMaximumSize(new Dimension(700, 400));
            brojSlajda.setFont(new Font(brojSlajda.getFont().getFontName(), brojSlajda.getFont().getStyle(), 20));
        }else{
            this.setPreferredSize(new Dimension(175,100));
            this.setMaximumSize(new Dimension(175,100));
            brojSlajda.setFont(new Font(brojSlajda.getFont().getFontName(), brojSlajda.getFont().getStyle(), 14));
        }
    }

    private SlotView makeSlotView(Slot slot){
        SlotView sw = null;
        if(velicina == 2)
            sw = new SlotView(slot, true);
        else
            sw = new SlotView(slot, false);
        if(velicina == 1)
            sw.setRight(true);
        if(velicina == 0){
            sw.setWidth(sw.getWidth()/4);
            sw.setHeight(sw.getHeight()/4);
            sw.setX(sw.getX()/4);
            sw.setY(sw.getY()/4);
            sw.setStrokeSize(sw.getStrokeSize()/3);
        }
        return sw;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        URL imageURL = getClass().getResource(((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        ImageIcon icon = null;

        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        else
            System.err.println("Resources not found: " + ((Prezentacija)slideRuNode.getParent()).getUrlPozadina());

        image = icon.getImage();
        g.drawImage(image,0,0,getWidth(),getHeight(),null);

        for(SlotView s: slotViewList)
            s.paint((Graphics2D) g);

    }

    private void loadImage(){
        URL imageURL = getClass().getResource(((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        ImageIcon icon = null;

        if(imageURL != null){
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resources not found: " + ((Prezentacija)slideRuNode.getParent()).getUrlPozadina());
        }
        image = icon.getImage();
    }

    public Slide getSlideRuNode() {
        return slideRuNode;
    }

    public void setSlideRuNode(Slide slideRuNode) {
        this.slideRuNode.removeSubscriber(this);
        ((Prezentacija)this.slideRuNode.getParent()).removeSubscriber(this);
        this.slideRuNode = slideRuNode;
        this.slideRuNode.addSubscriber(this);
        ((Prezentacija)this.slideRuNode.getParent()).addSubscriber(this);
    }

    @Override
    public void updateSubsriber(Object notification, String message) {
        if(notification instanceof Slide && message.equals("redni broj")){
            brojSlajda.setText(String.valueOf(slideRuNode.getRedniBroj()));
        }

        if(notification instanceof String && message.equals("promena pozadine")){
                loadImage();
                this.validate();
                this.repaint();
        }
        if(notification instanceof Slot && message.equals("dodavanje slota")){
            SlotView sw = makeSlotView((Slot) notification);
            slotViewList.add(sw);
            sw.getSlot().addSubscriber(this);
            repaint();
        }
        if(notification instanceof Slot && message.equals("brisanje slota")){
            SlotView brisanje = null;
            for(SlotView sv : slotViewList){
                if(sv.getSlot().equals(notification)){
                    brisanje = sv;
                    sv.getSlot().removeSubscriber(this);
                    break;
                }
            }
            slotViewList.remove(brisanje);
            repaint();
        }

        if(notification instanceof Slot && message.equals("Position changed")){
            if(velicina == 0){
                for(SlotView slotView : slotViewList){
                    if(slotView.getSlot().equals(notification)){
                        slotView.setX(slotView.getX()/4);
                        slotView.setY(slotView.getY()/4);
                        break;
                    }
                }
            }
            repaint();
        }
    }

    public List<SlotView> getSlotViewList() {
        return slotViewList;
    }

    public SlotView getSelectedSlotView() {
        return selectedSlotView;
    }

    public void setSelectedSlotView(SlotView selectedSlotView, SlideView slideView) {
        this.selectedSlotView = selectedSlotView;
        parent.setSelectedSlotView(selectedSlotView);
        parent.repaint();
    }
}
