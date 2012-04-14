package arithmea.client.sort;

import java.util.Comparator;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;

public class TermSortByGematriaMethod implements Comparator<Term> {
    private final GematriaMethod gm;

    public TermSortByGematriaMethod(final GematriaMethod gm) {
        this.gm = gm;
    }

    public final GematriaMethod getGematriaMethod() {
        return gm;
    }

    public final int compare(final Term first, final Term second) {
        return first.get(gm).compareTo(second.get(gm));
    }
}
