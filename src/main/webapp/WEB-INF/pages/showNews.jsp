<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
        <div class="indexMainC fl">
	      	<ul class="backLog mgb20">
            	<c:forEach items="${amNews }" var="new">
	      			<li><a href="${new.url }" target="_blank" class="fl ft12">${new.subject }</a><span class="fr ft12">${new.createTime }</span></li>
	      		</c:forEach>
            </ul>
      	</div>
	</div>