package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JpmMemberConfig;

/**
 * An interface that provides a data management interface to the JpmMemberConfig
 * table.
 */
public interface JpmMemberConfigDao extends GenericDao<JpmMemberConfig, Long>
{
    /**
     * 添加配置信息 
     * @return
     */
    public Integer addJpmMemberConfig(JpmMemberConfig jpmMemberConfig);
    
    /**
     * 修改配置信息
     * @return
     */
    public Integer modifyJpmMemberConfig(JpmMemberConfig jpmMemberConfig);
    
    /**
     * 删除配置信息
     * @param configNo
     * @return
     */
    public Integer delJpmMemberConfig(Long configNo);
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map);
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map);
    
    public JpmMemberConfig getJpmMemberConfigByconfigNo(Long configNo);
    
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo,String status,Long price);
    
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo,String status);
    
    public String getProductNoByProductId(String productId);
}