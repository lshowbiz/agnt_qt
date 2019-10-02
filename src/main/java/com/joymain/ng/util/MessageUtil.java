package com.joymain.ng.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;

import com.joymain.ng.model.JsysUser;


public class MessageUtil {

	protected final static String MESSAGES_KEY = "successMessages";

	public static void saveExceptionLocaleMessage(HttpServletRequest request, AppException e) {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List messages = getMessages(request);
		messages.add(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), e.getErrMsg()));
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

	public static void saveLocaleMessage(HttpServletRequest request, String msgKey) {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List messages = getMessages(request);
		messages.add(LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(), msgKey));
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

	public static void saveMessage(HttpServletRequest request, String msg) {

		List messages = getMessages(request);
		messages.add(msg);
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

	private static List getMessages(HttpServletRequest request) {
		List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}
		return messages;
	}

	/**
	 * 清空Session中的信息
	 * @param request
	 */
	public static void resetMessages(HttpServletRequest request) {
		List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);
		messages = new ArrayList();
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

}
