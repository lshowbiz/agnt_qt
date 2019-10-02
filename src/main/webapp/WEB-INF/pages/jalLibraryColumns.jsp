<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<head>
    <meta name="menu" content="JalLibraryColumnMenu"/>
   	<link rel="stylesheet" href="${ctx}/css/style.css"/>
    <style>
        .galleryBox { width: 100%; overflow: hidden; }
        .galleryBox > a { display: block; width: 27px; height: 44px;}
        .galleryBox > a.prev { left: 0; top: 140px; background: url("${ctx}/images/arr_left.gif") left center no-repeat;}
        .galleryBox > a.next { right: 0; top: 140px; background: url("${ctx}/images/arr_right.gif") left center no-repeat;}
        .gallery { overflow: hidden; }
        .frame { float: left; width: 100%; margin:0 23px; overflow: hidden; text-align:center}
        .frame ul li { float: left; margin: 10px 16px 30px;}
        .category { width:100%; padding-left:25%; overflow: hidden;  border-bottom: 1px solid #cf3638; }
        .category li { float: left; margin-left:10px}
        .category li a {color: #666666;
	 display: block;
    width: 103px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    border: 1px solid #C7C7C7;
    border-bottom: none;}
.category li a.current {color: #fff;
    border: 1px solid #cf3638;
    border-bottom: none;}
    
    .mg_left{margin-left:-170px;}
    </style>
</head>
<div class="cont mg_left">
	<div class="bt mt20">
			<h3 class="color2 ml">资料中心</h3>
	</div>

<form method="get" action="${ctx}/jalLibraryColumns" id="searchForm" class="form-search">

		<ul class="category tc" id="category">
	<c:forEach items="${jalLibraryPlates}" var="jalLibraryPlate" varStatus="status">
		<c:if test="${status.index==0}">
			<li><a href="javascript:void(0);" attr="#galleryBox-<c:out value="${status.index+1}"/>" class="current">${jalLibraryPlate.plateName }</a></li>
		</c:if>
		<c:if test="${status.index!=0 && jalLibraryPlate.plateIndex!=5}">
			<li><a href="javascript:void(0);" attr="#galleryBox-<c:out value="${status.index+1}"/>">${jalLibraryPlate.plateName }</a></li>
		</c:if>
		
	</c:forEach>
	</ul>

	<div align="center">
	<div class="galleryBox rel" id="galleryBox-1">
		<%-- <div class="gallery">
		<%
			List list1=(List)request.getAttribute("jalLibraryColumnList1");
			int listSize=list1.size()/8;
			int listLastNum=list1.size()%8;
			for(int i=0;i<listSize;i++){
				%>
				<div class="frame">
					<ul>
						<c:forEach items="${jalLibraryColumnList1}" var="jalLibraryColumn" begin="<%=i*8 %>" end="<%=(i+1)*8 %>" varStatus="status">
							<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
						</c:forEach>
				</ul>
				</div>
				<%
			}
			if(listLastNum>0){
				%>
				<div class="frame">
					  <ul>
						<c:forEach items="${jalLibraryColumnList1}" var="jalLibraryColumn" begin="<%=listSize*8 %>" end="<%=listSize*8+listLastNum %>" varStatus="status">
									<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>	
						</c:forEach>
					</ul>
				 </div>
				<%
			}
		 %>
		</div>
		<a href="javascript:void(0);" class="prev abs"></a>
		<a href="javascript:void(0);" class="next abs"></a> --%>
		<div class="gallery">
			<div class="frame">
				<ul>
					<c:forEach items="${jalLibraryColumnList1}" var="jalLibraryColumn" varStatus="status">
						<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<div class="galleryBox rel" id="galleryBox-2" style="display: none;">
		<%-- <div class="gallery">
		<%
			List list2=(List)request.getAttribute("jalLibraryColumnList2");
			int listSize2=list2.size()/8;
			int listLastNum2=list2.size()%8;
			for(int i=0;i<listSize2;i++){
				%>
				<div class="frame">
					<ul>
						<c:forEach items="${jalLibraryColumnList2}" var="jalLibraryColumn" begin="<%=i*8 %>" end="<%=(i+1)*8 %>" varStatus="status">
							<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
						</c:forEach>
				</ul>
				</div>
				<%
			}
			if(listLastNum2>0){
				%>
				<div class="frame">
					  <ul>
						<c:forEach items="${jalLibraryColumnList2}" var="jalLibraryColumn" begin="<%=listSize2*8 %>" end="<%=listSize2*8+listLastNum2 %>" varStatus="status">
									<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>	
						</c:forEach>
					</ul>
				 </div>
				<%
			}
		 %>
		</div>
		<a href="javascript:void(0);" class="prev abs"></a>
		<a href="javascript:void(0);" class="next abs"></a> --%>
		<div class="gallery">
			<div class="frame">
				<ul>
					<c:forEach items="${jalLibraryColumnList2}" var="jalLibraryColumn" varStatus="status">
						<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<div class="galleryBox rel" id="galleryBox-3" style="display: none;">
		<%-- <div class="gallery">
		<%
			List list3=(List)request.getAttribute("jalLibraryColumnList3");
			int listSize3=list3.size()/8;
			int listLastNum3=list3.size()%8;
			for(int i=0;i<listSize3;i++){
				%>
				<div class="frame">
					<ul>
						<c:forEach items="${jalLibraryColumnList3}" var="jalLibraryColumn" begin="<%=i*8 %>" end="<%=(i+1)*8 %>" varStatus="status">
							<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
						</c:forEach>
				</ul>
				</div>
				<%
			}
			if(listLastNum3>0){
				%>
				<div class="frame">
					  <ul>
						<c:forEach items="${jalLibraryColumnList3}" var="jalLibraryColumn" begin="<%=listSize3*8 %>" end="<%=listSize3*8+listLastNum3 %>" varStatus="status">
									<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>	
						</c:forEach>
					</ul>
				 </div>
				<%
			}
		 %>
		</div>
		<a href="javascript:void(0);" class="prev abs"></a>
		<a href="javascript:void(0);" class="next abs"></a> --%>
		<div class="gallery">
			<div class="frame">
				<ul>
					<c:forEach items="${jalLibraryColumnList3}" var="jalLibraryColumn" varStatus="status">
						<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<div class="galleryBox rel" id="galleryBox-4" style="display: none;">
		<%-- <div class="gallery">
		<%
			List list4=(List)request.getAttribute("jalLibraryColumnList4");
			int listSize4=list4.size()/8;
			int listLastNum4=list4.size()%8;
			for(int i=0;i<listSize4;i++){
				%>
				<div class="frame">
					<ul>
						<c:forEach items="${jalLibraryColumnList4}" var="jalLibraryColumn" begin="<%=i*8 %>" end="<%=(i+1)*8 %>" varStatus="status">
							<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
						</c:forEach>
				</ul>
				</div>
				<%
			}
			if(listLastNum4>0){
				%>
				<div class="frame">
					  <ul>
						<c:forEach items="${jalLibraryColumnList4}" var="jalLibraryColumn" begin="<%=listSize4*8 %>" end="<%=listSize4*8+listLastNum4 %>" varStatus="status">
									<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>	
						</c:forEach>
					</ul>
				 </div>
				<%
			}
		 %>
		</div>
		<a href="javascript:void(0);" class="prev abs"></a>
		<a href="javascript:void(0);" class="next abs"></a> --%>
		<div class="gallery">
			<div class="frame">
				<ul>
					<c:forEach items="${jalLibraryColumnList4}" var="jalLibraryColumn" varStatus="status">
						<li class="shadow"><a href="${pageContext.request.contextPath}/jalLibraryFiles?1=1&columnId=${jalLibraryColumn.columnId}"><img src="${fileUrl }/${jalLibraryColumn.columnImgs}" width="165" height="132" alt="${jalLibraryColumn.columnName}"/></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	</div>
<div class="clear"></div>
</div>

	
<script>
    	

        $(function(){
            function init(attr){

                $gallery    =   $(attr).find('.gallery');
                $frame      =   $gallery.find('.frame');
                $showLS     =   $frame.find('li');
                frameN      =   $frame.length;
                p           =   0;
                //  var frameW      =   $frame.outerWidth(true);  // freme的实际宽度：834
                //$gallery.width( Math.ceil( $showLS.length / 8 ) * 834 );
            }

            function slide(sel,i){
                $(sel).siblings('.gallery').animate({
                    marginLeft  :   '-' + (i * 834 + 'px')
                },500);
            }
            var $gallery,
                $frame,
                $showLS,
                frameN,
                p;

            init('#galleryBox-1');

            $(".galleryBox").coffee({
                'click':{
                    '.prev':function(){
                    
                        if( p > 0 ){
                            p--;
                            slide(this,p);
                        }else{
                            return false;
                        }
                    },
                    '.next':function(){
                        if( p < frameN - 1 ){
                            p++;
                            slide(this,p);
                        }else{
                            return false;
                        }
                    }
                }
            });

            $('#category').coffee({
                'click':{
                    'a':function(){
                        var $this   =   $(this);
                        var vAttr   =   $this.attr('attr');

                        $this.addClass('current').parent().siblings().find('a').removeClass('current');
                        $(vAttr).show().siblings('.galleryBox').hide();
                        init(vAttr);
                    }
                }

            });

        });

    </script>
</form>