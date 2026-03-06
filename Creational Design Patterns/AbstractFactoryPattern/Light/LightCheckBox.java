package AbstractFactoryPattern.Light;

import AbstractFactoryPattern.Checkbox;

public class LightCheckBox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Rendering Light Checkbox");
    }

    @Override
    public void toggle() {
        System.out.println("Toggling Light Checkbox");
    }

}
