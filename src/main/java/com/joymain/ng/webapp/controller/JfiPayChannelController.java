package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.Iterator;

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

import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalCountryManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.chanjet.Chanjet;
import com.joymain.ng.util.channel.ChannelBean;

/**
 * 盛付通支付表单
 * @author hywen
 *
 */
@Controller
@RequestMapping("/jfiPayChannel")
public class JfiPayChannelController extends BaseFormController{
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	
	@Autowired
	private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
	
	@Autowired
	private JalCountryManager alCountryManager;
	
	@Autowired
	private JalCityManager alCityManager;
	
	@Autowired
	private JalDistrictManager alDistrictManager;
	
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	
	@Autowired
    private JpmProductSaleNewManager  jpmProductSaleNewManager=null;
	
	@Autowired
    private FiBcoinBalanceManager fiBcoinBalanceManager;// 积分换购方法
	
		
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//当前用户
		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//当前订单ID
		String orderId = request.getParameter("orderId");

        //flag(0:为电子存折  1:订单)
        String flag = request.getParameter("flag");
        if("1".equals(flag)){
        	
        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
        	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        	if("CN".equals(jpoMemberOrder.getCompanyCode())){

            	while (its1.hasNext()) {
            		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            		
            		String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();//商品状态，是否过期
            		
            		//商品状态
            		if(!"P26010100101CN0".equalsIgnoreCase(jpoMemberOrderList.
            				getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())){
            			if(!"1".equals(status)){
                			return new ModelAndView("jfiOrderProductMsg", "isOver", "产品(" + 
            			jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo() + ")已停售!");
                		}
            		} 
            	}    
            	
            	 if(validateOrder(jpoMemberOrder,loginSysUser)){
         			log.info(jpoMemberOrder.getMemberOrderNo() + 
         					LocaleUtil.getLocalText("user.validate"));
         			return new ModelAndView("jfiOrderProductMsg", "isOver", 
         					LocaleUtil.getLocalText("user.validate"));
         		}  
            	//验证产品购买数量是否超过限制
            	if(isBuyPro(jpoMemberOrder)){
            		
            		return new ModelAndView("jfiOrderProductMsg", "isOver", "产品已售完或剩余不足!");
            	}

        	}else{
        		return new ModelAndView("jfiOrderProductMsg", "isOver", "订单不存在!");
        	}
        	
        	//设置支付后返回的url
			String url = "jpoMemberOrders/orderAll?needReload=1";
			
			if(!StringUtils.isEmpty(url)){
				request.setAttribute("url", url);
			}
        	
        	if("30".equals(jpoMemberOrder.getOrderType())){

        		return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        	}
        	 
        	//订单为空，订单状态已审核，不是当前用户
        	if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())){
        		
        		return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
        	}

        	 //积分支付判断开关
            if(("1").equals(getAuthorityBcoinPayByOrder(jpoMemberOrder))){
            	 request.setAttribute("coinPay", "true");
            }
    		
        	request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        	request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        }
        if(StringUtils.isEmpty(flag))
        {
        	request.setAttribute("flag", 0);
        }else
        {
        	request.setAttribute("flag", flag);
        }


        ChannelBean channel = new ChannelBean(); 
        channel.setOrderNo(orderId);
        
        JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("jsysUser", jsysUser);
        
        return new ModelAndView("jfiPayChannel", "channel", channel);
        
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
	
	/**
	 * 中秋月饼促销，产品编号：P23030100101CN0,P23030200101CN0
	 * @param jpoMemberOrder
	 * @return
	 */
	private boolean isMooncakesProduct(JpoMemberOrder jpoMemberOrder) {
		
		boolean flag = false;
		
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			
			if(("P23030100101CN0").equals(productNo) || ("P23030200101CN0").equals(productNo)){
				
				flag = true;//含有月饼促销产品
				break;
			}
		}
		
		if(flag==true){
			
	    	while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
				
				if(!("P23030100101CN0").equals(productNo) && !("P23030200101CN0").equals(productNo)){
					
					flag = false;
					break;
			    }
			}
		}
		
		return flag;
	}
	
	/**
	 * 判断是否为三代无忧奖励计划产品、台湾旅游积分产品：产品编号N07010100101CN0,N07020100101CN0
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkTWLYJFProductNo(JpoMemberOrder jpoMemberOrder){
		
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		
		if("N07010100101CN0".equals(productNo) || "N07020100101CN0".equals(productNo)){
			
			return true;
		}
		return false;
	}
	/**
     * 2015五财月积分换购
     * 
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkFiveProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        String teamId = jpoMemberOrder.getTeamCode();
        if ("P01170100101CN0".equals(productNo) || "P08420300201CN0".equals(productNo)
            || "P1508010101CN0".equals(productNo) || "P01290100201CN0".equals(productNo))
        {
            if (null != teamId && !"".equals(teamId))
            {
                if (!"CN16481747".equals(teamId) && !"CN18728599".equals(teamId)
                    && !"CN30768473".equals(teamId) && !"CN12365064".equals(teamId))
                {
                    log.info("-------------------------------------------------1");
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 2015五财月积分换购
     * 
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkFiveYGDProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        String teamId = jpoMemberOrder.getTeamCode();
        if ("P1604010201CN0".equals(productNo) || "P16120100201CN0".equals(productNo))
        {
            if (null != teamId && !"".equals(teamId))
            {
                if ("CN12365064".equals(teamId))
                {
                    log.info("-------------------------------------------------2");
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 2015五财月积分换购,限购数量的商品
     * 
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkFiveNumProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        String teamId = jpoMemberOrder.getTeamCode();
        if ("P25040200201CN0".equals(productNo) || "P25040200301CN0".equals(productNo)
            || "P25040100201CN0".equals(productNo)) // ||
                                                    // "P25040100301CN0".equals(productNo))
        {
            Iterator it = jpoMemberOrder.getJpoMemberOrderList().iterator();
            
            while (it.hasNext())
            {
                JpoMemberOrderList orderList = (JpoMemberOrderList) it.next();
                log.info("-------------------------------------------------333333333333333333333"+jpoMemberOrderManager.getIsOver3(productNo, orderList.getQty()));
                if (jpoMemberOrderManager.getIsOver3(productNo, orderList.getQty()) == 0)
                {
                    log.info("falsefalsefalsefalsefalsefalsefalsefalsefalsefalsefalsefalsefalse");
                    return false;
                }
            }
            
            if (null != teamId && !"".equals(teamId))
            {
                if (!"CN16481747".equals(teamId) && !"CN18728599".equals(teamId)
                    && !"CN30768473".equals(teamId) && !"CN12365064".equals(teamId))
                {
                    log.info("-------------------------------------------------3");
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 判断是否可以用积分支付
     * @param jpoMemberOrder
     * @return
     */
    public String getAuthorityBcoinPayByOrder(JpoMemberOrder jpoMemberOrder)
    {

        // 5:辅消单
        if ("5".equals(jpoMemberOrder.getOrderType()))
        {
            // 判断是不是优惠订购的产品
            int preferential = jpoMemberOrderManager.getPreferentialOrder(jpoMemberOrder);
            if (preferential == 2)
            {// 2代表订单通过 需要计算物流费，允许用积分换购
                return "1";
            }else{
            	return "0";
            }
        }
        
        // 积分，只有特定的订单类型能用,4：重消单　9：一级店铺重消　14：二级店铺重消
        if ("4".equals(jpoMemberOrder.getOrderType()) || "9".equals(jpoMemberOrder.getOrderType()) || "14".equals(jpoMemberOrder.getOrderType()))
        {
            FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
            if (new BigDecimal(1).compareTo(fiBcoinBalance.getBalance()) == 1){
            	return "0";
            }
            
//            int payByCoin = Integer.parseInt(ConfigUtil.getConfigValue("CN", "paybycoin"));
//            if (payByCoin == 1){
//            	return "1";// 积分支付打开
//            }
            
            if(checkAbleCoinProductNo(jpoMemberOrder)){
            	return "1";
            }
            
            // 判断是否为启智派商品
            if (checkQzpProductNo(jpoMemberOrder))
            {
                return "1";
            }
//            try {
//            	
//            	String str_startDate = LocaleUtil.getLocalText("zh_CN", "cx.startDate");
//            	String str_endDate = LocaleUtil.getLocalText("zh_CN", "cx.endDate");
//            	Date starDate = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss", str_startDate);
//				Date endDate = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss", str_endDate);
//				Date curDate = Calendar.getInstance().getTime();
//				//2-14至3-20
//	    		if(curDate.after(starDate) && curDate.before(endDate)){
//	
//					//6财月促销 start
//	                if (this.checkCX6(jpoMemberOrder))
//	                {
//	                	return "1";//积分支付打开
//	                }else{
//	                	return "0";
//	                }
//	                //6财月促销 end
//				}
//	    		//2-14之前
//	    		if(curDate.before(starDate)){
//	    			
//	    			// 判断第五财月积分换购
//	                if (checkFiveProductNo(jpoMemberOrder))
//	                {
//	                	 return "1";// 积分支付打开
//	                }
//	                
//	                // 判断第五财月积分换购
//	                if (checkFiveYGDProductNo(jpoMemberOrder))
//	                {
//	                	 return "1";// 积分支付打开
//	                }
//	                
//	                // 判断第五财月积分换购
//	                if (checkFiveNumProductNo(jpoMemberOrder))
//	                {
//	                	 return "1";// 积分支付打开
//	                }
//	                
//	                if(!checkNumByOrder(jpoMemberOrder))
//	    	        {
//	    	            return "0";
//	    	        }
//	                
//	                //限制数量的商品
//	                if (checkNumProductNo(jpoMemberOrder))
//	                {
//	                    return "1";// 积分支付打开
//	                }
//	                
//	                //云南团队促销
//	                if (checkYNProductNo(jpoMemberOrder))
//	                {
//	                    return "1";
//	                }
//	                // 中秋月饼促销
//	                if (this.isMooncakesProduct(jpoMemberOrder))
//	                {
//	                    return "1";
//	                }
//	                
//	                // 调用公共方法判断是否单独购买九款特殊商品（积分换购),返回true代表是
//	                if (jpoIsOnly(jpoMemberOrder))
//	                {
//	                    return "1";
//	                }
//	    		}
//			} catch (ParseException e) {
//				
//				e.printStackTrace();
//				return "0";
//			}
        }
        
        return "0";
    }
    
    /**
     * 13财月促销---积分换购
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkAbleCoinProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        
        //13财月促销---积分换购,积分换购商品编码：P1508010101CN0 
        if ("P1508010101CN0".equals(productNo))
        {
            
            return true;
        }
        return false;
    }
}
