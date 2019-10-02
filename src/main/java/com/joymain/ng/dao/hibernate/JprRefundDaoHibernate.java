package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JprRefund;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.JprRefundDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jprRefundDao")
public class JprRefundDaoHibernate extends GenericDaoHibernate<JprRefund, String> implements JprRefundDao {

    public JprRefundDaoHibernate() {
        super(JprRefund.class);
    }

	@Override
	public List getJprRefundsListPage(GroupPage page, String roNo,
			String memberOrderNo, String userCode, String timeBegin,
			String timeEnd) {
		  String sql = "select a.member_order_no memberOrderNo,b.ro_no roNo,b.order_date orderDate,b.refund_status refundStatus " +
		  		" from jpr_refund b,jpo_member_order a where a.mo_id=b.mo_id " +
		  		" and  b.user_code = '"+userCode+"' ";
		  if(!StringUtil.isEmpty(roNo)){
				sql += " and b.ro_no like '%"+roNo+"%' ";
		  }
		  
		/*  if(!StringUtil.isEmpty(memberOrderNo)){
				sql += " and b.mo_id ='"+memberOrderNo+"' ";
		  }*/
		  if(!StringUtil.isEmpty(memberOrderNo)){
				sql += " and a.member_order_no  ='"+memberOrderNo+"' ";
		  }
		  
		  if(!StringUtil.isEmpty(timeBegin)){
				sql += " and b.order_date >=to_date('"+timeBegin+" 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		  }
		  if(!StringUtil.isEmpty(timeEnd)){
				sql += " and b.order_date <=to_date('"+timeEnd+" 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		  }
		  
			sql += " order by b.ro_no desc ";

		  return this.findObjectsBySQL(sql, page);
		  
	}

	@Override
	public JprRefund getJprRefunds(String roNo) {
		
		
		
		
		return null;
	}
}
