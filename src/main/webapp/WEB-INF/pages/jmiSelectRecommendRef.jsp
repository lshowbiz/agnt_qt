<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/style-ng.css" />
<style>
body {
	background: url(images/bkgd.png) left top repeat;
}

table {
	border-collapse: separate;
}

.tr {
	text-align: right !important;
}

.tl {
	text-align: left !important;
}

.netBox {
	padding-top: 10px;
}

.netBox .line {
	height: 20px;
	width: 4px;
}

.netBox .down {
	background-color: #FE9A3C;
	margin: 0px auto;
}

.netBox .top {
	border-top: 3px solid #FE9A3C;
}

.netBox .left {
	border-right: 2px solid #FE9A3C;
}

.netBox .right {
	border-left: 2px solid #FE9A3C;
}

.netBox td {
	text-align: center;
	vertical-align: top;
	padding: 0;
	height: 17px;
}

.netBox .node {
	position: relative;
	display: inline-block;
	width: 96px;
	margin: 0 2px;
	border: 2px solid white;
	color: #FFF;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	background-color: #58ACA7;
}

.netBox .node p {
	height: 17px;
	line-height: 17px;
	text-indent: 0px;
	font-size: 14px;
	text-align: center;
}

.tabSide {
	display: none;
	position: absolute;
	top: -2px;
	width: 205px;
	height: 68px;
	border: 2px solid #ffffff;
	background: #BBDBFF;
	*line-height: 15px;
}

.tabSide-R {
	left: 92px;
	border-left: none;
	border-radius: 0 5px 5px 0;
}

.tabSide-L {
	left: -205px;
	border-right: none;
	border-radius: 5px 0 0 5px;
}

.tabSide td {
	color: #333;
}

#lastSN {
	position: absolute;
	left: -1px;
	top: 82px;
	width: 94px;
	border: 2px solid white;
	border-radius: 2px;
	background: #CCC;
}

#lastSN p {
	height: 30px;
	line-height: 30px;
}

.arrImg {
	display: block;
	height: 26px;
}

#loadingImg {
	position: absolute;
	top: 90px;
	left: 21px;
}
</style>

<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/jmiMemberManager.js'/>"></script>
<script src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
</head>

<body>


	<ul id="memberNet" style="display: none">

	</ul>

	<script>
		<c:if test="${not empty jmiLinkRef}">

		$('#memberNet')
				.append(
						'<li><p>${jmiLinkRef.miLinkRef.jmiMember.userCode}</p><p>${jmiLinkRef.miLinkRef.jmiMember.petName}</p><p>Group:${jmiLinkRef.gpv}</p><ul id="${jmiLinkRef.miLinkRef.userCode}"></ul></li>');

		<c:forEach items="${jmiLinkRefs }" var="jmiLinkRefVar">

		$('#${jmiLinkRefVar.miLinkRef.jmiMember.linkNo}')
				.append(
						'<li><p>${jmiLinkRefVar.miLinkRef.jmiMember.userCode}</p><p>${jmiLinkRefVar.miLinkRef.jmiMember.petName}</p><p>Group:${jmiLinkRefVar.gpv}</p><p>人数：${jmiLinkRefVar.miLinkRefCount}</p><ul></ul></li>');
	
		</c:forEach>

		</c:if>

		;
		(function($) {
			$.fn.joyLifeNet = function(options) {

				var opts = $.extend({}, $.fn.joyLifeNet.defaults, options);
				var $appendTo = $(opts.netWrap);
				var $container = $("<div class='" + opts.netBoxClass + "'/>");

				$this = $(this);
				if ($this.is("ul")) {
					buildNode($this.find("li:first"), $container, 0, opts);
				} else if ($this.is("li")) {
					buildNode($this, $container, 0, opts);
				}
				$appendTo.append($container);
			};

			$.fn.joyLifeNet.defaults = {
				netWrap : 'body',
				netBoxClass : "netBox"
			};

			function buildNode($node, $container, opts) {

				var $table = $("<table cellpadding='0' cellspacing='0' border='0' style='margin:0 auto;' />");
				var $tbody = $("<tbody/>");

				var $nodeRow = $("<tr/>").addClass("node-cells");
				var $nodeCell = $("<td/>").addClass("node-cell").attr(
						"colspan", 2);
				var $childNodes = $node.children("ul:first").children("li");
				var $nodeDiv;

				var month_consumer_pv = $node.find('input:eq(0)').val();
				var week_group_pv = $node.find('input:eq(1)').val();
				var link_num = $node.find('input:eq(2)').val();
				var pending_link_num = $node.find('input:eq(3)').val();

				if ($childNodes.length > 1) {
					$nodeCell.attr("colspan", $childNodes.length * 2);
				}
				var $nodeContent = $node.clone().children("ul,li").remove()
						.end().html();
			/* 	var $nodeLinkRefCount = "<p>人数:" + "#123#" + "</p>" */
				var $arrImg = "<a href='javascript:void(0);' class='arrImg'><img src='images/arr.png' /></a>";

				$nodeDiv = $("<div>").addClass("node").attr("id",
						"" + $node.index() + $childNodes.index() + "").append(
						$nodeContent).append($arrImg);

				if ($node.find("p:first").html() == '${param.userCode}') { // 如果是頂點，就去除箭頭圖標
					$nodeDiv.find('a').remove();
				}

				if ($childNodes.length > 0) {
					$nodeDiv.click(function() {

						var $this = $(this);
						var $tr = $this.closest("tr");

						if ($tr.hasClass('contracted')) {
							$this.css('cursor', 'n-resize');
							$tr.removeClass('contracted').addClass('expanded');
							$tr.nextAll("tr").css('visibility', '');
							$node.removeClass('collapsed');
						} else {
							$this.css('cursor', 's-resize');
							$tr.removeClass('expanded').addClass('contracted');
							$tr.nextAll("tr").css('visibility', 'hidden');
							$node.addClass('collapsed');
						}
					});
				}

				$nodeCell.append($nodeDiv);
				$nodeRow.append($nodeCell);
				$tbody.append($nodeRow);

				if ($childNodes.length > 0) {
					$nodeDiv.css('cursor', 'n-resize');

					var $downLineRow = $("<tr/>");
					var $downLineCell = $("<td/>").attr("colspan",
							$childNodes.length * 2);

					$downLineRow.append($downLineCell);
					$downLine = $("<div></div>").addClass("line down");
					$downLineCell.append($downLine);
					$tbody.append($downLineRow);

					if ($childNodes.length == 1) {
						$downLine.css('height', '23px');
					}

					var $linesRow = $("<tr/>");

					$childNodes.each(function() {

						var $left = $("<td>&nbsp;</td>").addClass(
								"line left top");
						var $right = $("<td>&nbsp;</td>").addClass(
								"line right top");

						$linesRow.append($left).append($right);
					});

					$linesRow.find("td:first").removeClass("top").end().find(
							"td:last").removeClass("top");
					$tbody.append($linesRow);

					var $childNodesRow = $("<tr/>");

					$childNodes.each(function() {

						var $td = $("<td/>");

						$td.attr("colspan", 2);
						buildNode($(this), $td, opts);
						$childNodesRow.append($td);
					});
					$tbody.append($childNodesRow);
				}

				if ($node.attr('class') != undefined) {

					var classList = $node.attr('class').split(/\s+/);

					$.each(classList, function(index, item) {
						if (item == 'collapsed') {
							$nodeRow.nextAll('tr').css('visibility', 'hidden');
							$nodeRow.removeClass('expanded');
							$nodeRow.addClass('contracted');
							$nodeDiv.css('cursor', 's-resize');
						} else {
							$nodeDiv.addClass(item);
						}
					});
				}

				$table.append($tbody);
				$container.append($table);

				$nodeDiv.children('a').click(function(e) {
					e.stopPropagation();
				});
			}
			;

		})(jQuery);
	</script>
	<script>
		function validateOthers(theForm) {
			if (theForm.memberNo.value == "") {
				alert("<ng:locale key="bdBonus.tree.memberNo.required"/>");
				theForm.memberNo.focus();
				return false;
			}
			return true;
		}

		function arrImgEvent(p, selectNo) {

			var $lastSN = "<p>" + selectNo + "</p>";
			var $SnBox = $("<a href='#'>").attr('id', 'lastSN').append($lastSN);

			$('#lastSN').remove();
			$('#' + p).append($SnBox);
			$SnBox.click(function() {
				var SN = $(this).find('p').text();
				windowClose(SN);
			});
		}

		function showSN(pID) {
			$(pID).append();
		}

		function windowClose(m_no) {
			window.opener.document.getElementById("linkNo").value = m_no;
			window.open('', '_parent', '');
			window.close();
		}

		var selectPid;

		$(function() {
			$("#memberNet").joyLifeNet();
			$('.arrImg').click(
					function() {

						var $sel = $(this).parent();
						var pID = $sel.attr('id');
						selectPid = pID;
						var linkNo = $sel.find('p:eq(0)').text();
						var $loading = $("<img src='images/loading.gif' />")
								.attr('id', 'loadingImg');

						$('#loadingImg').remove();
						$sel.append($loading);
						getSelectNo(linkNo);
					});

		});

		function getSelectNo(selectNo) {
			jmiMemberManager.getJmiSelectLinkNo1('${param.userCode}', selectNo,
					callBack);
		}
		function callBack(valid) {
			if (valid != '') {
				arrImgEvent(selectPid, valid);
				$('#loadingImg').remove();
			}
		}
	</script>


</body>
</html>

