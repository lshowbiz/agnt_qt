<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmWineTemplatePictureList.title"/></title>
    <meta name="menu" content="JpmWineTemplatePictureMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jpmWineTemplatePictureList.heading"/></h2>

    <form method="get" action="${ctx}/jpmWineTemplatePictures" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jpmWineTemplatePictureList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jpmWineTemplatePictureform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jpmWineTemplatePictureList" class="table table-condensed table-striped table-hover" requestURI="" id="jpmWineTemplatePictureList" export="true" pagesize="25">
    <display:column property="idf" sortable="true" href="jpmWineTemplatePictureform" media="html"
        paramId="idf" paramProperty="idf" titleKey="jpmWineTemplatePicture.idf"/>
    <display:column property="idf" media="csv excel xml pdf" titleKey="jpmWineTemplatePicture.idf"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="jpmWineTemplatePicture.createTime">
         <fmt:formatDate value="${jpmWineTemplatePictureList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="isInvalid" sortable="true" titleKey="jpmWineTemplatePicture.isInvalid"/>
    <display:column property="pictureExt" sortable="true" titleKey="jpmWineTemplatePicture.pictureExt"/>
    <display:column property="pictureName" sortable="true" titleKey="jpmWineTemplatePicture.pictureName"/>
    <display:column property="picturePath" sortable="true" titleKey="jpmWineTemplatePicture.picturePath"/>
    <display:column property="pictureSize" sortable="true" titleKey="jpmWineTemplatePicture.pictureSize"/>
    <display:column property="subName" sortable="true" titleKey="jpmWineTemplatePicture.subName"/>
    <display:column property="subNo" sortable="true" titleKey="jpmWineTemplatePicture.subNo"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jpmWineTemplatePictureList.jpmWineTemplatePicture"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jpmWineTemplatePictureList.jpmWineTemplatePictures"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jpmWineTemplatePictureList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jpmWineTemplatePictureList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jpmWineTemplatePictureList.title"/>.pdf</display:setProperty>
</display:table>
