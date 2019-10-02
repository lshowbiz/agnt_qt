package com.joymain.ng.service.impl;

import com.joymain.ng.dao.InwIntegrationTotalDao;
import com.joymain.ng.model.InwIntegrationTotal;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.MiCoinLog;
import com.joymain.ng.service.InwIntegrationTotalManager;
import com.joymain.ng.service.MiCoinLogManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.jws.WebService;

@Service("inwIntegrationTotalManager")
@WebService(serviceName = "InwIntegrationTotalService", endpointInterface = "com.joymain.ng.service.InwIntegrationTotalManager")
public class InwIntegrationTotalManagerImpl extends GenericManagerImpl<InwIntegrationTotal, Long> implements InwIntegrationTotalManager {
    InwIntegrationTotalDao inwIntegrationTotalDao;

    @Autowired
    public InwIntegrationTotalManagerImpl(InwIntegrationTotalDao inwIntegrationTotalDao) {
        super(inwIntegrationTotalDao);
        this.inwIntegrationTotalDao = inwIntegrationTotalDao;
    }
    @Autowired
	private MiCoinLogManager miCoinLogManager;
	public Pager<InwIntegrationTotal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return inwIntegrationTotalDao.getPager(InwIntegrationTotal.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
     * 用户减掉积分的操作
     * @author yxzz  2014-06-09
     * @param userCode 会员编号
     * @param integratotal 积分
     * @param uniqueCode 唯一码
     * @return boolean
     */
	public boolean minusIntegrationTotal(String userCode, String integratotal,String uniqueCode) {
		return inwIntegrationTotalDao.minusIntegrationTotal(userCode,integratotal,uniqueCode);
	}

	@Override
	public InwIntegrationTotal getInwIntegrationTotal(String userCode) {
		return inwIntegrationTotalDao.getInwIntegrationTotal(userCode);
	}
	
	public MiCoinLog sendCoin(BigDecimal bcoinNum,JsysUser jsysUser ,String mallType,String sendNo){

		
		
		
		//2.推送（小叶需要添加方法）

		MiCoinLog miCoinLog = new MiCoinLog();
		miCoinLog.setCoin(bcoinNum);
		miCoinLog.setCoinType("C"); 
		miCoinLog.setCreateNo(jsysUser.getUserCode());
		miCoinLog.setCreateTime(new Date());
		miCoinLog.setDealType(mallType);
		miCoinLog.setUserCode(jsysUser.getUserCode());
		miCoinLog.setStatus("1");
		miCoinLog.setSendNo(sendNo);
		miCoinLogManager.saveMiCoinLog(miCoinLog);
		
		
		if(!minusIntegrationTotal(jsysUser.getUserCode(), bcoinNum.toString(), miCoinLog.getId().toString())){
			throw new AppException("扣除失败");
		}
		
		
		
		
		return miCoinLog;
	}
	
}