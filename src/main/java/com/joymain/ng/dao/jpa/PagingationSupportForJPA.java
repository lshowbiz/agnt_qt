package com.joymain.ng.dao.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.google.common.collect.Lists;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

public class PagingationSupportForJPA<T, PK extends Serializable> extends
		GenericDaoJpa<T, PK> {

	public PagingationSupportForJPA(Class<T> persistentClass) {
		super(persistentClass);
		// TODO Auto-generated constructor stub
	}

	public PagingationSupportForJPA(Class<T> persistentClass,
			EntityManager entityManager) {
		super(persistentClass, entityManager);
		// TODO Auto-generated constructor stub
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			int pageSize, Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray,
			LinkedHashMap<String, String> orderBy) {
		String queryForListHQL = getQueryString(clazz, conditions, orderBy);
		return this.queryForPaginationList(currentPage, pageSize,
				queryForListHQL, preparedParameterArray);
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			int pageSize, String queryForListHQL,
			Object[] preparedParameterArray) {
		int dataCount = this.getTotalObjects(queryForListHQL,
				preparedParameterArray);
		Pager<T> Pager = new Pager<T>(dataCount, currentPage, pageSize);
		Pager.setPageList(queryForList(queryForListHQL, preparedParameterArray,
				Pager.getFirstResult(), Pager.getEndResult()));
		return Pager;
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			Class<T> clazz, LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray,
			LinkedHashMap<String, String> orderBy) {
		String queryForListHQL = this
				.getQueryString(clazz, conditions, orderBy);
		return this.queryForPaginationList(currentPage, queryForListHQL,
				preparedParameterArray);
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			String queryForListHQL, Object[] preparedParameterArray) {
		int totalObjects = getTotalObjects(queryForListHQL,
				preparedParameterArray);
		Pager<T> Pager = new Pager<T>(totalObjects, currentPage);
		Pager.setPageList(queryForList(queryForListHQL, preparedParameterArray,
				Pager.getFirstResult(), Pager.getEndResult()));
		return Pager;
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			Class<T> clazz, LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray) {
		return queryForPaginationList(currentPage, clazz, conditions,
				preparedParameterArray, null);
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			Class<T> clazz) {
		return queryForPaginationList(currentPage, clazz, null, null);
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			int pageSize, Class<T> clazz) {
		return queryForPaginationList(currentPage, pageSize, clazz, null, null);
	}

	public final <T> Pager<T> queryForPaginationList(int currentPage,
			int pageSize, Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			Object[] preparedParameterArray) {
		return queryForPaginationList(currentPage, pageSize, clazz, conditions,
				preparedParameterArray, null);
	}

	private final int getTotalObjects(String queryForListHQL,
			Object[] preparedParameterArray) {

		StringBuilder countHQLBuilder = new StringBuilder(" select count(*) ");
		try {
			countHQLBuilder.append(queryForListHQL.substring(queryForListHQL
					.toLowerCase().indexOf("from")));
		} catch (RuntimeException ex) {
			throw new BadSqlGrammarException("SQL  :  ", queryForListHQL, null);
		}
		return queryForInt(countHQLBuilder.toString(), preparedParameterArray);
	}

	public final int queryForInt(String queryIntHQL, Object[] parameterArray) {
		Query query = getEntityManager().createQuery(queryIntHQL);
		setQueryParameterValues(parameterArray, query);
		int result = Integer.parseInt(query.getSingleResult().toString());
		return result;
	}

	final List queryForList(String queryHQL, Object[] parameterArray,
			int firstResult, int maxResult) {
		Query query = this.getEntityManager().createQuery(queryHQL);
		setQueryParameterValues(parameterArray, query);
		if (firstResult >= 0)
			query.setFirstResult(firstResult);
		if (maxResult > 0)
			query.setMaxResults(maxResult);
		return query.getResultList();
	}

	final <T> String getQueryString(Class<T> clazz,
			LinkedHashMap<String, String> conditions,
			LinkedHashMap<String, String> orderBy) {
		StringBuilder queryBuilder = new StringBuilder("  select o from  ");
		queryBuilder.append(getEntityFullName(clazz)).append(" o ")
				.append(getCondtitons(conditions)).append(getOrderBy(orderBy));
		return queryBuilder.toString();
	}

	final <T> String getEntityFullName(Class<T> clazz) {
		return clazz.getName();
	}

	final String getCondtitons(LinkedHashMap<String, String> conditions) {
		StringBuilder conditionsBuilder = new StringBuilder("  where 1=1  ");
		if (conditions == null || conditions.size() == 0) {
			return conditionsBuilder.toString();
		}
		Set<String> conditonFields = conditions.keySet();
		for (String conditionField : conditonFields) {
			conditionsBuilder.append(" and o.");
			conditionsBuilder.append(conditionField);
			conditionsBuilder.append(" ");
			conditionsBuilder.append(conditions.get(conditionField));
			conditionsBuilder.append("  ?  ");
		}
		return conditionsBuilder.toString();
	}

	final String getOrderBy(LinkedHashMap<String, String> orderBy) {
		if (orderBy == null || orderBy.size() == 0)
			return "";
		StringBuilder orderByBuilder = new StringBuilder(" order by ");
		Set<String> orderFields = orderBy.keySet();
		for (String orderField : orderFields) {
			orderByBuilder.append(" o.").append(orderField).append(" ")
					.append(orderBy.get(orderField)).append(",");
		}
		orderByBuilder.delete(orderByBuilder.length() - 1,
				orderByBuilder.length());
		return orderByBuilder.toString();
	}

	final void setQueryParameterValues(Object[] parameterArray, Query query) {
		if (parameterArray == null || parameterArray.length == 0)
			return;
		for (int i = 0; i < parameterArray.length; i++) {
			query.setParameter(i + 1, parameterArray[i]);
		}
	}

	/************** new **************/

	public final Pager<T> getPager(Class<T> clazz,
			final Collection<SearchBean> searchBeans,
			final Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		Long totalObjects = getTotals(clazz, searchBeans);
		Pager<T> pager = new Pager<T>(totalObjects.intValue(), currentPage,
				pageSize);
		pager.setPageList(queryForList(clazz, searchBeans, OrderBys,
				pager.getFirstResult(), pager.getPageSize()));
		log.info("Pager="+pager);
		return pager;
	}

	public final Long getTotals(Class<T> clazz,
			final Collection<SearchBean> searchBeans) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cql = builder.createQuery(Long.class);
		Root<T> root = cql.from(clazz);
		cql.select(builder.count(root));
		Predicate predicate = creatPredicate(root, searchBeans, builder);
		cql.where(predicate);
		TypedQuery<Long> query = this.getEntityManager().createQuery(cql);
		return query.getSingleResult();

	}

	public final List<T> queryForList(Class<T> clazz,
			final Collection<SearchBean> searchBeans,
			final Collection<OrderBy> OrderBys, int firstResult, int maxResult) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = builder.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.select(root);
		Predicate predicate = creatPredicate(root, searchBeans, builder);
		cq.where(predicate);
		cq.orderBy(createOrders(root, OrderBys, builder));
		TypedQuery<T> query = this.getEntityManager().createQuery(cq);

		if (firstResult >= 0)
			query.setFirstResult(firstResult);
		if (maxResult > 0)
			query.setMaxResults(maxResult);
		return query.getResultList();
	}

	// 构造orders
	final List<Order> createOrders(Root<T> root,
			final Collection<OrderBy> OrderBys, CriteriaBuilder builder) {
		List<Order> orders = Lists.newArrayList();
		for (OrderBy orderBy : OrderBys) {
			switch (orderBy.direction) {

			case ASC:
				orders.add(builder.asc(root.get(orderBy.columnOrProperty)));
				break;
			case DESC:
				orders.add(builder.desc(root.get(orderBy.columnOrProperty)));
				break;
			}

		}
		return orders;
	}

	// 构造Predicate
	final Predicate creatPredicate(Root<T> root,
			final Collection<SearchBean> searchBeans, CriteriaBuilder builder) {

		List<Predicate> predicates = Lists.newArrayList();
		for (SearchBean searchBean : searchBeans) {
			String[] names = StringUtils.split(searchBean.fieldName, ".");
			Path expression = root.get(names[0]);
			for (int i = 1; i < names.length; i++) {
				expression = expression.get(names[i]);
			}

			// logic operator
			switch (searchBean.operator) {
			case EQ:
				predicates.add(builder.equal(expression, searchBean.value));
				break;
			case LIKE:
				predicates.add(builder.like(expression, "%" + searchBean.value
						+ "%"));
				break;
			case GT:
				predicates.add(builder.greaterThan(expression,
						(Comparable) searchBean.value));
				break;
			case LT:
				predicates.add(builder.lessThan(expression,
						(Comparable) searchBean.value));
				break;
			case GTE:
				predicates.add(builder.greaterThanOrEqualTo(expression,
						(Comparable) searchBean.value));
				break;
			case LTE:
				predicates.add(builder.lessThanOrEqualTo(expression,
						(Comparable) searchBean.value));
				break;
			case NEQ:
				predicates.add(builder.notEqual(expression,
						(Comparable) searchBean.value));
				break;

			}
			if (predicates.size() > 0) {
				return builder.and(predicates.toArray(new Predicate[predicates
						.size()]));
			}
		}
		return builder.conjunction();
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
	 * 根据HQL语句查询并返回第一条对象
	 * 
	 * @param hqlQuery String
	 * @return Object
	 */
	public Object getObjectByHqlQuery(final String hqlQuery) {
		Query query = this.getEntityManager().createQuery(hqlQuery);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List list= query.getResultList();
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
