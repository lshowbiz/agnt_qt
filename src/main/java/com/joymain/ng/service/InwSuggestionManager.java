package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface InwSuggestionManager extends GenericManager<InwSuggestion, Long> {
    
	public Pager<InwSuggestion> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * 手机建议查看
	 * @param param ： 查询条件
	 * @return ： 手机建议结果集
	 */
	public List list( Map param,GroupPage page);
	
	/**
	 * 去数据库表inw_viewPeople中查,看当前登录用户对该条建议是否已阅
	 * @author gw 2013-08-23
	 * @param id
	 * @param userCode
	 * @return List
	 */
	public List getInwViewpeopleIsExist(String id, String userCode);
    
	/**
	 * 创新共赢的录入意见之前的校验
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 * @param errors
	 * @return boolean
	 */
	public boolean getCheckPassing(InwSuggestion inwSuggestion,BindingResult errors);
    
	/**
	 * 创新共赢的保存操作
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 */
	public void saveInwSuggestion(InwSuggestion inwSuggestion);

	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * @author gw 2013-09-05  update   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	public List getInwSuggestionReply(String id, String userCode);
	
	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * 分页
	 * @author WuCF 2013-11-29  add   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	public List getInwSuggestionReplyPage(GroupPage page,String id, String userCode);

	/**
     * 创新共赢的会员查看自己所提建议的详细(包括公司对该建议的回复)(新需求)
     * @author gw 2013-09-05  update 2013-11-11
     * @param id
     * @return string
     */
	public InwSuggestion getInwSuggestionById(String id);
}