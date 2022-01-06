package controller.actions;

import controller.errorHandler.ErrorFactory;
import view.MainFrame;
import view.serialization.SerialExperimentsLain.MetaSerializationFactory;
import view.serialization.SerialExperimentsLain.SaveFactory;
import view.serialization.dialogs.OpenDialog;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenAction extends AbstractRudokAction{
    public OpenAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("../images/open.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Opening model");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OpenDialog openDialog = new OpenDialog();
        openDialog.setVisible(true);
        MyTreeNode selectedTreeNode = openDialog.getSelectedTreeNode();
        if(selectedTreeNode == null){
            ErrorFactory.getInstance().generateError("Must select Project as location!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

//        if(selectedTreeNode.getNode() instanceof Workspace)
//            openProject(selectedTreeNode);
//        else
//            openPresentation(selectedTreeNode);

        SaveFactory openFactory = MetaSerializationFactory.returnOpenFactory(selectedTreeNode);
        if(openFactory == null){
            ErrorFactory.getInstance().generateError("Must select valid node", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        openFactory.open(selectedTreeNode, false);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }

//    private void openProject(MyTreeNode locTreeNode){
//
//    }
//
//    private void openPresentation(MyTreeNode locTreeNode){
//
//    }
//
//    protected void generateSlides(MyTreeNode presTreeNode){
//        Prezentacija p = (Prezentacija) presTreeNode.getNode();
//        for(RuNode s : p.getChildren()){
//            MyTreeNode slideTree = new MyTreeNode(s);
//            presTreeNode.getChildren().add(slideTree);
//            slideTree.setParent(presTreeNode);
//        }
//    }
//
//    protected void generatePresentations(MyTreeNode projTreeNode){
//        Projekat p = (Projekat) projTreeNode.getNode();
//        for(RuNode pr : p.getChildren()){
//            MyTreeNode presTreeNode = new MyTreeNode(pr);
//            projTreeNode.getChildren().add(presTreeNode);
//            presTreeNode.setParent(projTreeNode);
//            generateSlides(presTreeNode);
//        }
//    }
}
