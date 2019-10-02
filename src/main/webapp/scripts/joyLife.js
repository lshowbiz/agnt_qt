
// 为jQuery扩展一个函数	
	
$.fn.coffee = function(obj){  
	for(var eName in obj)  
		for(var selector in obj[eName])  
			$(this).on(eName, selector, obj[eName][selector]);  
} 

function closeDialog(obj){

	var $mask = $("#mask");
	
	if( $mask ) $mask.animate({ opacity:"hide" },600);
	$(obj).parents("[id^='popup']").animate({ opacity:"hide" },600);
}

function popupNewAddr(){
	
	var $mask = $('#mask');
	var $popupAddr = $('#popupAddr');
	
	$mask.show().css({
		"height":$("body").height()
	});
	$popupAddr.css({
	
		left:( $(window).width() - $popupAddr.outerWidth() ) / 2,
		top:( $(window).height() - $popupAddr.outerHeight() ) / 2,
		zIndex:"9999",
		background:"#FFF"
	}).animate({
		opacity:"show"
	},600);
	
}

$(function(){		
		
	// 显示更多 & 收起

/*	$(".pdList").find('li:gt(3)').hide();
	$('#pdlist-1').show().next().find('a').html('收起<b class="s1"></b>');
	$(".slideBox").on('click','a',function(){
		
		var it = $(this);
		var $u = it.parent().prev();
		var uClass = $u.attr('class');

		uClass == 'pdList' ? $u.find('li:gt(3)').toggle() : $u.toggle();
		it.text() == '显示更多' ? it.html('收起<b class="s1"></b>') : it.html('显示更多<b></b>');
		
	});*/

	// 商品浏览

	$("#js_pdView").hover(
		function(){
			$(this).find('.subCategory').show();
		},
		function(){
			$(this).find('.subCategory').hide();
		}
	);

	// orderLs宽度控制
	
	var $orderLs = $("#js_orderLs");
	var len = $orderLs.find('li').length;

	$orderLs.width(len * 100);
	
	// 选择收货地址
	
	$("#js_address").on('change',':radio',function(){
		
		var $this  = $(this);
		var itemId = $this.attr('id');
		
		$this.closest('form').find(":hidden[name='fabName']").val(itemId);
		$this.parent().parent().addClass('current');
		$this.parent().parent().siblings().removeClass('current');
		
	});

	// 商品详情tag
	
	$('#js_pdTags a').mouseover(function(){
		var currentTab =  $(this).attr('href');
		$(this).addClass("current");
		$(this).parent().siblings().find('a').removeClass("current");
		$(".pdContent").hide(); 
		$(currentTab).show(); 
		return false; 
	});
	
	// 收货地址弹出框
	
	$('#js_newAddr').click(function(){
		popupNewAddr();
	});
	
	// 全选
	
	$("input[id^='checkAll_']").on('click',function(){
	
		var itId = $(this).attr('id');
		var aimLs = itId.split('_')[1];
		var aimId = '#chk_' + aimLs;
         
		$(aimId).find(':checkbox').prop('checked',$(this).is(':checked'));
		
		if($(aimId).find(':checkbox').prop('checked'))
		{
		   
		}else{
		    $("#div_"+aimLs).removeClass("myclip-item-highlight");  
		}
		
	});
	
	$("[id^='chk_']").on('click',':checkbox',function(){

		var $tbody = $(this).parents("[id^='chk_']");
		var $checkBox = $tbody.find(':checkbox');
		var itId = $tbody.attr("id");
		var aimLs = itId.split('_')[1];
		var aimId = '#checkAll_' + aimLs;
		var $checkAll = $(aimId);
		var checkBoxL = $checkBox.length;
		var checkedBoxL = $checkBox.filter(':checked').length;
		
		checkBoxL == checkedBoxL ? $checkAll.prop('checked',true) : $checkAll.prop('checked',false);
		
	});
	
	// 自己提货
	
	$('#diy').click(function(){
	
		var $address = $('#js_address'); 
		var $newAddr = $('#js_newAddr');
		
		if( $(this).prop('checked') ){
			$address.slideUp().find(":radio").prop('checked',false);
			$newAddr.unbind().css('color','#DBDBDB').removeClass('hoverLine');
		}else{
			$address.slideDown().find(".current :radio").prop('checked',true);
			$newAddr.bind('click',popupNewAddr).css('color','#bd5f21').addClass('hoverLine');
		}
	});

    // 导航子菜单

    $('#js_nav').coffee({
		'mouseover':{
			'> li':function(){
				$(this).find('.subNav').animate({
					opacity:'show'
				},300);	
			}
		},
		'mouseleave':{
			'> li':function(){
				$(this).find('.subNav').animate({
					opacity:'hide'
				},300);	
			}
		}

	});

    //

    $('#js_sidebar').coffee({
        'mouseover':{
            '> li > a':function(){
                $(this).stop().animate({
                    'text-indent':'32px'
                },250)
            },
            'li.level-3 > a':function(){
                $(this).stop().animate({
                    'text-indent':'48px'
                },250)
            }
        },
        'mouseleave':{
            '> li > a':function(){
                $(this).stop().animate({
                    'text-indent':'20px'
                },250)
            },
            'li.level-3 > a':function(){
                $(this).stop().animate({
                    'text-indent':'36px'
                },250)
            }
        }
    });

});

function showDialog(siteroot,params,width,Height,scroll) {
	var features="height="+Height+",width="+width;
	if(scroll!=undefined && scroll!=""){
		features+=",scrollbars";
	}
	
	newWindow = window.open(params[1],"subWind","status,menubar,"+features);
	newWindow.focus( );
}