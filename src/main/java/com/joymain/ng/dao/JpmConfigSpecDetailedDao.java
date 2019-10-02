package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.JpmConfigSpecDetailed;

/**
 * An interface that provides a data management interface to the JpmConfigSpecDetailed table.
 */
public interface JpmConfigSpecDetailedDao extends GenericDao<JpmConfigSpecDetailed, Long> {

    public void insertJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed);
    
    public void modifyJpmConfigSpecDetailed(JpmConfigSpecDetailed jpmConfigSpecDetailed);
    
    public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo);
    
    public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo);
    
    public void delJpmConfigSpecDetailedBySpecNo(Long specNo);
    
    public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo);
    
    public Long getPriceByConfigNo(String configNo);
}