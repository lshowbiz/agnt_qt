package com.joymain.ng.dao.jpa;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JbdDayCustRecommendDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdDayCustRecommend;
import com.joymain.ng.model.JbdDayCustRecommendOrder;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
@SuppressWarnings("unused")
@Repository("jbdDayCustRecommendDao")
public class JbdDayCustRecommendDaoJpa extends GenericDaoHibernate<JbdDayCustRecommend, Long> implements JbdDayCustRecommendDao {

    public JbdDayCustRecommendDaoJpa() {
        super(JbdDayCustRecommend.class);
    }
	protected JdbcTemplate jdbcTemplate;//查询ec正式库数据源配置

	
	/**
	 * 分页查询 Add By WuCF  2013-11-25
	 * 奖金查询－初始化查询或有条件查询时的ＤＡＯ方法
	 * @author gong_wei
	 */
	@Override
	public List getJbdDayCustRecommendByUserCodeWeekPage(Map<String, String> params, GroupPage page) {
			String userCode = params.get("userCode");
			String startweek = params.get("startweek");
	        String endweek = params.get("endweek");
			String sql = "select t.*,(select PET_NAME from jmi_member where user_code=t.user_code) as PET_NAME,(select VALUE_TITLE from JSYS_LIST_VALUE where KEY_ID=(select KEY_ID from JSYS_LIST_KEY where LIST_CODE='pass.star') and VALUE_CODE=t.MEMBER_STAR) as MEMBER_STAR_NAME , (select VALUE_TITLE from JSYS_LIST_VALUE where KEY_ID=(select KEY_ID from JSYS_LIST_KEY where LIST_CODE='member.level') and VALUE_CODE=t.MEMBER_LEVEL) as MEMBER_LEVEL_NAME from JBD_DAY_CUST_RECOMMEND t where user_code='"+userCode+"'";

			if(StringUtils.isNotEmpty(startweek)){
				sql += " and w_week>="+startweek;
			}
			if(StringUtils.isNotEmpty(endweek)){
				sql += " and w_week<="+endweek;
			}
			sql += " order by com_date desc";
			return this.findObjectsBySQL(sql, page);
		}
		
	/**
	 * 查出已归档的记录
	 * @createTime 2013-06-21
	 * @param sqlQuery
	 * @param count
	 * @return list
	 */
	private Integer getNotCanSearch(){
		String sql="select * from jbd_period b where b.archiving_status=1 order by b.w_year desc, b.w_week desc";
		List list=this.findTopObjectsBySQL(sql, 1);
		if(list==null){
			return null;
		}else{
			Map bdPeriod=(Map) list.get(0);
			String res=bdPeriod.get("w_year").toString()+StringUtils.leftPad(bdPeriod.get("w_week").toString(), 2, "0");
			return new Integer(res);
		}
	}
		/**
		 * 获取SQL对应的前几行的记录.(单纯提取前几行时,使用此方法提高性能)
		 * @createTime 2013-06-21
		 * @param sqlQuery
		 * @param count
		 * @return list
		 */
		@SuppressWarnings("unchecked")
		public List findTopObjectsBySQL(final String sqlQuery, final int count){
			String tmpQuery="select * from ("+sqlQuery+") where rownum <="+count;
			List result = null;
			try {
		        long startTime = System.currentTimeMillis();
		        result = (List) this.jdbcTemplate.query(tmpQuery, new ResultSetExtractor() {
			        public Object extractData(ResultSet rs) throws SQLException {
				        return buildSqlResultList(rs);
			        }
		        });
		        return result;
	        } catch (DataAccessException e) {
	        	e.printStackTrace();
	        }
			return result;
		}
		
		/**
		 * 根据SQL查询所返回的RS生成对应的List
		 * @createTime 2013-06-21
		 * @param rs
		 * @return list
		 */
		public List<HashMap<String, String>> buildSqlResultList(ResultSet rs) {
			List<HashMap<String, String>> presidents = new ArrayList<HashMap<String, String>>();
			try {
				ResultSetMetaData mData = rs.getMetaData();
				int fieldCount = mData.getColumnCount();
				while (rs.next()) {
					HashMap<String, String> president = new HashMap<String, String>();
					for (int i = 1; i <= fieldCount; i++) {
						String fieldName = mData.getColumnName(i);
						int fieldType = mData.getColumnType(i);
						String fieldValue = null;
						switch (fieldType) {
							case Types.DATE:
							case Types.TIME:
							case Types.TIMESTAMP:
								fieldValue = rs.getString(i);
								if (fieldValue == null) {
									fieldValue = "";
								} else if (fieldValue.indexOf(".") >= 0) {// oracle日期处理
									fieldValue = fieldValue.substring(0, fieldValue.indexOf("."));
								}
								break;
							default:
								fieldValue = rs.getString(i);
								if (fieldValue == null)
									fieldValue = "";
								break;
						}

						president.put(fieldName.toLowerCase(), fieldValue);
					}
					presidents.add(president);
				} // while
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {  
				JdbcUtils.closeResultSet(rs);
			}
			return presidents;
		}


		@Override
		public List getJbdDayCustRecommendDetail(String userCode, String wweek,GroupPage page) {
			
			String hqlQuery="select * from JBD_DAY_CUST_RECOMMEND_ORDER  where user_code='"+userCode+"' and w_week= "+ wweek + "and send_flag = '1' order by com_date desc";
			return  this.findObjectsBySQL(hqlQuery);
			
		}
		@Override
		public List getJbdDayCustRecommendDetail2(String userCode, String wweek,GroupPage page) {
			
			String hqlQuery="select t.* ,(select VALUE_TITLE from JSYS_LIST_VALUE where KEY_ID=(select KEY_ID from JSYS_LIST_KEY where LIST_CODE='po.all.order_type') and VALUE_CODE=t.ORDER_TYPE) as ORDERS_TYPE from JBD_DAY_CUST_RECOMMEND_ORDER t where t.user_code='"+userCode+"' and t.w_week= "+ wweek + "and t.send_flag = '1'  order by com_date desc";
			return  this.findObjectsBySQL(hqlQuery);
			
		}
		
}
