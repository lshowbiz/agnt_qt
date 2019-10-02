package com.joymain.ng.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.MsgSendService;
import utils.Constants;

import com.joymain.ng.dao.MailStatusDao;
import com.joymain.ng.log.util.LogUtil;
import com.joymain.ng.model.Items;
import com.joymain.ng.model.MailStatus;
import com.joymain.ng.model.PdLogisticsBaseDetail;
import com.joymain.ng.model.PdLogisticsBaseNum;
import com.joymain.ng.service.JpmProductCombinationManager;
import com.joymain.ng.service.MailStatusManager;
import com.joymain.ng.service.PdLogisticsBaseDetailManager;
import com.joymain.ng.service.PdLogisticsBaseNumManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;


@Service("mailStatusManager")
@WebService(serviceName = "MailStatusService", endpointInterface = "com.joymain.ng.service.MailStatusManager")
public class MailStatusManagerImpl extends GenericManagerImpl<MailStatus, Long> implements MailStatusManager {
	
	
    MailStatusDao mailStatusDao;
    @Autowired
	public PdLogisticsBaseNumManager pdLogisticsBaseNumManager;
    
    @Autowired
	public PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager;
    
    @Autowired
    public JpmProductCombinationManager jpmProductCombinationManager;
    
    @Autowired
    public MailStatusManagerImpl(MailStatusDao mailStatusDao) {
        super(mailStatusDao);
        this.mailStatusDao = mailStatusDao;
    }
	
	public Pager<MailStatus> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return mailStatusDao.getPager(MailStatus.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * @author gw 2014-11-24-----------------------------------------需求变更后，这个方法弃用20150806
	 * 物流跟踪查询-保存最后一次的物流查询信息
	 */
	public boolean saveMailStatus(MailStatus mailStatus) {
		return mailStatusDao.saveMailStatus(mailStatus);
	}
	
	/**
	 * 根据物流跟踪单号查询物流物流的实时信息----------------------------有用方法20150806
	 * @author gw  物流跟踪查询-接口准备数据  2014-12-08
	 * @param  mailNoAndproductNo 物流单号和商品编号拼接的字符串   modify gw 2015-01-05 
	 * 
	 */
	public List<Items> getInterfaceByMailStatus(String mailNoAndproductNo){
		//jpo_member_order_list.TRACKING_SINGLE is '物流跟踪单号(这里面寸的是pd_logistics_base_num的主键)';
		log.info("JOCS前台根据物流单号获取物流信息(在类MailStatusManagerImpl的方法中getInterfaceByMailStatus运行)");
		 try{
			    String stringEvent = this.getInterfaceByProductNo(mailNoAndproductNo);
			    if(StringUtil.isEmpty(stringEvent)){
			    	return null;
			    }
			    String[] a = mailNoAndproductNo.split("--");
		    	String mailNo = a[0];
		    	MailStatus mailStatusTotal = new MailStatus();
		    	List<Items> itemsListTotal = new ArrayList();
		    	Items itemsTotal = new Items();
		    	itemsTotal.setRemark(stringEvent);
		    	itemsListTotal.add(itemsTotal);
		    	
				if(StringUtil.isEmpty(mailNo)){
					mailStatusTotal.setItems(itemsListTotal);
					return mailStatusTotal.getItems();
				}else{
					PdLogisticsBaseNum pdLogisticsBaseNum = pdLogisticsBaseNumManager.getPdLogisticsBaseNumForMailNo(mailNo);

					//物流实时信息优化后的方法   modify gw 2015-06-17
					MailStatus mailStatusDBA = mailStatusDao.getMailStatusByMailNo(mailNo,pdLogisticsBaseNum);
					if(null!=mailStatusDBA){
						if(null!=mailStatusDBA.getItems()){
							List<Items> itemsList = mailStatusDBA.getItems();
							for(int i=0;i<itemsList.size();i++){
								itemsListTotal.add(itemsList.get(i));
							}
							log.info("在类MailStatusManagerImpl的方法中getInterfaceByMailStatus运行,给集合赋值");
							return itemsListTotal;
						}
					}
					mailStatusTotal.setItems(itemsListTotal);
					return mailStatusTotal.getItems();
				}
		 }catch(Exception e){
			 log.info("根据物流单号获取物流实现信息失败-------"+e.toString());
			 e.printStackTrace();
			 return null;
		 }
	}

	/**
	 * 根据商品编号查询物流信息中的套餐子商品----------------------------有用方法20150806
	 * @author gw  物流跟踪查询-接口准备数据  2015-05-05
	 * @param  mailNoAndproductNo 物流单号和商品编号拼接的字符串
	 * @return 
	 */
	public String getInterfaceByProductNo(String mailNoAndproductNo) {
		log.info("JOCS前台获取已发货信息(在类MailStatusManagerImpl的方法中getInterfaceByProductNo运行)");
		try{
			    String result = "";
			    String name = "";
			    if(!StringUtil.isEmpty(mailNoAndproductNo)){
				    	String[] a = mailNoAndproductNo.split("--");
				    	String mailNo = a[0];
				        String productNo = a[1];
						if((!StringUtil.isEmpty(mailNo))&&(!StringUtil.isEmpty(productNo))){
							//modify by fu 2016-05-09 物流实时信息查询因物流单号和商品对应的商品对应不上，所以就不查物流单号对应哪些商品了----begin
							//判断商品是不是套餐商品  
							/*boolean isCombinationProduct = jpmProductCombinationManager.getIsisCombinationProduct(productNo);
							PdLogisticsBaseNum pdLogisticsBaseNum = pdLogisticsBaseNumManager.getPdLogisticsBaseNumForMailNo(mailNo);
							if(isCombinationProduct){
								if(null==pdLogisticsBaseNum){
									return null;
								}
								name = pdLogisticsBaseNum.getName();
								//查询套餐商品在这个物流跟踪单号下的子商品
								Long numId = pdLogisticsBaseNum.getNumId();
								List<PdLogisticsBaseDetail> listpdLogisticsBaseDetail = pdLogisticsBaseDetailManager.getlistpdLogisticsBaseDetail(numId,productNo);
								if(null!=listpdLogisticsBaseDetail){
									for(int i=0;i<listpdLogisticsBaseDetail.size();i++){
										String productName = pdLogisticsBaseDetailManager.getProductName(listpdLogisticsBaseDetail.get(i).getProduct_no());
										String qty = listpdLogisticsBaseDetail.get(i).getQty();
										result +=  productName+"×"+qty+",";
									}
								}
							}else{
								if(null==pdLogisticsBaseNum){
									return null;
								}
								name = pdLogisticsBaseNum.getName();
							}*/
							//modify by fu 2016-05-09 物流实时信息查询因物流单号和商品对应的商品对应不上，所以就不查物流单号对应哪些商品了----end

							//modify by fu 2016-05-09-------------优化后的物流实时信息----begin
							PdLogisticsBaseNum pdLogisticsBaseNum = pdLogisticsBaseNumManager.getPdLogisticsBaseNumForMailNo(mailNo);
							name = pdLogisticsBaseNum.getName();
							//modify by fu 2016-05-09-------------优化后的物流实时信息----end						
							
							if(!StringUtil.isEmpty(result)){
								result = result.substring(0, result.length()-1);
								result = "物流公司："+name+"</br>已发货商品：</br>"+result;
							}else{
							   result = "物流公司:"+name;
							}
							return result;
						}
			    }
		}catch(Exception e){
		    log.info("套餐商品物流信息前台展示：查询套餐商品的子商品失败"+e.toString());
            e.printStackTrace();
		}
		return null;
	}
	
}