package com.joymain.ng.security;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.joymain.ng.dao.JsysUserDao;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.StringUtil;

public class AccessAuthenticationFailureHandler extends GenericManagerImpl<JsysUser, String> implements
		AuthenticationFailureHandler {
	JsysUserDao jsysUserDao;

	@Autowired
	public AccessAuthenticationFailureHandler(JsysUserDao jsysUserDao) {
		super(jsysUserDao);
		this.jsysUserDao = jsysUserDao;
	}

    private String defaultFailureUrl;
    
	public final static String TRY_MAX_COUNT="tryMaxCount";
	private int maxTryCount=3;


	public AccessAuthenticationFailureHandler(String defaultFailureUrl) {
        setDefaultFailureUrl(defaultFailureUrl);
    }

    /**
     * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise returns a 401 error code.
     * <p>
     * If redirecting or forwarding, {@code saveException} will be called to cache the exception for use in
     * the target view.
     */
    @SuppressWarnings("unchecked")
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
    	HttpSession session=request.getSession();

    	Integer tryCount=(Integer) session.getAttribute(TRY_MAX_COUNT);
    	//锁定账户 
		String userCode=request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);//用户名
		String pwd=request.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY);//密码
		String password = StringUtil.encodePassword(pwd,"MD5");//MD5加密的密码
		
		//通过会员编号获得会员信息
		HashMap<String,String> map = (HashMap<String,String>)jsysUserDao.getJsysUser(userCode);
		
		//返回错误代码：
		String urlErr = "";
		if(map==null){
			urlErr = "1";//1.登录会员编号不存在
		}else{
			String mUserCode = map.get("userCode");//会员编号
			String mPassWord = map.get("passWord");//密码
			String mActive = map.get("active");//是否死点
//			String mExitDate = map.get("exitDate");//是否退出
			String state = map.get("state");//状态   1：正常
			if(!StringUtil.isEmpty(mPassWord) && !mPassWord.equals(password)){
				urlErr = "2";//2.登录会员密码不正确
			}else{//
				if("0".equals(state)){//禁用的会员
					urlErr = "4";//4.死点会员：只要判断state=0即可
//					active='1' 死点
//					exit_date 不为空是退出
				}
			}
			
		}
		defaultFailureUrl = "/login.jsp?urlErr="+urlErr+"&userCode="+userCode;
		
		//直接跳转到登录页面
		request.setAttribute("urlErr", urlErr);
        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
    }

    /**
     * Caches the {@code AuthenticationException} for use in view rendering.
     * <p>
     * If {@code forwardToDestination} is set to true, request scope will be used, otherwise it will attempt to store
     * the exception in the session. If there is no session and {@code allowSessionCreation} is {@code true} a session
     * will be created. Otherwise the exception will not be stored.
     */
   /* protected void saveException(HttpServletRequest request, AuthenticationException exception) {
    	System.out.println("进入到登录失败处理功能！");
        if (forwardToDestination) {
            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
        } else {
            HttpSession session = request.getSession(false);
            if (session != null || allowSessionCreation) {
            	if(request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)==null)
            			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
            }
        }
    }*/

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

	public int getMaxTryCount() {
		return maxTryCount;
	}

	public void setMaxTryCount(int maxTryCount) {
		this.maxTryCount = maxTryCount;
	}
    
}
