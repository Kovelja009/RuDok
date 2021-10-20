package controller;



public class ActionManager {
    private InfoAction infoAction;
    private NewAction newAction;

    public ActionManager(){
        infoAction = new InfoAction();
        newAction = new NewAction();
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public NewAction getNewAction() {
        return newAction;
    }

    public void setNewAction(NewAction newAction) {
        this.newAction = newAction;
    }
}
