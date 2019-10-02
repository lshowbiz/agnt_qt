package com.joymain.ng.webapp.controller;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.PdExchangeOrderManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.GsonUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PdSelectNewProductSortedController {
    private final Log log = LogFactory.getLog(PdSelectNewProductSortedController.class);
    @Autowired
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    @Autowired
    private JpmProductSaleNewManager  jpmProductSaleNewManager=null;
    @Autowired
    private PdExchangeOrderManager pdExchangeOrderManager = null;
    
    @Autowired
    private JmiTeamManager jmiTeamManager;

	
	@RequestMapping(value = "/pdSelectNewProductSorted", method = RequestMethod.GET)
	//@ResponseBody
	public String scAll(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		
		// 初始化登录的一些基本信息，例如：登录用户，地址，会员信息
		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		// 登录账号
		String userCode = loginSysUser.getUserCode();
		// 公司编码
		String companyCode = loginSysUser.getCompanyCode();
		request.setAttribute("userCode", userCode);
		request.setAttribute("companyCode", companyCode);

		//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
		// Integer pageSize = 20; //默认每页显示20条记录
		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		String orderNo = request.getParameter("orderNo");
		String orderType = "";
		String teamCode = "";
		if (!Strings.isNullOrEmpty(orderNo)) {
			// 从原订单中获取的team_code和order_type
			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderNo);
			if (jpoMemberOrder != null) {
				//以前的团队编号从原订单里面获取
				//teamCode = jpoMemberOrder.getTeamCode();
				//需求变更：团队产品换货设置以会员所属最新团队的规则为准
				teamCode = jmiTeamManager.teamStr(loginSysUser);
				orderType = jpoMemberOrder.getOrderType();
			}
		}
		request.setAttribute("jpoMemberOrder", jpoMemberOrder);
		
		String sortKeyword = request.getParameter("sortKeyword");
		String sortFlag = request.getParameter("sortFlag");//"0"代表升序，"1"代表降序
		
		String sortParam = "&sortKeyword=" + sortKeyword + "&sortFlag=" + sortFlag; 
		// 创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","pdSelectNewProductSorted?&orderNo="+ orderNo + sortParam + "&pageSize=" + pageSize, pageSize, request);
		
		// GroupPage("pdExchangeOrderform","orderSelfHelpExchange?strAction=selectNewProduct&moId"+moId+"&pageSize="+pageSize,pageSize,request);
		request.setAttribute("page", page);
		
		List<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = jpmProductSaleNewManager
				.getJpmProductSaleTeamTypeListPageSorted(page,orderType, teamCode, sortKeyword, sortFlag);
		
		
		request.setAttribute("jpmProductSaleTeamTypeList", jpmProductSaleTeamTypeList);
		// 分页时依然保存到orderNo 这个参数
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("sortKeyword", sortKeyword);
		request.setAttribute("sortFlag", sortFlag);
		
		//返回结果
		return "pdProductSelect";
		
	}

}
