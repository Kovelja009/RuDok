package view.tree.controller;

import model.workspace.Slide;
import view.tree.model.MyTreeNode;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MyTreeSelectionListener implements TreeSelectionListener{

    public void valueChanged(TreeSelectionEvent e) {

        TreePath path = e.getPath();

            for(int i=0; i<path.getPathCount(); i++){
                if(path.getPathComponent(i) instanceof MyTreeNode d && ((MyTreeNode)path.getPathComponent(i)).getNode() instanceof Slide){
                //selektovan je dijagram u stablu, potreno je pronaci odgovarajuci
                //DiagramView i postaviti ga u fokus
                System.out.println("Selektovan MyTreeNode: "+d);

                System.out.println("getPath: "+e.getPath());
                System.out.println("getPath: "+e.getNewLeadSelectionPath());
                break;
            }
        }
    }
}
