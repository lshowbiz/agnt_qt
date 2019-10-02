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

</head>

<body>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml">客户跟踪</h3>
		</div>	
	<div class="mt">
            <!-- <form:form commandName="linkman"  action="jadLinkmanDetailQuery" id="jadLinkmanDetailQueryId" onsubmit="if(isFormPosted()){return true;}{return false;}">
             -->
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
                       <td><ng:locale key="linkman.customerSource"/>：<ng:code listCode="linkman.customersource" value="${linkman.customerSource}"/></td>
                    </tr>
                    <!--  
                    <tr>
                        <td><label class="pdl10"><ng:locale key="linkman.heat"/>：</label></td>
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
                        <td><label class="pdl10"><ng:locale key="linkman.status"/>：</label></td>
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
                       <td>
                       	  所属群组：
                         <%-- <form:select path="lcId" cssClass="text medium mySelect" disabled="true">
                             <form:option label="" value=""/>
                             <form:options items="${linkmanCategoryList}" itemValue="id" itemLabel="name"/>
                         </form:select> --%>
                         ${linkmanCategory }
                       </td>
                       <td>客户类型：<ng:code listCode="linkman.status" value="${linkman.linkmanStatus}"/></td>
                       <td></td>
                    </tr>
                  </tbody>
            </table>

			<div id="ordersBox" style="display: none">
            <h2 class="title mgb20"><ng:locale key = "linkman.familyAddress"/></h2>
            <table class="personalInfoTab detail" width="100%" border="0">
                <colgroup style="width:110px;"></colgroup>
                <colgroup></colgroup>
                <tbody>
                
                
                    <tr>
                        <td><label class="pdl10"><ng:locale key="linkman.familyPhone"/>：</label></td>
                        <td>${linkman.familyPhone}</td>
                    </tr>
                    <tr>
                        <td><label class="pdl10"><ng:locale key="linkman.familySeat"/>：</label></td>
                        <td>
                            <form:select path="province" cssClass="text medium mySelect" onchange="getCity();"  disabled="${provinceRemark}">
                                <form:option label="" value=""/>
                                <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
                            </form:select>
                            &nbsp;
                            <select name="city" id="city" class="mySelect" onchange="getAreaByCity();" disabled="${cityRemark}">
                                <option value=""><ng:locale key="list.please.select"/></option>
                            </select>
                            &nbsp;
                            <select name="district" id="district" class="mySelect" disabled="${districtRemark}">
                                <option value=""><ng:locale key="list.please.select"/></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label class="pdl10"><ng:locale key="linkman.familyDetailAddress"/>：</label></td>
                        <td>${linkman.familyAddress}</td>
                    </tr>
               </tbody>
            </table>


            <h2 class="title mgb20"><ng:locale key="linkman.companyInformation"/></h2>
            <table class="personalInfoTab detail" width="100%" border="0">
                <colgroup style="width:110px;"></colgroup>
                <colgroup></colgroup>
                <colgroup style="width:100px;"></colgroup>
                <colgroup ></colgroup>
                <tbody>
                    <tr>
                        <td><label class="pdl10"><ng:locale key="miMember.companyName"/>：</label></td>
                        <td colspan="3">${linkman.company}</td>
                    </tr>
                    <tr>
                        <td><label class="pdl10"><ng:locale key="miMember.companyAddr"/>：</label></td>
                        <td colspan="3">${linkman.companyAddress}</td>
                    </tr>
                    <tr>
                        <td><label class="pdl10"><ng:locale key="linkman.companyPhone"/>：</label></td>
                        <td>${linkman.companyPhone}</td>
                        <td style="width:114px;"><label class="pdl10"><ng:locale key="miMember.faxtele"/>：</label></td>
                        <td>${linkman.fax}</td>
                    </tr>
                    <tr>
                        <td><label class="pdl10"><ng:locale key="subStore.postalcode"/>：</label></td>
                        <td colspan="3">${linkman.post}</td>
                    </tr>
                </tbody>
            </table>


            <h2 class="title mgb20"><ng:locale key="linkman.otherInformation"/></h2>
            <table class="personalInfoTab detail" width="100%" border="0">
                <colgroup style="width:110px;"></colgroup>
                <colgroup></colgroup>
                <colgroup style="width:100px;"></colgroup>
                <colgroup ></colgroup>
                <tbody>
                    <tr>
                        <td><label class="pdl10"><ng:locale key="micro.channel"/></label>：</td>
                        <td>${linkman.microMessage}</td>
                    </tr>
                    <tr>
                        <td><label class="pdl10">QQ</label>：</td>
                        <td>${linkman.qq}</td>
                        <td><label class="pdl10"><ng:locale key="linkman.weibo"/>：</label></td>
                        <td>${linkman.weibo}</td>
                    </tr>
                    <tr>
                        <td><label class="pdl10">MSN：</label></td>
                        <td>${linkman.msn}</td>
                        <td><label class="pdl10"><ng:locale key="subStore.email"/>：</label></td>
                        <td>${linkman.email}</td>
                    </tr>
                    <tr>
                        <td style="vertical-align: top;"><label class="pdl10"><ng:locale key="linkman.remark"/>：</label></td>
                        <td colspan="3">
                        <c:if test="${not empty linkman.remark}">
                            <form:textarea path="remark" id="remark" cssClass="text medium" cssErrorClass="text medium error" disabled="true" resize="none" style="width:80%;height:60px;margin-top:7px;"/>
                        </c:if>
                        </td>
                    </tr>
                </tbody>
            </table>
 <!--
        </form:form>-->
        </div>
        <!-- <div class="slideBox">
                <a href="javascript:void(0);" class="btn btn_ext corner2">显示更多<b></b></a>
            </div> -->
        
        <!-- <h2 class="title mgb20">跟踪记录</h2> -->
     <div style="margin-top:20px;">   
     <c:forEach items="${relationshipRecordList}" var="relationshipRecord" >
     <table class="form_details_table">
        <tbody>
           <tr>
           <td>主题：${relationshipRecord.subject }</td>
           <td>发生时间：<fmt:formatDate value="${relationshipRecord.contactTime }" pattern="yyyy-MM-dd"/></td>
           <td>
           	      联系方式：
              <c:if test="${relationshipRecord.contactType=='1'}">
                  <ng:code listCode="relationshiprecord.type" value="1"/>
              </c:if>
              <c:if test="${relationshipRecord.contactType=='2'}">
                  <ng:code listCode="relationshiprecord.type" value="2"/>
              </c:if>
              <c:if test="${relationshipRecord.contactType=='3'}">
                  <ng:code listCode="relationshiprecord.type" value="3"/>
              </c:if>
              <c:if test="${relationshipRecord.contactType=='4'}">
                  <ng:code listCode="relationshiprecord.type" value="4"/>
              </c:if>
              <c:if test="${relationshipRecord.contactType=='5'}">
                  <ng:code listCode="relationshiprecord.type" value="5"/>
              </c:if>
           </td>
           </tr> 
           <tr>
	          <td colspan="3">
              		具体内容：${relationshipRecord.content }
	           </td>
           </tr> 
           </tbody></table>
           <div align="right">
				<a href="#" onclick="toUpdateRelation('${ctx}/toUpdateRelationshipRecord?1=1&relationId=${relationshipRecord.id}&linkmanId=${linkman.id}')" class="ft12 colorQL">[修改]</a>&nbsp;&nbsp;
				<a href="#" onclick="toConDel('${ctx}/delJadRelationshipRecord?1=1&relationId=${relationshipRecord.id}&linkmanId=${linkman.id}')" class="ft12 colorQL">[删除]</a>
			</div>
           <br>
     </c:forEach>
     </div>
<script>
	function toUpdateRelation(url){
		
		alert("请在该页面下端进行修改操作！");
		window.location.href = url;
	}
	function toConDel(url){
	
	     if(window.confirm("您确定删除该记录？")){
	     
			window.location.href = url;
	     }
	}
</script>
	
	<div class="bt mt">
		<h3 class="color2 ml">
			<c:if test="${not empty relationshipRecord.id}">修改记录</c:if>
        	<c:if test="${empty relationshipRecord.id}">添加新记录</c:if>
		</h3>
	</div>
        
    <form:form commandName="relationshipRecord" method="post" action="addJadRelationshipRecord" id="relationshipRecordAddId" onsubmit="if(isFormPosted()){return true;}{return false;}">
    <input type="hidden" name="linkmanId" id="linkmanId" value="${linkman.id }" />
    
    <form:hidden path="id" id="id"/>

        <table class="form_edit_table">
            <tr>
                <td><span class="star"></span>主题：</td>
                <td><form:input path="subject" id="subject" type="text" maxlength="200"/></td>
				<td><span class="star"></span>联系方式：</td>
                <td>
                    <ng:list name="contactType" id="contactType" listCode="relationshiprecord.type" value="${relationshipRecord.contactType}" defaultValue=""/>
                </td>
	            <td><span class="star"></span>发生时间：</td>
	            <td>
	            	<form:input path="contactTime" id="contactTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            </td>
            </tr>
            <tr>
                <td>具体内容：</td>
                <td colspan="5">
                    <form:textarea path="content" id="content" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" onkeydown="checkNumber(this.value)" />
                </td>
            </tr>
        </table>
        <div class="tc">
         	<c:if test="${not empty relationshipRecord.id}"><button type="submit" class="btn btn-info" name="save" id="update">保存</button></c:if>
          	<c:if test="${empty relationshipRecord.id}"><button type="submit" class="btn btn-info" name="save" id="update">添加</button></c:if>
          	&nbsp;&nbsp;<button type="button" onclick="goBack()" class="btn btn-success"><ng:locale key="operation.button.return"/></button>
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
		 //返回按钮的方法  
	      function goBack(){
	            window.location.href="jadLinkmanQueryGz";
	      }
</script>
</body>