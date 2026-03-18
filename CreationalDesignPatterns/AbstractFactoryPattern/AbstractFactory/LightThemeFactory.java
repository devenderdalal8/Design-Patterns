package AbstractFactoryPattern.AbstractFactory;

import AbstractFactoryPattern.Button;
import AbstractFactoryPattern.Checkbox;
import AbstractFactoryPattern.TextField;
import AbstractFactoryPattern.Light.LightButton;
import AbstractFactoryPattern.Light.LightCheckBox;
import AbstractFactoryPattern.Light.LightTextField;

public class LightThemeFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new LightCheckBox();
    }

    @Override
    public TextField createTextField() {
        return new LightTextField();
    }

}
