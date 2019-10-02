<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<style>
#toolkit{position:absolute;left:5px;top:75px;font-style:italic;font-size:14px;font-weight:bold;color:#A7A8AA;width:720px;height:200px;} 
</style>
<script src='<c:url value="/ckeditor/ckeditor.js" />' ></script>
<script>
var editor;
function checkSubmit() {
	if(editor) {
		var content = editor.document.getBody().getText();
		if(content.trim().length==0){
			alert("请输入非空内容");
			return;
		}
	}
	if(isFormPosted()){
		document.sysUserEditForm.submit();
	} 
}
</script>



<spring:bind path="amMessage.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>



        
<div class="cont mt mr">	
			<div class="bt mt">
				<h3 class="color2 ml">信息</h3>
			</div>	 
 <div class="mt">

<form:form commandName="amMessage" method="post" action="amMessageform" name="sysUserEditForm" id="sysUserEditForm">
<input type="hidden" name="strAction" value="${param.strAction }" />



<form:hidden path="uniNo"/>

	<table width="90%" class="mt" style="margin:0 auto;" > 		
              
                    <tbody>
                       
                        <tr>
                            <td><label class="star"><ng:locale key="amMessage.msgClassNo" />：</label></td>
                            <td>    
                            	<c:choose>
						    		<c:when test="${param.strAction == 'viewAmMessage'}">	
						        		<ng:code listCode="msgclassno" value="${amMessage.msgClassNo}"/>
						        	</c:when>
						    		<c:when test="${param.strAction == 'addAmMessage' || param.strAction == 'editAmessage' }">	
						        		 <ng:list listCode="msgclassno" onchange="changeType()" name="msgClassNo" id="msgClassNo" value="${amMessage.msgClassNo}" defaultValue=""/>
						        	</c:when>
						    		<c:when test="${param.strAction == 'editReplyAmMessage'}">	
						        		<ng:code listCode="msgclassno" value="${amMessage.msgClassNo}"/>
						        	</c:when>
                            	</c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="star"><ng:locale key="amMessage.subject" />：</label></td>
                            <td>   
                            
                            	<c:choose>
						    		<c:when test="${param.strAction == 'viewAmMessage'}">	
						        		${amMessage.subject }
						        	</c:when>
						    		<c:when test="${param.strAction == 'addAmMessage' || param.strAction == 'editAmessage' }">	
						        		<form:input path="subject" id="subject" cssClass="text medium"/>
						        	</c:when>
						    		<c:when test="${param.strAction == 'editReplyAmMessage'}">	
						        		${amMessage.subject }
						        	</c:when>
                            	</c:choose>
                            
                            </td>
                        </tr>
                        
                        <c:if test="${param.strAction != 'editReplyAmMessage'}">
	                        <tr>
	                            <td><label class="star"><ng:locale key="amMessage.content" />：</label></td>
	                            <td>
	                            	<div class="rel" id="et" style="width: 700px">
							    		<c:if test="${param.strAction == 'addAmMessage' ||  param.strAction == 'editAmessage'  }">
							    		
			    							<textarea id="content" name="content">${amMessage.content }</textarea>
				    							<script>
				    							editor = CKEDITOR.replace( 'content' );
												</script>
													
							    		</c:if>
							    		<c:if test="${param.strAction == 'viewAmMessage' }">	
							    			${amMessage.content }
							    		</c:if>
							    	</div>	
	                            </td>
	                        </tr>
                        </c:if>
                        
                        <c:if test="${param.strAction == 'viewAmMessage'}">
	                        <tr>
	                            <td><label class="star"><ng:locale key="amMessage.replyContent" />：</label></td>
	                            <td>
	                            	
							    		${amMessage.replyContent }
							    		
	                            </td>
	                        </tr>
                        </c:if>
                        
                        <c:if test="${param.strAction == 'editReplyAmMessage'}">
	                        <tr>
	                            <td><label class="star"><ng:locale key="amMessage.replyContent" />：</label></td>
	                            <td>
	                            	<form:textarea path="replyContent" id="replyContent" rows="10" cols="50"/>
	                            </td>
	                        </tr>
                        </c:if>
                        
                        
                        
                    </tbody>
                </table>
                <div class="tc" style="margin-top: 10px;"><c:choose>
						    		<c:when test="${param.strAction == 'viewAmMessage'}">	
						        		
						        	</c:when>
						    		<c:when test="${param.strAction == 'addAmMessage' || param.strAction == 'editAmessage' }">	
						        		<!--  <a href="javascript:checkSubmit();" class="btn_common corner2">发&nbsp;送</a> -->
						        		 
						   <button type="button"  onclick="javascript:checkSubmit();" >&nbsp;<span>发&nbsp;送</span>&nbsp;</button>	
                                 		 
						        		 
						        	</c:when>
						    		<c:when test="${param.strAction == 'editReplyAmMessage'}">	
						        	<!-- 	 <a href="javascript:checkSubmit();" class="btn_common corner2">发&nbsp;送</a> -->
						        		 
						     <button type="button"  onclick="javascript:checkSubmit();" >&nbsp;<span>发&nbsp;送</span>&nbsp;</button>		
                             		  
						        		 
						        	</c:when>
                            	</c:choose>
                            	<button type="button" class="btn btn-success" onclick="history.go(-1)" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>
                             	</div>
	</form:form>


</div>
		</div>

	<script>
	
	
	function changeType(){
		var opV = $("#msgClassNo").children(":selected").val();

		 <c:if test="${not empty amMessage.content}">
		 opV ="99";
        </c:if>
		var str1 =   '<div id="toolkit">'+
		'<span>问题描述提示：</span><br>'+
		'1、消息类别需增加多个物流相关选项，（如：查货、改地址、暂停发货、恢复发货、扣运费、仓储费等）。<br>'+
		'2、物流相关的每条留言，需有订单号、消息描述、留言人电话必填项。<br>'+
		'3、当消息类别为改地址时，消息内容中请添加新地址、新收货人、新电话可填空白框。<br>'+
		'4、增加留言单页全选功能。<br>'+
		'请按问题描述提交信息，谢谢。'+
		'</div>';
		var str2 =   '<div id="toolkit">'+
						'<span>问题描述提示：</span><br>'+
						'询问奖金需要告知您有疑问的奖金项目、结算期别、订单编号，以便为您查询。<br>'+
						'请按问题描述提交信息，谢谢。'+
					'</div>';
		var str4 =   '<div id="toolkit">'+
					'<span>问题描述提示：</span><br>'+
					'咨询业务办理进度需注明办理业务的会员编号、姓名、联系方式、业务类型及业务办理提交时间信息。<br>'+
					'请按问题描述提交信息，谢谢。'+
				'</div>';
				var str10 =   '<div id="toolkit">'+
				'<span>问题描述提示：</span><br>'+
				'申请扣除业务操作费需注明业务办理的会员编号、姓名、业务类型及业务办理费扣除的会员编号信息。<br>'+
				'请按问题描述提交信息，谢谢。'+
			'</div>';
			var str11 =   '<div id="toolkit">'+
			'<span>问题描述提示：</span><br>'+
			'为更好的为您提供服务，留言中需注明具体咨询的业务内容和资料信息。<br>'+
			'请按问题描述提交信息，谢谢。'+
		'</div>';
		var str12 =   '<div id="toolkit">'+
		'<span>问题描述提示：</span><br>'+
		'需将出现质量问题产品拍成照片发送至公司邮箱zmsh2009@163.com，需附上会员编码、订单号、姓名、联系电话、地址及情况说明。<br>'+
		'请按问题描述提交信息，谢谢。'+
	'</div>';

var str13 =   '<div id="toolkit">'+
'<span>问题描述提示：</span><br>'+
'需说明换货原因，并告知您所换产品明细、更改后的产品明细，以便为您尽快办理。<br>'+
'请按问题描述提交信息，谢谢。'+
'</div>';
var str14 =   '<div id="toolkit">'+
'<span>问题描述提示：</span><br>'+
'需告知您购买的配件名称、数量及在扣款编号，需附上寄回地址、收件人、电话。<br>'+
'请按问题描述提交信息，谢谢。'+
'</div>';
var str15 =   '<div id="toolkit">'+
'<span>问题描述提示：</span><br>'+
'进行账户之间转账操作，烦请您注明需转账金额，转账类别(定向基金/分红基金/电子存折，发展基金转账烦请您联系业务处理中心申请)，以及需转入的会员编号。<br>'+
'请按问题描述提交信息，谢谢。'+
'</div>';
var str16 =   '<div id="toolkit">'+
'<span>问题描述提示：</span><br>'+
'对于误操作，以及发放失败的提现奖金进行转报单操作。需请您准确注明取消提现得金额。<br>'+
'请按问题描述提交信息，谢谢。'+
'</div>';
$("#toolkit").remove();
		
		if( opV == 1 ){
			//alert(opV);
			$("#cke_1_contents").append(str1);		
		}else if(opV == 2 ){
			$("#cke_1_contents").append(str2);		
		}else if(opV == 4 ){
			$("#cke_1_contents").append(str4);		
		}else if(opV == 10 ){
			$("#cke_1_contents").append(str10);		
		}else if(opV == 11 ){
			$("#cke_1_contents").append(str11);		
		}else if(opV == 12 ){
			$("#cke_1_contents").append(str12);		
		}else if(opV == 13 ){
			$("#cke_1_contents").append(str13);		
		}else if(opV == 14 ){
			$("#cke_1_contents").append(str14);		
		}else if(opV == 15 ){
			$("#cke_1_contents").append(str15);		
		}else if(opV == 16 ){
			$("#cke_1_contents").append(str16);		
		}else{
			//$("#toolkit").remove();
		}
		
}
	
		$(function(){
			$("#msgClassNo").change(function(){

			});
			
			$("#et").delegate("#cke_content","click",function(){
				$("#toolkit").remove();
			});
			
		});
		
		

		setTimeout("changeType()", 500);
	</script>



