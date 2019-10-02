package com.joymain.ng.util.channel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.StringUtil;

/**
 * 盛付通实现类
 * @author Administrator
 *
 */
@Service("channelUtil")
@WebService(serviceName = "ChannelUtilService", endpointInterface = "com.joymain.ng.util.channel.ChannelUtil")
public class ChannelUtilImpl implements ChannelUtil {
	
	@Autowired
    private JsysIdManager sysIdManager;//生成ID号
	
	@Autowired
	private JsysUserManager sysUserManager = null;
	
	//盛付通支付请求地址
	public static final String post_url = "https://mas.shengpay.com/web-acquire-channel/cashier.htm";
	
	//服务端通知地址
	public static final String notify_url = "http://59.188.183.199:8083/jocs_ht/fiPayChannelReceive.html";
	
	//支付完成后跳转页面
	public static final String page_url = "59.188.183.199:8081/jocs/jpoMemberOrders/orderAll";
	
	/**
	 * 根据会员编号获取收款快钱账户对象
	 * @param userCode 会员编号
	 * @return 快钱商户号
	 */
	public String getFiBillAccountByUserCode(String userCode){
		
		
		return "";
	}

	/**
	 * 生成Bill99对像
	 * flag(0:为电子存折  1:订单,2:公益基金 )
	 */
	public ChannelBean getChannelBean(ChannelBean channel, int flag){
		
		channel.setPostUrl(post_url);
		channel.setName("B2CPayment");
		channel.setVersion("V4.1.1.1.1");
		channel.setCharset("UTF-8");
		channel.setMsgSender(getFiBillAccountByUserCode(channel.getExt1()));//根据会员编号获取商户号
		
		if(flag==0){
			channel.setOrderNo(sysIdManager.buildIdStr("advicecode_cn"));//充值订单号
			channel.setProductName("充值单");
		}
		channel.setOrderTime(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()));
		channel.setPayType("PT001");
		channel.setPageUrl(page_url);
		channel.setNotifyUrl(notify_url);
		
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();//获得本机IP
			
			channel.setBuyerIp(ip);//防钓鱼用,买家的ip地址
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();//获得本机IP
			
			channel.setBuyerIp(ip);//防钓鱼用,买家的ip地址
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		JsysUser sysUser = sysUserManager.get(channel.getExt1());
		channel.setBuyerContact(sysUser.getUserName());//联系方式
		channel.setExt1(channel.getExt1() + "," + String.valueOf(flag));
		channel.setSignType("MD5");
		channel.setSignMsg(getSignMsg(channel));
		
		return channel;
	}
	
public String getSignMsg(ChannelBean channel){
		
		String md5Key = "shengfutongSHENGFUTONGtest";//测试MD5密钥：shengfutongSHENGFUTONGtest
		
		String origin = channel.getName()+channel.getVersion()+channel.getCharset()+channel.getMsgSender();
			
		if(!StringUtil.isEmpty(channel.getSendTime())){
			origin += channel.getSendTime();
		}
		
		origin += channel.getOrderNo()+channel.getOrderAmount()+channel.getOrderTime()+channel.getPayType();
		
		if(!StringUtil.isEmpty(channel.getPayChannel())){
			origin += channel.getPayChannel();
		}
		
		if(!StringUtil.isEmpty(channel.getInstCode())){
			origin += channel.getInstCode();
		}
		
		origin += channel.getPageUrl();
		
		if(!StringUtil.isEmpty(channel.getBackUrl())){
			origin += channel.getBackUrl();
		}
		
		origin += channel.getNotifyUrl();
		
		if(!StringUtil.isEmpty(channel.getProductName())){
			origin += channel.getProductName();
		}
		
		if(!StringUtil.isEmpty(channel.getBuyerContact())){
			origin += channel.getBuyerContact();
		}
		
		origin += channel.getBuyerIp()+channel.getExt1()+channel.getSignType();
		
		
		return Md5.Md5(origin+md5Key).toUpperCase();
	}
}
