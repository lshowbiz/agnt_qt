package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.joymain.ng.model.LinkmanGoods;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.LinkmanGoodsDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

@Repository("linkmanGoodsDao")
public class LinkmanGoodsDaoJpa extends GenericDaoHibernate<LinkmanGoods, Long> implements LinkmanGoodsDao {

    public LinkmanGoodsDaoJpa() {
        super(LinkmanGoods.class);
    }

    /**
	 * 客户管理-客户的商品-初始化(有条件)查询
	 * @author gw 2013-10-08
	 * @param userCode
	 * @param name
	 * @param buyTimeBegin
	 * @param buyTimeEnd
	 * @param buyQuantityBegin
	 * @param buyQuantityEnd
	 * @return list
	 */
	public List getLinkmanGoodsList(String userCode, String name,
			String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,
			String buyQuantityEnd) {
		String sql = "select a.name name ,b.buy_goods buyGoods,to_char(b.buy_time,'yyyy-MM-dd')  buyTime,b.buy_quantity buyQuantity,b.id id,b.linkman_id linkmanId " +
				" from  linkman a ,linkman_goods b " +
				" where a.id = b.linkman_id and b.user_code = ? ";
		String params = userCode;
		if(!StringUtil.isEmpty(name)){
			sql +=" and a.name like '%"+name+"%'";
		}
		if(!StringUtil.isEmpty(buyTimeBegin)){
			sql += " and b.buy_time >=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			params = params+","+buyTimeBegin;
		}
		if(!StringUtil.isEmpty(buyTimeEnd)){
			sql += " and b.buy_time <=to_date(?,'yyyy-MM-dd hh24:mi:ss') ";
			params = params+","+buyTimeEnd;
		}
		if(!StringUtil.isEmpty(buyQuantityBegin)){
			sql +=  " and b.buy_quantity >= "+buyQuantityBegin;
			//params = params+","+buyQuantityBegin;
		}
		if(!StringUtil.isEmpty(buyQuantityEnd)){
			sql += " and b.buy_quantity <= "+buyQuantityEnd;
			//params = params +","+buyQuantityEnd;
		}
		Object[] parameters = params.split(",");
		return this.jdbcTemplate.queryForList(sql,parameters);
	}

	/**
	 * 客户管理-客户的商品-详细查询
	 * @author gw 2013-0-10-08
	 * @param id
	 * @return linkmanGoods
	 */
	public LinkmanGoods getLinkmanGoodsById(String id) {
		Long idl = Long.parseLong(id);
		Query query = getSession().createQuery(" from LinkmanGoods a where a.id= ?");
		query.setParameter(0, idl);
		List<LinkmanGoods> list = query.list();
		if(list!=null && list.size()!=0){
			return (LinkmanGoods)list.get(0);
		}
		return null;
	}

	/**
     * 客户管理-客户的商品-修改或新增之前不为空的校验
     * @author gw 2013-10-09
     * @param linkmanGoods
     * @return boolean
     */
	public boolean getLinkmanGoodsCheckEmpty(LinkmanGoods linkmanGoods,BindingResult errors) {
		String linkmanId = linkmanGoods.getLinkmanId();
		if(StringUtil.isEmpty(linkmanId)){
			StringUtil.getErrorsFormat(errors, "isNotNull", "linkmanId", "linkman.name");
			return true;
		}
		String buyGoods = linkmanGoods.getBuyGoods();
		if(StringUtil.isEmpty(buyGoods)){
			StringUtil.getErrorsFormat(errors, "isNotNull", "buyGoods", "pdWarehouseStock.productNo");
			return true;
		}
		
		String buyQuantity = linkmanGoods.getBuyQuantity();
		if(!StringUtil.isEmpty(buyQuantity)){
			if(this.getPattern("^[0-9]*", buyQuantity)){
				StringUtil.getErrorsFormat(errors, "errors.buyGoods", "buyGoods", "linkmanGoods.buyGoods");
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

	/**
	 * 客户管理-客户的商品-修改或录入操作
	 * @author gw 2013-10-09
	 * @param linkmanGoods
	 */
	public void updateOrAddLinkmanGoods(LinkmanGoods linkmanGoods) {
		this.save(linkmanGoods);
	}

}
