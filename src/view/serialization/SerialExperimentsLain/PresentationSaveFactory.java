package view.serialization.SerialExperimentsLain;

import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import view.MainFrame;
import view.serialization.PresentationFIleFilter;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.io.*;

public class PresentationSaveFactory extends SaveFactory{
    @Override
    public void save(RuNode saveNode, boolean isClosing) {
        Prezentacija presentation = (Prezentacija) saveNode;
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new PresentationFIleFilter());
        File presentationFile = presentation.getFile();
        if(!presentation.isChanged()){
            System.out.println(presentation.getName() + " nije menjan");
            return;
        }

        if(presentation.getFile() == null){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                presentationFile =jfc.getSelectedFile();
                if(!presentationFile.getAbsolutePath().endsWith(".rdf")){
                    presentationFile = new File(presentationFile.getAbsolutePath()+".rdf");
                }

            }else
                return;
        }

        ObjectOutputStream os;
        try {
            presentation.setFile(presentationFile);
            presentation.setChanged(false);
            String ime = presentation.getName();
            if(ime.endsWith("*"))
                presentation.setName(ime.substring(0, ime.toCharArray().length - 1));
            os = new ObjectOutputStream(new FileOutputStream(presentationFile));
            os.writeObject(presentation);
            System.out.println("Uspesno serijalizovan " + presentation.getName());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void saveAs(RuNode saveNode) {

        Prezentacija presentation = (Prezentacija)saveNode;
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new PresentationFIleFilter());
        File presentationFile = null;

        if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            presentationFile =jfc.getSelectedFile();
            if(!presentationFile.getAbsolutePath().endsWith(".rdf")){
                presentationFile = new File(presentationFile.getAbsolutePath()+".rdf");
            }

        }else{
            return;
        }

        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(presentationFile));
            os.writeObject(presentation);
            System.out.println("Uspesno serijalizovano saveAs");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void open(MyTreeNode locTreeNode, boolean isContext) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new PresentationFIleFilter());

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
                Prezentacija p=null;

                try {
                    p = (Prezentacija) os.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                MyTreeNode presTreeNode = new MyTreeNode(p);
                presTreeNode.setParent(locTreeNode);
                generateSlides(presTreeNode);
                locTreeNode.addChild(presTreeNode, locTreeNode.getChildCount());
                MainFrame.getInstance().getMainProjectView().setProjekatRuNode((Projekat) locTreeNode.getNode());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
