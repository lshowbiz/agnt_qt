package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PdShipFeeDao;
import com.joymain.ng.model.PdShipFee;
import com.joymain.ng.service.PdShipFeeManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("pdShipFeeManager")
@WebService(serviceName = "PdShipFeeService", endpointInterface = "com.joymain.ng.service.PdShipFeeManager")
public class PdShipFeeManagerImpl extends GenericManagerImpl<PdShipFee, String> implements PdShipFeeManager {
    PdShipFeeDao pdShipFeeDao;

    @Autowired
    public PdShipFeeManagerImpl(PdShipFeeDao pdShipFeeDao) {
        super(pdShipFeeDao);
        this.pdShipFeeDao = pdShipFeeDao;
    }
	
	public Pager<PdShipFee> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdShipFeeDao.getPager(PdShipFee.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public PdShipFee getPdShipFeeByProvince(String province) {
		return this.pdShipFeeDao.getPdShipFeeByProvince(province);
	}
}