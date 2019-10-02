package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.JbdMemberFrozen;

/**
 * An interface that provides a data management interface to the JbdBonusFund table.
 */
public interface JbdMemberFrozenDao extends GenericDao<JbdMemberFrozen, String> {

    public List getJbdMmeberFrozens();
}