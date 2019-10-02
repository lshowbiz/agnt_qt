package com.joymain.ng.util.pay;

import java.math.BigDecimal;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;

import com.joymain.ng.model.JfiPayOrderCompany;

public class PayUtils {
	/**
	 * 获取备注信息
	 * 
	 * @param company
	 * @param dataType
	 *            支付平台1:pc,2:手机
	 * @param payFee
	 *            手续费
	 * @param zmType
	 *            用于判断为那家支付公司
	 * @return
	 */
	public static String getRemarkBean(JfiPayOrderCompany company, BigDecimal payFee, String zmType) {
		RemarkBean entity = new RemarkBean();
		JSONArray json = null;
		try {
			BeanUtils.copyProperties(entity, company);
			entity.setPayFee(payFee);
			entity.setZmType(zmType);
			json = JSONArray.fromObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	public static String getRemarkBean(JfiPayOrderCompany company, BigDecimal payFee, String zmType, String merchantId) {
		RemarkBean entity = new RemarkBean();
		JSONArray json = null; 
		try {
			BeanUtils.copyProperties(entity, company);
			entity.setPayFee(payFee);
			entity.setZmType(zmType);
			entity.setMerchantId(merchantId);
			json = JSONArray.fromObject(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	public static RemarkBean getRemarkBean2(JfiPayOrderCompany company, BigDecimal payFee, String zmType, String merchantId) {
		RemarkBean entity = new RemarkBean();
		try {
			BeanUtils.copyProperties(entity, company);
			entity.setPayFee(payFee);
			entity.setZmType(zmType);
			entity.setMerchantId(merchantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity; 
	}
	
	public static void main(String[] args) {
		JfiPayOrderCompany entity = new JfiPayOrderCompany();
		entity.setUserCode("sadfds");
		entity.setDataType("000222");
		System.out.println(PayUtils.getRemarkBean(entity, new BigDecimal("0.00"), "yspay"));
		;
	}
}
