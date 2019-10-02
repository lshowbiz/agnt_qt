package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.joymain.ng.model.JbdMemberLinkCalcHist;
import com.joymain.ng.model.JbdSendRecordHist;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
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
//@RequestMapping("/jbdMemberLinkCalcHists/")
public class JbdMemberLinkCalcHistController {
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    //日志　
    private final Log log = LogFactory.getLog(JbdMemberLinkCalcHistController.class);
    
    @Autowired
    public void setJbdMemberLinkCalcHistManager(JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
        this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
    }
    @Autowired
    private JbdSendRecordHistManager jbdSendRecordHistManager;

    @Autowired
    private JsysUserManager jsysUserManager;
    /*@RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdMemberLinkCalcHistManager.search(query, JbdMemberLinkCalcHist.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdMemberLinkCalcHistManager.getAll());
        }
        return model;
    }*/

    @RequestMapping(value="/JbdBonus/api/getJbdMemberLinkCalcHist",method = RequestMethod.GET)
    @ResponseBody
    public List getJbdMemberLinkCalcHist(String userId,String token,int pageNum,int pageSize,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		
		//----------------------Modify By WuCF 添加分页展示功能 
		//分页单位：固定写法
    	pageSize = StringUtil.dealPageSize(request);
    	
		GroupPage page = new GroupPage("","jbdMemberLinkCalcHist?1=1",pageSize,request); 
		
		List jbdSendRecordHist = jbdMemberLinkCalcHistManager.getJbdSendRecordHistByUserCodeWeek(user.getUserCode(), null,page);
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
    
    
    @RequestMapping(value="/JbdBonus/api/getJbdMemberLinkCalcHistDetail",method = RequestMethod.GET)
    @ResponseBody
    public Map getJbdMemberLinkCalcHistDetail(String userId,String token,String wweek){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
    	JbdMemberLinkCalcHist jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getBonusQueryDetail(user.getUserCode(),wweek);
    	
    	jbdMemberLinkCalcHist.setWweek(WeekFormatUtil.getFormatWeek("w", jbdMemberLinkCalcHist.getWweek()));
    	jbdMemberLinkCalcHist.setIsstore(ListUtil.getListValue(user.getDefCharacterCoding(), "isstore", jbdMemberLinkCalcHist.getIsstore()));
    	jbdMemberLinkCalcHist.setCardType(ListUtil.getListValue(user.getDefCharacterCoding(), "bd.cardtype", jbdMemberLinkCalcHist.getCardType()));
    	
    	if(jbdMemberLinkCalcHist.getMemberLevel()==null){
    		jbdMemberLinkCalcHist.setMemberLevelStr("");
    	}else{
    		jbdMemberLinkCalcHist.setMemberLevelStr(ListUtil.getListValue(user.getDefCharacterCoding(), "member.level", jbdMemberLinkCalcHist.getMemberLevel()+""));
    	}
    	
    	if(jbdMemberLinkCalcHist.getRetainLevel()==null){
    		jbdMemberLinkCalcHist.setRetainLevelStr("");
    	}else{
        	jbdMemberLinkCalcHist.setRetainLevelStr(ListUtil.getListValue(user.getDefCharacterCoding(), "member.level", jbdMemberLinkCalcHist.getRetainLevel()+""));
    	}
    	if(jbdMemberLinkCalcHist.getCioType()==null){
    		jbdMemberLinkCalcHist.setCioTypeStr("");
    	}else{
        	jbdMemberLinkCalcHist.setCioTypeStr(ListUtil.getListValue(user.getDefCharacterCoding(), "cio.type", jbdMemberLinkCalcHist.getCioType()+""));
    	}
    	
    	if(jbdMemberLinkCalcHist.getZyType()==null){
    		jbdMemberLinkCalcHist.setZyTypeStr("");
    	}else{
        	jbdMemberLinkCalcHist.setZyTypeStr(ListUtil.getListValue(user.getDefCharacterCoding(), "yesno", jbdMemberLinkCalcHist.getZyType()+""));
    	}
    	
    	
    	if(jbdMemberLinkCalcHist!=null && jbdMemberLinkCalcHist.getWweek()>201516){
        	Map jbdLevel=jbdMemberLinkCalcHistManager.getJBdLevel(user.getUserCode(), wweek+"");
        	
        	jbdMemberLinkCalcHist.setLastMaxpartPv(jbdLevel.get("lastmaxpart_pv")+"");
        	jbdMemberLinkCalcHist.setLastareaPv(jbdLevel.get("lastarea_pv")+"");
        	jbdMemberLinkCalcHist.setMaxpartPv(jbdLevel.get("maxpart_pv")+"");
        	jbdMemberLinkCalcHist.setAreaPv(jbdLevel.get("area_pv")+"");
        }
    	
    	
    	
    	//honor.star.zero pass.star.zero

    	jbdMemberLinkCalcHist.setHonorStarStr(ListUtil.getListValue(user.getDefCharacterCoding(), "honor.star.zero", jbdMemberLinkCalcHist.getHonorStar()+""));
    	jbdMemberLinkCalcHist.setPassStarStr(ListUtil.getListValue(user.getDefCharacterCoding(), "pass.star.zero", jbdMemberLinkCalcHist.getPassStar()+""));
    	jbdMemberLinkCalcHist.setStatusStr(ListUtil.getListValue(user.getDefCharacterCoding(), "yesno", jbdMemberLinkCalcHist.getStatus()+""));
    	jbdMemberLinkCalcHist.setGradeTypeStr(ListUtil.getListValue(user.getDefCharacterCoding(), "grade.type", jbdMemberLinkCalcHist.getGradeType()+""));
    	return (Map)ConvertUtil.ConvertObjToMap(jbdMemberLinkCalcHist);
    }
    /**
     * 根据会员编号或者期别查询奖金
     * @author gong_wei
     * @param request
     * @return
     */
    //-------------- 假如查询结果页面为　JbdMemberLinkCalcHist
    @RequestMapping(value="/jbdMemberLinkCalcHist",method = RequestMethod.GET)
    public String getJbdMemberLinkCalcHist(HttpServletRequest request){
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
    	//-------假如查询结果页面为　JbdMemberLinkCalcHist
    	String returnView = "jbdMemberLinkCalcHist";
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
    		
    		GroupPage page = new GroupPage("","jbdMemberLinkCalcHist",pageSize,request);
    		
    		jbdSendRecordHist = jbdMemberLinkCalcHistManager.getJbdSendRecordHistByUserCodeWeek(userCode, wweek,page);
    		request.setAttribute("page", page);
    	    //向查询结果给查询页面
    		request.setAttribute("jbdMemberLinkCalcHist", jbdSendRecordHist);
    		//向页面传递参数,允许表单的列的标题头排序
    		//request.setAttribute("sortAble", "false");
    		//-------查询结果的总条数－－－这个在不分页的情况下不知道是否要使用？
    		//request.setAttribute("jbdSendRecordHistTable_TotalRows", jbdSendRecordHist.size());
    	}else{
    		//向页面传递参数,不允许表单的列的标题头排序
    		request.setAttribute("sortAble", "false");
    	}
    	return returnView;
    }
    
    /**
     * 奖金查询详细信息的Controller方法
     * @author gong_wei
     * @param request
     * @return　String
     */
    //-------------- 假如查询结果页面为　JbdMemberLinkCalcHist
    @RequestMapping(value="/jbdBonusQueryDetail",method = RequestMethod.GET)
    public String getBonusQueryDetail(HttpServletRequest request){
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
    	//集合，装载JbdMemberLinkCalcHist的对象（详细查询结果的对象）
    	JbdMemberLinkCalcHist jbdMemberLinkCalcHist = null;
    	String returnView = "jbdBonusQueryDetail";
    	String newWeek=(String) Constants.sysConfigMap.get("AA").get("new.week");

		if(StringUtil.formatInt(wweek)>=StringUtil.formatInt("201805")){
			returnView = "jbdBonusQueryDetail201805";
		}else if(StringUtil.formatInt(wweek)>=StringUtil.formatInt("201607") && StringUtil.formatInt(wweek)< StringUtil.formatInt("201805")){
    		returnView = "jbdBonusQueryDetail201607";
    	}else if(StringUtil.formatInt(wweek)>=StringUtil.formatInt(newWeek)){
    		returnView = "jbdBonusQueryDetail2015";
    	}

		request.setAttribute("returnView", returnView);
    	boolean isExit = (!isEmpty(userCode))&&(!isEmpty(wweek));
    	if(isExit){
    		jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getBonusQueryDetail(userCode,wweek);
    		
    		if(jbdMemberLinkCalcHist!=null && jbdMemberLinkCalcHist.getWweek()>201516){
            	Map jbdLevel=jbdMemberLinkCalcHistManager.getJBdLevel(userCode, wweek);
            	request.setAttribute("jbdLevel", jbdLevel);
            }
    		
    		request.setAttribute("jbdMemberLinkCalcHist", jbdMemberLinkCalcHist);
    		//request.setAttribute("sortAble", "false");
    	}else{
    		request.setAttribute("jbdMemberLinkCalcHist", jbdMemberLinkCalcHist);
    		//request.setAttribute("sortAble", "false");
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
	 * 奖衔查询
	 * getJbdMemberLinkCalcHist
	 *
	 * @param request
	 * @return
	 */
    @RequestMapping(value="/jbdMemberStarQuery",method = RequestMethod.GET)
    public String getJbdMemberStarQuery(HttpServletRequest request){
        //获取当前登录用户的信息
        JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前会员的登录账号－－对应其他表的会员编号
        String userCode = defSysUser.getUserCode();
        
        String wweek = request.getParameter("wweek");
        //分页单位：固定写法
        Integer pageSize = StringUtil.dealPageSize(request);
        
        GroupPage page = new GroupPage("","jbdMemberStarQuery?wweek="+StringUtil.dealStr(wweek)+"&pageSize="+pageSize,pageSize,request);
        
        List jbdMemberLinkCalcHists = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistByUserCodeWeek(userCode, wweek,page);
        request.setAttribute("page", page);
        //向查询结果给查询页面
        request.setAttribute("jbdMemberLinkCalcHists", jbdMemberLinkCalcHists);
        
        return "jbdMemberStarQuery";
    }
    
    /**
     * 会员奖衔查询手机端接口
     * getMobilePublicSchedules
     *
     * @param userId
     * @param token
     * @param wweek
     * @return
     */
    @RequestMapping(value="/JbdBonus/api/getJbdMemberStarList",method = RequestMethod.GET)
    @ResponseBody
    public List getJbdMemberStarListForMobile(String userId,String token,String wweek){
        JsysUser user = jsysUserManager.getUserByToken(userId, token);
        Object object = jsysUserManager.getAuthErrorCode(user, "List");
        
        if(null!=object){
            return (List)object;
        }
        
        String characterCode = user.getDefCharacterCoding();
        List jbdMemberLinkCalcHists = new ArrayList<JbdMemberLinkCalcHist>();
        try{
            jbdMemberLinkCalcHists = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistByUserCodeWeek(user.getUserCode(), wweek, characterCode);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jbdMemberLinkCalcHists;
    }
    
    @RequestMapping(value="/jbdSendTypeList",method = RequestMethod.GET)
    public String getJbdSendTypeList(HttpServletRequest request) throws Exception{

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	String userCode = defSysUser.getUserCode();
    	
    	String wweek = request.getParameter("wweek");
    
    	String returnView = "jbdSendTypeList";


    	String strAction = request.getParameter("strAction");

		if ("sendBonusType".equals(strAction)) {

        	String[] bonusStr = request.getParameter("bonusStr").split(",");

        	String sendTypeSelect = request.getParameter("sendTypeSelect");

    		for (int i = 0; i < bonusStr.length; i++) {
    			if (!StringUtils.isEmpty(bonusStr[i])) {

    			    JbdSendRecordHist jbdSendRecordHist=jbdSendRecordHistManager.get(new Long(bonusStr[i]));
    			    
    			    if(jbdSendRecordHist.getWweek()>201522 || jbdSendRecordHist.getWweek()<201517){
    			    	MessageUtil.saveMessage(request, "奖金错误");
    	    			break;
    			    }
    			    
    			    if(jbdSendRecordHist.getSendDate()!=null || "2".equals(jbdSendRecordHist.getSendStatusDev())){
    	    			MessageUtil.saveMessage(request, "奖金已发放");
    	    			break;
    			    }
    			    if(jbdSendRecordHist.getWweek()<201516){
    			    	MessageUtil.saveMessage(request, "奖金操作失败");
    	    			break;
    			    }
    			    if(jbdSendRecordHist.getRemittanceMoney().compareTo(new BigDecimal(0))!=1){
    			    	MessageUtil.saveMessage(request, "奖金金额为零");
    	    			break;
    			    }
    			    if(jbdSendRecordHist.getFreezeStatus()==1L){
    			    	MessageUtil.saveMessage(request, "奖金已冻结");
    	    			break;
    			    }
    				jbdSendRecordHistManager.saveJbdSendType(bonusStr[i], sendTypeSelect, defSysUser);
    				
    			}
        	
    		}
        	
		}
    	
    	
        //判断期别是否为空
    	boolean wwIsEmpty = isEmpty(wweek);
    	//奖金查询--初始化查询或者是有条件查询返回的对象
    	List jbdSendRecordHist = null;
//    	if(wwIsEmpty){

    		
        	Integer pageSize = StringUtil.dealPageSize(request);
    		
    		GroupPage page = new GroupPage("","jbdSendTypeList",pageSize,request);
    		
    		jbdSendRecordHist = jbdMemberLinkCalcHistManager.getJbdSendTypeList(userCode, wweek,page);
    		request.setAttribute("page", page);
    		request.setAttribute("jbdMemberLinkCalcHist", jbdSendRecordHist);
//    	}else{
//    		request.setAttribute("sortAble", "false");
//    	}
    	return returnView;
    }
}
