package view.tree.controller;

import model.workspace.Projekat;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import view.tree.view.MyTree;
import view.workspaceView.PrezentacijaView;
import view.workspaceView.ProjekatView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BrojKlikovaKontroler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(e.getClickCount() == 2 && o instanceof MyTreeNode && ((MyTreeNode)o).getNode() instanceof Projekat){
            Projekat p = (Projekat) ((MyTreeNode)o).getNode();
            MainFrame.getInstance().getMainProjectView().setProjekatRuNode(p);
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
