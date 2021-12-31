package view.tree.controller;

import model.RuNode;
import model.workspace.*;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MyCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);


        RuNode node = null;
        if(value instanceof MyTreeNode){
            node = ((MyTreeNode) value).getNode();
        }


        if (node instanceof Projekat) {
            URL imageURL = getClass().getResource("../../../controller/images/folder.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        } else if (node instanceof Prezentacija) {
            URL imageURL = getClass().getResource("../../../controller/images/presentation.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        } else if (node instanceof Slide) {
            URL imageURL = getClass().getResource("../../../controller/images/slide.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        }else if (node instanceof Workspace) {
            URL imageURL = getClass().getResource("../../../controller/images/workspace.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        }
        if(value instanceof MyTreeNode && ((MyTreeNode) value).isShared()){
            URL imageURL = getClass().getResource("../../../controller/images/presentationShared.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        }



        return this;
    }
}
