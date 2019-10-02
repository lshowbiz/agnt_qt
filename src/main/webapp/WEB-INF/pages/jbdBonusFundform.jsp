<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>





<script language="javascript">

function getTotalAmout(){
	var amountTotal = document.getElementById("amountTotal").value;
	var amount1 = document.getElementById("amount1");
	var amount2 = document.getElementById("amount2");
	var amount3 = document.getElementById("amount3");
	var amountTotal=amountTotal+"";
	
	var amountTotals=new Array();
	
	amountTotals=amountTotal.split(".");
	
	if(amountTotals.length>1){
		if(amountTotals[1].length==1){
			amount2.value = amountTotals[1].substr(0,1);
		}else{
			amount2.value = amountTotals[1].substr(0,1);
			amount3.value = amountTotals[1].substr(1,2);
		}
	}
	amount1.value=amountTotals[0];
	
	calcFund();
}



var bCancel1 = true;
function checkForm(){
if(bCancel1==true){
	var amount1 = document.getElementById("amount1").value;
	var amount2 = document.getElementById("amount2").value;
	var amount3 = document.getElementById("amount3").value;
	var extReg1 = /^(0|[1-9]\d*)?$/;
	if(amount1 ==""){
		alert('<ng:locale key="errors.required" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(!amount1.match(extReg1)){
		alert('<ng:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	var extReg2 = /^(0|[1-9])?$/;
	if(amount2 =="" || amount3 ==""){
		alert('<ng:locale key="errors.required" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(!amount2.match(extReg2) || !amount3.match(extReg2)){
		alert('<ng:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}

}
	if(isFormPosted()){
		document.jbdBonusFundform.submit();
	}
}

function calcFund(){

	var amount1 = document.getElementById("amount1").value;
	var amount2 = document.getElementById("amount2").value;
	var amount3 = document.getElementById("amount3").value;

	var extReg1 = /^(0|[1-9]\d*)?$/;
	var extReg2 = /^(0|[1-9])?$/;
	
	if(amount1.match(extReg1) && amount2.match(extReg2) && amount3.match(extReg2) ){
		amount2=accMul(amount2,0.1);
		amount3=accMul(amount3,0.01);
		amount1=accAdd(amount1,accAdd(amount2,amount3));
		document.getElementById("calcres").innerHTML=accMul(amount1,1.2).toFixed(2);
	}
}
function accMul(arg1,arg2) 

{ 

var m=0,s1=arg1.toString(),s2=arg2.toString(); 

try{m+=s1.split(".")[1].length}catch(e){} 

try{m+=s2.split(".")[1].length}catch(e){} 

return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m) 

} 




//加法 

function accAdd(arg1,arg2){ 

var r1,r2,m; 

try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 

try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 

m=Math.pow(10,Math.max(r1,r2)) 

return (arg1*m+arg2*m)/m 

}

</script>




<spring:bind path="jbdBonusFund.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>



<div class="content fr">
			<h2 class="title mgb20">8财月奖金转基金</h2>


<form:form commandName="jbdBonusFund" method="post" action="jbdBonusFundform" name="jbdBonusFundform" id="jbdBonusFundform">
                <table width="100%" border="0" class="personalInfoTab">
                    <colgroup style="width:200px;"></colgroup>
                    <colgroup></colgroup>
                    <tbody>
                        <tr>
                            <td><label class="pdl10">会员编号：</label></td> 
                            <td>    	
			                     ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}
                            </td>
                        </tr>
                        
                        <tr>
                            <td><label class="pdl10">8财月总奖金：</label></td> 
                            <td>    	
			                     <c:if test="${not empty noBonus }">
			                     	8财月奖金不存在
			                     </c:if>
			               <a href="#" onclick="getTotalAmout();" style="text-decoration:underline; "><font size="4" color="green"><b>${totalBonus }</b></font></a>
                            	<input type="hidden" id="amountTotal" name="amountTotal" value="${totalBonus }" />
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><b>
                            	
                        <c:if test="${isApply==true }">
                            	
                            	（点击金额可全额转入）
                            	</c:if>
                            	</b></font>      
			                     
			                     
			                     
			                     
			                     
			                     
                            </td>
                        </tr>
                        
                        <c:if test="${isApply==true }">
                          <tr>
                            <td><label class="star">申请金额：</label></td> 
                            <td>    	
			                   
        <input name="amount1" id="amount1" type="text" value="0" onblur="calcFund()"/>&nbsp;&nbsp;<span style="margin-top: 25px;font-size: 30px;"><b>.</b></span><input name="amount2" id="amount2" type="text" value="0" size="2" maxlength="1" onblur="calcFund()"/>
        <input name="amount3" id="amount3" type="text" value="0" size="2" maxlength="1" onblur="calcFund()"/>
                            </td>
                            
                        </tr>
                        
                        </c:if>
                      
                        
                        
                        <tr>
                            <td><label class="pdl10">转发展基金：</label></td> 
                            <td>    	
			                    <font size="4" color="green"><b> <span id="calcres">
			                    <fmt:formatNumber value="${jbdBonusFundMoney }"></fmt:formatNumber>
			                    
			                    </span></b></font>
                            </td>
                            
                        </tr>
                        
                        <tr>
                            <td></td> 
                            <td>    	
			                   <font color="red" style="font-size: 15px;"><b>注：8财月奖金转发展基金只可操作一次，提交后不可撤销</b></font>  
                            </td>
                            
                        </tr>
                        
	                        <tr>
	                            
	                            <td > 
	                            </td>
                        <c:if test="${isApply==true }">
	                            
	                            <td align="left"><a href="#" onclick="checkForm();" class="btn_common corner2">确定申请</a>
					 			 </td>
	                            </c:if>
	                        </tr>
                       
                       
                    </tbody>
                </table>
                
                
               </form:form>
		</div>















