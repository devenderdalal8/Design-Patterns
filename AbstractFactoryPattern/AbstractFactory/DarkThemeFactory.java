package AbstractFactoryPattern.AbstractFactory;

import AbstractFactoryPattern.Button;
import AbstractFactoryPattern.Checkbox;
import AbstractFactoryPattern.TextField;
import AbstractFactoryPattern.Dark.DarkButton;
import AbstractFactoryPattern.Dark.DarkCheckBox;
import AbstractFactoryPattern.Dark.DarkTextField;

public class DarkThemeFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new DarkCheckBox();
    }

    @Override
    public TextField createTextField() {
        return new DarkTextField();
    }

}
