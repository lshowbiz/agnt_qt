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
   	    if(""=="<%=request.getAttribute("maintainId")%>" || "null"=="<%=request.getAttribute("maintainId")%>"){
   	         var linkmanMaintainFunctionAdd = "linkmanMaintainAdd";
   	         window.location.href = "linkmanMaintainform.html?linkmanId="+id+"&linkmanMaintainFunction="+linkmanMaintainFunctionAdd;
   	    }
   	    //编辑客户维护
   	    else{
   	        var maintainId="<%=request.getAttribute("maintainId")%>";
   	        var linkmanMaintainFunctionUpdate = "linkmanMaintainUpdate";
   	        window.location.href = "linkmanMaintainform.html?linkmanId="+id+"&id="+maintainId+"&linkmanMaintainFunction="+linkmanMaintainFunctionUpdate;
   	    }
   }
   
</script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
    <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="linkmanMaintain.maintain"/></h3>
		</div>
        <form action="jadLinkmanQuery" method="get" name="jadLinkmanQuery" id="jadLinkmanQuery">
            <table class="search_table mt">
                       <input name="strAction" id="strAction" value="linkmanMaintainSelect" style="display:none"/>
                       <input name="maintainId" id="maintainId" value="${param.maintainId }" style="display:none"/>
				<tbody>
					<tr>
						<td><ng:locale key="linkman.name"/>：</td>
						<td><input type="text" name="name" value="${param.name }" maxlength="100"/></td>
						<td><ng:locale key="list.sex"/>：</td>
						<td>
							<select name="sex" id="sex" >
								<option value=""><ng:locale key="list.please.select"/></option>
								<option value="M"><ng:code listCode="sex" value="M"/></option>
								<option value="F"><ng:code listCode="sex" value="F"/></option>
							</select>
						</td>
					    <td><ng:locale key="linkmanCategory.name"/>：</td>
						<td colspan="2">
							<select name="lcId" id="lcId">
								<option value=""><ng:locale key="list.please.select"/></option>
							<c:forEach items="${linkmanCategoryList}" var="linkmanCategory">
								<option value="${linkmanCategory.id}">${linkmanCategory.name }</option>
							</c:forEach>
							</select>
						</td>
						<td>
							<button id="search" type="submit" onclick='selectLinkmanQQQ(document.jadLinkmanQuery)'><ng:locale key="operation.button.search"/></button>
						</td>
					</tr>
				</tbody>
            </table>
        </form>
	<div class="mt">
	<div id="kkk"></div>
	<table id="LinkmanQueryId" class="prod mt">
		<tbody class="list_tbody_header">
				<tr>
				    <td><ng:locale key ="sysOperationLog.moduleName"/></td>
					<td><ng:locale key ="linkman.name"/></td>
					<td><ng:locale key ="list.sex"/></td>
					<td><ng:locale key ="linkman.mobilephone"/></td>
					<td><ng:locale key ="linkman.familyPhone"/></td>
					<td><ng:locale key ="linkman.companyPhone"/></td>				
				</tr>
		</tbody>
		<tbody class="list_tbody_row">
			<c:forEach items="${linkmanList}" var="linkmanOb" >
			<tr class="bg-c gry3">
			    <td>
			       <img src="images/bonus_tree/tree_person_pv.gif" style="cursor:pointer;" onclick="linkmanSelect('${linkmanOb.id}','${linkmanOb.name}')" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
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
	</div>
</div>
</body>