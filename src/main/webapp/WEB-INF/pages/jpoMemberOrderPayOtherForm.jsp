<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<style>
.personalInfoTab td { height:35px; line-height:35px;}
</style>

<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml">支付他人订单</h3>
		</div>	
	<div class="mt">
<form:form commandName="jpoMemberOrder" method="post" action="jpoMemberOrderPayOtherForm" id="jpoMemberOrderForm" onsubmit="return onSubmitCheck()">
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="stepNext" id="stepNext" value=""/>

<c:if test="${ empty viewOrder  }"> 
      <table class="form_edit_table" style="margin-top: 60px;margin-bottom: 200px;">
      		
              <tbody>
				  <tr>
                      <td style="width:30%;text-align:right;"><span class="star"></span><b  style="font-size: 16px;">会员编号 :</b></td>
                      <td style="padding:18px 0;"><input type="text" name="userCode" id="userCode" style="margin-left: 33px; height: 35px; width:300px;"/></td>
                  </tr>
                  <tr>
                      <td style="width:30%;text-align:right;"><span class="star"></span><b  style="font-size: 16px;">订单编号 :</b></td>
                      <td style="padding:18px 0;"> <form:input path="memberOrderNo" id="memberOrderNo"  style="margin-left: 33px; height: 35px; width:300px;"/></td>
                  </tr>
                 
                  <tr>
                      <td></td>
                      <td>
                       <table>
                       	<tr>
                       		<td align="left">
                       		
                          <button type="button" class="mt" name="save" style="margin-left: 30px;" onclick="nextStep(this.form)">
		            		下一步
			       		 </button>
			    		</td>
        					<td></td>
                       	</tr>
                       </table> 
       				</td>
                  </tr>
             </tbody>
      </table>
</c:if>
                   
<c:if test="${ not empty viewOrder  }">
	<input type="hidden" name="memberOrderNo" id="memberOrderNo" value="${jpoMemberOrder.memberOrderNo }"/>
	<input type="hidden" name="userCode" id="userCode" value="${jpoMemberOrder.sysUser.userCode }"/>
	<table class="form_edit_table" style="line-height:50px;">
                   
                    <tbody>
						<tr>
                            <td width="110px">订单金额：</td>
                            <td width="100px"><ins class="red">${jpoMemberOrder.amount }</ins></td>
                            <td>电子存折帐户余额：</td>
                            <td><ins class="red">${bankbook }</ins></td>
							<td>发展基金帐户余额：</td>
                            <td><ins class="red">${coin }</ins></td>
                        </tr>
                        <c:if test="${not empty jpoMemberOrder && (isFund=='true')}">
                        <tr>
                            <td>使用发展基金：</td>
                            <td colspan="5"><input id="amount" name="amount" type="text" value="0" onkeyup="resetOrderAmount()" onchange="resetOrderAmount()"/><span class="red" style="margin-left:35px;">(使用基金时填写)</span></td>
                        </tr>
                         </c:if>
                        <tr>
                            <td><sapn class="star"></sapn>支付密码：</td>
                            <td><input name="password" id="password" type="password" value=""/></td>
                            <td width="150px" style="padding-right:15px;">
                            	<button type="submit" class="btn btn-info" name="save">
					            	&nbsp;确认支付&nbsp;
						        </button>
                            </td>
                            <td width="120px">
						        <button type="button" class="btn btn-success fr" name="button12" onclick="window.location.href='jpoMemberOrderPayOtherForm'">
									&nbsp;上一步&nbsp;
								</button>
                            </td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                   </tbody>
    </table>
  <div class="mt">
	<c:if test="${not empty jpoMemberOrder }">
	<!-- <h2 class="title mgb20">订单信息</h2> -->
	<table class="form_details_table">
        <tbody>
            <tr>
                <td class="tr">订单编号：</td>
                <td>${jpoMemberOrder.memberOrderNo}</td>
                <td class="tr">订单类型：</td>
                <td><ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/></td>
				<td class="tr">订单状态：</td>
                <td><ng:code listCode="po.status" value="${jpoMemberOrder.status}"/></td>
                
				<td class="tr">创建时间：</td>
                <td><fmt:formatDate value="${jpoMemberOrder.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            <tr>
				<td class="tr">会员编号：</td>
                <td>${jpoMemberOrder.sysUser.userCode}</td>
				<td class="tr">联系电话：</td>
                <td>${jpoMemberOrder.mobiletele }</td>
                <td class="tr">总金额：</td>
                <td>${jpoMemberOrder.amount }&nbsp;元</td>
                <td class="tr">总PV：</td>
                <td>${jpoMemberOrder.pvAmt}&nbsp;PV</td>
            </tr>

        </tbody>
    </table>
    </div>
    <div class="mt">		
	<%-- <h2 class="title mgb20">订单商品明细</h2>
			<h3 class="title_2">[<ng:code listCode="po.all.order_type" value="${jpoMemberOrder.orderType}"/>]</h3> --%>
			
			<c:if test="${not empty jpoMemberOrder.jpoMemberOrderList}">
			<table class="ords-table ords-table-thc" width="100%">
				<tbody class="list_tbody_header">
					<tr>
						<td>商品名称</td>
						<td>商品编号</td>
						<td>单价</td>
						<td>单件PV</td>
						<td>数量</td>
						<td>价格合计</td>
						<td>PV合计</td>
					
					</tr>
				</tbody>
				<tbody class="list_tbody_row">
				  <c:forEach items="${jpoMemberOrder.jpoMemberOrderList}"  var="jpoMemberOrderList">
					<tr class="bg-c gry3">
						<td>
							<a href="${ctx}/jpoShoppingCartOrderView/cartView?uniNo=${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.uniNo}&teamCode=${jpoMemberOrder.teamCode}&orderType=${jpoMemberOrder.orderType}&pptId=${jpoMemberOrderList.jpmProductSaleTeamType.pttId}" target="blank">
								<img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.jpmProductSaleImageList[0].imageLink}" 
	                       			alt="商品图片" width="56" height="56"/>
							${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName }
							</a>
						</td>
						<td>${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo }</td>
						<td>${jpoMemberOrderList.price}&nbsp;元</td>
						<td>${jpoMemberOrderList.pv}&nbsp;PV</td>
						<td>${jpoMemberOrderList.qty }</td>
						<td>${jpoMemberOrderList.price*jpoMemberOrderList.qty}&nbsp;元</td>
						<td>${jpoMemberOrderList.pv*jpoMemberOrderList.qty}&nbsp;PV</td>
					</tr>
				 </c:forEach>
				</tbody>
			</table>
			</c:if>
			</div>
		</c:if>
</c:if>
</form:form>
</div>
</div>
<script language="javascript">
function resetOrderAmount(){

	var amount = document.getElementById("amount");
   	if(amount.value==null || isNaN(amount.value) || amount.value<0){
   		
   		amount.value=0;
   		return;
   	}
}

function nextStep(theForm){

	
	
	$("#stepNext").attr("value",'stepNext');
	theForm.method='get';
	//if(isFormPosted()){
		theForm.submit();
	//}
}
function onSubmitCheck(theForm){

	var amount = document.getElementById("amount");
   	if(amount.value=="" || isNaN(amount.value) || amount.value<0){
   		
   		alert("请输入正确的发展基金金额!");
   		return false;
   	}
   	
	var regu = "^[0-9]+[\.][0-9]{0,2}$"; 
	var re = new RegExp(regu); 
	var regu2 = "^[0-9]+$"; 
	var re2 = new RegExp(regu2); 
	
	if(re.test(amount.value)!=true && amount.value.search(re2) == -1){
		alert("基金支付金额不符合要求");
		amount.focus();
		return false;
	}
	
	if(amount.value > parseFloat("${jpoMemberOrder.amount}")){
		alert("基金支付金额必须小于付款金额!");
		amount.focus();
		return false;
	}
   	
	if(amount.value > parseFloat("${coin}")){
		alert("基金支付余额不足！");
		amount.focus();
		return false;
	}
   	
   	var password2 = document.getElementById("password");

   	if(password2.value==""){
   	
   		alert("请输入支付密码！");
   		return false;
   	}
   	
   	<c:if test="${ not empty viewOrder  }">

		if(${jpoMemberOrder.amount} > parseFloat(parseFloat(amount.value)+parseFloat(${bankbook})).toFixed(2)){
		
			alert("对不起！您的电子存折帐户余额不足！不能完成本次支付，请到充值页面进行充值！谢谢！");
			return false;
		}
	</c:if>
	
	if(isFormPosted()){
		theForm.submit();
	}
}
</script>
