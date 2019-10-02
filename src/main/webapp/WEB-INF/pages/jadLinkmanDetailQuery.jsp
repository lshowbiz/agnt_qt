<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<!--<title><fmt:message key="linkmanDetail.title"/></title>-->

    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
    <script src="./scripts/calendar/calendar.js"></script>
    <script src="./scripts/calendar/calendar-setup.js"></script>
    <script src="./scripts/calendar/lang.jsp"> </script>
	<!--dwr省市区级联插件--- START  -->
	<script src="<c:url value='/dwr/util.js'/>" ></script> 
    <script src="<c:url value='/dwr/engine.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>
	<!--dwr省市区级联插件--- END  -->
	

    
    <script>
         
         //返回的方法
         function goBack(){
             window.location.href="jadLinkmanQuery";
         }
    
    </script>
    
</head>
 <!--
 <div id="titleBar" class="searchBar">&nbsp;&nbsp;
		<input type="button" class="btn btn_mini2" name="cancel"  onclick="goBack()" value="<ng:locale key="operation.button.return"/>" />
 </div>
 -->
<body>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="linkman.base.informatin"/></h3>
		</div>	
	<div class="mt">
            <form:form commandName="linkman"  action="jadLinkmanDetailQuery" id="jadLinkmanDetailQueryId" onsubmit="if(isFormPosted()){return true;}{return false;}">
            <table class="form_details_table">
                <tbody>
                    <tr>
                       <td><ng:locale key="linkman.name"/>：${linkman.name }</td>
                       <td>
                       		<ng:locale key="list.sex"/>：
                       		<c:if test="${linkman.sex=='M'}">
                                <ng:code listCode="sex" value="M"/>
                            </c:if>
                            <c:if test="${linkman.sex=='F'}">
                                <ng:code listCode="sex" value="F"/>
                            </c:if>
                       </td>
                       <td><ng:locale key="linkman.birthday"/>：${linkman.birthday}</td>
                    </tr>
                    <tr>
                       <td><ng:locale key="linkman.mobilephone"/>：${linkman.mobilephone}</td>
                       <td><ng:locale key="linkman.nextContactTime"/>：${linkman.nextContactTime}</td>
                       <td>
                       	   <ng:locale key="linkman.customerSource"/>：
                           <c:if test="${linkman.customerSource=='1'}">
                                <ng:code listCode="linkman.customersource" value="1"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='2'}">
                                <ng:code listCode="linkman.customersource" value="2"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='3'}">
                                <ng:code listCode="linkman.customersource" value="3"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='4'}">
                                <ng:code listCode="linkman.customersource" value="4"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='5'}">
                                <ng:code listCode="linkman.customersource" value="5"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='6'}">
                                <ng:code listCode="linkman.customersource" value="6"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='7'}">
                                <ng:code listCode="linkman.customersource" value="7"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='8'}">
                                <ng:code listCode="linkman.customersource" value="8"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='9'}">
                                <ng:code listCode="linkman.customersource" value="9"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='10'}">
                                <ng:code listCode="linkman.customersource" value="10"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='11'}">
                                <ng:code listCode="linkman.customersource" value="11"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='12'}">
                                <ng:code listCode="linkman.customersource" value="12"/>
                           </c:if>
                           <c:if test="${linkman.customerSource=='13'}">
                                <ng:code listCode="linkman.customersource" value="13"/>
                           </c:if>
                       </td>
                    </tr>
                    <tr>
                       <td>客户分组：${linkmanCategory }</td>
                       <td>
                       	          客户类型：
                           <c:if test="${linkman.linkmanStatus=='1'}">
                                <ng:code listCode="linkman.status" value="1"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='2'}">
                                <ng:code listCode="linkman.status" value="2"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='3'}">
                                <ng:code listCode="linkman.status" value="3"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='4'}">
                                <ng:code listCode="linkman.status" value="4"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='5'}">
                                <ng:code listCode="linkman.status" value="5"/>
                           </c:if>
                        </td>
                       <td><ng:locale key="linkman.familyPhone"/>：${linkman.familyPhone}</td>
                       
                    </tr>
                    <!--  
                    <tr>
                        <td><ng:locale key="linkman.heat"/>：</td>
                        <td>
                            <c:if test="${linkman.heat=='1'}">
                                <ng:code listCode="linkman.heat" value="1"/>
                            </c:if>
                            <c:if test="${linkman.heat=='2'}">
                                <ng:code listCode="linkman.heat" value="2"/>
                            </c:if>
                            <c:if test="${linkman.heat=='3'}">
                                <ng:code listCode="linkman.heat" value="3"/>
                            </c:if>
                            <c:if test="${linkman.heat=='4'}">
                                <ng:code listCode="linkman.heat" value="4"/>
                            </c:if>
                        </td>
                        <td><ng:locale key="linkman.status"/>：</td>
                        <td>
                           <c:if test="${linkman.linkmanStatus=='1'}">
                                <ng:code listCode="linkman.status" value="1"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='2'}">
                                <ng:code listCode="linkman.status" value="2"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='3'}">
                                <ng:code listCode="linkman.status" value="3"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='4'}">
                                <ng:code listCode="linkman.status" value="4"/>
                           </c:if>
                           <c:if test="${linkman.linkmanStatus=='5'}">
                                <ng:code listCode="linkman.status" value="5"/>
                           </c:if>
                        </td>
                    </tr>-->
                    <tr>
                       <td colspan="3"><ng:locale key="linkman.familyDetailAddress"/>：${linkman.familyAddress}</td>
                    </tr>
                    <tr>
                        <td colspan="3">
                        	<ng:locale key="linkman.familySeat"/>：
                            <%-- <form:select path="province" cssClass="text medium mySelect" onchange="getCity();"  disabled="${provinceRemark}">
                                <form:option label="" value=""/>
                                <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
                            </form:select> --%>
                            ${province }
                            &nbsp;
                            <%-- <select name="city" id="city" class="mySelect" onchange="getAreaByCity();" disabled="${cityRemark}">
                                <option value=""><ng:locale key="list.please.select"/></option>
                            </select> --%>
                            ${city }
                            &nbsp;
                            <%-- <select name="district" id="district" class="mySelect" disabled="${districtRemark}">
                                <option value=""><ng:locale key="list.please.select"/></option>
                            </select> --%>
                            ${district }
                        </td>
                    </tr>
                    <tr>
                        <td><ng:locale key="miMember.companyName"/>：${linkman.company}</td>
                        <td colspan="2"><ng:locale key="miMember.companyAddr"/>：${linkman.companyAddress}</td>
                    </tr>
                    <tr>
                        <td><ng:locale key="linkman.companyPhone"/>：${linkman.companyPhone}</td>
                        <td><ng:locale key="miMember.faxtele"/>：${linkman.fax}</td>
                        <td><ng:locale key="subStore.postalcode"/>：${linkman.post}</td>
                    </tr>
                    <tr>
                        <td><ng:locale key="micro.channel"/>：${linkman.microMessage}</td>
                        <td>QQ：${linkman.qq}</td>
                        <td><ng:locale key="linkman.weibo"/>：${linkman.weibo}</td>
                    </tr>
                    <tr>
                        <td>MSN：${linkman.msn}</td>
                        <td><ng:locale key="subStore.email"/>：${linkman.email}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="3">
                        <ng:locale key="linkman.remark"/>：
                        ${linkman.remark }
                        <%-- <c:if test="${not empty linkman.remark}">
                            <form:textarea path="remark" id="remark" cssClass="text medium" cssErrorClass="text medium error" disabled="true" resize="none" style="width:99%;height:70px;margin-top:7px;"/>
                        </c:if> --%>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="tc mt">
				<button type="button" class="btn btn-success" name="cancel" onclick="returnOld()"><ng:locale key="operation.button.return"/></button>
			</div>
        </form:form>
    </div>
</div>
<v:javascript formName="linkman" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script src="<c:url value='/scripts/validator.jsp'/>"></script>
<script src="js/jQuery-1.9.1.min.js"></script>
<script src="js/joyLife.js"></script>
<script>
            var qquery = jQuery.noConflict();
   //在初始化或者选取省份的时候执行该方法
		   window.onload = function getCity(){
		        //省的公示与隐藏
		        var provinceMark ="<%= request.getAttribute("provinceRemark")%>";
		        if("true"==provinceMark){
		            document.getElementById("province").style.display='';
		        }else{
		            document.getElementById("province").style.display='none';
		        }
		        
		        //市的公示与隐藏
		        var cityMark ="<%= request.getAttribute("cityRemark")%>";
		        if("true"==cityMark){
		            document.getElementById("city").style.display='';
		        }else{
		            document.getElementById("city").style.display='none';
		        }
		        
		        //地区的公示与隐藏
		        var districtMark ="<%= request.getAttribute("districtRemark")%>";
		        if("true"==districtMark){
		            document.getElementById("district").style.display='';
		        }else{
		            document.getElementById("district").style.display='none';
		        }
		        
		        var provinceId = qquery("#province").val();
		        //通过ＤＷＲ开源框架，这里调用java后台的项目，并且使用了回调函数
		        jalCityManager.getAlCityByProvinceId(provinceId,callIdCity);
		    }
		    //回调函数
		    function callIdCity(valid){
		        //去掉下拉框中原有的数值
		        DWRUtil.removeAllOptions("city");
		        //给下拉框加上受选项－请选择(select)
		        DWRUtil.addOptions("city",{'':'select..'});
		        //将集合valid中的数据放入下拉框中
		        DWRUtil.addOptions("city",valid, "cityId","cityName");
		        if(''!='${linkman.city}'){
		        　　　　    //将数据库中查询到的城市的代码放入下拉框，就是使下拉框选中对应的城市
		        　　　　　qquery("#city").val('${linkman.city}');　
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
		       DWRUtil.addOptions("district",{'':'select..'});
		       DWRUtil.addOptions("district",districtList,"districtId","districtName");
		       if(''!='${linkman.district}'){
		        　　　　    //将数据库中查询到的地区的代码放入下拉框，就是使下拉框选中对应的地区
		        　　　　　qquery("#district").val('${linkman.district}');　
		       }
		   }

		   function returnOld(){
              window.location.href = "jadLinkmanQuery.html";
           }
</script>
</body>