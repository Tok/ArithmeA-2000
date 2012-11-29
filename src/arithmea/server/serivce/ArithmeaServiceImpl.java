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

/**
 * Implementation of the service to store, delete and retrieve Terms.
 */
@SuppressWarnings("serial")
public class ArithmeaServiceImpl extends RemoteServiceServlet implements
        ArithmeaService {
    private static final int ROWS_TO_FETCH = 30;
    private static final int MAX_WORD_LENGTH = 30;
    private static final PersistenceManagerFactory PMF = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    /**
     * Default constructor.
     */
    public ArithmeaServiceImpl() {
    }

    /**
     * Stores a new Term.
     */
    public final Term addTerm(final Term term) {
        return updateTerm(term);
    }

    /**
     * Updates and returns an existing term.
     * @return term
     */
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

    /**
     * Deletes the terms with the provided ids.
     * @return message
     */
    public final String deleteTerms(final ArrayList<String> ids) {
        int i;
        for (i = 0; i < ids.size(); ++i) {
            deleteTerm(ids.get(i));
        }
        return "Deleted " + i + " words.";
    }

    /**
     * Deletes a term and returns true.
     * @return boolean
     */
    public final Boolean deleteTerm(final String id) {
        final PersistenceManager pm = PMF.getPersistenceManager();
        try {
            pm.deletePersistent(pm.getObjectById(Term.class, id));
        } finally {
            pm.close();
        }
        return true;
    }

    /**
     * Recalculates and updates the values terms with the provided ids.
     * @return message
     */
    @Override
    public String fixTerms(ArrayList<String> ids) {
        int i;
        for (i = 0; i < ids.size(); ++i) {
            fixTerm(ids.get(i));
        }
        return "Fixed " + i + " words.";
    }

    /**
     * Recalculates and updates the values for a term.
     */
    public final void fixTerm(final String id) {
        final Term newTerm = new Term(id);
        updateTerm(newTerm);
    }
    
    /**
     * Deletes all terms and returns their count.
     * @return count as String
     */
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

    /**
     * Returns all terms that match the provided number for the provided method.
     * @return terms list
     */
    @Override
    public final ArrayList<Term> getTermsFor(final String method, final Integer number) {
        return getTermsWithLimit(method, number, 0);
    }

    /**
     * Returns a limited number of terms that match the provided number for the provided method.
     * @return terms list
     */
    @Override
    public ArrayList<Term> getTermsWithLimit(String method, Integer number, int limit) {
        final PersistenceManager pm = PMF.getPersistenceManager();
        ArrayList<Term> result = new ArrayList<Term>();
        try {
            Query query = pm.newQuery(Term.class);
            query.setFilter(method.toLowerCase() + " == n");
            if (limit > 0) {
                query.setRange(0, limit);
            }
            query.declareParameters("Integer n");
            @SuppressWarnings("unchecked")
            List<Term> tmp = (List<Term>) query.execute(number);
            result.addAll(tmp);
        } finally {
            pm.close();
        }
        return result;
    }
    
    /**
     * Returns some terms that start with the provided letter from the provided offset.
     * @return terms list
     */
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

    /**
     * Returns all terms from the provided offset.
     * @return terms list
     */
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

    /**
     * Returns the term with the provided id.
     * @return term
     */
    @Deprecated
    public final Term getTerm(final String id) {
        final PersistenceManager pm = PMF.getPersistenceManager();
        Term result = null;
        try {
            Query query = pm.newQuery(Term.class);
            query.setFilter("latinString == id");
            query.setUnique(true);
            query.declareParameters("String id");
            result = (Term) query.execute(id);
        } finally {
            pm.close();
        }
        return result;
    }

    /**
     * Parses the provided text and stores the resulting terms.
     * @return report with count
     */
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
