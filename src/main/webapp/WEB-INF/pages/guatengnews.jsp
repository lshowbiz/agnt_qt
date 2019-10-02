<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <!-- <link rel="stylesheet" href="style-ng.css"> 这个css文件在套进去ec系统的时候应该去掉 -->
    <link href="<c:url value='/styles/index/style.css'/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<c:url value='/styles/lib/bootstrap.min.css'/>" />
    <style>
        body { font-family: "microsoft yahei";}
        h1 { padding: 10px; color: #666; }

        .content { width: auto; margin: 110px 20px 0 10px; text-align: center; padding: 0; background: #fff; float: none; }

        .gt-box {height:280px; margin: 0 auto; width:100%;  text-align:center; overflow:hidden }
        .gt-box .gt-item { width:25%;   margin:20px 0; float: left;}
        .gt-box .gt-item .bgs1 { width:150px;  height:70px;  padding:10px 0; text-align: center; border-left:10px solid #db5b34; margin:0 60px;}
        .gt-box .gt-item .bgs2 { width:150px;  height:70px;  padding:10px 0; text-align: center; border-left:10px solid #e86996; margin:0 60px;}
        .gt-box .gt-item .bgs3 { width:150px;  height:70px;  padding:10px 0; text-align: center; border-left:10px solid #3fafe1; margin:0 60px;}
        .gt-box .gt-item .bgs4 { width:150px;  height:70px;  padding:10px 0; text-align: center; border-left:10px solid #8ac216; margin:0 60px;}
        
        .gt-box .gt-item .gt-item-text {font-size:14px;  padding:30px; margin-top:80px; background: #fff; text-align: justify; vertical-align:middle;}
  		.gt-box .gt-item .gt-item-text p{ font-size:14px; line-height:24px}
      /*** .gt-box .gt-item .bg1 { background: url("<c:url value='/images/guatengnews/bg1-a.png'/>") left center no-repeat; }
        .gt-box .gt-item .bg2 { background: url("<c:url value='/images/guatengnews/bg2-a.png'/>") left center no-repeat; width: 52px; }
        .gt-box .gt-item .bg3 { background: url("<c:url value='/images/guatengnews/bg3-a.png'/>") left center no-repeat; width: 57px; }
        .gt-box .gt-item .bg4 { background: url("<c:url value='/images/guatengnews/bg4-a.png'/>") left center no-repeat; width: 50px; }****/ 
	.color_1{color:#db5b34;}
	.color_2{color:#e86996}
	.color_3{color:#3fafe1}
	.color_4{color:#8ac216}
    .number{font-size:50px; font-weight:bold;}   

        .gt-split { width: 100%; height: 109px; background: #FEE4BF; top: 144px; z-index: 9; }

        .gt-news { margin-top: 20px; padding: 10px; text-align: left; }
        .gt-news .gt-news-box { width: 45%; }
        .gt-news-title { width: 100%; height: 48px; line-height: 48px; text-indent: 12px; border-radius: 4px; text-align: left; font-size: 1.2em; color: #A00912; background: url("<c:url value='/images/guatengnews/bg-title.png'/>") left center repeat-x; }
        .gt-news-title .bgs { width: 105px; height: 68px; top: -22px; right: 0; background: url("<c:url value='/images/guatengnews/aa.png'/>") center center no-repeat; }
        .gt-news-title .bgs.bg-5 { background: url("<c:url value='/images/guatengnews/bb.png'/>") center center no-repeat; }
        .gt-news-list { margin-top: 10px; padding-left: 24px; line-height: 180%; border-bottom: 1px dotted #fff; overflow: hidden; background: url("<c:url value='/images/guatengnews/cc.png'/>") left center no-repeat; }
        .gt-news-box.weico .gt-news-list { background: url("<c:url value='/images/guatengnews/dd.png'/>") left center no-repeat; }
		.gt-todo { width:100%; height:120px; text-align:center}
        .gt-todo .user-icon { width: 120px; height: 142px; }
        .gt-todo .user-icon .user-stat { width: 20px; height: 20px; right: 0; top: 90px; border: 3px solid #fff; border-radius: 20px; background: #00D097; }

        .input-edg { width: 400px; height: 40px; padding: 0 10px; border: none; border-radius: 4px; background: #fff url("<c:url value='/images/guatengnews/arr-right.png'/>") 360px center no-repeat; border:1px dashed #dd2726;  }
        .btn-rng { display: inline-block; min-width: 110px; height: 40px; line-height: 40px; text-align: center; padding: 0 20px; color: #dd2726; border-radius: 4px; background: #fff; border:1px dashed #dd2726; }

        .input-edg { width: 580px; height: 69px; padding: 0 10px; font-size: 1.2em; font-family: "microsoft yahei"; border: none; border-radius: 4px; background: #fff url("<c:url value='/images/guatengnews/ar.png'/>") 511px center no-repeat; }
        .btn-rng { display: inline-block; width: 143px; height: 69px; line-height: 69px; font-size: 1.2em; text-align: center; color: #dd2726;  /** background: url("<c:url value='/images/guatengnews/bt-arr.png'/>") center center no-repeat; **/}

        .btn-group { margin: 20px 0; }
        .btn-group a{line-height:69px; color:#dd2726;}

        .gt-actions { width: 100%; height:70px; border-top:1px solid #ebebeb; margin-top:10px;  line-height: 70px; overflow: hidden;}
        .gt-actions button{ margin:20px 60px; height:30px; line-height:1.42857143; font-size:15px;  display: inline-block;}
		 .gt-actions a{ margin:20px 60px; height:30px; line-height:1.42857143; font-size:15px;  display: inline-block;}
		.rel {
		    position: relative;
		    float: none;}

    </style>
</head>
<body>
<div class="cont" >
    <!-- <div class="content fr"> 这一层div在套进去ec系统的时候应该去掉 -->


   
	<div><img style="width:100%" src="./images/guatengnews/guat.png"></div>

	<div class="banner" id="bannergd" style="overflow:hidden;width: 100%;"><jsp:include page="/scripts/index.jsp" /></div>
	
	    <div class="gt-todo">                   
                <!-- 两组按钮根据编号绑定情况确定哪组出现 -->
                <c:if test="${!isBind}">
                <div class="btn-group">
                    <input type="text" name="" id="" class="input-edg" value="您的编号未绑定手机号码，请前往绑定。" 
                    style="width: 580px; height: 69px; padding: 0 10px; font-size: 1.2em; font-family: 'microsoft yahei'; border:1px solid #dd2726; border-radius: 4px; background: #fff url(<c:url value='/images/guatengnews/ar.png'/>) 511px center no-repeat;">
                    <a href="<c:url value='jmiActivateEcMallform?1=1&parentId=712624'/>" class="btn-rng">绑定手机号</a>
                </div>
				</c:if>
				<c:if test="${isBind}">
                <div class="btn-group">
                    <input type="text" name="" id="" class="input-edg" value="您的编号已经绑定手机号码，请前往购物。" 
                    style="width: 580px; height: 69px; padding: 0 10px; font-size: 1.2em; font-family: 'microsoft yahei';  border:1px solid #dd2726; border-radius: 4px; background: #fff url(<c:url value='/images/guatengnews/ar.png'/>) 511px center no-repeat;">
                    <a href="http://www.guaten.com/" class="btn-rng" target="_blank">去购物</a>
                    <a href="<c:url value='fiBcoinBalanceform?1=1&parentId=712624'/>" class="btn-rng">积分迁移</a>
                </div>
				</c:if>
          
        </div>

            <div class="gt-box tc">
                <div class="gt-item rel" >
                    <div class="bgs1 fl color_1">  
                    <span class="number" >01</span>              
                    <h2 class="color_1">积分抵扣</h2>
                   </div>
                    <div class="gt-item-text mt" >会员在瓜藤网购物可以使用积分抵扣部分货款，1积分=1元</div>
                </div>
                <div class="gt-item rel">
                 <div class="bgs2 fl color_2">  
                    <span class="number" >02</span>     
                    <h2  class="color_2">分享返佣</h2>
                   </div>
                    <div class="gt-item-text mt" >为激励会员推广瓜藤网而设立的两级分享返佣奖励，直接邀请获6%返佣，间接邀请获3%返佣。只要通过您介绍的会员在瓜藤网成功消费，即可获得相应的分享返佣奖励</div>
                </div>
                <div class="gt-item rel">
                  <div class="bgs3 fl color_3">  
                    <span class="number" >03</span>     
                    <h2  class="color_3">幸运抽号</h2>
                   </div>
                    <div class="gt-item-text mt" >全网商品单笔订单付现满300元，即可随机获得1个幸运号，参加每周一次的幸运号抽奖，100%中奖哦！购买金额越多，获得的幸运号越多，获奖机会越多</div>
                </div>
                <div class="gt-item rel">
                    <div class="bgs4 fl color_4">  
                    <span class="number" >04</span>                  
                    <h2  class="color_4">红包接龙</h2>
                   </div>
                    <div class="gt-item-text " ><p>巴马百岁源红包接龙游戏；每周一13点13分开始新一期，瓜藤网根据客户购买的先后顺序，依次发放接龙红包，会员有机会获得15元~210元不等的瓜币红包</p></div>
                </div>
            </div>




        <div class="gt-actions tc">
            <button type="button" onclick="window.open('<c:url value='/guatenghandbook1'/>')">瓜藤网绑定手册&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></button>
            <button type="button" onclick="window.open('<c:url value='/guatenghandbook2'/>')">瓜藤网积分迁移手册&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></button>
            <button type="button" onclick="window.open('<c:url value='/guatenghandbook3'/>')">瓜藤网购物手册&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></button>
        </div>



    <!-- </div> -->
</div>
    
    <!--<script src="jquery-1.8.2.min.js"></script>  这个js文件在套进去ec系统的时候应该去掉 -->
    <!--<script>// 在套进ec系统的时候保留这段js代码
        $(function(){
            $(".gt-item").mouseenter(function(){
                var $this = $(this);
                $this.addClass('active');
                $this.find(".gt-item-text").slideDown();
            }).mouseleave(function(){
                var $this = $(this);
                $this.removeClass('active');
                $this.find(".gt-item-text").slideUp();
            });
        });
    </script>-->
</body>
</html>