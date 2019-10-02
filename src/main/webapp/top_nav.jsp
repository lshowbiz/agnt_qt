<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="java.util.*"%>
<%
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");

  String userCode = request.getParameter("userCode");


 List topmenus = jsysResourceManager.getTopMenuByUserCode(userCode);
 session.setAttribute("topmenus", topmenus);
%>
<div class="navBox">

    <ul class="nav rel centerDiv clearfix" id="js_nav">
        <li><a href="${ctx}/loginform/showuserinfo" style="letter-spacing:1em;">首页</a></li>
        <c:forEach var="menu" items="${topmenus}">
        <li>
            <a <c:if test="${menu['RES_ID'] eq parentId}"> class='current'</c:if> href="<c:url value="${menu['RES_URL']}&parentId=${menu['RES_ID']}"/>">${menu["RES_NAME"]}</a>
        </li>
        </c:forEach>
        <!--
        <li><a href="<c:url value="/loginform/showuserinfo?j_username=CN17429969&j_password=1"/>">My Home</a></li>
        <li><a href="<c:url value="/announce/showAnnounce"/>">Announce</a></li>
        <li><a href="${ctx}/amMessages/showMessage"/>Message</a></li>
         --> 
        <li class="fastMenu rel" style="float:right;">
            <a href="${ctx}/jmiMemberCreateform?1=1&currentSubMenuId=jmiMemberCreateform">快捷入口&nbsp;<b></b></a>
            <ul class="subNav abs" style="display:none;">
                <li><a href="${ctx}/jfiBankbookJournals?1=1&currentSubMenuId=jfiBankbookJournals">存折查询</a></li>
                <li><a href="${ctx}/fiBankbookJournals?1=1&currentSubMenuId=fiBankbookJournals">基金查询</a></li>
                <li><a href="${ctx}/fiBcoinJournals?1=1&currentSubMenuId=fiBcoinJournals">积分查询</a></li>
                <li><a href="${ctx}/jbdMemberLinkCalcHist?1=1&currentSubMenuId=jbdMemberLinkCalcHist">奖金查询</a></li>
                <li><a href="${ctx}/fiTransferAccounts?1=1&currentSubMenuId=fiTransferAccounts">会员转账</a></li>
            </ul>
        </li>
        <a href="javascript:history.go(1);" class="abs goforward" title="前进"></a>
        <a href="javascript:history.go(-1)" class="abs goback" title="返回"></a>
    </ul>
</div>