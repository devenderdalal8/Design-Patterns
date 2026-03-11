package BehavioralDesignPatterns.Command.receiver;

public class TextEditor {
    private StringBuilder text = new StringBuilder();

    public void insert(String str) {
        text.append(str);
        System.out.println("Text: " + text);
    }

    public void deleteText(int length) {
        int start = text.length() - length;
        text.delete(start, text.length());
        System.out.println("Text: " + text);
    }

    public String getText() {
        return text.toString();
    }

}
