package AbstractFactoryPattern.AbstractFactory;

import AbstractFactoryPattern.Button;
import AbstractFactoryPattern.Checkbox;
import AbstractFactoryPattern.TextField;

public interface UIFactory {
    Button createButton();

    Checkbox createCheckbox();

    TextField createTextField();
}
