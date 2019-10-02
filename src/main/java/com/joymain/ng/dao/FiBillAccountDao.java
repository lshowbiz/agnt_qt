package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FiBillAccount;

/**
 * An interface that provides a data management interface to the FiBillAccount table.
 */
public interface FiBillAccountDao extends GenericDao<FiBillAccount, String> {

	
	/**
	 * 根据商户号获取商户对象
	 * @param billAccountCode
	 * @return
	 */
	public FiBillAccount getFiBillAccountByBillAccountCode(
			String billAccountCode) ;
	
	/**
	 * 根据省份获取商户
	 * @param province
	 * @return
	 */
	public FiBillAccount getFiBillAccountByProvince(String province);
	
	/**
	 * 根据省份查询默认商户号
	 * @param province
	 * @return
	 */
	public FiBillAccount getDefaultFiBillAccountByProvince(String province);
	
	/**
	 * 随机获取一个默认收款商户
	 * @return
	 */
	public FiBillAccount getRandomFiBillAccountByDefault();
	
	/**
     * 根据经销商获取商户号
     * @param userCode
     * @return
     */
    public FiBillAccount getFiBillAccountByUserCode(String userCode, String accountType);
    
    /**
     * 根据会员编号获取经销商编号
     * @param userCode 会员编号
     * @return 经销商编号
     */
    public String getSaleCodeByUserCode(final String userCode);
}