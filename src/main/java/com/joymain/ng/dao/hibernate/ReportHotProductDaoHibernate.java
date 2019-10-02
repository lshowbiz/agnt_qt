package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.ReportHotProduct;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.ReportHotProductDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("reportHotProductDao")
public class ReportHotProductDaoHibernate extends GenericDaoHibernate<ReportHotProduct, Long> implements ReportHotProductDao {

    public ReportHotProductDaoHibernate() {
        super(ReportHotProduct.class);
    }

	@Override
	public List getHotProductReport(String jperiod, String province, String city) {
		
		String sql = "select t.PRODUCT_NAME,t.PRODUCT_NUM from REPORT_HOT_PRODUCT t where rownum <=10 ";
		
		if(!StringUtil.isEmpty(jperiod)){
			
			String year = jperiod.substring(0, 3);
			String month = jperiod.substring(4);
			
			sql += " and TIME_ID in(select TIME_ID from REPORT_TIME where YEAR_NAME="+year+" and MONTH_NAME="+month+")";
		}
		
		if(!StringUtil.isEmpty(province)){
			
			sql += " and PROVINCE_ID='"+province+"'";
		}

		if(!StringUtil.isEmpty(city)){
			
			sql += " and CITY_ID='"+city+"'";
		}
		
		sql += " order by t.PRODUCT_NUM desc";
		
		return this.jdbcTemplate.queryForList(sql);
	}
}
