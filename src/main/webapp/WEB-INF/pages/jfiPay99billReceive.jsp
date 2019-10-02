<%@ include file="/common/taglibs.jsp"%>
<c:if test="${not empty showUrl }">
<result>1</result><redirecturl>${showUrl }</redirecturl>
</c:if>
<c:if test="${empty showUrl }">
${returnMsg }
</c:if>
