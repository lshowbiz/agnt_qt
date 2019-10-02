package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.model.JpmWineSettingInf;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.dao.JpmWineSettingInfDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jpmWineSettingInfDao")
public class JpmWineSettingInfDaoHibernate extends GenericDaoHibernate<JpmWineSettingInf, Long> implements JpmWineSettingInfDao {

    public JpmWineSettingInfDaoHibernate() {
        super(JpmWineSettingInf.class);
    }

    @Override
    public List<JpmWineSettingInf> getListPage(GroupPage page) {
        String totalHql = "select count(*) from JpmWineSettingInf order by createTime desc";
        String hqlQuery = "from JpmWineSettingInf order by createTime desc";
        return findObjectsByHQL(totalHql, hqlQuery, page);
    }

    @Override
    public void saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf) {
        this.getSession().saveOrUpdate(jpmWineSettingInf);
    }

    @Override
    public JpmWineSettingInf getJpmWineSettingInf(Long settingId) {
        return (JpmWineSettingInf) this.getSession().get(JpmWineSettingInf.class, settingId);
    }
}
