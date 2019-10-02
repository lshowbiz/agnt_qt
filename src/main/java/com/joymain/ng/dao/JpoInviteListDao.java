package com.joymain.ng.dao;

import com.joymain.ng.model.JpoInviteList;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.data.CommonRecord;

import java.util.List;
import java.util.Map;

/**
 * An interface that provides a data management interface to the JpoInviteList table.
 */
public interface JpoInviteListDao extends GenericDao<JpoInviteList, Long> {

	List getJpoInviteListPage(GroupPage page, CommonRecord crm);

	/**
	 * @author fu 2017-5-26
	 * 手机端我的邀请码查询接口
	 */
	List getJpoInviteList(Map<String, String> params, GroupPage page);

	public JpoInviteList getJpoInviteCode(String inviteCode);
	public JpoInviteList getJpoInviteCodeByUserCode(String useUserCode);


	


}