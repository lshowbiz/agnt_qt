package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdYdRebateListDao;
import com.joymain.ng.model.JbdYdRebateList;
import com.joymain.ng.service.JbdYdRebateListManager;
import com.joymain.ng.util.GroupPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;

@Service("jbdYdRebateListManager")
@WebService(serviceName = "jbdYdRebateListService", endpointInterface = "com.joymain.ng.service.JbdYdRebateListManager")
public class JbdYdRebateListManagerImpl extends GenericManagerImpl<JbdYdRebateList, Long> implements JbdYdRebateListManager {

	private JbdYdRebateListDao jbdYdRebateListDao;
	@Autowired
    public JbdYdRebateListManagerImpl(JbdYdRebateListDao jbdYdRebateListDao) {
        super(jbdYdRebateListDao);
        this.jbdYdRebateListDao = jbdYdRebateListDao;
    }


	@Override
	public List getJbdYdRebateListsPage(Map<String, String> params, GroupPage page) {
		return jbdYdRebateListDao.getJbdYdRebateList(params,page);
	}
}