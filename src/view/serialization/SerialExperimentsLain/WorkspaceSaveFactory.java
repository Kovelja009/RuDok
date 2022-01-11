package view.serialization.SerialExperimentsLain;

import model.RuNode;
import model.workspace.Projekat;
import model.workspace.Workspace;
import view.MainFrame;
import view.serialization.PresentationFIleFilter;
import view.serialization.WorkspaceFileFilter;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.io.*;
import java.util.Scanner;

public class WorkspaceSaveFactory extends SaveFactory{
    @Override
    public void save(RuNode saveNode, boolean isClosing) {
        Workspace workspace = (Workspace) saveNode;
        File workspaceFile = null;

        boolean saved = true;

        for(RuNode proj : workspace.getChildren()){
            if(proj.isChanged()){
                saved = false;
                break;
            }
        }

        if(!saved){
            shouldSave = false;
            return;
        }

        if(isClosing)
            workspaceFile = new File("src/load_workspace.txt");
        else{
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new WorkspaceFileFilter());
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                workspaceFile =jfc.getSelectedFile();
                if(!workspaceFile.getAbsolutePath().endsWith(".txt")){
                    workspaceFile = new File(workspaceFile.getAbsolutePath()+".txt");
                }
            }else{
                return;
            }
        }

        try (FileWriter writer = new FileWriter(workspaceFile, false)){

            writer.write(workspace.getName());
            writer.write("\n");
            for(RuNode project : workspace.getChildren()){
                if(project.getFile() != null)
                    writer.write(project.getFile().getAbsolutePath());
                else
                    writer.write("null");
                writer.write("\n");
            }
            shouldSave = true;
        }catch (Exception e){
            System.out.println("Can't find path");
        }
    }

    @Override
    public void open(MyTreeNode locTreeNode, boolean isContext) {
        File workspaceFile = null;

        if(isContext)
            workspaceFile = new File("src/load_workspace.txt");
        else{
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new WorkspaceFileFilter());

            if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION)
                workspaceFile = jfc.getSelectedFile();
        }

        try (Scanner sc = new Scanner(workspaceFile)){
            if(!sc.hasNext()){
                System.out.println("empty file");
                return;
            }
            Workspace workspace = new Workspace(sc.next());
            MyTreeNode workspaceTreeNode = new MyTreeNode(workspace);

            while (sc.hasNext()){
                readProject(workspaceTreeNode, sc.next());
            }
            MainFrame.getInstance().getMyTree().setModel(new DefaultTreeModel(workspaceTreeNode));
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readProject(MyTreeNode parentTree, String projectStr){
        if(projectStr.equals("null"))
            return;
        File projectFile = new File(projectStr);

        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(projectFile));
            Projekat p=null;

            try {
                p = (Projekat) os.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

            if(p!= null){
            MyTreeNode projTreeNode = new MyTreeNode(p);
            p.setChanged(true);
            projTreeNode.setParent(parentTree);
            generatePresentations(projTreeNode);
            parentTree.addChild(projTreeNode, parentTree.getChildCount());
            }else {
                System.out.println("neuspesno ucitavanje projekta");
            }
        } catch (IOException e1) {
            System.out.println(e1.getMessage());
        }
    }
}
