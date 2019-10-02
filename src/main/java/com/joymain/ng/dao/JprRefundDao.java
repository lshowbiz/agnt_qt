package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JprRefund;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JprRefund table.
 */
public interface JprRefundDao extends GenericDao<JprRefund, String> {

	List getJprRefundsListPage(GroupPage page, String roNo,
			String memberOrderNo, String userCode, String timeBegin,
			String timeEnd);

	JprRefund getJprRefunds(String roNo);

}