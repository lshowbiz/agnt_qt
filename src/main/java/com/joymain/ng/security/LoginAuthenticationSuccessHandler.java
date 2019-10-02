package com.joymain.ng.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import org.springframework.util.StringUtils;

public class LoginAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.onAuthenticationSuccess(request, response, authentication);
		 SavedRequest savedRequest = getSavedRequest(request);

	        if (savedRequest == null) {
	            super.onAuthenticationSuccess(request, response, authentication);

	            return;
	        }

	        if (isAlwaysUseDefaultTargetUrl()
	                || StringUtils.hasText(request
	                        .getParameter(getTargetUrlParameter()))) {
	            removeSavedRequest(request);
	            super.onAuthenticationSuccess(request, response, authentication);

	            return;
	        }

	        
	        // 参考Lingo 的Spring security 3.0文档 附录 C. Spring Security-3.0.0.M1
	        HttpSession session = request.getSession();
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//	        Users currentUser = usersDao.findByUnique("loginname", userDetails.getUsername().toString());
//	        session.setAttribute("currentUser", currentUser);

	        // Use the SavedRequest URL
	      /*  String targetUrl = savedRequest.getFullRequestUrl();
	        logger.debug("Redirecting to SavedRequest Url: " + targetUrl);
	        RedirectUtils.sendRedirect(request, response, targetUrl,
	                isUseRelativeContext());*/
	}

	private void removeSavedRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);

//        if (session != null) {
//            logger.debug("Removing SavedRequest from session if present");
//            session
//                    .removeAttribute(SavedRequest.SPRING_SECURITY_SAVED_REQUEST_KEY);
//        }
	}

	private SavedRequest getSavedRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);

//        if (session != null) {
//            return (SavedRequest) session
//                    .getAttribute(SavedRequest.SPRING_SECURITY_SAVED_REQUEST_KEY);
//        }

        return null;
	}

}
