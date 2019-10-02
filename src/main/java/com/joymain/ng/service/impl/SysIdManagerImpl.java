package com.joymain.ng.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.SysIdDao;
import com.joymain.ng.model.SysId;
import com.joymain.ng.service.SysIdManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.data.CommonRecord;


@Service("sysIdManager")
public class SysIdManagerImpl extends GenericManagerImpl<SysId, Long> implements SysIdManager {
	private SysIdDao sysIdDao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	@Autowired
	public void setSysIdDao(SysIdDao sysIdDao) {
		this.sysIdDao = sysIdDao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#getSysIds(com.joymain.jecs.sys.model.SysId)
	 */
	public List getSysIds(final SysId sysId) {
		return sysIdDao.getSysIds(sysId);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#getSysId(String id)
	 */
	public SysId getSysId(final String id) {
		return sysIdDao.getSysId(new Long(id));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#saveSysId(SysId sysId)
	 */
	public void saveSysId(SysId sysId) {
		sysIdDao.saveSysId(sysId);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#removeSysId(String id)
	 */
	public void removeSysId(final String id) {
		sysIdDao.removeSysId(new Long(id));
	}

	/**
	 * 根据ID获取对应的序列器
	 * @param idCode
	 * @return
	 */
	public SysId getSysIdByCode(final String idCode) {
		return sysIdDao.getSysIdByCode(idCode);
	}

	/**
	 * 取得一个对应表的唯一主键
	 * @param table 指定的数据表
	 * @param idName 指定对应表的那个字段
	 * @return String =""表示未取得唯一主键
	 */

	public String buildIdStr(final String idCode) {
		if (idCode == null) {
			throw new AppException("idCode is empty!");
		}
		Map resultMap=sysIdDao.callProcBuildIdStr(idCode);
		if(resultMap==null || resultMap.get("p_out_code")==null){
			log.info("过程调用失败");
			throw new AppException("过程调用失败!");
		}
		
		return resultMap.get("p_out_code").toString();
	}
	
	/**
	 * 换货单和发货退回单生成发货单时发货单编号用新的函数
	 * @author fu 2016-03-17 
	 * @param idCode
	 * @return 
	 */
	public String buildIdStrTwo(final String idCode){
		if (idCode == null) {
			throw new AppException("idCode is empty!");
		}
		Map resultMap=sysIdDao.callProcBuildIdStrTwo(idCode);
		if(resultMap==null || resultMap.get("p_out_code")==null){
			log.info("过程调用失败");
			throw new AppException("过程调用失败!");
		}
		
		return resultMap.get("p_out_code").toString();
	}
	
	public String getBuildIdStr(String idCode){
		return this.buildIdStr(idCode);
	}

	/**
	 * 根据外部参数分页获取对应的序列器列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysIdsByPage(CommonRecord crm, Pager pager) {
		return sysIdDao.getSysIdsByPage(crm, pager);
	}
}
