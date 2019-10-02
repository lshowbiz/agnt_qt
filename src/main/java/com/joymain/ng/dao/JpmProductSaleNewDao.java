package com.joymain.ng.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JpmProductSaleImage;
import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.JpmProductSaleRelated;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpmSmssendInfo;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.data.CommonRecord;

/**
 * An interface that provides a data management interface to the JpmProductSaleNew table.
 */
public interface JpmProductSaleNewDao extends GenericDao<JpmProductSaleNew, Long> {
	final String ZTWL_URL = "http://www.ztky.com";//中铁物流
	final String GT_URL = "http://www.gto365.com";//国通
	final String BZ_URL = "";//倍智
	final String TXJ_URL = "";//统新捷
	final String YD_URL = "http://www.yundaex.com";//韵达
	final String ZJS_URL = "http://www.zjs.com.cn";//宅急送
	final String KJ_URL = "http://www.itl.cn";//科捷
	final String ZTO_URL = "http://www.zto.cn";//中通
	final String EMS_URL = "http://www.ems.com.cn";//EMS
	final String DTW_URL = "http://www.dtw.com.cn/waybill.jsp";//大田物流
	final String SHUNFENG_URL = "http://www.sf-express.com/cn/sc";//顺丰
	final String SHENTONG_URL = "http://www.sto.cn";//申通
	final String HUITONG_URL = "http://www.fxnkd.com.cn";//汇通快递
	final String DEBANG_URL = "http://www.deppon.com/zhuizong/";//德邦物流
	final String YUANTONG_URL = "http://www.yto.net.cn/cn/index/index.html";//2014-03-31 圆通快递
	final String BAISHI_URL = "http://www.800best.com";//百世物流
	final String BSHT_URL = "http://www.htky365.com";//百世汇通
	final String XINYITAI_URL = "http://sou.chinawutong.com/";//新易泰物流
	final String SUER_URL = "http://www.sure56.com";//速尔快递
	final String TLU_URL = "http://www.fyps.cn";//速尔快递
	final String STARS_URL = "http://www.ckd.cc/xcjb/";//星晨急便
	/*************************************1.商品信息管理**************************************/
	/**
	 * 获得相关商品数据集合
	 * @param uniNo：商品销售新表主键
	 * @status：状态类型
	 * @return
	 */
	public List<JpmProductSaleRelated> getJpmProductSaleRelatedList(String uniNo,String status);
	
	
	/**
	 * 获得相关商品对应的JpmProductSaleTeamType对象的集合
	 * @param uniNo：商品销售新表主键
	 * @param teamCode：团队编码
	 * @param orderType：订单类型
	 * @return:uniNo对应的商品JpmProductSaleTeamType对象的List集合
	 */
	public List<JpmProductSaleTeamType> getRelatedTeamTypeList(String uniNo,String teamCode,String orderType);
	
	/**
	 * 获得商品的图片集合
	 * @param uniNo：商品销售新表主键
	 * @imageType：图片类型
	 * @return 返回指定图片类型的图片对象JpmProductSaleImage的List集合    
	 * 注释：如果imageType为空，则返回指定商品uniNo所有的图片的集合
	 */
	public List<JpmProductSaleImage> getJpmProductSaleImageList(String uniNo,String imageType);
	
	/**
	 * 获得商品的团队类型集合
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	 * 注释：参数查询条件teamCode,orderType,companyCode如果为空，则传递null
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String uniNo,String teamCode,String orderType,String companyCode);


	/**
	 * 获得商品团队类型数据集合
	 * @param productNo：商品编码
	 * @param productName：商品名称
	 * @param productCategory：商品类别
	 * @param teamCode：团队编码
	 * @param orderType：订单类型
	 * @param companyCode：国别
	 * @return：获得商品的团队类型集合
	 * 注释：商品编码、名称、类别、国别都是按等于或like；团队编码、订单类型可能有多个
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String productNo,String productName,String productCategory,String teamCode,String orderType,String companyCode);

	
	/**
	 * 获得商品的团队类型集合
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productStr：商品编号字符串，用逗号隔开
	 * @type：类型判断    如果为orderType=0：不处理      如果orderType=1，则展示1和5
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	 * 注释：参数查询条件teamCode,orderType,companyCode如果为空，则传递null
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeStrList(String uniNo,String teamCode,String orderType,String companyCode,String productStr,String type);

	
	/**
	 * 获得商品的团队类型Map
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productCategoryStr：商品系列
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	  * 注释：
	 *     返回的HashMap<Key,Value>    key:商品类别   value:List<JpmProductSaleTeamType>
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMap(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr);

	
	/**
	 * 获得商品的团队类型Map
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productCategoryStr：商品系列
	 * @productStr：商品编号字符串，用逗号隔开
	 * @type：类型判断    如果为orderType=0：不处理      如果orderType=1，则展示1和5
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	  * 注释：
	 *     返回的HashMap<Key,Value>    key:商品类别   value:List<JpmProductSaleTeamType>
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMap(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type);

	
	/**
	 * Add By WuCF 20160428 新增商品价格&PV&名称
	 * 获得商品的团队类型Map
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productCategoryStr：商品系列
	 * @productStr：商品编号字符串，用逗号隔开
	 * @type：类型判断    如果为orderType=0：不处理      如果orderType=1，则展示1和5
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	  * 注释：
	 *     返回的HashMap<Key,Value>    key:商品类别   value:List<JpmProductSaleTeamType>
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMapProNew(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type,
			String minPrice,String maxPrice,String minPv,String maxPv,String productNameStr);

	
	/**
	 * 获得商品的团队类型Map
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productCategoryStr：商品系列
	 * @productStr：商品编号字符串，用逗号隔开
	 * @type：类型判断    如果为orderType=0：不处理      如果orderType=1，则展示1和5
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	  * 注释：
	 *     返回的HashMap<Key,Value>    key:商品类别   value:List<JpmProductSaleTeamType>
	 */
	public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMapPage(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type,int pagenum,int pageSize);

	
	/**
	 * 获得商品的团队类型集合
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productStr：商品编号字符串，用逗号隔开
	 * @type：类型判断    如果为orderType=0：不处理      如果orderType=1，则展示1和5
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	 * 注释：参数查询条件teamCode,orderType,companyCode如果为空，则传递null
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeStrListPage(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type,int pagenum,int pageSize);
	
	/**
	 * Add By WuCF 20160425
	 * 获得商品的团队类型List分页数据
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @productCategoryStr：商品系列
	 * @productStr：商品编号字符串，用逗号隔开
	 * @type：类型判断    如果为orderType=0：不处理      如果orderType=1，则展示1和5
	 * @param pageNum：分页起始值
	 * @param pageSize：分页结束值
	 * @param minPrice：起始价格
	 * @param maxPrice：截止价格
	 * @param minPv：起始PV
	 * @param maxPv：截止PV
	 * @param productNameStr：名称(模糊查询)
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	  * 注释：
	 *     返回的HashMap<Key,Value>    key:商品类别   value:List<JpmProductSaleTeamType>
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPage2(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type,int pagenum,int pageSize,
			Integer minPrice,Integer maxPrice,Integer minPv,Integer maxPv,String productNameStr);
	
	/**
	 * 获得指定查询条件查询的商品类别集合
	 * @param orderType
	 * @param user
	 * @param categoryIds
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getProductCategoryByOrderType(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type);

	
	/**
	 * 获得指定的商品团队类型对象
	 * @param productNo:商品编码
	 * @param teamCode:团队类型
	 * @param orderType:订单类型
	 * @param companyCode:分区
	 * @return:指定的商品团队类型对象
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String productNo,String teamCode,String orderType,String companyCode);
	
	/**
	 * 获得指定的商品团队类型对象
	 * @param productNo:商品编码
	 * @param teamCode:团队类型
	 * @param orderType:订单类型
	 * @param companyCode:分区
	 * @param status:商品状态
	 * @return:指定的商品团队类型对象
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String productNo,String teamCode,String orderType,String companyCode,String status);

	
	/**
	 * 通过主键获得JpmProductSaleTeamType对象
	 * @param pttId
	 * @return
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String pttId);
	
	public List<Map<String, Object>> getIsOnSale(String pttId);
	public List<Map<String, Object>> getIsOnSale2(String pttId);
	/**
	 * 通过主键获得JpmProductSaleNew对象
	 * @param pttId
	 * @return
	 */
	public JpmProductSaleNew getJpmProductSaleNew(String uniNo);
	
	/**
	 * 通过商品编号productNo商品团队类型对应的主键ptt_id
	 * @param：productNo 商品编码
	 * @param：teamCode  团队编码
	 * @param：orderType 订单类型
	 * @return：商品团队类型对应的主键ptt_id
	 */
	public String getJpmProductSaleTeamTypePttid(String productNo,String teamCode,String orderType);
	
	/**
	 * 获得商品编码和名称信息
	 * @param status：状态，可以传递多个，用逗号隔开：0,1,2
	 * @return
	 */
	public Map<String,String> getJpmProductSaleNoAndName(String status);
	
	/*************************************2.物流信息**************************************/
	/**
	 * 通过主键获得物流发货信息，目前只查询4个字段：发货仓库、物流公司、运单号、发货时间
	 * @param pttId
	 * @return
	 */
	public List<Map<String, Object>> getPdSendinfo(String orderNo);
	
	/**
	 * 通过主键获得物流发货信息，
	 * 5个字段：发货仓库、       物流公司、        订单号、      发货时间、     　　状态  
	 *       WAREHOUSE_NO　　SH_NO　　　ORDER_NO　CREATE_TIME　ORDER_FLAG
	 * @param pttId
	 * @return
	 */
	public Map<String,List<Map<String, Object>>> getPdSendinfoMap(String orderNo);
	
	/**
	 * 通过物流公司编号获得物流信息：目前返回的只有网站信息
	 * @param shno
	 * @return
	 */
	public HashMap<String,String> getShnoInfo();
	
	/**
	 * 通过物流公司编号获得物流网站查询网址
	 * @param shno：物流公司编号
	 * @return：物流公司查询网址
	 */
	public String getShnoUrl(String shno);
	
	/**
	 * 通过订单号得到用户的发货网址信息
	 * @param orderNo
	 * @return
	 */
	public List<String[]> getUrlsByOrderno(String orderNo);
	
	/**
	 * 通过物流公司编号获得物流信息：返回的手机对应的物流公司编码（提供给手机）
	 * @param shno
	 * @return
	 */
	public HashMap<String, String> getShnoInfoforMobile();
	
	/**
	 * 通过订单号得到用户的发货物流信息（提供给手机）
	 * @param orderNo
	 * @return
	 */
	public List getWLByOrdernoforMobile(String orderNo);
	
	/**
	 * 获得团队表中的数据
	 * @param type：   0或1
	 * @return
	 * type=0:  返回Map<团队编号,true/flase:是否购买事业锦囊>
	 * type=1:  返回Map<团队编号,团队编号>
	 */
	public Map<String, Object> getJmiMemberTeamMap(String type);
	
	/**
	 * 获得指定参数列表中的数据集合
	 * @param listCode：   pmproduct.productcategory　　商品类别
	 * @return　
	 * 例如：传递参数pmproduct.productcategory，则返回所有商品类别的Map集合，
	 * 如果传递参数listCode为null或""，则返回所有的参数列表
	 */
	public Map<String, Object> getJsysListValueMap(String listCode);
	
	/**
     * Add By WuCF 20140311 发送短信记录保存
     */
    public void saveJpmSmssendInfo(JpmSmssendInfo jpmSmssendInfo);
    
    /**
	 * Add By WuCF 20150406 
	 * 获取家套餐45/65万大单商品数据
	 * @teamCode：团队编码     例：CN11111111
	 * @orderType：订单类型   例：1 目前只有首单
	 * @productStr：商品编号字符串，用逗号隔开    例：PN00001,PN00002,PN00003
	 * @companyCode：分公司编码     例：CN
	 * @return 返回指定商品团队订单类型数据JpmProductSaleTeamType的List集合    
	 * 注释：目前默认只有首单才卖
	 */
    public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeJtc(String teamCode,String orderType,String productStr,String companyCode);
    
    /**
     * 根据商品编号判断该商品是否为不可换货商品
     * 
     */
    boolean getIsNotChangeProduct(String productNo);
    
    /**
     * @param page：分页信息
	 * @param crm：查询的条件集合
	 * @return
     * 
     */
	List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPage(
			GroupPage page, String orderType, String teamCode);

	/**
	 * 
	 * @param page:分页信息
	 * @param crm：查询条件
	 * @return	分页集合
	 */
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeByCrm(
			GroupPage page, CommonRecord crm);
	
	/**
	 * 根据订单类型和团队编号获取推荐商品
	 * @param jsysUser
	 * @param isRecommend
	 * @param orderType
	 * @return
	 */
	public List<JpmProductSaleTeamType> getRecommendProductList(String teamType,String isRecommend, String orderType, Integer rownum);


	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPageSorted(
			GroupPage page,String orderType, String teamCode, String sortKeyword,String sortFlag);


}