package BehavioralDesignPatterns.Command;

import BehavioralDesignPatterns.Command.ConcreteCommand.InsertCommand;
import BehavioralDesignPatterns.Command.receiver.TextEditor;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        CommandManager manager = new CommandManager();

        manager.executeCommand(new InsertCommand(editor, "Hello, "));
        manager.executeCommand(new InsertCommand(editor, "World!"));
        // Text: Hello, World!

        manager.undoLast();
        // Text: Hello,

        manager.undoLast();
        // Text: (empty)
    }
}
