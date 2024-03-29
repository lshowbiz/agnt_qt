package com.joymain.ng.dao.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.dao.SearchException;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.FlushMode;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 *
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.joymain.ng.dao.hibernate.GenericDaoJpaHibernate"&gt;
 *          &lt;constructor-arg value="com.joymain.ng.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 *      Updated by jgarcia: update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoJpa<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String PERSISTENCE_UNIT_NAME = "ApplicationEntityManager";

    /**
     * Entity manager, injected by Spring using @PersistenceContext annotation on setEntityManager()
     */
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> persistentClass;
    private Analyzer defaultAnalyzer;

    protected JdbcTemplate jdbcTemplate;
    
    
    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
    @Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing or using dependency injection.
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoJpa(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing or using dependency injection.
     * @param persistentClass the class type you'd like to persist
     * @param entityManager the configured EntityManager for JPA implementation.
     */
    public GenericDaoJpa(final Class<T> persistentClass, EntityManager entityManager) {
        this.persistentClass = persistentClass;
        this.entityManager = entityManager;
        
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }

    public EntityManager getEntityManager() {
    	log.info("flush mode >>"+this.entityManager.getFlushMode());
        return this.entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return this.entityManager.createQuery(
                "select obj from " + this.persistentClass.getName() + " obj")
                .getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection result = new LinkedHashSet(getAll());
        return new ArrayList(result);
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);

//        if (entity == null) {
//            String msg = "Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...";
//            log.warn(msg);
//            throw new EntityNotFoundException(msg);
//        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        T entity = this.entityManager.find(this.persistentClass, id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return this.entityManager.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        this.entityManager.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        this.entityManager.remove(this.get(id));
    }

    public List<T> search(String searchTerm) throws SearchException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        org.apache.lucene.search.Query qry;
        try {
            qry = HibernateSearchJpaTools.generateQuery(searchTerm, this.persistentClass, entityManager, defaultAnalyzer);
        } catch (ParseException ex) {
            throw new SearchException(ex);
        }
        org.hibernate.search.jpa.FullTextQuery hibQuery = fullTextEntityManager.createFullTextQuery(qry, this.persistentClass);
        // filter search results by owner.id value:
        // hibQuery.enableFullTextFilter("owned").setParameter("ownerId", owner.getId().toString());
        return hibQuery.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        HibernateSearchJpaTools.reindex(persistentClass, getEntityManager());
    }


    /**
     * {@inheritDoc}
     */
    public void reindexAll(boolean async) {
        HibernateSearchJpaTools.reindexAll(async, getEntityManager());
    }
	
	public  Pager<T> getPager(Class<T> clazz,
			Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		log.info("GenericDaoJpa..pager");
		return null;
	}
}
