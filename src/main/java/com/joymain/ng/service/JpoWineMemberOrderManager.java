/*
 * 文件名：  JpoWineMemberOrderManager.java 2013-11-26
 * 版权：    Copyright 2000-2013 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2013-11-26
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */
package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;

/**
 * <p>
 * Title: (Initialize)
 * </p>
 * <p>
 * Description: (Initialize)
 * </p>
 * 
 * @author jfoy
 * @version [RBT_CNCMSXV5.0D605SP33, 2013-11-26]
 * @since SP33
 */
@WebService
public interface JpoWineMemberOrderManager extends GenericManager<JpoMemberOrder, Long>
{
    // 根据条件查询会员酒业订单
    public List<JpoMemberOrder> getWineOrderByParam(Map<String, String> map);
    
    /**
     * 查询酒业商品信息
     * @param map
     * @return
     */
    public List<JpoMemberOrderList> getWineProductOrderByParam(Map<String, String> map);
    
    /**
     * 根据订单编号查询订单下商品已配置总数量
     * @param map
     * @return
     */
    public Long getWineProductConfigCountByOrderId(Map<String, Long> map);
    
    /**
     * 根据订单商品编号查询订单下商品已配置总数量
     * @param map
     * @return
     */
    public Long getWineProductConfigCountByMolId(Map<String,Long> map);
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String,Long> map);
    
    /**
     * 根据商品id查询商品编码
     * @param map
     * @return
     */
    public String getProductNoByProductId(Map<String,Long> map);
    
}
