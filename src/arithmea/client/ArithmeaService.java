package arithmea.client;

import java.util.ArrayList;

import arithmea.shared.Term;
import arithmea.shared.TermDetails;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("arithmeaService")
public interface ArithmeaService extends RemoteService {
  Term addTerm(Term term); //Create
  Term getTerm(String id); //Update
  ArrayList<TermDetails> deleteTerms(ArrayList<String> ids);
  ArrayList<TermDetails> getTermDetails();
  Term updateTerm(Term term); //Read
  Boolean deleteTerm(String id);  //Destroy
}
