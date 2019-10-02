package com.joymain.ng.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.joymain.ng.model.Items;
import com.joymain.ng.model.MailStatus;
import com.joymain.ng.model.PdLogisticsBaseNum;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.MailStatusDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("mailStatusDao")
public class MailStatusDaoHibernate extends GenericDaoHibernate<MailStatus, Long> implements MailStatusDao {

    public MailStatusDaoHibernate() {
        super(MailStatus.class);
    }

	/**
	 * @author gw 2014-11-24-----------------------------------------需求变更后，这个方法弃用20150806
	 * 物流跟踪查询-保存最后一次的物流查询信息
	 */
	public boolean saveMailStatus(MailStatus mailStatus) {
		if(!(null==mailStatus)){
		   if(!StringUtil.isEmpty(mailStatus.getMail_no())){
			   this.save(mailStatus);
			   return true;
		   }else{
			   return true;
		   }
		}else{
		   return false;
		}
	}

	/**
	 * 根据物流单号查询物流实时信息----------------------------有用方法20150806
	 * @author gw 2015-06-17
	 * @param mailNo
	 * @param numId
	 * @return
	 */
	public MailStatus getMailStatusByMailNo(String mailNo,PdLogisticsBaseNum pdLogisticsBaseNum) {
		
		/*String hql = " from MailStatus mailStatus where mailStatus.mail_no = '"+mailNo+"'";
		MailStatus  mailStatus = (MailStatus) this.getObjectByHqlQuery(hql);
		if(null!=mailStatus){
			if(!StringUtil.isEmpty(mailStatus.getMail_no())){
				return mailStatus;
			}
		}
		return null;*/
		//
		log.info("在类MailStatusDaoHibernate的方法中getMailStatusByMailNo运行");
		//String sqlMailStatus = "select status_id,mail_no,logist_comp from mail_status where mail_no= '"+mailNo+"'";
		//List list = this.jdbcTemplate3.queryForList(sqlMailStatus);

		//if((null!=list)&&(list.size()>0)){
			    MailStatus  mailStatusNew = new MailStatus();
			   /* Map map = (Map) list.get(0);
			    BigDecimal statusId = (BigDecimal)map.get("status_id");
			    String mail_no = (String)map.get("mail_no"); 
			    String logist_comp = (String)map.get("logist_comp");*/
			    mailStatusNew.setMail_no(pdLogisticsBaseNum.getPdLogisticsBaseNum_no());
			    mailStatusNew.setLogist_comp(pdLogisticsBaseNum.getName());
			    List<Items> items = new ArrayList();
				//String sqlItems = "select items_id,accepttime,acceptaddress,event from items where logistic_id = '"+statusId.toString()+"'";
				String sqlItems = "select items_id,accepttime,acceptaddress,event from items where num_id = '"+pdLogisticsBaseNum.getNumId().toString()+"' order by accepttime desc ";

			    List listItems = this.jdbcTemplate3.queryForList(sqlItems);
				
				if((null!=listItems)&&(listItems.size()>0)){
                       for(int i=0;i<listItems.size();i++){
                    	   Items itemsNew = new Items();
                    	   Map mapItems = (Map) listItems.get(i);
                    	   String acceptTime = (String)mapItems.get("accepttime");
                    	   String acceptAddress  = (String)mapItems.get("acceptaddress");
                    	   String remark = (String)mapItems.get("event");
                    	   itemsNew.setAcceptTime(acceptTime);
                    	   itemsNew.setAcceptAddress(acceptAddress);
                    	   itemsNew.setRemark(remark);
                    	   items.add(itemsNew);
                       }
				}
				mailStatusNew.setItems(items);
				return mailStatusNew;
		/*}else{
			return null;
		}*/
	}
	
}
