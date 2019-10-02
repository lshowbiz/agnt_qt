package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.AmMessage;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface AmMessageManager extends GenericManager<AmMessage, Long> {
    
	public Pager<AmMessage> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	/**
	 * find message by userCode
	 * @param userCode
	 * @return list
	 */
	public List findMessageByUserCode(String userCode,String companyCode,String msgClassNo,String type);
	
	/**
	 * Add By WuCF  分页展示
	 * find message by userCode
	 * @param userCode
	 * @return list
	 */
	public List findMessageByUserCodePage(GroupPage page,String userCode,String companyCode,String msgClassNo,String type);
	
	public List<AmMessage> findMessage(String msgClassNo,String status,String userCode);
	public List<AmMessage> ascfindMessage(String msgClassNo,String status,String userCode);

	public Integer getNoReadReply(String userCode,String companyCode,String msgClassNo);

	public AmMessage saveAmMessage(AmMessage amMessage,JsysUser defJsysUser,String strAction);
	
}