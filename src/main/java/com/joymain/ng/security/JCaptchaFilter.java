package com.joymain.ng.security;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

public class JCaptchaFilter implements Filter {

	//web.xml中的参数名定义
	public static final String PARAM_CAPTCHA_PARAMTER_NAME = "captchaParamterName";
	public static final String PARAM_CAPTCHA_SERVICE_ID = "captchaServiceId";
	public static final String PARAM_FILTER_PROCESSES_URL = "filterProcessesUrl";
	public static final String PARAM_FAILURE_URL = "failureUrl";
	public static final String PARAM_AUTO_PASS_VALUE = "autoPassValue";

	//默认值定义
	public static final String DEFAULT_FILTER_PROCESSES_URL = "/j_security_check";
	public static final String DEFAULT_CAPTCHA_SERVICE_ID = "captchaService";
	public static final String DEFAULT_CAPTCHA_PARAMTER_NAME = "j_captcha";

	private static Logger logger = LoggerFactory.getLogger(JCaptchaFilter.class);

	private String failureUrl;
	private String filterProcessesUrl = DEFAULT_FILTER_PROCESSES_URL;
	private String captchaServiceId = DEFAULT_CAPTCHA_SERVICE_ID;
	private String captchaParamterName = DEFAULT_CAPTCHA_PARAMTER_NAME;
	private String autoPassValue;

	private CaptchaService captchaService;
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest theRequest, ServletResponse theResponse,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest) theRequest;
		HttpServletResponse response = (HttpServletResponse) theResponse;
		String servletPath = request.getServletPath();
		System.out.println("validateCaptchaChallenge servletPath>>>"+servletPath);
		logger.info("validateCaptchaChallenge servletPath>>>"+servletPath);
		//符合filterProcessesUrl为验证处理请求,其余为生成验证图片请求.
		if (StringUtils.startsWith(servletPath, filterProcessesUrl)) {
			
			boolean validated = validateCaptchaChallenge(request);
			logger.info("validateCaptchaChallenge--validated>>>"+validated);
			if (validated) {
				chain.doFilter(request, response);
			} else {
				redirectFailureUrl(request, response);
			}
		} else {
			genernateCaptchaImage(request, response);
		}
	}

	private void genernateCaptchaImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
//		WebUtil.setNoCacheHeader(response);
		response.setDateHeader("Expires", 0);
		response.addHeader("Pragma", "no-cache");
		//Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
		
		response.setContentType("image/jpeg");
		logger.info("JCaptchaFilter--genernateCaptchaImage>>>");
		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			BufferedImage challenge = (BufferedImage) captchaService.getChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}

	private void redirectFailureUrl(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
//		request.getRequestDispatcher(request.getContextPath() + failureUrl+"&urlErr=3").forward(request, response);
		response.sendRedirect(request.getContextPath() + failureUrl+"&urlErr=3"); 
	}

	private boolean validateCaptchaChallenge(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			String captchaID = request.getSession().getId();
			String challengeResponse = request.getParameter(captchaParamterName);

			//自动通过值存在时,检验输入值是否等于自动通过值
			if (StringUtils.isNotBlank(autoPassValue) && autoPassValue.equals(challengeResponse)) {
				return true;
			}
			return captchaService.validateResponseForID(captchaID, challengeResponse);
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		initParameters(filterConfig);
		initCaptchaService(filterConfig);
	}

	private void initCaptchaService(FilterConfig filterConfig) {
		// TODO Auto-generated method stub
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		captchaService = (CaptchaService) context.getBean(captchaServiceId);
	}

	private void initParameters(FilterConfig fConfig) {
		// TODO Auto-generated method stub

		if (StringUtils.isBlank(fConfig.getInitParameter(PARAM_FAILURE_URL))) {
			throw new IllegalArgumentException("CaptchaFilter缺少failureUrl参数");
		}

		failureUrl = fConfig.getInitParameter(PARAM_FAILURE_URL);

		if (StringUtils.isNotBlank(fConfig.getInitParameter(PARAM_FILTER_PROCESSES_URL))) {
			filterProcessesUrl = fConfig.getInitParameter(PARAM_FILTER_PROCESSES_URL);
		}

		if (StringUtils.isNotBlank(fConfig.getInitParameter(PARAM_CAPTCHA_SERVICE_ID))) {
			captchaServiceId = fConfig.getInitParameter(PARAM_CAPTCHA_SERVICE_ID);
		}

		if (StringUtils.isNotBlank(fConfig.getInitParameter(PARAM_CAPTCHA_PARAMTER_NAME))) {
			captchaParamterName = fConfig.getInitParameter(PARAM_CAPTCHA_PARAMTER_NAME);
		}

		if (StringUtils.isNotBlank(fConfig.getInitParameter(PARAM_AUTO_PASS_VALUE))) {
			autoPassValue = fConfig.getInitParameter(PARAM_AUTO_PASS_VALUE);
		}
	}

}
