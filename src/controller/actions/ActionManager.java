package controller.actions;



public class ActionManager {
    private InfoAction infoAction;
    private NewAction newAction;
    private PopupAutorAction popupAutorAction;
    private PopupPozadinaAction popupPozadinaAction;
    private DeleteAction deleteAction;
    private  PreviewAction previewAction;
    private PreviousAction previousAction;
    private NextAction nextAction;
    private EditAction editAction;
    private AddSlotAction addSlotAction;
    private RemoveSlotAction removeSlotAction;
    private DefaultSlotAction defaultSlotAction;
    private ColorPickerAction colorPickerAction;

    public ActionManager(){
        infoAction = new InfoAction();
        newAction = new NewAction();
        popupAutorAction = new PopupAutorAction();
        popupPozadinaAction = new PopupPozadinaAction();
        deleteAction = new DeleteAction();
        previewAction = new PreviewAction();
        previousAction = new PreviousAction();
        nextAction = new NextAction();
        editAction = new EditAction();
        addSlotAction = new AddSlotAction();
        removeSlotAction = new RemoveSlotAction();
        defaultSlotAction = new DefaultSlotAction();
        colorPickerAction = new ColorPickerAction();

    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public NewAction getNewAction() {
        return newAction;
    }

    public PopupAutorAction getPopupAutorAction() {
        return popupAutorAction;
    }

    public PopupPozadinaAction getPopupPozadinaAction() {
        return popupPozadinaAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public PreviewAction getPreviewAction() {
        return previewAction;
    }

    public PreviousAction getPreviousAction() {
        return previousAction;
    }

    public NextAction getNextAction() {
        return nextAction;
    }

    public EditAction getEditAction() {
        return editAction;
    }

    public AddSlotAction getAddSlotAction() {
        return addSlotAction;
    }

    public RemoveSlotAction getRemoveSlotAction() {
        return removeSlotAction;
    }

    public DefaultSlotAction getDefaultSlotAction() {
        return defaultSlotAction;
    }

    public ColorPickerAction getColorPickerAction() {
        return colorPickerAction;
    }
}
