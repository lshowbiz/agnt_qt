package com.joymain.ng.dao.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.dao.PdExchangeOrderDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository("pdExchangeOrderDao")
public class PdExchangeOrderDaoHibernate extends GenericDaoHibernate<PdExchangeOrder, String> implements PdExchangeOrderDao {

    public PdExchangeOrderDaoHibernate() {
        super(PdExchangeOrder.class);
    }

	@Override
	public List<PdExchangeOrder> getPdExchangeOrders() {
		// TODO Auto-generated method stub
		return getJdbcTemplate().queryForList("from PdExchangeOrder",PdExchangeOrder.class);
	}

	@Override
	public PdExchangeOrder getPdExchangeOrder(String eoNo) {
		// TODO Auto-generated method stub
		PdExchangeOrder pdExchangeOrder = (PdExchangeOrder) this.getSession().get(PdExchangeOrder.class, eoNo);
        /*if (pdExchangeOrder == null) {
            log.warn("uh oh, pdExchangeOrder with eoNo '" + eoNo + "' not found...");
            throw new ObjectRetrievalFailureException(PdExchangeOrder.class, eoNo);
        }*/

        return pdExchangeOrder;
	}

	@Override
	public void savePdExchangeOrder(PdExchangeOrder pdExchangeOrder) {
		this.getSession().saveOrUpdate(pdExchangeOrder);
		
	}

	@Override
	public void removePdExchangeOrder(String eoNo) {
		this.getSession().delete(getPdExchangeOrder(eoNo));
		
	}

	public List getPdExchangeOrdersByCrm(CommonRecord crm, GroupPage page) {
		// TODO Auto-generated method stub
	    	
    	String totalHql = "select count(*) from PdExchangeOrder pdExchangeOrder where 1=1";
    	String hql = "from PdExchangeOrder pdExchangeOrder where 1=1";
    	
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			totalHql += " and pdExchangeOrder.createUsrCode ='"+userCode+"' ";
			hql += " and pdExchangeOrder.createUsrCode ='"+userCode+"' ";
		}
		
		String eoNo = crm.getString("eoNo", "");
		if (!StringUtils.isEmpty(eoNo)) {
			totalHql += " and pdExchangeOrder.eoNo ='"+eoNo+"' ";
			hql += " and pdExchangeOrder.eoNo ='"+eoNo+"' ";
		}
		String orderNo = crm.getString("orderNo", "");
		if (!StringUtils.isEmpty(orderNo)) {
			totalHql += " and pdExchangeOrder.orderNo ='"+orderNo+"' ";
			hql += " and pdExchangeOrder.orderNo ='"+orderNo+"' ";
		}
		String isPay = crm.getString("isPay", "");
		if (!StringUtils.isEmpty(isPay)) {
			//modify by fu 2016-05-19 是否支付修改
			if("Y".equals(isPay)){
				totalHql += " and pdExchangeOrder.isPay ='Y' ";
				hql += " and pdExchangeOrder.isPay ='Y' ";
			}else{
				totalHql += " and ( pdExchangeOrder.isPay ='N' or pdExchangeOrder.isPay is null )";
				hql += " and ( pdExchangeOrder.isPay ='N' or pdExchangeOrder.isPay is null ) ";
			}
			//modify by fu 2016-05-19 是否支付修改

		}
		
		String startLogTime = crm.getString("startLogTime", "");
		if (!StringUtils.isEmpty(startLogTime)) {
			totalHql += " and pdExchangeOrder.createTime >=to_date('"+startLogTime+"','yyyy-mm-dd hh24:mi:ss') ";
			hql += " and pdExchangeOrder.createTime >=to_date('"+startLogTime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		String endLogTime = crm.getString("endLogTime", "");
		if (!StringUtils.isEmpty(endLogTime)) {
			totalHql += " and pdExchangeOrder.createTime <=to_date('"+endLogTime+"','yyyy-mm-dd hh24:mi:ss') ";
			hql += " and pdExchangeOrder.createTime <=to_date('"+endLogTime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode)) {
			totalHql += " and pdExchangeOrder.companyCode ='"+companyCode+"' ";
			hql += " and pdExchangeOrder.companyCode ='"+companyCode+"' ";
		}
		String selfReplacement = crm.getString("selfReplacement", "");
		if (!StringUtils.isEmpty(selfReplacement)) {
			totalHql += " and pdExchangeOrder.selfReplacement ='"+selfReplacement+"' ";
			hql += " and pdExchangeOrder.selfReplacement ='"+selfReplacement+"' ";
		}
		
		totalHql += " and pdExchangeOrder.cancelExchange is null order by createTime desc ";
		hql += " and pdExchangeOrder.cancelExchange is null order by createTime desc ";
		
		System.out.println("hql==="+hql);
		return this.findObjectsByHQL(totalHql, hql, page);
	}

	@Override
	public List getSumGroupByAst(CommonRecord crm) {
		// TODO Auto-generated method stub
		return null;
	
	}
	
	/**
     * 自助换货单的支付状态修改
     * @author fu 2016-04-26
     * @param eoNo 换货单号
     * @return boolean true自助换货单的修改支付状态成功；false自助换货单的修改支付状态失败
     */
	public boolean reSetIsPay(String eoNo){
		String sql = " update pd_exchange_order set is_pay='Y' where eo_no='"+eoNo+"' ";
		int a = this.jdbcTemplate.update(sql);
		if(a>=1){
			return true;//自助换货单的修改支付状态成功
		}else{
			return false;//自助换货单的修改支付状态失败
		}
	}

	@Override
	public String getOrderFlagByEoNo(String eoNo) {
		String sql = "select order_flag from pd_exchange_order where eo_no='" + eoNo + "'";
		return this.jdbcTemplate.queryForList(sql).get(0).get("order_flag").toString();
	}
	
	/**
	 * 根据会员编号获取会员的已审核、正常状况、需要支付且未支付的自助换货单
	 * @author fu 2016-04-25
	 * @param userCode 会员编号
	 * @param startIndex 开始查询行数
	 * @param endIndex 结束查询行数
	 * @return list
	 */
	public List getPdExchangeOrderByUsercode(String userCode, Integer startIndex, Integer endIndex){
		//SELF_REPLACEMENT=Y（表示是自助换货单）并且ORDER_FLAG=1（表示已审核）并且正常状况（CANCEL_EXCHANGE is null）
    	//A换货单需要支付，表pd_exchange_order的NEED_PAY=Y（表示需要支付）并且已经支付IS_PAY=Y（表示已支付）
    	//已审核、正常状况、需要支付且未支付的自助换货单才允许支付
		//CUSTOMER_CODE
		if(!StringUtil.isEmpty(userCode)){
			String sql = " select eo_no,CREATE_TIME from pd_exchange_order where CUSTOMER_CODE='"+userCode+"' and SELF_REPLACEMENT='Y' and ORDER_FLAG=1 and NEED_PAY='Y' " +
					" and ROWNUM>='"+startIndex+"' AND ROWNUM<= '"+endIndex+"' and (IS_PAY='N' or IS_PAY is null)  and CANCEL_EXCHANGE is null ";
			List list = this.jdbcTemplate.queryForList(sql);
			if(null!=list && list.size()>0){
				return list;
			}else{
				return null;
			}
		}
		
		return null;
		
	}

	public List getPdExchangeOrdersByCrmSql(CommonRecord crm, GroupPage page){
		String userCode = crm.getString("userCode", "");
		String sql=" select o.member_order_no,a.eo_no,a.pv_amt_ex,a.amount_ex,a.create_time,o.status,o.is_pay,o.self_help_exchange,o.exchange_flag ,"
				+ "o.mo_id,a.order_flag,a.cancel_exchange,a.self_replacement,a.need_pay,a.is_pay as pd_is_pay,a.need_pay_amount from jpo_member_order o "
				+ "left join PD_EXCHANGE_ORDER a on a.order_no=o.member_order_no and a.create_usr_code='"+userCode+"' "
				+ "and a.self_replacement='Y' ";
		
		sql+="  where o.order_type<>'32' and o.status='2' and a.cancel_exchange is null ";
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode)) {
			sql+=" and o.company_code='"+companyCode+"'";
		}
		String self_help_exchange = crm.getString("selfReplacement", "");
		if (!StringUtils.isEmpty(self_help_exchange)) {
			sql+=" and o.self_help_exchange='"+self_help_exchange+"' ";
		}
		if (!StringUtils.isEmpty(userCode)) {
			sql+=" and o.user_code='"+userCode+"' ";
		}
		String eoNo = crm.getString("eoNo", "");
		if (!StringUtils.isEmpty(eoNo)) {
			sql+=" and a.eo_no='"+eoNo+"'";
		}
		String orderNo = crm.getString("orderNo", "");
		if (!StringUtils.isEmpty(orderNo)) {
			sql+=" and o.member_order_no='"+orderNo+"'";
		}

		String isPay = crm.getString("isPay", "");
		if (!StringUtils.isEmpty(isPay)) {
			if("Y".equals(isPay)){
				sql += " and a.is_pay ='Y' ";
			}else{
				sql += " and ( a.is_pay ='N' or a.is_pay is null ) ";
			}
		}
		
		
		String startLogTime = crm.getString("startLogTime", "");
		if (!StringUtils.isEmpty(startLogTime)) {
			sql += " and a.create_time >=to_date('"+startLogTime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		String endLogTime = crm.getString("endLogTime", "");
		if (!StringUtils.isEmpty(endLogTime)) {
			sql += " and a.create_time <=to_date('"+endLogTime+"','yyyy-mm-dd hh24:mi:ss') ";
		}
		
		
		sql+=" order by a.create_time desc";
		return this.findObjectsBySQL(sql, page);
	}
	
}
