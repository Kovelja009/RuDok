package view.tree.view;

import view.tree.controller.BrojKlikovaKontroler;
import view.tree.controller.MyCellRenderer;
import view.tree.controller.MyTreeEditor;
import view.tree.controller.MyTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyTree extends JTree {
    public MyTree() {

        addTreeSelectionListener(new MyTreeSelectionListener());
        setCellEditor(new MyTreeEditor(this,new DefaultTreeCellRenderer()));
        setCellRenderer(new MyCellRenderer());
        setEditable(true);

        BrojKlikovaKontroler m = new BrojKlikovaKontroler();
        this.addMouseListener(m);

    }
}
