<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<div class="cont" >	
		<div class="bt mt">
			<h3 class="color2 ml">会员资料管理</h3>
		</div>	
		<div class="mt">	
			<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >	
				<tbody class="list_tbody_header">
	            	<tr>
		                <td><ng:locale key="miMember.memberNo"/>     </td>
		                <td><ng:locale key="miMember.petName"/>     </td>
		               <%--  <th><ng:locale key="bdSendRecord.cardType"/>     </th> --%>
		                <td><ng:locale key="bdSendRecord.cardType"/>     </td>
		                <td><ng:locale key="miMember.store.type"/>     </td>
		               <%--  <td><ng:locale key="miMember.recommendNo"/>     </td> --%>
		                <td><ng:locale key="miMember.papernumber"/>     </td>
		                <td><ng:locale key="miMember.createTime"/>     </td>
		                <td><ng:locale key="title.operation"/>     </td>
	           		</tr>
        		</tbody>
		        <tbody class="list_tbody_row">	
		            <c:forEach items="${ jmiMembers }" var="jmiMemberVar">
		            <tr class="bg-c gry3">
		                <td>${jmiMemberVar.userCode }</td>
		                <td>${jmiMemberVar.petName }</td>
		               <%--  <td><ng:code listCode="bd.cardtype" value="${jmiMemberVar.cardType }"/></td> --%>
		                <td><ng:code listCode="member.level" value="${jmiMemberVar.memberLevel }"/></td>
		                <td><ng:code listCode="store.type" value="${jmiMemberVar.isstore }"/></td>
		                <%-- <td>${jmiMemberVar.recommendNo }</td> --%>
		                <td>${jmiMemberVar.papernumber }</td>
		                <td>${jmiMemberVar.createTime }</td>
		                <td>
							 <security:authorize url="/app/jmiMembers&strAction=deleteMember">
			                    <a href="jmiMembers?userCode=${jmiMemberVar.userCode}&strAction=deleteMember" onclick="return window.confirm('<ng:locale key="amMessage.confirmdelete"/>');"><img border="0" src="images/delete.gif" alt="<ng:locale key="operation.button.delete"/>" /></a>
			                 </security:authorize>
		                </td>
		            </tr>
		            </c:forEach>
		        </tbody>
    		</table>
	</div>
</div>

