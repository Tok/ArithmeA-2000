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
  
  public void testTermSort(){
    ArrayList<Term> terms = new ArrayList<Term>();
    terms.add(new Term("c_term"));
    terms.add(new Term("b_term"));
    terms.add(new Term("a_term"));
    termsPresenter.setTermDetails(terms);
    termsPresenter.sortTermDetails();
    assertTrue(termsPresenter.getTermDetail(0).getLatinString().equals("a_term"));
    assertTrue(termsPresenter.getTermDetail(1).getLatinString().equals("b_term"));
    assertTrue(termsPresenter.getTermDetail(2).getLatinString().equals("c_term"));
  }
}
