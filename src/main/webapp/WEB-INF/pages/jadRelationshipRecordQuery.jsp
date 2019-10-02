<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title>客户管理 － 拜访记录查询</title>-->
</head>
<!-- 日历样式 -->
<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
<script>
		//在查询过程中查询结果部分显示的提示语
		function loading(){
			var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
			str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
			document.getElementById("kkk").innerHTML=str;
		}
        //添加的方法
        function relationshipRecordAdd(){
            window.location.href="jadRelationshipRecordAdd";
        }
        
        //删除的方法
        function relationshipRecordDelete(id){
            if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
                 window.location.href="jadRelationshipRecordDelete.html?id="+id;
            }
        }
   
</script>
<img style="display:none" src="images/indicator_smallwaitanim.gif" alt=Loading  align=absmiddle/>
<body>
    <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="relatioshipRecord"/></h3>
		</div>	
        <form action="jadRelationshipRecordQuery" method="get" name="relationshipQueryList" id="relationshipQueryId">
            <table class="search_table mt">
	            <tr>
	                <td><ng:locale key="relationshipRecord.contact"/>：</td>
	                <td><input name="name" type="text" value="${param.name }"/></td>
	                <td><ng:locale key="relationshipRecord.contactType"/>：</td>
	                <td><ng:list listCode="relationshiprecord.type" name="contactType" value="${param.contactType}" defaultValue=""/></td>
	                <td><ng:locale key="relationshipRecord.subject"/>：</td>
	                <td><input name="subject" type="text" value="${param.subject}"/></td>
	            </tr>
	            <tr>
	               	<td>联系开始时间：</td>
	                <td>
	                     <input name="contactTimeBegin" id="contactTimeBegin" type="text" value="${param.contactTimeBegin }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	                </td>
	                <td>联系结束时间：</td>
	                <td>
	                	<input name="contactTimeEnd" id="contactTimeEnd" type="text" value="${param.contactTimeEnd }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	                </td>
	                <td colspan="2">
	                    <button id="search" type="submit" onclick='loading();'><ng:locale key="operation.button.search"/></button>
	                	<button id="add" type="button" onclick="relationshipRecordAdd()" style="margin-left:10px;"><ng:locale key="operation.button.add"/></button>
	                </td>
	            </tr>
            </table>
        </form>
   <div id="kkk"></div>
   <div class="mt">
        <table id="RelationshipRecordQueryId" class="prod mt">
    <tbody class="list_tbody_header">
         <tr>
             <td><ng:locale key ="relationshipRecord.subject"/></td>
             <td><ng:locale key ="relationshipRecord.contactTime"/></td>
             <td><ng:locale key ="relationshipRecord.contactType"/></td>
             <td width="12%"><ng:locale key ="sysOperationLog.moduleName"/></td>
         </tr>
     </tbody>
  <tbody class="list_tbody_row"> 
     <c:forEach items="${relationshipRecordList}" var="relationshipRecordOb" >
     <tr class="bg-c gry3">
           <td>${relationshipRecordOb.subject }</td>
           <td nowrap="nowrap">
              <fmt:formatDate value="${relationshipRecordOb.contact_Time }" pattern="yyyy-MM-dd"/>
           </td>
           <td nowrap="nowrap">
              <c:if test="${relationshipRecordOb.contact_Type=='1'}">
                  <ng:code listCode="relationshiprecord.type" value="1"/>
              </c:if>
              <c:if test="${relationshipRecordOb.contact_Type=='2'}">
                  <ng:code listCode="relationshiprecord.type" value="2"/>
              </c:if>
              <c:if test="${relationshipRecordOb.contact_Type=='3'}">
                  <ng:code listCode="relationshiprecord.type" value="3"/>
              </c:if>
              <c:if test="${relationshipRecordOb.contact_Type=='4'}">
                  <ng:code listCode="relationshiprecord.type" value="4"/>
              </c:if>
              <c:if test="${relationshipRecordOb.contact_Type=='5'}">
                  <ng:code listCode="relationshiprecord.type" value="5"/>
              </c:if>
           </td>
           <td nowrap="nowrap">
                <img src="images/pencil.gif" style="cursor:pointer;" onclick="window.location.href='jadRelationshipRecordUpdate.html?id=${relationshipRecordOb.id }';" alt="<ng:locale key="operation.button.edit"/>"/>   
                &nbsp;&nbsp;
                <img src="images/delete.gif" style="cursor:pointer;" onclick="relationshipRecordDelete(${relationshipRecordOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
           </td>
           </tr>    
     </c:forEach>
  </tbody>
</table>
</div>
${page.pageInfo }
</div>
</body>
