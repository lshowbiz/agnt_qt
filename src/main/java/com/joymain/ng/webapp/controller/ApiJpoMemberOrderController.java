package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/jpoMemberOrders/")
public class ApiJpoMemberOrderController {
    private JpoMemberOrderManager jpoMemberOrderManager;
    private JsysUserManager jsysUserManager;

    @Autowired
    public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}

	@Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jpoMemberOrderManager.search(query, JpoMemberOrder.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpoMemberOrderManager.getAll());
        }
        return model;
    }
    
    @RequestMapping(value="orderAll",method = RequestMethod.GET)
    @ResponseBody
    public List<JpoMemberOrder> scAll(Model model,HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	String id=request.getParameter("id");//订单编号
        String token=request.getParameter("token");//历史订单
    	JsysUser jsysUser = jsysUserManager.getUserByToken(id, token); 
    	Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List");
		if(null!=object){
			return (List)object;
		}
        String memberOrderNO=request.getParameter("memberOrderNO");//订单编号
        String orderTime=request.getParameter("orderTime");//历史订单
        String status=request.getParameter("status");//订单状态
        String orderType=request.getParameter("orderType");//订单类型
        String isShipments=request.getParameter("isShipments");//订单发货状态
    
    	Map<String ,String> map=new HashMap<String, String>();
    	map.put("memberOrderNO", memberOrderNO);
    	map.put("orderTime", orderTime);
    	map.put("status", status);
    	map.put("orderType", orderType);
    	map.put("isShipments", isShipments);
    	map.put("userCode", jsysUser.getUserCode());
    	map.put("companyCode", jsysUser.getCompanyCode());
    	List<JpoMemberOrder> orderList=jpoMemberOrderManager.getOrderByParam(map);
    	System.out.println(orderList.size());
    	return orderList;
    }
}
