package arithmea.client.widgets.tree;

import arithmea.shared.gematria.HebrewLetter;
import arithmea.shared.qabalah.HebrewPath;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.canvas.dom.client.CssColor;

/**
 * Widget to draw the tree of live with the classical 22 paths.
 */
public class HebrewTreeWidget extends AbstractTreeWidget {

    /**
     * Default constructor.
     * @param width in pixels
     * @param height in pixels
     */
    public HebrewTreeWidget(final int width, final int height) {
        super(width, height);
        drawAllPaths();
    }

    /**
     * Sets a word and highlights the corresponding paths.
     * @param hebrew String
     */
    public final void setWord(final String hebrew) {
        getCanvas().getContext2d().clearRect(0, 0, getCanvas().getCoordinateSpaceWidth(), getCanvas().getCoordinateSpaceHeight());
        getCanvas().getContext2d().setLineWidth(SephirothData.UNIT / 5);
        getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
        for (final HebrewLetter letter : HebrewLetter.values()) {
            final CharSequence c = String.valueOf(letter.getHebrew());
            if (hebrew.contains(c)) {
                drawPath(HebrewPath.valueOf(letter.name()));
            }
        }
        drawAllPaths();
        drawSephiroth();
    }

    /**
     * Draws all 22 paths on the tree.
     */
    private void drawAllPaths() {
        getCanvas().getContext2d().setLineWidth(1);
        getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
        for (final HebrewPath path : HebrewPath.values()) {
            drawPath(path);
        }
    }

    /**
     * Draws the provided path.
     * @param path
     */
    public final void drawPath(final HebrewPath path) {
        drawPath(path.getFrom(), path.getTo());
    }
}
