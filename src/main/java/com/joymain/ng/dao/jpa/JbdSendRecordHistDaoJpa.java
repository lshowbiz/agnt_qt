package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JbdSendRecordHist;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.dao.JbdSendRecordHistDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import com.joymain.ng.util.StringUtil;
import org.hibernate.LockMode;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository("jbdSendRecordHistDao")
public class JbdSendRecordHistDaoJpa extends GenericDaoHibernate<JbdSendRecordHist, Long> implements JbdSendRecordHistDao {

    public JbdSendRecordHistDaoJpa() {
        super(JbdSendRecordHist.class);
    }

    /**
	 * 查询明细－－创业销售奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getVentureSalesPv(String userCode, String wweek) {
		//创业销售奖明细表  Jbd_Sell_Calc_Sub
		//String sqlQuery = " select * from JBD_SELL_CALC_SUB_HIST a　where a.user_code = '"+userCode+"' and a.w_week = '"+integer+"' ";
		
		String sqlQuery = " select * from V_Jbd_Sell_Calc_Sub a　where a.link_no = '"+userCode+"' and a.w_week = '"+wweek+"' ";
		//-----封装的ＳＱＬ的ＪＤＢＣ的方法
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//VJbdSellCalcSub

	/**
	 * 查询明细－－创业领导奖01--jbdMemberLinkCalcHist.ventureFund  成功领导奖02
	 * @author Administrator
	 * @param userCode
	 * @param integer
	 */
	@Override
	public List getVentureLeaderPvOne(String userCode, String wweek,
			String string) {

		//旬结旬发
		if(StringUtil.formatInt(wweek)>=StringUtil.formatInt("201905")){
			StringBuffer sb = new StringBuffer("   Select rownum as num,");
			sb.append("   b.User_Code,");
			sb.append("   a.w_Week,");
			sb.append("   b.WEEK_GROUP_FIRST_PV_LNK,");
			sb.append("   decode(SIGN(b.Serial_Number - 1), 1, a.Bouns_Point, 0) As Bouns_Point,");
			sb.append("   (DECODE(SIGN(B.SERIAL_NUMBER - 1),1,LEAST((CASE WHEN B.USER_CODE = A.LAST_KEEP_USER_CODE THEN");
			sb.append("	      (B.WEEK_GROUP_FIRST_PV_LNK + NVL(A.LAST_KEEP_PV, 0)) * A.BOUNS_POINT");
			sb.append("                       ELSE B.WEEK_GROUP_FIRST_PV_LNK * A.BOUNS_POINT END), A.FD_PV),0)) AS Bouns,");


			sb.append("   decode(b.Serial_Number, 1, a.Keep_Pv, 0) As Keep_Pv,");
			sb.append("   decode(a.Last_Keep_User_Code, b.User_Code, a.Last_Keep_Pv, 0) As Last_Keep_Pv");
			sb.append("   From V_JBD_CALC_SELL_LIST a, V_JBD_CALC_SELL_LIST b");
			sb.append("   Where 1 = 1");
			sb.append("   And a.User_Code = '"+userCode+"'");
			sb.append("   And a.User_Code = b.Link_No");
			sb.append("   And a.w_Week = b.w_Week");
			sb.append("   And a.w_Week = "+wweek);
			return   this.findObjectsBySQL(sb.toString());
		}
		//日结月发
		else if(StringUtil.formatInt(wweek)<StringUtil.formatInt("201905")&& StringUtil.formatInt(wweek)>=StringUtil.formatInt("201805")){
			StringBuffer sb = new StringBuffer("   Select rownum as num,");
			sb.append("   b.User_Code,");
			sb.append("   a.w_Week,");
			sb.append("   b.WEEK_GROUP_FIRST_PV_LNK,");
			sb.append("   decode(SIGN(b.Serial_Number - 1), 1, a.Bouns_Point, 0) As Bouns_Point,");

			//sb.append("   decode(SIGN(b.Serial_Number - 1),1,least(b.WEEK_GROUP_FIRST_PV_LNK, a.FD_PV) * a.Bouns_Point,0) as Bouns,");

			//Bouns
			sb.append("   (DECODE(SIGN(B.SERIAL_NUMBER - 1),1,LEAST((CASE WHEN B.USER_CODE = A.LAST_KEEP_USER_CODE THEN");
			sb.append("	      (B.WEEK_GROUP_FIRST_PV_LNK + NVL(A.LAST_KEEP_PV, 0)) * A.BOUNS_POINT");
			sb.append("                       ELSE B.WEEK_GROUP_FIRST_PV_LNK * A.BOUNS_POINT END), A.FD_PV),0)) AS Bouns,");

			sb.append("   decode(b.Serial_Number, 1, a.Keep_Pv, 0) As Keep_Pv,");
			sb.append("   decode(a.Last_Keep_User_Code, b.User_Code, a.Last_Keep_Pv, 0) As Last_Keep_Pv");
			sb.append("   From V_JBD_CALC_SELL_LIST a, V_JBD_CALC_SELL_LIST b, JBD_PERIOD JP");
			sb.append("   Where 1 = 1");
			sb.append("   AND a.W_WEEK >= TO_CHAR(JP.START_TIME, 'YYYYMMDD')");
			sb.append("   AND a.W_WEEK < TO_CHAR(JP.END_TIME, 'YYYYMMDD')");
			sb.append("   AND JP.W_YEAR || LPAD(JP.W_WEEK, 2, 0) = "+wweek);
			sb.append("   AND b.W_WEEK >= TO_CHAR(JP.START_TIME, 'YYYYMMDD')");
			sb.append("   AND b.W_WEEK < TO_CHAR(JP.END_TIME, 'YYYYMMDD')");
			sb.append("   AND JP.W_YEAR || LPAD(JP.W_WEEK, 2, 0) = "+wweek);
			sb.append("   And a.User_Code = '"+userCode+"'");
			sb.append("   And a.User_Code = b.Link_No");
			sb.append("   And a.w_Week = b.w_Week");

			return    this.findObjectsBySQL(sb.toString());
		}else{
			String sqlQuery = " select * from V_JBD_VENTURE_LEADER_SUB a where a.user_code = '"+userCode+"' and a.w_week =  "+wweek+"  order by nlevel";
			return this.jdbcTemplate.queryForList(sqlQuery);
		}
	}//V_Jbd_Venture_Leader_Sub

	@Override
	public List getVentureLeaderDetail(String userCode, String wweek) {

		StringBuffer sb = new StringBuffer("   SELECT EL.REUSER_CODE , EL.MEMBER_ORDER_NO, EL.ORDER_TYPE, EL.ORDER_PV ");
		sb.append("   FROM V_JBD_CALC_SELL_ORDER_LIST EL");
		sb.append("   WHERE 1=1  ");
		sb.append("   AND EL.W_WEEK = " +wweek);
		sb.append("   AND EL.TEAM_CODE = '"+userCode+"'");

		return this.findObjectsBySQL(sb.toString());

	}

	/**
	 * 查询明细－－成功销售奖---页面中没有该明细-(暂时不写)
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getSuccessSaleBonus(String userCode, String wweek) {

		String sqlQuery = "select * from v_jbd_success_sales where user_code='"+userCode+"' and w_week='"+wweek+"' ";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//VJbdMemberLinkCalc

	/**
	 * 查询明细－－加盟店店补
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 * @param string
	 */
	@Override
	public List getFranchises(String userCode, String wweek, String string) {
		String sqlQuery = "select * from v_jbd_franchise_fees where user_code='"+userCode+"' and w_week='"+wweek+"' and fees_type='"+string+"'";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//v_jbd_franchise_fees

	/**
	 * VENTURE_FUND 查询明细－－扶持奖( 创业基金)---jbdMemberLinkCalcHist.ventureFund
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getVentureFundPv(String userCode, String wweek) {
		String sqlQuery = " select * from JBD_VENTURE_FUND_LIST a where a.user_code='"+userCode+"' and a.w_week='"+wweek+"'";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//JBD_VENTURE_FUND_LIST

	/**
	 * 查询明细－－推荐奖金
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getJbdSellCalcRecommendBouns(String userCode, String wweek) {
		String sqlQuery = " select * from V_JBD_SELL_CALC_SUB_DETAIL a where a.user_code='"+userCode+"' and a.w_week='"+wweek+"' ";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//V_Jbd_Sell_Calc_Sub_Detail
	/**
	 * 查询明细－－推荐奖金
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getPopularizeBonusPv(String userCode, String wweek) {
		String sqlQuery = " select * from V_JBD_RECOMMEND_LIST_X a where a.user_code='"+userCode+"' and a.w_week='"+wweek+"' ";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}
	/**
	 * 查询明细－－店铺拓展奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getStoreExpandPv(String userCode, String wweek) {
		String sqlQuery = " select * from V_JBD_STORE_EXPAND_SUB a where a.user_code='"+userCode+"' and a.w_week='"+wweek+"' ";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//v_jbd_store_expand_sub

	/**
	 * 查询明细－－店铺服务奖 
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getStoreServePv(String userCode, String wweek) {
		String sqlQuery = " select * from V_JBD_STORE_SERVE_SUB a where a.user_code='"+userCode+"' and a.w_week='"+wweek+"' ";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//v_jbd_store_serve_sub

	/**
	 * 查询明细－－店铺推荐奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getStoreRecommendPv(String userCode, String wweek) {
		String sqlQuery = " select * from V_JBD_STORE_RECOMMEND_SUB a where a.user_code='"+userCode+"' and a.w_week='"+wweek+"' ";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//v_Jbd_Store_Recommend_Sub

	/**
	 * 查询明细－－创业销售奖－－查询明细－－创业销售奖明细查询
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getJbdSellCalcSubDetailHist(String userCode, String wweek) {
		String sqlQuery = " select * from v_jbd_sell_calc_sub_detail a where a.user_code='"+userCode+"' and a.w_week='"+wweek+"' ";
		return this.jdbcTemplate.queryForList(sqlQuery);
	}//V_Jbd_Sell_Calc_Sub_Detail
	
	public List getJbdSuccessLeaderPv(String userCode, String wweek) {
		return this.jdbcTemplate.queryForList("select * from v_jbd_success_leader where user_code='"+userCode+"' and w_week="+wweek);
	}
	
	/**
	 * 代数奖明细
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getSuccessLeaderPvDetail(String userCode,String wweek,String passStar,String startDate,String endDate,String algebra){
		
		String sql="select a.re_user_code,b.member_order_no,b.pv_amt from "+passStar+" a, jpo_member_order b "
				+ "where a.user_code = '"+userCode+"' and a.w_week = "+wweek+"  and a.RE_USER_CODE = b.user_code and b.check_date >= "
				+ " to_date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') and b.check_date <  to_date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') and b.status='2'";
		if("100".equals(algebra)){
			sql+="  and a.RE_USER_CODE='"+userCode+"' ";
		}
		
		return this.jdbcTemplate.queryForList(sql);
		
	}
	
	
   public JbdSendRecordHist getJbdSendRecordHistForUpdate( Long id) {

        JbdSendRecordHist jbdSendRecordHist= (JbdSendRecordHist) this.getSession().get(JbdSendRecordHist.class, id, LockMode.UPGRADE);
		
		
        return jbdSendRecordHist; 
    }

@Override
public JbdSendRecordHist getJbdSendRecordHistByUserCodeByWeek(String userCode,String wweek) {
	return (JbdSendRecordHist) this.getObjectByHqlQuery("from JbdSendRecordHist where user_code='"+userCode+"' and wweek="+wweek);
}

	public List getVentureLeaderPvDetail(String userCode,String startDate,String endDate ){
		String sql="select b.user_code,b.company_code,b.order_type,b.member_order_no,b.pv_amt from jpo_member_order b where b.user_code = '"+userCode+"' and b.check_date >= to_date('"+startDate+"', 'yyyy-mm-dd hh24:mi:ss') "
				+ "and b.check_date < to_date('"+endDate+"', 'yyyy-mm-dd hh24:mi:ss') and b.status = '2' ";

		return this.jdbcTemplate.queryForList(sql);
	}


	public List getServicePv(String userCode, String wweek) {
		return this.jdbcTemplate.queryForList("select * from v_JBD_VENTURE_SALES where user_code='"+userCode+"' and w_week="+wweek);
	}
	
	public List getServicePv201607(String userCode, String wweek){
		return this.jdbcTemplate.queryForList("select * from V_JBD_SERVICE_AWARDS where user_code='"+userCode+"' and w_week="+wweek);
	}
	
	public List getbdjPv201607(String userCode, String wweek){
		StringBuffer sb = new StringBuffer(" select recommended_no as reno,isstore as isstore,order_no as order_no,pv_amt as pv_amt,proportion as bfb,pv as pv from v_jbd_store_expand_sub ");
		sb.append(" where user_code='"+userCode+"'  and w_week=" +wweek);
		sb.append(" union all ");
		sb.append(" select recommend_no as reno,'' as isstore,member_order_no as order_no,fees as pv_amt,percentage as bfb,null as pv from v_jbd_franchise_fees ");
		sb.append(" where user_code='"+userCode+"'  and w_week=" +wweek);
		
		return this.jdbcTemplate.queryForList(sb.toString());
	}
	
}
