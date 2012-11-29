package arithmea.shared.gematria;

/**
 * Enum for hebrew gematria methods.
 */
public enum HebrewMethod implements GematriaMethod {
    Full(true),
    Ordinal(true),
    Katan(true);
    
    private final boolean showMatchesbyDefault;
    
    private HebrewMethod(final boolean showMatchesbyDefault) {
        this.showMatchesbyDefault = showMatchesbyDefault;
    }

    public boolean showMatchesbyDefault() {
        return showMatchesbyDefault;
    }
}
