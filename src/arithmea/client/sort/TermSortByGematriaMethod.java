package arithmea.client.sort;

import java.util.Comparator;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;

/**
 * Sorter for Terms by GematriaMethod.
 */
public class TermSortByGematriaMethod implements Comparator<Term> {
    private final GematriaMethod gm;

    /**
     * Default constructor.
     * @param gematria method
     */
    public TermSortByGematriaMethod(final GematriaMethod gm) {
        this.gm = gm;
    }

    /**
     * Returns the GematriaMethod by wich the terms are sorted.
     * @return gematria method
     */
    public final GematriaMethod getGematriaMethod() {
        return gm;
    }

    /**
     * Compares two terms.
     * @return positive or negative number or 0.
     */
    public final int compare(final Term first, final Term second) {
        return first.get(gm).compareTo(second.get(gm));
    }
}
