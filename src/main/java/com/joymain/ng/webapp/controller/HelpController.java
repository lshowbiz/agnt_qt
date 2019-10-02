package com.joymain.ng.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.SalesAnalysisManager;
import com.joymain.ng.util.StringUtil;

@Controller
public class HelpController {
	private final Log log = LogFactory.getLog(HelpController.class);
	private SalesAnalysisManager salesAnalysisManager; 

	/**
     * show message page
     * @param request
     * @param response
     * @return string
     */
    @RequestMapping(value="/loginform/help",method=RequestMethod.GET)
    public String showMessage(HttpServletRequest request,HttpServletResponse response){
		String returnStr = "";
		String flag = request.getParameter("flag");  
		if("zhucehuiyuan".equals(flag)){//1.如何注册新会员
			returnStr = "zhucehuiyuan";
		}else if("denglu".equals(flag)){//2.新会员登录
			returnStr = "denglu";
		}else if("bank".equals(flag)){//3.开通网上银行
			returnStr = "bank";
		}else if("ziliao".equals(flag)){//4.完善个人资料
			returnStr = "ziliao";
		}else if("baodan".equals(flag)){//5.新会员如何报单
			returnStr = "baodan";
		}else if("payment".equals(flag)){//6.如何支付订单
			returnStr = "payment";
		}else if("chaxun".equals(flag)){//7.如何查询订单
			returnStr = "chaxun";
		}else if("queren".equals(flag)){//8.查询发货信息
			returnStr = "queren";
		}else if("shenqingdianpu".equals(flag)){//9.二级店铺管理
			returnStr = "shenqingdianpu";
		}else if("erbu".equals(flag)){//10.二级店铺首购订单
			returnStr = "erbu";
		}else if("tixian".equals(flag)){//11.提现查询
			returnStr = "tixian";
		}else if("jifen".equals(flag)){//12.积分换购
			returnStr = "jifen";
		}else if("chanyehuajijin".equals(flag)){//13.产业化基金
			returnStr = "chanyehuajijin";
		}else if("guateng".equals(flag)){//101.瓜藤网指南
			returnStr = "guateng";
		}else if("guatengmobile".equals(flag)){//102.瓜藤网指南：手机端
			returnStr = "guatengmobile";
		}else if("guatengpc".equals(flag)){//103.瓜藤网指南：PC
			returnStr = "guatengpc";
		}else if("tcckzzcx".equals(flag)){//弹出窗口阻止程序关闭步骤
			returnStr = "tcckzzcx";
		}else if ("newHelp".equals(flag)) {
			returnStr = "newHelp";
		}else{
			returnStr = "help";
		}
    	return returnStr;
    }
    
    
    /**************************getter/setter*****************************/
  	@Autowired
	public void setSalesAnalysisManager(SalesAnalysisManager salesAnalysisManager) {
		this.salesAnalysisManager = salesAnalysisManager;
	}
  	
  	
}
