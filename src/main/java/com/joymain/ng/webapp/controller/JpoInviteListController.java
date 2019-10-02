package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpoInviteListManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.webapp.util.RequestUtil;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jpoInviteLists/")
public class JpoInviteListController {
	private org.apache.commons.logging.Log log = LogFactory.getLog(JpoInviteListController.class);
	private JpoInviteListManager jpoInviteListManager;
	private JsysUserManager jsysUserManager;

    @Autowired
    public void setJpoInviteListManager(JpoInviteListManager jpoInviteListManager) {
        this.jpoInviteListManager = jpoInviteListManager;
    }
	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}
    /**
     * 会员订单统计
     * @param WuCF 2017-05-19
     * @param request
     * @return string
     */
    @RequestMapping(value="jpoInviteListMaintenance",method=RequestMethod.GET)
    public String getLinkmanController(HttpServletRequest request){
    	String returnView = "jpoInviteLists";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		CommonRecord crm = RequestUtil.toCommonRecord(request);

		try{

			//String inviteUserCode = request.getParameter("inviteUserCode");
			//只能查询用户自己的邀请码
			String inviteUserCode = userCode;
			String inviteCode = request.getParameter("inviteCode");
			String memberOrderNo = request.getParameter("memberOrderNo");

	    	//处理字符串
			inviteUserCode = StringUtil.dealStr(inviteUserCode);
			inviteCode = StringUtil.dealStr(inviteCode);
			memberOrderNo = StringUtil.dealStr(memberOrderNo);


	    	//存放条件
			crm.setValue("inviteUserCode", inviteUserCode);
	    	crm.setValue("inviteCode", inviteCode);
	    	crm.setValue("memberOrderNo", memberOrderNo);

	    	//----------------------Modify By WuCF 添加分页展示功能
	    	//分页单位：固定写法
	    	Integer pageSize = StringUtil.dealPageSize(request);

			//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
	    	GroupPage page = new GroupPage("jpoInviteLists","jpoInviteListMaintenance?inviteUserCode="+inviteUserCode+"&inviteCode="+inviteCode
					+"&memberOrderNo="+memberOrderNo+"&pageSize="+pageSize,pageSize,request);
			request.setAttribute("page", page);


	    	List jpoInviteLists = jpoInviteListManager.getJpoInviteListPage(page,crm);
	    	request.setAttribute("jpoInviteLists", jpoInviteLists);
	        //设置参数条件值
	    	request.setAttribute("inviteUserCode", inviteUserCode);
	    	request.setAttribute("inviteCode", inviteCode);
	    	request.setAttribute("memberOrderNo", memberOrderNo);

		}catch(Exception e){
			e.printStackTrace();
		}
    	return returnView;
    }
	
}
