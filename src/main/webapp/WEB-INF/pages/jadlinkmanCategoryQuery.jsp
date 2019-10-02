<%@ page contentType="text/html; charset=UTF-8" language="java" %> 
<%@ include file="/common/taglibs.jsp"%>  

<head>
<!--<title>客户管理 － 客户分类</title>-->
</head>
<script>
     function loading(){
			var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
			str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
			document.getElementById("kkk").innerHTML=str;
     }
     
     //添加客户分类的方法
     function addLinkmanCategory(){
     　　　　　window.location.href="jadAddLinkmanCategory";
     }
     
     //添加删除的方法
     function linkmanCategoryDelete(id){
          if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
               window.location.href="jadDeleteLinkmanCategory.html?id="+id;
          }
     }
     
     
     
</script>
<body>
  <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">群组设置</h3>
		</div>
    <form action="jadlinkmanCategoryQuery" method="get" name="linkmanCategoryName" id="linkmanCategoryNameId">
       
            <table class="search_table mt">
                <tbody>
                    <tr>
                        <td width="70px"><ng:locale key="linkmanCategory.name"/>：</td>
                        <td width="200px"><input name="name" type="text" value="${param.name }" size="25" maxlength="50"/></td>
                        <td width="100px"><button name="search" id="search" type="submit" onclick='loading();'><ng:locale key="operation.button.search"/></button></td>
                        <td width="100px"><button name="add" id="add" type="button" onclick="addLinkmanCategory()"><ng:locale key="operation.button.add"/></button></td>
                        <td>&nbsp;</td>
                    </tr>
                </tbody>
            </table>
    </form>
 
    <div id="kkk"></div>
<div class="mt">
    <table class="prod mt" id="LinkmanCategoryQueryId" >
        <thead class="list_tbody_header">
          <tr>
            <td><ng:locale key="linkmanCategory.name"/></td>
            <td width="12%"><ng:locale key="sysOperationLog.moduleName"/></td>
          </tr>
        </thead>
        <tbody class="list_tbody_row">
	        <c:forEach items="${linkmanCategoryList}" var="linkmanCategory">
	           <tr class="bg-c gry3">
	                <td>${linkmanCategory.name }</td>
	                <td>
	                    <img src="images/pencil.gif" onclick="window.location.href='jadUpdateLinkmanCategory.html?id=${linkmanCategory.id }';" alt="<ng:locale key="operation.button.edit"/>"/>
	                    &nbsp;&nbsp;
	                    <img src="images/delete.gif" onclick="linkmanCategoryDelete(${linkmanCategory.id })" alt="<ng:locale key="operation.button.delete"/>"/>
	                    &nbsp;&nbsp;
	                </td>
	           </tr>
	        </c:forEach>
	      </tbody>
    </table>
     </div>
	${page.pageInfo }
	</div>
</body>
