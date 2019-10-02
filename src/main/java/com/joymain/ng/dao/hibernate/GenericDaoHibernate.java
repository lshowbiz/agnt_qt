package com.joymain.ng.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.dao.SearchException;

import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id="fooDao" class="com.joymain.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.joymain.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> Updated by jgarcia:
 *         update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements
		GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Logger log =LoggerFactory.getLogger(getClass());
	private Class<T> persistentClass;
	@Resource
	private SessionFactory sessionFactory;
	private Analyzer defaultAnalyzer;

	protected JdbcTemplate jdbcTemplate;//查询ec正式库数据源配置

	//Add BY WuCF 20140704 查询容灾库数据源配置
	protected JdbcTemplate jdbcTemplate3;
	
	protected JdbcTemplate jdbcTemplateBi;

	@Autowired
	public void setJdbcTemplateBi(JdbcTemplate jdbcTemplateBi) {
		this.jdbcTemplateBi = jdbcTemplateBi;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate3() {
		return jdbcTemplate3;
	}

	@Autowired
	public void setJdbcTemplate3(JdbcTemplate jdbcTemplate3) {
		this.jdbcTemplate3 = jdbcTemplate3;
	}
	
	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoHibernate(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
	}

	/**
	 * Constructor that takes in a class and sessionFactory for easy creation of
	 * DAO.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 * @param sessionFactory
	 *            the pre-configured Hibernate SessionFactory
	 */
	public GenericDaoHibernate(final Class<T> persistentClass,
			SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		this.sessionFactory = sessionFactory;
		defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Session getSession() throws HibernateException {
		Session sess = getSessionFactory().getCurrentSession();
		if (sess == null) {
			sess = getSessionFactory().openSession();
		}
		return sess;
	}

	@Autowired
	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Session sess = getSession();
		return sess.createCriteria(persistentClass).list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllDistinct() {
		Collection<T> result = new LinkedHashSet<T>(getAll());
		return new ArrayList<T>(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);

/*		if (entity == null) {
			log.warn("Uh oh, '" + this.persistentClass + "' object with id '"
					+ id + "' not found...");
			throw new ObjectRetrievalFailureException(this.persistentClass, id);
		}*/

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T save(T object) {
		Session sess = getSession();
		return (T) sess.merge(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(T object) {
		Session sess = getSession();
		sess.delete(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);
		sess.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		Session sess = getSession();
		Query namedQuery = sess.getNamedQuery(queryName);

		for (String s : queryParams.keySet()) {
			namedQuery.setParameter(s, queryParams.get(s));
		}

		return namedQuery.list();
	}

	@Override
	public List<T> search(String searchTerm) throws SearchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reindex() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reindexAll(boolean async) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pager<T> getPager(Class<T> clazz,
			Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查询数据库并返回第一条记录
	 * 
	 * @param sqlQuery String 指定查询的SQL语句
	 * @return CommonRecord 返回查询结果
	 * @throws AppException
	 */
	public HashMap findOneObjectBySQL(final String sqlQuery) {
		HashMap map = null;
		List recordSet = this.jdbcTemplate.queryForList(sqlQuery);
		if (recordSet != null && recordSet.size() > 0) {
			map = (HashMap) recordSet.get(0);
		}
		return map;
	}

	/**
	 * 查询数据库并返回第一条记录
	 * 
	 * @param sqlQuery String 指定查询的SQL语句
	 * @return CommonRecord 返回查询结果
	 * @throws AppException
	 */
	public List findObjectsBySQL(final String sqlQuery) {
		HashMap map = null;
		List recordSet = this.jdbcTemplate.queryForList(sqlQuery);
		if (recordSet != null && recordSet.size() > 0) {
			return recordSet;
		}
		return null;
	}
	
	/**
	 * 根据HQL语句查询并返回第一条对象
	 * 
	 * @param hqlQuery String
	 * @return Object
	 */
	public Object getObjectByHqlQuery(final String hqlQuery) {
		Query query = this.getSession().createQuery(hqlQuery);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List list= query.list();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	/**
	 * 生成查询总记录数的SQL语句
	 * 
	 * @param queryString 指定的查询语句
	 * @return 查询总记录数的SQL语句
	 */
	public String getSqlCountString(String queryString) {
		return "select count(*) from (" + queryString + ")";
	}
	/**
	 * 根据SQL语句查询并得到记录总数
	 * 
	 * @param sqlQuery String
	 * @return int
	 */
	public int getTotalCountBySQL(final String sqlQuery) {
		return this.jdbcTemplate.queryForInt(getSqlCountString(sqlQuery));
	}
	/**
	 * 将SQL语句转化为分页的SQL语句
	 * 
	 * @param queryString 指定的SQL语句
	 * @param offset 需要读取的第一条数据的偏移量
	 * @param limit 需要读取的数据数
	 * @return String 生成的SQL语句
	 */
	public String getLimitString(String queryString, int offset, int limit) {
		queryString = queryString.trim();

		
		StringBuffer pagingSelect = new StringBuffer(queryString.length() + 100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(queryString);
		pagingSelect.append(" ) row_ where rownum <= " + (offset + limit) + ") where rownum_ > " + offset);

		return pagingSelect.toString();
	}
	/**
	 * 根据查询语句及页码,分页大小查询数据库并返回查询结果所包含的业务对象集合
	 * 
	 * @param sqlQuery 指定查询的SQL语句
	 * @param pager 分页对象
	 * @return 返回查询结果包含的业务对象集合
	 * @throws AppException
	 */
	public List findObjectsBySQL(final String sqlQuery, final GroupPage page) throws AppException {

		try {
			int totalCount = this.getTotalCountBySQL(sqlQuery);
			page.setPagecount(totalCount);
			if(page.getPagenum()>page.getPages()){
				return new ArrayList();
			}
			String limitString = sqlQuery;
			if (page != null && page.getPagesize() > 0) {

				//pager.calc();
				
				limitString = getLimitString(sqlQuery, (page.getPagenum()-1)  * page.getPagesize(), page.getPagesize());
				
			}
			List recordSet =  this.jdbcTemplate.queryForList(limitString);

	/*		List recordSet = (List) this.jdbcTemplate.query(limitString, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					return buildSqlResultList(rs);
				}
			});*/
			return recordSet;
		} catch (DataAccessException e) {
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}
	public List findObjectsBySQL3(final String sqlQuery, final GroupPage page) throws AppException {

		try {
			int totalCount = this.getTotalCountBySQL(sqlQuery);
			page.setPagecount(totalCount);
			if(page.getPagenum()>page.getPages()){
				return new ArrayList();
			}
			String limitString = sqlQuery;
			if (page != null && page.getPagesize() > 0) {

				//pager.calc();
				
				limitString = getLimitString(sqlQuery, (page.getPagenum()-1)  * page.getPagesize(), page.getPagesize());
				
			}
			List recordSet =  this.jdbcTemplate3.queryForList(limitString);

	/*		List recordSet = (List) this.jdbcTemplate.query(limitString, new ResultSetExtractor() {
				public Object extractData(ResultSet rs) throws SQLException {
					return buildSqlResultList(rs);
				}
			});*/
			return recordSet;
		} catch (DataAccessException e) {
			throw new AppException("执行查询失败, 查询语句为: " + sqlQuery, e);
		}
	}
	/**
	 * 根据查询语句及页码,分页大小查询数据库并返回查询结果所包含的业务对象集合
	 * 
	 * @param hqlQuery 指定查询的HQL语句
	 * @param pager 分页对象
	 * @return 返回查询结果包含的业务对象集合
	 * @throws AppException
	 */
	public List findObjectsByHQL(final String totalHql,final String hqlQuery, final GroupPage page) throws AppException {

		try {
			int totalCount = Integer.parseInt(this.getSession().createQuery(totalHql).uniqueResult().toString());
			page.setPagecount(totalCount);
			if(page.getPagenum()>page.getPages()){
				return new ArrayList();
			}
			List list= new ArrayList();
			if (page != null && page.getPagesize() > 0) {
				Query query = this.getSession().createQuery(hqlQuery);
				query.setFirstResult((page.getPagenum()-1)  * page.getPagesize());
				query.setMaxResults(page.getPagesize());
				list= query.list();
			}
			return list;
		} catch (DataAccessException e) {
			throw new AppException("执行查询失败, 查询语句为: " + hqlQuery, e);
		}
	}
	
	/**
	 * 根据SQL查询所返回的RS生成对应的List
	 * 
	 * @param rs
	 * @return
	 */
/*	public List<HashMap<String, String>> buildSqlResultList(ResultSet rs) {
		List<HashMap<String, String>> presidents = new ArrayList<HashMap<String, String>>();
		try {
			ResultSetMetaData mData = rs.getMetaData();
			int fieldCount = mData.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> president = new HashMap<String, String>();
				for (int i = 1; i <= fieldCount; i++) {
					String fieldName = mData.getColumnName(i);
					int fieldType = mData.getColumnType(i);
					String fieldValue = null;
					switch (fieldType) {
						case Types.DATE:
						case Types.TIME:
						case Types.TIMESTAMP:
							fieldValue = rs.getString(i);
							if (fieldValue == null) {
								fieldValue = "";
							} else if (fieldValue.indexOf(".") >= 0) {// oracle日期处理
								fieldValue = fieldValue.substring(0, fieldValue.indexOf("."));
							}
							break;
						default:
							fieldValue = rs.getString(i);
							if (fieldValue == null)
								fieldValue = "";
							break;
					}

					president.put(fieldName.toLowerCase(), fieldValue);
				}
				presidents.add(president);
			} // while
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {  
			JdbcUtils.closeResultSet(rs);
		}
		return presidents;
	}*/
}
