package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiTransferFundbookDao;
import com.joymain.ng.model.FiTransferFundbook;
import com.joymain.ng.model.FiTransferFundbook;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiFundbookJournalManager;
import com.joymain.ng.service.FiTransferFundbookManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("fiTransferFundbookManager")
@WebService(serviceName = "FiTransferFundbookService", endpointInterface = "com.joymain.ng.service.FiTransferFundbookManager")
public class FiTransferFundbookManagerImpl extends GenericManagerImpl<FiTransferFundbook, Long> implements FiTransferFundbookManager {
    FiTransferFundbookDao fiTransferFundbookDao;
    //产业化基金服务管理对象
  	private FiFundbookJournalManager fiFundbookJournalManager;
  	
    @Autowired
    public FiTransferFundbookManagerImpl(FiTransferFundbookDao fiTransferFundbookDao) {
        super(fiTransferFundbookDao);
        this.fiTransferFundbookDao = fiTransferFundbookDao;
    }
	
    @Autowired
	public void setFiFundbookJournalManager(
			FiFundbookJournalManager fiFundbookJournalManager) {
		this.fiFundbookJournalManager = fiFundbookJournalManager;
	}

	public Pager<FiTransferFundbook> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiTransferFundbookDao.getPager(FiTransferFundbook.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	@Override
	public List getFiTransferFundbookListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime) {
		// TODO Auto-generated method stub
		return fiTransferFundbookDao.getFiTransferFundbookListByUserCodePage(page,userCode,dealStartTime,dealEndTime);
	}
	
	/**
	 * 创建转账单
	 * 业务规则：保存转账单、从产业基金帐户扣钱 同步执行
	 * @throws Exception 
	 */
	@Override
	public void doTransferFundbookDeduct(FiTransferFundbook fiTransferFundbook, JsysUser transferUser) throws Exception{
		
		Long fId = this.fiTransferFundbookDao.saveFiTransferFundbookGetId(fiTransferFundbook);
		
		//转账金额
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = fiTransferFundbook.getMoney();
		
		//资金类型
		Integer[] moneyType = new Integer[1];
		
		//说明
		String[] notes = new String[1];
		
		
		if(("1").equals(fiTransferFundbook.getBankbookType()) && ("1").equals(fiTransferFundbook.getTransferType())){
			
			moneyType[0] = 2;//分红基金转他人分红基金
			notes[0] = "分红基金转账支出";
		}
		if(("1").equals(fiTransferFundbook.getBankbookType()) && ("2").equals(fiTransferFundbook.getTransferType())){
					
					moneyType[0] = 3;//分红基金转定向基金
					notes[0] = "分红基金转账支出";
		}
		if(("1").equals(fiTransferFundbook.getBankbookType()) && ("3").equals(fiTransferFundbook.getTransferType())){
			
			moneyType[0] = 4;//分红基金转发展基金
			notes[0] = "分红基金转账支出";
		}
		if(("2").equals(fiTransferFundbook.getBankbookType())){
			
			moneyType[0] = 6;//定向基金转入他人定向基金
			notes[0] = "定向基金转账支出";
		}

		//从用户帐户扣取金额
		fiFundbookJournalManager.saveFiBankbookDeduct("CN", transferUser.getUserCode(), moneyType, moneyArray, transferUser.getUserCode(), transferUser.getUsername(), "CYJJ"+fId, notes, fiTransferFundbook.getBankbookType(), "1");
	}
}