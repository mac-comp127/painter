package painter;

import comp127graphics.Ellipse;
import comp127graphics.GraphicsGroup;
import comp127graphics.GraphicsObject;

import java.awt.Color;
import java.awt.RadialGradientPaint;

public class PaintUtils {
    /**
     * Creates a circle that smoothly transitions to transparent at the edge. The fill is a radial
     * gradient whose transparency approximately follows a raised cosine window.
     *
     * @param color  Color of the dot
     * @param radius Outer radius of the circle, at the point where it becomes completely transparent
     * @param alpha  Transparency of the dot at its most opaque in the center (1 = fully opaque)
     * @return A graphics object you can reposition and add to a canvas
     */
    public static GraphicsObject createFuzzyDot(Color color, float radius, double alpha) {
        Ellipse dot = new Ellipse(0, 0, radius * 2, radius * 2);
        RadialGradientPaint gradient = new RadialGradientPaint(
            radius, radius, radius,
            new float[] { 0, 0.10f, 0.26f, 0.5f, 0.75f, 0.87f, 1 },
            new Color[] {
                adjustTransparency(color, alpha * 1.00),  // semi-transparent in the middle
                adjustTransparency(color, alpha * 0.96),
                adjustTransparency(color, alpha * 0.84),
                adjustTransparency(color, alpha * 0.50),
                adjustTransparency(color, alpha * 0.16),
                adjustTransparency(color, alpha * 0.04),
                adjustTransparency(color, alpha * 0.00)   // fully transparent at the edge
            } );
        dot.setFillColor(gradient);
        dot.setStroked(false);

        // Gradients are relative to the coordinates of their container. Putting the dot in a group
        // makes the group the container, so that the gradient center moves with the dot wherever
        // it goes on the screen.
        GraphicsGroup dotContainer = new GraphicsGroup();
        dotContainer.add(dot);
        return dotContainer;
    }

    /**
     * Creates a new color with the same RGB value as the given one, but with a different transparency.
     */
    public static Color adjustTransparency(Color color, double alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) Math.round(alpha * 255));
    }
}
