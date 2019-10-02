package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.InwSuggestionDao;
import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.service.InwSuggestionManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("inwSuggestionManager")
@WebService(serviceName = "InwSuggestionService", endpointInterface = "com.joymain.ng.service.InwSuggestionManager")
public class InwSuggestionManagerImpl extends GenericManagerImpl<InwSuggestion, Long> implements InwSuggestionManager {
    InwSuggestionDao inwSuggestionDao;
    


    @Autowired
    public InwSuggestionManagerImpl(InwSuggestionDao inwSuggestionDao) {
        super(inwSuggestionDao);
        this.inwSuggestionDao = inwSuggestionDao;
    }
	
	public Pager<InwSuggestion> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return inwSuggestionDao.getPager(InwSuggestion.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
    /**
	 * 手机建议查看
	 * @param param ： 查询条件
	 * @return ： 手机建议结果集
	 */
	public List list( Map param,GroupPage page){
		return inwSuggestionDao.list(param,page);
	}


	
	/**
	 * 去数据库表inw_viewPeople中查,看当前登录用户对该条建议是否已阅
	 * @author gw 2013-08-23
	 * @param id
	 * @param userCode
	 * @return InwViewpeople
	 */
	public List getInwViewpeopleIsExist(String id, String userCode) {
		
		return inwSuggestionDao.getInwViewpeopleIsExist(id,userCode);
	}
	
	/**
	 * 创新共赢的录入意见之前的校验
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 * @param result
	 * @return boolean
	 */
	public boolean getCheckPassing(InwSuggestion inwSuggestion,BindingResult errors) {
		return inwSuggestionDao.getCheckPassing(inwSuggestion,errors);
	}

	/**
	 * 创新共赢的保存操作
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 */
	public void saveInwSuggestion(InwSuggestion inwSuggestion) {
		 inwSuggestionDao.saveInwSuggestion(inwSuggestion);
	}
	
	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * @author gw 2013-09-05  update   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	public List getInwSuggestionReply(String id, String userCode) {
		return  inwSuggestionDao.getInwSuggestionReply(id,userCode);
	}
	
	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * 分页
	 * @author gw 2013-09-05  add   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	public List getInwSuggestionReplyPage(GroupPage page,String id, String userCode) {
		return  inwSuggestionDao.getInwSuggestionReplyPage(page,id,userCode);
	}

	/**
     * 创新共赢的会员查看自己所提建议的详细(包括公司对该建议的回复)(新需求)
     * @author gw 2013-09-05  update 2013-11-11
     * @param id
     * @return string
     */
	public InwSuggestion getInwSuggestionById(String id) {
		return  inwSuggestionDao.getInwSuggestionById(id);
	}
}