package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.AmMessageDao;
import com.joymain.ng.model.AmMessage;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.AmMessageManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("amMessageManager")
@WebService(serviceName = "AmMessageService", endpointInterface = "com.joymain.ng.service.AmMessageManager")
public class AmMessageManagerImpl extends GenericManagerImpl<AmMessage, Long> implements AmMessageManager {
    AmMessageDao amMessageDao;

    @Autowired
    public AmMessageManagerImpl(AmMessageDao amMessageDao) {
        super(amMessageDao);
        this.amMessageDao = amMessageDao;
    }
	
	public Pager<AmMessage> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return amMessageDao.getPager(AmMessage.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List findMessageByUserCode(String userCode,String companyCode,String msgClassNo,String type){
		return amMessageDao.findMessageByUserCode(userCode,companyCode,msgClassNo,type);
	}
	
	@Override
	public List findMessageByUserCodePage(GroupPage page,String userCode,String companyCode,String msgClassNo,String type){
		return amMessageDao.findMessageByUserCodePage(page,userCode,companyCode,msgClassNo,type);
	}

	@Override
	public List<AmMessage> findMessage(String msgClassNo,String status,String userCode) {
		return amMessageDao.findMessage(msgClassNo, status, userCode);
	}

	@Override
	public List<AmMessage> ascfindMessage(String msgClassNo, String status,
			String userCode) {
		return amMessageDao.ascfindMessage(msgClassNo, status, userCode);
	}

	public Integer getNoReadReply(String userCode, String companyCode,String msgClassNo) {
		return amMessageDao.getNoReadReply(userCode, companyCode,msgClassNo);
	}
	
	public AmMessage saveAmMessage(AmMessage amMessage,JsysUser defJsysUser,String strAction){
		
		
		if("editReplyAmMessage".equals(strAction)){
			amMessage.setReceiverNo(defJsysUser.getUserCode());
			amMessage.setReceiverName(defJsysUser.getUserName());
			amMessage.setReplyTime(new Date());
			amMessage.setStatus(4);
		}else{

			amMessage.setAgentNo(defJsysUser.getUserCode());
			amMessage.setAgentName(defJsysUser.getUsername());
			amMessage.setSendRoute("1");
			

			amMessage.setSenderNo(defJsysUser.getUserCode());
			amMessage.setSenderName(defJsysUser.getUserName());
			amMessage.setIssueTime(new Date());
			
			if("addAmMessage".equals(strAction)){
				amMessage.setCompanyCode(defJsysUser.getCompanyCode());
				amMessage.setStatus(0);
			}
		}
		

		
		return this.save(amMessage);
	}
}