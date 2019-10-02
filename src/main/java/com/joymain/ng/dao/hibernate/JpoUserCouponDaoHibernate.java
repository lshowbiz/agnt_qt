package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JpmCouponInfo;
import com.joymain.ng.model.JpoUserCoupon;
import com.joymain.ng.util.GroupPage;

import jsx3.gui.IFrame;

import com.joymain.ng.dao.JpmCouponInfoDao;
import com.joymain.ng.dao.JpoUserCouponDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("jpoUserCouponDao")
public class JpoUserCouponDaoHibernate extends GenericDaoHibernate<JpoUserCoupon, Long> implements JpoUserCouponDao {
	
	@Autowired
	private JpmCouponInfoDao jpmCouponInfoDao;

    public JpoUserCouponDaoHibernate() {
        super(JpoUserCoupon.class);
    }

	@Override
	public List getJpoUserCouponPageToSql(Map<String, String> params, GroupPage page) {
		String userCode = params.get("userCode");
		String status=params.get("status");
		
		String sql="select juc.*,jcr.mo_id "
				+ "from JPO_USER_COUPON juc LEFT JOIN "
				+ "JPO_COUPON_RELATIONSHIP jcr "
				+ "on juc.cp_id=jcr.cp_id where user_code='CN888888'";
		
		return this.findObjectsBySQL(sql, page);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getJpoUserCouponToSql(Map<String, String> params) {
		
		String userCode=params.get("userCode");
		String status=params.get("status");
		String sql="SELECT"+
						" CIR.*, JPO.MEMBER_ORDER_NO jpoMemberOrderNo"+
					" FROM"+
						" (SELECT"+
								" JUC. ID jucId,"+
								" JUC.USER_CODE jucUserCode,"+
								" JUC.STATUS jucStatus,"+
								" JUC.CP_ID jucCpId,"+
								" JCI.CP_NAME jciCpName,"+
								" JCI.CP_VALUE jciCpValue,"+
								" JCI.STATUS jciStatus,"+
								" JCR.mo_id jcrMoId,"+
								" JCR.CREATE_TIME jcrUseTime"+	
							" FROM"+
								" JPO_USER_COUPON JUC"+
							" LEFT JOIN JPM_COUPON_INFO JCI ON JUC.cp_id = JCI.cp_id"+
							" LEFT JOIN JPO_COUPON_RELATIONSHIP JCR ON JUC.id = JCR.cp_id"+
							" WHERE"+
								" JUC.USER_CODE = ?"+
								" AND JCI.STATUS = 0 "+
								" AND (JUC.ABLE_STATUS IS NULL OR JUC.ABLE_STATUS='Y')";
		if (StringUtils.isEmpty(status)) {
			sql+=" ORDER BY JUC.CREATE_TIME DESC"+
					" ) CIR"+
					" LEFT JOIN JPO_MEMBER_ORDER JPO ON JPO.MO_ID = CIR.jcrMoId";
		}else{
			if (status.equals("0")) {//如果未使用，则根据创建时间排序
				sql+=" AND JUC.STATUS = ?"+
						" ORDER BY JUC.CREATE_TIME DESC"+
						" ) CIR"+
						" LEFT JOIN JPO_MEMBER_ORDER JPO ON JPO.MO_ID = CIR.jcrMoId";
			}
			if (status.equals("1")) {//如果使用，根据使用时间排序
				sql+=" AND JUC.STATUS = ?"+
						" ORDER BY JCR.CREATE_TIME DESC"+
						" ) CIR"+
						" LEFT JOIN JPO_MEMBER_ORDER JPO ON JPO.MO_ID = CIR.jcrMoId";
			}
			
		}
		List<Map> resultMapList=new ArrayList<Map>();
		List<Map<String, Object>> queryForList=new ArrayList<Map<String,Object>>();
		if (StringUtils.isEmpty(status)) {
			queryForList = this.jdbcTemplate.queryForList(sql, userCode);
		}else{
			queryForList = this.jdbcTemplate.queryForList(sql, userCode,status);
		}
		
		for (Map<String, Object> map : queryForList) {
			Map resultMap=new HashMap();
			resultMap.put("jucId", map.get("JUCID"));
			resultMap.put("jucUserCode", map.get("JUCUSERCODE"));
			resultMap.put("jucStatus", map.get("JUCSTATUS"));
			resultMap.put("jucCpId", map.get("JUCCPID"));
			resultMap.put("jciCpName", map.get("JCICPNAME"));
			resultMap.put("jciCpValue", map.get("JCICPVALUE"));
			resultMap.put("jciStatus", map.get("JCISTATUS"));
			resultMap.put("jcrMoId", map.get("JCRMOID"));
			resultMap.put("JpoMemberOrderNo", map.get("JPOMEMBERORDERNO"));
			resultMap.put("jcrUseTime", map.get("JCRUSETIME"));
			resultMapList.add(resultMap);
		}
		return resultMapList;
	}
	
	/**
	 * Add By WuCF 20170523
	 * 通过会员编号获得会员拥有的代金券信息
	 * @param userCode
	 * @return
	 */
	@Override
	public List getJpoUserCoupons(String userCode) {
		/*select * from JPO_USER_COUPON t1,JPM_COUPON_INFO t2 where t1.cp_id=t2.cp_id and 
		(t1.start_time<=sysdate or t1.start_time is null) and (t1.end_time>sysdate or t1.end_time is null) and 
		(t2.start_time<=sysdate or t2.start_time is null) and (t2.end_time>sysdate or t2.end_time is null) and t2.status='0' 
		and t1.user_code='CN12045192';*/
		/*StringBuffer hqlBuf = new StringBuffer("select juc from JpoUserCoupon juc,JpmCouponInfo jci where juc.cpId=jci.cpId");
		hqlBuf.append(" and (juc.startTime<=sysdate or juc.startTime is null) ");
		hqlBuf.append(" and (juc.endTime>sysdate or juc.endTime is null) ");
		hqlBuf.append(" and (jci.startTime<=sysdate or jci.startTime is null) ");
		hqlBuf.append(" and (jci.endTime>sysdate or jci.endTime is null) and jci.status='0' ");
		hqlBuf.append(" and juc.userCode=?");
	
		Query q = this.getSession().createQuery(hqlBuf.toString());
		q.setParameter(0, userCode);
		
		return q.list();*/
		StringBuffer sqlBuf = new StringBuffer("select t1.id USER_CP_ID,t2.cp_id,t2.cp_value,t2.cp_name,t2.status from JPO_USER_COUPON t1,JPM_COUPON_INFO t2 where t1.cp_id=t2.cp_id and ");
		sqlBuf.append(" (t1.start_time<=sysdate or t1.start_time is null) and (t1.end_time>sysdate or t1.end_time is null) and t1.status='0' and (t1.able_status is null or t1.able_status<>'N') and ");
		sqlBuf.append(" (t2.start_time<=sysdate or t2.start_time is null) and (t2.end_time>sysdate or t2.end_time is null)   ");
		sqlBuf.append(" and t1.user_code=? ");
	
		return this.jdbcTemplate.queryForList(sqlBuf.toString(), userCode);
	}

	@Override
	public JpoUserCoupon getJpoUserCouponById(String id) {
		JpoUserCoupon jpoUserCoupon = this.get(Long.valueOf(id));
		return jpoUserCoupon;
	}
}
