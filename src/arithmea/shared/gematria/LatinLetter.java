package arithmea.shared.gematria;

/**
 * Enum for latin letters with their values in different methods.
 * Thanks to Alien696 for the EQ cipher and the corresponding signs.
 */
public enum LatinLetter {
    //character,, chaldean, pythagorean, ia, naeq, tq, germanValue, eq,, eq sign
    A('A', new int[] {1, 1,  1,  1,  5, 1,   1}, "Gemini"),
    B('B', new int[] {2, 2,  2, 20, 20, 0, 200}, "Earth"),
    C('C', new int[] {3, 3,  3, 13,  2, 0,  40}, "Ophiuchus"), //formerly Neptune
    D('D', new int[] {4, 4,  4,  6, 23, 0,   6}, "Taurus"),
    E('E', new int[] {5, 5,  5, 25, 13, 2, 700}, "Earth"),
    F('F', new int[] {8, 6,  6, 18, 12, 0,  90}, "Sol"),
    G('G', new int[] {3, 7,  7, 11, 11, 7,  20}, "Virgo"),
    H('H', new int[] {5, 8,  8,  4,  3, 8,   4}, "Aquarius"),
    I('I', new int[] {1, 9,  9, 23, 26, 3, 400}, "Fire"), //formerly Air
    J('J', new int[] {1, 1, 10, 16,  7, 0,  70}, "Saturn"),
    K('K', new int[] {2, 2, 11,  9, 17, 0,   9}, "Libra"),
    L('L', new int[] {3, 3, 12,  2,  1, 9,   2}, "Capricorn"),
    M('M', new int[] {4, 4, 13, 21, 21, 0, 300}, "Jupiter"),
    N('N', new int[] {5, 5, 14, 14, 24, 0,  50}, "Mercury"),
    O('O', new int[] {7, 6, 15, 19, 10, 4,   7}, "Aries"),
    P('P', new int[] {8, 7, 16, 26,  4, 0, 800}, "Spirit"),
    Q('Q', new int[] {1, 8, 17, 17, 16, 0, 100}, "Luna"),
    R('R', new int[] {2, 9, 18, 12, 14, 0,  20}, "Pisces"),
    S('S', new int[] {3, 1, 19,  5, 15, 6,   5}, "Scorpio"),
    T('T', new int[] {4, 2, 20, 24,  9, 0, 600}, "Water"),
    U('U', new int[] {6, 3, 21, 17, 25, 5,  80}, "Mars"),
    V('V', new int[] {6, 4, 22, 10, 22, 0,  10}, "Cancer"),
    W('W', new int[] {6, 5, 23,  3,  8, 0,   3}, "Sagittarius"),
    X('X', new int[] {5, 6, 24, 22,  6, 0, 500}, "Air"), //formerly Fire
    Y('Y', new int[] {1, 7, 25, 15, 18, 7,  60}, "Venus"),
    Z('Z', new int[] {7, 8, 26,  8, 19, 0,   8}, "Leo");

    private char character;
    private int chaldeanValue;
    private int pythagoreanValue;
    private int iaValue;
    private int naeqValue;
    private int tqValue;
    private int germanValue;
    private int eqValue;
    private String eqSign;

    /**
     * Default constructor.
     * @param character
     * @param chaldeanValue
     * @param pythagoreanValue
     * @param iaValue
     * @param naeqValue
     * @param tqValue
     * @param germanValue
     * @param eqValue
     * @param eqSign
     */
    private LatinLetter(final char character, final int[] values, final String eqSign) {
        this.character = character;
        this.chaldeanValue = values[0];
        this.pythagoreanValue = values[1];
        this.iaValue = values[2];
        this.naeqValue = values[3];
        this.tqValue = values[4];
        this.germanValue = values[5];
        this.eqValue = values[6];
        this.eqSign = eqSign;
    }

    /**
     * Returns the letter as char.
     * @return character
     */
    public final char getCharacter() {
        return character;
    }

    /**
     * Returns the value of the letter in chaldean numerology.
     * http://en.wikipedia.org/wiki/Arithmancy#The_Chaldean_Method
     * @return chaldeanValue
     */
    public final int getChaldeanValue() {
        return chaldeanValue;
    }

    /**
     * Returns the value of the letter in pythagorean numerology.
     * http://en.wikipedia.org/wiki/Arithmancy#The_Agrippan_Method
     * @return pythagoreanValue
     */
    public final int getPythagoreanValue() {
        return pythagoreanValue;
    }

    /**
     * Returns the value of the letter in Simple English Gematria.
     * 1=A, 2=B, 3=C .. 26=Z.
     * http://wmjas.wikidot.com/simple-english-gematria
     * @return iaValue
     */
    public final int getIaValue() {
        return iaValue;
    }

    /**
     * Returns the value of the letter in New Aeon English Qabalah
     * http://en.wikipedia.org/wiki/English_Qabalah#ALW_Cipher
     * @return naeqValue
     */
    public final int getNaeqValue() {
        return naeqValue;
    }

    /**
     * Returns the value of the letter in Trigrammaton Qabalah
     * http://en.wikipedia.org/wiki/English_Qabalah#Trigrammaton_Qabalah_.28TQ.29
     * @return tqValue
     */
    public final int getTqValue() {
        return tqValue;
    }

    /**
     * Returns the value of the letter for a cipher specific to
     * the german language, that was discovered by Rolf Keppler.
     * http://www.rolf-keppler.de/schluessel.htm
     * Most letters have a value of 0.
     * @return germanValue
     */
    public final int getGermanValue() {
        return germanValue;
    }

    /**
     * Returns the value of the letter in an English Qabalah method that uses the same sequence as NAEQ,
     * but without reducing the Values (thanks to Alien696).
     * @return eqValue
     */
    public final int getEqValue() {
        return eqValue;
    }

    /**
     * Returns the String of the sign for the letter associated with the EQ method.
     * @return eqSign
     */
    public final String getEqSign() {
        return eqSign;
    }

}
