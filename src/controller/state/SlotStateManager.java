package controller.state;

import controller.state.concreteSlotState.AddState;
import controller.state.concreteSlotState.DragState;
import controller.state.concreteSlotState.SelectState;
import controller.state.concreteSlotState.RemoveState;

import java.awt.*;

public class SlotStateManager {
    private StateSlot curr;
    private AddState addState;
    private RemoveState removeState;
    private SelectState selectState;
    private DragState dragState;
    private Color color = new Color(24, 35, 234, 150);

    public SlotStateManager(){
        addState = new AddState();
        removeState = new RemoveState();
        selectState = new SelectState();
        dragState = new DragState();
        curr = selectState;
    }

    public StateSlot getCurr() {
        return curr;
    }

    public void setAddState() {curr = addState;}
    public void setRemoveState() {
        curr = removeState;
    }
    public void setSelectState(){
        curr = selectState;
    }
    public void setDragState(){curr = dragState;}

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 150);
    }
}
