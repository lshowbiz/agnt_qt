package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiSmsNote;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JmiSmsNoteManager extends GenericManager<JmiSmsNote, Long> {
    
	public Pager<JmiSmsNote> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	public String sendSms(String phone);
    public JmiSmsNote getJmiSmsNoteByUserCode(String userCode);
    public JmiSmsNote getJmiSmsNoteByUserCodeStatus(String userCode);
}