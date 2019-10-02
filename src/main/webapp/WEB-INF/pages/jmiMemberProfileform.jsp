<%@ include file="/common/taglibs.jsp"%>

<head>
	<meta name="heading"
		content="<fmt:message key='jmiMemberDetail.heading'/>" />
</head>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalCityManager.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalDistrictManager.js'/>"></script>



<form:form commandName="jmiMember">  
    <form:errors path="*"></form:errors>  
</form:form>


<form:form commandName="jmiMember"  method="post" action="jmiMemberProfileform" id="jmiMemberForm" >
${jmiMember.userCode }
		 <form:input path="firstName" id="firstName" cssClass="text medium"/>
	<form:select path="province" id="province" cssClass="text medium" onchange="getIdCity();">
		<form:option label="" value="" />
		<form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
	</form:select>

	<select name="city" id="city" onchange="getIdDistrict();">
		<option value="">
			select...
		</option>
	</select>

	<select name="district" id="district">
		<option value="">
			select...
		</option>
	</select>
 <form:password path="sysUser.password" cssClass="text medium" id="password1"/>
	<button type="submit" class="btn btn-primary" name="save"
		onclick="bCancel=false">
		<i class="icon-ok icon-white"></i>
		<fmt:message key="button.save" />
	</button>

</form:form>

<script type="text/javascript">

var jq = jQuery.noConflict();

			function getIdCity(){
			   var province=jq("#province").val();
			   jalCityManager.getAlCityByProvinceId(province,callIdCity);
		   }
		   function callIdCity(valid){
		   		
		 		  DWRUtil.removeAllOptions("city");
		 		  DWRUtil.addOptions("city",{'':'select..'});  
                  DWRUtil.addOptions("city",valid,"cityId","cityName");
			   		if(''!='${jmiMember.city}'){
			   			jq("#city").val('${jmiMember.city}');
			   			getIdDistrict();
			   		}
			   
		   }
		    function getIdDistrict(){
		   		var city=jq("#city").val();
		   		jalDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
		   }
		   function callBackIdDistrict(valid){
	    		DWRUtil.removeAllOptions("district");
	 			DWRUtil.addOptions("district",{'':'select..'});  
                DWRUtil.addOptions("district",valid,"districtId","districtName");
		   		if(''!='${jmiMember.district}'){
		   			jq("#district").val('${jmiMember.district}');
		   		}

		   }
		   getIdCity();
</script>
