package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.SalesAnalysisDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.AmMessage;
import com.joymain.ng.model.JmiRecommendRef;

@Repository("salesAnalysisDao")
public class SalesAnalysisDaoJpa extends GenericDaoHibernate<AmMessage, Long> implements SalesAnalysisDao {
	 
	
    public SalesAnalysisDaoJpa() {
        super(AmMessage.class);
    }


	/**
	 * 1.获得指定区域的会员信息：当前登录用户下面的会员地域分布情况，按省
	 * @param companyCode：分公司编码
	 * @return 返回List集合
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberInfo(String province,String companyCode,String userCode,String startLogTime,String endLogTime) {
		StringBuffer sqlBuffer = new StringBuffer(" select recommendJmiMember from JmiRecommendRef recommendJmiMember where recommendJmiMember.userCode='");
		sqlBuffer.append(userCode);
		sqlBuffer.append("'");
		List<JmiRecommendRef> list = this.getSession().createQuery(sqlBuffer.toString()).list();
		String treeIndex = "";
		if(list!=null && list.size()>=1){
			treeIndex = list.get(0).getTreeIndex();
		}
		
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		
		if(StringUtils.isNotEmpty(province) && !"null".equals(province)){
			sqlBuffer = new StringBuffer(" select *  from (with tt as (select city, count(*) sum ");
			sqlBuffer.append(" from jmi_member t, JMI_RECOMMEND_REF t2   where 1 = 1 and t.user_code = t2.user_code ");
			//起始时间
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and t.create_time>=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss')");
				paramsBuf.append(","+startLogTime);
			}
			//截止时间
			if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
				sqlBuffer.append(" and t.create_time<=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss')");
				paramsBuf.append(","+endLogTime);
			}			
			
			//分公司
			sqlBuffer.append(" and t.company_code = ?"); 
			
			//省份
			sqlBuffer.append(" and t.province = ?"); 
			
			//会员索引
			sqlBuffer.append(" and t2.tree_index like ? "); 
			
			sqlBuffer.append(" group by t.city) ");
			sqlBuffer.append(" select tt.city,(select t1.state_province_name||'|'||t2.city_name from jal_state_province t1, jal_city t2 ");
			sqlBuffer.append(" where t1.state_province_id=t2.state_province_id and t2.state_province_id = ?");
			sqlBuffer.append("and t2.city_id=tt.city) NAME, tt.sum SUM from tt order by tt.sum desc) ");
			sqlBuffer.append(" where NAME is not null ");
			
			//设置参数
			paramsBuf.append(","+companyCode);
			paramsBuf.append(","+province);
			paramsBuf.append(","+treeIndex+"%"); 
			paramsBuf.append(","+province);
		}else{
			sqlBuffer = new StringBuffer("select * from (with tt as ");
			sqlBuffer.append(" (select province,count(*) sum from jmi_member t,JMI_RECOMMEND_REF t2 where 1=1 and t.user_code=t2.user_code ");
			//分公司
			if(StringUtils.isEmpty(companyCode)){
				companyCode = "CN";				
			}
			sqlBuffer.append(" and t.company_code = ?"); 
			paramsBuf.append(","+companyCode);
			
			//登录用户
			if(StringUtils.isNotEmpty(userCode)){
				sqlBuffer.append(" and t2.tree_index like ? "); 
				paramsBuf.append(","+treeIndex+"%");
			}
			//起始时间
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and create_time>=to_date(?"); 
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss')");
				paramsBuf.append(","+startLogTime);				
			}
			//截止时间
			if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
				sqlBuffer.append(" and create_time<=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss')");
				paramsBuf.append(","+endLogTime);
			}
			sqlBuffer.append(" group by t.province) ");
			sqlBuffer.append(" select '");
			sqlBuffer.append(startLogTime);
			sqlBuffer.append("' startLogTime,'");
			sqlBuffer.append(endLogTime);
			sqlBuffer.append("' endLogTime,"); 
			sqlBuffer.append(" tt.province PROVINCE,(select t2.state_province_name from jal_state_province t2 where t2.state_province_id=tt.province) NAME, ");
			sqlBuffer.append(" tt.sum SUM from tt order by tt.sum desc ) where NAME is not null ");
		}
		
		//返回时调用分页的查询的方法
		Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return this.getJdbcTemplate3().queryForList(sqlBuffer.toString(),parameters); 
	}
	
	/**
	 * 2.成员活跃度分析
	 * @day：日期
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberActive(String saleFlag2,String companyCode,String userCode,String startLogTime,String endLogTime) {
		StringBuffer sqlBuffer = new StringBuffer(" select recommendJmiMember from JmiRecommendRef recommendJmiMember where recommendJmiMember.userCode='");
		sqlBuffer.append(userCode);
		sqlBuffer.append("'");
		List<JmiRecommendRef> list = this.getSession().createQuery(sqlBuffer.toString()).list();
		String treeIndex = "";
		if(list!=null && list.size()>=1){
			treeIndex = list.get(0).getTreeIndex();
		}
		
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		
		if("logCount".equals(saleFlag2)){//1.登录次数
			sqlBuffer = new StringBuffer("with t as( ");
			sqlBuffer.append(" SELECT  t1.user_code user_code,count(*) count FROM JSYS_LOGIN_LOG t1, JMI_RECOMMEND_REF t2 ");
			sqlBuffer.append(" where t1.user_code = t2.user_code ");
			sqlBuffer.append(" and t1.operation_type in(3,4) ");
			sqlBuffer.append(" and t2.tree_index like ?  ");
			//会员索引
			paramsBuf.append(","+treeIndex+"%");
			
			//起始时间
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and t1.operate_time>=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+startLogTime+" 00:00:00");
			}
			//截止时间
			if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
				sqlBuffer.append(" and t1.operate_time<=to_date(?");  
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+endLogTime+" 23:59:59");
			}
			
			sqlBuffer.append(" group by t1.user_code order by count(*) desc) ");
			sqlBuffer.append(" select t.user_code NAME,t.count SUM from t where rownum<=8 ");
		}else if("orderType".equals(saleFlag2)){//2.订单类型
			sqlBuffer = new StringBuffer("with t as(select t2.user_code,t3.order_type ");
			sqlBuffer.append(" from JMI_RECOMMEND_REF t1, jmi_member t2,JPO_MEMBER_ORDER t3  ");
			sqlBuffer.append(" where t1.user_code = t2.user_code and t2.user_code=t3.user_code   ");  
			//分公司
			if(StringUtils.isEmpty(companyCode)){
				companyCode = "CN";				
			}
			sqlBuffer.append(" and t3.company_code = ?"); 
			paramsBuf.append(","+companyCode);
			
			sqlBuffer.append(" and t1.tree_index like ? ");   
			//会员索引
			paramsBuf.append(","+treeIndex+"%");
			
			//起始时间
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and t3.check_time>=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+startLogTime+" 00:00:00");
			}
			//截止时间
			if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
				sqlBuffer.append(" and t3.check_time<=to_date(?");  
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+endLogTime+" 23:59:59");
			}
			
			sqlBuffer.append(") select t.order_type NAME,count(t.user_code) SUM from t group by t.order_type order by count(*) desc ");
		}else if("orderCount".equals(saleFlag2)){//3.订单数量
			sqlBuffer = new StringBuffer("with t as(select t2.user_code,t3.amount ");
			sqlBuffer.append(" from JMI_RECOMMEND_REF t1, jmi_member t2,JPO_MEMBER_ORDER t3  ");
			sqlBuffer.append(" where t1.user_code = t2.user_code and t2.user_code=t3.user_code  ");
			//分公司
			if(StringUtils.isEmpty(companyCode)){
				companyCode = "CN";				
			}
			sqlBuffer.append(" and t3.company_code = ?"); 
			paramsBuf.append(","+companyCode);
			
			sqlBuffer.append(" and t1.tree_index like ? "); 
			//会员索引
			paramsBuf.append(","+treeIndex+"%");
			
			//起始时间
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and t3.check_time>=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+startLogTime+" 00:00:00");
			}
			//截止时间
			if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
				sqlBuffer.append(" and t3.check_time<=to_date(?");  
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+endLogTime+" 23:59:59");
			}
			
			sqlBuffer.append(") select t.user_code NAME,count(t.amount) SUM from t group by t.user_code order by count(t.amount) desc ");
		}else if("orderMoney".equals(saleFlag2)){//4.订单金额
			sqlBuffer = new StringBuffer("with t as(select t2.user_code,t3.amount ");
			sqlBuffer.append(" from JMI_RECOMMEND_REF t1, jmi_member t2,JPO_MEMBER_ORDER t3 ");
			sqlBuffer.append(" where t1.user_code = t2.user_code and t2.user_code=t3.user_code ");
			//分公司
			if(StringUtils.isEmpty(companyCode)){
				companyCode = "CN";				
			}
			sqlBuffer.append(" and t3.company_code = ?"); 
			paramsBuf.append(","+companyCode);
			
			sqlBuffer.append(" and t1.tree_index like ? ");
			//会员索引
			paramsBuf.append(","+treeIndex+"%");
			
			//起始时间
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and t3.check_time>=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+startLogTime+" 00:00:00");
			}
			//截止时间
			if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
				sqlBuffer.append(" and t3.check_time<=to_date(?");  
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+endLogTime+" 23:59:59");
			}
			
			sqlBuffer.append(") select t.user_code NAME,sum(t.amount) SUM from t group by t.user_code order by sum(t.amount) desc ");
		}else if("orderPv".equals(saleFlag2)){//5.订单PV
			sqlBuffer = new StringBuffer("with t as(select t2.user_code,t3.pv_amt ");
			sqlBuffer.append(" from JMI_RECOMMEND_REF t1, jmi_member t2,JPO_MEMBER_ORDER t3 ");
			sqlBuffer.append(" where t1.user_code = t2.user_code and t2.user_code=t3.user_code ");
			//分公司
			if(StringUtils.isEmpty(companyCode)){
				companyCode = "CN";				
			}
			sqlBuffer.append(" and t3.company_code = ?"); 
			paramsBuf.append(","+companyCode);
			
			sqlBuffer.append(" and t1.tree_index like ? ");
			//会员索引
			paramsBuf.append(","+treeIndex+"%");
			
			//起始时间
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and t3.check_time>=to_date(?");
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+startLogTime+" 00:00:00");
			}
			//截止时间 
			if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
				sqlBuffer.append(" and t3.check_time<=to_date(?");  
				sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
				paramsBuf.append(","+endLogTime+" 23:59:59");
			}
			
			sqlBuffer.append(") select t.user_code NAME,sum(t.pv_amt) SUM from t group by t.user_code order by sum(t.pv_amt) desc ");
		}else{		
			//统计语句
			sqlBuffer = new StringBuffer("select *from (with tt as( ");
			sqlBuffer.append(" SELECT to_char(t.operate_time,'yyyyMMdd') operate_date,t.user_code FROM JSYS_LOGIN_LOG t  ");
			sqlBuffer.append("　,JMI_RECOMMEND_REF t2 where t.user_code=t2.user_code and t.operation_type>=3 and t2.tree_index like ? ");
			paramsBuf.append(","+treeIndex+"%");
			
			if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
				sqlBuffer.append(" and t.operate_time between to_date(?"); 
				sqlBuffer.append(",'yyyy-mm-dd hh24:mi:ss')-10 and  ");
				sqlBuffer.append(" to_date(?"); 
				sqlBuffer.append(",'yyyy-mm-dd hh24:mi:ss') ");
				
				paramsBuf.append(","+startLogTime+" 00:00:00");
				paramsBuf.append(","+startLogTime+" 23:59:59");
			}
			sqlBuffer.append(") select tt.operate_date NAME,count(*) SUM from tt group by tt.operate_date order by tt.operate_date)where rownum<=8 ");
		}
		
		
		
		//返回时调用分页的查询的方法
	    Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return this.getJdbcTemplate3().queryForList(sqlBuffer.toString(),parameters); 
	}
	
	/**
	 * 3.团队新增会员:当前登录会员下面的会员新增情况
	 * @param companyCode：分公司
	 * @param userCode：用户编码
	 * @param year：年份
	 * @param userType：会员类型
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberNewTeams(String companyCode,String userCode,String year,String userType) {
		StringBuffer sqlBuffer = new StringBuffer(" select recommendJmiMember from JmiRecommendRef recommendJmiMember where recommendJmiMember.userCode='");
		sqlBuffer.append(userCode);
		sqlBuffer.append("'");
		List<JmiRecommendRef> list = this.getSession().createQuery(sqlBuffer.toString()).list();
		String treeIndex = "";
		if(list!=null && list.size()>=1){
			treeIndex = list.get(0).getTreeIndex();
		}
		
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		
		//查询语句
		sqlBuffer = new StringBuffer("with tt as( ");
		sqlBuffer.append(" select t.user_code user_code,to_char(create_time,'yyyyMM') create_date from jmi_member t,JMI_RECOMMEND_REF t2 ");
		sqlBuffer.append(" where 1=1 and t.user_code=t2.user_code ");
		
		//用户年份
		if(StringUtils.isNotEmpty(year)){
			sqlBuffer.append(" and to_char(create_time,'yyyy') = ? ");
			paramsBuf.append(","+year);
		}
		
		//分公司
		if(StringUtils.isEmpty(companyCode)){
			companyCode = "CN";
		}
		sqlBuffer.append(" and t.company_code = ? "); 
		paramsBuf.append(","+companyCode);
		
		//会员级别
		if(StringUtils.isNotEmpty(userType)){
			sqlBuffer.append(" and t.isstore = ?");
			paramsBuf.append(","+userType);
		} 
		
		//会员索引
		if(StringUtils.isNotEmpty(userCode)){
			sqlBuffer.append(" and t2.tree_index like ?) "); 
			paramsBuf.append(","+treeIndex+"%");
		} 
		sqlBuffer.append(" select tt.create_date NAME,count(*) SUM from tt group by tt.create_date order by tt.create_date ");
		
		//返回时调用分页的查询的方法
	    Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return this.getJdbcTemplate3().queryForList(sqlBuffer.toString(),parameters); 
	}
	
	/**
	 * 4.环比分析:当前登录会员登录按月份比较
	 * @year:年份
	 * @return 返回List集合
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberPoMemberOrder(String companyCode,String userCode,String startLogTime,String endLogTime,String type) {
		StringBuffer sqlBuffer = new StringBuffer(" select recommendJmiMember from JmiRecommendRef recommendJmiMember where recommendJmiMember.userCode='");
		sqlBuffer.append(userCode);
		sqlBuffer.append("'");
		List<JmiRecommendRef> list = this.getSession().createQuery(sqlBuffer.toString()).list();
		String treeIndex = "";
		if(list!=null && list.size()>=1){
			treeIndex = list.get(0).getTreeIndex();
		}
		
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		//查询语句
		sqlBuffer = new StringBuffer(" select t4.f_year||lpad(t4.f_month,2,'0') NAME ");
		if("0".equals(type)){
			sqlBuffer.append(", sum(amount) SUM ");
		}else if("1".equals(type)){
			sqlBuffer.append(", sum(pv_amt) SUM ");
		}
		sqlBuffer.append(" From JMI_RECOMMEND_REF t1, jmi_member t2, Jpo_Member_Order t3, jbd_period t4 ");
		sqlBuffer.append(" Where t1.user_code = t2.user_code and t2.user_code = t3.user_code ");
		//分公司
		if(StringUtils.isEmpty(companyCode)){
			companyCode = "CN";
		}
		sqlBuffer.append(" and t3.company_code = ? "); 
		paramsBuf.append(","+companyCode);
		
		//起始时间
		if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
			sqlBuffer.append(" and t3.check_time>=to_date(?");
			sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+startLogTime+" 00:00:00");
		}
		//截止时间
		if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
			sqlBuffer.append(" and t3.check_time<=to_date(?");  
			sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+endLogTime+" 23:59:59");
		}
		sqlBuffer.append(" and t3.check_time > t4.start_time and t3.check_time < t4.end_time ");
		
		//会员索引号
		sqlBuffer.append(" and t1.tree_index like ? ");
		paramsBuf.append(","+treeIndex+"%");
		
		sqlBuffer.append(" group by t4.f_year||lpad(t4.f_month,2,'0') ");
		sqlBuffer.append(" order by t4.f_year||lpad(t4.f_month,2,'0') ");

		
		//返回时调用分页的查询的方法
	    Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return this.getJdbcTemplate3().queryForList(sqlBuffer.toString(),parameters); 
	}
	
	/**
	 * 5.商品分析:当前登录会员下面的会员下单的商品数量展示情况
	 * @param companyCode：分公司
	 * @param userCode：用户编号
	 * @param startLogTime：起始时间
	 * @param endLogTime：截止时间
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberProduct(String companyCode,String userCode,String startLogTime,String endLogTime) {
		StringBuffer sqlBuffer = new StringBuffer(" select recommendJmiMember from JmiRecommendRef recommendJmiMember where recommendJmiMember.userCode='");
		sqlBuffer.append(userCode);
		sqlBuffer.append("'");
		List<JmiRecommendRef> list = this.getSession().createQuery(sqlBuffer.toString()).list();
		String treeIndex = "";
		if(list!=null && list.size()>=1){
			treeIndex = list.get(0).getTreeIndex();
		}
		
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		
		sqlBuffer = new StringBuffer("select * from (SELECT /*+ ordered use_hash(t5) use_hash(t4) swap_join_inputs(t4) */ ");
		sqlBuffer.append(" max(t2.PRODUCT_NO || '<br/>' || t2.PRODUCT_NAME) NAME,sum(t1.qty) SUM FROM JPO_MEMBER_ORDER t0,jmi_member t5,JMI_RECOMMEND_REF t4,JPO_MEMBER_ORDER_LIST t1,jpm_product_sale_team_type t3,jpm_product_sale_new t2 ");
		sqlBuffer.append(" where t1.product_id = t3.ptt_id and t0.mo_id = t1.mo_id and t2.uni_no = t3.uni_no and t0.user_code = t5.user_code and t4.user_code = t5.user_code and t0.mo_id = t1.mo_id ");
		if(StringUtils.isNotBlank(companyCode)){
			sqlBuffer.append(" and t0.company_code = ? ");
			paramsBuf.append(","+companyCode);
		}
		if(StringUtils.isNotBlank(userCode)){
			sqlBuffer.append(" and t4.tree_index like ? "); 
			paramsBuf.append(","+treeIndex+"%");
		}
		if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
			sqlBuffer.append(" and t0.check_time>=to_date(?"); 
			sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+startLogTime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
			sqlBuffer.append(" and t0.check_time<=to_date(?");
			sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+endLogTime+" 23:59:59");
		}
		sqlBuffer.append(" AND T2.COMPANY_CODE= ? ");
		paramsBuf.append(","+companyCode);
		
		sqlBuffer.append(" group by t2.uni_no order by sum(t1.qty) desc)  ");
		sqlBuffer.append(" where rownum <= 10 ");

		//返回时调用分页的查询的方法 
	    Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return this.getJdbcTemplate3().queryForList(sqlBuffer.toString(),parameters); 
	} 
	
	/**
	 * 6.冠军推荐:当前登录会员下面的推荐人数数量情况
	 * @year:用户编码
	 * @return 返回List集合
	 */
	@Override
	public List<Map<String, Object>> getJmiMemberChampion(String userCode,String startLogTime,String endLogTime) {
		StringBuffer sqlBuffer = new StringBuffer(" select recommendJmiMember from JmiRecommendRef recommendJmiMember where recommendJmiMember.userCode='");
		sqlBuffer.append(userCode);
		sqlBuffer.append("'");
		List<JmiRecommendRef> list = this.getSession().createQuery(sqlBuffer.toString()).list();
		String treeIndex = "";
		if(list!=null && list.size()>=1){
			treeIndex = list.get(0).getTreeIndex();
		}
		
	    //预编译参数
		StringBuffer paramsBuf = new StringBuffer(treeIndex+"%");
		
		//查询统计语句
		sqlBuffer = new StringBuffer(" with t as( ");
		sqlBuffer.append(" select t1.recommend_no,count(*) SUM from JMI_RECOMMEND_REF t1,jmi_member t2 where t1.user_code=t2.user_code ");
		sqlBuffer.append(" and t1.tree_index like ? ");
		
		if(StringUtils.isNotBlank(startLogTime) && !"null".equals(startLogTime)){
			sqlBuffer.append(" and t2.create_time>=to_date(?");
			sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss') ");
			paramsBuf.append(","+startLogTime+" 00:00:00");
		}
		if(StringUtils.isNotBlank(endLogTime) && !"null".equals(endLogTime)){
			sqlBuffer.append(" and t2.create_time<=to_date(?");
			sqlBuffer.append(",'yyyy-MM-dd hh24:mi:ss')  ");
			paramsBuf.append(","+endLogTime+" 23:59:59");
		}
		//Modify By WuCF 20140425 排除传入的会员信息
		if(StringUtils.isNotBlank(userCode) && !"null".equals(userCode)){
			sqlBuffer.append(" and t2.user_code!=? ");
			paramsBuf.append(","+userCode);
		}
		sqlBuffer.append("  group by t1.recommend_no order by count(*) desc) ");
		sqlBuffer.append(" select t.recommend_no NAME,t.sum SUM from t where rownum<=8 ");
		 
		//返回时调用分页的查询的方法
	    Object[] parameters = paramsBuf.toString().split(",");
	    return  this.getJdbcTemplate3().queryForList(sqlBuffer.toString(), parameters); 
	}
}
