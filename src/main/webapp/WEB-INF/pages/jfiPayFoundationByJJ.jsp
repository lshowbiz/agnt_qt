<%@ page language="java"  import="java.util.*"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<content tag="heading"><fmt:message key="jfiPay99billDetail.heading"/></content>
<!-- <style>
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
</style> -->
<script src="scripts/validate.js"></script>
   
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">公益订单发展基金支付</h3>
			</div>	
			
<form id="fiPayByJJ" method="GET" action="payFoundationReceiveByJJ" onsubmit="return validateOthers();return false;">
<input type="hidden" id="orderId" name="orderId" value="${foundationOrder.orderId}"/>

   <!--  <h2 class="title mgb20">公益订单发展基金支付</h2> -->
   
  
			
    
			<table class="form_edit_table" style="line-height:30px;"> 	
        <tbody>
        	<tr>
                <td>&nbsp;</td>
                <td style="vertical-align: bottom;padding-bottom: 10px">
                <span style="font-weight: 700; font-size:14px">公益订单 |  ${foundationOrder.foundationItem.name}</span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${jsysUser.userName}
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="color: red;font-weight: 700;font-size:14px"><fmt:formatNumber value="${foundationOrder.amount }" type="number" pattern="###,###,##0.00"/></span>元
				</td>

            </tr>
            
            <tr>
            	<td>&nbsp;</td>
                <td>
                	
                	电子存折帐户：${jsysUser.userCode }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	帐户余额：<b>${bankbook }</b>元
                	
                </td>
            </tr>
            <tr>
            	<td>&nbsp;</td>
                <td>
                	
                	发展基金帐户：${jsysUser.userCode }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	帐户余额：<b>${fjBalance}</b>元
                	
                </td>
            </tr>

            <tr>
            	<td>&nbsp;</td>
                <td>
                	使用发展基金：<input id="amount" name="amount" type="text" value="0" size="10" onkeyup="checkInputAmount()"/>(请输入使用发展基金金额)
                </td>
            </tr>

            <tr>
            	<td>&nbsp;</td>
                <td>
                	<!-- <button type="submit" class="btn-primary btn_common ft14" name="btn_pay" id="btn_pay">
					<i class="icon-ok icon-white"></i>&nbsp;确认付款&nbsp;
				</button> -->
				  	<button type="submit"  name="btn_pay" id="btn_pay" class="btn btn-info">&nbsp;<span>确认付款</span>&nbsp;</button>	
                </td>
            </tr>
            
        </tbody>
    </table>
</form>

<script type="text/javascript">
function checkInputAmount(){
	
	var orderAmount1=document.getElementById("amount");
	if(orderAmount1.value==""){
		alert("基金支付金额不能为空");
		orderAmount1.focus();
		return false;
	}
	if(orderAmount1.value <= 0){
		alert("基金支付金额必须大于0");
		orderAmount1.focus();
		return false;
	}
	var regu = "^[0-9]+[\.][0-9]{0,3}$"; 
	var re = new RegExp(regu); 
	
	var regu2 = "^[0-9]+$"; 
	var re2 = new RegExp(regu2); 
	if(re.test(orderAmount1.value)!=true && orderAmount1.value.search(re2) == -1){
		
		alert("基金支付金额不符合要求");
		orderAmount1.focus();
		return false;
	}
	
	if(orderAmount1.value > ${fjBalance}){
		alert("基金支付余额不足！");
		orderAmount1.focus();
		return false;
	}
	if(orderAmount1.value > ${foundationOrder.amount}){
		alert("基金支付金额输入超过订单金额！");
		orderAmount1.focus();
		return false;
	}
}

function validateOthers(){
	
	checkInputAmount();
	
	var orderAmount=document.getElementById("amount");
	var num1 = parseFloat(${bankbook});
	var num2 = parseFloat(orderAmount.value);
	var total = num1 + num2;

	if(${foundationOrder.amount} > total){
		alert("对不起，发展基金或电子存折余额不足！不能完成本次支付！");
		orderAmount.focus();
		return false;
	}
	
	$("#btn_pay").attr('disabled',true);
	$("#btn_pay").attr("style", "bgcolor:#ff0000");
	$("#btn_pay").attr("value",'已提交...请勿离开!');
	
	return true;
}
</script>
<br/>
<c:if test="${not empty foundationOrder }">
<!-- <h2 class="title mgb20">订单详情</h2> -->
    <table  border="1" class="form_details_table">
        <tbody>
            <tr>
                <td class="tr">订单号：</td>
                <td>${foundationOrder.orderId}</td>
                <td class="tr">项目名称：</td>
                <td >${foundationOrder.foundationItem.name}</td>
                <td></td>
                <td></td>
            </tr>
            <tr>

                <td class="tr">项目介绍：</td>
                <td width="450px" colspan="5">${foundationOrder.foundationItem.remark}</td>
            </tr>
			<tr>
                <td class="tr">爱心方案：</td>
                <td>${foundationOrder.foundationItem.amount}/${foundationOrder.foundationItem.unit}</td>
                <td class="tr">捐赠数量：</td>
                <td>${foundationOrder.fiNum}${foundationOrder.foundationItem.unit}</td>
                <td class="tr">总金额：</td>
                <td>${foundationOrder.amount }&nbsp;元</td>
            </tr>
            <tr>

                <td class="tr">捐赠留言：</td>
                <td colspan="5">${foundationOrder.field2}</td>
            </tr>
        </tbody>
    </table>
</c:if>


</div>
<%-- <h2 class="title mgb20">基金会联系方式</h2>
    <table width="100%" border="0">
    	<tr>
		    <td rowspan="2" width="50%"  valign="top">
				<p>地址：北京市东城区东直门南大街5号中青旅大厦1415</p>
			    <p>邮编：100007</p>
			    <p>电话：010-5815 6300</p>
			    <p>传真：010-5815 6305</p>
			    <p>电子邮箱：jmdhfoundation@jmtop.com</p>
			</td>
		    <td width="50%" colspan="2">
				<p>官方微信：
				
				</p>
			</td>
		  </tr>
		  <tr  valign="top">
		    <td valign="top" align="left"><p>搜索公众号：“中脉道和公益”或”jmdhgy” </p>
		    <br><br><br>
		    <p align="right">扫描二维码：</p></td>
		    <td align="right" width="110"><img src="${pageContext.request.contextPath}/images/gongyi.jpg" width="110" height="110" /></td>
		  </tr>

    </table>   --%>