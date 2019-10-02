package com.joymain.ng.dao.jpa;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanDemandDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.LinkmanDemand;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("linkmanDemandDao")
public class LinkmanDemandDaoJpa extends GenericDaoHibernate<LinkmanDemand, Long> implements LinkmanDemandDao {

    public LinkmanDemandDaoJpa() {
        super(LinkmanDemand.class);
    }

    /**
	 * 客户管理-客户需求-查询(初始化查询或有条件查询)-----使用了绑定变量
	 * @author gw 2013-09-25
	 * @param userCode
	 * @param linkmanName
	 * @param registerTimeBegin
	 * @param registerTimeEnd
	 * @return  List
	 */
	public List getLinkmanDemandList(String userCode, String linkmanName,
			String registerTimeBegin, String registerTimeEnd) {
		String sql = " select a.id id,a.linkman_Id linkman_Id,a.customer_Demand customer_Demand,a.customers_Wishes customers_Wishes,a.interests interests,a.good_Sports good_Sports," +
				" a.favoritw_Activity favoritw_Activity,to_char(a.register_Time,'yyyy-MM-dd')  register_Time,b.name name" +
				" from linkman_demand a,linkman b  where a.linkman_id = b.id and a.user_code = ? ";
		String params = userCode;
		if(!StringUtil.isEmpty(linkmanName)){
			sql += " and b.name like '%"+linkmanName+"%' ";
			//params = params +","+linkmanName;
		}
		if(!StringUtil.isEmpty(registerTimeBegin)){
			sql += " and a.register_Time>=to_date(?,'yyyy-MM-dd hh24:mi:ss ') ";
			params = params +","+registerTimeBegin;
		}
		if(!StringUtil.isEmpty(registerTimeEnd)){
			sql += " and a.register_Time<=to_date(?,'yyyy-MM-dd hh24:mi:ss ') ";
			params = params +","+registerTimeEnd;
		}
		Object[] parameters = params.split(",");
		return this.jdbcTemplate.queryForList(sql,parameters);
	}
	
	/**
	 * 分页
	 * 客户管理-客户需求-查询(初始化查询或有条件查询)
	 * @author WuCF 2013-12-02
	 * @param userCode
	 * @param linkmanName
	 * @param registerTimeBegin
	 * @param registerTimeEnd
	 * @return
	 */
	public List getLinkmanDemandListPage(GroupPage page,String userCode, String linkmanName,
			String registerTimeBegin, String registerTimeEnd) {
		String sql = " select a.id id,a.linkman_Id linkman_Id,a.customer_Demand customer_Demand,a.customers_Wishes customers_Wishes,a.interests interests,a.good_Sports good_Sports," +
				" a.favoritw_Activity favoritw_Activity,to_char(a.register_Time,'yyyy-MM-dd')  register_Time,b.name name" +
				" from linkman_demand a,linkman b  where a.linkman_id = b.id and a.user_code = '"+userCode+"' ";
		String params = userCode;
		if(!StringUtil.isEmpty(linkmanName)){
			sql += " and b.name like '%"+linkmanName+"%' ";
			//params = params +","+linkmanName;
		}
		if(!StringUtil.isEmpty(registerTimeBegin)){
			sql += " and a.register_Time>=to_date('"+registerTimeBegin+"','yyyy-MM-dd hh24:mi:ss ') ";
//			params = params +","+registerTimeBegin;
		}
		if(!StringUtil.isEmpty(registerTimeEnd)){
			sql += " and a.register_Time<=to_date('"+registerTimeEnd+"','yyyy-MM-dd hh24:mi:ss ') ";
//			params = params +","+registerTimeEnd;
		}
//		Object[] parameters = params.split(",");
//		return this.jdbcTemplate.queryForList(sql,parameters);
		return this.findObjectsBySQL(sql, page);
	}

	/**
	 * 客户管理-客户需求 -详细查询
	 * @author gw  2013-09-25
	 * @param  id 
	 * @return linkmanDemand
	 */
	public LinkmanDemand getLinkmanDemand(String id) {
		String hql = " from LinkmanDemand where id = '"+id+"'";
		return (LinkmanDemand) this.getObjectByHqlQuery(hql);
	}

	/**
	 * 客户需求--修改/客户需求分析-修改
	 * @author gw  2013-09-26
	 * @param linkmanDemand
	 */
	public void updateLinkmanDemand(LinkmanDemand linkmanDemand) {
          this.save(linkmanDemand);		
	}

	/**
	 * 客户需求分析录入(修改)时,不为空的校验
	 * @author gw 2013-09-30
	 * @param userCode
	 * @param linkmanDemand
	 * @return boolean
	 */
	public boolean getLinkmanDemandEmptyCheck(String userCode,
			LinkmanDemand linkmanDemand,BindingResult errors) {
		String meetDemandMeasure = linkmanDemand.getMeetDemandMeasure();
		if(StringUtil.isEmpty(meetDemandMeasure)){
			StringUtil.getErrorsFormat(errors, "isNotNull", "meetDemandMeasure", "linkmanDemand.meetDemandMeasure");
            return true;
		}
		
		String suitableProducts = linkmanDemand.getSuitableProducts();
		if(StringUtil.isEmpty(suitableProducts)){
			StringUtil.getErrorsFormat(errors,"isNotNull","suitableProducts","linkmanDemand.suitableProducts");
			return true;
		}
		return false;
	}

	/**
	 * 客户需求在修改或者录入之前做不为空的校验
	 * @author gw 2013-09-30
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	public boolean getLinkmanDemandCheckEmpty(LinkmanDemand linkmanDemand,
			BindingResult errors) {
		String linkmanId = linkmanDemand.getLinkmanId();
		if(StringUtil.isEmpty(linkmanId)){
			StringUtil.getErrorsFormat(errors,"isNotNull","linkmanId","relationshipRecord.contact");
			return true;
		}
		String customerDemand = linkmanDemand.getCustomerDemand();
		if(StringUtil.isEmpty(customerDemand)){
			StringUtil.getErrorsFormat(errors,"isNotNull","customerDemand","linkmanDemand.customerDemand");
			return true;
		}
		String customersWishes = linkmanDemand.getCustomersWishes();
		if(StringUtil.isEmpty(customersWishes)){
			StringUtil.getErrorsFormat(errors,"isNotNull","suitableProducts","linkmanDemand.customersWishes");
			return true;
		}
		return false;
	}

	/**
	 * 客户管理-客户的商品-初始化查询或有条件查询(修改)
	 * @author gw  2013-10-15
	 * @param userCode
	 * @param name
	 * @param buyTimeBegin
	 * @param buyTimeEnd
	 * @param buyQuantityBegin
	 * @param buyQuantityEnd
	 * @return list
	 */
	public List getLinkmanDemandGoodsList(String userCode, String name,
			String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,
			String buyQuantityEnd) {
		
		     String sql = " select a.id id,a.linkman_id linkman_id,a.buy_goods buy_goods,to_char(a.buy_time,'yyyy-MM-dd') buy_time,a.buy_quantity buy_quantity,b.name name" +
		                  " from linkman_demand a,linkman b  where a.linkman_id = b.id and a.user_code = ? ";
			String params = userCode;
			if(!StringUtil.isEmpty(name)){
				sql += " and b.name like '%"+name+"%' ";
			}
			if(!StringUtil.isEmpty(buyTimeBegin)){
				sql += " and a.buy_time>=to_date(?,'yyyy-MM-dd hh24:mi:ss ') ";
				params = params +","+buyTimeBegin;
			}
			if(!StringUtil.isEmpty(buyTimeEnd)){
				sql += " and a.buy_time<=to_date(?,'yyyy-MM-dd hh24:mi:ss ') ";
				params = params +","+buyTimeEnd;
			}
			if(!StringUtil.isEmpty(buyQuantityBegin)){
				sql +=  " and a.buy_quantity >= "+buyQuantityBegin;
			}
			if(!StringUtil.isEmpty(buyQuantityEnd)){
				sql +=  " and a.buy_quantity <= "+buyQuantityEnd;
			}
			Object[] parameters = params.split(",");
            return this.jdbcTemplate.queryForList(sql,parameters);
	}
	
	/**
	 * 分页
	 * 客户管理-客户的商品-初始化查询或有条件查询(修改)
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param name
	 * @param buyTimeBegin
	 * @param buyTimeEnd
	 * @param buyQuantityBegin
	 * @param buyQuantityEnd
	 * @return list
	 */
	public List getLinkmanDemandGoodsListPage(GroupPage page,String userCode, String name,
			String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,
			String buyQuantityEnd) {
		
		     String sql = " select a.id id,a.linkman_id linkman_id,a.buy_goods buy_goods,to_char(a.buy_time,'yyyy-MM-dd') buy_time,a.buy_quantity buy_quantity,b.name name" +
		                  " from linkman_demand a,linkman b  where a.linkman_id = b.id and a.user_code = '"+userCode+"' ";
			String params = userCode;
			if(!StringUtil.isEmpty(name)){
				sql += " and b.name like '%"+name+"%' ";
			}
			if(!StringUtil.isEmpty(buyTimeBegin)){
				sql += " and a.buy_time>=to_date('"+buyTimeBegin+"','yyyy-MM-dd hh24:mi:ss ') ";
//				params = params +","+buyTimeBegin;
			}
			if(!StringUtil.isEmpty(buyTimeEnd)){
				sql += " and a.buy_time<=to_date('"+buyTimeEnd+"','yyyy-MM-dd hh24:mi:ss ') ";
//				params = params +","+buyTimeEnd;
			}
			if(!StringUtil.isEmpty(buyQuantityBegin)){
				sql +=  " and a.buy_quantity >= "+buyQuantityBegin;
			}
			if(!StringUtil.isEmpty(buyQuantityEnd)){
				sql +=  " and a.buy_quantity <= "+buyQuantityEnd;
			}
//			Object[] parameters = params.split(",");
//          return this.jdbcTemplate.queryForList(sql,parameters);
            return this.findObjectsBySQL(sql, page);
	}

	/**
	 * 客户管理-客户的商品-录入或修改之前不为空的校验
	 * @author gw 2013-10-15
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	public boolean getLinkmanDemandGoodsCheckEmpty(LinkmanDemand linkmanDemand,BindingResult errors) {
		String buyGoods = linkmanDemand.getBuyGoods();
		if(StringUtil.isEmpty(buyGoods)){
			StringUtil.getErrorsFormat(errors, "isNotNull", "buyGoods", "pdWarehouseStock.productNo");
			return true;
		}
		String buyQuantity = linkmanDemand.getBuyQuantity();
		if(!StringUtil.isEmpty(buyQuantity)){
			if(this.getPattern("^[0-9]*", buyQuantity)){
				StringUtil.getErrorsFormat(errors, "errors.buyGoods", "buyQuantity", "linkmanDemand.buyQuantity");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 纯数字校验
	 * @author gw 2013-10-10
	 * @param expressions
	 * @param str
	 * @return
	 */
	private boolean getPattern(String expressions,String str){
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}
	
}
		
    
