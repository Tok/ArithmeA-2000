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
    private Integer chaldean;
    @Persistent
    private Integer pythagorean;
    @Persistent
    private Integer ia;
    @Persistent
    private Integer naeq;
    @Persistent
    private Integer tq;
    @Persistent
    private Integer eq;
    @Persistent
    private Integer german;
    @Persistent
    private String hebrewString;
    @Persistent
    private Integer full;
    @Persistent
    private Integer ordinal;
    @Persistent
    private Integer katan;

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
        this.eq = values.get(LatinMethod.EQ);
        this.german = values.get(LatinMethod.German);
        this.full = values.get(HebrewMethod.Full);
        this.ordinal = values.get(HebrewMethod.Ordinal);
        this.katan = values.get(HebrewMethod.Katan);
    }

    public final Integer get(final GematriaMethod method) {
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
        } else if (LatinMethod.EQ.equals(method)) {
            return eq;
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

    public final String getLatinString() {
        return latinString;
    }

    public final String getFirstLetter() {
        return firstLetter;
    }

    public final String getHebrewString() {
        return hebrewString;
    }

    public final Integer getChaldean() {
        return chaldean;
    }

    public final Integer getPythagorean() {
        return pythagorean;
    }

    public final Integer getIa() {
        return ia;
    }

    public final Integer getNaeq() {
        return naeq;
    }

    public final Integer getTq() {
        return tq;
    }

    public final Integer getEq() {
        return eq;
    }

    public final Integer getGerman() {
        return german;
    }

    public final Integer getFull() {
        return full;
    }

    public final Integer getOrdinal() {
        return ordinal;
    }

    public final Integer getKatan() {
        return katan;
    }

    public final String toString() {
        return latinString;
    }

}
