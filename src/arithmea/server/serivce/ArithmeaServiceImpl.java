package arithmea.server.serivce;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
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
    private static final int ROWS_TO_FETCH = 30;
    private static final int MAX_WORD_LENGTH = 30;
    private static final PersistenceManagerFactory PMF = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    public ArithmeaServiceImpl() {
    }

    public final Term addTerm(final Term term) {
        return updateTerm(term);
    }

    public final Term updateTerm(final Term term) {
        if (term.getFirstLetter().equals(" ")) {
            return term;
        }
        final PersistenceManager pm = PMF.getPersistenceManager();
        try {
            pm.makePersistent(term);
        } finally {
            pm.close();
        }
        return term;
    }

    public final Boolean deleteTerm(final String id) {
        final PersistenceManager pm = PMF.getPersistenceManager();
        try {
            pm.deletePersistent(pm.getObjectById(Term.class, id));
        } finally {
            pm.close();
        }
        return true;
    }

    public final String deleteTerms(final ArrayList<String> ids) {
        int i;
        for (i = 0; i < ids.size(); ++i) {
            deleteTerm(ids.get(i));
        }
        return "Deleted " + i + " words.";
    }

    @Override
    public final String deleteAllTerms() {
        PersistenceManager pm = PMF.getPersistenceManager();
        Extent<Term> extent = pm.getExtent(Term.class, false);
        int count = 0;
        for (Term term : extent) {
            pm.deletePersistent(term);
            count++;
        }
        return String.valueOf(count);
    }

    @Override
    public final ArrayList<Term> getTermsFor(final String method, final Integer number) {
        final PersistenceManager pm = PMF.getPersistenceManager();
        ArrayList<Term> result = new ArrayList<Term>();
        try {
            Query query = pm.newQuery(Term.class);
            query.setFilter(method.toLowerCase() + " == n");
            query.declareParameters("Integer n");
            @SuppressWarnings("unchecked")
            List<Term> tmp = (List<Term>) query.execute(number);
            result.addAll(tmp);
        } finally {
            pm.close();
        }
        return result;
    }

    @Override
    public final ArrayList<Term> getTermsFromOffset(final String letter, final int from) {
        final PersistenceManager pm = PMF.getPersistenceManager();
        ArrayList<Term> result = new ArrayList<Term>();
        try {
            Query query = pm.newQuery(Term.class);
            query.setFilter("firstLetter == l");
            query.declareParameters("String l");
            query.setRange(from, from + ROWS_TO_FETCH);
            @SuppressWarnings("unchecked")
            List<Term> tmp = (List<Term>) query.execute(letter);
            result.addAll(tmp);
        } finally {
            pm.close();
        }
        return result;
    }

    public final ArrayList<Term> getAllTermsFromOffset(final Integer offset) {
        final PersistenceManager pm = PMF.getPersistenceManager();
        ArrayList<Term> result = new ArrayList<Term>();
        try {
            Query query = pm.newQuery(Term.class);
            query.setRange(offset, offset + ROWS_TO_FETCH);
            @SuppressWarnings("unchecked")
            List<Term> tmp = (List<Term>) query.execute();
            result.addAll(tmp);
        } finally {
            pm.close();
        }
        return result;
    }

    public final Term getTerm(final String id) {
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

    @Override
    public final String parseTerms(final String input) {
        String[] words = input.split("\\s+");
        int count = 0;
        int ignored = 0;
        for (String word : words) {
            try {
                if (word.length() < MAX_WORD_LENGTH) { //ignore long words
                    Term term = new Term(word);
                    addTerm(term);
                    count++;
                }
            } catch (final IllegalArgumentException iae) {
                ignored++;
            }
        }
        return "parsed " + count + " words. (" + ignored + " ignored).";
    }

}
