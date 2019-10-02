package com.joymain.ng.util.chinapnr;
@SuppressWarnings({ "serial" })
public class Chinapnr implements java.io.Serializable {
	
	private String Version;// 版本号
	private String CmdId = "Buy";// 消息类型
	private String MerId;// 商户号
	private String OrdId; // 订单号
	private String OrdAmt; // 订单金额
	private String CurCode = "RMB";// 币种
	private String Pid;// 商品编号
	private String RetUrl;// 页面返回地址
	private String BgRetUrl;// 后台返回地址
	private String MerPriv;// 商户私有数据项
	private String GateId;// 网关号 银行 ID
	private String UsrMp;// 购买者手机号
	private String DivDetails;// 分账明细
	private String PayUsrId;// 付款人用户号
	private String ChkValue;// 数字签名
	private String postUrl;// 支付跳转页面地址

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getCmdId() {
		return CmdId;
	}

	public void setCmdId(String cmdId) {
		CmdId = cmdId;
	}

	public String getMerId() {
		return MerId;
	}

	public void setMerId(String merId) {
		MerId = merId;
	}

	public String getOrdId() {
		return OrdId;
	}

	public void setOrdId(String ordId) {
		OrdId = ordId;
	}

	public String getOrdAmt() {
		return OrdAmt;
	}

	public void setOrdAmt(String ordAmt) {
		OrdAmt = ordAmt;
	}

	public String getCurCode() {
		return CurCode;
	}

	public void setCurCode(String curCode) {
		CurCode = curCode;
	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

	public String getRetUrl() {
		return RetUrl;
	}

	public void setRetUrl(String retUrl) {
		RetUrl = retUrl;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getMerPriv() {
		return MerPriv;
	}

	public void setMerPriv(String merPriv) {
		MerPriv = merPriv;
	}

	public String getGateId() {
		return GateId;
	}

	public void setGateId(String gateId) {
		GateId = gateId;
	}

	public String getUsrMp() {
		return UsrMp;
	}

	public void setUsrMp(String usrMp) {
		UsrMp = usrMp;
	}

	public String getDivDetails() {
		return DivDetails;
	}

	public void setDivDetails(String divDetails) {
		DivDetails = divDetails;
	}

	public String getPayUsrId() {
		return PayUsrId;
	}

	public void setPayUsrId(String payUsrId) {
		PayUsrId = payUsrId;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

}
