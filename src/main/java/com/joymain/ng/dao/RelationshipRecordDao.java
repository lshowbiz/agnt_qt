package com.joymain.ng.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.RelationshipRecord;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the RelationshipRecord table.
 */
public interface RelationshipRecordDao extends GenericDao<RelationshipRecord, Long> {
    
	/**
     * 联系记录－添加功能(修改功能）校验的方法
     * @author gw 2013-07-29
     * @param request
     * @param errors
     * @param relationshipRecord
     * @return　string
     */
	boolean getRelationshipRecordCheckPass(
			RelationshipRecord relationshipRecord, BindingResult errors);

	/**
	 * 联系记录－添加功能
	 * @author gw 2013-07-29
	 * @param relationshipRecord
	 */
	void saveOrUpdateRelationshpRecord(RelationshipRecord relationshipRecord);

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
	List getRelationshipRecordByQuery(String subject, String contactTimeBegin,
			String contactTimeEnd, String contactType, String linkmanId,
			String userCode);
	
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
	List getRelationshipRecordByQueryPage(GroupPage page,String subject, String contactTimeBegin,
			String contactTimeEnd, String contactType, String linkmanId,
			String userCode);
    
	/**
	 * 联系记录－修改－初始化查询
	 * @author gw 2013-07-29
	 * @param id
	 * @return
	 */
	RelationshipRecord getRelationshipRecordByUpdateInit(String id);

	/**
     * 联系记录－删除
     * @author gw 2013-07-29
     * @param id
     */
	void deleteRelationshipRecordById(String id);
    
	/**
	 * 联系记录添加-联系记录添加(修改)查询客户分类名称的集合
	 * @author gw 2013-07-30
	 * @param userCode
	 * @return list
	 */
	List getCategoryNameList(String userCode);

	/**
	 * 联系记录－更加当前登录用户和客户名字（联系人）名字查询Id
	 * @author gw 2013-07-31
	 * @param userCode
	 * @param name
	 * @return
	 */
	Linkman getLinkmanId(String userCode, String name);
    
	/**
	 * 添加或者修改（查询）时查询未分组的客户的信息
	 * @author gw 2013-08-02
	 * @param userCode
	 * @return
	 */
	List getLinkmanNameList(String userCode);

	/**
	 * 根据客户分类查出对应分类下的联系人
	 * @author gw 2013-08-02
	 * @param lcid
	 * @return
	 */
	List getLinkmanNameByLcId(String lcid, String userCode);

	/**
	 * 客户信息删除后,客户相关的拜访记录(联系记录)也同步删除
	 * @author gw 2013-09-24
	 * @param id
	 */
	void deleteRelationshipRecordByLinkmanId(String linkmanId);

	/**
	 * 客户分类中相关的分类删除后,将 拜访记录中相关分类置为空
	 * @param userCode
	 * @param id
	 */
	void setRelationshipRecordLcIdNull(String userCode, String id);

	/**
	 * 根据客户ID查询联系记录
	 * @param linkmanId
	 * @return
	 */
	public List getRelationshipRecordsByLinkManId(String linkmanId);
}