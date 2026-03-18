package AbstractFactoryPattern.Light;

import AbstractFactoryPattern.TextField;

public class LightTextField implements TextField {

    @Override
    public void render() {
        System.out.println("Rendering Light TextField");
    }

    @Override
    public void setText(String text) {
        System.out.println("Setting text in Light TextField: " + text);
    }
}