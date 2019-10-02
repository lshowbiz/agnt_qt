package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.joymain.ng.model.JbdSendNote;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdSendNoteManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;

@Controller
public class JbdSendNoteController {
    private JbdSendNoteManager jbdSendNoteManager;
    private JmiMemberManager jmiMemberManager;
    
    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    @Autowired
    public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
        this.jbdSendNoteManager = jbdSendNoteManager;
    }

    @Autowired
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    @Autowired
    private JsysUserManager jsysUserManager;

    @RequestMapping(value="/jbdSendNotes",method = RequestMethod.GET)
    public String getJbdSendNote(HttpServletRequest request){

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	//----------------------Modify By WuCF 添加分页展示功能 
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		GroupPage page = new GroupPage("","jbdSendNotes?pageSize="+pageSize,pageSize,request);
    	
//    	List jbdSendNotes=jbdSendNoteManager.getJbdSendNote(defSysUser.getUserCode());
		List jbdSendNotes=jbdSendNoteManager.getJbdSendNotePage(page,defSysUser.getUserCode());
    	
    	request.setAttribute("page", page);//将分页信息加入到request作用域中
    	request.setAttribute("jbdSendNotes", jbdSendNotes);
    	
    	JmiMember jmiMember = jmiMemberManager.getJmiMember(defSysUser.getUserCode());
    	Integer memberLevel = jmiMember.getMemberLevel();
    	String ml = memberLevel+"";
    	request.setAttribute("memberLevel",ml);
    	
    	//查询存折余额对象 
    	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.get(defSysUser.getUserCode());
    	request.setAttribute("jfiBankbookBalance",jfiBankbookBalance);
    	return "jbdSendNotes";
    }

    
    @RequestMapping(value="/JmiMember/api/getJbdSendNote",method = RequestMethod.GET)
    @ResponseBody
    public List getJbdSendNote(String userId,String token,int pageNum,int pageSize,HttpServletRequest request){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
		pageSize = StringUtil.dealPageSize(request);

		GroupPage page = new GroupPage("","jbdSendNotes?pageSize="+pageSize,pageSize,request);

		List jbdSendNotes=jbdSendNoteManager.getJbdSendNotePage(page, user.getUserCode());
		
		
		for (int i = 0; i < jbdSendNotes.size(); i++) {
			JbdSendNote jbdSendNote=(JbdSendNote) jbdSendNotes.get(i);
			
			if("1".equals(jbdSendNote.getRegisterStatus()) || "3".equals(jbdSendNote.getRegisterStatus())){
				jbdSendNote.setRegisterStatus(LocaleUtil.getLocalText(user.getDefCharacterCoding(), "bdSendRecord.unSend"));
			}else if("2".equals(jbdSendNote.getRegisterStatus())){
				jbdSendNote.setRegisterStatus(LocaleUtil.getLocalText(user.getDefCharacterCoding(), "bdSendRecord.sended"));
			}else if("4".equals(jbdSendNote.getRegisterStatus())){
				jbdSendNote.setRegisterStatus(LocaleUtil.getLocalText(user.getDefCharacterCoding(), "busi.bd.notSend"));
			}
			
			
		}
		return jbdSendNotes;
		
		
    }
    
    @RequestMapping(value="/JmiMember/api/saveJbdSendNote",method = RequestMethod.GET)
    @ResponseBody
    public String saveJbdSendNoteJson(String userId,String token,String amount,String password){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	String returnFlag="1";
    	
    	if(!StringUtil.encodePassword(password, "MD5").equals(user.getPassword2()) || !StringUtil.isDouble(amount) || new BigDecimal(amount).compareTo(new BigDecimal("3"))!=1){
    		return returnFlag="0";
		}
    	JfiBankbookBalance jfiBankbookBalance=jfiBankbookBalanceManager.get(user.getUserCode());
		if(new BigDecimal(amount).compareTo(jfiBankbookBalance.getBalance())==1){
			return returnFlag="0";
		}
    	
    	
    	try {
    		jbdSendNoteManager.saveJbdSendNoteAndDeductBankbook(user.getCompanyCode(), user, user.getJmiMember().getUserCode(), user.getUserName(), new BigDecimal(amount), new Date().getTime()+"","2");       	
		} catch (Exception e) {
			return returnFlag="0";
		}
    	return returnFlag;
    }
}
