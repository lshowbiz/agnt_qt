package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.AmAnnounce;
import com.joymain.ng.model.AmAnnounceRecord;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.AmAnnounceManager;
import com.joymain.ng.service.AmAnnouncePermitManager;
import com.joymain.ng.service.AmAnnounceRecordManager;
import com.joymain.ng.service.AmMessageManager;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.ConvertUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
@RequestMapping("/announce")
public class AnnounceController extends BaseFormController{
	private final Log log = LogFactory.getLog(AnnounceController.class);
	@Autowired
	private AmMessageManager amMessageManager;
	@Autowired
	private AmAnnounceManager amAnnounceManager;
	@Autowired
	private AmAnnounceRecordManager amAnnounceRecordManager;
	@Autowired
	private JsysUserManager jsysUserManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private AmAnnouncePermitManager amAnnouncePermitManager;
	
	/*************************/
	@Autowired
	private JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager;
	
	@Autowired
	private JfiBankbookJournalManager JfiBankbookJournalManager;
	
	@Autowired
	private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
	
	@RequestMapping("/showAnnounce")
	public String showAnnounce(HttpServletRequest request,HttpServletResponse response){
		JsysUser user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String annSub = request.getParameter("annSub");
		String anStatus = request.getParameter("anStatus");
		String annoClassNo = request.getParameter("annoClassNo");
		String stime = request.getParameter("stime"); 
		String etime = request.getParameter("etime");
		
		Map<String,String> map = new HashMap<String, String>();
		map=amAnnounceManager.getSearchAnnounceMap(user);
		if(StringUtils.isNotBlank(annSub)){
			map.put("annSub", annSub);
		}
		if(StringUtils.isNotBlank(anStatus)){
			map.put("anStatus", anStatus);
		}
		if(StringUtils.isNotBlank(annoClassNo)){
			map.put("annoClassNo", annoClassNo);
		}
		if(StringUtils.isNotBlank(stime)){
			map.put("stime", stime);
		}
		if(StringUtils.isNotBlank(etime)){
			map.put("etime", etime);
		}
		//----------------------Modify By WuCF 添加分页展示功能 
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
		
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("announce","showAnnounce?1=1&pageSize="+pageSize,pageSize,request);
		 
		
		//设置记录总条数，需要根据以前查询的数据集合重载一个方法，查询满足条件的所有数据的条数。
		//page.setPagecount(amAnnounceManager.findAnnounceCount(map));
		
		//获得当前页码对应的数据集合：传递当前页码和分页单位
		List<AmAnnounce> announceList = amAnnounceManager.findAnnouncePage(page, map);//集合数据
		
		request.setAttribute("page", page);//将分页信息加入到request作用域中
		request.setAttribute("announceList", announceList);
		
		//page.pageInfo
		//转到jsp页面后，page对象中的pageinfo为展示到页面最下的“首页、上一页、下一页”： 
		
		return "announceList";
	}
	
	@RequestMapping("/detailinfo")
	public String showAnnouceDetail(HttpServletRequest request){
		JsysUser user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String aaNo = request.getParameter("aaNo");
		log.info("aaNo is:"+aaNo);
		AmAnnounce an = amAnnounceManager.getAnnounceById(aaNo);
		request.setAttribute("announce", an);
		
		AmAnnounceRecord ar = amAnnounceRecordManager.getRecordByUserNo(user.getUserCode(), aaNo);
		if(ar == null){
			ar = new AmAnnounceRecord();
			ar.setAmAnnounce(an);
			ar.setBrowseTime(Calendar.getInstance().getTime());
			ar.setUserNo(user.getUserCode());
			amAnnounceRecordManager.save(ar);
		}
		
		
		return "announceDetail";
	}
	
/*	@RequestMapping("/searchAnnounce")
	public String searchAnnounce(HttpServletRequest request){
		JsysUser user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String annSub = request.getParameter("annSub");
		String anStatus = request.getParameter("anStatus");
		String annoClassNo = request.getParameter("annoClassNo");
		String stime = request.getParameter("stime");
		String etime = request.getParameter("etime");
		
		if(log.isDebugEnabled())
			log.debug("subject :"+annSub+" status :"+anStatus +" " +
				"and classNo :"+annoClassNo +" and stime:"+stime +" and etime is:"+etime);
		
		Map<String,String> map = new HashMap<String, String>();
		if(StringUtils.isNotBlank(annSub)){
			map.put("annSub", annSub);
		}
		if(StringUtils.isNotBlank(anStatus)){
			map.put("anStatus", anStatus);
		}
		if(StringUtils.isNotBlank(annoClassNo)){
			map.put("annoClassNo", annoClassNo);
		}
		if(StringUtils.isNotBlank(stime)){
			map.put("stime", stime);
		}
		if(StringUtils.isNotBlank(etime)){
			map.put("etime", etime);
		}
		
		List<AmAnnounce> announceList = amAnnounceManager.findAnnounceByColum(map);
		bindList(user, announceList);
		request.setAttribute("announceList", announceList);
		return "announceList";
	}*/
	
	
/*	private int  readedList(String userCode,List<AmAnnounce> anList){
		int readedNum=0;
		for(AmAnnounce an:anList){
			an.setAlready(false);
			for(AmAnnounceRecord am : an.getAmAnnounceRecords()){
				if(userCode.equalsIgnoreCase(am.getUserNo())){
					an.setAlready(true);
					readedNum++;
					break;
				}
			}
		}
		return readedNum;
	}*/
	
	/**
	 * 手机显示公告
	 * @param userId
	 * @param token
	 * @param pageNum
	 * @return
	 */
	
	@RequestMapping("/api/mobileAnnounce")
	@ResponseBody
	public List  showMobileAnnounce(String userId,String token,int pageNum,int pageSize,String reply_status,HttpServletRequest request){
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}

		Map<String,String> map = new HashMap<String, String>();
		map=amAnnounceManager.getSearchAnnounceMap(user);
		map.put("reply_status", reply_status);
		GroupPage page = new GroupPage("announce","showAnnounce",pageSize,request);
		
		List announceList = amAnnounceManager.findAnnouncePage(page, map);
		
		return announceList;
	}
	
	/**
	 * 手机公告 标记会员已阅读
	 * @param userId
	 * @param token
	 * @param aaNo
	 * @return
	 */
	@RequestMapping("/api/Announce/detailinfo")
	@ResponseBody
	public String showAnnouceDetailInfo(String userId,String token,String aaNo){
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
		String isRead="0";
		AmAnnounce an = amAnnounceManager.getAnnounceById(aaNo);
		AmAnnounceRecord ar = amAnnounceRecordManager.getRecordByUserNo(user.getUserCode(), aaNo);
		if(ar == null){
			ar = new AmAnnounceRecord();
			ar.setAmAnnounce(an);
			ar.setBrowseTime(Calendar.getInstance().getTime());
			ar.setUserNo(user.getUserCode());
			try {
			amAnnounceRecordManager.save(ar);
			isRead="1";
			}catch (Exception e) {
				isRead="0";
			}
			 
		}
		
		return isRead;
	}
	
	/**
	 * 手机根据aaNo显示公告
	 * @param userId
	 * @param token
	 * @param aaNo
	 * @return
	 */
	
	@RequestMapping("/api/mobileAnnounceByaaNo")
	@ResponseBody
	public Map  showMobileAnnounceByaaNo(String userId,String token,String aaNo){
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "Map");
		if(null!=object){
			return (Map)object;
		}
    	AmAnnounce aa = amAnnounceManager.findAnnounceByaaNo(aaNo);
		return (Map)ConvertUtil.ConvertObjToMap(aa);
	}
}
