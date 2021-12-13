package view.factory;

import model.RuNode;
import model.workspace.Prezentacija;

public class PrezentacijaFactory extends AbstractNodeFactory{

    @Override
    protected RuNode createNode(RuNode parent, int num) {
        return new Prezentacija("Prezentacija " + num, parent);
    }
}
