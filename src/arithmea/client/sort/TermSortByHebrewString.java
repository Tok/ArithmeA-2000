package arithmea.client.sort;

import java.util.Comparator;

import arithmea.shared.data.Term;

public class TermSortByHebrewString implements Comparator<Term> {
	public int compare(final Term first, final Term second) {
		return first.getHebrewString().compareTo(second.getHebrewString());
	}
}
