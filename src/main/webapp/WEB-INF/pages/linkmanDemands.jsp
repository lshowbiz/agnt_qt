<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title>客户管理 － 客户需求</title>-->
</head>
<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
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
   function linkmanDemandAdd(){
       window.location.href="linkmanDemandform.html?demandFunction=demandAdd";
   }
   
   //删除
   function linkmanDemandDelete(id){
       if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
           window.location.href="linkmanDemandDelete.html?id="+id;
       }
   }
   
  </script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
    <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="linkmanDemand.customerDemand"/></h3>
		</div>
        <form action="linkmanDemandQuery" method="get" name="linkmanDemandFormQuery" id="linkmanDemandFormQuery">
            <table class="search_table mt">
				<tr>
					<td>开始时间：</td>
					<td>
						 <input name="registerTimeBegin" id="registerTimeBegin" type="text" value="${registerTimeBegin }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					<td>结束时间：</td>
					<td>
						<input name="registerTimeEnd" id="registerTimeEnd" type="text" value="${registerTimeEnd }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					<td><ng:locale key="linkman.name"/>：</td>
					<td><input type="text" name="name" value="${name }" maxlength="100"/></td>
					<td colspan="2">
						<button name="search" id="search" type="button" onclick="checkApplication(document.linkmanDemandFormQuery)"><ng:locale key="operation.button.search"/></button>
						<button name="add" id="add" type="button" onclick="linkmanDemandAdd()"><ng:locale key="operation.button.add"/></button>
					</td>
				</tr>
            </table>
        </form>

	<div id="kkk"></div>
	<div class="mt">
	<table id="LinkmanQueryId" class="prod mt">
		<tbody class="list_tbody_header">
			<tr>
				<td><ng:locale key ="linkman.name"/></td>
				<td><ng:locale key ="linkmanDemand.registerTime"/></td>
				<td width="12%"><ng:locale key ="sysOperationLog.moduleName"/></td>
			</tr>
		</tbody>
		<tbody class="list_tbody_row">
		 <c:forEach items="${linkmanDemandList}" var="linkmanDemandOb" >
			<tr class="bg-c gry3">
				<td>${linkmanDemandOb.name }</td>
				<td>${linkmanDemandOb.register_Time }</td>
				<td>
					<img src="images/search.gif" style="cursor:pointer;" onclick="window.location.href='linkmanDemandDetail.html?id=${linkmanDemandOb.id }';" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
				&nbsp;&nbsp;
					<img src="images/pencil.gif" style="cursor:pointer;" onclick="window.location.href='linkmanDemandform.html?id=${linkmanDemandOb.id }&demandFunction=demandUpdate';" alt="<ng:locale key="operation.button.edit"/>"/>
				&nbsp;&nbsp;
					<img src="images/delete.gif" style="cursor:pointer;" onclick="linkmanDemandDelete(${linkmanDemandOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
				</td>
			</tr>
		 </c:forEach>
		</tbody>
	</table>
	</div>
	${page.pageInfo }
	</div>
</body>
