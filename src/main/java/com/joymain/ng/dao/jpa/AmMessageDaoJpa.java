package com.joymain.ng.dao.jpa;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.AmMessageDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.AmMessage;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("amMessageDao")
public class AmMessageDaoJpa extends GenericDaoHibernate<AmMessage, Long> implements AmMessageDao {

    public AmMessageDaoJpa() {
        super(AmMessage.class);
    }

	@Override
	public List findMessageByUserCode(String userCode,String companyCode,String msgClassNo,String type) {
		String sql = "select * from am_message a where a.agent_no='"+userCode+"' and ((a.send_route='0' and a.status>2) or a.send_route='1') and a.company_code='"+companyCode+"' " ;
				if(!StringUtil.isEmpty(msgClassNo)){
					sql+=" and a.MSG_CLASS_NO='"+msgClassNo+"'";
				} 
				if("m".equals(type)){
					sql+=" order by a.issue_time asc";
				}else{
					sql+=" order by a.issue_time desc";
				}
				
		return this.jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public List findMessageByUserCodePage(GroupPage page,String userCode,String companyCode,String msgClassNo,String type) {
		String sql = "select * from am_message a where a.agent_no='"+userCode+"' and ((a.send_route='0' and a.status>2) or a.send_route='1') and a.company_code='"+companyCode+"' " ;
				if(!StringUtil.isEmpty(msgClassNo)){
					sql+=" and a.MSG_CLASS_NO='"+msgClassNo+"'";
				}
				if("m".equals(type)){
					sql+=" order by a.issue_time asc";
				}else{
					sql+=" order by a.issue_time desc";
				}
		return findObjectsBySQL(sql, page);
//		return this.jdbcTemplate.queryForList(sql);
	}
	
/*	public List getMessageByType(String userCode,String companyCode){
		String sql="select a.msg_class_no,count(a.msg_class_no) as  from am_message a where a.agent_no='"+userCode+"' and ((a.send_route='0' and a.status>2) or a.send_route='1') and a.company_code='"+companyCode+"'  group by a.msg_class_no order by a.msg_class_no";
		List list=this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			Integer num=this.getNoReadReply(userCode, companyCode, msgClassNo);
		}
		return 
	}*/

	public Integer getNoReadReply(String userCode,String companyCode,String msgClassNo){

		
		/*String hql = "select count(*) from Am_Message amMessage where  amMessage.status =3  " +
			" and ((amMessage.send_Route='0' and amMessage.status>2) or (amMessage.send_Route='1') )";
		
    		hql += " and amMessage.company_Code='"+companyCode+"'";
    
		
    		hql += " and amMessage.agent_No='"+userCode+"'";
    		if(!StringUtil.isEmpty(msgClassNo)){
    			hql += " and amMessage.MSG_CLASS_NO='"+msgClassNo+"'";
			}
    		
		
		return this.jdbcTemplate.queryForInt(hql);*/
		//Modify By WuCF 20140703
		//预编译参数
		StringBuffer paramsBuf = new StringBuffer("");
		
		//查询语句
		StringBuffer hql = new StringBuffer("select count(*) from Am_Message amMessage where  amMessage.status =3 ");
		hql.append(" and ((amMessage.send_Route='0' and amMessage.status>2) or (amMessage.send_Route='1') ) ");
		
		hql.append(" and amMessage.company_Code=?" );
		paramsBuf.append(","+companyCode);
		
		hql.append(" and amMessage.agent_No=? ");
		paramsBuf.append(","+userCode);
		
		if(!StringUtil.isEmpty(msgClassNo)){
			hql.append(" and amMessage.MSG_CLASS_NO=? ");
			paramsBuf.append(","+msgClassNo);
		}
		//返回时调用分页的查询的方法 
	    Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return jdbcTemplate3.queryForInt(hql.toString(),parameters); 
	}
	
	
	@Override
	public List<AmMessage> findMessage(String msgClassNo,String status,String userCode) {
		String sql = "select * from am_message t where sender_no='"+userCode+"' ";
		if(StringUtils.isNotBlank(msgClassNo)){
			sql +=" and t.MSG_CLASS_NO='"+msgClassNo+"' ";
		}
		if(StringUtils.isNotBlank(status)){
			sql += " and t.status="+status ;
		}
		return this.jdbcTemplate.query(sql, new AmMessage());
	}
	
	@Override
	public List<AmMessage> ascfindMessage(String msgClassNo,String status,String userCode) {
		String sql = "select * from am_message t where sender_no='"+userCode+"' ";
		if(StringUtils.isNotBlank(msgClassNo)){
			sql +=" and t.MSG_CLASS_NO='"+msgClassNo+"' ";
		}
		if(StringUtils.isNotBlank(status)){
			sql += " and t.status="+status ;
		}
		sql+=" order by issue_time asc ";
		
		return this.jdbcTemplate.query(sql, new AmMessage());
	}
}
