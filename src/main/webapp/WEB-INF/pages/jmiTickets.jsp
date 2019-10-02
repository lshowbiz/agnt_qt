<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>







        <h2 class="title mgb20">青奥会门票申请</h2>
        <div class="content fr"> 
 

	<c:if test="${empty jmiTickets }">
		您当前无数据
	</c:if>

	<c:if test="${not empty jmiTickets }">
	 <table width="100%" border="0" class="tabQueryLs" id="tabQueryLs" style="margin-top:2px;">
            <thead>
                <tr>
                    <th>会员编号     </th>
                    <th>门票类型     </th>
                    <th>申请人姓名     </th>
                    <th><ng:locale key="title.operation"/>     </th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${ jmiTickets}" var="jmiTicketVar">
            	
            	<tr>
                    <td>${jmiTicketVar.userCode }</td>
                    <td>
                    <ng:code listCode="tickettype" value="${jmiTicketVar.ticketType }"></ng:code>
                    </td>
                    <td>${jmiTicketVar.userName }</td>
                   <td>
                   <img src="images/pencil.gif" onclick="window.location.href='jmiTicketform?id=${jmiTicketVar.id}&strAction=editJmiTicket';" alt="<ng:locale key="button.edit"/>" style="cursor:pointer"/>
		
                   
                   </td>
                </tr>
            	
            	</c:forEach>
               
                

            </tbody>
        </table>
	</c:if>
       
        </div>
        <div class="clear"></div>