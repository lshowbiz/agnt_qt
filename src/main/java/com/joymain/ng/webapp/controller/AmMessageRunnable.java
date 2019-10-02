package com.joymain.ng.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import service.MsgSendService;

import com.joymain.ng.Constants;
import com.joymain.ng.log.util.LogUtil;
import com.joymain.ng.model.AmMessage;
import com.joymain.ng.model.AmMessageForCrm;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.ListUtil;

/**
 * 会员信息新增、修改接口任务
 * AmMessageRunnable
 *  
 * @author  JM
 * @version  2015-1-30
 * @see  AmMessageRunnable
 * @since  1.0
 */
public class AmMessageRunnable implements Runnable
{
    private static final Log LOG = LogFactory.getLog(AmMessageRunnable.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final MsgSendService SEND = new MsgSendService();
    private AmMessage amMessage;
    private String type;
    
    public AmMessageRunnable(AmMessage amMessage,String type){
        this.amMessage=amMessage;
        this.type=type;
    }
    @Override
    public void run()
    {
        if("addOrUpdate".equals(type)){
            addOrUpdateAmMessage();
        } else if("delete".equals(type)){
            deleteAmMessage();
        }
        
    }
    
    public void addOrUpdateAmMessage(){
    	JSONObject json = null;//Modify By WuCF 20150430s
    	String aa = ""	;
        try {
            //新注册会员推送至CRM
            Date start = new Date();
            LOG.info("会员新增/修改留言信息推送至CRM开始！"+SDF.format(start));
            
            String msgclassno = amMessage.getMsgClassNo();
            msgclassno = msgclassno==null?null:ListUtil.getListValue("zh_CN", "msgclassno", msgclassno);
            LOG.info("am message is :"+amMessage);
            AmMessageForCrm amMessageForCrm = new AmMessageForCrm(amMessage.getUniNo().toString(),
                    amMessage.getSenderNo(),msgclassno,
                    amMessage.getSenderName(),DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss",amMessage.getIssueTime()),
                    amMessage.getSubject(),amMessage.getContent(),
                    DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss",amMessage.getReplyTime()),amMessage.getReplyContent(),
                    amMessage.getReceiverName(),"");
            
            json = JSONObject.fromObject(amMessageForCrm);
            LOG.info("am message for crm json is :"+json);
            SEND.setSender(Constants.CRM_SEND);
            aa = SEND.post(json.toString(), "servicecenter.leavemsg_add");
            Date end = new Date();
            LOG.info("会员新增/修改留言信息推送至CRM结束！"+SDF.format(end)+"，耗时："+(end.getTime()-start.getTime())+"ms,共推送1条记录！");
        }catch(Exception e){
            e.printStackTrace();
            LOG.error("会员新增/修改留言信息推送至CRM发送错误！", e);
        }finally{
        	/*//=============================接口日志写入开始 Modify By WuCF 20150430 添加日志记录
			JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
			jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
			jocsInterfaceLog.setSender(Constants.CRM_SEND);//数据的接收方
			jocsInterfaceLog.setMethod("servicecenter.leavemsg_add");//方法名称
			jocsInterfaceLog.setContent(json.toString());//发送内容content
			jocsInterfaceLog.setReturnDesc(aa);//返回内容
			
			LogUtil.addJocsInterface(jocsInterfaceLog);//写入日志操作
			//=============================接口日志写入完毕
*/        }
    }
    
    public void deleteAmMessage(){
        try {
            //新注册会员推送至CRM
            Date start = new Date();
            LOG.info("会员删除留言信息推送至CRM开始！"+SDF.format(start));
            
            Map<String,String> map = new HashMap<String, String>();
            map.put("message_bn", amMessage.getUniNo().toString());
            
            JSONObject json = JSONObject.fromObject(map);
            SEND.setSender(Constants.CRM_SEND);
            SEND.post(json.toString(), "servicecenter.leavemsg_delete");
            Date end = new Date();
            LOG.info("会员删除留言信息推送至CRM结束！"+SDF.format(end)+"，耗时："+(end.getTime()-start.getTime())+"ms,共推送1条记录！");
        }catch(Exception e){
            e.printStackTrace();
            LOG.error("会员删除留言信息推送至CRM发送错误！", e);
        }
    }
}
