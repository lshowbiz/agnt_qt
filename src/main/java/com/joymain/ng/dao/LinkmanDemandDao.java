package com.joymain.ng.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.LinkmanDemand;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the LinkmanDemand table.
 */
public interface LinkmanDemandDao extends GenericDao<LinkmanDemand, Long> {

	/**
	 * 客户管理-客户需求-查询(初始化查询或有条件查询)
	 * @author gw 2013-09-25
	 * @param userCode
	 * @param linkmanName
	 * @param registerTimeBegin
	 * @param registerTimeEnd
	 * @return
	 */
	List getLinkmanDemandList(String userCode, String linkmanName,String registerTimeBegin, String registerTimeEnd);

	/**
	 * 分页
	 * 客户管理-客户需求-查询(初始化查询或有条件查询)
	 * @author WuCF 2013-12-02
	 * @param userCode
	 * @param linkmanName
	 * @param registerTimeBegin
	 * @param registerTimeEnd
	 * @return
	 */
	List getLinkmanDemandListPage(GroupPage page,String userCode, String linkmanName,String registerTimeBegin, String registerTimeEnd);

	
	/**
	 * 客户管理-客户需求-查询详细
	 * @author gw 2013-09-25
	 * @param id
	 * @return linkmanDemand
	 */
	LinkmanDemand getLinkmanDemand(String id);

	/**
	 * 客户需求--修改/客户需求分析-修改
	 * @author gw  2013-09-26
	 * @param linkmanDemand
	 */
	void updateLinkmanDemand(LinkmanDemand linkmanDemand);

	/**
	 * 客户需求分析录入(修改)时,不为空的校验
	 * @author gw 2013-09-30
	 * @param userCode
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	boolean getLinkmanDemandEmptyCheck(String userCode,LinkmanDemand linkmanDemand,BindingResult errors);

	/**
	 * 客户需求在修改或者录入之前做不为空的校验
	 * @author gw 2013-09-30
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	boolean getLinkmanDemandCheckEmpty(LinkmanDemand linkmanDemand,BindingResult errors);

	/**
	 * 客户管理-客户的商品-初始化查询或有条件查询(修改)
	 * @author gw  2013-10-15
	 * @param userCode
	 * @param name
	 * @param buyTimeBegin
	 * @param buyTimeEnd
	 * @param buyQuantityBegin
	 * @param buyQuantityEnd
	 * @return list
	 */
	List getLinkmanDemandGoodsList(String userCode, String name,String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,String buyQuantityEnd);

	/**
	 * 分页
	 * 客户管理-客户的商品-初始化查询或有条件查询(修改)
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param name
	 * @param buyTimeBegin
	 * @param buyTimeEnd
	 * @param buyQuantityBegin
	 * @param buyQuantityEnd
	 * @return list
	 */
	List getLinkmanDemandGoodsListPage(GroupPage page,String userCode, String name,String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,String buyQuantityEnd);
	
		
	/**
	 * 客户管理-客户的商品-录入或修改之前不为空的校验
	 * @author gw 2013-10-15
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	boolean getLinkmanDemandGoodsCheckEmpty(LinkmanDemand linkmanDemand,BindingResult errors);
	
}