package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.PdLogisticsBaseDetail;
import com.joymain.ng.model.PdLogisticsBaseNum;
import com.joymain.ng.dao.PdLogisticsBaseNumDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("pdLogisticsBaseNumDao")
public class PdLogisticsBaseNumDaoHibernate extends GenericDaoHibernate<PdLogisticsBaseNum, Long> implements PdLogisticsBaseNumDao {

    public PdLogisticsBaseNumDaoHibernate() {
        super(PdLogisticsBaseNum.class);
    }

    /**
	 * @author gw 2014-11-11----modify 2014-12-08 该方法弃用
	 * 报单中心前台-物流跟踪查询 
	 * @param moId
	 * @return
	 */
	public List getPdLogisticsBaseNumAndDetail(String moId) {
		
		String sql = " select a.pdlogisticsbasenum_no, a.name, a.status, b.erp_goods_bn, b.product_no, b.qty "+
		   " from pd_logistics_base_num a, pd_logistics_base_detail b "+
		   " where a.num_id = b.num_id "+
		   " and a.base_id in "+
	       " ( select base_id from pd_logistics_base c where c.member_order_no = '"+moId+"' ) ";
		
		return this.jdbcTemplate.queryForList(sql);
		
	}

	/**
	 * 根据物流单号获取物流信息
	 * @author gw 2015-02-11
	 * @param mailNo
	 * @return pdLogisticsBaseNum
	 */
	public PdLogisticsBaseNum getPdLogisticsBaseNumForMailNo(String mailNo) {
		String hql = " from PdLogisticsBaseNum pdLogisticsBaseNum where pdLogisticsBaseNum.pdLogisticsBaseNum_no='"+mailNo+"'";
		List<PdLogisticsBaseNum> list = getSession().createQuery(hql).list();
		if(null!=list){
			if(list.size()>0){
				return list.get(0);
			}
		}
		/*String sql = " select * from PD_LOGISTICS_BASE_NUM a where a.PDLOGISTICSBASENUM_NO = '"+mailNo+"'";
		List list = this.jdbcTemplate.queryForList(sql);
		if(null!=list){
			if(list.size()>0){
				return new PdLogisticsBaseNum();
			}
		}*/
		return null;
	}
}
