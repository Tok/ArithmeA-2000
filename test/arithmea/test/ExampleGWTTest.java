package arithmea.test;

import java.util.ArrayList;

import arithmea.client.ArithmeaService;
import arithmea.client.ArithmeaServiceAsync;
import arithmea.client.presenter.TermsPresenter;
import arithmea.client.view.TermsView;
import arithmea.shared.TermDetails;

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
    ArrayList<TermDetails> termDetails = new ArrayList<TermDetails>();
    termDetails.add(new TermDetails("0", "c_term"));
    termDetails.add(new TermDetails("1", "b_term"));
    termDetails.add(new TermDetails("2", "a_term"));
    termsPresenter.setTermDetails(termDetails);
    termsPresenter.sortTermDetails();
    assertTrue(termsPresenter.getTermDetail(0).getDisplayName().equals("a_term"));
    assertTrue(termsPresenter.getTermDetail(1).getDisplayName().equals("b_term"));
    assertTrue(termsPresenter.getTermDetail(2).getDisplayName().equals("c_term"));
  }
}
