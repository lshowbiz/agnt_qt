package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpmProductSaleNew;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PdExchangeOrder;
import com.joymain.ng.model.PdExchangeOrderBack;
import com.joymain.ng.model.PdExchangeOrderDetail;
import com.joymain.ng.model.PdNotChangeProduct;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.PdExchangeOrderBackManager;
import com.joymain.ng.service.PdExchangeOrderDetailManager;
import com.joymain.ng.service.PdExchangeOrderManager;
import com.joymain.ng.service.PdNotChangeProductManager;
import com.joymain.ng.service.SysIdManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.webapp.util.RequestUtil;

@Controller
@RequestMapping("/pdExchangeOrderform*")
public class PdExchangeOrderFormController extends BaseFormController {
    private PdExchangeOrderManager pdExchangeOrderManager = null;
    
    private JpoMemberOrderManager jpoMemberOrderManager;
    
    private JpoMemberOrderListManager jpoMemberOrderListManager;
    
	private JmiAddrBookManager jmiAddrBookManager;
    private JalStateProvinceManager jalStateProvinceManager;
    
    private JpmProductSaleNewManager  jpmProductSaleNewManager;
    
    private SysIdManager sysIdManager;
    
    private PdNotChangeProductManager pdNotChangeProductManager;
    
    private PdExchangeOrderBackManager pdExchangeOrderBackManager;
    
    private PdExchangeOrderDetailManager pdExchangeOrderDetailManager;
    
    private JmiMemberManager jmiMemberManager;
    
    private JmiTeamManager jmiTeamManager;
    
    @Autowired
    public void setJmiTeamManager(JmiTeamManager jmiTeamManager) {
		this.jmiTeamManager = jmiTeamManager;
	}
    
    @Autowired
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
    @Autowired
    public void setJpoMemberOrderListManager(
    		JpoMemberOrderListManager jpoMemberOrderListManager) {
    	this.jpoMemberOrderListManager = jpoMemberOrderListManager;
    }
    @Autowired
	public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
		this.jmiAddrBookManager = jmiAddrBookManager;
	}

    @Autowired
	public void setJalStateProvinceManager(
			JalStateProvinceManager jalStateProvinceManager) {
		this.jalStateProvinceManager = jalStateProvinceManager;
	}

    @Autowired
	public void setJpmProductSaleNewManager(
			JpmProductSaleNewManager jpmProductSaleNewManager) {
		this.jpmProductSaleNewManager = jpmProductSaleNewManager;
	}

    @Autowired
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

    
    @Autowired
	public void setPdNotChangeProductManager(
			PdNotChangeProductManager pdNotChangeProductManager) {
		this.pdNotChangeProductManager = pdNotChangeProductManager;
	}

    @Autowired
	public void setPdExchangeOrderBackManager(
			PdExchangeOrderBackManager pdExchangeOrderBackManager) {
		this.pdExchangeOrderBackManager = pdExchangeOrderBackManager;
	}

    @Autowired
	public void setPdExchangeOrderDetailManager(
			PdExchangeOrderDetailManager pdExchangeOrderDetailManager) {
		this.pdExchangeOrderDetailManager = pdExchangeOrderDetailManager;
	}

    @Autowired
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	@Autowired
    public void setPdExchangeOrderManager(PdExchangeOrderManager pdExchangeOrderManager) {
        this.pdExchangeOrderManager = pdExchangeOrderManager;
    }

    public PdExchangeOrderFormController() {
        setCancelView("redirect:pdExchangeOrders");
        setSuccessView("redirect:pdExchangeOrders");
    }
    
//    /pdExchangeOrderform/queryExchangeOrderList
    @RequestMapping(value="/queryExchangeOrderList",method=RequestMethod.GET)
    public String queryExchangeOrderList(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String returnView = "pdExchangeOrderList";
    	//获取当前登录用户的信息
		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//获取会员的编号
		String userCode = defSysUser.getUserCode();

    	String eoNo = request.getParameter("eoNo");
    	String orderNo = request.getParameter("orderNo");
    	String isPay = request.getParameter("isPay");
    	String startLogTime = request.getParameter("startLogTime");
    	String endLogTime = request.getParameter("endLogTime");
    	//处理字符串
    	eoNo = StringUtil.dealStr(eoNo);
    	orderNo = StringUtil.dealStr(orderNo);
    	isPay = StringUtil.dealStr(isPay);
    	startLogTime = StringUtil.dealStr(startLogTime);
    	endLogTime = StringUtil.dealStr(endLogTime);
    	
    	if(!MeteorUtil.isBlank(startLogTime)){
    		request.setAttribute("startLogTime", "startLogTime");
    		startLogTime = startLogTime + " 00:00:00";
    	}
    	if(!MeteorUtil.isBlank(endLogTime)){
    		request.setAttribute("endLogTime", "endLogTime");
    		endLogTime = endLogTime + " 23:59:59";
    	}
    	
    	String parma = "&eoNo="+eoNo+"&orderNo="+orderNo+"&isPay="+isPay+"&startLogTime="+startLogTime+"&endLogTime="+endLogTime+"&companyCode=CN&selfReplacement=Y";
    	//分页单位：固定写法
    	Integer pageSize = StringUtil.dealPageSize(request);
    	
		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
		//GroupPage page = new GroupPage("","pdExchangeOrderform/queryExchangeOrderList?userCode="+userCode+parma,pageSize,request);
    	GroupPage page = new GroupPage("pdExchangeOrderform","queryExchangeOrderList?userCode="+userCode+parma+"&pageSize="+pageSize,pageSize,request);
    	
		CommonRecord crm = new CommonRecord();
		crm.addField("userCode", userCode);
		crm.addField("eoNo", eoNo);
		crm.addField("orderNo", orderNo);
		crm.addField("isPay", isPay);
		crm.addField("startLogTime", startLogTime);
		crm.addField("endLogTime", endLogTime);
		
		crm.addField("companyCode", "CN");
		crm.addField("selfReplacement", "Y");
		//
    	//List pdExchangeOrders = pdExchangeOrderManager.getPdExchangeOrdersByCrm(crm,page);
    	List pdExchangeOrders = pdExchangeOrderManager.getPdExchangeOrdersByCrmSql(crm,page);
    	
    	request.setAttribute("page", page);
    	
    	//像页面传递值－这里应该是能分页的集合,因为客户人数不是很多，暂时不给分页
    	request.setAttribute("pdExchangeOrders", pdExchangeOrders);
    	
	    return returnView;
    }
    
    /**
     * 换货单制单页面的初始化和选择新商品
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/orderSelfHelpExchange",method = RequestMethod.GET)
    public String showPdExchangeOrderForm(Model model,HttpServletRequest request,HttpServletResponse response)
    	    throws Exception{
		String returnView = "pdExchangeOrderform";
		
		//初始化登录的一些基本信息，例如：登录用户，地址，会员信息
		 JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	 //登录账号
		 String userCode = loginSysUser.getUserCode();
		 //公司编码
	  	 String companyCode = loginSysUser.getCompanyCode();
	  	 request.setAttribute("userCode", userCode);
	  	 request.setAttribute("companyCode", companyCode);
	  	    
	  	 //获取地址信息
	  	 List<JmiAddrBook> addressList=jmiAddrBookManager.getJmiAddrBookByUserCode(userCode);  
	     List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(companyCode);
	     request.setAttribute("alStateProvinces", alStateProvinces);
	     //request.setAttribute("addressList", addressList);
	  	    
	     //获得会员信息
	     JmiMember jmiMember = jmiMemberManager.get(userCode);
	     request.setAttribute("jmiMember", jmiMember);
		
		String strAction = request.getParameter("strAction");
		//换货单制单初始化页面
		if("addPdExchangeOrderInit".equals(strAction)){
			
			//获取原订单的信息
		    String moId = request.getParameter("moId");//订单号
		    JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		    
		    if(!Strings.isNullOrEmpty(moId)){
		    	jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(moId));
		    }
		    request.setAttribute("jpoMemberOrder", jpoMemberOrder);
		    //根据原订单得到原订单的地址信息
			JmiAddrBook jmiAddrBook = this.assembleJmiAddrBook(jpoMemberOrder);
		    
		    //根据原订单中的地址信息重置制单初始化页面的默认地址信息
			resetDefaultAddr(addressList, jmiAddrBook);
			
		    request.setAttribute("addressList", addressList);
		    
		    //根据原订单编号得到原订单的所有商品
         	Set<JpoMemberOrderList> set = jpoMemberOrder.getJpoMemberOrderList();
         	List<JpoMemberOrderList> jpoMemberOrderLists = Lists.newArrayList(set);
         	
         	//不可换货标志位，“Y”表示不可换货，"N"表示可以换货
         	String notChangeProductFlag = "";
         	if(jpoMemberOrderLists != null && jpoMemberOrderLists.size()>0){
         		for (JpoMemberOrderList jpoMemberOrderList : jpoMemberOrderLists) {
         			
         			//判断原订单商品列表中的每一条商品是否是不可换货商品
					if(jpoMemberOrderListManager.isNotExchangeProduct(jpoMemberOrderList)){
						notChangeProductFlag = "Y";
					}else{
						notChangeProductFlag = "N";
					}
					//给每一个原订单商品加上不可换货的标志位
					jpoMemberOrderList.setNotChangeProductFlag(notChangeProductFlag);
				}
         		
         	}
         	request.setAttribute("jpoMemberOrderLists", jpoMemberOrderLists);
         	
         	//原订单中不可换货的商品需要在换货商品列表中显示
         	//得到原订单中所有不能换货的商品列表，这些商品要保存到pd_exchange_order_detail表中
     		List<JpoMemberOrderList> jpoMemberOrderListNotChange = new ArrayList<JpoMemberOrderList>();
     		for (JpoMemberOrderList jpoMemberOrderList : jpoMemberOrderLists) {
     			//不可换商品
				if("Y".equals(jpoMemberOrderList.getNotChangeProductFlag())){
					jpoMemberOrderListNotChange.add(jpoMemberOrderList);
				}
			}
     		request.setAttribute("jpoMemberOrderListNotChange", jpoMemberOrderListNotChange);
     		
     		//换货单制单页面初始化完成之后，跳转到换货单制单页面pdExchangeOrderform.jsp
     		return returnView;
    	    
    	}
		//选择新商品
		else if("selectNewProduct".equals(strAction)){
    		
//    		CommonRecord crm = RequestUtil.toCommonRecord(request);
    		//分页查询所有的商品信息
    		//----------------------Modify By WuCF 添加分页展示功能 
			//分页单位：固定写法
	    	Integer pageSize = StringUtil.dealPageSize(request);
	    	
    		//换货商品来源
			String productSource="N";//换货商品来源于选择新商品
    		request.setAttribute("productSource", productSource);
			
    		//Integer pageSize = 20;		//默认每页显示20条记录
    		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
    		String orderNo = request.getParameter("orderNo");
    		String orderType = "";
    		String teamCode = "";
    		if(!Strings.isNullOrEmpty(orderNo)){
    			//从原订单中获取的team_code和order_type
    			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderNo);
    			if(jpoMemberOrder != null){
    				//以前的团队编号从原订单里面获取
    				//teamCode = jpoMemberOrder.getTeamCode();
    				//需求变更：团队产品换货设置以会员所属最新团队的规则为准
    				teamCode = jmiTeamManager.teamStr(loginSysUser);
    				orderType = jpoMemberOrder.getOrderType();
    			}
    		}
    		request.setAttribute("jpoMemberOrder", jpoMemberOrder);
    		
    		//创建分页数据对象，传递URL和分页单位：一级目录(如果没有则传空字符串)、 二级目录、分页单位
    		GroupPage page = new GroupPage("pdExchangeOrderform","orderSelfHelpExchange?strAction=selectNewProduct&orderNo="+orderNo+"&pageSize="+pageSize,pageSize,request);
        	//GroupPage page = new GroupPage("pdExchangeOrderform","orderSelfHelpExchange?strAction=selectNewProduct&moId"+moId+"&pageSize="+pageSize,pageSize,request);
    		request.setAttribute("page", page);
    			
    		//1.分页数据
    		//查询该订单下可以换货的商品信息
    	    List<JpmProductSaleTeamType> jpmProductSaleTeamTypeList = jpmProductSaleNewManager.getJpmProductSaleTeamTypeListPage(page,orderType,teamCode);
    	    request.setAttribute("jpmProductSaleTeamTypeList", jpmProductSaleTeamTypeList);
    	    //分页时依然保存到orderNo 这个参数
    	    request.setAttribute("orderNo", orderNo);
    		
    		//跳转到选择商品的页面pdProductSelect.jsp
    		return "pdProductSelect";
    	} 	
		//	pdExchangeOrderform/orderSelfHelpExchange?strAction=editPdExchangeOrderInit	
		//换货单编辑初始化页面
    	else if("editPdExchangeOrderInit".equals(strAction)){
    	
    		//获取换货单号和原订单编号
    		String eoNo = request.getParameter("eoNo");		//换货单号
    		String orderNo = request.getParameter("orderNo");	//原订单编号
    		PdExchangeOrder pdExchangeOrder = new PdExchangeOrder();
    		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
    		
    		Set<PdExchangeOrderDetail> pdExchangeOrderDetails = new HashSet<PdExchangeOrderDetail>();
    		Set<PdExchangeOrderBack> pdExchangeOrderBacks = new HashSet<PdExchangeOrderBack>();
    		List<PdExchangeOrderDetail> pdExchangeOrderDetailList = new ArrayList<PdExchangeOrderDetail>();
    		List<PdExchangeOrderBack> pdExchangeOrderBackList = new ArrayList<PdExchangeOrderBack>();
    		
    		if(!Strings.isNullOrEmpty(orderNo)){
    			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderNo);
    		}
    		request.setAttribute("jpoMemberOrder", jpoMemberOrder);
    		
    		//展示换货单的信息
    		if(!Strings.isNullOrEmpty(eoNo)){
    			//得到换货单信息
    			pdExchangeOrder = pdExchangeOrderManager.getPdExchangeOrder(eoNo);
    			request.setAttribute("pdExchangeOrder", pdExchangeOrder);
    			
    			//得到换货单中的地址信息
    			JmiAddrBook jmiAddrBook = this.assembleJmiAddrBook(pdExchangeOrder);
    			//request.setAttribute("jmiAddrBook", jmiAddrBook);
    		    
    		    //根据换货单中的地址信息重置初始化地址信息
    			resetDefaultAddr(addressList,jmiAddrBook);
    		    request.setAttribute("addressList", addressList);
    			
    			//得到换货单商品信息
    			pdExchangeOrderDetails = pdExchangeOrder.getPdExchangeOrderDetails();
    			pdExchangeOrderBacks = pdExchangeOrder.getPdExchangeOrderBacks();
    			if(pdExchangeOrderDetails != null && pdExchangeOrderDetails .size()>0){
    				pdExchangeOrderDetailList = Lists.newArrayList(pdExchangeOrderDetails);
    			}
    			if(pdExchangeOrderBacks != null && pdExchangeOrderBacks.size() > 0){
    				pdExchangeOrderBackList = Lists.newArrayList(pdExchangeOrderBacks);
    			}
    			
    			request.setAttribute("pdExchangeOrderDetailList", pdExchangeOrderDetailList);
    			request.setAttribute("pdExchangeOrderBackList", pdExchangeOrderBackList);
    			
             	//2.获得商品编码和名称明细
    	    	Map<String,String> jpmProductNewMap = jpmProductSaleNewManager.getJpmProductSaleNoAndName("0,1,2");
    	    	request.setAttribute("jpmProductNewMap", jpmProductNewMap);
    	    	
    		}
    		//跳转到换货单编辑的页面pdExchangeOrderformEdit.jsp
    		return "pdExchangeOrderformEdit";
    	}
    	
    	return returnView;
    }
    
    
    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdExchangeOrder showForm(HttpServletRequest request)
    throws Exception {
        String eoNo = request.getParameter("eoNo");

        if (!StringUtils.isBlank(eoNo)) {
            return pdExchangeOrderManager.get(new String(eoNo));
        }
        return new PdExchangeOrder();
    }

    
    @SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdExchangeOrder pdExchangeOrder, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
    /*	if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
        if (validator != null) { // validator is null during testing
            validator.validate(pdExchangeOrder, errors);
            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdExchangeOrderform";
            }
        }
        if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		*/
        
    	 //获取用户登录的信息
  	    JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  	    String userCode = loginSysUser.getUserCode();
  	    String companyCode = loginSysUser.getCompanyCode();
  	    request.setAttribute("userCode", userCode);
  	    request.setAttribute("companyCode", companyCode);
  	    
  	    //获取地址信息
  	    List addressList=jmiAddrBookManager.getJmiAddrBookByUserCode(userCode);  
      	List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(companyCode);
      	request.setAttribute("alStateProvinces", alStateProvinces);
        request.setAttribute("addressList", addressList);
  	    
        //获得会员信息
        JmiMember jmiMember = jmiMemberManager.get(userCode);
        request.setAttribute("jmiMember", jmiMember);
        
        List errorMsgs = new ArrayList();
        
        boolean isNew = (pdExchangeOrder.getEoNo() == null);
        Locale locale = request.getLocale();
        String key = null;
        
        //获取用户登录的信息
        String strAction = request.getParameter("strAction");
        //获取原订单的信息
	    
	    
	    //保存成功后跳转到换货单制单的页面
        String view = "";
        
        //获得表单地址栏的  地址的id
        String fabId=request.getParameter("fabName");
        JmiAddrBook addrBook = new JmiAddrBook();
        
        String bingProduct = request.getParameter("bingProduct");
    	if(bingProduct == null){ bingProduct = "";}
    	
        if(!Strings.isNullOrEmpty(fabId)){
        	addrBook = jmiAddrBookManager.get(new Long(fabId));
        }
        
        //自助换货制单
        if("addPdExchangeOrder".equals(strAction)){
        	String moId = request.getParameter("moId");//订单号
    	    JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
    	    
    	    if(!Strings.isNullOrEmpty(moId)){
    	    	jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(moId));
    	    }
    	    request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        	
    	    jpoMemberOrder.setExchangeFlag("1");
            jpoMemberOrderManager.save(jpoMemberOrder);
    	    
        	key="pdExchangeOrder.add";

        	//根据原订单编号得到原订单的所有商品
         	Set<JpoMemberOrderList> set = jpoMemberOrder.getJpoMemberOrderList();
         	List<JpoMemberOrderList> jpoMemberOrderLists = Lists.newArrayList(set);
         	
         	//不可换货标志位，“Y”表示不可换货，"N"表示换货
         	String notChangeProductFlag = "N";
         	if(jpoMemberOrderLists != null && jpoMemberOrderLists.size()>0){
         		for (JpoMemberOrderList jpoMemberOrderList : jpoMemberOrderLists) {
         			
         			//判断原订单商品列表中的每一条商品是否是不可换货商品
					if(jpoMemberOrderListManager.isNotExchangeProduct(jpoMemberOrderList)){
						notChangeProductFlag = "Y";
					}else{
						notChangeProductFlag = "N";
					}
					//给每一个原订单商品加上不可换货的标志位
					jpoMemberOrderList.setNotChangeProductFlag(notChangeProductFlag);
				}
         	}
         	
         	//原订单中不可换货的商品需要在换货商品列表中显示
         	//得到原订单中所有不能换货的商品列表，这些商品要保存到pd_exchange_order_detail表中
     		List<JpoMemberOrderList> jpoMemberOrderListNotChange = new ArrayList<JpoMemberOrderList>();
     		for (JpoMemberOrderList jpoMemberOrderList : jpoMemberOrderLists) {
     			//不可换商品
				if(jpoMemberOrderList.getNotChangeProductFlag().equals("Y")){
					jpoMemberOrderListNotChange.add(jpoMemberOrderList);
				}
			}
     		
        	//保存换货单主表信息pd_exchange_order基本信息和地址信息
    		pdExchangeOrder = this.assemblePdExchangeOrder(jpoMemberOrder, pdExchangeOrder, addrBook);
        	
    		//获取发货方式
    		//String shipType = request.getParameter("shipType");
    		pdExchangeOrder.setShipType("0");
    		
        	//计算换货单的总价格和总PV
        	pdExchangeOrder = this.getHandleTotalAmountExAndPv(request,response,pdExchangeOrder);
        	
        	//换货单制单的条件
        	boolean submitFlag = this.getCheckingPassByPv(jpoMemberOrder,pdExchangeOrder, errors);
        	//pv满足		换货商品合计PV>=(原订单PV-10)的条件，可以自助换货
        	if(submitFlag){
        		
        		//设置need_pay的值
            	pdExchangeOrder = this.getHandleNeedPay(request, response, jpoMemberOrder, pdExchangeOrder);
            	
            	//保存换货单
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER STRAT<<<<<<<<<<<<<<<<<<<<<<<");
            	pdExchangeOrderManager.savePdExchangeOrder(pdExchangeOrder);
            	String eoNo = pdExchangeOrder.getEoNo();
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER END<<<<<<<<<<<<<<<<<<<<<<<");
            	
            	//保存原订单中不可换商品到pd_exchange_order_detail表
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER_DETAIL(NOT CHANGE PRODUCT) STRAT<<<<<<<<<<<<<<<<<<<<<<<");
            	savePdNotChangeProductToDetail(request, response, pdExchangeOrder, jpoMemberOrderListNotChange);
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER_DETAIL(NOT CHANGE PRODUCT) END<<<<<<<<<<<<<<<<<<<<<<<");
            	
            	//根据用户选择的数量，分别保存保存原订单中的商品到pd_exchange_order_detail表和pd_exchange_order_back表
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER_DETAIL,PD_EXCHANGE_ORDER_BACK STRAT<<<<<<<<<<<<<<<<<<<<<<<");
            	savePdExchangeOrderDetailAndBack(request, response, 
            			pdExchangeOrder, jpoMemberOrderLists);
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER_DETAIL,PD_EXCHANGE_ORDER_BACK END<<<<<<<<<<<<<<<<<<<<<<<");
            	
            	//选择新商品，保存新商品信息到pd_exchange_order_detail表
            	//页面中隐藏域保存 选择新商品的字符串 selectNewProductUniqueIds
            	String selectNewProductUniqueIds = request.getParameter("selectNewProductUniqueIds");
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER_DETAIL(SELECT NEW PRODUCT) STRAT<<<<<<<<<<<<<<<<<<<<<<<");
            	if(!Strings.isNullOrEmpty(selectNewProductUniqueIds)){
            		saveNewProductFromUniqueIds(request, response, pdExchangeOrder, selectNewProductUniqueIds);
            	}
            	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER_DETAIL(SELECT NEW PRODUCT) END<<<<<<<<<<<<<<<<<<<<<<<");
            	
            	
            	//因为存在一张订单生成了多张换货单的BUG，将下面的代码移到制单操作的最前面
                //自助换货单制单成功的同时需要订单的状态修改成已换货状态
            	
                jpoMemberOrder.setExchangeFlag("1");
                jpoMemberOrderManager.save(jpoMemberOrder);
            	 
            	 view = "redirect:pdExchangeOrderform/queryExchangeOrderList";
        	}else{
        		//request.setAttribute("errorMessage", "您换货商品合计PV需>=(原订单PV-10)!请重新选择!");
        		//errors.reject("您换货商品合计PV需>=(原订单PV-10)!请重新选择!");
        		this.saveError(request, "您换货商品合计PV需>=(原订单PV-10)!请重新选择!");
        		return "redirect:pdExchangeOrderform?strAction=addPdExchangeOrder&molId="+moId;
        	}
        }
        //换货单编辑
        else if("editPdExchangeOrder".equals(strAction)){
        	String eoNo = request.getParameter("eoNo");
        	String orderNo = request.getParameter("orderNo");
        	JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
        	if(!Strings.isNullOrEmpty(orderNo)){
    			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderNo);
    		}
    		request.setAttribute("jpoMemberOrder", jpoMemberOrder);
    		
    		
    		jpoMemberOrder.setExchangeFlag("1");
            jpoMemberOrderManager.save(jpoMemberOrder);
            
        	if(!Strings.isNullOrEmpty(eoNo)){
        		/*
        		 * 自助换货单编辑的时候，在后台用SQL查询换货单，如果ORDER_FLAG为-1,0那么才允许编辑，否则不允许编辑
        		 */
        		String orderFlag = pdExchangeOrderManager.getOrderFlagByEoNo(eoNo);
        		
        		if("-1".equals(orderFlag) || "0".equals(orderFlag)){
        			pdExchangeOrder = pdExchangeOrderManager.getPdExchangeOrder(eoNo);
            		
            		//设置换货单的修改时间和修改用户
            		pdExchangeOrder.setEditTime(new Date());
            		pdExchangeOrder.setEditUsrCode(userCode);
            		
            		//发货方式,0正常发货
            		//String shipType = request.getParameter("shipType");
            		pdExchangeOrder.setShipType("0");
            		
            		//自助换货单编辑提交时，ORDER_FLAG 赋值为 0（已提交，未审核），SELFCHECK_STATUS 赋值为""
            		pdExchangeOrder.setOrderFlag(new Long(0));
            		pdExchangeOrder.setSelfCheckStatus("");
            		
            		//地址信息
            		pdExchangeOrder = this.assemblePdExchangeOrderAddr(pdExchangeOrder, addrBook);
            		
            		//计算换货单的总价格和总PV
                	pdExchangeOrder = this.getHandleTotalAmountExAndPv(request,response,pdExchangeOrder);
                	
                	boolean submitFlag = this.getCheckingPassByPv(jpoMemberOrder, pdExchangeOrder, errors);
                	if(submitFlag){
                		//设置need_pay的值
                    	pdExchangeOrder = this.getHandleNeedPay(request, response,jpoMemberOrder, pdExchangeOrder);
                		
                		Set<PdExchangeOrderBack> pdExchangeOrderBacks = pdExchangeOrder.getPdExchangeOrderBacks();
                		Set<PdExchangeOrderDetail> pdExchangeOrderDetails = pdExchangeOrder.getPdExchangeOrderDetails();
                		
                		List<PdExchangeOrderBack> pdExchangeOrderBackList = Lists.newArrayList(pdExchangeOrderBacks);
                		List<PdExchangeOrderDetail> pdExchangeOrderDetailList = Lists.newArrayList(pdExchangeOrderDetails);
                		PdExchangeOrderDetail pdExchangeOrderDetail2 = new PdExchangeOrderDetail();
                		
                		//退回
                		for(PdExchangeOrderBack pdExchangeOrderBack :pdExchangeOrderBackList ){
                			//pd_exchange_order_back中的可换商品，可换货商品的数量才可以编辑，不可换货商品数量不能编辑。
                			if("N".equals(pdExchangeOrderBack.getNotChangeProductFlag())){
                				//页面上退货数量控件的input域的值
                				//name = back_num_item_${pdExchangeOrderBack.uniNo }
                				String qty = request.getParameter("back_num_item_" + pdExchangeOrderBack.getUniNo().toString());
                				//初始化数量
                				String initQty = pdExchangeOrderBack.getQty().toString();
                				//制单数量
                				Long originalQty = pdExchangeOrderBack.getOriginalQty();
                				
                				String uniNo = pdExchangeOrderBack.getUniNo().toString();
                				//动态添加的一行,编辑页面隐藏域， name=detail_num_item_O_+uniNo
                				String inputHiddenId = request.getParameter("detail_num_item_O_"+uniNo);
               
                				PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
                				if(!Strings.isNullOrEmpty(qty) && StringUtils.isNumeric(qty)){
                					//如果退货数量控件的值和初始值不一样，重新保存换货单时，pd_exchange_order_back和pd_exchange_order_detail都需要更新
                					//退货数量改变时，才需要更新操作
                					if(!qty.equals(initQty)){
                						//更新操作
                						//更新pd_exchange_order_back表
                    					pdExchangeOrderBack.setQty(new Long(qty));
                    					pdExchangeOrderBackManager.savePdExchangeOrderBack(pdExchangeOrderBack);
                    					
                    					/*修改页面中pdExchangeOrderBackTable中可以换货的商品时（改变数量或点击换货数量控件的加号和减号），
                    					(1)pdExchangeOrderDetailTable中有pdExchangeOrderBack相同的商品时,直接修改pdExchangeOrderDetail对应行的数量的值
                    					(2)pdExchangeOrderDetailTable没有pdExchangeOrderBack相同的商品时，页面上会动态在pdExchangeOrdeDetailTable中添加一行*/
                    					//更新pd_exchange_order_detail表
                    					
                    					//动态添加的一行td里的隐藏域     name=detail_num_item_O_+uniNo
                    					if(!Strings.isNullOrEmpty(inputHiddenId)){
                    						pdExchangeOrderDetail = this.assemblePdExchangeOrderDetail(pdExchangeOrderBack.getProductNo(), 
                    								pdExchangeOrderBack.getPrice(), 
                    								(originalQty-(new Long(qty))), 
                    								pdExchangeOrderBack.getPv(), 
                    								pdExchangeOrderBack.getErpProductNo(), 
                    								pdExchangeOrderBack.getErpPrice(), 
                    								"O", 
                    								pdExchangeOrderBack.getNotChangeProductFlag(), 
                    								pdExchangeOrder);
                    						
                    						//输入的退货数量等于原订单制单数量（最大的退货数量），那么换货数量就为0，需要从pd_exchange_order_detail表中删除
                        					if(originalQty-(new Long(qty)) != 0L){
                        						//更新操作，退货数量等于原订单制单数量，则换货数量为0，不更新到数据库
                        						pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail);
                        						
                        					}
                    						
                    					}
                					}
                					
                				} 
                			}
                		}
                		
                		//换走
                		for (PdExchangeOrderDetail pdExchangeOrderDetail : pdExchangeOrderDetailList) {
        					/*pd_exchange_order_detail中可换的商品
        					 * （1）原换货单中的可换商品剩余的
        					 * （2）原换货单中选择新商品的
        					 * （3）新换货单中选择新商品的
        					 * 
        					 */
                			//商品来源于原订单
                			//if("O".equals(pdExchangeOrderDetail.getProductSource())){
                			if("N".equals(pdExchangeOrderDetail.getNotChangeProductFlag())){
                    			//(1)原订单中可换商品剩余的	name="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }"
                				String qty = request.getParameter("detail_num_item_"+pdExchangeOrderDetail.getUniNo().toString()+"_"+pdExchangeOrderDetail.getProductSource());
                				String initQty = pdExchangeOrderDetail.getQty().toString();
                				
                				//得到赠品（滤芯）
                				pdExchangeOrderDetail2 = pdExchangeOrderDetailManager.getDonationPdExchangeOrderDetail(pdExchangeOrderDetail);
                				
                				if(!Strings.isNullOrEmpty(qty) && StringUtils.isNumeric(qty)){
                					
                					if(!qty.equals(initQty)){
                						
                						//页面上name="detail_num_item_${pdExchangeOrderDetail.uniNo }_${pdExchangeOrderDetail.productSource }"
                						//换走数量为0，需要从pd_exchange_order_detail表中删除掉相应的记录
                						if(new Long(qty) == 0L){
                							
                							//删除操作
                							//因为对象间有级联关系（casecade），所以不能直接删除
                							//删除pd_exchange_order_detail和pd_exchange_order的关联关系
                							pdExchangeOrderDetail.getPdExchangeOrder().getPdExchangeOrderDetails().remove(pdExchangeOrderDetail);
                							pdExchangeOrderDetail.setPdExchangeOrder(null);
                							//pd_exchange_order_detail表中的记录
                							pdExchangeOrderDetailManager.remove(pdExchangeOrderDetail);
                							
                							/*
                				  	         * 由于活力杯产品特殊性，需开发如下功能：
                								会员自助换货时，选择活力杯，系统自动送滤芯。该功能仅针对会员自助换货环节，后台换货流程不变；

                								产品编码：
                								P05080100101CN0 活力杯  
                								P05090100101CN0  滤芯（活力杯赠品）
                				  	         */
                							
                							if(pdExchangeOrderDetail2 != null){
                								//P05090100101CN0  滤芯（活力杯赠品）
                								
                								//删除该赠品
                								pdExchangeOrderDetail2.getPdExchangeOrder().getPdExchangeOrderDetails().remove(pdExchangeOrderDetail2);
                								pdExchangeOrderDetail2.setPdExchangeOrder(null);
                								
                								//pd_exchange_order_detail表中的记录
                								pdExchangeOrderDetailManager.remove(pdExchangeOrderDetail2);
                							}
                						}else{
                							//更新操作
                							pdExchangeOrderDetail.setQty(new Long(qty));
                							pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail);
                							/*
                				  	         * 由于活力杯产品特殊性，需开发如下功能：
                								会员自助换货时，选择活力杯，系统自动送滤芯。该功能仅针对会员自助换货环节，后台换货流程不变；

                								产品编码：
                								P05080100101CN0 活力杯  
                								P05090100101CN0  滤芯（活力杯赠品）
                				  	         */
                							//PdExchangeOrderDetail pdExchangeOrderDetail2 = pdExchangeOrderDetailManager.getDonationPdExchangeOrderDetail(pdExchangeOrderDetail);
                							if(pdExchangeOrderDetail2 != null){
                								//P05090100101CN0  滤芯（活力杯赠品）
                								
                								//更新赠品的数量信息
                								pdExchangeOrderDetail2.setQty(new Long(qty));
                								
                								//更新pd_exchange_order_detail表中的记录
                								pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail2);
                							}
                						}
                					}
                				}
                				
                			}
                			
                		}
                		
                		String deleteProductUniqueIds = request.getParameter("deleteProductUniqueIds");
                    	if(!Strings.isNullOrEmpty(deleteProductUniqueIds)){
                    		deleteProductFromUniqueIds(deleteProductUniqueIds);
                    	}
                    	
                    	String selectNewProductUniqueIds = request.getParameter("selectNewProductUniqueIds");
                    	log.info(">>>>>>>>>>>>>>>>>>>>>SAVE PD_EXCHANGE_ORDER_DETAIL(SELECT NEW PRODUCT) STRAT<<<<<<<<<<<<<<<<<<<<<<<");
                    	if(!Strings.isNullOrEmpty(selectNewProductUniqueIds)){
                    		saveNewProductFromUniqueIds(request, response, pdExchangeOrder, selectNewProductUniqueIds);
                   		}
                    	
                    	log.info(pdExchangeOrder);
                    	
                   		pdExchangeOrderManager.savePdExchangeOrder(pdExchangeOrder);
                    	
                    	//自助换货单制单成功的同时需要订单的状态修改成已换货状态
                    	
                        jpoMemberOrder.setExchangeFlag("1");
                        jpoMemberOrderManager.save(jpoMemberOrder);
                    	
                   		key="pdExchangeOrder.hasChecked";
                   		//换货单编辑保存完成后跳到换货单查询页面pdExchangeOrderList.jsp
                        view="redirect:pdExchangeOrderform/queryExchangeOrderList"; 
        			}else{
        				//request.setAttribute("errorMessage", "您换货商品合计PV需>=(原订单PV-10)!请重新选择!");
        				//errors.reject("您换货商品合计PV需>=(原订单PV-10)!请重新选择!");
        				this.saveError(request, "您换货商品合计PV需>=(原订单PV-10)!请重新选择!");
        				return "redirect:pdExchangeOrderform?strAction=editPdExchangeOrder&orderNo="+orderNo;
        			}
        		}else{
        			//errors.reject("pdExchangeOrder.orderType.alreadyChecked");
        			//errors.reject("换货单已审核，不可编辑！");
        			this.saveError(request,"换货单已审核，不可编辑！");
        			//${pageContext.request.contextPath }/pdExchangeOrderform/orderSelfHelpExchange?strAction=editPdExchangeOrderInit&eoNo=${pdExchangeOrder.eoNo }&orderNo=${pdExchangeOrder.orderNo }
        			view = "redirect:pdExchangeOrderform/orderSelfHelpExchange?strAction=editPdExchangeOrderInit&eoNo="+eoNo+"&orderNo="+orderNo;
        			return view;
        		}
        		
            }
        	 
        }     	
        return view;
    }
    
	private boolean getCheckingPassByPv(JpoMemberOrder jpoMemberOrder,PdExchangeOrder pdExchangeOrder,
			BindingResult errors) {
		BigDecimal jpoMemberOrderPvAmt = new BigDecimal(0);
		BigDecimal pdExchangeOrderPvAmt = new BigDecimal(0);
		if(jpoMemberOrder!= null && pdExchangeOrder!=null){
			jpoMemberOrderPvAmt = jpoMemberOrder.getPvAmt();
			pdExchangeOrderPvAmt = pdExchangeOrder.getPvAmtEx();
			//换货商品合计PV>=(原订单PV-10),才能自助换货
			if(jpoMemberOrderPvAmt != null && pdExchangeOrderPvAmt != null){
				BigDecimal pvDifference = pdExchangeOrderPvAmt.subtract(jpoMemberOrderPvAmt);//差价PV=换货单总PV-原订单总PV
				int compareResult = pvDifference.compareTo(new BigDecimal(-10));
				if(compareResult == -1){
					//StringUtil.getErrorsFormat(errors, "isNotNull", "pvAmtEx", "inwSuggestion.suggestedTopics");
					return false;
				}else{
					return true;
				}
			}
		}
		return true;
	}
	private PdExchangeOrder assemblePdExchangeOrderAddr(PdExchangeOrder pdExchangeOrder,
			JmiAddrBook addrBook) {

		//获取地址相关的信息
		
		//PROVINCE
    	pdExchangeOrder.setProvince(addrBook.getProvince());
		//CITY
        pdExchangeOrder.setCity(addrBook.getCity());
		//DISTRICT
        pdExchangeOrder.setDistrict(addrBook.getDistrict());
		//ADDRESS
        pdExchangeOrder.setAddress(addrBook.getAddress());
		//POSTALCODE
        pdExchangeOrder.setPostalcode(addrBook.getPostalcode());
        //FIRST_NAME
        pdExchangeOrder.setFirstName(addrBook.getFirstName());	
      	//LAST_NAME
      	pdExchangeOrder.setLastName(addrBook.getLastName());
		//PHONE,如果用户不填的话，默认跟mobiletele取一样的值
      	if(!Strings.isNullOrEmpty(addrBook.getPhone())){
      		pdExchangeOrder.setPhone(addrBook.getPhone());
      	}else{
      		pdExchangeOrder.setPhone(addrBook.getMobiletele());
      	}
      	
      	//MOBILETELE
      	pdExchangeOrder.setMobiletele(addrBook.getMobiletele());
		//EMAIL
		pdExchangeOrder.setEmail(addrBook.getEmail());
		
		//SHIP_TYPE
		//pdExchangeOrder.setShipType(request.getParameter("shipType"));
		
		//--------------------------取自原订单的信息---------------------------------

		return pdExchangeOrder;
	}
	
	
	//保存原订单中不可换商品到pd_exchange_order_detail表中
    private void savePdNotChangeProductToDetail(HttpServletRequest request,
			HttpServletResponse response, PdExchangeOrder pdExchangeOrder,
			List<JpoMemberOrderList> jpoMemberOrderNotChangeList){
    	String productNo="";
        BigDecimal price=new BigDecimal(0);
        Long qty=0L;
        BigDecimal pv = new BigDecimal(0);
        Long originalQty = 0L;
        String erpProductNo = "";
        BigDecimal erpPrice = new BigDecimal(0);
        String productSource="O";//换货商品的来源，"O"（Original）表示来自原订单，"N"(New)表示来源于选择新商品
        String notChangeProductFlag = "";
//        PdExchangeOrder pdExchangeOrder;
        PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
        PdExchangeOrderBack pdExchangeOrderBack = new PdExchangeOrderBack();
    	
        for (JpoMemberOrderList jpoMemberOrderList : jpoMemberOrderNotChangeList) {
    		
    		productNo = jpoMemberOrderList.getProNo();
			price = jpoMemberOrderList.getPrice();
			qty = new Long(jpoMemberOrderList.getQty());
			pv = jpoMemberOrderList.getPv();
			erpProductNo = jpoMemberOrderList.getJpmProductSaleTeamType()
					.getJpmProductSaleNew().getJpmProduct().getErpProductNo();
			erpPrice = jpoMemberOrderList.getJpmProductSaleTeamType()
					.getJpmProductSaleNew().getJpmProduct().getErpPrice();
			
			
			productSource="O";//换货商品来源于原订单
    		//notChangeProductFlag = "Y";	//不可换商品
    		notChangeProductFlag = jpoMemberOrderList.getNotChangeProductFlag();
			pdExchangeOrderDetail = this.assemblePdExchangeOrderDetail(productNo, price, qty, pv, erpProductNo, erpPrice,productSource,notChangeProductFlag, pdExchangeOrder);

			pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail);
			
    	}		
    }
    
    //根据用户输入的数量，保存原订单中商品到pd_exchange_order_back表和pd_exchange_order_detail表中
    private void savePdExchangeOrderDetailAndBack(HttpServletRequest request,
			HttpServletResponse response, PdExchangeOrder pdExchangeOrder,
			List<JpoMemberOrderList> jpoMemberOrderLists) {
    	
    	String productNo="";
        BigDecimal price=new BigDecimal(0);
        Long qty=0L;
        BigDecimal pv = new BigDecimal(0);
        Long originalQty = 0L;
        String erpProductNo = "";
        BigDecimal erpPrice = new BigDecimal(0);
        String productSource="O";//换货商品的来源，"O"（Original）表示来自原订单，"N"(New)表示来源于选择新商品
        String notChangeProductFlag = "";
//        PdExchangeOrder pdExchangeOrder;
        PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
        PdExchangeOrderBack pdExchangeOrderBack = new PdExchangeOrderBack();
    	
        //(2)选择换货剩余的商品	
        	for (JpoMemberOrderList jpoMemberOrderList : jpoMemberOrderLists) {
        		
        		String molId = String.valueOf(jpoMemberOrderList.getMolId());
        		productNo = jpoMemberOrderList.getProNo();
    			price = jpoMemberOrderList.getPrice();
    			pv = jpoMemberOrderList.getPv();
    			//从页面隐藏域得到原订单商品的原始数量
    			//max_back_num_item_${jpoMemberOrderList.molId }
    			/*
    			String originalQtyStr = request.getParameter("max_back_num_item" + molId).trim();
    			if(!Strings.isNullOrEmpty(originalQtyStr)){
    				originalQty = Long.parseLong(originalQtyStr);
    			}*/
    			originalQty = new Long(jpoMemberOrderList.getQty());
    			erpProductNo = jpoMemberOrderList.getJpmProductSaleTeamType()
    					.getJpmProductSaleNew().getJpmProduct().getErpProductNo();
    			erpPrice = jpoMemberOrderList.getJpmProductSaleTeamType()
    					.getJpmProductSaleNew().getJpmProduct().getErpPrice();
    			productSource = "O";//换货商品来源于原订单
    			//notChangeProductFlag = "N";//可换货商品
    			notChangeProductFlag = jpoMemberOrderList.getNotChangeProductFlag();
    			//得到原订单中可换商品的输入数量 ------------------------------------------
    			//页面中id为back_num_item_${jpoMemberOrderList.molId }
        		String backQtyStr = request.getParameter("back_num_item_"+molId).trim();
        		if(!Strings.isNullOrEmpty(backQtyStr)){
        			qty = Long.parseLong(backQtyStr);
        			/*
        			if(qty == 0L){
        				pdExchangeOrderBack = 
    							this.assemblePdExchangeOrderBack(productNo, price, originalQty,qty , pv, erpProductNo, erpPrice, pdExchangeOrder);
    					pdExchangeOrderBackManager.savePdExchangeOrderBack(pdExchangeOrderBack);
        			}
        			*/
        			if(qty <= originalQty){
        				//qty!=original，需要同时保存pd_exchange_order_back表和pd_exchange_order_detail表
        				//一种特殊情况是：qty=original时，退货数量等于原订单数量，换走的数量为0，此时不需要保存pd_exchange_order_detail表
        				if( (originalQty-qty) > 0L){
        					pdExchangeOrderDetail = 
        							this.assemblePdExchangeOrderDetail(productNo, price, originalQty-qty, pv, erpProductNo, erpPrice,productSource,notChangeProductFlag, pdExchangeOrder);
        					
        					pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail);
        					
        				}
        				//即使退货数量是0，也是需要保存到pd_exchange_order_back表中。退回数量为0
    					pdExchangeOrderBack = 
    							this.assemblePdExchangeOrderBack(productNo, price, originalQty,qty , pv, erpProductNo, erpPrice,notChangeProductFlag, pdExchangeOrder);
    					pdExchangeOrderBackManager.savePdExchangeOrderBack(pdExchangeOrderBack);
    				}else{
    					log.info("=========qty=========" + qty);
    					log.error("error in --- PdExchangeOrderformController --- addPdExchangeOrderDetailAndBack ---");
    					throw new RuntimeException("页面输入数量不能大于原订单的的商品数量");
    				}
        		}
        	}
        			
      
    }
       	
    //根据删除商品传递过来的uniqueIds，删除商品
    private void deleteProductFromUniqueIds(String uniqueIds){
    	//解析页面传递过来的字符串  uniNo,uniNo,uniNo
    	String[] uniNos = uniqueIds.split(",");
    	for (int i = 0; i < uniNos.length; i++) {
			Long uniNo = new Long(uniNos[i]);
			
			
			PdExchangeOrderDetail pdExchangeOrderDetail = pdExchangeOrderDetailManager.getPdExchangeOrderDetail(uniNo);
			
			//在删除P05080100101CN0 活力杯  之前 得到它的赠品P05090100101CN0  滤芯（活力杯赠品）信息
			PdExchangeOrderDetail pdExchangeOrderDetail2 = pdExchangeOrderDetailManager.getDonationPdExchangeOrderDetail(pdExchangeOrderDetail);
			
			//删除pd_exchange_order_detail和pd_exchange_order的关联关系
			pdExchangeOrderDetail.getPdExchangeOrder().getPdExchangeOrderDetails().remove(pdExchangeOrderDetail);
			pdExchangeOrderDetail.setPdExchangeOrder(null);
			//pd_exchange_order_detail表中的记录
			pdExchangeOrderDetailManager.removePdExchangeOrderDetail(uniNo);
			
			/*
  	         * 由于活力杯产品特殊性，需开发如下功能：
				会员自助换货时，选择活力杯，系统自动送滤芯。该功能仅针对会员自助换货环节，后台换货流程不变；

				产品编码：
				P05080100101CN0 活力杯  
				P05090100101CN0  滤芯（活力杯赠品）
  	         */
			
			if(pdExchangeOrderDetail2 != null){
				//P05090100101CN0  滤芯（活力杯赠品）
				
				//删除该赠品
				pdExchangeOrderDetail2.getPdExchangeOrder().getPdExchangeOrderDetails().remove(pdExchangeOrderDetail2);
				pdExchangeOrderDetail2.setPdExchangeOrder(null);
				
				//pd_exchange_order_detail表中的记录
				pdExchangeOrderDetailManager.remove(pdExchangeOrderDetail2);
			}
			
		}
    }
    
  //根据选择新商品传过来的uniqueIds，保存选择的新商品
  	private void saveNewProductFromUniqueIds(HttpServletRequest request,
  			HttpServletResponse response, PdExchangeOrder pdExchangeOrder,
  			String uniqueIds){
  		String pttId="";
  		String productName="";
  		String productNo="";
          BigDecimal price=new BigDecimal(0);
          Long qty=0L;
          BigDecimal pv=new BigDecimal(0);
          Long originalQty=0L;
          String erpProductNo="";
          BigDecimal erpPrice=new BigDecimal(0);
          String productSource = "N";//换货商品的来源，"O"（Original）表示来自原订单，"N"(New)表示来源于选择新商品
          String notChangeProductFlag = "N";//选择新商品的所有商品，都是可换货商品
          JpmProductSaleTeamType jpmProductSaleTeamType = new JpmProductSaleTeamType();
          PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
          
          /*	selectStr
  		 * value="${jpmProductSaleTeamType.pttId}#${jpmProductSaleTeamType.jpmProductSaleNew.productName }#${jpmProductSaleTeamType.price }#${jpmProductSaleTeamType.pv }
  		 * */
          if(!Strings.isNullOrEmpty(uniqueIds)){
  			String[] productsArr = uniqueIds.split(",");
  			if(productsArr!=null && productsArr.length > 0){
  				for (int i = 0; i < productsArr.length; i++) {
  					String[] productStrArr = productsArr[i].split("#");
  					pttId = productStrArr[0];
  					productName = productStrArr[1];
  					price = new BigDecimal(productStrArr[2]);
  					pv = new BigDecimal(productStrArr[3]);
  					
  					jpmProductSaleTeamType = jpmProductSaleNewManager.getJpmProductSaleTeamType(pttId);
  					
  					//得到页面输入的换货数量的值
  					//"detail_num_item_"+productSource+pttId
  					String qtyStr = request.getParameter("detail_num_item_" + productSource + pttId);
  					if(!Strings.isNullOrEmpty(qtyStr)){
  						qty = Long.parseLong(qtyStr);
  					}
  					if(jpmProductSaleTeamType != null){
  						productNo = jpmProductSaleTeamType.getJpmProductSaleNew().getProductNo();
  						erpProductNo = jpmProductSaleTeamType.getJpmProductSaleNew().getJpmProduct().getErpProductNo();
  						erpPrice = jpmProductSaleTeamType.getJpmProductSaleNew().getJpmProduct().getErpPrice();
  						
  					}
  	    	        
  	    	        pdExchangeOrderDetail = this.assemblePdExchangeOrderDetail(productNo, price, qty, pv, erpProductNo, erpPrice,productSource,notChangeProductFlag, pdExchangeOrder);
  	    	        pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail);
  	    	        
  	    	        /*
  	    	         * 由于活力杯产品特殊性，需开发如下功能：
						会员自助换货时，选择活力杯，系统自动送滤芯。该功能仅针对会员自助换货环节，后台换货流程不变；

						产品编码：
						P05080100101CN0 活力杯  
						P05090100101CN0  滤芯（活力杯赠品）
  	    	         */
  	    	        if("P05080100101CN0".equals(productNo)){
  	    	        	PdExchangeOrderDetail pdExchangeOrderDetail2 = new PdExchangeOrderDetail();
  	    	        	pdExchangeOrderDetail2.setProductNo("P05090100101CN0");
  	    	        	pdExchangeOrderDetail2.setErpPrice(new BigDecimal(0));
  	    	        	pdExchangeOrderDetail2.setPdExchangeOrder(pdExchangeOrder);
  	    	        	pdExchangeOrderDetail2.setPrice(new BigDecimal(0));
  	    	        	pdExchangeOrderDetail2.setPv(new BigDecimal(0));
  	    	        	//数量的比例是1:1
  	    	        	pdExchangeOrderDetail2.setQty(qty);
  	    	        	//赠品为不可换商品
  	    	        	pdExchangeOrderDetail2.setNotChangeProductFlag("Y");
  	    	        	//赠品来源为选择新商品
  	    	        	pdExchangeOrderDetail2.setProductSource("N");
  	    	        	
  	    	        	//表示该商品为赠品，isDonation赠品标志位
  	    	        	pdExchangeOrderDetail2.setIsDonation("Y");
  	    	        	
  	    	        	//保存P05080100101CN0 活力杯  的时候，也要顺带保存	P05090100101CN0  滤芯（活力杯赠品）
  	    	        	pdExchangeOrderDetailManager.savePdExchangeOrderDetail(pdExchangeOrderDetail2);
  	    	        }
  					
  				}
  			}
  		}
  	}
    
	private void editPdExchangeOrder(HttpServletRequest request,
			HttpServletResponse response, PdExchangeOrder pdExchangeOrder) {
		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pdExchangeOrder.setEditUsrCode(loginSysUser.getUserCode());
		pdExchangeOrder.setEditTime(new Date());
		
		pdExchangeOrderManager.savePdExchangeOrder(pdExchangeOrder);
		
	}
	
	/**
	 * 组装PdExchangeOrder对象，并调用存储过程返回PdExchangeOrder的换货单号
	 * @param request
	 * @param response
	 * @param pdExchangeOrder
	 * @param addrBook
	 * @return
	 */
	private PdExchangeOrder assemblePdExchangeOrder(JpoMemberOrder jpoMemberOrder,
			PdExchangeOrder pdExchangeOrder,JmiAddrBook addrBook) {
//		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//根据存储过程生成换货单号EO_NO
		pdExchangeOrder.setEoNo(sysIdManager.buildIdStr("pd_eono"));
		//CUSTOMER_CODE
		pdExchangeOrder.setSysUser(loginSysUser);
		//COMPANY_CODE
		pdExchangeOrder.setCompanyCode(loginSysUser.getCompanyCode());
		//CREATE_USER_CODE
		pdExchangeOrder.setCreateUsrCode(loginSysUser.getUserCode());
		//CREATE_TIME
		pdExchangeOrder.setCreateTime(new Date());
		//ORDER_FLAG,自助换货单状态是未审核
		pdExchangeOrder.setOrderFlag(new Long(0));
		//换货单是自助换货单
		pdExchangeOrder.setSelfReplacement("Y");
		//SELF_CHECK_STATUS赋空值
		pdExchangeOrder.setSelfCheckStatus("");
		
		//PV_AMT_EX
		pdExchangeOrder.setPvAmtEx(new BigDecimal(0));
		//AMOUNT_EX
		pdExchangeOrder.setAmountEx(new BigDecimal(0));
		//STOCK_FLAG
		pdExchangeOrder.setStockFlag("0");
		//获取地址相关的信息
		
		//PROVINCE
    	pdExchangeOrder.setProvince(addrBook.getProvince());
		//CITY
        pdExchangeOrder.setCity(addrBook.getCity());
		//DISTRICT
        pdExchangeOrder.setDistrict(addrBook.getDistrict());
		//ADDRESS
        pdExchangeOrder.setAddress(addrBook.getAddress());
		//POSTALCODE
        pdExchangeOrder.setPostalcode(addrBook.getPostalcode());
        //FIRST_NAME
        pdExchangeOrder.setFirstName(addrBook.getFirstName());	
      	//LAST_NAME
      	pdExchangeOrder.setLastName(addrBook.getLastName());
		//PHONE,如果用户不填的话，默认跟mobiletele取一样的值
      	if(!Strings.isNullOrEmpty(addrBook.getPhone())){
      		pdExchangeOrder.setPhone(addrBook.getPhone());
      	}else{
      		pdExchangeOrder.setPhone(addrBook.getMobiletele());
      	}
      	
      	//MOBILETELE
      	pdExchangeOrder.setMobiletele(addrBook.getMobiletele());
		//EMAIL
		pdExchangeOrder.setEmail(addrBook.getEmail());
		
		//SHIP_TYPE
		//String shipType = request.getParameter("shipType");
		//pdExchangeOrder.setShipType(shipType);
		
		//--------------------------取自原订单的信息---------------------------------
		//ORDER_NO
		pdExchangeOrder.setOrderNo(jpoMemberOrder.getMemberOrderNo());
			
		//NEED_PAY
		//pdExchangeOrder.setNeedPay("Y");
		
		return pdExchangeOrder;
		
	}
	
	
	//组装PdExchangeOrderDetail
	private PdExchangeOrderDetail assemblePdExchangeOrderDetail(String productNo,BigDecimal price,
			Long qty,BigDecimal pv,String erpProductNo,BigDecimal erpPrice,String productSource,String notChangeProductFlag,PdExchangeOrder pdExchangeOrder){
		
		PdExchangeOrderDetail pdExchangeOrderDetail = new PdExchangeOrderDetail();
		pdExchangeOrderDetail.setProductNo(productNo);
		pdExchangeOrderDetail.setPrice(price);
		pdExchangeOrderDetail.setQty(qty);
		pdExchangeOrderDetail.setPv(pv);
		pdExchangeOrderDetail.setErpProductNo(productNo);
		pdExchangeOrderDetail.setErpPrice(erpPrice);
		pdExchangeOrderDetail.setProductSource(productSource);
		
		pdExchangeOrderDetail.setNotChangeProductFlag(notChangeProductFlag);
		//将PdExchangeOrderDetail和PdExchangeOrder关联起来
		pdExchangeOrderDetail.setPdExchangeOrder(pdExchangeOrder);
		return pdExchangeOrderDetail;
	}
	
	//组装PdExchangeOrderBack
	private PdExchangeOrderBack assemblePdExchangeOrderBack(String productNo,BigDecimal price,
			Long originalQty,Long qty,BigDecimal pv,String erpProductNo,BigDecimal erpPrice,String notChangeProductFlag,
			PdExchangeOrder pdExchangeOrder){
		    PdExchangeOrderBack pdExchangeOrderBack = new PdExchangeOrderBack();
		    pdExchangeOrderBack.setProductNo(productNo);
		    pdExchangeOrderBack.setPrice(price);
		    pdExchangeOrderBack.setOriginalQty(originalQty);
		    pdExchangeOrderBack.setQty(qty);
		    pdExchangeOrderBack.setPv(pv);
		    pdExchangeOrderBack.setErpProductNo(erpProductNo);
		    pdExchangeOrderBack.setErpPrice(erpPrice);
		    
		    pdExchangeOrderBack.setNotChangeProductFlag(notChangeProductFlag);
		    //将PdExchangeOrderBack和PdExchangeOrder关联起来
		    pdExchangeOrderBack.setPdExchangeOrder(pdExchangeOrder);
		    return pdExchangeOrderBack;
	}
	
	//计算原订单的总价格和总PV
	//计算原订单商品的总价格和总PV的值
/*	private Map<String,BigDecimal> getTotalBackPrice(List<JpoMemberOrderList> jpoMemberOrderLists){
		Map<String,BigDecimal> toTalBackPriceAndPvMap = new HashMap<String, BigDecimal>();
		BigDecimal totalBackPrice = new BigDecimal(0);
		BigDecimal totalBackPv = new BigDecimal(0);
		if(jpoMemberOrderLists != null && jpoMemberOrderLists.size() > 0){
			for (JpoMemberOrderList jpoMemberOrderList : jpoMemberOrderLists) {
				totalBackPrice = totalBackPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				totalBackPv = totalBackPv.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
			toTalBackPriceAndPvMap.put("totalBackPrice", totalBackPrice);
			toTalBackPriceAndPvMap.put("totalBackPv", totalBackPv);
		}
		return toTalBackPriceAndPvMap;
	}
*/	
	//根据换货单号 得到当前换货单的地址信息
	private JmiAddrBook assembleJmiAddrBook(PdExchangeOrder pdExchangeOrder){
		JmiAddrBook jmiAddrBook = new JmiAddrBook();
		if(pdExchangeOrder != null){
			jmiAddrBook.setProvince(pdExchangeOrder.getProvince());
			jmiAddrBook.setCity(pdExchangeOrder.getCity());
			jmiAddrBook.setDistrict(pdExchangeOrder.getDistrict());
			jmiAddrBook.setAddress(pdExchangeOrder.getAddress());
			jmiAddrBook.setFirstName(pdExchangeOrder.getFirstName());
			jmiAddrBook.setLastName(pdExchangeOrder.getLastName());
			jmiAddrBook.setPhone(pdExchangeOrder.getPhone());
			jmiAddrBook.setMobiletele(pdExchangeOrder.getMobiletele());
			jmiAddrBook.setEmail(pdExchangeOrder.getEmail());
			
		}
		return jmiAddrBook;
	}
	
	//根据原订单 得到当前换货单的地址信息
		private JmiAddrBook assembleJmiAddrBook(JpoMemberOrder jpoMemberOrder){
			JmiAddrBook jmiAddrBook = new JmiAddrBook();
			if(jpoMemberOrder != null){
				jmiAddrBook.setProvince(jpoMemberOrder.getProvince());
				jmiAddrBook.setCity(jpoMemberOrder.getCity());
				jmiAddrBook.setDistrict(jpoMemberOrder.getDistrict());
				jmiAddrBook.setAddress(jpoMemberOrder.getAddress());
				jmiAddrBook.setFirstName(jpoMemberOrder.getFirstName());
				jmiAddrBook.setLastName(jpoMemberOrder.getLastName());
				jmiAddrBook.setPhone(jpoMemberOrder.getPhone());
				jmiAddrBook.setMobiletele(jpoMemberOrder.getMobiletele());
				jmiAddrBook.setPostalcode(jpoMemberOrder.getPostalcode());
				jmiAddrBook.setEmail(jpoMemberOrder.getEmail());
				jmiAddrBook.setBuilding(jpoMemberOrder.getBuilding());
				jmiAddrBook.setTown(jpoMemberOrder.getTown());
				
			}
			return jmiAddrBook;
		}
		
		
		//根据原订单的地址信息或者换货单的地址信息，重置制单初始化页面或换货单编辑初始化页面的初始地址
		public void resetDefaultAddr(List<JmiAddrBook> addressList,JmiAddrBook jmiAddrBook){
			for(JmiAddrBook addrBook : addressList){
		    	
		    	//全部取消默认地址
		    	//tem.setIsDefault("");
		    	//(1)如果原订单的地址存在于会员的地址列表中,用原订单的地址作为默认地址
		    	if(jmiAddrBook.getProvince().equals(addrBook.getProvince())
		    			&&jmiAddrBook.getCity().equals(addrBook.getCity())
		    			&&jmiAddrBook.getDistrict().equals(addrBook.getDistrict())
		    			&&jmiAddrBook.getAddress().equals(addrBook.getAddress())
		    			&&jmiAddrBook.getFirstName().equals(addrBook.getFirstName())
		    			&&jmiAddrBook.getLastName().equals(addrBook.getLastName())
		    			&&jmiAddrBook.getMobiletele().equals(addrBook.getMobiletele()))
		    	{
		    		//换货单地址，在会员的地址列表里，用原订单的地址作为默认地址
		    		addrBook.setIsDefault("1");
		    		for(JmiAddrBook obj : addressList){
		    			if(obj.getFabId() != addrBook.getFabId() ){
		    				obj.setIsDefault("");
		    			}
		    		}
		    		
		    	}
		    	//(2)原订单地址，不在会员的地址列表里，用会员地址列表的默认地址作为默认地址
		    /*	else{
		    		if(defaultFabId == tem.getFabId()){
		    			tem.setIsDefault("1");
		    			log.info(tem);
		    		}else{
		    			tem.setIsDefault("");
		    			log.info(tem);
		    		}
		    	}	*/
		    	
		    }
		}
		
		/**
		 * 判断自主换货是否需要支付及支付的金额
		 * @param request
		 * @param response
		 * @param pdExchangeOrder
		 * @return
		 */
		private PdExchangeOrder getHandleNeedPay(HttpServletRequest request,HttpServletResponse response,JpoMemberOrder jpoMemberOrder,PdExchangeOrder pdExchangeOrder){
			BigDecimal allDetailAmount = new BigDecimal(0);
			String allDetailAmountStr = request.getParameter("allDetailAmount");
			if(!Strings.isNullOrEmpty(allDetailAmountStr)){
				allDetailAmount = new BigDecimal(allDetailAmountStr);
			}
			//原订单的总价格
			//BigDecimal amountEx = pdExchangeOrder.getAmountEx();
			BigDecimal amountEx = new BigDecimal(0);
			if(jpoMemberOrder != null){
				amountEx = jpoMemberOrder.getAmount();
			}
			if(amountEx != null && allDetailAmount != null){
				BigDecimal amountDifference = allDetailAmount.subtract(amountEx);//差价金额=换货单总金额-原订单总金额
				int compareResult = amountDifference.compareTo(new BigDecimal(0));
				if(compareResult == 1){
					pdExchangeOrder.setNeedPay("Y");//自助换货单需要支付
					pdExchangeOrder.setNeedPayAmount(amountDifference);//自助换货单需要支付的金额
				}else{
					pdExchangeOrder.setNeedPay("N");//自助换货单不需要支付
				}
			}
			return pdExchangeOrder;
		}
		
		//计算换货单的总价格和总PV
		private PdExchangeOrder getHandleTotalAmountExAndPv(
				HttpServletRequest request, HttpServletResponse response,
				PdExchangeOrder pdExchangeOrder) {
			BigDecimal allDetailAmount = new BigDecimal(0);
			BigDecimal allDetailPv = new BigDecimal(0);
			String allDetailAmountStr = request.getParameter("allDetailAmount");
			String allDetailPvStr = request.getParameter("allDetailPv");
			if(!Strings.isNullOrEmpty(allDetailAmountStr)){
				allDetailAmount = new BigDecimal(allDetailAmountStr);
			}
			if(!Strings.isNullOrEmpty(allDetailPvStr)){
				allDetailPv = new BigDecimal(allDetailPvStr);
			}
			pdExchangeOrder.setAmountEx(allDetailAmount);
			pdExchangeOrder.setPvAmtEx(allDetailPv);
			return pdExchangeOrder;
		}
	
}
