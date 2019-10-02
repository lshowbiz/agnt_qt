package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.LinkmanCategory;
import com.joymain.ng.model.LinkmanDemand;
import com.joymain.ng.service.LinkmanCategoryManager;
import com.joymain.ng.service.LinkmanDemandManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkmanGoodsController {
	public static final String MESSAGES_KEY = "successMessages";
	
    
    private LinkmanManager linkmanManager;
    
    private LinkmanDemandManager linkmanDemandmanager;
    
    @Autowired
    private LinkmanCategoryManager linkmanCategoryManager;
    

    @Autowired
    public void setLinkmanManager(LinkmanManager linkmanManager) {
		this.linkmanManager = linkmanManager;
	}

    @Autowired
	public void setLinkmanDemandmanager(LinkmanDemandManager linkmanDemandmanager) {
		this.linkmanDemandmanager = linkmanDemandmanager;
	}

	//----------------------------------------------------------------------------客户的商品修改部分--------------------开始
	
	/**
     * 客户管理--客户的商品---查询(初始化查询或有条件查询)---修改
     * @author gw 2013-10-15
     * @param request
     * @return string 
     */
    @RequestMapping("/linkmanDemandGoodsQuery")
    public String getLinkmanDemandQueryGoods(HttpServletRequest request){
    	String returnView = "linkmanGoodss";
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
    	String name = request.getParameter("name");
    	String buyTimeBegin = request.getParameter("buyTimeBegin");
    	String buyTimeEnd = request.getParameter("buyTimeEnd");
    	String buyQuantityBegin = request.getParameter("buyQuantityBegin");
    	String buyQuantityEnd = request.getParameter("buyQuantityEnd");
    	//如果购买数量输入的不是数字,那么将这个查询条件清空
    	if(!StringUtil.isEmpty(buyQuantityBegin)){
	    	if(this.getPattern("^[0-9]*", buyQuantityBegin)){
	    		buyQuantityBegin = "";
		    	this.saveMessage(request, LocaleUtil.getLocalText("errors.buyGoods"));
	        	return returnView;
	    	}
    	}
    	if(!StringUtil.isEmpty(buyQuantityEnd)){
	    	if(this.getPattern("^[0-9]*", buyQuantityEnd)){
	    		buyQuantityEnd ="";
		    	this.saveMessage(request, LocaleUtil.getLocalText("errors.buyGoods"));
	        	return returnView;
	    	}
    	}
    	
    	//处理字符串：处理如果传递过来的字符串为“null”的情况
    	name = StringUtil.dealStr(name);
    	buyTimeBegin = StringUtil.dealStr(buyTimeBegin);
    	buyTimeEnd = StringUtil.dealStr(buyTimeEnd);
    	buyQuantityBegin = StringUtil.dealStr(buyQuantityBegin);
    	buyQuantityEnd = StringUtil.dealStr(buyQuantityEnd);    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","linkmanDemandGoodsQuery?name="+name+"&buyTimeBegin="+buyTimeBegin+
									   "&buyTimeEnd="+buyTimeEnd+"&buyQuantityBegin="+buyQuantityBegin+
									   "&buyQuantityEnd="+buyQuantityEnd+"&pageSize="+pageSize,pageSize,request);
    	
//        List linkmanDemandGoodsList = linkmanDemandmanager.getLinkmanDemandGoodsList(userCode,name,buyTimeBegin,buyTimeEnd,buyQuantityBegin,buyQuantityEnd);
        List linkmanDemandGoodsList = linkmanDemandmanager.getLinkmanDemandGoodsListPage(page,userCode,name,buyTimeBegin,buyTimeEnd,buyQuantityBegin,buyQuantityEnd);
        
        request.setAttribute("page", page);
        request.setAttribute("linkmanDemandGoodsList", linkmanDemandGoodsList);
        
        request.setAttribute("name",name);
    	request.setAttribute("buyTimeBegin",buyTimeBegin);
    	request.setAttribute("buyTimeEnd",buyTimeEnd);
    	request.setAttribute("buyQuantityBegin",buyQuantityBegin);
    	request.setAttribute("buyQuantityEnd",buyQuantityEnd);
    	return returnView;
    }
	
    /**
     * 客户管理-客户的商品-详细查询(修改)
     * @author gw 2013-0-10-22
     * @param request
     * @return string 
     */
    @RequestMapping("/linkmanDemandGoodsDetailQuery")
    public String getLinkmanDemandGoodsDetail(HttpServletRequest request){
    	String returnView = "linkmanGoodsDetailQuery";
    	String id = request.getParameter("id");
        JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
	    request.setAttribute("userCode", defUser.getUserCode());
	    LinkmanDemand linkmanDemandGoods = linkmanDemandmanager.getLinkmanDemand(id);
    	request.setAttribute("linkmanDemand", linkmanDemandGoods);
    	LinkmanCategory linkmanCategorymd = linkmanCategoryManager.getLinkmanCategoryById(linkmanDemandGoods.getLcId());
    	String linkmanCategory = "";
    	if(linkmanCategorymd!=null){
    		linkmanCategory = linkmanCategorymd.getName();
    	}
    	request.setAttribute("linkmanCategory", linkmanCategory);
    	Linkman linkmanmd = linkmanManager.getLinkmanDetail(linkmanDemandGoods.getLinkmanId());
    	String linkman = "";
    	if(linkmanmd!=null){
    		linkman = linkmanmd.getName();
    	}
    	request.setAttribute("linkman", linkman);
	    return returnView;
    }

	//--------------------------------------------------------------------------客户的商品修改部分--------------------------结束
    
    
    /**
	 * 纯数字校验
	 * @author gw 2013-10-10
	 * @param expressions
	 * @param str
	 * @return
	 */
	private boolean getPattern(String expressions,String str){
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}
    
	/**
	 * 提示语--数量格式有误的方法
	 * @author gw 2013-10-10
	 * @param request
	 * @param msg
	 */
	public void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add(msg);
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

	  /**
     * 客户管理-客户的商品-删除--修改
     * @author gw  2013-10-16
     * @param request
     * @return string 
     */
    @RequestMapping("/linkmanDemandGoodsDelete")
    public String linkmanDemandGoodsDelete(HttpServletRequest request){
    	String returnView = "linkmanGoodss";
    	String id = request.getParameter("id");
    	Long idl = Long.parseLong(id);
    	//删除的方法
    	linkmanDemandmanager.remove(idl);
    	//删除成功提示语
    	this.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
    	JsysUser defUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String userCode = defUser.getUserCode();
        List linkmanDemandGoodsList = linkmanDemandmanager.getLinkmanDemandGoodsList(userCode,"","","","","");
        request.setAttribute("linkmanDemandGoodsList", linkmanDemandGoodsList);
    	return returnView;
    }

}
