package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JbdMemberCongrations;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JbdMemberLinkCalcHist table.
 */
public interface JbdMemberCongrationsDao extends GenericDao<JbdMemberCongrations, Long> {

    List getJbdMemberCongrationsPage(Map<String, String> params, GroupPage page);

	
}