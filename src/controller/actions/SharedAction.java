package controller.actions;

import controller.errorHandler.ErrorFactory;
import model.RuNodeComposite;
import model.workspace.Prezentacija;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SharedAction extends AbstractRudokAction{
    public SharedAction(){
        putValue(SMALL_ICON, loadIcon("../images/share.png"));
        putValue(NAME, "Share");
        putValue(SHORT_DESCRIPTION, "Sharing presentations");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode treeNode = (MyTreeNode) MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if (!(treeNode.getNode() instanceof Prezentacija)){
            ErrorFactory.getInstance().generateError("Must select presentation in order to share it", "Error", JOptionPane.ERROR_MESSAGE);
        return;
        }

        MyTreeNode workspaceTreeNode = (MyTreeNode)MainFrame.getInstance().getMyTree().getModel().getRoot();

        List<JRadioButton> buttons = new ArrayList<>();
        JPanel panel = new JPanel();
        List<Integer> indexi = new ArrayList<>();
        MyTreeNode currProj = null;
        for(TreeNode proj : workspaceTreeNode.getChildren())
            if(((MyTreeNode)proj).getChildren().contains(treeNode)){
                currProj = (MyTreeNode) proj;
                System.out.println("CurrProject that shares: " + currProj.getNode().getName());
                break;
            }

        for(int i = 0; i < workspaceTreeNode.getChildCount(); i++){
            if(!(workspaceTreeNode.getChildAt(i).equals(currProj)) && !((MyTreeNode)workspaceTreeNode.getChildAt(i)).containSharing(treeNode)){
                JRadioButton tmp = new JRadioButton(workspaceTreeNode.getChildAt(i).toString());
                buttons.add(tmp);
                panel.add(tmp);
                indexi.add(i);
            }
        }

        JDialog dialog = new JDialog(MainFrame.getInstance(), true);
        dialog.setTitle("Choose project as location");
        JButton selectBtn = new JButton("select");

        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = 0;

                System.out.println("Sharing presentation with: ");
                for(int i = 0; i < buttons.size(); i++){
                    if(buttons.get(i).isSelected()){
                        MyTreeNode shareProject = (MyTreeNode) workspaceTreeNode.getChildAt(indexi.get(i));

                        MyTreeNode sharePresentation = new MyTreeNode(treeNode.getNode());
                        sharePresentation.setSharedNode(true);
                        treeNode.setSharedNode(true);
                        shareProject.addChild(sharePresentation, shareProject.getChildCount());
                        System.out.print(shareProject.toString() + " ");

                        generateSlides(sharePresentation, treeNode);
                        selected++;

                    }
                }

                if(selected == 0){
                    ErrorFactory.getInstance().generateError("Nothing selected", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
                dialog.dispose();
            }
        });

        panel.add(selectBtn);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(MainFrame.getInstance());
        dialog.setVisible(true);
    }

    private void generateSlides(MyTreeNode sharedPresentation, MyTreeNode original){
        for(int i = 0; i < original.getChildCount(); i++){
            ((MyTreeNode)original.getChildren().get(i)).setSharedNode(true);
            MyTreeNode slideTreeNode = new MyTreeNode(((RuNodeComposite)original.getNode()).getChildren().get(i));
            slideTreeNode.setSharedNode(true);
            sharedPresentation.addChild(slideTreeNode, sharedPresentation.getChildCount());
        }
    }
}
