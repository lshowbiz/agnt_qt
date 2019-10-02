package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.FiBcoinJournal;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.MiCoinLog;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface FiBcoinJournalManager extends GenericManager<FiBcoinJournal, Long> {
    
	public Pager<FiBcoinJournal> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List<FiBcoinJournal> getFiBcoinJournalListByUser(String userCode,
			String dealStartTime, String dealEndTime,int pageNum,int pageSize);
	
	public List<FiBcoinJournal> getFiBcoinJournalListByUserPage(GroupPage page,String userCode,
			String dealStartTime, String dealEndTime);
	
	/**
	 * 存入资金至用户帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate,String dataType);
	
	/**
	 * 从用户帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @throws AppException 存款不够的错误
	 */
	public void saveJfiBankbookDeduct(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final Long[] appId,String dataType) throws AppException;
	
	/**
	 * 积分推送至商城
	 * @param userCode
	 * @param bcoinNum
	 */
	public MiCoinLog sendBcoinsToShangCheng(JsysUser sysUser,BigDecimal bcoinNum,String mallType,String sendNo) throws AppException;
}