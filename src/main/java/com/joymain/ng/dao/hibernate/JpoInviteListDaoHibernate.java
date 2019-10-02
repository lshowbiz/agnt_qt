package com.joymain.ng.dao.hibernate;

import com.joymain.ng.dao.JpoInviteListDao;
import com.joymain.ng.model.JpoInviteList;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("jpoInviteListDao")
public class JpoInviteListDaoHibernate extends GenericDaoHibernate<JpoInviteList, Long> implements JpoInviteListDao {

    public JpoInviteListDaoHibernate() {
        super(JpoInviteList.class);
    }

	@Override
	public List getJpoInviteListPage(GroupPage page, CommonRecord crm) {
		// TODO Auto-generated method stub
		StringBuffer sqlCount = new StringBuffer("select count(*) from JpoInviteList t where 1=1 and t.status<>'2' ");
    	//使用会员编号
    	String inviteUserCode = crm.getString("inviteUserCode", "");
    	if(!StringUtil.isEmpty(inviteUserCode)){
    		sqlCount.append(" and t.userCode ='");
    		sqlCount.append(inviteUserCode);
    		sqlCount.append("' ");
    	} 
    	//邀请码
    	String inviteCode = crm.getString("inviteCode", "");
    	if(!StringUtil.isEmpty(inviteCode)){
    		sqlCount.append(" and t.inviteCode ='");
    		sqlCount.append(inviteCode);
    		sqlCount.append("' ");
    	} 
  
  /*
    	//订单编号
    	String memberOrderNo = crm.getString("memberOrderNo", "");
    	if(!StringUtil.isEmpty(memberOrderNo)){
    		sqlCount.append(" and t.memberOrderNo ='");
    		sqlCount.append(memberOrderNo);
    		sqlCount.append("' ");
    	} 
  */  	
    	sqlCount.append(" order by t.id,t.createTime desc ");
 
    	//==================询数据语句
    	//代金券状态为“作废”的不显示
    	StringBuffer sql = new StringBuffer(" from JpoInviteList t where 1=1 and t.status<>'2' "); 
    	//使用会员编号
    	if(!StringUtil.isEmpty(inviteUserCode)){
    		sql.append(" and t.userCode ='");
    		sql.append(inviteUserCode);
    		sql.append("' ");
    	} 
    	//邀请码
    	if(!StringUtil.isEmpty(inviteCode)){
    		sql.append(" and t.inviteCode ='");
    		sql.append(inviteCode);
    		sql.append("' ");
    	} 
 
  /*  	
    	//订单编号
    	if(!StringUtil.isEmpty(memberOrderNo)){
    		sql.append(" and t.memberOrderNo ='");
    		sql.append(memberOrderNo);
    		sql.append("' ");
    	} 
   */ 	
    	sql.append(" order by t.id ,t.createTime desc ");
    	
    	//返回时调用分页的查询的方法  
    	log.info("getJpoMemberOrderPage-sqlCount："+sqlCount.toString());
    	log.info("getJpoMemberOrderPage-sql："+sql.toString());
    	return this.findObjectsByHQL(sqlCount.toString(), sql.toString(), page);
    	
	}

	/**
	 * @author fu 2017-5-26
	 * 手机端我的邀请码查询接口
	 */
	public List getJpoInviteList(Map<String, String> params, GroupPage page) {
		String userCode = params.get("userCode");
		String inviteCode = params.get("inviteCode");
		String memberOrderNo = params.get("memberOrderNo");
        String status = params.get("status");
        String inviteType = params.get("inviteType");
		String useUserCode = params.get("useUserCode");
        //modify by  fu 2017-5-31 手机端我的邀请码查询---已作废的邀请码不给用户展示
		String sql = "SELECT t.* FROM JPO_INVITE_LIST t WHERE 1=1 AND t.status!=2 ";
		
		if(StringUtils.isNotEmpty(userCode)){
			sql += " and t.USER_CODE='"+userCode+"'";
		}
		if(StringUtils.isNotEmpty(inviteCode)){
			sql += " and t.INVITE_CODE='"+inviteCode+"'";
		}
		
		if(StringUtils.isNotEmpty(memberOrderNo)){
			sql += " and t.MEMBER_ORDER_NO='"+memberOrderNo+"'";
		}
		if(StringUtils.isNotEmpty(status)){
			sql += " and t.status='"+status+"'";
		}
		if(StringUtils.isNotEmpty(inviteType)){
			sql += " and t.INVITE_TYPE='"+inviteType+"'";
		}
		if (StringUtils.isNotEmpty(useUserCode)) {
			sql += " and t.USE_USER_CODE='"+useUserCode+"'";
		}
		sql+=" order by t.status desc ";
		//return this.findObjectsBySQL(sql, page);
		if (null!=page) {
			return this.findObjectsBySQL(sql, page);
		}else{
			return this.findObjectsBySQL(sql);
		}
	}
    
    
	//新增支付绑定邀请码
	public JpoInviteList getJpoInviteCode(String inviteCode){
		String hql="from JpoInviteList where inviteCode='"+inviteCode+"'";
		return (JpoInviteList) this.getObjectByHqlQuery(hql);
	}
	public JpoInviteList getJpoInviteCodeByUserCode(String useUserCode){
		String hql="from JpoInviteList where useUserCode='"+useUserCode+"'";
		return (JpoInviteList) this.getObjectByHqlQuery(hql);
	}


}