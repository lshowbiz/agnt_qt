package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FoundationOrderDao;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.FiLovecoinJournalManager;
import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

@Service("foundationOrderManager")
@WebService(serviceName = "FoundationOrderService", endpointInterface = "com.joymain.ng.service.FoundationOrderManager")
public class FoundationOrderManagerImpl extends GenericManagerImpl<FoundationOrder, Long> implements FoundationOrderManager {
    FoundationOrderDao foundationOrderDao;
    private FiLovecoinJournalManager fiLovecoinJournalManager;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    private FiBankbookJournalManager fiBankbookJournalManager;
    
    @Autowired
    public void setFiBankbookJournalManager(
			FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}
    
	@Autowired
    public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}
    @Autowired
    public void setFiLovecoinJournalManager(FiLovecoinJournalManager fiLovecoinJournalManager) {
        this.fiLovecoinJournalManager = fiLovecoinJournalManager;
    }

    @Autowired
    public FoundationOrderManagerImpl(FoundationOrderDao foundationOrderDao) {
        super(foundationOrderDao);
        this.foundationOrderDao = foundationOrderDao;
    }
	
	public Pager<FoundationOrder> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return foundationOrderDao.getPager(FoundationOrder.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List<FoundationOrder> getFoundationOrdersByUserCode(String userCode,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		return foundationOrderDao.getFoundationOrdersByUserCode(userCode, startTime, endTime);
	}

	@Override
	public List getOrdersByItemTypeAndTime(String userCode) {
		// TODO Auto-generated method stub
		return foundationOrderDao.getOrdersByItemTypeAndTime(userCode);
	}

	/**
	 * 慈善公益基金审单
	 */
	@Override
	public void checkFoundationOrder(FoundationOrder foundationOrder,
			JsysUser sysUser) throws AppException {
		
		//1.扣钱
		//公司编码
		String companyCode = sysUser.getCompanyCode();
		
		//资金类别
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 99;//99代表审核公益产品订单
		
		//要扣的金额
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = foundationOrder.getAmount();
		
		//摘要
		String[] notes = new String[1];
		notes[0] = "慈善公益订单支付,订单号：" + foundationOrder.getOrderId();
		
		//电子存折扣钱
		jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), "CJ"+foundationOrder.getOrderId(), notes,"1");
		
		//2.审单
        foundationOrder.setStatus("1");
        foundationOrder.setStartTime(new Date());
        dao.save(foundationOrder);
		
		//3.赠送爱心积分,爱心积分认购活动才赠送积分
        if(("2").equals(foundationOrder.getFoundationItem().getType())){
        	
        	this.saveLoveCoin(foundationOrder, sysUser);  
        }             
	}
	
	/**
	 * 电子存折+发展基金支付慈善公益基金审单
	 * @throws Exception 
	 */
	@Override
	public void checkFoundationOrderByJJ(FoundationOrder foundationOrder, BigDecimal jJamount, BigDecimal cZamount, JsysUser sysUser) throws Exception {
		
		//1.发展基金扣钱部分
		//公司编码
		String companyCode = sysUser.getCompanyCode();
		
		//资金类别
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 99;//99代表审核公益产品订单
		
		//要扣的金额
		BigDecimal[] jJmoneyArray = new BigDecimal[1];
		jJmoneyArray[0] = jJamount;
		
		//摘要
		String[] notes = new String[1];
		notes[0] = "慈善公益订单支付,订单号：" + foundationOrder.getOrderId();
		
		//电子存折扣钱
		fiBankbookJournalManager.saveFiBankbookDeduct(companyCode, sysUser, moneyType, jJmoneyArray, sysUser.getUserCode(), sysUser.getUserName(), "CJ"+foundationOrder.getOrderId(), notes,"1","1");
		
		//2.电子存折扣钱部分		
		if(cZamount.compareTo(BigDecimal.ZERO) == 1){//电子存折支付部分金额大于0
			
			//要扣的金额
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0] = cZamount;

			//电子存折扣钱
			jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), "CJ"+foundationOrder.getOrderId(), notes,"1");
		}
		
		//3.审单
        foundationOrder.setStatus("1");
        foundationOrder.setStartTime(new Date());
        dao.save(foundationOrder);
		
		//4.赠送爱心积分,爱心积分认购活动才赠送积分
        if(("2").equals(foundationOrder.getFoundationItem().getType())){
        	
        	this.saveLoveCoin(foundationOrder, sysUser);  
        }       
	}
	
	/**
	 * 赠送爱心积分具体处理方法
	 * @param foundationOrder
	 * @param sysUser
	 */
	public void saveLoveCoin(FoundationOrder foundationOrder,JsysUser sysUser) throws AppException{
		
		//爱心积分账户开户(会自动判断账户是否存在，不存在的情况下才会创建)
		fiLovecoinJournalManager.createLovecoin(sysUser.getUserCode());
		
		 //资金类型
        Integer[] moneyType = new Integer[1];
		moneyType[0] = 99;//99代表审核公益产品订单
		
		//赠送的爱心积分金额
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = foundationOrder.getAmount();
		
		//摘要
		String[] notes = new String[1];
		
		if(StringUtil.isEmpty(foundationOrder.getField2())){
			notes[0] = "慈善公益订单支付赠送爱心积分,订单号：" + foundationOrder.getOrderId();
		}else{
			notes[0] = "慈善公益订单支付赠送爱心积分,订单号：" + foundationOrder.getOrderId()+";捐赠附言："+foundationOrder.getField2();
		}
		
		Long[] appId = new Long[1];
		appId[0] = 2l;
		
		//爱心积分账户进账
        fiLovecoinJournalManager.saveFiPayDataVerify("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), foundationOrder.getOrderId().toString(), notes, appId, null);
	}
	@Override
	public Long saveFoundationOrder(FoundationOrder foundationOrder) {
		
		return foundationOrderDao.saveFoundationOrder(foundationOrder);
	}

}
