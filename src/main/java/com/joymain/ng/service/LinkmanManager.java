package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.Linkman;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface LinkmanManager extends GenericManager<Linkman, Long> {
    
	public Pager<Linkman> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
    
	/**
	 * 客户维护－客户信息查询
	 * @param name
	 * @param sex
	 * @param mobilePhone
	 * @param lcId
	 * @param customerFocus
	 * @return
	 */
	public List getLinkmanBybaseInfo(String name, String sex, String mobilePhone,String userCode,String lcId,String customerFocus);

	/**
	 * 客户维护－客户信息查询
	 * Add By WuCF 2013-12-02
	 * 分页
	 * @param name
	 * @param sex
	 * @param mobilePhone
	 * @param lcId
	 * @param customerFocus
	 * @return
	 */
	public List getLinkmanBybaseInfoPage(GroupPage page,String name, String sex, String mobilePhone,String userCode,String lcId,String customerFocus);

	
	/**
	 * 客户维护－客户信息详细查询（客户详细信息修改的初始查询方法）
	 * @author gw 2013-07-16
	 * @param id
	 * @return　linkman
	 */
	public Linkman getLinkmanDetail(String id);

	/**
	 * 客户维护－客户详细信息修改（或新增的方法）
	 * @author gw 2013-07-16
	 * @param linkman
	 * @return 
	 */
	public void updateOrAddLinkmanDetail(Linkman linkman);

	/**
	 * 客户维护－客户详细信息修改之前的校验方法
	 * @param linkman
	 * @param errors
	 * @return boolean
	 */
	public boolean getlinkmanManagerMark(Linkman linkman, BindingResult errors);
    
	/**
	 * 客户维护（添加）－查询客户分类
	 * @author gw 2013-07-26
	 * @param userCode
	 * @return　list
	 */
	public List getLinkmanCategoryList(String userCode);

	/**
	 * 客户分类中的类别删除过后,对应的客户的分类的类别就置为"未分组"
	 * @author gw 2013-09-24
	 * @param userCode
	 * @param id
	 */
	public void setLinkmanUngrouped(String userCode, String id);

	/**
	 * 获取客户基本信息表的实体对象
	 * @author gw 2013-09-25
	 * @param linkmanId
	 * @return
	 */
	public Linkman getLinkmanById(String linkmanId);

	
	public List getLinkmanListBybaseInfoPage(GroupPage page,String name, String sex, String mobilePhone,String userCode,String lcId,String customerFocus);
}