package com.joymain.ng.handle.movie;

import java.net.SocketTimeoutException;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.util.ConfigUtil;

import net.sf.json.JSONObject;


public class TicketMovie {
	
	private static final int _timeout = 20;
	
	//private final static  String SERVER_URL = "http://121.40.179.94:8280/zhongmai_movie/service";
	private  final static  String MD5_KEY = "yurtycfgh";
	private final static  String _desKey = "";
	private final static int CHANNEL_Id = 153;
	private final static String ACTION = "order_Addorconfirm";
	private final static String COUPON_TYPE = "566";

	

	/**
	 * 
	 * 
	* @Title: ecPay
	* @Description: TODO
	* @param @param userName
	* @param @param count
	* @param @return
	* @param @throws Exception    
	* @return JSONObject    
	* @throws
	 */
	public static JSONObject ticket(String userName,int count) throws Exception {
			
			String url = ConfigUtil.getConfigValue("CN", "movieurl");
			HttpRequest req = new HttpRequest(url, MD5_KEY, null, CHANNEL_Id );
			req.addParam(new Param("action", ACTION));
			req.addParam(new Param("user_name", userName));
			//req.addParam(new Param("password", "B20F79DBF58A29C7610A4F5F7C1518B2"));
			req.addParam(new Param("coupon_type", COUPON_TYPE));
			req.addParam(new Param("count", count));
			req.addParam(new Param("mobile", "13344444444"));
			String content = req.getRequest(_timeout);
			//System.out.println("接口返回的json:" + content);
			return JSONObject.fromObject(content); 

	}

}
