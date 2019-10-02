package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
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

import com.joymain.ng.Constants;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.FiBcoinPayconfigDetail;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.FiBcoinPayconfigDetailManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.chinapnr.Chinapnr;

/**
 * 汇付天下付款页面
 * @author lzg
 *
 */
@Controller
@RequestMapping("/jfiPayChinapnr*")
public class JfiPayChinapnrController extends BaseFormController{
	
private final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	
	@Autowired
	private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
	
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	
	@Autowired
    private FiBcoinBalanceManager fiBcoinBalanceManager;// 积分换购方法
	
	@Autowired
    private FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//当前用户
		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//当前订单ID
		String orderId = request.getParameter("orderId");
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));

		 if(validateOrder(jpoMemberOrder,loginSysUser)){
			log.info(jpoMemberOrder.getMemberOrderNo() + 
					LocaleUtil.getLocalText("user.validate"));
			return new ModelAndView("jfiPayChinapnr", "entity", 
					LocaleUtil.getLocalText("user.validate"));
		 }  
		 
		// flag(0:为电子存折 1:订单)
        String flag = request.getParameter("flag");
        if ("1".equals(flag))
        {
            
            //积分支付判断开关
            if(("1").equals(getAuthorityBcoinPayByOrder(jpoMemberOrder))){
            	 request.setAttribute("coinPay", "true");
            }

            request.setAttribute("jpoMemberOrder", jpoMemberOrder);
            JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
            request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        }
        
        if(StringUtils.isEmpty(flag)){
        	request.setAttribute("flag", 0);
        }else{
        	request.setAttribute("flag", flag);
        }

        //汇付天下
        Chinapnr entity = new Chinapnr();
        entity.setOrdId(orderId);
        //entity.setMerorderid(orderId);
        
        JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        request.setAttribute("jsysUser", jsysUser);
        
        return new ModelAndView("jfiPayChinapnr", "entity", entity);
        
	}
		
	/**
     * 判断是否为启智派商品：产品编号P24010100101CN0
     * 
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkQzpProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        
        if ("P24010100101CN0".equals(productNo))
        {
            
            return true;
        }
        return false;
    }
    
    /**
     * 中秋月饼促销，产品编号：P23030100101CN0,P23030200101CN0
     * 
     * @param jpoMemberOrder
     * @return
     */
    private boolean isMooncakesProduct(JpoMemberOrder jpoMemberOrder)
    {
        
        boolean flag = false;
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        while (its1.hasNext())
        {
            
            JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            String productNo =
                jpoMemberOrderList.getJpmProductSaleTeamType()
                    .getJpmProductSaleNew()
                    .getJpmProduct()
                    .getProductNo();
            
            if (("P23030100101CN0").equals(productNo) || ("P23030200101CN0").equals(productNo))
            {
                
                flag = true;// 含有月饼促销产品
                break;
            }
        }
        
        if (flag == true)
        {
            
            while (its1.hasNext())
            {
                JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
                String productNo =
                    jpoMemberOrderList.getJpmProductSaleTeamType()
                        .getJpmProductSaleNew()
                        .getJpmProduct()
                        .getProductNo();
                
                if (!("P23030100101CN0").equals(productNo)
                    && !("P23030200101CN0").equals(productNo))
                {
                    
                    flag = false;
                    break;
                }
            }
        }
        
        return flag;
    }
    
    /**
     * 判断是否为三代无忧奖励计划产品、台湾旅游积分产品：产品编号N07010100101CN0,N07020100101CN0
     * 
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkTWLYJFProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        
        if ("N07010100101CN0".equals(productNo) || "N07020100101CN0".equals(productNo))
        {
            
            return true;
        }
        return false;
    }
    
    /**
     * 云南团队：产品编号P03190100102CN0,P03200100102CN0
     * 
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkYNProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        
        if ("P03190100102CN0".equals(productNo) || "P03200100102CN0".equals(productNo))
        {
            
            return true;
        }
        return false;
    }
    
    /**
     * 限制订购数量：产品编号P25090100101CN0,P25090100201CN0,P25090100301CN0,P25100100101CN0,
     * P25100100201CN0,P25100100301CN0,P25110100101CN0,P25110100201CN0
     * 
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkNumProductNo(JpoMemberOrder jpoMemberOrder)
    {
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        String productNo =
            jpoMemberOrderList.getJpmProductSaleTeamType()
                .getJpmProductSaleNew()
                .getJpmProduct()
                .getProductNo();
        
        if ("P25090100201CN0".equals(productNo) || "P25090100301CN0".equals(productNo)
            || "P25100100101CN0".equals(productNo) || "P25100100201CN0".equals(productNo)
            || "P25100100301CN0".equals(productNo) || "P25110100101CN0".equals(productNo)
            || "P25110100201CN0".equals(productNo) || "P25090100101CN0".equals(productNo))
        {
            
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
                log.info("-------------------------------------------------333333333333333333333"
                    + jpoMemberOrderManager.getIsOver3(productNo, orderList.getQty()));
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
    
    public boolean checkNumByOrder(JpoMemberOrder jpoMemberOrder)
    {
        Iterator it = jpoMemberOrder.getJpoMemberOrderList().iterator();
        // 迭代出订单下商品
        while (it.hasNext())
        {
            
            JpoMemberOrderList orderList = (JpoMemberOrderList) it.next();
            String proNo =
                orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
            
            // 已购买数量
            Integer proNum = jpoMemberOrderListManager.getSumQtyByProNo(proNo);
            
            // 新订单数量+已购买数量=总数量
            Integer num = proNum + orderList.getQty();
            return checkNum(proNo, num);
            
        }
        return true;
    }
    
    public boolean checkNum(String proNo, Integer num)
    {
        if (Constants.PROC.equals(proNo))
        {
            if (num > Constants.PROCnum)
            {
                return false;
            }
        }
        else if (Constants.PROC1.equals(proNo))
        {
            if (num > Constants.PROC1num)
            {
                return false;
            }
        }
        else if (Constants.PROC2.equals(proNo))
        {
            if (num > Constants.PROC2num)
            {
                return false;
            }
        }
        else if (Constants.PROC3.equals(proNo))
        {
            if (num > Constants.PROC3num)
            {
                return false;
            }
        }
        else if (Constants.PROC4.equals(proNo))
        {
            if (num > Constants.PROC4num)
            {
                return false;
            }
        }
        else if (Constants.PROC5.equals(proNo))
        {
            if (num > Constants.PROC5num)
            {
                return false;
            }
        }
        else if (Constants.PROC6.equals(proNo))
        {
            if (num > Constants.PROC6num)
            {
                return false;
            }
        }
        else if (Constants.PROC7.equals(proNo))
        {
            if (num > Constants.PROC7num)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 6财月促销商品检测
     * @param jpoMemberOrder
     * @return
     */
    public boolean checkCX6(JpoMemberOrder jpoMemberOrder)
    {
    	//不参加团队：田阳2、5  TT2：CN16481747, TT5；CN18728599
        if(("CN16481747").equals(jpoMemberOrder.getTeamCode()) || ("CN18728599").equals(jpoMemberOrder.getTeamCode())){
        	
        	return false;
        }
        
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        while (its1.hasNext()){
        	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
	        String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
	        
	        //YGD积分换购：YGD：CN12365064 ,不参加产品：P1604010101CN0颐丽超音波熏香灯芯头
	        if(("CN12365064").equals(jpoMemberOrder.getTeamCode())){
	        	
	        	if ("P1604010101CN0".equals(productNo)){
	        		
	        		return false;
	        	}        	
	        }
	        
	        //不参加产品：P03210100101CN0颐佳漱爽露便携装 ,P01050200101CN0中脉有乐生命活能饮（旅行装）,P01170400101CN0Cellight海洋单细胞海藻胶囊（5瓶装） //Modify By WuCF 20150216 添加P04060100101CN0
	        if ("P03210100101CN0".equals(productNo) || "P01050200101CN0".equals(productNo) || "P01170400101CN0".equals(productNo) || "P04060100101CN0".equals(productNo))
	        {
	            
	            return false;
	        }

	        //String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
	        int buyNum = jpoMemberOrderList.getQty();
	        
	        //限购产品：1、P04010212001CN0中脉远红磁性保健功能床垫（复合套装）规格：120CM*190CM 数量：1000
	       	//2、 P04010119001CN0中脉远红磁性保健功能床垫（复合套装）规格：150CM*190CM 数量：1000
	        //3、 P04010118001CN0能量睡眠系列中脉健康床垫180cm 数量：2000
	        //4、 P04010115001CN0中脉远红磁性保健功能床垫150cm*200cm 数量：1000
	        //5、 P04010300101CN0  能量睡眠系列中脉健康床垫(单只装)  数量 2000
	        if ("P04010212001CN0".equals(productNo) || "P04010119001CN0".equals(productNo) || "P04010118001CN0".equals(productNo) || "P04010115001CN0".equals(productNo) || "P04010300101CN0".equals(productNo))
	        {
	        	FiBcoinPayconfigDetail fiBcoinPayconfigDetail = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetailByProductId(productNo);
	        	if(fiBcoinPayconfigDetail!=null){
	        		
	        		if(fiBcoinPayconfigDetail.getNowQuantity() == 0 || fiBcoinPayconfigDetail.getNowQuantity()<buyNum){
	        			return false;
					}
	        	}else{
	        		 return false;
	        	}
	        }
        }
        return true;
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
