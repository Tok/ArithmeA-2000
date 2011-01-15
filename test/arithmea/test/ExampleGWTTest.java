package arithmea.test;

import java.util.ArrayList;

import arithmea.client.ArithmeaService;
import arithmea.client.ArithmeaServiceAsync;
import arithmea.client.presenter.TermsPresenter;
import arithmea.client.view.TermsView;
import arithmea.shared.LatinMethod;
import arithmea.shared.Term;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;

public class ExampleGWTTest extends GWTTestCase {
	private static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private TermsPresenter termsPresenter;
	private ArithmeaServiceAsync rpcService;
	private HandlerManager eventBus;
	private TermsPresenter.Display display;

	public String getModuleName() {
		return "arithmea.Arithmea";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(ArithmeaService.class);
		eventBus = new HandlerManager(null);
		display = new TermsView();
		termsPresenter = new TermsPresenter(rpcService, eventBus, display);
	}

	public void testTermSortByLatinString() {
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

	public void testTermSortByChaldean() {
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
	
	public void testTermSortByPythagorean() {
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
	
	public void testTermSortByIa() {
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

	public void testTermSortByNaeq() {
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

	public void testTermSortByTq() {
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
	
	public void testChaldean() {
		assertTrue(new Term(ABC).get(LatinMethod.Chaldean).equals(new Integer(103)));
		assertTrue(new Term("ARITHMEA").get(LatinMethod.Chaldean).equals(new Integer(23)));
	}

	public void testPythagorean() {
		assertTrue(new Term(ABC).get(LatinMethod.Pythagorean).equals(new Integer(126)));
		assertTrue(new Term("ARITHMEA").get(LatinMethod.Pythagorean)
				.equals(new Integer(39)));
	}

	public void testIa() {
		assertTrue(new Term(ABC).get(LatinMethod.IA).equals(new Integer(351)));
		assertTrue(new Term("ARITHMEA").get(LatinMethod.IA).equals(new Integer(75)));
	}

	public void testNaeq() {
		assertTrue(new Term(ABC).get(LatinMethod.NAEQ).equals(new Integer(361)));
		assertTrue(new Term("ARITHMEA").get(LatinMethod.NAEQ).equals(new Integer(111)));
	}

	public void testTq() {
		assertTrue(new Term(ABC).get(LatinMethod.TQ).equals(new Integer(351)));
		assertTrue(new Term("ARITHMEA").get(LatinMethod.TQ).equals(new Integer(96)));
	}
}
