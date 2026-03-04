package Bridge.Shape.abstraction;

import Bridge.Shape.implementation.DrawingAPI;

public abstract class Shape {

    DrawingAPI drawingAPI;

    Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    public abstract void draw();

    public abstract void resize(double factor);

    public abstract String getShapeName();

    public void switchAPI(DrawingAPI newAPI) {
        System.out.println("  🔄 Switching from " + drawingAPI.getAPIName()
                + " → " + newAPI.getAPIName());
        this.drawingAPI = newAPI;
    }

    public void describe() {
        System.out.println("  📐 Shape     : " + getShapeName());
        System.out.println("  🖥️  Renderer  : " + drawingAPI.getAPIName());
    }

}
