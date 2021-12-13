package view.factory;

import model.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Workspace;

public class MetaFactory {
    private static ProjectFactory projectFactory = new ProjectFactory();
    private static PrezentacijaFactory prezentacijaFactory = new PrezentacijaFactory();
    private static SlideFactory slideFactory = new SlideFactory();

    public static AbstractNodeFactory returnFactory(RuNode parent){
        if(parent instanceof Workspace)
            return projectFactory;
        if(parent instanceof Projekat)
            return prezentacijaFactory;
        if(parent instanceof Prezentacija)
            return slideFactory;
        return null;
    }
}
