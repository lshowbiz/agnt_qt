package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the InwSuggestion table.
 */
public interface InwSuggestionDao extends GenericDao<InwSuggestion, Long> {
	
	/**
	 * 手机建议查看
	 * @param param ： 查询条件
	 * @return ： 手机建议结果集
	 */
	public List list( Map param,GroupPage page );
	/**
	 * 去数据库表inw_viewPeople中查,看当前登录用户对该条建议是否已阅
	 * @author gw 2013-08-23
	 * @param id
	 * @param userCode
	 * @return List
	 */
	public List getInwViewpeopleIsExist(String id, String userCode) ;

	/**
	 * 创新共赢的录入意见之前的校验
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 * @param errors
	 * @return boolean
	 */
	boolean getCheckPassing(InwSuggestion inwSuggestion,BindingResult errors);
    
	/**
	 * 创新共赢的保存操作
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 */
	void saveInwSuggestion(InwSuggestion inwSuggestion);

	
	
	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * @author gw 2013-09-05  update   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	List getInwSuggestionReply(String id, String userCode);
	
	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * @author gw 2013-09-05  update   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	List getInwSuggestionReplyPage(GroupPage page,String id, String userCode);

	/**
     * 创新共赢的会员查看自己所提建议的详细(包括公司对该建议的回复)(新需求)
     * @author gw 2013-09-05  update 2013-11-11
     * @param id
     * @return string
     */
	InwSuggestion getInwSuggestionById(String id);

}