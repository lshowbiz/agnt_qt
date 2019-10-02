package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysLoginLogDao;
import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jsysLoginLogManager")
@WebService(serviceName = "JsysLoginLogService", endpointInterface = "com.joymain.ng.service.JsysLoginLogManager")
public class JsysLoginLogManagerImpl extends GenericManagerImpl<JsysLoginLog, Long> implements JsysLoginLogManager {
    JsysLoginLogDao jsysLoginLogDao;

    @Autowired
    public JsysLoginLogManagerImpl(JsysLoginLogDao jsysLoginLogDao) {
        super(jsysLoginLogDao);
        this.jsysLoginLogDao = jsysLoginLogDao;
    }
	
	public Pager<JsysLoginLog> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jsysLoginLogDao.getPager(JsysLoginLog.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}