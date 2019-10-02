package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiUserInvestigation;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JmiUserInvestigationManager extends GenericManager<JmiUserInvestigation, Long> {
    
	public Pager<JmiUserInvestigation> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * @Description  根据会员编号查询调查文件答案对象
	 * @author houxyu
	 * @date 2016-4-28
	 * @param userCode
	 * @return
	 */
	public JmiUserInvestigation getJmiUserInvestigationByUserCode(String userCode);
}