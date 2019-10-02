<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

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
<script src="<c:url value='/dwr/interface/yeePayUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/YeePay.js'/>" ></script>


<form id="jfiPayYeePayForm" name="jfiPayYeePayForm" method="POST" action="" target="_blank">
	<input type='hidden' name='p0_Cmd' id='p0_Cmd'>
	<input type='hidden' name='p1_MerId' id='p1_MerId'>
	<input type='hidden' name='p2_Order' id='p2_Order'>
	<input type='hidden' name='p3_Amt' id='p3_Amt'>
	<input type='hidden' name='p4_Cur' id='p4_Cur'>
	<input type='hidden' name='p5_Pid' id='p5_Pid'>
	<input type='hidden' name='p6_Pcat' id='p6_Pcat'>
	<input type='hidden' name='p7_Pdesc' id='p7_Pdesc'>
	<input type='hidden' name='p8_Url' id='p8_Url'>
	<input type='hidden' name='p9_SAF' id='p9_SAF'>
	<input type='hidden' name='pa_MP' id='pa_MP'>
	<input type='hidden' name='pd_FrpId' id='pd_FrpId'>
	<input type='hidden' name='pm_Period' id='pm_Period'>
	<input type='hidden' name='pn_Unit' id='pn_Unit'>
	<input type="hidden" name="pr_NeedResponse" id="pr_NeedResponse">
	<input type='hidden' name='hmac' id='hmac'>

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
                <td class="tr">付款银行：</td>
                <td colspan="3">                	
                	<select name="bankId" id="bankId">
                		<option value="ICBC-NET-B2C">工商银行</option>
                		<option value="CMBCHINA-NET-B2C">招商银行</option>
                		<option value="CCB-NET-B2C">建设银行</option>
                		<option value="BOCO-NET-B2C">交通银行</option>
                		<option value="CIB-NET-B2C">兴业银行</option>
                		<option value="CMBC-NET-B2C">中国民生银行</option>
                		<option value="BOC-NET-B2C">中国银行</option>
                		<option value="PINGANBANK-NET-B2C">平安银行</option>
                		<option value="ECITIC-NET-B2C">中信银行</option>
                		<option value="SDB-NET-B2C">深圳发展银行</option>
                		<option value="GDB-NET-B2C">广发银行</option>
                		<option value="SHB-NET-B2C">上海银行</option>
                		<option value="SPDB-NET-B2C">上海浦东发展银行</option>
                		<option value="HXB-NET-B2C">华夏银行</option>
                		<option value="BCCB-NET-B2C">北京银行</option>
                		<option value="ABC-NET-B2C">中国农业银行</option>
                		<option value="POST-NET-B2C">中国邮政储蓄银行</option>
                	</select> 
                </td>
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

			var yeePay =  new YeePay();

			yeePay.p3_Amt = $("#orderAmount1").attr("value");
			//yeePay.p2_Order = '${param.orderId}';
			yeePay.pa_MP = '${jsysUser.userCode}';
			yeePay.pd_FrpId = $("#bankId").attr("value");
			
			yeePayUtil.getYeePay(yeePay,${flag},callBack);
			
			popupNewAddr();
		}
	}
}
function callBack(valid){
	
	if(valid==null){
		alert('ERROR!');
	}else{
		
		$("#p0_Cmd").attr("value",valid.p0_Cmd);
		$("#p1_MerId").attr("value",valid.p1_MerId);
		$("#p2_Order").attr("value",valid.p2_Order);

		$("#p3_Amt").attr("value",valid.p3_Amt);
		$("#p4_Cur").attr("value",valid.p4_Cur);
		$("#p5_Pid").attr("value",valid.p5_Pid);
		$("#p6_Pcat").attr("value",valid.p6_Pcat);
		$("#p7_Pdesc").attr("value",valid.p7_Pdesc);
		$("#p8_Url").attr("value",valid.p8_Url);
		
		$("#p9_SAF").attr("value",valid.p9_SAF);
		$("#pa_MP").attr("value",valid.pa_MP);
		$("#pd_FrpId").attr("value",valid.pd_FrpId);
		$("#pm_Period").attr("value",valid.pm_Period);
		$("#pn_Unit").attr("value",valid.pn_Unit);
		
		$("#pr_NeedResponse").attr("value",valid.pr_NeedResponse);
		$("#hmac").attr("value",valid.hmac);
		
		//alert(valid.post_url)
		$("#jfiPayYeePayForm").attr("action",valid.post_url);
		$("#jfiPayYeePayForm").attr("target",'_blank');
		$("#jfiPayYeePayForm").submit();
	}
}

function payOK(){

	window.location.href = "jfiBankbookJournals";
}
function payERROR(){

	window.location.href = "jfiRechPay";
}

</script>
