package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.Constants;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JbdSendRecordHistManager;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.StringUtil;
/**
 * 奖金查询-查询明细
 * @author 
 *
 */
@Controller
//@RequestMapping("/jbdSendRecordHists/")
public class JbdSendRecordHistController {
    private JbdSendRecordHistManager jbdSendRecordHistManager;

    @Autowired
    public void setJbdSendRecordHistManager(JbdSendRecordHistManager jbdSendRecordHistManager) {
        this.jbdSendRecordHistManager = jbdSendRecordHistManager;
    }

    @Autowired
    private JbdPeriodManager jbdPeriodManager;

    /*@RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(jbdSendRecordHistManager.search(query, JbdSendRecordHist.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jbdSendRecordHistManager.getAll());
        }
        return model;
    }*/
    
    /**
     * 奖金查询－－查询明细（详细查询的页面）
     * @param request
     * @return
     */
    //-----假如返回的页面是jbdBonusEnquiryDetails,
    //----注解－－－页面跳转的时候－－－注意这个地方与页面跳转页面的链接的一致性
    @RequestMapping(value="/jbdBonusEnquiryDetails",method = RequestMethod.GET)
    public String getBonusEnquiryDetails(HttpServletRequest request){
    	//获取当前登录用户的信息
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前会员的登录账号－－对应其他表的会员编号
    	String userCode = defSysUser.getUserCode();
    	String userCodeParam = request.getParameter("userCodeParam");
    	String wweek = request.getParameter("wweek");
    	//---如果是会员的话---新系统貌似不需要进行判断
    	/*SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("M".equals(defSysUser.getUserType())){
        	userCode=defSysUser.getUserCode();
        }*/
    	//查询明细的类型：因为页面有多个查询明细的链接，所以需要查询明细的类型
    	String type = request.getParameter("type");
    	
    	//假如返回的页面是jbdBonusEnquiryDetails
    	String returnView = "jbdBonusEnquiryDetails";
    	
    	String newWeek=(String) Constants.sysConfigMap.get("AA").get("new.week");
    	if(StringUtil.formatInt(wweek)>=StringUtil.formatInt(newWeek)){
    		returnView = "jbdBonusEnquiryDetails2015";
    	}
    	
    	
    	//String returnView = "queryList";
    	
    	// VENTURE_SALES_PV   查询明细－－创业销售奖
    	if("ventureSalesPv".equals(type)){
            List jbdSellCalcSubHists=jbdSendRecordHistManager.getVentureSalesPv(userCode, wweek);
            request.setAttribute("jbdSellCalcSubHists", jbdSellCalcSubHists);
        }
    	
    	//VENTURE_FUND 查询明细－－扶持奖( 创业基金)---jbdMemberLinkCalcHist.ventureFund
        if("ventureFund".equals(type)){
        	List ventureFundPvs=jbdSendRecordHistManager.getVentureFundPv(userCode, wweek);
            request.setAttribute("ventureFundPvs", ventureFundPvs);
        }
        
    	//VENTURE_LEADER_PV 查询明细－－创业领导奖01--jbdMemberLinkCalcHist.ventureLeaderPv37 //旧：感恩 新：级差
        if("ventureLeaderPv".equals(type)){
        	List jbdVentureLeaderSubHists=jbdSendRecordHistManager.getVentureLeaderPvOne(userCode, wweek, "01");
            request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
        }
        
      //服务奖明细 
        if("servicePv".equals(type)){
        	List servicePvList=jbdSendRecordHistManager.getServicePv(userCode, wweek);
	         request.setAttribute("servicePvList", servicePvList);
        }
        
        //SUCCESS_LEADER_PV  查询明细－成功领导奖02 //代数奖
        if("successLeaderPv".equals(type)){
        	

        	if(StringUtil.formatInt(wweek)>=StringUtil.formatInt(newWeek)){

            	List jbdSuccessLeaderPvList=jbdSendRecordHistManager.getJbdSuccessLeaderPv(userCode, wweek);
                request.setAttribute("jbdSuccessLeaderPvList", jbdSuccessLeaderPvList);
        	}else{
        		List jbdVentureLeaderSubHists=jbdSendRecordHistManager.getVentureLeaderPvOne(userCode, wweek, "02");
                request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
        	}
        	
        	
        	
        	/**/
        }
        
        /** 20160817 add  销售奖改版  */
        if("ventureLeaderPv201607".equals(type)){//销售奖
        	List jbdVentureLeaderSubHists=jbdSendRecordHistManager.getVentureLeaderPvOne(userCode, wweek, "01");
            request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
        }
        //201805改版
		if("ventureLeaderPv201805".equals(type)){//销售奖
			List jbdVentureLeaderSubHists=jbdSendRecordHistManager.getVentureLeaderPvOne(userCode, wweek, "01");
			request.setAttribute("jbdVentureLeaderSubHists", jbdVentureLeaderSubHists);
		}
		if("ventureLeaderPvDetail201805".equals(type)){//销售奖明细的明细
			List jbdVentureLeaderSubHistsDetail=jbdSendRecordHistManager.getVentureLeaderDetail(userCodeParam, wweek);
			request.setAttribute("jbdVentureLeaderSubHistsDetail", jbdVentureLeaderSubHistsDetail);
		}

        
        if("bdjPv201607".equals(type)){
        	List storeExpandPvs=jbdSendRecordHistManager.getbdjPv201607(userCode, wweek);
            request.setAttribute("storeExpandPvs", storeExpandPvs);
        }
        
      //服务奖明细 201607以后的路线
        if("servicePv201607".equals(type)){
			 List servicePvList=jbdSendRecordHistManager.getServicePv201607(userCode, wweek);
	         request.setAttribute("servicePvList", servicePvList);
	    }
        
        
        
        //SUCCESS_SALES_PV 查询明细－－成功销售奖---页面中没有该明细 卓越领导奖
        if("successSalesPv".equals(type)){
        	List jbdMemberLinkCalcHists=jbdSendRecordHistManager.getSuccessSaleBonus(userCode, wweek);
            request.setAttribute("jbdMemberLinkCalcHists", jbdMemberLinkCalcHists);
        }
        
        //RECOMMEND_BONUS_PV  查询明细－－推荐奖金
        if("recommendBonusPv".equals(type)){
        	  List jbdSellCalcRecommendBouns=jbdSendRecordHistManager.getJbdSellCalcRecommendBouns(userCode, wweek);
              request.setAttribute("jbdSellCalcRecommendBouns", jbdSellCalcRecommendBouns);
        }

		//推广奖金
		if("popularizeBonusPv".equals(type)){
			List popularizeBonusPvs=jbdSendRecordHistManager.getPopularizeBonusPv(userCode, wweek);
			request.setAttribute("popularizeBonusPvs", popularizeBonusPvs);
		}
        //STORE_EXPAND_PV    查询明细－－店铺拓展奖
        if("storeExpandPv".equals(type)){
        	List storeExpandPvs=jbdSendRecordHistManager.getStoreExpandPv(userCode, wweek);
            request.setAttribute("storeExpandPvs", storeExpandPvs);
        }
        
        //STORE_SERVE_PV    查询明细－－店铺服务奖 
        if("storeServePv".equals(type)){
        	List storeServePvs=jbdSendRecordHistManager.getStoreServePv(userCode, wweek);
            request.setAttribute("storeServePvs", storeServePvs);
        }
        
        //STORE_RECOMMEND_PV  查询明细－－店铺推荐奖
        if("storeRecommendPv".equals(type)){
        	List storeRecommendPvs=jbdSendRecordHistManager.getStoreRecommendPv(userCode, wweek);
            request.setAttribute("storeRecommendPvs", storeRecommendPvs);
        }
        
        //FRANCHISE_MONEY 查询明细－－加盟店店补--数据库中对应的类型为：１加盟店店补；２重消物流费
        if("franchiseMoney".equals(type)){
        	List franchiseMoneys=jbdSendRecordHistManager.getFranchises(userCode, wweek, "01");
            request.setAttribute("franchiseMoneys", franchiseMoneys);
        }
        
        //CONSUMER_AMOUNT   查询明细－－重消物流费
        if("consumerAmount".equals(type)){
        	List consumerAmounts=jbdSendRecordHistManager.getFranchises(userCode, wweek, "02");
            request.setAttribute("consumerAmounts", consumerAmounts);
        }
        
        //代数奖明细
        if("successLeaderPvDetail".equals(type)){
        	
        	JbdPeriod bdPeriod=jbdPeriodManager.getBdPeriodByFormatedWeek(wweek);

        	Date sDate=null;
        	Date eDate=null;
        	
        	
        	List bdPeriodList=jbdPeriodManager.getBdPeriodsByMonth(bdPeriod.getFyear()+"", bdPeriod.getFmonth()+"");
			if(!bdPeriodList.isEmpty()){		
				if(bdPeriodList.size()==1){
					JbdPeriod curBdPeriod=(JbdPeriod) bdPeriodList.get(0);
					sDate=curBdPeriod.getStartTime();
					eDate=curBdPeriod.getEndTime();
				}else{
					for (int i = 0; i < bdPeriodList.size(); i++) {
						JbdPeriod curBdPeriod=(JbdPeriod) bdPeriodList.get(i);
						if(i==0){
							sDate=curBdPeriod.getStartTime();
						}else if(i==(bdPeriodList.size()-1)){
							eDate=curBdPeriod.getEndTime();
						}
					}
				}
			}
        	
        	
        	
        	
        	
        	
        	
        	
        	String stime = DateUtil.getDate(sDate,"yyyy-MM-dd HH:mm:ss");
        	String etime = DateUtil.getDate(eDate,"yyyy-MM-dd HH:mm:ss");
        	List successLeaderPvDetailList=null;
        	String passStar="v_jbd_crown_list";
    		String algebra=request.getParameter("algebra");
    /*		if("1".equals(passStar) || "2".equals(passStar) || "3".equals(passStar)){
    			passStar="v_jbd_gem_list";
    		}else if("4".equals(passStar) || "5".equals(passStar) || "6".equals(passStar)){
    			passStar="v_jbd_diamond_list";
    		}else if("7".equals(passStar) || "8".equals(passStar) || "9".equals(passStar)){
    			passStar="v_jbd_crown_list";
    		}else{
    			//request.setAttribute("successLeaderPvDetailList", successLeaderPvDetailList);
    		}*/
        	
        	//if(passStar.length()>5){
            	successLeaderPvDetailList=jbdSendRecordHistManager.getSuccessLeaderPvDetail(userCode, wweek, passStar, stime, etime,algebra);
        	//}
        	
        	
            request.setAttribute("successLeaderPvDetailList", successLeaderPvDetailList);
        }
        
        
        //销售奖明细的明细
        if("ventureLeaderPvDetail".equals(type)){
          	JbdPeriod bdPeriod=jbdPeriodManager.getBdPeriodByFormatedWeek(wweek);

        	Date sDate=null;
        	Date eDate=null;
        	
        	
        	List bdPeriodList=jbdPeriodManager.getBdPeriodsByMonth(bdPeriod.getFyear()+"", bdPeriod.getFmonth()+"");
			if(!bdPeriodList.isEmpty()){		
				if(bdPeriodList.size()==1){
					JbdPeriod curBdPeriod=(JbdPeriod) bdPeriodList.get(0);
					sDate=curBdPeriod.getStartTime();
					eDate=curBdPeriod.getEndTime();
				}else{
					for (int i = 0; i < bdPeriodList.size(); i++) {
						JbdPeriod curBdPeriod=(JbdPeriod) bdPeriodList.get(i);
						if(i==0){
							sDate=curBdPeriod.getStartTime();
						}else if(i==(bdPeriodList.size()-1)){
							eDate=curBdPeriod.getEndTime();
						}
					}
				}
			}
        	
        	String stime = DateUtil.getDate(sDate,"yyyy-MM-dd HH:mm:ss");
        	String etime = DateUtil.getDate(eDate,"yyyy-MM-dd HH:mm:ss");
        	
        	List ventureLeaderPvDetailList=jbdSendRecordHistManager.getVentureLeaderPvDetail(userCode, stime, etime);
        	

            request.setAttribute("ventureLeaderPvDetailList", ventureLeaderPvDetailList);
        }
        
        
        
        
        //NETWORK_MONEY 查询明细－－网路费－－去掉不用
        request.setAttribute("type", type);

    	return returnView;
    }

    /**
     * 查询明细－－创业销售奖－－查询明细－－创业销售奖明细查询
     * @author Administrator
     * @param request
     */
    //------假设返回的页面是：jbdSellCalcSubDetailHist
    //----注解－－－页面跳转的时候－－－注意这个地方与页面跳转页面的链接的一致性
    @RequestMapping(value="/jbdSellCalcSubDetailHist",method = RequestMethod.GET)
    public String getJbdSellCalcSubDetailHist(HttpServletRequest request){
        String returnView = "jbdSellCalcSubDetailHist";
        //获取当前登录用户的信息
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前会员的登录账号－－对应其他表的会员编号
    	String userCode = defSysUser.getUserCode();
        //String userCode = request.getParameter("userCode");
        String wweek = request.getParameter("wweek");
        List jbdSellCalcSubDetailHists = jbdSendRecordHistManager.getJbdSellCalcSubDetailHist(userCode,wweek);
        request.setAttribute("jbdSellCalcSubDetailHists", jbdSellCalcSubDetailHists);
        return returnView;
    }
}
