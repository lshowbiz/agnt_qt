package com.joymain.ng.dao;

import java.util.List;
import java.util.Set;

import com.joymain.ng.model.JpmConfigDetailed;

/**
 * An interface that provides a data management interface to the
 * JpmConfigDetailed table.
 */
public interface JpmConfigDetailedDao extends GenericDao<JpmConfigDetailed, Long>
{
    public Integer addJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed);
    
    public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed);
    
    public Integer delJpmConfigDetailed(Long detailedId);
    
    public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo);
    
    public void saveJpmConfigDetailedList(Set<JpmConfigDetailed> jpmConfigDetailedList);
    
    public void delJpmConfigDetailedBySpecNo(Long specNo);
    
    /**
     * 根据规格id查询对应配件数量
     * @param specNo
     * @return
     */
    public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo);
}