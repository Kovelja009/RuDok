package view.factory;

import model.RuNode;

public abstract class AbstractNodeFactory {
    public RuNode getNode(RuNode parent, int num){
        return createNode(parent, num);
    }

    protected abstract RuNode createNode(RuNode parent, int num);
}
