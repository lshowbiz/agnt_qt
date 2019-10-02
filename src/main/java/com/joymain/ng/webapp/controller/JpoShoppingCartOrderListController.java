package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.SearchException;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;

@Controller
@RequestMapping("/jpoShoppingCartOrderLists/")
public class JpoShoppingCartOrderListController {
    private JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager;
    private JpoShoppingCartOrderManager jpoShoppingCartOrderManager;
    @Autowired
    private JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
    private JmiTeamManager jmiTeamManager;
    @Autowired
    private JpmProductSaleNewManager jpmProductSaleNewManager;
    @Autowired
    public void setJpoShoppingCartOrderListManager(JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager) {
        this.jpoShoppingCartOrderListManager = jpoShoppingCartOrderListManager;
    }
    @Autowired
	public void setJpoShoppingCartOrderManager(
			JpoShoppingCartOrderManager jpoShoppingCartOrderManager) {
		this.jpoShoppingCartOrderManager = jpoShoppingCartOrderManager;
	}
    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {

        Model model = new ExtendedModelMap();
        try {
        
            model.addAttribute(jpoShoppingCartOrderListManager.search(query, JpoShoppingCartOrderList.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jpoShoppingCartOrderListManager.getAll());
        }
        return model;
    }
    
    
    @RequestMapping(value="scAll",method = RequestMethod.GET)
    public String scAll(Model model,HttpServletRequest request,HttpServletResponse response)
    throws Exception {
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	JpoShoppingCart jpoShoppingCart=new JpoShoppingCart();
    	jpoShoppingCart.setCompanyCode(jsysUser.getCompanyCode());
    	jpoShoppingCart.setUserCode(jsysUser.getUserCode());
    	List<JpoShoppingCartOrder> scList=jpoShoppingCartOrderManager.getJpoScOrderList(jpoShoppingCart);
    	List jpoMemberList=jpoMemberOrderDao.getJpoMemberMark(jsysUser.getJmiMember().getPapernumber(), "P08520100101CN0","1");
    	boolean b=false;
    	String teamType=jmiTeamManager.teamStr(jsysUser);//获取团队编号
    	Map<String,Object> map1=jpmProductSaleNewManager.getJmiMemberTeamMap("0");  //判断团队是否需要绑定事业锦囊，判断不绑定事业锦囊的团队  0:否
		  Iterator it = map1.entrySet().iterator();
	        while (it.hasNext()) {
	            Map.Entry entry = (Map.Entry) it.next();
	            Object key = entry.getKey();
	            boolean value = (Boolean)entry.getValue();
	            if(value){ //绑定
	            	if(key.equals(teamType))
	            	{	
	            		b = true;
	            		break;
	            	}
	            }
	        }
	        
	    request.setAttribute("effectiveValid",null);
	    if( b == true){
	    	if(jpoMemberList!=null && jpoMemberList.size()>0)
	            //获取产品
	            {
	            	//购买过事业锦囊.不强制购买事业锦囊
	        		request.setAttribute("effectiveValid",true);	
	            }else
	            {
	            	//没购买过事业锦囊，强制购买事业锦囊
	         		request.setAttribute("effectiveValid",false);
	            }
	    }
    	
    	if(scList!=null && scList.size()>0)
    	{
    		request.setAttribute("scList", scList);
    		
    	}
    	List<Map<String, Object>> list = null;//得到数据值 Modify By WuCF 20150202  解决IE8浏览器展示价格PV为0的情况
    	list = jpoMemberOrderDao.getTatalPrice(jsysUser.getUserCode(),"1");
    	Map<String,BigDecimal[]> pricePvMap = new HashMap<String,BigDecimal[]>();
    	BigDecimal priceTotal = new BigDecimal(0);//累加总价格
    	BigDecimal pvTotal = new BigDecimal(0);//累加总PV
    	BigDecimal priceTemp = new BigDecimal(0);//价格取值
    	BigDecimal pvTemp = new BigDecimal(0);//pv取值
    	BigDecimal[] strTemp = null;
    	for(Map<String,Object> map : list){
    		strTemp = new BigDecimal[2];
    		priceTemp = new BigDecimal(String.valueOf(map.get("PRICE_PV")).split("-")[0]);//价格
    		pvTemp = new BigDecimal(String.valueOf(map.get("PRICE_PV")).split("-")[1]);//PV
    		strTemp[0] = priceTemp;
    		strTemp[1] = pvTemp;
    		pricePvMap.put(String.valueOf(map.get("ORDER_TYPE")), strTemp);
    		priceTotal = priceTotal.add(priceTemp);//累加总价格
    		pvTotal = pvTotal.add(pvTemp);//累加总PV
    	}
    	request.setAttribute("pricePvMap", pricePvMap);
    	request.setAttribute("priceTotal", priceTotal);
    	request.setAttribute("pvTotal", pvTotal);
    	return "jpoShoppingCartOrderLists";
    }

}
