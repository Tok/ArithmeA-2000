package arithmea.test;

import java.util.ArrayList;

import arithmea.client.ArithmeaService;
import arithmea.client.ArithmeaServiceAsync;
import arithmea.client.presenter.TermsPresenter;
import arithmea.client.view.TermsView;
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

	public void testTermSort() {
		ArrayList<Term> terms = new ArrayList<Term>();
		terms.add(new Term("c_term"));
		terms.add(new Term("b_term"));
		terms.add(new Term("a_term"));
		termsPresenter.setTermDetails(terms);
		termsPresenter.sortTermDetails();
		assertTrue(termsPresenter.getTermDetail(0).getId().equals("A_TERM"));
		assertTrue(termsPresenter.getTermDetail(1).getId().equals("B_TERM"));
		assertTrue(termsPresenter.getTermDetail(2).getId().equals("C_TERM"));
	}

	public void testChaldean() {
		assertTrue(new Term(ABC).getChaldean().equals(new Integer(103)));
		assertTrue(new Term("ARITHMEA").getChaldean().equals(new Integer(23)));
	}

	public void testPythagorean() {
		assertTrue(new Term(ABC).getPythagorean().equals(new Integer(126)));
		assertTrue(new Term("ARITHMEA").getPythagorean()
				.equals(new Integer(39)));
	}

	public void testIa() {
		assertTrue(new Term(ABC).getIa().equals(new Integer(351)));
		assertTrue(new Term("ARITHMEA").getIa().equals(new Integer(75)));
	}

	public void testNaeq() {
		assertTrue(new Term(ABC).getNaeq().equals(new Integer(361)));
		assertTrue(new Term("ARITHMEA").getNaeq().equals(new Integer(111)));
	}

	public void testTq() {
		assertTrue(new Term(ABC).getTq().equals(new Integer(351)));
		assertTrue(new Term("ARITHMEA").getTq().equals(new Integer(96)));
	}
}
