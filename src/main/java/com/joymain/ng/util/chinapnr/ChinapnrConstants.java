package com.joymain.ng.util.chinapnr;

import com.joymain.ng.util.bill99.Bill99Constants;

import java.util.HashMap;
import java.util.Map;

public class ChinapnrConstants {

    /**移动端参数 end**/
    public static final String merCustId="6666000000036490";
    public static final String inCustId="6666000000036490";
    public static final String inAcctId="38883";
    public static final String appId="wx2a5538052969956e";
    public static final String url="http://mertest.chinapnr.com/npay/merchantRequest";
    /**移动端参数 end**/

    public static Map<String, Map<String, String>> account = new HashMap<String, Map<String, String>>();;
    static {

        //手机端汇付微信pay
        Map app1 = new HashMap();
        app1.put("memberCode", "6666000000036490");
        app1.put("inAcctId", "38883");
        app1.put("merchantName", "手机端商户号");
        app1.put("password", "123456");
        app1.put("pfxFile", "key/app/hftxPay/mulan.pfx");
        app1.put("cerFile", "key/app/hftxPay/CFCA_ACS_TEST_OCA31.cer");
        //固定值
        app1.put("hfMerId", "100001");
        app1.put("appId", "wx2a5538052969956e");
        account.put("hfmobile", app1);
    }


}