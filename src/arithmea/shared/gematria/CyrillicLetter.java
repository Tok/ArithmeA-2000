package arithmea.shared.gematria;

/**
 * Enum for current and ancient cyrillic letters.
 */
public enum CyrillicLetter {
    А('\u0410', true, 1, 1, 1), //А
    Б('\u0411', false, 0, 0, 0), //Be
    В('\u0412', true, 2, 2, 2), //Ve
    Г('\u0413', true, 3, 3, 3), //Ghe
    Д('\u0414', true, 4, 4, 4), //De
    Е('\u0415', true, 5, 5, 5), //Ie
    Ж('\u0416', false, 0, 0, 0), //Zhe
    Ѕ('\u0405', true, 6, 6, 6), //Dze
    З('\u0417', true, 7, 7, 7), //Ze
    И('\u0418', true, 8, 8, 8), //I
    Й('\u0419', false, 0, 0, 0), //I (short)
    Ѳ('\u0472', true, 9, 9, 9), //Fita
    І('\u0406', true, 10, 10, 1), //I
    К('\u041A', true, 11, 20, 2), //Ka
    Л('\u041B', true, 12, 30, 3), //El
    М('\u041C', true, 13, 40, 4), //Em
    Н('\u041D', true, 14, 50, 5), //En
    Ѯ('\u046E', true, 15, 60, 6), //Ksi
    О('\u041E', true, 16, 70, 7), //O
    П('\u041F', true, 17, 80, 8), //Pe
    Ч('\u0427', true, 18, 90, 9), //Che
    Р('\u0420', true, 19, 100, 1), //Er
    С('\u0421', true, 20, 200, 2), //Es
    Т('\u0422', true, 21, 300, 3), //Te
    Ѵ('\u0474', true, 22, 400, 4), //Izhitsa
    У('\u0423', false, 0, 0, 0), //U
    Ф('\u0424', true, 23, 500, 5), //Ef
    Х('\u0425', true, 24, 600, 6), //Ha
    Ѱ('\u0470', true, 25, 700, 7), //Psi
    Ѡ('\u047E', true, 26, 800, 8), //Ot
    Ц('\u0426', true, 27, 900, 9), //Tse
    Ш('\u0428', false, 0, 0, 0), //Sha
    Щ('\u0429', false, 0, 0, 0), //Shcha
    Ы('\u042B', false, 0, 0, 0), //Yeru
    Э('\u042D', false, 0, 0, 0), //E
    Ю('\u042E', false, 0, 0, 0), //Yu
    Я('\u042F', false, 0, 0, 0); //Ya

    private char cyrillic;
    private boolean isAncient;
    private int ordinalValue;
    private int fullValue;
    private int katanValue;

    private CyrillicLetter(final char cyrillic, final boolean isAncient,
            final int ordinalValue, final int fullValue, final int katanValue) {
        this.cyrillic = cyrillic;
        this.isAncient = isAncient;
        this.ordinalValue = ordinalValue;
        this.fullValue = fullValue;
        this.katanValue = katanValue;
    }

    public char getCyrillic() {
        return cyrillic;
    }

    /**
     * Returns true if the letter is obsolete.
     * @return isAncient
     */
    public boolean isAncient() {
        return isAncient;
    }

    public int getOrdinalValue() {
        return ordinalValue;
    }

    public int getFullValue() {
        return fullValue;
    }

    public int getKatanValue() {
        return katanValue;
    }

}
