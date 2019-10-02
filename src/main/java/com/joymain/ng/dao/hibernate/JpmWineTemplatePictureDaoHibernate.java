package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmWineTemplatePictureDao;
import com.joymain.ng.model.JpmWineTemplatePicture;

@Repository("jpmWineTemplatePictureDao")
public class JpmWineTemplatePictureDaoHibernate extends GenericDaoHibernate<JpmWineTemplatePicture, Long> implements JpmWineTemplatePictureDao {

    public JpmWineTemplatePictureDaoHibernate() {
        super(JpmWineTemplatePicture.class);
    }

    @Override
    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureListBySubNo(String subNo)
    {
        StringBuffer hql = new StringBuffer("from JpmWineTemplatePicture j where j.jpmProductWineTemplateSub.subNo = " + subNo);
        Query query = getSession().createQuery(hql.toString());
        return query.list();
    }
}
