package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.dao.JpmWineOrderInterfaceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jpmWineOrderInterfaceDao")
public class JpmWineOrderInterfaceDaoHibernate extends GenericDaoHibernate<JpmWineOrderInterface, Long> implements JpmWineOrderInterfaceDao {

    public JpmWineOrderInterfaceDaoHibernate() {
        super(JpmWineOrderInterface.class);
    }

    @Override
    public List<JpmWineOrderInterface> getListPage(GroupPage page) {
        String totalHql = "select count(*) from JpmWineOrderInterface order by createTime desc";

        String hqlQuery = "from JpmWineOrderInterface order by createTime desc";
        return findObjectsByHQL(totalHql, hqlQuery, page);
    }

    @Override
    public void saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface) {
        this.getSession().saveOrUpdate(jpmWineOrderInterface);

    }

    @Override
    public JpmWineOrderInterface getJpmWineOrderInterface(final Long moId) {
        return (JpmWineOrderInterface) this.getSession().get(JpmWineOrderInterface.class, moId);
    }
}
