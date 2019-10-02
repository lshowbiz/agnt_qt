package com.joymain.ng.handle.shipping;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;


/**
 * 中国区物流费
 */
@Component
public class ChinaShippingImpl extends ShippingMoneyFactory {
	
	private final Log log = LogFactory.getLog(ChinaShippingImpl.class);
	/**
	 * 升级单&首购单
	 * <li>小于6000根据收货地址获取物流费</li>
	 * <li>大于6000不收取</li>
	 * @param amount
	 * @param provinceName
	 * @return if amount < flag return true
	 */
	public boolean upOrderShipping(BigDecimal amount,String provinceName){
		BigDecimal mon = new BigDecimal(shipMonFlag);
		log.info("amount is:"+amount +" and shipping Flag is:"+mon);
		if(amount.compareTo(mon)<1){
			//return shippingAddress(provinceName);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 辅消品
	 * <li>大于500不收物流费</li>
	 * <li>小于500按地址收取物流费</li>
	 * @param amount
	 * @param provinceName
	 * @return if amount < flag return true
	 */
	public boolean aidOrderShipping(BigDecimal amount,String provinceName){
		BigDecimal flag = new BigDecimal(aidMonFlag);
		if(amount.compareTo(flag)<1){
			//return shippingAddress(provinceName);
			return true;
		} else{
			return false;
		}
	}
	/**
	 * 续约单
	 * <li>店铺根据物流公司收取物流费</li>
	 * @return BigDecimal
	 */
	public BigDecimal continuedOrderShipping(){
		return null;
	}
	/**
	 * 重消单
	 * <li>团队:青岛,田阳1-7 按地址获取物流费</li>
	 * <li>其他团队和会员,店铺根据物流公司,出物流费</li>
	 * @param memerCode
	 * @param provinceName
	 * @return BigDecimal
	 */
	public BigDecimal reOrderShipping(String memerCode, String provinceName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
