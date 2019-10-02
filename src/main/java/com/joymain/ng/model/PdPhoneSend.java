package com.joymain.ng.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 手机端发货单号查询一实体类PdPhoneSend
 * @author fu 2016-04-22
 *
 */
public class PdPhoneSend {
    public List<PdPhoneSendInfo> pdPhoneSendInfoList = new ArrayList();
    
	public List<PdPhoneSendInfo> getPdPhoneSendInfoList() {
		return pdPhoneSendInfoList;
	}

	public void setPdPhoneSendInfoList(List<PdPhoneSendInfo> pdPhoneSendInfoList) {
		this.pdPhoneSendInfoList = pdPhoneSendInfoList;
	}
     
     
}
