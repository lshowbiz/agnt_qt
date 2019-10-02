package com.joymain.ng.dao.jpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;




import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.User;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.JpoShoppingCartOrderDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository("jpoShoppingCartOrderDao")
public class JpoShoppingCartOrderDaoJpa extends GenericDaoHibernate<JpoShoppingCartOrder, Long> implements JpoShoppingCartOrderDao {

    public JpoShoppingCartOrderDaoJpa() {
        super(JpoShoppingCartOrder.class);
    }

   //查询购物车中购买的产品
    public JpoShoppingCartOrder getJpoShoppingCartOrder(JpoShoppingCart jpoShoppingCart)  {
    	/*Query q=getSession().createQuery("from JpoShoppingCartOrder sc where orderType='"+JpoShoppingCart.getOrderType()+"' and" +
    			" sc.sysUser.userCode='"+JpoShoppingCart.getUserCode()+"' " +
    			"and companyCode='"+JpoShoppingCart.getCompanyCode()+"' " +
    			"and teamType='"+JpoShoppingCart.getTeamType()+"'");
    	List<JpoShoppingCartOrder> scOrderS=q.list();
    	if(scOrderS!=null && scOrderS.size()!=0)
    	{	    	    
    		return (JpoShoppingCartOrder)scOrderS.get(0);
    	} 
    	return null;*/
    	//Modify By WuCF 20140630 查询语句修改成绑定变量
    	StringBuffer hql = new StringBuffer("from JpoShoppingCartOrder sc where sc.orderType= :orderType ");
    	hql.append(" and sc.sysUser.userCode= :userCode ");
    	hql.append(" and sc.companyCode= :companyCode ");
    	hql.append(" and sc.teamType= :teamType ");
    	Query q=getSession().createQuery(hql.toString());
    	q.setParameter("orderType",jpoShoppingCart.getOrderType());
		q.setParameter("userCode",jpoShoppingCart.getUserCode());
	    q.setParameter("companyCode",jpoShoppingCart.getCompanyCode());
	    q.setParameter("teamType",jpoShoppingCart.getTeamType());
	    List<JpoShoppingCartOrder> list = q.list();
	    if(list!=null && list.size()>=1){	    	   
	    	return (JpoShoppingCartOrder)list.get(0);
	    } 
		return null;
    }
    
	//手机端查询购物车中的商品
	public  List<JpoShoppingCartOrder>  getMoilbeJpoShoppingCartOrder(JpoShoppingCart JpoShoppingCart){
		   Query q=getSession().createQuery("from JpoShoppingCartOrder sc where isMobile='"+JpoShoppingCart.getIsMobile()+"' and" +
	          		" sc.sysUser.userCode='"+JpoShoppingCart.getUserCode()+"' " +
	          		"and companyCode='"+JpoShoppingCart.getCompanyCode()+"' " +
	          		"and teamType='"+JpoShoppingCart.getTeamType()+"'");
		   List<JpoShoppingCartOrder>    scOrderS=q.list();
	       return scOrderS;
       
	}
	//判断首购，一级店铺升级，一级店铺首购，二级店铺升级，二级店铺首购是否已经绑定了指定的商品
	public List getJpoShoppingCartBindingProduct(JpoShoppingCart JpoShoppingCart)	
	{
		String sqlQuery="select * from jpo_shopping_cart_order sc ," +
				"jpo_shopping_cart_order_list scl where sc.order_type='"+JpoShoppingCart.getOrderType()+"'" +
				"and  sc.user_code='"+JpoShoppingCart.getUserCode()+"' and sc.team_type='"+JpoShoppingCart.getTeamType()+"' and scl.product_status='1' and scl.sc_id=sc.sc_id";
	   return this.getJdbcTemplate().queryForList(sqlQuery);
	}
	//查询会员自己加入购物车中的商品
	public List<JpoShoppingCartOrder>  getJpoScOrderList(JpoShoppingCart jpoShoppingCart)
	{
		/*String hql="from JpoShoppingCartOrder sc where sc.companyCode='"+jpoShoppingCart.getCompanyCode()+"'" +
		"  and sc.sysUser.userCode='"+jpoShoppingCart.getUserCode()+"'";
		       if("1".equals(jpoShoppingCart.getIsCheck()))
				{
			       hql+=" and sc.isCheck='"+jpoShoppingCart.getIsCheck()+"'";
			
				}
//		       if(!StringUtils.isEmpty(jpoShoppingCart.getIsMobile()))
//		       {
//		    	   hql+="and sc.isMobile='"+jpoShoppingCart.getIsMobile()+"'";
//		       }
		       if(!StringUtils.isEmpty(jpoShoppingCart.getOrderType()))
		       {
		    	   hql+="and sc.orderType='"+jpoShoppingCart.getOrderType()+"'";
		       }
		Query q=getSession().createQuery(hql);
		List<JpoShoppingCartOrder> scOrderS=q.list();
		if(scOrderS.size()>0)
		{
			return scOrderS;
		}
		return null;*/
		StringBuffer hql = new StringBuffer("from JpoShoppingCartOrder sc where sc.companyCode= :companyCode ");
		hql.append(" and sc.sysUser.userCode= :userCode ");
		if("1".equals(jpoShoppingCart.getIsCheck())){
			hql.append(" and sc.isCheck= :isCheck ");
		}
		if(!StringUtils.isEmpty(jpoShoppingCart.getOrderType())){
			hql.append(" and sc.orderType= :orderType ");
		}
		Query query=getSession().createQuery(hql.toString());
		StringUtil.dealSetParameter(query,"companyCode",jpoShoppingCart.getCompanyCode());
		StringUtil.dealSetParameter(query,"userCode",jpoShoppingCart.getUserCode());
		if("1".equals(jpoShoppingCart.getIsCheck())){
			StringUtil.dealSetParameter(query,"isCheck",jpoShoppingCart.getIsCheck());
		}
		if(!StringUtils.isEmpty(jpoShoppingCart.getOrderType())){
			StringUtil.dealSetParameter(query,"orderType",jpoShoppingCart.getOrderType());
		}
		List<JpoShoppingCartOrder> scOrderS=query.list();
		if(scOrderS.size()>0)
		{
			return scOrderS;
		}
		return null;
	}
	
	//查询会员所有购物车中的数量
   public int getShoppinigCartSum(JpoShoppingCart jpoShoppingCart)
   {
	  
	   /*String  sqlQuery="select nvl(sum(qty),0) as total from jpo_shopping_cart_order_list ol where ol.sc_id " +
	   		"in(select so.sc_id  from jpo_shopping_cart_order so where " +
	   		"so.company_code='"+JpoShoppingCart.getCompanyCode()+"' " +
	   		"and so.user_code='"+JpoShoppingCart.getUserCode()+"' ";
	   		if(!StringUtil.isEmpty(JpoShoppingCart.getOrderType())){
	   			sqlQuery+="and so.order_type='"+JpoShoppingCart.getOrderType()+"' ";
	   		}
	   		sqlQuery+=")"; 	   		
	   Map map = (Map)this.findOneObjectBySQL(sqlQuery);
	   return Integer.parseInt(map.get("total").toString());*/	   
	   //Modify By WuCF 20140703 绑定变量
	   //预编译参数
	   StringBuffer paramsBuf = new StringBuffer("");
		
	   //查询语句
	   StringBuffer sqlQuery= new StringBuffer("select nvl(sum(qty),0) as total from jpo_shopping_cart_order_list ol where ");
	   sqlQuery.append(" exists (select 1 from jpo_shopping_cart_order so where so.sc_id=ol.sc_id ");
	   sqlQuery.append(" and so.company_code=? ");
	   paramsBuf.append(","+jpoShoppingCart.getCompanyCode());
	   
	   sqlQuery.append(" and so.user_code=? ");
	   paramsBuf.append(","+jpoShoppingCart.getUserCode());
	   
	   if(!StringUtil.isEmpty(jpoShoppingCart.getOrderType())){
		   sqlQuery.append(" and so.order_type=? ");
		   paramsBuf.append(","+jpoShoppingCart.getOrderType());
	   }
	   sqlQuery.append(")"); 	   		
	   //返回时调用分页的查询的方法 
	   Object[] parameters = paramsBuf.toString().substring(1).split(",");
	   return this.getJdbcTemplate().queryForInt(sqlQuery.toString(),parameters); 	 
   }

	@Override
	public void deleMobileJpoShoppingCartOrder(long sc_id) {
		String sql="delete jpo_shopping_cart_order t where t.sc_id="+sc_id;
		this.jdbcTemplate.execute(sql);
		String sql2="delete jpo_shopping_cart_order_list t where t.sc_id ="+sc_id;
		this.jdbcTemplate.execute(sql2);
	}
	

	
}
