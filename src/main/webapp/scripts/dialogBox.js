
function MyDialog(opts) {

    var defaults = {
        selector	:	'#selector',
        title		:	true ,
        boxTitle	:	'<a href="javascript:void(0);" class="dialog_close"></a>',
        okBtn		:	false,
        noBtn		:	true,
        boxContent	:	'默认弹窗内容',
        boxWidth	:	414,
        boxHeight	:	'auto',
        dialogMask	:	true
    };
    opts = $.extend({}, defaults, opts || {});

    var $elem       = $(opts.selector),
        $dialogDiv  = $(".dialog_div"),
        $dialogBox  = $(".dialog_box"),
		$window 	= $(window),
        $body       = $("body");

    var methods = {

        init : function() {
            $(".dialog_div").remove();
            $(".dialog_box").remove();
            methods.makeBox();
            methods.popDialog();
        },

        makeBox : function() {
            var box = $('<div class="dialog_div"></div><div class="dialog_box" style="position:absolute;  width: 450px; height:200px; z-index:200; top:40%; left:40%; background:#fff;border:4px solid #ebebeb; padding:20px"><div class="dialog_title"></div><div class="dialog_content"></div><div class="dialog_footer" align="center"></div></div>');
            $body.append(box);
            $dialogDiv = $(".dialog_div");
			$dialogBox 	= $(".dialog_box");
        },

        popDialog : function() {

            if( opts.title ){
                $dialogBox.find(".dialog_title").html(opts.boxTitle);
                $(".dialog_title").show();
            }else{
                $dialogBox.find(".dialog_title").hide();
            }

            if( opts.noBtn || opts.okBtn ) {
                //var noBtn = opts.noBtn ? '<a href="javascript:void(0)" class="btn_common btn_no corner2 fr" style="margin:10px;">取&nbsp;消</a>' : '',
        		var noBtn = opts.noBtn ? '<button type="button" class="btn btn-success"  >关&nbsp;闭</button>' : '',
                    okBtn = opts.okBtn ? '<button type="button" class="btn btn-info"  >确&nbsp;定</button>' : '';
        		
                $dialogBox.find(".dialog_footer").append(okBtn).append('&nbsp;&nbsp;').append(noBtn);
            }

            $(".dialog_box").find('.dialog_content').append(opts.boxContent);
            $(".dialog_box").css({"width":opts.boxWidth,"height":opts.boxHeight});

            methods.showDialog();

            $('.dialog_close,.btn_ok,.btn_no,.dialog_div,.btn').click(function() {
                methods.closeDialog();
            });

            $dialogBox.click(function(e) {
                e.stopPropagation();
            });

            $elem.click(function(e) {
                e.stopPropagation();
            });
        },

        showDialog : function() {

            var selW    =   $dialogBox.outerWidth(true),
                selH    =   $dialogBox.outerHeight(true),
                winL    =   ( $window.width() - selW ) / 2,
                winT    =   ( $window.height() - selH ) / 2;

            $dialogBox.css({"zIndex":11,'top':167 + $(document).scrollTop() + 'px','left':winL + 'px'}).animate({ top:200 + $(document).scrollTop() + 'px',opacity:"show" },300);
            if(opts.dialogMask) {
            	var winH  = $window.height();
            	var bodyH = $body.height();
				
            	vHeight = winH > bodyH ? winH : bodyH;     
                $dialogDiv.animate({ opacity:"show" },600).css({"height":vHeight,"width":$('body').width()});
            }else{
                $dialogDiv.hide();
            }
        },

        closeDialog : function() {
            $dialogBox.animate({ 
				top:167 + $(document).scrollTop() + 'px',
				opacity:"hide" 
			},300,function(){
				$(".dialog_div").remove();
			});
            if( $dialogDiv.length > 0 ) {
				$dialogDiv.animate({ opacity:"hide" },600,function(){$(".dialog_box").remove();});
			};
        }

    };

    methods.init();
    return methods;
}
