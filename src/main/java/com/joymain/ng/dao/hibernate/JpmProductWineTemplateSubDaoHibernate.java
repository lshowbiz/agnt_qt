package com.joymain.ng.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmProductWineTemplateSubDao;
import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JpmProductWineTemplateSub;

@Repository("jpmProductWineTemplateSubDao")
public class JpmProductWineTemplateSubDaoHibernate extends GenericDaoHibernate<JpmProductWineTemplateSub, String> implements JpmProductWineTemplateSubDao {

    public JpmProductWineTemplateSubDaoHibernate() {
        super(JpmProductWineTemplateSub.class);
    }

    @Override
    public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(
        Map<String, String> map)
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplateSub j where j.jpmProductWineTemplate.productTemplateNo='"+map.get("productTemplateNo")+"'");
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }

    @Override
    public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo)
    {
        StringBuffer hql = new StringBuffer("from JpmProductWineTemplateSub where subNo = '" + subNo +"'");
        return (JpmProductWineTemplateSub)this.getObjectByHqlQuery(hql.toString());
    }
}
