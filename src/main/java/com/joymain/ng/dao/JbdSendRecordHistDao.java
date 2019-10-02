package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JbdSendRecordHist;

/**
 * An interface that provides a data management interface to the JbdSendRecordHist table.
 */
public interface JbdSendRecordHistDao extends GenericDao<JbdSendRecordHist, Long> {
	
	/**
	 * 查询明细－－创业销售奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getVentureSalesPv(String userCode, String wweek);

	/**
	 * 查询明细－－创业领导奖01--jbdMemberLinkCalcHist.ventureFund
	 * @author Administrator
	 * @param userCode
	 * @param integer
	 */
	List getVentureLeaderPvOne(String userCode, String wweek, String string);


	/**
	 * 查询明细－－成功销售奖---页面中没有该明细
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getSuccessSaleBonus(String userCode, String wweek);

	/**
	 * 查询明细－－加盟店店补
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 * @param string
	 */
	List getFranchises(String userCode, String wweek, String string);

	/**
	 * VENTURE_FUND 查询明细－－扶持奖( 创业基金)---jbdMemberLinkCalcHist.ventureFund
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getVentureFundPv(String userCode, String wweek);

	/**
	 * 查询明细－－推荐奖金
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getJbdSellCalcRecommendBouns(String userCode, String wweek);

	/**
	 * 查询明细－－店铺拓展奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getStoreExpandPv(String userCode, String wweek);

	/**
	 * 查询明细－－店铺服务奖 
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getStoreServePv(String userCode, String wweek);

	/**
	 * 查询明细－－店铺推荐奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getStoreRecommendPv(String userCode, String wweek);

	/**
	 * 查询明细－－创业销售奖－－查询明细－－创业销售奖明细查询
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	List getJbdSellCalcSubDetailHist(String userCode, String wweek);
	public List getJbdSuccessLeaderPv(String userCode, String wweek);
	/**
	 * 代数奖明细
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getSuccessLeaderPvDetail(String userCode,String wweek,String passStar,String startDate,String endDate,String algebra);
	public JbdSendRecordHist getJbdSendRecordHistForUpdate( Long id);


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
	public List getVentureLeaderDetail(String userCode, String wweek);

	/**
	 * 推广奖
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getPopularizeBonusPv(String userCode, String wweek);
}