package com.joymain.ng.service.impl;

import com.joymain.ng.dao.LinkmanGoodsDao;
import com.joymain.ng.model.LinkmanGoods;
import com.joymain.ng.service.LinkmanGoodsManager;
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

@Service("linkmanGoodsManager")
@WebService(serviceName = "LinkmanGoodsService", endpointInterface = "com.joymain.ng.service.LinkmanGoodsManager")
public class LinkmanGoodsManagerImpl extends GenericManagerImpl<LinkmanGoods, Long> implements LinkmanGoodsManager {
    LinkmanGoodsDao linkmanGoodsDao;

    @Autowired
    public LinkmanGoodsManagerImpl(LinkmanGoodsDao linkmanGoodsDao) {
        super(linkmanGoodsDao);
        this.linkmanGoodsDao = linkmanGoodsDao;
    }
	
	public Pager<LinkmanGoods> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return linkmanGoodsDao.getPager(LinkmanGoods.class, searchBeans, OrderBys, currentPage, pageSize);
	}

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
	public List getLinkmanGoodsList(String userCode, String name,
			String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,
			String buyQuantityEnd) {		
		return linkmanGoodsDao.getLinkmanGoodsList(userCode,name,buyTimeBegin,buyTimeEnd,buyQuantityBegin,buyQuantityEnd);
	}

	/**
	 * 客户管理-客户的商品-详细查询
	 * @author gw 2013-0-10-08
	 * @param id
	 * @return linkmanGoods
	 */
	public LinkmanGoods getLinkmanGoodsById(String id) {
		return linkmanGoodsDao.getLinkmanGoodsById(id);
	}

	/**
     * 客户管理-客户的商品-修改或新增之前不为空的校验
     * @author gw 2013-10-09
     * @param linkmanGoods
     * @param errors
     * @return boolean
     */
	public boolean getLinkmanGoodsCheckEmpty(LinkmanGoods linkmanGoods,BindingResult errors) {
		return linkmanGoodsDao.getLinkmanGoodsCheckEmpty(linkmanGoods,errors);
	}

	/**
	 * 客户管理-客户的商品-修改或录入操作
	 * @author gw 2013-10-09
	 * @param linkmanGoods
	 */
	public void updateOrAddLinkmanGoods(LinkmanGoods linkmanGoods) {
          linkmanGoodsDao.updateOrAddLinkmanGoods(linkmanGoods);		
	}

}