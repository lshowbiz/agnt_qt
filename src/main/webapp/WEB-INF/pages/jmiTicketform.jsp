<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>





<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/jalCityManager.js'/>"></script>
<script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>"></script>

<div class="content fr">
			<h2 class="title mgb20">青奥会门票申请</h2>
			
		
<spring:bind path="jmiTicket.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>	

<form:form commandName="jmiTicket" method="post" action="jmiTicketform" name="jmiTicketform"  id="jmiTicketform">


<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>
<form:hidden path="id"/>
			
			
                <table width="100%" border="0" class="personalInfoTab" id="addrFiled">
                    <colgroup style="width:120px;"></colgroup>
                    <colgroup style="width:160px;"></colgroup>
                    <colgroup style="width:160px;"></colgroup>
                    <colgroup></colgroup>
                    <colgroup></colgroup>
                    <tbody>
                        <tr>
                            <td><label for="theName" class="pdl10">会员编号:</label></td>
                            <td>${jmiTicket.userCode }</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="tickettype" class="pdl10">门票类型:</label></td>
                            <td><ng:code listCode="tickettype" value="${jmiTicket.ticketType }"></ng:code> </td>
                            <td></td>
                            <td></td>
                        </tr>
                  <%--       <tr>
                            <td><label for="applyUserCode" class="pdl10">申请人编号:</label></td>
                            <td> <form:input path="applyUserCode" id="applyUserCode" /> </td>
                            <td></td>
                            <td></td>
                        </tr> --%>
                        <tr>
                            <td><label for="papernumber" class="star">参会员人身份证号:</label></td>
                            <td><form:input path="papernumber" id="papernumber"  /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="userName" class="star">参会员姓名:</label></td>
                            <td><form:input path="userName" id="userName"  /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="censusProvince" class="star">户籍所在地区:</label></td>
                            <td>
                                <form:select path="censusProvince"  cssClass="mySelect" onchange="getCensusCity()" >
											<form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
								            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
								</form:select>
                            </td>
                            <td>          
								<select name="censusCity" id="censusCity" onchange="getCensusDistrict();" class="mySelect">
										<option value=""><ng:locale key="list.please.select"/></option>
								</select>
								
                            </td>
                            <td>
	                    		<select name="censusDistrict" id="censusDistrict"  class="mySelect">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>	
						         		
                            </td>
                        </tr>
                        <tr>
                            <td><label for="theStreet" class="star">户籍所在街道地址:</label></td>
                            <td colspan="3">
                            <form:input path="censusAddress" id="censusAddress"  cssStyle="width:70%;" /></td>
                        </tr>
                        
                        
                        
                              <tr>
                            <td><label for="theProvince" class="star">居住所在地区:</label></td>
                            <td>
                                <form:select path="province"  cssClass="mySelect" onchange="getCity()" >
											<form:option label="" value=""><ng:locale key='list.please.select'/></form:option>
								            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
								</form:select>
                            </td>
                            <td>          
								<select name="city" id="city" onchange="getDistrict();" class="mySelect">
										<option value=""><ng:locale key="list.please.select"/></option>
								</select>
								
                            </td>
                            <td>
	                    		<select name="district" id="district"  class="mySelect">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>	
						         		
                            </td>
                        </tr>
                        <tr>
                            <td><label for="address" class="star">居住所在街道地址:</label></td>
                            <td colspan="3">
                            <form:input path="address" id="address"   cssStyle="width:70%;"/></td>
                        </tr>
                        
                        
                        
                        
                        
                        <tr>
                            <td><label for="mobiletele" class="star">参会人员手机号码:</label></td>
                            <td><form:input path="mobiletele" id="mobiletele"  /></td>
                            <td></td>
                            <td></td>
                        </tr>
                        
                        <tr>
                            <td><label for="mobiletele" class="pdl10">备注:</label></td>
                            <td colspan="3">
                            <form:textarea path="remark" cols="60" rows="5"/>
                            </td>
                        </tr>
                        
                        <tr>
                            <td></td>
                            <td><a href="#"  onclick="submitFormCommit(jmiTicketform);" class="btn_common corner2 fr mgt30">保存</a></td>
                            <td ></td>
                            <td >
                          
                            </td>
                        </tr>
                    </tbody>
                </table>
              </form:form>  
		</div>


















<script>

var jq = jQuery.noConflict();

function getCity(){
			   var province=jq("#province").val();
			   jalCityManager.getAlCityByProvinceId(province,callCity);
		   }
		   function callCity(valid){
		   		
		 		  DWRUtil.removeAllOptions("city");
		 		  DWRUtil.addOptions("city",{'':'<ng:locale key="list.please.select"/>'});  
                  DWRUtil.addOptions("city",valid,"cityId","cityName");
			   		if('${jmiTicket.city}'!=''){
			   			jq('#city').val('${jmiTicket.city}');
			   		}
			   	getDistrict();
		   }
		    function getDistrict(){
		   		var city=jq("#city").val();
		   		jalDistrictManager.getAlDistrictByCityId(city,callBackDistrict);
		   }
		   function callBackDistrict(valid){
	    		DWRUtil.removeAllOptions("district");
	 			DWRUtil.addOptions("district",{'':'<ng:locale key="list.please.select"/>'});  
                DWRUtil.addOptions("district",valid,"districtId","districtName");
                
		   		if('${jmiTicket.district}'!=''){
			   			jq('#district').val('${jmiTicket.district}');
			   	}
		   }



function getCensusCity(){
			   var province=jq("#censusProvince").val();
			   jalCityManager.getAlCityByProvinceId(province,callCensusCity);
		   }
		   function callCensusCity(valid){
		   		
		 		  DWRUtil.removeAllOptions("censusCity");
		 		  DWRUtil.addOptions("censusCity",{'':'<ng:locale key="list.please.select"/>'});  
                  DWRUtil.addOptions("censusCity",valid,"cityId","cityName");
			   		if('${jmiTicket.censusCity}'!=''){
			   			jq('#censusCity').val('${jmiTicket.censusCity}');
			   		}
			   	getCensusDistrict();
		   }
		    function getCensusDistrict(){
		   		var city=jq("#censusCity").val();
		   		jalDistrictManager.getAlDistrictByCityId(city,callBackCensusDistrict);
		   }
		   function callBackCensusDistrict(valid){
	    		DWRUtil.removeAllOptions("censusDistrict");
	 			DWRUtil.addOptions("censusDistrict",{'':'<ng:locale key="list.please.select"/>'});  
                DWRUtil.addOptions("censusDistrict",valid,"districtId","districtName");
                
		   		if('${jmiTicket.censusDistrict}'!=''){
			   			jq('#censusDistrict').val('${jmiTicket.censusDistrict}');
			   	}
		   }

		   getCity(); getCensusCity();
</script>






