package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalCountry;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoUserCoupon;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.FiProductPointBalanceManager;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalCountryManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoUserCouponManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
/**
 * 代金券支付
 * @author WuCF 2017-05-20
 *
 */
@Controller
@RequestMapping("/jfiPayByCoupon*")
@SuppressWarnings({ "rawtypes", "unused" ,"unchecked"})
public class JfiPayByCouponsController extends BaseFormController{

    //private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private JalCountryManager alCountryManager;
    private JalCityManager alCityManager;
    private JalDistrictManager alDistrictManager;
    private JpoUserCouponManager jpoUserCouponManager;
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;
    private FiProductPointBalanceManager fiProductPointBalanceManager = null;
    
    @Autowired
    private JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
	public void setAlCityManager(JalCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}
    
    @Autowired
	public void setFiBankbookBalanceManager(
			FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
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
	public void setFiProductPointBalanceManager(
			FiProductPointBalanceManager fiProductPointBalanceManager) {
		this.fiProductPointBalanceManager = fiProductPointBalanceManager;
	}

    @Autowired
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
    
    @Autowired
	public void setJpoUserCouponManager(JpoUserCouponManager jpoUserCouponManager) {
		this.jpoUserCouponManager = jpoUserCouponManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            	/*String proNo =  jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
				if ("N07010100101CN0".equals(proNo)) {
					return new ModelAndView("jfiOrderProductMsg", "isOver", "该商品不能基金支付(N07010100101CN0)!");
				}*/

            }
        }else{
        	return new ModelAndView("jfiOrderProductMsg", "isOver", "订单不存在!");
        }
    	if("30".equals(jpoMemberOrder.getOrderType())){
    		return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
    	}
        	
        if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())){
        	
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        }
        	
        request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        
        /*// 查询代金券账户
        FiProductPointBalance productPointBalance = fiProductPointBalanceManager.getProductPointBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
        request.setAttribute("productPointBalance", productPointBalance.getBalance());*/
        //代金券UNDO：查询代金券的账户查询
        List jpoUserCouponList = jpoUserCouponManager.getJpoUserCoupons(loginSysUser.getUserCode());
        System.out.println("================JpoUserCoupon:"+jpoUserCouponList);
        request.setAttribute("jpoUserCouponList", jpoUserCouponList);
        // 查询电子存折账户
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        
        //查询发展基金账户
        FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
        request.setAttribute("fjBalance", fiBankbookBalance.getBalance());
        
     // 查询抵用券账户
        FiProductPointBalance productPointBalance = fiProductPointBalanceManager.getProductPointBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
        request.setAttribute("productPointBalance", productPointBalance.getBalance());

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
        return new ModelAndView("jfiPayByCoupon");
    }
    
    //代金券支付提交
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response) {

    	Locale locale = request.getLocale();
    	
    	String checkUserCpId = request.getParameter("userCpId");
    	//Modify By WuCF 判断代金券是否已经使用
    	if(checkUserCpId!=null && !"".equals(checkUserCpId)){
    		JpoUserCoupon jpoUserCoupon = jpoUserCouponManager.getJpoUserCouponById(checkUserCpId);
    		if(jpoUserCoupon.getStatus()){
    			return new ModelAndView("jfiOrderProductMsg", "isOver", "您选择的代金券已经使用过了，支付失败！");
    		}
    	}
    	
    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String orderId = request.getParameter("orderId");
        
        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
        JpoMemberOrder order = new JpoMemberOrder();
        
        if(orderIsPayVali(jpoMemberOrder)){
        	log.info("orderIsPayVali:"+jpoMemberOrder.getMemberOrderNo());
        	return new ModelAndView("jfiOrderProductMsg", "isOver", jpoMemberOrder.getMemberOrderNo()+"已支付");
        }
        if(validateOrder(jpoMemberOrder, loginSysUser)){
        	log.info("orderIsPayVali:"+jpoMemberOrder.getMemberOrderNo());
        	return new ModelAndView("jfiOrderProductMsg", "isOver", LocaleUtil.getLocalText("user.validate"));
        }
        
        if(orderIsPayVali(jpoMemberOrder)){
        	log.info("orderIsPayVali:"+jpoMemberOrder.getMemberOrderNo());
        	return new ModelAndView("jfiOrderProductMsg", "isOver", "");
        }
        
    	if("POST".equals(request.getMethod())){
        	BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        	log.info("amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo());
        	
        	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        	while (its1.hasNext()) {
            	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            	String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
            	if(!"1".equals(status)){
            		return new ModelAndView("jfiOrderProductMsg", "isOver", "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().
            				getJpmProductSaleNew().getProductNo() + ")已售完!");
            	}
            }
        	boolean isCheck = false;
        	boolean b = false;
        	
        	if(amount.compareTo(new BigDecimal("0"))!=-1){//代金券支付金额不小于0，正常逻辑
        		
        		//代金券比例不超过设定的比例，暂不开启这个功能
        		/*String productPointRate = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "productpoint.rate");
        		if(!StringUtils.isEmpty(productPointRate)) {
        			BigDecimal orderAmt = jpoMemberOrder.getAmount();			//订单总金额
            		BigDecimal pointRate = new BigDecimal(productPointRate);	//代金券可支付的比例
            		
            		BigDecimal allowPayAmt = orderAmt.multiply(pointRate);		//订单总金额乘于允许支付的比例，就是可用的代金券
            		
            		allowPayAmt = allowPayAmt.setScale(2, BigDecimal.ROUND_HALF_UP);	//最多可支付的金额
            		
            		if(amount.compareTo(allowPayAmt)==1) {
            			log.info("支付金额：" + amount + "，允许用代金券支付的金额：" + allowPayAmt);
            			return new ModelAndView("jfiOrderProductMsg", "isOver", "代金券支付金额：" + amount + " 超出可支付额上限：" + allowPayAmt);
            		} else {
            			log.info("支付金额小于允许代金券支付的比例");
            		}
        		} else {
        			log.info("没配置代金券支付比例");
        		}*/

        		order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
        		
        		String userCpId = request.getParameter("userCpId");//Modify By WuCF 20170524 使用的会员所属代金券的主键
            	if(userCpId!=null && !"".equals(userCpId)){
            		order.setUserCpId(Long.parseLong(userCpId));
            	}else{
            		log.info("必须使用代金券或代金券加其他支付方式才可支付");
            		return new ModelAndView("jfiOrderProductMsg", "isOver", "必须使用代金券或代金券加其他支付方式才可支付");
            	}
        		if(amount.compareTo(new BigDecimal("0"))==1){
        			order.setPayByCp("1");//代金券支付标识
        		}
        		if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){//代金券支付金额不小于订单金额
            		order.setCpValue(jpoMemberOrder.getAmount()); //代金券金额
	        	}else{
	        		//order.setJjAmount(amount);
	        		order.setCpValue(amount); //代金券金额
	        		String sysm = request.getParameter("sysm");
	            	System.out.println("sysm======"+sysm);
	            	if("2".equals(sysm)){//发展基金
	            		order.setPayByJj("1");
	            		order.setJjAmount(jpoMemberOrder.getAmount().subtract(amount));//发展基金支付部分
	            	}else if("3".equals(sysm)){//抵用券
	            		order.setPayByProduct("1");
	            		order.setProductPointAmount(jpoMemberOrder.getAmount().subtract(amount)); //抵用券支付部分
	            	}else{//电子存折
	            		
	            	}
	        	}
	    		
	    		log.info("payByCp is:"+order.getPayByCp()+" " + "orderNo is:"+order.getMemberOrderNo());
	    		System.out.println("1amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo()+"-userCpId:"+order.getUserCpId());
            	//调用扣款
            	isCheck = this.checkFlagOne(order, loginSysUser);
            	System.out.println("2amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo()+"-userCpId:"+order.getUserCpId());
        	}
        	
//        	if(amount.compareTo(new BigDecimal("0"))==1){//代金券支付金额大于0
//        		
//        		//代金券比例不超过设定的比例，暂不开启这个功能
//        		/*String productPointRate = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "productpoint.rate");
//        		if(!StringUtils.isEmpty(productPointRate)) {
//        			BigDecimal orderAmt = jpoMemberOrder.getAmount();			//订单总金额
//            		BigDecimal pointRate = new BigDecimal(productPointRate);	//代金券可支付的比例
//            		
//            		BigDecimal allowPayAmt = orderAmt.multiply(pointRate);		//订单总金额乘于允许支付的比例，就是可用的代金券
//            		
//            		allowPayAmt = allowPayAmt.setScale(2, BigDecimal.ROUND_HALF_UP);	//最多可支付的金额
//            		
//            		if(amount.compareTo(allowPayAmt)==1) {
//            			log.info("支付金额：" + amount + "，允许用代金券支付的金额：" + allowPayAmt);
//            			return new ModelAndView("jfiOrderProductMsg", "isOver", "代金券支付金额：" + amount + " 超出可支付额上限：" + allowPayAmt);
//            		} else {
//            			log.info("支付金额小于允许代金券支付的比例");
//            		}
//        		} else {
//        			log.info("没配置代金券支付比例");
//        		}*/
//
//        		order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
//        		
//        		String userCpId = request.getParameter("userCpId");//Modify By WuCF 20170524 使用的会员所属代金券的主键
//            	if(userCpId!=null && !"".equals(userCpId)){
//            		order.setUserCpId(Long.parseLong(userCpId));
//            	}
//        		
//	    		order.setPayByCp("1");//代金券支付标识
//	    		if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){//代金券支付金额不小于订单金额
//            		order.setCpValue(jpoMemberOrder.getAmount()); //代金券金额
//	        	}else{
//	        		//order.setJjAmount(amount);
//	        		order.setCpValue(amount); //代金券金额
//	        	}
//	    		
//	    		log.info("payByCp is:"+order.getPayByCp()+" " + "orderNo is:"+order.getMemberOrderNo());
//	    		System.out.println("1amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo()+"-userCpId:"+order.getUserCpId());
//            	//调用扣款
//            	isCheck = this.checkFlagOne(order, loginSysUser);
//            	System.out.println("2amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo()+"-userCpId:"+order.getUserCpId());
//        	}else if(amount.compareTo(new BigDecimal("0"))==-1){//代金券支付金额小于0
//        		isCheck = false;
//        	}else{
//        		System.out.println("3amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo()+"-userCpId:"+order.getUserCpId());
//        		//代金券支付金额等于0，存折支付
//            	isCheck = this.checkFlagOne(jpoMemberOrder, loginSysUser);
//            	System.out.println("4amount="+amount+" and orderNo :"+jpoMemberOrder.getMemberOrderNo()+"-userCpId:"+jpoMemberOrder.getUserCpId());
//        	}
        	
        	if(isCheck){
        		b = true;
        		// 支付成功 
    			saveMessage(request, LocaleUtil.getLocalText("order.pay.success"));
        	}else{
        		// 支付失败
				saveMessage(request, LocaleUtil.getLocalText("order.pay.fail"));
        	}
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
//			if("1".equals(order.getPayByCp())){//代金券支付
				log.info("checkJpoMemberOrderByProduct start:"+order.getMemberOrderNo());
				jpoMemberOrderManager.checkJpoMemberOrderByCpValue(order, operatorSysUser,"1");
				log.info("checkJpoMemberOrderByProduct stop:"+order.getMemberOrderNo());
//			}else{//全存折支付
//				log.info("checkJpoMemberOrder start:"+order.getMemberOrderNo());
//				jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"1");
//				log.info("checkJpoMemberOrder stop:"+order.getMemberOrderNo());
//			}
			
		}catch(AppException app){
			log.error("订单支付失败信息:",app);
			return false;
		
		}catch(Exception app){
			log.error("订单支付失败信息:",app);
			return false;
		}
		return true;
	}

	public JpoMemberOrderDao getJpoMemberOrderDao() {
		return jpoMemberOrderDao;
	}

	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
	
	public static void main(String[] args) {
		
		/*BigDecimal amount = new BigDecimal("39.33");
		
		BigDecimal bignum1 = new BigDecimal("199.33");
		BigDecimal bignum2 = new BigDecimal("0.32");
		BigDecimal bignum3 = bignum1.multiply(bignum2);
		BigDecimal allowPayAmt = bignum3.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		System.out.println(bignum1.multiply(bignum2));
		System.out.println(allowPayAmt);
		
		if(amount.compareTo(allowPayAmt)==1) {
			System.out.println("支付金额：" + amount + "> 比例金额：" + allowPayAmt);
		} else {
			System.out.println("支付金额小于允许代金券支付的比例");
		}*/
	}
	
}
