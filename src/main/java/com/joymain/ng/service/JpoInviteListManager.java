package com.joymain.ng.service;

import com.joymain.ng.model.JpoInviteList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.data.CommonRecord;

import javax.jws.WebService;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@WebService
public interface JpoInviteListManager extends GenericManager<JpoInviteList, Long> {
    
	public Pager<JpoInviteList> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
										 int currentPage, int pageSize);

	public List getJpoInviteListPage(GroupPage page, CommonRecord crm);

	/**
	 * @author fu 2017-5-26
	 * 手机端我的邀请码查询接口
	 */
	public List getJpoInviteList(Map<String, String> params, GroupPage page);

	//新增支付绑定邀请码
	public JpoInviteList getJpoInviteCodeByUserCode(String useUserCode);
	public String getCheckJpoInviteList(String inviteCode);
	public boolean useJpoInviteList(String inviteCode,String moId);

	public JpoInviteList getJpoInviteListForInviteCode(String inviteCode);

	public String useJpoInviteListToApp(String inviteCode, String memberOrderNo, JsysUser user);


	

}