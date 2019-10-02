package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JpmCardSeq;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JpmCardSeqManager extends GenericManager<JpmCardSeq, Long> {
    
	public Pager<JpmCardSeq> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	public void saveUserJpmCardSeq(JpoMemberOrder jpoMemberOrder,String oldCard,String newCard);
}