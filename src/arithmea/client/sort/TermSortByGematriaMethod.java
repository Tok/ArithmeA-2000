package arithmea.client.sort;

import java.util.Comparator;

import arithmea.shared.GematriaMethod;
import arithmea.shared.Term;

public class TermSortByGematriaMethod implements Comparator<Term> {
	private final GematriaMethod gm;
	
	public TermSortByGematriaMethod(GematriaMethod gm) {
		this.gm = gm;
	}
	
	public GematriaMethod getGematriaMethod() {
		return gm;
	}
	
	public int compare(final Term first, final Term second) {
		return first.get(gm).compareTo(second.get(gm));
	}
}
