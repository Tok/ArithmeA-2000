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
  void addOrUpdateTerm(final Term term);
  String deleteTerms(final ArrayList<String> ids);
  String fixTerms(final ArrayList<String> ids);
  ArrayList<Term> getTermsFromOffset(final String letter, int from);
  Boolean deleteTerm(final String id); //Destroy
  ArrayList<Term> getTermsFor(final String methodName, final int number);
  ArrayList<Term> getTermsWithLimit(final String methodName, final int number, final int limit);
  String parseTerms(String input);
  ArrayList<Term> getAllTermsFromOffset(int offset);
  String deleteAllTerms();
}
