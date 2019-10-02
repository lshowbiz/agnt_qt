
package com.joymain.ng.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiProductPointTempDao;
import com.joymain.ng.model.FiProductPointTemp;

@Repository("fiProductPointTempDao")
public class FiProductPointTempDaoHibernate extends GenericDaoHibernate<FiProductPointTemp, Long> implements FiProductPointTempDao {

    public FiProductPointTempDaoHibernate() {
		super(FiProductPointTemp.class);
	}
	
    /**
     * 获取某用户的存折条数
     */
	@Override
	public long getCountByDate(String companyCode, String userCode,
			String bankbookType) {
		
		return this.getJdbcTemplate().queryForLong("select count(*) as totalCount from FI_PRODUCT_POINT_TEMP where COMPANY_CODE='" + companyCode + "' and PRODUCT_POINT_TYPE ='" + bankbookType + "' and USER_CODE='" + userCode + "'");
	}

	@Override
	public void saveFiProductPointTemp(FiProductPointTemp fiProductPointTemp) {
		this.getSession().saveOrUpdate(fiProductPointTemp);
	}
}
