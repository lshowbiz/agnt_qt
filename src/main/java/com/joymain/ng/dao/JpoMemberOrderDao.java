package com.joymain.ng.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.util.data.CommonRecord;
import com.meterware.pseudoserver.HttpRequest;

/**
 * An interface that provides a data management interface to the JpoMemberOrder table.
 */
public interface JpoMemberOrderDao extends GenericDao<JpoMemberOrder, Long> {
	/**
	 * 根据会员编号回去身份证
	 * @param userCode
	 * @return
	 */
	public String getPaperNumber(String userCode);
	/**
	 * 根据身份证查询所有会员
	 * @param paperNumber
	 * @return
	 */
	public List<JmiMember> getJmimemberList(String paperNumber);
	public List getJpoMemberMark(String papernumber,String productNo,String orderType);
	public List<JpoMemberOrder> getJpoMemberOrder(List<String> memberOrderL);
	public List<JpoMemberOrder> getOrderByParam(Map<String,String> map);
	
	/**
	 * 查询指定会员订购的家套餐的数据的订单 Add By WuCF 20150410
	 */
	public List<JpoMemberOrder> getOrderByParamStj(Map<String,String> map);
	
	/**
     * 根据条件查询会员新增成功的订单：分页
     * @param map：条件
     * @param pageNum：当前页码
     * @param pageSize：分页单位
     * @return
     */
    public List<JpoMemberOrder> getOrderByParamPage(Map<String,String>  map,int pageNum,int pageSize);
	
	public String getMemberFirstOrderStatusTime(String memberNo);
	/**
     * 查询首购单的审核时间
     * 
     * @param memberNo
     * @return
     */
	public String getMemberCheckDate(String memberNo);
	public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder);
	public List getMemberOrderNopay(String memberNo);
	
	/**
	 * Add By WuCF 20131209 
	 * 查询指定行数的数据
	 * @param memberNo
	 * @return
	 */
	public List getMemberOrderNopay(String memberNo,Integer startIndex,Integer endIndex);
	
    /**
     * 获取订单数量
     * @param orderType
     * @param userCode
     * @param status
     * @return
     */
    public List getJpoMemberOrdersByTCS(String orderType, String userCode, String status);
    
    /**
	 * 时间段内获取商品订购量
	 * @param productNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getProductsSum(String productNo,String startTime,String endTime,String payBy);
	/**
	 * 按条件获取订单总金额
	 * 
	 * @param crm
	 * @return
	 */
	public BigDecimal getJpoMemberOrderStatics(CommonRecord crm);
	
	//根据订单号获取订单对象
	public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo);
	
	/**
	 * 根据订单号修改订单配置状态
	 * @param map
	 */
	public void modifyOrderStatusByMoId(Map<String,String> map);
	/**
	 * find order by userCode and proNo
	 * @param userCode
	 * @param proNo
	 * @return list
	 */
	public List<JpoMemberOrder> findOrderByUserCode(String userCode,String proNo);
	
	/**
	 * 提供手机统计订单
	 * @param request   扩展查询条件
	 * @param startCreateTime   开始时间
	 * @param endCreateTime		结束时间	
	 * @param pageNum			页面
	 * @param pageSize			每页显示数量
	 * @return
	 */
	public List<JpoMemberOrder> getJpoMemberOrderByMobile(HttpServletRequest request,String startCreateTime,String endCreateTime,int pageNum,int pageSize);

	/**
	 * 得到指定的会员
	 * @param userCode
	 * @return
	 */
	public List<Map<String, Object>> getTatalPrice(String userCode);
	/**
	 * @Description:得到指定的会员的购物车总金额
	 * @author:			侯忻宇
	 * @date:		    2016-11-28
	 * @param userCode
	 * @param isCheck
	 * @return:
	 * @exception:
	 * @return:
	 */
	public List<Map<String, Object>> getTatalPrice(String userCode,String isCheck);
	
	/**
     * 生态家套餐订单级联删除
     * @param order
     * @return
     */
    public boolean deleteOrderByMoids(String moids);
    public List<JpoMemberOrder> getOrderByType(String type,String userCode);
    public Integer callSTJFunction(String orderNo,Integer type);

    /**
     * 更新订单经销商字段
     * @param orderId
     * @param saleNo
     */
    public void updateJpoMemberOrderSaleNo(String orderId, String saleNo);
    
    //Add By WuCF 20160805
	public void updateJpoMemberOrderPaymentType(String moid,String paymentType);
	
	/**
	 * 删除订单，需要备份到临时表中
	 */
	public void removeJpoMemberOrder(String moId);
  }