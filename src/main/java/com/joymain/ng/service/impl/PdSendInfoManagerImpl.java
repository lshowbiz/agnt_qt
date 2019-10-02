package com.joymain.ng.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.PdSendInfoDao;
import com.joymain.ng.model.HttpMsg;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.model.PdPhoneSend;
import com.joymain.ng.model.PdPhoneSendInfo;
import com.joymain.ng.model.PdSendInfo;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.PdSendInfoManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;

@Service("pdSendInfoManager")
@WebService(serviceName = "pdSendInfoManager", endpointInterface = "com.joymain.ng.service.PdSendInfoManager")
public class PdSendInfoManagerImpl extends GenericManagerImpl<Linkman, Long> implements PdSendInfoManager {
	PdSendInfoDao pdSendInfoDao;
	private JsysUserManager jsysUserManager;
 
    @Autowired
    public PdSendInfoManagerImpl(PdSendInfoDao pdSendInfoDao) {
        super(pdSendInfoDao);
        this.pdSendInfoDao = pdSendInfoDao;
    }
	
    @Autowired
    public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}




	/**
	 * 会员发货单管理
	 * Add By WuCF 2014-01-14
	 * 分页
	 * @param page：分页信息
	 * @param userCode：会员编号
	 * @param siNo：发货单号
	 * @param orderNo：订单号
	 * @return
	 */
	public List getPdSendInfoPage(GroupPage page,String userCode,String siNo, String orderNo,String orderFlag) {
		return pdSendInfoDao.getPdSendInfoPage(page,userCode,siNo,orderNo,orderFlag);
	}

	/**
	 * 会员发货单管理
	 * Add By WuCF 2014-04-28
	 * 分页
	 * @param page：分页信息
	 * @param crm：查询的条件集合
	 * @return
	 */
	public List getJpoMemberOrderPage(GroupPage page,CommonRecord crm) {
		return pdSendInfoDao.getJpoMemberOrderPage(page,crm);
	}
	
	/**
	 * 订单总金额
	 * Add By WuCF 2014-04-28
	 * @param crm
	 * @return
	 */
	@Override
	public Map getSumAmountByCrm(CommonRecord crm) {
		return pdSendInfoDao.getSumAmountByCrm(crm);
	}

	/**
	 * 根据条件统计商品销售量
	 * Add By WuCF 2014-04-28
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm){
		return pdSendInfoDao.getStatisticProductSale(crm);
	}
	public int getStatisticProductSale2(CommonRecord crm){
		return pdSendInfoDao.getStatisticProductSale2(crm);
	}

	/**
	 * 会员收货确认
	 * @param siNo：订单编号
	 * @return
	 */
	public int sendInfoConfirm(String siNo) {
		return pdSendInfoDao.sendInfoConfirm(siNo);
	}


	/**
	 * 获得指定会员的发货单中：已经发货，但未确认的发货信息，只有在发货单10天后到17天之间如果没有确认的，才会提示！
	 * @param userCode
	 * @return
	 */
	public int isExistNotConfirm(String userCode) {
		return pdSendInfoDao.isExistNotConfirm(userCode);
	}

	/**
	 * Add By WuCF 20141219
	 * 通过订单号和商品编号，查询返回商品的物流跟踪单号
	 * @param orderNo：订单号
	 * @param productNo：商品编码---实际上是订单明细表jpo_member_order_list的主键
	 * @return：返回具体商品的物流跟踪信息，可能有多个，则用逗号英文“,”隔开
	 */
	public List<String> getTrackingNoInfo(String orderNo,String productNo){
		return pdSendInfoDao.getTrackingNoInfo(orderNo,productNo);
	}
	
	/**
	 * Add By WuCF 20150907是否拥有股票链接地址
	 * @param userCode
	 * @return
	 */
	public boolean isGuPiaoUser(String userCode){
		return pdSendInfoDao.isGuPiaoUser(userCode);
	}
	
	/**
	 * 得到瓜藤网图片的地址和读取名称
	 * @param listCode
	 * @return
	 */
	public List getGuaTenLinks(String listCode){
		return pdSendInfoDao.getGuaTenLinks(listCode);
	}


	@Override
	public List getJpoMemberOrderList(CommonRecord crm) {
		// TODO Auto-generated method stub
		return pdSendInfoDao.getJpoMemberOrderList(crm);
	}
	
	/**
	 * 手机端发货确认
	 * @author fu 2016-04-22
	 * @param request
	 * @param response
	 */
	public String pdSendInfoConfirmByPhone(HttpServletRequest request,HttpServletResponse response){
		//e001 系统参数失败
		//e002签名错误
		//e003接口不存在
		//e004应用参数不存在
		//e005业务数据问题
		String result = "";
		try{
				String userId = request.getParameter("userId");//会员编号
				String token = request.getParameter("token");//加密标志
				String logisticsNo = request.getParameter("logisticsNo");//发货单号
				log.info("在PdSendInfoManagerImpl类的pdSendInfoConfirmByPhone方法中运行-发货确认：会员编号为"+userId+"发货单号为"+logisticsNo);
				if(StringUtil.isEmpty(logisticsNo)){
					setHttpMsg("e005","手机端发货确认接口的logisticsNo为空!",response);
					return result;
				}else if(StringUtil.isEmpty(userId)){
					setHttpMsg("e005","手机端发货确认接口的userId为空!",response);
					return result;
				}
				//暂时定的加密方式---------------------------begin----暂时注释掉,以后再决定是否放开
		       /* JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
				Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List"); 
				if(null!=object){
					//鉴权失败、、直接返回错误信息
					setHttpMsg("e001","token鉴权失败!",response);
					return result;
				}*/
				//暂时定的加密方式---------------------------begin
				int a = pdSendInfoDao.sendInfoConfirm(logisticsNo);
				//收货确认成功
				if(a>=1){
					setHttpMsg("s000","发货确认成功！",response);
				}else{
					PdSendInfo pdSendInfo = pdSendInfoDao.getPdSendInfoBySiNo(logisticsNo);
					if(null==pdSendInfo){
						setHttpMsg("e005","发货单号"+logisticsNo+"在系统中不存在！",response);
					}else{
						Long orderFlag = pdSendInfo.getOrderFlag();
						if(2!=orderFlag){
							setHttpMsg("e005","发货单号"+logisticsNo+"对应的发货单未发货不能进行发货确认",response);
						}
					}
				}
				return result;
		}catch(Exception e){
			e.printStackTrace();
			log.info("在PdSendInfoManagerImpl类的pdSendInfoConfirmByPhone方法中运行-发货确认错误:"+e.toString());
			return result;
		}
		
	}
	
	/**
	 * 发货单号查询
	 * @author fu 2016-04-22
	 * @param request
	 * @param response
	 * @return
	 */
	public String getSiNoList(HttpServletRequest request,HttpServletResponse response){
		//e001 系统参数失败
		//e002签名错误
		//e003接口不存在
		//e004应用参数不存在
		//e005业务数据问题
		String result = "";
		String orderNo = request.getParameter("orderNo");//订单编号
		try{
				String userId = request.getParameter("userId");//会员编号
				String token = request.getParameter("token");//加密标志
				log.info("在PdSendInfoManagerImpl类的getSiNoList方法中运行-发货单号查询：会员编号为"+userId+"订单编号为"+orderNo);
				
				if(StringUtil.isEmpty(orderNo)){
					setHttpMsg("e005","发货单号查询手机接口的orderNo为空!",response);
					return result;
				}else if(StringUtil.isEmpty(userId)){
					setHttpMsg("e005","发货单号查询手机接口的userId为空!",response);
					return result;
				}
				//暂时定的加密方式---------------------------begin----暂时注释掉,以后再决定是否放开
		       /* JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
				Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List"); 
				if(null!=object){
					//鉴权失败、、直接返回错误信息
					setHttpMsg("e001","token鉴权失败!",response);
					return result;
				}*/
				//暂时定的加密方式---------------------------begin
				
				//获取订单号对应的发货信息
				PdPhoneSend pdPhoneSend = pdSendInfoDao.getPdSendInfoList(orderNo);
				List<PdPhoneSendInfo> pdPhoneSendInfoList = pdPhoneSend.getPdPhoneSendInfoList();
				if((null!=pdPhoneSendInfoList)&&pdPhoneSendInfoList.size()>0){
		 	        writeGridDataModelJson(response,pdPhoneSend);//响应请求
				}else{
					setHttpMsg("e005","订单编号"+orderNo+"对应的发货单都没有发货!",response);
				}
				return result;

		}catch(Exception e){
			e.printStackTrace();
			log.info("在PdSendInfoManagerImpl类的getSiNoList方法中运行-系统后台根据订单号"+orderNo+"查询发货单异常:"+e.toString());
			try {
				setHttpMsg("e005","系统后台根据订单号"+orderNo+"查询发货单异常",response);
			} catch (Exception e1) {
				e1.printStackTrace();
				log.info("在PdSendInfoManagerImpl类的getSiNoList方法中运行-系统后台根据订单号"+orderNo+"查询发货单异常:"+e1.toString());
			}
			return result;
		}

	}
	
	 private void setHttpMsg(String code,String msg, HttpServletResponse response) throws Exception{
	    	HttpMsg hm = new HttpMsg();
	    	hm.setCode(code);
			hm.setMsg(msg);
			log.info("在PdSendInfoManagerImpl类的setHttpMsg方法中运行：code为"+code+"msg为"+msg);
			writeGridDataModelJson(response,hm);
	    }
	 
	   /**
		 * 模块单对象页面输出格式
		 * @param response
		 * @param m 结果集
		 * @throws IOException
		 */
		public <T> void writeGridDataModelJson(HttpServletResponse response,T t) throws IOException{
			ObjectMapper mapper = new ObjectMapper();
			String writeJson = mapper.writeValueAsString(t);
			response.setCharacterEncoding("UTF-8");
			log.info("在PdSendInfoManagerImpl类的writeGridDataModelJson方法中运行");
			response.getWriter().print(writeJson);
	    }

		/**
		* 手机端接口-根据订单号号获取物流单号和物流公司
		* @author fu 2016-05-18
		* @param memberOrderNo 订单编号
		* @return list
		*
		*/
	@Override
	public List<Map<String, String>> getLogisticsByMobile(String memberOrderNo) {
		Log.info("手机物流跟踪号接口-在类PdSendInfoManagerImpl的方法中getLogisticsByMobile运行");
		List list = pdSendInfoDao.getPdSendInfoForMemberOrderNo(memberOrderNo);
        List<Map<String,String>> listResult = new ArrayList<Map<String,String>>();
		if((null!=list)&&list.size()>0){
	    	Log.info("手机物流跟踪号接口-在类PdSendInfoManagerImpl的方法中getLogisticsByMobile运行,发货单信息为"+list.toString());
	    	for(int i=0;i<list.size();i++){
            	Map map = (Map) list.get(i);//物流单号
            	String trackingNo = (String) map.get("tracking_no");
            	if(!StringUtil.isEmpty(trackingNo)){
            		String shNo = (String) map.get("sh_no");//物流公司
            		Map mapTwo = new HashMap();
            		mapTwo.put("shNo", shNo);
            		mapTwo.put("trackingNo", trackingNo);
            		listResult.add(mapTwo);
            	}
            }
		}
    Log.info("手机物流跟踪号接口-在类PdSendInfoManagerImpl的方法中getLogisticsByMobile运行结束");
	return listResult;
	}
}