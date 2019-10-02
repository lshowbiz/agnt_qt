
// 商品购买数量 

var setAmount = {
    min:1,					// 最小为 0
    max:99999,					// 最大数量为商品库存
	
    reg:function(x) {
        return new RegExp("^[0-9]\\d*$").test(x);
    },
    amount:function(obj, mode) {
      
        var x = $(obj).val();

        if (this.reg(x)) {
            if (mode) {
                x++;
            } else {
                x--;
            }
            
        } else {
            alert("请输入正确的数量！");
            $(obj).val(0);
            $(obj).focus();
        }
        return x;
    },
    reduce:function(obj,orderType,x) {
     
        var x = this.amount(obj, false);
        var sclId=obj.split('_')[2];
        if (x >= this.min) {
            $(obj).val(x);
             dChangeValue(obj,orderType,x);
             changeQtyClick(sclId,x);
        } else {
			return false;
        }
    },
    add:function(obj,orderType) {
   
       var sclId=obj.split('_')[2];
        var x = this.amount(obj, true);
      
    
        if (x <= this.max) {
            $(obj).val(x);
            dChangeValue(obj,orderType,x);
            changeQtyClick(sclId,x);
        } 
        
      
       
    }
};


$(function () {
    $("input[name^=num_item_]").bind("blur", function(){
		var it = $(this);
		var x = it.val();
		if( x.length > 5 || ! RegExp("^[0-9]\\d*$").test(x) || x==0 ){
		    alert("请输入您购买的数量不允许为0或负数不能大于99999");
			it.val(1);
            it.focus();
		};
		
	});

});
$(function () {
    $("input[name^=num_item_]").bind("focus", function(){
   
		var it = $(this);
		var x = it.val();
		if(x>0)
		{
		    $("input[id^=hidden_num_item_]").val(x);
		}
		
	});

});

function dChangeValue(obj,orderType,x)
{     
        
        var id=obj.split('_')[2];
        var tdPrice=$("#tr_"+id).find("td").eq(4).find("span").eq(0).html();
        var tdPv=$("#tr_"+id).find("td").eq(5).find("span").eq(0).html();
        var tdTotalPrice=$("#tr_"+id).find("td").eq(7).find("span").eq(0).html();
        
        var tdTotalPv=$("#tr_"+id).find("td").eq(8).html();
        var formatPrice=parseFloat(x)*parseFloat(tdPrice);//格式化数据  价格合计
        var formatPv=parseFloat(x)*parseFloat(tdPv);//格式化数据   pv合计

        $("#tr_"+id).find("td").eq(7).find("span").eq(0).html(formatPrice.toFixed(2));
        $("#tr_"+id).find("td").eq(8).html(formatPv.toFixed(2));
      
        var aftdTotalPrice= $("#tr_"+id).find("td").eq(7).find("span").eq(0).html();
        var aftdTotalPv=$("#tr_"+id).find("td").eq(8).html();
        
        var tableTotalPrice=$("#orderCount_"+orderType).find("td").eq(1).html();
        var tableTotalPv=$("#orderCount_"+orderType).find("td").eq(4).html();
        var formatOrderCountPrice=parseFloat(tableTotalPrice)-parseFloat(tdTotalPrice)+parseFloat(aftdTotalPrice);  //格式化数据  订单价格合计
        var formatOrderCountPv=parseFloat(tableTotalPv)-parseFloat(tdTotalPv)+parseFloat(aftdTotalPv);   //格式化数据  订单pv合计
        $("#orderCount_"+orderType).find("td").eq(1).html(formatOrderCountPrice.toFixed(2));
        $("#orderCount_"+orderType).find("td").eq(4).html(formatOrderCountPv.toFixed(2));

       if($("#label_"+orderType).find(':checkbox').prop("checked"))
       {

         var allCountPrice=$("#priceTotalId");
         var allCOuntPv=$("#pvTotalId");
         var formatAllCountPrice=parseFloat(allCountPrice.html())-parseFloat(tdTotalPrice)+parseFloat(aftdTotalPrice);//格式化数据  所有要购买的订单价格合计
         var formatAllCOuntPv=parseFloat(allCOuntPv.html())-parseFloat(tdTotalPv)+parseFloat(aftdTotalPv);//格式化数据  所有要购买的订单pv合计
         allCountPrice.html(formatAllCountPrice.toFixed(2));
         allCOuntPv.html(formatAllCOuntPv.toFixed(2));
       }
       if(!orderType){
    	   $(obj).trigger("change");
       }
   	
    
}

function changeQty(obj,orderType,sclId)
{
   var qty=$(obj).val();
  
   if( qty.length <= 5 && RegExp("^[0-9]\\d*$").test(qty) && qty!=0)
    {
		   dChangeValue(obj,orderType,qty);
		   changeQtyClick(sclId,qty);//调用dwr修改数量
    }
}



