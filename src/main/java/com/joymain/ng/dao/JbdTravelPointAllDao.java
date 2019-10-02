package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.JbdTravelPointAll;
import com.joymain.ng.model.JbdTravelPointAllId;

/**
 * An interface that provides a data management interface to the JbdTravelPointAll table.
 */
public interface JbdTravelPointAllDao extends GenericDao<JbdTravelPointAll, JbdTravelPointAllId> {
	public List getJbdTravelPointAlls(String userCode);
}