package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JbdSendRecordHist;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface JbdSendRecordHistManager extends GenericManager<JbdSendRecordHist, Long> {
    
	public Pager<JbdSendRecordHist> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
    //查询明细－－创业销售奖
	public List getVentureSalesPv(String userCode, String wweek);
	
	//查询明细－－创业领导奖01--jbdMemberLinkCalcHist.ventureFund 成功领导奖02
	public List getVentureLeaderPvOne(String userCode, String wweek,
			String string);
	//查询明细的明细
	public List getVentureLeaderDetail(String userCode, String wweek);

	//SUCCESS_SALES_PV 查询明细－－成功销售奖---页面中没有该明细
	public List getSuccessSaleBonus(String userCode, String wweek);
	
	//FRANCHISE_MONEY 查询明细－－加盟店店补
	public List getFranchises(String userCode, String wweek, String string);
	
	//VENTURE_FUND 查询明细－－扶持奖( 创业基金)---jbdMemberLinkCalcHist.ventureFund
	public List getVentureFundPv(String userCode, String wweek);
	
	//查询明细－－推荐奖金
	public List getJbdSellCalcRecommendBouns(String userCode, String wweek);
	
	//查询明细－－店铺拓展奖
	public List getStoreExpandPv(String userCode, String wweek);
	
	//查询明细－－店铺服务奖 
	public List getStoreServePv(String userCode, String wweek);
	
	//查询明细－－店铺推荐奖
	public List getStoreRecommendPv(String userCode, String wweek);
	
	//查询明细－－创业销售奖－－查询明细－－创业销售奖明细查询
	public List getJbdSellCalcSubDetailHist(String userCode, String wweek);
	public List getJbdSuccessLeaderPv(String userCode, String wweek);
	/**
	 * 代数奖明细
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getSuccessLeaderPvDetail(String userCode,String wweek,String passStar,String startDate,String endDate,String algebra);
	public void saveInFiBook(JsysUser defSysUser,String id,String type);
	public void saveInDevFiBook(JsysUser defSysUser,String id) throws Exception;
	public void saveJbdSendType(String id,String sendType,JsysUser defSysUser) throws Exception;
	public JbdSendRecordHist getJbdSendRecordHistByUserCodeByWeek(String userCode,String wweek);
	public List getVentureLeaderPvDetail(String userCode,String startDate,String endDate );
	public List getServicePv(String userCode, String wweek);
	/**
	 * 报单奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getbdjPv201607(String userCode, String wweek);

	public List getServicePv201607(String userCode, String wweek) ;

	/**
	 * 推广奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getPopularizeBonusPv(String userCode, String wweek);
}