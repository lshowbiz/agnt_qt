package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.InwDemandsort;
import com.joymain.ng.dao.InwDemandsortDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("inwDemandsortDao")
public class InwDemandsortDaoJpa extends GenericDaoHibernate<InwDemandsort, Long> implements InwDemandsortDao {

    public InwDemandsortDaoJpa() {
        super(InwDemandsort.class);
    }

    /**
     * 需求分类初始化查询(这个查询时没有任何查询条件的)
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	public List getDemandsortList() {
		//最后查询结果要按一定的顺序排序  ----根据展示的需要进行排序
		String sql = " select * from inw_demandsort where 1=1  order by   id  asc";
		return this.jdbcTemplate.queryForList(sql);
	}
    
    /**
     * 查询该需求大类上的所有小类的集合
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	public List getDemandsortDetailList(String id) {
		//最后查询结果要按一定的顺序排序  ----根据展示的需要进行排序
		String sql = " select a.id demandsortId,a.name sortname,b.id id,b.name name,b.summary summary,b.demand_Image demand_Image  from inw_demandsort a,inw_demand b  where a.id=b.demandsort_id  and b.verify = 'Y' and  a.id='"+id+"'   order  by  b.id  asc";
		return this.jdbcTemplate.queryForList(sql);
	}


	/**
	 * 创新共赢---需求分类-----获取需求分类的分类名称
	 * @author gw  2013-11-08
	 * @param demandsort_id
	 * @return string
	 */
	public String getInwDemandsortById(String demandsortId) {
		String hql = " from InwDemandsort where id='"+demandsortId+"'";
	    InwDemandsort inwDemandsort = (InwDemandsort)this.getObjectByHqlQuery(hql);
		return inwDemandsort.getName();
	}
	
	
}
