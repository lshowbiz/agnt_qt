package com.joymain.ng.dao.jpa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.Jfi99billLogDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.util.StringUtil;

@Repository("jfi99billLogDao")
public class Jfi99billLogDaoJpa extends GenericDaoHibernate<Jfi99billLog, Long> implements Jfi99billLogDao {

    public Jfi99billLogDaoJpa() {
        super(Jfi99billLog.class);
    }

	@Override
	public List getJfi99billLogs(Jfi99billLog jfi99billLog) {
		// TODO Auto-generated method stub
		String sql="select t from Jfi99billLog t where 1=1";
		if(!StringUtils.isEmpty(jfi99billLog.getDealId())){
			sql+=" and t.dealId='"+jfi99billLog.getDealId()+"'";
		}
		if(!StringUtils.isEmpty(jfi99billLog.getInc())){
			sql+=" and t.inc='"+jfi99billLog.getInc()+"'";
		}
		Query q = this.getSession().createQuery(sql);
        return q.list();
	}

	@Override
	public List getSuccessJfi99billLogsByConditions(String dealId) {
		
		String sql="select t from Jfi99billLog t where t.inc='1' and t.dealId='"+dealId+"'";
		
		Query q = this.getSession().createQuery(sql);
		
        return q.list();
	}
	
	public List getJfi99billLogsByMobile(Jfi99billLog jfi99billLog,String startCreateTime,String endCreateTime,int pageNum,int pageSize){

		String hql = "from Jfi99billLog jfi99billLog where 1=1";
		String userCode = jfi99billLog.getUserCode();
		if(StringUtils.isNotEmpty(userCode)){
			hql += " and userCode = '" + userCode + "'";
		}
		String dataType = jfi99billLog.getDataType();
		if(StringUtils.isNotEmpty(dataType)){
			hql += " and dataType = '" + dataType + "'";
		}

		String inc = jfi99billLog.getInc();
		if(StringUtils.isNotEmpty(inc)){
			hql += " and inc = '" + inc + "'"; 
		}

		String payResult = jfi99billLog.getPayResult();
		if(StringUtils.isNotEmpty(payResult)){
			hql += " and payResult = '" + payResult + "'";
		}
		String merchantAcctId = jfi99billLog.getMerchantAcctId();
		if(StringUtils.isNotEmpty(merchantAcctId)){
			hql += " and merchantAcctId = '" + merchantAcctId + "'";
		}

		String dealId = jfi99billLog.getDealId();
		if(StringUtils.isNotEmpty(dealId)){
			hql += " and dealId = '" + dealId + "'";
		}

		String bankDealId = jfi99billLog.getBankDealId();
		if(StringUtils.isNotEmpty(bankDealId)){
			hql += " and bankDealId = '" + bankDealId + "'";
		}

		if(StringUtils.isNotEmpty(startCreateTime)){//'yyyy-mm-dd hh24:mi:ss'
			hql += " and createTime >= to_date('" + startCreateTime+" 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')";
		}

		if(StringUtils.isNotEmpty(endCreateTime)){
			hql += " and createTime <= to_date('" + endCreateTime+" 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')";
		}
			
		hql += " order by createTime desc";
		
//		String sql="select * from JFI_99BILL_LOG";
//		List list=this.jdbcTemplate.queryForList(sql);
//		return list;
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
}
