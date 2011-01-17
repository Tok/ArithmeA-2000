package arithmea.shared.data;

import java.io.Serializable;
import java.util.HashMap;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.GematriaUtil;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;
import arithmea.shared.gematria.ValidationUtil;

@PersistenceCapable(detachable = "true")
public class Term implements Serializable {
	private static final long serialVersionUID = -1672673901417871681L;

	@PrimaryKey
	@Persistent
	private String latinString;	
	
	@Persistent
	private String firstLetter;
	
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
	
	@Persistent
	public Integer german;
	
	@Persistent
	private String hebrewString;

	@Persistent
	public Integer full;

	@Persistent
	public Integer ordinal;

	@Persistent
	public Integer katan;
	
	public Term() {
	}

	public Term(final String latinString) {
		ValidationUtil vu = new ValidationUtil();
		String tmp = vu.getLetterString(latinString);
		this.latinString = tmp;	
		if (tmp.length() == 0) {
			throw new IllegalArgumentException("Fail: String has no letters.");
		} else {
			this.firstLetter = tmp.substring(0, 1);
		}
		
		GematriaUtil gu = new GematriaUtil();
		this.hebrewString = gu.getHebrew(tmp);
		
		final HashMap<GematriaMethod, Integer> values = gu.getAllValues(tmp);
		this.chaldean = values.get(LatinMethod.Chaldean);
		this.pythagorean = values.get(LatinMethod.Pythagorean);
		this.ia = values.get(LatinMethod.IA);
		this.naeq = values.get(LatinMethod.NAEQ);
		this.tq = values.get(LatinMethod.TQ);
		this.german = values.get(LatinMethod.German);		
		this.full = values.get(HebrewMethod.Full);
		this.ordinal = values.get(HebrewMethod.Ordinal);
		this.katan = values.get(HebrewMethod.Katan);
	}
	
	public String getLatinString() {
		return latinString;
	}
	
	public String getFirstLetter() {
		return firstLetter;
	}
	
	public String getHebrewString() {
		return hebrewString;
	}

	public Integer get(GematriaMethod method) {
		if (LatinMethod.Chaldean.equals(method)) {
			return chaldean;
		} else if (LatinMethod.Pythagorean.equals(method)) {
			return pythagorean;
		} else if (LatinMethod.IA.equals(method)) {
			return ia;
		} else if (LatinMethod.NAEQ.equals(method)) {
			return naeq;
		} else if (LatinMethod.TQ.equals(method)) {
			return tq;
		} else if (LatinMethod.German.equals(method)) {
			return german;
		} else if (HebrewMethod.Full.equals(method)) {
			return full;
		} else if (HebrewMethod.Ordinal.equals(method)) {
			return ordinal;
		} else if (HebrewMethod.Katan.equals(method)) {
			return katan;
		}
		
		assert false;
		return 0;
	}
	
	public String toString() {
		return latinString;
	}

}