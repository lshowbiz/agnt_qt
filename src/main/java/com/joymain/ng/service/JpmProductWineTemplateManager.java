package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JpmProductWineTemplate;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpmProductWineTemplateManager extends GenericManager<JpmProductWineTemplate, Long> {
    
	public Pager<JpmProductWineTemplate> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	/**
     * 查询出所有可用模版
     * @param map
     * @return
     */
    public List<JpmProductWineTemplate> getTemplateList();
    
    /**
     * 根据商品编码获取对应的模版信息
     * @param map
     * @return
     */
    public List<JpmProductWineTemplate> getTemplateListByProductNo(String productNo);
}