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
    <!-- 下面是校验时的提示标语 -->
<spring:bind path="jmiPrizeTourism.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>    
<body>
	<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">奖游信息完善</h3>
			</div>	
		 <div class="mt">
		<form:form commandName="jmiPrizeTourism" method="post" action="jmiPrizeTourismEdit" id="jmiPrizeTourismForm" name="jmiPrizeTourismForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
		<form:hidden path="prizeTouismId" />
		<form:hidden path="passStar" value="${jmiPrizeTourism.passStar }" />
		<form:hidden path="userCode" value="${jmiPrizeTourism.userCode}"/>
			<table width="100%" class="form_edit_table">
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:110px;"></colgroup>
				<colgroup></colgroup>
				<tbody>
					<tr>
						<td><label class="pdl10"><ng:locale key="miMember.memberNo"/>：</label></td>
						<td colspan="5">${ jmiPrizeTourism.userCode} </td>
					</tr>
					<tr>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.cardname"/>：</label></td>
					<td><form:input path="cardname" id="cardname" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.cardid"/>：</label></td>
					<td><form:input path="cardid" id="cardid" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.phone" />：</label></td>
					<td><form:input path="phone" id="phone" cssClass="text medium"/></td>
				</tr>
				<tr>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.province"/>：</label></td>
					<td>
						<form:select path="province" id="province"  cssClass="text medium mySelect" onchange="getCity('province');">
						   <form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
						   <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
						</form:select>
					</td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.city"/>：</label></td>
					<td>
						<select name="city" id="city" class="mySelect" onchange="getAreaByCity('city');">
						   <option value=""><ng:locale key="list.please.select"/></option>
						</select>
					</td>
					<td><label class="star"> <ng:locale key="jmiPrizeTourism.area" />：</label></td>
					<td>
						<select name="area" id="area" class="mySelect" >
							<option value=""><ng:locale key="list.please.select"/></option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.address" />：</label></td>
					<td colspan="5"><form:input path="address" id="address" cssClass="text medium"  size ="80"/></td>
				</tr>
				<tr>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.height" />：</label></td>
					<td><form:input path="height" id="height" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.weight" />：</label></td>
					<td><form:input path="weight" id="weight" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.clothessize" />：</label></td>
					<td>
					<ng:list name="clothessize" listCode="jmiprizetourism.clothessize" value="${jmiPrizeTourism.clothessize}" defaultValue="" styleClass="mySelect"/>
					</td>
				</tr>
				<tr>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.avoidcertainfood" />：</label></td>
					<td>
						<form:hidden path="avoidcertainfood" />
						<c:forEach items="${avoidcertainfoodList}" var="cb" >
				        <input type="checkbox" name="avoidcertainfoods" onchange="chk('avoidcertainfoods','avoidcertainfood')" onclick="chk('avoidcertainfoods','avoidcertainfood')"  <c:if test="${cb.checked=='1'}">checked</c:if> value="${cb.cId}"/>${cb.cValue}&nbsp;&nbsp;
				        </c:forEach>
			        </td>
					<td><label class="pdl10"><ng:locale key="jmiPrizeTourism.passStar" />：</label></td>
					<td colspan="3"><ng:code listCode="pass.star" value="${jmiPrizeTourism.passStar }"/></td>
				</tr>
				<tr>
					<td colspan="1"><label class="star">证件收寄地址：</label></td>
					<td colspan="5"><form:input path="acceptanceaddress" id="acceptanceaddress" cssClass="text medium"  cssStyle="width:420px;"/></td>
				</tr>
				<tr>
					<td><label class="pdl10"><ng:locale key="jmiPrizeTourism.isPeer" />：</label></td>
					<td colspan="5">
						<ng:list name="ispeer" id="ispeer" listCode="jmiprizetourism.ispeer" onchange="isShowPeer('1');" value="${jmiPrizeTourism.ispeer}" defaultValue="" styleClass="mySelect"/>
					</td>
				</tr>
				<tr class="peer"><th colspan="6">
					<h2 class="title mgb20"><ng:locale key="jmiPrizeTourism.peer.info"/></h2>
			    </th></tr>
			    <tr class="peer">
					<td><label class="star"><ng:locale key="jmiPrizeTourism.peertype" />：</label></td>
					<td>
						<ng:list name="peertype" listCode="jmiprizetourism.peertype" value="${jmiPrizeTourism.peertype}" defaultValue="" styleClass="mySelect"/>
					</td>
					<td><label class="star"><ng:locale key="list.sex" />：</label></td>
					<td colspan="3"><ng:code listCode="testuser.sex" value="${jmiPrizeTourism.peersex }"/>
						<ng:list name="peersex" listCode="testuser.sex" value="${jmiPrizeTourism.peersex}" defaultValue="" styleClass="mySelect"/>
					</td>
				</tr>
				<tr class="peer">
					<td><label class="star"><ng:locale key="jmiPrizeTourism.cardname" />：</label></td>
					<td><form:input path="peercardname" id="peercardname" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.cardid" />：</label></td>
					<td><form:input path="peercardid" id="peercardid" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.phone" />：</label></td>
					<td><form:input path="peerphone" id="peerphone" cssClass="text medium"/></td>
				</tr>
				<tr class="peer">
					<td><label class="star"><ng:locale key="jmiPrizeTourism.province"/>：</label></td>
					<td> 
						<form:select path="peerprovince" id="peerprovince"  cssClass="text medium mySelect" onchange="getCity('peerprovince');">
						   <form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
						   <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
						</form:select>
					</td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.city"/>：</label></td>
					<td>
						<select name="peercity" id="peercity" class="mySelect" onchange="getAreaByCity('peercity');">
						   <option value=""><ng:locale key="list.please.select"/></option>
						</select>
					</td>
					<td><label class="star"> <ng:locale key="jmiPrizeTourism.area" />：</label></td>
					<td>
						<select name="peerarea" id="peerarea" class="mySelect" >
							<option value=""><ng:locale key="list.please.select"/></option>
						</select>
					</td>
				</tr>
				
				<tr class="peer">
					<td><label class="star"><ng:locale key="jmiPrizeTourism.address" />：</label></td>
					<td colspan="5"><form:input path="peeraddress" id="peeraddress" cssClass="text medium"  size ="80"/></td>
				</tr>
				<tr class="peer">
					<td><label class="star"><ng:locale key="jmiPrizeTourism.height" />：</label></td>
					<td><form:input path="peerheight" id="peerheight" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.weight" />：</label></td>
					<td><form:input path="peerweight" id="peerweight" cssClass="text medium"/></td>
					<td><label class="star"><ng:locale key="jmiPrizeTourism.clothessize" />：</label></td>
					<td>
						<ng:list name="peerclothessize" listCode="jmiprizetourism.clothessize" value="${jmiPrizeTourism.peerclothessize}" defaultValue="" styleClass="mySelect"/>
					</td>
				</tr>
				<tr class="peer">
					<td><label class="star"><ng:locale key="jmiPrizeTourism.avoidcertainfood" />：</label></td>
					<td colspan="5">
					<form:hidden path="peeravoidcertainfood" />
					<c:forEach items="${peerAvoidcertainfoodList}" var="cb" >
			        <input type="checkbox" name="peeravoidcertainfoods" onchange="chk('peeravoidcertainfoods','peeravoidcertainfood')" onclick="chk('peeravoidcertainfoods','peeravoidcertainfood')" <c:if test="${cb.checked=='1'}">checked</c:if> value="${cb.cId}"/>${cb.cValue}&nbsp;&nbsp;
			        </c:forEach>
					</td>
				</tr>
				</tbody>
			</table>
			<div class="tc" style="margin-top: 10px;"> <button name="save" type="button"  onclick="addOrUpdate(document.jmiPrizeTourismForm)" >&nbsp;<span>保存</span>&nbsp;</button>
			<button type="button" class="btn btn-success ml" onclick="history.go(-1)" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>	
			</div>
	   </form:form>
	   </div>
	   </div>
    <script src="<c:url value='/dwr/util.js'/>" ></script>
    <script src="<c:url value='/dwr/engine.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>
	<script>
	var qquery = jQuery.noConflict();
   //在初始化或者选取省份的时候执行该方法
	function getCity(p){
		var provinceId = document.getElementById(p).value;
		
		if ('peerprovince' == p) {
		 	//通过ＤＷＲ开源框架，这里调用java后台的项目，并且使用了回调函数
			jalCityManager.getAlCityByProvinceId(provinceId,callIdPeerCity);
		 } else if ('province' == p) {
		 	//通过ＤＷＲ开源框架，这里调用java后台的项目，并且使用了回调函数
			jalCityManager.getAlCityByProvinceId(provinceId,callIdCity);
		 }
	}
	
	//回调函数
	function callIdCity(valid){
		//去掉下拉框中原有的数值
		DWRUtil.removeAllOptions("city");
		//给下拉框加上受选项－请选择(select)
		DWRUtil.addOptions("city",{'':'<ng:locale key='list.please.select'/>'});
		//将集合valid中的数据放入下拉框中
		DWRUtil.addOptions("city",valid, "cityId","cityName");
		if(''!='${jmiPrizeTourism.city}'){
		　　　　　//使用别名的jQuery代码
		　　　　    //将数据库中查询到的城市的代码放入下拉框，就是使下拉框选中对应的城市
		　　　　　qquery("#city").val('${jmiPrizeTourism.city}');　
		}
		//通过城市获取地区
			getAreaByCity('city');
	} 
	//回调函数
	function callIdPeerCity(valid){
		//去掉下拉框中原有的数值
		DWRUtil.removeAllOptions("peercity");
		//给下拉框加上受选项－请选择(select)
		DWRUtil.addOptions("peercity",{'':'<ng:locale key='list.please.select'/>'});
		//将集合valid中的数据放入下拉框中
		DWRUtil.addOptions("peercity",valid, "cityId","cityName");
		if(''!='${jmiPrizeTourism.peercity}'){
		　　　　　//使用别名的jQuery代码
		　　　　    //将数据库中查询到的城市的代码放入下拉框，就是使下拉框选中对应的城市
		　　　　　qquery("#peercity").val('${jmiPrizeTourism.peercity}');　
		} 
		//通过城市获取地区
			getAreaByCity('peercity');
	}  

	//通过城市获取地区的方法
	function getAreaByCity(cc){
		 var cityId = document.getElementById(cc).value;
		 if ('peercity' == cc) {
		 	jalDistrictManager.getAlDistrictByCityId(cityId, callIdPeerDistrict);
		 } else if ('city' == cc) {
		 	//使用ＤＷＲ开源框架，调用java后台的代码
	　　　　	jalDistrictManager.getAlDistrictByCityId(cityId,callIdDistrict);
		 }
		 
	}

	//回调函数－获取所在地区
	function callIdDistrict(districtList){
	   DWRUtil.removeAllOptions("area");
	   DWRUtil.addOptions("area",{'':'<ng:locale key='list.please.select'/>'});
	   DWRUtil.addOptions("area",districtList,"districtId","districtName");
	   if(''!='${jmiPrizeTourism.area}'){
	   
		　　　　　//使用别名的jQuery代码
		　　　　    //将数据库中查询到的地区的代码放入下拉框，就是使下拉框选中对应的地区
		　　　　　qquery("#area").val('${jmiPrizeTourism.area}');　
	   }
	}
	//回调函数－获取所在地区
	function callIdPeerDistrict(districtList){
	   DWRUtil.removeAllOptions("peerarea");
	   DWRUtil.addOptions("peerarea",{'':'<ng:locale key='list.please.select'/>'});
	   DWRUtil.addOptions("peerarea",districtList,"districtId","districtName");
	   if(''!='${jmiPrizeTourism.peerarea}'){
	   
		　　　　　//使用别名的jQuery代码
		　　　　    //将数据库中查询到的地区的代码放入下拉框，就是使下拉框选中对应的地区
		　　　　　qquery("#peerarea").val('${jmiPrizeTourism.peerarea}');　
	   }
	}
	
	getCity('province');

	function addOrUpdate(theFrom){
	   theFrom.submit();
	}
	
	function chk(o1,o2) {
		var obj = document.getElementsByName(o1); 
		
		var s = '';
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].checked)
				s += obj[i].value + ','; 
		}
		$('#'+o2).val(s);
	}
	
	
	function isShowPeer(f1){
		var isPeer = document.getElementById('ispeer').value;
		if('1'==isPeer){
			qquery(".peer").css('display','');
		}else{
			qquery(".peer").css('display','none');
		}
		if('0'==f1){
			qquery("#ispeer").attr("disabled",true); 
		}
	}
	isShowPeer('${jmiPrizeTourism.ispeer}');
	
	function addOrUpdate(theFrom){
	   if(!validateForm()) return false;
	   theFrom.submit();
	}
	//非空验证
	function validateEmpty(inputArr,messageArr){
		for(var i=0;i<inputArr.length;i++){
			if(''==$('#'+inputArr[i]).val()){
				alert(messageArr[i]+' 必填项！');
				$('#'+inputArr[i]).focus();
				return false;
			}	
		}
		return true;
	}
	//长度验证
	function validateLen(inputArr,messageArr,lenArr){
		for(var i=0;i<inputArr.length;i++){
			if(getBytesLength($('#'+inputArr[i]).val())>lenArr[i]){
				alert(messageArr[i]+' 超长！');
				$('#'+inputArr[i]).focus();
				return false;
			}	
		}
		return true;
	}
	function getBytesLength(str) {
	// 在GBK编码里，除了ASCII字符，其它都占两个字符宽
		return str.replace(/[^\x00-\xff]/g, 'xx').length;
	}
	//正则表达式验证数据合法性
	function validateExec(patrn,input,mes){
		if(!patrn.exec($('#'+input).val())){
			alert(mes);
			$('#'+input).focus();
			return false;
		}
		return true;
	}
	function validateForm(){
		var inputArr=['cardname','cardid','phone','province','city','area','address','height','weight','avoidcertainfood','acceptanceaddress'];//需验证的元素控件
		var messageArr=['证件姓名','身份证号码','联系电话','省','市','区','详细地址','身高','体重','餐饮忌口','证件材料收寄地址'];//提示语
		var lenArr=[50,18,20,10,10,10,200,20,20,20,200];//长度
		//为空
		if(!validateEmpty(inputArr,messageArr)) return false;
		//长度，字节数
		if(!validateLen(inputArr,messageArr,lenArr)) return false;
		
		var isPeer = $('#ispeer').val();
		if('1'==isPeer){
			inputArr=['peercardname','peercardid','peerphone','peerprovince','peercity','peerarea','peeraddress','peerheight','peerweight','peeravoidcertainfood'];
			messageArr=['同行人证件姓名','同行人身份证号码','同行人联系电话','同行人省','同行人市','同行人区','同行人详细地址','同行人身高','同行人体重','同行人餐饮忌口'];
			lenArr=[50,18,20,10,10,10,200,20,20,20];//长度
			//为空
			if(!validateEmpty(inputArr,messageArr)) return false;
			//长度，字节数
			if(!validateLen(inputArr,messageArr,lenArr)) return false;
		}
		
		var patrn=/^\d{15}|\d{18}$/;//身份证
		if(!validateExec(patrn,'cardid','身份证格式不正确！')) return false;
		if('1'==isPeer){
			if(!validateExec(patrn,'peercardid','同行人身份证格式不正确！')) return false;
		}
		patrn=/^([0-9]{0,20})$/;//手机
		if(!validateExec(patrn,'phone','联系电话不正确！')) return false;
		if('1'==isPeer){
			if(!validateExec(patrn,'peerphone','同行人联系电话为不正确手机号码！')) return false;
		}
		patrn=/^[0-9]+(.[0-9]{0,2})?$/;//手机
		if(!validateExec(patrn,'height','身高为数字且最多两位小数！')) return false;
		if(!validateExec(patrn,'weight','体重为数字且最多两位小数！')) return false;
		if('1'==isPeer){
			if(!validateExec(patrn,'peerheight','同行人身高为数字且最多两位小数！')) return false;
			if(!validateExec(patrn,'peerweight','同行人体重为数字且最多两位小数！')) return false;
		}
		
		return true;
	}
	
</script>
</body>
