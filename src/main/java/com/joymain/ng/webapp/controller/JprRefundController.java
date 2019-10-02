package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JprRefundManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/jprRefunds/")
public class JprRefundController {
    private JprRefundManager jprRefundManager;
    
    private JpoMemberOrderManager jpoMemberOrderManager;

    @Autowired
    public void setJprRefundManager(JprRefundManager jprRefundManager) {
        this.jprRefundManager = jprRefundManager;
    }
    
    @Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

    /**
     * 退货单查询
     * @author gw 2015-06-12
     * 
     */
	@RequestMapping("/jprRefunds")
    public String getJprRefundQuery(HttpServletRequest request){
		String returnView = "jprRefundLists";
    	String roNo = request.getParameter("roNo");
    	String memberOrderNo = request.getParameter("memberOrderNo");
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userCode = defUser.getUserCode();
        String timeBegin = request.getParameter("timeBegin");
		String timeEnd = request.getParameter("timeEnd");
		// 处理字符串：处理如果传递过来的字符串为“null”的情况
		roNo = StringUtil.dealStr(roNo);
		memberOrderNo = StringUtil.dealStr(memberOrderNo);
		/*if(!StringUtil.isEmpty(memberOrderNo)){
			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
			if(null!=jpoMemberOrder){
				if(!StringUtil.isEmpty(jpoMemberOrder.getMemberOrderNo())){
				      memberOrderNo = jpoMemberOrder.getMoId().toString();
				}
			}
		}*/
		timeBegin = StringUtil.dealStr(timeBegin);
		timeEnd = StringUtil.dealStr(timeEnd);
		// 分页单位：固定写法
		Integer pageSize = StringUtil.dealPageSize(request);
		// 创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("", "jprRefunds?roNo="+roNo+"&memberOrderNo=" +memberOrderNo
				+ "&userCode=" + userCode + "&timeBegin=" + timeBegin + "&timeEnd="
				+ timeEnd + "&pageSize=" + pageSize,
				pageSize, request);
		List jprRefundsList = jprRefundManager.getJprRefundsListPage(page, roNo, memberOrderNo, userCode, timeBegin, timeEnd);
		request.setAttribute("page", page);
		request.setAttribute("jprRefundsList",jprRefundsList);
    	
    	return returnView;
    }
    
}
