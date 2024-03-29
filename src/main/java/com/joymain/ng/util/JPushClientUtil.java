package com.joymain.ng.util;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.IOSExtra;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

public class JPushClientUtil {

	private static final String appKey ="2db9727e543c1b3fac99e54c";
	
	private static final String masterSecret = "f3dff10f72a69ed10d141eac";
	
	private static JPushClient jpush = null;

	/*
	 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
	 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
	 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒
	 */
	private static long timeToLive =  60 * 60 * 24;  

	public static void main(String[] args) {
		/*
		 * Example1: 初始化,默认发送给android和ios，同时设置离线消息存活时间
		 * jpush = new JPushClient(masterSecret, appKey, timeToLive);
		 * 
		 * Example2: 只发送给android
		 * jpush = new JPushClient(masterSecret, appKey, DeviceEnum.Android);
		 * 
		 * Example3: 只发送给IOS
		 * jpush = new JPushClient(masterSecret, appKey, DeviceEnum.IOS);
		 * 
		 * Example4: 只发送给android,同时设置离线消息存活时间
		 * jpush = new JPushClient(masterSecret, appKey, timeToLive, DeviceEnum.Android);
		 */
		jpush = new JPushClient(masterSecret, appKey, timeToLive);

		/* 
		 * 是否启用ssl安全连接, 可选
		 * 参数：启用true， 禁用false，默认为非ssl连接
		 */
		//jpush.setEnableSSL(true);

		//测试发送消息或者通知
		testSend();
	}

	private static void testSend() {
	    // 在实际业务中，建议 sendNo 是一个你自己的业务可以处理的一个自增数字。
	    // 除非需要覆盖，请确保不要重复使用。详情请参考 API 文档相关说明。
	    int sendNo = getRandomSendNo();
		String msgTitle = "中脉移动";
		String msgContent = "第12财月欢乐积分欢乐送1";
		
		/*
		 * IOS设备扩展参数,
		 * 设置badge，设置声音
		 */

		Map<String, Object> extra = new HashMap<String, Object>();
		IOSExtra iosExtra = new IOSExtra(10, "WindowsLogonSound.wav");
		extra.put("ios", iosExtra);
		extra.put("page", "msgJpush.html");
		//测试 通过  公告 id   aaNO 定位具体信息
		extra.put("aaNo", "10012382");
		MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent, 0, extra);

		//对所有用户发送通知, 更多方法请参考文档
	//	MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
		
		if (null != msgResult) {
			System.out.println("服务器返回数据: " + msgResult.toString());
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				System.out.println("发送成功， sendNo=" + msgResult.getSendno());
			} else {
				System.out.println("发送失败， 错误代码=" + msgResult.getErrcode() + ", 错误消息=" + msgResult.getErrmsg());
			}
		} else {
			System.out.println("无法获取数据");
		}
		
		
	}
	
    public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX/2;
	
	/**
	 * 保持 sendNo 的唯一性是有必要的
	 * It is very important to keep sendNo unique.
	 * @return sendNo
	 */
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}
	
}

