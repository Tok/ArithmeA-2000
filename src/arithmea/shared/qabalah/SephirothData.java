package arithmea.shared.qabalah;

import com.google.gwt.canvas.dom.client.CssColor;

/**
 * Class representing the positions of the sephiroth.
 */
public class SephirothData {
    public static final int UNIT = 30;
    private static final int INITIAL = 86;
    private static final int PERCENT = 100;
    private static final int FACTOR = ((UNIT * 2) * INITIAL) / PERCENT;
    public static final int WIDTH = 24 * UNIT;
    public static final int HEIGHT = 5 * UNIT;

    /**
     * Enum calculating the relative sephiroth positions.
     */
    public enum SephiraPosition {
        Kether(Sephira.Kether, CssColor.make("#FFFFFF"), UNIT * 3, UNIT),
        Chokmah(Sephira.Chokmah, CssColor.make("#AAAAAA"), (UNIT * 3) + (HEIGHT * FACTOR) / HEIGHT, UNIT * 2),
        Binah(Sephira.Binah, CssColor.make("#000000"), (UNIT * 3) - (HEIGHT * FACTOR) / HEIGHT, UNIT * 2),
        Daath(Sephira.Daath, CssColor.make("#777777"), UNIT * 3, UNIT * 3),
        Chesed(Sephira.Chesed, CssColor.make("#3F48CC"), (UNIT * 3) + (HEIGHT * FACTOR) / HEIGHT, UNIT * 4),
        Geburah(Sephira.Geburah, CssColor.make("#ED1C24"), (UNIT * 3) - (HEIGHT * FACTOR) / HEIGHT, UNIT * 4),
        Tiphareth(Sephira.Tiphareth, CssColor.make("#FFF200"), UNIT * 3, UNIT * 5),
        Netzach(Sephira.Netzach, CssColor.make("#22B14C"), (UNIT * 3) + (HEIGHT * FACTOR) / HEIGHT, UNIT * 6),
        Hod(Sephira.Hod, CssColor.make("#FF7F27"), (UNIT * 3) - (HEIGHT * FACTOR) / HEIGHT, UNIT * 6),
        Yesod(Sephira.Yesod, CssColor.make("#A349A4"), UNIT * 3, UNIT * 7),
        Malkuth(Sephira.Malkuth, CssColor.make("#642100"), UNIT * 3, UNIT * 9);

        private Sephira sephira;
        private CssColor color;
        private int x;
        private int y;

        /**
         * Default constructor.
         * @param sephira
         * @param color
         * @param x position
         * @param y position
         */
        private SephiraPosition(final Sephira sephira, final CssColor color, final int x, final int y) {
            this.sephira = sephira;
            this.color = color;
            this.x = x;
            this.y = y;
        }

        public final Sephira getSephira() {
            return sephira;
        }

        public final CssColor getColor() {
            return color;
        }

        public final int getX() {
            return x;
        }

        public final int getY() {
            return y;
        }
    }

    /**
     * Returns the relative position that was calculated for the provided sephira.
     * @param sephira
     * @return SephiraPosition
     */
    public final SephiraPosition getPosition(final Sephira sephira) {
        for (final SephiraPosition sephPos: SephiraPosition.values()) {
            if (sephPos.sephira.equals(sephira)) {
                return sephPos;
            }
        }
        return SephiraPosition.Daath;
    }

}
