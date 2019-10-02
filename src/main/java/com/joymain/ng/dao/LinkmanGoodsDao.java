package com.joymain.ng.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.LinkmanGoods;

/**
 * An interface that provides a data management interface to the LinkmanGoods table.
 */
public interface LinkmanGoodsDao extends GenericDao<LinkmanGoods, Long> {

	/**
	 * 客户管理-客户的商品-初始化(有条件)查询
	 * @author gw 2013-10-08
	 * @param userCode
	 * @param name
	 * @param buyTimeBegin
	 * @param buyTimeEnd
	 * @param buyQuantityBegin
	 * @param buyQuantityEnd
	 * @return list
	 */
	List getLinkmanGoodsList(String userCode, String name, String buyTimeBegin,String buyTimeEnd, String buyQuantityBegin, String buyQuantityEnd);

	/**
	 * 客户管理-客户的商品-详细查询
	 * @author gw 2013-0-10-08
	 * @param id
	 * @return linkmanGoods
	 */
	LinkmanGoods getLinkmanGoodsById(String id);

	/**
     * 客户管理-客户的商品-修改或新增之前不为空的校验
     * @author gw 2013-10-09
     * @param linkmanGoods
     * @param errors
     * @return boolean
     */
	boolean getLinkmanGoodsCheckEmpty(LinkmanGoods linkmanGoods,BindingResult errors);

	/**
	 * 客户管理-客户的商品-修改或录入操作
	 * @author gw 2013-10-09
	 * @param linkmanGoods
	 */
	void updateOrAddLinkmanGoods(LinkmanGoods linkmanGoods);


}