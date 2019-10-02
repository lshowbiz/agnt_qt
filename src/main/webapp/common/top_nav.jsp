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
        <li class="de"><a href="${ctx}/loginform/showuserinfo" style="letter-spacing:1em;">首页</a></li>
        <c:forEach var="menu" items="${topmenus}">
        <li class="de">
            <a <c:if test="${menu['RES_ID'] eq parentId}"> class='current'</c:if> href="<c:url value="${menu['RES_URL']}&parentId=${menu['RES_ID']}"/>">${menu["RES_NAME"]}</a>
        </li>
        </c:forEach>
        <li class="fastMenu rel" style="float:right;">
            <a href="${ctx}/jmiMemberCreateform?1=1&currentSubMenuId=jmiMemberCreateform" class="in">快捷入口&nbsp;<b></b></a>
            <ul class="subNav abs" style="display:none;">
                <li><a href="${ctx}/jfiBankbookJournals?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712821">存折查询</a></li>
                <li><a href="${ctx}/fiBankbookJournals?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712822">基金查询</a></li>
                <li><a href="${ctx}/fiBcoinJournals?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712823">积分查询</a></li>
                <li><a href="${ctx}/jbdMemberLinkCalcHist?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712785">奖金查询</a></li>
                <li><a href="${ctx}/fiTransferAccounts?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712824">会员转账</a></li>
            </ul>
        </li>
        <a href="javascript:history.go(1);" class="abs goforward" title="前进"></a>
        <a href="javascript:history.go(-1)" class="abs goback" title="返回"></a>
    </ul> 
</div>