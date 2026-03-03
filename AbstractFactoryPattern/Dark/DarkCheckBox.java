package AbstractFactoryPattern.Dark;

import AbstractFactoryPattern.Checkbox;

public class DarkCheckBox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Dark Checkbox");
    }

    @Override
    public void toggle() {
        System.out.println("Toggling Dark Checkbox");
    }
}
