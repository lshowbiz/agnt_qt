
package com.joymain.ng.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JfiBankbookTempDao;
import com.joymain.ng.model.JfiBankbookTemp;

@Repository("jfiBankbookTempDao")
public class JfiBankbookTempDaoHibernate extends GenericDaoHibernate<JfiBankbookTemp, Long> implements JfiBankbookTempDao {

    public JfiBankbookTempDaoHibernate() {
		super(JfiBankbookTemp.class);
		// TODO Auto-generated constructor stub
	}

    /**
	 * 获取某用户的存折条数
	 * @param companyCode
	 * @param agentNo
	 * @return
	 */
	@Override
	public long getCountByDate(final String companyCode, final String userCode) {
		return this.getJdbcTemplate().queryForLong("select count(*) as totalCount from JFI_BANKBOOK_TEMP where COMPANY_CODE='" + companyCode + "' and USER_CODE='" + userCode + "'");
	}

	@Override
	public void saveJfiBankbookTemp(JfiBankbookTemp jfiBankbookTemp){
		this.getSession().saveOrUpdate(jfiBankbookTemp);
	}
}
