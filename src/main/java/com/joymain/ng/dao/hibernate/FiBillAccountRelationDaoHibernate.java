package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.FiBillAccountRelation;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.FiBillAccountRelationDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("fiBillAccountRelationDao")
public class FiBillAccountRelationDaoHibernate extends GenericDaoHibernate<FiBillAccountRelation, Long> implements FiBillAccountRelationDao {

    public FiBillAccountRelationDaoHibernate() {
        super(FiBillAccountRelation.class);
    }
    
    /**
	 * 根据省市区三个条件查询商户list,用于验证是否重叠
	 * @param mfiBillAccountRelation
	 * @return 列表
	 */
	@Override
	public List getFiBillAccountRelationsByConditions(FiBillAccountRelation mfiBillAccountRelation) {
		
		String hql="from FiBillAccountRelation t where 1=1";
		
		if(!StringUtil.isEmpty(mfiBillAccountRelation.getDistrict())){
			
			hql+=" and t.district='"+mfiBillAccountRelation.getDistrict()+"'";
		}
		if(!StringUtil.isEmpty(mfiBillAccountRelation.getCity())){
			
			hql+=" and t.city='"+mfiBillAccountRelation.getCity()+"'";
		}
		if(!StringUtil.isEmpty(mfiBillAccountRelation.getProvince())){
			
			hql+=" and t.province='"+mfiBillAccountRelation.getProvince()+"'";
		}
		
		return this.getSession().createSQLQuery(hql).list();
	}

	/**
	 * 根据条件匹配一个对应的商户,用于快钱支付时候选择商户号
	 * @param mfiBillAccountRelation
	 * @return 商户对象
	 */
	@Override
	public FiBillAccountRelation getAccountCodeByConditions(FiBillAccountRelation fiBillAccountRelation) {
		
		//第一种情况，匹配区
		List list = this.getFiBillAccountRelationsByConditions(fiBillAccountRelation);
		if(list.size()==1){
			
			return (FiBillAccountRelation) list.get(0);
		}else{
			
			//第2种情况,匹配市
			fiBillAccountRelation.setDistrict(null);
			List list2 = this.getFiBillAccountRelationsByConditions(fiBillAccountRelation);
			
			if(list2.size()==1){
				
				return (FiBillAccountRelation) list2.get(0);
			}else{
				
				//第3种情况，匹配省
				fiBillAccountRelation.setCity(null);
				List list3 = this.getFiBillAccountRelationsByConditions(fiBillAccountRelation);
				
				if(list3.size()==1){
					
					return (FiBillAccountRelation) list3.get(0);
				}
			}
		}
		
		return null;
	}
}
