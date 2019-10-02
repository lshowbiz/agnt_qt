package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiSubStore;
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
public interface JmiSubStoreManager extends GenericManager<JmiSubStore, Long> {
    
	public Pager<JmiSubStore> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	public String getCheckSubRecommendStore(BindingResult errors,String subRecommendStore,String recommendNo );
	public boolean getCheckJmiSubStore(BindingResult errors,JmiSubStore jmiSubStore,HttpServletRequest request);
	public void saveJmiSubStoreCheck(JmiSubStore jmiSubStore,JmiMember jmiMember);
    public JmiSubStore getJmiSubStoreByUserCode(String userCode);
    public List getJmiSubStoreList(Map map);
}