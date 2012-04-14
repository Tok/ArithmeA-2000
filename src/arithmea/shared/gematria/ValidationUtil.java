package arithmea.shared.gematria;

public class ValidationUtil {

    public ValidationUtil() {
    }

    public final String getLetterString(final String input) {
        String temp = input.toUpperCase().trim();
        String result = temp.replaceAll("[^A-Z-]", "");
        return result;
    }

}
