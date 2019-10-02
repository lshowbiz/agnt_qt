<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript" src="<c:url value='/scripts/region_without_xj.js'/>"></script>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiLinkRefManager.js'/>"></script>

<script type="text/javascript">


function submitFormCommit1(theForm){
/* 	var pwd=$('#password1').val();
	if(checkStrong(pwd)<=1){
		alert("您的密码过于简单，请重新输入");
		return false;
	}
	 */
	if(isFormPosted()){
		theForm.submit();
	} 
}

function autoLinkNo(){
	var recommendNo=$('#recommendNo').val();
	jmiLinkRefManager.getLinkNo(recommendNo,autoLinkNoCallBack);
}
function autoLinkNoCallBack(valid){
	$('#linkNo').val(valid);
}
</script>


        
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">会员注册信息</h3>
			</div>	 

<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

 <div class="mt">
        <form:form commandName="jmiMember" method="post" action="jmiMemberCreateform" id="jmiMemberform" name="jmiMemberform"  >
        <form:hidden path="userCode"/>
			
			
			<table width="90%" class="mt" style="margin:0 auto;" >
						<tr style="display: none;">
                            <td><label class="star fr">会员类型：</label></td>
                            <td><ng:list name="memberUserType" id="memberUserType" listCode="member_user_type" onclick="" onchange="" value="${jmiMember.memberUserType}" defaultValue="1" styleClass="mySelect" />
                            </td>
                        </tr>
                        <tr>
                            <td><label class="star fr"><ng:locale key="miMember.recommendNo" />：</label></td>
                            <td> <form:input path="jmiRecommendRef.recommendJmiMember.userCode" id="recommendNo" cssStyle="float: left;"/>
                
                             <security:authorize url="/app/netView">
                          <!--  <input type="button"  name="submit1" value="" class="btn_common btn_mini corner2 fl ml"/>  -->
<button type="button" class="ml btn btn-success" style="margin-top: 6px"  onclick="miSelectMember(this.form)" ><span><ng:locale key="operation.button.search"/></span></button>
                                <script type="text/javascript">
                                function showDialog(siteroot,params,width,Height,scroll) {
                                    var features="height="+Height+",width="+width;
                                    if(scroll!=undefined && scroll!=""){
                                        features+=",scrollbars";
                                    }

                                    newWindow = window.open(params[1],"subWind","status,menubar,"+features);
                                    newWindow.focus();
                                }
                                function miSelectMember(theForm){
                                    //if(theForm.recommendNo.value==""){
                                    //	alert('<ng:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
                                    //	theForm.recommendNo.focus();
                                    //	return;
                                    //}
                                    var pars=new Array();
                                    pars[0]="<ng:locale key='function.menu.findMembers'/>";
                                    pars[1]="jmiSelectRecommendRef?userCode=" + theForm.recommendNo.value;
                                    pars[2]=window;
                                    var ret=showDialog("<%=request.getContextPath()%>",pars,870,550,1);
                                    if(ret!=undefined){
                                        theForm.linkNo.value=ret;
                                    }
                                }

                                </script> 
                                </security:authorize>
                                
                           <%--  <c:if test="${sessionScope.teamCode=='AJ34272972' }">
                            
<button type="button" class="ml btn btn-success" style="margin-top: 6px"  onclick="autoLinkNo()" ><span>自动安置</span></button> 
                            </c:if> --%>
                            
                                
                            </td>
                            <td><label class="star fr"><nobr><ng:locale key="miMember.linkNo" />：</nobr></label></td>
                            <td><form:input path="jmiLinkRef.linkJmiMember.userCode" id="linkNo" /></td>
                            <td></td>
                        </tr>
                        <!-- <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr> -->
                        <%-- <tr>
                            <td><label class="star fr"><ng:locale key="miMember.firstName" />：</label></td>
                            <td><form:input path="firstName" id="firstName" /></td>
                            <td><label class="pdl10 fr"><ng:locale key="miMember.firstNamePy" />：</label></td>
                            <td><form:input path="firstNamePy" id="firstNamePy" /></td>
                            <td>请输入大写字母</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="star fr"><ng:locale key="miMember.lastName" />：</label></td>
                            <td><form:input path="lastName" id="lastName" /></td>
                            <td><label class="pdl10 fr"><ng:locale key="miMember.lastNamePy" />：</label></td>
                            <td><form:input path="lastNamePy" id="lastNamePy" /></td>
                            <td>请输入大写字母</td> 
                            <td></td>
                        </tr>
                        <tr> 
                            <td><label class="star fr"><ng:locale key="miMember.petName" />：</label></td>
                            <td><form:input path="petName" id="petName" /></td>
                            <td><label class="star fr"><ng:locale  key="miMember.sex" />：</label></td>
                            <td><ng:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue="" styleClass="mySelect" /></td>
                            <td></td>
                            <td></td>
                        </tr> --%>
                        <tr>
                            <td><label class="star fr"> <ng:locale  key="miMember.papertype"/>：</label></td>
                            <td>
                               <ng:list name="papertype" id="papertype" listCode="paper.member" value="${jmiMember.papertype}" defaultValue="1" styleClass="mySelect" />
                            </td>
                            <td><label class="star fr"> <ng:locale key="miMember.papernumber" />：</label></td>
                            <td><form:input path="papernumber" id="papernumber"  /></td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr style="display: none">
                            <td><label class="star fr"><ng:locale key="miMember.province" />：</label></td>
                            <td>
                                <form:select path="province" cssClass="formerror mySelect" onchange="getIdCity();">
                                    <form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
                                    <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
                                </form:select>
                            </td>
                            <td><label class="star fr"><ng:locale key="miMember.idAddr2" />：</label></td>
                            <td>
                                <select name="city" id="city" class="mySelect" onchange="getIdDistrict();">
                                    <option value=""><ng:locale key="list.please.select"/></option>
                                </select>
                            </td>
                            <td><label class="pdl10 fr"><nobr><ng:locale key="miMember.district" />：</nobr></label></td>
                            <td>
                                <select name="district" id="district" class="mySelect">
                                    <option value=""><ng:locale key="list.please.select"/></option>
                                </select>
                            </td>
                        </tr>
                        <%-- <tr>
                            <td><label class="star fr"><ng:locale key="miMember.idAddr"/>：</label></td>
                            <td colspan="4"><form:input path="address" id="address"  cssStyle="width:80%;"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="pdl10 fr"><ng:locale key="miMember.addrcl"/>：</label></td>
                            <td colspan="4"><form:input path="clAddress" id="clAddress"  cssStyle="width:80%;"/></td>
                            <td></td>
                        </tr>
                        <!-- <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr> -->
                        <tr>
                            <td><label class="pdl10 fr"><ng:locale key="miMember.phone" />：</label></td>
                            <td><form:input path="phone" id="phone" /></td>
                            <td><label class="star fr"><ng:locale key="miMember.mobiletele"/>：</label></td>
                            <td><form:input path="mobiletele" id="mobiletele" /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="pdl10 fr"><ng:locale  key="miMember.faxtele"/>：</label></td>
                            <td><form:input path="faxtele" id="faxtele" /></td>
                            <td><label class="pdl10 fr"><ng:locale  key="miMember.email"/>：</label></td>
                            <td><form:input path="email" id="email" /></td>
                            <td><label class="pdl10 fr"> <ng:locale key="miMember.postalcode" />：</label></td>
                            <td><form:input path="postalcode" id="postalcode" /></td>
                        </tr>
                        <tr>
                            <td><label class="star fr"><ng:locale key="miMember.pwd1"/>：</label></td>
                            <td ><form:password path="sysUser.password"  id="password1" onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)"/></td>
                            <td colspan="3"><table class="pwdLv" width="30%" border="1">  
						<tr>  

							<td width="33%" align="center" id="strength_L">弱</td>  

							<td width="33%" align="center"  id="strength_M">中</td>  

							<td width="33%" align="center"  id="strength_H">强</td>  
						</tr>  
					</table> 
                            </td>
                          
                        </tr>
                        <tr>
                            <td><label class="star fr"><ng:locale key="label.affirmNewPassword" />：</label></td>
                            <td><input id="password1Confirm" name="password1Confirm" type="password" class="formerror"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="star fr"><ng:locale key="miMember.pwd2" />：</label></td>
                            <td colspan="4"><form:password path="sysUser.password2"  id="password2"/></td>
                        </tr>
                        <tr>
                            <td><label class="star fr"><nobr><ng:locale key="label.affirmAdvancedPassword" />：</nobr></label></td>
                            <td><input id="password2Confirm" name="password2Confirm" type="password" class="formerror"></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr> --%>
                        <tr>
                            <td colspan="6">
                            
                            <!-- <a href="#"  onclick="submitFormCommit1(jmiMemberform);" class="btn_common corner2 fr mgt30">提交注册</a> -->
                            
                            <div class="tc"> <button type="button"  onclick="submitFormCommit1(jmiMemberform);" >&nbsp;<span>提交注册</span>&nbsp;</button>	</div>	
                            </td>
                        </tr>
                        
                </table>



</form:form>

</div>
</div>
