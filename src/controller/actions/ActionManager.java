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
    private SelectSlotAction selectSlotAction;
    private ColorPickerAction colorPickerAction;
    private DragSlotAction dragSlotAction;
    private DashedAction dashedAction;
    private CircleAction circleAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private PictureAction pictureAction;
    private TextAction textAction;
    private EditorAction editorAction;
    private SaveSlotChangeAction saveSlotChangeAction;
    private SharedAction sharedAction;

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
        selectSlotAction = new SelectSlotAction();
        colorPickerAction = new ColorPickerAction();
        dragSlotAction = new DragSlotAction();
        dashedAction = new DashedAction();
        circleAction = new CircleAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        pictureAction = new PictureAction();
        textAction = new TextAction();
        editorAction = new EditorAction();
        saveSlotChangeAction = new SaveSlotChangeAction();
        sharedAction = new SharedAction();
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

    public ColorPickerAction getColorPickerAction() {
        return colorPickerAction;
    }

    public SelectSlotAction getSelectSlotAction() {
        return selectSlotAction;
    }

    public DragSlotAction getDragSlotAction() {
        return dragSlotAction;
    }

    public DashedAction getDashedAction() {
        return dashedAction;
    }

    public CircleAction getCircleAction() {
        return circleAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public PictureAction getPictureAction() {
        return pictureAction;
    }

    public TextAction getTextAction() {
        return textAction;
    }

    public EditorAction getEditorAction() {
        return editorAction;
    }

    public SaveSlotChangeAction getSaveSlotChangeAction() {
        return saveSlotChangeAction;
    }

    public SharedAction getSharedAction() {
        return sharedAction;
    }
}
