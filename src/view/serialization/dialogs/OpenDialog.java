package view.serialization.dialogs;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenDialog extends JDialog {
    String selectedItem;
    MyTreeNode selectedTreeNode;

    public OpenDialog(){
        setModal(true);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Choose object to import");
        JPanel panel = new JPanel();
        ButtonGroup buttons = new ButtonGroup();
        JRadioButton project = new JRadioButton("Project");
        JRadioButton presentation = new JRadioButton("Presentation");
        buttons.add(project);
        buttons.add(presentation);
        project.setSelected(true);
        JButton selectBtn = new JButton("select");
        panel.add(project);
        panel.add(presentation);
        panel.add(selectBtn);
        add(panel);
        this.pack();
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTreeNode workspaceTreeNode = (MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot();
                OpenDialog.this.setVisible(false);
                if(project.isSelected()){
                    selectedItem = "project";
                    selectedTreeNode = workspaceTreeNode;
                }else{
                    selectedItem = "presentation";
                    LocationDialog locationDialog = new LocationDialog();
                    if(locationDialog.isSelection())
                        locationDialog.setVisible(true);
                    else
                        ErrorFactory.getInstance().generateError("No projects to place in!", "Warning", JOptionPane.WARNING_MESSAGE);
                    selectedTreeNode = locationDialog.getSelectedTreeNode();
                }
                if(selectedTreeNode != null)
                    System.out.println(selectedTreeNode.getNode().getName());
            }
        });
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public MyTreeNode getSelectedTreeNode() {
        return selectedTreeNode;
    }
}
