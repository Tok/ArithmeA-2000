package arithmea.client.widgets;

import java.util.HashMap;
import java.util.Map;

import arithmea.shared.gematria.LatinLetter;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Widget that draws the alphabet on a circle and connects the provided letters.
 */
public class LetterStarWidget extends Composite {
    private static final double DEGREES_IN_CIRCLE = 360.0D;
    private static final double INNER_RATIO = 0.40D;
    private static final double OUTER_RATIO = 0.44D;
    private final VerticalPanel panel = new VerticalPanel();
    private Canvas canvas;
    private Map<LatinLetter, Double> xPoints = new HashMap<LatinLetter, Double>(LatinLetter.values().length);
    private Map<LatinLetter, Double> yPoints = new HashMap<LatinLetter, Double>(LatinLetter.values().length);
    private Map<LatinLetter, Double> xLetterPos = new HashMap<LatinLetter, Double>(LatinLetter.values().length);
    private Map<LatinLetter, Double> yLetterPos = new HashMap<LatinLetter, Double>(LatinLetter.values().length);

    /**
     * Default constructor.
     * @param width in pixels
     * @param height in pixels
     */
    public LetterStarWidget(final int width, final int height) {
        canvas = Canvas.createIfSupported();
        canvas.setCoordinateSpaceHeight(height);
        canvas.setCoordinateSpaceWidth(width);
        initPositions();
        drawStar();
        panel.add(canvas);
        initWidget(panel);
        setStyleName("tree-of-life");
    }

    /**
     * Returns the canvas element of this widget.
     * @return canvas
     */
    public final Canvas getCanvas() {
        return canvas;
    }

    /**
     * Initializes the alphabet circle.
     */
    private void initPositions() {
        for (final LatinLetter ll : LatinLetter.values()) {
            final double angle = (ll.getIaValue() - 1) * (DEGREES_IN_CIRCLE / (LatinLetter.values().length));
            final double radians = Math.toRadians(angle);

            final double x = canvas.getCoordinateSpaceWidth() / 2 + ((Math.sin(radians) * canvas.getCoordinateSpaceWidth() * INNER_RATIO));
            final double y = canvas.getCoordinateSpaceHeight() / 2 - ((Math.cos(radians) * canvas.getCoordinateSpaceHeight() * INNER_RATIO));
            xPoints.put(ll, x);
            yPoints.put(ll, y);

            final double xLetter = canvas.getCoordinateSpaceWidth() / 2 + ((Math.sin(radians) * canvas.getCoordinateSpaceWidth() * OUTER_RATIO) - 4);
            final double yLetter = canvas.getCoordinateSpaceHeight() / 2 - ((Math.cos(radians) * canvas.getCoordinateSpaceHeight() * OUTER_RATIO) - 4);
            xLetterPos.put(ll, xLetter);
            yLetterPos.put(ll, yLetter);
        }
    }

    /**
     * Draws the circle.
     */
    private void drawStar() {
        final Context2d ctx = canvas.getContext2d();
        // reset content
        ctx.clearRect(0, 0, canvas.getCoordinateSpaceWidth(), canvas.getCoordinateSpaceHeight());
        ctx.beginPath();
        ctx.setStrokeStyle(CssColor.make("#FFFFFF"));
        ctx.setFillStyle(CssColor.make("#FFFFFF"));
        ctx.setLineWidth(1);
        // draw circle
        ctx.arc(
                canvas.getCoordinateSpaceWidth() / 2,
                canvas.getCoordinateSpaceHeight() / 2,
                canvas.getCoordinateSpaceWidth() * INNER_RATIO,
                0,
                DEGREES_IN_CIRCLE * Math.PI / (DEGREES_IN_CIRCLE / 2));
        // draw letters
        for (final LatinLetter ll : LatinLetter.values()) {
            ctx.fillText(ll.name(), xLetterPos.get(ll), yLetterPos.get(ll));
        }
        ctx.closePath();
        ctx.stroke();
    }

    /**
     * Sets the word in order to connect its letters.
     * @param input
     */
    public final void setWord(final String input) {
        final String word = input.replaceAll("[^A-Z]", "");
        drawStar();
        if (word.length() <= 1) {
            return;
        }
        for (int i = 0; i < word.length() - 1; i++) {
            final String letter = word.substring(i, i + 1);
            final String nextLetter = word.substring(i + 1, i + 2);
            final LatinLetter current = LatinLetter.valueOf(letter);
            final LatinLetter next = LatinLetter.valueOf(nextLetter);
            if (next != null && current != null) {
                drawLine(xPoints.get(current), yPoints.get(current), xPoints.get(next), yPoints.get(next));
            }
        }
    }

    /**
     * Draws a line on the canvas.
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    private void drawLine(final double startX, final double startY, final double endX, final double endY) {
        canvas.getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
        canvas.getContext2d().setLineWidth(3);
        canvas.getContext2d().beginPath();
        canvas.getContext2d().moveTo(startX, startY);
        canvas.getContext2d().lineTo(endX, endY);
        canvas.getContext2d().closePath();
        canvas.getContext2d().stroke();
    }
}
