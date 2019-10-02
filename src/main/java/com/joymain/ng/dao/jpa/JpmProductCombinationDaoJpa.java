package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmProductCombinationDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JpmProductCombination;
import com.joymain.ng.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository("jpmProductCombinationDao")
public class JpmProductCombinationDaoJpa extends GenericDaoHibernate<JpmProductCombination, Long> implements JpmProductCombinationDao {

	public JpmProductCombinationDaoJpa() {
		super(JpmProductCombination.class);
	}

	public List<Map<String, Object>> getJpmProductList(String productNo) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuf = new StringBuffer("SELECT A.PRODUCT_NO PNO,A.SUB_PRODUCT_NO SUBNO,B.PRODUCT_NAME name,A.QTY,B.COMBINE_FLAG flag from v_pm_combination a left join JPM_PRODUCT b on a.sub_product_no=b.product_no  where 1=1  ");
		if (StringUtils.isNotEmpty(productNo)) {
			sqlBuf.append(" and a.product_no= '").append(productNo).append("'").append(" order  by flag desc");
			log.info("getJpmProductSaleRelatedList:" + sqlBuf.toString());
			SQLQuery query = getSession().createSQLQuery(sqlBuf.toString());
			// 设定结果结果集中的每个对象为Map类型
			query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} else {
			log.info("getJpmProductSaleRelatedList:null没有传递参数");
			return null;
		}
	}
	
	/**
	 * 根据商品编号判断商品是否是套餐商品
	 * @author fx 2015-05-04
	 * @param productNo
	 * @return boolean
	 */
	public boolean getIsisCombinationProduct(String productNo) {
		if(!StringUtil.isEmpty(productNo)){
		     StringBuffer sqlBuf = new StringBuffer("select a.product_no from jpm_product a where combine_flag='1' ");
		     sqlBuf.append(" and a.product_no= '").append(productNo).append("'");
		     List list = this.jdbcTemplate.queryForList(sqlBuf.toString());
		     if(null!=list){
		    	 if(list.size()>0){
		    		 return true;
		    	 }
		     }
		     return false;
		}
		return false;
	}

}
