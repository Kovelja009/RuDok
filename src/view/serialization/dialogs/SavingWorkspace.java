package view.serialization.dialogs;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingWorkspace extends JDialog {
    public SavingWorkspace(){
        setModal(true);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Saving workspace");
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        JButton noBtn = new JButton("no");
        initializeBtn(noBtn);
        initializeToolbar(toolbar, noBtn);
        add(toolbar);
        pack();
    }

    private void initializeBtn(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("noooooo");
                MainFrame.getInstance().getSw().dispose();
            }
        });
    }

    private void initializeToolbar(JToolBar toolbar, JButton noBtn){
        toolbar.setPreferredSize(new Dimension(170, 30));
        toolbar.addSeparator(new Dimension(30, 50));
        toolbar.add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
        toolbar.addSeparator(new Dimension(50, 50));
        toolbar.add(noBtn);
    }
}
