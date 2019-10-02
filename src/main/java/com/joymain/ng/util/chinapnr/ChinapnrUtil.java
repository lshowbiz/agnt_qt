package com.joymain.ng.util.chinapnr;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.jws.WebService;

@WebService
public interface ChinapnrUtil {
	
    /**
     * 根据会员编号获取商户号
     * 
     * @param userCode
     * @return
     */
	public Map<String, String> getMerchantByUserCode(String userCode);
	public Chinapnr getChinapnr(Chinapnr entity, int flag) throws UnsupportedEncodingException;
}
