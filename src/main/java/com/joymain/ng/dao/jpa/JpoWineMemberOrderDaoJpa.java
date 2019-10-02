/*
 * 文件名：  JpoWineMemberOrderDaoJpa.java 2013-11-26
 * 版权：    Copyright 2000-2013 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2013-11-26
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */
package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpoWineMemberOrderDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JpmProductWineTemplate;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;

 /**
 * <p>Title: (Initialize)</p>
 * <p>Description: (Initialize)</p>
 * @author  jfoy
 * @version  [RBT_CNCMSXV5.0D605SP33, 2013-11-26]
 * @since SP33
 */
@Repository("jpoWineMemberOrderDao")
public class JpoWineMemberOrderDaoJpa extends GenericDaoHibernate<JpoMemberOrder, Long> implements
    JpoWineMemberOrderDao
{
    
    public JpoWineMemberOrderDaoJpa()
    {
        super(JpoMemberOrder.class);
    }

    @Override
    public List<JpoMemberOrder> getWineOrderByParam(Map<String, String> map)
    {
        StringBuffer hql=new StringBuffer("from JpoMemberOrder po where po.sysUser.userCode='"+map.get("userCode")+"' and po.sysUser.companyCode='"+map.get("companyCode")+"' and status = '2'");    
        String memberOrderNo=map.get("memberOrderNo");//订单编号
        String configStatus=map.get("status");//订单状态
        String orderType=map.get("orderType");//订单类型
        String isShipments=map.get("isShipments");//发货状态
        if(!StringUtils.isEmpty(memberOrderNo))
        {
            hql.append("  and po.memberOrderNo='"+memberOrderNo+"'");
        }
        if(!StringUtils.isEmpty(orderType))
        {
            hql.append(" and po.orderType='"+orderType+"'");
        }
        if(!StringUtils.isEmpty(isShipments))
        {
            if(!"-1".equals(isShipments))
            {
              hql.append(" and po.isShipments='"+isShipments+"'");
            }
        }
        if(StringUtils.isNotEmpty(configStatus))
        {
            if(!"-1".equals(configStatus))
            {
                //配置表中的配置状态删除，在订单表中增加
                hql.append(" and po.configStatus = '"+ configStatus +"'");
            }
        }
        hql.append(" order by po.moId desc");
        Query q=getSession().createQuery(hql.toString());
        return q.list();
    }
    
    @Override
    public List getWineOrderList()
    {
        StringBuffer hql = new StringBuffer();
        hql.append("select ptt_id from jpm_product_sale_team_type where uni_no in ");
        //'P1506010101CN0','P1501010101CN0'箱装酒
        hql.append("(select uni_no from jpm_product_sale_new where product_no in ('P1505010101CN0','P1503010101CN0'))");
        return this.getJdbcTemplate().queryForList(hql.toString());
    }

    /**
     * 酒业商品信息查询
     */
    @Override
    public List<JpoMemberOrderList> getWineProductOrderByParam(Map<String, String> map)
    {
        StringBuffer hql = new StringBuffer("from JpoMemberOrderList po where po.jpoMemberOrder.sysUser.userCode='"+map.get("userCode")+"' and po.jpoMemberOrder.sysUser.companyCode='"+map.get("companyCode")+"'");
        String moId = map.get("moId");
        if(!StringUtils.isEmpty(moId))
        {
            hql.append("  and po.jpoMemberOrder.moId='"+moId+"'");
        }
        hql.append(" and po.jpmProductSaleTeamType.jpmProductSaleNew.productNo in('P1506010101CN0','P1505010101CN0','P1503010101CN0','P1501010101CN0')");
        Query q=getSession().createQuery(hql.toString());
        return q.list();
    }

    /**
     * 根据订单编号查询订单下商品已配置总数量
     * @param map
     * @return
     */
    @Override
    public Long getWineProductConfigCountByOrderId(Map<String, Long> map)
    {
        StringBuffer hql = new StringBuffer("select count(pm.amount) from JpmMemberConfig pm where pm.status = '0' and pm.moId = "+map.get("moId"));
        return (Long)getSession().createQuery(hql.toString()).uniqueResult();
    }

    /**
     * 根据订单商品编号查询订单下商品已配置总数量
     * @param map
     * @return
     */
    @Override
    public Long getWineProductConfigCountByMolId(Map<String, Long> map)
    {
        StringBuffer hql = new StringBuffer("select count(pm.amount) from JpmMemberConfig pm where pm.molId = "+map.get("molId"));
        return (Long)getSession().createQuery(hql.toString()).uniqueResult();
    }

    /**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    @Override
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map)
    {
        StringBuffer hql = new StringBuffer("select j from JpmMemberConfig j where j.molId = '"+map.get("molId")+"'");
        Query q=getSession().createQuery(hql.toString());
        return q.list();
    }
    
    /**
     * 根据商品id查询商品编码
     * @param map
     * @return
     */
    public String getProductNoByProductId(Map<String,Long> map)
    {
        StringBuffer hql = new StringBuffer("select product_no from jpm_product_sale_new where uni_no = (select uni_no from jpm_product_sale_team_type where ptt_id = '"+map.get("productId")+"')");
        List strList = this.getJdbcTemplate().queryForList(hql.toString());
        if(CollectionUtils.isNotEmpty(strList))
        {
            Map strMap = (Map)strList.get(0);
            return strMap.get("product_no").toString();
        }
        return null;
    }
    

}

