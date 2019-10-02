<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>js首页按钮控制焦点图片滚动</title>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
/* outer */
#outer{height:130px;position:relative;overflow:hidden;background:#eee;border-radius:5px;border:1px solid #e5e5e5; margin-right:10px; }
#inner{display:block;height:130px;position:absolute;left:0;top:0;}
#inner li{display:block;height:130px;float:left;font-size:72px;line-height:130px;text-align:center;content:#666;background:#eee;}
#inner li img{vertical-align:top;}
#nav{height:40px;padding-left:770px;position:absolute;bottom:0;left:0;opacity:0.8;filter:alpha(opacity=80);}
#nav em{display:block;float:left;width:24px;height:24px;margin-right:8px;margin-top:8px;line-height:24px;text-align:center;font-size:14px;color:#333;font-style:normal;font-family:Arial;background:#eee;border-radius:12px;cursor:pointer;}
#nav .ehover{background:#360;color:#fff;}
#left,#right{ width:40px;height:100px;position:absolute;top:100px;background:#333;opacity:0.4;color:#fff;font-size:36px;text-align:center;line-height:100px;cursor:pointer;}
#left:hover,#right:hover{opacity:0.8}
#left{left:0;border-top-right-radius:10px;border-bottom-right-radius:10px;}
#right{right:0;border-top-left-radius:10px;border-bottom-left-radius:10px;}
</style>
</head>

	<div id="outer">
		<ul id="inner">
			<li><a href="javascript:void(0)"><img id="s1" name="imggd" src="${ctx}/images/bannerYD.gif" style="width:100%" height="130" ></a></li>
			<li><a href="${ctx}/loginform/help?flag=newHelp&1=1" target="_blank"><img id="s2" name="imggd" src="${ctx}/images/bannerXSYD.gif" style="width:100%" height="130" ></a></li>
		</ul>
	</div>
	
	<script type="text/javascript">
	var bwt = window.parent.document.getElementById("bannergd").offsetWidth ;
	var imggds=document.getElementsByName("imggd");
	for(var i=0; i<imggds.length;i++){
		imggds[i].style.width=bwt+"px";
	}
	
    var outer=document.getElementById("outer");
    var oInner=document.getElementById("inner");
    var oLis=oInner.getElementsByTagName("li");
    //var oleft=document.getElementById("left");
    //var oright=document.getElementById("right");
    
    var bw = document.getElementById("s1").width;
	//alert(bw);alert(oLis.length);
	outer.style.width=bw+"px";
	oInner.style.width=(bw*oLis.length)+"px";
	for(var i=0; i<oLis.length;i++){
        oLis[i].style.width=bw+"px";
    }
    
    var step=0;
    var timer=null;
    function buttur(ele,obj){
    	window.clearTimeout(ele.timer);
    	var end=null;
    	for(direc in obj){
    		var direc1=direc.toLowerCase();
    		var strOffset="offset"+direc1.substr(0,1).toUpperCase()+direc1.substring(1).toLowerCase();
    		var target=obj[direc];
    		var nSpeed=(target-ele[strOffset])/10;
    		nSpeed=nSpeed>=0?Math.ceil(nSpeed):Math.floor(nSpeed);
    		ele.style[direc1]=ele[strOffset]+nSpeed+"px";
    		end+=nSpeed;
    	}
    	if(end)
    		if(typeof fnCallback=="function"){
    			fnCallback.call(ele);
    		}else{
            ele.timer=window.setTimeout(function(){buttur(ele,obj)},30);
    		}
    }
    var divs=document.createElement("div");
    divs.setAttribute("id","nav");
    divs.setAttribute("width",bw);
    for(var i=0; i<oLis.length;i++){
        var oa=document.createElement("em");
        oa.innerHTML=i+1;
        divs.appendChild(oa);
    }
    outer.appendChild(divs);
    var btn=document.getElementById("nav").getElementsByTagName("em");
    for(var i=0; i<btn.length; i++){
        btn[i].indx=i;
        btn[0].className="ehover";
        btn[i].onclick=function(){
            //window.clearTimeout(timer);
            for(var i=0; i<btn.length; i++){
                btn[i].className="";
                btn[this.indx].className="ehover";
            }
            buttur(oInner,{left:-oLis[0].offsetWidth*this.indx}); 
        }    
    }
    function autoMove(){
     	step++;
    	if(step<btn.length){
             for(var i=0; i<btn.length; i++){
                btn[i].className="";
                btn[step].className="ehover";
                buttur(oInner,{left:step*-bw});
            }           
    	 }else{ 
                step=btn.length-(oLis.length+1);        
         }
     	timer=window.setTimeout(autoMove,3000);
    }
    autoMove(); 
    /*oleft.onclick=function(){
    	window.clearTimeout(timer);
    	step++;
    	if(step<btn.length){
             for(var i=0; i<btn.length; i++){
                btn[i].className="";
                btn[step].className="ehover";
                buttur(oInner,{left:step*-bw});
            }           
         }else{ 
                step=btn.length-(oLis.length+1);        
         }
     }
    oright.onclick=function(){
    	window.clearTimeout(timer);
    	step--;
    	if(step<0){
             step=btn.length;
         }else{ 
            for(var i=0; i<btn.length; i++){
                btn[i].className="";
                btn[step].className="ehover";
                buttur(oInner,{left:step*-bw}); 
            }
         }
    }*/
     
   oInner.onmouseover=function(){window.clearTimeout(timer);}
   oInner.onmouseout=function(){timer=window.setTimeout(autoMove,3000);}
   /*oleft.onmouseover=function(){window.clearTimeout(timer);}
   oleft.onmouseout=function(){timer=window.setTimeout(autoMove,3000);}
   oright.onmouseover=function(){window.clearTimeout(timer);}
   oright.onmouseout=function(){timer=window.setTimeout(autoMove,3000);} */
	</script>	
