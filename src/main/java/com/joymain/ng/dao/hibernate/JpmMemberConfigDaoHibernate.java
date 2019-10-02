package com.joymain.ng.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmMemberConfigDao;
import com.joymain.ng.model.JpmMemberConfig;

@Repository("jpmMemberConfigDao")
public class JpmMemberConfigDaoHibernate extends GenericDaoHibernate<JpmMemberConfig, Long> implements JpmMemberConfigDao {

    public JpmMemberConfigDaoHibernate() {
        super(JpmMemberConfig.class);
    }


    @Override
    public Integer delJpmMemberConfig(Long configNo)
    {
        StringBuffer sql = new StringBuffer("delete from jpm_member_config where config_no = " + configNo);
        this.getJdbcTemplate().execute(sql.toString());
        return 0;
    }

    @Override
    public Integer addJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        this.getSession().saveOrUpdate(jpmMemberConfig);
        return 0;
    }

    @Override
    public Integer modifyJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    @Override
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map)
    {
        //查询为已支付完成，配置完成的数量
        StringBuffer hql = new StringBuffer("from JpmMemberConfig where molId = '"+map.get("molId")+"'");
        Query q=getSession().createQuery(hql.toString());
        return q.list();
    }


    @Override
    public JpmMemberConfig getJpmMemberConfigByconfigNo(Long configNo)
    {
        StringBuffer hql = new StringBuffer("from JpmMemberConfig j where j.configNo="+configNo);
        return (JpmMemberConfig)this.getObjectByHqlQuery(hql.toString());
    }


    @Override
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map)
    {
      //查询为已支付完成，配置完成的数量
        StringBuffer hql = new StringBuffer("from JpmMemberConfig where molId = '"+map.get("molId")+"'");
        Query q=getSession().createQuery(hql.toString());
        return q.list();
    }


    @Override
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo,String status,Long price)
    {
        StringBuffer sql = null;
        if(price != null)
        {
            sql = new StringBuffer("update jpm_member_config set status='"+ status +"' , price="+ price +" where config_no='"+ configNo +"'");
        }
        else
        {
            sql = new StringBuffer("update jpm_member_config set status='"+ status +"' where config_no='"+ configNo +"'");
        }
        this.getJdbcTemplate().execute(sql.toString());
    }


    @Override
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo, String status)
    {
        StringBuffer hql = new StringBuffer("from JpmMemberConfig where configNo = '"+ configNo +"'");
        JpmMemberConfig config = (JpmMemberConfig)this.getObjectByHqlQuery(hql.toString());
        hql = new StringBuffer("from JpmMemberConfig where moId = " + config.getMoId());
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }


    @Override
    public String getProductNoByProductId(String productId)
    {
        StringBuffer sql = new StringBuffer("select n.product_no from jpm_product_sale_new n where n.uni_no = (");
        sql.append("select t.uni_no from jpm_product_sale_team_type t where t.ptt_id ='"+ productId +"')");
        return (String)getSession().createQuery(sql.toString()).uniqueResult();
    }
}
