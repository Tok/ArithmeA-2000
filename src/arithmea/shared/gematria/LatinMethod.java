package arithmea.shared.gematria;

/**
 * Enum for gematria methods that work on a word in latin letters.
 */
public enum LatinMethod implements GematriaMethod {
    Chaldean(false),
    Pythagorean(false),
    IA(true),
    NAEQ(true),
    TQ(true),
    German(false),
    EQ(true);
    
    private final boolean showMatchesbyDefault;
    
    private LatinMethod(final boolean showMatchesbyDefault) {
        this.showMatchesbyDefault = showMatchesbyDefault;
    }

    public boolean showMatchesbyDefault() {
        return showMatchesbyDefault;
    }
}
