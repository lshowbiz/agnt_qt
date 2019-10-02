package com.joymain.ng.dao.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joymain.ng.util.GroupPage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.util.StringUtil;


@Repository("jmiMemberDao")
public class JmiMemberDaoJpa extends GenericDaoHibernate<JmiMember, String> implements JmiMemberDao {
	 
	//-----会员信息系统－个人资料维护（使用地点gw）
	@Autowired
	private JsysUserRoleManager jsysUserRoleManager;
	 
    public JmiMemberDaoJpa() {
        super(JmiMember.class);
    }
    /**
     * 判断ec商场电话号码是否存在
     * @param miMember
     * @return
     */
	public boolean getCheckEcMallPhone(JmiMember miMember){
		String sqlQuery ="select count(*) as sumphone from JMi_Member m where m.ec_mall_phone='"+miMember.getEcMallPhone()+"' ";
		List list = this.jdbcTemplate.queryForList(sqlQuery);
		Map map = (Map) list.get(0);
		if (!"0".equals(map.get("sumphone").toString())){
			return true;
		}
		return false;
	}
    
    
	/**
	 * 检查身份证编号是否存在
	 * 
	 * @param miMember
	 *            存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberIdNoByMiMember(JmiMember miMember) {
		String sqlQuery = "select count(m.papernumber) as idsum from JMi_Member m where upper(m.papernumber)=upper('"
				+ miMember.getPapernumber() + "')";
		if (!StringUtil.isEmpty(miMember.getUserCode())) {
			sqlQuery += " and m.user_code!='" + miMember.getUserCode() + "'";
		}
		sqlQuery += " and m.papertype='"
				+ miMember.getPapertype()
				+ "' and ((m.member_type = '2' and m.exit_date is null) or (m.member_type != '2' or m.member_type is null))";
		//先判断身份证是否存在，如果存在再进行下一步判断
		List membeList2 = this.jdbcTemplate.queryForList(sqlQuery);
		Map idsum2 = (Map) membeList2.get(0);
		boolean flag=false;
		if (!"0".equals(idsum2.get("idsum").toString())){
				//判断是否在安置体系下
				String linkNo="";
				if(!StringUtil.isEmpty(miMember.getUserCode())){
					linkNo=miMember.getUserCode();
				}else{
					linkNo=miMember.getJmiLinkRef().getLinkJmiMember().getUserCode();
				}
				sqlQuery += " and m.user_code not in ( Select user_code From jmi_member Connect By Nocycle Prior User_Code = Link_No " +
						"Start With User_Code = (select user_code from (select a.user_code, a.link_no, a.papernumber, level " +
						"from jmi_member a where upper(a.papernumber) = upper('"+miMember.getPapernumber()+"') start with user_code = '"+
						linkNo+"' " +
						"CONNECT BY prior a.link_no = user_code order by level desc) c where rownum = 1)) ";
			List membeList = this.jdbcTemplate.queryForList(sqlQuery);
			Map idsum = (Map) membeList.get(0);

			if ("0".equals(idsum.get("idsum").toString())){
				flag=true;//不存在
			}
		}else{
			flag=true;//不存在
		}
		String sqlQuery1 = "select count(m.spouse_idno) as idsum from JMi_Member m where upper(m.spouse_idno)=upper('"
				+ miMember.getPapernumber() + "')";
		// if(!StringUtil.isEmpty(miMember.getUserCode())){
		// sqlQuery1 += " and m.user_code!='" + miMember.getUserCode() + "'";
		// }
		List membeList1 = this.jdbcTemplate.queryForList(sqlQuery1);
		Map idsum1 = (Map) membeList1.get(0);
		if ("0".equals(idsum1.get("idsum").toString())
				&& flag) {
			return false;
		}
		return true;
	}
	/**
	 * 检查配偶身份证编号是否存在
	 * 
	 * @param miMember
	 *            存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberSpouseIdNoByMiMember(JmiMember miMember) {
		String sqlQuery = "select count(m.papernumber) as idsum from JMi_Member m where upper(m.papernumber)=upper('"
				+ miMember.getSpouseIdno()
				+ "') and ((m.member_type = '2' and m.exit_date is null) or (m.member_type != '2' or m.member_type is null))";
		
		sqlQuery += " and m.papertype='" + miMember.getPapertype() + "'";
		List membeList = this.jdbcTemplate.queryForList(sqlQuery);
		Map idsum = (Map) membeList.get(0);

		String sqlQuery1 = "select count(m.spouse_idno) as idsum from JMi_Member m where upper(m.spouse_idno)=upper('"
				+ miMember.getSpouseIdno() + "')";
		if (!StringUtil.isEmpty(miMember.getUserCode())) {
			sqlQuery1 += " and m.user_code!='" + miMember.getUserCode() + "'";
		}
		List membeList1 = this.jdbcTemplate.queryForList(sqlQuery1);
		Map idsum1 = (Map) membeList1.get(0);

		if ("0".equals(idsum1.get("idsum").toString())
				&& "0".equals(idsum.get("idsum").toString())) {
			return false;
		}
		return true;
	}

	/*
	public String getPapernumberUserCode(JmiMember miMember){
		String sql="select user_code from (select a.user_code, a.link_no, a.papernumber, level " +
				"from jmi_member a where upper(a.papernumber) = upper('"+miMember.getPapernumber()+"') " +
				"start with user_code = '"+miMember.getJmiLinkRef().getLinkJmiMember().getUserCode()+"' CONNECT BY prior a.link_no = user_code order by level desc) c where rownum = 1";
		
		Map map= this.findOneObjectBySQL(sql);
		if(map!=null&&!map.isEmpty()){
			return map.get("user_code").toString();
		}else{
			return "";
		}
	}
	*/
	public List getPapernumberUserCode(JmiMember miMember){
		String sql="select user_code from (select a.user_code, a.link_no, a.papernumber, level " +
				"from jmi_member a where upper(a.papernumber) = upper('"+miMember.getPapernumber()+"') " +
				"start with user_code = '"+miMember.getJmiLinkRef().getLinkJmiMember().getUserCode()+"' CONNECT BY prior a.link_no = user_code order by level desc) c where rownum = 1";
		
		List  list = null;
		Map map= this.findOneObjectBySQL(sql);
		if(map!=null&&!map.isEmpty()){
			String uc = map.get("user_code").toString();
			sql="select user_code from (select a.user_code, a.link_no, a.papernumber, level " +
					"from jmi_member a where upper(a.papernumber) = upper('"+miMember.getPapernumber()+"') and a.first_name is not null " +
					"start with user_code = '"+uc+"' CONNECT BY prior user_code = a.link_no order by level desc) c ";
			list  =this.findObjectsBySQL(sql);
		}
		return list;
	}
	
	/**
	 * 会员信息系统服务平台-安置查询/推荐查询
	 * @author gw ylj 
	 * @create time 2013-06-28
	 * @param topUserCode
	 * @param netType
	 * @param layerId
	 */
	public List getNet(String topUserCode,String netType,String layerId){
		//如果layerId有值，那么表示不是初始化时候的查询，那么可以进行查询
			String sql="select s.user_code,s.link_no,s.recommend_no,s.card_type,s.pet_name,level - 1 as layerid,s.exit_date,s.check_date,s.create_time,s.isstore,s.active," +
					" s.freeze_status,s.mi_user_name,s.exit_date,u.state,s.check_date,s.create_time,s.isstore,s.active,s.freeze_Status " +
					" from jmi_member s,jsys_user u where s.user_code=u.user_code ";
			if(StringUtil.isInteger(layerId)){
				sql+=" and level - 1 <=  "+layerId;
			}else{
				//如果layerId为空，那么它就不是整数，就不让其查出结果．这个是针对初始化进页面的．让它初始化进来时是空页面．
				sql+=" and level - 1 <=  '' ";
			}
			sql+=" Connect By Nocycle Prior s.user_code = s."+netType+" Start With s.User_Code = '"+topUserCode+"' ";
			return this.jdbcTemplate.queryForList(sql);
	}
	

	public List getJmiTeamType() {
		return this.jdbcTemplate.queryForList("select * from jmi_team_type");
	}
	

	public List getChildJbdDayBounsCalcBySql(String userCode,String netType, final Integer wweek){
		String sqlQuery="select * from v_jbd_day_bouns_calc where "+netType+"='"+userCode+"' and w_week="+wweek;
		return this.jdbcTemplate.queryForList(sqlQuery);
	}
	
	public Map getJbdDayBounsCalcByUserCode(String userCode, final Integer wweek){
		String sqlQuery="select * from v_jbd_day_bouns_calc where user_code='"+userCode+"' and w_week="+wweek;
		return findOneObjectBySQL(sqlQuery);
	}

	public JmiRecommendRef getTopIndex(String userCode){
		String sqlQuery="from JmiRecommendRef where user_code='"+userCode+"' ";
		return (JmiRecommendRef) this.getObjectByHqlQuery(sqlQuery);
	}
	
	public List getJmiMembers(Map map) {
		String hql="from JmiMember where 1=1";
		Object cardType=map.get("cardType");
		if(cardType!=null){
			hql+=" and cardType ='"+cardType+"'";
		}
		Object createNo=map.get("createNo");
		if(createNo!=null){
			hql+=" and createNo ='"+createNo+"'";
		}
		Object gradeType=map.get("gradeType");
		if(gradeType!=null){
			hql+=" and gradeType ="+gradeType ;
		}
		return this.getSession().createQuery(hql).list();
	}

	/**
	 * 会员基本信息－银行信息修改－初始化查询
	 * @author gw 2013-07-02
	 * @param userCode
	 * @return
	 */
	public JmiMember getJmiMemberBankInformation(String userCode) {
//		String hql = "from JmiMember where userCode = '"+userCode+"'"; 
//		return (JmiMember)this.getObjectByHqlQuery(hql);
		//Modify By WuCF 20140630 查询语句修改成绑定变量
		Query q=getSession().createQuery("from JmiMember where userCode = :userCode ");
		q.setParameter("userCode",userCode);
	    List<JmiMember> list = q.list();
	    if(list!=null && list.size()>=1){	    	   
	    	return (JmiMember)list.get(0);
	    } 
		return null;
	}
    
	/**
	 * 会员信息系统－银行信息修改－修改
	 * @author gw 2013-07-03
	 * @param member
	 * @return 
	 */
	public void saveJmiMemberBankInformationChange(JmiMember member) {
	          this.save(member);
	}

	/**
	 * 会员信息系统－银行信息修改－查出可用的银行--此方法在代码中没有用到
	 * @author gw 2013-07-03
	 * @param companyCode
	 * @return
	 */
	public List getSysBankByCompanyCode(String companyCode){
		String sqlQuery = " select * from JSYS_BANK a where 1=1";
		if (!StringUtils.isEmpty(companyCode)&&!"AA".equals(companyCode)) {
			sqlQuery += " and a.company_Code ='" + companyCode + "'";
		}
		sqlQuery+=" order by order_No";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}

	/**
	 * 会员系统－个人资料维护－查询首购单的审核时间
	 * @author gw 2013-07-08 
	 * @param userCode
	 * @return
	 */
	public String getJmiMemberCheckOrCreateTime(String userCode) {
		String sqlQuery = "select max(CHECK_DATE) log_create_time from jpo_member_order a where a.order_type='1' and a.status='2'";
		sqlQuery += " and user_code='" + userCode + "'";
		Map m = this.findOneObjectBySQL(sqlQuery);
		//此处转换注意　　就是强制转成string
		Object CreateTime = m.get("log_create_time");
		boolean logCreateTime = (null==CreateTime)||"".equals(CreateTime);
		if (logCreateTime) {
			sqlQuery = "select max(CHECK_DATE) check_date,max(CREATE_TIME) create_time from jmi_member m where m.user_code='"
					+ userCode + "'";
			m = this.findOneObjectBySQL(sqlQuery);
			//在做类型转换的之前，要先做不为空的校验，避免空指针异常
			Object checkDate = m.get("check_date");
			boolean checkDateTT = (null==checkDate)||"".equals(checkDate);
			Object checkTime = m.get("create_time");
			boolean checkTimeTT = (null==checkTime)||"".equals(checkTime);
			if (!checkDateTT) {
				return m.get("check_date").toString();
			}
			else if(!checkTimeTT){
				return m.get("create_time").toString();
			}else{
				return "2007-05-01";
			}
		}else{
		      return CreateTime.toString();
		}
	}

	/**
	 * 会员系统－个人资料维护－ 执行保存或修改的操作
	 * @author gw 2013-07-09
	 * @param miMember
	 * @return 
	 */
	public void savePersonalDataMaintenance(JmiMember miMember) {

    	if(!"JP".equals(miMember.getCompanyCode())){
    		miMember.setBankbook(miMember.getSysUser().getUserName());
    	}
    	JsysUserRole cn6SysUserRole=this.getSysUserRoleByUserCode(miMember.getUserCode());
		JsysRole cn6CurSysRole=this.getSysRole(cn6SysUserRole.getRoleId().toString());
		
		if("cn.member.6".equals(cn6CurSysRole.getRoleCode())){
			//如果为J公司的会员，更新角色到激活角色
	        if("2".equals(miMember.getMemberType())||"3".equals(miMember.getMemberType())||"4".equals(miMember.getMemberType())||"6".equals(miMember.getMemberType())){

	    		JsysUserRole sysUserRole= this.getSysUserRoleByUserCode(miMember.getUserCode());
	    		JsysRole curSysRole=this.getSysRole(sysUserRole.getRoleId().toString());
	        	if("cn.member.6".equals(curSysRole.getRoleCode())){
	        		//设置角色
	        		String aa=miMember.getOriCard();
	        		if("4".equals(miMember.getMemberType())){
	        			aa="0";
	        		}
	        		String memberRoleId =(String) Constants.sysConfigMap.get(miMember.getCompanyCode()).get("member_role_id."+aa);
	        		JsysRole memberSysRole=this.getSysRoleByCode(memberRoleId);
	        		sysUserRole.setRoleId(memberSysRole.getRoleId());
	        		jsysUserRoleManager.save(sysUserRole);
	        		miMember.setChangeStatus("1");
	        	}
	    		this.getSetUserName(miMember);
	        }
		} 
		
        //保存或者是修改操作－个人资料维护
        this.save(miMember);
	}

	/**
	 * 个人资料维护－更改姓名
	 * @author gw 2013-07-09
	 * @param jmiMember
	 * @return
	 */
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
		
		if("TW".equals(jmiMember.getCompanyCode())){
			//台湾昵称与名称一样
			jmiMember.setPetName(jmiMember.getMiUserName());
		}
	}
	
	/**
	 * 会员信息系统－个人资料维护－获取用户角色表的对象
	 * @author gw 2013-07-09
	 * @param userCode
	 * @return JsysUserRole
	 */
	public JsysUserRole getSysUserRoleByUserCode(String userCode) {
		return (JsysUserRole)this.getObjectByHqlQuery("from JsysUserRole where userCode='"+userCode+"'");
	}
	
	/**
	 * 会员信息系统－个人资料维护－获取角色表的对象
	 * @author gw 2013-07-09
	 * @param string
	 * @return jsysRole
	 */
	public JsysRole getSysRole(final String string) {
        JsysRole jsysRole = (JsysRole)this.getObjectByHqlQuery("from JsysRole where roleId='"+string+"'");
        return jsysRole;
    }
	
	/**
     * 会员信息系统－个人资料维护－根据角色编码获取对应的角色记录
     * @author gw 2013-07-09
     * @param roleCode
     * @return JsysRole
     */
    public JsysRole getSysRoleByCode(final String roleCode) {
    	return (JsysRole)this.getObjectByHqlQuery("from JsysRole where roleCode='"+roleCode+"'");
    }
    
    
    /* (non-Javadoc)
     * @see com.joymain.ng.dao.JmiMemberDao#checkJmiMemberPwdReset(java.lang.String, java.lang.String, java.lang.String)
     */
    public JmiMember checkJmiMemberPwdReset(String userCode, String papernumber, String mobiletele){
    	String hqlQuery = "from JmiMember j where j.userCode = '" + userCode +"' and j.papernumber = '" + papernumber +"' and j.mobiletele = '"+ mobiletele +"'";
    	System.out.println( hqlQuery);
    	return (JmiMember)this.getObjectByHqlQuery(hqlQuery);
    }
    
    /**
     * @param jMember
     */
    public void updatePwdRandom(JmiMember jMember){
    	this.save(jMember);
    }
    
    /**
	 * 个人资料维护--获取当前登录用户的身份证号码
	 * @author gw 2013-08-29
	 * @param userCode
	 * @return String
	 */
	public String getPapernumberCheck(String userCode) {
//		String hql = "from JmiMember where userCode = '"+userCode+"'"; 
//		return ((JmiMember)this.getObjectByHqlQuery(hql)).getPapernumber();
		//Modify By WuCF 20140630 查询语句修改成绑定变量
		String paperNumber = "";
		Query q=getSession().createQuery("from JmiMember where userCode = :userCode ");
		q.setParameter("userCode",userCode);
	    List<JmiMember> list = q.list();
	    if(list!=null && list.size()>=1){	    	   
	    	paperNumber = ((JmiMember)list.get(0)).getPapernumber();
	    } 
		return paperNumber;
	}
	
	public List getJmiClubs(String userCode){

		String sql="select * from jlb_member where status='1' ";

			sql += " and user_code = '" + userCode + "'";
		return this.jdbcTemplate.queryForList(sql);
		
	}
	
	public Integer getIsNoFirst(JmiMember jmiMember){
		
		String sql="select not_first from jmi_member where user_code='"+ jmiMember.getUserCode()+"' ";
		return this.jdbcTemplate.queryForInt(sql);
		
	}
	
	public JmiMember getJmiMember(String userCode){
		
		String hqlQuery = "from JmiMember j where j.userCode = '" + userCode +"' ";
		return (JmiMember)this.getObjectByHqlQuery(hqlQuery);
		
	}

		
	public List findJmiMemberById(String userCode) {
		// TODO Auto-generated method stub
		List<JmiMember> list = null;
		StringBuffer hql = new StringBuffer(200);
		hql.append(" select a.bank,a.bankaddress,a.bankbook,a.bankcard,a.bank_city,a.bank_province,a.remark from JMI_MEMBER a where 1=1 ");

		if (!StringUtils.isEmpty(userCode)) {
			hql.append(" and a.user_code = '" + userCode + "'" );
		}
		
//		Session session = super.getSession();
//		Query query = session.createSQLQuery(hql.toString());
		Map map = jdbcTemplate.queryForMap(hql.toString());
		if(map.isEmpty()){
			list = new ArrayList<JmiMember>();
		}else{
//			List<Object[]> lists = query.list();
			list = transJmiMemberByMap(map);
		}
		return list;
	}
	
	private List<JmiMember> transJmiMemberByMap(Map map){
		List<JmiMember> list = new ArrayList<JmiMember>();
		if(!map.isEmpty()){
			JmiMember jmiMember = new JmiMember();
			jmiMember.setBank(String.valueOf(map.get("bank")));
			jmiMember.setBankaddress(String.valueOf(map.get("bankaddress")));
			jmiMember.setBankbook(String.valueOf(map.get("bankbook")));
			jmiMember.setBankcard(String.valueOf(map.get("bankcard")));
			jmiMember.setBankCity(String.valueOf(map.get("bank_city")));
			jmiMember.setBankProvince(String.valueOf(map.get("bank_province")));
			
			list.add(jmiMember);
			jmiMember = null;
		}
		 
		return list;
	}

	/**
	 * 密码重置验证(手机端)
	 * @param userCode：会员编号
	 * @param parpeNumber：证件号
	 * @param mobiletele：手机号
	 * @return
	 */
	public String checkJmiMemberPwdResetMobile(String userCode, String cardNumber, String mobilePhone){
		//去空
		if(userCode==null){
			userCode = "";
		}
		if(cardNumber==null){
			cardNumber = "";
		}
		if(mobilePhone==null){
			mobilePhone = "";
		}
		
		String returnStr = "0";//返回值为0，标识返回的结果正确
		JmiMember jmiMember = null;
		
		//1.会员编号不存在
		String hqlQuery = "from JmiMember j where j.userCode = '" + userCode +"' ";
		jmiMember = (JmiMember)this.getObjectByHqlQuery(hqlQuery);
		if(jmiMember == null){
			return "1";
		}
		
		//2.证件号不存在
		hqlQuery = "from JmiMember j where j.papernumber = '" + cardNumber +"' ";
		jmiMember = (JmiMember)this.getObjectByHqlQuery(hqlQuery);
		if(jmiMember == null){
			return "2";
		}
		
		//3.手机号不存在
		hqlQuery = "from JmiMember j where j.mobiletele = '"+ mobilePhone +"'";
		jmiMember = (JmiMember)this.getObjectByHqlQuery(hqlQuery);
		if(jmiMember == null){
			return "3";
		}
		
		//4.会员编号、证件号、手机号不匹配
    	hqlQuery = "from JmiMember j where j.userCode = '" + userCode +"' and j.papernumber = '" + cardNumber +"' and j.mobiletele = '"+ mobilePhone +"' ";
    	jmiMember = (JmiMember)this.getObjectByHqlQuery(hqlQuery);
    	if(jmiMember == null){
			return "4";
		}
    	
    	//5.成功返回0
    	return returnStr;
    }
	
	@Override
	public String getCloudshopPhoneByUserCode(String userCode) {
		String sql="select * from jmi_member where user_code='"+userCode+"'";
		String cloudshopPhone = "";
		
			try {
				List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
				if (list!=null && list.size()>=1) {
					Map<String, Object> map = list.get(0);
					cloudshopPhone = (String) map.get("CLOUDSHOP_PHONE");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		return cloudshopPhone;
	}
	
	@Override
	public boolean getCloudshopPhoneIsExist(String cloudshopPhone) {
		String sql="select * from jmi_member where CLOUDSHOP_PHONE='"+cloudshopPhone+"'";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		if (list!=null && list.size()>=1) {
			return true;
		}
		return false;
	}
	
	@Override
	public void updateCloudshopPhone(String userCode, String cloudshopPhone) {
		String sql="update jmi_member set CLOUDSHOP_PHONE='"+cloudshopPhone+"' where user_code='"+userCode+"'";
		this.getJdbcTemplate().update(sql);
		
	}

	@Override
	public List getjmiYkSearchList(Map<String,String> params,GroupPage page) {
		String userCode=params.get("userCode");
		String ykUserCode=params.get("ykUserCode");
		String mobiletele=params.get("mobiletele");
		if(StringUtils.isNotEmpty(userCode)){
			String sql="select t.*,invite.invite_code from jmi_member t  ,jpo_invite_list invite where t.is_cloudshop='1' and"+
					" t.member_user_type='2' and t.user_code in(SELECT r.user_code"+
					" FROM JMI_RECOMMEND_REF  r START WITH r.user_code = '"+userCode+"'"+
					"CONNECT BY PRIOR r.user_code=r.recommend_no  ) and t.user_code=invite.use_user_code(+) and t.user_code <> '"+userCode+"' ";
			if(StringUtils.isNotEmpty(ykUserCode)){
				sql+=" and t.user_code='"+ykUserCode.trim()+"'";
			}
			if(StringUtils.isNotEmpty(mobiletele)){
				sql+=" and t.mobiletele='"+mobiletele.trim()+"'";
			}
			sql+="order by t.user_code";
			return this.findObjectsBySQL(sql, page);
		}
		return null;
	}
}
