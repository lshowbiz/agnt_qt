package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JpmProduct;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.data.CommonRecord;

/**
 * An interface that provides a data management interface to the PdExchangeOrder table.
 */
public interface PdExchangeOrderDao extends GenericDao<PdExchangeOrder, String> {
	
    public List<PdExchangeOrder> getPdExchangeOrders();

    public PdExchangeOrder getPdExchangeOrder(final String eoNo);

    public void savePdExchangeOrder(PdExchangeOrder pdExchangeOrder);

    public void removePdExchangeOrder(final String eoNo);
    
    //added for getPdExchangeOrdersByCrm
    public List getPdExchangeOrdersByCrm(CommonRecord crm, GroupPage page);

	public List getSumGroupByAst(CommonRecord crm);

	/**
     * 自助换货单的支付状态修改
     * @author fu 2016-04-26
     * @param eoNo 换货单号
     * @return boolean true自助换货单的修改支付状态成功；false自助换货单的修改支付状态失败
     */
	public boolean reSetIsPay(String eoNo);

	public String getOrderFlagByEoNo(String eoNo);

	/**
	 * 根据会员编号获取会员的已审核、正常状况、需要支付且未支付的自助换货单
	 * @author fu 2016-04-25
	 * @param userCode 会员编号
	 * @param startIndex 开始查询行数
	 * @param endIndex 结束查询行数
	 * @return list
	 */
	public List getPdExchangeOrderByUsercode(String userCode, Integer startIndex, Integer endIndex);
	public List getPdExchangeOrdersByCrmSql(CommonRecord crm, GroupPage page);
		
    
}