package view.serialization.SerialExperimentsLain;

import model.RuNode;
import model.workspace.Projekat;
import view.MainFrame;
import view.serialization.ProjectFileFilter;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.io.*;

public class ProjectSaveFactory extends SaveFactory{
    @Override
    public void save(RuNode saveNode) {
        Projekat projekat = (Projekat) saveNode;
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFileFilter());
        File projectFile = projekat.getFile();
        if(!projekat.isChanged()){
            System.out.println(projekat.getName() + " nije menjan");
            return;
        }

        if(projekat.getFile() == null){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                projectFile=jfc.getSelectedFile();
                if(!projectFile.getAbsolutePath().endsWith(".rpf")){
                    projectFile = new File(projectFile.getAbsolutePath()+".rpf");
                }
            }else
                return;
        }

        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(projekat);
            projekat.setFile(projectFile);
            projekat.setChanged(false);
            System.out.println("Uspesno serijalizovan" + projekat.getName());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void saveAs(RuNode saveNode) {
        Projekat project = (Projekat) saveNode;
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFileFilter());
        File projectFile = null;

        if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            projectFile=jfc.getSelectedFile();
            if(!projectFile.getAbsolutePath().endsWith(".rpf")){
                projectFile = new File(projectFile.getAbsolutePath()+".rpf");
            }
        }else{
            return;
        }
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(project);
            System.out.println("Uspesno serijalizovano saveAs");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void open(MyTreeNode locTreeNode) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFileFilter());

        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
                Projekat p=null;

                try {
                    p = (Projekat) os.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                MyTreeNode projTreeNode = new MyTreeNode(p);
                projTreeNode.setParent(locTreeNode);
                generatePresentations(projTreeNode);
                locTreeNode.addChild(projTreeNode, locTreeNode.getChildCount());
                MainFrame.getInstance().getMainProjectView().setProjekatRuNode(p);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
