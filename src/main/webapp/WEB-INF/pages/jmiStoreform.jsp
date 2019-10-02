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
		<h3 class="color2 ml">一级店铺修改</h3>
	</div>
<form:form commandName="jmiStore" method="post" action="jmiStoreform" name="jmiStoreform" onsubmit="return validateOthers();" id="jmiStoreform">


<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>
<form:hidden path="id"/>

 <div class="mt">
 	<table class="form_details_table">
                    <colgroup style="width:205px;"></colgroup>
                    <colgroup style="width:215px;"></colgroup>
                    <colgroup style="width:190px;"></colgroup>
                    <tbody>
                        <tr>
                            <td class="tr"><ng:locale  key="miMember.memberNo"/>:</td>
                            <td>
							 	 	${jmiStore.jmiMember.userCode }
					    	</td>
                            <td class="tr"> <ng:locale key="bdCalculatingSubDetail.name" />:</td>
                            <td>${jmiStore.jmiMember.miUserName }</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="miMember.sex" />:</td>
                            <td><ng:code listCode="sex" value="${jmiStore.jmiMember.sex}"/></td>
                            <td class="tr">奖衔:</td>
                            <td><ng:code listCode="honor.star.zero" value="${jmiStore.honorStar}"/></td>
                        </tr>
                        <tr>
                            <td class="tr"> <ng:locale  key="miMember.papernumber"/>:</td>
                            <td>${jmiStore.jmiMember.papernumber }</td>
                            <td class="tr"> <ng:locale  key="miMember.postalcode"/>:</td>
                            <td>
	                            <c:if test="${modifyStatus=='yes' }">
						    	<form:input path="postalcode" id="postalcode" cssClass="text medium"/>
						    	 </c:if>
						    	<c:if test="${modifyStatus=='no' }">
						    		${jmiStore.postalcode }
						    	</c:if>
					    	</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale  key="store.mailAddr"/>:</td>
                            <td colspan="3">
                            <c:if test="${modifyStatus=='yes' }">
					       <form:input path="mailAddr" id="mailAddr" />
					    	 </c:if>
					    	<c:if test="${modifyStatus=='no' }">
					    		${jmiStore.mailAddr }
					    	</c:if>	
					    	</td>
                        </tr>
                        <tr>
                            <td class="tr"><span style="color: red;"> * </span><ng:locale  key="miMember.mobiletele"/>:</td>
                            <td colspan="3">
                            <c:if test="${modifyStatus=='yes' }">
					       <form:input path="mobiletele" id="mobiletele" cssClass="text medium"/>
					    	 </c:if>
					    	<c:if test="${modifyStatus=='no' }">
					    		${jmiStore.mobiletele }
					    	</c:if>	
					    	</td>
                        </tr>
                        <tr>
                            <td class="tr"> <ng:locale  key="miMember.email"/>:</td>
                            <td><c:if test="${modifyStatus=='yes' }">
					       <form:input path="email" id="email" cssClass="text medium" />
					    	 </c:if>
					    	<c:if test="${modifyStatus=='no' }">
					    		${jmiStore.email }
					    	</c:if>	</td>
                            <td class="tr"><ng:locale  key="miMember.faxtele"/>:</td>
                            <td>
                            	<c:if test="${modifyStatus=='yes' }">
						    	 <form:input path="faxtele" id="faxtele" cssClass="text medium"/>
						    	 </c:if>
						    	<c:if test="${modifyStatus=='no' }">
						    		${jmiStore.faxtele }
						    	</c:if>	
                            </td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale  key="store.subStoreAddr"/>:</td>
                            <td colspan="3"><c:if test="${modifyStatus=='yes' }">
					       <form:input path="subStoreAddr" id="subStoreAddr" cssClass="text medium" />
					    	 </c:if>
					    	<c:if test="${modifyStatus=='no' }">
					    		${jmiStore.subStoreAddr }
					    	</c:if>	</td>
                        </tr>
                        <tr>
                            <td class="tr"><span style="color: red;"> * </span><ng:locale  key="store.storeAddr"/>:</td>
                            <td>
                               <c:if test="${modifyStatus=='yes' }">
						       <%--  <form:select path="province"  cssClass="text medium" onchange="getIdCity()" >
											<form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
								            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
								</form:select> --%>
						        	<ng:region regionType="p" regionId="${jmiStore.province }"></ng:region>
										<input type="hidden" name="province" value="${jmiStore.province }" id="province"/>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
						        	<ng:region regionType="p" regionId="${jmiStore.province }"></ng:region>
						    	</c:if>
                            </td>
                            <td>
                                
								    	<c:if test="${modifyStatus=='yes' }">
									        <%-- <select name="city" id="city" onchange="getIdDistrict()"  >
												<option value=""><ng:locale key="list.please.select"/></option>
											</select> --%>
											<ng:region regionType="c" regionId="${jmiStore.city }"></ng:region>
											
										<input type="hidden" name="city" value="${jmiStore.city }" id="city"/>
								    	</c:if>
								    	<c:if test="${modifyStatus=='no' }">
									       <ng:region regionType="c" regionId="${jmiStore.city }"></ng:region>
								    	</c:if>
							</td>
                            <td>
                                <c:if test="${modifyStatus=='yes' }">
							        <select name="district" id="district" >
										<option value=""><ng:locale key="list.please.select"/></option>
									</select>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							       <ng:region regionType="d" regionId="${jmiStore.district }"></ng:region>
						    	</c:if>	
                            </td>
                        </tr>
                        <tr>
                            <td class="tr"><span style="color: red;"> * </span><ng:locale  key="busi.address"/>:</td>
                            <td colspan="3"><c:if test="${modifyStatus=='yes' }">
									       <form:input path="address" id="address" cssClass="text medium" />
									    	 </c:if>
									    	<c:if test="${modifyStatus=='no' }">
									    		${jmiStore.address }
									    	</c:if>	
									    	</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale  key="subStore.businessArea"/>:</td>
                            <td><c:if test="${modifyStatus=='yes' }">
						       <form:input path="businessArea" id="businessArea" cssClass="text medium" />
						    	 </c:if>
						    	<c:if test="${modifyStatus=='no' }">
						    		${jmiStore.businessArea }
						    	</c:if>	
						    	 <ng:locale key="busi.unit.m"/>
    	 				</td>
                            <td class="tr"><ng:locale  key="subStore.personQty"/>:</td>
                            <td><c:if test="${modifyStatus=='yes' }">
					       <form:input path="personTotal" id="personTotal" cssClass="text medium" />
					    	 </c:if>
					    	<c:if test="${modifyStatus=='no' }">
					    		${jmiStore.personTotal }
					    	</c:if>	
					    	 <ng:locale key="busi.unit.wan"/></td>
                        </tr>
                        <tr>
                            <td class="tr"> <ng:locale  key="store.cityType"/>:</td>
                            <td>
                               <c:if test="${modifyStatus=='yes' }">
						         <ng:list name="cityType" listCode="store.citytype" value="${jmiStore.cityType}" defaultValue="" showBlankLine="true"/>	
						    	 </c:if>
						    	<c:if test="${modifyStatus=='no' }">
						       <ng:code listCode="store.citytype" value="${jmiStore.cityType}"/>
						    	</c:if>	
                            </td>
                            <td class="tr"> <ng:locale  key="subStore.averageIncome"/>:</td>
                            <td><c:if test="${modifyStatus=='yes' }">
					       <form:input path="averageIncome" id="averageIncome" cssClass="text medium" />
					    	 </c:if>
					    	<c:if test="${modifyStatus=='no' }">
					    		${jmiStore.averageIncome }
					    	</c:if>	
					    	 <ng:locale key="busi.unit.yuan"/></td>
                        </tr>
                        <tr>
                            <td class="tr"> <ng:locale  key="subStore.isdeal"/>:</td>
                            <td>
                               <c:if test="${modifyStatus=='yes' }">
						         <ng:list name="isdeal" listCode="yesno" value="${jmiStore.isdeal}" defaultValue="" showBlankLine="true"/>	
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							 	 	 <ng:code listCode="yesno" value="${jmiStore.isdeal}"/>
						    	</c:if>
                            </td>
                            <td class="tr"> <ng:locale  key="subStore.specificBusiness"/>:</td>
                            <td>
					         <c:if test="${modifyStatus=='yes' }">
					       		 <form:input path="business" id="business" cssClass="text medium"/>
					    	</c:if>
					    	<c:if test="${modifyStatus=='no' }">
					    		${jmiStore.business }
					    	</c:if>	
					    	</td>
                        </tr>
                    </tbody>
                </table>
                
                
               <c:if test="${param.strAction!='viewJmiStore' && empty jmiStoreExist }">
                
               <%--  <h3 class="pdTitle"><ng:locale key="register.memberInfoIden.agree"/></h3>
                <ul class="provision">
                    <li>
                
<jsp:include flush="true" page="store_attachment0.jsp"></jsp:include>    
                    </li> 
                  
                </ul>
                <div class="mgtb10"><input type="checkbox" value="agree" name="agree" id="agree" /><label for="agree"><span class="ft12 pdl10"><ng:locale key="register.agree"/></span></label></div>
                --%>
                <div class="tc" style="margin-top: 10px;">
                	<button type="submit" class="btn btn-info"><ng:locale key="button.save"/></button>
                    <button type="button" class="btn btn-success" name="back" onclick="returnOld()"><ng:locale key="button.back"/></button>
                </div>
                
				</c:if>
                
                <c:if test="${param.strAction=='viewJmiStore' }">
                	<div class="tc" style="margin-top: 10px;">
						<button type="button" class="btn btn-success" name="back" onclick="returnOld()"><ng:locale key="button.back"/></button>
					</div>
                </c:if>
                
		</div>

</form:form>



</div>


<script type="text/javascript">

function returnOld(){
   window.location.href = "jmiStores";
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