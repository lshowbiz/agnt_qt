package com.joymain.ng.dao;

import java.util.Map;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpoMemberOrderList;

/**
 * An interface that provides a data management interface to the JpoMemberOrderList table.
 */
public interface JpoMemberOrderListDao extends GenericDao<JpoMemberOrderList, Long> {

    /**
     * 根据订单明细流水号查询商品重量等信息　 
     * @param map
     * @return
     */
    public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String,Long> map);
    /**
     * 写死端午节商品统计数量
     * @param proNo
     * @return
     */
    public Long getProSumByProNo(String proNo);
    
    /**
     * 库存清货数量限制
     * @param proNo
     * @param statetime
     * @param endtime
     * @return
     */
    public Long getProSumByProNo(String proNo,String statetime,String endtime);
}