package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpoInviteList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JpoInviteListManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.webapp.util.RequestUtil;

import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/mobileJpoInviteList/api")
public class ApiJpoInviteListController {
	private org.apache.commons.logging.Log log = LogFactory.getLog(ApiJpoInviteListController.class);
	private JpoInviteListManager jpoInviteListManager;
	@Autowired
	private JmiMemberManager jmiMemberManager;

    @Autowired
    public void setJpoInviteListManager(JpoInviteListManager jpoInviteListManager) {
        this.jpoInviteListManager = jpoInviteListManager;
    }
	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}
	@Autowired
	private JsysUserManager jsysUserManager;

	@RequestMapping(value="jpoInviteList")
	@ResponseBody
	public Map getJpoInviteListApp(HttpServletRequest request,String token1 ,String userId1) throws ParseException {
		//会员中心：我的邀请码手机端接口
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中开始运行");
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		Map<String, Object> map = new HashMap<String, Object>();
		String message="";
		String returnCode="";
		//登陆验证
		if(null!=object){
			Map<String,Object> messageCode=(Map)object;
			String code=(String) messageCode.get("autherror");
			if(code.equals("0")){
				message="禁用用户";
				returnCode="0";
			}else if(code.equals("1")){
				message="用户名或密码不正确！";
				returnCode="1";
			}else if(code.equals("2")){
				message="系统禁止用户登录";
				returnCode="2";
			}
			map.put("message", message);
			map.put("returnCode", returnCode);
			return map;
		}
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---登陆验证通过");

		//Integer pageSize = StringUtil.dealPageSize(request);
		int pageSize = 5;
		String ps = request.getParameter("pageSize");
		if(!StringUtil.isEmpty(ps)){
			pageSize=Integer.parseInt(ps);
		}

		String inviteCode = request.getParameter("inviteCode");//邀请码
		String memberOrderNo = request.getParameter("memberOrderNo");//订单编号
		String status = request.getParameter("status");//状态
		String inviteType = request.getParameter("inviteType");//类型：1正常下单 2.系统刷赠送
		Map<String, String> params = new HashMap<String, String>();
		params.put("userCode", userId);
		params.put("inviteCode", inviteCode);
		params.put("memberOrderNo", memberOrderNo);
		params.put("status", status);
		params.put("inviteType", inviteType);
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询开始");
		GroupPage page = new GroupPage("","mobileJpoInviteList/api/jpoInviteList?userId="+userId+"&token="+token+
				"&inviteCode="+inviteCode+"&memberOrderNo="+memberOrderNo+"&status="+status+"&inviteType="+inviteType+"&pageSize="+pageSize,pageSize,request);
		List jpoInviteList = jpoInviteListManager.getJpoInviteList(params, page);
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询结束");
		message="查询成功";
		returnCode="3";
		map.put("message", message);
		map.put("pageCount",String.valueOf(page.getPagecount()));
		map.put("returnCode", returnCode);
		map.put("pages", String.valueOf(page.getPages()));
		map.put("pageSize", String.valueOf(page.getPagesize()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List tempList=new ArrayList();
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询结果数据处理开始");
		for (int i=0;jpoInviteList.size()>i;i++){
			Map maps=(Map) jpoInviteList.get(i);
			Map tempMap = new HashMap();
			BigDecimal id=(BigDecimal)maps.get("ID");
			tempMap.put("ID",id.toString());
			tempMap.put("USER_CODE", maps.get("USER_CODE"));
			tempMap.put("INVITE_CODE", maps.get("INVITE_CODE"));
			tempMap.put("MEMBER_ORDER_NO", maps.get("MEMBER_ORDER_NO"));
			Timestamp createTime=(Timestamp) (maps.get("CREATE_TIME"));
			tempMap.put("CREATE_TIME",createTime!=null?simpleDateFormat.format(createTime):null);
			tempMap.put("STATUS",maps.get("STATUS"));
			tempMap.put("USE_USER_CODE", maps.get("USE_USER_CODE"));
			Timestamp useTime=(Timestamp) (maps.get("USE_TIME"));
			tempMap.put("USE_TIME",useTime!=null?simpleDateFormat.format(useTime):null);
			tempMap.put("INVITE_TYPE", String.valueOf(maps.get("INVITE_TYPE")));
			tempMap.put("VERSION", String.valueOf(maps.get("VERSION")));
			tempList.add(tempMap);
		}
		map.put("pageDataList", tempList);
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询结果数据处理结束");
		map.put("pageNum", String.valueOf(page.getPagenum()));
		return map;
	}
	@RequestMapping(value="jmiYkSearchList")
	@ResponseBody
	public Map getJmiYkSearchListApp(HttpServletRequest request,String token1 ,String userId1) throws ParseException {
		//会员中心：我的邀请码手机端接口
		log.info("在类ApiJpoInviteListController的getJmiYkSearchListApp方法中开始运行");
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		Map<String, Object> map = new HashMap<String, Object>();
		String message="";
		String returnCode="";
		//登陆验证
		if(null!=object){
			Map<String,Object> messageCode=(Map)object;
			String code=(String) messageCode.get("autherror");
			if(code.equals("0")){
				message="禁用用户";
				returnCode="0";
			}else if(code.equals("1")){
				message="用户名或密码不正确！";
				returnCode="1";
			}else if(code.equals("2")){
				message="系统禁止用户登录";
				returnCode="2";
			}
			map.put("message", message);
			map.put("returnCode", returnCode);
			return map;
		}
		log.info("在类ApiJpoInviteListController的getJmiYkSearchListApp方法中运行---登陆验证通过");

		//Integer pageSize = StringUtil.dealPageSize(request);
		int pageSize = 5;
		String ps = request.getParameter("pageSize");
		if(!StringUtil.isEmpty(ps)){
			pageSize=Integer.parseInt(ps);
		}
		List jmiYkSearchList =new ArrayList<Map>();
		String ykUserCode=request.getParameter("ykUserCode");
		String mobiletele=request.getParameter("mobiletele");
		Map<String, String> params = new HashMap<String, String>();
		params.put("userCode", userId);
		params.put("ykUserCode", ykUserCode);
		params.put("mobiletele", mobiletele);
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询开始");
		GroupPage page = new GroupPage("","mobileJpoInviteList/api/jmiYkSearchList?userId="+userId+"&token="+token+
				"&ykUserCode="+ykUserCode+"&mobiletele="+mobiletele+"&pageSize="+pageSize,pageSize,request);
		List tempList = jmiMemberManager.getjmiYkSearchList(params,page);

		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询结束");
		message="查询成功";
		returnCode="3";
		map.put("message", message);
		map.put("pageCount",String.valueOf(page.getPagecount()));
		map.put("returnCode", returnCode);
		map.put("pages", String.valueOf(page.getPages()));
		map.put("pageSize", String.valueOf(page.getPagesize()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List resultList=new ArrayList();
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询结果数据处理开始");
		for(int i=0;tempList.size()>i;i++){
			Map tempMap=(Map) tempList.get(i);
			Map obj=new HashMap<String, String>();
			obj.put("userCode", tempMap.get("User_Code"));
			obj.put("mobiletele", tempMap.get("MOBILETELE"));
			obj.put("inviteCode", tempMap.get("invite_code"));
			String paperNumber=   tempMap.get("Papernumber")==null?"": (String) tempMap.get("Papernumber");
			String paper="";
			if(StringUtil.isObjectNotBlank(paperNumber)&&paperNumber.length()>=8) {
				//身份证号码屏蔽
				paper = paperNumber.replaceFirst(paperNumber.substring(1, 4), "***");
				paper = paper.replaceFirst(paper.substring(paper.length() - 4, paper.length()), "****");
			}
			obj.put("paperNumber",paper );
			resultList.add(obj);
		}
		map.put("pageDataList", resultList);
		log.info("在类ApiJpoInviteListController的getJpoInviteListApp方法中运行---分页查询结果数据处理结束");
		map.put("pageNum", String.valueOf(page.getPagenum()));
		return map;
	}
	/**
	 * 手机端邀请码验证
	 * @param request
	 * @param userId
	 * @param token
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="checkInviteToApp")
	@ResponseBody
	public Map getInviteToApp(HttpServletRequest request, String userId, String token) {
		Map resultMap = new HashMap();
		try {
			// 登录验证
			JsysUser user = jsysUserManager.getUserByToken(userId, token);
			Object object = jsysUserManager.getAuthErrorCode(user, "Map");
			if (null != object) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMessage", "用户登录异常");
				return resultMap;
			}
			String inviteCode = request.getParameter("inviteCode");
			String memberOrderNo = request.getParameter("orderId");

			// 验证邀请码是否存在
			JpoInviteList jpoInviteList = jpoInviteListManager.getJpoInviteListForInviteCode(inviteCode);
			if (jpoInviteList == null || !"0".equals(jpoInviteList.getStatus())) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMessage", "邀请不存在或者已使用");
				return resultMap;
			}
			// 用户的推荐人是否是邀请码的所有人
			String inviteUserCode = jpoInviteList.getUserCode();
			JmiMember jmiMember = jmiMemberManager.get(user.getUserCode());
			if (!inviteUserCode.equals(jmiMember.getRecommendNo())) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMessage", "无权使用该邀请码");
				return resultMap;
			}
			String resultStr = jpoInviteListManager.useJpoInviteListToApp(inviteCode, memberOrderNo, user);
			if (StringUtils.isNotEmpty(resultStr)) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMessage", resultStr);
				return resultMap;
			} else {
				resultMap.put("resultCode", "1");
				resultMap.put("resultMessage", "成功");
				return resultMap;
			}
		} catch (Exception e) {
			resultMap.put("resultCode", "0");
			resultMap.put("resultMessage", "系统异常");
			resultMap.put("resultException", e);
			return resultMap;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value="checkInviteUseUserCodeToApp")
	@ResponseBody
	public Map checkInciteUseUserCode(HttpServletRequest request, String userId, String token){
    	Map resultMap = new HashMap();
    	try {
    		// 登录验证
    		JsysUser user = jsysUserManager.getUserByToken(userId, token);
    		Object object = jsysUserManager.getAuthErrorCode(user, "Map");
    		if (null != object) {
    			resultMap.put("resultCode", "-1");
    			resultMap.put("resultMessage", "用户登录异常");
    			return resultMap;
    		}
        	JpoInviteList jpoInvite = jpoInviteListManager.getJpoInviteCodeByUserCode(user.getUserCode());
        	if (jpoInvite!=null) {
        		resultMap.put("resultCode", "-1");
    			resultMap.put("resultMessage", "用户已经使用过邀请码");
    			return resultMap;
			}else{
				resultMap.put("resultCode", "1");
    			resultMap.put("resultMessage", "用户没有使用过邀请码");
    			return resultMap;
			}
		} catch (Exception e) {
			resultMap.put("resultCode", "0");
			resultMap.put("resultMessage", "系统异常");
			resultMap.put("resultException", e);
			return resultMap;
		}
    }
}
