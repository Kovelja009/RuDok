package controller.state.concreteEditorState;

import controller.state.EditorState;

public class PictureState implements EditorState {
    @Override
    public String getType() {
        return "picture";
    }
}
