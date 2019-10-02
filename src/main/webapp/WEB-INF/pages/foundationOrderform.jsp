<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <!-- <title><fmt:message key="foundationOrderDetail.title"/></title> -->
    <meta name="heading" content="<fmt:message key='foundationOrderDetail.heading'/>"/>
</head>

<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="foundationOrder" method="post" action="foundationOrderform"  
           id="foundationOrderForm" onsubmit="return validateOthers()">
       
    <input type="hidden" id="fiId" name="fiId" value="${foundationOrder.foundationItem.fiId }"/>  
 
    <form:hidden path="orderId" id="orderId" cssClass="text medium"/>

	<!-- <h2 class="title mgb20">公益产品订单确认</h2> -->

<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">公益产品订单确认</h3>
			</div>	  
			

	<table width="90%" class="form_edit_table">
		<colgroup style="width:90px;"></colgroup>
		<colgroup></colgroup>
		<tbody>
	   
			<tr>
				<td class="tr">公益项目：</td>
				<td>${foundationOrder.foundationItem.name }</td>
			</tr>
			<tr>
				<td class="tr">公益介绍：</td>
				<td>${foundationOrder.foundationItem.remark }</td>
			</tr>
			<tr>
				<td class="tr">爱心方案：</td>
				<td>${foundationOrder.foundationItem.amount }元/${foundationOrder.foundationItem.unit }</td>
			</tr>
			<tr>
				<td class="tr">捐赠数量：</td>
				<td class="pNumber" style="float:left;">
					
					
						<div class="">						
							<a class="reduce" onclick="setAmount.reduce('#fiNum')" href="javascript:void(0)">-</a>
								<input type="text" class="text" name="fiNum" value="${foundationOrder.fiNum }" id="fiNum" size="3" onkeydown="setFinum()" onkeyup="setFinum()"/>
							<a class="add" onclick="setAmount.add('#fiNum')"  href="javascript:void(0)">+</a>
						</div>
					
				</td>
			</tr>
			<tr>
				<td class="tr">总金额：</td>
				<td>
					<span id="amount" color="red">${foundationOrder.amount }</span>
				</td>

			</tr>
			<tr>
				<td class="tr">捐赠留言：</td>
				<td>
					<form:textarea path="field2" id="field2" rows="4" cols="40" cssClass="text medium"/>(捐赠留言字数限100字以内)
				</td>

			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<button type="submit" class="mt" name="save" onclick="bCancel=false">确认捐赠</button>
				</td>

			</tr>
		</tbody>
	</table>
</div>
</form:form> 

<%-- 	<br><br><br><br><br><br><br><br><br><br><br><br>
	<h2 class="title mgb20">基金会联系方式</h2>
    <table width="100%">
    	<tr>
		    <td rowspan="2" width="55%">
				<p>地址：北京市朝阳区东风南路十号院悠然尚东四合院甲院中脉道和公益基金会</p>
			    <p>邮编：100016</p>
			    <p>电话：010-5798 3910/11/12/13</p>
			    <p>传真：010-5798 3916</p>
			    <p>基金会电子邮箱：jmdhfoundation@jmtop.com</p>
			    <p>家基金电子邮箱：joyfamily@jmdhfoundation.org</p>
			</td>
		    <td width="50%" colspan="2">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td style=""><p><b>扫描二维码：</b> </p></td>
			<td class="tr">
				<img src="${pageContext.request.contextPath}/images/gongyi.jpg" width="110" height="110" />&nbsp;
				<img src="${pageContext.request.contextPath}/images/jiajijin.jpg" width="110" height="110" />
			</td>
		</tr>
		<tr>
		    <td>
				<p>基金会官方微信：搜索公众号：“中脉道和公益”或”jmdhgy”</p>
				<p>家基金官方微信：搜索公众号：“家基金”</p>
			</td>
		</tr>
		<tr>
			<td>
				<p>附件：中脉道和志愿者联合会志愿者注册登记表&nbsp;&nbsp;<a class="colorQL" href="${pageContext.request.contextPath}/images/volunteer.doc">点击下载</a></p>
			</td>
			<td></td>
			<td></td>
		</tr>

    </table>  --%>       
<script>
    Form.focusFirstElement($('foundationOrderForm'));
    
    function setFinum(){
    	
    	var fiNum = document.getElementById("fiNum").value;
    	
    	var regu = "^[0-9]+$";
		var re = new RegExp(regu); 

		if(re.test(fiNum)!=true){
			
			alert("捐赠数量输入不正确,请输入大于0的正整数格式！");
			document.getElementById("fiNum").value = 1;
			document.getElementById("amount").innerHTML = ${foundationOrder.amount };
			document.getElementById("fiNum").focus();
			return;
		}
		if(fiNum <= 0){
			alert("捐赠数量输入不正确,请输入大于0的正整数格式！");
			document.getElementById("fiNum").value = 1;
			document.getElementById("amount").innerHTML = ${foundationOrder.amount };
			document.getElementById("fiNum").focus();
			return;
		}
		
		if(fiNum<=0 || fiNum>99999){
			alert("请输入您购买的数量不允许为0或负数不能大于99999");
			document.getElementById("fiNum").value = 1;
			document.getElementById("amount").innerHTML = ${foundationOrder.amount };
			document.getElementById("fiNum").focus();
			return;
		}
    	
    	var amountV = ${foundationOrder.foundationItem.amount }
	    var amount = document.getElementById("amount");
	
    	if(fiNum==null || isNaN(fiNum) || fiNum<0){
    	
    		fiNum = '${foundationOrder.fiNum }';
    	}else{
	    	amount.innerHTML = amountV*fiNum;
	    }
    }
    
    var checkSubmitFlg = false;

    function validateOthers(){
  	
  		if (!checkSubmitFlg) {
  		
  			var fiNum = document.getElementById("fiNum");
  			if(fiNum.value==null){
    			
    			alert("捐赠数量填写不正确！");
    			fiNum.value = '${foundationOrder.fiNum }';	
    			return false;
    		}
  			if(fiNum.value<=0 || fiNum.value>99999){
  				alert("请输入您购买的数量不允许为0或负数不能大于99999");
    			return false;
  			}
    		var field2 = document.getElementById("field2");
    		if(field2.value.length > 100){
    		
    			alert("捐赠留言字数限100字以内!");
    			return false;
    		}
		
			checkSubmitFlg = true;
			return isFormPosted();
		}else {

			//重复提交
			alert("请勿重复提交!");
			return false;
		}
	}
</script>	
<script>
function changeV(){
	var fiNum = $('#fiNum').val();
	var amount = $('#amount');
	amount.html( fiNum * ${foundationOrder.foundationItem.amount } );
}

var setAmount = {
    min:1,				
    max:99999999999,				
	
    reg:function(x) {
        return new RegExp("^[0-9]\\d*$").test(x);
    },
    amount:function(obj, mode) {
      
        var x = $(obj).val();

        if (this.reg(x)) {
            if (mode) {
                x++;
            } else {
                x--;
            }
            
        } else {
            alert("请输入正确的数量！");
            $(obj).val(0);
            $(obj).focus();
        }
        return x;
    },
    reduce:function(obj,orderType,x) {
     
        var x = this.amount(obj, false);
        var sclId=obj.split('_')[2];
		
        if (x >= this.min) {
            $(obj).val(x);

        } else {
			return false;
        }
		changeV();
    },
    add:function(obj,orderType) {

		var sclId=obj.split('_')[2];
		var x = this.amount(obj, true);

		if (x <= this.max) {
			$(obj).val(x);
		} 
		changeV();
    }
};


$(function () {
    $("input[name^=num_item_]").bind("blur", function(){
		var it = $(this);
		var x = it.val();
		if( x.length > 5 || ! RegExp("^[0-9]\\d*$").test(x) || x==0 ){
		    alert("请输入您购买的数量不允许为0或负数");
			it.val($("input[id^=hidden_num_item_]").val());
            it.focus();
		};
		
	});

    $("input[name^=num_item_]").bind("focus", function(){
   
		var it = $(this);
		var x = it.val();
		if(x>0)
		{
		    $("input[id^=hidden_num_item_]").val(x);
		}
		
	});

});



</script>

