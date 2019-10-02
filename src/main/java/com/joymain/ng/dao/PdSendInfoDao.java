package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;


import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.PdPhoneSend;
import com.joymain.ng.model.PdSendInfo;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.data.CommonRecord;

/**
 * An interface that provides a data management interface to the Linkman table.
 */
public interface PdSendInfoDao extends GenericDao<Linkman, Long> {
    
	/**
	 * 会员发货单管理
	 * Add By WuCF 2014-01-14
	 * 分页
	 * @param page：分页信息
	 * @param userCode：会员编号
	 * @param siNo：发货单号
	 * @param orderNo：订单号
	 * @return
	 */
	List getPdSendInfoPage(GroupPage page,String userCode,String siNo, String orderNo,String orderFlag);

	/**
	 * 会员发货单管理
	 * Add By WuCF 2014-04-28
	 * 分页
	 * @param page：分页信息
	 * @param crm：查询的条件集合
	 * @return
	 */
	public List getJpoMemberOrderPage(GroupPage page,CommonRecord crm);
	
	/**
	 * 订单总金额
	 * Add By WuCF 2014-04-28
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
	
	/**
	 * 根据条件统计商品销售量
	 * Add By WuCF 2014-04-28
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm);
	public int getStatisticProductSale2(CommonRecord crm);
	
	/**
	 * 会员收货确认
	 * @param siNo：订单编号
	 * @return
	 */
	public int sendInfoConfirm(String siNo);
	
	/**
	 * 获得指定会员的发货单中：已经发货，但未确认的发货信息，只有在发货单10天后到17天之间如果没有确认的，才会提示！
	 * @param userCode
	 * @return
	 */
	public int isExistNotConfirm(String userCode);

	/**
	 * Add By WuCF 20141219
	 * 通过订单号和商品编号，查询返回商品的物流跟踪单号
	 * @param orderNo：订单号
	 * @param productNo：商品编码---实际上是订单明细表jpo_member_order_list的主键
	 * @return：返回具体商品的物流跟踪信息，可能有多个，则用逗号英文“,”隔开
	 */
	public List<String> getTrackingNoInfo(String orderNo,String productNo);
	
	/**
	 * Add By WuCF 20150907是否拥有股票链接地址
	 * @param userCode
	 * @return
	 */
	public boolean isGuPiaoUser(String userCode);
	
	/**
	 * 得到瓜藤网图片的地址和读取名称
	 * @param listCode
	 * @return
	 */
	public List getGuaTenLinks(String listCode);
	
	/**
	 * @Description 手机端订单统计查询
	 * @author houxyu
	 * @date 2016-4-20
	 * @param crm
	 * @return
	 */
	public List getJpoMemberOrderList(CommonRecord crm);

	/**
	 * 根据发货单号获取发货单
	 * @author fu 2016-04-22
	 * @param logisticsNo
	 * @return
	 */
	public PdSendInfo getPdSendInfoBySiNo(String logisticsNo);

	/**
	 * 根据订单号获取对应的发货信息
	 * @author fu 2016-04-22
	 * @param orderNo
	 * @return 
	 */
	public PdPhoneSend getPdSendInfoList(String orderNo);
	
	/**
	* 手机端接口-根据订单号号获取物流单号和物流公司-根据订单号获取发货单信息
	* @author fu 2016-05-18
	* @param memberOrderNo 订单编号
	* @return list
	*
	*/
	public List getPdSendInfoForMemberOrderNo(String memberOrderNo);
}