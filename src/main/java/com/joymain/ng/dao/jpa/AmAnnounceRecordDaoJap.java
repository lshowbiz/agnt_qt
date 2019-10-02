package com.joymain.ng.dao.jpa;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import com.joymain.ng.dao.AmAnnounceRecordDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.AmAnnounceRecord;

@Repository("amAnnounceRecordDao")
public class AmAnnounceRecordDaoJap  extends GenericDaoHibernate<AmAnnounceRecord, String> implements AmAnnounceRecordDao {

	public AmAnnounceRecordDaoJap() {
		super(AmAnnounceRecord.class);
	}

	@Override
	public AmAnnounceRecord getRecordByUserNo(String userNo, String aaNo) {
		String hql= "from AmAnnounceRecord a where 1=1 ";
		if(StringUtils.isNotBlank(userNo)){
			hql += " and a.userNo='"+userNo+"' " ;
		}
		if(StringUtils.isNotBlank(aaNo)){
			hql += " and a.amAnnounce.aaNo="+aaNo+" ";
		}
		
		return (AmAnnounceRecord)this.getObjectByHqlQuery(hql);
	}

	

}
