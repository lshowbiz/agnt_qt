package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.dao.PdExchangeOrderBackDao;
import com.joymain.ng.dao.PdExchangeOrderDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JpmProduct;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.model.PdExchangeOrderBack;

import org.springframework.stereotype.Repository;

@Repository("pdExchangeOrderBackDao")
public class PdExchangeOrderBackDaoHibernate extends GenericDaoHibernate<PdExchangeOrderBack, Long> implements PdExchangeOrderBackDao  {

	public PdExchangeOrderBackDaoHibernate() {
        super(PdExchangeOrderBack.class);
    }

	@Override
	public void savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack) {
		this.getSession().saveOrUpdate(pdExchangeOrderBack);
		
	}

	@Override
	public List<PdExchangeOrderBack> getPdExchangeOrderBacks() {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().queryForList("from PdExchangeOrderBack", PdExchangeOrderBack.class);
	}

	@Override
	public PdExchangeOrderBack getPdExchangeOrderBack(Long uniNo) {
		// TODO Auto-generated method stub
		return (PdExchangeOrderBack) this.getSession().get(PdExchangeOrderBack.class, uniNo);
	}

	@Override
	public void removePdExchangeOrderBack(Long uniNo) {
		
		this.getSession().delete(getPdExchangeOrderBack(uniNo));
		
	}
	
}
