package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmProductWineTemplateDao;
import com.joymain.ng.model.JpmProductWineTemplate;
import com.joymain.ng.service.JpmProductWineTemplateManager;
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

@Service("jpmProductWineTemplateManager")
@WebService(serviceName = "JpmProductWineTemplateService", endpointInterface = "com.joymain.ng.service.JpmProductWineTemplateManager")
public class JpmProductWineTemplateManagerImpl extends GenericManagerImpl<JpmProductWineTemplate, Long> implements JpmProductWineTemplateManager {
    JpmProductWineTemplateDao jpmProductWineTemplateDao;

    @Autowired
    public JpmProductWineTemplateManagerImpl(JpmProductWineTemplateDao jpmProductWineTemplateDao) {
        super(jpmProductWineTemplateDao);
        this.jpmProductWineTemplateDao = jpmProductWineTemplateDao;
    }
	
	public Pager<JpmProductWineTemplate> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpmProductWineTemplateDao.getPager(JpmProductWineTemplate.class, searchBeans, OrderBys, currentPage, pageSize);
	}

    @Override
    public List<JpmProductWineTemplate> getTemplateList()
    {
        return jpmProductWineTemplateDao.getTemplateList();
    }

    /**
     * 根据商品编码获取对应的模版信息
     * @param map
     * @return
     */
    @Override
    public List<JpmProductWineTemplate> getTemplateListByProductNo(String productNo)
    {
        return jpmProductWineTemplateDao.getTemplateListByProductNo(productNo);
    }
}