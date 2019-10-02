<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiSmsNoteManager.js'/>"></script>

<script type="text/javascript">

        /*-------------------------------------------*/
        var InterValObj; //timer变量，控制时间
var count = ${validTime}; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var code = ""; //验证码
var codeLength = 6;//验证码长度
function sendMessage() {
            curCount = count;
            var dealType; //验证方式
			var uid=$("#uid").val();//用户uid
			if ($("#phone").attr("checked") == true) {
                dealType = "phone";
            }
            else {
                dealType = "email";
            }
            //产生验证码
			for (var i = 0; i < codeLength; i++) {
                code += parseInt(Math.random() * 9).toString();
            }
            //设置button效果，开始计时
                $("#btnSendCode").attr("disabled", "true");
                $("#btnSendCode").html("请在" + curCount + "秒内输入验证码");
                InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
            
              //  $.ajax({
              //      type: "POST", //用POST方式传输
              //      dataType: "text", //数据格式:JSON
              //      url: 'Login.ashx', //目标地址
               //     data: "dealType=" + dealType +"&uid=" + uid + "&code=" + code,
               //     error: function (XMLHttpRequest, textStatus, errorThrown) { },
               //     success: function (msg){ }
              //  });
            
            }
        //timer处理函数
function SetRemainTime() {
            if (curCount == 0) { 
                window.clearInterval(InterValObj);//停止计时器
                $("#btnSendCode").removeAttr("disabled");//启用按钮
                $("#btnSendCode").html("重新发送验证码");
                code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效 
            }
            else {
                curCount--;
                $("#btnSendCode").html("请在" + curCount + "秒内输入验证码");
            }
        }
        
        function sendSms(){
        	var cloudshopPhone=$('#cloudshopPhone').val();
        	//alert(ecMallPhone);
        	var patrn=/^([0-9]{11})$/;	
			if(!patrn.exec(cloudshopPhone)){
				alert('手机号码错误');
				return;
			}else{
				sendMessage();
				jmiSmsNoteManager.sendSms(cloudshopPhone,callBack);
			}
        }
        
        function callBack(valid){
        	if(valid==''){
        		//sendMessage();
        	}else{
        		alert(valid);
        	}
        	
        }
        function ckGzh(){
       		if (!$('#agree2').attr('checked')) {
       		    alert('请认真阅读告知函并同意!');
       		    return false;
       		}
        	submitFormCommit(jmiMemberform);
        }
</script>



<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>



<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml">云店账号激活</h3>
		</div>	
	<div class="mt">

<form:form commandName="jmiMember" method="post" action="jmiActivateCloudshopform" name="jmiMemberform" id="jmiMemberform">
              <table class="form_edit_table" style="margin-top: 40px;margin-bottom: 100px;">
                   
                   
                    
                     	<c:if test="${'1'=='1'}">
                     	

                    	
                        <tr>
                            <td style="width:20%;text-align:right;"><label class="star">云店绑定手机号码：</label></td>
                            <td  style="padding:12px 0;">    	
			                        <c:if test="${not empty cloudshopPhone }">
			                        	<div>${cloudshopPhone }</div>
			                        
			                        </c:if>
			                        <c:if test="${empty cloudshopPhone}">
			                    	    <form:input path="cloudshopPhone" id="cloudshopPhone" style="height: 35px;"/>
			                        
			                        </c:if>
                            </td>
                            <c:if test="${empty cloudshopPhone}">
                           <td style="width:20%;text-align:right;"><label class="star">验证码：</label></td>
                            <td  style="padding:12px 0;">
                            	<input type="text" name="verifyCode" id="verifyCode" style="height: 35px;"/>
                            	  <button  id="btnSendCode" type="button" onclick="sendSms()" class="btn btn-warning">发送验证码</button>
                            </td>
                            </c:if>
                        </tr>
                        <c:if test="${empty cloudshopPhone}">
                        <tr>
	                            <td  style="padding-top:12px;" colspan="4">
	                            	<ul class="provision">
										<li><jsp:include flush="true" page="cloudshop_attachment.jsp"></jsp:include>
										</li>
			
									</ul>	 </td>							
						</tr>		 
						
							 <tr>		
									<td>						 									 	
										<div align="left">
										<input type="checkbox" name="agree2" id="agree2" value="agree2" /><label
											for="agree_2"><span class="ft12 pdl10">同意告知函</span>
										</label>
								 </td>	
							 </tr>	
							<tr>			
				                           
	                            <td align="center" colspan="2">
	                            	<button type="button" onclick="ckGzh()" class="btn btn-info">激&nbsp;&nbsp;活</button>
					 			 </td>	                                           
                        </c:if>
                        		<td colspan="1"></td>
                         		<td colspan="1">
										<div class="tc"><img alt="IOS客户端" src="http://e1.jmtop.com/jecs/images/app.jpg" width="130" height="130">
										<p class="tc">云店 APP</p></div>
									</div>
	                            </td>
	                    </tr>
                        
                        <%-- <c:if test="${not empty cloudshopPhone }">
                        
	                        <tr>
	                            <td style="width:35%;text-align:right;">进入云店：</td>
	                            <td  style="padding:12px 0;">    	
				                        <a style="color: blue" href="#" target="_blank">点击此处</a>
	                            </td>
	                        </tr>
                        
                        </c:if> --%>
               
                      
                   		</c:if>
                   		
                     	<c:if test="${isCloudshop=='0'}">
                     		 <tr>
                            <td style="width:35%;text-align:right;"></td> 
                            <td  style="padding:12px 0;">    	
			                       暂不能申请
                            </td>
                        </tr>
                     	</c:if>
                </table>
                
                
               </form:form>
		</div>
</div>