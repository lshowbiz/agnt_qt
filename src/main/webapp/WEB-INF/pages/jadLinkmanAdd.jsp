<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<!--<title><fmt:message key="linkmanDetail.title"/></title>-->
    <script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
	<!--dwr省市区级联插件--- START  -->
	<script src="<c:url value='/dwr/util.js'/>" ></script>
    <script src="<c:url value='/dwr/engine.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>
	<!--dwr省市区级联插件--- END  -->
	
	<spring:bind path="linkman.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error" id="errorDiv" style="display: none">    
	        <c:forEach var="error" items="${status.errorCodes}">
	           <div> <c:out value="${error}" escapeXml="false"/></div>
	        </c:forEach>
	    </div>
	    </c:if>
    </spring:bind>
</head>
<body>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale  key="linkman.base.informatin"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkman" method="post" action="jadLinkmanAdd" id="jadLinkmanAdd" onsubmit="if(isFormPosted()){return true;}{return false;}">
    <!-- 客户基本信息 -->
    <table class="form_edit_table">
            <tbody>
                <tr>
                    <td><span class="star"></span><ng:locale key="linkman.name"/>：</td>
                    <td><form:input path="name" id="name" cssClass="text medium" size="20" maxlength="100"/></td>
                    <td><span class="star"></span><ng:locale key="list.sex"/>：</td>
                    <td><ng:list name="sex" listCode="sex" value="${linkman.sex}" defaultValue=""/></td>

                </tr>
                <tr>
                    <td><span class="pdl10"><ng:locale key="linkman.birthday"/>：</span></td>
                    <td>
                    <form:input path="birthday" id="birthday" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                    </td>
                    <td><span class="star"></span><ng:locale key="linkman.mobilephone"/>：</td>
                    <td><form:input path="mobilephone" id="mobilephone" cssClass="text medium" size="20" maxlength="20"/></td>

                </tr>
                <tr>
                    <td><span class="pdl10"><ng:locale key="linkman.nextContactTime"/>：</span></td>
                    <td>
                    <form:input path="nextContactTime" id="nextContactTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                    </td>
                    <td><span class="pdl10"><ng:locale key="linkman.customerSource"/>：</span></td>
                    <td>
                    <ng:list name="customerSource" listCode="linkman.customersource" value="${linkman.customerSource}" defaultValue=""/>
                    </td>

                </tr>
                <!--  
                <tr>
                    <td><span class="pdl10"><ng:locale key="linkman.heat"/>：</span></td>
                    <td>
                    <ng:list name="heat" listCode ="linkman.heat" value="${linkman.heat}" defaultValue=""/>
                    </td>
                    <td><span class="pdl10"><ng:locale key="linkman.status"/>：</span></td>
                    <td>
                    <ng:list name="linkmanStatus" listCode="linkman.status" value="${linkman.linkmanStatus}" defaultValue=""/>
                    </td>

                </tr>-->
                <tr>
                    <td><span class="pdl10">分组名称：</span></td>
                    <td>
                    <form:select path="lcId" cssClass="text medium mySelect">
                    <form:option value="">请选择</form:option>
                    <form:options items="${linkmanCategoryList}" itemValue="id" itemLabel="name"/>

                    </form:select>
                    </td>
                   <td><span class="pdl10">客户类型：</span></td>
                    <td>
                    <ng:list name="linkmanStatus" listCode="linkman.status" value="${linkman.linkmanStatus}" defaultValue=""/>
                    </td>
                </tr>
                
                <!-- 家庭住址信息 -->
                <tr>
	                <td><span class="pdl10"><ng:locale key="linkman.familyPhone"/>：</span></td>
	                <td><form:input path="familyPhone" id="familyPhone" cssClass="text medium" size="25" maxlength="20"/></td>
	                <td><span class="pdl10"><ng:locale key="linkman.familyDetailAddress"/>：</span></td>
	                <td><form:input path="familyAddress" id="familyAddress" cssClass="text medium" size="85" maxlength="200"/></td>
	           	</tr>
	            <tr>
	                <td><span class="pdl10"><ng:locale key="linkman.familySeat"/>：</span></td>
	                <td colspan="3">
	                    <form:select path="province" onchange="getCity();">
	                        <form:option value="">请选择</form:option>
	                        <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
	                    </form:select>
	                    &nbsp;
	                    <select name="city" id="city" onchange="getAreaByCity();">
	                        <option value=""><ng:locale key="list.please.select"/></option>
	                    </select>
	                    &nbsp;
	                    <select name="district" id="district">
	                        <option value=""><ng:locale key="list.please.select"/></option>
	                    </select>
	                </td>
	            </tr>
                
                <!-- 公司相关信息 -->
	            <tr>
	                <td><span class="pdl10"><ng:locale key="miMember.companyName"/>：</span></td>
	                <td><form:input path="company" id="company" cssClass="text medium" size="85" maxlength="100"/></td>
	                <td><span class="pdl10"><ng:locale key="miMember.companyAddr"/>：</span></td>
	                <td><form:input path="companyAddress" id="companyAddress" cssClass="text medium" size="85" maxlength="200"/></td>
	            </tr>
	            <tr>
	                <td><span class="pdl10"><ng:locale key="linkman.companyPhone"/>：</span></td>
	                <td><form:input path="companyPhone" id="companyPhone" cssClass="text medium" size="25" maxlength="11"/></td>
	                <td><span class="pdl10"><ng:locale key="miMember.faxtele"/>：</span></td>
	                <td><form:input path="fax" id="fax" cssClass="text medium" size="25" maxlength="20"/></td>
	            </tr>
	            <tr>
	                <td><span class="pdl10"><ng:locale key="subStore.postalcode"/>：</span></td>
	                <td><form:input path="post" id="post" cssClass="text medium" size="25" maxlength="6"  onkeyup="this.value=this.value.replace(/\D/g,'')"/></td>
	                <td><span class="pdl10"><ng:locale key="micro.channel"/>：</span></td>
	                <td><form:input path="microMessage" id="microMessage" cssClass="text medium" size="25" maxlength="11"/></td>
	            </tr>
	            <!--客户其他信息 -->
	            <tr>
	                <td><span class="pdl10">QQ：</span></td>
	                <td><form:input path="qq" id="qq" cssClass="text medium" size="25" maxlength="11"/></td>
	                <td><span class="pdl10"><ng:locale key="linkman.weibo"/>：</span></td>
	                <td><form:input path="weibo" id="weibo" cssClass="text medium" size="25" maxlength="80"/></td>
	            </tr>
	            <tr>
	                <td><span class="pdl10">MSN：</span></td>
	                <td><form:input path="msn" id="msn" cssClass="text medium" size="25" maxlength="32"/></td>
	                <td><span class="pdl10"><ng:locale key="subStore.email"/>：</span></td>
	                <td><form:input path="email" id="email" cssClass="text medium" size="25"/></td>
	            </tr>
	            <tr>
	                <td><span class="pdl10"><ng:locale key="linkman.remark"/>：</span></td>
	                <!-- 缺少输入框字符个数的校验 -->
	                <td colspan="3">
	                    <form:textarea path="remark" id="remark" cssClass="text medium" style="width:99%;height:70px;margin-top:8px;resize:none;overflow:hidden;" cssErrorClass="text medium error" onkeydown="checkNumber(this.value)" />
	                </td>
	            </tr>
            </tbody>
        </table>
        <div class="tc">
            <button type="submit" class="btn btn-info" name="save" id="saveId"><ng:locale key="operation.button.save"/></button>
        	<button type="button" class="btn btn-success" name="cancel" onclick="returnOld()"><ng:locale key="operation.button.cancel"/></button>
		</div>
    
</form:form>
</div>
</div>

<v:javascript formName="linkman" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script src="<c:url value='/scripts/validator.jsp'/>"></script>
<script>
           //给jQuery创建一个新的别名	
           var qquery = jQuery.noConflict();
          //在初始化或者选取省份的时候执行该方法
		   function getCity(){
		        var saveMark = "<%=request.getAttribute("saveMark")%>";
		        //保存键的显示与隐藏
		        if("saveMark"==saveMark){
		          document.getElementById("saveId").style.display='none';
		        }else{
		          document.getElementById("saveId").style.display='';
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
		        DWRUtil.addOptions("city",{'':'<ng:locale key='list.please.select'/>'});
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
		       DWRUtil.addOptions("district",{'':'<ng:locale key='list.please.select'/>'});
		       DWRUtil.addOptions("district",districtList,"districtId","districtName");
		       if(''!='${linkman.district}'){
		        　　　　    //将数据库中查询到的地区的代码放入下拉框，就是使下拉框选中对应的地区
		        　　　　　qquery("#district").val('${linkman.district}');　
		       }
		   }

          function checkNumber(mark){
              //alert("备注只能输入500个字符");
              if(mark.length>250){
                   alert("备注只能输入500个字符");
                   //document.getElementById("remark").disabled="true";
                   document.getElementById("remark").readOnly=true;
                   
              }
              
          }
          
          function returnOld(){
              window.location.href = "jadLinkmanQuery.html";
          }
          
          getCity();
</script>
</body>