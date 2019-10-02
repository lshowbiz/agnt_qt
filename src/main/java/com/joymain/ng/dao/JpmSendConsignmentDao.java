package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpmSendConsignment;

/**
 * An interface that provides a data management interface to the JpmSendConsignment table.
 */
public interface JpmSendConsignmentDao extends GenericDao<JpmSendConsignment, Long> {

    public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo);
    
    public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo);
    
    public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo);
}