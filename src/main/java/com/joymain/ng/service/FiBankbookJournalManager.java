package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.JfiPosImport;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface FiBankbookJournalManager extends GenericManager<FiBankbookJournal, Long> {
    
	public Pager<FiBankbookJournal> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List<FiBankbookJournal> getFiBankbookJournalListByUser(String userCode, String dealStartTime, String dealEndTime, int pageNum, int pageSize);
	
	/**
	 * 分页展示
	 * @param userCode
	 * @param dealStartTime
	 * @param dealEndTime
	 * @return
	 */
	public List<FiBankbookJournal> getFiBankbookJournalListByUserPage(GroupPage page,String userCode, String dealStartTime, String dealEndTime);

	
	/**
	 * 存入资金至用户帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param bankbookType 存折类别
	 * @param dataType 数据来源,1:PC, 2:手机
	 */
	public void saveFiPayDataVerify(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType,String dataType) throws Exception;

	/**
	 * 从发展基金帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param bankbookType 存折类别
	 * @param dataType 数据来源,1:PC, 2:手机
	 * @throws AppException 存款不够的错误
	 */
	public void saveFiBankbookDeduct(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType,String dataType) throws Exception;
	
	/**
	 * POS数据进存折
	 * @param companyCode
	 * @param sysUser
	 * @param operaterCode
	 * @param operaterName
	 * @param jfiPosImport
	 */
	public void saveJfiPayDataVerifyByPosImport(final String companyCode, final JsysUser sysUser, String operaterCode, final String operaterName,
			JfiPosImport jfiPosImport) throws Exception;
	
}