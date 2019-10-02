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
<script src="<c:url value='/dwr/interface/chanjetUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Chanjet.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<form id="jfiPayChanjetForm" name="jfiPayChanjetForm" action="" method="post" >
	<input type="hidden" id="merchantId" name="merchantId" value="">
	<input type="hidden" id="payerContactMbl" name="payerContactMbl" value="">
	<input type="hidden" id="payerContactMal" name="payerContactMal" value="">
	<input type="hidden" id="goodsId" name="goodsId" value="">
	<input type="hidden" id="productName" name="productName" value="">
	<input type="hidden" id="productDesc" name="productDesc" value="">
	<input type="hidden" id="orderId" name="orderId" value="">
	<input type="hidden" id="orderDate" name="orderDate" value="">
	<input type="hidden" id="orderTime" name="orderTime" value="">
	<input type="hidden" id="orderAmount" name="orderAmount" value="">
	<input type="hidden" id="amtType" name="amtType" value="">
	<input type="hidden" id="platIdtfy" name="platIdtfy" value="">
	<input type="hidden" id="bankType" name="bankType" value="">
	<input type="hidden" id="businessId" name="businessId" value="">
	<input type="hidden" id="gateId" name="gateId" value="">
	<input type="hidden" id="bgUrl" name="bgUrl" value="">
	<input type="hidden" id="notifyUrl" name="notifyUrl" value="">
	<input type="hidden" id="merPriv" name="merPriv" value="">
	<input type="hidden" id="expand" name="expand" value="">
	<input type="hidden" id="expand2" name="expand2" value="">
	<input type="hidden" id="payerName" name="payerName" value="">
	<input type="hidden" id="payerCardType" name="payerCardType" value="">
	<input type="hidden" id="deviceId" name="deviceId" value="">
	<input type="hidden" id="version" name="version" value="v1.0">
	<input type="hidden" id="redoFlag" name="redoFlag" value="">
	<input type="hidden" id="signType" name="signType" value="0">
	<input type="hidden" id="expireTime" name="expireTime" value="">
	<input type="hidden" id="signMsg" name="signMsg" value="">

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
			
			var chanjet =  new Chanjet();
			//chanjet.payerName = '${jsysUser.userName}';
			chanjet.orderAmount = $("#orderAmount1").attr("value");
			chanjet.expand = '${jsysUser.userCode}';
			
			chanjetUtil.getChanjet(chanjet,${flag},callBack1);
			
			popupNewAddr();
		}
	}
}
function callBack1(valid){
	
	if(valid==null){
		alert('ERROR!');
	}else{
		
		$("#orderId").attr("value",valid.orderId);
		$("#orderAmount").attr("value",valid.orderAmount);
		$("#merchantId").attr("value",valid.merchantId);
		
		$("#version").attr("value",valid.version);
		$("#signType").attr("value",valid.signType);
		$("#bgUrl").attr("value",valid.bgUrl);
		$("#notifyUrl").attr("value",valid.notifyUrl);
		$("#businessId").attr("value",valid.businessId);
		$("#platIdtfy").attr("value",valid.platIdtfy);
		
		$("#orderDate").attr("value",valid.orderDate);
		$("#amtType").attr("value",valid.amtType);
		$("#orderTime").attr("value",valid.orderTime);
		$("#redoFlag").attr("value",valid.redoFlag);
		$("#signMsg").attr("value",valid.signMsg);
		
		$("#expand").attr("value",valid.expand);
		$("#payerName").attr("value",valid.payerName);		
		
		$("#jfiPayChanjetForm").attr("action",valid.postUrl);
		$("#jfiPayChanjetForm").attr("target",'_blank');
		$("#jfiPayChanjetForm").submit();
	}
}

function payOK(){

	window.location.href = "jfiBankbookJournals";
}
function payERROR(){

	window.location.href = "jfiRechPayByChanjet";
}

</script>
