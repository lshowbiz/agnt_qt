<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-事件管理-查询或有条件查询 -->
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
   function linkmanEventAdd(){
       window.location.href="linkmanEventform.html";
   }
   
   //删除
   function linkmanEventDelete(id){
       if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
           window.location.href="linkmanEventDelete.html?id="+id;
       }
   }
   
  </script>


<body>
	<div class="cont" >	
			<div class="bt mt">
				<h3 class="color2 ml"><ng:locale key="linkmanEvent.event"/></h3>
			</div>	
   
        <form action="linkmanEvents" method="get" name="linkmanEvents" id="linkmanEvents">
            <table class="search_table mt" >
					<tr>
						<td><ng:locale key="linkmanEvent.title"/>：</td>
						<td><input type="text" name="title" value="${title }" maxlength="100"/></td>
						<td>会员编号：</td>
						<td><input type="text" name="mCode" value="${mCode }"  maxlength="100"/></td>
						<td>会员姓名：</td>
						<td><input type="text" name="name" value="${name }"  maxlength="100"/></td>
						
					</tr>
					<tr>	
						<td>开始时间：</td>
						<td>
							<input type="text" name="timeBegin" id="timeBegin"  value="${timeBegin }" 
							style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
							
						</td>
						<td>结束时间：</td>
						<td>
							<input type="text" name="timeEnd" id="timeEnd"  value="${timeEnd }" 
								style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</td>
						<td><ng:locale key="linkmanEvent.eventType"/>：</td>
						<td>
							<ng:list name="eventType" listCode="linkmanevent.type" value="${eventType}" defaultValue="" styleClass="mySelect" />
						</td>
						
						
						<td>
					
						<button name="search" id="search" type="button" onclick="checkApplication(document.linkmanEvents)"><ng:locale key="operation.button.search"/></button>
					
						<button name="add" id="add" type="button" onclick="linkmanEventAdd()"><ng:locale key="operation.button.add"/></button>

					</td>
					</tr>
            </table>
        </form>
	<div id="kkk"></div>
 	<div class="mt">	
		<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >	
			<tbody class="list_tbody_header">
				<tr>
					<td><ng:locale key ="linkmanEvent.title"/></td>
					<td><ng:locale key ="linkmanEvent.eventType"/></td>
					<td>会员编号</td>
					<td>会员姓名</td>
					<td>发生时间</td>
					<td><ng:locale key ="sysOperationLog.moduleName"/></td>
				</tr>
			</tbody>
			<tbody class="list_tbody_row">
				<c:forEach items="${linkmanEventList}" var="linkmanEventOb">
					<tr class="bg-c gry3">
					 	<td>${linkmanEventOb.title}</td>
					 	<td>
						   
							<ng:code listCode="linkmanevent.type" value="${linkmanEventOb.event_Type}"/>
						   
					   </td>
					   <td>${linkmanEventOb.m_Code }</td>
					   <td>${linkmanEventOb.m_Name }</td>
					  
					   
					   <td>
					   <fmt:formatDate value="${linkmanEventOb.time }" pattern="yyyy-MM-dd"/></td>
					   <td>
							<img src="images/search.gif" onclick="window.location.href='linkmanEventQueryDetail?id=${linkmanEventOb.id }';" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
							&nbsp;&nbsp;
							<img src="images/pencil.gif" onclick="window.location.href='linkmanEventform.html?id=${linkmanEventOb.id }&linkmanEventFunction=linkmanEventUpdate';" alt="<ng:locale key="operation.button.edit"/>"/>
							&nbsp;&nbsp;
							<img src="images/delete.gif" onclick="linkmanEventDelete(${linkmanEventOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
					   </td>
				   </tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	${page.pageInfo }
	</div>
</body>
