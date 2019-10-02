package com.joymain.ng.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.FiBillAccountDao;
import com.joymain.ng.model.FiBillAccount;

@Repository("fiBillAccountDao")
@SuppressWarnings("rawtypes")
public class FiBillAccountDaoHibernate extends GenericDaoHibernate<FiBillAccount, String> implements FiBillAccountDao {
	final Integer STATUS_ENABLE = 0 ; //0：启用，1停用

	public FiBillAccountDaoHibernate() {
		super(FiBillAccount.class);
	}

	/**
	 * 根据商户号获取商户对象
	 * 
	 * @param billAccountCode
	 * @return
	 */
	@Override
	public FiBillAccount getFiBillAccountByBillAccountCode(String billAccountCode) {
		String hql = "from FiBillAccount where billAccountCode='" + billAccountCode + "'";
		return (FiBillAccount) this.getObjectByHqlQuery(hql);
	}

	/**
	 * 根据省份获取商户
	 * 
	 * @param province
	 * @return
	 */
	@Override
	public FiBillAccount getFiBillAccountByProvince(String province) {

		// String sql =
		// "with tt as(select a.BILL_ACCOUNT_CODE from FI_BILL_ACCOUNT a left join FI_BILL_ACCOUNT_WARNING b on a.bill_account_code=b.bill_account_code where a.province='"+province+"' and a.status='1' and (a.income_limit-b.now_total_amount)>0 order by (a.income_limit-b.now_total_amount) desc) select BILL_ACCOUNT_CODE from tt where rownum=1";
		//
		// try{
		// Map resultMap = jdbcTemplate.queryForMap(sql);
		//
		// if(resultMap!=null && resultMap.get("BILL_ACCOUNT_CODE")!=null){
		//
		// String accountCode = (String) resultMap.get("BILL_ACCOUNT_CODE");
		//
		// FiBillAccount mfiBillAccount =
		// this.getFiBillAccountByBillAccountCode(accountCode);
		//
		// return mfiBillAccount;
		// }else{
		//
		// return null;
		// }
		// }catch(ClassCastException ex){
		// return null;
		// }

		String queryStr = "select * from FI_BILL_ACCOUNT a left join FI_BILL_ACCOUNT_WARNING b on a.bill_account_code=b.bill_account_code where a.province='" + province
				+ "' and a.status='"+STATUS_ENABLE+"' and a.is_default='0' and (a.income_limit-b.now_total_amount)>0 order by (a.income_limit-b.now_total_amount) desc";

		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(queryStr);

		if (list.isEmpty()) {

			return null;
		} else {

			// 获取到值后，跳出循环，实际上只获取第一行记录
			String accountCode = "";
			for (Map<String, Object> m : list) {

				accountCode = (String) m.get("BILL_ACCOUNT_CODE");

				if (!("").equals(accountCode))
					break;
			}

			FiBillAccount mfiBillAccount = this.getFiBillAccountByBillAccountCode(accountCode);

			return mfiBillAccount;
		}
	}

	/**
	 * 根据省份查询默认商户号
	 * 
	 * @param province
	 * @return
	 */
	@Override
	public FiBillAccount getDefaultFiBillAccountByProvince(String province) {
		String hql = "from FiBillAccount where isDefault='1' and province='" + province + "' and status='"+STATUS_ENABLE+"'";
		List list = this.getSession().createQuery(hql).list();
		if (list.isEmpty()) {
			return this.getRandomFiBillAccountByDefault();
		} else {
			return (FiBillAccount) list.get(0);
		}
	}

	/**
	 * 随机获取一个默认收款商户
	 * 
	 * @return
	 */
	@Override
	public FiBillAccount getRandomFiBillAccountByDefault() {
		String hql = "from FiBillAccount where isDefault='1' and status='"+STATUS_ENABLE+"'";
		List list = this.getSession().createQuery(hql).list();
		if (list.isEmpty()) {
			return null;
		} else {
			return (FiBillAccount) list.get(0);
		}
	}

	/**
	 * 根据经销商获取商户号
	 */
	@Override
	public FiBillAccount getFiBillAccountByUserCode(String userCode, String accountType) {
		// String hql =
		// "from FiBillAccount where userCode='"+userCode+"' and accountType='"+accountType+"' and status='1'";
		String hql = "from FiBillAccount where userCode='" + userCode + "' and status='"+STATUS_ENABLE+"' and business_type='2' ";
		try {
			return (FiBillAccount) getObjectByHqlQuery(hql);
		} catch (ClassCastException ex) {

			return null;
		}
	}

	/**
	 * 根据会员编号获取经销商编号
	 * 
	 * @param userCode
	 *            会员编号
	 * @return 经销商编号
	 */
	@Override
	public String getSaleCodeByUserCode(final String userCode) {
		String saleCode = "";
		List list = jdbcTemplate.queryForList("select Fn_Get_account_code('" + userCode + "') as topuser_code from dual");
		if (!list.isEmpty()) {
			Map map = (Map) list.get(0);
			if (map != null) {
				saleCode = String.valueOf(map.get("topuser_code").toString());
			}
		}
		return saleCode;
	}
}
