package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiRecommendRefDao;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jmiRecommendRefManager")
@WebService(serviceName = "JmiRecommendRefService", endpointInterface = "com.joymain.ng.service.JmiRecommendRefManager")
public class JmiRecommendRefManagerImpl extends GenericManagerImpl<JmiRecommendRef, String> implements JmiRecommendRefManager {
    JmiRecommendRefDao jmiRecommendRefDao;

    @Autowired
    public JmiRecommendRefManagerImpl(JmiRecommendRefDao jmiRecommendRefDao) {
        super(jmiRecommendRefDao);
        this.jmiRecommendRefDao = jmiRecommendRefDao;
    }
	
	public Pager<JmiRecommendRef> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiRecommendRefDao.getPager(JmiRecommendRef.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	/**
	 * 获取某推荐下的会员
	 * @param linkNo
	 */
	public List getJmiRecommendRefsByRecommendNo(String recommendNo){
		return jmiRecommendRefDao.getJmiRecommendRefsByRecommendNo(recommendNo);
	}
}