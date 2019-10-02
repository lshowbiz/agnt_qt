package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FiTransferFundbook;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the FiTransferFundbook table.
 */
public interface FiTransferFundbookDao extends GenericDao<FiTransferFundbook, Long> {

	public List getFiTransferFundbookListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime);
	
	/**
	 * 保存产业基金转账申请单，并返回ID
	 * @param fiTransferFundbook
	 * @return
	 */
	public Long saveFiTransferFundbookGetId(FiTransferFundbook fiTransferFundbook);
}