package view.factory;

import model.RuNode;
import model.workspace.Slide;

public class SlideFactory extends AbstractNodeFactory{
    @Override
    protected RuNode createNode(RuNode parent, int num) {
        Slide slide = new Slide("Slide " + num, parent);
        slide.setRedniBroj(num);
        return slide;
    }
}
