package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PdShipFee;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface PdShipFeeManager extends GenericManager<PdShipFee, String> {
    
	public Pager<PdShipFee> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	/**
	 * get PdShipFee by province
	 * @param province
	 * @return
	 */
	public PdShipFee getPdShipFeeByProvince(String province);
}