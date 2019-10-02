package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JpmProduct;
import com.joymain.ng.model.PdLogisticsBaseDetail;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.PdLogisticsBaseDetailDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("pdLogisticsBaseDetailDao")
public class PdLogisticsBaseDetailDaoHibernate extends GenericDaoHibernate<PdLogisticsBaseDetail, Long> implements PdLogisticsBaseDetailDao {

    public PdLogisticsBaseDetailDaoHibernate() {
        super(PdLogisticsBaseDetail.class);
    }

    /**
	 * 查询套餐商品在这个物流跟踪单号下的子商品
	 * @author gw 2015-05-04
	 * @param numId PdLogisticsBaseNum所关联表的主键
	 * @param productNo
	 * @return list
	 */
	public List<PdLogisticsBaseDetail> getlistpdLogisticsBaseDetail(Long numId,String productNo) {
		String hql = " from PdLogisticsBaseDetail pdLogisticsBaseDetail where pdLogisticsBaseDetail.numId='"+numId+"' and " +
				" pdLogisticsBaseDetail.combination_product_no='"+productNo+"'";
		List<PdLogisticsBaseDetail> list = getSession().createQuery(hql).list();
		if(null!=list){
			if(list.size()>0){
				return list;
			}
		}
		return null;
	}

	/**
	 * 套餐物流信息展示-根据商品编码获取商品名称
	 * @author gw 2015-05-08
	 * @param productNo 商品编码
	 * @return string
	 */
	public String getProductName(String productNo) {
		if(!StringUtil.isEmpty(productNo)){
			String hql = " from JpmProduct jpmProduct where jpmProduct.productNo='"+productNo+"' " ;
			List<JpmProduct> list = getSession().createQuery(hql).list();
			if(null!=list){
				if(list.size()>0){
					return list.get(0).getProductName();
				}
			}
		}
		return null;
	}
	
}
