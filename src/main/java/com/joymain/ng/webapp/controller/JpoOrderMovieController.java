package com.joymain.ng.webapp.controller;


import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import com.joymain.ng.Constants;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.handle.movie.TicketMovie;
import com.joymain.ng.handle.shipping.ShippingHandel;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class JpoOrderMovieController extends BaseFormController {
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    @Autowired
    private JmiMemberManager jmiMemberManager=null;
    @Autowired
    private JpoShoppingCartOrderManager jpoShoppingCartOrderManager=null;
    @Autowired
    private JpmProductSaleNewManager jpmProductSaleNewManager=null;
    @Autowired
    JmiAddrBookManager  jmiAddrBookManager=null;
    @Autowired
    private ShippingHandel shippingHandel;
    @Autowired
    private JsysUserManager jsysUserManager;
    
    @Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    
    public JpoOrderMovieController() {
       setSuccessView("redirect:/jpoMemberOrders/orderAll");
    }

    @RequestMapping(value="/jpoOrderMovie")
    public String onSubmit(HttpServletRequest request, 
    		HttpServletResponse response) throws Exception {
    	
    	String success=null;
    	try{
    		log.debug("entering 'onSubmit' method...");
    		String userCode = request.getParameter("userCode");
    		String count = request.getParameter("count");
    		if(StringUtils.isNotBlank(userCode) && StringUtils.isNotBlank(count)){
    			JSONObject jsonObj = TicketMovie.ticket(userCode, Integer.parseInt(count));
    			log.info(jsonObj.toString());
    			String status = jsonObj.get("status").toString();
    			if(status.equals("0")){
    				String moid = request.getParameter("moid");
    				JpoMemberOrder order = jpoMemberOrderManager.get(new Long(moid));
    				order.setStj_movie(1);
    				jpoMemberOrderManager.save(order);
    			} else {
    				saveMessage(request, "暂时无法提取.");
    			}
    		} else {
    			saveMessage(request, "userCode or count is not exist.");
    		}
    		
	        success = getSuccessView();
	        
    	}catch(Exception e){
    		log.error("",e);
    	}
    	log.info("success is:"+success);
    	return success;
    }
    
    //移动用
    @RequestMapping(value="/api/jpoOrderMovie")
	@ResponseBody
   public Map<String,List<String>> MobileAddPoMemberOrder(HttpServletRequest request,
		   String token,String userCode,String count,String moid){
    	Map<String,List<String>>  msgMap=new HashMap<String, List<String>>();
    	try{
    		JsysUser loginSysUser = jsysUserManager.getUserByToken(userCode, token);
	    	Object object = jsysUserManager.getAuthErrorCode(loginSysUser, "Map");
	 		if(null!=object){
	 			return (Map)object;
	 		}
	 		
    		if(StringUtils.isNotBlank(userCode) && StringUtils.isNotBlank(count)){
    			JSONObject jsonObj = TicketMovie.ticket(userCode, Integer.parseInt(count));
    			log.info(jsonObj.toString());
    			String status = jsonObj.get("status").toString();
    			if(status.equals("0")){
    				JpoMemberOrder order = jpoMemberOrderManager.get(new Long(moid));
    				order.setStj_movie(1);
    				jpoMemberOrderManager.save(order);
    			} else {
    				saveMessage(request, "暂时无法提取.");
    			}
    		} else {
    			saveMessage(request, "userCode or count is not exist.");
    		}
	 		
	    }catch (Exception e) {  
    		log.error("",e);
		}
	    return  msgMap;
   }
 
}
