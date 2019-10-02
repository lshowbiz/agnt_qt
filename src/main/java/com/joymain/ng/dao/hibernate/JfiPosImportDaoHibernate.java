package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JfiPosImport;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.JfiPosImportDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jfiPosImportDao")
public class JfiPosImportDaoHibernate extends GenericDaoHibernate<JfiPosImport, Long> implements JfiPosImportDao {

    public JfiPosImportDaoHibernate() {
        super(JfiPosImport.class);
    }

	@Override
	public List getJfiPosImports(JfiPosImport jfiPosImport) {
		
		String sql="from JfiPosImport f where ";
		
			sql+=" f.posNo='"+jfiPosImport.getPosNo()+"'";
			
			sql+=" and f.amount="+jfiPosImport.getAmount();

			sql+=" and f.PId='"+jfiPosImport.getPId()+"'";
		
			sql+=" and f.tel='"+jfiPosImport.getTel()+"'";
	
			sql+=" and f.status='"+jfiPosImport.getStatus()+"'";
		
			sql+=" and f.inc='"+jfiPosImport.getInc()+"'";
		
		return this.getSession().createQuery(sql).list();
	}
}
