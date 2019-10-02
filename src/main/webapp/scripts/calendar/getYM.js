 /*name:获取年月控件   
  author:cfy   
  design-date:2006/12/02   
  note:在IE5.5下测试通过   
  */   
    
  var   tarObject   
  var   d=new   Date()   
  sYear=d.getFullYear()   
  sMonth=d.getMonth()   +   1   
  
  if(sMonth<10){
  sMonth="0"+sMonth;
  }
	
  popup   =   window.createPopup()   
  popBody   =   popup.document.body   
  popBody.style.border="3px double #72cfd7"   
  popBody.style.fontSize   =   "12px"   
  popBody.style.backgroundColor=   "#1291a9"   
  popBody.style.cursor="hand"   
    
  var   strPop='<table  id="yMonth" style="border:1px solid #F0F0F0;font-size:12px;" author="liuzxit">'   
  strPop+='<th   width="28"   onclick="parent.yearChange(-1)"   style="color:#00ffff;"><<'   
  strPop+='<th   width="28"   style="color:#ffffff"   onclick="parent.selectClicked(this)">'+(sYear   -   2)   
  strPop+='<th   width="28"   style="color:#ffffff"   onclick="parent.selectClicked(this)">'+(sYear   -   1)   
  strPop+='<th   width="28"   style="color:#ff0000"   onclick="parent.selectClicked(this)">'+sYear   
  strPop+='<th   width="28"   style="color:#ffffff"   onclick="parent.selectClicked(this)">'+(sYear   +   1)   
  strPop+='<th   width="28"   onclick="parent.yearChange(1)"   style="color:#00ffff;">>></th>'   
  strPop+='<tr   align="center"><td style="border:1px solid #15ABC6;color:#72CFD7;" onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">01'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">02'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"  onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">03'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">04'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">05'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   style="border:1px solid#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">06</td></tr>'   
  strPop+='<tr   align="center"><td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">07'   
  strPop+='<td  style="border:1px solid #15ABC6;color:#72CFD7;"  onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">08'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">09'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">10'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">11'   
  strPop+='<td style="border:1px solid #15ABC6;color:#72CFD7;"   onmouseover="parent.tdMove(this)"   onmouseout="parent.tdOut(this)"   onclick="parent.selectClicked(this)">12</td></tr>'   
  strPop+='<tr   align="center"   bgcolor="#72cfd7"   style="color:#ffffff"><td   colspan=2 bgcolor="#72cfd7"   onclick="parent.selectClicked(this)">当前月<td   colspan=2 bgcolor="#72cfd7"   onclick="parent.cancellValue(this)">    置空<td   colspan=2 bgcolor="#72cfd7"   onclick="parent.hidePop();">关闭</td></tr>'   
  strPop+='</table>'   
  popBody.innerHTML=strPop   
    
function   tdMove(e){
	e.style.border="1px solid #FFFFFF";
	e.style.color="#FFFFFF";
}   
function   tdOut(e){
	e.style.border="1px solid #72CFD7";
	e.style.color="#72CFD7";
}   
  function   yearChange(n){   
	  var   e=popup.document.all('yMonth');
	  e.cells[1].innerText   =   parseInt(e.cells[1].innerText)   +   parseInt(n);
	  e.cells[2].innerText   =   parseInt(e.cells[2].innerText)   +   parseInt(n);  
	  e.cells[3].innerText   =   parseInt(e.cells[3].innerText)   +   parseInt(n);  
	  e.cells[4].innerText   =   parseInt(e.cells[4].innerText)   +   parseInt(n);  

	  var   reg=/(\d{4})(\d{1,2})/ ;
	  var   r=reg.exec(tarObject.value)   ;

	  tarObject.value=(parseInt(r[1])+n)+r[2];
  }   
    
function   selectClicked(e){
	var   p=e.parentElement;
	switch(p.rowIndex){   
		case   0:
			for(var   i=1;i<5;i++){   
				p.cells[i].style.backgroundColor='#7B869A';   
				p.cells[i].style.color='#ffffff';   
			}   
			e.style.backgroundColor='#ffffff';   
			e.style.color='#ff0000';   
			tarObject.value=tarObject.value.replace(/(\d{4})(\d{1,2})/,e.innerText+'$2');   
			break;   
		case   3:
			tarObject.value=sYear+''+sMonth;
			popup.hide();   
			break;   
		default:   
			tarObject.value=tarObject.value.replace(/(\d{4})(\d{1,2})/,'$1'+e.innerText);   
			e.style.border="solid   1pt   #c6c6c6"  ;
			popup.hide();   
			break;   
	}   
}   

function   cancellValue(e){
	var   p=e.parentElement;
			tarObject.value='';   
			popup.hide();   
}   

    
function   hidePop(){
	popup.hide();
} 
function   getYM(s){   
	tarObject=s   ;
	//---验证时间是否正确格式
	if(!validChar8date(s.value)) s.value='';
	//--结束验证
	if   (s.value=='') s.value=sYear+''+sMonth   ;
	var   reg=/(\d{4})(\d{1,2})/   ;
	var   r=reg.exec(s.value)   ;
	if   (r!=null){   
		var   e=popup.document.all('yMonth').rows[0]   ;
		for   (var   i=1;i<5;i++){   
			if   (e.cells[i].style.color=='#ff0000'){   
				s.value=e.cells[i].innerText+r[2]   ;
				yearChange(r[1]   -   e.cells[i].innerText);   
				break;   
			}   
		}   
	}   
	var   e=event.srcElement   ;
	popup.show(e.pixelLeft,e.clientHeight+5,196,84,e)   ;
}




//-----------------------------------验证时间开始
    function javaValidNumber(str)
    { 
        tmp = Math.floor(str)
        if (isNaN(tmp))
        {
            return false
        }
        return true
     }

    function validChar8date(str8){
     if (str8 == null) {
                //alert("日期格式不正确,请输入日期");
                return false;
          }
      if (str8.length != 6) {
                //alert("日期长度不正确，正确长度为6个数字字符");
                return false;
          }
          if (!javaValidNumber(str8))
          {
			     //alert("日期格式不正确,日期包含非数字字符");
                return false;
          }
          var str = str8.substring(0,4);
         var  year = parseInt(str);
	if (year>2050 || year<1950) {
		         //alert("日期格式不正确,年度错误，范围为：1950~2050");
                return false;
	}
      str = str8.substring(4,6);
       var   month = parseInt(str);
      if (month>12 || month<1) {
                //alert("日期格式不正确,月份错误");
                return false;
      }
	  return true;
 }
 //------------------------------------验证时间结束