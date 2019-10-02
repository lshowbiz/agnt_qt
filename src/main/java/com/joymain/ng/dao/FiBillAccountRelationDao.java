package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FiBillAccountRelation;

/**
 * An interface that provides a data management interface to the FiBillAccountRelation table.
 */
public interface FiBillAccountRelationDao extends GenericDao<FiBillAccountRelation, Long> {

	public FiBillAccountRelation getAccountCodeByConditions(FiBillAccountRelation fiBillAccountRelation);
	
	public List getFiBillAccountRelationsByConditions(FiBillAccountRelation fiBillAccountRelation);
}