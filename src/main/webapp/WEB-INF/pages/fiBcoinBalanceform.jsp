<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <meta name="menu" content="FiBcoinJournalMenu"/>

</head>

 

 
 
 
 
<div class="cont mt">	
  
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>

<form:form commandName="fiBoinBalance" method="post" action="fiBcoinBalanceform" 
           id="fiBoinBalanceForm" name="fiBoinBalanceForm" onsubmit="return validateOthers()">

    <!-- <h2 class="title mgb20"></h2> -->
    
    <div class="bt mt">
		<h3 class="color2 ml">积分迁移至瓜藤网</h3>
	</div>
	                
	<table  class="form_edit_table" > 
              
                    <tbody>
                       
                        <tr>
                            <td ><label for="">会员编号:</label></td>
                            <td>${fiBcoinBalance.userCode }</td>
                            <td></td>
                            <td></td>
                        </tr>
                  
                        <tr>
                        <td><label for="">欢乐积分余额:</label></td>
                            <td><fmt:formatNumber value="${fiBcoinBalance.balance }" type="number" pattern="###,###,###.##"></fmt:formatNumber></td>
                            
                            <td></td>
                            <td></td>
                        </tr>
                        
                        <tr>
                       
                            <td><label for="">创新积分余额:</label></td>
                            <td>
                            <c:if test="${not empty inwIntegrationTotal }">
                          	  ${inwIntegrationTotal.totalPoints }
                          	  <c:set value="${inwIntegrationTotal.totalPoints }" var="itt"></c:set>
                            </c:if>
                            <c:if test="${empty inwIntegrationTotal }">
                            0
                          	  <c:set value="0" var="itt"></c:set>
                            </c:if>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        
                        <c:if test="${ecMallStatus=='1' }">
	                        <tr>
	                            <td><label for="">激活信息:</label></td>
	                            <td>
	                            激活的会员编号为：${ecMallInfo.user_code }<br/>
	                            激活的手机号为：${ecMallInfo.ec_mall_phone }
	                            </td>
	                            <td>
	                            </td>
	                            <td></td>
	                        </tr>
                        </c:if>
                        
                        
                        
                        <tr>
                            <td><label for="">选择积分类型:</label></td>
                            <td>
                          
					 <security:authorize url="/app/fiBcoinBalanceform&strAction=BcoinType">
                            <input type="radio" name="coinType" value="B" >欢乐积分
                            </security:authorize>
					 <security:authorize url="/app/fiBcoinBalanceform&strAction=CcoinType">
                            <input type="radio" name="coinType" value="C" >创新积分
                            </security:authorize>
                            
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                       <%--  <tr>
                            <td><label for="">发送对象:</label></td>
                            <td>
					 <security:authorize url="/app/fiBcoinBalanceform&strAction=BcoinType1">
                            <input type="radio" name="mallType" value="1" >瓜藤网
                            </security:authorize>
					 <security:authorize url="/app/fiBcoinBalanceform&strAction=BcoinType2">
                            </security:authorize>
                            
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr> --%>
                         <input type="hidden" name="mallType" id="mallType" value="1" >
                        <tr>
                            <td><label for="">迁移积分数:</label></td>
                            <td>
                            	<input style="float: left" type="text" name="bcoinNum" id="bcoinNum"> 
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
	
                        <tr>
                            <td> </td>
                            <td >
                            <input  style="float: left" type="checkbox" id="agreementId" name="agreementId" checked="checked"> 
                            <span >
                            我已看过并接受	<a href="gt/gt_agreement.htm" style="color: rgb(0, 0, 238)" target="blank">&lt;&lt;瓜藤网积分迁移协议&gt;&gt;</a>
             </span>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        
                        <tr>
                            <td></td>
                            <td>
                            <table>
                            	<tr>
                            		<td  >
	                           <!--  <button type="submit" class="btn-primary btn_common ft14" name="save" onclick="bCancel=false">
					            	<i class="icon-ok icon-white"></i>&nbsp;确认提交&nbsp;
						        </button> -->
						        
								<div class="tc mt">
									<button type="submit"  onclick="javascript:checkSubmit();" class="btn btn-info">&nbsp;<span>确认提交</span>&nbsp;</button>	
									<button type="button" onclick="history.go(-1);" class="btn btn-success">&nbsp;&nbsp;&nbsp;&nbsp;<span>返&nbsp;&nbsp;回</span>&nbsp;&nbsp;&nbsp;&nbsp;</button>
								</div>	
                             		  
						        
						        </td>
					        	<td>
						        
						    </td>
                            	</tr>
                            	
                            	
                            
                            	
                            	
                            </table>
                             
					        </td>
							<td></td>
                            <td ></td> 
                        </tr>
                   </tbody>
                 </table>
    
</form:form>

</div>
</div> 
<script type="text/javascript">

    var checkSubmitFlg = false;

    function validateOthers(){
  	
  		if (!checkSubmitFlg) {

  			var agreementId = document.getElementById("agreementId");
  			
  			
  			if(agreementId.checked==false){
  				alert("未同意协议");
    			return false;
  			}
  			
  			
  			var coinType=$('input:radio:checked').val();
  			//console.log(coinType);
  			
  			
  			
  			var bcoinNum = document.getElementById("bcoinNum");
  			if(bcoinNum.value==null || isNaN(bcoinNum.value) || bcoinNum.value<0 || bcoinNum.value==0){
    	
    			alert("请输入需要迁移的积分数额，只允许输入大于0的数字!");
    			return false;
    		}
  			
  			//console.log(bcoinNum.value);
  			if(/^\d+$/.test(bcoinNum.value))  
  			{  
  			   
  			} else{
  				 alert("请输入正整数!"); 
  				return false;
  			} 
  			
  			
  			if("B"==coinType){
  				if(bcoinNum.value>${fiBcoinBalance.balance}){
  			    	
  	    			alert("迁移的积分数额超额，请检查您的欢乐积分帐户余额!");
  	    			return false;
  	    		}
  			}else if("C"==coinType){
  				if(bcoinNum.value>${itt}){
  			    	
  	    			alert("迁移的积分数额超额，请检查您的创新积分帐户余额!");
  	    			return false;
  	    		}
  			}
    		
  						
			if(window.confirm("您确定从积分帐户迁移积分"+bcoinNum.value+"至瓜藤网？")){
				
				checkSubmitFlg = true;
				//document.getElementById("save").disabled=true;
				return isFormPosted();
			}else{
			
				return false;	
			}
		}else {

			//重复提交
			alert("请勿重复提交!");
			return false;
		}
	}
    


</script> 