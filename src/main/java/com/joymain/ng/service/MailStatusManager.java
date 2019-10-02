package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.MailStatus;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface MailStatusManager extends GenericManager<MailStatus, Long> {
    
	public Pager<MailStatus> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * @author gw 2014-11-24-----------------------------------------需求变更后，这个方法弃用20150806
	 * 物流跟踪查询-保存最后一次的物流查询信息
	 */
	public boolean saveMailStatus(MailStatus mailStatus);
	
	/**
	 * 根据物流跟踪单号查询物流物流的实时信息----------------------------有用方法20150806
	 * @author gw  物流跟踪查询-接口准备数据  2014-12-08
	 * @param  mailNo 物流跟踪单号
	 * 
	 */
	public List getInterfaceByMailStatus(String mailNo);
	
	/**
	 * 根据商品编号查询物流信息中的套餐子商品----------------------------有用方法20150806
	 * @author gw  物流跟踪查询-接口准备数据  2015-05-05
	 * @param  mailNoAndproductNo 物流单号和商品编号拼接的字符串
	 * @return 
	 */
	public String getInterfaceByProductNo(String mailNoAndproductNo);
	
}