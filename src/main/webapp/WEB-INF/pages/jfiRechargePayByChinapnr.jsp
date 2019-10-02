<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPay99billDetail.heading"/></content>
<style>
.ordersDetails td { height:45px; line-height:45px;}
.ft-green {
	COLOR: #41ab01
}
.div-t{
	width:100%;background-color: #f4f6fc;padding-left: 40px;padding-top: 15px;padding-bottom: 20px;
}
</style>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/chinapnrUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Chinapnr.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<form id="jfiPayChinapnrForm" name="jfiPayChinapnrForm" method="POST" action="" target="_blank">
	<input type=hidden id="Version" name="Version" value="">
	<input type=hidden id="CmdId" name="CmdId" value="">
	<input type=hidden id="MerId" name="MerId" value="">
	<input type=hidden id="OrdId" name="OrdId" value="">
	<input type=hidden id="OrdAmt" name="OrdAmt" value="">
	<input type=hidden id="CurCode" name="CurCode" value="">
	<input type=hidden id="Pid" name="Pid" value="">
	<input type=hidden id="RetUrl" name="RetUrl" value="">
	<input type=hidden id="BgRetUrl" name="BgRetUrl" value="">
	<input type=hidden id="MerPriv" name="MerPriv" value="">
	<input type=hidden id="GateId" name="GateId" value="">
	<input type=hidden id="UsrMp" name="UsrMp" value="">
	<input type=hidden id="DivDetails" name="DivDetails" value="">
	<input type=hidden id="PayUsrId" name="PayUsrId" value="">
	<input type=hidden id="ChkValue" name="ChkValue" value="">

    <h2 class="title mgb20">充值</h2>
    <table width="100%" class="ordersDetails">
		
        <tbody>
        	<tr>
				<!--
				<div class="div-t">
				充值帐户：${jsysUser.userCode }(${jsysUser.userName })&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				帐户余额：<b class="ft-green">${bankbook }</b>元
				<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/>
				</div>
				-->
				<td class="tr">充值帐户：</td>
				<td>${jsysUser.userCode }</td>
				<td class="tr">帐户余额：</td>
				<td><b class="ft-green">${bankbook }</b>元<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/></td>
            </tr>

            
            <tr>
                <td class="tr">充值金额：</td>
                <td colspan="3">
                    
                    <input id="orderAmount1" name="orderAmount1" type="text" value="" size="10"/>&nbsp;元&nbsp;&nbsp;
                    
                </td>
            </tr>
            
            
			<tr>
                <td class="tr"><input type="hidden" id="payerContact" name="payerContact" value=""/></td>
                <td>
                    <a href="javascript:void(0);" class="btn_common corner2" name="cancel" onclick="actionPost();">确认付款</a>
                </td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            
        </tbody>
    </table>
</form>

<div class="mask" id="mask"></div>
<div class="popupAddr2 abs" id="popupAddr" style="display:none;width:500px;height:256px;">
	<div class="mgb20" style="height:24px;line-height:24px;">
		<span class="fl bold">支付</span>
		
	</div>

	
	<p>请您在新打开的页面进行支付，支付完成前请不要关闭该页面</p>
	<p>订单号：<span id="pay_order_id"></span></p>
	<div class="fb" style="width:294px;overflow:hidden;margin:0 auto;">
		<a href="javascript:void(0);" class="btn_common corner2 fl" id="js_confirm" onclick="payOK()">已完成支付</a>
		<a href="javascript:void(0);" class="btn_common corner2 fl" onclick="payERROR()">支付遇到问题</a>
	</div>
</div>
	
<script>

function actionPost(){
	
	var orderAmount1=document.getElementById("orderAmount1");
	if(orderAmount1.value==""){
		alert("充值金额不能为空");
		orderAmount1.focus();
		return;
	}
	if(orderAmount1.value <= 0){
		alert("充值金额必须大于0");
		orderAmount1.focus();
		return;
	}
	if(orderAmount1.value > 600000){
		alert("单笔充值金额不能超过60W");
		orderAmount1.focus();
		return;
	}
	var regu = "^[0-9]+[\.][0-9]{0,3}$"; 
	var re = new RegExp(regu); 
	
	var regu2 = "^[0-9]+$"; 
	var re2 = new RegExp(regu2); 
	if(re.test(orderAmount1.value)!=true && orderAmount1.value.search(re2) == -1){
		
		alert("充值金额不符合要求");
		orderAmount1.focus();
		return;
	}
	
	if(orderAmount1.value>0){
	
		if(isFormPosted()){
			var chinapnr =  new Chinapnr();
			chinapnr.ordAmt = $("#orderAmount1").attr("value");
			chinapnr.ordId = '${param.orderId}';
			chinapnr.merPriv = '[${jsysUser.userCode},${flag}]';
			chinapnrUtil.getChinapnr(chinapnr,${flag},callBack1);
			popupNewAddr();
		}
	}
}
function callBack1(valid){
	
	if(valid==null){
		alert('ERROR!');
	}else{
		
		$("#Version").attr("value",valid.version);
		$("#CmdId").attr("value",valid.cmdId);
		$("#MerId").attr("value",valid.merId);
		$("#OrdId").attr("value",valid.ordId);
		$("#OrdAmt").attr("value",valid.ordAmt);
		$("#CurCode").attr("value",valid.curCode);
		$("#Pid").attr("value",valid.pid);
		$("#RetUrl").attr("value",valid.retUrl);
		$("#BgRetUrl").attr("value",valid.bgRetUrl);
		$("#MerPriv").attr("value",valid.merPriv);
		$("#GateId").attr("value",valid.gateId);
		$("#UsrMp").attr("value",valid.usrMp);
		$("#DivDetails").attr("value",valid.divDetails);
		$("#PayUsrId").attr("value",valid.payUsrId);
		$("#ChkValue").attr("value",valid.chkValue);
		
		$("#jfiPayChinapnrForm").attr("action",valid.postUrl);
		$("#jfiPayChinapnrForm").attr("target",'_blank');
		$("#jfiPayChinapnrForm").submit();
	}
}

function payOK(){

	window.location.href = "jfiBankbookJournals";
}
function payERROR(){

	window.location.href = "jfiRechPayByChinapnr";
}

</script>
