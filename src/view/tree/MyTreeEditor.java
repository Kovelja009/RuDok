package view.tree;

import model.Projekat;
import model.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MyTreeEditor extends DefaultTreeCellEditor implements ActionListener{
    private Object stavka=null;
    private JTextField edit=null;

    public MyTreeEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {

        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        stavka=arg1;

        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }



    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            return ((MouseEvent) arg0).getClickCount() == 3;
        return false;
    }



    public void actionPerformed(ActionEvent e){
        RuNode node;
        if(stavka instanceof MyTreeNode){
            node = ((MyTreeNode)stavka).getNode();
            node.setName(e.getActionCommand());
        }

        //posle promene imena ili dijagrama treba obezbediti i promenu imena u GEDView-u



    }
}
