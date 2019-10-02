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
<script src="<c:url value='/dwr/interface/reapalUtil.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Reapal.js'/>" ></script>


<form id="jfiPayReapalForm" name="jfiPayReapalForm" action="" method="POST" >
	<input type='hidden' name='service' id='service'>
	<input type='hidden' name='payment_type' id='payment_type'>
	<input type='hidden' name='merchant_ID' id='merchant_ID'>
	<input type='hidden' name='seller_email' id='seller_email'>
	<input type='hidden' name='return_url' id='return_url'>
	<input type='hidden' name='notify_url' id='notify_url'>
	<input type='hidden' name='charset' id='charset'>
	<input type='hidden' name='order_no' id='order_no'>
	<input type='hidden' name='title' id='title'>
	<input type='hidden' name='body' id='body'>
	<input type='hidden' name='total_fee' id='total_fee'>
	<input type='hidden' name='buyer_email' id='buyer_email'>
	<input type='hidden' name='paymethod' id='paymethod'>
	<input type='hidden' name='defaultbank' id='defaultbank'>
	<input type='hidden' name='sign' id='sign'>
	<input type="hidden" name="sign_type" id="sign_type">
	
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
                		<option value="ICBC">工商银行</option>
                		<option value="CMB">招商银行</option>
                		<option value="CCB">建设银行</option>
                		<option value="BOCM">交通银行</option>
                		<option value="ABC">农业银行</option>
                		<option value="BOC">中国银行</option>
                		<option value="CIB">兴业银行</option>
                		<option value="CMBC">民生银行</option>                		
                		<option value="CITIC">中信银行</option>
                		<option value="CEB">光大银行</option>
                		<option value="CGB">广发银行</option>
                		<option value="SHBANK">上海银行</option>
                		<option value="SPDB">浦发银行</option>
                		<option value="HXB">华夏银行</option>
                		<option value="BCCB">北京银行</option>
                		<option value="BOHAI">渤海银行</option>
                		<option value="PSBC">邮储银行</option>
                		<option value="SHNS">上海农商</option>
                		<option value="SDB">平安银行</option>
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

			var reapal =  new Reapal();

			reapal.total_fee = $("#orderAmount1").attr("value");
			//reapal.order_no = '${param.orderId}';
			reapal.body = '${jsysUser.userCode}';
			reapal.defaultbank = $("#bankId").attr("value");
			
			reapalUtil.getReapal(reapal,${flag},callBack);
			
			popupNewAddr();
		}
	}
}
function callBack(valid){
	
	if(valid==null){
		alert('ERROR!');
	}else{
		
		$("#service").attr("value",valid.service);
		$("#payment_type").attr("value",valid.payment_type);
		$("#merchant_ID").attr("value",valid.merchant_ID);
		$("#seller_email").attr("value",valid.seller_email);

		$("#return_url").attr("value",valid.return_url);
		$("#notify_url").attr("value",valid.notify_url);
		$("#charset").attr("value",valid.charset);
		$("#order_no").attr("value",valid.order_no);
		$("#title").attr("value",valid.title);
		$("#body").attr("value",valid.body);
		
		$("#total_fee").attr("value",valid.total_fee);
		$("#buyer_email").attr("value",valid.buyer_email);
		$("#paymethod").attr("value",valid.paymethod);
		$("#defaultbank").attr("value",valid.defaultbank);
		$("#sign").attr("value",valid.sign);
		
		$("#sign_type").attr("value",valid.sign_type);		
		
		$("#jfiPayReapalForm").attr("action",valid.post_url);
		$("#jfiPayReapalForm").attr("target",'_blank');
		$("#jfiPayReapalForm").submit();
	}
}

function payOK(){

	window.location.href = "jfiBankbookJournals";
}
function payERROR(){

	window.location.href = "jfiRechPay";
}

</script>
