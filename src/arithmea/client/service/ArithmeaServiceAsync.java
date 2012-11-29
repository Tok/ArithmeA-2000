package arithmea.client.service;

import java.util.ArrayList;
import arithmea.shared.data.Term;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Asynchronous interface for the service to store and retrieve Terms.
 */
public interface ArithmeaServiceAsync {
    void addTerm(final Term term, final AsyncCallback<Term> callback);
    void updateTerm(final Term term, final AsyncCallback<Term> callback);
    void deleteTerm(final String id, final AsyncCallback<Boolean> callback);
    void parseTerms(String input, AsyncCallback<String> callback);
    void getTermsFromOffset(String letter, int from, AsyncCallback<ArrayList<Term>> callback);
    void getTermsFor(String methodName, Integer number, AsyncCallback<ArrayList<Term>> callback);
    void getAllTermsFromOffset(Integer offset, AsyncCallback<ArrayList<Term>> callback);
    void fixTerms(ArrayList<String> ids, AsyncCallback<String> callback);
    void deleteTerms(ArrayList<String> ids, AsyncCallback<String> callback);
    void deleteAllTerms(AsyncCallback<String> callback);
}
