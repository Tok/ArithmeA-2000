package arithmea.shared.qabalah;

/**
 * Enum to represent the 26 paths on the tree of life
 * associated with the 26 letters in the latin alphabet. 
 */
public enum LatinPath {
    A(1, Sephira.Kether, Sephira.Chokmah),
    B(2, Sephira.Kether, Sephira.Chesed),
    C(3, Sephira.Kether, Sephira.Binah),
    D(4, Sephira.Kether, Sephira.Geburah),
    E(5, Sephira.Kether, Sephira.Tiphareth),
    F(6, Sephira.Chokmah, Sephira.Binah),
    G(7, Sephira.Chokmah, Sephira.Geburah),
    H(8, Sephira.Chokmah, Sephira.Tiphareth),
    I(9, Sephira.Chokmah, Sephira.Chesed),
    J(10, Sephira.Binah, Sephira.Chesed),
    K(11, Sephira.Binah, Sephira.Tiphareth),
    L(12, Sephira.Binah, Sephira.Geburah),
    M(13, Sephira.Chesed, Sephira.Geburah),
    N(14, Sephira.Chesed, Sephira.Tiphareth),
    O(15, Sephira.Chesed, Sephira.Netzach),
    P(16, Sephira.Geburah, Sephira.Tiphareth),
    Q(17, Sephira.Geburah, Sephira.Hod),
    R(18, Sephira.Tiphareth, Sephira.Netzach),
    S(19, Sephira.Tiphareth, Sephira.Yesod),
    T(20, Sephira.Tiphareth, Sephira.Hod),
    U(21, Sephira.Netzach, Sephira.Hod),
    V(22, Sephira.Netzach, Sephira.Yesod),
    W(23, Sephira.Netzach, Sephira.Malkuth),
    X(24, Sephira.Hod, Sephira.Yesod),
    Y(25, Sephira.Hod, Sephira.Malkuth),
    Z(26, Sephira.Yesod, Sephira.Malkuth);

    private int number;
    private Sephira from;
    private Sephira to;

    /**
     * Default constructor.
     * @param number
     * @param from
     * @param to
     */
    private LatinPath(final int number, final Sephira from, final Sephira to) {
        this.number = number;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the number of the path.
     * The path numbers were determined by the same logic of order
     * as the 22 paths on the classical tree of life.
     * @return number
     */
    public final int getNumber() {
        return number;
    }

    /**
     * Returns the sephira at the top of the path.
     * @return from sephira
     */
    public final Sephira getFrom() {
        return from;
    }
    
    /**
     * Returns the sephira at the bottom of the path.
     * @return to sephira
     */
    public final Sephira getTo() {
        return to;
    }

}
