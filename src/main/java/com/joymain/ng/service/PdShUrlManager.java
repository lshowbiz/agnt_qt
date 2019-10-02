package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PdShUrl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface PdShUrlManager extends GenericManager<PdShUrl, Long> {
    
	public Pager<PdShUrl> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	/**
	 * 根据物流公司编码获取物流公司链接地址
	 * @author gw 2014-07-11
	 * @param shNo 物流公司编码
	 * @return string 
	 */
	public String getShUrl(String shNo);
	
}