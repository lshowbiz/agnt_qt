package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmProductWineTemplateSubDao;
import com.joymain.ng.model.JpmProductWineTemplateSub;
import com.joymain.ng.service.JpmProductWineTemplateSubManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@Service("jpmProductWineTemplateSubManager")
@WebService(serviceName = "JpmProductWineTemplateSubService", endpointInterface = "com.joymain.ng.service.JpmProductWineTemplateSubManager")
public class JpmProductWineTemplateSubManagerImpl extends GenericManagerImpl<JpmProductWineTemplateSub, String> implements JpmProductWineTemplateSubManager {
    JpmProductWineTemplateSubDao jpmProductWineTemplateSubDao;

    @Autowired
    public JpmProductWineTemplateSubManagerImpl(JpmProductWineTemplateSubDao jpmProductWineTemplateSubDao) {
        super(jpmProductWineTemplateSubDao);
        this.jpmProductWineTemplateSubDao = jpmProductWineTemplateSubDao;
    }
	
	public Pager<JpmProductWineTemplateSub> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpmProductWineTemplateSubDao.getPager(JpmProductWineTemplateSub.class, searchBeans, OrderBys, currentPage, pageSize);
	}

    @Override
    public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(
        Map<String, String> map)
    {
        return jpmProductWineTemplateSubDao.getJpmProductWineTemplateSubListByProductTemplateNo(map);
    }

    @Override
    public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo)
    {
        return jpmProductWineTemplateSubDao.getJpmProductWineTemplateSubBySubNo(subNo);
    }
}