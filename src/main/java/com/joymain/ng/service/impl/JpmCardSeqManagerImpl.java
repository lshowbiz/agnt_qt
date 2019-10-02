package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmCardSeqDao;
import com.joymain.ng.model.JpmCardSeq;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.service.JpmCardSeqManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;

@Service("jpmCardSeqManager")
@WebService(serviceName = "JpmCardSeqService", endpointInterface = "com.joymain.ng.service.JpmCardSeqManager")

public class JpmCardSeqManagerImpl extends GenericManagerImpl<JpmCardSeq, Long> implements JpmCardSeqManager {

    JpmCardSeqDao jpmCardSeqDao;

    @Autowired
    public JpmCardSeqManagerImpl(JpmCardSeqDao jpmCardSeqDao) {
        super(jpmCardSeqDao);
        this.jpmCardSeqDao = jpmCardSeqDao;
    }

    @Autowired
    private JsysIdManager jsysIdManager;
    
	public Pager<JpmCardSeq> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpmCardSeqDao.getPager(JpmCardSeq.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	

	public void saveUserJpmCardSeq(JpoMemberOrder jpoMemberOrder,String oldCard,String newCard) {
		Set<JpoMemberOrderList> jpoMemberOrderList=jpoMemberOrder.getJpoMemberOrderList();
		
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2013, 0, 19, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		

		java.util.Calendar endc=java.util.Calendar.getInstance();
		endc.set(2013, 1, 9, 00, 00, 00);
		java.util.Date endDate=endc.getTime();
		
		Date curDate=new Date();
		
		
		
		if("CN".equals(jpoMemberOrder.getCompanyCode())){
			if(curDate.after(startDate)&&curDate.before(endDate)){
				if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())){
					if(jpoMemberOrder.getDiscountAmount()!=null){
						int qty=jpoMemberOrder.getDiscountAmount().intValue()/1000;
						if(qty>0){
							for (int i = 1; i <= qty; i++) {
								JpmCardSeq seq=new JpmCardSeq();
								String cardseq_order=jsysIdManager.buildIdStr("cardseq_order");
								seq.setCardNo(cardseq_order);
								seq.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
								seq.setPassword("00000000");
								seq.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
								seq.setCreateTime(curDate);
								seq.setState("1");
								this.save(seq);
							}
						}
					}
				}
			}
			
			
		}

	}
}