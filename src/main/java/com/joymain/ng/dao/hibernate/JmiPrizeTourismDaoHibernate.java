package com.joymain.ng.dao.hibernate;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JmiPrizeTourismDao;
import com.joymain.ng.model.JmiPrizeTourism;

@Repository("jmiPrizeTourismDao")
public class JmiPrizeTourismDaoHibernate extends GenericDaoHibernate<JmiPrizeTourism, String> implements JmiPrizeTourismDao {

    public JmiPrizeTourismDaoHibernate() {
        super(JmiPrizeTourism.class);
    }

	@Override
	public JmiPrizeTourism getJmiPrizeTourismByUsercode(String userCode) {
		// TODO Auto-generated method stub
		String hql = " from JmiPrizeTourism a where a.userCode='"+userCode+"' ";
		return (JmiPrizeTourism) this.getObjectByHqlQuery(hql);
	}

	@Override
	public void saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(jmiPrizeTourism);
	}

	@Override
	public String getPassStarByUserCode(String userCode) {
		// TODO Auto-generated method stub
		StringBuffer sql= new StringBuffer("select nvl(pass_star, 0) as pass_star from jbd_travel_point2014 where user_code='"+userCode+"' ") ;
		
		Map map= this.findOneObjectBySQL(sql.toString());
		if(map!=null&&!map.isEmpty()){
			return map.get("pass_star").toString();
		}else{
			return "0";
		}
	}

	@Override
	public String geIspeerByUserCode(String userCode) {
		StringBuffer sql= new StringBuffer("select * from jmi_special_list where user_code='"+userCode+"' and sc_type='2'") ;
		
		Map map= this.findOneObjectBySQL(sql.toString());
		if(map!=null&&!map.isEmpty()){
			return "1";
		}else{
			return "0";
		}
	}
}
