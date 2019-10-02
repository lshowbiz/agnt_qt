package com.joymain.ng.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.FiBillAccountDao;
import com.joymain.ng.model.FiBillAccount;
import com.joymain.ng.model.PayAccount;
import com.joymain.ng.service.FiBillAccountService;

@Service("fiBillAccountService")
@WebService(serviceName = "FiBillAccountService", endpointInterface = "com.joymain.ng.service.FiBillAccountService")
public class FiBillAccountServiceImpl implements FiBillAccountService {
    
    @Autowired
	private FiBillAccountDao dao;

    public void setFiBillAccountDao(FiBillAccountDao dao) {
        this.dao = dao;
    }

	/**
	 * 根据会员编号获取经销商编号
	 * @param userCode 会员编号
	 * @return 经销商编号
	 */
	@Override
	public String getSaleCodeByUserCode(final String userCode) {
        
		 return dao.getSaleCodeByUserCode(userCode);
        
	}

	/**
	 * 根据会员编号、终端类型获取支付商户号对象
	 * @param userCode 会员编号
	 * @param accountType 终端类型，1：PC，2：移动终端
	 * @return PayAccount
	 */
	@Override
	public PayAccount getPayAccountByUserCode(String userCode,
			String accountType) {
		
		String saleCode = this.getSaleCodeByUserCode(userCode);//"CN11136424";
		
		FiBillAccount fiBillAccount = dao.getFiBillAccountByUserCode(saleCode,accountType);
		
		if(fiBillAccount != null){
			PayAccount payAccount = new PayAccount();
			payAccount.setAccountId(fiBillAccount.getAccountId());
			payAccount.setUserCode(fiBillAccount.getUserCode());
			payAccount.setAccountCode(fiBillAccount.getBillAccountCode());
			payAccount.setAccountType(fiBillAccount.getAccountType());
			payAccount.setProviderType(fiBillAccount.getProviderType());
			payAccount.setMaxMoney(fiBillAccount.getMaxMoney());
			//fiBillAccount.getPassword();
			return payAccount;
		}
		
		return null;
	}
	
	public FiBillAccount getFiBillAccountByUserCode(String userCode,String accountType) {
		String saleCode = this.getSaleCodeByUserCode(userCode);//"CN11136424";
		return dao.getFiBillAccountByUserCode(saleCode,accountType);
	}
}
