package com.joymain.ng.dao.jpa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.joymain.ng.model.JpoShippingFee;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.JpoShippingFeeDao;

import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("jpoShippingFeeDao")
public class JpoShippingFeeDaoJpa extends GenericDaoHibernate<JpoShippingFee, Long> implements JpoShippingFeeDao {

    public JpoShippingFeeDaoJpa() {
        super(JpoShippingFee.class);
    }

	@Override
	public JpoShippingFee getJpoShippingFee(String shippingType,String pro,
			String cityId, String district,String countrCode) {
	                                  
		String sql = "from JpoShippingFee t where t.shippingType='"+shippingType+"' ";
		if(countrCode!=null && !"null".equals(countrCode)){
			sql += " and t.countryCode='"+countrCode+"' ";
		}
		if(StringUtils.isNotBlank(district) &&  !"null".equalsIgnoreCase(district)){
			sql +=" and t.district='"+district+"' ";
		}else if(StringUtil.blankOrNull(cityId)){
			sql +=" and t.city='"+cityId+"' ";
		}else {
			sql +=" and t.province='"+pro+"' ";
		}
		
		log.info("dao shippingFee sql ="+sql);
		Query query = this.getSession().createQuery(sql);
		List<JpoShippingFee> feeList = query.list();
		if(feeList !=null && feeList.size()>0)
			return feeList.get(0); 
		else
			return null;
	}
	
	public BigDecimal getFee(JpoShippingFee fee,BigDecimal totle){
		BigDecimal shippingFee = new BigDecimal(0);
		if(totle.compareTo(new BigDecimal(0)) <= 0){
			shippingFee = new BigDecimal(0);
		}else if(totle.compareTo(fee.getShippingWeight()) <= 0){
			shippingFee = fee.getShippingFee();
		}else{
			shippingFee = fee.getShippingFee().add((totle.setScale(0, RoundingMode.UP).subtract(fee.getShippingWeight())).divide(fee.getShippingReweight(),2).setScale(0,RoundingMode.UP).multiply(fee.getShippingRefee()));			
		}
		return shippingFee;
	}
	
	public BigDecimal getFeeV(JpoShippingFee fee,BigDecimal totle){
		BigDecimal shippingFee = new BigDecimal(0);
		if(totle.compareTo(new BigDecimal(0)) <= 0){
			shippingFee = new BigDecimal(0);
		}else{
			shippingFee = fee.getShippingFee().add((totle.setScale(0, RoundingMode.UP)).divide(fee.getShippingReweight(),2).setScale(0,RoundingMode.UP).multiply(fee.getShippingRefee()));			
		}
		return shippingFee;
	}
	
	public BigDecimal getFeeWZ(JpoShippingFee fee,BigDecimal totle){
		BigDecimal shippingFee = new BigDecimal(0);
		if(totle.compareTo(new BigDecimal(0)) <= 0){
			shippingFee = new BigDecimal(0);
		}else{
			shippingFee = fee.getShippingFee().add((totle.setScale(0, RoundingMode.UP)).multiply(fee.getShippingRefee()));			
		}
		return shippingFee;
	}
}
