package arithmea.client.service;

import java.util.ArrayList;

import arithmea.shared.data.Term;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Service interface to store and retrieve Terms.
 */
@RemoteServiceRelativePath("arithmeaService")
public interface ArithmeaService extends RemoteService {
  Term addTerm(final Term term); //Create
  Term getTerm(final String id); //Update
  String deleteTerms(final ArrayList<String> ids);
  String fixTerms(final ArrayList<String> ids);
  ArrayList<Term> getTermsFromOffset(final String letter, int from);
  Term updateTerm(final Term term); //Read
  Boolean deleteTerm(final String id); //Destroy
  ArrayList<Term> getTermsFor(final String methodName, final Integer number);
  String parseTerms(String input);
  ArrayList<Term> getAllTermsFromOffset(Integer offset);
  String deleteAllTerms();
}
