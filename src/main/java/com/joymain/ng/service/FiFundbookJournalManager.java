package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.FiFundbookJournal;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FiFundbookJournalManager extends GenericManager<FiFundbookJournal, Long> {
    
	public Pager<FiFundbookJournal> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List<FiFundbookJournal> getFiFundbookJournalListByUserPage(GroupPage page, String userCode, String bankbookType, String dealStartTime,String dealEndTime);
	
	/**
	 * 存入资金至产业基金帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 唯一码，用于防止重复,0为没有
	 * @param bankbookType 基金类型：1，分红基金；2，定向基金
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType, final String dataType);
	
	/**
	 * 从产业基金帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param bankbookType 基金类型：1，分红基金；2，定向基金
	 * @throws AppException 存款不够的错误
	 */
	public void saveFiBankbookDeduct(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType, final String dataType) throws AppException;
}