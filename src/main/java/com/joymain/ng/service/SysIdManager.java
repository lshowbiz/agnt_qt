package com.joymain.ng.service;
import java.util.List;

import com.joymain.ng.model.Role;
import com.joymain.ng.model.SysId;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.data.CommonRecord;


public interface SysIdManager extends GenericManager<SysId, Long> {
	public final int DATE_FORMAT_no = 0;
	public final int DATE_FORMAT_yyyy = 1;
	public final int DATE_FORMAT_yyyyMM = 2;
	public final int DATE_FORMAT_yyyyMMdd = 3;
	public final int DATE_FORMAT_yyMMdd = 4;
	public final int ID_FIXED = 1;
	public final int ID_FIXED_NO = 0;

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#getSysIds(com.joymain.jecs.sys.model.SysId)
	 */
	public List getSysIds(final SysId sysId);

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#getSysId(String id)
	 */
	public SysId getSysId(final String id);

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#saveSysId(SysId sysId)
	 */
	public void saveSysId(SysId sysId);

	/**
	 * @see com.joymain.jecs.sys.service.SysIdManager#removeSysId(String id)
	 */
	public void removeSysId(final String id);

	/**
	 * 根据ID获取对应的序列器
	 * @param idCode
	 * @return
	 */
	public SysId getSysIdByCode(final String idCode);

	/**
	 * 取得一个对应表的唯一主键
	 * @param table 指定的数据表
	 * @param idName 指定对应表的那个字段
	 * @return int =-1表示未取得唯一主键
	 */
	//public long buildId(final String idCode);

	/**
	 * 取得一个对应表的唯一主键
	 * @param table 指定的数据表
	 * @param idName 指定对应表的那个字段
	 * @return String =""表示未取得唯一主键
	 */

	public String buildIdStr(final String idCode);

	/**
	 * 取得一个对应表的唯一主键
	 * @param table 指定的数据表
	 * @param idName 指定对应表的那个字段
	 * @param number 要取多少个ID值
	 * @return int[] =null表示未取得唯一主键
	 */
	//public long[] buildMultiId(final String idCode, int number);

	/**
	 * 取得一个对应表的唯一主键
	 * @param table 指定的数据表
	 * @param idName 指定对应表的那个字段
	 * @param number 要取多少个ID值
	 * @return int[] =null表示未取得唯一主键
	 */
	//public String[] buildMultiIdStr(final String idCode, int number);

	/**
	 * 根据外部参数分页获取对应的序列器列表
	 * @param crm
	 * @param pager
	 * @return
	 */
	public List getSysIdsByPage(CommonRecord crm, Pager pager) ;
	
	public String getBuildIdStr(String idCode);
	
	/**
	 * 换货单和发货退回单生成发货单时发货单编号用新的函数
	 * @author fu 2016-03-17 
	 * @param idCode
	 * @return 
	 */
	public String buildIdStrTwo(final String idCode);

	
	
}