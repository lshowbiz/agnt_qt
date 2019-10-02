<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPay99billDetail.heading"/></content>
<style>
.orderForms {
	margin:18px 0 20px 0;width: 796px;
}
.orderForms td{
	font-size:12px;
	height:36px;
	line-height:36px;
}
.divcss5{text-decoration:underline;color:#F00}
.ft-green {
	COLOR: #41ab01
}
.div-t{
	width:100%;background-color: #f4f6fc;padding-left: 40px;padding-top: 0px;padding-bottom: 0px;
}
.div-td{
	padding-left: 40px;
}
</style>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/bill99Util.js'/>" ></script>
<script src="<c:url value='/dwr/interface/Bill99.js'/>" ></script>
<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>

<form id="jfiPayConfig99bill" name="jfiPayConfig99bill" method="GET" action="" <c:if test='${empty param.orderId && param.offline == "1"}'>target="_blank" </c:if>>
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

	<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/>
	
    <h2 class="title mgb20">订单支付</h2>
    <table width="100%" class="orderForms">
    
        <tbody>
        	<tr>
                
                <td style="vertical-align: bottom;padding-bottom: 10px"><span style="font-weight: 700; font-size:14px">酒业配置单 | ${jpmMemberConfig.jpmProductSaleTeamType.productName}</span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${jpmMemberConfig.userCode}
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;font-weight: 700;font-size:14px"><fmt:formatNumber value="${jpmMemberConfig.price}" type="number" pattern="###,###,##0.00"/></span>元
                </td>

            </tr>
            <%--
             <tr>
                <td></td>
                <td colspan="3" id="lable_balance"></td>
            </tr>
        	<tr>
                <td class="tr">付款金额(元)：</td>
                <td colspan="3">

                    <input id="orderAmount1" name="orderAmount1" type="text" value="${jpmMemberConfig.price }" size="10" readonly="readonly"/>
                    
                </td>
            </tr>
            <tr>
                <td class="tr" width="120px"></td>
                <td width="180px">
                	<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1"/><span>&nbsp;使用电子存折支付</span>
                </td>
                <td></td>
                <td></td>
            </tr>
             --%>
            <tr>
               
                <td>
                	<div class="div-t">
                	电子存折帐户：${jfiBankbookBalance.userCode }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	帐户余额：<b>${jfiBankbookBalance.balance }</b>元
                	</div>
                </td>
            </tr>
            <tr>
                <td>
                	<div class="div-td">
                	<c:if test="${jfiBankbookBalance.balance>0}">
                		<input type="checkbox" id="bankbook" onclick="resetOrderAmount();" name="bankbook" value="1"/>
	                	<c:if test="${jfiBankbookBalance.balance-jpmMemberConfig.price >=0}">
	                		<span>&nbsp;使用电子存折支付${jpmMemberConfig.price }元</span>
	                	</c:if>
	                	<c:if test="${jfiBankbookBalance.balance-jpmMemberConfig.price <0}">
	                		<span>&nbsp;使用电子存折支付${jfiBankbookBalance.balance}元，使用网银支付剩余的${jpmMemberConfig.price-jfiBankbookBalance.balance}元</span>
	                	</c:if>
	                	<span id="lable_balance"></span></div>
                	</c:if>
                	<c:if test="${jfiBankbookBalance.balance==0}">
                		
                		电子存折帐户余额不足！请使用网银付款或者进入电子存折进行充值&nbsp;<a href="${pageContext.request.contextPath}/jfiBankbookJournals"  class="colorCS">我的电子存折</a>
                	</c:if>
                </td>
            </tr>
            

            <tr>
                
                <td>
                <div class="div-t">
                	付款银行：<ng:list listCode="bill99.bank.id" name="bankId" id="bankId" value="ICBC" styleClass="mySelect" defaultValue="ICBC"/>

                    <c:if test='${empty param.orderId && param.offline == "1"}'>
                        <input type="hidden" name="bankId" id="bankId" />
                    </c:if>
                </div>
                
                    </td>
            </tr>
            <tr>
                <td>
                	<div class="div-td">付款金额：<input type="text" id="orderAmount1" class="colorJH bold" name="orderAmount1" value="${jpoMemberOrder.amount }" size="10" readonly="readonly"/>
					&nbsp;元
                    </div>
                </td>
            </tr>
           
            <tr>
     
                <td><div class="div-td">手续费：<%=com.joymain.ng.util.bill99.Bill99Constants.feeP.multiply(new java.math.BigDecimal("1000")) %>&#8240;</div></td>

            </tr>
            <input type="hidden" id="payerContact" name="payerContact" value=""/>
            <tr>

                <td style="padding-left: 40px;">
                   <input type="button" style="cursor: pointer" class="btn_common corner2" name="btn_pay" id="btn_pay"  onclick="actionPost();" value="确认付款" />
                </td>

            </tr>
            <tr>
                <td></td>
            </tr>
            
        </tbody>
    </table>
</form>

<div>
  <span style="font-weight: 700; font-size:14px;padding-bottom: 20px">付款遇到问题：</span>
  <p><a href="${pageContext.request.contextPath}/loginform/help?1=1&flag=bank" target="_blank">帮助</a>&nbsp;&nbsp;<a href="jfiBankbookJournals">进入我的电子存折</a> </p>
</div>


<h2 class="title mgb20">订单详情</h2>
<table width="100%" border="1" class="ordersDetails">
       <tbody>
           <tr>
               <td class="tr">配置单号：</td>
               <td>${jpmMemberConfig.configNo}</td>
               <td class="tr">商品名称：</td>
               <td>${jpmMemberConfig.jpmProductSaleTeamType.productName}</td>
               <td class="tr">配置数量：</td>
               <td>${jpmMemberConfig.amount}</td>
			<td class="tr">配置状态：：</td>
               <td>
               	<c:if test="${jpmMemberConfig.status eq '0 '}">未配置完成</c:if>
				<c:if test="${jpmMemberConfig.status eq '1 '}">已配置完成</c:if>
               </td>
           </tr>
       </tbody>
   </table>
<h2 class="title mgb20">订单明细</h2>
<table class="ordersConfirmLs" width="100%" border="0">
	<thead>
		<tr>
			<th>规格名称：</th>
			<th>配置重量：</th>
			<th>价格</th>
		
		</tr>
	</thead>
	<tbody>
	  <c:forEach items="${jpmMemberConfig.jpmConfigSpecDetailed}"  var="jpmConfigSpecDetailed">
		<tr>
			<td>${jpmConfigSpecDetailed.productTemplateName }</td>
			<td>${jpmConfigSpecDetailed.complateWeight}</td>
			<td>${jpmConfigSpecDetailed.price }</td>
		</tr>
	 </c:forEach>
	</tbody>
</table>
			
<div class="mask" id="mask"></div>
	<div class="popupAddr2 abs" id="popupAddr" style="display:none;width:500px;height:256px;">
		<div class="mgb20" style="height:24px;line-height:24px;">
			<span class="fl bold">支付</span>
			
		</div>

		
		<p>请您在新打开的页面进行支付，支付完成前请不要关闭该页面</p>
		<p>配置单号：${jpmMemberConfig.configNo}</p>
		<div class="fb" style="width:294px;overflow:hidden;margin:0 auto;">
			<a href="javascript:void(0);" class="btn_common corner2 fl" id="js_confirm" onclick="payOK()">已完成支付</a>
			<a href="javascript:void(0);" class="btn_common corner2 fl" onclick="payERROR()">支付遇到问题</a>
		</div>
</div>
<script>
function resetOrderAmount(){
	
	if($("#bankbook").attr('checked')==undefined){
		
		$("#orderAmount1").attr("value",${jpmMemberConfig.price });
		$("#lable_balance").text('');
	}else{
		
		var orderAmount = ${jfiBankbookBalance.balance - jpmMemberConfig.price };
		if(orderAmount < 0 ){
			$("#orderAmount1").attr("value",-orderAmount);
			//$("#lable_balance").text('选择使用电子存折支付：'+${jfiBankbookBalance.balance}+'元，还需要在线支付剩下的'+-orderAmount+'元');
		}else{
		
			$("#orderAmount1").attr("value",'0');
			//$("#lable_balance").text('选择使用电子存折支付：'+${jpmMemberConfig.price }+'元');
		}
	}
	
}
function actionPost(){

	if($("#orderAmount1").attr("value") < 0){
		$("#orderAmount1").attr("value",'0');
		return;
	}
	
	<c:if test='${not empty param.orderId}'>
	
	if(isFormPosted()){
	
		if($("#orderAmount1").attr("value")==0){
			<c:if test='${not empty param.orderId}'>

				window.location.href = "payConfigReceive?orderId=${param.orderId}&isAllPay=1";
				
				$("#btn_pay").attr('disabled',true);
				$("#btn_pay").attr("style", "bgcolor:#ff0000");
				$("#btn_pay").attr("value",'已提交...请勿离开!');
			</c:if>
		}else{
		
			var bill99=new Bill99();
			
			bill99.payerName = $("#payerName").attr("value");
			bill99.bankId = $("#bankId").attr("value");
			bill99.orderAmount = $("#orderAmount1").attr("value");
			bill99.payerContact = $("#payerContact").attr("value");
			bill99.ext2 = '${jsysUser.userCode }';
			bill99.orderId = '${param.orderId}';
			
			bill99Util.getBill99(bill99,3,callBack1);
			
			popupNewAddr();
		}
	}
	</c:if>
}
function callBack1(valid){

	if(valid==null){
		alert('ERROR!');
	}else{
	
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

		$("#jfiPayConfig99bill").attr("action",valid.postUrl);
		$("#jfiPayConfig99bill").attr("target",'_blank');
		$("#jfiPayConfig99bill").submit();
	}
}
function payOK(){

	window.location.href = "jpoWineMemberOrders/orderConfigList?molId=${jpmMemberConfig.molId}&productNo=${jpmMemberConfig.jpmProductSaleTeamType.jpmProductSaleNew.productNo}";
}
function payERROR(){

	window.location.href = "payMemberConfig?orderId=${jpmMemberConfig.configNo}";
}
</script>
