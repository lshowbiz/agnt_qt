package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalCountry;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalCountryManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
/**
 * 基金支付
 * @author hywen
 *
 */
@Controller
@RequestMapping("/jfiPayByJJ*")
@SuppressWarnings({ "rawtypes", "unused" ,"unchecked"})
public class JfiPayByJJController extends BaseFormController{

    //private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;
    private JalCountryManager alCountryManager;
    private JalCityManager alCityManager;
    private JalDistrictManager alDistrictManager;
    @Autowired
    private JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
	public void setAlCityManager(JalCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

    @Autowired
	public void setAlDistrictManager(JalDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

    @Autowired
	public void setAlCountryManager(JalCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

    @Autowired
	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

    @Autowired
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
    
    @Autowired
	public void setFiBankbookBalanceManager(
			FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
//        if (log.isDebugEnabled()) {
//            log.debug("entering 'handleRequest' method...");
//        }
        JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String orderId = request.getParameter("orderId");
        	
        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        if("CN".equals(jpoMemberOrder.getCompanyCode())){
            while (its1.hasNext()) {
            	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            	String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
            	if(!"1".equals(status)){
            		return new ModelAndView("jfiOrderProductMsg", "isOver", "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo() + ")已售完!");
            	}
            	String proNo =  jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
				if ("N07010100101CN0".equals(proNo)) {
					return new ModelAndView("jfiOrderProductMsg", "isOver", "该商品不能基金支付(N07010100101CN0)!");
				}

            }
        }else{
        	return new ModelAndView("jfiOrderProductMsg", "isOver", "订单不存在!");
        }
        	
        //抢购时间：2010年5月11日-5月16日
        /**
        String isOver = "";
        its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        while (its1.hasNext()) {
        	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        	String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
        	if(jpoMemberOrderManager.getIsOver(productNo)==true){
        		isOver = productNo;
        		break;
        	}
        }
        if(!isOver.equals("")){
        	return new ModelAndView("jfiOrderProductMsg", "isOver", "产品(" + isOver + ")已售完!");
        }
    	*/
    	if("30".equals(jpoMemberOrder.getOrderType())){
    		//return new ModelAndView("redirect:jpoMemberIROrders.html?needReload=1");
    		return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
    	}
        	
        if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())){
        	
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        	/**
        	if("1".equals(jpoMemberOrder.getOrderType())){
            	return new ModelAndView("redirect:jpoMemberFOrders.html?needReload=1");
        	}else if("2".equals(jpoMemberOrder.getOrderType())){
        		if("1".equals(jpoMemberOrder.getIsSpecial())){
        			return new ModelAndView("redirect:jpoMemberSpecialUOrders.html?needReload=1");
        		}else{
        			return new ModelAndView("redirect:jpoMemberUOrders.html?needReload=1");
        		}
        	}else if("3".equals(jpoMemberOrder.getOrderType())){
        		return new ModelAndView("redirect:jpoMemberRSOrders.html?needReload=1");
        	}else if("4".equals(jpoMemberOrder.getOrderType())){
           		return new ModelAndView("redirect:jpoMemberROrders.html?needReload=1");
        	}else if("5".equals(jpoMemberOrder.getOrderType())){
        		return new ModelAndView("redirect:jpoMemberAOrders.html?needReload=1");
        	}else if("6".equals(jpoMemberOrder.getOrderType())){
            	return new ModelAndView("redirect:jpoMemberSFOrders.html?needReload=1");
        	}else if("7".equals(jpoMemberOrder.getOrderType())){
        			
        	}else if("8".equals(jpoMemberOrder.getOrderType())){
        			
        	}else if("9".equals(jpoMemberOrder.getOrderType())){
            	return new ModelAndView("redirect:jpoMemberSROrders.html?needReload=1");
        	}else if("11".equals(jpoMemberOrder.getOrderType())){
        		return new ModelAndView("redirect:jpoMemberSSubFOrders.html?needReload=1");
    		}else if("12".equals(jpoMemberOrder.getOrderType())){
        		return new ModelAndView("redirect:jpoMemberSSubUOrders.html?needReload=1");
    		}else if("14".equals(jpoMemberOrder.getOrderType())){
        		return new ModelAndView("redirect:jpoMemberSSubROrders.html?needReload=1");
    		}else if("30".equals(jpoMemberOrder.getOrderType())){
            	return new ModelAndView("redirect:jpoMemberIROrders.html?needReload=1");
        	}else{
        		return null;
        	}
        	*/
        }
        	
        request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        
        FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
        request.setAttribute("fjBalance", fiBankbookBalance.getBalance());
        
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        
        
//        if("POST".equals(request.getMethod())){
//        	BigDecimal amount = new BigDecimal(request.getParameter("amount"));
//
//            	jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
//            	boolean isCheck = false;
//            	boolean b = false;
//            	if(amount.compareTo(new BigDecimal("0"))==1){
//            		jpoMemberOrder.setPayByJj("1");
//                	if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){
//                		jpoMemberOrder.setJjAmount(jpoMemberOrder.getAmount());
//                		jpoMemberOrder.setAmount(new BigDecimal("0"));
//                	}else{
//                		jpoMemberOrder.setJjAmount(amount);
//                		jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(amount));
//                	}
//                	isCheck = this.checkFlagOne(orderId, loginSysUser);
//            	}else if(amount.compareTo(new BigDecimal("0"))==-1){
//            		isCheck = false;
//            	}else{
//                	isCheck = this.checkFlagOne(orderId, loginSysUser);
//            	}
//            	
//            	if(isCheck){
//            		b =  true;
//            		//saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
//            		
//            	}else{
//            		//saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poOrder.editedFail"));
//            	}
//            	
//            	if(b){
//            		//发送mq队列
//            		try{
//            			
//            			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, loginSysUser, "1");
//            			
//            			jpoMemberOrder.setStatus("3");
//            			jpoMemberOrderManager.save(jpoMemberOrder);
//            			log.info("=======审单MQ：JfiPayByJJController,orderNo1:" + jpoMemberOrder.getMemberOrderNo()); 
//            			
//            		}catch (Exception e) {
//            			log.info("发送mq消息失败：" + e);
//    					e.printStackTrace();
//					}
//            	}
//            	
//            	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
//            	/**
//            	if("1".equals(jpoMemberOrder.getOrderType())){
//                	return new ModelAndView("redirect:jpoMemberFOrders.html?needReload=1");
//            	}else if("2".equals(jpoMemberOrder.getOrderType())){
//            		if("1".equals(jpoMemberOrder.getIsSpecial())){
//            			return new ModelAndView("redirect:jpoMemberSpecialUOrders.html?needReload=1");
//            		}else{
//            			return new ModelAndView("redirect:jpoMemberUOrders.html?needReload=1");
//            		}
//            	}else if("3".equals(jpoMemberOrder.getOrderType())){
//            		return new ModelAndView("redirect:jpoMemberRSOrders.html?needReload=1");
//            	}else if("4".equals(jpoMemberOrder.getOrderType())){
//               		return new ModelAndView("redirect:jpoMemberROrders.html?needReload=1");
//            	}else if("5".equals(jpoMemberOrder.getOrderType())){
//            		return new ModelAndView("redirect:jpoMemberAOrders.html?needReload=1");
//            	}else if("6".equals(jpoMemberOrder.getOrderType())){
//                	return new ModelAndView("redirect:jpoMemberSFOrders.html?needReload=1");
//            	}else if("7".equals(jpoMemberOrder.getOrderType())){
//            			
//            	}else if("8".equals(jpoMemberOrder.getOrderType())){
//            			
//            	}else if("9".equals(jpoMemberOrder.getOrderType())){
//                	return new ModelAndView("redirect:jpoMemberSROrders.html?needReload=1");
//            	}else if("11".equals(jpoMemberOrder.getOrderType())){
//            		return new ModelAndView("redirect:jpoMemberSSubFOrders.html?needReload=1");
//        		}else if("12".equals(jpoMemberOrder.getOrderType())){
//            		return new ModelAndView("redirect:jpoMemberSSubUOrders.html?needReload=1");
//        		}else if("14".equals(jpoMemberOrder.getOrderType())){
//            		return new ModelAndView("redirect:jpoMemberSSubROrders.html?needReload=1");
//        		}else if("30".equals(jpoMemberOrder.getOrderType())){
//                	return new ModelAndView("redirect:jpoMemberIROrders.html?needReload=1");
//            	}else{
//            		return null;
//            	}
//            	*/
//
//        }

        //收货地区
        JalCountry alCountry = (JalCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
        alCountry.getJalStateProvinces().iterator();
        request.setAttribute("alStateProvinces", alCountry.getJalStateProvinces());

    	List<JalCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jpoMemberOrder.getProvince()));
    	for (JalCity city : citys) {
    		if(city.getCityId().toString().equals(jpoMemberOrder.getCity())){
    			jpoMemberOrder.setCity(city.getCityName());
        		List<JalDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
        		for (JalDistrict district : alDistricts) {
        			if(district.getDistrictId().toString().equals(jpoMemberOrder.getDistrict())){
        				jpoMemberOrder.setDistrict(district.getDistrictName());
        				break;
        			}
        		}
    		}
    	}
        
        return new ModelAndView("jfiPayByJJ");
    }
    
    //基金支付提交
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(HttpServletRequest request,
                           HttpServletResponse response) {

    	Locale locale = request.getLocale();
    	
    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String orderId = request.getParameter("orderId");
        	
        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
        JpoMemberOrder order = new JpoMemberOrder();
        
        if(orderIsPayVali(jpoMemberOrder)){
        	log.info("orderIsPayVali:"+jpoMemberOrder.getMemberOrderNo());
        	return new ModelAndView("jfiOrderProductMsg", "isOver", 
        			jpoMemberOrder.getMemberOrderNo()+"已支付");
        }
        if(validateOrder(jpoMemberOrder, loginSysUser)){
        	log.info("orderIsPayVali:"+jpoMemberOrder.getMemberOrderNo());
        	return new ModelAndView("jfiOrderProductMsg", "isOver", 
        			LocaleUtil.getLocalText("user.validate"));
        }
    	if("POST".equals(request.getMethod())){
        	BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        	log.info("amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo());
            	
        	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        	while (its1.hasNext()) {
            	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            	String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
            	if(!"1".equals(status)){
            		return new ModelAndView("jfiOrderProductMsg", "isOver", 
            				"产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().
            				getJpmProductSaleNew().getProductNo() + ")已售完!");
            	}
            }
        	boolean isCheck = false;
        	boolean b = false;
        	if(amount.compareTo(new BigDecimal("0"))==1){//基金支付金额大于0
        		order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
	    		//jpoMemberOrder.setPayByJj("1");//基金支付
	    		order.setPayByJj("1");
	    		if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){//基金支付金额不小于订单金额
//	    			jpoMemberOrder.setJjAmount(jpoMemberOrder.getAmount());//基金全额支付
//	    			jpoMemberOrder.setAmount(new BigDecimal("0"));
            		order.setJjAmount(jpoMemberOrder.getAmount());//基金全额支付
            		//2017-1-1 w
//            		order.setAmount(new BigDecimal("0"));	        		
	        		
	        	}else{
//	        		jpoMemberOrder.setJjAmount(amount);
//	        		jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(amount));//电子存折支付部分
	        		order.setJjAmount(amount);
	        		//2017-1-1 w
//	        		order.setAmount(jpoMemberOrder.getAmount().subtract(amount));//电子存折支付部分
	        	}
	    		
	    		log.info("payByJj is:"+order.getPayByJj()+" "
            			+ "orderNo is:"+order.getMemberOrderNo());
            	
            	//调用扣款
            	isCheck = this.checkFlagOne(order, loginSysUser);
            	
        	}else if(amount.compareTo(new BigDecimal("0"))==-1){//基金支付金额小于0
        		
        		isCheck = false;
        	}else{//基金支付金额等于0
        		
        		//存折支付
            	isCheck = this.checkFlagOne(order, loginSysUser);
        	}
        	
        	if(isCheck){
        		
        		b = true;
        		// 支付成功 
    			saveMessage(request, LocaleUtil.getLocalText("order.pay.success"));
        	}else{
        		// 支付失败
				saveMessage(request, LocaleUtil.getLocalText("order.pay.fail"));
        	}
        	/*
        	 * 支付改造
        	if(b){
        		//发送mq队列
        		try{
        			
        			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, loginSysUser, "1");
        			
        			jpoMemberOrder.setStatus("3");
        			jpoMemberOrderManager.save(jpoMemberOrder);
        			log.info("=======审单MQ：JfiPayByJJController,orderNo2:" + jpoMemberOrder.getMemberOrderNo()); 
        			
        		}catch (Exception e) {
        			log.info("发送mq消息失败：" + e);
					e.printStackTrace();
				}
        	}
        	*/
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        }
    	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
    }
	/**
	 * 扣款
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(JpoMemberOrder order, JsysUser operatorSysUser){
		
		try{
			if("1".equals(order.getPayByJj())){
				log.info("checkJpoMemberOrderByJJ start:"+order.getMemberOrderNo());
				jpoMemberOrderManager.checkJpoMemberOrderByJJ(order, operatorSysUser,"1");
				log.info("checkJpoMemberOrderByJJ stop:"+order.getMemberOrderNo());
			}else{
				log.info("checkJpoMemberOrder start:"+order.getMemberOrderNo());
				jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"1");
				log.info("checkJpoMemberOrder stop:"+order.getMemberOrderNo());
			}
			
		}catch(AppException app){
			log.error("订单支付失败信息:",app);
			return false;
		
		}catch(Exception app){
			
			log.error("订单支付失败信息:",app);
			return false;
		}
		return true;
	}
	/**
	 * 支付改造
	 * 扣款
	 * @param orderId
	 * @param operatorSysUser
	 
	private boolean checkFlagOne(String orderId, JsysUser operatorSysUser){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		
		JpoMemberOrder order = new JpoMemberOrder();
		order = jpoMemberOrder;
		order.setMoId(null);
		
		log.info("paybyjj="+jpoMemberOrder.getPayByJj());
		try{
			if("1".equals(jpoMemberOrder.getPayByJj())){
				
				jpoMemberOrderManager.checkJpoMemberOrderByJJ(order, operatorSysUser,"1");
			}else{
				
				jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"1");
			}
			
//			log.info("call function start. "+jpoMemberOrder.getStatus()+" "
//					+ "and "+jpoMemberOrder.getIsPay());
//			jpoMemberOrderDao.callSTJFunction(jpoMemberOrder.getMemberOrderNo(), 1);
//			log.info("call function END. ");
		}catch(AppException app){
			log.info("订单支付失败信息:"+app.getMessage());
			
			return false;
		}catch(Exception app){
			log.info("订单支付失败信息:"+app.getMessage());
			
			return false;
		}
		return true;
	}*/

	public JpoMemberOrderDao getJpoMemberOrderDao() {
		return jpoMemberOrderDao;
	}

	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
	
}
