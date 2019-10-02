package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.Constants;
import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.FoundationOrder;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JmiSubStore;
import com.joymain.ng.model.JmiTicket;
import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.model.JsysStockAccount;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PublicSchedule;
import com.joymain.ng.service.AmAnnounceManager;
import com.joymain.ng.service.AmAnnounceRecordManager;
import com.joymain.ng.service.AmMessageManager;
import com.joymain.ng.service.AmNewManager;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.FoundationOrderManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.JmiStoreManager;
import com.joymain.ng.service.JmiSubStoreManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JmiTicketManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.service.JsysStockAccountManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.service.PdExchangeOrderManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.service.PublicScheduleManager;
import com.joymain.ng.service.ScheduleManageManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;
import com.joymain.ng.webapp.util.RequestUtil;

@Controller
@RequestMapping("/loginform")
public class LoginFormController extends BaseFormController {
	private final Log log = LogFactory.getLog(LoginFormController.class);
	@Autowired
	private JbdPeriodManager periodManger;
	@Autowired
	private JdbcTemplate jdbcTemplate3;
	@Autowired
	private JbdPeriodManager bdPeriodManager;
	@Autowired
	private JmiSubStoreManager jmiSubStoreManager;
	@Autowired
	private JsysUserRoleManager userRoleManager;
	@Autowired
	private AmNewManager amNewManager;
	@Autowired
	private AmAnnounceManager amAnnounceManager;
	@Autowired
	private PublicScheduleManager publicScheduleManager;
	@Autowired
	private ScheduleManageManager scheduleManager;

	@Autowired
	private AmAnnounceRecordManager amAnnounceRecordManager;
	@Autowired
	private AmMessageManager amMessageManager;
	@Autowired
	private JsysUserManager jsysUserManager;
	@Autowired
	private JsysUserRoleManager jsysUserRoleManager;
	@Autowired
	private JmiRecommendRefManager jmiRecommendRefManager;

	@Autowired
	private JmiLinkRefManager jmiLinkRefManager;
	@Autowired
	private JsysLoginLogManager jsysLoginLogManager;

	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
	
	@Autowired
	private FoundationOrderManager foundationOrderManager;
	
	//发货单管理
	@Autowired
	private PdSendInfoManager pdSendInfoManager;
	
	@Autowired
	private JmiMemberManager jmiMemberManager;
	@Autowired
	private FiCommonAddrManager fiCommonAddrManager;

	@Autowired
	private JmiTicketManager jmiTicketManager;
	@Autowired
	private JmiStoreManager jmiStoreManager;
	
	//Modify By WuCF 20150408 获取会员所属团队
	@Autowired
	private JmiTeamManager jmiTeamManager;
	
	//modify by fu 2016-09-29 获取需要支付且未支付的自助换货单信息
	@Autowired
	private PdExchangeOrderManager pdExchangeOrderManager;
	
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}
	
	@Autowired
	private JsysStockAccountManager jsysStockAccountManager;
	
	@RequestMapping("/notice")
	public String showNotice(){
		return "notice";
	}

	@RequestMapping("/guaten_notice")
	public String showGuatenNotice(){
		return "guaten_notice";
	}
	
	@RequestMapping("/showuserinfo")
	public String showMemberInfo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info(">>>>>>loginForm>>>>>>>>>");
		JsysUser sysUser = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		String userCode = sysUser.getUserCode();
		JmiMember jmiMember=jmiMemberManager.get(sysUser.getUserCode());
		log.info("userCode is:[" + userCode + "]"); 
		request.setAttribute("user", sysUser);
		//1.PV values
		Map indexMap=new HashMap();
		getValuePV(request, sysUser,indexMap);

		//2.提示
		getTips(request, sysUser,indexMap);

		//
		
		
		/**
		 * news list List<AmNew> amNews =
		 * amNewManager.findNewByDate("2012-03-01 00:00","2012-03-29 23:59");
		 * System.out.println(">>>>>>>>>>>>>>>>"+amNews);
		 * request.setAttribute("amNews", amNews);
		 **/
		//3.个人安排
		Date today = new Date();
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		String tday = sdfm.format(today);

		/*List psList = scheduleManager.getScheduleByUserCode(userCode, tday);
		for (int i = 0; i < psList.size(); i++) {
			ScheduleManage s = (ScheduleManage) psList.get(i);
			if (s.getRemind() == '1') { // 设置提醒 
				// 发送短信

			}
		}
		if (psList.size() > 8) {
			psList = psList.subList(0, 8);
		}*/
		//Modify By WuCF 20131209
		//修改提取数据的方式，直接查询最多8条数据，而不是先查询出所有的数据然后用list.subList(param,param);
		List psList = scheduleManager.getScheduleByUserCode(userCode, tday,1,8);
		request.setAttribute("psList", psList);

		
		//4.集体活动
		/*List<PublicSchedule> pbsList = publicScheduleManager
				.getScheduleByUserCode(tday);
		if (pbsList.size() > 5) {
			pbsList = pbsList.subList(0, 5);
		}*/
		//Modify By WuCF 20131209
		//修改提取数据的方式，直接查询最多8条数据，而不是先查询出所有的数据然后用list.subList(param,param);
		List<PublicSchedule> pbsList = publicScheduleManager.getScheduleByUserCode(tday,1,5);
		request.setAttribute("pbsList", pbsList);

		// 下线生日 ，安置 推荐
		// 1964/2/26
		/*SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		// 推荐网下
		List recommends = jmiRecommendRefManager
				.getJmiRecommendRefsByRecommendNo(userCode);
		List birs = new ArrayList();
		System.out.println(sdf.format(today));
		for (int i = 0; i < recommends.size(); i++) {
			JmiRecommendRef jmiRecommendRef = (JmiRecommendRef) recommends
					.get(i);
			if (jmiRecommendRef.getJmiMember().getBirthday() != null) {
				String sdf1 = sdf.format(jmiRecommendRef.getJmiMember()
						.getBirthday());
				if (sdf1.endsWith(sdf.format(today))) {
					birs.add(jmiRecommendRef.getJmiMember());
				}
			}
			if (jmiRecommendRef.getRecommendJmiMember().getBirthday() != null) {
				String sdf2 = sdf.format(jmiRecommendRef
						.getRecommendJmiMember().getBirthday());
				if (sdf2.endsWith(sdf.format(today))) {
					birs.add(jmiRecommendRef.getJmiMember());
				}
			}
		}

		// 安置网下
		List links = jmiLinkRefManager.getJmiLinkRefByNo(userCode);
		for (int i = 0; i < links.size(); i++) {
			JmiLinkRef jmiLinkRef = (JmiLinkRef) links.get(i);
			if (jmiLinkRef.getLinkJmiMember().getBirthday() != null) {
				String birSdf = sdf.format(jmiLinkRef.getLinkJmiMember()
						.getBirthday());
				if (birSdf.endsWith(sdf.format(today))) {
					birs.add(jmiLinkRef.getJmiMember());
				}
			}
			if (jmiLinkRef.getJmiMember().getBirthday() != null) {
				String birSdf = sdf.format(jmiLinkRef.getJmiMember()
						.getBirthday());
				if (birSdf.endsWith(sdf.format(today))) {
					birs.add(jmiLinkRef.getJmiMember());
				}
			}
		}
		if (birs.size() > 5) {
			birs = birs.subList(0, 5);
		}
		System.out.println("birs: " + birs.size());
		request.setAttribute("birsList", birs);*/

		// 未支付订单
		if(jmiMemberManager.get(userCode).getFreezeStatus()!=1){
			/*List noPays = jpoMemberOrderManager.getMemberOrderNopay(userCode);
			if (noPays.size() > 8) {
				noPays = noPays.subList(0, 8);
			}*/
			//Modify By WuCF 20131209
			//修改提取数据的方式，直接查询最多8条数据，而不是先查询出所有的数据然后用list.subList(param,param);
			List noPays = jpoMemberOrderManager.getMemberOrderNopay(userCode,1,8);
			request.setAttribute("noPays", noPays);
		}
		request.setAttribute("infoEmpty", jmiMemberManager.getIsInfoEmpty(userCode));
		
		//modify by fu 2016-09-29 未支付的自助换货单---------begin
		//最多查询8条数据
		List eoNoList = pdExchangeOrderManager.getPdExchangeOrderByUsercode(userCode,1,8);
		if(null!=eoNoList){
			request.setAttribute("eoNoList", eoNoList);
		}
		//modify by fu 2016-09-29 未支付的自助换货单---------end

		Map ammap=amAnnounceManager.getSearchAnnounceMap(sysUser);	

		GroupPage page = new GroupPage("announce","showAnnounce",5,request);
		List announces = amAnnounceManager.findAnnouncePage(page, ammap);


		request.setAttribute("announceList", announces);
		

		request.setAttribute("tips4565Package", jmiMemberManager.getPromotionTips(sysUser.getUserCode()));
		
		
		// Modify By WuCF 20130830 登录时间存放到session中
		// 页面有需要显示的地方，用下面标签取值即可：
		// <fmt:formatDate value="${loginCurDate }"
		// pattern="yyyy-MM-dd HH:mm:ss"/>
		request.getSession().setAttribute("loginCurDate", new Date());
		
		
		//判断是否存在商户口分配地址
		FiCommonAddr fiCommonAddr=fiCommonAddrManager.get(sysUser.getUserCode());
		
		String easyPwdStr=ListUtil.getListValue(sysUser.getDefCharacterCoding(), "easy.pwd", sysUser.getPassword());
		
		if(!StringUtil.isEmpty(easyPwdStr)){
			request.setAttribute("easyPwdStr", "easyPwdStr");
		}else if((fiCommonAddr==null || jmiMemberManager.getIsInfoEmpty(sysUser.getUserCode()))){
			request.setAttribute("fiCommonAddr", "fiCommonAddr");
		}
		
		//Modify By WuCF 20150408 获得会员所属团队编号		
		String teamCode = jmiTeamManager.teamStr(sysUser);
		request.getSession().setAttribute("teamCode",teamCode);

		if(jmiMember.getMemberLevel().intValue()==500 & StringUtil.isEmpty(jmiMember.getPapernumber())){
			request.setAttribute("papernumberFill", "papernumberFill");
		}

		//Modify By WuCF 20150907 获取是否有股票下载资格
		boolean result = pdSendInfoManager.isGuPiaoUser(userCode);
		if(result){
			request.setAttribute("isGuPiao", "Y");
		}
		
		//Modify By WuCF 20151028 是否有查看瓜藤网的权限
		String guatengFlag = ConfigUtil.getConfigValue("CN", "guateng.flag");
		System.out.println("guatengFlag:"+guatengFlag);
		request.setAttribute("guatengFlag", guatengFlag);
		
		//Modify By dengmh 20160714 是否有港股账号录入权限
		JsysStockAccount jsysStockAccount =jsysStockAccountManager.getJsysStockAccountByUserCode(userCode);
		if(jsysStockAccount!=null){
			request.setAttribute("isStockAccount", "Y");
			request.getSession().setAttribute("isStockAccount", "Y");
		}
		
		//Modify By dengmh 20161019 判断当前会员是否绑定瓜藤网帐号
		boolean isBindGuaTeng = this.checkPhoneNumBind(userCode);
    	request.setAttribute("isBindGuaTeng", isBindGuaTeng);
		
		String[] links = new String[3];
		BigDecimal[] images = new BigDecimal[3];
		List<Map<String,String>> list=pdSendInfoManager.getGuaTenLinks("guaten.images.url");
		int num =0;
		int numTemp = 0;
    	for(Map<String,String> map : list){
    		numTemp = num++;
    		images[numTemp] = new BigDecimal(String.valueOf(map.get("ORDER_NO")));
    		links[numTemp] = map.get("VALUE_TITLE").toString();
    	}
    	String FILE_URL = ListUtil.getListValue("CN", "jpmproductsaleimage.url", "1");
    	request.getSession().setAttribute("FILE_URL", FILE_URL);
    	request.getSession().setAttribute("links", links);
    	request.getSession().setAttribute("images", images);
		

		return "index/index";

	}

	private void saveLogin(HttpServletRequest request, JsysUser sysUser) {

		String ipAddress = RequestUtil.getIpAddr(request);
		JsysLoginLog sysLoginLog = new JsysLoginLog();
		sysLoginLog.setUserCode(sysUser.getUserCode());
		sysLoginLog.setIpAddr(ipAddress);
		sysLoginLog.setOperaterCode(sysUser.getUserCode());
		sysLoginLog.setOperateTime(new Date());
		sysLoginLog.setOperationType("3");
		jsysLoginLogManager.save(sysLoginLog);
	}

	@RequestMapping("/scheduleInfo")
	public String showScheduleDetail(HttpServletRequest request) {
		JsysUser user = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String id = request.getParameter("id");

		scheduleManager.getSchedule(id);
		// scheduleManageform
		return "redirect:/scheduleManageform.html?id=" + id;
	}

	@RequestMapping("/api/showuserinfo")
	@ResponseBody
	public Map showMemberInfoForM(HttpServletRequest request,
			HttpServletResponse response) {
		Map map = new HashMap();
		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		JsysUser jSysUser = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(jSysUser, "Map");
		if(null!=object){
			return (Map)object;
		}
		getValuePV(request, jSysUser,map);
		map.put("userName", jSysUser.getUserName());


		JbdPeriod bdPeriod = periodManger.getBdPeriodByTime(new java.util.Date());
		String bdWeek = bdPeriod.getFyear()+ ""+ (bdPeriod.getFweek() < 10 ? "0" + bdPeriod.getFweek(): bdPeriod.getFweek());

		map.put("bdMonth", bdPeriod.getFmonth());
		map.put("bdWeek", bdWeek);
		//手机提示
		try {
			map.put("memberPV10", "");
			map.put("memberFreeze1", "");
			map.put("memberFreeze", "");
			map.put("activeinfo", "");
			map.put("tips4565Package", jmiMemberManager.getPromotionTips(jSysUser.getUserCode()));
			getTips(request, jSysUser,map);
			
			map.put("level", 0 );
			map.put("nexLevel", 0);
			map.put("curReSal", 0);
			map.put("needReSal", 0);
			map.put("region", 0);
			map.put("needRegion", 0);
			map.put("checkStime", 0);

			map.put("bigRegion", 0);
			map.put("needBigRegion", 0);
			map.put("multipleRegion", 0);
			map.put("multipleLevel",0);
			map.put("Month_Group_Pv", 0);
			map.put("cardType", 0);
			map.put("member_level", 0);
			map.put("next_member_level",0);
			map.put("pre_pv", 0);
			map.put("cur_total_pv", 0);
			map.put("cur_month_add_pv", 0);
			map.put("need_pv", 0);
			map.put("system_Upgrade_Day", 0);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//最近两个财月
		String aasql="select  aa from (select f_Year || Lpad(f_Month, 2, 0) as aa  from jbd_period t where t.start_time < sysdate  group by  f_Year || Lpad(f_Month, 2, 0)    order by f_Year || Lpad(f_Month, 2, 0)  desc"
			+ ") where rownum <=2";
		List recentlyList = this.jdbcTemplate3.queryForList(aasql);
		String week1= recentlyList.size()>=1?((Map)recentlyList.get(0)).get("aa").toString():"";
		String week2= recentlyList.size()>=2?((Map)recentlyList.get(1)).get("aa").toString():null;
		map.putAll(jmiMemberManager.getBonsuStar(week1,"0","1",jSysUser));
		map.putAll(jmiMemberManager.getBonsuStar(week2,"0","2",jSysUser));
		return map;
	}

	/**
	 * 获取各种业绩信息
	 * 
	 * @param request
	 * @param userCode
	 */
	private void getValuePV(HttpServletRequest request, JsysUser jSysUser,Map indexMap ) {

        String doubleView = ConfigUtil.getConfigValue(jSysUser.getCompanyCode().toUpperCase(), "double.view");
        request.setAttribute("doubleView", doubleView);
        
        
        
		//Map indexMap=new HashMap();
		
        
        
		
		//获取未读公告数
		Map ammap=amAnnounceManager.getSearchAnnounceMap(jSysUser);	
		int notRead = amAnnounceManager.countNotReadAnnounce(ammap);
		indexMap.put("notRead", notRead);

		int noReadReplyNum= amMessageManager.getNoReadReply(jSysUser.getUserCode(), jSysUser.getCompanyCode(),null);
		indexMap.put("notReadMesSize", noReadReplyNum);
		
		
		//最近两个财月
		String aasql="select  aa from (select f_Year || Lpad(f_Month, 2, 0) as aa  from jbd_period t where t.start_time < sysdate  group by  f_Year || Lpad(f_Month, 2, 0)    order by f_Year || Lpad(f_Month, 2, 0)  desc"
				+ ") where rownum <=2";
		List recentlyList = this.jdbcTemplate3.queryForList(aasql);
		request.setAttribute("recentlyList", recentlyList);
		

		List list = this.jdbcTemplate3.queryForList("select to_char(start_time,'yyyy-mm-dd hh24:mi:ss') start_time,to_char(end_time-1,'yyyy-mm-dd')|| ' 23:59:59' end_time "
				+ "from jbd_period a where a.start_time <= sysdate and a.end_time > sysdate");
		if(!list.isEmpty()){
			Map map=(Map) list.get(0);
			indexMap.put("startTime", map.get("start_time"));
			indexMap.put("endTime", map.get("end_time"));
		}
		
		request.setAttribute("indexMap", indexMap);
		//return indexMap;
		
	}

	
	private void getTips(HttpServletRequest request, JsysUser defSysUser,Map indexMap)
			throws Exception {
		log.info("get tips ");
		if(queryNycQualify(defSysUser.getUserCode())){
			log.info("get tips strNYCQualify");
			request.setAttribute("strNYCQualify", "Y");
			request.getSession().setAttribute("strNYCQualify", "Y");
		}

		if (request.getSession().getAttribute("tips") == null) {
			if ("M".equals(defSysUser.getUserType())) {
				

				this.saveLogin(request, defSysUser);
				request.setAttribute("notice", "notice");
				JbdPeriod cuBdPeriod = bdPeriodManager
						.getBdPeriodByTime(new Date());

				String wyear = cuBdPeriod.getWyear()
						+ ""
						+ (cuBdPeriod.getWmonth() < 10 ? "0"
								+ cuBdPeriod.getWmonth() : cuBdPeriod
								.getWmonth());
				String startDate = null;
				String endDate = null;

				String notTipsMemberStr = "CN10111767,CN10716695,CN12420821,CN12898280,CN13234245,CN13767892,CN15127332,CN17162209,CN17969214,CN19117719,CN19506487,CN19586220,CN21305849,CN21736826,CN23770918,CN32448960,CN33177159,CN33964954,CN35436309,CN39249753,CN39946324,CN41578183,CN41914148,CN43920921,CN45149899,CN46053558,CN47086094,CN50286556,CN52594654,CN53806311,CN55651283,CN55731860,CN60337189,CN64084584,CN66797927,CN70012697,CN71307943,CN73979900,CN74053562,CN77634454,CN79744255,CN83812440,CN84041158,CN84331187,CN85606100,CN86512772,CN91670213,CN91793911,CN92504790,CN97613480,CN99455440,CN99456088,CN11250891,CN98817038,CN17978514,CN86677808,CN89404186,CN13761602,CN25207240,CN14583255,CN15009737,CN14843039,CN12642515,CN59803060,CN20927321,CN10040104,CN13193334,CN18766575,CN77911511,CN70585804,CN21449995,CN19278434,CN14094272,CN72480232,CN13819542,CN12736898,CN84010762,CN17678496,CN13493338,CN97416865,CN17074043,CN12048204,CN15650495,CN36537943,CN30821124,CN13454735,CN78214050,CN83105421";

				if (cuBdPeriod != null) {

					// 先判断是否存在全年重消订单
					boolean flag = false;
					List list3 = this.jdbcTemplate3
							.queryForList("select min(start_time) as start_time, max(end_time) as end_time "
									+ "from jbd_period where w_year = "
									+ cuBdPeriod.getWyear());
					if (!list3.isEmpty()) {
						startDate = DateUtil.convertDateToString(DateUtil
								.convertStringToDate("yyyy-MM-dd HH:mm:ss",
										((Map) list3.get(0)).get("start_time")
												.toString()));
						endDate = DateUtil.convertDateToString(DateUtil
								.convertStringToDate("yyyy-MM-dd HH:mm:ss",
										((Map) list3.get(0)).get("end_time")
												.toString()));
						int pvAmt=3024;
						
/*		        		String loninTreeIndex=defSysUser.getJmiMember().getJmiRecommendRef().getTreeIndex();
		        		
		            	JmiMember qdMiMember = jmiMemberManager.get("CN40449939");
		            	if(qdMiMember!=null ){
		            		String tsTreeIndex=qdMiMember.getJmiRecommendRef().getTreeIndex();
		            		String indexTmp = StringUtil.getLeft(loninTreeIndex, tsTreeIndex.length());
		            		if(loninTreeIndex.length() >= tsTreeIndex.length() && indexTmp.equals(tsTreeIndex)){
		            			pvAmt=130;
		            		}
		            	}
		            	JmiMember ty4MiMember = jmiMemberManager.get("CN55092684");
		            	if(ty4MiMember!=null ){
		            		String tsTreeIndex=ty4MiMember.getJmiRecommendRef().getTreeIndex();
		            		String indexTmp = StringUtil.getLeft(loninTreeIndex, tsTreeIndex.length());
		            		if(loninTreeIndex.length() >= tsTreeIndex.length() && indexTmp.equals(tsTreeIndex)){
		            			pvAmt=130;
		            		}
		            	}*/
						
						
/*						String sql3="select check_date from jpo_member_order where order_type in ('4') and user_code = '"+defSysUser.getUserCode()+"' " +
								"and company_code = '"+defSysUser.getCompanyCode()+"' and pv_amt >= "+pvAmt+"  and status='2' and is_retreat_order='0' order by check_date desc";*/
		            	
		            	String sql3="select check_date from  jpo_member_order where mo_id in (select a.mo_id from jpo_member_order           a, "
		            			+ "jpo_member_order_list      b, JPM_PRODUCT_SALE_TEAM_TYPE c, JPM_PRODUCT_SALE_NEW       d where a.mo_id = b.mo_id "
		            			+ "and b.product_id = c.ptt_id and a.user_code = '"+defSysUser.getUserCode()+"' and c.uni_no = d.uni_no and d.product_no not in ('P17010200101CN0', 'P17010300101CN0') "
		            			+ "and a.status = '2' and a.is_retreat_order = '0'  and a.company_code = '"+defSysUser.getCompanyCode()+"'  and a.order_type in ('4') group by a.mo_id "
		            			+ "having nvl(sum(b.pv*b.qty),0) >="+pvAmt+") order by check_date desc";
						
						
						
						
						
						
						
						List list4 = this.jdbcTemplate3.queryForList(sql3);

						if (!list4.isEmpty()) {
							Map map=(Map) list4.get(0);
							if(map.get("check_date")!=null){
								String check_date=map.get("check_date").toString();
								JbdPeriod checkDatebdPeriod=bdPeriodManager.getBdPeriodByTime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", check_date), null);
								String period = bdPeriodManager.getFutureBdYearMonth(checkDatebdPeriod.getWyear().toString(), checkDatebdPeriod.getWmonth().toString(), 13);
								if(StringUtil.formatInt(wyear)<=StringUtil.formatInt(period)){
									flag=true;
									if(pvAmt==3276){
										List endDateList = this.jdbcTemplate3.queryForList("select TO_CHAR(max(end_time-1), 'yyyy-MM-dd') as end_time from Jbd_Period where concat(w_year, Lpad(w_month, 2, 0))=" +period);
										if(!endDateList.isEmpty()){
											Map datemap=(Map) endDateList.get(0);
											if(datemap.get("end_time")!=null){
												request.setAttribute("tips3276", datemap.get("end_time"));
											}
										}
									}
								}
							}
						}

					}
					//

					List list2 = this.jdbcTemplate3
							.queryForList("select min(start_time) as start_time, max(end_time) as end_time "
									+ "from jbd_period where concat(w_year, lpad(w_month, 2, 0)) = "
									+ wyear + " ");

					if (!list2.isEmpty() && !flag && defSysUser.getJmiMember().getCheckDate()!=null) {
						

						JbdPeriod miBdPeriod=bdPeriodManager.getBdPeriodByTime(defSysUser.getJmiMember().getCheckDate(), null);
						String mi_wyear= miBdPeriod.getWyear()+""+ (miBdPeriod.getWmonth()<10?"0" + miBdPeriod.getWmonth():miBdPeriod.getWmonth());
						
						if(StringUtil.formatInt(wyear)>StringUtil.formatInt(mi_wyear)){
							startDate = DateUtil.convertDateToString(DateUtil
									.convertStringToDate("yyyy-MM-dd HH:mm:ss",
											((Map) list2.get(0)).get("start_time")
													.toString()));
							endDate = DateUtil.convertDateToString(DateUtil
									.convertStringToDate("yyyy-MM-dd HH:mm:ss",
											((Map) list2.get(0)).get("end_time")
													.toString()));

							/*String sql = "select nvl(sum(pv_amt),0) as pv_amt from jpo_member_order where "
									+ "check_date >= to_date('"
									+ startDate
									+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
									+ "and check_date < to_date('"
									+ endDate
									+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
									+ "and order_type in ('4','9','14') "
									+ "and user_code='"
									+ defSysUser.getUserCode()
									+ "' and company_code='"
									+ defSysUser.getCompanyCode() + "' ";
							List list1 = this.jdbcTemplate3.queryForList(sql);

							if (!notTipsMemberStr
									.contains(defSysUser.getUserCode())) {

								if (!list1.isEmpty()) {
									String pv_amt = ((Map) list1.get(0)).get(
											"pv_amt").toString();
									if (StringUtil.isDouble(pv_amt)) {
										if (StringUtil.formatDouble(pv_amt) < 10) {
											request.setAttribute("memberPV10",
													"memberPV10");
											//手机提示
											indexMap.put("memberPV10", LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "order.10.chongxiao"));
										}
									}

								}
							}*/
						}
						
						

					}
				}

				//青奥门票
				List jmiTickets=jmiTicketManager.getJmiTicketByUserCode(defSysUser.getUserCode());
				if(!jmiTickets.isEmpty() ){
					boolean flag=false;
					String tickedType="";
					for (int i = 0; i < jmiTickets.size(); i++) {
						JmiTicket jmiTicket=(JmiTicket)jmiTickets.get(i);
						if(StringUtil.isEmpty(jmiTicket.getCensusAddress())){
							if("1".equals(jmiTicket.getTicketType())){
								tickedType="开";
							}else if("2".equals(jmiTicket.getTicketType())){
								tickedType="闭";
							}
							
							flag=true;
							break;
						}
					}
					if(flag){
						tickedType = MessageFormat.format("     亲爱的家人，您已获得公司“我要上青奥” 活动的参与资格，将获得南京青奥会{0}幕式门票2张，请您点击本窗口，填写参加人员相关信息。本次青奥会{0}幕式门票采取实名制，并需经相关部门政审。{0}幕式当天凭票及身份证核对入场，如出席人、身份证与此次报名信息不符将无法入场。还请家人们按照实际出席活动的人员信息如实填写。\\n\\r“我要上青奥”活动报名截止日期为6月18日24:00，请您尽快完善您的报名信息，并确保准确性。",new String[] { tickedType });
						request.setAttribute("tickedType", tickedType);
					}
				}
				
				//
				
				
				
				
			}

			// 如果会员银行资料不完全，提示补充完整
			if ("M".equals(defSysUser.getUserType())) {
				if (StringUtil.isEmpty(defSysUser.getJmiMember().getBank())
						|| StringUtil.isEmpty(defSysUser.getJmiMember()
								.getBankbook())
						|| StringUtil.isEmpty(defSysUser.getJmiMember()
								.getBankcard())) {
					request.setAttribute("bankinfo", "bankinfo");
				}
/*				if(StringUtil.isEmpty(defSysUser.getJmiMember().getClAddress())){
					request.setAttribute("clAddress", "clAddress.tips");
				}*/
				// 如为待审会员，并且在14天内未支付，提示
				int activeTime = StringUtil
						.formatInt((String) Constants.sysConfigMap.get(
								defSysUser.getCompanyCode()).get("active_time"));
				Date activeTimeDate = defSysUser.getJmiMember().getActiveTime();
				if (new Date().after(DateUtil.getDateOffset(activeTimeDate, 5,
						activeTime))) {
					activeTime = 0;
				} else {
					activeTime = DateUtil.daysBetweenDates(DateUtil
							.getDateOffset(activeTimeDate, 5, activeTime),
							new Date());
				}
				String activeinfo = MessageFormat.format(LocaleUtil
						.getLocalText("activeinfo.member"),
						new Integer[] { activeTime });
				if (defSysUser.getJmiMember().getNotFirst()==0
						&& activeTime != 0) {
					request.setAttribute("activeinfo", activeinfo);
					//手机提示
					indexMap.put("activeinfo", activeinfo);
				}
				try {
					/*String sql = "Select flag From jmi_member Where member_type ='1' and flag='1' and user_code='"
							+ defSysUser.getJmiMember().getUserCode() + "'";*/
					//Modify By WuCF 20140703 绑定变量
					StringBuffer sql = new StringBuffer("Select flag From jmi_member Where member_type ='1' and flag='1' and user_code=?");
					StringBuffer paramsBuf = new StringBuffer(","+defSysUser.getJmiMember().getUserCode());
					
					//返回时查询的方法 
				    Object[] parameters = paramsBuf.toString().substring(1).split(",");
					List miMembers =jdbcTemplate3.queryForList(sql.toString(),parameters); 
					if (miMembers.size() > 0) {
						request.setAttribute("ylMemberInfo", "1");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 续约提示
				if ("CN".equals(defSysUser.getCompanyCode())
						&& null != defSysUser.getJmiMember().getValidWeek()
						&& defSysUser.getJmiMember().getGradeType().intValue()!=0) {
					boolean daysflag = false;
					int days = 0;
					JbdPeriod endBdPeriod = null;
					Integer validWeek = defSysUser.getJmiMember()
							.getValidWeek();
					if (validWeek != null && validWeek.toString().length() == 6) {
						List list = bdPeriodManager.getBdPeriodsByMonth(
								validWeek.toString().substring(0, 4), validWeek
										.toString().substring(4,
												validWeek.toString().length()));
						if (list != null) {
							endBdPeriod = (JbdPeriod) list.get(list.size() - 1);
							days = DateUtil.daysBetween(new Date(),endBdPeriod.getEndTime() );
							if (days <= 60 && days > 0) {
								daysflag = true;
							}
						}
					}
					String notTipsMemberStr = "CN64084584,CN10716695,CN41578183,CN50286556,CN55731860,CN97613480,CN55651283,CN70012697,CN53806311,CN83812440,CN66797927,CN21305849,CN77634454,CN91670213,CN17162209,CN23770918,CN84331187,CN71307943,CN39946324,CN21736826,CN19117719,CN32448960,CN92504790,CN91793911,CN52594654,CN84041158,CN35436309,CN19586220,CN86512772,CN99455440,CN10111767,CN12898280,CN33177159,CN85606100,CN99456088,CN74053562,CN47086094,CN41914148,CN13234245,CN79744255,CN73979900,CN46053558,CN45149899,CN33964954,CN39249753,CN43920921,CN13767892,CN17969214,CN19506487,CN12420821,CN60337189,CN15127332,CN20474358,CN38323488,CN13717634,CN18310026,CN18243407,CN15090165,CN96233029,CN83900978,CN18660277,CN14608168,CN11824008,CN10464346,CN10617844,CN10729883,CN11172032,CN11452263,CN11559009,CN11585153,CN11907956,CN12058869,CN12083524,CN12251194,CN12560662,CN12631305,CN12782804,CN13373545,CN14009631,CN14453844,CN14716319,CN14741550,CN14815994,CN14823937,CN14906272,CN15080894,CN15090496,CN15137534,CN15501681,CN15626010,CN15706464,CN15739402,CN15784794,CN15791776,CN15815471,CN16031161,CN16136630,CN16395227,CN16437501,CN16618897,CN16635190,CN16662165,CN16715985,CN17061791,CN17180134,CN17372626,CN17505030,CN17593683,CN17720449,CN17754204,CN17926873,CN17980773,CN18229484,CN18556467,CN18671898,CN19491179,CN19775103,CN19895242,CN19957084,CN20438681,CN20609518,CN20659232,CN20906596,CN20944994,CN20961593,CN21015637,CN21184741,CN21404193,CN22300305,CN22610462,CN24307902,CN25741947,CN26087322,CN26793752,CN29829710,CN30449556,CN30547247,CN31684160,CN32516700,CN32866520,CN35039296,CN36138860,CN36918962,CN44506917,CN44934472,CN47812866,CN48935974,CN49342538,CN49347849,CN49408282,CN49986061,CN51335852,CN51747631,CN52627657,CN52813747,CN54000276,CN54480278,CN54753826,CN54821871,CN55236159,CN57290735,CN58556088,CN59718127,CN60375630,CN63528318,CN63857537,CN66050801,CN70859695,CN71819204,CN76880668,CN82553736,CN85066224,CN85305624,CN86533116,CN90911563,CN91354778,CN91708384,CN96159424,CN96949931,CN97994687,CN98385538,CN99336145,CN99864423,CN10104602,CN10455546,CN10457760,CN10560478,CN10758040,CN10943495,CN11943289,CN12591322,CN12857054,CN13205653,CN14253927,CN14303708,CN14440025,CN14865421,CN15369885,CN15506468,CN16330225,CN16403834,CN17004367,CN17042229,CN17498593,CN17600476,CN18121010,CN18713244,CN19604348,CN20238913,CN20304214,CN20564641,CN20808430,CN20904861,CN22201758,CN23827598,CN25132811,CN31881950,CN37016679,CN39110534,CN39416036,CN42208491,CN42601175,CN50349950,CN50510780,CN54596689,CN54687372,CN55559359,CN58162095,CN59638085,CN61988886,CN62562318,CN64879226,CN65615472,CN66026879,CN66723009,CN67190599,CN68002152,CN70297237,CN75187970,CN75883696,CN84663089,CN94772815,CN97926295,CN11439675,CN12017065,CN12154532,CN12741413,CN13111598,CN14780313,CN15200042,CN15219237,CN15589273,CN15729928,CN18814381,CN19745762,CN20202260,CN21332947,CN23754927,CN26392321,CN28010527,CN41186142,CN44194131,CN45162671,CN47625909,CN62817847,CN69863518,CN73470757,CN74876512,CN78164812,CN78482132,CN80350845,CN89492252,CN92358415,CN11896462,CN17045887,CN23394553,CN84567065,CN96787198,CN11250891,CN98817038,CN17978514,CN86677808,CN89404186,CN13761602,CN25207240,CN14583255,CN15009737,CN14843039,CN12642515,CN59803060,CN20927321,CN10040104,CN13193334,CN16052242,CN34381389,CN13601547,CN81002295,CN16177580,CN79701821,CN14979831,CN65395655,CN18129096,CN88735101,CN18911055,CN74364867,CN14094085";
					if (!notTipsMemberStr.contains((defSysUser.getUserCode()))) {
						if (defSysUser.getJmiMember().getFreezeStatus() == 1) {
							int offDays = DateUtil.daysBetween(new Date(),endBdPeriod.getEndTime());
							int offDaysDivisible = offDays / 365;
							offDaysDivisible = offDaysDivisible + 1;
							String msgMemberFreeze1 = MessageFormat
									.format(
											LocaleUtil
													.getLocalText("memberFreeze.tips1"),
											new Integer[] { 300 });
							request.setAttribute("memberFreeze1",msgMemberFreeze1);

							//手机提示
							indexMap.put("memberFreeze1", LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "memberFreeze.tips1","",new Integer[]{300}));
						} else {
							if (daysflag) {
								
								List miMembers =jdbcTemplate3.queryForList("select user_code from jbd_member_frozen where user_code='"+defSysUser.getUserCode()+"'"); 
								if(miMembers.isEmpty()){
									request.setAttribute("memberFreeze", days);
									//手机提示
									indexMap.put("memberFreeze", LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "memberFreeze.tips","",new Integer[]{days}));
								
								}
							}

						}
					}
				}
				
				
				if("1".equals(defSysUser.getJmiMember().getIsstore())){
					JmiStore  jmiStore=jmiStoreManager.getJmiStoreByUserCode(defSysUser.getUserCode());
					if(jmiStore!=null && !"1".equals(jmiStore.getConfirmStatus())){
						request.setAttribute("jmiStoreAddrTips", "jmiStoreAddrTips");
					}
				}
				
				
				
				//
				// 二级生活馆地址未审核提示
				if ("CN".equals(defSysUser.getCompanyCode())) {
					if ("2".equals(defSysUser.getJmiMember()
							.getSubStoreStatus())) {
						JmiSubStore jmiSubStore = jmiSubStoreManager
								.getJmiSubStoreByUserCode(defSysUser
										.getUserCode());
						if (jmiSubStore != null
								&& (!"1".equals(jmiSubStore.getAddrCheck()) || !"1"
										.equals(jmiSubStore.getAddrConfirm()))) {
							request.setAttribute("addrTips", "addrTips");
						} else if (jmiSubStore != null
								&& "1".equals(jmiSubStore.getAddrConfirm())
								&& ("0"
										.equals(jmiSubStore
												.getBusinessLicense())
										|| "0"
												.equals(jmiSubStore
														.getContract()) || "0"
										.equals(jmiSubStore.getStorePic()))) {
							request.setAttribute("othersTips", "othersTips");
						}
					}

/*					JsysUserRole sysUserRole = jsysUserRoleManager
							.getSysUserRoleByUserCode(defSysUser.getUserCode());
					if (sysUserRole != null
							&& sysUserRole.getRoleId() == 951589) {
						request.setAttribute("jmiStoreTips", "jmiStoreTips");
					}*/
				}
				//
			}
			
			
			//两个月内优惠顾客升级会员提示 尊敬的家人您好！您可在XX日内通过个人重消（大于等于2200PV）升级成为会员，逾期将无法升级！
			if(defSysUser.getJmiMember().getGradeType()==1 && defSysUser.getJmiMember().getCheckDate()!=null ){
				JbdPeriod miBdPeriod=bdPeriodManager.getBdPeriodByTime(defSysUser.getJmiMember().getCheckDate(), null);
				String month=bdPeriodManager.getFutureBdYearMonth(miBdPeriod.getWyear().toString(), miBdPeriod.getWmonth().toString(), 3);
				

				List list = this.jdbcTemplate3.queryForList("Select max(end_time) as end_time  From Jbd_Period Where Concat(w_Year, Lpad(w_Month, 2, 0)) = "+month);
				
				String end_time=((Map) list.get(0)).get("end_time").toString();
				
				Date endTime=DateUtil.convertStringToDate(end_time);
				
				int days = DateUtil.daysBetween(new Date(),endTime);
				if(days>0){
					String tips=LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), "member1.upgradetips","",new Integer[]{days});
					//request.setAttribute("upgradetipsTips", tips);//改制bug:3355
				}
			}
			
			//25号后判断准奖衔会员
			Date date =new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if(calendar.get(Calendar.DAY_OF_MONTH)>=25){
				JbdPeriod miBdPeriod=bdPeriodManager.getBdPeriodByTime(new Date(), null);
				String mi_wyear= miBdPeriod.getWyear()+""+ (miBdPeriod.getWweek()<10?"0" + miBdPeriod.getWweek():miBdPeriod.getWweek());
				String sql="select t.user_code, t.pass_star from jbd_day_bouns_calc t where t.pass_star > 20 and t.w_week = "+mi_wyear+" and t.user_code='"+defSysUser.getUserCode()+"'";
				List list =jdbcTemplate3.queryForList(sql);
				if(!list.isEmpty()){
					Map map=(Map) list.get(0);
					String passStar=map.get("pass_star").toString();
					if(passStar.length()==2){
						String passStarStr=ListUtil.getListValue(defSysUser.getDefCharacterCoding(), "pass.star.zero", passStar.substring(passStar.length()-1));
						request.setAttribute("passStarStrTips", "尊敬的会员：您本月已达成"+passStarStr+"奖衔大小区业绩考核，请尽快完成您的个人重消，以确保达成合格奖衔。此为实时业绩重消提醒，仅作参考。");
					}
				}
			}
			
			
			
			//查询会员是否参与过爱心365公益基金活动
			if ("M".equals(defSysUser.getUserType())) {
				
				//SysUser loginUser=SessionLogin.getLoginUser(request);
				//查询当前会员订单
				List tempList=foundationOrderManager.getOrdersByItemTypeAndTime(defSysUser.getUserCode());
				if(tempList.size()<1){
					
					//提示信息
					request.setAttribute("str365FTitle", "Y");
				}
				//没支付，依然提示
				if(tempList.size()>0){
					
					FoundationOrder tempOrder=(FoundationOrder) tempList.get(0);
					if(("0").equals(tempOrder.getStatus()))
						//提示信息
						request.setAttribute("str365FTitle", "Y");
				}
				
				
				//身份证地址归属地是：  江苏、杭州、沈阳注册的会员要求在会员下过首单之后且订单完成三个月内出提示语
				JmiMember temp = defSysUser.getJmiMember();
				String city = temp.getCity();
				String province = temp.getProvince();
//				'杭州市','温州市','台州市','宁波市','武汉市','西安市','济南市','广州市','福州市','泉州市','厦门市','沈阳市','哈尔滨市'
//				String []citys = {"163707","163709","163712","163712","163712","163712","163714","163714","163714","163716","163718","163720","163728"};
				
				LinkedHashMap<String, String> configMap =null;
		        try {
		            configMap = ListUtil.getListOptions(defSysUser.getCompanyCode(), "mescitys");
		        }catch(Exception e){
		        	configMap = null;
		        }
		        
				String cs = "";
		        for(String key : configMap.keySet()) {
		        	cs += key + ",";
		        }
				if (!"".equals(cs)) {
					String[] citys = cs.split(",");
					if (MeteorUtil.useList(citys, city)
							|| "163711".equals(province)) {
						// if("18357".equals(city)||"17843".equals(city)||"163711".equals(province)){
						// 首购单的审核时间
						String firstOrderStatusTime = jpoMemberOrderManager
								.getMemberCheckDate(temp.getUserCode());
						// 加上三个月与当前系统时间比较
						SimpleDateFormat sf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Calendar c = Calendar.getInstance();
						c.setTime(sf.parse(firstOrderStatusTime));
						c.add(Calendar.MONTH, 3);
						Date d = c.getTime();
						Date current = new Date();
						if (current.before(d)) {
							// 弹出提示
							String specialTips = MessageFormat
									.format("各位家人：\\n根据国家相关法律法规规定，要求直销区域营销人员须以直销员的身份进行业务运作。为响应国家政策，故请直销区域(江苏省、杭州市、温州市、台州市、宁波市、武汉市、西安市、济南市、广州市、福州市、泉州市、厦门市、沈阳市、哈尔滨市)的营销人员须配合公司加入直销员体系建设中，您已符合公司要求资格。请至所属分公司完成直销员的办理事项。具体详情咨询当地所属分公司。",
											"");
							request.setAttribute("specialTips", specialTips);
						}
					}
				}
			}
			//会员是否有未收货，提示：  Modify By WuCF
			//如果有未发货信息，则首页会提示。
			int pdSendInfoUnRead = pdSendInfoManager.isExistNotConfirm(defSysUser.getUserCode());
			request.getSession().setAttribute("pdSendInfoUnRead", pdSendInfoUnRead);
			
			request.getSession().setAttribute("tips", "tips");
		}

	}

/*	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("invok onSubmit !");
		String userCode = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		// String verifyCode = request.getParameter("verifyCode");
		// String loginTool = request.getParameter("loginTool");
		log
				.info("userCode is:[" + userCode + "] and pwd is:[" + password
						+ "]");

		try {
			// JsysUser user = userManger.getUserByPwd(userCode, password);
			JsysUser user = (JsysUser) jsysUserManager
					.loadUserByUsername(userCode);
			log.info("user is:" + user.getUserCode());
			request.setAttribute("userCode", userCode);
			if (user.getUserType().equalsIgnoreCase("M")) {
				// 全年重消提示
				JbdPeriod bdPeriod = periodManger.getBdPeriodByTime(new Date());
				log.info("bdPeriod is:" + bdPeriod);
				String pv = reOrderPv(bdPeriod, user);
				if (StringUtil.isDouble(pv)) {
					if (StringUtil.formatDouble(pv) < 10) {
						request.setAttribute("memberPV10", "memberPV10");
					}
				}

				JmiMember member = user.getJmiMember();
				if (StringUtil.isEmpty(member.getBank())
						|| StringUtil.isEmpty(member.getBankbook())
						|| StringUtil.isEmpty(member.getBankcard())) {
					request.setAttribute("bankinfo", "bankinfo");
				}

				// 如为待审会员，并且在14天内未支付，提示
				int activeTime = StringUtil
						.formatInt((String) com.joymain.ng.Constants.sysConfigMap
								.get(user.getCompanyCode()).get("active_time"));

				Date activeTimeDate = member.getActiveTime();
				if (new Date().after(DateUtil.getDateOffset(activeTimeDate, 5,
						activeTime))) {
					activeTime = 0;
				} else {
					activeTime = DateUtil.daysBetweenDates(DateUtil
							.getDateOffset(activeTimeDate, 5, activeTime),
							new Date());
				}
				String activeinfo = MessageFormat.format(LocaleUtil
						.getLocalText("activeinfo.member"),
						new Integer[] { activeTime });

				if ("0".equals(member.getCardType()) && activeTime != 0) {
					request.setAttribute("activeinfo", activeinfo);
				}
				try {
					String sql = "Select flag From jmi_member Where member_type ='1' and flag='1' "
							+ "and user_code='" + member.getUserCode() + "'";
					List miMembers = this.jdbcTemplate.queryForList(sql);
					if (miMembers.size() > 0) {
						request.setAttribute("ylMemberInfo", "1");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 续约提示
				if ("CN".equals(user.getCompanyCode())
						&& null != member.getValidWeek()
						&& !"0".equals(member.getCardType())) {
					boolean daysflag = false;
					int days = 0;
					JbdPeriod endBdPeriod = null;
					Integer validWeek = member.getValidWeek();
					System.out.println("validWeek is:[" + validWeek + "]");

					if (validWeek != null && validWeek.toString().length() == 6) {
						List<JbdPeriod> list = bdPeriodManager
								.getBdPeriodsByMonth(validWeek.toString()
										.substring(0, 4), validWeek.toString()
										.substring(4,
												validWeek.toString().length()));

						if (list != null) {
							endBdPeriod = (JbdPeriod) list.get(list.size() - 1);
							days = DateUtil.daysBetweenDates(endBdPeriod
									.getEndTime(), new Date());
							days = days - 1;
							if (days > 0 && days <= 56) {
								daysflag = true;
							}
						}
					}

					String notTipsMemberStr = "CN64084584,CN10716695,CN41578183,CN50286556,CN55731860,CN97613480,CN55651283,CN70012697,CN53806311,CN83812440,CN66797927,CN21305849,CN77634454,CN91670213,CN17162209,CN23770918,CN84331187,CN71307943,CN39946324,CN21736826,CN19117719,CN32448960,CN92504790,CN91793911,CN52594654,CN84041158,CN35436309,CN19586220,CN86512772,CN99455440,CN10111767,CN12898280,CN33177159,CN85606100,CN99456088,CN74053562,CN47086094,CN41914148,CN13234245,CN79744255,CN73979900,CN46053558,CN45149899,CN33964954,CN39249753,CN43920921,CN13767892,CN17969214,CN19506487,CN12420821,CN60337189,CN15127332,CN20474358,CN38323488,CN13717634,CN18310026,CN18243407,CN15090165,CN96233029,CN83900978,CN18660277,CN14608168,CN11824008,CN10464346,CN10617844,CN10729883,CN11172032,CN11452263,CN11559009,CN11585153,CN11907956,CN12058869,CN12083524,CN12251194,CN12560662,CN12631305,CN12782804,CN13373545,CN14009631,CN14453844,CN14716319,CN14741550,CN14815994,CN14823937,CN14906272,CN15080894,CN15090496,CN15137534,CN15501681,CN15626010,CN15706464,CN15739402,CN15784794,CN15791776,CN15815471,CN16031161,CN16136630,CN16395227,CN16437501,CN16618897,CN16635190,CN16662165,CN16715985,CN17061791,CN17180134,CN17372626,CN17505030,CN17593683,CN17720449,CN17754204,CN17926873,CN17980773,CN18229484,CN18556467,CN18671898,CN19491179,CN19775103,CN19895242,CN19957084,CN20438681,CN20609518,CN20659232,CN20906596,CN20944994,CN20961593,CN21015637,CN21184741,CN21404193,CN22300305,CN22610462,CN24307902,CN25741947,CN26087322,CN26793752,CN29829710,CN30449556,CN30547247,CN31684160,CN32516700,CN32866520,CN35039296,CN36138860,CN36918962,CN44506917,CN44934472,CN47812866,CN48935974,CN49342538,CN49347849,CN49408282,CN49986061,CN51335852,CN51747631,CN52627657,CN52813747,CN54000276,CN54480278,CN54753826,CN54821871,CN55236159,CN57290735,CN58556088,CN59718127,CN60375630,CN63528318,CN63857537,CN66050801,CN70859695,CN71819204,CN76880668,CN82553736,CN85066224,CN85305624,CN86533116,CN90911563,CN91354778,CN91708384,CN96159424,CN96949931,CN97994687,CN98385538,CN99336145,CN99864423,CN10104602,CN10455546,CN10457760,CN10560478,CN10758040,CN10943495,CN11943289,CN12591322,CN12857054,CN13205653,CN14253927,CN14303708,CN14440025,CN14865421,CN15369885,CN15506468,CN16330225,CN16403834,CN17004367,CN17042229,CN17498593,CN17600476,CN18121010,CN18713244,CN19604348,CN20238913,CN20304214,CN20564641,CN20808430,CN20904861,CN22201758,CN23827598,CN25132811,CN31881950,CN37016679,CN39110534,CN39416036,CN42208491,CN42601175,CN50349950,CN50510780,CN54596689,CN54687372,CN55559359,CN58162095,CN59638085,CN61988886,CN62562318,CN64879226,CN65615472,CN66026879,CN66723009,CN67190599,CN68002152,CN70297237,CN75187970,CN75883696,CN84663089,CN94772815,CN97926295,CN11439675,CN12017065,CN12154532,CN12741413,CN13111598,CN14780313,CN15200042,CN15219237,CN15589273,CN15729928,CN18814381,CN19745762,CN20202260,CN21332947,CN23754927,CN26392321,CN28010527,CN41186142,CN44194131,CN45162671,CN47625909,CN62817847,CN69863518,CN73470757,CN74876512,CN78164812,CN78482132,CN80350845,CN89492252,CN92358415,CN11896462,CN17045887,CN23394553,CN84567065,CN96787198";
					if (!notTipsMemberStr.contains((user.getUserCode()))) {
						if (member.getFreezeStatus() == 1) {
							int offDays = DateUtil.daysBetweenDates(new Date(),
									endBdPeriod.getEndTime());
							int offDaysDivisible = offDays / 365;
							offDaysDivisible = offDaysDivisible + 1;
							String msgMemberFreeze1 = MessageFormat
									.format(
											LocaleUtil
													.getLocalText("memberFreeze.tips1"),
											new Integer[] { 252 });
							request.setAttribute("memberFreeze1",
									msgMemberFreeze1);
						} else {
							if (daysflag) {
								request.setAttribute("memberFreeze", days);
							}
						}
					}
				}
				//
				// 二级生活馆地址未审核提示
				if ("CN".equals(user.getCompanyCode())) {
					if ("2".equals(member.getSubStoreStatus())) {

						JmiSubStore jmiSubStore = jmiSubStoreManager
								.getJmiSubStoreByUserCode(user.getUserCode());

						if (jmiSubStore != null
								&& (!"1".equals(jmiSubStore.getAddrCheck()) || !"1"
										.equals(jmiSubStore.getAddrConfirm()))) {
							request.setAttribute("addrTips", "addrTips");
						} else if (jmiSubStore != null
								&& "1".equals(jmiSubStore.getAddrConfirm())
								&& ("0"
										.equals(jmiSubStore
												.getBusinessLicense())
										|| "0"
												.equals(jmiSubStore
														.getContract()) || "0"
										.equals(jmiSubStore.getStorePic()))) {
							request.setAttribute("othersTips", "othersTips");
						}
					}

					JsysUserRole sysUserRole = userRoleManager
							.getSysUserRoleByUserCode(user.getUserCode());
					if (sysUserRole != null
							&& sysUserRole.getRoleId() == 951589) {
						request.setAttribute("jmiStoreTips", "jmiStoreTips");
					}
				}
			}
		} catch (Exception e) {
			log.error(userCode + " login error!", e);
			return new ModelAndView("cancelView");
		}
		System.out.println("over !~~!");
		return new ModelAndView("welcome");
	}

	private String reOrderPv(JbdPeriod bdPeriod, JsysUser user)
			throws ParseException {

		String startDate = "", endDate = "";
		// String wyear= bdPeriod.getWyear()+""+ (bdPeriod.getWmonth()<10?"0" +
		// bdPeriod.getWmonth():bdPeriod.getWmonth());
		String wyear = "2013";
		String notTipsMemberStr = "CN10111767,CN10716695,CN12420821,CN12898280,CN13234245,CN13767892,CN15127332,CN17162209,CN17969214,CN19117719,CN19506487,CN19586220,CN21305849,CN21736826,CN23770918,CN32448960,CN33177159,CN33964954,CN35436309,CN39249753,CN39946324,CN41578183,CN41914148,CN43920921,CN45149899,CN46053558,CN47086094,CN50286556,CN52594654,CN53806311,CN55651283,CN55731860,CN60337189,CN64084584,CN66797927,CN70012697,CN71307943,CN73979900,CN74053562,CN77634454,CN79744255,CN83812440,CN84041158,CN84331187,CN85606100,CN86512772,CN91670213,CN91793911,CN92504790,CN97613480,CN99455440,CN99456088,CN11250891,CN98817038,CN17978514,CN86677808,CN89404186,CN13761602,CN25207240,CN14583255,CN15009737,CN14843039,CN12642515,CN59803060,CN20927321,CN10040104,CN13193334,CN18766575,CN77911511,CN70585804,CN21449995,CN19278434,CN14094272,CN72480232,CN13819542,CN12736898,CN84010762,CN17678496,CN13493338,CN97416865,CN17074043,CN12048204,CN15650495,CN36537943,CN30821124,CN13454735,CN78214050,CN83105421";

		// 先判断是否存在全年重消订单
		boolean flag = false;
		String reOrderSql = "select min(start_time) as start_time, max(end_time) as end_time "
				+ "from jbd_period where w_year = " + wyear;
		List list3 = this.jdbcTemplate.queryForList(reOrderSql);

		System.out.println("sql is:" + reOrderSql);

		if (!list3.isEmpty()) {
			log.info("list3 size is:" + list3.size() + " and value:["
					+ list3.get(0) + "]");
			startDate = DateUtil.convertDateToString(DateUtil
					.convertStringToDate("yyyy-MM-dd HH:mm:ss", ((Map) list3
							.get(0)).get("start_time").toString()));
			endDate = DateUtil.convertDateToString(DateUtil
					.convertStringToDate("yyyy-MM-dd HH:mm:ss", ((Map) list3
							.get(0)).get("end_time").toString()));

			log
					.info("startDate is:" + startDate + " and endDate is:"
							+ endDate);

			String sql3 = "select nvl(sum(pv_amt),0) as pv_amt from jpo_member_order where "
					+ "check_date >= to_date('"
					+ startDate
					+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
					+ "and check_date < to_date('"
					+ endDate
					+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
					+ "and order_type in ('4') "
					+ "and user_code='"
					+ user.getUserCode()
					+ "' and company_code='"
					+ user.getCompanyCode() + "' ";

			List list4 = this.jdbcTemplate.queryForList(sql3);

			if (!list4.isEmpty()) {
				String pv_amt = ((Map) list4.get(0)).get("pv_amt").toString();
				if (StringUtil.isDouble(pv_amt)) {
					if (StringUtil.formatDouble(pv_amt) > 3276) {
						flag = true;
					}
				}
			}
		}

		String list_sql = "select min(start_time) as start_time, max(end_time) as end_time "
				+ "from jbd_period where concat(w_year, lpad(w_month, 2, 0)) = "
				+ wyear + " ";

		list_sql = "select min(start_time) as start_time, max(end_time) as end_time "
				+ "from jbd_period where w_year = " + wyear + " ";

		List list2 = this.jdbcTemplate.queryForList(list_sql);

		System.out.println("list2 size is:" + list2.size() + " and flag is:"
				+ flag);
		System.out.println("list2 sql is:" + list_sql);

		if (!list2.isEmpty() && !flag) {
			System.out.println("list2 get0 is:" + list2.get(0));
			startDate = DateUtil.convertDateToString(DateUtil
					.convertStringToDate("yyyy-MM-dd HH:mm:ss", ((Map) list2
							.get(0)).get("start_time").toString()));
			endDate = DateUtil.convertDateToString(DateUtil
					.convertStringToDate("yyyy-MM-dd HH:mm:ss", ((Map) list2
							.get(0)).get("end_time").toString()));

			String sql = "select nvl(sum(pv_amt),0) as pv_amt from jpo_member_order where "
					+ "check_date >= to_date('"
					+ startDate
					+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
					+ "and check_date < to_date('"
					+ endDate
					+ " 00:00:00', 'yyyy-mm-dd hh24:mi:ss')"
					+ "and order_type in ('4','9','14') "
					+ "and user_code='"
					+ user.getUserCode()
					+ "' and company_code='"
					+ user.getCompanyCode() + "' ";

			System.out.println("=====sql is:" + sql);
			List list1 = this.jdbcTemplate.queryForList(sql);

			if (!notTipsMemberStr.contains(user.getUserCode())) {
				System.out.println("list1 size is:" + list1.size()
						+ " and list1 value:" + list1.get(0));
				if (!list1.isEmpty()) {
					return ((Map) list1.get(0)).get("pv_amt").toString();
				}
			}
		}

		return null;
	}*/

	public JbdPeriodManager getPeriodManger() {
		return periodManger;
	}

	public void setPeriodManger(JbdPeriodManager periodManger) {
		this.periodManger = periodManger;
	}



	public JbdPeriodManager getBdPeriodManager() {
		return bdPeriodManager;
	}

	public void setBdPeriodManager(JbdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public JmiSubStoreManager getJmiSubStoreManager() {
		return jmiSubStoreManager;
	}

	public void setJmiSubStoreManager(JmiSubStoreManager jmiSubStoreManager) {
		this.jmiSubStoreManager = jmiSubStoreManager;
	}

	public JsysUserRoleManager getUserRoleManager() {
		return userRoleManager;
	}

	public void setUserRoleManager(JsysUserRoleManager userRoleManager) {
		this.userRoleManager = userRoleManager;
	}

	public AmNewManager getAmNewManager() {
		return amNewManager;
	}

	public void setAmNewManager(AmNewManager amNewManager) {
		this.amNewManager = amNewManager;
	}

	public PublicScheduleManager getPublicScheduleManager() {
		return publicScheduleManager;
	}

	public void setPublicScheduleManager(
			PublicScheduleManager publicScheduleManager) {
		this.publicScheduleManager = publicScheduleManager;
	}

	public AmAnnounceManager getAmAnnounceManager() {
		return amAnnounceManager;
	}

	public void setAmAnnounceManager(AmAnnounceManager amAnnounceManager) {
		this.amAnnounceManager = amAnnounceManager;
	}

	public AmAnnounceRecordManager getAmAnnounceRecordManager() {
		return amAnnounceRecordManager;
	}

	public void setAmAnnounceRecordManager(
			AmAnnounceRecordManager amAnnounceRecordManager) {
		this.amAnnounceRecordManager = amAnnounceRecordManager;
	}

	public void setFoundationOrderManager(
			FoundationOrderManager foundationOrderManager) {
		this.foundationOrderManager = foundationOrderManager;
	}
	
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	private boolean queryNycQualify(String userCode){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date now=new Date();
		Calendar now30 = Calendar.getInstance();
		now30.setTime(now);
		now30.add(Calendar.DAY_OF_MONTH,30);

		String sql="SELECT count(*) FROM JPO_MEMBER_NYC WHERE (to_date('"+sdf.format(now)+"','yyyy-mm-dd') between PUSH_AT and (PUSH_AT+30) ) AND status='0' AND MEMBER_NO=?";
		int count = jdbcTemplate3.queryForInt(sql, userCode);
		log.info("sql:"+sql);
		log.info("get "+userCode+" nyc qualify count:"+count);
		if(count>0){
			return true;
		}
		return  false;
	}
	
	/**
     * 判断会员是否绑定了瓜藤网手机号
     * @param userCode
     * @return
     */
    public boolean checkPhoneNumBind(String userCode){
		String sql = "select count(*) from jmi_member where user_code='"+userCode+"' and (ec_mall_phone is null or ec_mall_phone='')";
		int count = this.jdbcTemplate3.queryForInt(sql);
		if(count==0){
			return true;
		}
		return false;
	}
}
