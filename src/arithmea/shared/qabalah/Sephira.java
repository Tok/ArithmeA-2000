package arithmea.shared.qabalah;

/**
 * Enum to represent the ten sephiroth and da'ath.
 */
public enum Sephira {
    Kether(false, 1),
    Chokmah(false, 2),
    Binah(false, 3),
    Daath(true, 11),
    Chesed(false, 4),
    Geburah(false, 5),
    Tiphareth(false, 6),
    Netzach(false, 7),
    Hod(false, 8),
    Yesod(false, 9),
    Malkuth(false, 10);

    private boolean isImplicate;
    private int number;

    /**
     * Default constructor.
     * @param isImplicate (for daath)
     * @param number of the sephira
     */
    private Sephira(final boolean isImplicate, final int number) {
        this.isImplicate = isImplicate;
        this.number = number;
    }

    /**
     * Returns true if sephira is implicate.
     * Applies to daath only, wich technically isn't a sephira at all.
     * @return isImplicate
     */
    public final boolean isImplicate() {
        return isImplicate;
    }

    /**
     * Returns the number of the sephira.
     * @return numer
     */
    public final int getNumber() {
        return number;
    }

}
