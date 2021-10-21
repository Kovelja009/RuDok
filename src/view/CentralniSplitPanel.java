package view;

import javax.swing.*;

//nadograditi obicanPanel

public class CentralniSplitPanel extends JSplitPane {
    private JScrollPane leviScrollPane;
    private JPanel obicanPanel;

    public CentralniSplitPanel(){
        super(JSplitPane.HORIZONTAL_SPLIT);

        leviScrollPane = new LeviScrollPane();
        obicanPanel = new JPanel();

        add(leviScrollPane);
        add(obicanPanel);

        setOneTouchExpandable(true);

    }
}
