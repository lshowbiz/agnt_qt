package com.joymain.ng.dao.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JpmProductSaleImage;
import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.JpmProductSaleRelated;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpmSmssendInfo;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.PdShUrlManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;

@Repository("jpmProductSaleNewDao")
@SuppressWarnings({"unchecked","unused"})
public class JpmProductSaleNewDaoJpa extends GenericDaoHibernate<JpmProductSaleNew, Long> implements JpmProductSaleNewDao {

	//add by gw     2014-07-11 
	@Autowired
	public PdShUrlManager pdShUrlManager;
	
    public JpmProductSaleNewDaoJpa() {
        super(JpmProductSaleNew.class);
    }
     
    /*************************************1.商品信息管理**************************************/
    /**
	 * 获得相关商品数据集合
	 * @param uniNo：商品销售新表主键
	 * @status：状态类型
	 * @return
	 */
    @Override
	public List<JpmProductSaleRelated> getJpmProductSaleRelatedList(String uniNo,String status) {
		StringBuffer sqlBuf = new StringBuffer("select jpsr from JpmProductSaleRelated jpsr where 1=1  ");
		//查询条件：分公司编码
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpsr.jpmProductSaleNew.uniNo='");
			sqlBuf.append(uniNo);
			sqlBuf.append("' ");
		}
		if(StringUtils.isNotEmpty(status)){
			sqlBuf.append(" and jpsr.status='");
			sqlBuf.append(status);
			sqlBuf.append("' ");
		}
		log.info("getJpmProductSaleRelatedList:"+sqlBuf.toString());
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
    /**
	 * 获得相关商品对应的JpmProductSaleTeamType对象的集合
	 * @param uniNo：商品销售新表主键
	 * @param teamCode：团队编码
	 * @param orderType：订单类型
	 * @return:uniNo对应的商品JpmProductSaleTeamType对象的List集合
	 */
    @Override
	public List<JpmProductSaleTeamType> getRelatedTeamTypeList(String uniNo,String teamCode,String orderType) {
    	List<JpmProductSaleTeamType> returnList = new ArrayList<JpmProductSaleTeamType>();
    	
    	//查询商品的相关商品，状态参数：0 表示:可用
    	ArrayList list = (ArrayList<JpmProductSaleRelated>)this.getJpmProductSaleRelatedList(uniNo,"0");
    	
    	//获得相关商品的编号信息字符串
    	StringBuffer relatedUninoBuf = new StringBuffer("");
		for(int i=0;i<list.size();i++){
			if(null!=((JpmProductSaleRelated) list.get(i)).getRelationJpmProductSaleNew()){
				relatedUninoBuf.append("'");
				relatedUninoBuf.append(((JpmProductSaleRelated)list.get(i)).getRelationJpmProductSaleNew().getUniNo());
				relatedUninoBuf.append("'");
				if(i<list.size()-1){
					relatedUninoBuf.append(",");
				}
			}
		}
		
		//获得满足指定条件的teamCode和orderType的JpmProductSaleTeamType对象的集合
		StringBuffer sqlBuf = new StringBuffer();
		if(!"".equals(relatedUninoBuf.toString())){
			sqlBuf.append(" select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
			sqlBuf.append(" and jpstt.jpmProductSaleNew.uniNo in(");
			sqlBuf.append(relatedUninoBuf.toString());
			sqlBuf.append(")");
			
			//添加日期判断 Add By WuCF 需要添加时间判断
			sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
			sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
			
			//查询条件：分公司编码
			if(StringUtils.isNotEmpty(teamCode)){
				sqlBuf.append(" and jpstt.jmiMemberTeam.code='");
				sqlBuf.append(teamCode);
				sqlBuf.append("' ");
			}
			if(StringUtils.isNotEmpty(orderType)){
				sqlBuf.append(" and jpstt.orderType='");
				sqlBuf.append(orderType);
				sqlBuf.append("' ");
			}
			//Modify By WuCF 20170207 首单订单类型不展示辅销品
			if("1".equals(orderType)){
				sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
			}
			returnList = this.getSession().createQuery(sqlBuf.toString()).list(); 
		}
		log.info("getRelatedTeamTypeList:"+sqlBuf.toString());
		//Modify By WuCF 20131024 如果添加的相关商品数据不足3个，则取同类型的商品填充list返回
		int size = returnList.size();
		if(size<3){
			int rownum = 3-size;
			JpmProductSaleNew jpsn = this.getJpmProductSaleNew(uniNo);
			sqlBuf = new StringBuffer(" select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");  
			sqlBuf.append(" and jpsn.jpmProduct.productCategory='");
			sqlBuf.append(jpsn.getJpmProduct().getProductCategory());
			sqlBuf.append("' and jpsn.uniNo!='");
			sqlBuf.append(jpsn.getUniNo());
			sqlBuf.append("' and jpsn.companyCode='");
			sqlBuf.append(jpsn.getCompanyCode());
			sqlBuf.append("' ");
			
			//添加日期判断 Add By WuCF 需要添加时间判断
			sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
			sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
			
			//查询条件：分公司编码
			if(StringUtils.isNotEmpty(teamCode)){
				sqlBuf.append(" and jpstt.jmiMemberTeam.code='");
				sqlBuf.append(teamCode);
				sqlBuf.append("' ");
			}
			if(StringUtils.isNotEmpty(orderType)){
				sqlBuf.append(" and jpstt.orderType='");
				sqlBuf.append(orderType);
				sqlBuf.append("' ");
			}
			//Modify By WuCF 20150310 已经包含的相关商品，不要再重复查询
			if(!"".equals(relatedUninoBuf.toString())){
				sqlBuf.append(" and jpsn.uniNo not in(");
				sqlBuf.append(relatedUninoBuf.toString());
				sqlBuf.append(")");
			}
			//Modify By WuCF 20170207 首单订单类型不展示辅销品
			if("1".equals(orderType)){
				sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
			}
			
			sqlBuf.append(" and rownum<="+rownum); 
			returnList.addAll(this.getSession().createQuery(sqlBuf.toString()).list());
		}
		return returnList; 

	}
	
    
    /**
	 * 获得商品的图片集合
	 * @param uniNo：商品销售新表主键
	 * @imageType：图片类型
	 * @return 返回指定图片类型的图片对象JpmProductSaleImage的List集合    
	 * 注释：如果imageType为空，则返回指定商品uniNo所有的图片的集合
	 */
    @Override
	public List<JpmProductSaleImage> getJpmProductSaleImageList(String uniNo,String imageType) {
		StringBuffer sqlBuf = new StringBuffer("select jpsi from JpmProductSaleImage jpsi where 1=1 ");
		//如果查询的图片类型不为空，则作为查询条件
		if(StringUtils.isNotEmpty(imageType)){
			sqlBuf.append(" and jpsi.jpmProductSaleNew.jpmProduct.uniNo='");
			sqlBuf.append(uniNo);
			sqlBuf.append("' ");
		}
		
		//如果查询的图片类型不为空，则作为查询条件
		if(StringUtils.isNotEmpty(imageType)){
			sqlBuf.append(" and jpsi.imageType='");
			sqlBuf.append(imageType);
			sqlBuf.append("' ");
		}
		log.info("getJpmProductSaleImageList:"+sqlBuf.toString());
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
    /**
	 * 获得商品的团队类型集合
	 * @param uniNo：商品销售新表主键
	 * @teamCode：团队编码
	 * @orderType：订单类型
	 * @companyCode：分公司编码
	 * @return 返回指定图片类型的图片对象JpmProductSaleTeamType的List集合    
	 * 注释：参数查询条件teamCode,orderType,companyCode如果为空，则传递null
	 */
    @Override
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(String uniNo,String teamCode,String orderType,String companyCode) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//商品UNI_NO
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.uniNo in('");
			sqlBuf.append(uniNo.replace(",", "','"));
			sqlBuf.append("') ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType in('");
			sqlBuf.append(orderType.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamTypeList:"+sqlBuf.toString());
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
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
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeList(
			String productNo, String productName, String productCategory,
			String teamCode, String orderType, String companyCode) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//商品编码
		if(StringUtils.isNotEmpty(productNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productNo like '%");
			sqlBuf.append(productNo.trim());
			sqlBuf.append("%' ");
		}		
		//商品名称
		if(StringUtils.isNotEmpty(productName)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productName like '%");
			sqlBuf.append(productName.trim());
			sqlBuf.append("%' ");
		}	
		//商品类别
		if(StringUtils.isNotEmpty(productName)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.productCategory ='");
			sqlBuf.append(productCategory.trim());
			sqlBuf.append("' ");
		}	
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType in('");
			sqlBuf.append(orderType.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamTypeList:"+sqlBuf.toString());
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
    
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
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeStrList(String uniNo,String teamCode,String orderType,String companyCode,String productStr,String type) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//商品UNI_NO
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.uniNo in('");
			sqlBuf.append(uniNo.replace(",", "','"));
			sqlBuf.append("') ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//商品编号字符串
		if(StringUtils.isNotEmpty(productStr)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productNo in('");
			sqlBuf.append(productStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			if("1".equals(type)){//如果为0：不展示   1：如果orderType为1，则展示1和5
				if("1".equals(orderType)){
					sqlBuf.append(" and jpstt.orderType in('1','5') ");
				}else{
					sqlBuf.append(" and jpstt.orderType in('");
					sqlBuf.append(orderType.replace(",", "','"));
					sqlBuf.append("') ");
				}
			}else{
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}
		
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
/*		companyCode = "CN,JP";
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in (:companyCode) "); 
		}
		Query q = this.getSession().createQuery(sqlBuf.toString());
//		List result = dao.query(hql, "idList", "1,2,3,4, 6, 10, 11, 12 ,13"); 
		q.setString("companyCode", companyCode);
		List list = q.list();
		System.out.println("=============="+list);*/
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamTypeStrList:"+sqlBuf.toString());
		log.info("type:"+type);
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
	
	/**
	 * Add By WuCF 20160428 新增商品价格&PV&名称
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
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeStrListProNew(String uniNo,String teamCode,String orderType,String companyCode,String productStr,String type,
			String minPrice,String maxPrice,String minPv,String maxPv,String productNameStr) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//商品UNI_NO
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.uniNo in('");
			sqlBuf.append(uniNo.replace(",", "','"));
			sqlBuf.append("') ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//商品编号字符串
		if(StringUtils.isNotEmpty(productStr)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productNo in('");
			sqlBuf.append(productStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			if("1".equals(type)){//如果为0：不展示   1：如果orderType为1，则展示1和5
				if("1".equals(orderType)){
					sqlBuf.append(" and jpstt.orderType in('1','5') ");
				}else{
					sqlBuf.append(" and jpstt.orderType in('");
					sqlBuf.append(orderType.replace(",", "','"));
					sqlBuf.append("') ");
				}
			}else{
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}
		
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//价格min
		if(StringUtils.isNotEmpty(minPrice)){
			sqlBuf.append(" and jpstt.price >=");
			sqlBuf.append(minPrice);
			sqlBuf.append(" ");
		}
		
		//价格max
		if(StringUtils.isNotEmpty(maxPrice)){
			sqlBuf.append(" and jpstt.price <=");
			sqlBuf.append(maxPrice);
			sqlBuf.append(" ");
		}
		
		//PV值min
		if(StringUtils.isNotEmpty(minPv)){
			sqlBuf.append(" and jpstt.pv >=");
			sqlBuf.append(minPv);
			sqlBuf.append(" ");
		}
		
		//PV值max
		if(StringUtils.isNotEmpty(maxPv)){
			sqlBuf.append(" and jpstt.pv <=");
			sqlBuf.append(maxPv);
			sqlBuf.append(" ");
		}
		
		//商品名称：模糊查询
		if(StringUtils.isNotEmpty(productNameStr)){
			sqlBuf.append(" and jpsn.productName like '%");
			sqlBuf.append(productNameStr.trim().replace("\'", "").replace("\"", ""));
			sqlBuf.append("%' ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
/*		companyCode = "CN,JP";
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in (:companyCode) "); 
		}
		Query q = this.getSession().createQuery(sqlBuf.toString());
//		List result = dao.query(hql, "idList", "1,2,3,4, 6, 10, 11, 12 ,13"); 
		q.setString("companyCode", companyCode);
		List list = q.list();
		System.out.println("=============="+list);*/
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamTypeStrList:"+sqlBuf.toString());
		log.info("type:"+type);
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
	
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
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeStrListPage(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type,int pagenum,int pageSize) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//商品UNI_NO
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.uniNo in('");
			sqlBuf.append(uniNo.replace(",", "','"));
			sqlBuf.append("') ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//商品编号字符串
		if(StringUtils.isNotEmpty(productStr)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productNo in('");
			sqlBuf.append(productStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//商品类别
		if(StringUtils.isNotBlank(productCategoryStr)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.productCategory in('");
			sqlBuf.append(productCategoryStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			if("1".equals(type)){//如果为0：不展示   1：如果orderType为1，则展示1和5
				if("1".equals(orderType)){
					sqlBuf.append(" and jpstt.orderType in('1','5') ");
				}else{
					sqlBuf.append(" and jpstt.orderType in('");
					sqlBuf.append(orderType.replace(",", "','"));
					sqlBuf.append("') ");
				}
			}else{
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}
		
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		//返回分页数据
		Query query = this.getSession().createQuery(sqlBuf.toString());
		query.setFirstResult((pagenum-1)*pageSize);
		query.setMaxResults(pageSize);
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamTypeStrListPage:"+sqlBuf.toString());
		return query.list();
//		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
	
	
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
			Integer minPrice,Integer maxPrice,Integer minPv,Integer maxPv,String productNameStr) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//商品UNI_NO
		if(StringUtils.isNotEmpty(uniNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.uniNo in('");
			sqlBuf.append(uniNo.replace(",", "','"));
			sqlBuf.append("') ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//商品编号字符串
		if(StringUtils.isNotEmpty(productStr)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productNo in('");
			sqlBuf.append(productStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//商品类别
		if(StringUtils.isNotBlank(productCategoryStr)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.productCategory in('");
			sqlBuf.append(productCategoryStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			if("1".equals(type)){//如果订单类型包含了首购单1，则要把辅销单5对应的商品类别展示
				if(orderType.contains(",1,")){
					sqlBuf.append(" and (jpstt.orderType in('");
					sqlBuf.append(orderType.replace(",", "','"));
					sqlBuf.append("') or jpstt.orderType='5') ");
				}else{
					sqlBuf.append(" and jpstt.orderType in('");
					sqlBuf.append(orderType.replace(",", "','"));
					sqlBuf.append("') ");
				}
			}else{
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		/*if(StringUtils.isNotEmpty(orderType)){
			if("1".equals(type)){//如果为0：不展示   1：如果orderType为1，则展示1和5
				if("1".equals(orderType)){
					sqlBuf.append(" and jpstt.orderType in('1','5') ");
				}else{
					sqlBuf.append(" and jpstt.orderType in('");
					sqlBuf.append(orderType.replace(",", "','"));
					sqlBuf.append("') ");
				}
			}else{
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}*/
		
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//价格min
		if(minPrice!=null){
			sqlBuf.append(" and jpstt.price >=");
			sqlBuf.append(minPrice);
			sqlBuf.append(" ");
		}
		
		//价格max
		if(maxPrice!=null){
			sqlBuf.append(" and jpstt.price <=");
			sqlBuf.append(maxPrice);
			sqlBuf.append(" ");
		}
		
		//PV值min
		if(minPv!=null){
			sqlBuf.append(" and jpstt.pv >=");
			sqlBuf.append(minPv);
			sqlBuf.append(" ");
		}
		
		//PV值max
		if(maxPv!=null){
			sqlBuf.append(" and jpstt.pv <=");
			sqlBuf.append(maxPv);
			sqlBuf.append(" ");
		}
		
		//商品名称：模糊查询
		if(StringUtils.isNotEmpty(productNameStr)){
			sqlBuf.append(" and jpsn.productName like '%");
			sqlBuf.append(productNameStr.trim());
			sqlBuf.append("%' ");
		}
		
		//返回分页数据
		Query query = this.getSession().createQuery(sqlBuf.toString());
		query.setFirstResult((pagenum-1)*pageSize);
		query.setMaxResults(pageSize);
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamTypeStrListPage:"+sqlBuf.toString());
		return query.list();
//		return this.getSession().createQuery(sqlBuf.toString()).list();
	}
	
	
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
    @Override
	public HashMap<String,ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMap(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr) {
    	HashMap<String,ArrayList<JpmProductSaleTeamType>> map = new HashMap<String,ArrayList<JpmProductSaleTeamType>>();
		
    	//调用方法获得JpmProductSaleTeamType对象的List集合
    	ArrayList<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = (ArrayList<JpmProductSaleTeamType>)this.getJpmProductSaleTeamTypeList(uniNo, teamCode, orderType, companyCode);
		
		//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//第一步：存放商品系列的Set容器(不能重复)
		Set<String> productCategorys = new HashSet<String>();
		for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
			productCategorys.add(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory());
		}
		
		//获得系列的list集合
		List productCateList = new ArrayList<String>();
		if(productCategoryStr!=null){
			String[] strs = productCategoryStr.split(",");
			for(String str : strs){
				productCateList.add(str);
			}
		}
		
		
		//第二步：通过商品系列分类
		ArrayList<JpmProductSaleTeamType> tempList = null; 
		for(String productCategory : productCategorys){
			if(productCateList.contains(productCategory) || productCategoryStr==null || "".equals(productCategoryStr) ){
				tempList = new ArrayList<JpmProductSaleTeamType>();
				for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
					if(productCategory!=null && productCategory.equals(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory())){
						tempList.add(jpstt);
					}
				}
				map.put(productCategory, tempList);
			}
		}
		
		
		return map;
	}
    
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
    public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMap(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type) {
    	HashMap<String,ArrayList<JpmProductSaleTeamType>> map = new HashMap<String,ArrayList<JpmProductSaleTeamType>>();
		
    	//调用方法获得JpmProductSaleTeamType对象的List集合
    	ArrayList<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = (ArrayList<JpmProductSaleTeamType>)this.getJpmProductSaleTeamTypeStrList(uniNo, teamCode, orderType, companyCode,productStr,type);
		
		//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//第一步：存放商品系列的Set容器(不能重复)
		Set<String> productCategorys = new HashSet<String>();
		for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
			productCategorys.add(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory());
		}
		
		//获得系列的list集合
		List productCateList = new ArrayList<String>();
		if(productCategoryStr!=null){
			String[] strs = productCategoryStr.split(",");
			for(String str : strs){
				productCateList.add(str);
			}
		}
		
		
		//第二步：通过商品系列分类
		ArrayList<JpmProductSaleTeamType> tempList = null; 
		for(String productCategory : productCategorys){
			if(productCateList.contains(productCategory) || productCategoryStr==null || "".equals(productCategoryStr) ){
				tempList = new ArrayList<JpmProductSaleTeamType>();
				for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
					if(productCategory!=null && productCategory.equals(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory())){
						tempList.add(jpstt);
					}
				}
				map.put(productCategory, tempList);
			}
		}
		
		
		return map;
	}
    
    
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
    		String minPrice,String maxPrice,String minPv,String maxPv,String productNameStr) {
    	HashMap<String,ArrayList<JpmProductSaleTeamType>> map = new HashMap<String,ArrayList<JpmProductSaleTeamType>>();
		
    	//调用方法获得JpmProductSaleTeamType对象的List集合
    	ArrayList<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = (ArrayList<JpmProductSaleTeamType>)this.getJpmProductSaleTeamTypeStrListProNew(uniNo, teamCode, orderType, companyCode,productStr,type,
    			minPrice,maxPrice,minPv,maxPv,productNameStr);
		
		//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//第一步：存放商品系列的Set容器(不能重复)
		Set<String> productCategorys = new HashSet<String>();
		for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
			productCategorys.add(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory());
		}
		
		//获得系列的list集合
		List productCateList = new ArrayList<String>();
		if(productCategoryStr!=null){
			String[] strs = productCategoryStr.split(",");
			for(String str : strs){
				productCateList.add(str);
			}
		}
		
		
		//第二步：通过商品系列分类
		ArrayList<JpmProductSaleTeamType> tempList = null; 
		for(String productCategory : productCategorys){
			if(productCateList.contains(productCategory) || productCategoryStr==null || "".equals(productCategoryStr) ){
				tempList = new ArrayList<JpmProductSaleTeamType>();
				for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
					if(productCategory!=null && productCategory.equals(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory())){
						tempList.add(jpstt);
					}
				}
				map.put(productCategory, tempList);
			}
		}
		
		
		return map;
	}
    /**
	 * 获得指定查询条件查询的商品类别集合
	 * @param orderType
	 * @param user
	 * @param categoryIds
	 * @param type
	 * @return
	 */
    public List<Map<String, Object>> getProductCategoryByOrderType(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type) {
    	HashMap<String,String> map = new HashMap<String,String>();
		
    	//调用方法获得JpmProductSaleTeamType对象的List集合
    	ArrayList<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = (ArrayList<JpmProductSaleTeamType>)this.getJpmProductSaleTeamTypeStrList(uniNo, teamCode, orderType, companyCode,productStr,type);
		
    	//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//第一步：存放商品系列的Set容器(不能重复)
		Set<String> productCategorys = new HashSet<String>();
		for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
			productCategorys.add(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory());
		}
    	
		//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//商品类别字符串用逗号隔开
		StringBuffer categorys = new StringBuffer("");
		for(String jpstt: productCategorys){
			categorys.append(","+jpstt);
		}
		
		StringBuffer sqlBuf = new StringBuffer(" with t as( ");
		sqlBuf.append(" select t2.value_code,t2.value_title, ");
		sqlBuf.append(" (select t4.character_value from JAL_CHARACTER_KEY t3,JAL_CHARACTER_VALUE t4 where t3.key_id=t4.key_id ");
		sqlBuf.append(" and t4.character_code='zh_CN' and t3.character_key=t2.value_title) value_title2,t2.ORDER_NO ");
		sqlBuf.append(" from JSYS_LIST_KEY t1,JSYS_LIST_VALUE t2 where t1.key_id=t2.key_id ");
		sqlBuf.append(" and t1.list_code='pmproduct.productcategory') ");
		sqlBuf.append(" select t.value_code code,(case when value_title2 is not null then value_title2 else value_title end) name,t.ORDER_NO from t ");
		sqlBuf.append(" where 1=1 ");
		if(categorys.toString()!=null && categorys.length()>=1){
			sqlBuf.append(" and t.value_code in(");
			sqlBuf.append(categorys.toString().substring(1));
			sqlBuf.append(")");
		}
		sqlBuf.append(" order by t.order_no ");
		log.info("getProductCategoryByOrderType:"+sqlBuf.toString());
		return this.getJdbcTemplate().queryForList(sqlBuf.toString());
	}
    
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
    public HashMap<String, ArrayList<JpmProductSaleTeamType>> getJpmProductSaleTeamTypeMapPage(String uniNo,String teamCode,String orderType,String companyCode,String productCategoryStr,String productStr,String type,int pagenum,int pageSize) {
    	HashMap<String,ArrayList<JpmProductSaleTeamType>> map = new HashMap<String,ArrayList<JpmProductSaleTeamType>>();
		
    	//调用方法获得JpmProductSaleTeamType对象的List集合
    	ArrayList<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = (ArrayList<JpmProductSaleTeamType>)this.getJpmProductSaleTeamTypeStrListPage(uniNo, teamCode, orderType, companyCode,productCategoryStr,productStr,type,pagenum,pageSize);
		
		//开始处理查询到的数据集合，将List数据按商品类型作为键，返回Map
		//第一步：存放商品系列的Set容器(不能重复)
		Set<String> productCategorys = new HashSet<String>();
		for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
			productCategorys.add(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory());
		}
		
		//获得系列的list集合
		List productCateList = new ArrayList<String>();
		if(productCategoryStr!=null){
			String[] strs = productCategoryStr.split(",");
			for(String str : strs){
				productCateList.add(str);
			}
		}
		
		
		//第二步：通过商品系列分类
		ArrayList<JpmProductSaleTeamType> tempList = null; 
		for(String productCategory : productCategorys){
			if(productCateList.contains(productCategory) || productCategoryStr==null || "".equals(productCategoryStr) ){
				tempList = new ArrayList<JpmProductSaleTeamType>();
				for(JpmProductSaleTeamType jpstt: jpmProductSaleTeamTypeList){
					if(productCategory!=null && productCategory.equals(jpstt.getJpmProductSaleNew().getJpmProduct().getProductCategory())){
						tempList.add(jpstt);
					}
				}
				map.put(productCategory, tempList);
			}
		}
		
		
		return map;
	}
	
    
    /**
	 * 获得指定的商品团队类型对象
	 * @param productNo:商品编码
	 * @param teamCode:团队类型
	 * @param orderType:订单类型
	 * @param companyCode:分区
	 * @return:指定的商品团队类型对象
	 */	
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String productNo,
			String teamCode, String orderType, String companyCode) {
		JpmProductSaleTeamType jpst = null;
		StringBuffer sqlBuf = new StringBuffer(" select jpstt from JpmProductSaleNew jpsn,JpmProductSaleTeamType jpstt ");
		sqlBuf.append(" where jpsn.uniNo=jpstt.jpmProductSaleNew.uniNo and jpsn.status='1' and jpstt.state='0' ");
		//商品编码
		if(StringUtils.isNotEmpty(productNo)){
			sqlBuf.append(" and jpsn.jpmProduct.productNo='");
			sqlBuf.append(productNo);
			sqlBuf.append("' ");
		}
		//团队类型
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code='");
			sqlBuf.append(teamCode);
			sqlBuf.append("' ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType='");
			sqlBuf.append(orderType);
			sqlBuf.append("' ");
		}
		//分区
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpsn.companyCode='");
			sqlBuf.append(companyCode);
			sqlBuf.append("' ");
		}
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamType:"+sqlBuf.toString());
		List<JpmProductSaleTeamType> list = this.getSession().createQuery(sqlBuf.toString()).list();
		if(list!=null && list.size()>=1){
			jpst = (JpmProductSaleTeamType)list.get(0);
		}
		return jpst;
	}
	
	/**
	 * 获得指定的商品团队类型对象
	 * @param productNo:商品编码
	 * @param teamCode:团队类型
	 * @param orderType:订单类型
	 * @param companyCode:分区
	 * @return:指定的商品团队类型对象
	 */	
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String productNo,
			String teamCode, String orderType, String companyCode ,String status) {
		JpmProductSaleTeamType jpst = null;
		StringBuffer sqlBuf = new StringBuffer(" select jpstt from JpmProductSaleNew jpsn,JpmProductSaleTeamType jpstt ");
		sqlBuf.append(" where jpsn.uniNo=jpstt.jpmProductSaleNew.uniNo and jpstt.state='0' ");
		//商品编码
		if(StringUtils.isNotEmpty(productNo)){
			sqlBuf.append(" and jpsn.jpmProduct.productNo='");
			sqlBuf.append(productNo);
			sqlBuf.append("' ");
		}
		//团队类型
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code='");
			sqlBuf.append(teamCode);
			sqlBuf.append("' ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType='");
			sqlBuf.append(orderType);
			sqlBuf.append("' ");
		}
		//分区
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpsn.companyCode='");
			sqlBuf.append(companyCode);
			sqlBuf.append("' ");
		}
		//状态
		if(StringUtils.isNotBlank(status)){
			sqlBuf.append(" and jpsn.status in("+status+")");
			//sqlBuf.append(status);
			//sqlBuf.append("' ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamType:"+sqlBuf.toString());
		List<JpmProductSaleTeamType> list = this.getSession().createQuery(sqlBuf.toString()).list();
		if(list!=null && list.size()>=1){
			jpst = (JpmProductSaleTeamType)list.get(0);
		}
		return jpst;
	}

	/**
	 * 通过主键获得JpmProductSaleTeamType对象
	 * @param pttId
	 * @return
	 */ 
    @Override
	public JpmProductSaleTeamType getJpmProductSaleTeamType(String pttId) {
    	return (JpmProductSaleTeamType) this.getObjectByHqlQuery("from JpmProductSaleTeamType jpstt where jpstt.pttId='"+pttId+"' ");
	}	
    
    /**
     * 查商品是否已停售
     * @param pttId
     * @return
     */
    public List<Map<String, Object>> getIsOnSale(String pttId) {
		StringBuffer sqlBuffer = new StringBuffer("SELECT product_no FROM jpm_product_sale_new a ,Jpm_Product_Sale_Team_Type b " +
				" WHERE a.uni_no = b.uni_no AND a.status IN (0,2) AND b.ptt_id = '"+pttId+"'");
		log.info("IsOnsale product:"+sqlBuffer.toString());
		return this.getJdbcTemplate().queryForList(sqlBuffer.toString());
	}
    public List<Map<String, Object>> getIsOnSale2(String pttId) {
    	StringBuffer sqlBuffer = new StringBuffer("SELECT product_no FROM jpm_product_sale_new a ,Jpm_Product_Sale_Team_Type b " +
    			" WHERE a.uni_no = b.uni_no AND b.ptt_id = '"+pttId+"'");
    	log.info("IsOnsale product:"+sqlBuffer.toString());
    	return this.getJdbcTemplate().queryForList(sqlBuffer.toString());
    }
    
    /**
	 * 通过主键获得JpmProductSaleNew对象
	 * @param pttId
	 * @return
	 */ 
    @Override
	public JpmProductSaleNew getJpmProductSaleNew(String uniNo) {
    	return (JpmProductSaleNew) this.getObjectByHqlQuery("from JpmProductSaleNew jpsn where jpsn.uniNo='"+uniNo+"' ");
	}	
    
    /**
	 * 通过商品编号productNo商品团队类型对应的主键ptt_id
	 * @param：productNo 商品编码
	 * @return：商品团队类型对应的主键ptt_id
	 */
    @Override
	public String getJpmProductSaleTeamTypePttid(String productNo,String teamCode,String orderType) {
    	String pttId = "";
    	StringBuffer sqlBuf = new StringBuffer(" select jpstt from JpmProductSaleNew jpsn , ");
    	sqlBuf.append(" JpmProductSaleTeamType jpstt where jpsn.uniNo=jpstt.jpmProductSaleNew.uniNo and jpsn.status='1' and jpstt.state='0' ");
    	//商品UNI_NO
		if(StringUtils.isNotEmpty(productNo)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.jpmProduct.productNo='");
			sqlBuf.append(productNo);
			sqlBuf.append("' ");
		}		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code='");
			sqlBuf.append(teamCode);
			sqlBuf.append("' ");
		}
		//订单类型
		if(StringUtils.isNotEmpty(orderType)){
			sqlBuf.append(" and jpstt.orderType='");
			sqlBuf.append(orderType);
			sqlBuf.append("' ");
		}
		
		List list =this.getSession().createQuery(sqlBuf.toString()).list();
		if(list!=null && list.size()>=1){
			pttId = String.valueOf(((JpmProductSaleTeamType)list.get(0)).getPttId());
		}
		log.info("getJpmProductSaleTeamTypePttid:"+sqlBuf.toString());
    	return pttId;
	}	
    
    /**
	 * 获得商品编码和名称信息
	 * @param status：状态，可以传递多个，用逗号隔开：0,1,2
	 * @return
	 */
	public Map<String,String> getJpmProductSaleNoAndName(String status){
		Map<String,String> map = new HashMap<String,String>();
		StringBuffer sqlBuf = new StringBuffer(" from JpmProductSaleNew jpsn where jpsn.companyCode='CN' ");
		//状态
		if(StringUtils.isNotEmpty(status)){
			sqlBuf.append(" and jpsn.status in('");
			sqlBuf.append(status.replace(",", "','"));
			sqlBuf.append("') ");
		}
		List list = this.getSession().createQuery(sqlBuf.toString()).list();
		JpmProductSaleNew jpsn = null;
		for(Object obj : list){
			jpsn = (JpmProductSaleNew)obj;
			map.put(jpsn.getProductNo(),jpsn.getProductName());
		}
		log.info("getJpmProductSaleNoAndName:"+sqlBuf.toString());
		return map;
	}
    
    /*************************************2.物流信息**************************************/
    /**
	 * 通过主键获得物流发货信息，目前只查询
	 * 5个字段：发货仓库、       物流公司、        订单号、      发货时间、     　　状态  、        物流跟踪号;
	 *       WAREHOUSE_NO　　SH_NO　　　ORDER_NO　CREATE_TIME　ORDER_FLAG  tracking_No
	 * @param pttId
	 * @return 返回List
	 */
	@Override
	public List<Map<String, Object>> getPdSendinfo(String orderNo) {
		StringBuffer sqlBuffer = new StringBuffer("select t.* from (select (select t0.warehouse_name from pd_warehouse t0 where t0.warehouse_no=t1.warehouse_no and rownum=1) warehouse_name,t1.sh_no,t1.order_no,t1.ok_time,t1.order_flag,t1.tracking_No ");
		sqlBuffer.append(" from pd_send_info t1 inner join jpo_member_order t2 ");
		sqlBuffer.append(" on t1.order_no=t2.member_order_no where t1.order_no=? order by t1.ok_time desc )t ");
		log.info("getPdSendinfo:"+sqlBuffer.toString());
		return this.getJdbcTemplate().queryForList(sqlBuffer.toString(), new Object[]{orderNo}); 
	}
	
	
	/**
	 * 通过主键获得物流发货信息，目前只查询
	 * 5个字段：发货仓库、       物流公司、        订单号、      发货时间、     　　状态  
	 *       WAREHOUSE_NO　　SH_NO　　　ORDER_NO　CREATE_TIME　ORDER_FLAG
	 * @param pttId
	 * @return 返回Map
	 * 返回值：Map<String, List<Map<String, Object>>>

		如果返回结果的key为“-1#-1#-1”，表示没有查询到数据 list.size()=0

		其他形式 例如“0#2#1#URL1#URL2”  list.size()>0
	 */
	@Override
	public Map<String, List<Map<String, Object>>> getPdSendinfoMap(String orderNo) {
		HashMap<String,List<Map<String, Object>>> map = new HashMap<String,List<Map<String, Object>>>();
		List<Map<String, Object>> list = this.getPdSendinfo(orderNo);
		List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
		if(list!=null && list.size()>=1){
			Integer num1 = 0;
			Integer num2 = 0;
			Integer num3 = 0;
		
			for(Map<String,Object> m : list){
				if(Integer.parseInt(m.get("ORDER_FLAG").toString())<2){
					num1++;
				}else if(Integer.parseInt(m.get("ORDER_FLAG").toString())==2){
			
					returnList.add(m);
					num2++;
				}else if(Integer.parseInt(m.get("ORDER_FLAG").toString())>=3){
				
					//returnList.add(m);
					num3++;
				} 
			}
			String key = num1+"#"+num2+"#"+num3;
			map.put(key, returnList);
		}else{
			map.put("-1#-1#-1", returnList);
		}
		return map;
	}

	/**
	 * 通过物流公司编号获得物流信息：目前返回的只有网站信息
	 * @param shno
	 * @return
	 */
	@Override
	public HashMap<String, String> getShnoInfo() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("ZTWL", JpmProductSaleNewDao.ZTWL_URL);//中铁物流
		map.put("GT", JpmProductSaleNewDao.GT_URL);//国通
		map.put("BZ", JpmProductSaleNewDao.BZ_URL);//倍智
		map.put("TXJ", JpmProductSaleNewDao.TXJ_URL);//统新捷
		map.put("YD", JpmProductSaleNewDao.YD_URL);//韵达
		map.put("ZJS", JpmProductSaleNewDao.ZJS_URL);//宅急送
		map.put("DTW", JpmProductSaleNewDao.DTW_URL);//大田物流  目前已经禁用
		map.put("ZTO", JpmProductSaleNewDao.ZTO_URL);//中通
		map.put("EMS", JpmProductSaleNewDao.EMS_URL);//EMS
		map.put("SHUNFENG", JpmProductSaleNewDao.SHUNFENG_URL);//顺丰
		map.put("SHENTONG", JpmProductSaleNewDao.SHENTONG_URL);//申通
		map.put("HUITONG", JpmProductSaleNewDao.HUITONG_URL);//汇通快递
		map.put("DEBANG", JpmProductSaleNewDao.DEBANG_URL);//德邦物流
		map.put("YUANTONG", JpmProductSaleNewDao.YUANTONG_URL);//2014-03-31 圆通快递
		map.put("BAISHI", JpmProductSaleNewDao.BAISHI_URL);//百世物流 
		map.put("BSHT", JpmProductSaleNewDao.BSHT_URL);//百世汇通
		map.put("XINYITAI", JpmProductSaleNewDao.XINYITAI_URL);//新易泰物流
		map.put("SUER", JpmProductSaleNewDao.SUER_URL);//速尔快递
		map.put("TLU", JpmProductSaleNewDao.TLU_URL);//广州通路
		map.put("STARS", JpmProductSaleNewDao.STARS_URL);//广州通路
		return map;
	}
	/**
	 * 通过物流公司编号获得物流信息：返回的手机对应的物流公司编码（提供给手机）
	 * @param shno
	 * @return
	 */
	@Override
	public HashMap<String, String> getShnoInfoforMobile() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("ZTWL", "zhongtiekuaiyun");//中铁物流	(zhongtiekuaiyun	中铁快运)
		map.put("GT", "guotongkuaidi");//国通	(guotongkuaidi	国通快递)
		map.put("BZ", "");//倍智
		map.put("TXJ","");//统新捷
		map.put("YD", "yunda");//韵达	(yunda		韵达快运)
		map.put("ZJS", "zhaijisong");//宅急送	(zhaijisong	宅急送)
		map.put("DTW", "datianwuliu");//大田物流  目前已经禁用         	(datianwuliu	大田物流)
		map.put("ZTO", "zhongtong");//中通		(zhongtong	中通速递)
		map.put("EMS", "ems");//EMS		(ems		ems快递)
		map.put("SHUNFENG", "shunfeng");//顺丰		(shunfeng	顺丰)
		map.put("SHENTONG", "shentong");//申通		(shentong	申通)
		map.put("HUITONG", "huitongkuaidi");//汇通快递		(huitongkuaidi	汇通快运)
		map.put("DEBANG", "debangwuliu");//德邦物流		(debangwuliu	德邦物流)
		map.put("YUANTONG", "yuantong");//2014-03-31 圆通快递		(yuantong	圆通速递)
		map.put("BAISHI", "baishi");//百世物流 
		map.put("BSHT", "baishihuitong");//百世汇通
		map.put("XINYITAI", "xinyitai");//新易泰物流
		map.put("SUER", "suer");//速尔快递
		map.put("TLU", "tlu");//广州通路
		map.put("STARS", "xinchenjibian");//星晨急便
		return map;
	}
	
	/**
	 * 通过物流公司编号获得物流网站查询网址
	 * @param shno：物流公司编号
	 * @return：物流公司查询网址
	 */
	@Override
	public String getShnoUrl(String shno) {
		String url = "";
		Map map = this.getShnoInfo();
//		log.info("========www:"+map);
		if(map.get(shno)!=null){
			url = map.get(shno).toString();
		}else{
			url = "";
		}
		return url;
	}
	
	/**
	 * 通过订单号得到用户的发货网址信息
	 * @param orderNo
	 * @return
	 */
	@Override
	public List<String[]> getUrlsByOrderno(String orderNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String[]> returnList = new ArrayList<String[]>();
		StringBuffer sqlBuffer = new StringBuffer("select t1.sh_no,t1.tracking_no ");
		sqlBuffer.append(" from pd_send_info t1 inner join jpo_member_order t2 "); 
		sqlBuffer.append(" on t1.order_no=t2.member_order_no where t1.order_no=? ");
		sqlBuffer.append(" and t1.order_flag>=2 ");
		list = this.getJdbcTemplate().queryForList(sqlBuffer.toString(), new Object[]{orderNo}); 
		String[] strs = null;
		
		for(Map<String,Object> m : list){
			//strs[0]:物流公司对应的网站，strs[1]：对应查询的跟踪号
			//目前倍智和统新捷没有对应网站！
			strs = new String[2];
			//--gw 注释  2014-07-11
			//strs[0] = this.getShnoUrl(m.get("SH_NO").toString());
			
			//add by gw  begin
			strs[0] = pdShUrlManager.getShUrl(m.get("SH_NO").toString());
			//add by gw  begin
			
			strs[1] = m.get("TRACKING_NO")!=null ? m.get("TRACKING_NO").toString() : "";
			if(StringUtils.isNotEmpty(strs[1])){
				returnList.add(strs);
			}
		}
		log.info("getUrlsByOrderno:"+sqlBuffer.toString());
		return returnList;
	}
	
	/**
	 * 通过订单号得到用户的发货物流信息（提供给手机）
	 * @param orderNo
	 * @return
	 */
	@Override
	public List getWLByOrdernoforMobile(String orderNo) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List returnList = new ArrayList();
		StringBuffer sqlBuffer = new StringBuffer("select t1.sh_no,t1.tracking_no ");
		sqlBuffer.append(" from pd_send_info t1 inner join jpo_member_order t2 "); 
		sqlBuffer.append(" on t1.order_no=t2.member_order_no where t1.order_no=? ");
		sqlBuffer.append(" and t1.order_flag>=2 ");
		list = this.getJdbcTemplate().queryForList(sqlBuffer.toString(), new Object[]{orderNo}); 
		Map<String,Object>  map= null; 
		
		for(Map<String,Object> m : list){
			//type:物流公司对应的提供手机的物流公司编码，postid：对应查询的跟踪号
			//目前手机 端，倍智，统新捷，百世物流，百世汇通 没有对应的链接查询！
			map = new HashMap();
			String type=getShnoInfoforMobile().get(m.get("SH_NO").toString());
			String postid=m.get("TRACKING_NO")!=null ? m.get("TRACKING_NO").toString() : "";
			map.put("type",type);
			map.put("postidMsg", postid);
			map.put("postid", getPostidList(postid));
//			map.put("url", "http://m.kuaidi100.com/index_all.html?type="+type+"&postid="+postid);
			if(StringUtils.isNotEmpty(postid)){
				returnList.add(map);
			}
		}
//		log.info("getWLByOrdernoforMobile --orderNo:"+orderNo);
//		log.info("getWLByOrdernoforMobile:"+sqlBuffer.toString());
		return returnList;
	}
	/**
	 * 只返回物流跟踪号list集合
	 * @param postid
	 * @return
	 */
	public List<String> getPostidList(String postid){
		 List<String> ss = new ArrayList<String>();
		 if(StringUtils.isNotEmpty(postid)){
			 for(String sss:postid.replaceAll("[^0-9]", ",").split(",")){
		            if (sss.length()>=8)
		                ss.add(sss);
		        }
		 }
	     return ss;
	}
	/**
	 * 获得团队表中的数据
	 * @param type：   0或1
	 * @return
	 * type=0:  返回Map<团队编号,true/flase:是否购买事业锦囊>
	 * type=1:  返回Map<团队编号,团队编号>
	 */
	public Map<String, Object> getJmiMemberTeamMap(String type) {
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuffer sqlBuffer = new StringBuffer(" select t.code,t.is_buy from jmi_member_team t where t.status='0' ");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sqlBuffer.toString()); 
		String code = "";
		for(Map<String,Object> m : list){
			code = m.get("CODE").toString(); 
//			System.out.println(code+"\t"+m.get("IS_BUY")+"\t"+("0".equals(m.get("IS_BUY")) ? false : true));
			if("0".equals(type)){
				map.put(code, "0".equals(m.get("IS_BUY")) ? false : true);
			}else if("1".equals(type)){
				map.put(code, code);
			}
		}
		/*System.out.println("=========================");
		Set<String> key = map.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            String s = (String) it.next();
            System.out.println(s+"\t"+map.get(s));
        }*/
		return map;
	}
	
	
	/**
	 * 获得指定参数列表中的数据集合
	 * @param listCode：   pmproduct.productcategory　　商品类别
	 * @return　
	 * 例如：传递参数pmproduct.productcategory，则返回所有商品类别的Map集合，
	 * 如果传递参数listCode为null或""，则返回所有的参数列表
	 */
	public Map<String, Object> getJsysListValueMap(String listCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuffer sqlBuffer = new StringBuffer(" with t as( ");
		sqlBuffer.append(" select t2.value_code,t2.value_title, ");
		sqlBuffer.append(" (select t4.character_value from JAL_CHARACTER_KEY t3,JAL_CHARACTER_VALUE t4 where t3.key_id=t4.key_id ");
		sqlBuffer.append(" and t4.character_code='zh_CN' and t3.character_key=t2.value_title) value_title2 ");
		sqlBuffer.append(" from JSYS_LIST_KEY t1,JSYS_LIST_VALUE t2 where t1.key_id=t2.key_id ");
		sqlBuffer.append(" and t1.list_code='");
		sqlBuffer.append(listCode);
		sqlBuffer.append("') ");
		sqlBuffer.append(" select t.value_code CODE,(case when value_title2 is not null then value_title2 else value_title end) NAME from t ");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sqlBuffer.toString()); 
		String code = "";
		String name = "";
		for(Map<String,Object> m : list){
			code = m.get("CODE").toString();
			name = m.get("NAME").toString();
			map.put(code, name);
		}
		log.info("getJsysListValueMap:"+sqlBuffer.toString());
		return map;
	}

	/**
     * Add By WuCF 20140311 发送短信记录保存
     */
	public void saveJpmSmssendInfo(JpmSmssendInfo jpmSmssendInfo) {
		if(jpmSmssendInfo!=null && jpmSmssendInfo.getSmsMessage()!=null){//发送消息不能为空，且过滤引号
    		String smsMessage = jpmSmssendInfo.getSmsMessage();
    		smsMessage.replace("\'", "‘");
    		smsMessage.replace("\"", "“");
    		jpmSmssendInfo.setSmsMessage(smsMessage);
    	}else{
    		return;
    	}
    	
    	StringBuffer sqlBuf = new StringBuffer("insert into jpm_smssend_info");
    	sqlBuf.append("(sms_id,sms_type,sms_recipient,sms_message,sms_time,sms_operator,sms_status,remark,phone_num)values(");
    	sqlBuf.append("SEQ_SMS.nextval,'");
    	sqlBuf.append(jpmSmssendInfo.getSmsType());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getSmsRecipient());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getSmsMessage());
    	sqlBuf.append("',sysdate,'");
    	sqlBuf.append(jpmSmssendInfo.getSmsOperator());
    	sqlBuf.append("','"); 
    	sqlBuf.append(jpmSmssendInfo.getSmsStatus());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getRemark());
    	sqlBuf.append("','");
    	sqlBuf.append(jpmSmssendInfo.getPhoneNum());
    	sqlBuf.append("')");
    	this.getJdbcTemplate().execute(sqlBuf.toString());
	}
	
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
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeJtc(String teamCode,String orderType,String productStr,String companyCode) {
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//订单类型
		if("1".equals(orderType)){
			sqlBuf.append(" and jpstt.orderType in('1') ");
		}else{
			sqlBuf.append(" and jpstt.orderType in('");
			sqlBuf.append(orderType.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//商品编号字符串
		if(StringUtils.isNotEmpty(productStr)){
			sqlBuf.append(" and jpstt.jpmProductSaleNew.productNo in('");
			sqlBuf.append(productStr.replace(",", "','"));
			sqlBuf.append("') ");
		}
		
		//分公司
		if(StringUtils.isNotEmpty(companyCode)){
			sqlBuf.append(" and jpstt.companyCode in('");
			sqlBuf.append(companyCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		sqlBuf.append(" order by jpsn.sortFlag ");
		log.info("getJpmProductSaleTeamTypeJtc:"+sqlBuf.toString());
		return this.getSession().createQuery(sqlBuf.toString()).list();
	}

	@Override
	public boolean getIsNotChangeProduct(String productNo) {
		if(!StringUtil.isEmpty(productNo)){
		     StringBuffer sqlBuf = new StringBuffer("select a.product_no from jpm_product_sale_new a where CHANGEABLED_FLAG='0' ");
		     sqlBuf.append(" and a.product_no= '").append(productNo).append("'");
		     List list = this.jdbcTemplate.queryForList(sqlBuf.toString());
		     if(null!=list){
		    	 if(list.size()>0){
		    		 return true;
		    	 }
		     }
		     return false;
		}
		return false;
	}

	@Override
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPage(
			GroupPage page, String orderType,String teamCode) {
		//查询分页的总数量
		StringBuffer sqlCountBuf = new StringBuffer("select count(jpstt.pttId) from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' and jpsn.companyCode='CN' ");
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlCountBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlCountBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//换货单数据排除美体的数据
    	sqlCountBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");
		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlCountBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlCountBuf.append(teamCode.replace(",", "','"));
			sqlCountBuf.append("') ");
		}
		//订单类型
		//自助换货--选择新商品：如果订单是首购单，那么选新商品的时候可以选择辅销品
		if(StringUtils.isNotEmpty(orderType)){
				//如果为0：不展示   1：如果orderType为1，则展示1和5
			if("1".equals(orderType)){
				sqlCountBuf.append(" and jpstt.orderType in('1','5') ");
			}else{
				sqlCountBuf.append(" and jpstt.orderType in('");
				sqlCountBuf.append(orderType.replace(",", "','"));
				sqlCountBuf.append("') ");
			}
					
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlCountBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		
		
		//查询分页所有的数据
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' and jpsn.companyCode='CN' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//换货单数据排除美体的数据
    	sqlBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");
		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//订单类型
		//自助换货--选择新商品：如果订单是首购单，那么选新商品的时候可以选择辅销品
		if(StringUtils.isNotEmpty(orderType)){
			//如果为0：不展示   1：如果orderType为1，则展示1和5
			if("1".equals(orderType)){
				sqlBuf.append(" and jpstt.orderType in('1','5') ");
			}else{
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		
		log.info("getJpmProductSaleTeamTypeListPage-sqlCount:" + sqlCountBuf.toString());
		log.info("getJpmProductSaleTeamTypeListPage-sql:"+sqlBuf.toString());
		//return this.getSession().createQuery(sqlBuf.toString()).list();
		return this.findObjectsByHQL(sqlCountBuf.toString(), sqlBuf.toString(), page);
	}

	@Override
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeByCrm(
			GroupPage page, CommonRecord crm) {
		//查询分页的总数量
		StringBuffer sqlCountBuf = new StringBuffer("select count(jpstt.pttId) from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' and jpsn.companyCode='CN' ");
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlCountBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlCountBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//换货单数据排除美体的数据
    	sqlCountBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");
		
    	String teamCode = crm.getString("teamCode", "");
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlCountBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlCountBuf.append(teamCode.replace(",", "','"));
			sqlCountBuf.append("') ");
		}
		//订单类型
		//自助换货--选择新商品：如果订单是首购单，那么选新商品的时候可以选择辅销品
		String orderType = crm.getString("orderType", "");
		if(StringUtils.isNotEmpty(orderType)){
				//如果为0：不展示   1：如果orderType为1，则展示1和5
			if("1".equals(orderType)){
				sqlCountBuf.append(" and jpstt.orderType in('1','5') ");
			}else{
				sqlCountBuf.append(" and jpstt.orderType in('");
				sqlCountBuf.append(orderType.replace(",", "','"));
				sqlCountBuf.append("') ");
			}
					
		}
		
		//商品编号
		String productNo = crm.getString("productNo", "");
		if(StringUtils.isNotEmpty(productNo)){
			sqlCountBuf.append(" and jpsn.productNo='");
			sqlCountBuf.append(productNo.replace(",", "','"));
			sqlCountBuf.append("'");
		}
		//商品名称
		String productName = crm.getString("productName", "");
		if(StringUtils.isNotEmpty(productName)){
			sqlCountBuf.append(" and jpsn.productName like '%");
			sqlCountBuf.append(productName.replace(",", "','"));
			sqlCountBuf.append("%'");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlCountBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		
		//查询分页所有的数据
		StringBuffer sqlBuf = new StringBuffer("select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' and jpsn.companyCode='CN' ");
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		//换货单数据排除美体的数据
    	sqlBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");
		
		//团队编码
		if(StringUtils.isNotEmpty(teamCode)){
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//订单类型
		//自助换货--选择新商品：如果订单是首购单，那么选新商品的时候可以选择辅销品
		if(StringUtils.isNotEmpty(orderType)){
			//如果为0：不展示   1：如果orderType为1，则展示1和5
			if("1".equals(orderType)){
				sqlBuf.append(" and jpstt.orderType in('1','5') ");
			}else{
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}

		//商品编号
		if(StringUtils.isNotEmpty(productNo)){
			sqlBuf.append(" and jpsn.productNo='");
			sqlBuf.append(productNo.replace(",", "','"));
			sqlBuf.append("'");
		}
		//商品名称
		if(StringUtils.isNotEmpty(productName)){
			sqlBuf.append(" and jpsn.productName like '%");
			sqlBuf.append(productName.replace(",", "','"));
			sqlBuf.append("%'");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		
		log.info("getJpmProductSaleTeamTypeListPage-sqlCount:" + sqlCountBuf.toString());
		log.info("getJpmProductSaleTeamTypeListPage-sql:"+sqlBuf.toString());
		//return this.getSession().createQuery(sqlBuf.toString()).list();
		return this.findObjectsByHQL(sqlCountBuf.toString(), sqlBuf.toString(), page);	
	
	}
	
	 //异步列出是否存在跟商品类型对应的推荐商品   add by hdg 2016-09-14
	@Override
	public List<JpmProductSaleTeamType> getRecommendProductList(
							String teamType, String isRecommend, String orderType, Integer rownum) {
		
		StringBuffer sb = new StringBuffer();
		String hql = "select jpstt " + 
				     "from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn " +
					 "where jpstt.jpmProductSaleNew.uniNo = jpsn.uniNo " +
					 "and jpsn.status='1' and jpstt.state='0' and jpstt.isHidden='0' ";
		sb.append(hql);
		
		//添加日期判断 Add By WuCF 需要添加时间判断
		sb.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sb.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");
		
		if(!StringUtil.isEmpty(teamType)){
			sb.append(" and jpstt.jmiMemberTeam.code='");
			sb.append(teamType);
			sb.append("'");
		}
		if(!StringUtil.isEmpty(isRecommend)){
			sb.append(" and jpsn.isRecommend='");
			sb.append(isRecommend);
			sb.append("'");
		}
		if(!StringUtil.isEmpty(orderType)){
			sb.append(" and jpstt.orderType='");
			sb.append(orderType);
			sb.append("'");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sb.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		if(null != rownum) {
			sb.append(" and rownum <= " + rownum);
		}
		sb.append(" order by jpsn.uniNo desc");
		
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}

	@Override
	public List<JpmProductSaleTeamType> getJpmProductSaleTeamTypeListPageSorted(
			GroupPage page,String orderType, String teamCode, String sortKeyword,String sortFlag) {
		//查询分页的总数量
		StringBuffer sqlCountBuf = new StringBuffer(
				"select count(jpstt.pttId) from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' and jpsn.companyCode='CN' ");
		// 添加日期判断 Add By WuCF 需要添加时间判断
		sqlCountBuf
				.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlCountBuf
				.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");

		// 换货单数据排除美体的数据
		sqlCountBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");

		// 团队编码
		if (StringUtils.isNotEmpty(teamCode)) {
			sqlCountBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlCountBuf.append(teamCode.replace(",", "','"));
			sqlCountBuf.append("') ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlCountBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		// 订单类型
		// 自助换货--选择新商品：如果订单是首购单，那么选新商品的时候可以选择辅销品
		if (StringUtils.isNotEmpty(orderType)) {
			// 如果为0：不展示 1：如果orderType为1，则展示1和5
			if ("1".equals(orderType)) {
				sqlCountBuf.append(" and jpstt.orderType in('1','5') ");
			} else {
				sqlCountBuf.append(" and jpstt.orderType in('");
				sqlCountBuf.append(orderType.replace(",", "','"));
				sqlCountBuf.append("') ");
			}

		}

		// 查询分页所有的数据
		StringBuffer sqlBuf = new StringBuffer(
				"select jpstt from JpmProductSaleTeamType jpstt,JpmProductSaleNew jpsn where jpstt.jpmProductSaleNew.uniNo=jpsn.uniNo and jpsn.status='1' and jpstt.state='0' and jpsn.companyCode='CN' ");

		// 添加日期判断 Add By WuCF 需要添加时间判断
		sqlBuf.append(" and (jpsn.startOnSale <= sysdate or jpsn.startOnSale is null) ");
		sqlBuf.append(" and (jpsn.endOnSale > sysdate-1 or jpsn.endOnSale is null) ");

		// 换货单数据排除美体的数据
		sqlBuf.append(" and jpsn.jpmProduct.productNo not like 'PLC%'");

		// 团队编码
		if (StringUtils.isNotEmpty(teamCode)) {
			sqlBuf.append(" and jpstt.jmiMemberTeam.code in('");
			sqlBuf.append(teamCode.replace(",", "','"));
			sqlBuf.append("') ");
		}
		//Modify By WuCF 20170207 首单订单类型不展示辅销品
		if("1".equals(orderType)){
			sqlBuf.append(" and jpsn.jpmProduct.smNo!='A' ");
		}
		// 订单类型
		// 自助换货--选择新商品：如果订单是首购单，那么选新商品的时候可以选择辅销品
		if (StringUtils.isNotEmpty(orderType)) {
			// 如果为0：不展示 1：如果orderType为1，则展示1和5
			if ("1".equals(orderType)) {
				sqlBuf.append(" and jpstt.orderType in('1','5') ");
			} else {
				sqlBuf.append(" and jpstt.orderType in('");
				sqlBuf.append(orderType.replace(",", "','"));
				sqlBuf.append("') ");
			}
		}
		
		//按不同的关键字排序
		if(StringUtils.isNotEmpty(sortKeyword)){
			//
			if("productNo".equalsIgnoreCase(sortKeyword)){	//按商品编号排序
				sqlBuf.append(" order by jpstt.jpmProductSaleNew.jpmProduct.productNo ");
			}else if("productName".equalsIgnoreCase(sortKeyword)){	//按商品名称排序
				//sqlBuf.append(" order by jpstt.jpmProductSaleNew.productName ");
				
				//商品名称按照首字母先后顺序排序
				sqlBuf.append(" order by NLSSORT(jpstt.jpmProductSaleNew.productName,'NLS_SORT=SCHINESE_PINYIN_M') ");
			}else if("price".equalsIgnoreCase(sortKeyword)){	//按价格排序
				sqlBuf.append(" order by jpstt.price ");
			}else if("pv".equalsIgnoreCase(sortKeyword)){		//按PV排序
				sqlBuf.append(" order by jpstt.pv ");
			}
		}
		
		//升序和降序
		if(StringUtils.isNotEmpty(sortFlag)){
			switch (Integer.parseInt(sortFlag)) {	//"0"代表升序，"1"代表降序
				case 0:
					sqlBuf.append(" asc ");
					break;
				case 1:
					sqlBuf.append(" desc ");
					break;
				default:
					break;
			}
		}

		log.info("getJpmProductSaleTeamTypeListPage-sqlCount:"
				+ sqlCountBuf.toString());
		log.info("getJpmProductSaleTeamTypeListPage-sql:" + sqlBuf.toString());
		
		//查询经过关键字升序和降序排序后的结果集
		//return this.getSession().createQuery(sqlBuf.toString()).list();
		//查询经过关键字升序和降序排序后，并有分页的结果集
		return this.findObjectsByHQL(sqlCountBuf.toString(), sqlBuf.toString(),page);
				
	}
}
