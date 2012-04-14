package arithmea.client.sort;

import java.util.Comparator;
import arithmea.shared.data.Term;

/**
 * Sorter to sort terms by their hebrew String.
 */
public class TermSortByHebrewString implements Comparator<Term> {

    /**
     * Compares two terms by their hebrew String.
     * @return positive or negative number or 0.
     */
    public final int compare(final Term first, final Term second) {
        return first.getHebrewString().compareTo(second.getHebrewString());
    }
}
