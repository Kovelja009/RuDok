package view.factory;

import model.RuNode;
import model.workspace.Projekat;

public class ProjectFactory extends AbstractNodeFactory{
    @Override
    protected RuNode createNode(RuNode parent, int num) {
        return new Projekat("Project " + num, parent);
    }
}
