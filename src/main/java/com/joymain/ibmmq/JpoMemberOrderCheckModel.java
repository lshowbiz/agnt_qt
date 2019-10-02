package com.joymain.ibmmq;

import java.io.Serializable;

import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;

/**
 * 内部消息对象。
 * @author 
 *
 */
public class JpoMemberOrderCheckModel implements Serializable
{
    /**
     * 订单
     */
    private JpoMemberOrder jpoMemberOrder;
    
    /**
     * 操作人
     */
    private JsysUser jsysUser;
    
    //来源
    private String dataType;

	public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}

	public JsysUser getJsysUser() {
		return jsysUser;
	}

	public void setJsysUser(JsysUser jsysUser) {
		this.jsysUser = jsysUser;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
    
    

    
}
