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
<script src="<c:url value='/dwr/interface/channelUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/ChannelBean.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<form id="fiPayChannel" method="post" action="" target="_blank" >
			<input type="hidden" id="Name" name="Name" value=""/>
			<input type="hidden" id="Version" name="Version" value=""/>
			<input type="hidden" id="Charset" name="Charset" value=""/>
			<input type="hidden" id="MsgSender" name="MsgSender" value=""/>
			<input type="hidden" id="SendTime" name="SendTime" value=""/>
			<input type="hidden" id="OrderNo" name="OrderNo" value=""/>
			<input type="hidden" id="OrderAmount" name="OrderAmount" value=""/>
			<input type="hidden" id="OrderTime" name="OrderTime" value=""/>
			<input type="hidden" id="PayType" name="PayType" value=""/>
			<input type="hidden" id="PayChannel" name="PayChannel" value=""/>
			<input type="hidden" id="InstCode" name="InstCode" value=""/>
			<input type="hidden" id="PageUrl" name="PageUrl" value=""/>
			<input type="hidden" id="BackUrl " name="BackUrl " value=""/>
			<input type="hidden" id="NotifyUrl" name="NotifyUrl" value=""/>
			<input type="hidden" id="ProductName" name="ProductName" value=""/>
			<input type="hidden" id="BuyerContact" name="BuyerContact" value=""/>
			<input type="hidden" id="BuyerIp" name="BuyerIp" value=""/>
			<input type="hidden" id="Ext1" name="Ext1" value=""/>
			<input type="hidden" id="SignType" name="SignType" value=""/>
			<input type="hidden" id="SignMsg" name="SignMsg" value=""/>


    <h2 class="title mgb20">充值</h2>
    <table width="100%" class="ordersDetails">
		
        <tbody>
        	<tr>

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
			
			var channel = new ChannelBean();

			channel.orderAmount = $("#orderAmount1").attr("value");
			channel.ext1 = "${jsysUser.userCode}";
			channel.buyerContact = $("#payerContact").attr("value");
			
			channelUtil.getChannelBean(channel,${flag},callBack1);
			
			popupNewAddr();
		}
	}
}
function callBack1(valid){
	
	if(valid==null){
		alert('ERROR!');
	}else{
		
		$("#Name").attr("value",valid.name);
		$("#Version").attr("value",valid.version);
		$("#Charset").attr("value",valid.charset);
		$("#MsgSender").attr("value",valid.msgSender);
		$("#SendTime").attr("value",valid.sendTime);
		$("#OrderNo").attr("value",valid.orderNo);
		$("#OrderAmount").attr("value",valid.orderAmount);
		$("#OrderTime").attr("value",valid.orderTime);
		$("#PayType").attr("value",valid.payType);
		$("#PayChannel").attr("value",valid.payChannel);
		$("#InstCode").attr("value",valid.instCode);
		$("#PageUrl").attr("value",valid.pageUrl);
		$("#BackUrl").attr("value",valid.backUrl);
		$("#NotifyUrl").attr("value",valid.notifyUrl);
		$("#ProductName").attr("value",valid.productName);
		$("#BuyerContact").attr("value",valid.buyerContact);
		$("#BuyerIp").attr("value",valid.buyerIp);
		$("#Ext1").attr("value",valid.ext1);
		$("#SignType").attr("value",valid.signType);
		$("#SignMsg").attr("value",valid.signMsg);

		$("#fiPayChannel").attr("action",valid.postUrl);		
		$("#fiPayChannel").attr("target","_blank");
		$("#fiPayChannel").submit();
	}
}

function payOK(){

	window.location.href = "jfiBankbookJournals";
}
function payERROR(){

	window.location.href = "jfiRechPayByChannel";
}

</script>
