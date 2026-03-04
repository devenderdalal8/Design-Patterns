package Bridge.Shape.implementation;

public interface DrawingAPI {
    void drawCircle(double x, double y, double radius);
    void drawRectangle(double x, double y, double width, double height);
    void drawTriangle(double x1, double y1, double x2, double y2, double x3, double y3);
    String getAPIName();
}
