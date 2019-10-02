package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PdShUrlDao;
import com.joymain.ng.model.PdShUrl;
import com.joymain.ng.service.PdShUrlManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("pdShUrlManager")
@WebService(serviceName = "PdShUrlService", endpointInterface = "com.joymain.ng.service.PdShUrlManager")
public class PdShUrlManagerImpl extends GenericManagerImpl<PdShUrl, Long> implements PdShUrlManager {
    PdShUrlDao pdShUrlDao;

    @Autowired
    public PdShUrlManagerImpl(PdShUrlDao pdShUrlDao) {
        super(pdShUrlDao);
        this.pdShUrlDao = pdShUrlDao;
    }
	
	public Pager<PdShUrl> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdShUrlDao.getPager(PdShUrl.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 根据物流公司编码获取物流公司链接地址
	 * @author gw 2014-07-11
	 * @param shNo 物流公司编码
	 * @return string 
	 */
	public String getShUrl(String shNo) {
		return pdShUrlDao.getShUrl(shNo);
	}
}