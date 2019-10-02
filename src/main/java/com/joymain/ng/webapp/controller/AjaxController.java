package com.joymain.ng.webapp.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoProductNumLimit;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.model.PdNotChangeProduct;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysResourceManager;
import com.joymain.ng.service.PdExchangeOrderBackManager;
import com.joymain.ng.service.PdExchangeOrderDetailManager;
import com.joymain.ng.service.PdExchangeOrderManager;
import com.joymain.ng.service.PdNotChangeProductManager;
import com.joymain.ng.service.SysIdManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.webapp.util.RequestUtil;

@Controller
@RequestMapping("/ajaxController")
public class AjaxController extends BaseFormController {
	private JsysResourceManager jsysResourceManager;
	@Autowired
    public JmiMemberManager jmiMemberManager;
	@Autowired
    public JpoMemberOrderManager jpoMemberOrderManager;

    @Autowired
    public void setJsysResourceManager(JsysResourceManager jsysResourceManager) {
        this.jsysResourceManager = jsysResourceManager;
    }

    public AjaxController() {
//        setCancelView("redirect:pdExchangeOrders");
//        setSuccessView("redirect:pdExchangeOrders");
    }
    
    /**
     * @Description:	根据传入的菜单编码生成重定向的URL，主要考虑菜单导航选中定位
     * @author:			侯忻宇
     * @date:		    2016-11-10
     * @param request
     * @param response
     * @return
     * @throws Exception:
     * @exception:
     * @return:
     */
    @RequestMapping(value="/redirectByRes",method=RequestMethod.GET)
    public void queryExchangeOrderList(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	PrintWriter out = response.getWriter();
		String str = "";
		String resCode = request.getParameter("resCode");//传入的菜单编码
		
		List commonMenus = jsysResourceManager.getTLMenu(resCode);
		if(!MeteorUtil.isBlankByList(commonMenus)){
			for(Object o:commonMenus){
				Map m = (Map)o;
				request.getSession().setAttribute("parentId", String.valueOf(m.get("TOPID")));
		        request.getSession().setAttribute("currentMenuId", String.valueOf(m.get("RES_ID")));
		        request.getSession().setAttribute("currentSubMenuId", String.valueOf(m.get("RES_ID")));
		        str = "1";
			}
		}
		out.print(str);
		out.close();
    }
    
    
  /**
   * @Description:会员首购单验证  是否已经存在收购单
   * @author:			侯忻宇
   * @date:		    2016-12-5
   * @param request
   * @param response
   * @throws Exception:
   * @exception:
   * @return:
   */
    @RequestMapping(value="/checkUserSgOrder",method=RequestMethod.GET)
    private void checkUserSgOrder(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
    	PrintWriter out = response.getWriter();
    	String str = "1";
		Map<String,String> orderMap=new HashMap<String,String >();
		orderMap.put("userCode", jsysUser.getUserCode());
		orderMap.put("companyCode", jsysUser.getCompanyCode());
		orderMap.put("orderType", "1");
		List jpoMemberOrders = jpoMemberOrderManager.getOrderByParam(orderMap);
		if(jpoMemberOrders!=null && jpoMemberOrders.size()!=0){//会员首单存在
			str = "0";//会员首单已经存在，不允许在下首单
		}
		out.print(str);
		out.close();

    }
    
    /**
     * @Description:局部刷新订单详情页状态用，，暂时不改了。。
     * @author:			侯忻宇
     * @date:		    2016-12-12
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception:
     * @exception:
     * @return:
     */
	public String scAll(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
//		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
		String moId = request.getParameter("moId");
		String moCode = request.getParameter("moCode");
		String str = "0,0,0";
		JpoMemberOrder jpoMemberOrder = null;
		if (StringUtils.isNotEmpty(moId) || StringUtils.isNotEmpty(moCode)) {
			if (StringUtils.isEmpty(moId)) {
				jpoMemberOrder = jpoMemberOrderManager
						.getJpoMemberOrderByMemberOrderNo(moCode);
			} else {
				jpoMemberOrder = jpoMemberOrderManager.get(new Long(moId));
			}
		}
		if(null!=jpoMemberOrder){
			str = jpoMemberOrder.getStatus()+","+jpoMemberOrder.getCheckDate()+","+jpoMemberOrder;
		}
		
		printOutStr(response,str);
		return null;
	}
	
	
	public static void printOutStr(HttpServletResponse response,String str) throws Exception{
		PrintWriter out = response.getWriter();
		out.print(str);
		out.close();
	}

}
