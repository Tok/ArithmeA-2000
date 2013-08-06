package arithmea.client.widgets.tree;

import arithmea.shared.gematria.LatinLetter;
import arithmea.shared.qabalah.LatinPath;
import arithmea.shared.qabalah.SephirothData;
import com.google.gwt.canvas.dom.client.CssColor;

/**
 * Widget to draw the tree of live with all 26 paths corresponding to the latin alphabet.
 */
public class LatinTreeWidget extends AbstractTreeWidget {

    /**
     * Default constructor.
     * @param width in pixels.
     * @param height in pixels.
     */
    public LatinTreeWidget(final int width, final int height) {
        super(width, height);
        drawAllPaths();
    }

    /**
     * Sets a word and highlights the corresponding paths.
     * @param latin String
     */
    public final void setWord(final String latin) {
        getCanvas().getContext2d().clearRect(0, 0, getCanvas().getCoordinateSpaceWidth(), getCanvas().getCoordinateSpaceHeight());
        getCanvas().getContext2d().setLineWidth(SephirothData.UNIT / 5);
        getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
        for (final LatinLetter letter : LatinLetter.values()) {
            final CharSequence c = String.valueOf(letter.name());
            if (latin.contains(c)) {
                drawPath(LatinPath.valueOf(letter.name()));
            }
        }
        drawAllPaths();
        drawSephiroth();
    }

    /**
     * Draws all 26 paths on the tree.
     */
    private void drawAllPaths() {
        getCanvas().getContext2d().setLineWidth(1);
        getCanvas().getContext2d().setStrokeStyle(CssColor.make("#FFFFFF"));
        for (final LatinPath path : LatinPath.values()) {
            drawPath(path);
        }
    }

    /**
     * Draws the provided path.
     * @param path
     */
    public final void drawPath(final LatinPath path) {
        drawPath(path.getFrom(), path.getTo());
    }
}
