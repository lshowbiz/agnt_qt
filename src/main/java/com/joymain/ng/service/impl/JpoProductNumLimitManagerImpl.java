package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpoProductNumLimitDao;
import com.joymain.ng.model.JpoProductNumLimit;
import com.joymain.ng.service.JpoProductNumLimitManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jpoProductNumLimitManager")
@WebService(serviceName = "JpoProductNumLimitService", endpointInterface = "com.joymain.ng.service.JpoProductNumLimitManager")
public class JpoProductNumLimitManagerImpl extends GenericManagerImpl<JpoProductNumLimit, Long> implements JpoProductNumLimitManager {
    JpoProductNumLimitDao jpoProductNumLimitDao;

    @Autowired
    public JpoProductNumLimitManagerImpl(JpoProductNumLimitDao jpoProductNumLimitDao) {
        super(jpoProductNumLimitDao);
        this.jpoProductNumLimitDao = jpoProductNumLimitDao;
    }
	
	public Pager<JpoProductNumLimit> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpoProductNumLimitDao.getPager(JpoProductNumLimit.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public JpoProductNumLimit getNum(String productNo){
		return jpoProductNumLimitDao.getNum(productNo);
	}
	
	public List getAllPros(){
		return jpoProductNumLimitDao.getAllPros();
	}
}