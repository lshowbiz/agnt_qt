package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JalLibraryPlate;
import com.joymain.ng.dao.JalLibraryPlateDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalLibraryPlateDao")
public class JalLibraryPlateDaoHibernate extends GenericDaoHibernate<JalLibraryPlate, Long> implements JalLibraryPlateDao {

    public JalLibraryPlateDaoHibernate() {
        super(JalLibraryPlate.class);
    }

	@Override
	public List getJalLibraryPlatesOrderByIndex() {
		
		String sql="from JalLibraryPlate jalLibraryPlate order by jalLibraryPlate.plateIndex";
		
		return this.getSession().createQuery(sql).list();
	}
}
