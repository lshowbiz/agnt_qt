<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<!-- <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> -->
<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>


<spring:bind path="jmiSubStore.*">
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
		<h3 class="color2 ml">二级店铺管理</h3>
	</div>
<form:form  commandName="jmiSubStore" method="post" action="jmiSubStoreAddrform"   id="jmiSubStoreForm" name="jmiSubStoreForm"  >

<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>

<form:hidden path="id"/>
<div class="mt">	
           <!--  <div class="bt mt"><h3 class="color2 ml"><ng:locale key="member.base.info"/></h3></div> -->
                <table class="form_details_table">
                        <tr>
                            <td class="tr" style="width: 205px;"><ng:locale key="miMember.memberNo" />:</td>
                            <td style="width: 30%;">${jmiSubStore.jmiMember.userCode }</td>
                            <td class="tr" style="width: 195px;"><ng:locale key="bdCalculatingSubDetail.name" />:</td>
                            <td>${jmiSubStore.jmiMember.miUserName }</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="miMember.sex" />:</td>
                            <td><ng:code listCode="sex" value="${jmiSubStore.jmiMember.sex}"/></td>
                            <td class="tr"><ng:locale key="miMember.mobiletele" />:</td>
                            <td>${jmiSubStore.jmiMember.mobiletele }</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="miMember.papertype" />:</td>
                            <td> <ng:code listCode="papertype" value="${jmiSubStore.jmiMember.papertype}"/></td>
                            <td class="tr"><ng:locale key="miMember.papernumber" />:</td>
                            <td>${jmiSubStore.jmiMember.papernumber }</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale  key="miMember.subRecommendStore"/>:</td>
                            <td>${jmiSubStore.jmiMember.subRecommendStore }
		    		<input type="hidden" id="jmiMember.subRecommendStore" name="jmiMember.subRecommendStore" value="${jmiSubStore.jmiMember.subRecommendStore }" ></td>
                            <td class="tr"><ng:locale  key="miMember.subRecommendStore.name"/>:</td>
                            <td>
						    	<c:if test="${not empty recommendStoreName }">
						    		${recommendStoreName }
						    	</c:if>
	    					</td>
                        </tr>

                </table>

                
            <div class="bt mt"><h3 class="color2 ml"><ng:locale key="subStore.address.info"/></h3></div>
                <table class="form_details_table mt">
                        <tr>
                            <td class="tr" style="width: 205px;"><ng:locale key="subStore.province" />:</td>
                            <td style="width: 30%;">
						         <ng:region regionType="p" regionId="${jmiSubStore.province }"></ng:region>
						         <input id="provinceHidden" name="provinceHidden" value="${jmiSubStore.province }"  type="hidden">
    					</td>
                            <td class="tr" style="width: 195px;"><ng:locale key="subStore.city" />:</td>
                            <td>
	                             <ng:region regionType="c" regionId="${jmiSubStore.city }"></ng:region>
						         <input id="cityHidden" name="cityHidden" value="${jmiSubStore.city }"  type="hidden">
					    	</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="subStore.district" />:</td>
                            <td>
					         <c:if test="${modifyStatus=='yes' }">
						        <select name="district" id="district" >
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
					    	</c:if>
					    	<c:if test="${modifyStatus=='no' }">
						       <ng:region regionType="d" regionId="${jmiSubStore.district }"></ng:region>
					    	</c:if>	
					    	</td>
                            <td class="tr"><label class="star"><ng:locale key="subStore.address" />:</label></td>
                            <td>
					         <c:if test="${modifyStatus=='yes' }">
						  	<form:input path="address" id="address" />
						  	
					    	</c:if>
					    	<c:if test="${modifyStatus=='no' }">
						 	 	${jmiSubStore.address }
					    	</c:if>
					    	</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="subStore.postalcode" />:</td>
                            <td> 
						            <c:if test="${modifyStatus=='yes' }">
							  		<form:input path="postalcode" id="postalcode" />
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							 	 	${jmiSubStore.postalcode }
						    	</c:if></td>
                            <td class="tr"><ng:locale key="subStore.email" />:</td>
                            <td>
                            	<c:if test="${modifyStatus=='yes' }">
							  		<form:input path="email" id="email"  />
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							 	 	${jmiSubStore.email }
						    	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr"><label class="star"><ng:locale  key="subStore.mobiletele"/>:</label></td>
                            <td>
                            	<c:if test="${modifyStatus=='yes' }">
							  		<form:input path="mobiletele" id="mobiletele" />
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							 	 	${jmiSubStore.mobiletele }
						    	</c:if>
                            
                            </td>
                            <td class="tr"><ng:locale  key="subStore.phone"/>:</td>
                            <td>
						    		<c:if test="${modifyStatus=='yes' }">
								  		<form:input path="phone" id="phone" />
							    	</c:if>
							    	<c:if test="${modifyStatus=='no' }">
								 	 	${jmiSubStore.phone }
							    	</c:if>
	    					</td>
                        </tr>

                </table>  
                
                
                
            <div class="bt mt"><h3 class="color2 ml"><ng:locale key="subStore.other.info"/></h3></div>
                <table class="form_details_table mt"> 
                        <tr>
                            <td class="tr" style="width: 205px;"><ng:locale key="subStore.personQty" />:</td>
                            <td style="width: 30%;">
						         <c:if test="${modifyStatus=='yes' }">
						        <form:input path="personQty" id="personQty" 
						        	 onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							    	 ${jmiSubStore.personQty }
						    	</c:if><ng:locale  key="busi.unit.wan"/>
    					</td>
                            <td class="tr" style="width: 195px;"><ng:locale key="subStore.storePhone" />:</td>
                            <td>
	                            <c:if test="${modifyStatus=='yes' }">
							       <form:input path="storePhone" id="storePhone" />
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							       ${jmiSubStore.storePhone }
						    	</c:if>
					    	</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="subStore.businessArea" />:</td>
                            <td>
					         <c:if test="${modifyStatus=='yes' }">
						        <form:input path="businessArea" id="businessArea" />
					    	</c:if>
					    	<c:if test="${modifyStatus=='no' }">
						     	${jmiSubStore.businessArea }
					    	</c:if>	<ng:locale  key="busi.unit.m"/>
					    	</td>
                            <td class="tr"><ng:locale key="subStore.averageIncome" />:</td>
                            <td>
					         <c:if test="${modifyStatus=='yes' }">
						   <form:input path="averageIncome" id="averageIncome" 
						   		onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
					    	</c:if>
					    	<c:if test="${modifyStatus=='no' }">
						 	 	${jmiSubStore.averageIncome }
					    	</c:if><ng:locale  key="busi.unit.yuan"/>
					    	</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="subStore.investAmount" />:</td>
                            <td> 
						         <c:if test="${modifyStatus=='yes' }">
							  		<form:input path="investAmount" id="investAmount" 
							  			 onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							 	 ${jmiSubStore.investAmount }
						    	</c:if><ng:locale  key="busi.unit.million"/></td>
                            <td class="tr"><ng:locale key="subStore.startDate" />:</td>
                            <td>
                            	<c:if test="${modifyStatus=='yes' }">
							  		 <%-- <form:input path="startDate" id="startDate" readonly="true" size="8" cssClass="readonly"/>
									<img src="./images/calendar/calendar7.gif" id="img_startDate" style="cursor: pointer;" title="<ng:locale key="Calendar.TT.SEL_DATE"/>"/> 
									<script type="text/javascript"> 
										Calendar.setup({
										inputField     :    "startDate", 
										ifFormat       :    "%Y-%m-%d",  
										button         :    "img_startDate", 
										singleClick    :    true
										}); 
									</script> --%>
									<form:input path="startDate" id="startDate" type="text" value="${startTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							 	 	<fmt:formatDate value="${jmiSubStore.startDate }" pattern="yyyy-MM-dd"/>
						    	</c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale  key="subStore.isdeal"/>:</td>
                            <td>
                            	<c:if test="${modifyStatus=='yes' }">
							  	<ng:list name="isdeal" listCode="yesno" value="${jmiSubStore.isdeal}" defaultValue="" showBlankLine="true"/>	
						    	</c:if>
						    	<c:if test="${modifyStatus=='no' }">
							 	 	 <ng:code listCode="yesno" value="${jmiSubStore.isdeal}"/>
						    	</c:if>
                            
                            </td>
                            <td class="tr"><ng:locale  key="subStore.specificBusiness"/>:</td>
                            <td>
						    		<c:if test="${modifyStatus=='yes' }">
								  		<form:input path="specificBusiness" id="specificBusiness" />
							    	</c:if>
							    	<c:if test="${modifyStatus=='no' }">
								 	 	${jmiSubStore.specificBusiness }
							    	</c:if>
	    					</td>
                        </tr>

                </table>  
                
                
                
                <div class="tc" style="margin-top: 10px;">
                	<c:if test="${param.strAction!='viewJmiSubStoreAddr' && (jmiSubStore.addrCheck!='1') }">
			               		<button type="submit" class="btn btn-info"><ng:locale key="button.save"/></button>
			                </c:if>
                            <button type="button" class="btn btn-success" name="back" onclick="returnOld()"><ng:locale key="button.back"/></button>
                </div>
		</div>
		

</form:form>
</div>

<script>

function returnOld(){
   window.location.href = "jmiSubStoreAddrs";
}
			var jq = jQuery.noConflict();
			
			function getIdCity(){
			   var province=jq("#provinceHidden").val();
			   jalCityManager.getAlCityByProvinceId(province,callIdCity);
			}
			function callIdCity(valid){
					
					  DWRUtil.removeAllOptions("cityHidden");
					  DWRUtil.addOptions("cityHidden",{'':'<ng:locale key='list.please.select'/>'});  
			      DWRUtil.addOptions("cityHidden",valid,"cityId","cityName");
			   		if(''!='${jmiSubStore.city}'){
			   			jq("#city").val('${jmiSubStore.city}');
			   			
			   		}
			   getIdDistrict();
			}
			function getIdDistrict(){
					var city=jq("#cityHidden").val();
					jalDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
			}
			function callBackIdDistrict(valid){
				DWRUtil.removeAllOptions("district");
					DWRUtil.addOptions("district",{'':'<ng:locale key='list.please.select'/>'});  
			    DWRUtil.addOptions("district",valid,"districtId","districtName");
					if(''!='${jmiSubStore.district}'){
						jq("#district").val('${jmiSubStore.district}');
					}
			
			}
		
			
		   <c:if test="${not empty jmiSubStore.province }">
		   getIdCity();
		   </c:if>
		</script>

		