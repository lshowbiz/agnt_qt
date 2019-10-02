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
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/bill99Util.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Bill99.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<form id="jfiPay99bill" name="jfiPay99bill" method="GET" action="" target="_blank">
    <input type="hidden" id="inputCharset" name="inputCharset" value=""/>
    <input type="hidden" id="bgUrl" name="bgUrl" value=""/>
    <input type="hidden" id="pageUrl" name="pageUrl" value=""/>
    <input type="hidden" id="version" name="version" value=""/>
    <input type="hidden" id="language" name="language" value=""/>
    <input type="hidden" id="signType" name="signType" value=""/>
    <input type="hidden" id="signMsg" name="signMsg" value=""/>
    <input type="hidden" id="merchantAcctId" name="merchantAcctId" value=""/>
    <input type="hidden" id="payerContactType" name="payerContactType" value=""/>
    <input type="hidden" id="orderId" name="orderId" value=""/>
    <input type="hidden" id="orderTime" name="orderTime" value=""/>
    <input type="hidden" id="productName" name="productName" value=""/>
    <input type="hidden" id="productNum" name="productNum" value=""/>
    <input type="hidden" id="productId" name="productId" value=""/>
    <input type="hidden" id="productDesc" name="productDesc" value=""/>
    <input type="hidden" id="ext1" name="ext1" value=""/>
    <input type="hidden" id="ext2" name="ext2" value=""/>
    <input type="hidden" id="payType" name="payType" value=""/>
    <input type="hidden" id="redoFlag" name="redoFlag" value=""/>
    <input type="hidden" id="pid" name="pid" value=""/>
    <input type="hidden" id="orderAmount" name="orderAmount" value=""/>

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
                <td class="tr" width="100px">付款银行：</td>
                <td width="200px">
                <ng:list listCode="bill99.bank.id" name="bankId" id="bankId" value="ICBC" styleClass="mySelect" defaultValue="ICBC"/>

					<input type="hidden" name="bankId" id="bankId" />
				
				</td>
				<td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="tr">充值金额：</td>
                <td colspan="3">
                    
                    <input id="orderAmount1" name="orderAmount1" type="text" value="" size="10"/>&nbsp;元&nbsp;&nbsp;（单笔支付限额60万元）
                    
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
			
			var bill99 =  new Bill99();
			bill99.payerName = $("#payerName").attr("value");
			bill99.bankId = $("#bankId").attr("value");
			bill99.orderAmount = $("#orderAmount1").attr("value");
			bill99.payerContact = $("#payerContact").attr("value");
			bill99.ext2 = '${jsysUser.userCode }';
			
			//bill99.orderId = $("#orderId").attr("value");
			bill99Util.getBill99(bill99,${flag},callBack1);
			popupNewAddr();
		}
	}
}
function callBack1(valid){
	
	if(valid==null){
		alert('ERROR!');
	}else{
		$("#pay_order_id").text(valid.orderId);
	
		$("#inputCharset").attr("value",valid.inputCharset);
		$("#bgUrl").attr("value",valid.bgUrl);
		$("#pageUrl").attr("value",valid.pageUrl);
		$("#version").attr("value",valid.version);
		$("#language").attr("value",valid.language);
		$("#signType").attr("value",valid.signType);
		$("#signMsg").attr("value",valid.signMsg);
		$("#merchantAcctId").attr("value",valid.merchantAcctId);
		$("#payerContactType").attr("value",valid.payerContactType);
		$("#orderId").attr("value",valid.orderId);
		$("#orderTime").attr("value",valid.orderTime);
		$("#productName").attr("value",valid.productName);
		$("#productNum").attr("value",valid.productNum);
		$("#productId").attr("value",valid.productId);
		$("#productDesc").attr("value",valid.productDesc);
		$("#ext1").attr("value",valid.ext1);
		$("#ext2").attr("value",valid.ext2);
		$("#payType").attr("value",valid.payType);
		$("#redoFlag").attr("value",valid.redoFlag);
		$("#pid").attr("value",valid.pid);
		$("#orderAmount").attr("value",valid.orderAmount);

		$("#jfiPay99bill").attr("action",valid.postUrl);
		$("#jfiPay99bill").attr("target",'_blank');
		$("#jfiPay99bill").submit();
	}
}

function payOK(){

	window.location.href = "jfiBankbookJournals";
}
function payERROR(){

	window.location.href = "jfiRechPay";
}

</script>
