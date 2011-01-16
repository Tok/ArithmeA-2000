package arithmea.server.serivce;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import arithmea.client.service.ArithmeaService;
import arithmea.shared.data.Term;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ArithmeaServiceImpl extends RemoteServiceServlet implements
		ArithmeaService {
	private static final PersistenceManagerFactory PMF = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	public ArithmeaServiceImpl() {
	}

	public Term addTerm(final Term term) {
		return updateTerm(term);
	}

	public Term updateTerm(final Term term) {
		final PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.makePersistent(term);
		} finally {
			pm.close();
		}
		return term;
	}

	public Boolean deleteTerm(final String id) {
		final PersistenceManager pm = PMF.getPersistenceManager();
		try {
			pm.deletePersistent(pm.getObjectById(Term.class, id ));
		} finally {
			pm.close();
		}
		return true;
	}

	public ArrayList<Term> deleteTerms(final ArrayList<String> ids) {
		for (int i = 0; i < ids.size(); ++i) {
			deleteTerm(ids.get(i));
		}
		return getTerms();
	}

	private List<Term> getAllTerms() {
		final PersistenceManager pm = PMF.getPersistenceManager();
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
	
	public ArrayList<Term> getTerms() {
		//TODO remove this
		final ArrayList<Term> terms = new ArrayList<Term>();
		final Iterator<Term> it = getAllTerms().iterator();
		while (it.hasNext()) {
			Term term = it.next();
			terms.add(term);
		}
		return terms;
	}

	public Term getTerm(String id) {
		final PersistenceManager pm = PMF.getPersistenceManager();
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
	}

}
