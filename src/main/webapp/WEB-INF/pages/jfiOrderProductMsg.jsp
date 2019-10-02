<%@ include file="/common/taglibs.jsp"%>
<script>
alert('${isOver }');
<c:if test="${empty url}">
history.go(-1);
</c:if>
<c:if test="${not empty url}">
window.location = "${url}";
</c:if>
</script>

