package arithmea.shared.qabalah;

import arithmea.shared.gematria.HebrewLetter;

/**
 * Enum to represent the classical 22 paths on the tree of life.
 */
public enum HebrewPath {
    Aleph(HebrewLetter.Aleph, 11, Sephira.Kether, Sephira.Chokmah),
    Beth(HebrewLetter.Beth, 12, Sephira.Kether, Sephira.Binah),
    Gimel(HebrewLetter.Gimel, 13, Sephira.Kether, Sephira.Tiphareth),
    Daleth(HebrewLetter.Daleth, 14, Sephira.Chokmah, Sephira.Binah),
    Heh(HebrewLetter.Heh, 15, Sephira.Chokmah, Sephira.Tiphareth),
    Vav(HebrewLetter.Vav, 16, Sephira.Chokmah, Sephira.Chesed),
    Zain(HebrewLetter.Zain, 17, Sephira.Binah, Sephira.Tiphareth),
    Cheth(HebrewLetter.Cheth, 18, Sephira.Binah, Sephira.Geburah),
    Teth(HebrewLetter.Teth, 19, Sephira.Chesed, Sephira.Geburah),
    Yud(HebrewLetter.Yud, 20, Sephira.Chesed, Sephira.Tiphareth),
    Kaph(HebrewLetter.Kaph, 21, Sephira.Chesed, Sephira.Netzach),
    Kaph_Final(HebrewLetter.Kaph_Final, 21, Sephira.Chesed, Sephira.Netzach),
    Lamed(HebrewLetter.Lamed, 22, Sephira.Geburah, Sephira.Tiphareth),
    Mem(HebrewLetter.Mem, 23, Sephira.Geburah, Sephira.Hod),
    Mem_Final(HebrewLetter.Mem_Final, 23, Sephira.Geburah, Sephira.Hod),
    Nun(HebrewLetter.Nun, 24, Sephira.Tiphareth, Sephira.Netzach),
    Nun_Final(HebrewLetter.Nun_Final, 24, Sephira.Tiphareth, Sephira.Netzach),
    Samekh(HebrewLetter.Samekh, 25, Sephira.Tiphareth, Sephira.Yesod),
    Ayin(HebrewLetter.Ayin, 26, Sephira.Tiphareth, Sephira.Hod),
    Peh(HebrewLetter.Peh, 27, Sephira.Netzach, Sephira.Hod),
    Peh_Final(HebrewLetter.Peh_Final, 27, Sephira.Netzach, Sephira.Hod),
    Tzaddi(HebrewLetter.Tzaddi, 28, Sephira.Netzach, Sephira.Yesod),
    Tzaddi_Final(HebrewLetter.Tzaddi_Final, 28, Sephira.Netzach, Sephira.Yesod),
    Qoph(HebrewLetter.Qoph, 29, Sephira.Netzach, Sephira.Malkuth),
    Resh(HebrewLetter.Resh, 30, Sephira.Hod, Sephira.Yesod),
    Shin(HebrewLetter.Shin, 31, Sephira.Hod, Sephira.Malkuth),
    Tav(HebrewLetter.Tav, 32, Sephira.Yesod, Sephira.Malkuth);

    private HebrewLetter letter;
    private int number;
    private Sephira from;
    private Sephira to;

    /**
     * Default constructor.
     * @param letter
     * @param number
     * @param from
     * @param to
     */
    private HebrewPath(final HebrewLetter letter, final int number, final Sephira from, final Sephira to) {
        this.letter = letter;
        this.number = number;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the HebrewLetter associated with the path.
     * @return the hebrew letter
     */
    public final HebrewLetter getLetter() {
        return letter;
    }

    /**
     * Returns the number of the path.
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
