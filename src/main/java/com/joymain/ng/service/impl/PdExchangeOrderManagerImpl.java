package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmProductCombinationDao;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.JpoMemberOrderListDao;
import com.joymain.ng.dao.PdExchangeOrderBackDao;
import com.joymain.ng.dao.PdExchangeOrderDao;
import com.joymain.ng.dao.PdExchangeOrderDetailDao;
import com.joymain.ng.model.JpmProduct;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JpmProductCombinationManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.PdExchangeOrderManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.util.pay.PassbookVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

@Service("pdExchangeOrderManager")
@WebService(serviceName = "PdExchangeOrderService", endpointInterface = "com.joymain.ng.service.PdExchangeOrderManager")
public class PdExchangeOrderManagerImpl extends GenericManagerImpl<PdExchangeOrder, String> implements PdExchangeOrderManager {
	@Autowired
	private PdExchangeOrderDao pdExchangeOrderDao;
	
	@Autowired
	private JfiBankbookJournalManager jfiBankbookJournalManager; //modify by fu 2016-04-26 自助换货单支付用到
	
	@Override
	public List<PdExchangeOrder> getPdExchangeOrders() {
		// TODO Auto-generated method stub
		return pdExchangeOrderDao.getPdExchangeOrders();
	}

	@Override
	public PdExchangeOrder getPdExchangeOrder(String eoNo) {
		// TODO Auto-generated method stub
		return pdExchangeOrderDao.getPdExchangeOrder(eoNo);
	}

	@Override
	public void savePdExchangeOrder(PdExchangeOrder pdExchangeOrder) {
		// TODO Auto-generated method stub
		pdExchangeOrderDao.savePdExchangeOrder(pdExchangeOrder);
	}

	@Override
	public void removePdExchangeOrder(String eoNo) {
		// TODO Auto-generated method stub
		pdExchangeOrderDao.remove(eoNo);
	}

	@Override
	public List getPdExchangeOrdersByCrm(CommonRecord crm, GroupPage page) {
		// TODO Auto-generated method stub
		return pdExchangeOrderDao.getPdExchangeOrdersByCrm(crm, page);
	}

	@Override
	public boolean hasProductCountByAsNo(String asNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void confirmPdExchangeOrder(PdExchangeOrder pdExchangeOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getSumGroupByAst(CommonRecord crm) {
		// TODO Auto-generated method stub
		return pdExchangeOrderDao.getSumGroupByAst(crm);
	}
	
	/**
     * 自助换货单的支付
     * @author fu 2016-04-25
     * @param eoNo 换货单号
     * @return String
     */
    public String payPdExchangeOrder(String eoNo){
    	String result = "fail";
    	try{
    		    log.info("在PdExchangeOrderManagerImpl类的payPdExchangeOrder方法中运行，自助换货单支付，换货单号为："+eoNo);
		    	//SELF_REPLACEMENT=Y（表示是自助换货单）并且ORDER_FLAG=1（表示已审核）并且正常状况（CANCEL_EXCHANGE is null）
		    	//A换货单需要支付，表pd_exchange_order的NEED_PAY=Y（表示需要支付）并且已经支付IS_PAY=Y（表示已支付）
		    	//已审核、正常状况、需要支付且未支付的自助换货单才允许支付
		    	PdExchangeOrder pdExchangeOrder = pdExchangeOrderDao.getPdExchangeOrder(eoNo);
		    	if(null!=pdExchangeOrder){
			    		Long orderFlag = pdExchangeOrder.getOrderFlag();//换货单状态
			    		String cancelExchange = pdExchangeOrder.getCancelExchange();//是否取消自助换货单：Y取消，空恢复
			    		String selfCheckStatus = pdExchangeOrder.getSelfCheckStatus();//自助换货单审核状态：Y审核通过，N审核不通过，空表示初始化制单的时候不用赋值 
			    		String needPay = pdExchangeOrder.getNeedPay();//是否需要支付:Y表示需要支付，N或空表示不需要支付
			    		String isPay = pdExchangeOrder.getIsPay();//是否支付:Y是，N或空表示否,B表示自助换货单不需要支付
			    		
			    		if(((null!=orderFlag)&&"1".equals(orderFlag.toString()))
			    				&&(StringUtil.isEmpty(cancelExchange))
			    				&&((null!=selfCheckStatus)&&"Y".equals(selfCheckStatus))
			    				&&((!StringUtil.isEmpty(needPay))&&"Y".equals(needPay))
			    				&&((StringUtil.isEmpty(isPay))||"N".equals(isPay))){
			    		                PassbookVO passbookVO = this.getPassbookVO(pdExchangeOrder);
			    		                log.info("在PdExchangeOrderManagerImpl类的payPdExchangeOrder方法中运行，自助换货单证准备支付，换货单号为："+eoNo);
						    			Map map = jfiBankbookJournalManager.saveDebitPassBook(passbookVO);
			    			            Boolean isPayBoolean = (Boolean)map.get("isPay");
			    			            String msg = (String)map.get("msg");
			    			            if(isPayBoolean){
			    			            	//自助换单支付成功，那么需要将自助换货单的状态修改支付成功
			    			            	boolean resetIsPay = this.reSetIsPay(eoNo);
			    			            	if(resetIsPay){
			    			            		 log.info("在PdExchangeOrderManagerImpl类的payPdExchangeOrder方法中运行，自助换货单的修改支付成功状态");
			    			            		 result = "succ";
			    			            	}
			    			            }else{
			    			            	result = msg;
			    			            }
			    		}else{
			    			result = "nopay";
			    		}
    	        }
    	}catch(Exception e){
              log.info("在PdExchangeOrderManagerImpl类的payPdExchangeOrder方法中运行，自助换货单支付异常："+e.toString());
    	      e.printStackTrace();
    	}
    	return result;
    }
    
    
	/**
     * 获取自助换货单支付时需要的对象PassbookVO
     * @author fu 2016-04-26
     * @param pdExchangeOrder
     * @return passbookVO
     */
    private PassbookVO getPassbookVO(PdExchangeOrder pdExchangeOrder){
        log.info("在PdExchangeOrderManagerImpl类的getPassbookVO方法中运行，获取自助换货单支付时需要的对象PassbookVO");
    	PassbookVO passbookVO = new PassbookVO();//自助换货单存折支付对象
		passbookVO.setCompanyCode(pdExchangeOrder.getCompanyCode());
		passbookVO.setJsysUser(pdExchangeOrder.getSysUser());
		passbookVO.setOperaterCode(pdExchangeOrder.getSysUser().getUserCode());
		passbookVO.setOperaterName(pdExchangeOrder.getSysUser().getUserName());
		passbookVO.setUniqueCode(pdExchangeOrder.getEoNo());
		Integer[] moneyType = { 191 };//资金类别，自助换货单的支付为191
		passbookVO.setMoneyTypes(moneyType);
		/*Long needPayAmount = pdExchangeOrder.getNeedPayAmount();//自助换货单需要支付的金额 
		BigDecimal needPayAmountB = new BigDecimal(needPayAmount.toString());*/
		BigDecimal needPayAmountB = pdExchangeOrder.getNeedPayAmount();//自助换货单需要支付的金额 
		BigDecimal[] moneys = {needPayAmountB};//支付金额
		passbookVO.setMoneys(moneys);
		String[] notes = {pdExchangeOrder.getEoNo()};
		passbookVO.setNotes(notes);
		passbookVO.setDataType("1");//PC端支付
		return passbookVO;
    }
    
    /**
     * 自助换货单的支付状态修改
     * @author fu 2016-04-26
     * @param eoNo 换货单号
     * @return boolean true自助换货单的修改支付状态成功；false自助换货单的修改支付状态失败
     */
    private boolean reSetIsPay(String eoNo) {
		 log.info("在PdExchangeOrderManagerImpl类的reSetIsPay方法中运行，自助换货单的支付状态修改");
		 return pdExchangeOrderDao.reSetIsPay(eoNo);
	}

	@Override
	public String getOrderFlagByEoNo(String eoNo) {
		// TODO Auto-generated method stub
		return pdExchangeOrderDao.getOrderFlagByEoNo(eoNo);
	}
    
	/**
	 * 根据会员编号获取会员的已审核、正常状况、需要支付且未支付的自助换货单
	 * @author fu 2016-04-25
	 * @param userCode 会员编号
	 * @param startIndex 开始查询行数
	 * @param endIndex 结束查询行数
	 * @return list
	 */
	public List getPdExchangeOrderByUsercode(String userCode, Integer startIndex, Integer endIndex){
		return pdExchangeOrderDao.getPdExchangeOrderByUsercode(userCode,startIndex,endIndex);
	}

	@Override
	public List getPdExchangeOrdersByCrmSql(CommonRecord crm, GroupPage page) {
		
		return pdExchangeOrderDao.getPdExchangeOrdersByCrmSql(crm, page);
	}
    
   
}