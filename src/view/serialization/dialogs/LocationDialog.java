package view.serialization.dialogs;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LocationDialog extends JDialog {
    private MyTreeNode selectedTreeNode;
    private List<JRadioButton> buttons;
    private boolean selection = true;

    public LocationDialog(){
        setModal(true);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Choose project to place in");

        MyTreeNode workspaceTreeNode = (MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot();
        ButtonGroup btngroup = new ButtonGroup();
        JPanel pnl = new JPanel();
        buttons = new ArrayList<>();
        for(int i = 0; i < workspaceTreeNode.getChildCount(); i++){
            JRadioButton tmp = new JRadioButton(workspaceTreeNode.getChildAt(i).toString());
            btngroup.add(tmp);
            buttons.add(tmp);
            tmp.setSelected(true);
            pnl.add(tmp);
        }

        if(buttons.size() == 0)
            selection = false;

        JButton selectBtn = new JButton("select");
        pnl.add(selectBtn);
        add(pnl);
        this.pack();

        initializebtn(selectBtn);
    }

    private void initializebtn(JButton selectBtn){
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = false;
                MyTreeNode workspaceTreeNode = (MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot();

                for(int i = 0; i < buttons.size(); i++){
                    if(buttons.get(i).isSelected()){
                        selected = true;
                        selectedTreeNode = (MyTreeNode) workspaceTreeNode.getChildAt(i);
                        break;
                    }
                }
                if(selected)
                    LocationDialog.this.dispose();
                else
                    ErrorFactory.getInstance().generateError("Must select project", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public MyTreeNode getSelectedTreeNode() {
        return selectedTreeNode;
    }

    public boolean isSelection() {
        return selection;
    }
}
