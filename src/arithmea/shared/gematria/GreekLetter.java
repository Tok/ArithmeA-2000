package arithmea.shared.gematria;

/**
 * Enum for current and ancient greek letters.
 */
public enum GreekLetter {
    Alpha('\u0391', false, 1, 1, 1),
    Beta('\u0392', false, 2, 2, 2),
    Gamma('\u0393', false, 3, 3, 3),
    Delta('\u0394', false, 4, 4, 4),
    Epsilon('\u0395', false, 5, 5, 5),
    Digamma('\u03DC', true, 6, 6, 6),
    Zeta('\u0396', false, 7, 7, 7),
    Eta('\u0397', false, 8, 8, 8),
    Theta('\u0398', false, 9, 9, 9),
    Itoa('\u0399', false, 10, 10, 1),
    Kappa('\u039A', false, 11, 20, 2),
    Lambda('\u039B', false, 12, 30, 3),
    Mu('\u039C', false, 13, 40, 4),
    Nu('\u039D', false, 14, 50, 5),
    Xi('\u039E', false, 15, 60, 6),
    Omikron('\u039F', false, 16, 70, 7),
    Pi('\u03A0', false, 17, 80, 8),
    Qoppa('\u03D8', true, 18, 90, 9),
    Rho('\u03A1', false, 19, 100, 1),
    Sigma('\u03A3', false, 20, 200, 2),
    Tau('\u03A4', false, 21, 300, 3),
    Upsilon('\u03A5', false, 22, 400, 4),
    Phi('\u03A6', false, 23, 500, 5),
    Chi('\u03A7', false, 24, 600, 6),
    Psi('\u03A8', false, 25, 700, 7),
    Omega('\u03A9', false, 26, 800, 8),
    Sampi('\u03E0', true, 27, 900, 9);

    private char greek;
    private boolean isObsolete;
    private int ordinalValue;
    private int fullValue;
    private int katanValue;

    private GreekLetter(final char greek, final boolean isObsolete,
            final int ordinalValue, final int fullValue, final int katanValue) {
        this.greek = greek;
        this.isObsolete = isObsolete;
        this.ordinalValue = ordinalValue;
        this.fullValue = fullValue;
        this.katanValue = katanValue;
    }

    public final char getGreek() {
        return greek;
    }

    /**
     * Returns true if the letter is obsolete.
     * @return isObsolete
     */
    public final boolean isObsolete() {
        return isObsolete;
    }

    public final int getOrdinalValue() {
        return ordinalValue;
    }

    public final int getFullValue() {
        return fullValue;
    }

    public final int getKatanValue() {
        return katanValue;
    }

}
