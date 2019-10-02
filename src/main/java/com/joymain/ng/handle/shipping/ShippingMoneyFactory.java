package com.joymain.ng.handle.shipping;

import java.math.BigDecimal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.JpoShippingFee;

public abstract class ShippingMoneyFactory {
	
	private final Log log = LogFactory.getLog(ShippingMoneyFactory.class);
//	private PdShipFeeDao shipFeeDao;

	/**
	 * 需要收取物流费临界值
	 */
	protected int shipMonFlag=6000;
	/**
	 * 辅消品收取物流费临界值
	 */
	protected int aidMonFlag=500; 
	/**
	 * TW PV 临界值
	 */
	protected int pvFlag=70;
	/**
	 * TW 物流费
	 */
	protected double twShipping=150;
	
	/**
	 * 按收货地址获取物流费
	 * @param provinceName
	 * @return BigDecimal
	 */
	protected BigDecimal shippingAddress(String provinceName){
//		HashMap<String, BigDecimal> feeMap = shipFeeDao.getFeeMap();
		log.info("province code is:"+provinceName);
//		BigDecimal fee = feeMap.get(provinceName) != null ? feeMap.get(provinceName) : new BigDecimal(0);
		return null;
	}
	/**
	 * 按物流公司获取物流费
	 * <li>dtProFlag:1是大田0是宅急送 </li>
	 * @param sumWeight
	 * @param sumVolume
	 * @param JpoShippingFee
	 * @param dtProFlag
	 * @return BigDecimal
	*/
	protected BigDecimal shippingCompany(BigDecimal sumWeight,BigDecimal sumVolume,JpoShippingFee jpoShippingFee,int dtProFlag){
		BigDecimal sumPrice = new BigDecimal(0);
		BigDecimal fee = jpoShippingFee.getShippingFee();
		if(dtProFlag==1){
    		sumPrice = sumVolume.multiply(fee);//大田物流公司
    	}else {
    		sumPrice = zjsCompany(sumWeight, jpoShippingFee);//宅急送物流
        }
		return sumPrice;
	} 
	/**
	 * 宅急送物流公司物流费
	 * @param weight
	 * @param jpoShippingFee
	 * @return BigDecimal
	 */
	protected BigDecimal zjsCompany(BigDecimal weight,JpoShippingFee jpoShippingFee){
		BigDecimal sumPrice = new BigDecimal(0);
		BigDecimal fee = jpoShippingFee.getShippingFee();
		
		//某个产品不收物流费,订单中必须只有此商品.
		if(weight.compareTo(new BigDecimal(0)) == 0 ) return sumPrice;
		
		if(weight.compareTo(new BigDecimal(1)) > 0 ){
			BigDecimal sumSubWeight = weight.subtract(
					new BigDecimal(1)).setScale(0, BigDecimal.ROUND_UP);	
			sumPrice = sumPrice.add(fee);
			if(log.isDebugEnabled()){
				log.debug("fee is:["+fee+"] and sumPrice=["+sumPrice+"] " +
					"and sumSubWeight=["+sumSubWeight+"]" +
					"and ShippingRefee=["+jpoShippingFee.getShippingRefee()+"] " +
					"and >>>"+sumSubWeight.multiply(jpoShippingFee.getShippingRefee()));
			}
			sumPrice = sumPrice.add(sumSubWeight.multiply(jpoShippingFee.getShippingRefee()));
			log.info("price ="+sumPrice);
		}else {
			sumPrice = sumPrice.add(fee);
		}
		return sumPrice;
	}
	/**
	 * 美国区计算税费
	 * <li>taxCode 类似 20008144P01010100101EN0</li>
	 * @param taxCode
	 * @param price
	 * @param qty
	 * @return BigDecimal
	 */
	public BigDecimal usSumTax(String taxCode,BigDecimal price,int qty){
		BigDecimal sumTax = new BigDecimal(0);
		log.info("price is:"+price+" and qty is:"+qty);
		BigDecimal amount = price.multiply(new BigDecimal(qty));
		BigDecimal usTax = new BigDecimal(0);
		
		if(GlobalVar.taxMap.containsKey(taxCode))
			usTax = GlobalVar.taxMap.get(taxCode);
			
		sumTax = sumTax.add(amount.multiply(usTax));
		log.info("US tax is:["+usTax+"] and sum Tax is:"+sumTax);
		return sumTax;
	}
	/**
	 * 美国区计算总金额
	 * @param proCode
	 * @param price
	 * @param qty
	 * @return BigDecimal
	 */
	public BigDecimal usSumShipping(String proCode,BigDecimal price,int qty){
		BigDecimal sumShipping = new BigDecimal(0);
		if("P01010100101EN0".equals(proCode)){
			if(qty<6){
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.06")));
			}else if(qty<11){
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.05")));
			}else{
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.045")));
			}
		}
		if("P05010300101EN0".equals(proCode)){
			if(qty<6){
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.06")));
			}else if(qty<11){
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.05")));
			}else{
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.045")));
			}
		}
		if("P06010100101EN0".equals(proCode)){
			if(qty<6){
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.05")));
			}else if(qty<11){
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.045")));
			}else{
				sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.0425")));
			}
		}
		sumShipping = sumShipping.add(price.multiply(new BigDecimal(qty)).multiply(new BigDecimal("0.045")));
		return sumShipping;
	}
	/**
	 * TW 物流费
	 * <li>小于70pv收取150物流费</li>
	 * @param pv
	 * @return BigDecimal
	 */
	public BigDecimal twShipping(BigDecimal pv){
		if(pv.intValue() < pvFlag)
			return new BigDecimal(twShipping);
		else
			return new BigDecimal(0);
	}
	
	/**
	 * 升级单&首购单
	 * <li>小于6000根据收货地址获取物流费</li>
	 * <li>大于6000不收取</li>
	 * @param amount
	 * @param provinceName
	 * @return BigDecimal
	 */
	public abstract boolean upOrderShipping(BigDecimal amount,String provinceName);
	/**
	 * 重消单
	 * <li>团队:青岛,田阳1-7 按地址获取物流费</li>
	 * <li>其他团队和会员,店铺根据物流公司,出物流费</li>
	 * @param memerCode
	 * @param provinceName
	 * @return BigDecimal
	 */
	public abstract BigDecimal reOrderShipping(String memerCode,String provinceName);
	/**
	 * 辅消品
	 * <li>大于500不收物流费</li>
	 * <li>小于500按地址收取物流费</li>
	 * @param amount
	 * @param provinceName
	 * @return BigDecimal
	 */
	public abstract boolean aidOrderShipping(BigDecimal amount,String provinceName);
	/**
	 * 续约单
	 * <li>店铺根据物流公司收取物流费</li>
	 * @return BigDecimal
	 */
	public abstract BigDecimal continuedOrderShipping();
	
//	public PdShipFeeDao getShipFeeDao() {
//		return shipFeeDao;
//	}
//	public void setShipFeeDao(PdShipFeeDao shipFeeDao) {
//		this.shipFeeDao = shipFeeDao;
//	}

	public int getShipMonFlag() {
		return shipMonFlag;
	}
	public void setShipMonFlag(int shipMonFlag) {
		this.shipMonFlag = shipMonFlag;
	}
	public int getAidMonFlag() {
		return aidMonFlag;
	}
	public void setAidMonFlag(int aidMonFlag) {
		this.aidMonFlag = aidMonFlag;
	}
	public int getPvFlag() {
		return pvFlag;
	}
	public void setPvFlag(int pvFlag) {
		this.pvFlag = pvFlag;
	}
	public double getTwShipping() {
		return twShipping;
	}
	public void setTwShipping(double twShipping) {
		this.twShipping = twShipping;
	}
	
}
