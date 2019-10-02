package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JmiMember table.
 */
public interface JmiMemberDao extends GenericDao<JmiMember, String> {

	/**
	 * 检查身份证编号是否存在
	 * 
	 * @param miMember  
	 *            存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberIdNoByMiMember(JmiMember miMember) ;

	/**
	 * 检查配偶身份证编号是否存在
	 * 
	 * @param miMember
	 *            存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberSpouseIdNoByMiMember(JmiMember miMember);
	public List getPapernumberUserCode(JmiMember miMember);

	public List getNet(String topUserCode,String netType,String layerId);
	public List getJmiTeamType();
	public List getChildJbdDayBounsCalcBySql(String userCode,String netType, final Integer wweek);
	public Map getJbdDayBounsCalcByUserCode(String userCode, final Integer wweek);
	public JmiRecommendRef getTopIndex(String userCode);
	public List getJmiMembers(Map map);
	
	/**
	 * 会员基本信息－银行信息修改-初始化查询
	 * @author gw 2013-07-02
	 * @param userCode
	 * @return
	 */
	public JmiMember getJmiMemberBankInformation(String userCode);
    
	/**
	 * 会员信息系统－银行信息修改－修改
	 * @author gw 2013-07-03
	 * @param member
	 * @return
	 */
	public void saveJmiMemberBankInformationChange(JmiMember member);

	/**
	 * 会员信息系统－银行信息修改－查出可用的银行
	 * @author gw 2013-07-03
	 * @param companyCode
	 * @return
	 */
	public List getSysBankByCompanyCode(String companyCode);

	/**
	 * 会员系统－个人资料维护－ 查询首购单的审核时间
	 * @author gw 2013-07-08 
	 * @param userCode
	 * @return
	 */
	public String getJmiMemberCheckOrCreateTime(String userCode);

	/**
	 * 会员系统－个人资料维护－ 执行保存或修改的操作
	 * @author gw 2013-07-09
	 * @param miMember
	 * @return 
	 */
	public void savePersonalDataMaintenance(JmiMember miMember);
	
	/**
	 * 忘记登录密码-- 密码重置验证
	 * @param userCode
	 * @param parpeNumber
	 * @param mobiletele
	 */
	public JmiMember checkJmiMemberPwdReset(String userCode, String parpeNumber, String mobiletele);
	
	/**
	 * 随机密码
	 * @param userCode
	 * @param pwd
	 */
	public void updatePwdRandom(JmiMember jmiMember);

	/**
	 * 个人资料维护--获取当前登录用户的身份证号码
	 * @author gw 2013-08-29
	 * @param userCode
	 * @return String
	 */
	public String getPapernumberCheck(String userCode);
    /**
     * 判断ec商场电话号码是否存在
     * @param miMember
     * @return
     */
	public boolean getCheckEcMallPhone(JmiMember miMember);
	public List getJmiClubs(String userCode);
	
	/**
	 * sql 查是否能下首单
	 * @param jmiMember
	 * @return
	 */
	public Integer getIsNoFirst(JmiMember jmiMember);
	
	/**
	 * 查会员
	 * @param userCode
	 * @return
	 */
	public JmiMember getJmiMember(String userCode);
	
	/**
	 * 查询原来的银行信息  记录日志
	 * @param userCode
	 * @return
	 */
	public List findJmiMemberById(String userCode);
	
	/**
	 * 密码重置验证(手机端)
	 * @param userCode：会员编号
	 * @param parpeNumber：证件号
	 * @param mobiletele：手机号
	 * @return
	 */
	public String checkJmiMemberPwdResetMobile(String userCode, String cardNumber, String mobilePhone);
	/**
	 * 通过userCode查询JmiMember对象的CloudshopPhone
	 * @param userCode
	 * @return
	 */
	public String getCloudshopPhoneByUserCode(String userCode);
	
	/**
	 * 该手机号是否已注册云店账号
	 * @param cloudshopPhone
	 * @return
	 */
	public boolean getCloudshopPhoneIsExist(String cloudshopPhone);
	
	/**
	 * 修改云店账号
	 * @param userCode
	 * @param cloudshopPhone
	 */
	public void updateCloudshopPhone(String userCode,String cloudshopPhone);
	/**
	 * 查询会员下的所有云客
	 */
	public List getjmiYkSearchList(Map<String,String> params,GroupPage page);
}