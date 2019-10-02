;(function($) {
		$.fn.joyLifeNet = function(options) {

			var opts		=	$.extend({}, $.fn.joyLifeNet.defaults, options);
			var $appendTo	=	$(opts.netWrap);
			var $container	=	$("<div class='" + opts.netBoxClass + "'/>");
			
			$this = $(this);	
			if( $this.is("ul") ) {
				buildNode($this.find("li:first"), $container, 0, opts);
			}else if( $this.is("li") ) {
				buildNode($this, $container, 0, opts);
			}
			$appendTo.append($container);
		};

		$.fn.joyLifeNet.defaults = {
			netWrap		:	'body',			// 树形结构的最外层元素，用于定位
			netBoxClass	:	"joyLifeNet"	// 树形结构向上最近一层元素的 class ，用于样式控制
		};

		//	buildNode		树形节点的构造方法
		//	$node 			父节点（这里是 li）
		//	$container		节点的容器
		//	opts			参数集

		function buildNode($node, $container, opts) {
		
			var $table		=	$("<table cellpadding='0' cellspacing='0' border='0'/>");
			var $tbody		=	$("<tbody/>");

			// 构建树形结构
			// 每个节点都是一个 table(子节点就是嵌套在父节点里的 table )
			// 节点的连接线全部由 td 构成
			var $nodeRow	=	$("<tr/>").addClass("node-cells");
			var $nodeCell	=	$("<td/>").addClass("node-cell").attr("colspan", 2);
			var $childNodes	=	$node.children("ul:first").children("li");
			var $nodeDiv;
			
			// 如果某节点的子节点数量大于 1 ，那么 colspan 的值应该是子节点数量的 2 倍
			if( $childNodes.length > 1 ) {
				$nodeCell.attr("colspan", $childNodes.length * 2);
			}
			
			// 获取节点内容（除了 ul ，li 以外的任何东东）
			var $nodeContent = $node.clone().children("ul,li").remove().end().html();
            var $sideContent =
                                "<div class='tabSide'>" +
                                    "<table width='100%' border='0''>" +
                                        "<colgroup style='width:112px;'></colgroup>" +
                                        "<colgroup></colgroup>" +
                                        "<tbody>" +
                                            "<tr>" +
                                                "<td class='tr'>当月个人业绩：</td>" +
                                                "<td class='tl'>10000.00&nbsp;PV</td>" +
                                            "</tr>" +
                                            "<tr>" +
                                                "<td class='tr'>安置网络：</td>" +
                                                "<td class='tl'>10000.00&nbsp;PV</td>" +
                                            "</tr>" +
                                            "<tr>" +
                                                "<td class='tr'>安置网络人数：</td>" +
                                                "<td class='tl'>100&nbsp;人</td>" +
                                            "</tr>" +
                                            "<tr>" +
                                                "<td class='tr'>安置网络新增人数：</td>" +
                                                "<td class='tl'>100&nbsp;人</td>" +
                                            "</tr>" +
                                        "</body>" +
                                    "</table>" +
                                "</div>";

			$nodeDiv = $("<div>").addClass("node").append($nodeContent).append($sideContent);

			// 节点的展开和折叠
			if ( $childNodes.length > 0 ) {
				$nodeDiv.click(function() {
				
					var $this	=	$(this);
					var $tr		=	$this.closest("tr");			// 注意这里

					if( $tr.hasClass('contracted') ){				// 如果原来是折叠的……
						$this.css('cursor','n-resize');
						$tr.removeClass('contracted').addClass('expanded');
						$tr.nextAll("tr").css('visibility', '');	// 不能使用 show() ，因为即使隐藏了，还需要占据空间以保持队形，show() 做不到

						$node.removeClass('collapsed');				// 给父节点加上这个 class，以保持队形
					}else{											
						$this.css('cursor','s-resize');
						$tr.removeClass('expanded').addClass('contracted');
						$tr.nextAll("tr").css('visibility', 'hidden');
						
						$node.addClass('collapsed');				
					}
				});
			}
		
			$nodeCell.append($nodeDiv);
			$nodeRow.append($nodeCell);
			$tbody.append($nodeRow);

			if( $childNodes.length > 0 ) {
				$nodeDiv.css('cursor','n-resize');			
				
				// 绘制从父节点到水平线的连接线
				var $downLineRow  = $("<tr/>");
				var $downLineCell = $("<td/>").attr("colspan", $childNodes.length * 2);
				
				$downLineRow.append($downLineCell);
				$downLine = $("<div></div>").addClass("line down");
				$downLineCell.append($downLine);
				$tbody.append($downLineRow);
				
				// 值为 1 时，因为没有水平线，需要额外增加 3px 的高度，以保持树形水平整齐
				if( $childNodes.length == 1 ){
					$downLine.css('height','23px');
				}
				// 绘制水平线条
				var $linesRow  =  $("<tr/>");
				
				$childNodes.each(function() {
				
					var $left  = $("<td>&nbsp;</td>").addClass("line left top");
					var $right = $("<td>&nbsp;</td>").addClass("line right top");
					
					$linesRow.append($left).append($right);
				});

				// 水平线不能超出第一个和最后一个分支
				$linesRow.find("td:first").removeClass("top").end().find("td:last").removeClass("top");
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

			// 给节点上加上 'collapsed' 这个 class ，将隐藏这个节点
			if( $node.attr('class') != undefined ) {
			
				var classList = $node.attr('class').split(/\s+/);
				
				$.each(classList, function(index,item) {
					if ( item == 'collapsed' ) {
					//	console.log($node);
						$nodeRow.nextAll('tr').css('visibility', 'hidden');
							$nodeRow.removeClass('expanded');
							$nodeRow.addClass('contracted');
							$nodeDiv.css('cursor','s-resize');
					} else {
						$nodeDiv.addClass(item);
					}
				});
			}

			$table.append($tbody);
			$container.append($table);
		
			// 如果在节点里有可以点击的链接，需要阻止它的冒泡
			$nodeDiv.children('a').click(function(e){
			//	console.log(e);
				e.stopPropagation();
			});
		};

	})(jQuery);	
	