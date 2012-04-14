package arithmea.client.sort;

import java.util.Comparator;
import arithmea.shared.data.Term;

public class TermSortByLatinString implements Comparator<Term> {
    public final int compare(final Term first, final Term second) {
        return first.getLatinString().compareTo(second.getLatinString());
    }
}
