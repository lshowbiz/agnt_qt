package com.joymain.ng.util;

import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
import utils.Base64;

/**
 * 
 * HttpClient处理
 * 
 * @author soul
 * @email soul.lau0328@gmail.com
 * @qq 278834379
 * @since 2013-12-2 下午4:36:04 
 * 
 */
public class HttpClientUtil {

    /**
     * 日志
     */
    private static final Log log = LogFactory.getLog(HttpClientUtil.class);

    private static HttpClientUtil multiThreadHttp;

    private CloseableHttpClient httpClient;

    private PoolingHttpClientConnectionManager connectionManager;

    private int connectionTimeout = 30;

    private int socketTimeout = 30;

    private HttpClientUtil() {

        connectionTimeout = StringUtil.formatInt(ConfigUtil.getConfigValue("AA", "httpClient.connectionTimeout"), connectionTimeout) * 1000;
        socketTimeout = StringUtil.formatInt(ConfigUtil.getConfigValue("AA", "httpClient.socketTimeout"), socketTimeout) * 1000;
        int defaultMaxConnectionsPerHost = StringUtil.formatInt(ConfigUtil.getConfigValue("AA", "httpClient.defaultMaxConnectionsPerHost"), 1000);
        int maxTotal = StringUtil.formatInt(ConfigUtil.getConfigValue("AA", "httpClient.defaultMaxConnectionsPerHost"), 2000);
        // 设置ConnectionConfig
        ConnectionConfig defaultConnectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();
        // 设置ConnectionManager
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal); // 最大请求数2000
        connectionManager.setDefaultMaxPerRoute(defaultMaxConnectionsPerHost); // 单个地址最大请求数1000
        connectionManager.setDefaultConnectionConfig(defaultConnectionConfig);
        // 生成HttpClient对象
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

    }

    /**
     * 获取单例
     * 
     * @return HttpClientUtil
     */

    public static HttpClientUtil getInstance() {
        if (multiThreadHttp == null) {
            multiThreadHttp = new HttpClientUtil();
        }
        return multiThreadHttp;
    }

    /**
     * 
     * 向服务器Post XML报文
     * 
     * @param url
     *            请求地址
     * @param xml
     *            XML
     * @param headers
     *            请求头
     * @return String 异常
     */

    public String postXMLDefault(String url, String xml, Map<String, String> headers) {
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            // 设置请求参数,超时时间20秒
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectionTimeout).build();
            httpPost.setConfig(requestConfig);
            // 设置请求头
            if (headers != null) {
                Set<String> headerNames = headers.keySet();
                Iterator<String> its = headerNames.iterator();
                while (its.hasNext()) {
                    String headerName = its.next();
                    httpPost.addHeader(headerName, headers.get(headerName));
                }
            }
            // 设置请求体
            StringEntity xmlEntity = new StringEntity(xml, ContentType.create("text/xml", Consts.UTF_8));
            httpPost.setEntity(xmlEntity);

            response = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // byte[] bytes = EntityUtils.toByteArray(entity);
                    String responseXML = EntityUtils.toString(entity, "UTF-8");
                    return responseXML;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    /**
     * 
     * 重载postXML方法
     * 
     * @param url
     *            请求地址
     * @param xml
     *            请求XML
     * @param headers
     *            请求头信息
     * @return ActionResult
     */

    public String postXML(String url, String xml, Map<String, String> headers) {
        return this.postXML(url, xml, connectionTimeout, headers);
    }

    /**
     * 
     * 向服务器Post XML报文
     * 
     * @param url
     *            请求地址
     * @param xml
     *            XML
     * @param timeOut
     *            超时时间
     * @param headers
     *            请求头
     * @return 请求失败则返回null,否则返回响应体
     * 
     */

    public String postXML(String url, String xml, int timeOut, Map<String, String> headers) {

        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            // 设置请求参数,超时时间20秒
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20 * 1000).setConnectTimeout(timeOut).setConnectionRequestTimeout(timeOut).build();
            httpPost.setConfig(requestConfig);
            // 设置请求头
            if (headers != null) {
                Set<String> headerNames = headers.keySet();
                Iterator<String> its = headerNames.iterator();
                while (its.hasNext()) {
                    String headerName = its.next();
                    httpPost.addHeader(headerName, headers.get(headerName));
                }
            }
            // 设置请求体
            StringEntity xmlEntity = new StringEntity(xml, ContentType.create("text/xml", Consts.UTF_8));
            httpPost.setEntity(xmlEntity);

            // 记录请求报文日志
            response = httpClient.execute(httpPost);
            log.debug("Response Code :" + response.getStatusLine().getStatusCode());
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // byte[] bytes = EntityUtils.toByteArray(entity);
                    String responseXML = EntityUtils.toString(entity);
                    return responseXML;
                }
            }
        } catch (ConnectTimeoutException e) {
            log.error("Request ConnectTimeoutException:" + e);
            return null;
        } catch (SocketTimeoutException e) {
            log.error("Request SocketTimeoutException:" + e);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return null;
            }
        } catch (Exception e) {
            log.error("HttpClientUtil.postXML Error : " + e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    log.error("Close Http Response Error:" + e);
                }

            }
        }
        return null;
    }
    // public static void main(String args[])
    // {
    // String url = "http://120.132.134.248:8088/mcmm/api/imcmm";
    // Map<String, String> headerMap = new HashMap<String, String>();
    // headerMap.put("x-UserAgent", "android|SAMSUMG9100|android 2.3|1.0");
    // headerMap.put("Accept", "application/xml");
    // headerMap.put("Authorization", "ip bWFzOjEzNjMxNDYwNzY3");
    //
    // String xml =
    // "<?xml version=\"1.0\" encoding=\"UTF-8\"?><getMissionInfo><account>+86+123456789</account></getMissionInfo>";
    // try
    // {
    // String responseXML = HttpClientUtil.getInstance().postXML(
    // url, xml, headerMap);
    // System.out.print(responseXML);
    //
    // }
    // catch (Exception e)
    // {
    // // 
    // e.printStackTrace();
    // }
    //
    // }
    
    public JSONObject httpPost(String url, JSONObject jsonParam, String companyCode) throws Exception {
		// 约定KEY
		String key = ConfigUtil.getConfigValue(companyCode,"cloudshop.key");
		
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jResult = null;
		HttpPost method = new HttpPost(url);

		// 签名
		Iterator it = jsonParam.keys();
		String linkedStr = "";
		while (it.hasNext()) {
			String key1 = (String) it.next();
			linkedStr += key1 + "=" + jsonParam.get(key1) + ",";
		}
		linkedStr = linkedStr.substring(0, linkedStr.length() - 1);
		String linkedStrMd5 = StringUtil.encodePassword(linkedStr, "md5");
		String linkedStrMd52 = "sign=" + linkedStrMd5 + "," + linkedStr;
		linkedStrMd52 = linkedStrMd52 + key;
		String base64 = Base64.encode(linkedStrMd52.getBytes());
		String sign = StringUtil.encodePassword(base64, "md5");

		if (null != jsonParam) {
			/* 添加签名 */
			jsonParam.element("sign", sign);
			System.out.println(jsonParam);
			// 解决中文乱码问题
			StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			method.setEntity(entity);
		}
		HttpResponse result = httpClient.execute(method);
		url = URLDecoder.decode(url, "UTF-8");
		// 请求发送成功,并得到响应
		if (result.getStatusLine().getStatusCode() == 200) {
			String temp = "";
			// 读取服务器返回过来的json字符串数据
			temp = EntityUtils.toString(result.getEntity());
			// 把json字符串转换成json对象
			jResult = JSONObject.fromObject(temp);
		}
		return jResult;
	}  

}