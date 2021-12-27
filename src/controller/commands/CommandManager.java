package controller.commands;

import view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands;
    private int current;

    public CommandManager(){
        commands = new ArrayList<>();
        current = 0;
    }

    public void addCommand(AbstractCommand command){
        while (current < commands.size())
            commands.remove(current);
        commands.add(command);
        doCommand();
    }

    public void doCommand(){
        if(current < commands.size()){
            commands.get(current++).doCommand();
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
        }
        if(current == commands.size())
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }

    public void undoCommand(){
        if(current > 0){
            commands.get(--current).undoCommand();
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
        }
        if(current == 0)
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }
}
