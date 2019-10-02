


package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.LinkmanCategoryManager;
import com.joymain.ng.service.LinkmanManager;
import com.joymain.ng.service.RelationshipRecordManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.LinkmanCategory;
import com.joymain.ng.model.RelationshipRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinkmanController {
    private LinkmanManager linkmanManager;
    
    private RelationshipRecordManager relationshipRecordManager ;

	public static final String MESSAGES_KEY = "successMessages";
  
    @Autowired
	private JalStateProvinceManager jalStateProvinceManager;
    
    @Autowired
    private LinkmanCategoryManager linkmanCategoryManager;
    
    @Autowired
    private JalCityManager jalCityManager;
    
    @Autowired
    private JalDistrictManager jalDistrictManager;
    
    @Autowired
	public void setRelationshipRecordManager(RelationshipRecordManager relationshipRecordManager) {
		this.relationshipRecordManager = relationshipRecordManager;
	}
    

    @Autowired
    public void setLinkmanManager(LinkmanManager linkmanManager) {
        this.linkmanManager = linkmanManager;
    }
    
    //日志
    private final Log log = LogFactory.getLog(LinkmanController.class);
    
    /**
     * 会员系统－客户管理－客户维护－查询
     * @param gw 2013-07-16
     * @param request
     * @return string
     */
    @RequestMapping(value="/jadLinkmanQuery",method=RequestMethod.GET)
    public String getLinkmanController(HttpServletRequest request){
    	String returnView = "jadLinkmanQuery";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		//查出该会员已经定义的客户分类
		List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
		request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	//获取客户的信息－姓名,手机号，性别
    	String name = request.getParameter("name");
    	String sex = request.getParameter("sex");
    	String mobilePhone = request.getParameter("mobilephone");
    	String lcId = request.getParameter("lcId");
    	//重点客户
    	String customerFocus = request.getParameter("customerFocus");
    	//客户的信息查询要用到分页--相关的参数暂时写死，后面再改？
    	//Pager pager = new Pager(0, 0);
    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//处理字符串
    	name = StringUtil.dealStr(name);
    	sex = StringUtil.dealStr(sex);
    	mobilePhone = StringUtil.dealStr(mobilePhone);
    	lcId = StringUtil.dealStr(lcId);
    	customerFocus = StringUtil.dealStr(customerFocus);   
    	
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","jadLinkmanQuery?name="+name+"&sex="+sex+"&mobilephone="+mobilePhone+
									   "&lcId="+lcId+"&customerFocus="+customerFocus+"&pageSize="+pageSize,pageSize,request);
		
		//客户维护中查询联系人的页面
    	if("linkmanMaintainSelect".equals(request.getParameter("strAction"))){
    		page = new GroupPage("","jadLinkmanQuery?strAction=linkmanMaintainSelect&name="+name+"&sex="+sex+"&mobilephone="+mobilePhone+
					   "&lcId="+lcId+"&customerFocus="+customerFocus+"&pageSize="+pageSize,pageSize,request);
    	}
		
		//
    	List linkmanList = linkmanManager.getLinkmanListBybaseInfoPage(page,name,sex,mobilePhone,userCode,lcId,customerFocus);
    	
    	request.setAttribute("page", page);
    	
    	//查询客户的信息-因为涉及到的客户人数不是很多，因此暂时不考虑分页
//    	List linkmanList = linkmanManager.getLinkmanBybaseInfo(name,sex,mobilePhone,userCode,lcId,customerFocus);
    	
    	
    	//像页面传递值－这里应该是能分页的集合,因为客户人数不是很多，暂时不给分页
    	request.setAttribute("linkmanList", linkmanList);
    	//使性别的下拉框有值（就是他按什么选的，就查询后就显示该值）
    	if(!StringUtil.isEmpty(sex)){
    		request.setAttribute("sexValue", sex);
    	}else{
    		request.setAttribute("sexValue", "");
    	}
    	//使分类名称的下拉框有值（就是他按什么选的，就查询后就显示该值）
    	if(!StringUtil.isEmpty(lcId)){
    		request.setAttribute("lcIdValue", lcId);
    	}else{
    		request.setAttribute("lcIdValue", "");
    	}
    	//使重点客户的下拉框有值（就是他按什么选的，就查询后就显示该值）
    	if(!StringUtil.isEmpty(customerFocus)){
    		request.setAttribute("customerFocusValue", customerFocus);
    	}else{
    		request.setAttribute("customerFocusValue", "");
    	}
    	String maintainId = request.getParameter("maintainId");
    	String strAction = request.getParameter("strAction");
    	String linkmanMaintainFunction = request.getParameter("linkmanMaintainFunction");
  	    request.setAttribute("linkmanMaintainFunction",linkmanMaintainFunction);
  	    request.setAttribute("maintainId",maintainId);
    	request.setAttribute("nameValue", name);
    	request.setAttribute("mobilePhoneValue", mobilePhone);
    	
    	//客户维护中查询联系人的页面
    	if("linkmanMaintainSelect".equals(request.getParameter("strAction"))){
    	   return "linkmanMaintainSelect";
    	}
    	//事件管理中查询联系人的页面
    	else if("linkmanEventSelect".equals(strAction)){
    	   String eventId = request.getParameter("eventId");
    	   request.setAttribute("eventId", eventId);
    	   return "linkmanEventSelect";
    	}
    	else{
    	   return returnView;
    	}
    }
    
    /**
     * 会员系统－客户管理－客户维护－详细查询
     * @author gw 2013-07-16
     * @param request
     * @return　String
     */
    @RequestMapping(value="/jadLinkmanDetailQuery", method=RequestMethod.GET)
    public String getLinkmanDetail(HttpServletRequest request){
    	String returnView = "jadLinkmanDetailQuery";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String companyCode = defSysUser.getCompanyCode();
		 //读取省份－－－－开始，关于城市和地区的在ｊｓｐ页面做了处理
		List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
		request.setAttribute("alStateProvinces", alStateProvinces);
		//查出该会员已经定义的客户分类
	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	//获取客户信息表的ＩＤ
    	String id = request.getParameter("id");
    	Linkman linkman = linkmanManager.getLinkmanDetail(id);
    	request.setAttribute("linkman", linkman);
    	
    	//如果省有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getProvince())){
    		request.setAttribute("provinceRemark", "true");
    	}
    	//如果市有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getCity())){
    	   // Long cityId = Long.valueOf(linkman.getCity());
    	   // List cityList = jalCityManager.getAlCityByProvinceId(cityId);
    	   request.setAttribute("cityRemark","true");
    	}
    	//如果地区有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getDistrict())){
    		//Long districtId = Long.valueOf(linkman.getDistrict());
    	    //List districtList = jalDistrictManager.getAlDistrictByCityId(districtId);
    	   request.setAttribute("districtRemark","true");
    	}
    	
    	if(linkman!=null&&!StringUtil.isEmpty(linkman.getLcId())){
	    	LinkmanCategory linkmanCategorymd = linkmanCategoryManager.getLinkmanCategoryById(linkman.getLcId());
	    	String linkmanCategory = "";
	    	if(linkmanCategorymd!=null){
	    		linkmanCategory = linkmanCategorymd.getName();
	    	}
	    	request.setAttribute("linkmanCategory", linkmanCategory);
    	}
    	
    	if(linkman!=null&&!StringUtil.isEmpty(linkman.getProvince())){
	    	JalStateProvince jalStateProvince = jalStateProvinceManager.get(linkman.getProvince());
	    	String province = "";
	    	if(jalStateProvince!=null){
	    		province = jalStateProvince.getStateProvinceName();
	    	}
	    	request.setAttribute("province", province);
    	}
    	
    	if(linkman!=null&&!StringUtil.isEmpty(linkman.getCity())){
	    	JalCity jalCity = jalCityManager.get(linkman.getCity());
	    	String city = "";
	    	if(jalCity!=null){
	    		city = jalCity.getCityName();
	    	}
	    	request.setAttribute("city", city);
    	}
    	
    	if(linkman!=null&&!StringUtil.isEmpty(linkman.getDistrict())){
	    	JalDistrict jalDistrict = jalDistrictManager.get(linkman.getDistrict());
	    	String district = "";
	    	if(jalDistrict!=null){
	    		district = jalDistrict.getDistrictName();
	    	}
	    	request.setAttribute("district", district);
    	}
    	return returnView;
    }
    
    /**
     * 会员系统－客户管理－客户维护－详细查询(客户信息详细修改初始化查询)
     * @author gw 2013-07-16
     * @param request
     * @return　String
     */
    @RequestMapping(value="/jadLinkmanDetailQueryUpdate", method=RequestMethod.GET)
    public String getLinkmanDetailUpdate(HttpServletRequest request){
    	String returnView = "jadLinkmanDetailUpdate";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//读取省份－－－－开始，关于城市和地区的在ｊｓｐ页面做了处理
		List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
		request.setAttribute("alStateProvinces", alStateProvinces);
		//查出该会员已经定义的客户分类
	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	//获取客户信息表的ＩＤ
    	String id = request.getParameter("id");
    	Linkman linkman = linkmanManager.getLinkmanDetail(id);
    	request.setAttribute("linkman", linkman);
    	return returnView;
    }
    
    
    /**
     * 会员系统－客户管理－客户信息删除（在客户信息维护的菜单下）
     * @author gw 2013-07-17  update 2014-03-03
     * @param request
     * @return
     */
    @RequestMapping(value="/jadLinkmanDelete")
    public String deleteLinkmanInfor(HttpServletRequest request){
    	String returnView = "jadLinkmanQuery";
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userCode = defSysUser.getUserCode();
		//删除方法之前，获取linkman表的ＩＤ
    	String id = request.getParameter("id");
    	Long idL = Long.parseLong(id);
    	//执行删除操作
    	linkmanManager.remove(idL);
    	//删除对应的客户,那么该客户对应的拜访记录(联系记录)都同步删除
    	relationshipRecordManager.deleteRelationshipRecordByLinkmanId(id);
    	//删除操作成功后，给于友好提示－－－－？有待验证
    	this.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
    	//删除成功后，对客户信息重新进行查询一下
    	return this.getLinkmanController(request);
    }
    
    /**
     * 设为重点客户  或者  设为普通客户
     * @author gw  2013-09-24  update 2014-03-03
     * @param  request
     * @return string
     */
    @RequestMapping(value="/isOrNotCustomerFocus")
    public String whetherCustomerFocus(HttpServletRequest request){
    	
    	//删除方法之前，获取linkman表的ＩＤ
    	String id = request.getParameter("id");
    	String customerFocus = request.getParameter("customerFocusss");
    	Linkman linkman = linkmanManager.getLinkmanDetail(id);
    	linkman.setCustomerFocus(customerFocus);
    	linkmanManager.updateOrAddLinkmanDetail(linkman);
    	
    	//修改成功后，对客户信息重新进行查询一下
    	return this.getGzLinkmanController(request);
    }
    
	/**
	 * 提示语
	 * @author gw 2013-10-22
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
     * 会员系统－客户管理－客户跟踪
     * @param 
     * @param request
     * @return string
     */
    @RequestMapping(value="/jadLinkmanQueryGz",method=RequestMethod.GET)
    public String getGzLinkmanController(HttpServletRequest request){
    	String returnView = "jadLinkmanQueryGz";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		//查出该会员已经定义的客户分类
		List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
		request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	//获取客户的信息－姓名,手机号，性别
    	String name = request.getParameter("name");
    	String sex = request.getParameter("sex");
    	String mobilePhone = request.getParameter("mobilephone");
    	String lcId = request.getParameter("lcId");
    	//重点客户
    	String customerFocus = request.getParameter("customerFocus");
    	//客户的信息查询要用到分页--相关的参数暂时写死，后面再改？
    	//Pager pager = new Pager(0, 0);
    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//处理字符串
    	name = StringUtil.dealStr(name);
    	sex = StringUtil.dealStr(sex);
    	mobilePhone = StringUtil.dealStr(mobilePhone);
    	lcId = StringUtil.dealStr(lcId);
    	customerFocus = StringUtil.dealStr(customerFocus);   
    	
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","jadLinkmanQuery?name="+name+"&sex="+sex+"&mobilephone="+mobilePhone+
									   "&lcId="+lcId+"&customerFocus="+customerFocus+"&pageSize="+pageSize,pageSize,request);
		
		//石：改造客户跟踪查询
    	List linkmanList = linkmanManager.getLinkmanListBybaseInfoPage(page,name,sex,mobilePhone,userCode,lcId,customerFocus);
    	
    	request.setAttribute("page", page);
    	
    	//查询客户的信息-因为涉及到的客户人数不是很多，因此暂时不考虑分页
//    	List linkmanList = linkmanManager.getLinkmanBybaseInfo(name,sex,mobilePhone,userCode,lcId,customerFocus);
    	
    	
    	//像页面传递值－这里应该是能分页的集合,因为客户人数不是很多，暂时不给分页
    	request.setAttribute("linkmanList", linkmanList);
    	//使性别的下拉框有值（就是他按什么选的，就查询后就显示该值）
    	if(!StringUtil.isEmpty(sex)){
    		request.setAttribute("sexValue", sex);
    	}else{
    		request.setAttribute("sexValue", "");
    	}
    	//使分类名称的下拉框有值（就是他按什么选的，就查询后就显示该值）
    	if(!StringUtil.isEmpty(lcId)){
    		request.setAttribute("lcIdValue", lcId);
    	}else{
    		request.setAttribute("lcIdValue", "");
    	}
    	//使重点客户的下拉框有值（就是他按什么选的，就查询后就显示该值）
    	if(!StringUtil.isEmpty(customerFocus)){
    		request.setAttribute("customerFocusValue", customerFocus);
    	}else{
    		request.setAttribute("customerFocusValue", "");
    	}
    	String maintainId = request.getParameter("maintainId");
    	String strAction = request.getParameter("strAction");
    	String linkmanMaintainFunction = request.getParameter("linkmanMaintainFunction");
  	    request.setAttribute("linkmanMaintainFunction",linkmanMaintainFunction);
  	    request.setAttribute("maintainId",maintainId);
    	request.setAttribute("nameValue", name);
    	request.setAttribute("mobilePhoneValue", mobilePhone);
    	
    	//客户维护中查询联系人的页面
    	if("linkmanMaintainSelect".equals(request.getParameter("strAction"))){
    	   return "linkmanMaintainSelect";
    	}
    	//事件管理中查询联系人的页面
    	else if("linkmanEventSelect".equals(strAction)){
    	   String eventId = request.getParameter("eventId");
    	   request.setAttribute("eventId", eventId);
    	   return "linkmanEventSelect";
    	}
    	else{
    	   return returnView;
    	}
    }
    
    /**
     * 会员系统－客户管理－客户跟踪－跟踪详细
     * @author gw 2013-07-16
     * @param request
     * @return　String
     */
    @RequestMapping(value="/jadLinkmanDetailQueryGz", method=RequestMethod.GET)
    public String getLinkmanDetailGz(HttpServletRequest request){
    	String returnView = "jadLinkmanDetailQueryGz";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String companyCode = defSysUser.getCompanyCode();
		 //读取省份－－－－开始，关于城市和地区的在ｊｓｐ页面做了处理
		List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
		request.setAttribute("alStateProvinces", alStateProvinces);
		//查出该会员已经定义的客户分类
	    List linkmanCategoryList = linkmanManager.getLinkmanCategoryList(defSysUser.getUserCode());
	    request.setAttribute("linkmanCategoryList", linkmanCategoryList);
    	//获取客户信息表的ＩＤ
    	String id = request.getParameter("id");
    	Linkman linkman = linkmanManager.getLinkmanDetail(id);
    	request.setAttribute("linkman", linkman);
    	
    	//如果省有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getProvince())){
    		request.setAttribute("provinceRemark", "true");
    	}
    	//如果市有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getCity())){
    	   // Long cityId = Long.valueOf(linkman.getCity());
    	   // List cityList = jalCityManager.getAlCityByProvinceId(cityId);
    	   request.setAttribute("cityRemark","true");
    	}
    	//如果地区有值，那么对应的页面的下拉框就不可改变
    	if(!StringUtil.isEmpty(linkman.getDistrict())){
    		//Long districtId = Long.valueOf(linkman.getDistrict());
    	    //List districtList = jalDistrictManager.getAlDistrictByCityId(districtId);
    	   request.setAttribute("districtRemark","true");
    	}
    	
    	//获取联系记录
    	List relationshipRecordList=relationshipRecordManager.getRelationshipRecordsByLinkManId(id);
    	request.setAttribute("relationshipRecordList", relationshipRecordList);
    	
    	RelationshipRecord relationshipRecord=new RelationshipRecord();
    	request.setAttribute("relationshipRecord", relationshipRecord);
    	
    	LinkmanCategory linkmanCategorymd = linkmanCategoryManager.getLinkmanCategoryById(linkman.getLcId());
    	String linkmanCategory = "";
    	if(linkmanCategorymd!=null){
    		linkmanCategory = linkmanCategorymd.getName();
    	}
    	request.setAttribute("linkmanCategory", linkmanCategory);
    	
    	return returnView;
    }
}
