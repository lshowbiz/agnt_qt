package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiMovieOrderDao;
import com.joymain.ng.model.FiMovieOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.FiMovieOrderManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

@Service("fiMovieOrderManager")
@WebService(serviceName = "FiMovieOrderService", endpointInterface = "com.joymain.ng.service.FiMovieOrderManager")
public class FiMovieOrderManagerImpl extends GenericManagerImpl<FiMovieOrder, String> implements FiMovieOrderManager {
	
    FiMovieOrderDao fiMovieOrderDao;
    
    @Autowired
	private FiBankbookJournalManager fiBankbookJournalManager;

    @Autowired
    public FiMovieOrderManagerImpl(FiMovieOrderDao fiMovieOrderDao) {
        super(fiMovieOrderDao);
        this.fiMovieOrderDao = fiMovieOrderDao;
    }
	
	public Pager<FiMovieOrder> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiMovieOrderDao.getPager(FiMovieOrder.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 发展基金支付微信电影票订单
	 * @param user
	 * @param orderId
	 * @param amount
	 * @throws Exception
	 */
	@Override
	public void payMovieOrder(JsysUser user, String orderId, BigDecimal amount) throws Exception {
		
		//生成订单
		FiMovieOrder fiMovieOrder = new FiMovieOrder();
		fiMovieOrder.setOrderId(orderId);
		fiMovieOrder.setUserCode(user.getUserCode());
		fiMovieOrder.setAmount(amount);
		fiMovieOrder.setStatus("0");//未出票
		fiMovieOrder.setCreateTime(new Date());
		
		//支付
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = amount;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 101;//发展基金支付微信电影票
		
		String[] notes = new String[1];
		notes[0] = "微信电影票支付";
		
		fiBankbookJournalManager.saveFiBankbookDeduct("CN", user, moneyType, moneyArray, user.getUserCode(), user.getUserName(), orderId, notes, "1", "2");
		
		fiMovieOrderDao.save(fiMovieOrder);
	}
}