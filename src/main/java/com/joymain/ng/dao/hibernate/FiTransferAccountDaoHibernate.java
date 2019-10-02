
package com.joymain.ng.dao.hibernate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiTransferAccountDao;
import com.joymain.ng.model.FiTransferAccount;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("fiTransferAccountDao")
public class FiTransferAccountDaoHibernate extends GenericDaoHibernate<FiTransferAccount, Long> implements FiTransferAccountDao {

    public FiTransferAccountDaoHibernate() {
		super(FiTransferAccount.class);
		// TODO Auto-generated constructor stub
	}

	//获取单日转账总额
	@Override
    public BigDecimal getSumTransferValueByTransferCode(final String transferCode){
    	
    	String hql = "select sum(fiTransferAccount.money) from FiTransferAccount fiTransferAccount where fiTransferAccount.transferUserCode='"+transferCode+"'";
    	
    	//获取当日起始日期
    	Calendar currentDate = new GregorianCalendar();    
    	  
    	currentDate.set(Calendar.HOUR_OF_DAY, 0);   
    	currentDate.set(Calendar.MINUTE, 0);   
    	currentDate.set(Calendar.SECOND, 0);   
    	Date todayStartTime = (Date)currentDate.getTime().clone();  
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	
    	hql += " and fiTransferAccount.createTime>=to_date('" + df.format(todayStartTime) + "','yyyy-mm-dd hh24:mi:ss')";
    	
    	if(this.getObjectByHqlQuery(hql) == null)
    		return new BigDecimal(0);
    	
    	return (BigDecimal) this.getObjectByHqlQuery(hql);
    }
	
	@Override
	public List getFiTransferAccountListByUserCode(String userCode,String dealStartTime,String dealEndTime) {
		
		//String sql="select * from FI_TRANSFER_ACCOUNT where TRANSFER_USER_CODE='"+userCode+"' or DESTINATION_USER_CODE='"+userCode+"'";
		String sql="select * from FI_TRANSFER_ACCOUNT where TRANSFER_USER_CODE='"+userCode+"'";
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and CREATE_TIME>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and CREATE_TIME<=to_date('" + dealEndTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by TA_ID desc";
		
		List list=this.jdbcTemplate.queryForList(sql);

		return list;
	}
	
	@Override
	public List getFiTransferAccountListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime) {
		
		//String sql="select * from FI_TRANSFER_ACCOUNT where TRANSFER_USER_CODE='"+userCode+"' or DESTINATION_USER_CODE='"+userCode+"'";
		String sql="select * from FI_TRANSFER_ACCOUNT where TRANSFER_USER_CODE='"+userCode+"'";
		if(!StringUtil.isEmpty(dealStartTime)){
			sql+=" and CREATE_TIME>=to_date('" + dealStartTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		if(!StringUtil.isEmpty(dealEndTime)){
			sql+=" and CREATE_TIME<=to_date('" + dealEndTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		sql+=" order by TA_ID desc";
		
//		List list=this.jdbcTemplate.queryForList(sql);
		List list = findObjectsBySQL(sql, page);

		return list;
	}

    //根据ID查状态
	public Integer getTransferAccountStatus(Long taId) {
		
		String hql = "select status from FiTransferAccount fiTransferAccount where fiTransferAccount.taId="+taId;
		
		return (Integer) this.getObjectByHqlQuery(hql);
	}

	/**
	 * Add By WuCF 20160824 保存转账信息并返回主键唯一值 
	 * @param fiTransferAccount
	 * @return
	 */
	public String saveFiTransferAccount(FiTransferAccount fiTransferAccount) {
		FiTransferAccount returnFtfa = this.save(fiTransferAccount);
		return String.valueOf(returnFtfa.getTaId());
	}
}
