package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JpmProductWineTemplateSub;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpmProductWineTemplateSubManager extends GenericManager<JpmProductWineTemplateSub, String> {
    
	public Pager<JpmProductWineTemplateSub> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(Map<String,String> map);
	
	public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo);
}