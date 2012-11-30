package arithmea.test;

import java.util.ArrayList;

import arithmea.client.presenter.TermsPresenter;
import arithmea.client.service.ArithmeaService;
import arithmea.client.service.ArithmeaServiceAsync;
import arithmea.client.view.TermsView;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Arithmea test cases.
 */
public class ArithmeaGWTTest extends GWTTestCase {
    private static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DER_SCHLUESSEL = "Der Schluessel";

    private TermsPresenter termsPresenter;
    private ArithmeaServiceAsync rpcService;
    private HandlerManager eventBus;
    private TermsPresenter.Display display;

    public final String getModuleName() {
        return "arithmea.Arithmea";
    }

    public final void gwtSetUp() {
        rpcService = GWT.create(ArithmeaService.class);
        eventBus = new HandlerManager(null);
        display = new TermsView(eventBus, "", 0);
        termsPresenter = new TermsPresenter(rpcService, eventBus, display);
    }

    /**
     * Tests sorting by latin String.
     */
    public final void testTermSortByLatinString() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("ccc"));
        terms.add(new Term("bbb"));
        terms.add(new Term("aaa"));
        termsPresenter.setTermDetails(terms);
        termsPresenter.sortTermsByLatinString();
        assertTrue(termsPresenter.getTermDetail(0).getLatinString().equals("AAA"));
        assertTrue(termsPresenter.getTermDetail(1).getLatinString().equals("BBB"));
        assertTrue(termsPresenter.getTermDetail(2).getLatinString().equals("CCC"));
    }

    /**
     * Tests sorting by chaldean values.
     */
    public final void testTermSortByChaldean() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("B"));
        terms.add(new Term("T"));
        terms.add(new Term("E"));
        terms.add(new Term("G"));
        terms.add(new Term("I"));
        termsPresenter.setTermDetails(terms);
        termsPresenter.sortTermsBy(LatinMethod.Chaldean);
        assertTrue(termsPresenter.getTermDetail(0).getLatinString().equals("I"));
        assertTrue(termsPresenter.getTermDetail(1).getLatinString().equals("B"));
        assertTrue(termsPresenter.getTermDetail(2).getLatinString().equals("G"));
        assertTrue(termsPresenter.getTermDetail(3).getLatinString().equals("T"));
        assertTrue(termsPresenter.getTermDetail(4).getLatinString().equals("E"));
    }

    /**
     * Tests sorting by pythagorean values.
     */
    public final void testTermSortByPythagorean() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("C"));
        terms.add(new Term("W"));
        terms.add(new Term("M"));
        terms.add(new Term("K"));
        terms.add(new Term("S"));
        termsPresenter.setTermDetails(terms);
        termsPresenter.sortTermsBy(LatinMethod.Pythagorean);
        assertTrue(termsPresenter.getTermDetail(0).getLatinString().equals("S"));
        assertTrue(termsPresenter.getTermDetail(1).getLatinString().equals("K"));
        assertTrue(termsPresenter.getTermDetail(2).getLatinString().equals("C"));
        assertTrue(termsPresenter.getTermDetail(3).getLatinString().equals("M"));
        assertTrue(termsPresenter.getTermDetail(4).getLatinString().equals("W"));
    }

    /**
     * Tests sorting by simple english gematria values.
     */
    public final void testTermSortByIa() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("A"));
        terms.add(new Term("Z"));
        terms.add(new Term("B"));
        terms.add(new Term("C"));
        termsPresenter.setTermDetails(terms);
        termsPresenter.sortTermsBy(LatinMethod.IA);
        assertTrue(termsPresenter.getTermDetail(0).getLatinString().equals("A"));
        assertTrue(termsPresenter.getTermDetail(1).getLatinString().equals("B"));
        assertTrue(termsPresenter.getTermDetail(2).getLatinString().equals("C"));
        assertTrue(termsPresenter.getTermDetail(3).getLatinString().equals("Z"));
    }

    /**
     * Tests sorting by naeq values.
     */
    public final void testTermSortByNaeq() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("A"));
        terms.add(new Term("B"));
        terms.add(new Term("C"));
        terms.add(new Term("D"));
        terms.add(new Term("E"));
        termsPresenter.setTermDetails(terms);
        termsPresenter.sortTermsBy(LatinMethod.NAEQ);
        assertTrue(termsPresenter.getTermDetail(0).getLatinString().equals("A"));
        assertTrue(termsPresenter.getTermDetail(1).getLatinString().equals("D"));
        assertTrue(termsPresenter.getTermDetail(2).getLatinString().equals("C"));
        assertTrue(termsPresenter.getTermDetail(3).getLatinString().equals("B"));
        assertTrue(termsPresenter.getTermDetail(4).getLatinString().equals("E"));
    }

    /**
     * Tests sorting by trigrammaton qabalah values.
     */
    public final void testTermSortByTq() {
        ArrayList<Term> terms = new ArrayList<Term>();
        terms.add(new Term("A"));
        terms.add(new Term("B"));
        terms.add(new Term("C"));
        terms.add(new Term("D"));
        terms.add(new Term("E"));
        termsPresenter.setTermDetails(terms);
        termsPresenter.sortTermsBy(LatinMethod.TQ);
        assertTrue(termsPresenter.getTermDetail(0).getLatinString().equals("C"));
        assertTrue(termsPresenter.getTermDetail(1).getLatinString().equals("A"));
        assertTrue(termsPresenter.getTermDetail(2).getLatinString().equals("E"));
        assertTrue(termsPresenter.getTermDetail(3).getLatinString().equals("B"));
        assertTrue(termsPresenter.getTermDetail(4).getLatinString().equals("D"));
    }

    /**
     * Tests results for the chaldean method.
     */
    public final void testChaldean() {
        final int chaldeanAbc = 103;
        final int chaldeanArithmea = 23;
        final int chaldeanDerSchluessel = 50;
        assertTrue(new Term(ABC).get(LatinMethod.Chaldean).equals(new Integer(chaldeanAbc)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.Chaldean).equals(new Integer(chaldeanArithmea)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.Chaldean).equals(new Integer(chaldeanDerSchluessel)));
    }

    /**
     * Tests results for the pythagorean method.
     */
    public final void testPythagorean() {
        final int pythagoreanAbc = 126;
        final int pythagoreanArithmea = 39;
        final int pythagoreanDerSchluessel = 51;
        assertTrue(new Term(ABC).get(LatinMethod.Pythagorean).equals(new Integer(pythagoreanAbc)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.Pythagorean).equals(new Integer(pythagoreanArithmea)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.Pythagorean).equals(new Integer(pythagoreanDerSchluessel)));
    }

    /**
     * Tests results for simple english gematria.
     */
    public final void testIa() {
        final int iaAbc = 351;
        final int iaArithmea = 75;
        final int iaDerSchluessel = 150;
        assertTrue(new Term(ABC).get(LatinMethod.IA).equals(new Integer(iaAbc)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.IA).equals(new Integer(iaArithmea)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.IA).equals(new Integer(iaDerSchluessel)));
    }

    /**
     * Tests results for new aeon english qabalah.
     */
    public final void testNaeq() {
        final int naeqAbc = 361;
        final int naeqArithmea = 111;
        final int naeqDerSchluessel = 146;
        assertTrue(new Term(ABC).get(LatinMethod.NAEQ).equals(new Integer(naeqAbc)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.NAEQ).equals(new Integer(naeqArithmea)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.NAEQ).equals(new Integer(naeqDerSchluessel)));
    }

    /**
     * Tests results for trigrammaton qabalah.
     */
    public final void testTq() {
        final int tqAbc = 351;
        final int tqArithmea = 96;
        final int tqDerSchluessel = 153;
        assertTrue(new Term(ABC).get(LatinMethod.TQ).equals(new Integer(tqAbc)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.TQ).equals(new Integer(tqArithmea)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.TQ).equals(new Integer(tqDerSchluessel)));
    }

    /**
     * Tests results for the german method.
     */
    public final void testGerman() {
        final int germanDerSchluessel = 55;
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.German).equals(new Integer(germanDerSchluessel)));
    }

    /**
     * Tests results for EQ values.
     */
    public final void testEq() {
        final int eqAdhaar = 44;
        assertTrue(new Term("AADHAAR").get(LatinMethod.EQ).equals(new Integer(eqAdhaar)));
    }

    /**
     * Tests results for full values.
     */
    public final void testFull() {
        final int fullDerSchluessel = 577;
        assertTrue(new Term(DER_SCHLUESSEL).get(HebrewMethod.Full).equals(new Integer(fullDerSchluessel)));
    }

    /**
     * Tests results for ordinal values.
     */
    public final void testOrdinal() {
        final int ordinalDerSchluessel = 82;
        assertTrue(new Term(DER_SCHLUESSEL).get(HebrewMethod.Ordinal).equals(new Integer(ordinalDerSchluessel)));
    }

    /**
     * Tests results for katan values.
     */
    public final void testKatan() {
        final int katanDerSchluessel = 28;
        assertTrue(new Term(DER_SCHLUESSEL).get(HebrewMethod.Katan).equals(new Integer(katanDerSchluessel)));
    }
}
