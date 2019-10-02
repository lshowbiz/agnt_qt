package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpoMovieDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JpoMovie;

@Repository("jpoMovieDao")
public class JpoMovieDaoJpa extends GenericDaoHibernate<JpoMovie,Long> implements JpoMovieDao {

	public JpoMovieDaoJpa(){
		super(JpoMovie.class);
	}
	
	public JpoMovieDaoJpa(Class<JpoMovie> persistentClass) {
		super(persistentClass);
	}
	
	@Override
	public List<JpoMovie> findMovieByName(char status) {
		String hql = "from JpoMovie m where m.status=? and m.maccount not like 'UA%' order by m.maccount";
		Query query = this.getSession().createQuery(hql);
		query.setCharacter(0, status);
		
		return query.list();
	}

	@Override
	public JpoMovie getMovieByOrderNo(String orderNo) {
		String hql = "from JpoMovie m where m.orderNo=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, orderNo);
		
		List<JpoMovie> list = query.list();
		if(list !=null && list.size()>0)
			return list.get(0);	
		else
			return null;
	}

}
