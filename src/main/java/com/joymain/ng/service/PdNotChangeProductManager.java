package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.PdNotChangeProduct;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface PdNotChangeProductManager extends GenericManager<PdNotChangeProduct, Long> {
    
	public Pager<PdNotChangeProduct> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	
	void savePdNotChangeProduct(PdNotChangeProduct pdNotChangeProduct);
	
	List<PdNotChangeProduct> getPdNotChangeProducts();
	
	PdNotChangeProduct getPdNotChangeProduct(String productNo);
}