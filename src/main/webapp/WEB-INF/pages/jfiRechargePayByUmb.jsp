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
<script src="<c:url value='/dwr/interface/umbPayUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/UmbPay.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<form id="jfiPayUmbForm" name="jfiPayUmbForm" method="POST" action="" target="_blank">
	<input type="hidden" id="merchantid" name="merchantid" value="">
	<input type="hidden" id="merorderid" name="merorderid" value="">
	<input type="hidden" id="amountsum" name="amountsum" value="">
	<input type="hidden" id="subject" name="subject" value="">
	<input type="hidden" id="currencytype" name="currencytype" value="">
	
	<input type="hidden" id="autojump" name="autojump" value="">
	<input type="hidden" id="waittime" name="waittime" value="">
	<input type="hidden" id="merurl" name="merurl" value="">
	<input type="hidden" id="informmer" name="informmer" value="">
	<input type="hidden" id="informurl" name="informurl" value="">
	
	<input type="hidden" id="confirm" name="confirm" value="">
	<input type="hidden" id="merbank" name="merbank" value="">
	<input type="hidden" id="tradetype" name="tradetype" value="">
	<input type="hidden" id="bankInput" name="bankInput" value="">
	<input type="hidden" id="interFace" name="interface" value="">
	
	<input type="hidden" id="bankcardtype" name="bankcardtype" value="">
	<input type="hidden" id="pdtdetailurl" name="pdtdetailurl" value="">
	<input type="hidden" id="mac" name="mac" value="">
	<input type="hidden" id="remark" name="remark" value="">
	<input type="hidden" id="pdtdnm" name="pdtdnm" value="">

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
			var umbPay =  new UmbPay();
			umbPay.amountsum = $("#orderAmount1").attr("value");
			umbPay.merorderid = '${param.orderId}';
			umbPay.remark = '[${jsysUser.userCode},${flag}]';
			umbPayUtil.getUmbPay(umbPay,${flag},callBack1);
			popupNewAddr();
		}
	}
}
function callBack1(valid){
	
	if(valid==null){
		alert('ERROR!');
	}else{
		$("#merchantid").attr("value",valid.merchantid);
		$("#merorderid").attr("value",valid.merorderid);
		$("#amountsum").attr("value",valid.amountsum);
		$("#subject").attr("value",valid.subject);
		$("#currencytype").attr("value",valid.currencytype);

		$("#autojump").attr("value",valid.autojump);
		$("#waittime").attr("value",valid.waittime);
		$("#merurl").attr("value",valid.merurl);
		$("#informmer").attr("value",valid.informmer);
		$("#informurl").attr("value",valid.informurl);
		
		$("#confirm").attr("value",valid.confirm);
		$("#merbank").attr("value",valid.merbank);
		$("#tradetype").attr("value",valid.tradetype);
		$("#bankInput").attr("value",valid.bankInput);
		$("#interFace").attr("value",valid.interFace);

		$("#bankcardtype").attr("value",valid.bankcardtype);
		$("#pdtdetailurl").attr("value",valid.pdtdetailurl);
		$("#mac").attr("value",valid.mac);
		$("#remark").attr("value",valid.remark);
		$("#pdtdnm").attr("value",valid.pdtdnm);
		
		$("#jfiPayUmbForm").attr("action",valid.postUrl);
		$("#jfiPayUmbForm").attr("target",'_blank');
		$("#jfiPayUmbForm").submit();
	}
}

function payOK(){

	window.location.href = "jfiBankbookJournals";
}
function payERROR(){

	window.location.href = "jfiRechPayByUmb";
}

</script>
