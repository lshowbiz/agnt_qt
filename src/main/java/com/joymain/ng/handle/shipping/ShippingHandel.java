package com.joymain.ng.handle.shipping;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoShippingFee;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PdShipFee;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpoShippingFeeManager;
import com.joymain.ng.service.PdShipFeeManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.LocaleUtil;

@Service("shippingHandel")
public class ShippingHandel {
	
	private final Log log = LogFactory.getLog(ShippingHandel.class);
	@Autowired
	private ChinaShippingImpl chinaShippingImpl;
	@Autowired
	private JpoShippingFeeManager jpoShippingFeeManager;
	@Autowired
	private PdShipFeeManager pdShipFeeManager;
	@Autowired
	private JmiTeamManager jmiTeamManager;
	public Set<JpoMemberOrderFee> orderFeeSet;
	
	public synchronized BigDecimal getShippingSum(
			HttpServletRequest request,JpoMemberOrder order,JsysUser user,Set<JpoMemberOrderList> orderSet ) throws Exception{
		
		BigDecimal $sp = new BigDecimal(0);
		try{
			String companyCode = user.getCompanyCode();
			String orderType = order.getOrderType();
			String province = order.getProvince();
			int type = Integer.parseInt(orderType);
			boolean isPro=false;
			
			@SuppressWarnings("rawtypes")
			//Set<JpoMemberOrderList> orderSet = order.getJpoMemberOrderList();
			BigDecimal sumMon = new BigDecimal(0),pv = new BigDecimal(0),amount = new BigDecimal(0);
			BigDecimal sumTax = new BigDecimal(0); 
			
			
			log.info("companyCode is:["+companyCode+"] and order type:["+orderType+"] " +
					"and orderList size is:"+orderSet.size()+"] " +
							"and orderNo is:"+order.getMemberOrderNo()+"]");
			
					for(JpoMemberOrderList pro :orderSet){
						if(log.isDebugEnabled()){
							log.debug("productSaleNew object is :"+pro.
									getJpmProductSaleTeamType().getJpmProductSaleNew());
						}
						
						String proCode = pro.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
						String usTax = order.getProvince()+proCode;
						
						
						if(log.isDebugEnabled()){
							log.debug("productNO is:["+proCode+"] and US tax is:["+usTax+"] " +
									"and pvFlag is:["+chinaShippingImpl.pvFlag+"] " +
									"and shipMonFlag is:["+chinaShippingImpl.shipMonFlag+"]");
						}
						sumMon = sumMon.add(chinaShippingImpl.usSumShipping(proCode, pro.getPrice(), pro.getQty()));
						sumTax = sumTax.add(chinaShippingImpl.usSumTax(usTax, pro.getPrice(),  pro.getQty()));
						pv = pv.add(pro.getPv().multiply(new BigDecimal(pro.getQty())));
						amount = amount.add(pro.getPrice().multiply(new BigDecimal(pro.getQty())));
						
					}
				
					log.info("orderNo is:["+order.getMemberOrderNo()+"] " +
							"and sumPv is:"+pv+" and amount is:"+amount );
					
				
				PdShipFee shipFee = new PdShipFee();	//省份的
				String shippingType = "";
				
				if(type == 3 || type == 4 || type == 9 || type == 14 || type == 40){
					shippingType = "MVZ"; //按照物流费策略计费
				}else if(type == 1 || type == 5 || type == 2){
					shippingType = "Province"; //按省份计物流费
				}else{
					shippingType = "free";  //免费
				}
				
				JpoMemberOrderFee orderFee = new JpoMemberOrderFee();
				orderFeeSet = new HashSet<JpoMemberOrderFee>();
				if(companyCode.equalsIgnoreCase("US")){
					$sp = sumMon.add(sumTax);
					
					JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
		    		jpoMemberOrderFee.setDetailType("UPS");
		    		jpoMemberOrderFee.setFee($sp);
		    		jpoMemberOrderFee.setFeeType("1");//物流费
		    		jpoMemberOrderFee.setJpoMemberOrder(order);
		    		orderFeeSet.add(jpoMemberOrderFee);
		    		
		    		JpoMemberOrderFee taxOrderFee = new JpoMemberOrderFee();
		    		taxOrderFee.setDetailType("TAX");
		    		taxOrderFee.setFee($sp);
		    		taxOrderFee.setFeeType("3");//税
		    		taxOrderFee.setJpoMemberOrder(order);
		    		orderFeeSet.add(taxOrderFee);
		    		
				}else if(companyCode.equalsIgnoreCase("TW")){
					$sp = chinaShippingImpl.twShipping(pv);
				} else {//中国区物流费
					switch (type) {
						case 1://首购单 自己 按省份
							//2014-05-28 00:00:00
							String str_startDate = LocaleUtil.getLocalText("zh_CN", "start.date");
							//2014-09-26 23:59:59
							String str_endDate = LocaleUtil.getLocalText("zh_CN", "end.date");
							
							if(amount.compareTo(new BigDecimal(0))>0){
								if(chinaShippingImpl.upOrderShipping(amount, province)){
									shipFee = pdShipFeeManager.getPdShipFeeByProvince(province);
									if(shipFee !=null && shipFee.getPsfId()!=null){
										$sp = shipFee.getFee();
									}else{
										log.info("物流省份收费标准未设置。");
										return new BigDecimal(-1);
									}
								}
									
							} else {
								if(!compDate(str_startDate,str_endDate)){
									if(chinaShippingImpl.upOrderShipping(amount, province))
										$sp = shipFee.getFee();
								}
							}
							
							order.setConsumerAmount(new BigDecimal(0));
							orderFee.setFee($sp);
							break;
							
						case 2://升级单 自己 按省份 
							if(chinaShippingImpl.upOrderShipping(amount, province)){
								shipFee = pdShipFeeManager.getPdShipFeeByProvince(province);
								if(shipFee !=null && shipFee.getPsfId()!=null){
									$sp = shipFee.getFee();
								}else{
									log.info("物流省份收费标准未设置。");
									return new BigDecimal(-1);
								}
							}
							order.setConsumerAmount(new BigDecimal(0));
							orderFee.setFee($sp);
							break;
						case 3://续约单 店铺出  大田，宅急送
							
							//新策略
							 $sp = $sp(order,orderSet);
							 order.setConsumerAmount($sp);
							 orderFee.setFee(new BigDecimal(0));
							break;
							
						case 4://重消单  店铺出  大田，宅急送
								
								//新策略
								$sp = $sp(order,orderSet);
								order.setConsumerAmount($sp);
								orderFee.setFee(new BigDecimal(0));
							break;
							
						case 40://全年重消单  店铺出  
							
								//新策略
								$sp = $sp(order,orderSet);
								order.setConsumerAmount($sp);
								orderFee.setFee(new BigDecimal(0));
							break;
							
						case 5://辅消品  自己 按省份
							if(chinaShippingImpl.aidOrderShipping(amount, province)){
								shipFee = pdShipFeeManager.getPdShipFeeByProvince(province);
								if(shipFee !=null && shipFee.getPsfId()!=null){
									$sp = shipFee.getFee();
								}else{
									log.info("物流省份收费标准未设置。");
									return new BigDecimal(-1);
								}
							}
							order.setConsumerAmount(new BigDecimal(0));
							orderFee.setFee($sp);
							break;
							
						case 9://一级店铺重消单根据物流公司收取物流费 收取店铺的  大田，宅急送
							
							 //新策略
							 $sp = $sp(order,orderSet);
							 order.setConsumerAmount($sp);
							 orderFee.setFee(new BigDecimal(0));
							break;
							
						case 14://二级店铺重消单根据物流公司收取物流费  收取店铺的  大田，宅急送

							 //新策略
							 $sp = $sp(order,orderSet);
							 order.setConsumerAmount($sp);
							 orderFee.setFee(new BigDecimal(0));
							break;
					   default://一级店铺首购，二级店铺首购升级都不收取物流费
					         order.setConsumerAmount(new BigDecimal(0));
					         orderFee.setFee(new BigDecimal(0));
					}
					
					orderFee.setDetailType(shippingType);
					orderFee.setFeeType("1");
					orderFee.setJpoMemberOrder(order);
		    		orderFeeSet.add(orderFee);
				}	
		}catch(Exception e){
			log.error("get shipping money error.",e);
			throw new AppException(e.getMessage());
		}
		log.info("shipping money is:"+$sp);
		return $sp;
	}
	
	private BigDecimal $sp(JpoMemberOrder order,Set<JpoMemberOrderList> orderSet) throws Exception{

		BigDecimal $sp = new BigDecimal(0);
		JpoShippingFee sfW = new JpoShippingFee();//按策略
		JpoShippingFee sfV = new JpoShippingFee();
		JpoShippingFee sfWZ = new JpoShippingFee();
		
		String district = order.getDistrict();
		String cityID = order.getCity();
		String prov = order.getProvince();
		String countryCode = order.getCountryCode();
		
		BigDecimal sumWeight = new BigDecimal(0);
		BigDecimal sumVol = new BigDecimal(0);
		BigDecimal sumWeightZ = new BigDecimal(0);
		
		//新策略
		for(JpoMemberOrderList pro :orderSet){
			
			JpmProductSaleNew product = pro.getJpmProductSaleTeamType().getJpmProductSaleNew();
			
			BigDecimal j_weigth = product.getWeight().multiply(new BigDecimal(pro.getQty()));
			BigDecimal j_vol = product.getVolume().multiply(new BigDecimal(pro.getQty()));
			BigDecimal weight=new BigDecimal(0);
			BigDecimal vol = new BigDecimal(0);
			
			log.info("j_weigth==["+j_weigth+"] and j_vol =["+j_vol+"]");
			
			if(j_weigth ==null && "".equals(j_weigth)){
				throw new AppException("商品重量未设置。");
			}else{
				weight = j_weigth;
			}
			
			if(j_vol ==null && "".equals(j_vol)){
				throw new AppException("商品体积未设置。");
			}else{
				vol = j_vol;
			}
			
			String shippingType = product.getLogisticsStrategy();
			
			if("".equals(shippingType) || shippingType == null){
    			log.info("所购商品没有指定物流费策略。");
//    			throw new AppException("所购商品没有指定物流费策略。");
    			return new BigDecimal(-1);
    		}else if(shippingType.equalsIgnoreCase("w")){
				sumWeight = sumWeight.add(weight);
			}else if(shippingType.equalsIgnoreCase("v")){
				sumVol = sumVol.add(vol);
			}else if(shippingType.equalsIgnoreCase("Z")){
				sumWeightZ = sumWeightZ.add(weight);
			}
		}

		if(sumWeight.compareTo(new BigDecimal(0))==1){
			sfW = jpoShippingFeeManager.getJpoShippingFee("W", prov, cityID, district, countryCode);
			if( null == sfW){
				return new BigDecimal(-1);
//				throw new AppException("物流费w未设置。");
				
			}else{
				$sp =  $sp.add(jpoShippingFeeManager.getFee(sfW,sumWeight));
			}
			
		}
		if(sumVol.compareTo(new BigDecimal(0))==1){
			sfV = jpoShippingFeeManager.getJpoShippingFee("V", prov, cityID, district, countryCode);
			if(null == sfV){
				return new BigDecimal(-1);				
//				throw new AppException("物流费v未设置。");
			}else{
				$sp =  $sp.add(jpoShippingFeeManager.getFeeV(sfV,sumVol)); 
			}
		}
		if(sumWeightZ.compareTo(new BigDecimal(0))==1){
			sfWZ = jpoShippingFeeManager.getJpoShippingFee("Z", prov, cityID, district, countryCode);
			if( null == sfWZ){
				return new BigDecimal(-1);
//				throw new AppException("物流费z未设置。");
				
			}else{
				$sp =  $sp.add(jpoShippingFeeManager.getFeeWZ(sfWZ,sumWeightZ));
			}
			
		}
		
		return $sp;
	}
	
	
	/**
	 * 在这个时间段内返回true
	 * @param str_startDate
	 * @param str_endDate
	 * @return true or false
	 */
	private boolean compDate(String str_startDate,String str_endDate){
		try{
			
	        log.info("str_startDate is["+str_startDate+"] " +
					"str_endDate is:"+str_endDate);
			
			Date starDate = DateUtil.convertStringToDate(
					"yyyy-MM-dd hh:mm:ss", str_startDate);
			Date endDate = DateUtil.convertStringToDate(
					"yyyy-MM-dd hh:mm:ss", str_endDate);
			
			Date curDate = Calendar.getInstance().getTime(); 
			
			log.info("starDate is["+starDate+"] " +
					" and endDate is:["+endDate+"] " +
					" and curDate is:["+curDate+"]");
			
			if(curDate.after(starDate) && curDate.before(endDate))
				return true;
			
		}catch(Exception e){
			throw new AppException(e);
		}
		return false;
	}
	
	
	public ChinaShippingImpl getChinaShippingImpl() {
		return chinaShippingImpl;
	}

	public void setChinaShippingImpl(ChinaShippingImpl chinaShippingImpl) {
		this.chinaShippingImpl = chinaShippingImpl;
	}
	
	public Set<JpoMemberOrderFee> getOrderFeeSet() {
		return orderFeeSet;
	}
	
}
