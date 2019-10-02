package com.joymain.ng.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmProductWineTemplateDao;
import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.model.JpmProductWineTemplate;

/**
 * 
 * <p>Title: (Initialize)</p>
 * <p>Description: (Initialize)</p>
 * @author  jfoy
 * @version  [ 2013-12-2]
 * @since 
 */
@Repository("jpmProductWineTemplateDao")
public class JpmProductWineTemplateDaoHibernate extends GenericDaoHibernate<JpmProductWineTemplate, Long> implements JpmProductWineTemplateDao {

    public JpmProductWineTemplateDaoHibernate() {
        super(JpmProductWineTemplate.class);
    }

    /**
     * 查询出所有可用模版
     * @param map
     * @return
     */
    @Override
    public List<JpmProductWineTemplate> getTemplateList()
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplate j where j.isInvalid = '0'");
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public List<JpmProductWineTemplate> getTemplateListByProductNo(String productNo)
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplate j where j.isInvalid = '0' and j.productNo = '"+productNo+"'");
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public JpmProductWineTemplate getJpmProductWineTemplate(Long productTemplateNo)
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplate j where j.productTemplateNo = "+ productTemplateNo);
        return (JpmProductWineTemplate)this.getObjectByHqlQuery(hql.toString());
    }
}
