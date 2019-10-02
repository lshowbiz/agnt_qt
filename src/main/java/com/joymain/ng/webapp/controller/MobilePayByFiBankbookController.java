package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.PromotionsUtils;

/**
 * 手机发展基金支付服务器端接口
 * @author hywen
 *
 */
@Controller
@RequestMapping("mobileJjPay")
@SuppressWarnings("rawtypes")
public class MobilePayByFiBankbookController extends BaseFormController {
	
	private Log log = LogFactory.getLog(MobilePayByFiBankbookController.class);
	
	@Autowired
	private FiBankbookJournalManager fiBankbookJournalManager;
	
	@Autowired
	private FiBankbookBalanceManager fiBankbookBalanceManager;
	
	@Autowired
	private JsysUserManager jsysUserManager;
	
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;
	
	
	/**
	 * 查询发展基金帐户交易流水
	 * @param userId 用户编码
	 * @param dealStartTime 开始时间
	 * @param dealEndTime 结束时间
	 * @return 交易流水
	 */
    @RequestMapping(value="api/getJjJournals")
	@ResponseBody
	public List getFiBankbookJournalListByUserCode(String userId, String token,String dealStartTime, String dealEndTime,int pageNum,int pageSize){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		if(!StringUtil.isEmpty(dealStartTime)&&!StringUtil.isEmpty(dealEndTime)&&dealStartTime.equals(dealEndTime)){
			dealEndTime+=" 23:59:59";
		}
		//查询交易流水
    	List<FiBankbookJournal> fiBankbookJournalList=fiBankbookJournalManager.getFiBankbookJournalListByUser(userId,dealStartTime,dealEndTime,pageNum,pageSize);

    	return fiBankbookJournalList;
	}
    
    /**
     * 查询发展基金帐户余额
     * @param userId 用户编码
     * @return 发展基金帐户余额
     */
    @RequestMapping(value="api/getJjBalance")
	@ResponseBody
	public String getFiBankbookBalanceManagerByUserCode(String userId,String token){
    	 JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	 Object object = jsysUserManager.getAuthErrorCode(user, "String");
 		 if(null!=object){
 		 	return (String)object;
 		 }
    	FiBankbookBalance fiBankbookBalance=fiBankbookBalanceManager.getFiBankbookBalance(userId, "1");
    	return fiBankbookBalance.getBalance().toString();
    }
    
    /**
     * 使用发展基金支付订单
     * @param userId 用户编号
     * @param orderId  订单id
     * @param userNum  使用发展基金数
     * @return 返回码：1代表支付成功, 0代表支付失败
     */
    @RequestMapping(value="api/payOrderByJj")
	@ResponseBody
    public String payJpoMemberOrderByFiBankbook(String userId, String orderId, String userNum, String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
    	
    	if(null!=object){
			return (String)object;
		}
    	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
    	JpoMemberOrder order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
    	if(!checkNumByOrder(jpoMemberOrder))
        {
            return "error";
        }
    	if(validateOrder(jpoMemberOrder,user)){
			log.info(jpoMemberOrder.getMemberOrderNo() + 
					LocaleUtil.getLocalText("user.validate"));
			return "error";
		}  
    	//订单为空，订单状态已审核，不是当前用户
    	if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !userId.equals(jpoMemberOrder.getSysUser().getUserCode())){
    		
    		return "error";
    	}
    	
    	//判断是否包含三代无忧产品
        if(this.checkSDWYProductNo(jpoMemberOrder)){
        	
        	return "error";
        }
    	
//    	//端午商品促销控制
//    	Integer proSum=0, proNoCount=0,countQty=0;
//		String proCount_str = ConfigUtil.getConfigValue("CN", "product.count");
//		log.info("product sum is:["+proCount_str+"]");
//		if(StringUtils.isNotBlank(proCount_str)){
//			proNoCount = Integer.parseInt(proCount_str);
//		}
//    	Iterator<JpoMemberOrderList> ite = jpoMemberOrder.getJpoMemberOrderList().iterator();
//    	while(ite.hasNext()){
//    		JpoMemberOrderList orderList = ite.next();
//    		String proNo = orderList.getJpmProductSaleTeamType().
//    				getJpmProductSaleNew().getProductNo();
//    		if(proNo.equalsIgnoreCase(Constants.PRONO) || 
//    				proNo.equalsIgnoreCase(Constants.PRONO1) || 
//    				proNo.equalsIgnoreCase(Constants.PRONO2)){
//    			
//    			Integer temQty = 0;
//    			if(proNo.equalsIgnoreCase(Constants.PRONO)){
//    				temQty =orderList.getQty();
//    			}else if(proNo.equalsIgnoreCase(Constants.PRONO1)){
//    				temQty = orderList.getQty() * 3;
//    			} else if(proNo.equalsIgnoreCase(Constants.PRONO2)){
//    				temQty= orderList.getQty() * 5;
//    			}
//    			countQty += temQty;
//    			
//    			proSum = jpoMemberOrderListManager.getSumQtyByProNo();
//    			
//    			if((proNoCount-proSum)<countQty){
//    				return "error";
//    			}
//    		}
//    		
//    		log.info("proNoCount =["+proNoCount+"] " +
//    				"and proSum is=["+proSum+"] countQty="+countQty);
//    		
//    		if(proSum >= proNoCount){
//    			return "error";
//    		}
//    		
//    	}
        //库存不足
        if (isOverQty(jpoMemberOrder)) {
        	return "error";
		}
        
        //验证是否停售
        String val = PromotionsUtils.verifyOrder(jpoMemberOrder);
		if(StringUtils.isNotEmpty(val)){
			return "error";
		}
    	//验证产品购买数量是否超过限制
    	if(isBuyPro(jpoMemberOrder)){
    		return "error";
    	}
    	
    	BigDecimal amount = new BigDecimal(userNum);
    	if(amount.compareTo(new BigDecimal("0"))==1){
    		
    		order.setPayByJj("1");
    		
    		//判断,设置
    		if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){
    			
    			//使用发展基金数>=订单金额，全部用基金支付
    			order.setJjAmount(jpoMemberOrder.getAmount());
//    			order.setAmount(new BigDecimal("0"));
        	}else{
        		
        		//使用发展基金数<订单金额，其余部分用电子存折支付
        		order.setJjAmount(amount);
//        		order.setAmount(jpoMemberOrder.getAmount().subtract(amount));
        	}
    	}
    	
    	boolean b = false;
    	//调用支付服务
    	try{
    		
			if("1".equals(jpoMemberOrder.getPayByJj())){
				log.info("MobilePayByFiBankbookController start:"+order.getMemberOrderNo());
				jpoMemberOrderManager.checkJpoMemberOrderByJJ(order, user,"2");
				log.info("MobilePayByFiBankbookController stop:"+order.getMemberOrderNo());
				//Modify By WUCF 20160805 新增订单支付来源标示字段：PAYMENT_TYPE修改
				jpoMemberOrderManager.updateJpoMemberOrderPaymentType(String.valueOf(jpoMemberOrder.getMoId()), "3");
				log.info("updateJpoMemberOrderPaymentType stop:"+order.getMemberOrderNo());
			}else{
				log.info("MobilePayByFiBankbookController start:"+order.getMemberOrderNo());
				jpoMemberOrderManager.checkJpoMemberOrder(order, user,"2");
				log.info("MobilePayByFiBankbookController stop:"+order.getMemberOrderNo());
				//Modify By WUCF 20160805 新增订单支付来源标示字段：PAYMENT_TYPE修改
				jpoMemberOrderManager.updateJpoMemberOrderPaymentType(String.valueOf(jpoMemberOrder.getMoId()), "1");
				log.info("updateJpoMemberOrderPaymentType stop:"+order.getMemberOrderNo());
			}
			b = true;
		
//			log.info("call function start,orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//			jpoMemberOrderDao.callSTJFunction(jpoMemberOrder.getMemberOrderNo(), 1);
//			log.info("call function END. ");
		}catch(Exception app){

			return "0";//支付失败
		}
    	/*支付改造
		if(b){
			try {
				//Modify By WuCF 20150921 调用MQ消息队列
				jpoMemberOrder.setStatus("3");
				jpoMemberOrderManager.save(jpoMemberOrder);
				jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, user, "2");
				log.info("=======审单MQ：MobilePayByFiBankbookController orderNo is:"+jpoMemberOrder.getMemberOrderNo());
			} catch (Exception e) {
				log.error(jpoMemberOrder.getMemberOrderNo()+" 发送mq消息失败：",e);
			}
		}
		*/
		return "1";//支付成功
    }
   
  
    /**
	 * 判断是否为三代无忧奖励计划产品、台湾旅游积分产品：产品编号N07010100101CN0,N07020100101CN0
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkSDWYProductNo(JpoMemberOrder jpoMemberOrder){
		
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		
		if("N07010100101CN0".equals(productNo) || "N07020100101CN0".equals(productNo)){
			
			return true;
		}
		return false;
	}
	
	public boolean checkNumByOrder(JpoMemberOrder jpoMemberOrder)
    {
        Iterator it = jpoMemberOrder.getJpoMemberOrderList().iterator();
        //迭代出订单下商品
        while (it.hasNext())
        {
            
            JpoMemberOrderList orderList = (JpoMemberOrderList) it.next();
            String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
            
            //已购买数量
            Integer proNum = jpoMemberOrderListManager.getSumQtyByProNo(proNo);
            
            //新订单数量+已购买数量=总数量
            Integer num = proNum + orderList.getQty(); 
            return checkNum(proNo,num);
            
        }
        return true;
    }
    
    public boolean checkNum(String proNo,Integer num)
    {
        if(Constants.PROC.equals(proNo))
        {
            if(num > Constants.PROCnum)
            {
                return false;
            }
        }
        else if(Constants.PROC1.equals(proNo))
        {
            if(num > Constants.PROC1num)
            {
                return false;
            }
        }
        else if(Constants.PROC2.equals(proNo))
        {
            if(num > Constants.PROC2num)
            {
                return false;
            }
        }
        else if(Constants.PROC3.equals(proNo))
        {
            if(num > Constants.PROC3num)
            {
                return false;
            }
        }
        else if(Constants.PROC4.equals(proNo))
        {
            if(num > Constants.PROC4num)
            {
                return false;
            }
        }
        else if(Constants.PROC5.equals(proNo))
        {
            if(num > Constants.PROC5num)
            {
                return false;
            }
        }
        else if(Constants.PROC6.equals(proNo))
        {
            if(num > Constants.PROC6num)
            {
                return false;
            }
        }
        else if(Constants.PROC7.equals(proNo))
        {
            if(num > Constants.PROC7num)
            {
                return false;
            }
        }
        return true;
    }

	public JpoMemberOrderDao getJpoMemberOrderDao() {
		return jpoMemberOrderDao;
	}

	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
    
}
