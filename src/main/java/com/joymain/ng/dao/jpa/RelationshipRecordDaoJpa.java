/**
 * RelationshipRecordDaoJpa
 */
package com.joymain.ng.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.RelationshipRecordDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.RelationshipRecord;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("relationshipRecordDao")
public class RelationshipRecordDaoJpa extends GenericDaoHibernate<RelationshipRecord, Long> implements RelationshipRecordDao {

    public RelationshipRecordDaoJpa() {
        super(RelationshipRecord.class);
    }

    /**
     * 联系记录－添加功能(修改功能）校验的方法
     * @author gw 2013-07-29
     * @param request
     * @param errors
     * @param relationshipRecord
     * @return　string
     */
	public boolean getRelationshipRecordCheckPass(
			RelationshipRecord relationshipRecord, BindingResult errors) {
		//联系主题不为空
		if(StringUtil.isEmpty(relationshipRecord.getSubject())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "subject", "relationshipRecord.subject");
		    return true;	
		}
		
		//联系人分类不为空
		if(StringUtil.isEmpty(relationshipRecord.getLcId())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "lcId", "relationshipRecord.linkmanCategory");
			return true;
		}
		
		//联系人（客户名字）不为空
		boolean linkmanIdIsEmpty = ((null==relationshipRecord.getLinkmanId())||("".equals(relationshipRecord.getLinkmanId())));
		if(linkmanIdIsEmpty){
			StringUtil.getErrorsFormat(errors, "isNotNull", "linkmanId", "relationshipRecord.contact");
			return true;
		}
		//联系时间不为空
		boolean contactTimeIsEmpty = ((null==relationshipRecord.getContactTime())||("".equals(relationshipRecord.getContactTime())));
		if(contactTimeIsEmpty){
			StringUtil.getErrorsFormat(errors, "isNotNull", "contactTime", "relationshipRecord.contactTime");
			return true;
		}
		//联系类型不为空---联系类型如果为0,其实就是表示为空没有进行选择的
		if("0".equals(relationshipRecord.getContactType())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "contactType", "relationshipRecord.contactType");
			return true;
		}
		return false;
	}

	/**
	 * 联系记录－添加功能
	 * @author gw 2013-07-29
	 * @param relationshipRecord
	 */
	public void saveOrUpdateRelationshpRecord(
			RelationshipRecord relationshipRecord) {
		this.save(relationshipRecord);
	}

	/**
	 * 联系记录－查询
	 * @author gw 2013-07-29
	 * @param subject
	 * @param contactTimeBegin
	 * @param contactTimeEnd
	 * @param contactType
	 * @param linkmanId
	 * @param userCode
	 * @return list
	 */
	public List getRelationshipRecordByQuery(String subject,
			String contactTimeBegin, String contactTimeEnd, String contactType,
			String linkmanId, String userCode) {
		String sqlQuery = " select * from relationship_record where user_code = '"+userCode+"'";
		if(!StringUtil.isEmpty(subject)){
			sqlQuery += " and subject like '%"+subject+"%'";
		}if(!StringUtil.isEmpty(contactTimeBegin)){
			sqlQuery += " and contact_time>=to_date('"+contactTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
			//sqlQuery += " and contact_time>='"+contactTimeBegin+"'";
		}if(!StringUtil.isEmpty(contactTimeEnd)){
			sqlQuery += " and contact_time<=to_date('"+contactTimeEnd+" 00:00:00','yyyy-MM-dd hh24:mi:ss')";
			//sqlQuery += " and contact_time<='"+contactTimeEnd+"'";
		}if(!StringUtil.isEmpty(contactType)){
			sqlQuery += " and contact_type = '"+contactType+"'";
		}if(!StringUtil.isEmpty(linkmanId)){
			sqlQuery += " and linkman_id = '"+linkmanId+"'";
		}
		return this.jdbcTemplate.queryForList(sqlQuery);
	}
	
	/**
	 * 联系记录－查询：分页
	 * @author WuCF 2013-12-02
	 * @param subject
	 * @param contactTimeBegin
	 * @param contactTimeEnd
	 * @param contactType
	 * @param linkmanId
	 * @param userCode
	 * @return list
	 */
	public List getRelationshipRecordByQueryPage(GroupPage page,String subject,
			String contactTimeBegin, String contactTimeEnd, String contactType,
			String linkmanId, String userCode) {
		String sqlQuery = " select * from relationship_record where user_code = '"+userCode+"'";
		if(!StringUtil.isEmpty(subject)){
			sqlQuery += " and subject like '%"+subject+"%'";
		}if(!StringUtil.isEmpty(contactTimeBegin)){
			sqlQuery += " and contact_time>=to_date('"+contactTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
			//sqlQuery += " and contact_time>='"+contactTimeBegin+"'";
		}if(!StringUtil.isEmpty(contactTimeEnd)){
			sqlQuery += " and contact_time<=to_date('"+contactTimeEnd+" 00:00:00','yyyy-MM-dd hh24:mi:ss')";
			//sqlQuery += " and contact_time<='"+contactTimeEnd+"'";
		}if(!StringUtil.isEmpty(contactType)){
			sqlQuery += " and contact_type = '"+contactType+"'";
		}if(!StringUtil.isEmpty(linkmanId)){
			sqlQuery += " and linkman_id = '"+linkmanId+"'";
		}
//		return this.jdbcTemplate.queryForList(sqlQuery);
		return this.findObjectsBySQL(sqlQuery, page);
	}

	/**
	 * 联系记录－修改－初始化查询
	 * @author gw 2013-07-29
	 * @param id
	 * @return
	 */
	public RelationshipRecord getRelationshipRecordByUpdateInit(String id) {
			String hql = " from RelationshipRecord where id = '"+id+"'";
			return (RelationshipRecord) this.getObjectByHqlQuery(hql);
	}

	/**
     * 联系记录－删除
     * @author gw 2013-07-29
     * @param id
     */
	public void deleteRelationshipRecordById(String id) {
		Long idL = Long.parseLong(id);
        this.remove(idL);		
	}

	/**
	 * 联系记录添加-联系记录添加(修改)查询客户分类名称的集合
	 * @author gw 2013-07-30
	 * @param userCode
	 * @return list
	 */
	public List getCategoryNameList(String userCode) {
		return this.getSession().createQuery(" select a from LinkmanCategory a where userCode='"+userCode+"' ").list();
	}

	/**
	 * 联系记录－更加当前登录用户和客户名字（联系人）名字查询Id
	 * @author gw 2013-07-31
	 * @param userCode
	 * @param name
	 * @return
	 */
	public Linkman getLinkmanId(String userCode, String name) {
		String hql = " select a from Linkman a where userCode = '"+userCode+"' and name='"+name+"' ";
		return (Linkman) this.getObjectByHqlQuery(hql);
	}

	/**
	 * 添加或者修改（查询）时查询未分组的客户的信息
	 * @author gw 2013-08-02
	 * @param userCode
	 * @return
	 */
	public List getLinkmanNameList(String userCode) {
		return this.getSession().createQuery(" select a from Linkman a where userCode='"+userCode+"' and lcId is null ").list();
	}

	/**
	 * 根据客户分类查出对应分类下的联系人
	 * @author gw 2013-08-02
	 * @param lcid
	 * @return
	 */
	public List getLinkmanNameByLcId(String lcid, String userCode) {
		//查询客户分类未分组的集合
		if("ungrouped".equals(lcid) || null==lcid || "".equals(lcid)){
			String hql = " from Linkman where userCode='"+userCode+"' and  lcId is null";
			return this.getSession().createQuery(hql).list();
		}
		//查询客户分类有分类的集合
		else{
			String hql = " from Linkman where lcId='"+lcid+"' and userCode='"+userCode+"'";
			return this.getSession().createQuery(hql).list();
		}
	}

	/**
	 * 客户信息删除后,客户相关的拜访记录(联系记录)也同步删除
	 * @author gw 2013-09-24
	 * @param id
	 */
	public void deleteRelationshipRecordByLinkmanId(String linkmanId) {
		
         //拜访记录的菜单功能区掉了,因此这一部分也没用,加上去还会报错  update 2014-03-03 author		
          /*String hql = " from RelationshipRecord where linkmanId = '"+linkmanId+"'";
          List<RelationshipRecord> relationshipRecordList = this.getSession().createQuery(hql).list();
          for(RelationshipRecord relationshipRecord:relationshipRecordList){
        	  Long id = relationshipRecord.getId();
        	  this.remove(id);	
          }*/
	}

	/**
	 * 客户分类中相关的分类删除后,将 拜访记录中相关分类置为空
	 * @param userCode
	 * @param id
	 */
	public void setRelationshipRecordLcIdNull(String userCode, String lcId) {
		 
		//拜访记录的菜单功能区掉了,因此这一部分也没用,加上去还会报错  update 2014-03-03 author		
         /*String hql= " from RelationshipRecord where userCode='"+userCode+"' and lcId='"+lcId+"'";	
         List<RelationshipRecord> relationshipRecordList = this.getSession().createQuery(hql).list();
         for(RelationshipRecord relationshipRecord : relationshipRecordList){
        	 relationshipRecord.setLcId("");
        	 this.save(relationshipRecord);
         }*/
		
	}
	
	/**
	 * 根据客户ID查询联系记录
	 * @param linkmanId
	 * @return
	 */
	public List getRelationshipRecordsByLinkManId(String linkmanId) {
		
		String hql = "from RelationshipRecord where linkmanId ="+linkmanId;
		
		return this.getSession().createQuery(hql).list();
	}
}

