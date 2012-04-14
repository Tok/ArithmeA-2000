package arithmea.shared.gematria;

public enum HebrewLetter {
    Aleph('\u05D0', false, 1, 1, 1),
    Beth('\u05D1', false, 2, 2, 2),
    Gimel('\u05D2', false, 3, 3, 3),
    Daleth('\u05D3', false, 4, 4, 4),
    Heh('\u05D4', false, 5, 5, 5),
    Vav('\u05D5', false, 6, 6, 6),
    Zain('\u05D6', false, 7, 7, 7),
    Cheth('\u05D7', false, 8, 8, 8),
    Teth('\u05D8', false, 9, 9, 9),
    Yud('\u05D9', false, 10, 10, 1),
    Kaph('\u05DB', false, 11, 20, 2),
    Lamed('\u05DC', false, 12, 30, 3),
    Mem('\u05DE', false, 13, 40, 4),
    Nun('\u05E0', false, 14, 50, 5),
    Samekh('\u05E1', false, 15, 60, 6),
    Ayin('\u05E2', false, 16, 70, 7),
    Peh('\u05E4', false, 17, 80, 8),
    Tzaddi('\u05E6', false, 18, 90, 9),
    Qoph('\u05E7', false, 19, 100, 1),
    Resh('\u05E8', false, 20, 200, 2),
    Shin('\u05E9', false, 21, 300, 3),
    Tav('\u05EA', false, 22, 400, 4),
    Kaph_Final('\u05DA', true, 23, 500, 5),
    Mem_Final('\u05DD', true, 24, 600, 6),
    Nun_Final('\u05DF', true, 25, 700, 7),
    Peh_Final('\u05E3', true, 26, 800, 8),
    Tzaddi_Final('\u05E5', true, 27, 900, 9);

    private char hebrew;
    private boolean isFinal;
    private int ordinalValue;
    private int fullValue;
    private int katanValue;

    private HebrewLetter(final char hebrew, final boolean isFinal,
            final int ordinalValue, final int fullValue, final int katanValue) {
        this.hebrew = hebrew;
        this.isFinal = isFinal;
        this.ordinalValue = ordinalValue;
        this.fullValue = fullValue;
        this.katanValue = katanValue;
    }

    public final char getHebrew() {
        return hebrew;
    }

    public final boolean isFinal() {
        return isFinal;
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
