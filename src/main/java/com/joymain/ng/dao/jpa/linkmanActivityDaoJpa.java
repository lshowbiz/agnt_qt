
package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanActivityDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.LinkmanActivity;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("linkmanActivityDao")
public class linkmanActivityDaoJpa extends GenericDaoHibernate<LinkmanActivity, Long> implements LinkmanActivityDao {

    public linkmanActivityDaoJpa() {
        super(LinkmanActivity.class);
    }

    /**
	 * 客户管理-活动管理-初始化查询\有条件查询
	 * @author gw 2013-10-16
	 * @param userCode
	 * @param topic
	 * @param eventName
	 * @param eventType
	 * @param beginTimeBegin
	 * @param beginTimeEnd
	 * @param endTimeBegin
	 * @param endTimeEnd
	 * @return
	 */
	public List getLinkmanActivityList(String userCode, String topic,
			String eventName, String eventType, String beginTimeBegin,
			String beginTimeEnd, String endTimeBegin, String endTimeEnd) {

		  String sql = " select  id,event_name,topic,to_char(begin_time,'yyyy-MM-dd') begin_time,to_char(end_time,'yyyy-MM-dd') end_time " +
		  		" from linkman_activity  where user_code = ? ";
			String params = userCode;
			if(!StringUtil.isEmpty(topic)){
			       sql += " and topic like '%"+topic+"%'";
			}
			if(!StringUtil.isEmpty(eventName)){
			      sql += " and event_name like '%"+eventName+"%' ";
			}
			if(!StringUtil.isEmpty(eventType)){
			      sql +=  " and event_type = ? ";
			      params = params+","+eventType;
			}
			if(!StringUtil.isEmpty(beginTimeBegin)){
			       sql += " and begin_time >=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			       params = params +","+beginTimeBegin;
			}
			if(!StringUtil.isEmpty(beginTimeEnd)){
			       sql += " and begin_time <=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			       params = params +","+beginTimeEnd;
			}
			if(!StringUtil.isEmpty(endTimeBegin)){
				  sql += " and end_time >=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
				  params = params +","+endTimeBegin;
			}
			if(!StringUtil.isEmpty(endTimeEnd)){
				  sql += " and end_time <=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
				  params = params +","+endTimeEnd;
			}
			Object[] parameters = params.split(",");
			return this.jdbcTemplate.queryForList(sql,parameters);
	}
	
	/**
	 * 分页
	 * 客户管理-活动管理-初始化查询\有条件查询
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param topic
	 * @param eventName
	 * @param eventType
	 * @param beginTimeBegin
	 * @param beginTimeEnd
	 * @param endTimeBegin
	 * @param endTimeEnd
	 * @return
	 */
	public List getLinkmanActivityListPage(GroupPage page,String userCode, String topic,
			String eventName, String eventType, String beginTimeBegin,
			String beginTimeEnd, String endTimeBegin, String endTimeEnd) {

		  String sql = " select  id,event_name,topic,to_char(begin_time,'yyyy-MM-dd') begin_time,to_char(end_time,'yyyy-MM-dd') end_time " +
		  		" from linkman_activity  where user_code = '"+userCode+"' ";
//			String params = userCode;
			if(!StringUtil.isEmpty(topic)){
			       sql += " and topic like '%"+topic+"%'";
			}
			if(!StringUtil.isEmpty(eventName)){
			      sql += " and event_name like '%"+eventName+"%' ";
			}
			if(!StringUtil.isEmpty(eventType)){
			      sql +=  " and event_type = '"+eventType+"' ";
//			      params = params+","+eventType;
			}
			if(!StringUtil.isEmpty(beginTimeBegin)){
			       sql += " and begin_time >=to_date('"+beginTimeBegin+"','yyyy-MM-dd hh24:mi:ss')";
//			       params = params +","+beginTimeBegin;
			}
			if(!StringUtil.isEmpty(beginTimeEnd)){
			       sql += " and begin_time <=to_date('"+beginTimeEnd+"','yyyy-MM-dd hh24:mi:ss')";
//			       params = params +","+beginTimeEnd;
			}
			if(!StringUtil.isEmpty(endTimeBegin)){
				  sql += " and end_time >=to_date('"+endTimeBegin+"','yyyy-MM-dd hh24:mi:ss')";
//				  params = params +","+endTimeBegin;
			}
			if(!StringUtil.isEmpty(endTimeEnd)){
				  sql += " and end_time <=to_date('"+endTimeEnd+"','yyyy-MM-dd hh24:mi:ss')";
//				  params = params +","+endTimeEnd;
			}
//			Object[] parameters = params.split(",");
//			return this.jdbcTemplate.queryForList(sql,parameters);
			return this.findObjectsBySQL(sql, page);
	}

	/**
	 * 客户管理-活动管理-详细查询
	 * @author gw 2013-10-16
	 * @param id
	 * @return
	 */
	public LinkmanActivity getLinkmanActivityDetail(String id) {
		Long idl = Long.parseLong(id);
		Query query = getSession().createQuery(" from LinkmanActivity a where a.id= ?");
		query.setParameter(0, idl);
		List<LinkmanActivity> list = query.list();
		if(list!=null && list.size()!=0){
			return (LinkmanActivity)list.get(0);
		}
		return null;
	}

	/**
	 * 客户管理-活动管理-录入或修改之前不为空的校验
	 * @author gw 2013-10-17
	 * @param linkmanActivity
	 * @param errors
	 * @return
	 */
	public boolean getLinkmanActivityEmptyCheck(
			LinkmanActivity linkmanActivity, BindingResult errors) {
		//活动组织者不为空的校验
		String organizer = linkmanActivity.getOrganizer();
		if(StringUtil.isEmpty(organizer)){
			StringUtil.getErrorsFormat(errors,"isNotNull","organizer","linkmanActivity.organizer");
            return true;
		}
		//活动名称不为空的校验
		String eventName = linkmanActivity.getEventName();
		if(StringUtil.isEmpty(eventName)){
			StringUtil.getErrorsFormat(errors,"isNotNull","eventName","linkmanActivity.eventName");
            return true;
		}
		//活动主办地不为空的校验
		String hostVenue = linkmanActivity.getHostVenue();
		if(StringUtil.isEmpty(hostVenue)){
			StringUtil.getErrorsFormat(errors,"isNotNull","hostVenue","linkmanActivity.hostVenue");
            return true;
		}
		
		//活动主题不为空的校验
		String topic = linkmanActivity.getTopic();
		if(StringUtil.isEmpty(topic)){
			StringUtil.getErrorsFormat(errors,"isNotNull","topic","linkmanActivity.topic");
            return true;
		}
		//活动内容不为空的校验
		String content = linkmanActivity.getContent();
		if(StringUtil.isEmpty(content)){
			StringUtil.getErrorsFormat(errors,"isNotNull","content","linkmanActivity.content");
            return true;
		}
		//事件类型不为空的校验
		String eventType = linkmanActivity.getEventType();
		if(StringUtil.isEmpty(eventType)){
			StringUtil.getErrorsFormat(errors,"isNotNull","eventType","linkmanActivity.eventType");
            return true;
		}
		
		return false;
	}

	/**
	 * 客户管理－活动管理－录入或修改
	 * @author gw 2013-10-17
	 * @param linkmanActivity
	 */
	public void updateOrAddLinkmanActivity(LinkmanActivity linkmanActivity) {
         this.save(linkmanActivity);		
	}
	
}

