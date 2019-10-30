package painter;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.Point;

import java.awt.Color;

public class PainterApp {
    private CanvasWindow canvas;
    private final BrushOptions brushOptions;

    public PainterApp() {
        canvas = new CanvasWindow("Painter", 900, 800);

        brushOptions = new BrushOptions(Color.BLUE, 12);
        canvas.add(brushOptions, 10 - brushOptions.getBounds().getMinX(), 10);

        canvas.onMouseDown((event) -> paint(event.getPosition()));
        canvas.onDrag((event) -> paint(event.getPosition()));
    }

    private void paint(Point location) {
        int size = brushOptions.getBrushSize();
        Ellipse brushStroke = new Ellipse(0, 0, size, size);
        brushStroke.setFillColor(brushOptions.getColor());
        brushStroke.setStroked(false);
        brushStroke.setCenter(location);
        canvas.add(brushStroke);
    }

    public static void main(String[] args) {
        new PainterApp();
    }
}
