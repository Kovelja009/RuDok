package controller.state;

import controller.state.concreteEditorState.PictureState;
import controller.state.concreteEditorState.TextState;

public class EditorStateManager {
    private PictureState pictureState;
    private TextState textState;
    private EditorState curr;

    public EditorStateManager() {
        pictureState = new PictureState();
        textState = new TextState();
        curr = textState;
    }

    public EditorState getCurr(){return curr;}

    public void setPictureState(){curr = pictureState;}
    public void setTextState(){curr = textState;}
}
