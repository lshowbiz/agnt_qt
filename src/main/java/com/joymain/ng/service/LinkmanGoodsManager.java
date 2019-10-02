package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.LinkmanGoods;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;

import org.springframework.validation.BindingResult;

import java.util.Collection;
@WebService
public interface LinkmanGoodsManager extends GenericManager<LinkmanGoods, Long> {
    
	public Pager<LinkmanGoods> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

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
	public List getLinkmanGoodsList(String userCode, String name,String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,String buyQuantityEnd);

	/**
	 * 客户管理-客户的商品-详细查询
	 * @author gw 2013-0-10-08
	 * @param id
	 * @return linkmanGoods
	 */
	public LinkmanGoods getLinkmanGoodsById(String id);

    /**
     * 客户管理-客户的商品-修改或新增之前不为空的校验
     * @author gw 2013-10-09
     * @param linkmanGoods
     * @param errors
     * @return boolean
     */
	public boolean getLinkmanGoodsCheckEmpty(LinkmanGoods linkmanGoods,BindingResult errors);

	/**
	 * 客户管理-客户的商品-修改或录入操作
	 * @author gw 2013-10-09
	 * @param linkmanGoods
	 */
	public void updateOrAddLinkmanGoods(LinkmanGoods linkmanGoods);

}