package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.FiBcoinPayconfigDetail;
import com.joymain.ng.dao.FiBcoinPayconfigDetailDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("fiBcoinPayconfigDetailDao")
public class FiBcoinPayconfigDetailDaoHibernate extends GenericDaoHibernate<FiBcoinPayconfigDetail, Long> implements FiBcoinPayconfigDetailDao {

    public FiBcoinPayconfigDetailDaoHibernate() {
        super(FiBcoinPayconfigDetail.class);
    }

	@Override
	public FiBcoinPayconfigDetail getFiBcoinPayconfigDetailsByProductNo(String configId, String productNo) {
		
		if(StringUtils.isNotEmpty(productNo)){
			
			String hql = "from FiBcoinPayconfigDetail fiBcoinPayconfigDetail where configId="+configId+" and productNo='"+productNo+"'";

			try{
				return (FiBcoinPayconfigDetail) this.getObjectByHqlQuery(hql);
			}catch(ClassCastException ex){
				
				return null;
			}
		}else{
			
			return null;
		}	
	}
	
	@Override
	public FiBcoinPayconfigDetail getFiBcoinPayconfigDetailByProductId(
			String productId) {
		
		String hql = "from FiBcoinPayconfigDetail fiBcoinPayconfigDetail where productNo='"+productId+"'";
		
		try{
			return (FiBcoinPayconfigDetail) this.getObjectByHqlQuery(hql);
		}catch(ClassCastException ex){
			
			return null;
		}
	}
}
