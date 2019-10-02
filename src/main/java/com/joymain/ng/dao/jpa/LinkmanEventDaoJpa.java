package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanEventDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.LinkmanEvent;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("linkmanEventDao")
public class LinkmanEventDaoJpa extends GenericDaoHibernate<LinkmanEvent, Long> implements LinkmanEventDao {

    public LinkmanEventDaoJpa() {
        super(LinkmanEvent.class);
    }

    /**
	 * 客户管理-事件管理-初始化查询或有条件查询
	 * @author gw  2013-10-14
	 * @param userCode
	 * @param name
	 * @param title
	 * @param eventType
	 * @param timeBegin
	 * @param timeEnd
	 * @paramreturn
	 */
	public List getLinkmanEventList(String userCode, String name, String title,
			String eventType, String timeBegin, String timeEnd) {
		
		  String sql = " select a.name name,b.title title,b.event_type eventType,to_char(b.time,'yyyy-MM-dd') time,b.id id,b.lc_Id lcId,b.linkman_Id linkmanId " +
	                   " from linkman a,linkman_event b " +
	                   " where a.id = b.linkman_id and b.user_code = ? ";
			String params = userCode;
			if(!StringUtil.isEmpty(name)){
			sql += " and a.name like '%"+name+"%'";
			}
			if(!StringUtil.isEmpty(title)){
			sql += " and b.title like '%"+title+"%' ";
			}
			if(!StringUtil.isEmpty(eventType)){
			sql +=  " and b.event_type = ? ";
			params = params+","+eventType;
			}
			if(!StringUtil.isEmpty(timeBegin)){
			sql += " and b.time >=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			params = params +","+timeBegin;
			}
			if(!StringUtil.isEmpty(timeEnd)){
			sql += " and b.time <=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			params = params +","+timeEnd;
			}
			Object[] parameters = params.split(",");
			return this.jdbcTemplate.queryForList(sql,parameters);
	}

	/**
	 * 分页
	 * 客户管理-事件管理-初始化查询或有条件查询
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param name
	 * @param title
	 * @param eventType
	 * @param timeBegin
	 * @param timeEnd
	 * @return
	 */
	public List getLinkmanEventListPage(GroupPage page,String userCode, String mCode, String name, String title,
			String eventType, String timeBegin, String timeEnd) {
		
		  String sql = "select * from linkman_event b where b.user_code = '"+userCode+"' ";

			if(!StringUtil.isEmpty(mCode)){
				sql += " and b.m_code like '%"+mCode+"%'";
			}
			if(!StringUtil.isEmpty(name)){
				sql += " and b.m_name like '%"+name+"%'";
			}
			if(!StringUtil.isEmpty(title)){
			sql += " and b.title like '%"+title+"%' ";
			}
			if(!StringUtil.isEmpty(eventType)){
			sql +=  " and b.event_type = '"+eventType+"' ";
//			params = params+","+eventType;
			}
			if(!StringUtil.isEmpty(timeBegin)){
			sql += " and b.time >=to_date('"+timeBegin+"','yyyy-MM-dd hh24:mi:ss')";
//			params = params +","+timeBegin;
			}
			if(!StringUtil.isEmpty(timeEnd)){
			sql += " and b.time <=to_date('"+timeEnd+"','yyyy-MM-dd hh24:mi:ss')";
//			params = params +","+timeEnd;
			}
//			Object[] parameters = params.split(",");
//			return this.jdbcTemplate.queryForList(sql,parameters);
			return this.findObjectsBySQL(sql, page);
	}
	
	/**
	 * 客户管理-事件管理-详细查询
	 * @author gw 2013-10-14
	 * @param id
	 * @return
	 */
	public LinkmanEvent getLinkmanEventList(String id) {
		Long idl = Long.parseLong(id);
		Query query = getSession().createQuery(" from LinkmanEvent a where a.id= ?");
		query.setParameter(0, idl);
		List<LinkmanEvent> list = query.list();
		if(list!=null && list.size()!=0){
			return (LinkmanEvent)list.get(0);
		}
		return null;

	}

	/**
	 * 客户管理-事件管理-录入或修改之前不为空的校验
	 * @author 2013-10-14 gw
	 * @param linkmanEvent
	 * @param errors
	 * @return
	 */
	public boolean getLinkmanEventEmptyCheck(LinkmanEvent linkmanEvent,BindingResult errors) {
		//客户姓名不为空的校验
		//String linkmanId = linkmanEvent.getLinkmanId();
		//联系人(姓名)
		String other = linkmanEvent.getOther();
		if(StringUtil.isEmpty(other)){
			StringUtil.getErrorsFormat(errors,"isNotNull","other","linkman.name");
            return true;
		}
		//主题不为空的校验
		String title = linkmanEvent.getTitle();
		if(StringUtil.isEmpty(title)){
			StringUtil.getErrorsFormat(errors,"isNotNull","title","linkmanEvent.title");
            return true;
		}
		//描述不为空的校验
		String description = linkmanEvent.getDescription();
		if(StringUtil.isEmpty(description)){
			StringUtil.getErrorsFormat(errors,"isNotNull","description","linkmanEvent.description");
            return true;
		}
		//事件类型不为空的校验
		String eventType = linkmanEvent.getEventType();
		if(StringUtil.isEmpty(eventType)){
			StringUtil.getErrorsFormat(errors,"isNotNull","eventType","linkmanEvent.eventType");
            return true;
		}
		
		return false;
	}

	/**
	 * 客户管理-事件管理-录入或修改
	 * @author gw 2013-10-14
	 * @param linkmanEvent
	 */
	public void updateOrAddLinkmanMaintain(LinkmanEvent linkmanEvent) {
         this.save(linkmanEvent);		
	}
}
