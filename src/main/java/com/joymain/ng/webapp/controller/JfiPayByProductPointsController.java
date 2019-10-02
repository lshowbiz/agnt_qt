package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.service.FiBankbookBalanceManager;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalCountry;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiProductPointBalanceManager;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalCountryManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;
/**
 * 产品积分支付
 * @author hdg 2017-01-03
 *
 */
@Controller
@RequestMapping("/jfiPayByProPoint*")
@SuppressWarnings({ "rawtypes", "unused" ,"unchecked"})
public class JfiPayByProductPointsController extends BaseFormController{

    //private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
	private FiBankbookBalanceManager fiBankbookBalanceManager = null;
    private FiProductPointBalanceManager fiProductPointBalanceManager = null;
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
	public void setFiBankbookBalanceManager(
		FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}
    @Autowired
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
    
    @Autowired
	public void setFiProductPointBalanceManager(
			FiProductPointBalanceManager fiProductPointBalanceManager) {
		this.fiProductPointBalanceManager = fiProductPointBalanceManager;
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
        
        // 查询产品积分账户
        FiProductPointBalance productPointBalance = fiProductPointBalanceManager.getProductPointBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
        request.setAttribute("productPointBalance", productPointBalance.getBalance());
        
        // 查询电子存折账户
        
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());

		// 查询电子存折账户

		FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
		request.setAttribute("fiBankbookBalance", fiBankbookBalance.getBalance());
        

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
        return new ModelAndView("jfiPayByProductPoint");
    }
    
    //产品积分支付提交
	@RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response) {

    	Locale locale = request.getLocale();
    	
    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String orderId = request.getParameter("orderId");
		String sysm = request.getParameter("sysm");
		if(!"2".equals(sysm)){
			sysm="1";
		}
		log.info("sysm------"+sysm);
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
        	if(amount.compareTo(new BigDecimal("0"))==1){//产品积分支付金额大于0
        		
        		//产品积分比例不超过设定的比例，暂不开启这个功能
        		/*String productPointRate = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "productpoint.rate");
        		if(!StringUtils.isEmpty(productPointRate)) {
        			BigDecimal orderAmt = jpoMemberOrder.getAmount();			//订单总金额
            		BigDecimal pointRate = new BigDecimal(productPointRate);	//产品积分可支付的比例
            		
            		BigDecimal allowPayAmt = orderAmt.multiply(pointRate);		//订单总金额乘于允许支付的比例，就是可用的产品积分
            		
            		allowPayAmt = allowPayAmt.setScale(2, BigDecimal.ROUND_HALF_UP);	//最多可支付的金额
            		
            		if(amount.compareTo(allowPayAmt)==1) {
            			log.info("支付金额：" + amount + "，允许用产品积分支付的金额：" + allowPayAmt);
            			return new ModelAndView("jfiOrderProductMsg", "isOver", "产品积分支付金额：" + amount + " 超出可支付额上限：" + allowPayAmt);
            		} else {
            			log.info("支付金额小于允许产品积分支付的比例");
            		}
        		} else {
        			log.info("没配置产品积分支付比例");
        		}*/

        		order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
	    		order.setPayByProduct("1");
	    		if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){//产品积分支付金额不小于订单金额
            		order.setProductPointAmount(jpoMemberOrder.getAmount()); 
	        	}else{
	        		//order.setJjAmount(amount);
	        		order.setProductPointAmount(amount);
	        		if("2".equals(sysm)){
						order.setJjAmount(jpoMemberOrder.getAmount().subtract(amount));
					}
	        	}
	    		
	    		log.info("payByProduct is:"+order.getPayByProduct()+" " + "orderNo is:"+order.getMemberOrderNo());
            	//调用扣款
            	isCheck = this.checkFlagOne(order, loginSysUser);
            	
        	}else if(amount.compareTo(new BigDecimal("0"))==-1){//产品积分支付金额小于0
        		isCheck = false;
        	}else{
        		//产品积分支付金额等于0，存折支付
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
			if("1".equals(order.getPayByProduct())){
				log.info("checkJpoMemberOrderByProduct start:"+order.getMemberOrderNo());
				jpoMemberOrderManager.checkJpoMemberOrderByProductPoint(order, operatorSysUser,"1");
				log.info("checkJpoMemberOrderByProduct stop:"+order.getMemberOrderNo());
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
			System.out.println("支付金额小于允许产品积分支付的比例");
		}*/
		BigDecimal orderAmt = new BigDecimal("39.33");			//订单总金额
		BigDecimal productPointAmt = new BigDecimal("49.33");	//产品积分支付金额
		if(orderAmt.compareTo(productPointAmt)==1 || orderAmt.compareTo(productPointAmt)==0) {
			System.out.println("订单总金额大于或者等于产品积分支付金额");
		} else {
			System.out.println("订单总金额小于产品积分支付金额");
		}
	}
	
}
