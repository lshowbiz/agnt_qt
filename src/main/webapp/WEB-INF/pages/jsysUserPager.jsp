<%@ include file="/common/taglibs.jsp"%>


<table class="table table-bordered .table-bordered">
<tr>
<td><ng:label key="miMember.bname" /></td>
<td>USER_NAME</td>
<td>COMPANY_CODE</td>
<td>USER_TYPE</td>
<td></td>
</tr>
<c:forEach var="jsysUser" items="${pager.pageList}">
			<tr>
<td>${jsysUser.userCode}</td>
<td>${jsysUser.userName}</td>
<td>${jsysUser.companyCode}</td>
<td>${jsysUser.userType}</td>
<td></td>
			</tr>
</c:forEach>

</table>

${pager}