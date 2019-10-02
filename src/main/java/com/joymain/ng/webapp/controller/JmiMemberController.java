package com.joymain.ng.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.joymain.ng.model.*;
import com.joymain.ng.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiLinkRefDao;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.ConvertUtil;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MessageUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

@Controller
public class JmiMemberController {
	private final Log log = LogFactory.getLog(JmiMemberController.class);
    private JmiMemberManager jmiMemberManager;
    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }

    @Autowired
    private JmiAddrBookManager jmiAddrBookManager;
    @Autowired
    public JbdPeriodManager jbdPeriodManager;
    @Autowired
    private JmiRecommendRefManager jmiRecommendRefManager;
    @Autowired
    private JsysUserManager jsysUserManager;
	@Autowired
	private JmiLinkRefManager jmiLinkRefManager;
    @Autowired
    private JalStateProvinceManager jalStateProvinceManager;
    @Autowired
    private JalCityManager jalCityManager;
    @Autowired
    private JalDistrictManager  jalDistrictManager;

    @Autowired
    private JdbcTemplate jdbcTemplate3;
    @Autowired
    public JmiLinkRefDao jmiLinkRefDao;

	@Autowired
	private JpoInviteListManager jpoInviteListManager;

	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;

	@Autowired
	public JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value="/JmiMember/api/getJmiMember",method = RequestMethod.GET)
    @ResponseBody
    public Map getJmiMember(String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
    	JmiMember jmiMember=user.getJmiMember();
    	jmiMember.setCardType(ListUtil.getListValue(user.getDefCharacterCoding(), "member.level", jmiMember.getMemberLevel()+""));
    	jmiMember.setPapertype(ListUtil.getListValue(user.getDefCharacterCoding(), "papertype", jmiMember.getPapertype()));
		if(!StringUtil.isEmpty(jmiMember.getProvince())){
    		JalStateProvince alStateProvince = jalStateProvinceManager.get(jmiMember.getProvince());
    		if (alStateProvince != null) {
    			jmiMember.setProvince(alStateProvince.getStateProvinceName());
    		}
    	}

    	if(!StringUtil.isEmpty(jmiMember.getCity())){
    		JalCity alCity = jalCityManager.get(jmiMember.getCity());
    		if (alCity != null) {
    			jmiMember.setCity(alCity.getCityName());
    		}
    	}
		if(!StringUtil.isEmpty(jmiMember.getDistrict())){
			JalDistrict alDistrict = jalDistrictManager.get(jmiMember.getDistrict());
			if (alDistrict != null) {
				jmiMember.setDistrict(alDistrict.getDistrictName());
			}
		}

		//原来接口返回，现在添加一个是否使用邀请码状态 isInvite 是否使用邀请码｛1：使用，0：未使用｝
		Map convertObjToMap = ConvertUtil.ConvertObjToMap(jmiMember);
		JpoInviteList jpoInviteCodeByUserCode = jpoInviteListManager.getJpoInviteCodeByUserCode(user.getUserCode());
		
		String isCloudshop = jmiMember.getIsCloudshop();//是否有云店资格
		Date standardMkTime = jmiMember.getStandardMkTime();//脉客达成时间
		String memberUserTypeMk = jmiMember.getMemberUserType();//会员类型
		Integer star = jmiMember.getMemberStar();

		if(star == null || star == 0){
			convertObjToMap.put("memberStars", "");
		}else if (star == 41) {
			convertObjToMap.put("memberStars", "顾问");
		}else if (star == 42) {
			convertObjToMap.put("memberStars", "达人");
		}else if (star == 43) {
			convertObjToMap.put("memberStars", "总代");
		}
		if (null != isCloudshop && isCloudshop.equals("1") && memberUserTypeMk.equals("1") ) {//脉客判断
			convertObjToMap.put("isInvite", "1");//是否是脉客｛1：是，0：不是｝
		}else{
			if (null==jpoInviteCodeByUserCode) {

				//添加条件，有41的360年费单
				JpoMemberOrder jpoMemberOrder=new JpoMemberOrder();
				jpoMemberOrder.setSysUser(user);
				jpoMemberOrder.setOrderType("41");
				jpoMemberOrder.setStatus("2");

				List jpoMemberOrdersByMiMember = jpoMemberOrderManager.getJpoMemberOrdersByMiMember(jpoMemberOrder);
				if (CollectionUtils.isEmpty(jpoMemberOrdersByMiMember)) {
					convertObjToMap.put("isInvite", "1");
				}else{
					convertObjToMap.put("isInvite", "0");
				}
			}else{
				convertObjToMap.put("isInvite", "1");
			}
		}
		convertObjToMap.put("cardType_code", jmiMember.getMemberLevel()+"");
    	return convertObjToMap;
    }
    
    @RequestMapping(value="/jmiRecommendRefs",method = RequestMethod.GET)
    public String getJmiRecommendRefs(HttpServletRequest request){

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Integer defLayerID = null;//TODO 读取参数
//		boolean isUnlimitUserFlag=jmiMemberManager.getCheckIsUnlimitUser(defSysUser);
		
		String layerIdStr = request.getParameter("layerId");
		
		List recommendList=null;
		
		
//		if(!StringUtil.isEmpty(layerIdStr)){
//			recommendList=jmiMemberManager.getNet(defSysUser.getUserCode(), "recommend_no", getlayerId(defLayerID, request, layerIdStr,isUnlimitUserFlag).toString());
//		}
		request.setAttribute("recommendList", recommendList);

    	return "jmiRecommendRefs";
    }
    private boolean getIsUnderLine(JsysUser defSysUser,JmiMember jmiMember,String defLayerID,String netType){
    	
    	
    	Integer perLayer=0;
    	if("link_no".equals(netType)){
    		perLayer=2;
		}else if("recommend_no".equals(netType)){
			perLayer=3;
		}
    	if(jmiMember==null){
    		return false;
    	}else{
    		String topTreeIndex="";
    		String underTreeIndex="";
    		
    		if("link_no".equals(netType)){
    			topTreeIndex=defSysUser.getJmiMember().getJmiLinkRef().getTreeIndex();
        		underTreeIndex=jmiMember.getJmiLinkRef().getTreeIndex();
    		}else if("recommend_no".equals(netType)){
    			topTreeIndex=defSysUser.getJmiMember().getJmiRecommendRef().getTreeIndex();
        		underTreeIndex=jmiMember.getJmiRecommendRef().getTreeIndex();
    		}
    		if(StringUtil.getCheckIsDownline(topTreeIndex,underTreeIndex)){
    			
    			String overTreeIndex=StringUtils.substring(underTreeIndex, topTreeIndex.length());
    			
    			Integer layer=(underTreeIndex.length()-topTreeIndex.length())/perLayer;
    			if(layer>=StringUtil.formatInt(defLayerID)){
    				return false;
    			}else{
    				return true;
    			}
    		}else{
    			return false;
    		}
    	}
    }
    @RequestMapping(value="/JmiMember/api/getJmiLinkRefs",method = RequestMethod.GET)
    @ResponseBody
    public List getJmiLinkRefs(String userId,String token,String userCode,String curLayer){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
    	String defLayerID=setList(null, user, "select.link.ordinaryuser.mobile", "select.link.ref.mobile");


    	JsysUser jsysUser=jsysUserManager.get(userCode);
    	
    	List jmiLinkRefs = new ArrayList();

		jmiLinkRefs.add(jsysUser.getJmiMember().getJmiLinkRef());
    	if(getIsUnderLine(user, jsysUser.getJmiMember(), defLayerID, "link_no")){
        	jmiMemberManager.getNetTree(jmiLinkRefs, jsysUser.getJmiMember().getJmiLinkRef(),1, 0);
    	}
    	
    	
    	
    	return reSetListCode(jmiLinkRefs,  user,userCode,StringUtil.formatInt(curLayer));
    }
    
    @RequestMapping(value="/JmiMember/api/getJmiRecommendRefs",method = RequestMethod.GET)
    @ResponseBody
    public List getJmiRecommendRefs(String userId,String token,String userCode,String curLayer){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
    	String defLayerID=setList(null, user, "select.recommend.ordinaryuser.mobile", "select.recommend.ref.mobile");

    	JsysUser jsysUser=jsysUserManager.get(userCode);
    	
    	
    	List jmiRecommendRefs = new ArrayList();

		jmiRecommendRefs.add(jsysUser.getJmiMember().getJmiRecommendRef());
    	if(getIsUnderLine(user, jsysUser.getJmiMember(), defLayerID, "recommend_no")){
        	jmiMemberManager.getNetTree(jmiRecommendRefs, jsysUser.getJmiMember().getJmiRecommendRef(),1, 0);
    	}
    	
    	return reSetListCode(jmiRecommendRefs, user,userCode,StringUtil.formatInt(curLayer));
    }
    
  
    
    private List reSetListCode(List list,JsysUser user,String userCode,int curLayer){
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
    	List resList=new ArrayList();
    	for (int i = 0; i < list.size(); i++) {
    		Object obj=list.get(i);
    		Map<String,Object> map=new HashMap<String,Object>();
    		if(obj instanceof JmiLinkRef){
    			JmiLinkRef jmiLinkRef=(JmiLinkRef) obj;
    			
    			
    			
    			map.put("USER_CODE", jmiLinkRef.getUserCode());
    			map.put("LINK_NO", jmiLinkRef.getJmiMember().getLinkNo());
    			map.put("PET_NAME", jmiLinkRef.getJmiMember().getPetName());
    			//map.put("LAYERID", jmiLinkRef.getLayerId());
    			
    			if(jmiLinkRef.getUserCode().equals(userCode)){
    				map.put("LAYERID", curLayer);
    			}else{
    				map.put("LAYERID", curLayer+1);
    			}
    			
    			if(jmiLinkRef.getJmiMember().getCheckDate()!=null){
    				map.put("create_time_str", sdf.format(jmiLinkRef.getJmiMember().getCheckDate()));
    			}else{
    				map.put("create_time_str", sdf.format(jmiLinkRef.getJmiMember().getCreateTime()));
    			}
    			map.put("card_type", ListUtil.getListValue(user.getDefCharacterCoding(), "bd.cardtype", jmiLinkRef.getJmiMember().getCardType()));
    			map.put("member_level", ListUtil.getListValue(user.getDefCharacterCoding(), "member.level", jmiLinkRef.getJmiMember().getMemberLevel()+""));
    			
        	}else if(obj instanceof JmiRecommendRef){
        		JmiRecommendRef jmiRecommendRef=(JmiRecommendRef) obj;
        		map.put("USER_CODE", jmiRecommendRef.getUserCode());
    			map.put("RECOMMEND_NO", jmiRecommendRef.getJmiMember().getRecommendNo());
    			map.put("PET_NAME", jmiRecommendRef.getJmiMember().getPetName());
    			//map.put("LAYERID", jmiRecommendRef.getLayerId());
    			
    			if(jmiRecommendRef.getUserCode().equals(userCode)){
    				map.put("LAYERID", curLayer);
    			}else{
    				map.put("LAYERID", curLayer+1);
    			}
    			
    			if(jmiRecommendRef.getJmiMember().getCheckDate()!=null){
    				map.put("create_time_str", sdf.format(jmiRecommendRef.getJmiMember().getCheckDate()));
    			}else{
    				map.put("create_time_str", sdf.format(jmiRecommendRef.getJmiMember().getCreateTime()));
    			}
    			map.put("card_type", ListUtil.getListValue(user.getDefCharacterCoding(), "bd.cardtype", jmiRecommendRef.getJmiMember().getCardType()));
    			map.put("member_level", ListUtil.getListValue(user.getDefCharacterCoding(), "member.level", jmiRecommendRef.getJmiMember().getMemberLevel()+""));
        	}
    		resList.add(map);
		}
    	
    	return resList;
    }
	private String setList(HttpServletRequest request,JsysUser defSysUser,String code,String zcCode){
		boolean isCSH = StringUtil.getCheckIsUnlimitUser(defSysUser.getUserCode());
    	List layerList = new ArrayList();
    	//如果是中策用户，那么允许查５代
    	String defLayerID="";
    	String codeStr="";
    	if(isCSH){
    		codeStr=zcCode;
    	}else{
    		codeStr=code;
    	}
    	defLayerID = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), codeStr);
    	int defId = Integer.parseInt(defLayerID);
		for (int i =1 ; i <= defId ; i++ ){
			layerList.add(i);
		}
    	if(request!=null){
    		request.setAttribute("layerList", layerList);
    	}
    	return defLayerID;
	}
    /**
     * 安置查询-会员信息系统平台
     * @author gw 2013-07-05
     * @param request
     * @return
     */
    @RequestMapping(value="/jmiLinkRefs",method = RequestMethod.GET)
    public String getJmiLinkRefs(HttpServletRequest request){
    	
    	//获取当前用户的信息
    	JsysUser defSysUser = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	String defLayerID=setList(request, defSysUser, "select.link.ordinaryuser", "select.link.ref");
    	List jmiLinkRefsList = new ArrayList();
    	String layerId  = request.getParameter("layerId");
    	if(!StringUtil.getIsSeachTime(defSysUser.getCompanyCode())){
			return "jmiLinkRefs";
		}
    	if(StringUtil.isInteger(layerId)&&StringUtil.formatInt(layerId) <=StringUtil.formatInt(defLayerID)){
    		jmiLinkRefsList.add(defSysUser.getJmiMember().getJmiLinkRef());
        	jmiMemberManager.getNetTree(jmiLinkRefsList, defSysUser.getJmiMember().getJmiLinkRef(),StringUtil.formatInt(layerId), 0);
    	}
    
    	request.setAttribute("jmiLinkRefsList", jmiLinkRefsList);
    	request.setAttribute("jmiLinkRefsListCount", jmiLinkRefsList.size());
    	return "jmiLinkRefs";
    }
    
    
    /**
     * 推荐查询-会员信息系统平台
     * @author gw 2013-07-05
     * @param request
     * @return
     */
    @RequestMapping(value="/jmiRecommendedInquiry",method = RequestMethod.GET)
    public String getJmiRecommendedInquiry(HttpServletRequest request){
    	
    	//获取当前用户的信息
    	JsysUser defSysUser = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	String defLayerID=setList(request, defSysUser, "select.recommend.ordinaryuser", "select.recommend.ref");
    	List jmiRecommendedInquiryList = new ArrayList();
    	String layerId = request.getParameter("layerId");
    	

    	if(!StringUtil.getIsSeachTime(defSysUser.getCompanyCode())){
			return "jmiRecommendedInquiry";
		}
    	
    	if(StringUtil.isInteger(layerId)&&StringUtil.formatInt(layerId) <=StringUtil.formatInt(defLayerID)){
        	jmiRecommendedInquiryList.add(defSysUser.getJmiMember().getJmiRecommendRef());
        	jmiMemberManager.getNetTree(jmiRecommendedInquiryList, defSysUser.getJmiMember().getJmiRecommendRef(),StringUtil.formatInt(layerId), 0);
    	}

    	request.setAttribute("jmiRecommendedInquiryList", jmiRecommendedInquiryList);
    	request.setAttribute("jmiRecommendedInquiryListCount", jmiRecommendedInquiryList.size());
    	return "jmiRecommendedInquiry";
    }

    @RequestMapping(value="/jmiMembers",method = RequestMethod.GET)
    public String getJmiMembers(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
/*    	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-service.xml");
    	WebClient webClient = ctx.getBean("webClient", WebClient.class);

        System.out.println("fffffffffff:"+webClient.path("user.php").accept(MediaType.TEXT_PLAIN).get(String.class));*/
    	

/*		WebClient webClient = WebClient.create("http://58.64.198.42/guateng/api/ec/");
		
		//?ec_user=CN21736826&mobile_phone=13800138000&password=c4ca4238a0b923820dcc509a6f75849b&email=null&time=1385368031091&code=aa6a8aadd0e6a27756932259474b0276
		String res=webClient.path("user.php").accept(MediaType.TEXT_PLAIN).get(String.class);
		
		System.out.println(res);
	*/
		
    	
    	
    	/*        String res=webClient.path("user.php").accept(MediaType.TEXT_PLAIN).get(String.class);
        
        System.out.println(res);
       Gson gson=new Gson();
        Map map1=gson.fromJson(res, Map.class);
        System.out.println(map1.get("code"));*/
    
    	
    	Map map=new HashMap();
    	map.put("gradeType", "0"); 
    	map.put("createNo", defSysUser.getUserCode());
    	String strAction=request.getParameter("strAction");
    	String userCode=request.getParameter("userCode");

    	
		/* 删除员会*/
		if ("deleteMember".equals(strAction)) {
			JmiMember jmiMember = jmiMemberManager.get(userCode);
    		try{
    			jmiMemberManager.removeJmiMember(jmiMember.getUserCode());
    			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.deleted"));
		        return "jmiMembers";
    		}catch(AppException e){
    			e.printStackTrace();
    			if("miLinkRef.hasMember".equals(e.getMessage())){
    				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miLinkRef.hasMember"));
            	}else if("member.remove.0".equals(e.getMessage())){
    				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("member.remove.0"));
            	}else if("jfiBankbookBalance.greatthan.0".equals(e.getMessage())){
    				MessageUtil.saveMessage(request, LocaleUtil.getLocalText(e.getErrMsg()));
            	}else{
            		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("miMember.deleteFail"));
            	}
    			 //return "jmiMembers";
    		}
		}
    	
    	List jmiMembers=jmiMemberManager.getJmiMembers(map);
    	
    	request.setAttribute("jmiMembers", jmiMembers);
    	
    	return "jmiMembers";
    }

    
    
    /**
     * 查安置组织业绩 首方法
     * @param request
     * @return
     */
    @RequestMapping(value="/jbdBonusTree",method = RequestMethod.GET)
    public String getJbdBonusTree(HttpServletRequest request){

		request.setAttribute("curBdPeriod", getCurBdPeriod());
    	return "jbdBonusTree";
    }

    @RequestMapping(value="/jbdBonusTreeSelect",method = RequestMethod.GET)
    public String getJbdBonusTreeSelect(HttpServletRequest request){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	String returnView="jbdBonusTreeSelect";

        String doubleView = ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "double.view");
        request.setAttribute("doubleView", doubleView);
        
		String userCode = request.getParameter("userCode");
		String formatedWeek = request.getParameter("formatedWeek");

		request.setAttribute("curBdPeriod", getCurBdPeriod());
		

    	if(!StringUtil.getIsSeachTime(defSysUser.getCompanyCode())){
			return returnView;
		}
		
		if ("bdBonusTreeQuery".equals(request.getParameter("strAction"))) {

			//判断期别是否存在
			JbdPeriod bdPeriod = this.jbdPeriodManager.getBdPeriodByFormatedWeek(formatedWeek);
			if (bdPeriod == null) {
				//this.saveMessage(request, LocaleUtil.getLocalText("error.wweek.not.existed"));
				return returnView;
			}
			//判断是否为下限
			JmiRecommendRef LoginMember = jmiRecommendRefManager.get(defSysUser.getUserCode());
			JmiRecommendRef searchMember = jmiRecommendRefManager.get(userCode);
			if(searchMember!=null && !StringUtil.getCheckIsDownline(LoginMember.getTreeIndex(), searchMember.getTreeIndex())){
				return returnView;
			}
			//如果编号为空 则默认登录会员
			if(userCode.equals("") || userCode == null){
				userCode = defSysUser.getUserCode();
			}
			Integer wweekInt=StringUtil.formatInt(WeekFormatUtil.getFormatWeek("f",formatedWeek));
			List jbdDayBonusList=new ArrayList();
			Map topMap=jmiMemberManager.getJbdDayBounsCalcByUserCode(userCode, wweekInt);			
			if(topMap==null){
				return returnView;
			}
			jbdDayBonusList.add(topMap);
			getBdBonusTree("link_no", jbdDayBonusList, topMap, 5, 0, wweekInt);
			/*for (int i = 0; i < jbdDayBonusList.size(); i++) {
				Map map=(Map) jbdDayBonusList.get(i);
				System.out.println(map.get("user_code")+"层数:"+map.get("level"));
			}*/

			Map<String, Integer> rowMap = new HashMap<String, Integer>();
			if (jbdDayBonusList != null && jbdDayBonusList.size() > 0) {
				for (int i = 0; i < jbdDayBonusList.size(); i++) {
					Map jbdDayBonusMap = (HashMap) jbdDayBonusList.get(i);
					rowMap.put(jbdDayBonusMap.get("user_code").toString(), new Integer(i + 1));
					if (userCode.equals(jbdDayBonusMap.get("user_code").toString())) {
						jbdDayBonusMap.put("parentId", new Integer(0));
					} else {
						jbdDayBonusMap.put("parentId", new Integer(rowMap.get(jbdDayBonusMap.get("link_no").toString()).toString()));
					}
				}
			}
			
			getUpNet(request, defSysUser.getUserCode(), userCode, wweekInt, "upLinkList");
			
			request.setAttribute("jbdDayBonusList", jbdDayBonusList);
		}

    	return returnView;
    }

    
    /** 
     * 查登录会员的安置组织业绩
     * @param request
     * @param loginUserCode
     * @param searchUserCode
     * @param wweekInt
     * @param netTypeList
     */
    private void getUpNet(HttpServletRequest request,String loginUserCode,String searchUserCode,Integer wweekInt,String netTypeList){
    	List list=new ArrayList();
		while(!searchUserCode.equals(loginUserCode)){
			Map curMap=jmiMemberManager.getJbdDayBounsCalcByUserCode(searchUserCode, wweekInt);
			
			if("upLinkList".equals(netTypeList)){
				searchUserCode=curMap.get("link_no").toString();
			}else if("upRecommendList".equals(netTypeList)){
				searchUserCode=curMap.get("recommend_no").toString();
			}
			list.add(curMap);
		}
		Collections.reverse(list);
		request.setAttribute(netTypeList, list);
    }
    
    /**
     * 安置网络递归
     * @param netType
     * @param jbdDayBonusList
     * @param dayMap
     * @param maxLevel
     * @param level
     * @param formatedWeek
     */
    private void getBdBonusTree(String netType,final List jbdDayBonusList,final Map dayMap, final int maxLevel, int level,Integer formatedWeek){
		List curList = jmiMemberManager.getChildJbdDayBounsCalcBySql(dayMap.get("user_code").toString(),netType, formatedWeek);
    	if(maxLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}
    	if(curList!=null && level <= maxLevel){
    		for(int i=0;i<curList.size();i++){
    			Map temp = (Map) curList.get(i);
    			temp.put("level", level);
    			jbdDayBonusList.add(temp);
    			getBdBonusTree(netType, jbdDayBonusList, temp, maxLevel, level, formatedWeek);
    		}
    	}
    }

    @RequestMapping(value="/JmiMember/api/saveNewJmiMember",method = RequestMethod.GET)
    @ResponseBody
    public Map saveNewJmiMember(JmiMember jmiMember,String userId,String token,String saveType,HttpServletRequest request,BindingResult errors){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
    	if(null!=object){
			return (Map)object;
		}
    	String checkType = "";
        //新增saveType参数，1为第一步注册，2为完善信息
    	if("1".equals(saveType)){
    		JmiLinkRef miLinkRef = new JmiLinkRef();
        	miLinkRef.setLinkJmiMember(new JmiMember());
        	
        	JmiRecommendRef miRecommendRef = new JmiRecommendRef();
        	miRecommendRef.setRecommendJmiMember(new JmiMember());
        	jmiMember.setCompanyCode(user.getCompanyCode());
        	jmiMember.setJmiRecommendRef(miRecommendRef);
        	jmiMember.setJmiLinkRef(miLinkRef);
            jmiMember.setSysUser(new JsysUser());
            
            String recommendNo=request.getParameter("recommendNo");
            String linkNo=request.getParameter("linkNo");
            jmiMember.getJmiRecommendRef().getRecommendJmiMember().setUserCode(recommendNo);
            jmiMember.getJmiLinkRef().getLinkJmiMember().setUserCode(linkNo);
            
    		jmiMember.getSysUser().setPassword("888888");
            jmiMember.getSysUser().setPassword2("888888");
            checkType = "1";
            
    	}else{
            checkType = "5";
    	}
        
        Map map=new HashMap();
        String errorStr="";
		try {
			if (jmiMemberManager.getCheckMiMember(jmiMember, errors, request, checkType, user)) {
				for (int i = 0; i < errors.getAllErrors().size(); i++) {
					ObjectError oe = errors.getAllErrors().get(i);
					if (i == 0) {
						errorStr += oe.getCode();
					} else {
						errorStr += (";" + oe.getCode());
					}
				}
			} else {
				// 如果是木兰团队，就判断两条边。。。
				/*
				 * if(木兰){ }
				 */

				if ("1".equals(saveType)) {
					List miLinkRefs = jmiLinkRefDao.getLinkRefbyLinkNo(
							jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode(), "userCode", "");
					if (miLinkRefs.size() >= 2) {
						errorStr = StringUtil.getErrorsCode(errors, "该服务商最多只能安置两个会员",
								"jmiLinkRef.linkJmiMember.userCode", "utf-8");
					}
				}
			}

			if (!StringUtil.isEmpty(errorStr)) {
				map.put("errorCode", "0");
				map.put("message", errorStr);
				return map;
			}
			String userCode = "";
			if("1".equals(saveType)){
				jmiMember.setIsMobile("1");
				userCode=jmiMemberManager.saveNewMiMember(jmiMember, user,null);
			}else{
				userCode = jmiMember.getUserCode();
				JmiMember oldJmiMember = jmiMemberManager.getJmiMemberBankInformation(userCode);
				oldJmiMember.setAddress(jmiMember.getAddress());
				oldJmiMember.setCity(jmiMember.getCity());
				oldJmiMember.setDistrict(jmiMember.getDistrict());
				oldJmiMember.setMobiletele(jmiMember.getMobiletele());
				oldJmiMember.setPetName(jmiMember.getPetName());
				oldJmiMember.setProvince(jmiMember.getProvince());
				if(MeteorUtil.isBlank(oldJmiMember.getMiUserName())){
					oldJmiMember.setFirstName(jmiMember.getFirstName());
					oldJmiMember.setLastName(jmiMember.getLastName());
					oldJmiMember.getSysUser().setFirstName(jmiMember.getFirstName());
					oldJmiMember.getSysUser().setLastName(jmiMember.getLastName());
					this.getSetUserName(oldJmiMember);
					oldJmiMember.setBankbook(oldJmiMember.getMiUserName());
				}
				
				jmiMemberManager.save(oldJmiMember);
			}
        	
     	   	map.put("errorCode", "1");
 			map.put("message", userCode);
 			return map;
        	 
		} catch (Exception e) {
			e.printStackTrace();

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
		    pw.close();
		    try {
				sw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			map.put("errorCode", "0");
 			map.put("message", sw.toString());
 			return map;
		} 
    }
    //木兰行注册信息
    @RequestMapping(value="/JmiMember/api/saveNewJmiMember2",method = RequestMethod.GET)
    @ResponseBody
    public Map saveNewJmiMember2(JmiMember jmiMember,String userId,String token,String saveType,HttpServletRequest request,BindingResult errors){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
    	if(null!=object){
    		return (Map)object;
    	}
    	String checkType = "";
    	//新增saveType参数，1为第一步注册，2为完善信息
    	if("1".equals(saveType)){
    		JmiLinkRef miLinkRef = new JmiLinkRef();
    		miLinkRef.setLinkJmiMember(new JmiMember());
    		
    		JmiRecommendRef miRecommendRef = new JmiRecommendRef();
    		miRecommendRef.setRecommendJmiMember(new JmiMember());
    		jmiMember.setCompanyCode(user.getCompanyCode());
    		jmiMember.setJmiRecommendRef(miRecommendRef);
    		jmiMember.setJmiLinkRef(miLinkRef);
    		jmiMember.setSysUser(new JsysUser());
    		
    		String recommendNo=request.getParameter("recommendNo");
    		String linkNo=jmiLinkRefManager.getLinkNo(recommendNo);
    	//	String linkNo=request.getParameter("linkNo");
    		jmiMember.getJmiRecommendRef().getRecommendJmiMember().setUserCode(recommendNo);
    		jmiMember.getJmiLinkRef().getLinkJmiMember().setUserCode(linkNo);
    		
    		jmiMember.getSysUser().setPassword("888888");
    		jmiMember.getSysUser().setPassword2("888888");
    		checkType = "1";
    		
    	}else{
    		checkType = "5";
    	}
    	
    	Map map=new HashMap();
    	String errorStr="";
    	try {
    		if (jmiMemberManager.getCheckMiMember(jmiMember, errors, request,
    				checkType, user)) {
    			for (int i = 0; i < errors.getAllErrors().size(); i++) {
    				ObjectError oe = errors.getAllErrors().get(i);
    				if (i == 0) {
    					errorStr += oe.getCode();
    				} else {
    					errorStr += (";" + oe.getCode());
    				}
    			}
    		}
    		
    		
    		if (!StringUtil.isEmpty(errorStr)) {
    			map.put("errorCode", "0");
    			map.put("message", errorStr);
    			return map;
    		}
    		String userCode = "";
    		if("1".equals(saveType)){
    			jmiMember.setIsMobile("1");
    			userCode=jmiMemberManager.saveNewMiMember(jmiMember, user,null);
    		}else{
    			userCode = jmiMember.getUserCode();
    			JmiMember oldJmiMember = jmiMemberManager.getJmiMemberBankInformation(userCode);
    			oldJmiMember.setAddress(jmiMember.getAddress());
    			oldJmiMember.setCity(jmiMember.getCity());
    			oldJmiMember.setDistrict(jmiMember.getDistrict());
    			oldJmiMember.setMobiletele(jmiMember.getMobiletele());
    			oldJmiMember.setPetName(jmiMember.getPetName());
    			oldJmiMember.setProvince(jmiMember.getProvince());
    			if(MeteorUtil.isBlank(oldJmiMember.getMiUserName())){
    				oldJmiMember.setFirstName(jmiMember.getFirstName());
    				oldJmiMember.setLastName(jmiMember.getLastName());
    				oldJmiMember.getSysUser().setFirstName(jmiMember.getFirstName());
    				oldJmiMember.getSysUser().setLastName(jmiMember.getLastName());
    				this.getSetUserName(oldJmiMember);
    				oldJmiMember.setBankbook(oldJmiMember.getMiUserName());
    			}
    			
    			jmiMemberManager.save(oldJmiMember);
    		}
    		
    		map.put("errorCode", "1");
    		map.put("message", userCode);
    		return map;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		
    		StringWriter sw = new StringWriter();
    		PrintWriter pw = new PrintWriter(sw);
    		e.printStackTrace(pw);
    		
    		pw.close();
    		try {
    			sw.close();
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    		map.put("errorCode", "0");
    		map.put("message", sw.toString());
    		return map;
    	} 
    }
    

  //更改姓名
  	public void getSetUserName(JmiMember jmiMember) {

  		String format=(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("member.name.format");
  		String space="";
  		if("0".equals(format)){
  			space="";
  		}else{
  			space=" ";
  		}
  		if((StringUtil.isEmpty(jmiMember.getFirstName())&&StringUtil.isEmpty(jmiMember.getLastName()))){
  			String name=jmiMember.getMiUserName();
  			jmiMember.getSysUser().setFirstName("");
  			jmiMember.getSysUser().setLastName(name);
  			jmiMember.setFirstName("");
  			jmiMember.setLastName(name);
  			jmiMember.getSysUser().setUserName(name);
  		}else{
  			jmiMember.getSysUser().setUserName((jmiMember.getFirstName()==null?"":jmiMember.getFirstName())+space+jmiMember.getLastName());
  			jmiMember.getSysUser().setFirstName(jmiMember.getFirstName());
  			jmiMember.getSysUser().setLastName(jmiMember.getLastName());
  			jmiMember.setMiUserName(jmiMember.getSysUser().getUserName());
  		}
  	}
  	
    @RequestMapping(value="/jmiClubs",method = RequestMethod.GET)
    public String getClubs(HttpServletRequest request){
    	String userCode=request.getParameter("userCode");
    	
    	
    	if(!StringUtil.isEmpty(userCode)){
    		List clubs=jmiMemberManager.getJmiClubs(userCode);
    		request.setAttribute("clubs", clubs);
    		
    	}
    	
    	return "jmiClubs";
    }

    private Integer getCurBdPeriod(){
    	Integer bdWeek=jbdPeriodManager.getBdPeriodByTimeFormated(new Date());
    	return WeekFormatUtil.getFormatWeek("w",bdWeek);
    	
    }
    @RequestMapping(value="/JmiMember/api/getJmiMemberZcw",method = RequestMethod.GET)
    @ResponseBody
    public Map getJmiMemberZcw(String userCode,String authId){
    	
    	String authKey="3=a4d4@vw/59['323.#";
    	
    	Map map=new HashMap();
    	
    	map.put("status", "0");
    	map.put("zcwUserCode", "");
    	map.put("remark", "");
    	
    	String md5=StringUtil.encodePassword(userCode+authKey, "md5");
    	
    	if(!md5.equals(authId)){
    		map.put("remark", "authKeyMD5值错误");
    		return map;
    	}
    	
    	if(StringUtil.isEmpty(userCode)){
    		map.put("remark", "会员编号不能为空");
    		return map;
    	}else if(jmiMemberManager.get(userCode)==null){
    		map.put("remark", "会员不存在");
    		return map;
    	}
    	
    	List list=jdbcTemplate3.queryForList("select  nvl(FN_GET_ZCWsxy('"+userCode+"'),'') as zcwUserCode from dual");
    	if(list.isEmpty()){
    		map.put("remark", "会员所属中策找不到");
    		return map;
    	}else{
    		String zcwUserCode=(String) ((Map)list.get(0)).get("zcwUserCode");
    		if(StringUtil.isEmpty(zcwUserCode)){
    			map.put("remark", "会员所属中策找不到");
    		}else{
            	map.put("status", "1");
        		map.put("zcwUserCode", zcwUserCode);
    		}
    	}
    	
    	
    	
    	return map;
    }

    @RequestMapping(value="/JmiMember/api/getJmiMemberTeamNo",method = RequestMethod.GET)
    @ResponseBody
    public Map getJmiMemberTeamNo(String userCode,String authId,String leaderUserCode ){
    	
    	String authKey="3=a4d4@vw/59['323.#";
    	
    	Map map=new HashMap();
    	
    	map.put("status", "0");
    	map.put("teamNoUserCode", "");
    	map.put("remark", "");
    	
    	String md5=StringUtil.encodePassword(userCode+authKey, "md5");
    	
    	if(!md5.equals(authId)){
    		map.put("remark", "authKeyMD5值错误");
    		return map;
    	}
    	
    	if(StringUtil.isEmpty(userCode)){
    		map.put("remark", "会员编号不能为空");
    		return map;
    	}else if(jmiMemberManager.get(userCode)==null){
    		map.put("remark", "会员不存在");
    		return map;
    	}
    	
    	if(StringUtil.isEmpty(leaderUserCode )){
    		map.put("remark", "领导人编号不能为空");
    		return map;
    	}
    	String teamNoStrss[]=leaderUserCode .split(",");
    	if(teamNoStrss.length==0){
    		map.put("remark", "领导人编号不能为空");
    		return map;
    	}
    	
    	
    	

    	//List list=jdbcTemplate3.queryForList("select  nvl(fn_get_team_no('"+userCode+"'),'') as zcwUserCode from dual");
    	
    	
    	
    	String recommendNo=userCode;
    	
    			
    	boolean flag=false;

    	while(!"8888888888".equals(recommendNo)){
    		recommendNo=jmiMemberManager.get(recommendNo).getRecommendNo();
    		if(leaderUserCode.contains(recommendNo)){
    			map.put("status", "1");
        		map.put("teamNoUserCode", recommendNo);
        		flag=true;
        		break;
    		}
		}
    	
    	if(!flag){
    		map.put("remark", "所属领导人找不到");
    	}
    	
    	
    	
    	return map;
    }
    
    @RequestMapping(value="/JmiMember/api/getJmiMemberForGHB",method = RequestMethod.GET)
    @ResponseBody
    public List saveNewJmiMember(String userCode,String phone){
        if(StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(phone)){
            return new ArrayList();
        }
        return this.jmiMemberManager.getJmiMemberForGHB(userCode,phone);
    }
    
    
/*    @RequestMapping(value="/JmiMember/api/getJmiRecommendNo",method = RequestMethod.GET)
    @ResponseBody
    public Map getJmiMemberTeamNo(String userCode,String authId ){
    	
    	String authKey="3=a4d4@vw/59['323.#";
    	
    	Map map=new HashMap();
    	
    	map.put("status", "0");
    	map.put("recommendNo", "");
    	map.put("remark", "");
    	
    	String md5=StringUtil.encodePassword(userCode+authKey, "md5");
    	
    	if(!md5.equals(authId)){
    		map.put("remark", "authKeyMD5值错误");
    		return map;
    	}
    	
    	if(StringUtil.isEmpty(userCode)){
    		map.put("remark", "会员编号不能为空");
    		return map;
    	}else{
    		JmiMember jmiMember=jmiMemberManager.get(userCode);
    		if(jmiMember==null){
    			map.put("remark", "会员不存在");
        		return map;
    		}else{
    			map.put("status", "1");
    			map.put("recommendNo", jmiMember.getRecommendNo());
        		return map;
    		}
    	}
    	
    }*/
    /**
     * 查询所属事业体
     * @return
     */
    @RequestMapping(value="/JmiMember/api/getBusinessNo",method = RequestMethod.GET)
    @ResponseBody
    public Map getBusinessUnits(String userCode,String authId ){
    	String userCodeStr="CN62085175,CN18959296,CN18959296,CN18222753,CN35644726,CN18959296,CN35644726,CN18222753,CN35644726,CN17586172,CN14509549,CN21279523,CN47327823,CN16238914,CN16238914,CN16238914,CN47327823,CN47327823,CN77980363,CN18590660,CN38929123,CN91020990,CN33605679,CN32345486,CN21206319,CN32345486,CN11644980,CN21206319,CN18853459,CN67198738,CN27014911,CN19899823,CN32080320,CN18105315,CN18419958,CN20896272,CN13140544,CN10330579,CN59430121,CN71466107,CN60988230,CN60988230,CN16685288,CN71889180,CN13013803,CN80498490,CN71889180,CN14770675,CN44804281,CN88035000,CN16539449,CN13732887,CN17617906,CN21164328,CN16740453,CN80498490,CN20288167,CN17054316,CN97659416,CN80822727,CN75040811,CN16611181,CN97659416,CN16137775,CN85139743,CN95932673,CN20587819,CN46468155,CN17054316,CN15861833,CN55051960,CN26029419,CN18954313,CN88190567,CN56116773,CN46468155,CN82869183,CN00034557,CN21003021,CN58007033,CN18640134,CN82869183,CN70575434,CN25330903,CN85735625,CN18640134,CN00034376,CN60302467,CN21003021,CN60302467,CN58007033,CN44606633,CN15077432,CN15077432,CN50026704,CN30888151,CN21409431,CN21239829,CN74194347,CN21409431,CN17217223,CN14156085,CN74194347,CN21239829,CN17334441,CN63903173,CN19200238,CN14156085,CN19200238,CN92663349,CN40401251,CN41704047,CN12898280,CN92663349,CN56359005,CN19110980,CN19110980,CN46122923,CN13940167,CN11390178,CN10521566,CN13642752,CN10521566,CN11390178,CN13642752,CN46308947,CN18698757,CN18698757,CN78124195,CN70650138,CN58347955,CN76318821,CN37320462,CN30332431,CN30332431,CN10910605,CN12632742,CN21622112,CN21622112,CN22851320,CN58347955,CN18698757,CN10910605,CN18766575,CN18645446,CN62827846,CN32985884,CN40449939,CN15456166,CN42108889,CN12391481";
    	//String userCodeStr="CN12484100,";
    	String authKey="3=a4d4@vw/59['323.#";
    	
    	Map map=new HashMap();
    	
    	map.put("status", "0");
    	map.put("BusinessNo", "");
    	map.put("remark", "");
    	
    	String md5=StringUtil.encodePassword(userCode+authKey, "md5");
    	
    	if(!md5.equals(authId)){
    		map.put("remark", "authKeyMD5值错误");
    		return map;
    	}
    	
    	if(StringUtil.isEmpty(userCode)){
    		map.put("remark", "会员编号不能为空");
    		return map;
    	}else{
    		JmiMember jmiMember=jmiMemberManager.get(userCode);
    		if(jmiMember==null){
    			map.put("remark", "会员不存在");
        		return map;
    		}
    		/*else{
    			map.put("status", "1");
    			map.put("recommendNo", jmiMember.getRecommendNo());
        		return map;
    		}*/
    	}
    	
    	
    	String recommendNo=userCode;
    	
		
    	boolean flag=false;

    	while(!"8888888888".equals(recommendNo)){
    		recommendNo=jmiMemberManager.get(recommendNo).getRecommendNo();
    		if(userCodeStr.contains(recommendNo)){
    			map.put("status", "1");
        		map.put("BusinessNo", recommendNo);
        		flag=true;
        		break;
    		}
		}
    	
    	if(!flag){
    		map.put("remark", "所属事业体找不到");
    	}
    	
    	return map;
    	
    	
    }
    
    @RequestMapping(value="/JmiMember/api/getIsBindingGuaten",method = RequestMethod.GET)
    @ResponseBody
    public Map getIsBindingGuaten(String userId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
    	JmiMember jmiMember=user.getJmiMember();
    	//先判断电话号码是否绑定 再判断身份证其他帐号是否绑定
		Map map=new HashMap();
		String ecMallStatus="";
		if("1".equals(jmiMember.getEcMallStatus())){
			ecMallStatus="1";
			map.put("ec_mall_phone", jmiMember.getEcMallPhone());
			map.put("user_code", user.getUserCode());
		}else{
			Map map1=jmiMemberManager.getSamePapernumberUserCode(jmiMember.getPapernumber());
			if(map1!=null){
				ecMallStatus="1";
				map.put("ec_mall_phone", map1.get("ec_mall_phone"));
				map.put("user_code", map1.get("user_code"));
			}else{
				ecMallStatus="0";
			}
		}
		map.put("ecMallStatus", ecMallStatus);
    	
    	return map;
    }
    @RequestMapping(value="/JmiMember/api/bindingGuaten",method = RequestMethod.GET)
    @ResponseBody
    public Map bindingGuaten(String userId,String token,String phone){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
		JmiMember jmiMember=user.getJmiMember();
		String remark="";
    	//先判断电话号码是否绑定 再判断身份证其他帐号是否绑定
		Map map=new HashMap();

		Map map2=new HashMap();
		String ecMallStatus="";
		if("1".equals(jmiMember.getEcMallStatus())){
			ecMallStatus="1";
			map.put("ec_mall_phone", jmiMember.getEcMallPhone());
			map.put("user_code", user.getUserCode());
		}else{
			map=jmiMemberManager.getSamePapernumberUserCode(jmiMember.getPapernumber());
			if(map!=null){
				ecMallStatus="1";
			}else{
				ecMallStatus="0";
			}
		}
		if(StringUtil.isEmpty(phone)){
			remark="请输入电话";
		}
		JmiMember jmiMember1=new JmiMember();
		jmiMember1.setEcMallPhone(phone);
		if(jmiMemberManager.getCheckEcMallPhone(jmiMember1)){
    		remark="该电话已经激活过";
    	}else if("1".equals(jmiMember.getEcMallStatus())){
			remark="已经激活过";
    	}
		if("1".equals(ecMallStatus)){
			remark="此会员已经绑定过";
		}
		map2.put("remark", remark);
		if(!StringUtil.isEmpty(remark)){
			map2.put("status", "0");
			return map2;
		}
		
		jmiMember.setEcMallPhone(phone);
		jmiMember.setEcMallStatus("1");
		jmiMemberManager.save(jmiMember);
		
    	map2.put("status", "1");
		return map2;
    }

	
    @RequestMapping(value="/JmiMember/api/bindingCloudshop",method = RequestMethod.GET)
    @ResponseBody
    public Map bindingCloudshop(String userId,String token,String phone,String cloudshopNo){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
		JmiMember jmiMember=user.getJmiMember();
		String remark="";

		Map map2=new HashMap();
		if(!"1".equals(jmiMember.getIsCloudshop())){
			remark="此会员未有资格绑定";
		}
		if(!StringUtil.isEmpty(jmiMember.getCloudshopPhone())){
			remark="此会员已经绑定过";
		}
		if(!StringUtil.isEmpty(remark)){
			map2.put("status", "0");
			return map2;
		}
		
		jmiMember.setCloudshopNo(cloudshopNo);
		jmiMember.setCloudshopPhone(phone);
		jmiMemberManager.save(jmiMember);
		
    	map2.put("status", "1");
		return map2;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	@RequestMapping(value="/JmiMember/api/getJmiMemberLinkNoToApp",method = RequestMethod.GET)
    @ResponseBody 
   	public Map find(HttpServletRequest request,String userId,String token,String recommendNo){
       	Map resultMap=new HashMap();
       	try {
       	JmiMember jmiMember=new JmiMember();
       	//手机登陆
       	JsysUser user = jsysUserManager.getUserByToken(userId, token);
       	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
       	//手机登陆出错验证
       	if(null!=object){
   			resultMap.put("returnCode", "-1");
   			resultMap.put("message", "登录验证失败");
   			resultMap.put("left_cn", "");
   			resultMap.put("left_nickName", "");
   			resultMap.put("right_cn", "");
   			resultMap.put("right_nickName", "");
   		}
       	
       
       	
       	//安置人
       	JmiLinkRef jmiLinkRef = new JmiLinkRef();
       	jmiLinkRef.setLinkJmiMember(new JmiMember());
       	
       	jmiMember.setJmiLinkRef(jmiLinkRef);
       	jmiMember.getJmiLinkRef().getLinkJmiMember().setUserCode(recommendNo);
       	
    	JmiMember member = jmiMemberManager.get(recommendNo);
       	if (member==null) {
       		resultMap.put("returnCode", "1");
   			resultMap.put("message", "CN号验证失败");
   			resultMap.put("left_cn", "");
   			resultMap.put("left_nickName", "");
   			resultMap.put("right_cn", "");
   			resultMap.put("right_nickName", "");
		}else{
			resultMap = jmiMemberManager.getCheckMiMemberLinkNo(jmiMember,user);
		}
   		} catch (Exception e) {
   			e.printStackTrace();
   			resultMap.put("returnCode", "0");
   			resultMap.put("message", "系统异常");
   			resultMap.put("left_cn", "");
   			resultMap.put("left_nickName", "");
   			resultMap.put("right_cn", "");
   			resultMap.put("right_nickName", "");
   		}
       	return resultMap;
       }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	@RequestMapping(value="/JmiMember/api/getJmiSelectLinkNoToApp",method = RequestMethod.GET)
    @ResponseBody
   	public Map find(HttpServletRequest request,String userId,String token,String recommendNo,String linkNo){
       	Map resultMap=new HashMap();
       	try {
       	JmiMember jmiMember=new JmiMember();
       	//手机登陆
       	JsysUser user = jsysUserManager.getUserByToken(userId, token);
       	Object object = jsysUserManager.getAuthErrorCode(user, "Map");
       	//手机登陆出错验证
       	if(null!=object){
   			resultMap.put("returnCode", "-1");
   			resultMap.put("message", "登录验证失败");
   			resultMap.put("searchNo", "");
   			resultMap.put("nickname", "");
   		}
       	
       	Map map = jmiMemberManager.getJmiSelectLinkNoToApp(recommendNo,linkNo,user);
       	Integer integer = (Integer)map.get("flag");
       	if (0 == integer) {
       		resultMap.put("returnCode", "-2");
       		String message = (String)map.get("msg");
    		resultMap.put("message",message);
   			resultMap.put("searchNo", "");
   			resultMap.put("nickname", "");
		}else{
			resultMap.put("returnCode", "1");
			resultMap.put("message", "查询成功");
			String jmiSelectLinkNo = (String)map.get("search");
			if (jmiSelectLinkNo == null) {
				jmiSelectLinkNo = "";
			}
			if (!jmiSelectLinkNo.equals("")) {
				String[] split = jmiSelectLinkNo.split(",");
				resultMap.put("searchNo", split[0]);
				resultMap.put("nickname", split[1]);
			}else{
				resultMap.put("searchNo", "");
				resultMap.put("nickname", "");
			}
	}
   		} catch (Exception e) {
   			resultMap.put("returnCode", "0");
   			resultMap.put("message", "系统异常");
   			resultMap.put("searchNo", "");
   			resultMap.put("nickname", "");
   		}
       	return resultMap;
       }

	/**
	 * 	手机端银行卡首次新增接口
	 */
	@RequestMapping(value = "/JmiMember/api/saveBankInformation", method = RequestMethod.GET)
	@ResponseBody
	public Map saveBankInformation(String userId, String token,HttpServletRequest request){
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		Map<String, Object> map = new HashMap<String, Object>();
		String message="";
		String returnCode="";
		if(null!=object){
			Map<String,Object> messageCode=(Map)object;
			String code=(String) messageCode.get("autherror");
			if(code.equals("0")){
				message="禁用用户";
				returnCode="0";
			}else if(code.equals("1")){
				message="用户名不存在";
				returnCode="1";
			}else if(code.equals("2")){
				message="系统禁止用户登录";
				returnCode="2";
			}
			map.put("message", message);
			map.put("returnCode", returnCode);
			return map;
		}
		JmiMember jmiMember=user.getJmiMember();
		if( StringUtils.isNotEmpty(jmiMember.getBankcard())||
				StringUtils.isNotEmpty(jmiMember.getBank())||
				StringUtils.isNotEmpty(jmiMember.getBankaddress())||
				StringUtils.isNotEmpty(jmiMember.getBankCity())||
				StringUtils.isNotEmpty(jmiMember.getBankProvince())
				){
			map.put("message", "已填写过银行信息");
			map.put("returnCode", "-1");
			return map;
		}
		//银行所在省
		String bankProvince=request.getParameter("bankProvince");
		//银行所在市
		String bankCity=request.getParameter("bankCity");
		//开户银行
		String bank=request.getParameter("bank");
		//开户行支行
		String bankAddress=request.getParameter("bankAddress");
		//银行账号
		String bankCard=request.getParameter("bankCard");

		if(StringUtils.isEmpty(bankProvince.trim())||StringUtils.isEmpty(bankCity.trim())||
				StringUtils.isEmpty(bank.trim())||StringUtils.isEmpty(bankAddress.trim())||
				StringUtils.isEmpty(bankCard.trim())){
			map.put("message", "银行信息未填写完整");
			map.put("returnCode", "-2");
			return map;
		}
		boolean isCSH = StringUtil.getCheckIsUnlimitUser(user.getUserCode());
		List lists=jdbcTemplate.queryForList("select * from jmi_member_log where user_code='"+user.getUserCode()+"' and log_type in ('3','4')");
		if(!isCSH && !lists.isEmpty()){
			map.put("message", "已填写过银行信息");
			map.put("returnCode", "-1");
			return map;
		}
		JmiMemberLog jmiMemberLog = new JmiMemberLog();
		// JmiMemberLog.setLogId(GuidHelper.genRandomGUID());
		jmiMemberLog.setLogTime(new Date());
		jmiMemberLog.setLogType("4");// 1:后台编辑，2后台导入，3前台编辑',移动端编辑
		jmiMemberLog.setLogUserCode(user.getUserCode());
		if (null != jmiMember) {
				jmiMemberLog.setBeforeBank(jmiMember.getBank());
				jmiMemberLog.setBeforeBankaddress(jmiMember.getBankaddress());
				jmiMemberLog.setBeforeBankbook(jmiMember.getBankbook());
				jmiMemberLog.setBeforeBankcard(jmiMember.getBankcard());
				jmiMemberLog.setBeforeBankcity(jmiMember.getBankCity());
				jmiMemberLog.setBeforeBankprovince(jmiMember.getBankProvince());


				jmiMemberLog.setAfterBank(bank);
				jmiMemberLog.setAfterBankaddress(bankAddress);
				jmiMemberLog.setAfterBankbook(jmiMember.getBankbook());
				jmiMemberLog.setAfterBankcard(bankCard);
				jmiMemberLog.setAfterBankcity(bankCity);
				jmiMemberLog.setAfterBankprovince(bankProvince);
				jmiMemberLog.setUserCode(jmiMember.getUserCode());
				jmiMemberLog.setUserName(jmiMember.getFirstName()+ jmiMember.getLastName());
		}

		jmiMember.setBankProvince(bankProvince);
		jmiMember.setBankCity(bankCity);
		jmiMember.setBank(bank);
		jmiMember.setBankaddress(bankAddress);
		jmiMember.setBankcard(bankCard);
		try{
			jmiMemberManager.saveJmiMemberBankAndLog(jmiMember,jmiMemberLog);
		}catch(Exception e ){
			map.put("message", "系统异常");
			map.put("returnCode", "-3");
			return map;
		}
		map.put("message", "成功");
		map.put("returnCode", "3");
		return map;
	}
	@RequestMapping(value="/JmiMember/api/checkPapernumber",method = RequestMethod.GET)
	@ResponseBody
	public Map checkPapernumber(JmiMember jmiMember1,String userId,String token,String papernumber,BindingResult errors){
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
		Map map=new HashMap();
		String errorStr="";

		user.getJmiMember().setPapernumber(papernumber);
		try {
			if (jmiMemberManager.getCheckPapernumber(user.getJmiMember(), errors, user)) {
				for (int i = 0; i < errors.getAllErrors().size(); i++) {
					ObjectError oe = errors.getAllErrors().get(i);
					if (i == 0) {
						errorStr += oe.getCode();
					} else {
						errorStr += (";" + oe.getCode());
					}
				}
			}
			if (!StringUtil.isEmpty(errorStr)) {
				map.put("errorCode", "0");
				map.put("message", errorStr);
				return map;
			}

			jmiMemberManager.save(user.getJmiMember());
			map.put("errorCode", "1");
			map.put("message", "");
			return map;

		} catch (Exception e) {
			log.error(e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			pw.close();
			try {
				sw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			map.put("errorCode", "0");
			map.put("message", sw.toString());
			return map;
		}

	}
}
