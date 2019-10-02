package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.PdShipFeeDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.PdShipFee;

@Repository("pdShipFeeDao")
public class PdShipFeeDaoJpa extends GenericDaoHibernate<PdShipFee, String> implements PdShipFeeDao {

    public PdShipFeeDaoJpa() {
        super(PdShipFee.class);
    }

	@Override
	public PdShipFee getPdShipFeeByProvince(String province) {
		//String sql = "select t.psf_id,t.province_name,t.fee from PD_SHIP_FEE t where t.province_name='"+province+"' ";
		String hql = "from PdShipFee t where t.provinceName='"+province+"' ";
		Query query = this.getSession().createQuery(hql);
		List<PdShipFee> feeList = query.list();
		
		if(feeList !=null && feeList.size()>0)
			return feeList.get(0);

		return null;
	}
}
