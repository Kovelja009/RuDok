package view.workspaceView;

import controller.observers.Subsriber;
import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Slide;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrezentacijaView extends JTabbedPane implements Subsriber {
    private List<RuNode> listaPrezentacija;

    public PrezentacijaView(List<RuNode> listaPrezentacija){
        this.listaPrezentacija = listaPrezentacija;
//            dodati ImageIcon na tab
            JPanel panel = new JPanel();
            panel.setBackground(Color.darkGray);

        for(RuNode p : this.listaPrezentacija){
            this.addTab(p.getName(), panel);
        }

    }

    public void updateSubsriber(Object notification) {
        System.out.println("Update prezentacija view");
    }
}
