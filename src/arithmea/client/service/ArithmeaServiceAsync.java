package arithmea.client.service;

import java.util.ArrayList;
import arithmea.shared.data.Term;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Asynchronous interface for the service to store and retrieve Terms.
 */
public interface ArithmeaServiceAsync {
    void addOrUpdateTerm(final Term term, final AsyncCallback<Void> callback);
    void deleteTerm(final String id, final AsyncCallback<Boolean> callback);
    void parseTerms(final String input, final AsyncCallback<String> callback);
    void getTermsFromOffset(final String letter, final int from, final AsyncCallback<ArrayList<Term>> callback);
    void getTermsFor(final String methodName, final int number, final AsyncCallback<ArrayList<Term>> callback);
    void getTermsWithLimit(final String methodName, final int number, final int limit, final AsyncCallback<ArrayList<Term>> callback);
    void getAllTermsFromOffset(final int offset, final AsyncCallback<ArrayList<Term>> callback);
    void fixTerms(final ArrayList<String> ids, final AsyncCallback<String> callback);
    void deleteTerms(final ArrayList<String> ids, final AsyncCallback<String> callback);
    void deleteAllTerms(final AsyncCallback<String> callback);
}
