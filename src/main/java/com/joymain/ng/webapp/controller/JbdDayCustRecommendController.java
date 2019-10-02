package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.Constants;
import com.joymain.ng.model.JbdDayCustRecommend;
import com.joymain.ng.model.JbdDayCustRecommendOrder;
import com.joymain.ng.model.JbdSendRecordHist;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdDayCustRecommendManager;
import com.joymain.ng.service.JbdSendRecordHistManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.ConvertUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MessageUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;

@Controller
//@RequestMapping("/jbdDayCustRecommends/")
public class JbdDayCustRecommendController {
    //日志　
    private final Log log = LogFactory.getLog(JbdDayCustRecommendController.class);
    @Autowired
    private JbdDayCustRecommendManager jbdDayCustRecommendManager;
    
    @Autowired
    public void setJbdDayCustRecommendManager(JbdDayCustRecommendManager jbdDayCustRecommendManager) {
        this.jbdDayCustRecommendManager = jbdDayCustRecommendManager;
    }

    @Autowired
    private JsysUserManager jsysUserManager;

  /*  @RequestMapping(value="/JbdBonus/api/getJbdDayCustRecommend",method = RequestMethod.GET)
    @ResponseBody
    public List getJbdDayCustRecommend(String userId,String token,int pageNum,int pageSize,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		
		//----------------------Modify By WuCF 添加分页展示功能 
		//分页单位：固定写法
    	pageSize = StringUtil.dealPageSize(request);
    	
		GroupPage page = new GroupPage("","jbdDayCustRecommend?1=1",pageSize,request); 
		
		List jbdSendRecordHist = jbdDayCustRecommendManager.getJbdSendRecordHistByUserCodeWeek(user.getUserCode(), null,page);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < jbdSendRecordHist.size(); i++) {
			Map map=(Map) jbdSendRecordHist.get(i);
			Object wweek=map.get("w_week");
			map.put("f_week", WeekFormatUtil.getFormatWeek("w", wweek.toString()));
			if(map.get("SEND_DATE")!=null){
				map.put("SEND_DATE_str", sdf.format(map.get("SEND_DATE")));
			}else{
				map.put("SEND_DATE_str", "");
			}
			Object REMITTANCE_MONEY=map.get("REMITTANCE_MONEY");
			Object CURRENT_DEV=map.get("CURRENT_DEV");
			if(StringUtil.isDouble(REMITTANCE_MONEY+"") && StringUtil.isDouble(CURRENT_DEV+"")){
				map.put("REMITTANCE_MONEY", StringUtil.formatDouble(REMITTANCE_MONEY+"")+StringUtil.formatDouble(CURRENT_DEV+""));
			}
		}
    	return jbdSendRecordHist;
    }
    
    
    @RequestMapping(value="/JbdBonus/api/getJbdDayCustRecommendDetail",method = RequestMethod.GET)
    @ResponseBody
    public Map getJbdDayCustRecommendDetail(String userId,String token,String wweek){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
    	JbdDayCustRecommend jbdDayCustRecommend = jbdDayCustRecommendManager.getBonusQueryDetail(user.getUserCode(),wweek);
    	
    	jbdDayCustRecommend.setWweek(WeekFormatUtil.getFormatWeek("w", jbdDayCustRecommend.getWweek()));
    	jbdDayCustRecommend.setIsstore(ListUtil.getListValue(user.getDefCharacterCoding(), "isstore", jbdDayCustRecommend.getIsstore()));
    	jbdDayCustRecommend.setCardType(ListUtil.getListValue(user.getDefCharacterCoding(), "bd.cardtype", jbdDayCustRecommend.getCardType()));
    	
    	if(jbdDayCustRecommend.getMemberLevel()==null){
    		jbdDayCustRecommend.setMemberLevelStr("");
    	}else{
    		jbdDayCustRecommend.setMemberLevelStr(ListUtil.getListValue(user.getDefCharacterCoding(), "member.level", jbdDayCustRecommend.getMemberLevel()+""));
    	}
    	
    	if(jbdDayCustRecommend.getRetainLevel()==null){
    		jbdDayCustRecommend.setRetainLevelStr("");
    	}else{
        	jbdDayCustRecommend.setRetainLevelStr(ListUtil.getListValue(user.getDefCharacterCoding(), "member.level", jbdDayCustRecommend.getRetainLevel()+""));
    	}
    	if(jbdDayCustRecommend.getCioType()==null){
    		jbdDayCustRecommend.setCioTypeStr("");
    	}else{
        	jbdDayCustRecommend.setCioTypeStr(ListUtil.getListValue(user.getDefCharacterCoding(), "cio.type", jbdDayCustRecommend.getCioType()+""));
    	}
    	
    	if(jbdDayCustRecommend.getZyType()==null){
    		jbdDayCustRecommend.setZyTypeStr("");
    	}else{
        	jbdDayCustRecommend.setZyTypeStr(ListUtil.getListValue(user.getDefCharacterCoding(), "yesno", jbdDayCustRecommend.getZyType()+""));
    	}
    	
    	
    	if(jbdDayCustRecommend!=null && jbdDayCustRecommend.getWweek()>201516){
        	Map jbdLevel=jbdDayCustRecommendManager.getJBdLevel(user.getUserCode(), wweek+"");
        	
        	jbdDayCustRecommend.setLastMaxpartPv(jbdLevel.get("lastmaxpart_pv")+"");
        	jbdDayCustRecommend.setLastareaPv(jbdLevel.get("lastarea_pv")+"");
        	jbdDayCustRecommend.setMaxpartPv(jbdLevel.get("maxpart_pv")+"");
        	jbdDayCustRecommend.setAreaPv(jbdLevel.get("area_pv")+"");
        }
    	
    	
    	
    	//honor.star.zero pass.star.zero

    	jbdDayCustRecommend.setHonorStarStr(ListUtil.getListValue(user.getDefCharacterCoding(), "honor.star.zero", jbdDayCustRecommend.getHonorStar()+""));
    	jbdDayCustRecommend.setPassStarStr(ListUtil.getListValue(user.getDefCharacterCoding(), "pass.star.zero", jbdDayCustRecommend.getPassStar()+""));
    	jbdDayCustRecommend.setStatusStr(ListUtil.getListValue(user.getDefCharacterCoding(), "yesno", jbdDayCustRecommend.getStatus()+""));
    	jbdDayCustRecommend.setGradeTypeStr(ListUtil.getListValue(user.getDefCharacterCoding(), "grade.type", jbdDayCustRecommend.getGradeType()+""));
    	return (Map)ConvertUtil.ConvertObjToMap(jbdDayCustRecommend);
    }*/
    /**
     * 根据会员编号或者期别查询奖金
     * @author gong_wei
     * @param request
     * @return
     */
    //-------------- 假如查询结果页面为　JbdDayCustRecommend
 /*   @RequestMapping(value="/jbdDayCustRecommend",method = RequestMethod.GET)
    public String getJbdDayCustRecommend(HttpServletRequest request){
    	//分公司的信息－－当前登录用户的信息－－貌似不用－－－?
    	//SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//用户的权限－－判断当前用户的级别：公司用户，会员用户，奖金查询默认是会员用户吧－－?
    	
    	//获取当前登录用户的信息
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前会员的登录账号－－对应其他表的会员编号
    	String userCode = defSysUser.getUserCode();
    	
    	//获取会员编号
    	//String userCode = request.getParameter("userCode");
    	//获取期别
    	String wweek = request.getParameter("wweek");
    	//-------假如查询结果页面为　JbdDayCustRecommend
    	String returnView = "jbdDayCustRecommend";
    	//StringUtil.isEmpty
    	//判断会员编号是否为空
    	boolean ucIsEmpty = isEmpty(userCode);
        //判断期别是否为空
    	boolean wwIsEmpty = isEmpty(wweek);
    	//奖金查询--初始化查询或者是有条件查询返回的对象
    	List jbdSendRecordHist = null;
    	if((ucIsEmpty && !wwIsEmpty) || (!ucIsEmpty && wwIsEmpty)|| (!ucIsEmpty && !wwIsEmpty)){
    		//页面输入的期别或页面展示的期别是财政周　　jbd_period　期别表，而在数据库中存的是工作周，因此要进行转换
    		//----------------------Modify By WuCF 添加分页展示功能 
    		//分页单位：固定写法
        	Integer pageSize = StringUtil.dealPageSize(request);
    		
    		GroupPage page = new GroupPage("","jbdDayCustRecommend",pageSize,request);
    		
    		jbdSendRecordHist = jbdDayCustRecommendManager.getJbdDayCustRecommendByUserCodeWeekPage(userCode, wweek,page);
    		request.setAttribute("page", page);
    	    //向查询结果给查询页面
    		request.setAttribute("jbdDayCustRecommend", jbdSendRecordHist);
    		//向页面传递参数,允许表单的列的标题头排序
    		//request.setAttribute("sortAble", "false");
    		//-------查询结果的总条数－－－这个在不分页的情况下不知道是否要使用？
    		//request.setAttribute("jbdSendRecordHistTable_TotalRows", jbdSendRecordHist.size());
    	}else{
    		//向页面传递参数,不允许表单的列的标题头排序
    		request.setAttribute("sortAble", "false");
    	}
    	return returnView;
    }*/
    
    
    
    @RequestMapping(value="/getJbdDayCustRecommend",method = RequestMethod.GET)
    public String getJbdDayCustRecommend(HttpServletRequest request){
    	String returnView = "jbdDayCustRecommends";
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();
		
        String startweek = request.getParameter("startweek");
        String endweek = request.getParameter("endweek");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userCode);
        params.put("startweek", startweek);
        params.put("endweek", endweek);
        
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);
        
        GroupPage page = new GroupPage("","getJbdDayCustRecommend?startweek="+StringUtil.dealStr(startweek)+"&endweek="+StringUtil.dealStr(endweek)+"&pageSize="+pageSize,pageSize,request);
                
        List jbdDayCustRecommend = jbdDayCustRecommendManager.getJbdDayCustRecommendByUserCodeWeekPage(params,page);
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jbdDayCustRecommend", jbdDayCustRecommend);
	   return returnView;
    }
    
    
    
    /**
     * 奖金查询详细信息的Controller方法
     * @author gong_wei
     * @param request
     * @return　String
     */
    //-------------- 假如查询结果页面为　JbdDayCustRecommend
    @RequestMapping(value="/jbdDayCustRecommendDetail",method = RequestMethod.GET)
    public String jbdDayCustRecommendDetail(HttpServletRequest request){
    	//仅针对会员用户
    	//获取当前登录用户的信息
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前会员的登录账号－－对应其他表的会员编号
    	String userCode = defSysUser.getUserCode();
        //公司会员用户和其他用户的区别－－－－－－？
    	//String userCode = request.getParameter("userCode");
    	//用户--获取登录人当前信息（老系统有的）---新系统不需要？
    	//String MemberUser = SessionLogin.getLoginUser(request); 
    	//获取期别
    	String wweek = request.getParameter("wweek");
    	//集合，装载JbdDayCustRecommend的对象（详细查询结果的对象）
    	List<JbdDayCustRecommendOrder> JbdDayCustRecommendOrder = null;
    	String returnView = "jbdDayCustRecommendDetail";
    	
    	 //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);
        
        GroupPage page = new GroupPage("","jbdDayCustRecommendDetail?&pageSize="+pageSize,pageSize,request);
        
		request.setAttribute("returnView", returnView);
    	boolean isExit = (!isEmpty(userCode))&&(!isEmpty(wweek));
    	if(isExit){
    		JbdDayCustRecommendOrder = jbdDayCustRecommendManager.getJbdDayCustRecommendDetail(userCode,wweek,page);
    		request.setAttribute("page", page);
    		request.setAttribute("jbdDayCustRecommendOrder", JbdDayCustRecommendOrder);
    	}else{
    		request.setAttribute("jbdDayCustRecommendOrder", JbdDayCustRecommendOrder);
    	}
    	return returnView;
    }
    
    /**
	 *　判断字符串是否为空
	 * @author Administrator
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}
    
	
    /**
     * 查询手机端接口
     * getMobilePublicSchedules
     *
     * @param userId
     * @param token
     * @param wweek
     * @return
     */
    @RequestMapping(value="/JbdDayCustRecommend/api/getJbdMemberStarList",method = RequestMethod.GET)
    @ResponseBody
    public List getJbdMemberStarListForMobile(String userId,String token,int pageNum,int pageSize,String startweek,String endweek,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		Map<String, String> params = new HashMap<String, String>();
        params.put("userCode", userId);
		params.put("startweek", startweek);
		params.put("endweek", endweek);
		GroupPage page = new GroupPage("", "jbdDayCustRecommend?startweek=" + StringUtil.dealStr(startweek)
				+ "&endweek=" + StringUtil.dealStr(endweek) + "&pageSize=" + pageSize, pageSize, request);
		List jbdDayCustRecommend = jbdDayCustRecommendManager.getJbdDayCustRecommendByUserCodeWeekPage(params, page);
		return jbdDayCustRecommend;
	}

	/**
	 * 查询手机端接口详细 getMobilePublicSchedules
	 *
	 * @param userId
	 * @param token
	 * @param wweek
	 * @return
	 */
	@RequestMapping(value = "/JbdDayCustRecommend/api/getJbdMemberStarDetail", method = RequestMethod.GET)
	@ResponseBody
	public List getJbdMemberStarListForMobile(String userId,String token,String wweek) {
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if (null != object) {
			return (List) object;
		}
		GroupPage page = new GroupPage();
		Map<String, String> params = new HashMap<String, String>();
		params.put("userCode", userId);
		params.put("wweek", wweek);
		List jbdDayCustRecommendOrder = jbdDayCustRecommendManager.getJbdDayCustRecommendDetail2(userId, wweek,page);
		return jbdDayCustRecommendOrder;
	}
}
