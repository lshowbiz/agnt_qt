<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title>客户管理 － 客户资料</title>-->
</head>
<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
}
   
   
   //查询后，让性别的下拉框有正确的值显示
   window.onload = function linkmanInit(){
         var sexValue = "<%=request.getAttribute("sexValue")%>";  
         if("M"==sexValue || "F"==sexValue){
               $("#sex").val(sexValue); 
         }
         var lcIdValue = "<%=request.getAttribute("lcIdValue")%>";
         if(""!=lcIdValue){
              $("#lcId").val(lcIdValue);
         } 
         var customerFocusValue = "<%=request.getAttribute("customerFocusValue")%>";
         if(""!=customerFocusValue){
              $("#customerFocus").val(customerFocusValue);
         }     
   }
   
   //设为重点客户
   function keyCcustomers(id){
        if(confirm("<ng:locale key = 'makeYouSureWantToFocusOnCustomer'/>")){
            window.location.href="isOrNotCustomerFocus.html?id="+id+"&customerFocus=Y";
        }
   }
   
   //设为普通客户
   function generalCustomer(id){
        if(confirm("<ng:locale key = 'makeSureToOrdinaryCustomers'/>")){
             window.location.href="isOrNotCustomerFocus.html?id="+id+"&customerFocus=N";
        }
   }
   
   //新增客户
   function linkmanAdd(){
       window.location.href = "jadLinkmanAdd";
   }
   
   //删除的方法
   function linkmanDelete(id){
       if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
           window.location.href="jadLinkmanDelete.html?id="+id;
       }
   }
   //查询的方法
   function selectLinkmanQQQ(theForm){
        theForm.submit();
   }
   
    //选择联系人
   function linkmanSelect(linkmanId,linkmanName){
   	    var id = linkmanId;	
   	    //新增客户维护
   	    if(""=="<%=request.getAttribute("eventId")%>" || "null"=="<%=request.getAttribute("eventId")%>"){
   	         var linkmanEventFunction = "linkmanEventAdd";
   	         window.location.href = "linkmanEventform.html?linkmanId="+id+"&linkmanEventFunction="+linkmanEventFunction;
   	    }
   	    //编辑客户维护
   	    else{
   	        var eventId="<%=request.getAttribute("eventId")%>";
   	        var linkmanEventFunction = "linkmanEventUpdate";
   	        window.location.href = "linkmanEventform.html?linkmanId="+id+"&id="+eventId+"&linkmanEventFunction="+linkmanEventFunction;
   	    }
   }
   
</script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
    <h2 class="title mgb20"><ng:locale key="linkmanEvent.event"/></h2>
    <div class="condition">
        <form action="jadLinkmanQuery" method="get" name="jadLinkmanQuery" id="jadLinkmanQuery">
            <table width="100%" class="personalInfoTab">
                       <input name="strAction" id="strAction" value="linkmanEventSelect" style="display:none"/>
                       <input name="maintainId" id="maintainId" value="${param.maintainId }" style="display:none"/>
				<tbody>
					<tr>
						<td><ng:locale key="linkman.name"/>：</td>
						<td><input type="text" name="name" value="${param.name }" size="20" maxlength="100"/></td>
						<td><ng:locale key="list.sex"/>：</td>
						<td>
							<select name="sex" id="sex" class="mySelect" >
								<option value=""><ng:locale key="list.please.select"/></option>
								<option value="M"><ng:code listCode="sex" value="M"/></option>
								<option value="F"><ng:code listCode="sex" value="F"/></option>
							</select>
						</td>
					    <td><ng:locale key="linkmanCategory.name"/>：</td>
						<td colspan="2">
							<select name="lcId" id="lcId" class="mySelect">
								<option value=""><ng:locale key="list.please.select"/></option>
							<c:forEach items="${linkmanCategoryList}" var="linkmanCategory">
								<option value="${linkmanCategory.id}">${linkmanCategory.name }</option>
							</c:forEach>
							</select>
						</td>
						<td>
							<input id="search" type="submit" style="margin:5px 10px 5px 0;" onclick='selectLinkmanQQQ(document.jadLinkmanQuery)' class="btn_common btn_mini corner2 fl" value="<ng:locale key="operation.button.search"/>"/>
						</td>
					</tr>
				</tbody>
            </table>
        </form>
    </div>

	<div id="kkk"></div>
	<table width="100%" border="0" class="tabQueryLs" id="LinkmanQueryId" style="margin-top:2px;">
		<thead>
				<tr>
				    <th><ng:locale key ="sysOperationLog.moduleName"/></th>
					<th><ng:locale key ="linkman.name"/></th>
					<th><ng:locale key ="list.sex"/></th>
					<th><ng:locale key ="linkman.mobilephone"/></th>
					<th><ng:locale key ="linkman.familyPhone"/></th>
					<th><ng:locale key ="linkman.companyPhone"/></th>				
				</tr>
			</thead>
		<tbody>
			<c:forEach items="${linkmanList}" var="linkmanOb" >
			<tr>
			    <td>
			       <a href="#" onclick="linkmanSelect('${linkmanOb.id}','${linkmanOb.name}')" title="选择"><img src="images/treeimages/xz.png" style="cursor:pointer;" />
			    </td>
				<td>${linkmanOb.name }</td>
				<td>
				<c:if test="${linkmanOb.sex=='M'}">
				  <ng:code listCode="sex" value="M"/>
				</c:if>
				<c:if test="${linkmanOb.sex=='F'}">
				  <ng:code listCode="sex" value="F"/>
				</c:if>
				</td>
				<td>${linkmanOb.mobilephone }</td>
				<td>${linkmanOb.family_Phone }</td>
				<td>${linkmanOb.company_Phone }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	${page.pageInfo }
</body>