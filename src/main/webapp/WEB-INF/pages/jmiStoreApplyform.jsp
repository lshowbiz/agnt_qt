<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>











<spring:bind path="jmiStore.*">
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
		<h3 class="color2 ml">一级店铺管理</h3>
	</div>

<form:form commandName="jmiStore" method="post" action="jmiStoreApplyform" name="jmiStoreform" onsubmit="return validateOthers();" id="jmiStoreform">


<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>
<form:hidden path="id"/>
<div class="mt">	
	<table class="form_details_table">
                    <colgroup style="width:190px;"></colgroup>
                    <colgroup style="width:190px;"></colgroup>
                    <colgroup style="width:190px;"></colgroup>
                    <tbody>
                        <tr>
                            <td><ng:locale  key="miMember.memberNo"/>：${jmiStore.jmiMember.userCode }</td>
                            <td><ng:locale key="bdCalculatingSubDetail.name" />：${jmiStore.jmiMember.miUserName }</td>
                            <td><ng:locale key="miMember.sex" />：<ng:code listCode="sex" value="${jmiStore.jmiMember.sex}"/></td>
                        </tr>
                        <tr>
                        	<td>奖衔：<ng:code listCode="honor.star.zero" value="${jmiStore.honorStar}"/></td>
                            <td><ng:locale  key="miMember.papernumber"/>：${jmiStore.jmiMember.papernumber }</td>
                            <td><ng:locale  key="miMember.mobiletele"/>：${jmiStore.jmiMember.mobiletele }</td>
                        </tr>
                    </tbody>
                </table>
                <div class="bt mt">
                 	<h3 class="color2 ml">一级生活馆地址信息</h3>
                </div>
                <table  class="form_details_table mt">
                    <colgroup style="width:160px;"></colgroup>
                    <colgroup style="width:160px;"></colgroup>
                    <colgroup style="width:160px;"></colgroup>
                    <tbody>
                        <tr>
                            <td colspan="3">
                               <c:if test="${modifyStatus=='yes' }">
						        <form:select path="province"  cssClass="text medium mySelect" onchange="getIdCity()">
											<form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
								            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
								</form:select>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							        <ng:region regionType="p" regionId="${jmiStore.province}"></ng:region>
						    	</c:if>
						    	&nbsp;
						    	<c:if test="${modifyStatus=='yes' }">
							        <select name="city" id="city" class="mySelect">
										<option value=""><ng:locale key="list.please.select"/></option>
									</select>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							        <ng:region regionType="c" regionId="${jmiStore.city}"></ng:region>
						    	</c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
                
                <c:if test="${param.strAction!='viewJmiStoreApply' && empty jmiStoreExist }">
                
                <h3 class="pdTitle"><ng:locale key="register.memberInfoIden.agree"/></h3>
                <ul class="provision">
                    <li>
                
						<jsp:include flush="true" page="store_attachment0.jsp"></jsp:include>    
                    </li>
                  
                </ul>
                <div class="mgtb10"><input type="checkbox" value="agree" name="agree" id="agree" /><label for="agree"><span class="ft12 pdl10"><ng:locale key="register.agree"/></span></label></div>
                
                
                <ul class="provision">
                    <li>
						<jsp:include flush="true" page="store_attachment1.jsp"></jsp:include>
                    </li>
                    
                </ul>
                <div class="mgtb10"><input type="checkbox" name="agree2" id="agree2" value="agree2"/><label for="agree_2"><span class="ft12 pdl10">同意</span></label></div>
                
                
				  
				</c:if>
				<div class="tc" style="margin-top: 10px;">
				<c:if test="${param.strAction!='viewJmiStoreApply' && empty jmiStoreExist }">
				<button type="submit" class="btn btn-info">保&nbsp;存</button>
                </c:if>
                            <button type="button" class="btn btn-success" name="back" onclick="returnOld()"><ng:locale key="button.back"/></button>
                </div>
              
		</div>

</form:form>


</div>



<script type="text/javascript">

function returnOld(){
   window.location.href = "jmiStoreApplys";
}

var jq = jQuery.noConflict();

			function getIdCity(){
			   var province=jq("#province").val();
			   jalCityManager.getAlCityByProvinceId(province,callIdCity);
		   }
		   function callIdCity(valid){
		   		
		 		  DWRUtil.removeAllOptions("city");
		 		  DWRUtil.addOptions("city",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("city",valid,"cityId","cityName");
			   		if(''!='${jmiStore.city}'){
			   			jq("#city").val('${jmiStore.city}');
			   			
			   		}
			   getIdDistrict();
		   }
		    function getIdDistrict(){
		   		var city=jq("#city").val();
		   		jalDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
		   }
		   function callBackIdDistrict(valid){
	    		DWRUtil.removeAllOptions("district");
	 			DWRUtil.addOptions("district",{'':'<ng:locale key='list.please.select'/>'});  
                DWRUtil.addOptions("district",valid,"districtId","districtName");
		   		if(''!='${jmiStore.district}'){
		   			jq("#district").val('${jmiStore.district}');
		   		}

		   }
		   
		
		   getIdCity();
		

 
		  
</script>	