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
        	var ecMallPhone=$('#ecMallPhone').val();
        	//alert(ecMallPhone);
        	var patrn=/^([0-9]{11})$/;	
			if(!patrn.exec(ecMallPhone)){
				alert('手机号码错误');
				return;
			}else{
				sendMessage();
				jmiSmsNoteManager.sendSms(ecMallPhone,callBack);
			}
        }
        
        function callBack(valid){
        	if(valid==''){
        		//sendMessage();
        	}else{
        		alert(valid);
        	}
        	
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
			<h3 class="color2 ml">瓜藤网账号激活</h3>
		</div>	
	<div class="mt">

<form:form commandName="jmiMember" method="post" action="jmiActivateEcMallform" name="jmiMemberform" id="jmiMemberform">
              <table class="form_edit_table" style="margin-top: 40px;margin-bottom: 100px;">
                   
                   
                    
                     
                    
                        <tr>
                            <td style="width:35%;text-align:right;"><label class="star">瓜藤网绑定手机号码：</label></td> 
                            <td  style="padding:12px 0;">    	
			                        <c:if test="${ecMallStatus=='1' }">
			                        	<div>${ecMallInfo.ec_mall_phone }</div>
			                        
			                        </c:if>
			                        <c:if test="${ecMallStatus=='0' }">
			                    	    <form:input path="ecMallPhone" id="ecMallPhone" style="height: 35px;"/>
			                        
			                        </c:if>
                            </td>
                        </tr>
                        
                        <c:if test="${ecMallStatus=='1' }">
                        <tr>
                        	<td style="width:35%;text-align:right;"><label class="star">瓜藤网绑定的会员编号：</label></td> 
                       		 <td  style="padding:12px 0;">    	
			                       <div>${ecMallInfo.user_code }</div>
                            </td>
                         </tr>
                        </c:if>
                        <c:if test="${ecMallStatus=='1' }">
                        
	                        <tr>
	                            <td style="width:35%;text-align:right;">进入瓜藤网：</td>
	                            <td  style="padding:12px 0;">    	
				                        <a style="color: blue" href="http://www.guaten.com" target="_blank">点击此处</a>
	                            </td>
	                        </tr>
                        
                        </c:if>
                        <c:if test="${ecMallStatus=='0' }">
	                         <tr>
	                            <td style="width:35%;text-align:right;"><label class="star">验证码：</label></td>
	                            <td  style="padding:12px 0;">
	                            	<input type="text" name="verifyCode" id="verifyCode" style="height: 35px;"/>
	                            	  <button id="btnSendCode" type="button" onclick="sendSms()" class="btn btn-warning">发送验证码</button>
	                            </td>
	                        </tr>
	                        <!-- <tr>
	                            <td><label class="star">瓜藤网登陆密码：</label></td>
	                            <td><input type="password" name="ec_mall_password" id="ec_mall_password"/> 由字母,数字,下划线组成(4-16个) </td>
	                        </tr>
	                        <tr>
	                            <td><label class="star">瓜藤网登陆密码确认：</label> </td>
	                            <td><input type="password" name="ec_mall_password_confirm" id="ec_mall_password_confirm"/></td>
	                        </tr> -->
	                       <!--  <tr>
	                            <td><label class="pdl10">邀请人：</label></td>
	                            <td><input type="text" name="recommend_mobile" placeholder="请填写手机号" id="recommend_mobile"/> 瓜藤网账号</td>
	                        </tr> --><input type="hidden" name="recommend_mobile" id="recommend_mobile"/>
	                        <tr>
                            <td style="width:35%;text-align:right;">&nbsp;</td> 
                            <td style="padding:12px 0;"> 
                               	<!-- 生产 -->
			                 <img height="150px" width="150px" src="http://www.guaten.com/static/selfdossy/images/1462434756.png"><br>	
                               	<!-- 测试 -->
			                 <!-- <img height="150px" width="150px" src="http://m.test.guaten.cn/images/1462418420.png">-->
                           <label  style="color: red;width:17%;" >&nbsp;扫一扫,加瓜藤</label>
                            </td>
                        </tr>
	                        <tr>
	                            
	                            <td > 
	                            </td>
	                            <td align="left">
	                            	<button type="button" onclick="submitFormCommit(jmiMemberform);" class="btn btn-info">保&nbsp;&nbsp;存</button>
	                            	<button type="button" onclick="history.go(-1);" class="btn btn-success">返&nbsp;&nbsp;回</button>
					 			 </td>
	                        </tr>
                        
                        </c:if>
                      
                   
                </table>
                
                
               </form:form>
		</div>
</div>