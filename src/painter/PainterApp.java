package painter;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

import java.awt.Color;

public class PainterApp {
    private CanvasWindow canvas;
    private final PaintSettingsView paintSettingsView;

    public PainterApp() {
        canvas = new CanvasWindow("Painter", 900, 800);

        paintSettingsView = new PaintSettingsView(Color.BLUE, 60);
        canvas.add(paintSettingsView, 10 - paintSettingsView.getBounds().getMinX(), 10);

        canvas.onMouseDown(event -> paint(event.getPosition()));
        canvas.onDrag(event -> paint(event.getPosition()));
    }

    private void paint(Point location) {
        BrushOptions brushOptions = paintSettingsView.getBrushOptions();

        // TODO: Add fuzzy dot to canvas (see instructions)
    }

    public static void main(String[] args) {
        new PainterApp();
    }
}
