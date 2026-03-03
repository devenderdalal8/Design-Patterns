package AbstractFactoryPattern.Light;

import AbstractFactoryPattern.Button;

public class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Light Button");
    }

    @Override
    public void onClick() {
        System.out.println("Light Button Clicked");
    }
    
}
