package com.joymain.ng.dao.jpa;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JbdMemberLinkCalcHistDao;
import com.joymain.ng.dao.JbdPeriodDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdMemberLinkCalcHist;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;
@SuppressWarnings("unused")
@Repository("jbdMemberLinkCalcHistDao")
public class JbdMemberLinkCalcHistDaoJpa extends GenericDaoHibernate<JbdMemberLinkCalcHist, Long> implements JbdMemberLinkCalcHistDao {

    @Resource
    private JbdPeriodDao jbdPeriodDao;
    
    public JbdMemberLinkCalcHistDaoJpa() {
        super(JbdMemberLinkCalcHist.class);
    }
	/**
	 * 获取某会员在某一期的奖金数据
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public Map getBdSendRecordMap(final String userCode, final String wweek){
		String sqlQuery="select * from jbd_member_link_calc_hist t where t.user_code='"+userCode+"' and t.w_week="+wweek;
		return this.findOneObjectBySQL(sqlQuery);
	}
	
	/**
	 * 奖金查询－初始化查询或有条件查询时的ＤＡＯ方法
	 * @author gong_wei
	 */
	public List getJbdSendRecordHistByUserCodeWeek(
			String userCode, String wweek,GroupPage page) {
		boolean wwIsEmpty = StringUtil.isEmpty(wweek);
		String sqlQuery ="";
		//没有输入期别
		if(wwIsEmpty){
			//如果期别为空，应该查询所有符合条件期别的信息
			 sqlQuery = " select * from  JBD_SEND_RECORD_HIST a where a.user_code = '"+userCode+"'  "; 
		}
		//输入了期别
		else{
		    //就是将财政周转换成工作周
			//对输入的字符串做处理. 去掉空格之类的无效字符   
			String weeks = wweek.trim();
			//f 代表期别的类型是财政周
			String weekww = WeekFormatUtil.getFormatWeek("f", weeks);
			//因为是会员登录,所以会员编号肯定是有的,但是初始化进去查询的时候,期别就没有,因此这里要做一些处理
			boolean isEmptyWweek = (null==weekww||"".equals(weekww))?true:false;
			//输入的期别没有对应的财政周
			if(isEmptyWweek){
			    sqlQuery = " select * from  JBD_SEND_RECORD_HIST a where a.user_code = '"+userCode+"' and a.w_week = '"+weekww+"' "; 
			}
			//输入的期别有对应的财政周
			else{
				sqlQuery = " select * from  JBD_SEND_RECORD_HIST a where a.user_code = '"+userCode+"' and a.w_week = '"+weekww+"' "; 
			}
					
		} 
		//首先查询出可查询的期别
		Integer notWeek=this.getNotCanSearch();
		if(null!=notWeek){
			sqlQuery += "  and a.w_week <= '"+notWeek+"' "; 
		}
	
		
		//根据会员的级别加上限制条件：如果是中策会员，那么不加限制条件，如果不是中策会员，那么加上限制条件
		//String limit = (String)Constants.sysConfigMap.get(crm.getString("companyCode","AA")).get("bdsend.memberno.limit");
		String limit=(String) Constants.sysConfigMap.get("AA").get("bdsend.memberno.limit");
		//判断用户是不是中策用户,如果isCSH为true,那么是中策用户；如果isCSH为false,那么不是中策用户，是普通会员
		boolean isCSH = StringUtil.getCheckIsUnlimitUser(userCode);
		//对查询的结果进行排序
		sqlQuery+=" order by a.w_week desc";
		//对非中策用户加上限制条件
		
		
		
		if(!StringUtil.isEmpty(limit)&&!"-".equals(limit)&&StringUtil.isInteger(limit) && !isCSH){

        	String sqlQueryOne = "select * from ( "+sqlQuery +" ) where 1=1 and rownum<='"+limit+"'";
        	//下面查询的数据做处理，因为会员比较少，所以不考虑分页
        	return this.findObjectsBySQL(sqlQueryOne, page);
    	}//对中策用户不加限制条件
		else{
			//下面查询的数据做处理，因为会员比较少，所以不考虑分页
        	return this.findObjectsBySQL(sqlQuery, page);
    	}
		
		
	}
	
	/**
	 * 分页查询 Add By WuCF  2013-11-25
	 * 奖金查询－初始化查询或有条件查询时的ＤＡＯ方法
	 * @author gong_wei
	 */
	@Override
	public List getJbdSendRecordHistByUserCodeWeekPage(
			String userCode, String wweek,int pageNum,int pageSize) {
		//因为是会员登录,所以会员编号肯定是有的,但是初始化进去查询的时候,期别就没有,因此这里要做一些处理
		boolean isEmptyWweek = (null==wweek||"".equals(wweek))?true:false;
		String sqlQuery ="";
		if(isEmptyWweek){
			wweek = "";
			//如果期别为空，应该查询所有符合条件期别的信息
			 sqlQuery = " select * from  JBD_SEND_RECORD_HIST a where a.user_code = '"+userCode+"'  "; 
		}else{
			 sqlQuery = " select * from  JBD_SEND_RECORD_HIST a where a.user_code = '"+userCode+"' and a.w_week = '"+wweek+"' "; 
		} 
		//首先查询出可查询的期别
		Integer notWeek=this.getNotCanSearch();
		if(null!=notWeek){
			sqlQuery += "  and a.w_week <= '"+notWeek+"' "; 
		}
	
		
		//根据会员的级别加上限制条件：如果是中策会员，那么不加限制条件，如果不是中策会员，那么加上限制条件
		//String limit = (String)Constants.sysConfigMap.get(crm.getString("companyCode","AA")).get("bdsend.memberno.limit");
		String limit=(String) Constants.sysConfigMap.get("AA").get("bdsend.memberno.limit");
		//判断用户是不是中策用户,如果isCSH为true,那么是中策用户；如果isCSH为false,那么不是中策用户，是普通会员
		boolean isCSH = StringUtil.getCheckIsUnlimitUser(userCode);
		//对查询的结果进行排序
		sqlQuery+=" order by a.w_week desc";
		//对非中策用户加上限制条件
		if(!StringUtil.isEmpty(limit)&&!"-".equals(limit)&&StringUtil.isInteger(limit) && !isCSH){
        	//sqlQuery += " and rownum <='"+limit+"'";
        	String sqlQueryOne = "select * from ( "+sqlQuery +" ) where 1=1 and rownum<='"+limit+"'";
        	
        	//返回分页数据
    		Query query = this.getSession().createSQLQuery(sqlQueryOne);
    		query.setFirstResult(pageNum*pageSize);
    		query.setMaxResults(pageSize);
    		return query.list();
        	 
    	}//对中策用户不加限制条件
		else{
			//返回分页数据
    		Query query = this.getSession().createSQLQuery(sqlQuery);
    		query.setFirstResult(pageNum*pageSize);
    		query.setMaxResults(pageSize);
    		return query.list();
    	}
	}//JbdSendRecordHist
	
	
	
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
	
	
	
	/**
	 * 奖金查询－点击＂查＂图标的详细页面的ＤＡＯ方法
	 * @param userCode
	 * @param wweek
	 * @author gong_wei
	 */
	@Override
	public JbdMemberLinkCalcHist getBonusQueryDetail(String userCode, String wweek) {
		//String sqlQuery = " select * from JBD_MEMBER_LINK_CALC_HIST a where a.user_code = '"+userCode+"' and a.w_week = '"+wweek+"' ";
		
		String hqlQuery="from JbdMemberLinkCalcHist where userCode='"+userCode+"' and wweek= "+wweek;
		return (JbdMemberLinkCalcHist) this.getObjectByHqlQuery(hqlQuery);
	}//VJbdMemberLinkCalc
	
	/**
     * 奖衔查询 如果没有输入期别，则查询出该会员当前财年的所有记录
     * getJbdMemberLinkCalcHistByUserCodeWeek
     *
     * @param userCode
     * @param wweek
     * @param page
     * @return
     */
    @Override
    public List getJbdMemberLinkCalcHistByUserCodeWeek(String userCode,
            String wweek, GroupPage page)
    {
        boolean wwIsEmpty = StringUtil.isEmpty(wweek);
        StringBuffer sqlQuery = new StringBuffer();
        JbdPeriod JbdPeriod = jbdPeriodDao.getBdPeriodByTime(new Date());
        Integer fYear = JbdPeriod.getFyear(); //当前财年
        //没有输入期别
        if(wwIsEmpty){
            //如果期别为空，应该查询所有符合条件期别的信息
            sqlQuery.append(" select * from  JBD_MEMBER_LINK_CALC_HIST a where a.user_code = '"+userCode+"'  and exists" +
            		"(select 1 from jbd_period p where concat(p.w_year, Lpad(p.w_week, 2, 0))=a.w_week and p.f_year="+fYear+") "); 
        } else{
            String weeks = wweek.trim();
            String weekww = WeekFormatUtil.getFormatWeek("f", weeks);
            if(StringUtil.isObjectBlank(weekww)) {
                return new ArrayList();
            }
            Integer lastfYear = fYear-1;
            if(!weeks.startsWith(fYear.toString()) && !weeks.startsWith(lastfYear.toString())) {
                return new ArrayList();
            }
            sqlQuery.append(" select * from  JBD_MEMBER_LINK_CALC_HIST a where a.user_code = '"+userCode+"' and a.w_week = '"+weekww+"' "); 
        } 
        sqlQuery.append(" order by a.w_week desc");
        return this.findObjectsBySQL(sqlQuery.toString(), page);
    }
    
    /**
     * 会员奖衔查询手机端接口
     * @param userCode
     * @param wweek
     * @return
     */
    @Override
    public List<Map<String,Object>> getJbdMemberLinkCalcHistByUserCodeWeek(
            String userCode, String wweek)
    {
        boolean wwIsEmpty = StringUtil.isEmpty(wweek);
        StringBuffer sqlQuery = new StringBuffer();
        JbdPeriod JbdPeriod = jbdPeriodDao.getBdPeriodByTime(new Date());
        Integer fYear = JbdPeriod.getFyear(); //当前财年
        //没有输入期别
        if(wwIsEmpty){
            //如果期别为空，应该查询所有符合条件期别的信息
            sqlQuery.append(" select * from  JBD_MEMBER_LINK_CALC_HIST a where a.user_code = '"+userCode+"'  and exists" +
                    "(select 1 from jbd_period p where concat(p.w_year, Lpad(p.w_week, 2, 0))=a.w_week and p.f_year="+fYear+") "); 
        } else{
            String weeks = wweek.trim();
            String weekww = WeekFormatUtil.getFormatWeek("f", weeks);
            if(StringUtil.isObjectBlank(weekww)) {
                return new ArrayList<Map<String,Object>>();
            }
            Integer lastfYear = fYear-1;
            if(!weeks.startsWith(fYear.toString()) && !weeks.startsWith(lastfYear.toString())) {
                return new ArrayList<Map<String,Object>>();
            }
            
            sqlQuery.append(" select * from  JBD_MEMBER_LINK_CALC_HIST a where a.user_code = '"+userCode+"' and a.w_week = '"+weekww+"' "); 
        } 
        sqlQuery.append(" order by a.w_week desc");
        return this.jdbcTemplate.queryForList(sqlQuery.toString());
    }
	
	public Map getJBdLevel(String userCode ,String wweek){
		String sql="select * from v_jbd_level where user_code='"+userCode+"' and w_week="+wweek;
		List list=this.jdbcTemplate.queryForList(sql);
		if(list.isEmpty()){
			return new HashMap();
		}else{
			return (Map) list.get(0);
		}
		
	}
	
	
	public List getJbdSendTypeList(String userCode, String wweek,GroupPage page) {

		String sqlQuery = " select * from  JBD_SEND_RECORD_HIST a where a.user_code = '"+userCode+"' and w_week >=201517 and w_week<=201522 "; 
	
		if(!StringUtil.isEmpty(wweek)){

			String weekww = WeekFormatUtil.getFormatWeek("f", wweek);
			if(!StringUtil.isEmpty(weekww)){
				sqlQuery+=" and a.w_week="+weekww;
			}
		}
		
		
		sqlQuery+=" order by a.w_week desc";
		
		
        return this.findObjectsBySQL(sqlQuery, page);
		
		
	}
}
