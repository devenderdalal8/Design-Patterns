package Bridge.Shape;

import Bridge.Shape.abstraction.*;
import Bridge.Shape.implementation.*;

// Main.java
public class Main {
    public static void main(String[] args) {

        // Create rendering APIs
        DrawingAPI openGL  = new OpenGLAPI();
        DrawingAPI directX = new DirectXAPI();
        DrawingAPI vulkan  = new VulkanAPI();

        System.out.println("============================================");
        System.out.println("   BRIDGE PATTERN — Shape + Rendering API  ");
        System.out.println("============================================\n");

        // Circle with OpenGL
        System.out.println(">>> Circle using OpenGL");
        Shape circle = new Circle(5.0, 10.0, 7.0, openGL);
        circle.describe();
        circle.draw();

        // Resize and redraw
        circle.resize(2.0);
        circle.draw();

        // Switch API at runtime — THE BRIDGE IN ACTION!
        System.out.println();
        circle.switchAPI(directX);
        circle.draw();

        System.out.println("\n--------------------------------------------\n");

        // Rectangle with DirectX
        System.out.println(">>> Rectangle using DirectX");
        Shape rectangle = new Rectangle(0, 0, 20.0, 10.0, directX);
        rectangle.describe();
        rectangle.draw();

        System.out.println("\n--------------------------------------------\n");

        // Triangle with Vulkan
        System.out.println(">>> Triangle using Vulkan");
        Shape triangle = new Triangle(0, 0, 5, 10, 10, 0, vulkan);
        triangle.describe();
        triangle.draw();

        // Switch to OpenGL at runtime
        System.out.println();
        triangle.switchAPI(openGL);
        triangle.draw();

        System.out.println("\n============================================");
        System.out.println("   Drawing ALL shapes with ALL APIs         ");
        System.out.println("============================================\n");

        DrawingAPI[] apis = {openGL, directX, vulkan};

        for (DrawingAPI api : apis) {
            System.out.println("--- Using " + api.getAPIName() + " ---");
            new Circle(1, 1, 5, api).draw();
            new Rectangle(0, 0, 10, 5, api).draw();
            new Triangle(0, 0, 4, 8, 8, 0, api).draw();
            System.out.println();
        }
    }
}
// ```

// ---

// ### Output
// ```
// ============================================
//    BRIDGE PATTERN — Shape + Rendering API
// ============================================

// >>> Circle using OpenGL
//   📐 Shape     : Circle
//   🖥️  Renderer  : OpenGL
//   [OpenGL] Drawing Circle    → center(5.0, 10.0) radius=7.0
//   ↔️  Resizing Circle by factor: 2.0
//   [OpenGL] Drawing Circle    → center(5.0, 10.0) radius=14.0

//   🔄 Switching from OpenGL → DirectX
//   [DirectX] Drawing Circle   → center(5.0, 10.0) radius=14.0

// --------------------------------------------

// >>> Rectangle using DirectX
//   📐 Shape     : Rectangle
//   🖥️  Renderer  : DirectX
//   [DirectX] Drawing Rectangle → pos(0.0, 0.0) size=20.0x10.0

// --------------------------------------------

// >>> Triangle using Vulkan
//   📐 Shape     : Triangle
//   🖥️  Renderer  : Vulkan
//   [Vulkan] Drawing Triangle  → (0.0,0.0) (5.0,10.0) (10.0,0.0)

//   🔄 Switching from Vulkan → OpenGL
//   [OpenGL] Drawing Triangle  → (0.0,0.0) (5.0,10.0) (10.0,0.0)

// ============================================
//    Drawing ALL shapes with ALL APIs
// ============================================

// --- Using OpenGL ---
//   [OpenGL] Drawing Circle     → center(1.0, 1.0) radius=5.0
//   [OpenGL] Drawing Rectangle  → pos(0.0, 0.0) size=10.0x5.0
//   [OpenGL] Drawing Triangle   → (0.0,0.0) (4.0,8.0) (8.0,0.0)

// --- Using DirectX ---
//   [DirectX] Drawing Circle    → center(1.0, 1.0) radius=5.0
//   [DirectX] Drawing Rectangle → pos(0.0, 0.0) size=10.0x5.0
//   [DirectX] Drawing Triangle  → (0.0,0.0) (4.0,8.0) (8.0,0.0)

// --- Using Vulkan ---
//   [Vulkan] Drawing Circle     → center(1.0, 1.0) radius=5.0
//   [Vulkan] Drawing Rectangle  → pos(0.0, 0.0) size=10.0x5.0
//   [Vulkan] Drawing Triangle   → (0.0,0.0) (4.0,8.0) (8.0,0.0)
// ```

// ---

// ### Architecture Diagram
// ```
//       ABSTRACTION                     IMPLEMENTOR
//   ┌─────────────────┐             ┌─────────────────┐
//   │     Shape       │             │   DrawingAPI     │
//   │─────────────────│   Bridge    │─────────────────│
//   │ #drawingAPI ────┼────────────►│ drawCircle()    │
//   │ draw()          │             │ drawRectangle() │
//   │ resize()        │             │ drawTriangle()  │
//   └────────┬────────┘             └────────┬────────┘
//            │                               │
//     ┌──────┼──────┐               ┌────────┼────────┐
//     ▼      ▼      ▼               ▼        ▼        ▼
//  Circle Rectangle Triangle    OpenGL  DirectX  Vulkan
