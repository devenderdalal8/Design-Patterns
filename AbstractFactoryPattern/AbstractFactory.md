Abstract Factory Pattern in Java
The Abstract Factory is a creational pattern that produces families of related objects without specifying their concrete classes.
Real-World Analogy

A UI theme factory — Dark Theme and Light Theme each produce a matching family of Button, Checkbox, and TextField. You switch the entire family by swapping the factory.

src/
├── Button.java              (interface)
├── Checkbox.java            (interface)
├── TextField.java           (interface)
│
├── DarkButton.java
├── DarkCheckbox.java
├── DarkTextField.java
│
├── LightButton.java
├── LightCheckbox.java
├── LightTextField.java
│
├── UIFactory.java           (Abstract Factory interface)
├── DarkThemeFactory.java    (Concrete Factory 1)
├── LightThemeFactory.java   (Concrete Factory 2)
│
└── Main.java                (Client)