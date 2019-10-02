package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.FiSecurityDepositJournal;
import com.joymain.ng.dao.FiSecurityDepositJournalDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiSecurityDepositJournalDao")
public class FiSecurityDepositJournalDaoHibernate extends GenericDaoHibernate<FiSecurityDepositJournal, Long> implements FiSecurityDepositJournalDao {

    public FiSecurityDepositJournalDaoHibernate() {
        super(FiSecurityDepositJournal.class);
    }

	@Override
	public List<FiSecurityDepositJournal> getFiSecurityDepositJournalsByUserCode(
			String userCode) {
		
		String hql="from FiSecurityDepositJournal where userCode='"+userCode+"' order by journalId desc";
		return this.getSession().createQuery(hql).list();
	}
}
