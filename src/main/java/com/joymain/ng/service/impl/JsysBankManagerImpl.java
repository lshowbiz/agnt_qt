package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysBankDao;
import com.joymain.ng.model.JsysBank;
import com.joymain.ng.service.JsysBankManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jsysBankManager")
@WebService(serviceName = "JsysBankService", endpointInterface = "com.joymain.ng.service.JsysBankManager")
public class JsysBankManagerImpl extends GenericManagerImpl<JsysBank, Long> implements JsysBankManager {
    JsysBankDao jsysBankDao;

    @Autowired
    public JsysBankManagerImpl(JsysBankDao jsysBankDao) {
        super(jsysBankDao);
        this.jsysBankDao = jsysBankDao;
    }
	
	public Pager<JsysBank> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jsysBankDao.getPager(JsysBank.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public List getSysBankByCompanyCode(String companyCode) {
		return jsysBankDao.getSysBankByCompanyCode(companyCode);
	}
}