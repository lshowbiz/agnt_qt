package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.FiProductPointJournal;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
@WebService
public interface FiProductPointJournalManager extends GenericManager<FiProductPointJournal, Long> {
	
	public void saveFiProductDeduct_pay(boolean isPayAllByProductPoint,String companyCode, JsysUser sysUser,
			Integer[] moneyType, BigDecimal[] moneyArray, String operaterCode,
			String operaterName, String uniqueCode, String[] notes, String dataType,BigDecimal needPayMoney,String checkType) throws AppException;

	public List<FiProductPointJournal> getJfiProductPointJournalListByUserCodePage(
			GroupPage page, String userCode, String dealStartTime,
			String dealEndTime);

	public List<FiProductPointJournal> getFiProductPointJournalListByUser(String userCode, String dealStartTime, String dealEndTime, int pageNum, int pageSize);
	/**
	 * Add By WuCF 20170526 
	 * 代金券支付提示
	 * @param isPayAllByProductPoint
	 * @param companyCode
	 * @param sysUser
	 * @param moneyType
	 * @param moneyArray
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param notes
	 * @param dataType
	 * @param needPayMoney
	 * @throws AppException
	 */
	public void saveFiCouponDeduct_pay(boolean isPayAllByProductPoint,String companyCode, JsysUser sysUser,
			Integer[] moneyType, BigDecimal[] moneyArray, String operaterCode,
			String operaterName, String uniqueCode, String[] notes, String dataType,BigDecimal needPayMoney,String checkType) throws AppException;
}