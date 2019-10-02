package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JfiChanjetLog;

/**
 * An interface that provides a data management interface to the JfiChanjetLog table.
 */
public interface JfiChanjetLogDao extends GenericDao<JfiChanjetLog, Long> {

	public List getJfiChanjetLogsByDealId(String dealId);
}