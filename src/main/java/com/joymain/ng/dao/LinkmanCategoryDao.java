package com.joymain.ng.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.LinkmanCategory;

/**
 * 
 * @author gw 2013-07-24
 * 客户分类的DAO类
 *
 */
/**
 * An interface that provides a data management interface to the LinkmanCategory table.
 */
public interface LinkmanCategoryDao extends GenericDao<LinkmanCategory, Long> {
    
	/**
	 * 客户分类－页面校验不为空的方法
	 * @author gw 2013-07-24
	 * @param linkmanCategory
	 * @param errors
	 * @param userCode
	 * @return boolean
	 */
	boolean getLinkmanCategoryCheck(LinkmanCategory linkmanCategory,BindingResult errors,String userCode);

	/**
	 * 客户分类－客户添加或修改的方法
	 * @author gw 2013-07-24
	 * @param linkmanCategory
	 */
	void saveOrUpdateLinkmanCategory(LinkmanCategory linkmanCategory);
     
	/**
	 * 客户分类－查询的方法
	 * @author gw 2013-07-24
	 * @param name
	 * @return list
	 */
	List getLinkmanCategoryByName(String name,String userCode);

	/**
	 * 客户分类－修改之前查询的方法
	 * @author gw 2013-07-24
	 * @param id
	 * @return list
	 */
	LinkmanCategory getLinkmanCategoryById(String id);
    
	/**
	 * 客户分类－删除的方法
	 * @author gw 2013-07-24
	 * @param id
	 */
	void deleteLinkmanCategoryById(String id);
    
	/**
	 * 客户分类－修改后点保存执行保存操作之前的校验方法的方法
	 * @author gw 2013-07-25
	 * @param linkmanCategory
	 * @param errors
	 * @param string
	 * @return LinkmanCategory
	 */
	boolean getLinkmanCategoryCheckById(LinkmanCategory linkmanCategory,
			BindingResult errors, String userCode);

}