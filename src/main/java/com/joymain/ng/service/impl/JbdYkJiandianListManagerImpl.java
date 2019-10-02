package com.joymain.ng.service.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdYkJiandianListDao;
import com.joymain.ng.model.JbdYkJiandianList;
import com.joymain.ng.service.JbdYkJiandianListManager;
import com.joymain.ng.util.GroupPage;

@Service("jbdYkJiandianListManager")
@WebService(serviceName = "jbdYkJiandianListService", endpointInterface = "com.joymain.ng.service.JbdYkJiandianListManager")
public class JbdYkJiandianListManagerImpl extends GenericManagerImpl<JbdYkJiandianList, Long> implements JbdYkJiandianListManager {
	private JbdYkJiandianListDao jbdYkJiandianListDao;
	@Autowired
    public JbdYkJiandianListManagerImpl(JbdYkJiandianListDao jbdYkJiandianListDao) {
        super(jbdYkJiandianListDao);
        this.jbdYkJiandianListDao = jbdYkJiandianListDao;
    }
	
	public List getJbdYkJiandianList(Map<String, String> params, GroupPage page){
		return jbdYkJiandianListDao.getJbdYkJiandianList(params, page);
	}
}