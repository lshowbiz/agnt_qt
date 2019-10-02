package com.joymain.ng.webapp.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.joymain.ng.model.AmAnnounce;
import com.joymain.ng.model.AmMessage;
import com.joymain.ng.model.JsysListKey;
import com.joymain.ng.model.JsysListValue;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.AmMessageManager;
import com.joymain.ng.service.JsysListKeyManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MessageUtil;
import com.joymain.ng.util.StringUtil;

@Controller
public class AmMessageController {
	private final Log log = LogFactory.getLog(AmMessageController.class);
	
    private AmMessageManager amMessageManager;
    private JsysListKeyManager jsysListKeyManager;
    private JsysUserManager jsysUserManager;
    private ResourceBundleMessageSource resourceBundleMessageSource;
    /**
     * show message page
     * @param request
     * @param response
     * @return string
     */
    @RequestMapping("/showMessage")
    public String showMessage(HttpServletRequest request,HttpServletResponse response){
    	JsysUser user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	String mesStatus = request.getParameter("mesStatus");
    	String mesCategory = request.getParameter("mesCategory");
    	String userCode = user.getUserCode(); 
    	
    	String strAction = request.getParameter("strAction");
    	
    	if("deleteAmessage".equals(strAction)){
        	String uniNo = request.getParameter("uniNo");
        	
        	AmMessage amMessage=amMessageManager.get(new Long(uniNo));
        	if(amMessage.getStatus()!=null && amMessage.getStatus()>=1){
        		//return "messageList";
        	}else{
        		amMessageManager.remove(new Long(uniNo));
        		MessageUtil.saveMessage(request, "删除成功");
        		

        		String msgAddCrm = ListUtil.getListValue("AA", "interface.sendswitch", "msg.add.crm");
        		if("Y".equals(msgAddCrm)){
            		/**
                	 * 会员删除留言信息推送至CRM
                	 */
                	AmMessage temp = new AmMessage();
                	temp.setUniNo(new Long(uniNo));
                	new Thread(new AmMessageRunnable(temp,"delete")).start();
        			
        		}
        	}
        	
        	
        	
    		
    	}
    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","showMessage?1=1&pageSize="+pageSize,pageSize,request);
    	 
//    	List mesList = amMessageManager.findMessageByUserCode(userCode, user.getCompanyCode(),null,"c");
		List mesList = amMessageManager.findMessageByUserCodePage(page,userCode, user.getCompanyCode(),null,"c");
		
		request.setAttribute("page", page);//将分页信息加入到request作用域中
    	request.setAttribute("mesList", mesList);
//    	int noReadReply     =amMessageManager.getNoReadReply(userCode, user.getCompanyCode());
//    	request.setAttribute("noReadReply", "noReadReply");
    	return "messageList";
    }
    /**
     * show add message page
     * @param request
     * @return string
     */
    @RequestMapping("/showAddMesPage")
    public String showAddmessagePage(HttpServletRequest request){
    	String uniNo = request.getParameter("uniNo");
    	JsysListKey mesKey = jsysListKeyManager.getListKeyByCode("msgclassno");
    	request.setAttribute("mesKey", mesKey);
    	log.info("show add message page unNO:"+uniNo +" and message categroy:"+mesKey.getSysListValues());
    	
    	if(uniNo !=null && ! uniNo.equals("")){
    		AmMessage message = amMessageManager.get(Long.parseLong(uniNo));
    		request.setAttribute("message", message);
    	}
    	return "addMessage";
    }
    /**
     * save message function
     * @param request
     * @param response
     */
    @RequestMapping(value="/amMessages/addMessage")
    public void addmessage(HttpServletRequest request,HttpServletResponse response){
    	JsysUser user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	String cry = request.getParameter("meCry");
    	String subject = request.getParameter("subject");
    	String cont = request.getParameter("cont");
    	
    	log.info("message category is:["+cry+"] and subject is:["+subject+"] and cont is:"+cont);
    	
    	AmMessage mes = new AmMessage();
    	mes.setAgentName(user.getUsername());
    	mes.setAgentNo(user.getUserCode());
    	
    	/*mes.setCheckMsgTime(Calendar.getInstance().getTime());
    	mes.setCheckUserNo("");
    	
    	mes.setDiscuss("");
    	mes.setReceiverName("");
    	mes.setReceiverNo("");
    	mes.setReplyContent("");
    	mes.setReplyTime(Calendar.getInstance().getTime());*/
    	mes.setCompanyCode(user.getCompanyCode());
    	mes.setSenderName(user.getUsername());
    	mes.setSendRoute("1");
    	mes.setStatus(0);
    	
    	mes.setSenderNo(user.getUserCode());
    	mes.setContent(cont);
    	mes.setIssueTime(Calendar.getInstance().getTime());
    	mes.setMsgClassNo(cry);
    	mes.setSubject(subject);
    	
    	amMessageManager.save(mes);
    	try {
    		response.sendRedirect(request.getContextPath()+"/amMessages/showMessage");
		} catch (IOException e) {
			System.out.println("message controller forword url error!");
			e.printStackTrace();
		}
 
    } 
    /**
     * edit message function
     * @param request
     * @param response
     */
    @RequestMapping(value="/amMessages/editMessage")
    public void editMessage(HttpServletRequest request,HttpServletResponse response){
    	log.info("message controller edit message function");
    	try{
    		String uino = request.getParameter("uniNo");
        	String subject = request.getParameter("subject");
        	String cont = request.getParameter("cont");
        	if(log.isDebugEnabled())
        		log.debug("uimo is:["+uino+"] and subject is:["+subject+"] and cont is:"+cont);
        	
        	AmMessage mes = amMessageManager.get(Long.parseLong(uino));
        	mes.setSubject(subject);
        	mes.setContent(cont);
        	mes.setIssueTime(Calendar.getInstance().getTime());
        	amMessageManager.save(mes);
        	
        	response.sendRedirect(request.getContextPath()+"/amMessages/showMessage");
    	}catch(Exception e){
    		log.error("",e);
    		e.printStackTrace();
    	}
    }
    
    @RequestMapping(value="/amMessages/delMessages")
    public void delMessages(HttpServletRequest request,HttpServletResponse response){
    	try{
    		String uinos = request.getParameter("allMessId");
        	log.info("delete message id is:"+uinos);
        	String[] uinoArr = uinos.split(",");
        	for(int i=1; i<uinoArr.length; i++){
        		log.info("===del message====="+uinoArr[i]);
        		amMessageManager.remove(Long.parseLong(uinoArr[i]));
        	}
        	response.sendRedirect(request.getContextPath()+"/amMessages/showMessage");
    	}catch(Exception e){
    		log.error("",e);
    		e.printStackTrace();
    	}
    	
    }
    @RequestMapping(value="/amMessages/searchMessage")
    public String searchMessage(HttpServletRequest request){
    	JsysUser user = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String mesCategory = request.getParameter("mesCategory");
    	String mesStatus = request.getParameter("mesStatus");
    	
    	log.info("category is:"+mesCategory +" and status :"+mesStatus);
    	
    	List<AmMessage> messageList = amMessageManager.findMessage(mesCategory, mesStatus,user.getUserCode());
    	JsysListKey mesKey = jsysListKeyManager.getListKeyByCode("msgclassno");
    	
    	request.setAttribute("mesKey", mesKey);
    	request.setAttribute("mesList", messageList);
    	request.setAttribute("mesCategory", mesCategory);
    	request.setAttribute("mesStatus", mesStatus);
    	
    	return "messageList";
    }
    /**
     * 手机端查询消息 类型 查全部记录 ，未读的变已读 
     * @param userId
     * @param token
     * @param mesCategory
     * @return list
     */
    @RequestMapping(value="/amMessages/api/mobileSearchMessage")
    @ResponseBody
    public List<AmMessage> searchMessage(String userId ,String token,String mesCategory){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
    	if(log.isDebugEnabled())
    	log.debug("mobile category is:"+mesCategory+" and user is:"+user.getUserCode());
    	
/*    	List<AmMessage> nuReadmsgList = amMessageManager.ascfindMessage(mesCategory, "3",user.getUserCode());
    	for(AmMessage am:nuReadmsgList){
    		am.setStatus(9);//已阅读
    		amMessageManager.save(am);
    	}
    	List<AmMessage> messageList = amMessageManager.ascfindMessage(mesCategory, null,user.getUserCode())*/;

//		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");

    	List messageList = amMessageManager.findMessageByUserCode(user.getUserCode(), user.getCompanyCode(),mesCategory,"m");
    	for (int i = 0; i < messageList.size(); i++) {
			Map map=(Map) messageList.get(i);
			String id=(String) map.get("uni_no");
			Integer status=((BigDecimal) map.get("status")).intValue();
			String send_route=(String) map.get("send_route");

//			Object issue_time=map.get("issue_time");
//			Object reply_time=map.get("reply_time");
//			Object check_msg_time=map.get("check_msg_time");
//			
//			if(issue_time!=null){
//				map.put("issue_time_str", sdf.format(issue_time));
//			}
//			if(reply_time!=null){
//				map.put("reply_time_str", sdf.format(reply_time));
//			} 
//			if(check_msg_time!=null){
//				map.put("check_msg_time_str", sdf.format(check_msg_time));
//			} 
			
			
			if (status==3 && ("1".equals(send_route) || "0".equals(send_route)) ){
				AmMessage amMessage=amMessageManager.get(new Long(id));
				amMessage.setStatus(9);
				map.put("STATUS", 9);
				amMessageManager.save(amMessage);
			}
			
		}
    	
    	
    	return messageList;
    }
    /**
     * 手机端获取 消息类别 取类型、未读数、类型value
     * @return set
     */
    @RequestMapping("/amMessages/api/mobileMesCategory")
	@ResponseBody
    public List messageMobileCategory(HttpServletRequest request){
    	String userId = request.getParameter("userId");
    	String token = request.getParameter("token");
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
		
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
    	
		LinkedHashMap linkedHashMap=ListUtil.getListOptions(user.getCompanyCode(), "msgclassno");

		List list=new ArrayList();

		Iterator linkedHashMapIt = linkedHashMap.entrySet().iterator();
		while (linkedHashMapIt.hasNext()) {

		    Map.Entry entry = (Map.Entry) linkedHashMapIt.next(); 
		    String key = entry.getKey().toString(); 
		    String val = entry.getValue().toString();
		    Integer num= amMessageManager.getNoReadReply(user.getUserCode(), user.getCompanyCode(), key);
		    
		    Map map=new HashMap();
		    map.put("key", key);
		    map.put("display_val", LocaleUtil.getLocalText(user.getDefCharacterCoding(),val));
		    map.put("unread_num", num);
		    
		    list.add(map);
		    
		}
		
		
    
    	
    	return list;
    }
    /**
     * mobile save message function
     * @param cry
     * @param subject
     * @param cont
     * @param userId
     * @param token
     * @return 0 or 1
     */
    @RequestMapping(value="/amMessages/api/mobile/addMessage")
    @ResponseBody
    public String addmessage(String cry,String subject,String cont,String userId ,String token){
    	try {
	    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
			Object object = jsysUserManager.getAuthErrorCode(user, "String");
			if(null!=object){
				return (String)object;
			}
	    	if(log.isDebugEnabled())
	    	log.debug("message category is:["+cry+"] and subject is:["+subject+"] and cont is:"+cont);
	    	
	    	AmMessage mes = new AmMessage();
	    	mes.setAgentName(user.getUsername());
	    	mes.setAgentNo(user.getUserCode());

	    	mes.setCompanyCode(user.getCompanyCode());
	    	
	    	
	    	/*mes.setCheckMsgTime(Calendar.getInstance().getTime());
	    	mes.setCheckUserNo("");
	    	mes.setDiscuss("");
	    	mes.setReceiverName("");
	    	mes.setReceiverNo("");
	    	mes.setReplyContent("");
	    	mes.setReplyTime(Calendar.getInstance().getTime());*/
	    	mes.setSendRoute("1");
	    	mes.setStatus(0);
	    	
	    	mes.setSenderNo(user.getUserCode());
	    	mes.setSenderName(user.getUsername());
	    	
	    	mes.setContent(cont);
	    	mes.setIssueTime(Calendar.getInstance().getTime());
	    	mes.setMsgClassNo(cry);
	    	mes.setSubject(subject);
	    	mes.setIsMobile("1");
	    	amMessageManager.save(mes);
	    	return "1";
		} catch (Exception e) {
			log.error("message controller forword url error!",e);
			e.printStackTrace();
			return "0";
		}
    } 
    

    
		
    @Autowired
    public void setJsysListKeyManager(JsysListKeyManager jsysListKeyManager) {
		this.jsysListKeyManager = jsysListKeyManager;
	}
    @Autowired
    public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}
	@Autowired
    public void setAmMessageManager(AmMessageManager amMessageManager) {
        this.amMessageManager = amMessageManager;
    }
	@Autowired
	public void setResourceBundleMessageSource(
			ResourceBundleMessageSource resourceBundleMessageSource) {
		this.resourceBundleMessageSource = resourceBundleMessageSource;
	}
	
}
