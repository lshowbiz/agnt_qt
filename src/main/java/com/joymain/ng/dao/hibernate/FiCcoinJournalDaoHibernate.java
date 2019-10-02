package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.FiCcoinJournal;
import com.joymain.ng.dao.FiCcoinJournalDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("fiCcoinJournalDao")
public class FiCcoinJournalDaoHibernate extends GenericDaoHibernate<FiCcoinJournal, Long> implements FiCcoinJournalDao {

    public FiCcoinJournalDaoHibernate() {
        super(FiCcoinJournal.class);
    }
    
    /**
	 * 获取用户对应的最后一条存折记录
	 * @param userCode
	 * @return
	 */
	public FiCcoinJournal getLastFiCcoinJournal(final String userCode) {
		return (FiCcoinJournal) this.getObjectByHqlQuery("from FiCcoinJournal where userCode='" + userCode
		        + "' order by journalId desc");
	}
}
