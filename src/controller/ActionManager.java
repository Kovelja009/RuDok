package controller;



public class ActionManager {
    private InfoAction infoAction;
    private NewAction newAction;
    private PopupAutorAction popupAutorAction;
    private PopupPozadinaAction popupPozadinaAction;
    private NewProjekatAction newProjekatAction;

    public ActionManager(){
        infoAction = new InfoAction();
        newAction = new NewAction();
        popupAutorAction = new PopupAutorAction();
        popupPozadinaAction = new PopupPozadinaAction();
        newProjekatAction = new NewProjekatAction();

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

    public PopupAutorAction getPopupAutorAction() {
        return popupAutorAction;
    }

    public void setPopupAutorAction(PopupAutorAction popupAutorAction) {
        this.popupAutorAction = popupAutorAction;
    }

    public PopupPozadinaAction getPopupPozadinaAction() {
        return popupPozadinaAction;
    }

    public void setPopupPozadinaAction(PopupPozadinaAction popupPozadinaAction) {
        this.popupPozadinaAction = popupPozadinaAction;
    }

    public NewProjekatAction getNewProjekatAction() {
        return newProjekatAction;
    }

    public void setNewProjekatAction(NewProjekatAction newProjekatAction) {
        this.newProjekatAction = newProjekatAction;
    }
}
