package com.joymain.ng.service;

import javax.jws.WebService;

import com.joymain.ng.model.FiBillAccount;
import com.joymain.ng.model.PayAccount;
@WebService
public interface FiBillAccountService {
    
	/**
	 * 根据会员编号、终端类型获取支付商户号对象
	 * @param userCode 会员编号
	 * @param accountType 终端类型，1：PC，2：移动终端
	 * @return PayAccount
	 */
	public PayAccount getPayAccountByUserCode(final String userCode, final String accountType);
	
	/**
	 * 根据会员编号、终端类型获取支付商户号对象
	 * @param userCode 会员编号
	 * @param accountType 终端类型，1：PC，2：移动终端
	 * @return PayAccount
	 */
	public FiBillAccount getFiBillAccountByUserCode(final String userCode, final String accountType);
	
	/**
	 * 根据会员编号获取经销商编号
	 * @param userCode 会员编号
	 * @return 经销商编号
	 */
	public String getSaleCodeByUserCode(final String userCode);
}