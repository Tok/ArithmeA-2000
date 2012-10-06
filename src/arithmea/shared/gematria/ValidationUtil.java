package arithmea.shared.gematria;

/**
 * Utility Class to validate input.
 */
public class ValidationUtil {

    /**
     * Empty default constructor.
     */
    public ValidationUtil() {
    }

    /**
     * Translates the provided input to an upper-case String with letters and the minus sign only.
     * Dashes are preserved.
     * @param input
     * @return result
     */
    public final String getLetterString(final String input) {
        String temp = input.toUpperCase().trim();
        String result = temp.replaceAll("[ ]", "-").replaceAll("[^A-Z-]", "");
        return result;
    }

}
