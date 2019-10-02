package com.joymain.ng.service.impl;

import com.joymain.ng.dao.LinkmanCategoryDao;
import com.joymain.ng.model.LinkmanCategory;
import com.joymain.ng.service.LinkmanCategoryManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

/**
 * 
 * @author gw 2013-07-24
 * 客户分类的managerImpl类
 *
 */
@Service("linkmanCategoryManager")
@WebService(serviceName = "LinkmanCategoryService", endpointInterface = "com.joymain.ng.service.LinkmanCategoryManager")
public class LinkmanCategoryManagerImpl extends GenericManagerImpl<LinkmanCategory, Long> implements LinkmanCategoryManager {
    LinkmanCategoryDao linkmanCategoryDao;

    @Autowired
    public LinkmanCategoryManagerImpl(LinkmanCategoryDao linkmanCategoryDao) {
        super(linkmanCategoryDao);
        this.linkmanCategoryDao = linkmanCategoryDao;
    }
	
	public Pager<LinkmanCategory> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return linkmanCategoryDao.getPager(LinkmanCategory.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 客户分类－页面校验不为空的方法
	 * @author gw 2013-07-24
	 * @param linkmanCategory
	 * @param errors
	 * @param userCode
	 * @return boolean
	 */
	public boolean getLinkmanCategoryCheck(LinkmanCategory linkmanCategory,BindingResult errors,String userCode) {
		return linkmanCategoryDao.getLinkmanCategoryCheck(linkmanCategory,errors,userCode);
	}

	/**
	 * 客户分类－客户添加或修改的方法
	 * @author gw 2013-07-24
	 * @param linkmanCategory
	 */
	public void saveOrUpdateLinkmanCategory(LinkmanCategory linkmanCategory) {
		 linkmanCategoryDao.saveOrUpdateLinkmanCategory(linkmanCategory);
	}

	/**
	 * 客户分类－查询的方法
	 * @author gw 2013-07-24
	 * @param name
	 * @return list
	 */
	public List getLinkmanCategoryByName(String name,String userCode) {
		return linkmanCategoryDao.getLinkmanCategoryByName(name,userCode);
	}
	
	/**
	 * 客户分类－修改之前查询的方法
	 * @author gw 2013-07-24
	 * @param id
	 * @return list
	 */
	public LinkmanCategory getLinkmanCategoryById(String id){
		return linkmanCategoryDao.getLinkmanCategoryById(id);
	}
    
	/**
	 * 客户分类－删除的方法
	 * @author gw 2013-07-24 
	 * @param id
	 */
	public void deleteLinkmanCategoryById(String id) {
		linkmanCategoryDao.deleteLinkmanCategoryById(id);
	}

	/**
	 * 客户分类－修改后点保存执行保存操作之前的校验方法的方法
	 * @author gw 2013-07-25
	 * @param linkmanCategory
	 * @param errors
	 * @param string
	 * @return LinkmanCategory
	 */
	public boolean getLinkmanCategoryCheckById(LinkmanCategory linkmanCategory,
			BindingResult errors, String userCode) {
		return linkmanCategoryDao.getLinkmanCategoryCheckById(linkmanCategory,errors,userCode);
	}
}