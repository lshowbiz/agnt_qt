package com.joymain.ng.webapp.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.joymain.ng.Constants;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoProductNumLimit;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.User;
import com.joymain.ng.service.JbdMemberFrozenManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoProductNumLimitManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.MailEngine;
import com.joymain.ng.service.UserManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;

/**
 * Implementation of <strong>SimpleFormController</strong> that contains
 * convenience methods for subclasses. For example, getting the current user and
 * saving messages/errors. This class is intended to be a base class for all
 * Form controllers.
 * 
 * <p>
 * <a href="BaseFormController.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseFormController implements ServletContextAware {
	protected final transient Log log = LogFactory.getLog(getClass());
	public static final String MESSAGES_KEY = "successMessages";
	private UserManager userManager = null;
	protected MailEngine mailEngine = null;
	protected SimpleMailMessage message = null;
	protected String templateName = "accountCreated.vm";
	protected String cancelView;
	protected String successView;

	private MessageSourceAccessor messages;
	private ServletContext servletContext;
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	@Autowired
	private JpoProductNumLimitManager jpoProductNumLimitManager;
	@Autowired
	private JsysUserManager jsysUserManager;
	@Autowired
	private JbdMemberFrozenManager jbdMemberFrozenManager;
	
	
	public JpoProductNumLimitManager getJpoProductNumLimitManager() {
		return jpoProductNumLimitManager;
	}

	public void setJpoProductNumLimitManager(
			JpoProductNumLimitManager jpoProductNumLimitManager) {
		this.jpoProductNumLimitManager = jpoProductNumLimitManager;
	}
	@Autowired(required = false)
	Validator validator;

	@Autowired
	public void setMessages(MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	@Autowired
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	@SuppressWarnings("unchecked")
	public void saveError(HttpServletRequest request, String error) {
		List errors = (List) request.getSession().getAttribute("errors");
		if (errors == null) {
			errors = new ArrayList();
		}
		errors.add(error);
		request.getSession().setAttribute("errors", errors);
	}

	@SuppressWarnings("unchecked")
	public void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add(msg);
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

	/**
	 * Convenience method for getting a i18n key's value. Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable is
	 * not set in unit tests b/c there's no DispatchServlet Request.
	 * 
	 * @param msgKey
	 * @param locale
	 *            the current locale
	 * @return
	 */
	public String getText(String msgKey, Locale locale) {
		return messages.getMessage(msgKey, locale);
	}

	public String getText(String characterCoding, String msgKey) {
		return LocaleUtil.getLocalText(characterCoding, msgKey);
	}

	public String getText(String msgKey) {

		return LocaleUtil.getLocalText(msgKey);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * 
	 * @param msgKey
	 * @param arg
	 * @param locale
	 *            the current locale
	 * @return
	 */
	public String getText(String msgKey, String arg, Locale locale) {
		return getText(msgKey, new Object[] { arg }, locale);
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 * 
	 * @param msgKey
	 * @param args
	 * @param locale
	 *            the current locale
	 * @return
	 */
	public String getText(String msgKey, Object[] args, Locale locale) {
		return messages.getMessage(msgKey, args, locale);
	}

	/**
	 * Convenience method to get the Configuration HashMap from the servlet
	 * context.
	 * 
	 * @return the user's populated form from the session
	 */
	public Map getConfiguration() {
		Map config = (HashMap) servletContext.getAttribute(Constants.CONFIG);

		// so unit tests don't puke when nothing's been set
		if (config == null) {
			return new HashMap();
		}

		return config;
	}

	/**
	 * Set up a custom property editor for converting form inputs to real
	 * objects
	 * 
	 * @param request
	 *            the current request
	 * @param binder
	 *            the data binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Integer.class, null,
				new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(
				Long.class, null, true));
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	/**
	 * Convenience message to send messages to users, includes app URL as
	 * footer.
	 * 
	 * @param user
	 *            the user to send a message to.
	 * @param msg
	 *            the message to send.
	 * @param url
	 *            the URL of the application.
	 */
	protected void sendUserMessage(User user, String msg, String url) {
		if (log.isDebugEnabled()) {
			log.debug("sending e-mail to user [" + user.getEmail() + "]...");
		}

		message.setTo(user.getFullName() + "<" + user.getEmail() + ">");

		Map<String, Serializable> model = new HashMap<String, Serializable>();
		model.put("user", user);

		// TODO: once you figure out how to get the global resource bundle in
		// WebWork, then figure it out here too. In the meantime, the Username
		// and Password labels are hard-coded into the template.
		// model.put("bundle", getTexts());
		model.put("message", msg);
		model.put("applicationURL", url);
		mailEngine.sendMessage(message, templateName, model);
	}
	
	/**
	 * 是否单独购买(9种商品)
	 * @param cartOrder
	 * @return
	 */
	protected boolean jpoIsOnly(JpoShoppingCartOrder cartOrder,Collection jpoList){
		boolean isOnly = true;
		Collection carProList= new  ArrayList();
		
		Iterator<JpoShoppingCartOrderList> iter = cartOrder.getJpoShoppingCartOrderList().iterator();
		while(iter.hasNext()){
			JpoShoppingCartOrderList carOrderList = iter.next();
    		String carProNo = carOrderList.getJpmProductSaleTeamType().
    				getJpmProductSaleNew().getJpmProduct().getProductNo();
    		carProList.add(carProNo);
		}
		
		if(Collections.disjoint(carProList,jpoList)){ //没有相同元素
			isOnly = true;
		}else{
			Iterator<JpoShoppingCartOrderList> iters = cartOrder.getJpoShoppingCartOrderList().iterator();
			while(iters.hasNext()){
				JpoShoppingCartOrderList carOrderList = iters.next();
	    		String carProNo = carOrderList.getJpmProductSaleTeamType().
	    				getJpmProductSaleNew().getProductNo();
	    		
	    		if(jpoList.contains(carProNo)){
	    			isOnly = true;
	    		}else{
	    			isOnly = false;
	    			break;
	    		}
			}	
		}
		return isOnly;
		
	}

	/**
	 * 产品是否单独购买
	 * @param cartOrder
	 * @return list
	 */
	protected List<String> proIsOnly(JpoShoppingCartOrder cartOrder){
		
		List<String> list = new ArrayList<String>();
		Iterator<JpoShoppingCartOrderList> iter = cartOrder.
				getJpoShoppingCartOrderList().iterator();
		boolean flag=false;
		int size = cartOrder.getJpoShoppingCartOrderList().size();
		
    	while(iter.hasNext()){
    		
    		JpoShoppingCartOrderList carOrderList = iter.next();
    		String carProNo = carOrderList.getJpmProductSaleTeamType().
    				getJpmProductSaleNew().getProductNo();
    		String proName = carOrderList.getJpmProductSaleTeamType().
    				getJpmProductSaleNew().getProductName();
    		
    		for(String proNo : GlobalVar.proList){
    			
    			log.info("temProNo is:["+proNo+"] " +
    					"and carProNo is:"+carProNo);
    			
    			if(proNo.equalsIgnoreCase(carProNo)){
    				if(size>1){
        	    		list.add(proName+LocaleUtil.
        	    				getLocalText("zh_CN","onlyinfo"));
        	    	}
    			}
    			
    		}
    	}
		return list;
	}
	
	/**
	 * 验证产品购买数量是否超过限制 (2015 09 财月做成功能)
	 * @param jpoMemberOrder
	 * @return true or false
	 */
	protected Integer isBuyProNumLimt(JpoShoppingCartOrder cartOrder,String pro){
		
    	Iterator its1 = cartOrder.getJpoShoppingCartOrderList().iterator();// .getJpoMemberOrderList().iterator();
    	
    	Integer proSum=0, proNoCount=-1,countQty=0;
    	List<String> productNos = new ArrayList();
    	List<JpoProductNumLimit> jpoProductNumLimits = jpoProductNumLimitManager.getAll();
    	for (JpoProductNumLimit jpoProductNumLimit : jpoProductNumLimits) {
    		productNos.add(jpoProductNumLimit.getProductNo());
		}
    	
    	JpoProductNumLimit plimit =  jpoProductNumLimitManager.getNum(pro);
    	String statetime = null;
    	String endtime = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(productNos.contains(pro)){
    		proNoCount = plimit.getNum();;
    		if(!"".equals(plimit.getStartime()) && plimit.getStartime() != null){
    			statetime = sdf.format(plimit.getStartime());
    		}
    		if(!"".equals(plimit.getEndtime()) && plimit.getEndtime() != null){
    			endtime = sdf.format(plimit.getEndtime());
    		}
    		
    	}else{
    		proNoCount = -1;
    	}
    	
    	log.info("product sum is:["+proNoCount+"]");	
//    	try {
    		/*bug:2754*/
//			Date sdate = sdf.parse(statetime);
//			Date edate = sdf.parse(endtime);
//			Date curDate = new Date();
//			if(curDate.after(sdate) && curDate.before(edate)){
				while (its1.hasNext()) {
					
					JpoShoppingCartOrderList jpoCatrList = 
		    				(JpoShoppingCartOrderList) its1.next();
		    		
		    		String proNo =jpoCatrList.getJpmProductSaleTeamType().
		    				getJpmProductSaleNew().getProductNo();
		    		
					if(pro.equalsIgnoreCase(proNo))
					{
						//已审数量
						proSum = jpoMemberOrderListManager.getProSumByProNo(proNo, statetime, endtime);
						countQty = jpoCatrList.getQty();
						
						log.info("proNoCount =["+proNoCount+"] " +
			    				"and proSum is=["+proSum+"] countQty="+countQty);
						/*
						 * 购买数量大于剩余数量, 或者统计数量大于等于库存数量
						 */
						if(proNoCount!= -1 && ( proSum+countQty) > proNoCount ){
							log.info("最多：" + (proNoCount-proSum) +"  实际：" + countQty);
			    			return proNoCount-proSum;
			    		}
					}
		    	}
//			}
//		} catch (ParseException e) {
//			log.error(e);
//		}
		return -1;
	}
	
	/**
	 * 验证产品购买数量是否超过限制 (2015 09 财月)
	 * @param jpoMemberOrder
	 * @return true or false
	 */
	protected boolean isOverQty(JpoMemberOrder jpoMemberOrder){
		
		Integer proSum=0, proNoCount=-1,countQty=0;
		List<String> productNos = new ArrayList();
    	List<JpoProductNumLimit> jpoProductNumLimits = jpoProductNumLimitManager.getAll();
    	for (JpoProductNumLimit jpoProductNumLimit : jpoProductNumLimits) {
    		productNos.add(jpoProductNumLimit.getProductNo());
		}
    	
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	
		while (its1.hasNext()) {
			
    		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
    		
    		String proNo =jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
    		
    		if(productNos.contains(proNo)){
    			
    			JpoProductNumLimit plimit =  jpoProductNumLimitManager.getNum(proNo);
    			proNoCount = plimit.getNum();;
    			
    			String statetime = null;
    	    	String endtime = null;
    	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			if(!"".equals(plimit.getStartime()) && plimit.getStartime() != null){
        			statetime = sdf.format(plimit.getStartime());
        		}
        		if(!"".equals(plimit.getEndtime()) && plimit.getEndtime() != null){
 
        			endtime = sdf.format(plimit.getEndtime());
        		}
    			proSum = jpoMemberOrderListManager.getProSumByProNo(proNo, statetime, endtime);
    			countQty = jpoMemberOrderList.getQty();
        		
    			/*
    			 * 购买数量大于剩余数量, 或者统计数量大于等于库存数量
    			 */
    			if((proNoCount-proSum) < countQty || proSum >= proNoCount){
        			return true;
        		}
    		}
    	}
		return false;
	} 
	
	/**
	 * 验证产品购买数量是否超过限制
	 * @param jpoMemberOrder
	 * @return true or false
	 */
	protected boolean isBuyPro(JpoMemberOrder jpoMemberOrder){
		
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	
    	Integer proSum=0, proNoCount=0,countQty=0;
		String proCount_str = ConfigUtil.
				getConfigValue("CN", "productqz.count");
		log.info("product sum is:["+proCount_str+"]");
		
		if(StringUtils.isNotBlank(proCount_str)){
			proNoCount = Integer.parseInt(proCount_str);
		}
    		
		while (its1.hasNext()) {
			
    		JpoMemberOrderList jpoMemberOrderList = 
    				(JpoMemberOrderList) its1.next();
    		
    		String proNo =jpoMemberOrderList.getJpmProductSaleTeamType().
    				getJpmProductSaleNew().getProductNo();
    		
    		countQty = jpoMemberOrderList.getQty();
    		
    		for(int i=0; i< GlobalVar.proNumList.size(); i++)
    		{
    			if(proNo.equalsIgnoreCase(GlobalVar.proNumList.get(i)))
    			{
    				proSum = jpoMemberOrderListManager.getSumQtyByProNo(proNo);
    				
    				log.info("proNoCount =["+proNoCount+"] " +
    	    				"and proSum is=["+proSum+"] countQty="+countQty);
    				/*
    				 * 购买数量大于剩余数量, 或者统计数量大于等于库存数量
    				 */
    				if((proNoCount-proSum) < countQty || proSum >= proNoCount){
    	    			return true;
    	    		}
    			}
    		}
    	}
		return false;
	}
	
	/**
	 * 判断是否单独购买九款特殊商品（积分换购),返回false代表是
	 * @param jpoMemberOrder：订单
	 * @return true:包含
	 */
	protected boolean jpoIsOnly(JpoMemberOrder jpoMemberOrder){
		boolean isOnly = true;
		
		Iterator its2 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while(its2.hasNext()){
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its2.next();
    		String carProNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();//商品
    		
    		if(GlobalVar.jpoList.contains(carProNo)){
    			
    			isOnly = true;//在9款产品范围内
    		}else{
    			
    			return false;//只要查询到一款不在范围内，直接return false
    		}
		}	
		
		return isOnly;
	}
	
	
	/**
	 * @Description 判断指定商品在订单中是否存在， 如有存在则返回true
	 * @author houxyu
	 * @date 2016-5-19
	 * @param jpoMemberOrder
	 * @param jpoList
	 * @return
	 */
	protected boolean jpoIsOnly(JpoMemberOrder jpoMemberOrder,List<String> jpoList){
		boolean isOnly = false;
		
		Iterator its2 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while(its2.hasNext()){
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its2.next();
    		String carProNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();//商品
    		
    		if(jpoList.contains(carProNo)){
    			isOnly = true;
    		}
    		
    		if(!jpoList.contains(carProNo)){
				isOnly = false;
				break;
			}
		}
		return isOnly;
	}
	
	@Autowired
	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	@Autowired
	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public final BaseFormController setCancelView(String cancelView) {
		this.cancelView = cancelView;
		return this;
	}

	public final String getCancelView() {
		// Default to successView if cancelView is invalid
		if (this.cancelView == null || this.cancelView.length() == 0) {
			return getSuccessView();
		}
		return this.cancelView;
	}

	public final String getSuccessView() {
		return this.successView;
	}

	public final BaseFormController setSuccessView(String successView) {
		this.successView = successView;
		return this;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	protected ServletContext getServletContext() {
		return servletContext;
	}
	/**
	 * 判断会员是否冻结状态，重消期别是否过期，是否死点
	 * 若是冻结状态，或者死点，或者过期则返回true
	 * @param order
	 * @param user
	 * @return true or false
	 */
	protected boolean validateOrder(JpoMemberOrder order,JsysUser user){
		
		user = jsysUserManager.get(user.getUserCode());
		JmiMember member = user.getJmiMember();
		log.info("member active is:"+member.getActive()+" "
				+ "and freeStatus :"+member.getFreezeStatus());
		if(! order.getOrderType().equals("3")){
			//冻结会员
			if(member.getFreezeStatus()!=null && 
					member.getFreezeStatus() == 1){
				return true;
			}
			
			List list = jbdMemberFrozenManager.getJbdMemberFrozen();
			if(list.contains(order.getSysUser().getUserCode())){
				return false;
			}
			//去除免重销的时间验证
/*			Integer validweek = member.getValidWeek();
			if(validweek!=null){
				Calendar curDate = Calendar.getInstance();
				Calendar validweek_date= Calendar.getInstance();
				
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
				try {
					validweek_date.setTime(sf.parse(validweek.toString()));
					
					//int lastDay = validweek_date.getActualMaximum(Calendar.DAY_OF_MONTH);
					//validweek_date.set(Calendar.DAY_OF_MONTH,lastDay);
					
					validweek_date.set(Calendar.MONTH, validweek_date.get(Calendar.MONTH)+1);
					validweek_date.set(Calendar.DAY_OF_MONTH, 1);
					
					if(curDate.after(validweek_date)){
						return true;
					}		
				} catch (ParseException e) {
					log.error("",e);
					return true;
				}
			}*/
		}
		
		//死点会员
		if("1".equals(member.getActive())){
    		return true;
		}
		return false;
	}
	/**
	 * 验证订单是否已支付,已支付状态返回true
	 * @param order
	 * @return
	 */
	protected boolean orderIsPayVali(JpoMemberOrder order){
		if(order.getIsPay().equals("1") || order.getStatus().equals("2")){
			return true;
		}
		return false;
	}
}
