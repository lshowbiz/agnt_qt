package com.joymain.ng.webapp.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.MemberOrderApiManager;
import com.joymain.ng.service.MobilePaymentManager;
import com.joymain.ng.service.PayOtherMemberOrderManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 手机端接口servlet入口
 * 
 * @author houxyu
 * @date 2016-4-13
 * 
 */
@SuppressWarnings("unchecked")
public class MobileHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json; charset=UTF-8");
		response.setContentType("text/xml;charset=utf-8");  
		response.setHeader("Content-Type", "application/json; charset=UTF-8"); 
		
		String methodName = request.getParameter("methodName");// 方法名，根据方法名走不同路线获取接口
		String token = request.getParameter("token");//
		String userId = request.getParameter("userId");//
		String jsonData = request.getParameter("jsonData");// 业务加密数据
 
		// 去支付他人订单,会员转账历史信息,申请转账
		String[] mobilePay = new String[] { "payOthersOrder","payTransfersDetails" ,"payTransfers"  }; 
		Map<String, Object> retMap = new HashMap<String, Object>();
		Object writeJson = "";//回写给调用接口的内容
		try {
			//Modify By WuCF 20160428 找回密码不用进行token校验
			if("retrievePassword".equals(methodName)){
				JmiMemberManager jmiMemberManager = getServiceById("jmiMemberManager", JmiMemberManager.class);
				// 得到返回结果
				String result = jmiMemberManager.checkJmiMemberPwdResetMobile(request, response);
				log.info("retrievePassword-result:"+result);
				return;
			}
			
			JsysUserManager jsysUserManager = getServiceById("jsysUserManager", JsysUserManager.class);
			JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token); // 当前会员
			if(jsysUser != null){
				if ("memberOrderPayOtherForm".equals(methodName)) {// 支付他人订单，获取订单信息
					PayOtherMemberOrderManager payOtherService = getServiceById("PayOtherMemberOrderService", PayOtherMemberOrderManager.class);
					payOtherService.payOtherOrderForm(request, response);
				} else if("OrderStatistics".equals(methodName)){//订单统计
					MemberOrderApiManager memberOrderApiService = getServiceById("MemberOrderApiService", MemberOrderApiManager.class);
					memberOrderApiService.OrderStatistics(request, response);
				} else if("pdSendInfoConfirm".equals(methodName)){//发货确认
					PdSendInfoManager  pdSendInfoManager = getServiceById("pdSendInfoManager",PdSendInfoManager.class);
					pdSendInfoManager.pdSendInfoConfirmByPhone(request, response);
				} else if("siNoList".equals(methodName)){//发货单号查询
					PdSendInfoManager  pdSendInfoManager = getServiceById("pdSendInfoManager",PdSendInfoManager.class);
					pdSendInfoManager.getSiNoList(request, response);
				}else if("cloudshopPhoneModify".equals(methodName)){//云店账号修改
					JmiMemberManager  jmiMemberManager = getServiceById("jmiMemberManager",JmiMemberManager.class);
					jmiMemberManager.modifyCloudshopPhone(request, response);
				}else if (StringUtil.contains(mobilePay, methodName)) {
					//jsonData = new String(jsonData.getBytes("ISO8859-1"), "UTF-8");
					log.info("jsonData=============="+jsonData);
					MobilePaymentManager payService = getServiceById("mobilePaymentManager", MobilePaymentManager.class);
					Method method = payService.getClass().getMethod(methodName, new Class[] { JsysUser.class, String.class });
					writeJson = method.invoke(payService, new Object[] { jsysUser, jsonData });
				}
			}else{
				retMap.put("code", "e002");
				retMap.put("msg", "鉴权失败,非法用户!");
				writeJson = JSONObject.fromObject(retMap);
			}
		}catch (InvocationTargetException e) {  //反射的时候定义异常
            Throwable t = e.getTargetException();// 获取目标异常  
            t.printStackTrace();  
			if (t instanceof AppException ){
				retMap.put("code", "e002");
				retMap.put("msg", t.getMessage());
			}else{
				retMap.put("code", "e005");
				retMap.put("msg", "业务数据问题"+jsonData);
			}
			log.info("手机端接口 " + methodName + " 访问错误", e);
			writeJson = JSONObject.fromObject(retMap);
        } catch (Exception e) {
        	log.info("手机端接口 " + methodName + " 访问错误", e);
			e.printStackTrace();
			retMap.put("code", "e005");
			retMap.put("msg", "业务数据问题"+jsonData);
			writeJson = JSONObject.fromObject(retMap);
		}
		response.getWriter().print(writeJson);
	}

	/**
	 * @Description 根据serviceId 获取
	 * @author houxyu
	 * @date 2016-4-13
	 * @param serviceId
	 * @param clazz
	 * @return
	 */
	private <T> T getServiceById(String serviceId, Class<T> clazz) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		T r = (T) context.getBean(serviceId);
		return r;
	}
}
