package arithmea.server.serivce;

import java.util.ArrayList

import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.JavaConverters.asScalaIteratorConverter
import scala.collection.JavaConverters._

import com.google.appengine.api.memcache.MemcacheServiceFactory
import com.google.gwt.user.server.rpc.RemoteServiceServlet

import arithmea.client.service.ArithmeaService
import arithmea.shared.data.Term
import javax.jdo.JDOHelper
import javax.jdo.Query

/**
 * Implementation of the service to store, delete and retrieve Terms.
 */
class ArithmeaServiceImpl extends RemoteServiceServlet with ArithmeaService {
  val PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional")
  val CACHE = MemcacheServiceFactory.getMemcacheService
  val ROWS_TO_FETCH = 30
  val MAX_WORD_LENGTH = 30

  /**
   * Updates and returns an existing term.
   * @return term
   */
  override def addOrUpdateTerm(term: Term): Unit = {
    val pm = PMF.getPersistenceManager
    try {
      pm.makePersistent(term)
    } finally {
      pm.close
    }
    CACHE.clearAll
  }

  /**
   * Deletes the terms with the provided ids.
   * @return message
   */
  def deleteTerms(ids: java.util.ArrayList[String]): String = {
    val pm = PMF.getPersistenceManager
    try {
      ids.asScala.foreach(x => pm.deletePersistent(pm.getObjectById(classOf[Term], x)))
    } finally {
      pm.close
    }
    CACHE.clearAll
    "Deleted " + ids.size + " words."
  }

  /**
   * Deletes a term and returns true.
   * @return boolean
   */
  override def deleteTerm(id: String): java.lang.Boolean = {
    val pm = PMF.getPersistenceManager
    try {
      pm.deletePersistent(pm.getObjectById(classOf[Term], id))
    } finally {
      pm.close
    }
    CACHE.clearAll
    true
  }

  /**
   * Recalculates and updates the values terms with the provided ids.
   * @return message
   */
  def fixTerms(ids: java.util.ArrayList[String]): String = {
    ids.asScala.foreach(x => addOrUpdateTerm(new Term(x)))
    "Fixed " + ids.size + " words."
  }

  /**
   * Deletes all terms and returns their count.
   * @return count as String
   */
  override def deleteAllTerms(): String = {
    val pm = PMF.getPersistenceManager
    val terms = pm.getExtent(classOf[Term], false).iterator().asScala.toList.map(x => pm.deletePersistent(x))
    CACHE.clearAll
    terms.size.toString
  }

  /**
   * Returns all terms that match the provided number for the provided method.
   * @return terms list
   */
  def getTermsFor(method: String, number: Int): java.util.ArrayList[Term] = getTermsWithLimit(method, number, 0)

  /**
   * Returns a limited number of terms that match the provided number for the provided method.
   * @return terms list
   */
  def getTermsWithLimit(method: String, number: Int, limit: Int): java.util.ArrayList[Term] = {
    val key = method + ":" + number + ":" + limit
    val cached = CACHE.get(key).asInstanceOf[java.util.ArrayList[Term]]
    if (cached != None.orNull) { cached
    } else {
      val pm = PMF.getPersistenceManager
      try {
        val query = pm.newQuery(classOf[Term])
        query.setFilter(method.toLowerCase + " == n")
        query.declareParameters("Integer n");
        if (limit > 0) {
          query.setRange(0, Math.max(0, limit))
        }
        makeCachedArrayList(key, query.execute(number).asInstanceOf[java.util.List[Term]])
      } finally {
        pm.close
      }
    }
  }

  /**
   * Returns some terms that start with the provided letter from the provided offset.
   * @return terms list
   */
  override def getTermsFromOffset(letter: String, from: Int): java.util.ArrayList[Term] = {
    val key = letter + ":" + from
    val cached: java.util.ArrayList[Term] = CACHE.get(key).asInstanceOf[java.util.ArrayList[Term]]
    if (cached != None.orNull) { cached
    } else {
      val pm = PMF.getPersistenceManager
      try {
        val query = pm.newQuery(classOf[Term])
        query.setFilter("firstLetter == l")
        query.declareParameters("String l")
        query.setRange(from.longValue, from + ROWS_TO_FETCH)
        makeCachedArrayList(key, query.execute(letter).asInstanceOf[java.util.List[Term]])
      } finally {
        pm.close
      }
    }
  }

  /**
   * Returns all terms from the provided offset.
   * @return terms list
   */
  override def getAllTermsFromOffset(offset: Int): java.util.ArrayList[Term] = {
    val key = String.valueOf(offset)
    val cached = CACHE.get(key).asInstanceOf[java.util.ArrayList[Term]]
    if (cached != None.orNull) { cached
    } else {
      val pm = PMF.getPersistenceManager
      try {
        val query = pm.newQuery(classOf[Term])
        query.setRange(offset.longValue, offset + ROWS_TO_FETCH)
        makeCachedArrayList(key, query.execute().asInstanceOf[java.util.List[Term]])
      } finally {
        pm.close
      }
    }
  }

  /**
   * Parses the provided text and stores the resulting terms.
   * @return report with count
   */
  def parseTerms(input: String): String = {
    val totalWords = input.split("\\s+")
    val filtered = totalWords.filter(_.length() < MAX_WORD_LENGTH)
    val wordsOnly = filtered.filter(_.matches("^[a-zA-Z-]+$")) //only letters and -
    val terms = wordsOnly.map(w => new Term(w.toUpperCase))
    terms.foreach(addOrUpdateTerm(_))
    "Parsed " + totalWords.length + " words. (" + terms.size + " added)."
  }

  /**
   * Turns a List into an ArrayList and caches the result.
   */
  def makeCachedArrayList(key: String, in: java.util.List[Term]): java.util.ArrayList[Term] = {
    val out = new java.util.ArrayList[Term]
    out.addAll(in)
    CACHE.put(key, out)
    out
  }
}
