package controller.state.concreteEditorState;

import controller.state.EditorState;

public class TextState implements EditorState {
    @Override
    public String getType() {
        return "text";
    }
}
