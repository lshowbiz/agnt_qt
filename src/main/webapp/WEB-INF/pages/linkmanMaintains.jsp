<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-客户维护-查询 -->
</head>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script src="./scripts/calendar/calendar.js"></script>
<script src="./scripts/calendar/calendar-setup.js"></script>
<script src="./scripts/calendar/lang.jsp"></script>
<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
}
   //查询
   function checkApplication(theForm){
      theForm.submit();
   }
   
   //新增
   function linkmanMaintainAdd(){
       window.location.href="linkmanMaintainform.html?linkmanMaintainFunction=linkmanMaintainAdd";
   }
   
   //删除
   function linkmanMaintainDelete(id){
       if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
           window.location.href="linkmanMaintainDelete.html?id="+id;
       }
   }
   
  </script>


<body>
	<div class="cont" >	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="linkmanMaintain.maintain"/></h3>
		</div>	
        <form action="linkmanMaintains" method="get" name="linkmanMaintains" id="linkmanMaintains">
           <table class="search_table mt" >
				<tr>
					<td><ng:locale key="linkman.name"/>：</td>
					<td>
						<input type="text" name="name" value="${name }" maxlength="100"/>
					</td>
					
					
					<td><ng:locale key="common.startTime"/>：</td>
					<td>
						<input type="text" name="maintenanceTimeBegin" id="maintenanceTimeBegin"  value="${param.maintenanceTimeBegin }" 
							style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/> 
					</td>
					
					<td><ng:locale key="publicSchedule.endTime"/>：</td>
					<td>
						<input type="text" name="maintenanceTimeEnd" id="maintenanceTimeEnd"  value="${param.maintenanceTimeEnd }" 
							style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					
				</tr>
				<tr>
					<td><ng:locale key="linkmanMaintain.maintenanceTopic"/>：</td>
					<td>
						<input type="text" name="maintenanceTopic" value="${param.maintenanceTopic }" />
					</td>
					<td><ng:locale key="linkmanMaintain.maintenanceMode"/>：</td>
					<td>
						<ng:list name="maintenanceMode" listCode="linkmanmaintain.type" value="${param.maintenanceMode}" defaultValue="" styleClass="mySelect"/>
						
					</td>
					<td></td>
					<td>
						<button name="search" id="search" type="button" onclick="checkApplication(document.linkmanMaintains)"><ng:locale key="operation.button.search"/></button>
						<button name="add" id="add" type="button" onclick="linkmanMaintainAdd()"><ng:locale key="operation.button.add"/></button>
					</td>
				</tr>
				
            </table>
            
            
			
        </form>


       <div class="mt">	
			<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >		
                 <tbody class="list_tbody_header">
					<tr>
                        <td><ng:locale key ="linkman.name"/></th>
                        <td><ng:locale key ="linkmanMaintain.maintenanceTopic"/></td>
                        <td><ng:locale key ="linkmanMaintain.maintenanceMode"/></td>
                        <td><ng:locale key ="linkmanMaintain.maintenanceTime"/></td>
                        <td><ng:locale key ="sysOperationLog.moduleName"/></td>
                    </tr>
				 </tbody>
                 <tbody class="list_tbody_row">
	                 <c:forEach items="${linkmanMaintainList}" var="linkmanMaintainOb" >
	                 <tr class="bg-c gry3">
	                       <td>${linkmanMaintainOb.name }</td>
	                       <td>${linkmanMaintainOb.maintenanceTopic }</td>
	                       <td>
	                           <c:if test="${linkmanMaintainOb.maintenanceMode=='1'}">
	                                     <ng:code listCode="linkmanmaintain.type" value="1"/>
	                           </c:if>
	                           <c:if test="${linkmanMaintainOb.maintenanceMode=='2'}">
	                                     <ng:code listCode="linkmanmaintain.type" value="2"/>
	                           </c:if>
	                           <c:if test="${linkmanMaintainOb.maintenanceMode=='3'}">
	                                     <ng:code listCode="linkmanmaintain.type" value="3"/>
	                           </c:if>
	                           <c:if test="${linkmanMaintainOb.maintenanceMode=='4'}">
	                                     <ng:code listCode="linkmanmaintain.type" value="4"/>
	                           </c:if>
	                           <c:if test="${linkmanMaintainOb.maintenanceMode=='5'}">
	                                     <ng:code listCode="linkmanmaintain.type" value="5"/>
	                           </c:if>
	                           <c:if test="${linkmanMaintainOb.maintenanceMode=='6'}">
	                                     <ng:code listCode="linkmanmaintain.type" value="6"/>
	                           </c:if>
	                           <c:if test="${linkmanMaintainOb.maintenanceMode=='7'}">
	                                     <ng:code listCode="linkmanmaintain.type" value="7"/>
	                           </c:if>
	                       </td>
	                       <td>${linkmanMaintainOb.maintenanceTime }</td>
	                       <td>
	                            <img src="images/search.gif" onclick="window.location.href='linkmanMaintainQueryDetail.html?id=${linkmanMaintainOb.id }';" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
	                            &nbsp;&nbsp;
	                            <img src="images/pencil.gif" onclick="window.location.href='linkmanMaintainform.html?id=${linkmanMaintainOb.id }&linkmanMaintainFunction=linkmanMaintainUpdate';" alt="<ng:locale key="operation.button.edit"/>"/>
	                            &nbsp;&nbsp;
	                            <img src="images/delete.gif" onclick="linkmanMaintainDelete(${linkmanMaintainOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
	                       </td>
	                       </tr>
	                 </c:forEach>
                </tbody>
        	</table>
        </div>
	${page.pageInfo }
	</div>
</body>
