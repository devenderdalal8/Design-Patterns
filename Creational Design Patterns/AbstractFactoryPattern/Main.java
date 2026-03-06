package AbstractFactoryPattern;

import AbstractFactoryPattern.AbstractFactory.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("======= DARK THEME =======");
        buildUI(new DarkThemeFactory());

        System.out.println("\n======= LIGHT THEME =======");
        buildUI(new LightThemeFactory());
    }

    private static void buildUI(UIFactory factory) {
        Button btn = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        TextField textField = factory.createTextField();

        System.out.println("--- Rendering UI Components ---");
        btn.render();
        checkbox.render();
        textField.render();

        System.out.println("\n--- Interacting with UI ---");
        btn.onClick();
        checkbox.toggle();
        textField.setText("Hello World");
    }

    
}