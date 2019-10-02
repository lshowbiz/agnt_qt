package com.joymain.ng.service;

import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.data.CommonRecord;

@WebService
public interface PdExchangeOrderManager extends GenericManager<PdExchangeOrder, String> {
    
    public List<PdExchangeOrder> getPdExchangeOrders();

    public PdExchangeOrder getPdExchangeOrder(final String eoNo);

    public void savePdExchangeOrder(PdExchangeOrder pdExchangeOrder);

    public void removePdExchangeOrder(final String eoNo);
    //added for getPdExchangeOrdersByCrm
    public List getPdExchangeOrdersByCrm(CommonRecord crm,  GroupPage page);

	public boolean hasProductCountByAsNo(String asNo);
	
	public void confirmPdExchangeOrder(PdExchangeOrder pdExchangeOrder);

	public List getSumGroupByAst(CommonRecord crm);

	/**
     * 自助换货单的支付
     * @author fu 2016-04-25
     * @param eoNo 换货单号
     * @return String
     */
    public String payPdExchangeOrder(String eoNo);

    //自助换货单编辑的时候，在后台用SQL查询换货单，如果ORDER_FLAG为-1,0那么才允许编辑，否则不允许编辑
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