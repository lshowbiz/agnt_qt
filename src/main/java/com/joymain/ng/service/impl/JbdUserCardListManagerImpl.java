package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdUserCardListDao;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JbdUserCardList;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JbdUserCardListManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;

@Service("jbdUserCardListManager")
@WebService(serviceName = "JbdUserCardListService", endpointInterface = "com.joymain.ng.service.JbdUserCardListManager")
public class JbdUserCardListManagerImpl extends GenericManagerImpl<JbdUserCardList, Long> implements JbdUserCardListManager {
	
    JbdUserCardListDao jbdUserCardListDao;

    @Autowired
    public JbdUserCardListManagerImpl(JbdUserCardListDao jbdUserCardListDao) {
        super(jbdUserCardListDao);
        this.jbdUserCardListDao = jbdUserCardListDao;
    }
    @Autowired
	private JbdPeriodManager jbdPeriodManager;
    
	public Pager<JbdUserCardList> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdUserCardListDao.getPager(JbdUserCardList.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	
	

	public void saveJbdUserCardList(String userCode,Date operatDate,String newCard,String updateType,String operaterType) {
		
		JbdPeriod bdPeriod = jbdPeriodManager.getBdPeriodByTime(operatDate);		
		
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());


		this.saveJbdUserCardList(userCode, StringUtil.formatInt(bdWeek), newCard, updateType,operaterType);

	}

	public boolean getPreviousJbdUserCardList(Date tDate, String userCode) {
		JbdPeriod sbdPeriod = jbdPeriodManager.getBdPeriodByTime(tDate);
		JbdUserCardList previousJbdUserCardList=jbdUserCardListDao.getPreviousJbdUserCardList(userCode, sbdPeriod.getWweek());
		if(previousJbdUserCardList==null){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * operaterType 1 审核订单 2.更改审核日期
	 */
	public void saveJbdUserCardList(String userCode, Integer wweek, String newCard,String updateType,String operaterType) {

		JbdUserCardList jbdUserCardList=jbdUserCardListDao.getJbdUserCardListByUserCodeAndWeek(userCode, wweek);

		JbdUserCardList previousJbdUserCardList=jbdUserCardListDao.getPreviousJbdUserCardList(userCode,  wweek);
		
		//当前期别前没有记录
		if(previousJbdUserCardList==null){
			if(jbdUserCardList==null){//当前期没数据
				jbdUserCardList=new JbdUserCardList();
				jbdUserCardList.setUserCode(userCode);
				jbdUserCardList.setOldCard("0");
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setWweek(wweek);
				jbdUserCardList.setUpdateType(updateType);
			}else{//当前期有数据
				jbdUserCardList.setOldCard("0");
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setUpdateType(updateType);
			}
		}else{
			if(jbdUserCardList==null){
				jbdUserCardList=new JbdUserCardList();
				jbdUserCardList.setUserCode(userCode);
				
				jbdUserCardList.setOldCard(previousJbdUserCardList.getNewCard());
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setWweek(wweek);
				jbdUserCardList.setUpdateType(updateType);
			}else{
				jbdUserCardList.setOldCard(previousJbdUserCardList.getNewCard());
				jbdUserCardList.setNewCard(newCard);
				jbdUserCardList.setUpdateType(updateType);
			}
			if("1".equals(updateType)){
				previousJbdUserCardList.setNewCard("0");
				jbdUserCardList.setOldCard("0");
				jbdUserCardListDao.save(previousJbdUserCardList);
			}else if("2".equals(updateType)){
				if("2".equals(previousJbdUserCardList.getUpdateType())&&"2".equals(operaterType)){
					previousJbdUserCardList.setNewCard(previousJbdUserCardList.getOldCard());
					jbdUserCardList.setOldCard(previousJbdUserCardList.getOldCard());
					jbdUserCardListDao.save(previousJbdUserCardList);
				}
			}
		}
		//手动变级时，更新修改期别后面的期别
		
		//自然升级时，如果更新的新卡别大于升级卡别时，不动那一期的新卡别
		
		jbdUserCardListDao.save(jbdUserCardList);

		
		String oldlCardType="";//每一期的
		//String newCardType="";
		List<JbdUserCardList> nextJbdUserCardList=jbdUserCardListDao.getNextJbdUserCardList(userCode,  wweek);
		
		for (int i = 0; i < nextJbdUserCardList.size(); i++) {
			JbdUserCardList curJbdUserCardList=nextJbdUserCardList.get(i);
			
			
			if("3".equals(updateType)){
				curJbdUserCardList.setOldCard(newCard);
				curJbdUserCardList.setNewCard(newCard);
			}else{
				if(i==0){
					oldlCardType=newCard;
				}
				curJbdUserCardList.setOldCard(oldlCardType);
				
				
				int curNewCard=Integer.valueOf(curJbdUserCardList.getNewCard());
				int inNewCard=Integer.valueOf(newCard);
				//如果卡别为5，则大于0时，修改，否则不修改
				
				if(curNewCard==0&&inNewCard==5){
					curJbdUserCardList.setNewCard(newCard);
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard!=0&&inNewCard==5){
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard==0&&inNewCard!=5){
					curJbdUserCardList.setNewCard(newCard);
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard==5&&inNewCard==0){
					oldlCardType=curJbdUserCardList.getNewCard();
				}else if(curNewCard==5&&inNewCard!=0){
					curJbdUserCardList.setNewCard(newCard);
					oldlCardType=curJbdUserCardList.getNewCard();
				}else{
					if(Integer.valueOf(curJbdUserCardList.getNewCard())<Integer.valueOf(newCard)){
						curJbdUserCardList.setNewCard(newCard);
					}
					oldlCardType=curJbdUserCardList.getNewCard();
				}
				
				
				
				
			}
			
			jbdUserCardListDao.save(curJbdUserCardList);

		}
		
	}
	
	
}