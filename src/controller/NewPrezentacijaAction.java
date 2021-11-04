package controller;

import model.Prezentacija;
import model.Projekat;
import model.RuNode;
import view.MainFrame;
import view.tree.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewPrezentacijaAction extends AbstractRudokAction{
    public NewPrezentacijaAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("images/add_img.png"));
        putValue(NAME, "Dodaj prezentaciju");
        putValue(SHORT_DESCRIPTION, "Dodavanje prezentacije");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(o instanceof MyTreeNode && ((MyTreeNode)o).getNode() instanceof Projekat){
            MyTreeNode projekatTreeNode = (MyTreeNode)o;
            RuNode projekat = projekatTreeNode.getNode();
            MyTreeNode detePrezentacija = new MyTreeNode(new Prezentacija("Nova prezentacija", projekat));
            projekatTreeNode.addChild(detePrezentacija);
            detePrezentacija.setParent(projekatTreeNode);

            System.out.println("Dodato dete " + detePrezentacija + " na " + projekatTreeNode);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
            return;
        }
        if(o!= null){
        System.out.println(o + " nije instanca MyTreeNode ili ruNode " + ((MyTreeNode)o).getNode().getName() + " nije instanca projekta!!!");
        }
    }
}
