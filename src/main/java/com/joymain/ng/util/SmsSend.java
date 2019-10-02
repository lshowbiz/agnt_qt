package com.joymain.ng.util;

import java.io.IOException;

import net.sf.json.JSONObject;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.joymain.ng.Constants;
import com.joymain.ng.model.JsysUser;

/**
 * 短信接口
 * @author WuCF
 * @date：2014-01-14
 *
 */
public class SmsSend {
//	public static String URL1 = "http://192.168.20.227:8080/JM-PLATFORM/sms/MobsetSendSMS/sysId/oa/mobileNum/";
//	public static String URL2 = "/message/hello";
	
	/**
	 * 发送短信方法
	 * @URL1：配置的路径1
	 * @URL2：配置的路径2
	 * @mobilePhone：手机号
	 * @message：发送消息内容（尽量将字数控制在70字以内，否则会分页发送多条，浪费资源）
	 */
	public static void sendSms(String url1,String url2,String mobilePhone,String message){
		//注释：得到URL1和URL2可以在列表中得到
//		String url1 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "1");
//		String url2 = ListUtil.getListValue(sessionLogin.getCompanyCode().toUpperCase(), "smssend.url", "2");

		
		String url = url1+mobilePhone+url2;//获得读取短信的URL
		//本地短信服务器访问地址
		ClientResource resource = new ClientResource(url);
		resource.setChallengeResponse(ChallengeScheme.HTTP_BASIC ,   
				"JM-PLATFORM", "JM-PLATFORM");   

		//生成对象
		Form form = null; 
		Representation representation = null;
		try {
			form = new Form();  
			form.add("sysId", "JECS");//登录账号
			form.add("mobileNum", mobilePhone);//设置手机号
			form.add("message", message); //设置内容
			representation = resource.put(form, MediaType.APPLICATION_JSON);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(form!=null){
				form.clear();//关闭资源
			}
		}
	}
	
	/**
	 * 发送短信验证码到手机
	 * @param verifyCode 验证码
	 * @param validTime 有效时间
	 * @param phoneNum 接收短信验证码的手机号
	 */
	public static String sendSmsVerifyCode(String verifyCode,String validTime,String phoneNum){
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String accessKeyId = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("sms.accesskeyid");
		String accessKeySecret = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("sms.accesskeysecret");
		String signName = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("sms.signname");//阿里云短信控制台创建的签名名称
		String templateCode = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("sms.templatecode");//阿里云短信控制台创建的模板CODE
		
		String paramstring = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("sms.cloudshop.paramstring");
		paramstring = paramstring.replace("${verifyCode}", verifyCode);
		paramstring = paramstring.replace("${validTime}", validTime);
		
		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName(signName);// 控制台创建的签名名称
			request.setTemplateCode(templateCode);// 控制台创建的模板CODE
			request.setParamString(paramstring);// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setRecNum(phoneNum);// 接收号码
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			String requestId = httpResponse.getRequestId();
			String model = httpResponse.getModel();
		} catch (ServerException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		} catch (ClientException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		}
		return "";
	}

	/**
	 * 发送短信验证码到手机
	 * @param password 密码
	 * @param phoneNum 接收短信验证码的手机号
	 */
	public static String sendSmsPassWord(String password,String phoneNum){
		String accessKeyId = ConfigUtil.getConfigValue("CN","sms.accesskeyid");
		String accessKeySecret =  ConfigUtil.getConfigValue("CN","sms.accesskeysecret");
		String signName =  ConfigUtil.getConfigValue("CN","sms.signname");//阿里云短信控制台创建的签名名称
		String templateCode =  ConfigUtil.getConfigValue("CN","sms.templatecode.password");//阿里云短信控制台创建的模板CODE

		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		JSONObject jsonObject=new JSONObject();
		jsonObject.put("password", password);

		try {
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms", "sms.aliyuncs.com");
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			// 控制台创建的签名名称
			request.setSignName(signName);
			// 控制台创建的模板CODE
			request.setTemplateCode(templateCode);
			// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setParamString(jsonObject.toString());
			// 接收号码
			request.setRecNum(phoneNum);
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			String requestId = httpResponse.getRequestId();
			String model = httpResponse.getModel();
		} catch (ServerException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		} catch (ClientException e) {
			e.printStackTrace();
			return "验证码发送失败，请检查短信接口！";
		}
		return "";
	}

	public static void main(String[] args) {
		String url1 = "http://192.168.20.227:8080/JM-PLATFORM/sms/MobsetSendSMS/sysId/oa/mobileNum/";
		String url2 = "/message/hello";
//		String mobilePhone = "13650710137";
		String mobilePhone = "15902032978";
		String message = "感谢您在中脉国际辛勤工作的一年里所做出的贡献！公司将给你发送2013年年终奖26999元到您的工资卡，以资奖励！请注意查收！";
//		String message = "感谢您为文思海辉辛勤工作的一年里所做出的贡献！公司将给你发送2013年年终奖25999元到您的工资卡，以资奖励！请注意查收！";
//		String message = "尊敬的顺丰客户您好！您有快递已经到达目的地，请到云来斯堡20楼中脉国际咨询部前台领取！";
		//sendSms(url1,url2,mobilePhone, message);
		sendSmsPassWord("abc",mobilePhone);
	}
}
