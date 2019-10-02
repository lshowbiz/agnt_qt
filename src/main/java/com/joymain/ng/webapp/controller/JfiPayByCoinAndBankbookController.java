package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.JpoMemberOrderListDao;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalCountry;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.FiBcoinPayconfigManager;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalCountryManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.webapp.util.PromotionsUtils;

/**
 * EC积分支付
 * @author shiyh
 *
 */
@Controller
@RequestMapping("/jfiPayByJF*")
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class JfiPayByCoinAndBankbookController extends BaseFormController{

    private final Log log = LogFactory.getLog(JfiPayByCoinAndBankbookController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;
    private JalCountryManager alCountryManager;
    private JalCityManager alCityManager;
    private JalDistrictManager alDistrictManager;
    @Autowired
    private JpoMemberOrderListDao jpoMemberOrderListDao;
    
	@Autowired
	private FiBcoinPayconfigManager fiBcoinPayconfigManager = null;//积分换购活动

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
    
	public JpoMemberOrderListDao getJpoMemberOrderListDao() {
		return jpoMemberOrderListDao;
	}

	public void setJpoMemberOrderListDao(JpoMemberOrderListDao jpoMemberOrderListDao) {
		this.jpoMemberOrderListDao = jpoMemberOrderListDao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
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
            }
        }else{
        	return new ModelAndView("jfiOrderProductMsg", "isOver", "订单不存在!");
        }

        	
        if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())){
        	
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        }
        
        if(!"5".equals(jpoMemberOrder.getOrderType())&&!"4".equals(jpoMemberOrder.getOrderType())&&!"9".equals(jpoMemberOrder.getOrderType())&&!"14".equals(jpoMemberOrder.getOrderType())&&!"40".equals(jpoMemberOrder.getOrderType())){
        	
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        }
//        }else{
//        	if(!"5".equals(jpoMemberOrder.getOrderType())){
//
//        		int payByCoin = Integer.parseInt(ConfigUtil.getConfigValue("CN","paybycoin"));
//        		if(payByCoin==0){
//        			
//        			String url = "jpoMemberOrders/orderAll?needReload=1";
//
//    	    	}
//        	}else{
//    			int preferential = jpoMemberOrderManager.getPreferentialOrder(jpoMemberOrder);
//    			//===========
//            	java.util.Calendar startc=java.util.Calendar.getInstance();
//    	    	startc.set(2012, 7, 19, 23, 59, 59);
//    	    	java.util.Date startDate=startc.getTime();
//    	    	Date curDate=new Date();
//    	    	if(curDate.after(startDate)){
//        			//=================临时取消月饼限制，允许积分换购
//    	    		preferential=2;
//    	    	}
//    			//===========
//    	    	String url = "jpoMemberOrders/orderAll?needReload=1";
//        	}
//        }
        	
        request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        
        FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("coin", fiBcoinBalance.getBalance());
        

		BigDecimal productAmount = new BigDecimal("0");//计算订单除爱心365以外的金额的
    	its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			
			//爱心365不能使用积分
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			if(!"P26010100101CN0".equals(productNo)){
				
				productAmount = productAmount.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}			
    	}
        BigDecimal sumCoin = new BigDecimal("0");

		if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2")))!=1){
			sumCoin = productAmount.multiply(new BigDecimal("0.5"));
		}else{
			sumCoin = fiBcoinBalance.getBalance();
		}
		if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2")))==-1){
			return new ModelAndView("jfiOrderProductMsg", "isOver", "积分计算有误!");
			//throw new Exception("积分计算有误");
		}
		
		//启智派商品换购比例：每台允许积分换购：换购比例：最高1000积分+2280元
		if(checkQzpProductNo(jpoMemberOrder)){
			
			its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			int sumUseCoin = jpoMemberOrderList.getQty()*1000;	

			BigDecimal sumNum= new BigDecimal(sumUseCoin);//每台最高可使用积分
			
			//如果会员积分不足
			if(sumNum.compareTo(fiBcoinBalance.getBalance())==1){
				
				sumCoin = fiBcoinBalance.getBalance();
			}else{//会员积分足够
				
				sumCoin = sumNum;
			}				
		}
		
		//调用公共方法判断是否单独购买九款特殊商品（积分换购),返回false代表是
		if(jpoIsOnly(jpoMemberOrder)){
			
			//重新计算积分
			if(fiBcoinBalance.getBalance().compareTo(productAmount.multiply(new BigDecimal("0.75")))==1){//积分大于订单总额的75%
				
				sumCoin = productAmount.multiply(new BigDecimal("0.75"));//允许使用订单金额75%的积分
			}else{
				
				sumCoin = fiBcoinBalance.getBalance();
			}
			if(sumCoin.compareTo(productAmount.multiply(new BigDecimal("0.75")))==1){
				return new ModelAndView("jfiOrderProductMsg", "isOver", "积分计算有误!"); 
				//throw new Exception("积分计算有误");
			}
		}
		
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2012, 6, 21, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		Date curDate=new Date();
		if(curDate.after(startDate)){//7月21日，改成辅消品6折
			if("5".equals(jpoMemberOrder.getOrderType())){
				if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2.5")))!=1){
					sumCoin = productAmount.multiply(new BigDecimal("0.4"));
				}else{
					sumCoin = fiBcoinBalance.getBalance();
				}
				if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2.5")))==-1){
					return new ModelAndView("jfiOrderProductMsg", "isOver", "积分计算有误!");
					//throw new Exception("积分计算有误");
				}
			}
		}
        
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        
        
        //============================2016年1月促销-积分换购================================================start
        Boolean isIntegra = true; //判断是否走以前的积分支付方式
		Map<String, String> resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder); 
		if (resultMap != null && !"-1".equals(resultMap.get("index"))) {// 是促销的商品
			sumCoin = new BigDecimal(resultMap.get("maxJF")).setScale(0, RoundingMode.DOWN);
			if (fiBcoinBalance.getBalance().compareTo(sumCoin) != 1) {
				sumCoin = fiBcoinBalance.getBalance();
			}
			isIntegra = false;
		}
		
		//============================2016年1月促销-积分换购================================================end
		
		//============================2016-05-25   2016年6月积分换购需求开发================================================start
		String jsonStr = "";
		//旋磁椅
		jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606xcy, "201606xcy.startdate", "201606xcy.enddate", fiBcoinBalance, "0", "25000") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}
		//3D床垫、道和国韵（体验装）
    	jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606, "201606coincx.startdate", "201606coincx.enddate", fiBcoinBalance, "1", "0.5") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}
		//道和国韵
		jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201805, "201805coincx.startdate", "201805coincx.enddate", fiBcoinBalance, "1", "0.5") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}
		 
		//11月  重消单开启积分换购可用积分试算   GlobalVar.jpocoincxList201611 为不参加积分换购的特殊商品
		jsonStr = PromotionsUtils.checkCoincxPay(jpoMemberOrder, GlobalVar.jpocoincxList201611, "201611coincx.startdate","201611coincx.enddate", fiBcoinBalance,  "0.5") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}

		//============================2016-05-25  2016年6月积分换购需求开发================================================end

		 request.setAttribute("isIntegra", isIntegra);
		 request.setAttribute("sumCoin",sumCoin.intValue());
		 
		if (isIntegra && sumCoin.compareTo(new BigDecimal("0")) == 1) {
        	String msg = "您本张订单总金额" + jpoMemberOrder.getAmount() + "，使用金额" + jpoMemberOrder.getAmount().subtract(sumCoin) + "，使用积分" + sumCoin + "，PV为0";
        	request.setAttribute("msg", msg);
        }
        
        if("POST".equals(request.getMethod())){
        	jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
        	boolean isCheck = this.checkFlagOne(orderId, loginSysUser);
        	
        	if(isCheck){
//        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
        	}else{
//        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poOrder.editedFail"));
        	}
        	
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");

        }

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
        
    	//积分支付页面
        return new ModelAndView("jfiPayByCoinAndBankbook");
    }
	
    //积分支付提交
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(HttpServletRequest request,
                           HttpServletResponse response) throws Exception{
    	
    	Locale locale = request.getLocale();
    	
    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String orderId = request.getParameter("orderId");
        	
        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
        
        if(validateOrder(jpoMemberOrder,loginSysUser)){
			log.info(jpoMemberOrder.getMemberOrderNo() + 
					LocaleUtil.getLocalText("user.validate"));
			return new ModelAndView("jfiOrderProductMsg", "isOver", 
					LocaleUtil.getLocalText("user.validate"));
		}
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        if("CN".equals(jpoMemberOrder.getCompanyCode())){
            while (its1.hasNext()) {
            	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            	String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
            	if(!"1".equals(status)){
            		return new ModelAndView("jfiOrderProductMsg", "isOver", "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo() + ")已售完!");
            	}
            }
        }else{
        	return new ModelAndView("jfiOrderProductMsg", "isOver", "订单不存在!");
        }
        	
        if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())){
        	
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        }
        
        if(!"5".equals(jpoMemberOrder.getOrderType())&&!"4".equals(jpoMemberOrder.getOrderType())&&!"9".equals(jpoMemberOrder.getOrderType())&&!"14".equals(jpoMemberOrder.getOrderType())&&!"40".equals(jpoMemberOrder.getOrderType())){
        	
        	return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        }
        	
        request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        
        FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("coin", fiBcoinBalance.getBalance());
        
		BigDecimal productAmount = new BigDecimal("0");
    	its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			
			//爱心365不能使用积分
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			if(!"P26010100101CN0".equals(productNo)){
				productAmount = productAmount.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
    	}
        BigDecimal sumCoin = new BigDecimal("0");

        //重消单
        if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())||"40".equals(jpoMemberOrder.getOrderType())){
        	
			if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2")))!=1){
				sumCoin = productAmount.multiply(new BigDecimal("0.5"));
			}else{
				sumCoin = fiBcoinBalance.getBalance();
			}
			if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2")))==-1){
				return new ModelAndView("jfiOrderProductMsg", "isOver", "积分计算有误!");
				//throw new Exception("积分计算有误");
			}
			
			//启智派商品换购比例：允许积分换购：换购比例：最高1000积分+2280元
			if(checkQzpProductNo(jpoMemberOrder)){
				
				its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				int sumUseCoin = jpoMemberOrderList.getQty()*1000;	

				BigDecimal sumNum= new BigDecimal(sumUseCoin);//每台最高可使用积分
				
				//如果会员积分不足
				if(sumNum.compareTo(fiBcoinBalance.getBalance())==1){
					
					sumCoin = fiBcoinBalance.getBalance();
				}else{//会员积分足够
					
					sumCoin = sumNum;
				}				
			}
			
			//调用公共方法判断是否单独购买九款特殊商品（积分换购),返回false代表是
			if(jpoIsOnly(jpoMemberOrder)){
				
				//重新计算积分
				if(fiBcoinBalance.getBalance().compareTo(productAmount.multiply(new BigDecimal("0.75")))==1){//积分大于订单总额的75%
					
					sumCoin = productAmount.multiply(new BigDecimal("0.75"));//允许使用订单金额75%的积分
				}else{
					
					sumCoin = fiBcoinBalance.getBalance();
				}
				if(sumCoin.compareTo(productAmount.multiply(new BigDecimal("0.75")))==1){
					return new ModelAndView("jfiOrderProductMsg", "isOver", "积分计算有误!");
					//throw new Exception("积分计算有误");
				}
			}
	    }
        
		//辅消品6折
		if("5".equals(jpoMemberOrder.getOrderType())){
			if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2.5")))!=1){
				sumCoin = productAmount.multiply(new BigDecimal("0.4"));
			}else{
				sumCoin = fiBcoinBalance.getBalance();
			}
			if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2.5")))==-1){
				//throw new Exception("积分计算有误");
				return new ModelAndView("jfiOrderProductMsg", "isOver", "积分计算有误!");
			}
		}
		
        
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        if(isOverQty(jpoMemberOrder)){ //库存
			return new ModelAndView("jfiOrderProductMsg", "isOver", "库存不足!");
		}
        //============================2016年1月促销-积分换购================================================start
        
        Boolean isIntegra = true; //判断是否走以前的积分支付方式
        Map<String, String> resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder); 
		if (resultMap != null && !"-1".equals(resultMap.get("index"))) {// 是促销的商品
			sumCoin = new BigDecimal(resultMap.get("maxJF")).setScale(0, RoundingMode.DOWN);
			if (fiBcoinBalance.getBalance().compareTo(sumCoin) != 1) {
				sumCoin = fiBcoinBalance.getBalance();
			}
			isIntegra = false;
		}
        
        
		//============================2016年1月促销-积分换购================================================end
		
		
		//============================2016-05-24圣诗蔓积分换购(>=5:5)================================================start
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date now = Calendar.getInstance().getTime();
		String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
    	String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
    	try {
	    	if(startCoin != null && endCoin != null){
				if(now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))){
					if(jpoIsOnly(jpoMemberOrder,GlobalVar.jpoList20160524)){
						BigDecimal coin = new BigDecimal(0);
						sumCoin = new BigDecimal(0);//最大使用积分抵扣限制额
						if(null != fiBcoinBalance){
							coin = fiBcoinBalance.getBalance();//积分余额
						}
						if(jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5)).compareTo(coin)==1){
							sumCoin = coin;
						}else{
							sumCoin = jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5));
						}
						//积分支付页面
						isIntegra = false;
					}
				}
			}
	    }catch(Exception e){
	    	log.info("支付失败!",e);
    	}
		
        //============================2016-05-24圣诗蔓积分换购(>=5:5)================================================end
    	
    	//============================2016-05-25   2016年6月积分换购需求开发================================================start
		String jsonStr = "";
		//旋磁椅
		jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606xcy, "201606xcy.startdate", "201606xcy.enddate", fiBcoinBalance, "0", "25000") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}
		//3D床垫、道和国韵（体验装）
    	jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606, "201606coincx.startdate", "201606coincx.enddate", fiBcoinBalance, "1", "0.5") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}
		//道和国韵
		jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201805, "201805coincx.startdate", "201805coincx.enddate", fiBcoinBalance, "1", "0.5") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}
 
		//11月  重消单开启积分换购可用积分试算   GlobalVar.jpocoincxList201611 为不参加积分换购的特殊商品
		jsonStr = PromotionsUtils.checkCoincxPay(jpoMemberOrder, GlobalVar.jpocoincxList201611, "201611coincx.startdate","201611coincx.enddate", fiBcoinBalance,  "0.5") ;
		if(!"-1".equals(jsonStr)){//验证通过  通过返回值得到可使用积分
			isIntegra = false;
			resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
			if (null != resultMap) {
				sumCoin = new BigDecimal(resultMap.get("sumCoin"));
			}
		}
		//============================2016-05-25  2016年6月积分换购需求开发================================================end
        
        
        if(isIntegra && sumCoin.compareTo(new BigDecimal("0"))==1){
        	String msg = "您本张订单总金额" + jpoMemberOrder.getAmount() + "，使用金额" + jpoMemberOrder.getAmount().subtract(sumCoin) + "，使用积分" + sumCoin + "，PV为0";
        	request.setAttribute("msg", msg);
        }
        
    	if("POST".equals(request.getMethod())){
    		boolean b = false;
    		BigDecimal blance = fiBcoinBalance.getBalance();// 现有积分
    		BigDecimal czBlance = jfiBankbookBalance.getBalance();//电子存折余额
    		String isOver = ""; //错误消息
			//============================2016年1月促销-积分换购================================================start
			if(!isIntegra){
				Integer integral = 0;
				try {
					integral = Integer.parseInt(request.getParameter("integral"));// 输入积分
					if (integral <= 0) {
						isOver = "积分输入错误,必须大于0!";
					}
				} catch (NumberFormatException e) {
					isOver = "积分输入错误,必须为整数!";
				}
				if( blance.compareTo(new BigDecimal(integral)) == -1 ){
					isOver = "积分不足!";
				}
				BigDecimal amount = jpoMemberOrder.getAmount().subtract(new BigDecimal(integral)); //实际使用金额(扣除电子存折的金额)
				if(czBlance.compareTo(amount)== -1){
					isOver = "电子存折余额不足!";
				}
				if(StringUtils.isNotEmpty(isOver)){
					return new ModelAndView("jfiOrderProductMsg", "isOver", isOver);
				}
				if(null != resultMap){
					if ("false".equals(resultMap.get("code"))) {
						return new ModelAndView("jfiOrderProductMsg", "isOver", resultMap.get("msg"));
					}
				}
				if(sumCoin.compareTo(new BigDecimal(integral))==-1){
					sumCoin = new BigDecimal(sumCoin.intValue());
					request.setAttribute("sumCoin",sumCoin.intValue());
					return new ModelAndView("jfiOrderProductMsg", "isOver", "该订单最多使用" + sumCoin + "积分!");
				}
				/*if (amount.compareTo(sumCoin) == -1) {
					return new ModelAndView("jfiOrderProductMsg", "isOver", "该订单最多使用" + sumCoin + "积分!");
				}*/
				// 审单扣款
				boolean isCheck = this.checkFlagOne(orderId, loginSysUser, new BigDecimal(integral));
				if (isCheck) {
					b = true;
					saveMessage(request, getText(LocaleUtil.getLocalText("opration.notice.js.orderNo.auditSuccess"), locale));
				} else {
					saveMessage(request, getText(LocaleUtil.getLocalText("poOrder.editedFail"), locale));
				}
			}
			//============================2016年1月促销-积分换购================================================end
			if(isIntegra){
				if(!"5".equals(jpoMemberOrder.getOrderType())){
					return new ModelAndView("jfiOrderProductMsg", "isOver", "该订单不允许积分支付!");
				}
				jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
				boolean isCheck = this.checkFlagOne(orderId, loginSysUser,sumCoin);
				if (isCheck) {
					b = true;
					saveMessage(request, getText(LocaleUtil.getLocalText("opration.notice.js.orderNo.auditSuccess"), locale));
				} else {
					saveMessage(request, getText(LocaleUtil.getLocalText("poOrder.editedFail"), locale));
				}
			}
			/*
			 * 支付改造
			 * if(b){
        		//发送MQ消息     
        		try{
        			jpoMemberOrder.setStatus("3");
        			jpoMemberOrderManager.save(jpoMemberOrder);
        			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, loginSysUser, "1");
        			log.info("=======审单MQ：JfiPayByCoinAndBankbookController,  orderNo:" + jpoMemberOrder.getMemberOrderNo());
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
	 * 审核订单
	 * @param orderId 订单id
	 * @param operatorSysUser 用户
	 * @param sumCoin 所扣积分
	 */
	private boolean checkFlagOne(String orderId, JsysUser operatorSysUser, BigDecimal sumCoin){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		try{
			/*事物统一，平移到manager层，否则会造成支付不成功，但这些数据保存了。
			jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));// 重算订单金额,扣去积分支付部分
			jpoMemberOrder.setDiscountAmount(sumCoin);
			jpoMemberOrder.setPvAmt(new BigDecimal("0"));
			
			Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				jpoMemberOrderList.setPv(new BigDecimal("0"));
				jpoMemberOrderListDao.save(jpoMemberOrderList);
			}
			
			jpoMemberOrder.setPayByCoin("1");
			jpoMemberOrderManager.mergeOrder(jpoMemberOrder);
			*/
			jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, operatorSysUser,"1",sumCoin);
			
		}catch(Exception app){
			log.error(app);
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderId, JsysUser operatorSysUser){
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		
		try{
			
			jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, operatorSysUser,"1");
			
		}catch(Exception app){
			
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否为启智派商品：产品编号P24010100101CN0   
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkQzpProductNo(JpoMemberOrder jpoMemberOrder){
		
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		
		if("P24010100101CN0".equals(productNo)){
			
			return true;
		}
		return false;
	}
	
	

	@Autowired
	public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
		this.fiBcoinBalanceManager = fiBcoinBalanceManager;
	}
}
