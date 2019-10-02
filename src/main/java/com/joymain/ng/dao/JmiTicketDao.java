package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JmiTicket;

/**
 * An interface that provides a data management interface to the JmiTicket table.
 */
public interface JmiTicketDao extends GenericDao<JmiTicket, Long> {

    public List getJmiTicketByUserCode(String userCode);
}