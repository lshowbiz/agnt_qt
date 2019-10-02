package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.model.JfiChanjetLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.pay.PassbookVO;
@WebService
public interface JfiBankbookJournalManager extends GenericManager<JfiBankbookJournal, Long> {
    
	public Pager<JfiBankbookJournal> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCode(String userCode,String dealStartTime,String dealEndTime);

	/**
	 * web分页
	 * @param userCode
	 * @param dealStartTime
	 * @param dealEndTime
	 * @return
	 */
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime);

	
	/**
	 * Add By WuCF 2013-11-25
	 * 手机分页展示数据   
	 * @param userCode
	 * @param dealStartTime
	 * @param dealEndTime
	 * @return
	 */
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(String userCode,String dealStartTime,String dealEndTime,int pageNum,int pageSize);

	
	/**
	 * 从电子存折帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param dataType 数据来源,1:PC, 2:手机
	 * @throws AppException 存款不够的错误
	 */
	public void saveJfiBankbookDeduct(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, String dataType) throws AppException;
	
	public void saveJfiBankbookDeduct_pay(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, String dataType) throws AppException;
	/**
	 * 扣款操作 返回map值 isPay:扣款成果与否,msg:成功，失败消息
	 * @param vo
	 * @return
	 */
	public Map<String, Object> saveDebitPassBook(PassbookVO vo);
	/**
	 * 快钱数据进存折
	 */
	public BigDecimal saveJfiPayDataVerifyByBill99(final String companyCode, final JsysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, Jfi99billLog jfi99billLog,final BigDecimal fee) throws Exception;
	
	/**
	 * 畅捷通支付数据进存折
	 * @param companyCode
	 * @param sysUser
	 * @param totalMoney
	 * @param operaterCode
	 * @param operaterName
	 * @param uniqueCode
	 * @param jfiChanjetLog
	 * @return
	 * @throws Exception
	 */
	public BigDecimal saveJfiPayDataVerifyByChanjet(final String companyCode, final JsysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiChanjetLog jfiChanjetLog) throws Exception;

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
	 * @throws Exception 
	 */
	public void saveJfiPayDataVerify(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, String dataType) throws Exception;
}