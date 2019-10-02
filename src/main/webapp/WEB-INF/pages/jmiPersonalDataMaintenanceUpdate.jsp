<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>


<!--<title><fmt:message key="jmiMemberDetail.title"/></title>-->
<!-- 个人资料维护 -->
	<link rel="stylesheet" href="css/style.css" />
    <c:if test="${not empty sessionScope.redirectTager }">
    <script>
        window.parent.location.href="login.html";
    </script>
    <c:remove var="redirectTager" scope="session"/>
    </c:if>
        
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml"><ng:locale key="menu.editJmiMemberProfile"/></h3>
			</div>	  
			
			
<!-- 下面是校验时的提示标语 -->
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
 <form:form commandName="jmiMember" method="post" action="jmiPersonalDataMaintenanceUpdate" id="jmiMemberForm" name="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
		 
			<table class="form_edit_table"> 				 
				<tr>     
					<td><ng:locale key="miMember.memberNo" />：</td><td>${ jmiMember.userCode} - 
						<ng:code listCode="member.level" value="${jmiMember.memberLevel}" /> 
						<c:if test="${jmiMember.isstore!='0'}"> - 
							<ng:code listCode="store.type" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.jmiMember.isstore}"></ng:code>
						</c:if>	</td>                                  
					<td style="display:none;"><ng:locale key="miMember.recommendNo"/>：</td><td style="display:none;">${jmiMember.recommendNo }</td>                   
					<td></td><td></td>                						
				</tr>
					
				<tr>     
					<td><span class="red">*</span><ng:locale key="miMember.petName"/>：</td><td><form:input path="petName" id="petName" /></td>                   
					<td><ng:locale key="miMember.sex" />：</td><td><ng:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue="" styleClass="mySelect"/></td>                                 
					<td></td><td></td>                  						
				</tr>	
				<tr>     
					<td><ng:locale key="miMember.papertype"/>：</td><td>	<ng:code listCode="papertype" value="${jmiMember.papertype }"/></td>                   
					<td><span class="red">*</span><ng:locale key="miMember.papernumber"/>： </td><td>
					<c:if test="${empty papernumberModify }">
								<form:input path="papernumber" id="papernumber" />
							</c:if>
							<c:if test="${not empty papernumberModify }">
								 ${ jmiMember.papernumber}
							</c:if>
					
					</td>                                 
					<td></td><td></td>                  						
				</tr>
				
				<tr>
					<td><span class="red">*</span><ng:locale key="miMember.province"/>：</td><td>
					<form:select path="province"  cssClass="text medium mySelect" onchange="getCity();">
							   <form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
							   <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
							</form:select></td>
					<td><span class="red">*</span><ng:locale key="miMember.idAddr2"/>：</td><td>
					<select name="city" id="city" class="mySelect" onchange="getAreaByCity();">
							   <option value=""><ng:locale key="list.please.select"/></option>
							</select>
					
					</td>
					<td><span class="red">*</span><ng:locale key="miMember.district" />：</td><td>
					
					<select name="district" id="district" class="mySelect" >
								<option value=""><ng:locale key="list.please.select"/></option>
							</select>
					</td>
					
				</tr>
				
				
				<tr>
					
					<td><span class="red">*</span><ng:locale key="miMember.idAddr"/>：</td><td><form:input path="address" id="address"  size ="80"/></td>
					<td><ng:locale key="miMember.phone" />：</td><td><form:input path="phone" id="phone" /></td>
					<td><span class="red">*</span><ng:locale key="miMember.mobiletele"/>：</td><td><form:input path="mobiletele" id="mobiletele" /></td>
				</tr>
				
				 
				<tr>     
					<td><ng:locale key="miMember.postalcode" />：</td><td><form:input path="postalcode" id="postalcode" /></td>                   
					<td><ng:locale key="miMember.spouseName" />：</td><td>${jmiMember.spouseName}</td>                                 
					<td><ng:locale key="miMember.spouseIdno" />：</td><td>${jmiMember.spouseIdno}</td>                  						
				</tr>
				
				<tr>     
					<td><ng:locale key="miMember.addrcl"/>：</td><td><form:input path="clAddress" id="clAddress"  size ="80"/></td>                   
					<td><ng:locale key="miMember.email"/>：</td><td><form:input path="email" id="email" /></td>                                 
					<td><ng:locale key="miMember.faxtele"/>：</td><td><form:input path="faxtele" id="faxtele" /></td>                  						
				</tr>
				
				<%-- <tr>
					<td><label class="pdl10"><ng:locale key="member.ugrade.time"/>：</label></td>
					<td>${days }</td>
					<td><label class="pdl10">
						
							 <ng:locale  key="jmiMember.validWeek"/>：
						</label>
					</td>
					<td colspan="3"  >
						<c:if test="${ jmiMember.gradeType!=0}" >
							 
						</c:if>														
					</td>
				</tr> --%>
				
				
				<c:if test="${(null!=jmiMember.bankcard && ''!=jmiMember.bankcard) || '3' ==jmiMember.memberUserType|| '2' ==jmiMember.memberUserType}">
				<tr>
					
					<c:if test="${'2' ==jmiMember.memberUserType && '1' ==jmiMember.isCloudshop}"><td>会员类型：</td><td>云客</td></c:if>
					<c:if test="${'3' ==jmiMember.memberUserType && '1' ==jmiMember.isCloudshop}"><td>会员类型：</td><td>脉客</td></c:if>
					<td><c:if test="${null!=jmiMember.bankcard && ''!=jmiMember.bankcard}">开户行：</c:if></td>
					<td><c:if test="${null!=jmiMember.bankcard && ''!=jmiMember.bankcard}">${ jmiMember.bank}</c:if></td>
					<td><c:if test="${null!=jmiMember.bankcard && ''!=jmiMember.bankcard}">银行帐号：</c:if></td>
					<td><c:if test="${null!=jmiMember.bankcard && ''!=jmiMember.bankcard}">*********${fn:substring(jmiMember.bankcard,fn:length(jmiMember.bankcard)-4,fn:length(jmiMember.bankcard))}</c:if></td>
					<c:if test="${'0' ==jmiMember.isCloudshop}"><td></td><td></td></c:if>
				
				</tr>
				</c:if>
				
			
				
				
			</table>
			<c:if test="${ jmiMember.gradeType!=0}" >
			<p id="validMonthYearId" class="mt ml gry">
			 <input type="hidden" value="<ng:monthFormat month='${jmiMember.validWeek}' monthType='w'/>" id="validMonthYear" name="validMonthYear"/>	    
						 
			<!-- 重销截止时间：2018工作年第04工作月  -->
			 
			 <script>
							 document.getElementById('validMonthYearId').innerHTML='<ng:locale  key="jmiMember.validWeek"/>：'+document.getElementById('validMonthYear').value.substr(0, 4)+'<ng:locale  key="bdPeriod.wyear"/><ng:locale  key="jmiMember.di"/>'+document.getElementById('validMonthYear').value.substr(4, 6)+'<ng:locale  key="bdPeriod.wmonth"/>';	
						  </script> 
			
			</p>
			</c:if>
			<div class="tc"> <button type="button"  onclick="addOrUpdate(document.jmiMemberForm)" >&nbsp;<span>保存</span>&nbsp;</button>
			<button type="button" class="btn btn-success ml" onclick="history.go(-1)" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>	
                             	
				</div>	
			
			<input type="hidden" name="papernumberCheck" value="${papernumberCheck }" id="papernumberCheck"/>
			</form:form>
	</div>				
     


</div>



<%-- <body>
	<h2 class="title mgb20"><ng:locale key="menu.editJmiMemberProfile"/></h2> --%>
			<%-- <table width="100%" class="personalInfoTab">
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<tbody>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.memberNo"/>：</label></td>
						<td colspan="5">${ jmiMember.userCode} -
	<ng:code listCode="member.level" value="${jmiMember.memberLevel}"/><c:if test="${jmiMember.isstore!='0'}">
					 - <ng:code listCode="store.type" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.jmiMember.isstore}"></ng:code> 
				</c:if></td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.recommendNo"/>：</label></td>
						<td colspan="5">${jmiMember.recommendNo }</td>
					</tr>
					<tr>
						<td><label class="star"><ng:locale key="miMember.petName"/>：</label></td>
						<td colspan="5"><form:input path="petName" id="petName" /></td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.sex"/>：</label></td>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.papertype"/>：</label></td>
						<td><ng:code listCode="papertype" value="${jmiMember.papertype }"/></td>
						<td><label class="star"><ng:locale key="miMember.papernumber"/>：</label></td>
						<td colspan="3">
							
						</td>
					</tr>
					<tr>
						<td><label class="star"><ng:locale key="miMember.province"/>：</label></td>
						<td>
							
						</td>
						<td><label class="star"><ng:locale key="miMember.idAddr2"/>：</label></td>
						<td>
							
						</td>
						<td nowrap="nowrap"><label class="pdl10"> <ng:locale key="miMember.district" />：</label></td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td><label class="star"><ng:locale key="miMember.idAddr"/>：</label></td>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.addrcl"/>：</label></td>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.postalcode" />：</label></td>
						<td colspan="5"></td>
					</tr>
					<tr>
						<td><label class="pdl10"> <ng:locale key="miMember.spouseName" />：</label></td>
						<td colspan="5">${ jmiMember.spouseName}</td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.spouseIdno" />：</label></td>
						<td colspan="5">${ jmiMember.spouseIdno}</td>
					</tr>
					<tr>
						<td><label class="pdl10"> <ng:locale key="miMember.phone" />：</label></td>
						<td></td>
						<td><label class="star"><ng:locale key="miMember.mobiletele"/>：</label></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale  key="miMember.faxtele"/>：</label></td>
						<td></td>
						<td><label class="pdl10"><ng:locale  key="miMember.email"/>：</label></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td><label class="pdl10"><ng:locale  key="member.ugrade.time"/>：</label></td>
						<td>${days }</td>
						<td><label class="pdl10">
						<c:if test="${ jmiMember.gradeType!=0}" >
								 <ng:locale  key="jmiMember.validWeek"/>：
							</c:if></label>
						</td>
						<td colspan="3" id="validMonthYearId" >
						<c:if test="${ jmiMember.gradeType!=0}" >
							<input type="hidden" value="<ng:monthFormat month='${jmiMember.validWeek}' monthType='w' />" id="validMonthYear" />	    
							<script>
								document.getElementById('validMonthYearId').innerHTML=document.getElementById('validMonthYear').value.substr(0, 4)+'<ng:locale  key="bdPeriod.wyear"/><ng:locale  key="jmiMember.di"/>'+document.getElementById('validMonthYear').value.substr(4, 6)+'<ng:locale  key="bdPeriod.wmonth"/>';	
							</script>
							</c:if>														
						</td>
					</tr>
					<tr>					   
						<td colspan="6"><a href="javascript:void(0);" class="btn_common corner2 fr" style="margin-top:50px;" name="save" value="<ng:locale key="operation.button.save"/>"><ng:locale key="save.new.add"/></a></td>
					</tr>
				</tbody>
			</table> --%>

		
		

	
    <script src="<c:url value='/dwr/util.js'/>" ></script>
    <script src="<c:url value='/dwr/engine.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>
	<script>
	var qquery = jQuery.noConflict();
   //在初始化或者选取省份的时候执行该方法
	function getCity(){
		var provinceId = qquery("#province").val();
		//通过ＤＷＲ开源框架，这里调用java后台的项目，并且使用了回调函数
		jalCityManager.getAlCityByProvinceId(provinceId,callIdCity);
	}
	
	//回调函数
	function callIdCity(valid){
		//去掉下拉框中原有的数值
		DWRUtil.removeAllOptions("city");
		//给下拉框加上受选项－请选择(select)
		DWRUtil.addOptions("city",{'':'<ng:locale key='list.please.select'/>'});
		//将集合valid中的数据放入下拉框中
		DWRUtil.addOptions("city",valid, "cityId","cityName");
		if(''!='${jmiMember.city}'){
		　　　　　//使用别名的jQuery代码
		　　　　    //将数据库中查询到的城市的代码放入下拉框，就是使下拉框选中对应的城市
		　　　　　qquery("#city").val('${jmiMember.city}');　
			//通过城市获取地区
			getAreaByCity();
		} 
	}    

	//通过城市获取地区的方法
	function getAreaByCity(){

		 var cityId = qquery("#city").val();
		 //使用ＤＷＲ开源框架，调用java后台的代码
	　　　　jalDistrictManager.getAlDistrictByCityId(cityId,callIdDistrict);
	}

	//回调函数－获取所在地区
	function callIdDistrict(districtList){
	   DWRUtil.removeAllOptions("district");
	   DWRUtil.addOptions("district",{'':'<ng:locale key='list.please.select'/>'});
	   DWRUtil.addOptions("district",districtList,"districtId","districtName");
	   if(''!='${jmiMember.district}'){
	   
		　　　　　//使用别名的jQuery代码
		　　　　    //将数据库中查询到的地区的代码放入下拉框，就是使下拉框选中对应的地区
		　　　　　qquery("#district").val('${jmiMember.district}');　
	   }
	}

	function addOrUpdate(theFrom){
	   theFrom.submit();
	}
	getCity();
</script>
</body>





















