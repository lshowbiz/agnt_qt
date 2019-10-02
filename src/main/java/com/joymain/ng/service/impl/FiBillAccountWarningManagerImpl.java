package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiBillAccountWarningDao;
import com.joymain.ng.model.FiBillAccountWarning;
import com.joymain.ng.service.FiBillAccountWarningManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("fiBillAccountWarningManager")
@WebService(serviceName = "FiBillAccountWarningService", endpointInterface = "com.joymain.ng.service.FiBillAccountWarningManager")
public class FiBillAccountWarningManagerImpl extends GenericManagerImpl<FiBillAccountWarning, String> implements FiBillAccountWarningManager {
    FiBillAccountWarningDao fiBillAccountWarningDao;

    @Autowired
    public FiBillAccountWarningManagerImpl(FiBillAccountWarningDao fiBillAccountWarningDao) {
        super(fiBillAccountWarningDao);
        this.fiBillAccountWarningDao = fiBillAccountWarningDao;
    }
	
	public Pager<FiBillAccountWarning> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBillAccountWarningDao.getPager(FiBillAccountWarning.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	/**
	 * 进账记录叠加
	 * @param amout
	 * @param billAccountCode
	 */
	@Override
	public void addTotalAmount(BigDecimal amout, String billAccountCode) {

		FiBillAccountWarning mfiBillAccountWarning = this.get(billAccountCode);
		
		if(mfiBillAccountWarning!=null){
			
			//累加
			BigDecimal tempAmount = mfiBillAccountWarning.getNowTotalAmount().add(amout);
			mfiBillAccountWarning.setNowTotalAmount(tempAmount);
			
			this.save(mfiBillAccountWarning);
		}
	}
}