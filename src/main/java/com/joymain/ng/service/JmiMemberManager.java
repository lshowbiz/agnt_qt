package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.util.GroupPage;
import org.springframework.validation.BindingResult;

import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiMemberLog;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JmiMemberManager extends GenericManager<JmiMember, String> {
    
	public Pager<JmiMember> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	/**
	 * 
	 * @param jmiMember
	 * @param errors
	 * @param type 1.会员窗新建会员  3.会员修改自己资料 
	 * @return
	 */
	public boolean getCheckMiMember(JmiMember jmiMember,BindingResult errors,HttpServletRequest request,String type,JsysUser defSysUser) throws Exception;
	/**
	 * 检测夫妻号
	 * @param errors
	 * @param spouseIdno
	 * @param userCode
	 * @param papertype
	 * @param companyCode
	 * @return
	 */
	public String getCheckSpouseIdno(BindingResult errors,String spouseIdno,String userCode,String papertype,String companyCode,String defCharacterCoding);
	
	/**
	 * 检查身份证
	 * @param errors
	 * @param papernumber
	 * @param defUserCode
	 * @param papertype
	 * @param companyCode
	 * @return
	 */
	public String getCheckIdNo(BindingResult errors,JmiMember jmiMember,String defUserCode,String companyCode,String defCharacterCoding);

	/**
	 * 检验推荐人
	 * @param errors
	 * @param recommendNo
	 * @param defUserCode
	 * @return
	 */
	public String getCheckRecommendNo(BindingResult errors,String recommendNo,String defUserCode,String defCharacterCoding) ;
	/**
	 * 验证安置人
	 * @param errors
	 * @param recommendNo
	 * @param linkNo
	 * @param defUserCode
	 * @return
	 */
	public String getCheckLinkNo(BindingResult errors, String recommendNo, String linkNo,String defUserCode,String defCharacterCoding);
	
	public JmiMember getJmiMember(String userCode);
	/**
	 * 查询网络--推荐查询
	 * @param topUserCode
	 * @param netType
	 * @param layerId
	 * @return
	 */
	public List getNet(String topUserCode,String netType,String layerId);

	
	public List getChildJbdDayBounsCalcBySql(String userCode,String netType, final Integer wweek);
	
	/**
	 * 查会员期别业绩
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public Map getJbdDayBounsCalcByUserCode(String userCode, final Integer wweek);
	
	/**
	 * 会员网下所有业绩
	 * @param userCode
	 * @return
	 */
	public JmiRecommendRef getTopIndex(String userCode);
	public List getJmiMembers(Map map);
	
	public void removeJmiMember(String userCode);
	public String saveNewMiMember(JmiMember jmiMember,JsysUser defSysUser,String operaType);
	
	/**
	 * 会员基本信息－银行信息修改－初始化查询
	 * @author gw 2013-07-02
	 * @param userCode
	 * @return
	 */
	public JmiMember getJmiMemberBankInformation(String userCode);
	/**
	 * 会员系统－银行信息修改－修改操作
	 * @author gw 2013-07-03 
	 * @param member
	 */
	public void saveJmiMemberBankInformationChange(JmiMember member);

	/**
	 * 会员系统－银行信息修改－查出可用银行的信息
	 * @author  gw 2013-07-03 
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
	 * @param recommendNo
	 * @param selectNo
	 * @return
	 */
	public String getJmiSelectLinkNo1(String recommendNo,String selectNo);
	
	/**
	 * 密码重置验证	
	 * @param userCode
	 * @param parpeNumber
	 * @param mobiletele
	 * @return
	 */
	public JmiMember checkJmiMemberPwdReset(String userCode, String parpeNumber, String mobiletele);
	
	/**
	 * 产生随机密码
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
	 * 递归网络图
	 * @param list
	 * @param topMember
	 * @param maxLevel
	 * @param level
	 */
	public void getNetTree(final List list,final Object topMember, final int maxLevel, int level);

	public Integer getRemainUpdateDays(JsysUser defSysUser) ;

    /**
     * 判断ec商场电话号码是否存在
     * @param miMember
     * @return
     */
	public boolean getCheckEcMallPhone(JmiMember miMember);
	public List getJmiClubs(String userCode);
	/**
	 * 验证身份证有效性
	 * @param id
	 * @return
	 */
	public boolean getIdCardFormatCheckRegisterCN(String idcard) ;
	public Map getBonsuStar(String week,String weekType ,String type,JsysUser jSysUser);
	public Map getBonsuStarView(String week,String weekType ,String type);
	/**
	 * 
	 * @param origJmiMember 原会员
	 * @param qty 数量	
	 * @param defSysUser 操作人
	 * @return
	 */
	public List generateMultiMember(JmiMember origJmiMember,Integer qty,JsysUser defSysUser);

	/**
	 * sql 查是否能下首单
	 * @param jmiMember
	 * @return
	 */
	public Integer getIsNoFirst(JmiMember jmiMember);
	public String getPromotionTips(String userCode);
	public Map getSamePapernumberUserCode(String papernumber);

    public List getJmiMemberForGHB(String userCode, String phone);
    
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
	 * @return：
	 * 0：重置密码成功，并发送短信
	 * 1：会员编号不存在   2：证件号不存在   3：手机号不存在   4：会员编号&证件号&手机号不匹配  
	 * -1：会员编号&证件号&手机号填写成功，但调用短信平台异常(注释：本地调用短信平台无法测试，只能再香港测试机才可以测试)
	 */
	public String checkJmiMemberPwdResetMobile(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	/**
	 * @Description:修改银行信息
	 * @author:			侯忻宇
	 * @date:		    2016-9-19
	 * @param member
	 * @param jmiMemberLog:
	 * @exception:
	 * @return:
	 */
	public void saveJmiMemberBankAndLog(JmiMember member,JmiMemberLog jmiMemberLog);
	/**
	 * 升级单PV验证,PV不够升一级返回true
	 * @param memberLevel
	 * @param orderPv
	 * @return true or false
	 */
	public boolean upgradePVByOrderMemberLevelCofig(Integer memberLevel,BigDecimal orderPv);
	/**
	 * 升级单PV差额验证,PV不够升一级返回true
	 * @param gradeType
	 * @param orderPv
	 * @return true or false
	 */
	public boolean upgradePV(int gradeType,BigDecimal orderPv);
	public boolean getIsInfoEmpty(String userCode);

	public void saveJmiMemberAndFiAddr(JmiAddrBook jmiAddrBook,Map map);

	public boolean getIsSameName(String firstName,String lastName);
	/**
	 * 通过userCode查询jmiMember中的cloudshopPhone
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
	 * 修改云店账号(手机端)
	 * @param request
	 * @param response
	 */
	public void modifyCloudshopPhone(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * @param recommendNo
	 * @param selectNo
	 * @param defSysUser
	 * @return
	 */
	public String getJmiSelectLinkNo(String recommendNo,String selectNo,JsysUser defSysUser) ;
	
	/**
	 * 
	 * @param jmiMember
	 * @param defSysUser
	 * @return
	 * @throws Exception
	 */
	public Map getCheckMiMemberLinkNo(JmiMember jmiMember,JsysUser defSysUser) throws Exception;
	
	public Map getJmiSelectLinkNoToApp(String recommendNo,String selectNo,JsysUser defSysUser) ;

	/**
	 * 查询会员下的所有云客
	 */
	public List getjmiYkSearchList(Map<String,String> params,GroupPage page) ;

	public boolean getCheckPapernumber(JmiMember jmiMember,BindingResult errors,JsysUser defSysUser);

	public String getCheckIdNoIndex(String papernumber);

	public void savePapernumber(String papernumber);
}