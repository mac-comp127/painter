package painter;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsObject;
import comp127graphics.Point;

import java.awt.Color;

public class PainterApp {
    private CanvasWindow canvas;
    private final BrushOptions brushOptions;

    public PainterApp() {
        canvas = new CanvasWindow("Painter", 900, 800);

        brushOptions = new BrushOptions(Color.BLUE, 60);
        canvas.add(brushOptions, 10 - brushOptions.getBounds().getMinX(), 10);

        paint(canvas.getCenter());  // TODO: Replace with event handlers that use mouse position
    }

    private void paint(Point location) {
        GraphicsObject dot = PaintUtils.createFuzzyDot(
            brushOptions.getColor(),
            brushOptions.getBrushSize(),
            0.2f);
        dot.setCenter(location);
        canvas.add(dot);
    }

    public static void main(String[] args) {
        new PainterApp();
    }
}
