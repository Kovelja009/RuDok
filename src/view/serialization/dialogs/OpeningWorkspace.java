package view.serialization.dialogs;

import view.MainFrame;
import view.serialization.SerialExperimentsLain.MetaSerializationFactory;
import view.serialization.SerialExperimentsLain.SaveFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpeningWorkspace extends JDialog{
    public OpeningWorkspace(){
        setModal(true);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Loading workspace");
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        JButton noBtn = new JButton("previous");
        initializeBtn(noBtn);
        initializeToolbar(toolbar, noBtn);
        add(toolbar);
        pack();
    }

    private void initializeBtn(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveFactory openFactory = MetaSerializationFactory.returnOpenFactory(null);
                if(!(openFactory == null))
                    openFactory.open(null, true);
                MainFrame.getInstance().getOw().dispose();
            }
        });
    }

    private void initializeToolbar(JToolBar toolbar, JButton noBtn){
        toolbar.setPreferredSize(new Dimension(200, 30));
        toolbar.add(MainFrame.getInstance().getActionManager().getLoadWorkspaceAction());
        toolbar.addSeparator(new Dimension(50, 50));
        toolbar.add(noBtn);
    }
}
