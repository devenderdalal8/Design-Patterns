package Flyweight;

public class TreeType {
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
        System.out.println("[TreeType Created] " + name
                + " | Memory heavy object loaded once!");
    }

    public void render(int x, int y) {
        System.out.println("Rendering " + name
                + " tree [color=" + color
                + ", texture=" + texture + "]"
                + " at (" + x + ", " + y + ")");
    }

}
