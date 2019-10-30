package painter;

import comp127graphics.CanvasWindow;

import java.awt.Color;

public class PainterApp {
    private CanvasWindow canvas;
    private final BrushOptions brushOptions;

    public PainterApp() {
        canvas = new CanvasWindow("Painter", 900, 800);

        brushOptions = new BrushOptions(Color.BLUE, 12);
        canvas.add(brushOptions, 10 - brushOptions.getBounds().getMinX(), 10);
    }

    public static void main(String[] args) {
        new PainterApp();
    }
}
