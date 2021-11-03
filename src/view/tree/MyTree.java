package view.tree;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyTree extends JTree {
    public MyTree() {

        addTreeSelectionListener(new MyTreeSelectionListener());
        setCellEditor(new MyTreeEditor(this,new DefaultTreeCellRenderer()));
        setCellRenderer(new MyCellRenderer());
        setEditable(true);
    }

}
