<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>
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

<div class="cont">
<form id="fiRechargeForm" name="fiRechargeForm" method="get" action="${pageContext.request.contextPath}/jfiRecharge.html" target="_blank">	
    <div class="bt mt">
		<h3 class="color2 ml">基金充值</h3>
	</div>
    <table class="form_edit_table"  style="margin-top: 80px;margin-bottom: 200px; line-height:50px;">
		
        <tbody>
        	<tr>
				<td style="width:30%;text-align:right;"><b style="font-size:14px;">充值帐户：</td>
				<td style="padding:0px 0;width: 170px;"><div>${jsysUser.userCode }</div></td>
				<td style="text-align: left;">
					<b style="font-size:14px;">余额：</b>
					<b class="ft-green">${bankbook }</b> 元<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/>
				</td>
            </tr>
            <tr>
                <td style="width:30%;text-align:right;"><b style="font-size:14px;">充值金额：</b></td>
                <td colspan="3" style="padding:0px 0;text-align: left;">                    
                    <input id="payAmount" name="payAmount" type="text" value="" style="height: 35px;"/>&nbsp;&nbsp;元                 
                </td>
            </tr>
			<tr>
                <!-- <td><input type="hidden" id="payerContact" name="payerContact" value=""/></td> -->
                <td><input type="hidden" id="flag" name="flag" value="${flag }"/></td>
                <td colspan="2">
                	<button id="search" type="button" class="btn btn-info mt" onclick="actionPost();">确认付款</button>
                	<button id="quxiao" type="button" class="btn btn-success mt" onclick="javascript:history.go(-1);">取消</button>
                </td>
                
            </tr>
            
        </tbody>
    </table>
</form>
</div>
<div class="mask" id="mask"></div>
<div class="popupAddr2 abs" id="popupAddr" style="display:none;width:500px;height:256px;">
	<div class="mgb20" style="height:24px;line-height:24px;">
		<span class="fl bold">充值</span>
		
	</div>

	
	<p>请您在新打开的页面进行充值，充值完成前请不要关闭该页面</p>
	<!-- <p>订单号：<span id="pay_order_id"></span></p> -->
	<div class="fb" style="width:294px;overflow:hidden;margin:0 auto;">
		<a href="javascript:void(0);" class="btn_common corner2 fl" id="js_confirm" onclick="payOK()">已完成充值</a>
		<a href="javascript:void(0);" class="btn_common corner2 fl" onclick="payERROR()">充值遇到问题</a>
	</div>
</div>
	
<script>

function actionPost(){
	
	var orderAmount1=document.getElementById("payAmount");
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
	var regu = "^[0-9]+[\.][0-9]{0,2}$"; 
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
			$("#fiRechargeForm").submit();
			popupNewAddr();
		}
	}
}


function payOK(){
		window.location.href = "fiBankbookJournals";
}
function payERROR(){
	window.location.href = "fiRechPay";
}

</script>
