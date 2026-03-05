package Flyweight;

public class Main {
    public static void main(String[] args) {

        Forest forest = new Forest();

        // Plant 6 trees — only 3 types exist (Oak, Pine, Maple)
        // TreeType objects are created ONCE and reused

        forest.plantTree(1, 2, "Oak", "Green", "oak_texture.png");
        forest.plantTree(5, 8, "Pine", "DarkGreen", "pine_texture.png");
        forest.plantTree(12, 3, "Maple", "Orange", "maple_texture.png");
        forest.plantTree(7, 15, "Oak", "Green", "oak_texture.png"); // reused
        forest.plantTree(20, 6, "Pine", "DarkGreen", "pine_texture.png"); // reused
        forest.plantTree(3, 18, "Oak", "Green", "oak_texture.png"); // reused

        forest.renderForest();

        System.out.println("\n--- Memory Report ---");
        System.out.println("Total Tree objects       : 6");
        System.out.println("Total TreeType objects   : "
                + TreeFactory.getTotalTreeTypesCreated()
                + "  ← only 3 heavy objects in memory!");
        System.out.println("Without Flyweight would be: 6 heavy objects");
    }
}
