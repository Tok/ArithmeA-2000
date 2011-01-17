package arithmea.client.service;

import java.util.ArrayList;

import arithmea.shared.data.Term;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("arithmeaService")
public interface ArithmeaService extends RemoteService {
  Term addTerm(final Term term); //Create
  Term getTerm(final String id); //Update
  ArrayList<Term> deleteTerms(final ArrayList<String> ids);
  ArrayList<Term> getTerms();
  Term updateTerm(final Term term); //Read
  Boolean deleteTerm(final String id); //Destroy

  ArrayList<Term> getTermsFor(final String methodName, final Integer number);
  String parseTerms(String input);
}
