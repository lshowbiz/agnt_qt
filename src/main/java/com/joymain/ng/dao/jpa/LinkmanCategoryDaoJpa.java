package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.LinkmanCategory;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.LinkmanCategoryDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

/**
 * @author gw 2013-07-24
 * 客户分类的daoJpa类
 */
@Repository("linkmanCategoryDao")
public class LinkmanCategoryDaoJpa extends GenericDaoHibernate<LinkmanCategory, Long> implements LinkmanCategoryDao {

    public LinkmanCategoryDaoJpa() {
        super(LinkmanCategory.class);
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
		String name = linkmanCategory.getName();
		//姓名不为空的校验
		//姓名为空
		if(StringUtil.isEmpty(name)){
			StringUtil.getErrorsFormat(errors, "isNotNull", "name", "linkmanCategory.name");
			return true;
		}
        //姓名不为空
		else{
			String hql = " from LinkmanCategory where userCode = '"+userCode+"' and name='"+name+"' ";
			LinkmanCategory linkmanCategoryObj = (LinkmanCategory) this.getObjectByHqlQuery(hql);
			
			if(linkmanCategoryObj!=null){
				//同一个会员　分类名称不一致的校验
					//给出提示语：分类名称原来已经定义过，请换个名称
					StringUtil.getErrorsFormat(errors, "linkmanCategory.nameIsExist", "name", "linkmanCategory.name");
					return true;
			}else{
				return false;
			}
		}
	}

	/**
	 * 客户分类－客户添加或修改的方法
	 * @author gw 2013-07-24
	 * @param linkmanCategory
	 */
	public void saveOrUpdateLinkmanCategory(LinkmanCategory linkmanCategory) {
		this.save(linkmanCategory);
	}

	/**
	 * 客户分类－查询的方法
	 * @author gw 2013-07-24
	 * @param name
	 * @return list
	 */
	public List getLinkmanCategoryByName(String name,String userCode) {
		String sql = " select * from linkman_category where user_code = '"+userCode+"' ";
		if(!StringUtil.isEmpty(name)){
			sql +=" and name like '%"+name+"%'";
		}
		return this.jdbcTemplate.queryForList(sql);
	}
	

	/**
	 * 客户分类－修改之前查询的方法
	 * @author gw 2013-07-24
	 * @param id
	 * @return list
	 */
	public LinkmanCategory getLinkmanCategoryById(String id) {
		if(!StringUtil.isEmpty(id)){
			String hql = " from LinkmanCategory where id = '"+id+"'";
			return (LinkmanCategory) this.getObjectByHqlQuery(hql);
		}else{
		    return new LinkmanCategory();
		}
	}

	/**
	 * 客户分类－删除的方法
	 * @author gw 2013-07-24
	 * @param id
	 * @return
	 */
	public void deleteLinkmanCategoryById(String id) {
       if(!StringUtil.isEmpty(id)){
    	   Long idL = Long.valueOf(id);
    	   this.remove(idL);
       }
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
		String name = linkmanCategory.getName();
		//在做修改的校验时，和该对象的分类名称一样的允许其执行修改（其实是更新操作）
		//但是和该对象的分类名称不一样的时候，执行操作时就要进行校验
		Long id = linkmanCategory.getId();
		//姓名不为空的校验
		//姓名为空
		if(StringUtil.isEmpty(name)){
			StringUtil.getErrorsFormat(errors, "isNotNull", "name", "linkmanCategory.name");
			return true;
		}
        //姓名不为空
		else{
			//在做修改的校验时，和该对象的分类名称一样的允许其执行修改（其实是更新操作）
			//但是和该对象的分类名称不一样的时候，执行操作时就要进行校验，所以HQL语句中加上了不等于符号
			String hql = " from LinkmanCategory where userCode = '"+userCode+"' and name='"+name+"' and id <> '"+id+"'";
			LinkmanCategory linkmanCategoryObj = (LinkmanCategory) this.getObjectByHqlQuery(hql);
			
			if(linkmanCategoryObj!=null){
				//在做修改的校验时，和该对象的分类名称一样的允许其执行修改（其实是更新操作）
				//但是和该对象的分类名称不一样的时候，执行操作时就要进行校验
					//给出提示语：分类名称原来已经定义过，请换个名称
					StringUtil.getErrorsFormat(errors, "linkmanCategory.nameIsExist", "name", "linkmanCategory.name");
					return true;
			}else{
				return false;
			}
		}
	}
	
}
