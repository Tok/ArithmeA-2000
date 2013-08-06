package arithmea.client.sort;

import java.io.Serializable;
import java.util.Comparator;
import arithmea.shared.data.Term;

/**
 * Sorter to sort terms by their latin String.
 */
public class TermSortByLatinString implements Comparator<Term>, Serializable {
    private static final long serialVersionUID = -6644647683647618334L;

    /**
     * Compares two terms by their latin String.
     * @return positive or negative number or 0.
     */
    public final int compare(final Term first, final Term second) {
        return first.getLatinString().compareTo(second.getLatinString());
    }
}
