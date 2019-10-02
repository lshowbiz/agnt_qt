
package com.joymain.ng.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiBankbookTempDao;
import com.joymain.ng.model.FiBankbookTemp;

@Repository("fiBankbookTempDao")
public class FiBankbookTempDaoHibernate extends GenericDaoHibernate<FiBankbookTemp, Long> implements FiBankbookTempDao {

    public FiBankbookTempDaoHibernate() {
		super(FiBankbookTemp.class);
		// TODO Auto-generated constructor stub
	}
	
    /**
     * 获取某用户的存折条数
     */
	@Override
	public long getCountByDate(String companyCode, String userCode,
			String bankbookType) {
		
		return this.getJdbcTemplate().queryForLong("select count(*) as totalCount from FI_BANKBOOK_TEMP where COMPANY_CODE='" + companyCode + "' and BANKBOOK_TYPE ='" + bankbookType + "' and USER_CODE='" + userCode + "'");
	}

	@Override
	public void saveFiBankbookTemp(FiBankbookTemp fiBankbookTemp) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(fiBankbookTemp);
	}
}
