package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanMaintainDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.LinkmanMaintain;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("linkmanMaintainDao")
public class LinkmanMaintainDaoJpa extends GenericDaoHibernate<LinkmanMaintain, Long> implements LinkmanMaintainDao {

    public LinkmanMaintainDaoJpa() {
        super(LinkmanMaintain.class);
    }


	/**
	 * 客户管理-客户维护-初始化或有条件查询
	 * @author gw 2013-10-12
	 * @param userCode
	 * @param name
	 * @param maintenanceTopic
	 * @param maintenanceMode
	 * @param maintenanceTimeBegin
	 * @param maintenanceTimeEnd
	 * @return list
	 */
	public List getLinkmanMaintainList(String userCode, String name,
			String maintenanceTopic, String maintenanceMode,
			String maintenanceTimeBegin, String maintenanceTimeEnd) {
		
		String sql = " select a.name name,b.maintenance_topic maintenanceTopic,b.maintenance_mode maintenanceMode,to_char(b.maintenance_time,'yyyy-MM-dd') maintenanceTime,b.id id,b.lc_id lcId,b.linkman_id linkmanId " +
				     " from linkman a,linkman_maintain b " +
				     " where a.id = b.linkman_id and b.user_code = ? ";
		String params = userCode;
		if(!StringUtil.isEmpty(name)){
			sql += " and a.name like '%"+name+"%'";
		}
		if(!StringUtil.isEmpty(maintenanceTopic)){
			sql += " and b.maintenance_topic like '%"+maintenanceTopic+"%' ";
		}
		if(!StringUtil.isEmpty(maintenanceMode)){
			sql +=  " and b.maintenance_mode = ? ";
			params = params+","+maintenanceMode;
		}
		if(!StringUtil.isEmpty(maintenanceTimeBegin)){
			sql += " and b.maintenance_time >=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			params = params +","+maintenanceTimeBegin;
		}
		if(!StringUtil.isEmpty(maintenanceTimeEnd)){
			sql += " and b.maintenance_time <=to_date(?,'yyyy-MM-dd hh24:mi:ss')";
			params = params +","+maintenanceTimeEnd;
		}
		Object[] parameters = params.split(",");
		return this.jdbcTemplate.queryForList(sql,parameters);
	}
	
	/**
	 * 分页
	 * 客户管理-客户维护-初始化或有条件查询
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param name
	 * @param maintenanceTopic
	 * @param maintenanceMode
	 * @param maintenanceTimeBegin
	 * @param maintenanceTimeEnd
	 * @return list
	 */
	public List getLinkmanMaintainListPage(GroupPage page,String userCode, String name,
			String maintenanceTopic, String maintenanceMode,
			String maintenanceTimeBegin, String maintenanceTimeEnd) {
		
		String sql = " select b.other name,b.maintenance_topic maintenanceTopic,b.maintenance_mode maintenanceMode,to_char(b.maintenance_time,'yyyy-MM-dd') maintenanceTime,b.id id,b.lc_id lcId,b.linkman_id linkmanId " +
				     " from linkman_maintain b " +
				     " where  b.user_code = '"+userCode+"' ";
//		String params = userCode;
		if(!StringUtil.isEmpty(name)){
			sql += " and b.other like '%"+name+"%'";
		}
		if(!StringUtil.isEmpty(maintenanceTopic)){
			sql += " and b.maintenance_topic like '%"+maintenanceTopic+"%' ";
		}
		if(!StringUtil.isEmpty(maintenanceMode)){
			sql +=  " and b.maintenance_mode = '"+maintenanceMode+"' ";
//			params = params+","+maintenanceMode;
		}
		if(!StringUtil.isEmpty(maintenanceTimeBegin)){
			sql += " and b.maintenance_time >=to_date('"+maintenanceTimeBegin+"','yyyy-MM-dd hh24:mi:ss')";
//			params = params +","+maintenanceTimeBegin;
		}
		if(!StringUtil.isEmpty(maintenanceTimeEnd)){
			sql += " and b.maintenance_time <=to_date('"+maintenanceTimeEnd+"','yyyy-MM-dd hh24:mi:ss')";
//			params = params +","+maintenanceTimeEnd;
		}
//		Object[] parameters = params.split(",");
//		return this.jdbcTemplate.queryForList(sql,parameters);
		return this.findObjectsBySQL(sql, page);
	}


	/**
	 * 客户维护-详细查询或修改之前的查询
	 * @author gw 2013-10-12
	 * @param  id
	 * @return linkmanMaintain
	 */
	public LinkmanMaintain getLinkmanMaintain(String id) {
		Long idl = Long.parseLong(id);
		Query query = getSession().createQuery(" from LinkmanMaintain a where a.id= ?");
		query.setParameter(0, idl);
		List<LinkmanMaintain> list = query.list();
		if(list!=null && list.size()!=0){
			return (LinkmanMaintain)list.get(0);
		}
		return null;
	}

	/**
	 * 客户维护-录入或修改之前不为空的校验
	 * @author gw 2013-10-12
	 * @param  linkmanMaintain
	 * @return boolean
	 */
	public boolean getLinkmanMaintainEmptyCheck(LinkmanMaintain linkmanMaintain,BindingResult errors) {
		//客户姓名不为空的校验
		String other = linkmanMaintain.getOther();
		
		if(StringUtil.isEmpty(other)){
			StringUtil.getErrorsFormat(errors,"isNotNull","linkmanId","linkman.name");
            return true;
		}
		//维护主题不为空的校验
		String maintenanceTopic = linkmanMaintain.getMaintenanceTopic();
		if(StringUtil.isEmpty(maintenanceTopic)){
			StringUtil.getErrorsFormat(errors,"isNotNull","maintenanceTopic","linkmanMaintain.maintenanceTopic");
            return true;
		}
		//维护内容不为空的校验
		String maintenanceContent = linkmanMaintain.getMaintenanceContent();
		if(StringUtil.isEmpty(maintenanceContent)){
			StringUtil.getErrorsFormat(errors,"isNotNull","maintenanceContent","linkmanMaintain.maintenanceContent");
            return true;
		}
		//维护内容不为空的校验
		String maintenanceMode = linkmanMaintain.getMaintenanceMode();
		if(StringUtil.isEmpty(maintenanceMode)){
			StringUtil.getErrorsFormat(errors,"isNotNull","maintenanceMode","linkmanMaintain.maintenanceMode");
            return true;
		}
		return false;
	}


	/**
	 * 客户维护-录入或修改
	 * @author gw 2013-10-12
	 * @param linkmanMaintain
	 */
	public void updateOrAddLinkmanMaintain(LinkmanMaintain linkmanMaintain) {
         this.save(linkmanMaintain);		
	}
}
