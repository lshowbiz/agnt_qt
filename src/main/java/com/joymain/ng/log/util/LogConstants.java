package com.joymain.ng.log.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LogConstants {
	public static List black_list=new ArrayList();
	public static Map tableMap = new HashMap();
	public static Map<String,String> TABLE_PID_MAP = new HashMap();
	public static Map<String,String> TABLE_BID_MAP = new HashMap();
	
	static{
		black_list.add(SysDataLog.class);
//		black_list.add(SysId.class);
//		black_list.add(SysOperationLog.class);
//		black_list.add(SysVisitLog.class);
//		black_list.add(JpmProductSaleLog.class);
//		black_list.add(Jfi99billLog.class);
//		
		TABLE_PID_MAP.put("PdSendInfo", "siNo");
		TABLE_BID_MAP.put("PdSendInfo", "siNo");
		
		TABLE_PID_MAP.put("PdSendInfoDetail", "uniNo");
		TABLE_BID_MAP.put("PdSendInfoDetail", "uniNo");
		
		TABLE_PID_MAP.put("JpmProduct", "productNo");
		TABLE_BID_MAP.put("JpmProduct", "productNo");
		
		TABLE_PID_MAP.put("JpmProductSale", "uniNo");
//		TABLE_BID_MAP.put("JpmProductSale", "productNo");
		
		TABLE_PID_MAP.put("PdFlitWarehouse", "fwNo");
		TABLE_BID_MAP.put("PdFlitWarehouse", "fwNo");
		
		TABLE_PID_MAP.put("PdFlitWarehouseDetail", "uniNo");
		TABLE_BID_MAP.put("PdFlitWarehouseDetail", "fwNo");
		
		TABLE_PID_MAP.put("PdEnterWarehouse", "ewNo");
		TABLE_BID_MAP.put("PdEnterWarehouse", "ewNo");
		
		TABLE_PID_MAP.put("PdEnterWarehouseDetail", "uniNo");
		TABLE_BID_MAP.put("PdEnterWarehouseDetail", "ewNo");
		
		TABLE_PID_MAP.put("PdReturnPurchase", "rpNo");
		TABLE_BID_MAP.put("PdReturnPurchase", "rpNo");
		
		TABLE_PID_MAP.put("PdReturnPurchaseDetail", "uniNo");
		TABLE_BID_MAP.put("PdReturnPurchaseDetail", "rpNo");
		
		
		
	}
}
