package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.FoundationItemManager;
import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.FoundationItem;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 * 公益基金产品订单处理控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/foundationOrderform*")
public class FoundationOrderFormController extends BaseFormController {
    private FoundationOrderManager foundationOrderManager = null;
    private FoundationItemManager foundationItemManager = null;

    @Autowired
    public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
        this.foundationItemManager = foundationItemManager;
    }
    @Autowired
    public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager; 
    }

    public FoundationOrderFormController() {
        setCancelView("redirect:foundationOrders");
        setSuccessView("redirect:foundationOrders");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FoundationOrder showForm(HttpServletRequest request)
    throws Exception {
    	
    	//创建订单初始化
        String fiId = request.getParameter("fiId");
        
		String fiNum=request.getParameter("fiNum");
		
		FoundationOrder foundationOrder = new FoundationOrder();
		FoundationItem foundationItem = null;
		
		//前台家基金跳转
		String fimg=request.getParameter("img");
		if(!StringUtil.isEmpty(fimg)){
			
			foundationItem = foundationItemManager.getFoundationItemByFiled1(fimg+".jpg");
		}
		
		//
		if(!StringUtil.isEmpty(fiId)){
			foundationItem = foundationItemManager.get(Long.parseLong(fiId));
		}

		//当前用户
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	log.info("user is:"+jsysUser.getUserCode());
		foundationOrder.setFoundationItem(foundationItem);
		foundationOrder.setUserCode(jsysUser.getUserCode());//会员编号
		
		if(!StringUtil.isEmpty(fimg)){
			
			foundationOrder.setFiNum(1);//数量默认为1个
			foundationOrder.setAmount(foundationItem.getAmount());
		}else{
			
			foundationOrder.setFiNum(Integer.parseInt(fiNum));
			foundationOrder.setAmount(new BigDecimal(fiNum).multiply(foundationItem.getAmount()));//总金额
		}

		
        return foundationOrder;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FoundationOrder foundationOrder, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        
        //项目编号
		String fiId = request.getParameter("fiId");
		String fiNum = request.getParameter("fiNum");

		//当前用户
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //判断爱心365,参与过的不需要再参与
		FoundationItem foundationItem = foundationItemManager.get(new Long(fiId));
//		if(("1").equals(foundationItem.getType())){
//			
//			//查询当前会员订单
//			List tempList=foundationOrderManager.getOrdersByItemTypeAndTime(jsysUser.getUserCode());
//			
//			if(tempList!=null && tempList.size()>0){
//				
//				FoundationOrder tempOrder=(FoundationOrder) tempList.get(0);
//				
//				//如果已经支付
////				if(("1").equals(tempOrder.getStatus())){
////					
////					saveMessage(request, LocaleUtil.getLocalText("365foundation.already.pay"));
////					return "redirect:foundationItems";
////				}
//				//如果未支付，则去支付
//				if(("0").equals(tempOrder.getStatus())){
//					
//					return "redirect:payFoundation?orderId="+tempOrder.getOrderId();
//				}
//			}
//		}

        //创建新订单
		foundationOrder.setFoundationItem(foundationItem);
		foundationOrder.setUserCode(jsysUser.getUserCode());
        foundationOrder.setStatus("0"); //默认为未审核
		foundationOrder.setCreateTime(new Date());//创建时间
		foundationOrder.setFiNum(Integer.parseInt(fiNum));
		foundationOrder.setAmount(new BigDecimal(fiNum).multiply(foundationItem.getAmount()));//总金额
		
		//保存数据并返回订单号
		Long orderId = foundationOrderManager.saveFoundationOrder(foundationOrder);

		//直接跳转到支付页面
        return "redirect:payFoundation?orderId="+orderId;
    }
}
