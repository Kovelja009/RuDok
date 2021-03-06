package controller.state;

import controller.state.concreteState.EditState;
import controller.state.concreteState.PreviewState;

import java.awt.*;

public class StateManager {
    private State curr;
    private EditState editState;
    private PreviewState previewState;

    public StateManager(){
        editState = new EditState();
        previewState = new PreviewState();
        curr = editState;
    }


    public State getCurr() {
        return curr;
    }

    public void setEditState() {curr = editState;}

    public void setPreviewState() {
        curr = previewState;
    }
}
