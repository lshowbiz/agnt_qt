package com.joymain.ng.service.impl;

import com.joymain.ng.model.JmiYdSendList;
import com.joymain.ng.service.JmiYdSendListManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joymain.ng.util.*;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.Constants;
import com.joymain.ng.dao.FiBankbookJournalDao;
import com.joymain.ng.dao.FiBcoinBalanceDao;
import com.joymain.ng.dao.FiBcoinJournalDao;
import com.joymain.ng.dao.FiCcoinBalanceDao;
import com.joymain.ng.dao.FiCcoinJournalDao;
import com.joymain.ng.dao.FiFundbookJournalDao;
import com.joymain.ng.dao.JfiBankbookJournalDao;
import com.joymain.ng.dao.JmiBlacklistDao;
import com.joymain.ng.dao.JmiLinkRefDao;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.dao.JmiMemberLogDao;
import com.joymain.ng.dao.JmiRecommendRefDao;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.FiBcoinJournal;
import com.joymain.ng.model.FiCcoinBalance;
import com.joymain.ng.model.FiCcoinJournal;
import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.model.FiLovecoinBalance;
import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.model.HttpMsg;
import com.joymain.ng.model.JamMsnDetail;
import com.joymain.ng.model.JamMsnType;
import com.joymain.ng.model.JbdBonusBalance;
import com.joymain.ng.model.JbdSummaryList;
import com.joymain.ng.model.JbdTravelPoint;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiDealList;
import com.joymain.ng.model.JmiLinkRef;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiMemberLog;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JpmSmssendInfo;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.FiFundbookBalanceManager;
import com.joymain.ng.service.FiLovecoinBalanceManager;
import com.joymain.ng.service.FiProductPointBalanceManager;
import com.joymain.ng.service.FiTransferAccountManager;
import com.joymain.ng.service.JamMsnDetailManager;
import com.joymain.ng.service.JamMsnTypeManager;
import com.joymain.ng.service.JbdBonusBalanceManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JbdSummaryListManager;
import com.joymain.ng.service.JbdTravelPointManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JmiDealListManager;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.JmiSmsNoteManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserRoleManager;

@Service("jmiMemberManager")
@WebService(serviceName = "JmiMemberService", endpointInterface = "com.joymain.ng.service.JmiMemberManager")
public class JmiMemberManagerImpl extends GenericManagerImpl<JmiMember, String> implements JmiMemberManager {
    JmiMemberDao jmiMemberDao;
    
    @Autowired
    JmiMemberLogDao jmiMemberLogDao;

    JpmProductSaleNewDao jpmProductSaleNewDao;
    
    @Autowired
    public JmiMemberManagerImpl(JmiMemberDao jmiMemberDao,JpmProductSaleNewDao jpmProductSaleNewDao) {
        super(jmiMemberDao);
        this.jmiMemberDao = jmiMemberDao;
        this.jpmProductSaleNewDao = jpmProductSaleNewDao;
    }
    @Autowired
	public JmiBlacklistDao jmiBlacklistDao;

    @Autowired
    private JsysRoleManager jsysRoleManager;
    @Autowired
    private JmiLinkRefDao jmiLinkRefDao;
    @Autowired
    private JmiRecommendRefDao jmiRecommendRefDao;
    @Autowired
    private JbdPeriodManager jbdPeriodManager;
    @Autowired
    private JmiLinkRefManager jmiLinkRefManager;
    @Autowired
    private JmiRecommendRefManager jmiRecommendRefManager;
    @Autowired
    private JdbcTemplate jdbcTemplate3;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JbdSummaryListManager jbdSummaryListManager;
    @Autowired
    private JamMsnTypeManager jamMsnTypeManager;
    @Autowired
    private JamMsnDetailManager jamMsnDetailManager;  
    @Autowired
    private JbdTravelPointManager jbdTravelPointManager;
    @Autowired
    private JbdBonusBalanceManager jbdBonusBalanceManager;

    @Autowired
    public JfiBankbookBalanceManager jfiBankbookBalanceManager;

    @Autowired
    public FiBankbookBalanceManager fiBankbookBalanceManager;

    @Autowired
    public JsysUserRoleManager jsysUserRoleManager;

    @Autowired
    public FiBcoinBalanceDao fiBcoinBalanceDao;

    @Autowired
    public JsysIdManager jsysIdManager;

    @Autowired
    public FiCcoinBalanceDao fiCcoinBalanceDao;
    
    @Autowired
    public JfiBankbookJournalDao jfiBankbookJournalDao;

    @Autowired
    public FiBcoinJournalDao fiBcoinJournalDao;

    @Autowired
    public FiCcoinJournalDao fiCcoinJournalDao;
    @Autowired
    public JpoMemberOrderManager jpoMemberOrderManager;
    @Autowired
    public FiTransferAccountManager fiTransferAccountManager;

    @Autowired
    public FiBankbookJournalDao fiBankbookJournalDao;


    @Autowired
    private JmiSmsNoteManager jmiSmsNoteManager;
    @Autowired
    private FiFundbookJournalDao fiFundbookJournalDao;
    
    @Autowired
    private FiFundbookBalanceManager fiFundbookBalanceManager;
    @Autowired
    private FiLovecoinBalanceManager fiLovecoinBalanceManager;

    @Autowired
    private JmiDealListManager jmiDealListManager;
    @Autowired
    private JmiAddrBookManager jmiAddrBookManager;

    @Autowired
    private FiProductPointBalanceManager fiProductPointBalanceManager;
    
    @Autowired
    private FiCommonAddrManager fiCommonAddrManager;
    
    @Autowired
	private JmiTeamManager jmiTeamManager;

	@Autowired
	private JmiYdSendListManager jmiYdSendListManager;


	public Pager<JmiMember> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiMemberDao.getPager(JmiMember.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	public String saveNewMiMember(JmiMember jmiMember,JsysUser defSysUser,String operaType){
		String newMemberNo=jsysIdManager.buildIdStr("member_no");//取出编号
		
		// TODO:ljye保存会员方法补充
		
		String companyCode=jmiMember.getCompanyCode();//取国别
		Date curDate=DateUtil.getNowTimeFromDateServer();
		String defCharacterCoding=defSysUser.getDefCharacterCoding();
		newMemberNo="AJ"+newMemberNo;
		JmiMember existMember=jmiMemberDao.get(newMemberNo);
		if(existMember!=null){
			throw new RuntimeException("member.exist");
		}
		jmiMember.setUserCode(newMemberNo);
		
		
		
		//接点体系入库
		JmiLinkRef miLinkRef =jmiLinkRefDao.get(jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode());
		JmiLinkRef miNewLinkRef = jmiLinkRefDao.getNewMiLinkRefManagersByLinkNo(miLinkRef, getMaxLinkNo(miLinkRef, jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode()));
		miNewLinkRef.setJmiMember(jmiMember);
		miNewLinkRef.setUserCode(newMemberNo);
		jmiMember.setJmiLinkRef(miNewLinkRef);
		jmiMember.setLinkNo(miNewLinkRef.getLinkJmiMember().getUserCode());
		miNewLinkRef.setDepartmentPv(new BigDecimal(0));
		miNewLinkRef.setNum(new BigDecimal(0));
		
		//推荐体系入库
		JmiRecommendRef miRecommendRef = jmiRecommendRefDao.get(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode());
		JmiRecommendRef miNewRecommendRef = jmiRecommendRefDao.getNewMiLinkRefManagersByRecommendNo(miRecommendRef);
		miNewRecommendRef.setJmiMember(jmiMember);
		miNewRecommendRef.setUserCode(newMemberNo);
		jmiMember.setJmiRecommendRef(miNewRecommendRef);
		jmiMember.setRecommendNo(miNewRecommendRef.getRecommendJmiMember().getUserCode());
		
		jmiMember.setCardType("0");
		if(!StringUtil.isEmpty(defSysUser.getUserCode())){
	    	jmiMember.setCreateNo(defSysUser.getUserCode());
    	}else{
	    	jmiMember.setCreateNo(newMemberNo);
    	}
    	jmiMember.setCreateTime(curDate);
    	jmiMember.setActiveTime(curDate);
    	jmiMember.setActive("0");
    	jmiMember.getSysUser().setJmiMember(jmiMember);
		jmiMember.getSysUser().setUserCode(newMemberNo);
		jmiMember.getSysUser().setUserType("M");
		jmiMember.getSysUser().setUserCode(jmiMember.getUserCode());
    	jmiMember.getSysUser().setFirstName(jmiMember.getFirstName());
    	jmiMember.getSysUser().setLastName(jmiMember.getLastName());
    	jmiMember.getSysUser().setCompanyCode(companyCode);
    	
    	//operaType 1 已加密的密码 为1时不做处理 2015-4-7
    	if(null==operaType){
        	jmiMember.getSysUser().setPassword(StringUtil.encodePassword(jmiMember.getSysUser().getPassword(), "MD5"));
        	jmiMember.getSysUser().setPassword2(StringUtil.encodePassword(jmiMember.getSysUser().getPassword2(), "MD5"));
    	}
    	
    	jmiMember.setCompanyCode(companyCode);
    	jmiMember.setIsstore("0");
    	this.getSetUserName(jmiMember);
    	jmiMember.setBankbook(jmiMember.getSysUser().getUserName());
    	jmiMember.getSysUser().setState("1");
    	jmiMember.getSysUser().setDefCharacterCoding(defCharacterCoding);
    	jmiMember.setFreezeStatus(0);
    	jmiMember.setBeforeFreezeStatus(0);
    	jmiMember.setCustomerLevel("0");
    	jmiMember.setCountryCode(companyCode);
    	jmiMember.setMemberLevel(0);
		jmiMember.setMemberStar(0);
    	jmiMember.setNotFirst(0);
    	jmiMember.setGradeType(0);
    	jmiMember.setIsCloudshop("0");
		jmiMember.setLinkNum((long)0);
		jmiMember.setPendingLinkNum((long)0);
		jmiMember.setRecommendNum((long)0);
		jmiMember.setPendingRecommendNum((long)0);
 //   	jmiMember.setMemberUserType("1");
//    	加入电子存折
    	

    	//加入电子存折
    	JfiBankbookBalance jfiBankbookBalance=new JfiBankbookBalance();
    	jfiBankbookBalance.setUserCode(newMemberNo);
    	jfiBankbookBalance.setBalance(new BigDecimal(0));
    	jfiBankbookBalanceManager.save(jfiBankbookBalance);
    	

    	//插入发展基金
    	FiBankbookBalance fiBankbookBalance=new FiBankbookBalance();
    	fiBankbookBalance.setBalance(new BigDecimal(0));
    	fiBankbookBalance.setBankbookType("1");
    	fiBankbookBalance.setUserCode(newMemberNo);
    	fiBankbookBalanceManager.save(fiBankbookBalance);
    	
    	//插入奖金累加表
    	
    	JbdBonusBalance jbdBonusBalance=new JbdBonusBalance();
    	jbdBonusBalance.setUserCode(newMemberNo);
    	jbdBonusBalance.setBonusBalance(new BigDecimal(0));
    	jbdBonusBalance.setAssignedBonus(new BigDecimal(0));
    	jbdBonusBalance.setFlag("0");
    	if("TW".equals(jmiMember.getCompanyCode())){//台湾
    		jbdBonusBalance.setStatus("1");
    	}else{
    		jbdBonusBalance.setStatus("0");
    	}
    	jbdBonusBalanceManager.save(jbdBonusBalance);
    	
    	
    	
    	//设置会员角色
    	String memberRoleId = Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("jocs.member.role.0");
		JsysRole memberSysRole=jsysRoleManager.getSysRoleByCode(memberRoleId);
		JsysUserRole sysUserRole=new JsysUserRole();
		sysUserRole.setRoleId(memberSysRole.getRoleId());
		sysUserRole.setUserCode(jmiMember.getUserCode());

		jsysUserRoleManager.save(sysUserRole);
    	
    	
		//TODO:ljye插入会员表
    	this.getTeamType(jmiMember);
		
		if("CN".equals(defSysUser.getCompanyCode())){
			//发货时默认发短信设置 
			JamMsnType jamMsnType=jamMsnTypeManager.get(125986L);
			JamMsnDetail jamMsnDetail=new JamMsnDetail();
			jamMsnDetail.setJamMsnType(jamMsnType);
			jamMsnDetail.setStatus("1");
			jamMsnDetail.setUserCode(newMemberNo);
			jamMsnDetailManager.save(jamMsnDetail);
			//
			jmiMember.setPbNo("bankprivate001-wfx");
		}

		this.save(jmiMember);
		//活动旅游积分 
		JbdTravelPoint jbdTravelPoint=new JbdTravelPoint();
		jbdTravelPoint.setUserCode(newMemberNo);
		jbdTravelPoint.setTotal(new BigDecimal(0));
		jbdTravelPoint.setPassStar(0);
		jbdTravelPointManager.save(jbdTravelPoint);
		//
		//插入每日计算表
		JbdSummaryList jbdSummaryList=new JbdSummaryList();
		jbdSummaryList.setUserCode(newMemberNo);
		jbdSummaryList.setCardType("0");
		jbdSummaryList.setInType(1);
		jbdSummaryList.setCreateTime(curDate);
		jbdSummaryList.setNewLinkNo(jmiMember.getLinkNo());
		jbdSummaryList.setNewRecommendNo(jmiMember.getRecommendNo());
		jbdSummaryList.setNewCompanyCode(defSysUser.getCompanyCode());
		jbdSummaryList.setUserCreateTime(curDate);
		jbdSummaryList.setWweek(jbdPeriodManager.getBdPeriodByTimeFormated(curDate));
		// TODO:ljye插入每日计算表
		jbdSummaryListManager.save(jbdSummaryList);
		// TODO:ljye插入BC积分

		FiBcoinBalance fiBcoinBalance=new FiBcoinBalance();
		fiBcoinBalance.setUserCode(newMemberNo);
		fiBcoinBalance.setBalance(new BigDecimal(0));
		fiBcoinBalanceDao.save(fiBcoinBalance);
		
		FiCcoinBalance fiCcoinBalance=new FiCcoinBalance();
		fiCcoinBalance.setUserCode(newMemberNo); 
		fiCcoinBalance.setBalance(new BigDecimal(0));
		fiCcoinBalanceDao.save(fiCcoinBalance);
		

		FiFundbookBalance fiFundbookBalance=new FiFundbookBalance();
		fiFundbookBalance.setBalance(new BigDecimal(0));
		fiFundbookBalance.setBankbookType("1");
		fiFundbookBalance.setUserCode(newMemberNo);
		fiFundbookBalanceManager.save(fiFundbookBalance);
		

		FiFundbookBalance fiFundbookBalance1=new FiFundbookBalance();
		fiFundbookBalance1.setBalance(new BigDecimal(0));
		fiFundbookBalance1.setBankbookType("2");
		fiFundbookBalance1.setUserCode(newMemberNo);
		fiFundbookBalanceManager.save(fiFundbookBalance1);
		
		FiLovecoinBalance fiLovecoinBalance=new FiLovecoinBalance();
		fiLovecoinBalance.setBalance(new BigDecimal(0));
		fiLovecoinBalance.setUserCode(newMemberNo);
		fiLovecoinBalanceManager.save(fiLovecoinBalance);
		
		FiProductPointBalance fiProductPointBalance=new FiProductPointBalance();
		fiProductPointBalance.setProductPointType("1");
		fiProductPointBalance.setUserCode(newMemberNo);
		fiProductPointBalance.setBalance(new BigDecimal(0));
		fiProductPointBalanceManager.save(fiProductPointBalance);
		
		JmiDealList jmiDealList=new JmiDealList();
		jmiDealList.setCreateTime(curDate);
		jmiDealList.setInType(1);
		jmiDealList.setLinkNo(jmiMember.getRecommendNo());
		jmiDealList.setUserCode(newMemberNo);
		jmiDealListManager.save(jmiDealList);

		//推送给云店的临时表
		JmiYdSendList jmiYdSendList=new JmiYdSendList();
		jmiYdSendList.setCreateTime(curDate);
		jmiYdSendList.setRecommendNo(jmiMember.getRecommendNo());
		jmiYdSendList.setSourceCode("AJ");
		jmiYdSendList.setUserCode(newMemberNo);
		jmiYdSendList.setSendNum(new BigDecimal(0));
		jmiYdSendListManager.save(jmiYdSendList);
		
/*		if("2".equals(jmiMember.getMemberUserType())){
			String province="163702";//北京
			String city="17356";
			String district="17361";
			String address="虚拟地址";
			String firstName="虚拟姓";
			String lastName="虚拟名";
			String mobiletele="12345678901";
			
			FiCommonAddr fiCommonAddr=new FiCommonAddr();
			fiCommonAddr.setAddress(address);
			fiCommonAddr.setProvince(province);
			fiCommonAddr.setCity(city);
			fiCommonAddr.setUserCode(newMemberNo);
			fiCommonAddr.setDistrict(district);
			fiCommonAddrManager.save(fiCommonAddr);
			
			JmiAddrBook jmiAddrBook=new JmiAddrBook();
			jmiAddrBook.setAddress(address);
			jmiAddrBook.setCity(city);
			jmiAddrBook.setDistrict(district);
			jmiAddrBook.setProvince(province);
			jmiAddrBook.setFirstName(firstName);
			jmiAddrBook.setLastName(lastName);
			jmiAddrBook.setMobiletele(mobiletele);
			jmiAddrBook.setIsDefault("1");
			jmiAddrBook.setJmiMember(jmiMember);
			jmiAddrBook.setPostalcode("12345");
			jmiAddrBookManager.save(jmiAddrBook);
		}*/
		
		
		
		return newMemberNo;
		
	}
	/**
	 * 
	 * @param jmiMember
	 * @param errors
	 * @param type 1.会员窗新建会员  3.会员修改自己资料  4.验证银行信息
	 * @return 
	 */
	public boolean getCheckMiMember(JmiMember jmiMember,BindingResult errors,HttpServletRequest request,String type,JsysUser defSysUser) throws Exception{
		boolean isNotPass = false;
		String companyCode=jmiMember.getCompanyCode();
		String defCharacterCoding=defSysUser.getDefCharacterCoding();
		//判断身份证与姓名一致，同名的时候
		if("1".equals(type)){

			String password1Confirm=request.getParameter("password1Confirm");
			String password2Confirm=request.getParameter("password2Confirm");
			
			/*if(StringUtils.isEmpty(jmiMember.getSysUser().getPassword())){
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "sysUser.password", "miMember.pwd1",defCharacterCoding);
				isNotPass=true;
			}else if(!jmiMember.getSysUser().getPassword().equals(password1Confirm)){
				StringUtil.getErrorsCode(errors, "error.password.not.accord", "",defCharacterCoding);
				isNotPass = true;
			}
			if(StringUtil.isEmpty(jmiMember.getSysUser().getPassword2())){
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "sysUser.password2", "miMember.pwd2",defCharacterCoding);
				isNotPass=true;
			}else if(!jmiMember.getSysUser().getPassword2().equals(password2Confirm)){
				StringUtil.getErrorsCode(errors, "miMember.pwd2.isNotEqual", "",defCharacterCoding);
				isNotPass = true;
			}*/
/*			if(!StringUtil.isEmpty(getCheckSpouseIdno(errors, jmiMember.getSpouseIdno(), jmiMember.getUserCode(), jmiMember.getPapertype(), companyCode))){
				isNotPass=true;
			}*/
			if(!StringUtil.isEmpty(getCheckRecommendNo(errors, jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode(), defSysUser.getUserCode(),defCharacterCoding))){
				isNotPass=true;
			}
			if(!StringUtil.isEmpty(getCheckLinkNo(errors,  jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode(), jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode(), defSysUser.getUserCode(),defCharacterCoding))){
				isNotPass=true;
			}
			if(StringUtils.isNotEmpty(jmiMember.getPapernumber()) && "1".equals(jmiMember.getPapertype())) {
	    	    String temp = jmiMember.getPapernumber();
	    	    LinkedHashMap<String, String> map = ListUtil.getListOptions(companyCode, "exclude.papernumber");
	    	    Collection<String> c = map.values();
	    	    for(String s:c){
	    	        if(temp.startsWith(s)){
	                    StringUtil.getErrorsCode(errors, "idNo.format", "papernumber",defCharacterCoding);
	                    isNotPass = true;
	                }
	    	    }
	    	    /**
                 * 新疆乌鲁木齐 身份证开头就必须6501
                                                    新疆克拉玛依                            6502
                                                    新疆昌吉                                   6523
                                                    新疆塔城                                   6542
                                    如果不是以上四位号码开头，则不允许保存；
                                    选择以上四个城市下面包括所有的区和县都必须都是上述标准
                 */
                
	    	}
			
		}
		if("1".equals(type)){
			if(!StringUtil.isEmpty(getCheckIdNo(errors, jmiMember, defSysUser.getUserCode(),companyCode,defCharacterCoding))){
				isNotPass=true;
			}
			if(!StringUtil.isEmpty(jmiMember.getEmail())&&this.getPattern("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", jmiMember.getEmail())){
				StringUtil.getErrorsFormatCode(errors, "errors.email", "email", "miMember.email",defCharacterCoding);
				isNotPass = true;
			}
	    	
		}
		//会员修改自己资料  gw  2013-08-29
		if("3".equals(type)){
			if(StringUtil.isEmpty(jmiMember.getPetName())){
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "petName", "miMember.petName",defCharacterCoding);
				isNotPass=true;
			}
			if (StringUtils.isEmpty(jmiMember.getMobiletele())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "mobiletele", "miMember.mobiletele",defCharacterCoding);
				isNotPass = true;
	    	}else if(this.getPattern("^[0-9]*", jmiMember.getMobiletele())){
				StringUtil.getErrorsFormatCode(errors, "errors.phone", "mobiletele", "miMember.mobiletele",defCharacterCoding);
				isNotPass = true;
	    	}
			if(!StringUtil.isEmpty(jmiMember.getEmail())&&this.getPattern("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", jmiMember.getEmail())){
				StringUtil.getErrorsFormatCode(errors, "errors.email", "email", "miMember.email",defCharacterCoding);
				isNotPass = true;
			}
			if (StringUtils.isEmpty(jmiMember.getProvince())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "province", "miMember.province",defCharacterCoding);
				isNotPass = true;
			}
			if (StringUtils.isEmpty(jmiMember.getCity())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "city", "busi.city",defCharacterCoding);
				isNotPass = true;
			}
	    	if (StringUtils.isEmpty(jmiMember.getAddress())) {
	    		StringUtil.getErrorsFormatCode(errors, "isNotNull", "address", "busi.address",defCharacterCoding);
				isNotPass = true;
	    	}
		}

		if("1".equals(type) || "3".equals(type)){
			if(!StringUtil.isEmpty(jmiMember.getFirstNamePy())){
				if(this.getPattern("([A-Z]|\\s)*", jmiMember.getFirstNamePy())){
					StringUtil.getErrorsCode(errors, "firstNamePy.UpPy", "firstNamePy",defCharacterCoding);
					isNotPass = true;
				}
			}
			if(!StringUtil.isEmpty(jmiMember.getLastNamePy())){
				if(this.getPattern("([A-Z]|\\s)*", jmiMember.getLastNamePy())){
					StringUtil.getErrorsCode(errors, "lastNamePy.UpPy", "lastNamePy",defCharacterCoding);
					isNotPass = true;
				}
			}
		}
		if("5".equals(type)){
			String papernumberUserCode = "";
	    	HashMap map = null;
	    	JmiMember jmiMember1=jmiMemberDao.get(jmiMember.getUserCode());
			List list=jmiMemberDao.getPapernumberUserCode(jmiMember1);
			if(!MeteorUtil.isBlankByList(list)){
				for(int i=0;i<list.size();i++){
					map = (HashMap) list.get(i);
					papernumberUserCode = String.valueOf(map.get("USER_CODE"));
					System.out.println("papernumberUserCode==="+papernumberUserCode);
					if(!StringUtil.isEmpty(papernumberUserCode)){
						JmiMember jmiMemberPapernumber=jmiMemberDao.get(papernumberUserCode);
						String curRegisterUserName=(jmiMember.getFirstName()+jmiMember.getLastName());
						if(!curRegisterUserName.equals(jmiMemberPapernumber.getMiUserName()) && !MeteorUtil.isBlank(jmiMemberPapernumber.getMiUserName())){
							StringUtil.getErrorsCode(errors, "idNo.linkNo.userName.no", "",defCharacterCoding);
							isNotPass = true;
						}
					}
				}
				
//				String papernumberUserCode="";//jmiMemberDao.getPapernumberUserCode(jmiMember);
//				if(!StringUtil.isEmpty(papernumberUserCode)){
//					JmiMember jmiMemberPapernumber=jmiMemberDao.get(papernumberUserCode);
//					String curRegisterUserName=(jmiMember.getFirstName()+jmiMember.getLastName());
//					if(!curRegisterUserName.equals(jmiMemberPapernumber.getMiUserName())){
//						StringUtil.getErrorsCode(errors, "idNo.linkNo.userName.no", "",defCharacterCoding);
//						isNotPass = true;
//					}
//				}
			}
			if(StringUtil.isEmpty(jmiMember.getFirstName())){
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "firstName", "miMember.firstName",defCharacterCoding);
				isNotPass=true;
			}
			if(StringUtil.isEmpty(jmiMember.getLastName())){
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "lastName", "miMember.lastName",defCharacterCoding);
				isNotPass=true;
			}
			if(StringUtil.isEmpty(jmiMember.getPetName())){
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "petName", "miMember.petName",defCharacterCoding);
				isNotPass=true;
			}
			if (StringUtils.isEmpty(jmiMember.getMobiletele())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "mobiletele", "miMember.mobiletele",defCharacterCoding);
				isNotPass = true;
	    	}else if(this.getPattern("^[0-9]*", jmiMember.getMobiletele())){
				StringUtil.getErrorsFormatCode(errors, "errors.phone", "mobiletele", "miMember.mobiletele",defCharacterCoding);
				isNotPass = true;
	    	}
			if (StringUtils.isEmpty(jmiMember.getProvince())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "province", "miMember.province",defCharacterCoding);
				isNotPass = true;
			}else if(!StringUtil.isInteger(jmiMember.getProvince())){
				StringUtil.getErrorsCode(errors, "有不合法字符", "province", defCharacterCoding);
				isNotPass = true;
			}
			if (StringUtils.isEmpty(jmiMember.getCity())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "city", "miMember.idAddr2",defCharacterCoding);
				isNotPass = true;
			}else if(!StringUtil.isInteger(jmiMember.getCity())){
				StringUtil.getErrorsCode(errors, "有不合法字符", "city", defCharacterCoding);
				isNotPass = true;
			}
			if(!StringUtils.isEmpty(jmiMember.getDistrict()) && !StringUtil.isInteger(jmiMember.getDistrict())){
				StringUtil.getErrorsCode(errors, "有不合法字符", "district", defCharacterCoding);
			}
	    	if (StringUtils.isEmpty(jmiMember.getAddress())) {
	    		StringUtil.getErrorsFormatCode(errors, "isNotNull", "address", "miMember.idAddr",defCharacterCoding);
				isNotPass = true;
	    	}
	    	//2014.12.19变更需求，部分地区姓名带.及超五个限制注册
            //2014.12.24变更需求1、身份证限制地区：和田；2、除了乌鲁木齐市（包括其下的乌鲁木齐县）、克拉玛依市、昌吉回族自治州（包括其下的市、县等）、塔城地区（包括其下的市、县等）4地 不限名字；其它全国范围内均对五个字以上包括带点的名字做限制；
            Map<String,String> xzMap = new HashMap<String,String>();
            xzMap.put("20648", "6501");//乌鲁木齐市
            xzMap.put("20658", "6502");//克拉玛依市
            xzMap.put("20672", "6523");//昌吉回族自治州
            xzMap.put("20744", "6542");//塔城地区
            String city = jmiMember.getCity();
	    	/**
	    	 * 身份证前六位以..开头的限制注册
	    	 */
	    	if(StringUtils.isNotEmpty(jmiMember.getPapernumber()) && "1".equals(jmiMember.getPapertype())) {
	    	    /**
                 * 新疆乌鲁木齐 身份证开头就必须6501
                                                    新疆克拉玛依                            6502
                                                    新疆昌吉                                   6523
                                                    新疆塔城                                   6542
                                    如果不是以上四位号码开头，则不允许保存；
                                    选择以上四个城市下面包括所有的区和县都必须都是上述标准
                 */
                if(city!=null&&!"".equals(city)&&xzMap.containsKey(city.trim())) {
                    String v = xzMap.get(city);
                    String pn = jmiMember.getPapernumber();
                    if(!pn.startsWith(v)){
                        StringUtil.getErrorsCode(errors, "所选身份证城市对应的证件号开头错误", "papernumber",defCharacterCoding);
                        isNotPass = true;
                    }
                }
	    	}
	    	/**
	    	 * 1、身份证选新疆维吾尔自治区；(需求变更：限制全国 && "163732".equals(jmiMember.getProvince()))
               2、大于等于五个字（包括点）；
               3、姓名中带【·】（包括全角半角）；
	    	 */
	    	if(StringUtils.isNotEmpty(jmiMember.getFirstName()) && StringUtils.isNotEmpty(jmiMember.getLastName())){
                if(city!=null&&!"".equals(city)&&!xzMap.containsKey(city.trim())) {
    	    	    String curRegisterUserName=jmiMember.getFirstName().trim()+jmiMember.getLastName().trim();
    	    	    if(curRegisterUserName.length()>=5){
    	    	        StringUtil.getErrorsCode(errors, "register.username.error", "firstName",defCharacterCoding);
                        isNotPass = true;
    	    	    }
    	    	    if(curRegisterUserName.indexOf("·")!=-1 || curRegisterUserName.indexOf("•")!=-1 || curRegisterUserName.indexOf(".")!=-1) {
    	    	        StringUtil.getErrorsCode(errors, "register.username.error", "lastName",defCharacterCoding);
                        isNotPass = true;
    	    	    }
                }
	    	}
			
		}

		//=====银行信息
		
		if("4".equals(type)){
			if (StringUtils.isEmpty(jmiMember.getBank())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "bank", "miMember.openBank",defCharacterCoding);
				isNotPass = true;
			}
			if (StringUtils.isEmpty(jmiMember.getBankaddress())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "bankaddress", "miMember.bcity",defCharacterCoding);
				isNotPass = true;
			}
	    	if (StringUtils.isEmpty(jmiMember.getBankcard())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "bankcard", "miMember.bnum",defCharacterCoding);
				isNotPass = true;
	    	}
        	if (StringUtils.isEmpty(jmiMember.getBankProvince())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "bankProvince", "miMember.bankProvince",defCharacterCoding);
				isNotPass = true;
        	}
        	if (StringUtils.isEmpty(jmiMember.getBankCity())) {
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "bankCity", "miMember.bankCity",defCharacterCoding);
				isNotPass = true;
        	}
		}
		
		
		
		
		return isNotPass;
	}
	private boolean getPattern(String expressions,String str){
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}
	/**
	 * 检测夫妻号
	 * @param errors
	 * @param spouseIdno
	 * @param userCode
	 * @param papertype
	 * @param companyCode
	 * @return
	 */
	public String getCheckSpouseIdno(BindingResult errors,String spouseIdno,String userCode,String papertype,String companyCode,String defCharacterCoding){
		JmiMember jmiMember=new JmiMember();
		jmiMember.setUserCode(userCode);
		jmiMember.setPapertype(papertype);
		jmiMember.setSpouseIdno(spouseIdno);
		String errorStr="";
		if("CN".equals(companyCode)){
			if(StringUtil.isEmpty(spouseIdno)){
				StringUtil.getErrorsFormatCode(errors, "isNotNull", "spouseIdno", "miMember.spouseIdno",defCharacterCoding);
			}else if(!StringUtil.isEmpty(spouseIdno)&&!getIdCardFormatCheckRegisterCN(spouseIdno)){
				errorStr=StringUtil.getErrorsCode(errors, "idNo.format", "spouseIdno",defCharacterCoding);
			}else if(jmiMemberDao.getCheckMiMemberSpouseIdNoByMiMember(jmiMember)){
				errorStr=StringUtil.getErrorsCode(errors, "miMember.idNo.isIn", "spouseIdno",defCharacterCoding);
			}else if(!jmiBlacklistDao.getCheckJmiBlacklist(jmiMember.getPapertype(), jmiMember.getPapernumber())){
				errorStr=StringUtil.getErrorsCode(errors, "idNo.blacklist", "spouseIdno",defCharacterCoding);
	    	}
		}
		return errorStr;
	}
	
	/**
	 * 检查身份证
	 * @param errors
	 * @param papernumber
	 * @param defUserCode
	 * @param papertype
	 * @param companyCode
	 * @return
	 */
	public String getCheckIdNo(BindingResult errors,JmiMember jmiMember,String defUserCode,String companyCode,String defCharacterCoding){
/*		JmiMember jmiMember=new JmiMember();
		jmiMember.setUserCode(defUserCode);
		jmiMember.setPapernumber(papernumber);
		jmiMember.setPapertype(papertype);*/
		String errorStr="";
		
		if (StringUtils.isEmpty(jmiMember.getPapernumber())) {//是否为空 
			errorStr=StringUtil.getErrorsFormatCode(errors, "isNotNull", "papernumber", "miMember.papernumber",defCharacterCoding);
		}else if(jmiMemberDao.getCheckMiMemberIdNoByMiMember(jmiMember)){//验证身份证的唯一性
			errorStr=StringUtil.getErrorsCode(errors, "miMember.idNo.isIn", "papernumber",defCharacterCoding);
		}else if(!jmiBlacklistDao.getCheckJmiBlacklist(jmiMember.getPapertype(), jmiMember.getPapernumber())){//黑名单
			errorStr=StringUtil.getErrorsCode(errors, "idNo.blacklist", "papernumber",defCharacterCoding);
    	}else if("1".equals(jmiMember.getPapertype()) && !getIdCardFormatCheckRegisterCN(jmiMember.getPapernumber())){//验证格式 
    		errorStr=StringUtil.getErrorsCode(errors, "idNo.format", "papernumber",defCharacterCoding);
    	}else if("1".equals(jmiMember.getPapertype()) && !StringUtil.isEmpty(getIdCardBirthday(jmiMember.getPapernumber()))  
    			&&!getCheckBirthday(getIdCardBirthday(jmiMember.getPapernumber()), null, 18)  ){
    		errorStr=StringUtil.getErrorsCode(errors, "member.age.tw", "birthday",defCharacterCoding);
    	}
		return errorStr;
	}
	/**
	 * 检验推荐人
	 * @param errors
	 * @param recommendNo
	 * @param defUserCode
	 * @return
	 */
	public String getCheckRecommendNo(BindingResult errors,String recommendNo,String defUserCode,String defCharacterCoding) {
        String errorStr="";
        if(StringUtil.isEmpty(recommendNo)){
        	errorStr=StringUtil.getErrorsFormatCode(errors, "isNotNull", "jmiRecommendRef.recommendJmiMember.userCode", "miMember.recommendNo",defCharacterCoding);
        }else if(jmiRecommendRefManager.get(recommendNo)==null){
        	errorStr=StringUtil.getErrorsCode(errors, "miRecommendRef.isNotFound", "jmiRecommendRef.recommendJmiMember.userCode",defCharacterCoding);
        }else{
            if(!StringUtil.isEmpty(defUserCode)&&!StringUtil.isEmpty(recommendNo)){//判断推荐人是否在当前登陆会员下
    			JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(defUserCode);
    			JmiRecommendRef miRecommendRef = jmiRecommendRefManager.get(recommendNo);
    			
    			
    			if(!StringUtil.getCheckIsDownline(defRecommendRef.getTreeIndex(), miRecommendRef.getTreeIndex())){
    				errorStr=StringUtil.getErrorsCode(errors, "miRecommendRef.isNotFound", "jmiRecommendRef.recommendJmiMember.userCode",defCharacterCoding);
    			}
    			
/*    			String rdefIndex=defRecommendRef.getTreeIndex();
    			String rIndex=miRecommendRef.getTreeIndex();
    			if(rIndex.length()<rdefIndex.length()){
    				errorStr=StringUtil.getErrors(errors, "miRecommendRef.isNotFound", "jmiRecommendRef.recommendJmiMember.userCode");
    			}
    			String rmemberIndexTmp = StringUtil.getLeft(rIndex, rdefIndex.length());
    			if(!rdefIndex.equals(rmemberIndexTmp)){//不为会员的下级组织
    				errorStr=StringUtil.getErrors(errors, "miRecommendRef.isNotFound", "jmiRecommendRef.recommendJmiMember.userCode");
    			}*/
    		}
        }
		return errorStr;
	}
	/**
	 * 验证安置人
	 * @param errors
	 * @param recommendNo
	 * @param linkNo
	 * @param defUserCode
	 * @return
	 */
	public String getCheckLinkNo(BindingResult errors, String recommendNo, String linkNo,String defUserCode,String defCharacterCoding) {
		String errorStr="";
		if(StringUtil.isEmpty(linkNo)){
			errorStr=StringUtil.getErrorsFormatCode(errors, "isNotNull", "jmiLinkRef.linkJmiMember.userCode", "miMember.linkNo",defCharacterCoding);
		}else if(jmiLinkRefManager.get(linkNo)==null){
			errorStr=StringUtil.getErrorsCode(errors, "miLinkRef.isNotFound", "jmiLinkRef.linkJmiMember.userCode",defCharacterCoding);
		}else if(StringUtil.isEmpty(getCheckRecommendNo(null, recommendNo, defUserCode,defCharacterCoding))){
			JmiRecommendRef miRecommendRef = jmiRecommendRefManager.get(recommendNo);
			JmiLinkRef miLinkRef =jmiLinkRefManager.get(linkNo);
			String rTreeIndex = miRecommendRef.getJmiMember().getJmiLinkRef().getTreeIndex();
			String lTreeIndex = miLinkRef.getTreeIndex();
			if (lTreeIndex.length() < rTreeIndex.length() || !rTreeIndex.equals(StringUtil.getLeft(lTreeIndex, rTreeIndex.length()))) {
				errorStr=StringUtil.getErrorsCode(errors, "miLinkRefMiRecommendRef.isNotEquals", "jmiLinkRef.linkJmiMember.userCode",defCharacterCoding);
			}
			//检查接点体系是否已满
/*			List miLinkRefs =jmiLinkRefDao.getLinkRefbyLinkNo(linkNo, "userCode", "");

			if (miLinkRefs.size() >= getMaxLinkNo(miLinkRef, linkNo)) {
				errorStr=StringUtil.getErrorsCode(errors, "miLinkRef.isFull", "jmiLinkRef.linkJmiMember.userCode",defCharacterCoding);
			}*/
		}
		return errorStr;
	}

	//更改姓名
	public void getSetUserName(JmiMember jmiMember) {

		String format=(String) Constants.sysConfigMap.get(jmiMember.getCompanyCode()).get("member.name.format");
		String space="";
		if("0".equals(format)){
			space="";
		}else{
			space=" ";
		}
		if((StringUtil.isEmpty(jmiMember.getFirstName())&&StringUtil.isEmpty(jmiMember.getLastName()))){
			String name=jmiMember.getMiUserName();
			jmiMember.getSysUser().setFirstName("");
			jmiMember.getSysUser().setLastName(name);
			jmiMember.setFirstName("");
			jmiMember.setLastName(name);
			jmiMember.getSysUser().setUserName(name);
		}else{
			jmiMember.getSysUser().setUserName((jmiMember.getFirstName()==null?"":jmiMember.getFirstName())+space+jmiMember.getLastName());
			jmiMember.getSysUser().setFirstName(jmiMember.getFirstName());
			jmiMember.getSysUser().setLastName(jmiMember.getLastName());
			jmiMember.setMiUserName(jmiMember.getSysUser().getUserName());
		}
	}
	public void getTeamType(JmiMember jmiMember){
		
		
		List list=jdbcTemplate.queryForList("select fn_get_member_type('"+jmiMember.getRecommendNo()+"') as member_type from dual");
		
		if(!list.isEmpty()){
			Map map=(Map) list.get(0);
			Object member_type=map.get("member_type");
			if(member_type!=null){
				jmiMember.setMemberType(member_type.toString());
			}
			
		}
		
		/*List teams=jmiMemberDao.getJmiTeamType();
		for (int i = 0; i < teams.size(); i++) {
			Map map=(Map) teams.get(i);
			String user_code=map.get("user_code").toString();
			String member_type=map.get("member_type").toString();
			
			JmiRecommendRef topMemberRecommendRef=jmiRecommendRefDao.get(user_code);
			JmiRecommendRef registerNoRecommendRef=jmiRecommendRefDao.get(jmiMember.getRecommendNo());
			
			if(topMemberRecommendRef!=null){
				String topIndex=topMemberRecommendRef.getTreeIndex();
				String registerNoRecommendRefIndex=registerNoRecommendRef.getTreeIndex();
				String rmemberIndexTmp = StringUtil.getLeft(registerNoRecommendRefIndex, topIndex.length());
				if(registerNoRecommendRefIndex.length()>=topIndex.length() && topIndex.equals(rmemberIndexTmp) ){
					jmiMember.setMemberType(member_type);
					break;
				}
			}
		}*/
	}
	private int getMaxLinkNo(JmiLinkRef miLinkRef,String linkNo){
		int maxLink = 3999;//生成最大接点人数
/*		List miLinkRefs =jmiLinkRefDao.getLinkRefbyLinkNo(linkNo, "userCode", "");
		if(miLinkRef.getNum().compareTo(new BigDecimal(0))!=0){
			maxLink=miLinkRef.getNum().intValue()+2;
		}
		if(miLinkRefs.size()<6){
			maxLink=6;
		}*/
		return maxLink;
	}
	
	/**
	 * 验证身份证有效性
	 * @param id
	 * @return
	 */
	public boolean getIdCardFormatCheckRegisterCN(String idcard) {
/*		if (idcard.length() == 15) {
			idcard = uptoeighteen(idcard);
		}
		if (idcard.length() != 18) {
			return false;
		}
		String verify = idcard.substring(17, 18);
		if (verify.equals(getVerify(idcard))) {
			return true;
		}
		return false;*/
		try {
			if(StringUtil.isEmpty(IDCardValidate(idcard))){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}	
	} 
	public String getVerify(String eightcardid) {
		
		int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
		int[] ai = new int[18];
		
		
		int remaining = 0;
		if (eightcardid.length() == 18) {
			eightcardid = eightcardid.substring(0, 17);
		}
		if (eightcardid.length() == 17) {
			int sum = 0;
			for (int i = 0; i < 17; i++) {
				String k = eightcardid.substring(i, i + 1);
				ai[i] = Integer.parseInt(k);
			}
			for (int i = 0; i < 17; i++) {
				sum = sum + wi[i] * ai[i];
			}
			remaining = sum % 11;
		}
		return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	}
	public String uptoeighteen(String fifteencardid) {
		String eightcardid = fifteencardid.substring(0, 6);
		eightcardid = eightcardid + "19";
		eightcardid = eightcardid + fifteencardid.substring(6, 15);
		eightcardid = eightcardid + getVerify(eightcardid);
		return eightcardid;
	}
	/**
	 * 验证身份证有效性
	 * @param id
	 * @return
	 */
	private String getIdCardBirthday(String id){
		id=id.toUpperCase();
		String regEx = "(^\\d{15}$)|(^\\d{17}([0-9]|[X])$)";
		//身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
		if(!StringUtil.isSpecialCharsIn(regEx, id)){
			return "";
		}
		int len=id.length();
		if(len==15){
			String year=id.substring(6, 8);
			String month=id.substring(8, 10);
			String day=id.substring(10, 12);
			if(StringUtil.isDate("19"+year+"-"+month+"-"+day)){
				return "19"+year+"-"+month+"-"+day;
			}else{
				return "";
			}
			
		}else if(len==18){
			String year=id.substring(6, 10);
			String month=id.substring(10, 12);
			String day=id.substring(12, 14);

			if(StringUtil.isDate(year+"-"+month+"-"+day)){
				return year+"-"+month+"-"+day;
			}else{
				return "";
			}
		}
		return "";
	}

	public boolean getCheckBirthday(String birthday, Date birthdayDate,int age) {
		if(!StringUtil.isEmpty(birthday)){
			try {
				birthdayDate=DateUtil.convertStringToDate(birthday);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(this.getAge(birthdayDate)<age){
    		return false;
		}else{
			return true;
		}
	}

	private int getAge(Date birthday){
		  Calendar cal = new GregorianCalendar();
		  cal.setTime(birthday);
		  Calendar now = new GregorianCalendar();
		  int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		  if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))|| (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
		      res--;
		  }
		  return res;
	}

	public void removeJmiMember(String userCode) {

    	JmiMember jmiMember = jmiMemberDao.get(userCode);
    	
    	if(!"0".equals(jmiMember.getCardType())){
			throw new AppException("member.remove.0");
    	}
    	
    	List miLinkRefs=jmiLinkRefDao.getLinkRefbyLinkNo(jmiMember.getUserCode(), "userCode", "");
    	
    	List miRecommendRefs=jmiRecommendRefDao.getJmiRecommendRefbyRecommendNo(jmiMember.getUserCode(), "userCode", "");
    	 
    	List fiTransferAccounts=fiTransferAccountManager.getFiTransferAccountListByUserCode(userCode, null, null);
    	
    	if(fiTransferAccounts.size()>0){
    		throw new AppException("存在交易记录");
    	}
    	
    	
    	JfiBankbookJournal jfiBankbookJournal=jfiBankbookJournalDao.getLastJfiBankbookJournal(userCode);
    	if(jfiBankbookJournal!=null){
    		throw new AppException("会员存折存在记录");
    	} 
    	FiBcoinJournal fiBcoinJournal=fiBcoinJournalDao.getLastFiBcoinJournal(userCode);
    	if(fiBcoinJournal!=null){
    		throw new AppException("B分存在记录");
    	}

    	FiCcoinJournal fiCcoinJournal=fiCcoinJournalDao.getLastFiCcoinJournal(userCode);
    	if(fiCcoinJournal!=null){
    		throw new AppException("C分存在记录");
    	}
    	FiBankbookJournal fiBankbookJournal= fiBankbookJournalDao.getLastFiBankbookJournal(userCode, "1");
    	if(fiBankbookJournal!=null){
    		throw new AppException("发展基金存在记录");
    	}
    	if(fiFundbookJournalDao.getCountByDate(jmiMember.getCompanyCode(),jmiMember.getUserCode())>0L){
    		throw new AppException("基金存在记录");
    	}
    	
    	JfiBankbookBalance jfiBankbookBalance=jfiBankbookBalanceManager.get(userCode);
    	if(jfiBankbookBalance.getBalance().compareTo(new BigDecimal(0))==1){
			throw new AppException("jfiBankbookBalance.greatthan.0");
    	}else{
    		
    	}
    	
    	Map map=new HashMap();
    	map.put("userCode", userCode);
    	map.put("companyCode", jmiMember.getCompanyCode());
    	List<JpoMemberOrder> jpoMemberOrders=jpoMemberOrderManager.getOrderByParam(map);
    	
    	if(jpoMemberOrders!=null && jpoMemberOrders.size()>0){
			throw new AppException("miMember.deleteFail");
    	}
    	if (miLinkRefs.size() == 0 && miRecommendRefs.size() == 0) {
    		jmiMemberDao.remove(userCode);
    		jbdBonusBalanceManager.remove(userCode);
			//插入每日计算表
    		Date curDate=DateUtil.getNowTimeFromDateServer();
			JbdSummaryList jbdSummaryList=new JbdSummaryList();
			jbdSummaryList.setUserCode(jmiMember.getUserCode());
			jbdSummaryList.setCardType(jmiMember.getCardType());
			jbdSummaryList.setInType(11);
			jbdSummaryList.setCreateTime(curDate);
			jbdSummaryList.setNewLinkNo(jmiMember.getLinkNo());
			jbdSummaryList.setNewRecommendNo(jmiMember.getRecommendNo());
			jbdSummaryList.setNewCompanyCode(jmiMember.getCompanyCode());
			jbdSummaryList.setUserCreateTime(curDate);
			jbdSummaryList.setWweek(jbdPeriodManager.getBdPeriodByTimeFormated(curDate));
			jbdSummaryListManager.save(jbdSummaryList);
			

			JmiDealList jmiDealList=new JmiDealList();
			jmiDealList.setCreateTime(curDate);
			jmiDealList.setInType(2);
			jmiDealList.setLinkNo(jmiMember.getRecommendNo());
			jmiDealList.setUserCode(userCode);
			jmiDealListManager.save(jmiDealList);
			//
		} else {
			throw new AppException("miLinkRef.hasMember");
		}
	}
	public List getNet(String topUserCode, String netType, String layerId) {
		return jmiMemberDao.getNet(topUserCode, netType, layerId);
	}
	public List getChildJbdDayBounsCalcBySql(String userCode, String netType, Integer wweek) {
		return jmiMemberDao.getChildJbdDayBounsCalcBySql(userCode, netType, wweek);
	}
	public Map getJbdDayBounsCalcByUserCode(String userCode, Integer wweek) {
		return jmiMemberDao.getJbdDayBounsCalcByUserCode(userCode, wweek);
	}
	public JmiRecommendRef getTopIndex(String userCode) {
		return jmiMemberDao.getTopIndex(userCode);
	}
	public List getJmiMembers(Map map) {
		return jmiMemberDao.getJmiMembers(map);
	}
	
	
	/**
	 * 会员基本信息－银行信息修改－初始化查询
	 * @author gw 2013-07-02
	 * @param userCode
	 * @return
	 */
	public JmiMember getJmiMemberBankInformation(String userCode) {
		
		return jmiMemberDao.getJmiMemberBankInformation(userCode);
	}
	
	/**
	 * 会员系统－银行信息修改－修改操作
	 * @author gw 2013-07-03 
	 * @param member
	 */
	public void saveJmiMemberBankInformationChange(JmiMember member) {
		 jmiMemberDao.saveJmiMemberBankInformationChange(member);
	}
	
	/**
	 * 会员系统－银行信息修改－查出可用银行的信息
	 * @author  gw 2013-07-03 
	 * @param companyCode
	 * @return
	 */
	public List getSysBankByCompanyCode(String companyCode){
		
		return jmiMemberDao.getSysBankByCompanyCode(companyCode);
	}
	
	
	/**
	 * 会员系统－个人资料维护－ 查询首购单的审核时间
	 * @author gw 2013-07-08 
	 * @param userCode
	 * @return
	 */
	public String getJmiMemberCheckOrCreateTime(String userCode) {
		return  jmiMemberDao.getJmiMemberCheckOrCreateTime(userCode);
	}
	
	/**
	 * 会员系统－个人资料维护－ 执行保存或修改的操作
	 * @author gw 2013-07-09
	 * @param miMember
	 * @return 
	 */
	public void savePersonalDataMaintenance(JmiMember miMember) {
		jmiMemberDao.savePersonalDataMaintenance(miMember);
	}
	public String getJmiSelectLinkNo1(String recommendNo,String selectNo) {
		
		JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (StringUtil.isEmpty(recommendNo)){//会员员为空
    		return "";
    	}
    	JmiLinkRef jmiLinkRef = jmiLinkRefManager.get(recommendNo);
    	if (jmiLinkRef ==null){//会员不存在
    		return "";
    	}
        JmiRecommendRef miRecommendRef = jmiRecommendRefManager.get(jmiLinkRef.getJmiMember().getJmiRecommendRef().getUserCode());
		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(defSysUser.getUserCode());
		if(!StringUtil.getCheckIsDownline(defRecommendRef.getTreeIndex(), miRecommendRef.getTreeIndex())){
			return "";
		}
		boolean flag=false;
		List list=jmiLinkRefManager.getLinkRefbyLinkNo(recommendNo, "a.jmiMember.createTime", "");
		for (int i = 0; i < list.size(); i++) {
			JmiLinkRef curJmiLinkRef =(JmiLinkRef) list.get(i);
			if(curJmiLinkRef.getUserCode().equals(selectNo)){
				flag=true;
			}
		}
		if(flag){
			String searchNo=selectNo;

			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			String teamCode=request.getSession().getAttribute("teamCode").toString();
/*			if("AJ34272972".equals(teamCode)){
				searchNo=jmiLinkRefManager.getLinkNo(selectNo);
			}else{
				}*/
			List jmiLinkRefs=jmiLinkRefManager.getLinkRefbyLinkNo(searchNo, "a.jmiMember.createTime", "");
			while (!jmiLinkRefs.isEmpty()) {
				JmiLinkRef curJmiLinkRef =(JmiLinkRef) jmiLinkRefs.get(0);
				jmiLinkRefs=jmiLinkRefManager.getLinkRefbyLinkNo(curJmiLinkRef.getUserCode(), "a.jmiMember.createTime", "");
				searchNo=curJmiLinkRef.getUserCode();
			}
			
		
			
			return searchNo;
		}else{
			return "";
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.joymain.ng.service.JmiMemberManager#checkJmiMemberPwdReset(java.lang.String, java.lang.String, java.lang.String)
	 */
	public JmiMember checkJmiMemberPwdReset(String userCode, String parpeNumber, String mobiletele){
		return jmiMemberDao.checkJmiMemberPwdReset(userCode, parpeNumber, mobiletele);
	}
	
	/* (non-Javadoc)
	 * @see com.joymain.ng.service.JmiMemberManager#updatePwdRandom(java.lang.String, java.lang.String)
	 */
	public void updatePwdRandom(JmiMember jmiMember){
		jmiMemberDao.updatePwdRandom(jmiMember);
	}
	
	/**
	 * 个人资料维护--获取当前登录用户的身份证号码
	 * @author gw 2013-08-29
	 * @param userCode
	 * @return String
	 */
	public String getPapernumberCheck(String userCode) {
		return jmiMemberDao.getPapernumberCheck(userCode);
	}
	
	
	public void getNetTree(final List list,final Object topMember, final int maxLevel, int level){
    	String netType="";
    	
    	List miTmps=null;
    	if(topMember instanceof JmiLinkRef){
    		netType="link";
    		JmiLinkRef jmiLinkRef=(JmiLinkRef) topMember;
    		miTmps=jmiLinkRefManager.getLinkRefbyLinkNo(jmiLinkRef.getJmiMember().getUserCode(), "userCode", "");
    	}else if(topMember instanceof JmiRecommendRef){
    		netType="recommend";
    		JmiRecommendRef jmiRecommendRef=(JmiRecommendRef) topMember;
    		miTmps=jmiRecommendRefManager.getJmiRecommendRefsByRecommendNo(jmiRecommendRef.getJmiMember().getUserCode());
    	}
    	
    	
    	if(maxLevel==0){
    		level = -1;
    	}else{
        	level++;
    	}
    	if(miTmps!=null && level <= maxLevel){
    		for(int i=0;i<miTmps.size();i++){

    				Object objTmp = miTmps.get(i);
	    			if("link".equals(netType)){
	    				JmiLinkRef miLinkRefTmp = (JmiLinkRef) miTmps.get(i);
	    				miLinkRefTmp.setLayerId(new Long(level));
	    			}else if("recommend".equals(netType)){
	    				JmiRecommendRef miRecommendRefTmp = (JmiRecommendRef) miTmps.get(i);
	    				miRecommendRefTmp.setLayerId(new Long(level));
	    			}
    				list.add(objTmp);
    				getNetTree(list, objTmp, maxLevel, level);
    		}
    	}
    }
	public Integer getRemainUpdateDays(JsysUser defSysUser) {
			
		int remainUpdateDays=0;
		   String num = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("member_upgrade_day");
		     Map map=new HashMap();
		     map.put("userCode", defSysUser.getUserCode());
		     map.put("status", "2");
		     map.put("orderType", "2");
		     map.put("companyCode", defSysUser.getCompanyCode());
		     List sjList=jpoMemberOrderManager.getOrderByParam(map);
		     
		     if((defSysUser.getJmiMember().getCheckDate()==null&&defSysUser.getJmiMember().getCreateTime()==null) || (sjList!=null&&!sjList.isEmpty())){
		    	 remainUpdateDays=0;
		     }else{
		    	 // 查询首购单的审核时间
		    	 String checkTime = this.getJmiMemberCheckOrCreateTime(defSysUser.getUserCode());
		         //给日期定义一种格式
		    	 DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		    	 Date checkDate = null;
		    	 try{
		    	     checkDate = format1.parse(checkTime);
		    	 }catch (ParseException e) {
		    	     e.printStackTrace();
		    	 }
		    	 //查询当前时间与升级截止时间直接的时间差
		    	 //DateUtil.getDateOffset(checkDate, 5, StringUtil.formatInt(num) 计算升级后的时间点
		    	 int days = DateUtil.daysBetweenDates(DateUtil.getDateOffset(checkDate, 5, StringUtil.formatInt(num)), new Date());
		    	 //判断当前系统的时间是否晚于升级后的时间点
		    	 if(new Date().after(DateUtil.getDateOffset(checkDate, 5, StringUtil.formatInt(num)))){
		    		 //如果当前系统时间晚于升级后的时间点,任意定义一个负数，表明是现在这种情况
		    		 days = -1;
		    	 }
		    	 //向页面传值－剩余升级天数
		    	 //如何还有剩余的升级天数
	             if(days>=0){
	            	 remainUpdateDays = days;
		         }
	             //这里是没有剩余的升级天数
	             else{
	            	 remainUpdateDays=0;
		         }
		     }
		   //cardType为６，表明该会员是ＶＩＰ级别（最高级别），那么他的剩余升级天数就一直是零．
		   //0代表是待审会员
		   String cardType = defSysUser.getJmiMember().getCardType();
		   if("6".equals(cardType)||"0".equals(cardType)){
			   remainUpdateDays = 0;
		   }
		     
		return remainUpdateDays;
	}
	public boolean getCheckEcMallPhone(JmiMember miMember) {
		return jmiMemberDao.getCheckEcMallPhone(miMember);
	}
	@Override
	public List getJmiClubs(String userCode) {
		return jmiMemberDao.getJmiClubs(userCode);
	}
	
	/*********************************** 身份证验证开始 ****************************************/	
	/**
	 * 身份证号码验证 
	 * 1、号码的结构
	 * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
	 * 八位数字出生日期码，三位数字顺序码和一位数字校验码。
	 * 2、地址码(前六位数） 
	 * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 
	 * 3、出生日期码（第七位至十四位）
	 * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 
	 * 4、顺序码（第十五位至十七位）
	 * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
	 * 顺序码的奇数分配给男性，偶数分配给女性。 
	 * 5、校验码（第十八位数）
	 * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
	 * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
	 * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0
	 * X 9 8 7 6 5 4 3 2
	 */

	/**
	 * 功能：身份证的有效验证
	 * @param IDStr 身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static String IDCardValidate(String IDStr) throws ParseException {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(
						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
			errorInfo = "身份证生日不在有效范围。";
			return errorInfo;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr) == false) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else {
			return "";
		}
		// =====================(end)=====================
		return "";
	}

	/**
	 * 功能：设置地区编码
	 * @return Hashtable 对象
	 */
	@SuppressWarnings("unchecked")
	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 功能：判断字符串是否为数字
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：判断字符串是否为日期格式
	 * @param str
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
//	public static void main(String[] args) throws ParseException {
//		// String IDCardNum="210102820826411";
//		// String IDCardNum="210102198208264114";
//		String IDCardNum = "500113198606245216";
//		CommonUtil cc = new CommonUtil();
//		System.out.println(cc.IDCardValidate(IDCardNum));
//		// System.out.println(cc.isDate("1996-02-29"));
//	}
/*********************************** 身份证验证结束 ****************************************/
	public Map getBonsuStar(String week,String weekType ,String type,JsysUser jSysUser){

		
/*		JsysUser jSysUser = (JsysUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();*/
		
		Map indexMap=new HashMap();
		String weeksql="";
		if("1".equals(type)){
			weeksql=" w_week=( Select Concat(Max(w_Year), Lpad(Max(w_Month), 2, 0))  From Jbd_Period Where End_Time >= sysdate And Start_Time <= sysdate)";

			//String sql = "SELECT column_value FROM TABLE(jbd_dealer_statistics('"+ jSysUser.getUserCode() + "'))";
			String sql = "SELECT column_value FROM TABLE(FN_GET_HOMEPAGE_INFO('"+ jSysUser.getUserCode() + "','"+week+"','"+weekType+"'))";
			
			List jmiRecommendRefs = this.jdbcTemplate3.queryForList(sql);
			Map map = (Map) jmiRecommendRefs.get(0);
			String[] strTemp = map.get("column_value").toString().split(",");

			if(strTemp.length==21){

				indexMap.put("cur_member_level", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "member.level", strTemp[0]) );// 目前销售级别
				indexMap.put("next_member_level", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "member.level", strTemp[1]));// 下级奖衔

				indexMap.put("pre_min_total", strTemp[2]);// 上期小区累计业绩
				indexMap.put("cur_min_total", strTemp[3]);// 目前小区累计业绩
				indexMap.put("cur_month_add", strTemp[4]);// 目前当月小区新增业绩
				indexMap.put("min_need_pv", strTemp[5]);// 小区还需完成业绩
				indexMap.put("pre_max_total", strTemp[6]);// 上期大区累计业绩
				indexMap.put("cur_max_total", strTemp[7]);// 目前大区累计业绩
				indexMap.put("cur_month_max_add", strTemp[8]);// 目前当月大区新增业绩
				indexMap.put("max_need_pv", strTemp[9]);// 大区还需完成业绩
				
				indexMap.put("cur_pass_star", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "pass.star.zero", strTemp[10]));// 目前的奖衔
				indexMap.put("next_pass_star", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "pass.star.zero", strTemp[11]));// 完成如下业绩可升至奖衔

				indexMap.put("cur_pa_duty", strTemp[12]);// 目前个人责任消费
				indexMap.put("need_pa_duty", strTemp[13]);// 还需完成责任消费
				
				indexMap.put("checkStime", strTemp[14]);
				
				if(strTemp[15]!=null){
		    		String leaderTime = "0";
					try{
			    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    			Date date = sdf.parse((strTemp[15]));
		    			leaderTime = dateFormat.format(date.getTime()-1000l);
		    		}catch(Exception e){
		    			//e.printStackTrace();
		    		}
					indexMap.put("checkEndTime", leaderTime);// 考核结束时间
				}
				

				indexMap.put("max_pv_need", strTemp[17]);
				indexMap.put("min_pv_need", strTemp[16]);

				indexMap.put("actual_max_pv", strTemp[18]);//实际大区业绩
				indexMap.put("actual_min_pv", strTemp[19]);//实际小区业绩
				indexMap.put("is_policy_month", strTemp[20]);//是否政策月（1为是，0为否）

				
	/*			indexMap.put("bigRegion", strTemp[8]);// 大区业绩
				indexMap.put("needBigRegion", strTemp[9]);// 需要完成大区业绩
				indexMap.put("multipleRegion", strTemp[10]);// 双倍小区业绩 
				indexMap.put("multipleLevel", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "pass.star.zero", strTemp[11]));// 双倍业绩奖衔
				indexMap.put("Month_Group_Pv", strTemp[12]);// 双倍业绩奖衔
				indexMap.put("cardType", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "bd.cardtype", strTemp[13]));// 级别
				
				
				//2015-3-26 新制度改版 
				indexMap.put("member_level", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "member.level", strTemp[14]));// 级别
				indexMap.put("next_member_level", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "member.level", strTemp[15]));//完成业绩可达成级别
				indexMap.put("pre_pv", strTemp[16]);//上期业绩
				indexMap.put("cur_total_pv", strTemp[17]);
				indexMap.put("cur_month_add_pv", strTemp[18]);
				indexMap.put("need_pv", strTemp[19]);
				
				//==================
*/				

			
				
				//=========
			}
		}else if("2".equals(type)){

			weeksql=" w_week=(  Select Fn_Get_Before_Month(Max(w_Year), Max(w_Month), 2) From Jbd_Period Where End_Time >= sysdate And Start_Time <= sysdate)";
			
			//String before_sql = "SELECT column_value FROM TABLE(Jbd_Dealer_Before('"+ jSysUser.getUserCode() + "'))";
			String before_sql = "SELECT column_value FROM TABLE(FN_GET_HOMEPAGE_INFO('"+ jSysUser.getUserCode() + "','"+week+"','"+weekType+"'))";

			List before_list = this.jdbcTemplate3.queryForList(before_sql);
			Map before_map = (Map) before_list.get(0);
			String[] before_strTemp = before_map.get("column_value").toString().split(","); 

			if(before_strTemp.length==21){
				indexMap.put("b_cur_member_level", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "member.level", before_strTemp[0]) );// 目前销售级别
				indexMap.put("b_next_member_level", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "member.level", before_strTemp[1]));// 下级奖衔

				indexMap.put("b_pre_min_total", before_strTemp[2]);// 上期小区累计业绩
				indexMap.put("b_cur_min_total", before_strTemp[3]);// 目前小区累计业绩
				indexMap.put("b_cur_month_add", before_strTemp[4]);// 目前当月小区新增业绩
				indexMap.put("b_min_need_pv", before_strTemp[5]);// 小区还需完成业绩
				indexMap.put("b_pre_max_total", before_strTemp[6]);// 上期大区累计业绩
				indexMap.put("b_cur_max_total", before_strTemp[7]);// 目前大区累计业绩
				indexMap.put("b_cur_month_max_add", before_strTemp[8]);// 目前当月大区新增业绩
				indexMap.put("b_max_need_pv", before_strTemp[9]);// 大区还需完成业绩
				
				indexMap.put("b_cur_pass_star", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "pass.star.zero", before_strTemp[10]));// 目前的奖衔
				indexMap.put("b_next_pass_star", ListUtil.getListValue(jSysUser.getDefCharacterCoding(), "pass.star.zero", before_strTemp[11]));// 完成如下业绩可升至奖衔

				indexMap.put("b_cur_pa_duty", before_strTemp[12]);// 目前个人责任消费
				indexMap.put("b_need_pa_duty", before_strTemp[13]);// 还需完成责任消费
				
			
				
				indexMap.put("b_checkStime", before_strTemp[14]);
				
				if(before_strTemp[15]!=null){
		    		String leaderTime = "0";
					try{
			    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    			Date date = sdf.parse((before_strTemp[15]));
		    			leaderTime = dateFormat.format(date.getTime()-1000l);
		    		}catch(Exception e){
		    			//e.printStackTrace();
		    		}
					indexMap.put("b_checkEndTime", leaderTime);// 考核结束时间
				}
				

				indexMap.put("b_max_pv_need", before_strTemp[17]);
				indexMap.put("b_min_pv_need", before_strTemp[16]);
				
				

				indexMap.put("b_actual_max_pv", before_strTemp[18]);//实际大区业绩
				indexMap.put("b_actual_min_pv", before_strTemp[19]);//实际小区业绩
				indexMap.put("b_is_policy_month", before_strTemp[20]);//是否政策月（1为是，0为否）
			}
		}
		
		
		String sql2="select * from jbd_day_fb_list where   user_code='"+ jSysUser.getUserCode()+"'";
		
		List fb_list=this.jdbcTemplate3.queryForList(sql2);
		
		indexMap.put("fb_list", fb_list);

		String recommend_list_sql="select * from JBD_DAY_RECOMMEND_LIST where   user_code='"+ jSysUser.getUserCode()+"'";
		List recommend_list=this.jdbcTemplate3.queryForList(recommend_list_sql);
		indexMap.put("recommend_list", recommend_list);
		//indexMap.put("system_Upgrade_Day", this.getRemainUpdateDays(jSysUser));
		return indexMap;
	}
	
	public Map getBonsuStarView(String week,String weekType ,String type) {

			JsysUser jSysUser = (JsysUser) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
		
		return getBonsuStar( week, weekType ,type,jSysUser);
	}
	
	/**
	 * 
	 * @param origJmiMember 原会员
	 * @param qty 数量	
	 * @param defSysUser 操作人
	 * @return
	 */
	public List generateMultiMember(JmiMember origJmiMember,Integer qty,JsysUser defSysUser){
		
		List list=new ArrayList();
		for (int i = 0; i < qty; i++) {

			JmiMember genMember=new JmiMember();
			copyProperties(genMember, origJmiMember);
	    	
	    	String userCode=this.saveNewMiMember(genMember, defSysUser,"1");
	    	//System.out.println(userCode);
	    	list.add(userCode);
	    	
		}
		
		
    	
    	
/*		JmiMember genMember1=new JmiMember();
    	
		ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
		ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);   
		try {
			BeanUtils.copyProperties(genMember1,jmiMember);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("复制会员失败");
		}
    	
    	
    	System.out.println(genMember1);*/
    	
		return list;
	}
	
	private void copyProperties(JmiMember dest,JmiMember orig){
		
		JmiLinkRef miLinkRef = new JmiLinkRef();
    	miLinkRef.setLinkJmiMember(new JmiMember());
    	
    	JmiRecommendRef miRecommendRef = new JmiRecommendRef();
    	miRecommendRef.setRecommendJmiMember(new JmiMember());

    	dest.setCompanyCode(orig.getCompanyCode());
    	dest.setJmiRecommendRef(miRecommendRef);
    	dest.setJmiLinkRef(miLinkRef);
    	dest.setSysUser(new JsysUser());
		
    	
    	dest.getJmiRecommendRef().getRecommendJmiMember().setUserCode(orig.getUserCode());
    	dest.getJmiLinkRef().getLinkJmiMember().setUserCode(orig.getUserCode());

    	
    	dest.setFirstName(orig.getFirstName());
    	dest.setLastName(orig.getLastName());
    	dest.setFirstNamePy(orig.getFirstNamePy());
    	dest.setLastNamePy(orig.getLastNamePy());
    	dest.setPetName(orig.getPetName());
    	dest.setSex(orig.getSex());
    	dest.setPapertype(orig.getPapertype());
    	dest.setPapernumber(orig.getPapernumber());
    	dest.setProvince(orig.getProvince());
    	dest.setCity(orig.getCity());
    	dest.setDistrict(orig.getDistrict());
    	dest.setAddress(orig.getAddress());
    	dest.setClAddress(orig.getClAddress());
    	dest.setPostalcode(orig.getPostalcode());
    	dest.setPhone(orig.getPhone());
    	dest.setMobiletele(orig.getMobiletele());
    	dest.setFaxtele(orig.getFaxtele());
    	dest.setEmail(orig.getEmail());
    	dest.setBirthday(orig.getBirthday());
    	
    	
    	//--银行信息
    	dest.setBank(orig.getBank());
    	dest.setBankaddress(orig.getBankaddress());
    	dest.setBankcard(orig.getBankcard());
    	dest.setBankbook(orig.getBankbook());
    	dest.setBankProvince(orig.getBankProvince());
    	dest.setBankCity(orig.getBankCity());
    	//
    	dest.getSysUser().setPassword(orig.getSysUser().getPassword());
    	dest.getSysUser().setPassword2(orig.getSysUser().getPassword2());
	}
	
	
	/**
	 * sql 查是否能下首单
	 * @param jmiMember
	 * @return
	 */
	public Integer getIsNoFirst(JmiMember jmiMember) {
		return jmiMemberDao.getIsNoFirst(jmiMember);
	}
	
	public String getPromotionTips(String userCode){
		
		
		String sql1="select * from jmi_special_list where user_code='"+userCode+"' and sc_type='1'";
		List scList = this.jdbcTemplate3.queryForList(sql1);
		if(!scList.isEmpty()){
			return "您已达成生态家大套餐资格"; 
		}
		
	/*	LinkedHashMap<String, String> map1 = ListUtil.getListOptions("AA", "tipes.45");
  	    Collection<String> c = map1.values();
  	    for(String s:c){
  	    	if(s.equals(userCode)){
  	    		return "您已达成生态家大套餐资格"; 
  	    	}
  	    }
		*/
		String sql = "select t.productflag from jpo_member_order t where  t.user_code='"+userCode+"' and t.order_type='1' and t.status='2' and t.productflag in ('45','65')";
		List list = this.jdbcTemplate3.queryForList(sql);
		if(!list.isEmpty()){
			Map map=(Map) list.get(0);
			String productflag=map.get("productflag").toString();
			if("45".equals(productflag)){
				return "您已达成生态家大套餐资格";
			}
/*			else if("65".equals(productflag)){
				return "恭喜，您已达成幸福套餐资格";
			}*/
		}
		return "";
	}
	
	public Map getSamePapernumberUserCode(String papernumber){
		String sql="select t.user_code,t.ec_mall_phone from jmi_member  t where t.papernumber='"+papernumber+"' and t.ec_mall_status='1'";
		List list = this.jdbcTemplate.queryForList(sql);
		if(!list.isEmpty()){
			Map map=(Map) list.get(0);
			return map;
		}
		return null;
	}
    @Override
    public List getJmiMemberForGHB(String userCode, String phone)
    {
        String sql="select t.user_code,t.mi_user_name,t.mobiletele,t.sex,t.papernumber,t.exit_date,t.active,t.isstore from jmi_member t where t.user_code='?' or t.MOBILETELE='?'";
        return this.jdbcTemplate.queryForList(sql,userCode,phone);
    }

	@Override
	public List findJmiMemberById(String userCode) {
		// TODO Auto-generated method stub
		return jmiMemberDao.findJmiMemberById(userCode);
	}

	/**
	 * 密码重置验证(手机端)
	 * @param userCode：会员编号
	 * @param parpeNumber：证件号
	 * @param mobiletele：手机号
	 * @return：
	 * 0：重置密码成功，并发送短信
	 * 1：会员编号不存在   2：证件号不存在   3：手机号不存在   4：会员编号&证件号&手机号不匹配  
	 * -1：会员编号&证件号&手机号填写成功，但调用短信平台异常(注释：本地调用短信平台无法测试，只能再香港测试机才可以测试)
	 * @throws Exception 
	 */
	public String checkJmiMemberPwdResetMobile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userCode = request.getParameter("userId");
		String cardNumber = request.getParameter("cardNumber");
		String mobilePhone = request.getParameter("mobilePhone");
		
		String returnStr = "0";
		//1.校验会员编号、证件号、身份证号是否存在
		returnStr = jmiMemberDao.checkJmiMemberPwdResetMobile(userCode, cardNumber, mobilePhone);
		
		if("1".equals(returnStr)){//
			setHttpMsg("e005","会员编号"+userCode+"不存在!",response);
			return "1";
		}else if("2".equals(returnStr)){//
			setHttpMsg("e005","证件号"+cardNumber+"不存在!",response);
			return "2";
		}else if("3".equals(returnStr)){//
			setHttpMsg("e005","手机号"+mobilePhone+"不存在!",response);
			return "3";
		}else if("4".equals(returnStr)){//
			setHttpMsg("e005","会员编号&证件号&手机号不匹配!",response);
			return "4";
		}else if("0".equals(returnStr)){//如果匹配结果正确，则调用发送短信
			try{			
				//调用发送短信接口
				//Modify By WuCF 20140115 手机短信发送密码修改
				//获得路径URL1,URL2
				//如果手机号格式不正确或不存在，那可能发送不成功，则只能找客服重置密码了！目前按照假设手机号都是正确的情况处理
				String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
				String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
				if(mobilePhone!=null){
					mobilePhone = mobilePhone.trim();
				}
				String tempPwd = this.getStr();//随机生成密码
				String md5NewPassword = StringUtil.encodePassword(tempPwd, "md5");
				JmiMember jmiMember = jmiMemberDao.get(userCode);
				jmiMember.getSysUser().setPassword(md5NewPassword);
				jmiMember.getSysUser().setToken("");
				jmiMemberDao.updatePwdRandom(jmiMember);
				
				String msgInfo  = "您的密码重置成功！   得到新密码："+ tempPwd +" ,可进入系统自行修改密码！";
				//调用短信发送平台方法
				String sendResult=SmsSend.sendSmsPassWord(tempPwd,mobilePhone);
				//Modify By WuCF 将短信写入到数据库表
				JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
				jpmSmssendInfo.setSmsType("2");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
				jpmSmssendInfo.setSmsRecipient(userCode);//短信接收人:用户会员编号
				jpmSmssendInfo.setSmsMessage(msgInfo);//短信内容
				jpmSmssendInfo.setSmsTime(new Date());//发送时间
				jpmSmssendInfo.setSmsOperator(userCode);//操作人，可以填写空
				if(StringUtil.isEmpty(sendResult)){
					jpmSmssendInfo.setSmsStatus("1");
				}else {
					//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
					jpmSmssendInfo.setSmsStatus("0");
				}
				jpmSmssendInfo.setRemark("会员中心手机端找回密码");//备注
				jpmSmssendInfo.setPhoneNum(mobilePhone);//手机号
				jpmProductSaleNewDao.saveJpmSmssendInfo(jpmSmssendInfo);//记录日志
				
				//调用短信发送平台方法
				//SmsSend.sendSms(url1, url2, mobilePhone, msgInfo);

			}catch(Exception e){
				e.printStackTrace();
				setHttpMsg("e005","短信发送过程中异常!",response);
				return "-1";
			}
		}
		setHttpMsg("s000","重置密码已经发送到您的手机，请注意查收!",response);
		//如果匹配结果不对，则直接返回
		return returnStr;
	}
	
	private void setHttpMsg(String code,String msg, HttpServletResponse response) throws Exception{
    	HttpMsg hm = new HttpMsg();
    	hm.setCode(code);
		hm.setMsg(msg);
		log.info("在JmiMemberManagerImpl类的setHttpMsg方法中运行：code为"+code+"msg为"+msg);
		writeGridDataModelJson(response,hm);
    }
	
	/**
	 * 模块单对象页面输出格式
	 * @param response
	 * @param m 结果集
	 * @throws IOException
	 */
	public <T> void writeGridDataModelJson(HttpServletResponse response,T t) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String writeJson = mapper.writeValueAsString(t);
		response.setCharacterEncoding("gbk");
		log.info("在JmiMemberManagerImpl类的writeGridDataModelJson方法中运行");
		response.getWriter().print(writeJson);
    }
	
	/**
	 *产生 随机码
	 */
	private final  String[] str = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s",
			"d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n",
	"m" };

	public String getStr() {
		String s = "";
		for (int i = 0; i < 8; i++) {
			int a=(int)(Math.random()*36);
			s+=str[a];
		}
		return s;
	}
	
	public void saveJmiMemberBankAndLog(JmiMember member,
			JmiMemberLog jmiMemberLog) {
		// TODO Auto-generated method stub
		jmiMemberLogDao.save(jmiMemberLog);
		jmiMemberDao.saveJmiMemberBankInformationChange(member);
	}

	public boolean upgradePVByOrderMemberLevelCofig(Integer memberLevel,BigDecimal orderPv) {
		boolean isup = false;
		log.info("memberLevel:"+memberLevel+" and orderPV:"+orderPv);
		StringBuffer sql=new StringBuffer( "select count(1) from JBD_ORDER_LEVEL_CONFIG t where  1=1 ");
		sql.append(" and t.expiry_time > trunc(sysdate)	and t.effective_time <= trunc(sysdate)");
		sql.append(" and t.old_member_level = "+memberLevel+" and min_pv <="+orderPv);
		int count=jdbcTemplate.queryForInt(sql.toString());
		if(count<=0){
			isup=true;
		}
		return isup;
	}

	public boolean upgradePV(int gradeType,BigDecimal orderPv) {

		String viplever = ConfigUtil.getConfigValue("CN", Constants.CARD_VIP_PV);
		String yinLevel = ConfigUtil.getConfigValue("CN", Constants.CARD1_PV);
		String jinLevel = ConfigUtil.getConfigValue("CN", Constants.CARD2_PV);
		String zuanLevel = ConfigUtil.getConfigValue("CN", Constants.CARD3_PV);
		String tgyLevel = ConfigUtil.getConfigValue("CN", Constants.CARD7_PV);
		
		log.info("yinLever is:"+yinLevel +" and jinLevel is:"+jinLevel+" "
				+ "and zuanLevel is:"+zuanLevel);
		BigDecimal zuanBig = new BigDecimal(zuanLevel);
		BigDecimal jinBig = new BigDecimal(jinLevel);
		BigDecimal yinBig = new BigDecimal(yinLevel);
		BigDecimal vipBig = new BigDecimal(viplever);
		BigDecimal tgyBig = new BigDecimal(tgyLevel);
		BigDecimal sub_pv = new BigDecimal(0);
		//BigDecimal subPV_1 = new BigDecimal(0);
		
		boolean isup = false;
		log.info("gradeType:"+gradeType+" and orderPV:"+orderPv);
		
		switch (gradeType) {
			case 5000://Constants.CARDTYPE_5000:
				isup = true;
				break;
			case 4000://Constants.CARDTYPE_4000:
				sub_pv = zuanBig.subtract(jinBig);
				log.info("subPV_2 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 3000://Constants.CARDTYPE_3000:
				sub_pv = jinBig.subtract(yinBig);
				log.info("subPV_1 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 1500://Constants.CARDTYPE_1500:
				sub_pv = yinBig.subtract(tgyBig);
				log.info("subPV_1500 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 2500:
				sub_pv = tgyBig.subtract(vipBig);
				log.info("subPV_2500 is:"+sub_pv);
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 1000://Constants.CARDTYPE_1000:
				if(orderPv.compareTo(tgyBig) < 0) isup = true;
				break;
			case 2000://Constants.CARDTYPE_2000:
				if(orderPv.compareTo(tgyBig) < 0) isup = true;
				break;
			default:
				isup = true;
				break;
		}
		return isup;
	}

	
	public boolean getIsInfoEmpty(String userCode){
		JmiMember jmiMember=this.get(userCode);
		if(StringUtil.isEmpty(jmiMember.getProvince())&& StringUtil.isEmpty(jmiMember.getCity()) && StringUtil.isEmpty(jmiMember.getDistrict())
				&& StringUtil.isEmpty(jmiMember.getAddress()) &&  StringUtil.isEmpty(jmiMember.getPetName()) &&  StringUtil.isEmpty(jmiMember.getMobiletele())
				&& StringUtil.isEmpty(jmiMember.getFirstName())&& StringUtil.isEmpty(jmiMember.getLastName())){
			return true;
		}
		return false;
	}
	
	public void saveJmiMemberAndFiAddr(JmiAddrBook jmiAddrBook,Map map){

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		jmiAddrBookManager.saveJmiAddrBoookAndFiAddr(jmiAddrBook);
		if(map!=null){
			JmiMember jmiMember=this.get(defSysUser.getUserCode());
			jmiMember.setProvince(map.get("idno_province").toString());
			jmiMember.setCity(map.get("idno_city").toString());
			jmiMember.setDistrict(map.get("idno_district").toString());
			jmiMember.setAddress(map.get("idno_address").toString());
			jmiMember.setPetName(map.get("petName").toString());
			jmiMember.setMobiletele(map.get("idno_mobiletele").toString());
			jmiMember.setFirstName(map.get("idno_firstName").toString());
			jmiMember.setLastName(map.get("idno_lastName").toString());
			this.getSetUserName(jmiMember);
			jmiMember.setBankbook(jmiMember.getSysUser().getUserName());
			this.save(jmiMember);
		}
		
	}
	
	public boolean getIsSameName(String firstName,String lastName){

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	JmiMember jmiMember=this.get(defSysUser.getUserCode());
    	String papernumberUserCode = "";
    	HashMap map = null;
		List list=jmiMemberDao.getPapernumberUserCode(jmiMember);
		if(!MeteorUtil.isBlankByList(list)){
			for(int i=0;i<list.size();i++){
				map = (HashMap) list.get(i);
				papernumberUserCode = String.valueOf(map.get("USER_CODE"));
				if(!StringUtil.isEmpty(papernumberUserCode)){
					JmiMember jmiMemberPapernumber=jmiMemberDao.get(papernumberUserCode);
					String curRegisterUserName=(firstName+lastName);
					if(!curRegisterUserName.equals(jmiMemberPapernumber.getMiUserName()) && !MeteorUtil.isBlank(jmiMemberPapernumber.getMiUserName())){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public String getCloudshopPhoneByUserCode(String userCode) {
		return jmiMemberDao.getCloudshopPhoneByUserCode(userCode);
	}
	@Override
	public boolean getCloudshopPhoneIsExist(String cloudshopPhone) {
		return jmiMemberDao.getCloudshopPhoneIsExist(cloudshopPhone);
	}
	@Override
	public void updateCloudshopPhone(String userCode, String cloudshopPhone) {
		jmiMemberDao.updateCloudshopPhone(userCode, cloudshopPhone);
		
	}
	
	/**
	 * 修改手机账号(手机端)
	 * @throws Exception 
	 */
	@Override
	public void modifyCloudshopPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userCode = request.getParameter("userId");//登录用户的用户编码
		String modifyPhone = request.getParameter("cloudshopPhone");//要修改的云店账号
		JmiMember jmiMember = jmiMemberDao.get(userCode);
		String mobiletele = jmiMember.getMobiletele();//手机号
		
		//原来的云店账号
    	String beforePhone = jmiMemberDao.getCloudshopPhoneByUserCode(userCode);
    	
    	//校验
    	if (StringUtil.isEmpty(modifyPhone)) {
    		//要修改的手机号为空
    		setHttpMsg("e005","要修改的手机号为空",response);
			return ;
		}
    	//程序能走到这里，说明要修改的手机号不为空
    	Pattern pattern = Pattern.compile("^[0-9]{11}");
		Matcher matcher = pattern.matcher(modifyPhone);
    	if(!matcher.matches()){
			//要修改的手机号码格式不对
			setHttpMsg("e005","要修改的手机号码格式不对",response);
			return ;
		}else if(StringUtils.isEmpty(beforePhone)){
			//原云店账号不存在
			setHttpMsg("e005","原云店账号不存在",response);
			return ;
		}else if(beforePhone.equals(modifyPhone)){
			//要修改的云店账号跟原有账号一致
			setHttpMsg("e005","要修改的云店账号跟原有账号一致",response);
			return ;
		}else if(jmiMemberDao.getCloudshopPhoneIsExist(modifyPhone)){
			//要修改的云店账号已被占用
			setHttpMsg("e005","要修改的云店账号已被占用",response);
			return ;
		}else if(StringUtils.isEmpty(mobiletele)){
			//现手机号不存在
			setHttpMsg("e005","现手机号不存在",response);
			return ;
		}else if(!mobiletele.equals(modifyPhone)){
			//要修改的云店账号跟现在的手机号不一致
			setHttpMsg("e005","要修改的云店账号必须跟现在的手机号一致",response);
			return ;
		}
    	
    	
    	//-----------------校验合格，执行修改----------------
    	jmiMemberDao.updateCloudshopPhone(jmiMember.getUserCode(),modifyPhone);
		setHttpMsg("s000","修改成功",response);
		return ;

	}
	
	/**
	 * 移动挂网
	 */
	@Override
	public Map getCheckMiMemberLinkNo(JmiMember jmiMember, JsysUser defSysUser) throws Exception {
		Map resultMap=new HashMap();
		String sql="select USER_CODE,PET_NAME from jmi_member where link_no='"+jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode()+"' order by create_time asc";
		List jmiLinkRefs=jdbcTemplate.queryForList(sql);
		if (!MeteorUtil.isBlankByList(jmiLinkRefs)) {
			if (jmiLinkRefs.size()==1) {
				resultMap.put("returnCode", "3");
				resultMap.put("message", "CN号验证成功，该用户下只有一名安置人");
				Map map=(Map)jmiLinkRefs.get(0);
				resultMap.put("left_cn", map.get("user_code"));
				resultMap.put("left_nickName", map.get("pet_name"));
				resultMap.put("right_cn", "");
				resultMap.put("right_nickName", "");
			}else if(jmiLinkRefs.size()==2){
				resultMap.put("returnCode", "4");
				resultMap.put("message", "CN号验证成功，该用户下有两名安置人");
				Map map=(Map)jmiLinkRefs.get(0);
				resultMap.put("left_cn", map.get("user_code"));
				resultMap.put("left_nickName", map.get("pet_name"));
				map=(Map)jmiLinkRefs.get(1);
				resultMap.put("right_cn", map.get("user_code"));
				resultMap.put("right_nickName", map.get("pet_name"));
			}else{
				//异常
				resultMap.put("returnCode", "1");
				resultMap.put("message", "CN号验证失败");
				resultMap.put("left_cn", "");
				resultMap.put("left_nickName", "");
				resultMap.put("right_cn", "");
				resultMap.put("right_nickName", "");
			}
		}else{
			//异常
			resultMap.put("returnCode", "2");
			resultMap.put("message", "CN号验证成功，该用户下暂时没有安置人");
			resultMap.put("left_cn", "");
			resultMap.put("left_nickName", "");
			resultMap.put("right_cn", "");
			resultMap.put("right_nickName", "");
		}
		return resultMap;
	}
	@Override
	public String getJmiSelectLinkNo(String recommendNo, String selectNo, JsysUser defSysUser) {
		if (StringUtil.isEmpty(recommendNo)){//会员员为空
    		return "";
    	}
    	JmiLinkRef jmiLinkRef = jmiLinkRefManager.get(recommendNo);
    	if (jmiLinkRef ==null){//会员不存在
    		return "";
    	}
        JmiRecommendRef miRecommendRef = jmiRecommendRefManager.get(jmiLinkRef.getJmiMember().getJmiRecommendRef().getUserCode());
		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(defSysUser.getUserCode());
		if(!StringUtil.getCheckIsDownline(defRecommendRef.getTreeIndex(), miRecommendRef.getTreeIndex())){
			return "";
		}
		boolean flag=false;
		List list=jmiLinkRefManager.getLinkRefbyLinkNo(recommendNo, "a.jmiMember.createTime", "");
		for (int i = 0; i < list.size(); i++) {
			JmiLinkRef curJmiLinkRef =(JmiLinkRef) list.get(i);
			if(curJmiLinkRef.getUserCode().equals(selectNo)){
				flag=true;
			}
		}
		if(flag){
			String searchNo=selectNo;
			String teamCode = jmiTeamManager.teamStr(defSysUser);
			/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			String teamCode=request.getSession().getAttribute("teamCode").toString();*/
			if("AJ34272972".equals(teamCode)){
				searchNo=jmiLinkRefManager.getLinkNo(selectNo);
			}else{
				List jmiLinkRefs=jmiLinkRefManager.getLinkRefbyLinkNo(searchNo, "a.jmiMember.createTime", "");
				while (!jmiLinkRefs.isEmpty()) {
					JmiLinkRef curJmiLinkRef =(JmiLinkRef) jmiLinkRefs.get(0);
					jmiLinkRefs=jmiLinkRefManager.getLinkRefbyLinkNo(curJmiLinkRef.getUserCode(), "a.jmiMember.createTime", "");
					searchNo=curJmiLinkRef.getUserCode();
				}
			}
			
		
			
			return searchNo;
		}else{
			return "";
		}
		
		
	}
	@Override
	public Map getJmiSelectLinkNoToApp(String recommendNo, String selectNo, JsysUser defSysUser) {
		Map map = new HashMap();
		if (StringUtil.isEmpty(recommendNo)){//会员员为空
			String msg = "查询失败,推荐人编号不能为空";
			map.put("flag", 0);
			map.put("msg", msg);
    		return map;
    	}
    	JmiLinkRef jmiLinkRef = jmiLinkRefManager.get(recommendNo);
    	if (jmiLinkRef ==null){//会员不存在
    		String msg = "查询失败,推荐人不存在";
			map.put("flag", 0);
			map.put("msg", msg);
    		return map;
    	}
        JmiRecommendRef miRecommendRef = jmiRecommendRefManager.get(jmiLinkRef.getJmiMember().getJmiRecommendRef().getUserCode());
		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(defSysUser.getUserCode());
		if(!StringUtil.getCheckIsDownline(defRecommendRef.getTreeIndex(), miRecommendRef.getTreeIndex())){
			String msg = "查询失败,该推荐人不在当前会员网体之下";
			map.put("flag", 0);
			map.put("msg", msg);
    		return map;
		}
		boolean flag=false;
		List list=jmiLinkRefManager.getLinkRefbyLinkNo(recommendNo, "a.jmiMember.createTime", "");
		for (int i = 0; i < list.size(); i++) {
			JmiLinkRef curJmiLinkRef =(JmiLinkRef) list.get(i);
			if(curJmiLinkRef.getUserCode().equals(selectNo)){
				flag=true;
			}
		}
		if(flag){
			String searchNo=selectNo;
			String teamCode = jmiTeamManager.teamStr(defSysUser);
			/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			String teamCode=request.getSession().getAttribute("teamCode").toString();*/
/*			if("AJ34272972".equals(teamCode)){
				searchNo=jmiLinkRefManager.getLinkNo(selectNo);
				JmiMember jmiMember = jmiMemberDao.get(searchNo);
				searchNo+=","+jmiMember.getPetName();
			}else{

			}*/
			String petName= " ";
			List jmiLinkRefs=jmiLinkRefManager.getLinkRefbyLinkNo(searchNo, "a.jmiMember.createTime", "");
			while (!jmiLinkRefs.isEmpty()) {
				JmiLinkRef curJmiLinkRef =(JmiLinkRef) jmiLinkRefs.get(0);
				jmiLinkRefs=jmiLinkRefManager.getLinkRefbyLinkNo(curJmiLinkRef.getUserCode(), "a.jmiMember.createTime", "");
				searchNo=curJmiLinkRef.getUserCode();
				JmiMember jmiMember = jmiMemberDao.get(searchNo);
				petName = jmiMember.getPetName();
			}
				searchNo+=","+petName;
			map.put("flag", 1);
			map.put("search", searchNo);
    		return map;
		//	return searchNo;
		}else{
			map.put("flag", 1);
			map.put("search", "");
    		return map;
		}
	}
	@Override
	public List getjmiYkSearchList(Map<String,String> params,GroupPage page) {
		return jmiMemberDao.getjmiYkSearchList(params,page);
	}
	
	public JmiMember getJmiMember(String userCode){
		return jmiMemberDao.getJmiMember(userCode);
	}


	public boolean getCheckPapernumber(JmiMember jmiMember,BindingResult errors,JsysUser defSysUser){
		boolean isNotPass=false;
		if(!StringUtil.isEmpty(getCheckIdNoIndex(errors, jmiMember, defSysUser.getUserCode(),jmiMember.getSysUser().getCompanyCode(),jmiMember.getSysUser().getDefCharacterCoding()))){
			isNotPass=true;
		}


		return isNotPass;
	}
	public String getCheckIdNoIndex(BindingResult errors,JmiMember jmiMember,String defUserCode,String companyCode,String defCharacterCoding){

		String errorStr="";

		if (StringUtils.isEmpty(jmiMember.getPapernumber())) {//是否为空
			errorStr=StringUtil.getErrorsFormatCode(errors, "isNotNull", "papernumber", "miMember.papernumber",defCharacterCoding);
		}else if(!jmiBlacklistDao.getCheckJmiBlacklist(jmiMember.getPapertype(), jmiMember.getPapernumber())){//黑名单
			errorStr=StringUtil.getErrorsCode(errors, "idNo.blacklist", "papernumber",defCharacterCoding);
		}else if("1".equals(jmiMember.getPapertype()) && !getIdCardFormatCheckRegisterCN(jmiMember.getPapernumber())){//验证格式
			errorStr=StringUtil.getErrorsCode(errors, "idNo.format", "papernumber",defCharacterCoding);
		}else if("1".equals(jmiMember.getPapertype()) && !StringUtil.isEmpty(getIdCardBirthday(jmiMember.getPapernumber()))
			&&!getCheckBirthday(getIdCardBirthday(jmiMember.getPapernumber()), null, 18)  ){
			errorStr=StringUtil.getErrorsCode(errors, "member.age.tw", "birthday",defCharacterCoding);
		}
		return errorStr;
	}
	public String getCheckIdNoIndex(String papernumber){
		JsysUser sysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JmiMember jmiMember=jmiMemberDao.get(sysUser.getUserCode());
		jmiMember.setPapernumber(papernumber);
		return getCheckIdNoIndex(null, jmiMember, sysUser.getUserCode(), sysUser.getCompanyCode(), sysUser.getDefCharacterCoding());
	}
	public void savePapernumber(String papernumber){
		JsysUser sysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JmiMember jmiMember=jmiMemberDao.get(sysUser.getUserCode());
		if(StringUtil.isEmpty(jmiMember.getPapernumber())){
			jmiMember.setPapernumber(papernumber);
			jmiMemberDao.save(jmiMember);
		}

	}
}