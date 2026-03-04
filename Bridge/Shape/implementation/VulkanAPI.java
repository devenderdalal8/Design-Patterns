package Bridge.Shape.implementation;

// VulkanAPI.java
public class VulkanAPI implements DrawingAPI {

    @Override
    public void drawCircle(double x, double y, double radius) {
        System.out.printf("  [Vulkan] Drawing Circle    → center(%.1f, %.1f) radius=%.1f%n",
                          x, y, radius);
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        System.out.printf("  [Vulkan] Drawing Rectangle → pos(%.1f, %.1f) size=%.1fx%.1f%n",
                          x, y, width, height);
    }

    @Override
    public void drawTriangle(double x1, double y1,
                             double x2, double y2,
                             double x3, double y3) {
        System.out.printf("  [Vulkan] Drawing Triangle  → (%.1f,%.1f) (%.1f,%.1f) (%.1f,%.1f)%n",
                          x1, y1, x2, y2, x3, y3);
    }

    @Override
    public String getAPIName() { return "Vulkan"; }
}