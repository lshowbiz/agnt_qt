package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.util.Collection;
@WebService
public interface JmiStoreManager extends GenericManager<JmiStore, Long> {
    
	public Pager<JmiStore> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
    public JmiStore getJmiStoreByUserCode(String userCode);
	public boolean getCheckJmiStore(JmiStore jmiStore,BindingResult errors,HttpServletRequest request);
    public List getJmiStoreList(Map map);
    public void saveJmiStoreApply(JmiStore jmiStore);
}