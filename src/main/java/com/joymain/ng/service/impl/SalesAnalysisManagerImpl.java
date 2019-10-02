package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.SalesAnalysisDao;
import com.joymain.ng.model.AmMessage;
import com.joymain.ng.service.SalesAnalysisManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("salesAnalysisManager")
@WebService(serviceName = "SalesAnalysisService", endpointInterface = "com.joymain.ng.service.SalesAnalysisManager")
public class SalesAnalysisManagerImpl extends GenericManagerImpl<AmMessage, Long> implements SalesAnalysisManager {
	SalesAnalysisDao salesAnalysisDao;

    @Autowired
    public SalesAnalysisManagerImpl(SalesAnalysisDao salesAnalysisDao) {
        super(salesAnalysisDao);
        this.salesAnalysisDao = salesAnalysisDao;
    }
	
	
    /**
	 * 1.获得指定区域的会员信息
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberInfo(String province,String companyCode,String userCode,String startLogTime,String endLogTime) {
		return salesAnalysisDao.getJmiMemberInfo(province,companyCode,userCode,startLogTime,endLogTime);
	}
	
	/**
	 * 2.成员活跃度分析
	 * @day：日期
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberActive(String saleFlag2,String companyCode,String userCode,String startLogTime,String endLogTime) {
		return salesAnalysisDao.getJmiMemberActive(saleFlag2,companyCode,userCode,startLogTime,endLogTime);
	}
	
	/**
	 * 3.团队新增会员
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param year：年份
	 * @param userType：会员类型
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberNewTeams(String companyCode,String userCode,String year,String userType) {
		return salesAnalysisDao.getJmiMemberNewTeams(companyCode,userCode,year,userType);
	}
	
	/**
	 * 4.团队新增会员
	 * @year:年份
	 * @return 返回List集合
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberPoMemberOrder(String companyCode,String userCode,String startLogTime,String endLogTime,String type) {
		return salesAnalysisDao.getJmiMemberPoMemberOrder(companyCode,userCode,startLogTime,endLogTime,type);
	}
	
	/**
	 * 5.商品分析
	 * @param companyCode：分公司
	 * @param userCode：用户编号
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberProduct(String companyCode,String userCode,String startLogTime,String endLogTime) {
		return salesAnalysisDao.getJmiMemberProduct(companyCode,userCode,startLogTime,endLogTime);
	}
	
	/**
	 * 6.销售冠军
	 * @year:用户编码
	 * @return 返回List集合
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberChampion(String userCode,String startLogTime,String endLogTime) {
		return salesAnalysisDao.getJmiMemberChampion(userCode,startLogTime,endLogTime);
	}
}