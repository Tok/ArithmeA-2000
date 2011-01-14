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
	private String id;

	@Persistent
	public Integer chaldean;

	@Persistent
	public Integer pythagorean;

	@Persistent
	public Integer ia;

	@Persistent
	public Integer naeq;

	public Term() {
	}

//	public Term(String latinString) {
//		setLatinString(latinString);
//	}
	
	public Term(String latinString) {
		setLatinString(latinString);
	}

	public String getId() {
		return id;
	}
	
	public String getLatinString() {
		return id;
	}

	public void setLatinString(String latinString) {
		this.id = latinString.toUpperCase().trim();
		this.chaldean = GematriaUtil.getChaldean(id);
		this.pythagorean = GematriaUtil.getPythagorean(id);
		this.ia = GematriaUtil.getIa(id);
		this.naeq = GematriaUtil.getNaeq(id);
	}

	public Integer getChaldean() {
		return chaldean;
	}

	public void setChaldean(Integer chaldean) {
		this.chaldean = chaldean;
	}

	public Integer getPythagorean() {
		return pythagorean;
	}

	public void setPythagorean(Integer pythagorean) {
		this.pythagorean = pythagorean;
	}

	public Integer getIa() {
		return ia;
	}

	public void setIa(Integer ia) {
		this.ia = ia;
	}

	public Integer getNaeq() {
		return naeq;
	}

	public void setNaeq(Integer naeq) {
		this.naeq = naeq;
	}

}
