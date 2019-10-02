package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@WebService
@SuppressWarnings("rawtypes")
public interface JpoMemberOrderManager extends GenericManager<JpoMemberOrder, Long>
{
    
    public Pager<JpoMemberOrder> getPager(Collection<SearchBean> searchBeans,
        Collection<OrderBy> OrderBys, int currentPage, int pageSize);
    
    // 创建新订单
	public List<String> editJpoMemberOrderBatch(List<JpoMemberOrder> jpoMemberOrders,
        List<Set> jpoMemberOrderSets, List<Set> jpoMemberOrderListSets,
        List<JpoShoppingCartOrder> scList);
    
    public String editJpoMemberOrder(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet,
        Set jpoMemberOrderListSet, JpoShoppingCartOrder sco);
    
    public List<JpoMemberOrder> getJpoMemberOrder(List<String> memberOrder);
    
    // 根据条件查询会员新增成功的订单
    public List<JpoMemberOrder> getOrderByParam(Map<String, String> map);
    
    //查询指定会员订购的家套餐的数据的订单 Add By WuCF 20150410
    public List<JpoMemberOrder> getOrderByParamStj(Map<String, String> map);
    
    /**
     * 根据条件查询会员新增成功的订单：分页
     * 
     * @param map：条件
     * @param pageNum：当前页码
     * @param pageSize：分页单位
     * @return
     */
    public List<JpoMemberOrder> getOrderByParamPage(Map<String, String> map, int pageNum,
        int pageSize);
    
    public int getPreferentialOrder(JpoMemberOrder jpoMemberOrder);
    
    /**
     *  电子存折支付   业务判断 + 扣款
     * 
     * @param jpoMemberOrder
     * @param operatorSysUser
     * @param dataType 数据来源，2：手机，null或者1：PC
     */
    public void checkJpoMemberOrder(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType);
    
    /**
     * 会员编号查找
     * 
     * @param jpoMemberOrder
     * @return
     */
    public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder);
    
    /**
     * 查询首购单的审核时间
     * 
     * @param memberNo
     * @return
     */
    public String getMemberFirstOrderStatusTime(String memberNo);
    
    /**
     * 查询首购单的审核时间
     * 
     * @param memberNo
     * @return
     */
    public String getMemberCheckDate(String memberNo);
    
    /**
     * 未支付订单
     * 
     * @param memberNo
     * @return
     */
    public List getMemberOrderNopay(String memberNo);
    
    /**
     * Add By WuCF 20131209 查询指定行数的数据
     * 
     * @param memberNo
     * @return
     */
    public List getMemberOrderNopay(String memberNo, Integer startIndex, Integer endIndex);
    
    /**
     * 时间段内获取商品订购量
     * 
     * @param productNo
     * @param startTime
     * @param endTime
     * @return
     */
    public int getProductsSum(String productNo, String startTime, String endTime, String payBy);
    
    /**
     * 抢购时间：2010年5月11日-5月16日 剩下多少个
     * 
     * @param orderProductMax
     * @return
     */
    public int getProductsLeave(String productNo);
    
    /**
     * 代金券支付
     * @param jpoMemberOrder
     * @param operatorSysUser
     * @param dataType
     * @throws Exception
     */
    public void checkJpoMemberOrderByCpValue(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws Exception;
    
    /**
     * 抢购时间：2010年5月11日-5月16日
     * 
     * @param productNo
     * @return
     */
    public boolean getIsOver(String productNo);
    
    /**
     * 抢购时间：2012年4月21日-5月4日
     * 
     * @param productNo
     * @return
     */
    public int getIsOver2(String productNo);
    
    /**
     * 积分换购抢购
     * 
     * @param productNo
     * @return
     */
    public int getIsOver3(String productNo,Integer num);
    
    /**
     * 发展基金抵扣审核会员订单
     * 
     * @param jpoMemberOrder
     * @param operatorSysUser
     */
    public void checkJpoMemberOrderByJJ(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws Exception;
    
    /**
     * 产品积分支付
     * @param jpoMemberOrder
     * @param operatorSysUser
     * @param dataType
     * @throws Exception
     */
    public void checkJpoMemberOrderByProductPoint(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws Exception;
    
    /**
     * 积分抵扣审核会员订单
     * 
     * @param jpoMemberOrder
     * @param operatorSysUser
     */
    public void checkJpoMemberOrderByCoinAndBankbook(JpoMemberOrder jpoMemberOrder,JsysUser operatorSysUser,String dataType);
    public void checkJpoMemberOrderByCoinAndBankbook(JpoMemberOrder jpoMemberOrder,JsysUser operatorSysUser,String dataType, BigDecimal sumCoin);
    /**
     * 积分抵扣审核会员订单
     * 
     * @param jpoMemberOrder
     * @param operatorSysUser
     */
    public void checkJpoMemberOrderByCoin(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser,String dataType)
        throws Exception;
    
    // 判断会员是否已经购买过事业锦囊
    public boolean getJpoMemberMark(String papernumber, String productNo, String orderType);
    
    // 根据订单号获取订单对象
    public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo);
    
    public void modifyOrderStatusByMoId(Map<String, String> map);
    public JpoMemberOrder mergeOrder(JpoMemberOrder order);
    /**
     * 生态家套餐审单
     * @param orderList
     * @param operatorSysUser
     * @param dataType
     * @throws Exception
     */
    public void checkJpoBigOrderByJJ(List<JpoMemberOrder> orderList, JsysUser operatorSysUser, String dataType) throws Exception;
    
    /**
     * 生态家套餐订单级联删除
     * @param order
     * @return
     */
    public boolean deleteOrderByMoids(String moids);
    public JpoMemberOrder getOrderByType(String orderType,String userCode);
    
    public boolean isBindProduct(String userTemCode);
    public void mergeOrder(List<JpoMemberOrder> orders);
    
    /**
     * 电子存折支付   业务判断 + 扣款
     * @param jpoMemberOrder
     * @param operatorSysUser
     * @param dataType
     */
    public void checkJpoMemberOrderByJfiBankbook(JpoMemberOrder jpoMemberOrder,JsysUser operatorSysUser,String dataType) throws AppException;
    
    /**
     * 电子存折支付扣款并且发MQ消息
     * @param jpoMemberOrder
     * @param operatorSysUser
     * @param dataType
     */
    public void checkJpoMemberOrderByJfiBankbookSendMQ(JpoMemberOrder jpoMemberOrder,JsysUser operatorSysUser,String dataType);
    
    /**
     * 更新订单经销商字段
     * @param orderId
     * @param saleNo
     */
    public void updateJpoMemberOrderSaleNo(String orderId, String saleNo);
    
    /**
	 * 支付成功的订单(ispay=1)发送mq消息 2015-9-21 w
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 */
	public void jpoMemberOrderPayedSendToMQ(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType);
	
	/**
	 * 扣款业务判断 2015-9-21 w
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 * @throws AppException
	 */
	public boolean checkJpoMemberOrderCanPay(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType);
	
	/**
	 * 审单保存
	 * @param jpoMemberOrder
	 * @param jmiMember
	 */
	public void saveOrderAndMember(JpoMemberOrder jpoMemberOrder,JmiMember jmiMember);
	public JpoMemberOrder newOrder(JpoMemberOrder order);
	
	//Add By WuCF 20160805
	public void updateJpoMemberOrderPaymentType(String moid,String paymentType);
	
	/**
	 * 删除订单，需要备份到临时表中
	 */
	public void removeJpoMemberOrder(String moId);
	
	//Modify By WUCF 20170417 升级单期限限制90天
	public boolean upGradeInTime(String userCodeStr,String orderId);

	//42顾问首单判断
    public boolean checkCartProduct(String myUserCode);
}