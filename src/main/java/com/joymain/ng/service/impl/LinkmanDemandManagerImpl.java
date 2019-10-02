package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanDemandDao;
import com.joymain.ng.model.LinkmanDemand;
import com.joymain.ng.service.LinkmanDemandManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("linkmanDemandManager")
@WebService(serviceName = "LinkmanDemandService", endpointInterface = "com.joymain.ng.service.LinkmanDemandManager")
public class LinkmanDemandManagerImpl extends GenericManagerImpl<LinkmanDemand, Long> implements LinkmanDemandManager {
    LinkmanDemandDao linkmanDemandDao;

    @Autowired
    public LinkmanDemandManagerImpl(LinkmanDemandDao linkmanDemandDao) {
        super(linkmanDemandDao);
        this.linkmanDemandDao = linkmanDemandDao;
    }
	
	public Pager<LinkmanDemand> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return linkmanDemandDao.getPager(LinkmanDemand.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 客户管理-客户需求-查询(初始化查询或有条件查询)
	 * @author gw 2013-09-25
	 * @param userCode
	 * @param linkmanName
	 * @param registerTimeBegin
	 * @param registerTimeEnd
	 * @return
	 */
	public List getLinkmanDemandList(String userCode, String linkmanName,String registerTimeBegin, String registerTimeEnd) {
		return linkmanDemandDao.getLinkmanDemandList(userCode, linkmanName, registerTimeBegin, registerTimeEnd);
	}
	
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
	public List getLinkmanDemandListPage(GroupPage page,String userCode, String linkmanName,String registerTimeBegin, String registerTimeEnd) {
		return linkmanDemandDao.getLinkmanDemandListPage(page,userCode, linkmanName, registerTimeBegin, registerTimeEnd);
	}

	/**
	 * 客户管理-客户需求-查询详细
	 * @author gw 2013-09-25
	 * @param id
	 * @return linkmanDemand
	 */
	public LinkmanDemand getLinkmanDemand(String id) {
		return linkmanDemandDao.getLinkmanDemand(id);
	}

	/**
	 * 客户需求--修改/客户需求分析-修改
	 * @author gw  2013-09-26
	 * @param linkmanDemand
	 */
	public void updateLinkmanDemand(LinkmanDemand linkmanDemand) {
		linkmanDemandDao.updateLinkmanDemand(linkmanDemand);
		
	}

	/**
	 * 客户需求分析录入(修改)时,不为空的校验
	 * @author gw 2013-09-30
	 * @param userCode
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	public boolean getLinkmanDemandEmptyCheck(String userCode,
			LinkmanDemand linkmanDemand,BindingResult errors) {
		return linkmanDemandDao.getLinkmanDemandEmptyCheck(userCode,linkmanDemand,errors);
	}

	/**
	 * 客户需求在修改或者录入之前做不为空的校验
	 * @author gw 2013-09-30
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	public boolean getLinkmanDemandCheckEmpty(LinkmanDemand linkmanDemand,
			BindingResult errors) {
		return linkmanDemandDao.getLinkmanDemandCheckEmpty(linkmanDemand,errors);
	}

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
	public List getLinkmanDemandGoodsList(String userCode, String name,
			String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,
			String buyQuantityEnd) {
		return linkmanDemandDao.getLinkmanDemandGoodsList(userCode,name,buyTimeBegin,buyTimeEnd,buyQuantityBegin,buyQuantityEnd);
	}
	
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
	public List getLinkmanDemandGoodsListPage(GroupPage page,String userCode, String name,
			String buyTimeBegin, String buyTimeEnd, String buyQuantityBegin,
			String buyQuantityEnd) {
		return linkmanDemandDao.getLinkmanDemandGoodsListPage(page,userCode,name,buyTimeBegin,buyTimeEnd,buyQuantityBegin,buyQuantityEnd);
	}

	/**
	 * 客户管理-客户的商品-录入或修改之前不为空的校验
	 * @author gw 2013-10-15
	 * @param linkmanDemand
	 * @param errors
	 * @return boolean
	 */
	public boolean getLinkmanDemandGoodsCheckEmpty(LinkmanDemand linkmanDemand,BindingResult errors) {
		return linkmanDemandDao.getLinkmanDemandGoodsCheckEmpty(linkmanDemand,errors);
	}
}