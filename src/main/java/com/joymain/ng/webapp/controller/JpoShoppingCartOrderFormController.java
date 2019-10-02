package com.joymain.ng.webapp.controller;

import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoShoppingCartOrderListManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/jpoShoppingCartOrderform*")
public class JpoShoppingCartOrderFormController extends BaseFormController {
	@Autowired
    private JpoShoppingCartOrderManager jpoShoppingCartOrderManager;
    @Autowired
    private JpoShoppingCartOrderListManager jpoShoppingCartOrderListManager;
    @Autowired
    private JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
    private JmiTeamManager jmiTeamManager;
    @Autowired
    private JpmProductSaleNewManager jpmProductSaleNewManager;
    
  /*  public void setJpoShoppingCartOrderManager(JpoShoppingCartOrderManager jpoShoppingCartOrderManager) {
        this.jpoShoppingCartOrderManager = jpoShoppingCartOrderManager;
    }*/

    public JpoShoppingCartOrderFormController() {
        setCancelView("redirect:jpoShoppingCartOrders");
        setSuccessView("redirect:jpoShoppingCartOrders");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoShoppingCartOrder showForm(HttpServletRequest request)
    throws Exception {
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	HttpSession session=request.getSession(); 
    	JpoShoppingCartOrder jpoShoppingCartOrder=new JpoShoppingCartOrder();
    	JpoShoppingCart jpoShoppingCart=new JpoShoppingCart();
         //获取订单类型
    	String orderType=request.getParameter("orderType");
    	String orderPv1=request.getParameter("orderPv");
    	
    	//Modify By WuCF 20160428商品展示新增查询条件
    	String minPrice = request.getParameter("minPrice");//价格
    	String maxPrice = request.getParameter("maxPrice");
    	String minPv = request.getParameter("minPv");//PV
    	String maxPv = request.getParameter("maxPv");
    	String productNameStr = request.getParameter("productNameStr");//名称
    	
    	String priceOrder = "".equals(request.getParameter("priceOrder"))?"asc":request.getParameter("priceOrder");//按价格升降排序
    	
    	//首购单时获取pv数进行判断
    	int  orderPv=0;
    	if(!StringUtil.isEmpty(request.getParameter("orderPv")) && !"null".equals(request.getParameter("orderPv")))
    	   {
    	      orderPv=Integer.parseInt(request.getParameter("orderPv"));
    	   }
    	log.info("orderPv1 is:"+orderPv1+" and orderPv is:"+orderPv);
    	
    	String linkType=request.getParameter("linkType");
    	String showId=request.getParameter("showId");
    	//获取系列类型
    	String   categoryIds=request.getParameter("categoryIds");
    	
    	boolean b = false;
    	String teamType=jmiTeamManager.teamStr(jsysUser);//获取团队编号
    	//会员首单判断是否购买了事业锦囊
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	HashMap<String, ArrayList<JpmProductSaleTeamType>>  productList=null;
    	HashMap<String, ArrayList<JpmProductSaleTeamType>> categoryList = null;//系列分类
    	
    	Map<String,Object> map=jpmProductSaleNewManager.getJmiMemberTeamMap("0");  //判断团队是否需要绑定事业锦囊，判断不绑定事业锦囊的团队  0:否
		  Iterator it = map.entrySet().iterator();
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
	            		
    	if("1".equals(orderType))
    	{
    		//没有购买事业锦囊,不展示事业锦囊跟辅销品
    		request.setAttribute("showProduct", false);
    		
    		List jpoMemberList=jpoMemberOrderDao.getJpoMemberMark(defSysUser.getJmiMember().getPapernumber(), "P08520100101CN0",orderType);
	    	if(jpoMemberList!=null && jpoMemberList.size()>0)
	    	//获取产品
	    	{
	    		if(b==true){
	    			//购买过事业锦囊,展示辅销品跟事业锦囊
			    	request.setAttribute("showProduct", true);
	    		}
	    		productList=jpoShoppingCartOrderListManager.getProductByOrderTypeProNew(orderType,jsysUser,categoryIds,"1",minPrice,maxPrice,minPv,maxPv,productNameStr);
	    		categoryList=jpoShoppingCartOrderListManager.getProductByOrderTypeProNew(orderType,jsysUser,null,"1",null,null,null,null,null);
	    	}
	    	else
	    	{
	    		productList=jpoShoppingCartOrderListManager.getProductByOrderTypeProNew(orderType,jsysUser,categoryIds,"0",minPrice,maxPrice,minPv,maxPv,productNameStr);
	    		categoryList=jpoShoppingCartOrderListManager.getProductByOrderTypeProNew(orderType,jsysUser,null,"0",null,null,null,null,null);
	    	}
    	}else
    	{ 	
    	      productList=jpoShoppingCartOrderListManager.getProductByOrderTypeProNew(orderType,jsysUser,categoryIds,null,minPrice,maxPrice,minPv,maxPv,productNameStr);
    	      categoryList=jpoShoppingCartOrderListManager.getProductByOrderTypeProNew(orderType,jsysUser,null,null,null,null,null,null,null);
    	}
    	//获取会员购物车中购买的商品总数量
    	jpoShoppingCart.setCompanyCode(jsysUser.getCompanyCode());
    	jpoShoppingCart.setUserCode(jsysUser.getUserCode());
    	int total=jpoShoppingCartOrderManager.getShoppinigCartSum(jpoShoppingCart);
//    	String teamType=jmiTeamManager.teamStr(jsysUser);//获取团队编号
    	jpoShoppingCartOrder.setOrderType(orderType);
    	jpoShoppingCartOrder.setTeamType(teamType);//获取团队编号
    	jpoShoppingCartOrder.setSysUser(jsysUser);
        request.setAttribute("productList", productList);
        request.setAttribute("categoryList", categoryList);
        
        //重新组装productList
        List pmList=new ArrayList();
        Iterator iter = productList.entrySet().iterator();
        while (iter.hasNext()) {
        	Map.Entry entry = (Map.Entry) iter.next();
        	Object key = entry.getKey();
        	Object val = entry.getValue();
        	pmList.addAll((ArrayList)val);
        }
        //添加按价格升降排序
        if(priceOrder!=null&&"asc".equals(priceOrder)){
	        Collections.sort(pmList, new Comparator<JpmProductSaleTeamType>(){  
	        	  
	            /*  
	             * int compare(JpmProductSaleTeamType o1, JpmProductSaleTeamType o2) 返回一个基本类型的整型，  
	             * 返回负数表示：o1 小于o2，  
	             * 返回0 表示：o1和o2相等，  
	             * 返回正数表示：o1大于o2。  
	             */  
	            public int compare(JpmProductSaleTeamType o1, JpmProductSaleTeamType o2) {  
	              
	                //按价格升序排列  
	                if(o1.getPrice().compareTo(o2.getPrice())==1){  
	                    return 1;
	                }  
	                if(o1.getPrice().compareTo(o2.getPrice())==0){  
	                    return 0;
	                }  
	                return -1;
	            }  
	        });
	        priceOrder = "desc";
        }else if(priceOrder!=null&&"desc".equals(priceOrder)){
        	Collections.sort(pmList, new Comparator<JpmProductSaleTeamType>(){  
	        	  
	            /*  
	             * int compare(JpmProductSaleTeamType o1, JpmProductSaleTeamType o2) 返回一个基本类型的整型，  
	             * 返回负数表示：o1 小于o2，  
	             * 返回0 表示：o1和o2相等，  
	             * 返回正数表示：o1大于o2。  
	             */  
	            public int compare(JpmProductSaleTeamType o1, JpmProductSaleTeamType o2) {  
	              
	                //按价格降序排列  
	                if(o1.getPrice().compareTo(o2.getPrice())==1){  
	                    return -1;
	                }  
	                if(o1.getPrice().compareTo(o2.getPrice())==0){  
	                    return 0;
	                }  
	                return 1;
	            }  
	        });
	        priceOrder = "asc";
        }
        request.setAttribute("pmList", pmList);
        request.setAttribute("priceOrder", priceOrder);
        //
        
        request.setAttribute("linkType", linkType);//链接判断
        request.setAttribute("showId", showId);
//        session.setAttribute("orderPv", orderPv);
        request.setAttribute("total", total);   
        request.setAttribute("orderType", orderType);//订单类型
        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("minPv", minPv);
        request.setAttribute("maxPv", maxPv);
        request.setAttribute("productNameStr", productNameStr);
        request.setAttribute("categoryIds", categoryIds);
        return jpoShoppingCartOrder;
    }

    
    
    //异步列出是否存在跟商品类型对应的推荐商品   add by hdg 2016-09-14
    @RequestMapping(value="getrecommendProduct")
    @ResponseBody
    public String getrecommendProduct(HttpServletRequest request) {
    	
    	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         //获取订单类型
    	String orderType=request.getParameter("orderType");
    	
    	String isRecommand = "1";
    	List<JpmProductSaleTeamType> recommendProductList = 
    			jpoShoppingCartOrderListManager.getRecommendProductList(jsysUser,isRecommand, orderType);
    	
    	return null;
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoShoppingCartOrder jpoShoppingCartOrder, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpoShoppingCartOrder, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpoShoppingCartOrderform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpoShoppingCartOrder.getScId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpoShoppingCartOrderManager.remove(jpoShoppingCartOrder.getScId());
            saveMessage(request, getText("jpoShoppingCartOrder.deleted", locale));
        } else {
            jpoShoppingCartOrderManager.save(jpoShoppingCartOrder);
            String key = (isNew) ? "jpoShoppingCartOrder.added" : "jpoShoppingCartOrder.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpoShoppingCartOrderform?scId=" + jpoShoppingCartOrder.getScId();
            }
        }

        return success;
    }
}
