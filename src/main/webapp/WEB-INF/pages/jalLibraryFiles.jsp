<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<head>
    <meta name="menu" content="JalLibraryFileMenu"/>
    <link rel="stylesheet" href="${ctx}/css/style.css" />
    <style>
        .folderLs { overflow: hidden; margin-left:20%;}
        .folderLs li { float: left; width: 122px; margin: 0 40px 20px;}
        .folderLs li dd { text-align: center; height: 34px; line-height: 15px;font-size: 12px;}
        .folderLs li dd a { margin: 0 auto; font-size: 12px;}
        .downAll { width: 160px; height: 40px; display: block; margin:0 auto; color: #FFF; font-size: 16px; line-height:40px;}
        .downAll span{ margin-top:-8px; vertical-align: middle;}
        .mg_left{margin-left:-170px;}
    </style>
</head>



<div class="cont mg_left">
		<div class="bt mt">
			<h3 class="color2 ml">${jalLibraryColumn.columnName }</h3>
		</div>	
	<div class="mt">
			<%-- <h2 class="title mgb20">${jalLibraryColumn.columnName }</h2> --%>
            <ul class="folderLs">
            	<c:forEach items="${jalLibraryFileList}" var="jalLibraryFile" varStatus="var"> 
            		<li>
	                    <dl title="${jalLibraryFile.fileName }">
	                        <dt><input type="hidden" name="tempUrls" id="tempUrls" value="${httpUrl}/${jalLibraryFile.fileUrl}"/><img src="images/icon_folder.gif" /></dt>
	                        <dd>
	                        	<c:choose>
									<c:when test="${fns:isAbbreviate(jalLibraryFile.fileName, 34)}">
										${fns:abbreviate(jalLibraryFile.fileName, 34,'...')}
									</c:when>
									<c:otherwise>
										${jalLibraryFile.fileName }
									</c:otherwise>
								</c:choose>
	                        </dd>
	                        <dd><button type="button" onclick="window.location.href='${httpUrl}/${jalLibraryFile.fileUrl}'" class="btn btn-default"><span class="glyphicon glyphicon-arrow-down">&nbsp;</span>点击下载</button></dd>
	                    </dl>
	                </li>
            	</c:forEach>

            </ul>
            
            <c:if test="${not empty jalLibraryFileList}">
            	<button type="button" onclick="toDownAll();" class="downAll btn-primary" ><span class="glyphicon glyphicon-download ft24">&nbsp;</span>全部下载</button>
			</c:if>
	    </div>
</div>
<script type="text/javascript">

function toDownAll(){
	
	var temp_urls = document.getElementsByName("tempUrls");
	
	for ( var i=0 ; i < temp_urls.length ; ++i ) {
		
		//alert(temp_urls[i].value);
		window.open(temp_urls[i].value);
	}
}

</script>