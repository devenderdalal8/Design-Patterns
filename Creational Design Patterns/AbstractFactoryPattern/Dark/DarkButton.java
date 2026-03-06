package AbstractFactoryPattern.Dark;

import AbstractFactoryPattern.Button;

public class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Dark Button");
    }

    @Override
    public void onClick() {
        System.out.println("Dark Button Clicked");
    }
    
}
