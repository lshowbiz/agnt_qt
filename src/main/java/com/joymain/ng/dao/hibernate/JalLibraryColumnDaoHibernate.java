package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JalLibraryColumn;
import com.joymain.ng.dao.JalLibraryColumnDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalLibraryColumnDao")
public class JalLibraryColumnDaoHibernate extends GenericDaoHibernate<JalLibraryColumn, Long> implements JalLibraryColumnDao {

    public JalLibraryColumnDaoHibernate() {
        super(JalLibraryColumn.class);
    }

	@Override
	public List getJalLibraryColumnListByPlateIndex(String plateIndex) {
		
		String sql="from JalLibraryColumn j where j.plateId=(select plateId from JalLibraryPlate p where p.plateIndex="+plateIndex+")";
		return this.getSession().createQuery(sql).list();
	}
}
