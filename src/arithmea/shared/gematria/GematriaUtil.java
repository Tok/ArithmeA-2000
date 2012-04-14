package arithmea.shared.gematria;

import java.util.HashMap;

/**
 * Utility class for gematria.
 */
public class GematriaUtil {
    private boolean skipNext = false;
    private boolean skipAfterNext = false;

    /**
     * Calculates the numbers for the provided String using all known gematria methods.
     * @param input
     * @return HashMap with the result
     */
    public final HashMap<GematriaMethod, Integer> getAllValues(final String input) {
        HashMap<GematriaMethod, Integer> result = new HashMap<GematriaMethod, Integer>(LatinMethod.values().length + HebrewMethod.values().length);

        final char[] latinChars = input.toUpperCase().toCharArray();
        int chaldeanResult = 0;
        int pythagoreanResult = 0;
        int iaResult = 0;
        int naeqResult = 0;
        int tqResult = 0;
        int germanResult = 0;
        int eqResult = 0;
        for (int i = 0; i < latinChars.length; i++) {
            try {
                LatinLetter letter = LatinLetter.valueOf(String.valueOf(latinChars[i]));
                chaldeanResult += letter.getChaldeanValue();
                pythagoreanResult += letter.getPythagoreanValue();
                iaResult += letter.getIaValue();
                naeqResult += letter.getNaeqValue();
                tqResult += letter.getTqValue();
                germanResult += letter.getGermanValue();
                eqResult += letter.getEqValue();
            } catch (final IllegalArgumentException iae) {
                // ignore everything that isn't a latin letter
                assert true;
            }
        }
        result.put(LatinMethod.Chaldean, chaldeanResult);
        result.put(LatinMethod.Pythagorean, pythagoreanResult);
        result.put(LatinMethod.IA, iaResult);
        result.put(LatinMethod.NAEQ, naeqResult);
        result.put(LatinMethod.TQ, tqResult);
        result.put(LatinMethod.German, germanResult);
        result.put(LatinMethod.EQ, eqResult);

        String hebrew = getHebrew(input);
        final char[] hebrewChars = hebrew.toCharArray();
        int fullResult = 0;
        int ordinalResult = 0;
        int katanResult = 0;
        for (int i = 0; i < hebrewChars.length; i++) {
            for (HebrewLetter letter : HebrewLetter.values()) {
                if (letter.getHebrew() == hebrewChars[i]) {
                    fullResult += letter.getFullValue();
                    ordinalResult += letter.getOrdinalValue();
                    katanResult += letter.getKatanValue();
                    break;
                }
            }
        }
        result.put(HebrewMethod.Full, fullResult);
        result.put(HebrewMethod.Ordinal, ordinalResult);
        result.put(HebrewMethod.Katan, katanResult);
        return result;
    }

    /**
     * Transliterates the provided String to hebrew.
     * @param input
     * @return the transliterated hebrew String.
     */
    public final String getHebrew(final String input) {
        final char[] chars = input.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (skipNext) {
                if (skipAfterNext) {
                    skipAfterNext = false;
                } else {
                    skipNext = false;
                }
            } else {
                final char current = chars[i];
                char next = '\u0000';
                if (i < chars.length - 1) {
                    next = chars[i + 1];
                }
                char afterNext = '\u0000';
                if (i < chars.length - 2) {
                    afterNext = chars[i + 2];
                }
                char resultCharacter = getHebrewCharacter(i == 0, current, next, afterNext);
                if (resultCharacter != '\u0000') {
                    result.append(resultCharacter);
                }
            }
        }
        return result.toString();
    }

    /**
     * Returns the hebrew letter for the next one to three latin letters.
     * @param isFirst mist be true if the current letter is at the beginning of the word
     * @param the current letter
     * @param the next letter if there is one
     * @param the letter after the next letter if there is one
     * @return the corresponding hebrew letter
     */
    private char getHebrewCharacter(final boolean isFirst, final char current, final char next, final char afterNext) {
        if (current == 'A') {
            return HebrewLetter.Aleph.getHebrew();
        } else if (current == 'B') {
            return HebrewLetter.Beth.getHebrew();
        } else if (current == 'C') {
            if (next == 'H') {
                skipNext = true;
                return HebrewLetter.Cheth.getHebrew();
            }
            if (next == 'C' || next == 'K') { // CC and CK
                skipNext = true;
            }
            if (afterNext == ' ' || next == '-' || afterNext == '\u0000') {
                return HebrewLetter.Kaph_Final.getHebrew();
            } else {
                return HebrewLetter.Kaph.getHebrew();
            }
        } else if (current == 'D') {
            return HebrewLetter.Daleth.getHebrew();
        } else if (current == 'E') {
            if (next == 'E') { // EE
                skipNext = true;
                return HebrewLetter.Heh.getHebrew();
            }
            return '\u0000';
        } else if (current == 'F') {
            return HebrewLetter.Peh.getHebrew();
        } else if (current == 'G') {
            return HebrewLetter.Gimel.getHebrew();
        } else if (current == 'H') {
            return HebrewLetter.Heh.getHebrew();
        } else if (current == 'I') {
            return HebrewLetter.Yud.getHebrew();
        } else if (current == 'J') {
            return HebrewLetter.Gimel.getHebrew();
        } else if (current == 'K') {
            if (next == 'H') { // KH
                // don't skip next heh
                if (afterNext == ' ' || next == '-' || afterNext == '\u0000') {
                    return HebrewLetter.Kaph_Final.getHebrew();
                } else {
                    return HebrewLetter.Kaph.getHebrew();
                }
            } else if (next == ' ' || next == '-' || (next == '\u0000' && afterNext == '\u0000')) {
                return HebrewLetter.Kaph_Final.getHebrew();
            } else {
                return HebrewLetter.Kaph.getHebrew();
            }
        } else if (current == 'L') {
            return HebrewLetter.Lamed.getHebrew();
        } else if (current == 'M') {
            if (next == ' ' || next == '-' || (next == '\u0000' && afterNext == '\u0000')) {
                return HebrewLetter.Mem_Final.getHebrew();
            } else {
                return HebrewLetter.Mem.getHebrew();
            }
        } else if (current == 'N') {
            if (next == ' ' || next == '-' || (next == '\u0000' && afterNext == '\u0000')) {
                return HebrewLetter.Nun_Final.getHebrew();
            } else {
                return HebrewLetter.Nun.getHebrew();
            }
        } else if (current == 'O') {
            if (next == 'O' || next == 'U') { // double O and ou
                skipNext = true;
                return HebrewLetter.Ayin.getHebrew();
            } else if (isFirst) { // O at start of word
                return HebrewLetter.Ayin.getHebrew();
            } else { // other O's
                return HebrewLetter.Vav.getHebrew();
            }

        } else if (current == 'P') {
            if (next == 'H') {
                skipNext = true;
                if (afterNext == ' ' || next == '-' || afterNext == '\u0000') {
                    return HebrewLetter.Peh_Final.getHebrew();
                } else {
                    return HebrewLetter.Peh.getHebrew();
                }
            } else if (next == ' ' || next == '-' || (next == '\u0000' && afterNext == '\u0000')) {
                return HebrewLetter.Peh_Final.getHebrew();
            } else {
                return HebrewLetter.Peh.getHebrew();
            }
        } else if (current == 'Q') {
            if (next == 'U') {
                skipNext = true;
            }
            return HebrewLetter.Qoph.getHebrew();
        } else if (current == 'R') {
            return HebrewLetter.Resh.getHebrew();
        } else if (current == 'S') {
            if (next == 'C' && afterNext == 'H') { // Sch
                skipNext = true;
                skipAfterNext = true;
                return HebrewLetter.Shin.getHebrew();
            } else if (next == 'H') { // Sh
                skipNext = true;
                return HebrewLetter.Shin.getHebrew();
            } else if (next == 'S') { // SS
                skipNext = true;
                return HebrewLetter.Zain.getHebrew();
            } else {
                return HebrewLetter.Samekh.getHebrew();
            }
        } else if (current == 'T') {
            if (next == 'Z' || next == 'X') { // Tz, Tx
                skipNext = true;
                if (afterNext == ' ' || next == '-' || afterNext == '\u0000') {
                    return HebrewLetter.Tzaddi_Final.getHebrew();
                } else {
                    return HebrewLetter.Tzaddi.getHebrew();
                }
            } else if (next == 'H') { // Th
                skipNext = true;
                return HebrewLetter.Tav.getHebrew();
            } else if (next == 'S') { // Ts
                skipNext = true;
                return HebrewLetter.Zain.getHebrew();
            } else {
                return HebrewLetter.Teth.getHebrew();
            }
        } else if (current == 'U') {
            return HebrewLetter.Vav.getHebrew();
        } else if (current == 'V') {
            return HebrewLetter.Vav.getHebrew();
        } else if (current == 'W') {
            return HebrewLetter.Vav.getHebrew();
        } else if (current == 'X') {
            if (next == ' ' || next == '-' || (next == '\u0000' && afterNext == '\u0000')) {
                return HebrewLetter.Tzaddi_Final.getHebrew();
            } else {
                return HebrewLetter.Tzaddi.getHebrew();
            }
        } else if (current == 'Y') {
            return HebrewLetter.Yud.getHebrew();
        } else if (current == 'Z') {
            return HebrewLetter.Zain.getHebrew();
        }

        return current;
    }
}
