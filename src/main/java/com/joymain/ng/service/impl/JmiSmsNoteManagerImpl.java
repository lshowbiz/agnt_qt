package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.Date;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiSmsNoteDao;
import com.joymain.ng.model.JmiSmsNote;
import com.joymain.ng.model.JpmSmssendInfo;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiSmsNoteManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.SmsSend;
import com.joymain.ng.util.StringUtil;

@Service("jmiSmsNoteManager")
@WebService(serviceName = "JmiSmsNoteService", endpointInterface = "com.joymain.ng.service.JmiSmsNoteManager")
public class JmiSmsNoteManagerImpl extends GenericManagerImpl<JmiSmsNote, Long> implements JmiSmsNoteManager {
    JmiSmsNoteDao jmiSmsNoteDao;

    @Autowired
    public JmiSmsNoteManagerImpl(JmiSmsNoteDao jmiSmsNoteDao) {
        super(jmiSmsNoteDao);
        this.jmiSmsNoteDao = jmiSmsNoteDao;
    }
    @Autowired
    private JpmProductSaleNewManager jpmProductSaleNewManager;
	
	public Pager<JmiSmsNote> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiSmsNoteDao.getPager(JmiSmsNote.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	
	public String sendSms(String phone){

		String verifyCode=StringUtil.generateCode(6);
		//调用短信接口=======
		String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
		String url2 = ListUtil.getListValue("CN", "smssend.url", "2");

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String validTime = (String)Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("ec.sms.valid.time");
		if(!StringUtil.isEmpty(phone)){
			phone = phone.trim();
		}
		String msgInfo  = "亲爱的中脉家人，您的验证码为:"+verifyCode+"，请在"+validTime+"秒内输入验证，逾期后您需要重新获取一个验证码。";
		//调用短信发送平台方法
		//SmsSend.sendSms(url1, url2, phone, msgInfo);
		
		//
		//String [] result=new String[2];
		Date curDate=new Date();

		   
		long validTimeLong=StringUtil.formatLong(validTime);
		
		JmiSmsNote resJmiSmsNote=this.getJmiSmsNoteByUserCode(defSysUser.getUserCode());
		
		if(resJmiSmsNote!=null){
			long res_time=resJmiSmsNote.getCreateTime().getTime();
			long cur_time=curDate.getTime();
			long time=(cur_time-res_time)/1000;
			if(time<validTimeLong){
				return "验证码已发送";
			}
			
		}
		
		//调用短信发送平台方法
		String msg = SmsSend.sendSmsVerifyCode(verifyCode, validTime, phone);
		
		if(StringUtil.isEmpty(msg)){
			JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
			jpmSmssendInfo.setSmsType("4");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
			jpmSmssendInfo.setSmsRecipient(defSysUser.getUserCode());//短信接收人:用户会员编号
			jpmSmssendInfo.setSmsMessage(msgInfo);//短信内容
			jpmSmssendInfo.setSmsTime(new Date());//发送时间
			jpmSmssendInfo.setSmsOperator(defSysUser.getUserCode());//操作人，可以填写空
			jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
			jpmSmssendInfo.setRemark("会员激活瓜藤网");//备注
			jpmSmssendInfo.setPhoneNum(phone);//手机号
			jpmProductSaleNewManager.saveJpmSmssendInfo(jpmSmssendInfo);
			
			JmiSmsNote jmiSmsNote=new JmiSmsNote();
			jmiSmsNote.setPhone(phone);
			jmiSmsNote.setVerifyCode(verifyCode);
			jmiSmsNote.setCreateTime(curDate);
			jmiSmsNote.setUserCode(defSysUser.getUserCode());
			this.save(jmiSmsNote);
		}
		
		return msg;
		
		
	}

	public JmiSmsNote getJmiSmsNoteByUserCode(String userCode) {
		return jmiSmsNoteDao.getJmiSmsNoteByUserCode(userCode);
	}

	public JmiSmsNote getJmiSmsNoteByUserCodeStatus(String userCode) {
		return jmiSmsNoteDao.getJmiSmsNoteByUserCodeStatus(userCode);
	}
}