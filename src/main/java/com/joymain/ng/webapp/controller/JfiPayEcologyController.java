package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.LocaleUtil;

/**
 * 生态家套餐支付
 * @author hywen
 *
 */
@Controller
@RequestMapping("/jfiPayEcology*")
public class JfiPayEcologyController extends BaseFormController{

    //private final Log log = LogFactory.getLog(Jfi99billLogController.class);

    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;
    
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    
    @Autowired
   	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
   		this.jpoMemberOrderManager = jpoMemberOrderManager;
   	}
    
    @Autowired
	public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
    	this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}
    
    @Autowired
   	public void setFiBankbookBalanceManager(FiBankbookBalanceManager fiBankbookBalanceManager) {
   		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
   	}

    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
    throws Exception {

        JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //查询订单集合
        List<JpoMemberOrder> orderList = new ArrayList<JpoMemberOrder>();
        
        //总金额
        BigDecimal totalAmount = new BigDecimal(0);
        
        String orderIds = request.getParameter("orderIds");
        String orderIdArray[] = orderIds.split(",");
        for(String id:orderIdArray){   
        	
        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(id));
        	orderList.add(jpoMemberOrder);
        	totalAmount = totalAmount.add(jpoMemberOrder.getAmount());
        }

        request.setAttribute("orderIds", orderIds);
        request.setAttribute("orderList", orderList);
        request.setAttribute("totalAmount", totalAmount);
        
        //查询存折余额
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(loginSysUser.getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        
        //查询基金余额
        FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(loginSysUser.getUserCode(),"1");
        request.setAttribute("fjBalance", fiBankbookBalance.getBalance());
        
        return new ModelAndView("jfiPayEcology");
    }
    
    //基金支付提交
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response) {

    	JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                	
    	if("POST".equals(request.getMethod())){
    		
    		List<JpoMemberOrder> orderList = new ArrayList<JpoMemberOrder>();
    		
    		//可使用发展基金数
    		BigDecimal amount = new BigDecimal(request.getParameter("amount"));
    		
    		//预计算发展基金存折混合支付
            String orderIds = request.getParameter("orderIds");
            String orderIdArray[] = orderIds.split(",");
            for(String id:orderIdArray){   
            	
            	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(id));
            	
            	if(amount.compareTo(jpoMemberOrder.getAmount()) == 1){
            		
            		amount = amount.subtract(jpoMemberOrder.getAmount());
            		
            		jpoMemberOrder.setJjAmount(jpoMemberOrder.getAmount());
//            		jpoMemberOrder.setAmount(BigDecimal.ZERO);
            	}else{
            		
            		jpoMemberOrder.setJjAmount(amount);
//            		jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(amount));
            		
            		amount = BigDecimal.ZERO;
            	}
            	
            	orderList.add(jpoMemberOrder);
            }
            
            try{
    			
            	jpoMemberOrderManager.checkJpoBigOrderByJJ(orderList, loginSysUser, "1");
            	
            	saveMessage(request, "订单支付成功！");
    		}catch(Exception app){
    			log.error("",app);
    			saveMessage(request, "订单支付失败！");
    		}
            
            return new ModelAndView("redirect:showBigPage");
        }
    	
    	return new ModelAndView("redirect:showBigPage");
    }
}
