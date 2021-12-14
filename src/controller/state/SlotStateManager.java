package controller.state;

import controller.state.concreteSlotState.AddState;
import controller.state.concreteSlotState.DefaultSlotState;
import controller.state.concreteSlotState.RemoveState;

public class SlotStateManager {
    private static SlotStateManager instance = null;
    private StateSlot curr;
    private AddState addState;
    private RemoveState removeState;
    private DefaultSlotState defaultSlotState;

    public SlotStateManager(){
        addState = new AddState();
        removeState = new RemoveState();
        defaultSlotState = new DefaultSlotState();
        curr = defaultSlotState;
    }

    public StateSlot getCurr() {
        return curr;
    }

    public void setAddState() {curr = addState;}
    public void setRemoveState() {
        curr = removeState;
    }
    public void setDefaultSlotState(){
        curr = defaultSlotState;
    }
}
