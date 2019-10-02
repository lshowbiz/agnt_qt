package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JbdUserCardList;
import com.joymain.ng.dao.JbdUserCardListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdUserCardListDao")
public class JbdUserCardListDaoHibernate extends GenericDaoHibernate<JbdUserCardList, Long> implements JbdUserCardListDao {

    public JbdUserCardListDaoHibernate() {
        super(JbdUserCardList.class);
    }
    
    

	public JbdUserCardList getJbdUserCardListByUserCodeAndWeek(String userCode, Integer wweek) {
		return (JbdUserCardList) this.getObjectByHqlQuery("from JbdUserCardList where userCode='"+userCode+"' and wweek="+wweek);
	}

	public JbdUserCardList getPreviousJbdUserCardList(String userCode,Integer wweek) {
		return (JbdUserCardList) this.getObjectByHqlQuery("from JbdUserCardList where userCode='"+userCode+"' and wweek < "+wweek +" order by wweek desc");
	}

	public List getJbdUserCardListByRange(Integer sweek, Integer eweek,String userCode) {
		return this.getSession().createQuery("from JbdUserCardList where userCode='"+userCode+"' and wweek>"+sweek+" and wweek<="+eweek).list();
	}

	public List getJbdUserCardListByUserCode(String userCode) {
		return this.getSession().createQuery("from JbdUserCardList where userCode='"+userCode+"' order by wweek ").list();
	}

	public List getNextJbdUserCardList(String userCode,Integer wweek) {
		return this.getSession().createQuery("from JbdUserCardList where userCode='"+userCode+"' and wweek > "+wweek +" order by wweek asc").list();
	}
}
