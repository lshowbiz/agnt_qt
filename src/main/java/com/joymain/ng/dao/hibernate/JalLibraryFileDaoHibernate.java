package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JalLibraryFile;
import com.joymain.ng.dao.JalLibraryFileDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalLibraryFileDao")
public class JalLibraryFileDaoHibernate extends GenericDaoHibernate<JalLibraryFile, Long> implements JalLibraryFileDao {

    public JalLibraryFileDaoHibernate() {
        super(JalLibraryFile.class);
    }

	@Override
	public List selectLibraryFilesByColumnId(String columnId) {
		// TODO Auto-generated method stub
		String sql="from JalLibraryFile j where j.columnId="+columnId;
		return this.getSession().createQuery(sql).list();
	}
}
