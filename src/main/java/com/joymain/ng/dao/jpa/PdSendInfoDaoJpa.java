package com.joymain.ng.dao.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.mortbay.log.Log;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.PdSendInfoDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.PdPhoneProduct;
import com.joymain.ng.model.PdPhoneSend;
import com.joymain.ng.model.PdPhoneSendInfo;
import com.joymain.ng.model.PdSendInfo;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;

@Repository("pdSendInfoDao")
public class PdSendInfoDaoJpa extends GenericDaoHibernate<Linkman, Long> implements PdSendInfoDao {

    public PdSendInfoDaoJpa() {
        super(Linkman.class);
    }

    /**
	 * 会员发货单管理
	 * Add By WuCF 2014-01-14
	 * 分页
	 * @param page：分页信息
	 * @param userCode：会员编号
	 * @param siNo：发货单号
	 * @param orderNo：订单号
	 * @return
	 */
    public List getPdSendInfoPage(GroupPage page,String userCode,String siNo, String orderNo,String orderFlag) {
    	String sqlCount = "select count(*) from PdSendInfo t where t.orderFlag>=2 ";
    	//会员编号
    	if(!StringUtil.isEmpty(userCode)){
    		sqlCount += " and t.sysUser.userCode ='"+userCode+"' ";
    	} 

    	if(!StringUtil.isEmpty(siNo)){
    		sqlCount += " and t.siNo = '"+siNo+"' ";
    	}

    	if(!StringUtil.isEmpty(orderNo)){
    		sqlCount += " and t.orderNo = '"+orderNo+"' ";
    	}
    	
    	//状态
    	if(!StringUtil.isEmpty(orderFlag)){
    		sqlCount += " and t.orderFlag = '"+orderFlag+"' ";
    	}else{
    		sqlCount += " and t.orderFlag >=2 ";
    	}

    	sqlCount += " order by t.orderFlag, t.createTime desc ";

    	String sql = " from PdSendInfo t where t.orderFlag>=2 "; 
    	//会员编号
    	if(!StringUtil.isEmpty(userCode)){
    		sql += " and t.sysUser.userCode ='"+userCode+"' ";
    	}

    	if(!StringUtil.isEmpty(siNo)){
    		sql += " and t.siNo = '"+siNo+"' ";
    	}

    	if(!StringUtil.isEmpty(orderNo)){
    		sql += " and t.orderNo = '"+orderNo+"' ";
    	}

    	//状态
    	if(!StringUtil.isEmpty(orderFlag)){
    		sql += " and t.orderFlag = '"+orderFlag+"' ";
    	}else{
    		sql += " and t.orderFlag >=2 ";
    	}
    	
    	sql += " order by t.orderFlag, t.createTime desc ";
    	//返回时调用分页的查询的方法 
//    	return this.findObjectsBySQL(sql, page);
    	return this.findObjectsByHQL(sqlCount, sql, page);
    }
    
    
    /**
	 * 会员发货单管理
	 * Add By WuCF 2014-04-28
	 * 分页
	 * @param page：分页信息
	 * @param crm：查询的条件集合
	 * @return
	 */
    public List getJpoMemberOrderPage(GroupPage page,CommonRecord crm) {
    	StringBuffer sqlCount = new StringBuffer("select count(*) from JpoMemberOrder t where 1=1 ");
    	//会员编号
    	String userCode = crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		sqlCount.append(" and t.sysUser.userCode ='");
    		sqlCount.append(userCode);
    		sqlCount.append("' ");
    	} 
    	//订单编号
    	String orderNo = crm.getString("orderNo", "");
    	if(!StringUtil.isEmpty(orderNo)){
    		sqlCount.append(" and t.memberOrderNo ='");
    		sqlCount.append(orderNo);
    		sqlCount.append("' ");
    	} 
    	//订单类型
    	String orderType = crm.getString("orderType", "");
    	if(!StringUtil.isEmpty(orderType)){
    		sqlCount.append(" and t.orderType ='");
    		sqlCount.append(orderType);
    		sqlCount.append("' ");
    	} 
    	//订单来源
    	String isMobile = crm.getString("isMobile", "");
    	if(!StringUtil.isEmpty(isMobile)){
    		sqlCount.append(" and t.isMobile ='");
    		sqlCount.append(isMobile);
    		sqlCount.append("' ");
    	} 
    	//退单状态
    	String isRetreatOrder = crm.getString("isRetreatOrder", "");
    	if(!StringUtil.isEmpty(isRetreatOrder)){
    		if("0".equals(isRetreatOrder)){
    			sqlCount.append(" and (t.isRetreatOrder2='");
    			sqlCount.append(isRetreatOrder);
    			sqlCount.append("' or t.isRetreatOrder2 is null)");
    		}else if("1".equals(isRetreatOrder)){
    			sqlCount.append(" and t.isRetreatOrder2='");
    			sqlCount.append(isRetreatOrder);
    			sqlCount.append("'");
    		}
    	} 
    	//发货状态
    	String isShipments = crm.getString("isShipments", "");
    	if(!StringUtil.isEmpty(isShipments)){
    		sqlCount.append(" and t.isShipments ='");
    		sqlCount.append(isShipments);
    		sqlCount.append("' ");
    	} 
    	//订单状态
    	String status = crm.getString("status", "");
    	if(!StringUtil.isEmpty(status)){
    		sqlCount.append(" and t.status ='");
    		sqlCount.append(status);
    		sqlCount.append("' ");
    	} 
    	String logType = crm.getString("logType", "");
    	String startLogTime = crm.getString("startLogTime", "");
    	String endLogTime = crm.getString("endLogTime", "");
		if (!StringUtils.isEmpty(logType)) { 
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					sqlCount.append(" and t.orderTime>=to_date('");
					sqlCount.append(startLogTime);
					sqlCount.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					sqlCount.append(" and t.checkDate>=to_date('");
					sqlCount.append(startLogTime);
					sqlCount.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					sqlCount.append(" and t.checkTime>=to_date('");
					sqlCount.append(startLogTime);
					sqlCount.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
			}
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					sqlCount.append(" and t.orderTime<=to_date('");
					sqlCount.append(endLogTime);
					sqlCount.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					sqlCount.append(" and t.checkDate<=to_date('");
					sqlCount.append(endLogTime);
					sqlCount.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					sqlCount.append(" and t.checkTime<=to_date('");
					sqlCount.append(endLogTime);
					sqlCount.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
			}
		}
		sqlCount.append(" order by t.orderTime desc ");
    	
    	
    	//==================询数据语句
    	StringBuffer sql = new StringBuffer(" from JpoMemberOrder t where 1=1 "); 
    	//会员编号
    	if(!StringUtil.isEmpty(userCode)){
    		sql.append(" and t.sysUser.userCode ='");
    		sql.append(userCode);
    		sql.append("' ");
    	} 
    	//订单编号
    	if(!StringUtil.isEmpty(orderNo)){
    		sql.append(" and t.memberOrderNo ='");
    		sql.append(orderNo);
    		sql.append("' ");
    	} 
    	//订单类型
    	if(!StringUtil.isEmpty(orderType)){
    		sql.append(" and t.orderType ='");
    		sql.append(orderType);
    		sql.append("' ");
    	} 
    	//订单来源
    	if(!StringUtil.isEmpty(isMobile)){
    		sql.append(" and t.isMobile ='");
    		sql.append(isMobile);
    		sql.append("' ");
    	} 
    	//退单状态
    	if(!StringUtil.isEmpty(isRetreatOrder)){
    		if("0".equals(isRetreatOrder)){
    			sql.append(" and (t.isRetreatOrder2='");
    			sql.append(isRetreatOrder);
    			sql.append("' or t.isRetreatOrder2 is null)");
    		}else if("1".equals(isRetreatOrder)){
    			sql.append(" and t.isRetreatOrder2='");
    			sql.append(isRetreatOrder);
    			sql.append("'");
    		}
    	} 
    	//发货状态
    	if(!StringUtil.isEmpty(isShipments)){
    		sql.append(" and t.isShipments ='");
    		sql.append(isShipments);
    		sql.append("' ");
    	} 
    	//订单状态
    	if(!StringUtil.isEmpty(status)){
    		sql.append(" and t.status ='");
    		sql.append(status);
    		sql.append("' ");
    	} 
		if (!StringUtils.isEmpty(logType)) { 
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					sql.append(" and t.orderTime>=to_date('");
					sql.append(startLogTime);
					sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					sql.append(" and t.checkDate>=to_date('");
					sql.append(startLogTime);
					sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					sql.append(" and t.checkTime>=to_date('");
					sql.append(startLogTime);
					sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
			}
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					sql.append(" and t.orderTime<=to_date('");
					sql.append(endLogTime);
					sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					sql.append(" and t.checkDate<=to_date('");
					sql.append(endLogTime);
					sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					sql.append(" and t.checkTime<=to_date('");
					sql.append(endLogTime);
					sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
			}
		}
		sql.append(" order by t.orderTime desc ");
		
    	//返回时调用分页的查询的方法  
    	log.info("getJpoMemberOrderPage-sqlCount："+sqlCount.toString());
    	log.info("getJpoMemberOrderPage-sql："+sql.toString());
    	return this.findObjectsByHQL(sqlCount.toString(), sql.toString(), page);
    }
    

    /**
	 * 会员订单总金额
	 * Add By WuCF 20140428
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm) {
		StringBuffer hql = new StringBuffer("select nvl(sum(jpoMemberOrder.amount2),0) as amount,"
				+ "nvl(sum(jpoMemberOrder.jjAmount),0) as jjAmount,nvl(sum(jpoMemberOrder.pvAmt),0) as pvAmt,"
				+ "nvl(sum(jpoMemberOrder.discountAmount),0) as discountAmount,"
				+ "nvl(sum(jpoMemberOrder.productPointAmount),0) as productPointAmount" //产品积分总额  add by hdg 2017-01-04
				+ " from JpoMemberOrder jpoMemberOrder where 1=1");
		hql.append(this.buildListHqlQuery(crm));
		
		Object[] sum = (Object[])this.getObjectByHqlQuery(hql.toString());
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map.put("amount", (BigDecimal) sum[0]);
		map.put("jjAmount", (BigDecimal) sum[1]);
		map.put("pvAmt", (BigDecimal) sum[2]);
		map.put("discountAmount", (BigDecimal) sum[3]);
		map.put("productPointAmount", (BigDecimal) sum[4]);	//产品积分总额  add by hdg 2017-01-04
		log.info("getSumAmountByCrm-sql："+hql);
		return map;
	}
    
	/**
	 * 限制2个套餐，数量限制分别是5000和8000
	 */
	public int getStatisticProductSale2(CommonRecord crm) {
		String po = crm.getString("po", "");
    	StringBuffer sql = new StringBuffer("Select Sum(a.Qty) as qty From Jpo_Member_Order_List a, Jpm_Product_Sale_Team_Type c,Jpm_Product_Sale_New pnew ");
		sql.append(" Where a.Product_Id = c.ptt_id  and c.uni_no=pnew.uni_no and pnew.product_no= '"+po+"'");
		sql.append(" And Exists (Select 1 From Jpo_Member_Order b Where a.mo_Id = b.mo_Id ");
		sql.append(") Group By pnew.product_no,pnew.product_name");
		log.info("getStatisticProductSale2-sql："+sql);
		List list = this.getJdbcTemplate().queryForList(sql.toString());
		if (list.size() > 0) {
			Object object = list.get(0);
			Map map = (Map)object;
			BigDecimal integer = (BigDecimal)map.get("qty");
			log.info("-----------------------"+ integer);
			if (integer != null) {
				return integer.intValue();
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}
	
	/**
	 * 根据条件统计商品销售量
	 * Add By WuCF 2014-04-28
	 * @param crm
	 * @return
	 */
	 @Override
		public List getStatisticProductSale(CommonRecord crm) {
	    	StringBuffer sql = new StringBuffer("Select pnew.product_no as product_no, pnew.product_name as product_name, Sum(a.Price*a.Qty) as price, Sum(a.Qty) as qty From Jpo_Member_Order_List a, Jpm_Product_Sale_Team_Type c,Jpm_Product_Sale_New pnew");
			sql.append(" Where a.Product_Id = c.ptt_id  and c.uni_no=pnew.uni_no And Exists (Select 1 From Jpo_Member_Order b Where a.mo_Id = b.mo_Id ");

			String companyCode = crm.getString("companyCode", "");
			if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
				sql.append(" And b.company_Code = '");
				sql.append(companyCode);
				sql.append("'");
			}

			//Modify By WuCF 20140430 
			//发货状态
			String isShipments = crm.getString("isShipments", "");
			if (!StringUtils.isEmpty(isShipments)) {
				sql.append(" And b.IS_SHIPMENTS = '");
				sql.append(isShipments);
				sql.append("'");
			}
			
			//订单来源
	    	String isMobile = crm.getString("isMobile", "");
	    	if(!StringUtil.isEmpty(isMobile)){
	    		sql.append(" and b.is_Mobile ='");
	    		sql.append(isMobile);
	    		sql.append("' ");
	    	} 
			
			String orderNo = crm.getString("orderNo", "");
			if (!StringUtils.isEmpty(orderNo)) {
				sql.append(" And b.member_Order_No = '");
				sql.append(orderNo);
				sql.append("'");
			}

			String province = crm.getString("province", "");
			if (!StringUtils.isEmpty(province)) {
				sql.append(" and b.province = '");
				sql.append(province);
				sql.append("'");
			}

			String city = crm.getString("city", "");
			if (!StringUtils.isEmpty(city)) {
				sql.append(" and b.city = '");
				sql.append(city);
				sql.append("'");
			}

			String productType = crm.getString("productType", "");
			if (!StringUtils.isEmpty(productType)) {
				if("ALL".equals(productType)){
					
				}else if("JOYMAIN".equals(productType)){
					sql.append(" and b.product_Type is null");
				}else if("LC".equals(productType)){
					sql.append(" and b.product_Type='");
					sql.append(productType);
					sql.append("'");
				}
			}

			String district = crm.getString("district", "");
			if (!StringUtils.isEmpty(district)) {
				sql.append(" and b.district = '");
				sql.append(district);
				sql.append("'");
			}

			String userCode = crm.getString("userCode", "");
			if (!StringUtils.isEmpty(userCode)) {
				sql.append(" and b.user_code = '");
				sql.append(userCode);
				sql.append("'");
			}

			String orderType = crm.getString("orderType", "");
			if (!StringUtils.isEmpty(orderType)) {
				sql.append(" and b.order_Type='");
				sql.append(orderType);
				sql.append("'");
			}

			String payByCoin = crm.getString("payByCoin", "");
			if (!StringUtils.isEmpty(payByCoin)) {
				if("0".equals(payByCoin)){
					sql.append(" and (b.pay_By_Coin='");
					sql.append(payByCoin);
					sql.append("' or b.pay_By_Coin is null)");
				}else{
					sql.append(" and b.pay_By_Coin='");
					sql.append(payByCoin);
					sql.append("'");
				}
			}
			
			String payByJJ = crm.getString("payByJJ", "");
			if (!StringUtils.isEmpty(payByJJ)) {
				if (!StringUtils.isEmpty(payByJJ)) {
					if("0".equals(payByJJ)){
						sql.append(" and (b.pay_By_Jj='");
						sql.append(payByJJ);
						sql.append("' or b.pay_By_Jj is null)");
					}else{
						sql.append(" and b.pay_By_Jj='");
						sql.append(payByJJ);
						sql.append("'");
					}
				}
			}
			
			String isRetreatOrder = crm.getString("isRetreatOrder", "");//退单的商品
			if (!StringUtils.isEmpty(isRetreatOrder)) {
				if("0".equals(isRetreatOrder)){
					sql.append(" and (b.IS_RETREAT_ORDER2='");
					sql.append(isRetreatOrder);
					sql.append("' or b.IS_RETREAT_ORDER2 is null)");
				}else{
					sql.append(" and b.IS_RETREAT_ORDER2='");
					sql.append(isRetreatOrder);
					sql.append("'");
				}
			}

			String locked = crm.getString("locked", "");
			if (!StringUtils.isEmpty(locked)) {
				sql.append(" and locked='");
				sql.append(locked);
				sql.append("'");
			}

			String status = crm.getString("status", "");
			if (!StringUtils.isEmpty(status)) {
				sql.append(" And b.status = '");
				sql.append(status);
				sql.append("'");
			}

			String logType = crm.getString("logType", "");
			if (!StringUtils.isEmpty(logType)) {
				String startLogTime = crm.getString("startLogTime", "");
				if (!StringUtils.isEmpty(startLogTime)) {
					if ("A".equals(logType)) {
						sql.append(" and b.order_Time>=to_date('");
						sql.append(startLogTime);
						sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
					}
					if ("C".equals(logType)) {
						sql.append(" and b.check_Date>=to_date('");
						sql.append(startLogTime);
						sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
					}
					if ("BC".equals(logType)) {
						sql.append(" and b.check_Time>=to_date('");
						sql.append(startLogTime);
						sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
					}
				}
				String endLogTime = crm.getString("endLogTime", "");
				if (!StringUtils.isEmpty(endLogTime)) {
					if ("A".equals(logType)) {
						sql.append(" and b.order_Time<=to_date('");
						sql.append(endLogTime);
						sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
					}
					if ("C".equals(logType)) {
						sql.append(" and b.check_Date<=to_date('");
						sql.append(endLogTime);
						sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
					}
					if ("BC".equals(logType)) {
						sql.append(" and b.check_Time<=to_date('");
						sql.append(endLogTime);
						sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
					}
				}
			}

			sql.append(") Group By pnew.product_no,pnew.product_name");
			log.info("getStatisticProductSale-sql："+sql);
			return this.getJdbcTemplate().queryForList(sql.toString());
		}
	
	
	
	/**
	 * 根据外部参数生成查询语句
	 * 
	 * @param crm
	 * @return
	 */
	private String buildListHqlQuery(CommonRecord crm) {
		StringBuffer hql = new StringBuffer("");

		String companyCode = crm.getString("companyCode", "");
		if (!"AA".equals(companyCode) && !StringUtils.isEmpty(companyCode)) {
			hql.append(" and companyCode='");
			hql.append(companyCode);
			hql.append("'");
		}

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql.append(" and sysUser.userCode='");
			hql.append(userCode);
			hql.append("'");
		}

		String recommendCompanyCode = crm.getString("sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode", "");
		if (!StringUtils.isEmpty(recommendCompanyCode)) {
			hql.append(" and sysUser.jmiMember.jmiRecommendRef.jmiMember.companyCode='");
			hql.append(recommendCompanyCode);
			hql.append("'");
		}

		String productType = crm.getString("productType", "");
		if (!StringUtils.isEmpty(productType)) {
			if("ALL".equals(productType)){
				
			}else if("JOYMAIN".equals(productType)){
				hql.append(" and productType is null");
			}else if("LC".equals(productType)){
				hql.append(" and productType='");
				hql.append(productType);
				hql.append("'");
			}
		}

		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			hql.append(" and orderType='");
			hql.append(orderType);
			hql.append("'");
		}

		String orderTypeIn = crm.getString("orderTypeIn", "");
		if (!StringUtils.isEmpty(orderTypeIn)) {
			hql.append(" and orderType in (");
			hql.append(orderTypeIn);
			hql.append(")");
		}

		String isSpecial = crm.getString("isSpecial", "");
		if (!StringUtils.isEmpty(isSpecial)) {
			hql.append(" and isSpecial='");
			hql.append(isSpecial);
			hql.append("'");
		}

		String status = crm.getString("status", "");
		if (!StringUtils.isEmpty(status)) {
			hql.append(" and status='");
			hql.append(status);
			hql.append("'");
		}

		String isPay = crm.getString("isPay", "");
		if (!StringUtils.isEmpty(isPay)) {
			hql.append(" and isPay='");
			hql.append(isPay);
			hql.append("'");
		}
		String isShipments = crm.getString("isShipments", "");
		if (!StringUtils.isEmpty(isShipments)) {
			if("2".equals(isShipments))
			{
				hql.append(" and (isShipments='0' or  isShipments is null)");
			}else
			{
				hql.append(" and (isShipments='");
				hql.append(isShipments);
				hql.append("')");
			}
			
		}

		String payByCoin = crm.getString("payByCoin", "");
		if (!StringUtils.isEmpty(payByCoin)) {
			if("0".equals(payByCoin)){
				hql.append(" and (payByCoin='");
				hql.append(payByCoin);
				hql.append("' or payByCoin is null)");
			}else{
				hql.append(" and payByCoin='");
				hql.append(payByCoin);
				hql.append("'");
			}
		}

		String payByJJ = crm.getString("payByJJ", "");
		if (!StringUtils.isEmpty(payByJJ)) {
			if("0".equals(payByJJ)){
				hql.append(" and (payByJJ='");
				hql.append(payByJJ);
				hql.append("' or payByJJ is null)");
			}else{
				hql.append(" and payByJJ='");
				hql.append(payByJJ);
				hql.append("'");
			}
		}
		
		String isRetreatOrder = crm.getString("isRetreatOrder", "");//退的单量
		if (!StringUtils.isEmpty(isRetreatOrder)) {
			if("0".equals(isRetreatOrder)){
				hql.append(" and (isRetreatOrder2='");
				hql.append(isRetreatOrder);
				hql.append("' or isRetreatOrder2 is null)");
			}else{
				hql.append(" and isRetreatOrder2='");
				hql.append(isRetreatOrder);
				hql.append("'");
			}
		}

		String province = crm.getString("province", "");
		if (!StringUtils.isEmpty(province)) {
			hql.append(" and province = '");
			hql.append(province);
			hql.append("'");
		}

		String city = crm.getString("city", "");
		if (!StringUtils.isEmpty(city)) {
			hql.append(" and city = '");
			hql.append(city);
			hql.append("'");
		}

		String district = crm.getString("district", "");
		if (!StringUtils.isEmpty(district)) {
			hql.append(" and district = '");
			hql.append(district);
			hql.append("'");
		}

		String locked = crm.getString("locked", "");
		if (!StringUtils.isEmpty(locked)) {
			hql.append(" and locked='");
			hql.append(locked);
			hql.append("'");
		}

		String submitStatus = crm.getString("submitStatus", "");
		if (!StringUtils.isEmpty(submitStatus)) {
			hql.append(" and submitStatus='");
			hql.append(submitStatus);
			hql.append("'");
		}

		//订单编号
		String orderNo = crm.getString("orderNo", "");
		if (!StringUtils.isEmpty(orderNo)) {
			hql.append(" and memberOrderNo='");
			hql.append(orderNo);
			hql.append("'");
		}

		String startOrderTime = crm.getString("startOrderTime", "");
		if (!StringUtils.isEmpty(startOrderTime)) {
			hql.append(" and orderTime>=to_date('");
			hql.append(startOrderTime);
			hql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}

		String endOrderTime = crm.getString("endOrderTime", "");
		if (!StringUtils.isEmpty(endOrderTime)) {
			hql.append(" and orderTime<=to_date('");
			hql.append(endOrderTime);
			hql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}

		String startOrderCheckTime = crm.getString("startOrderCheckTime", "");
		if (!StringUtils.isEmpty(startOrderCheckTime)) {
			hql.append(" and checkTime>=to_date('");
			hql.append(startOrderCheckTime);
			hql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}

		String endOrderCheckTime = crm.getString("endOrderCheckTime", "");
		if (!StringUtils.isEmpty(endOrderCheckTime)) {
			hql.append(" and checkTime<=to_date('");
			hql.append(endOrderCheckTime);
			hql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		String startOrderCheckDate = crm.getString("createBDate", "");
		if (!StringUtils.isEmpty(startOrderCheckDate)) {
			hql.append(" and checkDate>=to_date('");
			hql.append(startOrderCheckDate);
			hql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}

		String endOrderCheckDate = crm.getString("createEDate", "");
		if (!StringUtils.isEmpty(endOrderCheckDate)) {
			hql.append(" and checkDate<=to_date('");
			hql.append(endOrderCheckDate);
			hql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
		}
		
		//判断订单是否有发货------开始
		String isShipping = crm.getString("isShipping", "");
		if (!StringUtils.isEmpty(isShipping)) {
			if("1".equals(isShipping))
			{
				hql.append(" and (isShipping!='");
				hql.append(isShipping);
				hql.append("' or isShipping is null)");
			}
			
		}
		String shippingPay = crm.getString("shippingPay", "");
		if (!StringUtils.isEmpty(shippingPay)) {
			if("1".equals(shippingPay))
			{
				hql.append(" and (shippingPay!='");
				hql.append(shippingPay);
				hql.append("' or shippingPay is null)");
			}
			
		}
		String checkTimeF = crm.getString("checkTimeF", "");//3月20
		if (!StringUtils.isEmpty(checkTimeF)) {
			hql.append(" and checkTime>=to_date('");
			hql.append(checkTimeF);
			hql.append(" 10:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
		
		
		
        //判断订单是否有发货------结束	

		String inPeriod = crm.getString("inPeriod", "");
		if (!StringUtils.isEmpty(inPeriod)) {
			if ("A".equals(inPeriod)) {
				hql.append(" and checkDate>=to_date('");
				hql.append(crm.getString("inPeriodStartTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss')");
				
				hql.append(" and checkDate< to_date('");
				hql.append(crm.getString("inPeriodEndTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss')");
				
				hql.append(" and (checkTime< to_date('");
				hql.append(crm.getString("inPeriodStartTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss')");
				
				hql.append(" or checkTime>= to_date('");
				hql.append(crm.getString("inPeriodEndTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss'))");
			} else if ("D".equals(inPeriod)) {
				hql.append(" and checkTime>=to_date('");
				hql.append(crm.getString("inPeriodStartTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss')");
				
				hql.append(" and checkTime< to_date('");
				hql.append(crm.getString("inPeriodEndTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss')");
				
				hql.append(" and (checkDate< to_date('");
				hql.append(crm.getString("inPeriodStartTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss')");
				
				hql.append(" or checkDate>= to_date('");
				hql.append(crm.getString("inPeriodEndTime", ""));
				hql.append("','yyyy-mm-dd hh24:mi:ss'))");
			}
		}
		
		String petName = crm.getString("sysUser.jmiMember.petName", "");
		if (!StringUtils.isEmpty(petName)) {
			hql.append(" and sysUser.jmiMember.petName = '");
			hql.append(petName);
			hql.append("'");
		}
		
		String firstNameKana = crm.getString("sysUser.jmiMember.firstNameKana", "");
		if (!StringUtils.isEmpty(firstNameKana)) {
			hql.append(" and sysUser.jmiMember.firstNameKana = '");
			hql.append(firstNameKana);
			hql.append("'");
		}
		
		String lastNameKana = crm.getString("sysUser.jmiMember.lastNameKana", "");
		if (!StringUtils.isEmpty(lastNameKana)) {
			hql.append(" and sysUser.jmiMember.lastNameKana = '");
			hql.append(lastNameKana);
			hql.append("'");
		}

		String mode = crm.getString("mode", "");
		if (!StringUtils.isEmpty(mode)&&!"0".equals(mode)) {
			hql.append(" and sysUser.userCode in (select sysUser.userCode from JfiBankbookJournal b where journalId=(select max(journalId) from JfiBankbookJournal c where c.sysUser.userCode = b.sysUser.userCode");
			String company = crm.getString("company", "");
			if (!StringUtils.isEmpty(company)) {
				hql.append(" and c.companyCode = '");
				hql.append(company);
				hql.append("'");
			}
			hql.append(" ) and b.balance>=");
			hql.append(mode);
			hql.append(")");
		}

		String logType = crm.getString("logType", "");
		if (!StringUtils.isEmpty(logType)) {
			String startLogTime = crm.getString("startLogTime", "");
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					hql.append(" and orderTime>=to_date('");
					hql.append(startLogTime);
					hql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					hql.append(" and checkDate>=to_date('");
					hql.append(startLogTime);
					hql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					hql.append(" and checkTime>=to_date('");
					hql.append(startLogTime);
					hql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
			}
			String endLogTime = crm.getString("endLogTime", "");
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					hql.append(" and orderTime<=to_date('");
					hql.append(endLogTime);
					hql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					hql.append(" and checkDate<=to_date('");
					hql.append(endLogTime);
					hql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					hql.append(" and checkTime<=to_date('");
					hql.append(endLogTime);
					hql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
			}
		}
		
		String team = crm.getString("team", "");
		if(StringUtils.isNotBlank(team)){
			hql.append(" and sysUser.jmiMember.memberType='");
			hql.append(team.trim());
			hql.append("' ");
		}
		
		//Modify By WuCF 20140430 订单来源
		String isMobile = crm.getString("isMobile", "");
		if(StringUtils.isNotBlank(isMobile)){
			hql.append(" and isMobile='");
			hql.append(isMobile.trim());
			hql.append("' ");
		}
		
		return hql.toString();
	}
	

	/**
	 * 会员收货确认
	 * @param siNo：订单编号
	 * @return
	 */
	public int sendInfoConfirm(String siNo) {
		String sql = " update pd_send_info t set t.order_flag='3' where t.si_no='"+siNo+"' and t.order_flag='2' ";
    	//返回时调用分页的查询的方法 
    	return this.jdbcTemplate.update(sql);
	}

	/**
	 * 获得指定会员的发货单中：已经发货，但未确认的发货信息，只有在发货单10天后到17天之间如果没有确认的，才会提示！
	 * @param userCode
	 * @return
	 */
	public int isExistNotConfirm(String userCode) {
		/*int returnCount = 0;
		String sql = " SELECT * FROM PD_SEND_INFO a where a.custom_code='"+userCode+"' AND A.ORDER_FLAG=2 AND RECIPIENT_TIME IS NULL ";
		sql += "  and a.ok_time>=sysdate+10 and a.ok_time<=sysdate+17 ";
		List list = this.jdbcTemplate.queryForList(sql);
		if(list!=null && list.size()>=1){
			returnCount = list.size();
		}
		return returnCount;*/
		//Modify By WuCF 20140701 绑定变量
		int returnCount = 0;
		StringBuffer sql = new StringBuffer(" SELECT a.si_no FROM PD_SEND_INFO a where a.custom_code=? AND A.ORDER_FLAG=2 AND RECIPIENT_TIME IS NULL ");
		sql.append(" and a.ok_time>=sysdate-17 and a.ok_time<=sysdate-10 ");
		StringBuffer paramsBuf = new StringBuffer(","+userCode);
		
		Object[] parameters = paramsBuf.toString().substring(1).split(",");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString(),parameters); 
		if(list!=null && list.size()>=1){
			returnCount = list.size();
		}
		return returnCount;
	}

	/**
	 * Add By WuCF 20141219
	 * 通过订单号和商品编号，查询返回商品的物流跟踪单号
	 * @param orderNo：订单号
	 * @param productNo：商品编码---实际上是订单明细表jpo_member_order_list的主键
	 * @return：返回具体商品的物流跟踪信息，可能有多个，则用逗号英文“,”隔开
	 */
	public List<String> getTrackingNoInfo(String orderNo, String productNo) {
		List<String> list = new ArrayList<String>();
		try{
			StringBuffer sqlBuf = new StringBuffer("select t2.tracking_single TRACKING_SINGLE from jpo_member_order t1,jpo_member_order_list t2 where ");
			sqlBuf.append(" t1.mo_id=t2.mo_id and t1.member_order_no=? and t2.mol_id=? ");
			StringBuffer paramsBuf = new StringBuffer(","+orderNo);
			paramsBuf.append(","+productNo);
			
			Object[] parameters = paramsBuf.toString().substring(1).split(",");
			log.info("在类的方法中运行（手机物流接口）,sql语句:"+sqlBuf.toString());
			log.info("在类的方法中运行（手机物流接口）,参数为:"+parameters.toString());
			List<Map<String, Object>> listTemp = this.getJdbcTemplate().queryForList(sqlBuf.toString(),parameters); 
			log.info("在类的方法中运行（手机物流接口）,运行结果为：:"+listTemp.toString());
			for(Map<String,Object> map : listTemp){
				if(map.get("TRACKING_SINGLE")!=null){
					String str = String.valueOf(map.get("TRACKING_SINGLE"));
					String[] strs = str.split("</br>");
					for(String s : strs){
						list.add(s);//添加到集合中返回
					}
				}
			} 
			
			/*String pttId = "";
			StringBuffer sqlBuf = new StringBuffer(" select product_id PRODUCT_ID from jpo_member_order_list where mol_id=?");
			//参数字符串
			StringBuffer paramsBuf = new StringBuffer(","+productNo);
			Object[] parameters = paramsBuf.toString().substring(1).split(",");
			List<Map<String, Object>> listTemp = this.getJdbcTemplate().queryForList(sqlBuf.toString(),parameters); 
			for(Map<String,Object> map : listTemp){
				pttId = String.valueOf(map.get("PRODUCT_ID"));
			}
			
			sqlBuf = new StringBuffer("select t2.pdlogisticsbasenum_no PDLOGISTICSBASENUM_NO from PD_LOGISTICS_BASE t1,PD_LOGISTICS_BASE_NUM t2, ");
			sqlBuf.append(" PD_LOGISTICS_BASE_DETAIL t3,JPM_PRODUCT_SALE_TEAM_TYPE T4,JPM_PRODUCT_SALE_NEW T5  ");
			sqlBuf.append(" where t1.base_id=t2.base_id and t2.num_id=t3.num_id AND T4.UNI_NO=T5.UNI_NO AND T5.PRODUCT_NO=T3.PRODUCT_NO ");
			sqlBuf.append(" and t1.member_order_no= ? AND T5.COMPANY_CODE='CN' and t4.PTT_ID= ? ");
			//参数字符串
			paramsBuf = new StringBuffer(","+orderNo+","+pttId);
			
			parameters = paramsBuf.toString().substring(1).split(",");
			listTemp = this.getJdbcTemplate().queryForList(sqlBuf.toString(),parameters); 
			for(Map<String,Object> map : listTemp){
				list.add(String.valueOf(map.get("PDLOGISTICSBASENUM_NO")));
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		log.info("在类的方法中运行（手机物流接口）,运行结果为：:"+list.toString());
		return list;
	}
	
	/**
	 * Add By WuCF 20150907是否拥有股票链接地址
	 * @param userCode
	 * @return
	 */
	public boolean isGuPiaoUser(String userCode) {
		//Modify By WuCF 20140701 绑定变量
		boolean result = false;
		StringBuffer sql = new StringBuffer(" select * from JSYS_GUPIAO_USER where USER_CODE=? ");
		StringBuffer paramsBuf = new StringBuffer(","+userCode);
		
		Object[] parameters = paramsBuf.toString().substring(1).split(",");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString(),parameters); 
		if(list!=null && list.size()>=1){
			result = true;
		}
		return result;
	}
	
	/**
	 * 得到瓜藤网图片的地址和读取名称
	 * @param listCode
	 * @return
	 */
	public List getGuaTenLinks(String listCode) {
		List list = new ArrayList();
		String sql = "select T2.VALUE_CODE,t2.value_title VALUE_TITLE,t2.order_no ORDER_NO from jsys_list_key t1, "+
		 " jsys_list_value t2 where t1.key_id=t2.key_id "+
		 " and t1.list_code='"+listCode+"' ORDER BY T2.VALUE_CODE";
		list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	
	public List getJpoMemberOrderList(CommonRecord crm) {
    	//会员编号
    	String userCode = crm.getString("userCode", "");
    	//订单编号
    	String orderNo = crm.getString("orderNo", "");
    	//订单类型
    	String orderType = crm.getString("orderType", "");
    	//订单来源
    	String isMobile = crm.getString("isMobile", "");
    	//退单状态
    	String isRetreatOrder = crm.getString("isRetreatOrder", "");
    	//发货状态
    	String isShipments = crm.getString("isShipments", "");
    	//订单状态
    	String status = crm.getString("status", "");
    	String logType = crm.getString("logType", "");
    	String startLogTime = crm.getString("startLogTime", "");
    	String endLogTime = crm.getString("endLogTime", "");
    	String orderTime = crm.getString("orderTime", "");
    	String isPay = crm.getString("isPay", "");
    	
    	//==================询数据语句
    	StringBuffer sql = new StringBuffer(" from JpoMemberOrder t where 1=1 "); 
    	//会员编号
    	if(!StringUtil.isEmpty(userCode)){
    		sql.append(" and t.sysUser.userCode ='");
    		sql.append(userCode);
    		sql.append("' ");
    	} 
    	//订单编号
    	if(!StringUtil.isEmpty(orderNo)){
    		sql.append(" and t.memberOrderNo ='");
    		sql.append(orderNo);
    		sql.append("' ");
    	} 
    	//订单类型
    	if(!StringUtil.isEmpty(orderType)){
    		sql.append(" and t.orderType ='");
    		sql.append(orderType);
    		sql.append("' ");
    	} 
    	//订单来源
    	if(!StringUtil.isEmpty(isMobile)){
    		sql.append(" and t.isMobile ='");
    		sql.append(isMobile);
    		sql.append("' ");
    	} 
    	//退单状态
    	if(!StringUtil.isEmpty(isRetreatOrder)){
    		if("0".equals(isRetreatOrder)){
    			sql.append(" and (t.isRetreatOrder2='");
    			sql.append(isRetreatOrder);
    			sql.append("' or t.isRetreatOrder2 is null)");
    		}else if("1".equals(isRetreatOrder)){
    			sql.append(" and t.isRetreatOrder2='");
    			sql.append(isRetreatOrder);
    			sql.append("'");
    		}
    	} 
    	//发货状态
    	if(!StringUtil.isEmpty(isShipments)){
    		sql.append(" and t.isShipments ='");
    		sql.append(isShipments);
    		sql.append("' ");
    	} 
    	//订单状态
    	if(!StringUtil.isEmpty(status)){
    		sql.append(" and t.status ='");
    		sql.append(status);
    		sql.append("' ");
    	} 
    	
    	//isPay
    	if(!StringUtil.isEmpty(isPay)){
    		sql.append(" and t.isPay ='");
    		sql.append(isPay);
    		sql.append("' ");
    	} 
    	if(!StringUtils.isEmpty(orderTime)){
			//1:最近一周;2:最近一月; 3:最近三月; 4:最近半年; 5:最近一年;
			//Calendar calendar=Calendar.getInstance();
			//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			if("00".equals(orderTime)){
				
			}else if( "0".equals( orderTime ) ){
				sql.append(" and t.orderTime>=trunc(sysdate)-7 ");
			}else if( "1".equals( orderTime ) ){
				sql.append(" and t.orderTime>=trunc(sysdate)-30 ");
			}else if( "2".equals( orderTime ) ){
				sql.append(" and t.orderTime>=add_months( trunc(sysdate) ,-3 ) ");
			}else if( "3".equals( orderTime ) ){
				sql.append(" and t.orderTime>=add_months( trunc(sysdate) ,-6 ) ");
			}else if( "4".equals( orderTime ) ){
				sql.append(" and t.orderTime>=add_months( trunc(sysdate) ,-12) ");
			}else{
			}
		}
		if (!StringUtils.isEmpty(logType)) { 
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					sql.append(" and t.orderTime>=to_date('");
					sql.append(startLogTime);
					sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					sql.append(" and t.checkDate>=to_date('");
					sql.append(startLogTime);
					sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					sql.append(" and t.checkTime>=to_date('");
					sql.append(startLogTime);
					sql.append(" 00:00:00','yyyy-mm-dd hh24:mi:ss')");
				}
			}
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					sql.append(" and t.orderTime<=to_date('");
					sql.append(endLogTime);
					sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					sql.append(" and t.checkDate<=to_date('");
					sql.append(endLogTime);
					sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					sql.append(" and t.checkTime<=to_date('");
					sql.append(endLogTime);
					sql.append(" 23:59:59','yyyy-mm-dd hh24:mi:ss')");
				}
			}
		}
		sql.append(" order by t.orderTime desc ");
		
    	//返回时调用分页的查询的方法  
    	log.info("getJpoMemberOrderPage-sql："+sql.toString());
    	Query query = getSession().createQuery(sql.toString());
    	return query.list();
    }
	
	/**
	 * 根据发货单号获取发货单
	 * @author fu 2016-04-22
	 * @param logisticsNo
	 * @return
	 */
	public PdSendInfo getPdSendInfoBySiNo(String logisticsNo){
		String hql = " from PdSendInfo pdSendInfo where pdSendInfo.siNo= '"+logisticsNo+"'";
		if(null!=this.getObjectByHqlQuery(hql)){
		     PdSendInfo pdSendInfo =(PdSendInfo) this.getObjectByHqlQuery(hql);
		     return pdSendInfo;
		}
		return null;
	}
	
	/**
	 * 根据订单号获取对应的发货信息
	 * @author fu 2016-04-22
	 * @param orderNo
	 * @return 
	 */
	public PdPhoneSend getPdSendInfoList(String orderNo){
		//只查询已发货后的发货单
		//COMBINATION_PRODUCT_NO
		String sql = "select si_no,order_no,custom_code,recipient_name,order_flag from pd_send_info where order_flag>=2 and order_no = '"+orderNo+"'";
		List list = this.jdbcTemplate.queryForList(sql);
		PdPhoneSend pdPhoneSend = new PdPhoneSend();
		if((null!=list)&&list.size()>0){
				for(int i=0;i<list.size();i++){
						Map map = (Map) list.get(i);
						PdPhoneSendInfo pdPhoneSendInfo = new PdPhoneSendInfo();
						String siNo = (String)map.get("si_no");
						pdPhoneSendInfo.setSiNo(siNo);
						pdPhoneSendInfo.setMemberOrderNo((String)map.get("order_no"));
						pdPhoneSendInfo.setUserCode((String)map.get("custom_code"));
						pdPhoneSendInfo.setRecipientName((String)map.get("recipient_name"));
						BigDecimal orderFlag = (BigDecimal)map.get("order_flag");
						if("2".equals(orderFlag.toString())){
							pdPhoneSendInfo.setConfirmReceipt("N");
						}else{
							pdPhoneSendInfo.setConfirmReceipt("Y");
						}
		                String sqlDetail = " select a.product_no product_no," +
		                		" ( select product_name from jpm_product_sale_new b where b.company_code='CN' and b.product_no=a.product_no ) product_name, " +
		                		" a.qty qty,a.combination_product_no combination_product_no," +
		                		" ( select product_name from jpm_product_sale_new b where b.company_code='CN' and b.product_no=a.combination_product_no ) combination_product_name " +
		                		" from pd_send_info_detail a where a.si_no ='"+siNo+"'";
		                List listDetail = this.jdbcTemplate.queryForList(sqlDetail);
		                if((null!=listDetail)&&listDetail.size()>0){
		        			for(int m=0;m<listDetail.size();m++){
		        				Map mapDetail = (Map) listDetail.get(i);
		        				PdPhoneProduct pdPhoneProduct = new PdPhoneProduct();
		        				pdPhoneProduct.setProductNo((String)mapDetail.get("product_no"));
		        				pdPhoneProduct.setProductName((String)mapDetail.get("product_name"));
		        				BigDecimal qty = (BigDecimal)mapDetail.get("qty");
							    Integer qtyI = Integer.parseInt(qty.toString());
		        				pdPhoneProduct.setQty(qtyI);
		        				pdPhoneProduct.setCombinationProductNo((String)mapDetail.get("combination_product_no"));
		        				pdPhoneProduct.setCombinationProductName((String)mapDetail.get("combination_product_name"));
		        				pdPhoneSendInfo.getPdPhoneProductList().add(pdPhoneProduct);
		        			}	
					    }
		                pdPhoneSend.getPdPhoneSendInfoList().add(pdPhoneSendInfo);
			    }
	    }
		return pdPhoneSend;
   }

	@Override
	public List getPdSendInfoForMemberOrderNo(String memberOrderNo) {
		Log.info("手机物流跟踪号接口-在类PdSendInfoManagerImpl的方法中getPdSendInfoForMemberOrderNo运行,订单号"+memberOrderNo);
		String sql = "select tracking_no,sh_no,si_no from pd_send_info where order_no = '"+memberOrderNo+"'";
		List list = this.jdbcTemplate.queryForList(sql);
		return list;
	}
}
