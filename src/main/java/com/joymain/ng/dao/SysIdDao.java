package com.joymain.ng.dao;
import java.util.List;
import java.util.Map;

import com.joymain.ng.model.SysId;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.data.CommonRecord;

public interface SysIdDao extends GenericDao<SysId, Long> {
	/**
	 * Retrieves all of the sysIds
	 */
	public List getSysIds(SysId sysId);
	
	/**
	 * Gets sysId's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if 
	 * nothing is found.
	 * 
	 * @param id the sysId's id
	 * @return sysId populated sysId object
	 */
	public SysId getSysId(final Long id);

	/**
	 * Saves a sysId's information
	 * @param sysId the object to be saved
	 */
	public void saveSysId(SysId sysId);

	/**
	 * Removes a sysId from the database by id
	 * @param id the sysId's id
	 */
	public void removeSysId(final Long id);
	
	/**
     * 根据ID获取对应的序列器
     * @param idCode
     * @return
     */
    public SysId getSysIdByCode(final String idCode);
    
    /**
	 * 根据外部参数分页获取对应的序列器列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysIdsByPage(CommonRecord crm, Pager pager);
	
	/**
	 * 执行函数获取对应值
	 * @param idCode
	 * @return
	 */
	public Map callProcBuildIdStr(final String idCode);

	/**
	 * 换货单和发货退回单生成发货单时发货单编号用新的函数
	 * @author fu 2016-03-17 
	 * @param idCode
	 * @return 
	 */
	public Map callProcBuildIdStrTwo(String idCode);
}