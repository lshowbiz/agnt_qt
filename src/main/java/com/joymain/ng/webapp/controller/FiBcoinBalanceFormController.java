package com.joymain.ng.webapp.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.joymain.ng.Constants;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.InwIntegrationTotal;
import com.joymain.ng.model.JmiEcmallNote;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.MiCoinLog;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.FiBcoinJournalManager;
import com.joymain.ng.service.InwIntegrationTotalManager;
import com.joymain.ng.service.JmiEcmallNoteManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.MiCoinLogManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
/**
 * 积分迁移
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/fiBcoinBalanceform*")
public class FiBcoinBalanceFormController extends BaseFormController {
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;
    private FiBcoinJournalManager fiBcoinJournalManager;

    @Autowired
    private MiCoinLogManager miCoinLogManager;
    @Autowired
    private JmiEcmallNoteManager jmiEcmallNoteManager;
    @Autowired
    public void setFiBcoinJournalManager(FiBcoinJournalManager fiBcoinJournalManager) {
        this.fiBcoinJournalManager = fiBcoinJournalManager;
    }

    @Autowired
    private InwIntegrationTotalManager inwIntegrationTotalManager;
    
    @Autowired
    public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
		this.fiBcoinBalanceManager = fiBcoinBalanceManager;
	}

    @Autowired
    private JmiMemberManager jmiMemberManager;
    public FiBcoinBalanceFormController() {
        setCancelView("redirect:fiBcoinJournals");
        setSuccessView("redirect:fiBcoinJournals");
    }
    
	@ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiBcoinBalance showForm(HttpServletRequest request)
    throws Exception {
		
		//当前用户
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jsysUser.getUserCode());
    	InwIntegrationTotal inwIntegrationTotal=inwIntegrationTotalManager.getInwIntegrationTotal(jsysUser.getUserCode());
    	request.setAttribute("inwIntegrationTotal", inwIntegrationTotal);
    	JmiMember jmiMember=jmiMemberManager.get(jsysUser.getUserCode());
    	//找出绑定的帐号
    	Map map=new HashMap();
		String ecMallStatus="";
		if("1".equals(jsysUser.getJmiMember().getEcMallStatus())){
			ecMallStatus="1";
			map.put("ec_mall_phone", jmiMember.getEcMallPhone());
			map.put("user_code", jsysUser.getUserCode());
		}else{
			map=jmiMemberManager.getSamePapernumberUserCode(jmiMember.getPapernumber());
			if(map!=null){
				ecMallStatus="1";
				//request.setAttribute("ecMallInfo", map);
			}else{
				ecMallStatus="0";
			}
		}

		request.setAttribute("ecMallInfo", map);
		request.setAttribute("ecMallStatus", ecMallStatus);
    	
        return fiBcoinBalance;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiBcoinBalance fiBcoinBalance, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
    	
    	//获取用户输入积分数额
    	String bcoinNumTemp=request.getParameter("bcoinNum");
    	String mallType=request.getParameter("mallType");
    	String coinType=request.getParameter("coinType");
    	
    	BigDecimal bcoinNum = new BigDecimal(bcoinNumTemp);
    	
    	//当前用户
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	JmiMember jmiMember=jmiMemberManager.get(jsysUser.getUserCode());
    	
    	
    	//获取瓜藤网绑定的帐号
    	String bindingUserCode="";
    	String mobile_phone="";
    	if("1".equals(jsysUser.getJmiMember().getEcMallStatus())){
    		bindingUserCode=jsysUser.getUserCode();
    		mobile_phone=jmiMember.getEcMallPhone();
		}else{
			Map map=jmiMemberManager.getSamePapernumberUserCode(jmiMember.getPapernumber());
			if(map!=null){
				bindingUserCode=map.get("user_code").toString();
				mobile_phone=map.get("ec_mall_phone").toString();
				
			}
			
		}
    	
    	if(!StringUtil.isInteger(bcoinNumTemp)){
    		saveMessage(request, "请输入整数积分");
    		return "redirect:"+request.getHeader("Referer");
    	}
    	if(StringUtil.isEmpty(bindingUserCode)){
    		saveMessage(request, "未绑定瓜藤网帐号，无法推荐送积分");
    		return "redirect:"+request.getHeader("Referer");
    	}
    	
    	if(StringUtil.isEmpty(coinType)  ){
    		saveMessage(request, "请选择积分类型");
    		return "redirect:"+request.getHeader("Referer");
    	}
    	
    	if("2".equals(mallType) && "C".equals(coinType)){
    		saveMessage(request, "创新积分不可传至脉宝网");
    		return "redirect:"+request.getHeader("Referer");
    	}
    	if("C".equals(coinType)){
        	InwIntegrationTotal inwIntegrationTotal=inwIntegrationTotalManager.getInwIntegrationTotal(jsysUser.getUserCode());
    		if(inwIntegrationTotal==null || new BigDecimal(inwIntegrationTotal.getTotalPoints()).compareTo(bcoinNum)==-1){

        		saveMessage(request, "操作错误！错误原因：创新积分余额不足或输入有误！");
        		return "redirect:"+request.getHeader("Referer");
    		}
    		
    		
    	}else if("B".equals(coinType)){

        	//验证
        	FiBcoinBalance bcoinBalance = fiBcoinBalanceManager.get(jsysUser.getUserCode());
        	if(bcoinBalance==null || bcoinBalance.getBalance().compareTo(bcoinNum)==-1){
        		
        		saveMessage(request, "操作错误！错误原因：欢乐积分余额不足或输入有误！");
        		return "redirect:"+request.getHeader("Referer");
        	}
        	
    	}
    	//积分迁移

    	try{
    		MiCoinLog miCoinLog=null;
    		if("B".equals(coinType)){

        		miCoinLog=fiBcoinJournalManager.sendBcoinsToShangCheng(jsysUser, bcoinNum,mallType,mobile_phone);
        		
        	}else if("C".equals(coinType)){

        		miCoinLog=inwIntegrationTotalManager.sendCoin( bcoinNum, jsysUser, mallType,mobile_phone);
        	}
    		
    		
    		
    		//发送到积分瓜藤网

			Date curDate=new Date();

    		String key = "";
    		String mall_url = "";
    		
    		String code="";
    		String url="";
    		if("1".equals(mallType)){
    			key = (String)Constants.sysConfigMap.get(jsysUser.getCompanyCode()).get("ec.mall.key");
    			mall_url = (String)Constants.sysConfigMap.get(jsysUser.getCompanyCode()).get("ec.mall.url");
    			


    			code=StringUtil.encodePassword(curDate.getTime()+mobile_phone+bcoinNum+key+miCoinLog.getId()+coinType, "MD5");
    			//unique_code
        		url="get_points.php?mobile_phone="+mobile_phone+"&time="+curDate.getTime()+"&point="+miCoinLog.getCoin()+
        		"&unique_code="+miCoinLog.getId()+"&coinType="+coinType+"&code="+code+"&flag=EC";
    			
    		}else if("2".equals(mallType)){//脉宝网===已 不用
    			//key = (String)Constants.sysConfigMap.get(jsysUser.getCompanyCode()).get("maibao.mall.key");
    			mall_url = (String)Constants.sysConfigMap.get(jsysUser.getCompanyCode()).get("maibao.mall.url");
    			
    			code = "uniqueId="+miCoinLog.getId()+"&userCode="+jsysUser.getUserCode()+"&coin="+miCoinLog.getCoin()+"jmtop@coin!@#$%";
    			
    			url="api/fiBcoinReceive?"+"uniqueId="+miCoinLog.getId()+"&userCode="+jsysUser.getUserCode()+"&coin="
    			+miCoinLog.getCoin()+"&signMsg="+StringUtil.encodePassword(code,"MD5");
    			
    		}
    		
    		
    		
    		
    		
    		
    		WebResponse webResponse = null;
    		try {

    	    	
    	    	

    	    	WebConversation webConversation = new WebConversation();  
    	    	
    	    	for (int i = 0; i < 5; i++) {
    	    		try {
    	        		webResponse = webConversation.getResponse(mall_url+url); 
    	    			if(webResponse!=null){
    	    				break;
    	    			}
    				} catch (Exception e) {
    					
    					if(i>=4){
    						throw new AppException(e);
    					}
    				} 
    				
    			}
    	    	
    	    	
    	    	
        		
        		String resturn_info="";

        		Double resturn_code=0.0; 
        		if("1".equals(mallType)){
        			Gson gson=new Gson();
            		Map map=gson.fromJson(webResponse.getText(), Map.class);
            		
            		if(map.get("code")==null){
            			throw new AppException("code为空");
            	    }
        	    	
            	    resturn_code=StringUtil.formatDouble(map.get("code").toString());
            	    resturn_info=(String) map.get("info");
        		}else if("2".equals(mallType)){
        			String str=webResponse.getText();
        			if(StringUtil.isEmpty(str)){
        				throw new AppException("异常");
        			}
        			if("1".equals(str)){
        				resturn_code=1d;
        			}else if("2".equals(str)){
        				resturn_code=0d;
        			}else if("3".equals(str)){
        				resturn_code=-1d;
        			}
        		}
        		
        		
        		
        		
        	    

        	    /**
        	     * resturn_code -1 重复 0 失败 1 成功
        	     */
        	    if(resturn_code==0){
        	    	
        	    }else if(resturn_code>0){
        	    	miCoinLog.setSendDate(curDate);
        	    	//miCoinLog.setSendNo(mobile_phone);
        	    	miCoinLog.setStatus("2");
        	    	miCoinLogManager.save(miCoinLog);
        	    }else if(resturn_code<0){

        	    	miCoinLog.setSendDate(curDate);
        	    	//miCoinLog.setSendNo(mobile_phone);
        	    	miCoinLog.setStatus("2");
        	    	miCoinLogManager.save(miCoinLog);
        	    	
        	    }

        	    JmiEcmallNote jmiEcmallNote=new JmiEcmallNote();
        	    jmiEcmallNote.setCode(resturn_code+"");
        	    jmiEcmallNote.setCreateNo(jsysUser.getUserCode());
        	    jmiEcmallNote.setCreateTime(curDate);
        	    jmiEcmallNote.setUserCode(jsysUser.getUserCode());
        	    jmiEcmallNote.setUrl(mall_url+url);
        	    jmiEcmallNote.setInfo(resturn_info);
        	    jmiEcmallNote.setNoteTyoe("3");
        	    jmiEcmallNoteManager.save(jmiEcmallNote);
        		
        		
    		} catch (Exception e) {
    			
    			StringWriter sw = new StringWriter();
    			PrintWriter pw = new PrintWriter(sw);
    			e.printStackTrace(pw);
    			
    		    JmiEcmallNote jmiEcmallNote=new JmiEcmallNote();
    		    //jmiEcmallNote.setCode(code);
    		    jmiEcmallNote.setCreateNo(jsysUser.getUserCode());
    		    jmiEcmallNote.setCreateTime(curDate);
    		    jmiEcmallNote.setUserCode(jsysUser.getUserCode());
    		    jmiEcmallNote.setUrl(mall_url+url);
    		    //jmiEcmallNote.setInfo(resturn_info);
    		    jmiEcmallNote.setNoteTyoe("4");
    		    jmiEcmallNote.setRemark(sw.toString());
    		    jmiEcmallNoteManager.save(jmiEcmallNote);
    		    //System.out.println(sw.toString());
    		    pw.close();
    		    sw.close();
    		    
    		}  
    		
    		
    		
    		
    		
    		
    		//
    		
    		
    	}catch(AppException a){
    		a.printStackTrace();
    		saveMessage(request, "积分迁移失败！");
    		return "redirect:"+request.getHeader("Referer");
    	}
    	
    	saveMessage(request, "您已经成功迁移欢乐积分"+bcoinNumTemp+"至瓜藤网！请到瓜藤网查收！");
		return "redirect:"+request.getHeader("Referer");
    }
    
  
}
