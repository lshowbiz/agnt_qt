package com.joymain.ng.dao.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.joymain.ng.model.FiTransferFundbook;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.FiTransferFundbookDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("fiTransferFundbookDao")
public class FiTransferFundbookDaoHibernate extends GenericDaoHibernate<FiTransferFundbook, Long> implements FiTransferFundbookDao {

    public FiTransferFundbookDaoHibernate() {
        super(FiTransferFundbook.class);
    }

	@Override
	public List getFiTransferFundbookListByUserCodePage(GroupPage page,
			String userCode, String dealStartTime, String dealEndTime) {
		
		String sql="select * from FI_TRANSFER_FUNDBOOK where TRANSFER_USER_CODE='"+userCode+"'";
		
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and CREATE_TIME>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and CREATE_TIME<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by TA_ID desc";
		
		List list = findObjectsBySQL(sql, page);

		return list;
	}

	/**
	 * 保存产业基金转账申请单，并返回ID
	 * @param fiTransferFundbook
	 * @return
	 */
	@Override
	public Long saveFiTransferFundbookGetId(
			FiTransferFundbook fiTransferFundbook) {
		
		return (Long)this.getSession().save(fiTransferFundbook);
	}
}
