package arithmea.client;

import java.util.ArrayList;

import arithmea.shared.Term;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ArithmeaServiceAsync {
	void addTerm(Term term, AsyncCallback<Term> callback);
	void getTerm(String id, AsyncCallback<Term> callback);
	void deleteTerms(ArrayList<String> ids,
			AsyncCallback<ArrayList<Term>> callback);
	void getTerms(AsyncCallback<ArrayList<Term>> callback);
	
	void updateTerm(Term term, AsyncCallback<Term> callback);
	void deleteTerm(String id, AsyncCallback<Boolean> callback);

}

