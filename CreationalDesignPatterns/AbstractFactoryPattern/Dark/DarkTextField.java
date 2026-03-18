package AbstractFactoryPattern.Dark;

import AbstractFactoryPattern.TextField;

public class DarkTextField implements TextField {
    @Override
    public void render() {
        System.out.println("Rendering Dark TextField");
    }

    @Override
    public void setText(String text) {
        System.out.println("Setting text in Dark TextField: " + text);
    }

    
}
