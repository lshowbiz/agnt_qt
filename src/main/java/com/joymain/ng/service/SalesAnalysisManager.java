package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.AmMessage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface SalesAnalysisManager extends GenericManager<AmMessage, Long> {
    
	/**
	 * 1.获得指定区域的会员信息
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	public List<Map<String, Object>> getJmiMemberInfo(String province,String companyCode,String userCode,String startLogTime,String endLogTime);
	
	
	/**
	 * 2.成员活跃度分析
	 * @day：日期
	 */
	public List<Map<String, Object>> getJmiMemberActive(String saleFlag2,String companyCode,String userCode,String startLogTime,String endLogTime);
	
	/**
	 * 3.团队新增会员
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param year：年份
	 * @param userType：会员类型
	 * @return
	 */
	public List<Map<String, Object>> getJmiMemberNewTeams(String companyCode,String userCode,String year,String userType);
	
	/**
	 * 4.环比分析
	 * @year:用户编码
	 * @return 返回List集合
	 */
	public List<Map<String, Object>> getJmiMemberPoMemberOrder(String companyCode,String userCode,String startLogTime,String endLogTime,String type);
	
	/**
	 * 5.商品分析
	 * @param companyCode：分公司
	 * @param userCode：用户编号
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	public List<Map<String, Object>> getJmiMemberProduct(String companyCode,String userCode,String startLogTime,String endLogTime);
	
	/**
	 * 6.销售冠军
	 * @year:用户编码
	 * @return 返回List集合
	 */
	public List<Map<String, Object>> getJmiMemberChampion(String userCode,String startLogTime,String endLogTime);
		
}