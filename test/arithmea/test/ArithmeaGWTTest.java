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
        assertTrue(new Term(ABC).get(LatinMethod.Chaldean).equals(new Integer(103)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.Chaldean).equals(new Integer(23)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.Chaldean).equals(new Integer(50)));
    }

    /**
     * Tests results for the pythagorean method.
     */
    public final void testPythagorean() {
        assertTrue(new Term(ABC).get(LatinMethod.Pythagorean).equals(new Integer(126)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.Pythagorean).equals(new Integer(39)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.Pythagorean).equals(new Integer(51)));
    }

    /**
     * Tests results for simple english gematria.
     */
    public final void testIa() {
        assertTrue(new Term(ABC).get(LatinMethod.IA).equals(new Integer(351)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.IA).equals(new Integer(75)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.IA).equals(new Integer(150)));
    }

    /**
     * Tests results for new aeon english qabalah.
     */
    public final void testNaeq() {
        assertTrue(new Term(ABC).get(LatinMethod.NAEQ).equals(new Integer(361)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.NAEQ).equals(new Integer(111)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.NAEQ).equals(new Integer(146)));
    }
    
    /**
     * Tests results for trigrammaton qabalah.
     */
    public final void testTq() {
        assertTrue(new Term(ABC).get(LatinMethod.TQ).equals(new Integer(351)));
        assertTrue(new Term("ARITHMEA").get(LatinMethod.TQ).equals(new Integer(96)));
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.TQ).equals(new Integer(153)));
    }

    /**
     * Tests results for the german method.
     */
    public final void testGerman() {
        assertTrue(new Term(DER_SCHLUESSEL).get(LatinMethod.German).equals(new Integer(55)));
    }

    /**
     * Tests results for full values.
     */
    public final void testFull() {
        assertTrue(new Term(DER_SCHLUESSEL).get(HebrewMethod.Full).equals(new Integer(577)));
    }

    /**
     * Tests results for ordinal values.
     */
    public final void testOrdinal() {
        assertTrue(new Term(DER_SCHLUESSEL).get(HebrewMethod.Ordinal).equals(new Integer(82)));
    }

    /**
     * Tests results for katan values.
     */
    public final void testKatan() {
        assertTrue(new Term(DER_SCHLUESSEL).get(HebrewMethod.Katan).equals(new Integer(28)));
    }
}
