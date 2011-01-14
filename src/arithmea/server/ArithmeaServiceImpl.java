package arithmea.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import arithmea.client.ArithmeaService;
import arithmea.shared.Term;
import arithmea.shared.TermDetails;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ArithmeaServiceImpl extends RemoteServiceServlet implements
		ArithmeaService {
	private static final PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

//	private static final String[] termsData = new String[] { "Hollie",
//			"Emerson", "Healy", "Brigitte", "Elba", "Claudio", "Dena",
//			"Christina", "Gail", "Orville", "Rae", "Mildred", "Candice",
//			"Louise", "Emilio", "Geneva", "Heriberto", "Bulrush", "Abigail",
//			"Chad", "Terry", "Bell" };
//
//	private final Integer[] chaldeanData = new Integer[] { 0, 0, 0, 0, 0, 0, 0,
//			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//
//	private final Integer[] pythagoreanData = new Integer[] { 0, 0, 0, 0, 0, 0,
//			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

//	private final HashMap<String, Term> terms = new HashMap<String, Term>();

	public ArithmeaServiceImpl() {
//		initTerms();
	}

//	private void initTerms() {
//		// TODO: Create a real UID for each term
//		//
//		for (int i = 0; i < termsData.length && i < chaldeanData.length
//				&& i < pythagoreanData.length; ++i) {
//			Term term = new Term(String.valueOf(i), termsData[i],
//					chaldeanData[i], pythagoreanData[i]);
//			terms.put(term.getId(), term);
//		}
//	}

	public Term addTerm(Term term) {
//		term.setId(String.valueOf(terms.size()));
//		terms.put(term.getId(), term);
//		return term;
		
		return updateTerm(term);
	}

	public Term updateTerm(Term term) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.makePersistent(term);
			
//			terms.remove(term.getId());
//			terms.put(term.getId(), term);
		} finally {
			pm.close();
		}
		return term;
	}

	public Boolean deleteTerm(String id) {
		PersistenceManager pm = PMF.getPersistenceManager();
		try {
//			Term term = getTerm(id);

			pm.deletePersistent(pm.getObjectById(Term.class, id ));
			
			
			
		} finally {
			pm.close();
		}
	
//		terms.remove(id);
		return true;
	}

	public ArrayList<TermDetails> deleteTerms(ArrayList<String> ids) {

		for (int i = 0; i < ids.size(); ++i) {
			deleteTerm(ids.get(i));
		}

		return getTermDetails();
	}

	private List<Term> getAllTerms() {
		PersistenceManager pm = PMF.getPersistenceManager();
		List<Term> result = new ArrayList<Term>();
		try {
			Query q = pm.newQuery(Term.class);
			@SuppressWarnings("unchecked")
			List<Term> players = (List<Term>) q.execute();
			result.addAll(players);
		} finally {
			pm.close();
		}
		return result;
	}
	
	public ArrayList<TermDetails> getTermDetails() {
		ArrayList<TermDetails> termDetails = new ArrayList<TermDetails>();

		Iterator<Term> it = getAllTerms().iterator();
		while (it.hasNext()) {
//			Term term = terms.get(it.next());
			Term term = it.next();
			termDetails.add(term.getLightWeightTerm());
		}

		return termDetails;
	}

	public Term getTerm(String id) {
		PersistenceManager pm = PMF.getPersistenceManager();
		Term result = null;
		try {
			Query query = pm.newQuery(Term.class);
			query.setFilter("id == i");
			query.setUnique(true);
			query.declareParameters("String i");
			result = (Term) query.execute(id);
		} finally {
			pm.close();
		}
		return result;
		
//		return terms.get(id);
	}

}
