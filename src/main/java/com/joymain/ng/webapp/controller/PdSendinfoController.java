

package com.joymain.ng.webapp.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
public class PdSendinfoController {
	private PdSendInfoManager pdSendInfoManager;
	private JpmProductSaleNewManager jpmProductSaleNewManager;
    
    @Autowired
    public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}
    
    @Autowired
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}



	/**
     * 会员系统－客户管理－客户维护－查询
     * @param wucf 2014-01-5
     * @param request
     * @return string
     */
    @RequestMapping(value="/pdSendinfoQuery",method=RequestMethod.GET)
    public String getLinkmanController(HttpServletRequest request){
    	String returnView = "pdSendinfoQuery";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		
		//发货单编号、订单号
    	String siNo = request.getParameter("siNo");
    	String orderNo = request.getParameter("orderNo"); 
    	String orderFlag = request.getParameter("orderFlag");
    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//处理字符串
    	siNo = StringUtil.dealStr(siNo);
    	orderNo = StringUtil.dealStr(orderNo);   
    	orderFlag = StringUtil.dealStr(orderFlag);   
    	
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","pdSendinfoQuery?siNo="+siNo+"&orderNo="+orderNo+"&orderFlag="+orderFlag+"&pageSize="+pageSize,pageSize,request);
		
    	List pdSendInfoList = pdSendInfoManager.getPdSendInfoPage(page,userCode,siNo,orderNo,orderFlag);
    	
    	request.setAttribute("page", page);
    	
    	//查询客户的信息-因为涉及到的客户人数不是很多，因此暂时不考虑分页
//    	List linkmanList = linkmanManager.getLinkmanBybaseInfo(name,sex,mobilePhone,userCode,lcId,customerFocus);
    	
    	//获得商品编码和名称明细
    	Map<String,String> jpmProductNewMap = jpmProductSaleNewManager.getJpmProductSaleNoAndName("0,1,2");
    	
    	//像页面传递值－这里应该是能分页的集合,因为客户人数不是很多，暂时不给分页
    	request.setAttribute("pdSendInfoList", pdSendInfoList);
    	request.setAttribute("siNo", siNo);
    	request.setAttribute("orderNo", orderNo);
    	request.setAttribute("orderFlag", orderFlag);
    	request.setAttribute("jpmProductNewMap", jpmProductNewMap);
    	return returnView;
    }
}
