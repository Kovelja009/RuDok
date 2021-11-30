package view;

import model.RuNode;
import model.workspace.Slide;
import view.workspaceView.SlideView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PreviewPane extends JPanel {
    private JToolBar toolBar;
    private JPanel center;

    public PreviewPane(){
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        setPreferredSize(new Dimension(700, 400));
        setMaximumSize(new Dimension(700, 400));
        setMinimumSize(new Dimension(700, 400));

        setBackground(Color.DARK_GRAY);
        generateCenter();
        generateToolbar();
    }

    private void generateCenter(){
        center = new JPanel();
        center.setBackground(Color.LIGHT_GRAY);

        CardLayout sl = new CardLayout(10, 10);
        center.setLayout(sl);

        center.setPreferredSize(new Dimension(700, 400));
        center.setMaximumSize(new Dimension(700, 400));
        center.setMinimumSize(new Dimension(700, 400));

        add(center, BorderLayout.CENTER);
    }


    private void generateToolbar(){
        toolBar = new JToolBar(SwingConstants.HORIZONTAL);
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(100, 35));

        toolBar.add(MainFrame.getInstance().getActionManager().getPreviousAction());
        toolBar.addSeparator();
        toolBar.add(MainFrame.getInstance().getActionManager().getEditAction());
        toolBar.addSeparator();
        toolBar.add(MainFrame.getInstance().getActionManager().getNextAction());

        add(toolBar, BorderLayout.NORTH);
    }

    public void setSlides(List<RuNode> swlist){
        center.removeAll();
        center.revalidate();
        for(RuNode slide : swlist){
            if(slide instanceof Slide){
                SlideView sw = new SlideView((Slide)slide);
                center.add(sw);
            }
        }
        center.repaint();
    }

    public CardLayout getCardLayout(){return (CardLayout) center.getLayout();}

    public JPanel getCenter() {
        return center;
    }
}
