<%@ include file="/common/taglibs.jsp" %>
<head>
</head>
<%
 String parentId = request.getParameter("parentId");
 session.setAttribute("parentId", parentId);
%>
