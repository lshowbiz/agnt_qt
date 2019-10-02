<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType = "text/html; charset=utf-8" language="java"  %>
<!--
<style>

table.detail {
	
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
	border: 1px solid #dadada;
}

table.detail th {
	width:150px;
	border: 1px solid #dadada;
	color: #333333;
	text-align: right;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
}

table.detail th label {
	width:100%;
	text-align: right;
}

table.detail th.tallCell {
    vertical-align: top;
}

table.detail th.command{
	border: 1px solid #dadada;
	color: #165AB3;
	text-align: right;
	background: #f0f0f0;
	height: 20px;
	padding: 4px;
}

table.detail td {
	border: 1px solid #dadada;
	color: black;
	padding: 4px;
}

table.detail td.moveOptions {
    text-align: center;
    width: 50px;
    padding: 4px;
}

table.detail td.moveOptions button {
    margin-bottom: 3px;
    width: 45px;
    white-space: nowrap;
}

table.detail td.command{
	border: 1px solid #dadada;
	color: black;
	padding: 4px;
	background: #f0f0f0;
}

table.detail td.title{
	border: 1px solid #dadada;
	color: #6B91C9;
	background: #E1E9F4;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
	font-weight: bold;
}

table.detail td.buttonBar {
    padding-top: 10px;
}

table.detail td.updateStatus {
    font-size: 11px;
    color: #c0c0c0;
}

table.detailSub td{
	border: none;
	padding: 1px;
}

table.inSideTable {
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
}

table.inSideTable td {
	border: 1px none #dadada;
	color: black;
	padding: 4px;
}
</style> -->
<!--<title><fmt:message key="jmiMemberDetail.title"/></title>-->
<!-- 银行信息修改 -->
<c:if test="${not empty sessionScope.redirectTager }">
        
		
<script>
	window.parent.location.href="login.html";
</script> 
	<c:remove var="redirectTager" scope="session"/>
</c:if>	   
        
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>



<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">添加银行信息 &nbsp;(银行卡与身份证持有人一致)</h3>
			</div>	  



 <div class="mt">	
<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>
<form:form commandName="jmiMember" method="post" action="jmiBankInformationChange.html" id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
<c:if test="${ empty jmiMember.bankProvince ||  empty jmiMember.bankCity||   empty jmiMember.bank || empty jmiMember.bankaddress ||   empty jmiMember.bankcard }">
	<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="operate_code" value="${operate_code }"/>
	<!-- <h2 class="title mgb20">添加银行信息 &nbsp;(银行卡与身份证持有人一致)</h2> -->
	<input type="hidden" id="oldBank" value="${oldBank }">
	<input type="hidden" id="oldBankaddress" value="${oldBankaddress }">
	<input type="hidden" id="oldBankbook" value="${oldBankbook }">
	<input type="hidden" id="oldBankcard" value="${oldBankcard }">
	<input type="hidden" id="userCode" value="${jmiMember.userCode }">
	<table width="90%" class="mt" style="margin:0 auto;" > 		

		<tr>
			<td><label class="star"><ng:locale key="miMember.bankProvince"/></label>： </td>
			<td>
			<form:select path="bankProvince" class="mySelect" onchange="getBankCity()" disabled="${bankProvinceModify }">
				<form:option label="" value=""/>
				<form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
			</form:select>
			</td>
			<td><label class="star"><ng:locale  key="miMember.bankCity"/></label>：</td>
			<td>
				<select id="bankCity" name="bankCity" class="mySelect" <c:if test="${not empty bankCityModify }"> disabled="true"</c:if> >
					<option value=""><ng:locale key="list.please.select"/></option>
				</select>
			</td>
		</tr>

		<tr>
			<td><label class="star"><ng:locale key="miMember.openBank"/></label>：</td>
			<td>
				<c:if test="${empty bankModify }">
					<form:select path="bank" class="mySelect">
						<form:option value=""></form:option>
						<form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
					</form:select>
				</c:if>
				<c:if test="${not empty bankModify }">
					<c:forEach items="${sysBankList }" var="curSysBankList">
						<c:if test="${curSysBankList.bankKey==jmiMember.bank }">
							${curSysBankList.bankValue }
						</c:if>
					</c:forEach>
				</c:if>
			</td>
			<td><label class="star"><ng:locale key="miMember.bcity"/></label>：</td>
			<td>
				<c:if test="${empty bankaddressModify }">
					<form:input path="bankaddress" id="bankaddress" />
				</c:if>
				<c:if test="${not empty bankaddressModify }">
					${ jmiMember.bankaddress}
				</c:if>
			</td>
		</tr>

		<tr>
			<td><label class="star"><ng:locale key="miMember.bname"/></label>：</td>
			<td>${jmiMember.bankbook }</td>
			<td><label class="star"><ng:locale key="miMember.bnum"/></label>：</td>
			<td>
				<c:if test="${empty bankcardModify }">
					<form:input path="bankcard" id="bankcard" />
				</c:if>
				<c:if test="${not empty bankcardModify }">
					${ jmiMember.bankcard}
				</c:if>
			</td>
		</tr>
	</table>
    <%-- <div><input type="button" class="btn_common corner2 fr" onclick="saveBank();" style="margin-top:50px;" value="<ng:locale key="operation.button.save"/>" /></div>	 --%>
	<div class="tc"> <button type="button"  onclick="saveBank();" >&nbsp;<span><ng:locale key="operation.button.save"/></span>&nbsp;</button>	</div>	
</c:if>	

<div id="bankChangeZCid">
	<c:if test="${ (not empty jmiMember.bankProvince) &&  (not empty jmiMember.bankCity) &&   (not empty jmiMember.bank) && (not empty jmiMember.bankaddress) &&  ( not empty jmiMember.bankcard)}">
	<input type="hidden" name="strAction" value="${param.strAction }"/>
	<!-- 如果是中策用户,加一个判断条件 -->

	<%-- <h2 class="title mgb20"><ng:locale key="bankInformationChanges"/></h2> --%>
	<input type="hidden" id="oldBank" value="${oldBank }">
	<input type="hidden" id="oldBankaddress" value="${oldBankaddress }">
	<input type="hidden" id="oldBankbook" value="${oldBankbook }">
	<input type="hidden" id="oldBankcard" value="${oldBankcard }">
	<table width="90%" class="mt" style="margin:0 auto;" > 		
		<tr>
			<td><ng:locale key="miMember.bankProvince"/></td>
			<td>
				<form:select path="bankProvince" cssClass="mySelect" onchange="getBankCity()" disabled="${bankProvinceModify }">
					<form:option label="" value=""/>
					<form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
			</td>
			<td><ng:locale key="miMember.bankCity"/></td>
			<td>
				<select name="bankCity" id="bankCity" class="mySelect" <c:if test="${not empty bankCityModify }"> disabled="true"</c:if> >
					<option value=""><ng:locale key="list.please.select"/></option>
				</select>
			</td>
		</tr>

		<tr>
			<td><ng:locale key="miMember.openBank"/></td>
			<td>
				<c:if test="${empty bankModify }">
					<form:select path="bank" cssClass="mySelect">
						<form:option value=""></form:option>
						<form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
					</form:select>
				</c:if>
				<c:if test="${not empty bankModify }">
					<c:forEach items="${sysBankList }" var="curSysBankList">
						<c:if test="${curSysBankList.bankKey==jmiMember.bank }">
							${curSysBankList.bankValue }
						</c:if>
					</c:forEach>
				</c:if>
			</td>
			<td><ng:locale key="miMember.bcity"/></td>
			<td>
				<c:if test="${empty bankaddressModify }">
					<form:input path="bankaddress" id="bankaddress" />
				</c:if>
				<c:if test="${not empty bankaddressModify }">
					${ jmiMember.bankaddress}
				</c:if>
			</td>
		</tr>

		<tr>
			<td><ng:locale key="miMember.bname"/></td>
			<td>${jmiMember.bankbook }</td>
			<td><ng:locale key="miMember.bnum"/></td>
			<td>
				<c:if test="${empty bankcardModify }">
					<form:input path="bankcard" id="bankcard" />
				</c:if>
				<c:if test="${not empty bankcardModify }">
					${ jmiMember.bankcard}
				</c:if>
			</td>
		</tr>
		
	</table>

	</c:if>
</div>
</form:form>


</div>

 <div id="show" >
    <!-- <h2 class="title mgb20">添加银行信息 &nbsp;(银行卡与身份证持有人一致)</h2> -->
    <ng:locale key ='YourBankInformationIsComplete'/>
</div> 
</div>
<script> 

		function saveBank(){
			var fields = new Array(); 
			var i=0;
			var obj = document.getElementById("bankcard");
			var jmiMemberForm = document.getElementById("jmiMemberForm");
			var reg = new RegExp("^[0-9]*$");
			var userCode = document.getElementById("userCode").value;
			
			//20160706  特殊名单 不做银行修改信息限制
			var tshy = 'CN13234245,CN21305849,CN10111767,CN85606100,CN92504790,CN32448960,CN35436309,CN64084584,CN33177159,CN33964954,CN45149899,CN21736826,CN12898280,CN13731865,CN60337189,CN13767892,CN15127332,CN17969214,CN19506487,CN12420821,CN79744255,CN46053558,CN20474358,CN38323488,CN13717634,CN18310026,CN18243407,CN15090165,CN96233029,CN83900978,CN18660277,CN14608168,';
			if(tshy.indexOf(userCode+',') <= -1) {
				if(!reg.test(obj.value)){  
			        fields[i++] = '所填字符只能为数字';
			    }
				if(obj.value.length<12){
					fields[i++] = '填写的位数出错,必须大于等于12位';
				}
				
				if (fields.length > 0) {
			       alert(fields.join('\n'));
			       return false;
			    }
			}
			
			
			jmiMemberForm.submit();
			
		}


		  var qquery = jQuery.noConflict();
		   //选取省份时获取对应城市所用到的方法 
		  function getBankCity(){
		        //通过js控制，如果是中策用户，并且除银行户名之外的输入框都输入了，那么公示，否则隐藏
		        var zcbz = "<%= session.getAttribute("zchyBankInforChange")%>";
		           if("zchyBankInforChange"==zcbz){
		                document.getElementById("bankChangeZCid").style.display='';
		                <% session.setAttribute("zchyBankInforChange","");%>;
		                 document.getElementById("show").style.display='none';
		           }else{
		                document.getElementById("bankChangeZCid").style.display='none';
		                <% session.setAttribute("zchyBankInforChange","");%>;
		                document.getElementById("show").style.display='';
		           }
		        //form select 选取后获取它的值，就是获取其对应的path的值
		         var provinceId = qquery("#bankProvince").val();
		        //运用ＤＷＲ的开源框架，这里还使用了回调函数callIdCity
		        //当调用完jalCityManager.getAlCityByProvinceId(provinceId)方法后，然后在调用callIdCity，
		        jalCityManager.getAlCityByProvinceId(provinceId,callIdCity);
		   }  
		   //回调函数－－这里面的valid是jalCityManager.getAlCityByProvinceId(provinceId)的执行结果
		    function callIdCity(valid){
		 		  DWRUtil.removeAllOptions("bankCity");
		 		  DWRUtil.addOptions("bankCity",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("bankCity",valid,"cityId","cityName");
			   		if(''!='${jmiMember.bankCity}'){
			   			qquery("#bankCity").val('${jmiMember.bankCity}');
			   		}
			   		
			   	   showOrHide();
		   }
		    
		    //公示与隐藏
		    function showOrHide(){
		           var bankProvince = qquery("#bankProvince").val();
		           var bankCity = qquery("#bankCity").val();
		           var bank = qquery("#bank").val();
		           var bankaddress = qquery("#bankaddress").val();
		           var bankcard = qquery("#bankcard").val();
		           if(bankProvince==""||bankCity==""||bank==""||bankaddress==""||bankcard==""){
		               document.getElementById("show").style.display='none';
		           }
		    }
		    
		    getBankCity();
<c:if test="${ empty jmiMember.bank ||  empty jmiMember.bankbook ||   empty jmiMember.bankcard }">
		   getBankCity();
</c:if>	   
</script>
