package arithmea.client.service;

import java.util.ArrayList;

import arithmea.shared.data.Term;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ArithmeaServiceAsync {
	void addTerm(final Term term, final AsyncCallback<Term> callback);
	void getTerm(final String id, final AsyncCallback<Term> callback);
	void deleteTerms(final ArrayList<String> ids,
			final AsyncCallback<ArrayList<Term>> callback);
	void getTerms(final AsyncCallback<ArrayList<Term>> callback);
	
	void updateTerm(final Term term, final AsyncCallback<Term> callback);
	void deleteTerm(final String id, final AsyncCallback<Boolean> callback);

}

