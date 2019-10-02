package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiBillAccountDao;
import com.joymain.ng.dao.FiBillAccountRelationDao;
import com.joymain.ng.dao.FiCommonAddrDao;
import com.joymain.ng.dao.FiGetbillaccountLogDao;
import com.joymain.ng.dao.JmiAddrBookDao;
import com.joymain.ng.model.FiBillAccount;
import com.joymain.ng.model.FiBillAccountRelation;
import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.FiGetbillaccountLog;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.service.FiBillAccountManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

@Service("fiBillAccountManager")
@WebService(serviceName = "FiBillAccountService", endpointInterface = "com.joymain.ng.service.FiBillAccountManager")
public class FiBillAccountManagerImpl extends GenericManagerImpl<FiBillAccount, String> implements FiBillAccountManager {
    
	FiBillAccountDao fiBillAccountDao;
    FiCommonAddrDao fiCommonAddrDao;
    FiBillAccountRelationDao fiBillAccountRelationDao;
    FiGetbillaccountLogDao fiGetbillaccountLogDao;
    
    @Autowired
    public void setFiGetbillaccountLogDao(
			FiGetbillaccountLogDao fiGetbillaccountLogDao) {
		this.fiGetbillaccountLogDao = fiGetbillaccountLogDao;
	}

	@Autowired
    public void setFiBillAccountRelationDao(
			FiBillAccountRelationDao fiBillAccountRelationDao) {
		this.fiBillAccountRelationDao = fiBillAccountRelationDao;
	}

	@Autowired
	public void setFiCommonAddrDao(FiCommonAddrDao fiCommonAddrDao) {
		this.fiCommonAddrDao = fiCommonAddrDao;
	}

	@Autowired
    public FiBillAccountManagerImpl(FiBillAccountDao fiBillAccountDao) {
        super(fiBillAccountDao);
        this.fiBillAccountDao = fiBillAccountDao;
    }
	
	public Pager<FiBillAccount> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBillAccountDao.getPager(FiBillAccount.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	/**
	 * 根据会议编号获取商户号
	 * @param userCode 会员编号
	 * @return 快钱商户对象
	 */
	@Override
	public FiBillAccount getFiBillAccountByUserCode(String userCode) {
		
		//快钱商户对象
		FiBillAccount fiBillAccount=null;
		
		//获取会员常用收货地址
		FiCommonAddr fiCommonAddr = fiCommonAddrDao.get(userCode);
		
		if(fiCommonAddr!=null){
			
			//查询该省份下面差额最大的商户号，同时差额必须大于0
			fiBillAccount = fiBillAccountDao.getFiBillAccountByProvince(fiCommonAddr.getProvince());
			
			//如果找不到匹配的商户号
			if(fiBillAccount==null){
				
				//查询该省份下面的默认商户号
				fiBillAccount = fiBillAccountDao.getDefaultFiBillAccountByProvince(fiCommonAddr.getProvince());
			}
		}
		
		return fiBillAccount;
	}

	@Override
	public FiBillAccount getFiBillAccountByBillAccountCode(
			String billAccountCode) {
		// TODO Auto-generated method stub
		return this.fiBillAccountDao.getFiBillAccountByBillAccountCode(billAccountCode);
	}
}