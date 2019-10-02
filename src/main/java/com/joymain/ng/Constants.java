package com.joymain.ng;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.security.access.ConfigAttribute;





/**
 * Constant values used throughout the application.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class Constants {

	private Constants() {
		// hide me
	}

	// ~ Static fields/initializers
	// =============================================

	/**
	 * The name of the ResourceBundle used in this application
	 */
	public static final String BUNDLE_KEY = "ApplicationResources";

	/**
	 * File separator from System properties
	 */
	public static final String FILE_SEP = System.getProperty("file.separator");

	/**
	 * User home from System properties
	 */
	public static final String USER_HOME = System.getProperty("user.home")
			+ FILE_SEP;

	/**
	 * The name of the configuration hashmap stored in application scope.
	 */
	public static final String CONFIG = "appConfig";

	/**
	 * Session scope attribute that holds the locale set by the user. By setting
	 * this key to the same one that Struts uses, we get synchronization in
	 * Struts w/o having to do extra work or have two session-level variables.
	 */
	public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";

	/**
	 * The request scope attribute under which an editable user form is stored
	 */
	public static final String USER_KEY = "userForm";

	/**
	 * The request scope attribute that holds the user list
	 */
	public static final String USER_LIST = "userList";

	/**
	 * The request scope attribute for indicating a newly-registered user
	 */
	public static final String REGISTERED = "registered";

	/**
	 * The name of the Administrator role, as specified in web.xml
	 */
	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	/**
	 * The name of the User role, as specified in web.xml
	 */
	public static final String USER_ROLE = "ROLE_USER";

	/**
	 * The name of the user's role list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String USER_ROLES = "userRoles";

	/**
	 * The name of the available roles list, a request-scoped attribute when
	 * adding/editing a user.
	 */
	public static final String AVAILABLE_ROLES = "availableRoles";
	public static List periodList;

	/**
	 * The name of the CSS Theme setting.
	 * 
	 * @deprecated No longer used to set themes.
	 */
	public static final String CSS_THEME = "csstheme";

	public static Map<String, Map<String, String>> sysConfigMap;
    public static Map<String, Map<String, String[]>> sysListMap;
    public static Map<String, Map<String, String>> localLanguageMap;
    public static Map<String, String> companyMap;
    
	public static Map resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
	public static String SESSION_CURRENT_USER="sessionLogin";

	public static String SESSION_CURRENT_OPERATOR="sessionOperator";

	public static ServletContext context;
	/**
	 * 用户类型M: 会员
	 */
	public static final String MEMBER_M="M";
	/**
	 * 用户类型C: 公司用户
	 */
	public static final String MEMBER_C="C";
	/**
	 * 团队状态 0:可用
	 */
	public static final String TEAM_Y="0";
	/**
	 * 团队状态 1:不可用
	 */
	public static final String TEAM_N="1";
	
	public static boolean isLoad = false;
	public static boolean isLoad() {
		return isLoad;
	}
	public static void setLoad(boolean isLoad) {

		Constants.isLoad = isLoad;
	}
	

	/**
     * <li>促销策略 已激活</li>
     */
    public static final String JPMSALE_ACTIVA="1";
    /**
     * <li>促销策略 未激活</li>
     */
    public static final String JPMSALE_NO_ACTIVA="0";
    /**
     * 策略类型  1:折扣促销
     */
    public static final String JPMSALE_SALETYPE_DISCOUNT="1";
    /**
     * 策略类型  2:赠品促销
     */
    public static final String JPMSALE_SALETYPE_PRE="2";
    /**
     * 策略类型  3:积分赠送促销
     */
    public static final String JPMSALE_SALETYPE_INTEGRAL="3";
    /**
     * 策略类型  4:按订单类型赠送或金额或PV
     */
    public static final String JPMSALE_SALETYPE_ORDER="4";
    /**
     * 生态养生套餐产品编号
     */
    public static final String MOVIE_PRONO="P21010100101CN0";
    /**
     * 生态养生套餐产品编号
     */
    public static final String MOVIE_PRONO2="P21050100101CN0";
    /**P23010100101CN0 1箱*/
    public static final String PRONO="P23010100101CN0";
    /**P23010200101CN0 3箱*/
    public static final String PRONO1="P23010200101CN0";
    /**P23010300101CN0 5箱*/
    public static final String PRONO2="P23010300101CN0";
    
    /** 积分换购限制库存商品 及数量  */
    public static final String PROC="P25090100101CN0";
    public static final Integer PROCnum = 32;
    public static final String PROC1="P25090100201CN0";
    public static final Integer PROC1num = 282;
    public static final String PROC2="P25090100301CN0";
    public static final Integer PROC2num = 140;
    public static final String PROC3="P25100100101CN0";
    public static final Integer PROC3num = 92;
    public static final String PROC4="P25100100201CN0";
    public static final Integer PROC4num = 400;
    public static final String PROC5="P25100100301CN0";
    public static final Integer PROC5num = 214;
    public static final String PROC6="P25110100101CN0";
    public static final Integer PROC6num = 1930;
    public static final String PROC7="P25110100201CN0";
    public static final Integer PROC7num = 192;
    
    /**
	 * 普通会员角色*/
	public static final String JOCS_ROLE_NORMAL="jocs.member.role.normal";
	/**
	 * 优惠顾客*/
	public static final String JOCS_ROLE5="jocs.member.role.5";
	/**
	 * 一级会员 */
	public static final String JOCS_ROLE10="jocs.member.role.10";
	public static final String JOCS_ROLE20="jocs.member.role.20";
	public static final String JOCS_ROLE30="jocs.member.role.30";
	public static final String JOCS_ROLE40="jocs.member.role.40";
	public static final String JOCS_ROLE50="jocs.member.role.50";
	public static final String JOCS_ROLE60="jocs.member.role.60";
	public static final String JOCS_ROLE70="jocs.member.role.70";
	/**
	 *待审会员*/
	public static final String JOCS_ROLE0="jocs.member.role.0";
	/**
	 *已审二级店铺*/
	public static final String JOCS_STORE2_X="jocs.member.role.store2.x";
	/**
	 *正式二级店铺*/
	public static final String JOCS_STORE2="jocs.member.role.store2";
	/**
	 * 正式一级店铺*/
	public static final String JOCS_STORE1="jocs.member.role.store1";
	/**
	 * 已审一级店铺*/
	public static final String JOCS_STORE1_X="jocs.member.role.store1.x";
	/**
	 * JOCS已审一级店铺（二升一）*/
	public static final String JOCS_STORE21 = "jocs.member.role.store21.x";
	/**
	 * 冻结角色
	 */
	public static final String JOCS_FREEZE= "jocs.member.role.freeze";
	
	
	/**
	 * 一级店首购金额*/
	public static final String STORE1_FIRST_AMOUNT = "store.f.order.amount";
	/**
	 * 一级店首购PV*/
	public static final String STORE1_FIRST_PV = "store.f.order.pvamt";
	/**
	 * 二级店首购金额*/
	public static final String STORE2_FIRST_AMOUNT = "store.f2.order.amount";
	/**
	 * 二级店首购PV*/
	public static final String STORE2_FIRST_PV = "store.f2.order.pvamt";
	/**
	 * ygd二级店首购金额*/
//	public static final String STORE2_FIRST_AMOUNT_YGD = "store.f2.order.amount_ygd";
	/**
	 * HK二级店首购PV*/
	public static final String STORE2_FIRST_PV_HK = "store.f2.order.pv";
	/**
	 * 二级店升级PV*/
	public static final String STORE2_UP_PV = "store.u2.order.pv";
	/**
	 * 二级店升级金额*/
	public static final String STORE2_UP_AMOUNT = "store.u2.order.amount";
	/**
	 * ygd团队编号 */
	public static final String TEAMCODE_YGD = "CN12365064";
	
	
	/**
	 * 冻结状态： 1冻结
	 */
	public static final int FREEZE_STATUS_ONE = 1;
	/**
	 * 冻结状态： 0 未冻结
	 */
	public static final int FREEZE_STATUS_ZERO = 0;
	/**首购单*/
	public static final String FIRST_PURCHASE = "1";
	/**重消单*/
	public static final String MAINTAIN_PURCHASE="4";
	/**升级单*/
	public static final String UPDATE_PURCHASE = "2";
	/**3 ：续约单*/
	public static final String AUTO_PURCHASE = "3";
	/**店铺首购单*/
	public static final String STORE_FIRST_PURCHASE = "6";
	/**店铺重消*/
	public static final String STORE_MAINTAIN_PURCHASE="9";
	/**二级店铺首购*/
	public static final String SUBSTORE_FIRST_PURCHASE = "11";
	/**二级店铺重消*/
	public static final String SUBSTORE_MAINTAIN_PURCHASE="14";
	/**二级店铺升级*/
	public static final String SUBSTORE_UPDATE_PURCHASE = "12";
	
	/** 支付公司类型 快钱：1，畅捷通：2，盛付通：3，宝易互通：4，易宝：5，汇付天下：6，顺手付：7，银盛：8*/
	public static final String PAY_KUAIQIAN_COMPANY = "1";
	public static final String PAY_CJT_COMPANY = "2";
	public static final String PAY_SFT_COMPANY = "3";
	public static final String PAY_BYHT_COMPANY = "4";
	public static final String PAY_YIBAO_COMPANY = "5";
	public static final String PAY_HFTX_COMPANY = "6";
	public static final String PAY_SSF_COMPANY = "7";
	public static final String PAY_YS_COMPANY = "8";
	
	/**全年重消单*/
	public static final String MAINTAIN_PURCHASE_YEAR="40";
	public static final String CRM_SEND="3";
	/**待审会员 PV值*/
	public static final String CARD0_PV ="cardtype.0.value";
	/**银级会员 PV值*/
	public static final String CARD1_PV ="cardtype.1.value";
	/**金级会员 PV值*/
	public static final String CARD2_PV ="cardtype.2.value";
	/**白金会员 PV值*/
	public static final String CARD3_PV ="cardtype.3.value";
	/** 推广员 */
	public static final String CARD7_PV ="cardtype.7.value";
	/**钻石会员 PV值*/
	public static final String CARD4_PV ="cardtype.4.value";
	/**优惠顾客会员 PV值*/
	public static final String CARD5_PV ="cardtype.5.value";
	/**vip 级别 :180PV*/
	public static final String CARD_VIP_PV ="cardtype.2500.value";
	/**订单类型1 首购*/
	public static final String ORDER_TYPE_1 = "1";
	/**订单类型2 升级*/
	public static final String ORDER_TYPE_2 = "2";
	/**订单类型3 续约*/
	public static final String ORDER_TYPE_3 = "3";
	/**订单类型4 重消*/
	public static final String ORDER_TYPE_4 = "4";
	/**订单类型5 辅消*/
	public static final String ORDER_TYPE_5 = "5";
	/**订单类型6 一级店铺首单*/
	public static final String ORDER_TYPE_6 = "6";
	/**订单类型9 一级店铺重消*/
	public static final String ORDER_TYPE_9 = "9";
	/**订单类型93代金券换购单*/
	public static final String ORDER_TYPE_93 = "93";
	
	/**级别: 1000  优惠顾客*/
	public static final String CARDTYPE_1000 = "1000";
	/**级别: 2000 永久优惠顾客*/
	public static final String CARDTYPE_2000 = "2000";
	/**级别: 3000 普通*/
	public static final String CARDTYPE_3000 = "3000";
	/**级别: 4000 高级*/
	public static final String CARDTYPE_4000 = "4000";
	/**级别: 5000 特级*/
	public static final String CARDTYPE_5000 = "5000";
	/**级别: 500云客*/
	public static final String CARDTYPE_500 = "500";
	
	/**角色 待审*/
	public static final String ROLEID0="jocs.member.role.0";  
	/**角色 待审*/
	public static final String ROLEID41="cn.member.41";  
	public static final String ROLEID410="cn.member.41.0";  
	/**优惠顾客  id*/
	public static final String ROLEID1="member_role_id.1";
	/**永久优惠顾客 */
	public static final String ROLEID2="member_role_id.2";
	/**银级*/
	public static final String ROLEID3="member_role_id.3";
	/**金级*/
	public static final String ROLEID4="member_role_id.4";
	/**钻级*/
	public static final String ROLEID5="member_role_id.5";
	
	public static final String CN_MEMBER_1000="cn.member.1000";
	public static final String CN_MEMBER_1500="cn.member.1500";
	public static final String CN_MEMBER_2000="cn.member.2000";
	public static final String CN_MEMBER_3000="cn.member.3000";
	public static final String CN_MEMBER_4000="cn.member.4000";
	public static final String CN_MEMBER_5000="cn.member.5000";

	//
	public static final String CN_MEMBER_398="cn.member.398";
	/**
	 *已审二级店铺*/
	public static final String ROLE_STORE2_X="jocs.member.role.store2.x";
	/**
	 *正式二级店铺*/
	public static final String ROLE_STORE2="jocs.member.role.store2";
	/**
	 * 正式一级店铺*/
	public static final String ROLE_STORE1="jocs.member.role.store1";
	/**
	 * 已审一级店铺*/
	public static final String ROLE_STORE1_X="jocs.member.role.store1.x";
	/**
	 * JOCS已审一级店铺（二升一）*/
	public static final String ROLE_STORE21 = "jocs.member.role.store21.x";
	/**
	 * 冻结角色
	 */
	public static final String ROLE_FREEZE= "jocs.member.role.freeze";
}
