package arithmea.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Term implements Serializable {
	private static final long serialVersionUID = -1672673901417871681L;

	@PrimaryKey
	@Persistent
	private String latinString;

	@Persistent
	public Integer chaldean;

	@Persistent
	public Integer pythagorean;

	@Persistent
	public Integer ia;

	@Persistent
	public Integer naeq;

	@Persistent
	public Integer tq;
	
	public Term() {
	}

	public Term(final String latinString) {
		setLatinString(latinString);
	}
	
	public String getLatinString() {
		return latinString;
	}

	public void setLatinString(final String latinString) {
		this.latinString = latinString.toUpperCase().trim();
		this.chaldean = GematriaUtil.getChaldean(latinString);
		this.pythagorean = GematriaUtil.getPythagorean(latinString);
		this.ia = GematriaUtil.getIa(latinString);
		this.naeq = GematriaUtil.getNaeq(latinString);
		this.tq = GematriaUtil.getTq(latinString);		
	}

	public Integer get(GematriaMethod method) {
		if (GematriaMethod.Chaldean.equals(method)) {
			return chaldean;
		} else if (GematriaMethod.Pythagorean.equals(method)) {
			return pythagorean;
		} else if (GematriaMethod.IA.equals(method)) {
			return ia;
		} else if (GematriaMethod.NAEQ.equals(method)) {
			return naeq;
		} else if (GematriaMethod.TQ.equals(method)) {
			return tq;
		}
		
		assert false;
		return 0;
	}
	
	public String toString() {
		return latinString;
	}

}
