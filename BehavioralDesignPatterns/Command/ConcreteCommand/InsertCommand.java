package BehavioralDesignPatterns.Command.ConcreteCommand;

import BehavioralDesignPatterns.Command.Command;
import BehavioralDesignPatterns.Command.receiver.TextEditor;

public class InsertCommand implements Command {
    private final TextEditor editor;
    private final String textToInsert;

    public InsertCommand(TextEditor editor, String text) {
        this.editor = editor;
        this.textToInsert = text;
    }

    @Override
    public void execute() {
        editor.insert(textToInsert);
    }

    @Override
    public void undo() {
        editor.deleteText(textToInsert.length());
    }
}
