package arithmea.client.sort;

import java.util.Comparator;

import arithmea.shared.Term;

public class TermSortByLatinString implements Comparator<Term> {
	public int compare(final Term first, final Term second) {
		return first.getLatinString().compareTo(second.getLatinString());
	}
}
