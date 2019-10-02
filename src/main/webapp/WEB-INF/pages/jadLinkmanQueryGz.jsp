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
            window.location.href="isOrNotCustomerFocus.html?id="+id+"&customerFocusss=Y";
        }
   }
   
   //设为普通客户
   function generalCustomer(id){
        if(confirm("<ng:locale key = 'makeSureToOrdinaryCustomers'/>")){
             window.location.href="isOrNotCustomerFocus.html?id="+id+"&customerFocusss=N";
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
   
   
   
</script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
     <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">客户跟踪</h3>
		</div>	
        <form action="jadLinkmanQueryGz" method="get" name="jadLinkmanQuery" id="jadLinkmanQuery">
            <table class="search_table mt">
				<tr>
					<td><ng:locale key="linkman.name"/>：</td>
					<td><input type="text" name="name" value="${param.name }" maxlength="100"/></td>
					<td><ng:locale key="linkman.mobilephone"/>：</td>
					<td><input name="mobilephone" type="text" value="${param.mobilephone }" maxlength="20" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" /></td>
					<td>客户群组：</td>
					<td>
						<select name="lcId" id="lcId">
							<option value=""><ng:locale key="list.please.select"/></option>
						<c:forEach items="${linkmanCategoryList}" var="linkmanCategory">
							<option value="${linkmanCategory.id}">${linkmanCategory.name }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>特别关注：</td>
					<td>
						<select name="customerFocus" id="customerFocus">
							<option value=""><ng:locale key="list.please.select"/></option>
							<option value="Y"><ng:code listCode="point.client" value="Y"/></option>
							<option value="N"><ng:code listCode="point.client" value="N"/></option>
						</select>
					</td>
					<td colspan="2">
						<button id="search" type="submit" onclick='loading();'><ng:locale key="operation.button.search"/></button>
					</td>
				</tr>
            </table>
        </form>

	<div id="kkk"></div>
	<div class="mt">
	<table id="LinkmanQueryId" class="prod mt">
		<tbody class="list_tbody_header">
				<tr>
					<td>客户姓名</td>
					<td>客户类型</td>
					<td>客户分组</td>
					<td>客户来源</td>
					<td>手机号码</td>
					<td><ng:locale key ="linkman.familyPhone"/></td>
					<td><ng:locale key ="linkman.companyPhone"/></td>
					<td><ng:locale key ="sysOperationLog.moduleName"/></td>
				</tr>
			</tbody>
		<tbody class="list_tbody_row">
			<c:forEach items="${linkmanList}" var="linkmanOb" >
			<tr class="bg-c gry3">
				<td>${linkmanOb.name }</td>
				<td>
					<ng:code listCode="linkman.status" value="${linkmanOb.linkman_status}"/>
				</td>
				<td>
					${linkmanOb.q_name }
				</td>
				<td>
					<ng:code listCode="linkman.customersource" value="${linkmanOb.customer_source}"/>
				</td>
				
				<td>${linkmanOb.mobilephone }</td>
				<td>${linkmanOb.family_Phone }</td>
				<td>${linkmanOb.company_Phone }</td>
				<td>

					<a href="#" title="跟踪" onclick="window.location.href='jadLinkmanDetailQueryGz?id=${linkmanOb.id }';">
					<img src="images/user.gif" style="cursor:pointer;"/>
					</a>&nbsp;
					
					<c:if test="${linkmanOb.customer_focus=='Y' }">
						<a href="#" onclick="generalCustomer(${linkmanOb.id })" title="特别关注"><img src="images/treeimages/tb.gif" style="cursor:pointer;"/></a>
					</c:if>
					<c:if test="${linkmanOb.customer_focus!='Y' }">
						<a href="#" onclick="keyCcustomers(${linkmanOb.id })" title="普通关注"><img src="images/treeimages/pt.gif" style="cursor:pointer;"/></a>
					</c:if>
					<!--
					<a href="#" onclick="keyCcustomers(${linkmanOb.id })" title="设为特别关注"><img src="images/treeimages/gz.jpg" style="cursor:pointer;"/></a>
					&nbsp;
					<a href="#" onclick="generalCustomer(${linkmanOb.id })" title="取消关注"><img src="images/treeimages/nogz.jpg" style="cursor:pointer;" /></a>
					&nbsp;
					
					<img src="images/delete.gif" style="cursor:pointer;" onclick="linkmanDelete(${linkmanOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
				-->
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	${page.pageInfo }
	</div>
</body>
