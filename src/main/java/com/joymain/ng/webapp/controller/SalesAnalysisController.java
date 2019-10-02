package com.joymain.ng.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.SalesAnalysisManager;
 
@Controller
public class SalesAnalysisController {
	private final Log log = LogFactory.getLog(SalesAnalysisController.class);
	private SalesAnalysisManager salesAnalysisManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private JalCityManager jalCityManager;
	private JalStateProvinceManager jalStateProvinceManager;

	/**
     * show message page
     * @param request
     * @param response
     * @return string
     */
    @RequestMapping(value="/salesAnalysisShow",method=RequestMethod.GET)
    public String showMessage(HttpServletRequest request,HttpServletResponse response){
    	JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userCode = defSysUser.getUserCode();
		String companyCode = defSysUser.getCompanyCode();
		
		//查询年份
		int yearTmp = 2000;
    	int yearC = new Date().getYear()+1900;
    	List yearList = new ArrayList();
    	for (;yearTmp<=yearC;yearC--){
    		yearList.add(yearC);
    	}
    	request.setAttribute("yearList", yearList);
		
    	//子菜单资源编码和二级子菜单
    	String saleFlag = request.getParameter("saleFlag");
    	String saleFlag2 = request.getParameter("saleFlag2");
    	log.info("userCode is:"+userCode);
    	
    	//查询的起始、结束日期
    	String startLogTime = request.getParameter("startLogTime");
    	String endLogTime = request.getParameter("endLogTime");
    	String showFlag = request.getParameter("showFlag");
    	String search = request.getParameter("search");
    	String month = request.getParameter("month");//财政月
    	String type = request.getParameter("type");//Modify By WuCF 20140505 统计类型  0：按金额   1：按PV
		String unit = request.getParameter("unit");//环比分析的单位
    	
    	//默认日期
    	SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
		String defaultEndDate = dateformat2.format(new Date());
    	String defaultStartDate = (Integer.parseInt(defaultEndDate.substring(0,4))-1)+defaultEndDate.substring(4);
    	String defaultMonth = defaultEndDate.substring(0,7).replace("-", "");//默认财政月
    	if(StringUtils.isEmpty(startLogTime) || "null".equals(startLogTime)){
    		//环比分析的起始日期设置 
            if("mom".equals(saleFlag)){
        		startLogTime = defaultStartDate.substring(0,4)+"-10-01";//默认从第一财月第一天开始
            }else{//其他统计的起始日期
            	startLogTime = defaultStartDate;
            }            	
        }
        if(StringUtils.isEmpty(endLogTime) || "null".equals(endLogTime)){
        	endLogTime = defaultEndDate;
        }
        if(StringUtils.isEmpty(month) || "null".equals(month)){
        	month = defaultMonth;
        }
        
    	List<Map<String, Object>> list = null;
    	if("Y".equals(search)){//点击“查询”才查询出数据
	    	if("userArea".equals(saleFlag) || StringUtils.isEmpty(saleFlag) || "null".equals(saleFlag)){//1.会员地域分布：默认
	    		if(!"n".equals(showFlag)){
	    			String province = request.getParameter("province");
	    			list = salesAnalysisManager.getJmiMemberInfo(province,companyCode,userCode,startLogTime,endLogTime);
	    		}
	    	}else if("memberActivity".equals(saleFlag)){//2.成员活跃度分析
	    		//日期如果为空，默认不查询，查询记录默认是选择日期前10天
	    		list = salesAnalysisManager.getJmiMemberActive(saleFlag2,companyCode,userCode, startLogTime,endLogTime);
	    		if("logCount".equals(saleFlag2)){
	    			list = this.changeList(list, 8);
	    		}else{
	    			list = this.changeList(list, 8);
	    		}   		
	    	}else if("teamAdd".equals(saleFlag)){//3.团队新增会员
	    		String year = request.getParameter("year");
	    		String userType = request.getParameter("userType");
	    		if(StringUtils.isEmpty(year) || "null".equals(year)){
	    			if(yearList!=null && yearList.size()>=1){
	    				year = yearList.get(0).toString();
	    			}
	    		}
	    		list = salesAnalysisManager.getJmiMemberNewTeams(companyCode,userCode,year,userType);
	        	list = this.changeList(list, 12);
	        	request.setAttribute("year", year);
	        	request.setAttribute("userType", userType);
	    	}else if("mom".equals(saleFlag)){//4.环比分析
	    		if("1".equals(type)){
	    			unit = "PV";
	    		}else{
	    			unit = "RMB";
	    		}
	    		list = salesAnalysisManager.getJmiMemberPoMemberOrder(companyCode,userCode,startLogTime,endLogTime,type);
	    		list = this.changeList(list, 13);
	    	}else if("production".equals(saleFlag)){//5.商品分析
	    		list = salesAnalysisManager.getJmiMemberProduct(companyCode,userCode,startLogTime,endLogTime);
	    		list = this.changeList(list, 8);
	    	}else if("champion".equals(saleFlag)){//6.推荐冠军
	    		list = salesAnalysisManager.getJmiMemberChampion(userCode, startLogTime, endLogTime);
	    		list = this.changeList(list, 10);
	    	}else if("awardTitle".equals(saleFlag)){//7.团队奖衔
	    		String starType = request.getParameter("starType");
				if(StringUtils.isEmpty(starType) || "null".equals(starType)){
					starType = "2";//标示限制，查询所有奖衔
				}
				request.setAttribute("starType", starType); 
				Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				//--查询类型(0：准奖衔；1：合格奖衔 2:ALL)
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_HONOR_ANALY('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(month); 
				sqlBuf.append("','");
				sqlBuf.append(userCode);
				sqlBuf.append("','");
				sqlBuf.append(starType);
				sqlBuf.append("'))");
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						mapT.put("NAME", strTemp[0]);
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
				
	    	}else if("addPromotion".equals(saleFlag)){//8.新增晋级
				Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_HONOR_ANALY_NEWADD('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(month); 
				sqlBuf.append("','");
				sqlBuf.append(userCode); 
				sqlBuf.append("'))");
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						mapT.put("NAME", strTemp[0]);
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
	    	}else if("achievement".equals(saleFlag)){//9.区域业绩分布
	    		String province = request.getParameter("province");
	    		request.setAttribute("province", province);
	    		Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				//--查询类型(0：准奖衔；1：合格奖衔 2:ALL)
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_BOUNS_PROVINCE_ANALY('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(startLogTime);
				sqlBuf.append("','");
				sqlBuf.append(endLogTime);
				sqlBuf.append("','");
				sqlBuf.append(userCode); 
				sqlBuf.append("','");
				if(StringUtils.isNotEmpty(province)&& !"null".equals(province)){
					sqlBuf.append(province); 
				}
				sqlBuf.append("'))");
				
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
//					super.initStateCodeParem(request);
					Map<String,String> provinceMap = new HashMap<String,String>();//省份
					Map<String,String> cityMap = new HashMap<String,String>();//城市
					
					List<JalStateProvince> alStateProvinceMap = jalStateProvinceManager.getJalStateProvinceByCountryCode("CN");//省份
					for(JalStateProvince jsp : alStateProvinceMap){
						provinceMap.put(String.valueOf(jsp.getStateProvinceId()), jsp.getStateProvinceName());
					}
					List<JalCity> alCityList = new ArrayList();
					if(!StringUtils.isEmpty(province) && !"null".equals(province)){
						alCityList = jalCityManager.getAlCityByProvinceId(Long.parseLong(province));
						for(JalCity jc : alCityList){
							cityMap.put(String.valueOf(jc.getCityId()), jc.getCityName());
						}
					} 
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						if(StringUtils.isEmpty(province) || "null".equals(province)){
							mapT.put("NAME", provinceMap.get(strTemp[0]));
							mapT.put("CODE", strTemp[0]);
						}else{
							mapT.put("NAME", provinceMap.get(province)+"|"+cityMap.get(strTemp[0]));
							mapT.put("CODE", strTemp[0]);
						} 
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
	    	}else if("highIncome".equals(saleFlag)){//10.高收入人群
	    		String maxNum = request.getParameter("maxNum");
				String minMoney = request.getParameter("minMoney");
				if(StringUtils.isEmpty(maxNum) || "null".equals(maxNum)){
					maxNum = "50";
				}
				request.setAttribute("maxNum",maxNum);
				Map indexMap=new HashMap();
				//SELECT column_value FROM TABLE(JBD_HONOR_ANALY('CN','2010-01-01','2013-12-01','','2')); 
				//--查询类型(0：准奖衔；1：合格奖衔 2:ALL)
				StringBuffer sqlBuf = new StringBuffer("SELECT column_value FROM TABLE(JBD_BOUNS_TOP_ANALY('");
				sqlBuf.append(companyCode);
				sqlBuf.append("','");
				sqlBuf.append(startLogTime);
				sqlBuf.append("','");
				sqlBuf.append(endLogTime);
				sqlBuf.append("','");
				sqlBuf.append(userCode); 
				sqlBuf.append("','");
				sqlBuf.append(maxNum);
				sqlBuf.append("','");
				sqlBuf.append(minMoney);
				sqlBuf.append("'))");
				List jmiRecommendRefs = this.jdbcTemplate.queryForList(sqlBuf.toString());
				//[{COLUMN_VALUE=0,1622}]
				if(jmiRecommendRefs!=null && jmiRecommendRefs.size()>=1){ 
					//String[] strTemp = map.get("column_value").toString().split(",");
					Map map = null;
					Map mapT = null;
					String[] strTemp = null;
					list = new ArrayList();
					for(Object obj : jmiRecommendRefs){
						map = (Map)obj;
						mapT = new HashMap<String,String>();
						strTemp = map.get("column_value").toString().split(",");
						mapT.put("NAME", strTemp[0]);
						mapT.put("SUM", strTemp[1]);
						list.add(mapT);
					}
				}
	    	}
    	}
    	
    	if(list!=null && list.size()==0){
    		list = null;
    	}
//    	[{NAME=-, SUM=0}, {NAME=-, SUM=0}, {NAME=-, SUM=0}, {NAME=-, SUM=0}, {NAME=-, SUM=0}, {NAME=-, SUM=0}, {NAME=-, SUM=0}, {NAME=-, SUM=0}]
    	Integer num = 0;
    	if(list!=null){
	    	for(Object obj : list){
	    		Map<String,String> map = (Map<String,String>)obj;
	    		if(!"-".equals(map.get("NAME"))){
	    			num++;
	    		}
	    	}
    	}
    	if(num == 0){
    		list = null;
    	}
    	
    	request.setAttribute("list", list);
    	//设置参数
		request.setAttribute("saleFlag", saleFlag); 
    	request.setAttribute("saleFlag2", saleFlag2);
    	request.setAttribute("search", search);
    	request.setAttribute("startLogTime",startLogTime);
    	request.setAttribute("endLogTime",endLogTime);
    	request.setAttribute("month",month);
    	request.setAttribute("unit", unit);
    	request.setAttribute("type", type);
    	return "salesAnalysisList";
    }
    
    
    /**
  	 * 如果集合的数据没有达到指定的Num个，则用空的Map替代，防止页面显示变形
  	 * @param list
  	 * @param num
  	 * @return
  	 */
  	public List<Map<String,Object>> changeList(List<Map<String,Object>> list,Integer num){
  		//空值Map RECOMMEND_NAME=CN42734510, SUM=224
  		Map<String,Object> nullMap = new HashMap<String,Object>();
    	nullMap.put("NAME", "-");
    	nullMap.put("SUM", "0");
    	
  		if(list==null){
  			list = new LinkedList<Map<String,Object>>();
  		}
  		Integer size = list.size();
  		//如果数据个数小于指定，则填充空值
  		for(int i=0;i<num-size;i++){
  			list.add(nullMap);
  		}
  		for(int i=0;i<num-size;i++){
  			if(i>=num){
  				list.remove(i);
  			}
  		}
  		
  		//删除多余的数据
  		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
  		for(int i=0;i<num;i++){
  			returnList.add(list.get(i));
  		}
  		
  		return returnList;
  	}
    
    /**************************getter/setter*****************************/
  	@Autowired
	public void setSalesAnalysisManager(SalesAnalysisManager salesAnalysisManager) {
		this.salesAnalysisManager = salesAnalysisManager;
	}
  	
  	@Autowired
	public void setJalCityManager(JalCityManager jalCityManager) {
		this.jalCityManager = jalCityManager;
	}

  	@Autowired
	public void setJalStateProvinceManager(
			JalStateProvinceManager jalStateProvinceManager) {
		this.jalStateProvinceManager = jalStateProvinceManager;
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
