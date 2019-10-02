package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.LinkmanCategory;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;

import org.springframework.validation.BindingResult;

import java.util.Collection;

/**
 * 
 * @author gw 2013-07-24
 * 客户分类的manager类
 *
 */
@WebService
public interface LinkmanCategoryManager extends GenericManager<LinkmanCategory, Long> {
    
	public Pager<LinkmanCategory> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
    
	/**
	 * 客户分类－页面校验的方法
	 * @author gw 2013-07-24
	 * @param linkmanCategory
	 * @param errors
	 * @param userCode
	 * @return boolean
	 */
	public boolean getLinkmanCategoryCheck(LinkmanCategory linkmanCategory,BindingResult errors,String userCode);

	/**
	 * 客户分类－客户添加或修改的方法
	 * @author gw 2013-07-24
	 * @param linkmanCategory
	 */
	public void saveOrUpdateLinkmanCategory(LinkmanCategory linkmanCategory);
    
	/**
	 * 客户分类－查询的方法
	 * @author gw 2013-07-24
	 * @param name
	 * @return List
	 */
	public List getLinkmanCategoryByName(String name,String userCode);
	
	/**
	 * 客户分类－修改之前查询的方法
	 * @author gw 2013-07-24
	 * @param id
	 * @return LinkmanCategory
	 */
	public LinkmanCategory getLinkmanCategoryById(String id);

	/**
	 * 客户分类－删除的方法
	 * @author gw 2013-07-24 
	 * @param id
	 */
	public void deleteLinkmanCategoryById(String id);

	/**
	 * 客户分类－修改后点保存执行保存操作之前的校验方法的方法
	 * @author gw 2013-07-25
	 * @param linkmanCategory
	 * @param errors
	 * @param string
	 * @return LinkmanCategory
	 */
	public boolean getLinkmanCategoryCheckById(LinkmanCategory linkmanCategory,
			BindingResult errors, String userCode);
	
	
}