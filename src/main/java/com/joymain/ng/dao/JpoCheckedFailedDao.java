package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpoCheckedFailed;
import com.joymain.ng.model.JpoMemberOrder;

/**
 * An interface that provides a data management interface to the JpoCheckedFailed table.
 */
public interface JpoCheckedFailedDao extends GenericDao<JpoCheckedFailed, Long> {

	public JpoCheckedFailed getByOrderNo(JpoMemberOrder jpoMemberOrder);
	
	public Integer deleteJpoCheckedFaiiled(String moId);
}