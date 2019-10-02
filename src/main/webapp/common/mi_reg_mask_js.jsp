<%@ include file="/common/taglibs.jsp" %>

<c:if test="${pageContext.request.servletPath == '/jmiMemberCreateform' }">
<script>




var jq = jQuery.noConflict();



			function getIdCity(){
				
				var province=document.getElementById('province').value;
	
				var cityElemment=document.getElementById('city');
       			cityElemment.options.length=0;
         		var o=new Option("<ng:locale key="list.please.select"/>","");
          		cityElemment.options.add(o);  

          		
			   var districtElemment=document.getElementById('district');
       			districtElemment.options.length=0;
         		var o=new Option("<ng:locale key="list.please.select"/>","");
          		districtElemment.options.add(o);  
				
				
				
				jq.each(jal_city, function (k, p) { 
					 if (p.state_province_id == province) {
						 var o=new Option(p.city_name,p.city_id);
					     cityElemment.options.add(o);  
					     if(p.city_id=='${jmiMember.city}'){
					        	o.selected=true;
					     }
					 }
				 });
				getIdDistrict();
		   }
		 
		    function getIdDistrict(){
				 var city=document.getElementById('city').value;
				 var districtElemment=document.getElementById('district');
			    		
	        		districtElemment.options.length=0;
	     
	          		var o=new Option("<ng:locale key="list.please.select"/>","");
	           		districtElemment.options.add(o);
				 
	           		jq.each(jal_district, function (k, p) { 
						 if (p.city_id == city) {
							 var o=new Option(p.district_name,p.district_id);
							 districtElemment.options.add(o); 

						   if('${jmiMember.district}'==p.district_id){
						   		o.selected=true;
						   } 
						 }
					 });
		   }
	
		    getIdCity();
		   
			function showMask(){
			    jq('#mask').show().css({
			        height:jq("body").height()
			    });
			}
		  function showMaskTip(){
		 	 var popupReg=	jq("#popupReg");
		 		 showMask();
		        popupReg.css({
		            left:( jq(window).width() - popupReg.outerWidth() ) / 2,
		            top:( jq(window).height() - popupReg.outerHeight() ) / 2 + jq(window).scrollTop()
		        }).animate({
		            opacity:'show'
		        });
   		 }
		    
	 
			function closeDialog(obj){
			
				var mask = jq("#mask");
				
				if( mask ) mask.animate({ opacity:"hide" },600);
				jq(obj).parents("[id^='popup']").animate({ opacity:"hide" },600);
			}
		
		 var popupReg = jq('#popupReg');
	    	popupReg.on('click','#closeMe,#returnMe',function(){
	        closeDialog(this);
	    });
		
		
		
		   getIdCity();
		
<c:if test="${not empty sessionScope.mi_reg_success }">
 	showMaskTip();
		<c:remove var="mi_reg_success" scope="session" />
</c:if>
		  
	<%--	  
		var arr = new Array();
		  
		  
	 jq('form :input').blur(function(){
	 		
            var parent = jq(this).parent();
            
            if( jq(this).is('#recommendNo') ){
            		if(this.value==''){
                       var errorMsg = '<ng:locale key="errors.required" args="miMember.recommendNo" argTransFlag="true"/>';
                       arr.push(errorMsg);
            		}
            }
			if( jq(this).is('#linkNo') ){
            		if(this.value==''){
                       var errorMsg = '<ng:locale key="errors.required" args="miMember.linkNo" argTransFlag="true"/>';
                       arr.push(errorMsg);
            		}
            }
			
            
       }).focus(function(){
          jq(this).triggerHandler("blur");
		  var label = jq("#label_" + jq(this)[0].id)[0];
		  if(label) {
            label.style.display = "none";
          }
       });
       
        jq('#js_submit').click(function(){
        		jq("form .formerror").trigger('blur');
	            var numError = arr.length;
	           
	            if(numError>0){
	            	var msgout='';
	            	 for(i = 0; i < arr.length; i++) {
	            	 	msgout+=arr[i]+'\n';
	            	 }
	            	 alert(msgout);
	            	 arr.length = 0;
	            	 return false;
	            }else{
	            
	            }
        }); 
		  
--%></script>	
    </c:if>
<c:if test="${pageContext.request.servletPath == '/jmiMemberCreateformML' }">
<script>




var jq = jQuery.noConflict();



			function getIdCity(){
				
				var province=document.getElementById('province').value;
	
				var cityElemment=document.getElementById('city');
       			cityElemment.options.length=0;
         		var o=new Option("<ng:locale key="list.please.select"/>","");
          		cityElemment.options.add(o);  

          		
			   var districtElemment=document.getElementById('district');
       			districtElemment.options.length=0;
         		var o=new Option("<ng:locale key="list.please.select"/>","");
          		districtElemment.options.add(o);  
				
				
				
				jq.each(jal_city, function (k, p) { 
					 if (p.state_province_id == province) {
						 var o=new Option(p.city_name,p.city_id);
					     cityElemment.options.add(o);  
					     if(p.city_id=='${jmiMember.city}'){
					        	o.selected=true;
					     }
					 }
				 });
				getIdDistrict();
		   }
		 
		    function getIdDistrict(){
				 var city=document.getElementById('city').value;
				 var districtElemment=document.getElementById('district');
			    		
	        		districtElemment.options.length=0;
	     
	          		var o=new Option("<ng:locale key="list.please.select"/>","");
	           		districtElemment.options.add(o);
				 
	           		jq.each(jal_district, function (k, p) { 
						 if (p.city_id == city) {
							 var o=new Option(p.district_name,p.district_id);
							 districtElemment.options.add(o); 

						   if('${jmiMember.district}'==p.district_id){
						   		o.selected=true;
						   } 
						 }
					 });
		   }
	
		    getIdCity();
		   
			function showMask(){
			    jq('#mask').show().css({
			        height:jq("body").height()
			    });
			}
		  function showMaskTip(){
		 	 var popupReg=	jq("#popupReg");
		 		 showMask();
		        popupReg.css({
		            left:( jq(window).width() - popupReg.outerWidth() ) / 2,
		            top:( jq(window).height() - popupReg.outerHeight() ) / 2 + jq(window).scrollTop()
		        }).animate({
		            opacity:'show'
		        });
   		 }
		    
	 
			function closeDialog(obj){
			
				var mask = jq("#mask");
				
				if( mask ) mask.animate({ opacity:"hide" },600);
				jq(obj).parents("[id^='popup']").animate({ opacity:"hide" },600);
			}
		
		 var popupReg = jq('#popupReg');
	    	popupReg.on('click','#closeMe,#returnMe',function(){
	        closeDialog(this);
	    });
		
		
		
		   getIdCity();
		
<c:if test="${not empty sessionScope.mi_reg_success }">
 	showMaskTip();
		<c:remove var="mi_reg_success" scope="session" />
</c:if>
		  
	<%--	  
		var arr = new Array();
		  
		  
	 jq('form :input').blur(function(){
	 		
            var parent = jq(this).parent();
            
            if( jq(this).is('#recommendNo') ){
            		if(this.value==''){
                       var errorMsg = '<ng:locale key="errors.required" args="miMember.recommendNo" argTransFlag="true"/>';
                       arr.push(errorMsg);
            		}
            }
			if( jq(this).is('#linkNo') ){
            		if(this.value==''){
                       var errorMsg = '<ng:locale key="errors.required" args="miMember.linkNo" argTransFlag="true"/>';
                       arr.push(errorMsg);
            		}
            }
			
            
       }).focus(function(){
          jq(this).triggerHandler("blur");
		  var label = jq("#label_" + jq(this)[0].id)[0];
		  if(label) {
            label.style.display = "none";
          }
       });
       
        jq('#js_submit').click(function(){
        		jq("form .formerror").trigger('blur');
	            var numError = arr.length;
	           
	            if(numError>0){
	            	var msgout='';
	            	 for(i = 0; i < arr.length; i++) {
	            	 	msgout+=arr[i]+'\n';
	            	 }
	            	 alert(msgout);
	            	 arr.length = 0;
	            	 return false;
	            }else{
	            
	            }
        }); 
		  
--%></script>	
    </c:if>
    
    