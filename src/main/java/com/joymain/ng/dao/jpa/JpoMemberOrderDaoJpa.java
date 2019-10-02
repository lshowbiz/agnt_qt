package com.joymain.ng.dao.jpa;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
@SuppressWarnings({"unchecked","unused"})
@Repository("jpoMemberOrderDao")
public class JpoMemberOrderDaoJpa extends GenericDaoHibernate<JpoMemberOrder, Long> implements JpoMemberOrderDao {

	@Autowired
	private DataSource dataSource2;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public JpoMemberOrderDaoJpa() {
        super(JpoMemberOrder.class);
    }
	/**
	 * 
	 * @param papernumber  身份证号
	 * @param productNo 产品编号
	 * @param orderType  订单类型
	 * @return
	 */
	public List getJpoMemberMark(String papernumber,String productNo,String orderType){
		String sqlQuery="select a.user_code from jmi_member  a," +
				"jpo_member_order  b,jpo_member_order_list c," +
				"jpm_product_sale_team_type d,jpm_product_sale_new dn where  " +
				"a.user_code = b.user_code " +
				"and c.product_id = d.ptt_id  " +
				"and dn.uni_no=d.uni_no " +
				"and b.mo_id = c.mo_id " +
				"and b.status='2' and b.company_code='CN' " +
				"and dn.product_no='"+productNo+"' " +
				"and a.papernumber='"+papernumber+"' and b.order_type ='"+orderType+"' ";
			log.debug("==是否绑定事业锦囊："+sqlQuery);
			return this.getJdbcTemplate().queryForList(sqlQuery);
	}
	
	//查询会员确认过的订单
	public List<JpoMemberOrder> getJpoMemberOrder(List<String> memberOrderNos) {
		
		String hql="from JpoMemberOrder where memberOrderNo in( ";
		StringBuffer orderNos=new StringBuffer();
		for(String memberOrderNo : memberOrderNos){
			orderNos.append("'").append( memberOrderNo ).append("',");
		}
		orderNos.deleteCharAt( orderNos.length()-1);
		orderNos.append(") ");
		orderNos.insert(0, hql);
		Query q=getSession().createQuery(orderNos.toString());
		return q.list();
	}
	public List<JpoMemberOrder> getOrderByParam(Map<String, String> map){
		//可能按商品名称和编码查询   Modify By WuCF 暂时注释 2014-01-16
//		String hql="select po from JpoMemberOrder po,JpoMemberOrderList jmol where jmol.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo='N05270100101CN1' and po.sysUser.userCode='"+map.get("userCode")+"' and po.sysUser.companyCode='"+map.get("companyCode")+"'";	
		//Modify By WuCF 20140630 修改绑定变量的方式
		StringBuffer hql= new StringBuffer("from JpoMemberOrder po where po.orderType<>'32' and po.sysUser.userCode= :userCode and po.sysUser.companyCode= :companyCode ");	
		String userCode = map.get("userCode");//订单编号
		String companyCode = map.get("companyCode");//分公司
		
		//modify by fu 2016-03-29 自助换货
		String selfHelpExchange = map.get("selfHelpExchange");
		
		String memberOrderNo=map.get("memberOrderNo");//订单编号
		String status=map.get("status");//订单状态
		String orderType=map.get("orderType");//订单类型
		String orderTime=map.get("orderTime");//历史订单
		String isShipments=map.get("isShipments");//发货状态
		
		//Modify By WuCF 20140120 新增查询条件 
		String logType=map.get("logType");//日期类型
		String startLogTime=map.get("startLogTime");//起始时间
		String endLogTime=map.get("endLogTime");//截止时间
		String isRetreatOrder=map.get("isRetreatOrder");//退单状态
		String payByCoin=map.get("payByCoin");//使用积分支付
		String payByJJ=map.get("payByJJ");//基金支付
		
		//modify by fu 2016-03-29 自助换货
		if(!StringUtil.isEmpty(selfHelpExchange)){
			hql.append(" and po.selfHelpExchange= :selfHelpExchange ");
		}
		if(!StringUtils.isEmpty(memberOrderNo)){
			hql.append(" and po.memberOrderNo = :memberOrderNo ");
		}
		if(!StringUtils.isEmpty(status)){
			if(!"-1".equals(status)){
				 hql.append(" and po.status = :status ");
			}
		}
		if(!StringUtils.isEmpty(orderType)){
			hql.append(" and po.orderType = :orderType ");
		}
		if(!StringUtils.isEmpty(orderTime)){
			//1:最近一周;2:最近一月; 3:最近三月; 4:最近半年; 5:最近一年;
			//Calendar calendar=Calendar.getInstance();
			//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			if("0".equals(orderTime)){
				
			}else if( "1".equals( orderTime ) ){
				hql.append(" and po.orderTime>=trunc(sysdate)-7 ");
			}else if( "2".equals( orderTime ) ){
				hql.append(" and po.orderTime>=trunc(sysdate)-30 ");
			}else if( "3".equals( orderTime ) ){
				hql.append(" and po.orderTime>=add_months( trunc(sysdate) ,-3 ) ");
			}else if( "4".equals( orderTime ) ){
				hql.append(" and po.orderTime>=add_months( trunc(sysdate) ,-6 ) ");
			}else if( "5".equals( orderTime ) ){
				hql.append(" and po.orderTime>=add_months( trunc(sysdate) ,-12) ");
			}else{
			}
		}
		if(!StringUtils.isEmpty(isShipments)){
			if(!"-1".equals(isShipments)){
			  hql.append(" and po.isShipments = :isShipments ");
			}
		}
		
		//Modify By WuCF添加时间判断 20140120
		if (!StringUtils.isEmpty(logType)) {
			if (!StringUtils.isEmpty(startLogTime)) {
				if ("A".equals(logType)) {
					hql.append(" and po.orderTime>=to_date(:startLogTime,'yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					hql.append(" and po.checkDate>=to_date(:startLogTime,'yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					hql.append(" and po.checkTime>=to_date(:startLogTime,'yyyy-mm-dd hh24:mi:ss')");
				}
			}
			if (!StringUtils.isEmpty(endLogTime)) {
				if ("A".equals(logType)) {
					hql.append(" and po.orderTime<=to_date(:endLogTime,'yyyy-mm-dd hh24:mi:ss')");
				}
				if ("C".equals(logType)) {
					hql.append(" and po.checkDate<=to_date(:endLogTime,'yyyy-mm-dd hh24:mi:ss')");
				}
				if ("BC".equals(logType)) {
					hql.append(" and po.checkTime<=to_date(:endLogTime,'yyyy-mm-dd hh24:mi:ss')");
				}
			}
		}
		
		if (!StringUtils.isEmpty(isRetreatOrder)) {
			if("0".equals(isRetreatOrder)){
				hql.append(" and (po.isRetreatOrder2 = :isRetreatOrder or isRetreatOrder2 is null)");
			}else{
				hql.append(" and po.isRetreatOrder2 = :isRetreatOrder ");
			}
		}
		
		if (!StringUtils.isEmpty(payByCoin)) {
			if("0".equals(payByCoin)){
				hql.append(" and (po.payByCoin = :payByCoin or po.payByCoin is null) ");
			}else{
				hql.append(" and po.payByCoin = :payByCoin ");
			}
		}

		if (!StringUtils.isEmpty(payByJJ)) {
			if("0".equals(payByJJ)){
				hql.append(" and (po.payByJj = :payByJJ or po.payByJj is null) ");
			}else{
				hql.append(" and po.payByJj = :payByJJ ");
			}
		}		
		hql.append(" order by po.moId desc");
		
		Query query = getSession().createQuery(hql.toString());
		StringUtil.dealSetParameter(query,"userCode",userCode);
		StringUtil.dealSetParameter(query,"companyCode",companyCode);
		StringUtil.dealSetParameter(query,"selfHelpExchange",selfHelpExchange);//自助换货 modify by fu 2016-03-29 
		StringUtil.dealSetParameter(query,"memberOrderNo",memberOrderNo);
		if(!StringUtils.isEmpty(status) && !"-1".equals(status)){
			StringUtil.dealSetParameter(query,"status",status);
		}
		StringUtil.dealSetParameter(query,"orderType",orderType);
		if(!StringUtils.isEmpty(isShipments) && !"-1".equals(isShipments)){
			StringUtil.dealSetParameter(query,"isShipments",isShipments);
		}
		if(!StringUtils.isEmpty(startLogTime) && !"null".equals(startLogTime)){
			StringUtil.dealSetParameter(query,"startLogTime",startLogTime+" 00:00:00");
		}
		if(!StringUtils.isEmpty(endLogTime) && !"null".equals(endLogTime)){
			StringUtil.dealSetParameter(query,"endLogTime",endLogTime+" 23:59:59");
		}
		StringUtil.dealSetParameter(query,"isRetreatOrder",isRetreatOrder);
		StringUtil.dealSetParameter(query,"payByCoin",payByCoin);
		StringUtil.dealSetParameter(query,"payByJJ",payByJJ);
		return query.list();
	}
	
	/**
	 * 查询指定会员订购的家套餐的数据的订单 Add By WuCF 20150410
	 */
	public List<JpoMemberOrder> getOrderByParamStj(Map<String, String> map){
		//可能按商品名称和编码查询   Modify By WuCF 暂时注释 2014-01-16
//		String hql="select po from JpoMemberOrder po,JpoMemberOrderList jmol where jmol.jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo='N05270100101CN1' and po.sysUser.userCode='"+map.get("userCode")+"' and po.sysUser.companyCode='"+map.get("companyCode")+"'";	
		//Modify By WuCF 20140630 修改绑定变量的方式
		StringBuffer hql= new StringBuffer("from JpoMemberOrder po where po.orderUserCode= :userCode and po.sysUser.companyCode= :companyCode ");	
		String userCode = map.get("userCode");//订单编号
		String companyCode = map.get("companyCode");//分公司
		String orderType=map.get("orderType");//订单类型
		
		if(!StringUtils.isEmpty(orderType)){
			hql.append(" and po.orderType = :orderType ");
		}
		hql.append(" and po.productFlag is not null order by po.amount ");
		
		Query query = getSession().createQuery(hql.toString());
		StringUtil.dealSetParameter(query,"userCode",userCode);
		StringUtil.dealSetParameter(query,"companyCode",companyCode);
		StringUtil.dealSetParameter(query,"orderType",orderType);
		return query.list();
	}
	
	/**
	 * 分页查询
	 */
	public List<JpoMemberOrder> getOrderByParamPage(Map<String, String> map,int pageNum,int pageSize){
		String hql="from JpoMemberOrder po where po.sysUser.userCode='"+map.get("userCode")+"' and po.sysUser.companyCode='"+map.get("companyCode")+"'";	
		String memberOrderNo=map.get("memberOrderNo");//订单编号
		String status=map.get("status");//订单状态
		String orderType=map.get("orderType");//订单类型
		String orderTime=map.get("orderTime");//历史订单
		String isShipments=map.get("isShipments");//发货状态
		if(!StringUtils.isEmpty(memberOrderNo))
		{
			hql+="  and po.memberOrderNo='"+memberOrderNo+"'";
		}
		if(!StringUtils.isEmpty(status))
		{
			if(!"-1".equals(status))
			{
				 hql+=" and po.status='"+status+"'";
			}
		}
		if(!StringUtils.isEmpty(orderType))
		{
			hql+=" and po.orderType='"+orderType+"'";
		}
		if(!StringUtils.isEmpty(orderTime))
		{
			//1:最近一周;2:最近一月; 3:最近三月; 4:最近半年; 5:最近一年;
			//Calendar calendar=Calendar.getInstance();
			//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			if("0".equals(orderTime))
			{
				
			}else if( "1".equals( orderTime ) ){
				hql+=" and po.orderTime>=trunc(sysdate)-7 ";
				//calendar.add( Calendar.DATE, -7);
				//hql+=" and po.orderTime>=to_date('"+sdf.format( calendar.getTime() )+"','yyyy-mm-dd') "
			}else if( "2".equals( orderTime ) ){
				hql+=" and po.orderTime>=trunc(sysdate)-30 ";
				//calendar.add( Calendar.DATE, -30);
				//hql+=" and po.orderTime>=to_date('"+sdf.format( calendar.getTime() )+"','yyyy-mm-dd') "
			}else if( "3".equals( orderTime ) ){
				hql+=" and po.orderTime>=add_months( trunc(sysdate) ,-3 ) ";
				
				//calendar.add( Calendar.MONTH, -3);
				//hql+=" and po.orderTime>=to_date('"+sdf.format( calendar.getTime() )+"','yyyy-mm-dd') "
			}else if( "4".equals( orderTime ) ){
				hql+=" and po.orderTime>=add_months( trunc(sysdate) ,-6 ) ";
				//calendar.add( Calendar.MONTH, -6);
				//hql+=" and po.orderTime>=to_date('"+sdf.format( calendar.getTime() )+"','yyyy-mm-dd') "
			}else if( "5".equals( orderTime ) ){
				hql+=" and po.orderTime>=add_months( trunc(sysdate) ,-12) ";
				//calendar.add( Calendar.MONTH, -12);
				//hql+=" and po.orderTime>=to_date('"+sdf.format( calendar.getTime() )+"','yyyy-mm-dd') "
			}else{
			}
			//java 
			
			
		}
		if(!StringUtils.isEmpty(isShipments))
		{
			if(!"-1".equals(isShipments))
			{
			  hql+=" and po.isShipments='"+isShipments+"'";
			}
		}
		hql+=" order by po.moId desc";
//		Query q=getSession().createQuery(hql);
//		return q.list();
		//返回分页数据
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	/**
	 * 查询首购单的审核时间
	 * 
	 * @param memberNo
	 * @return
	 */
	public String getMemberFirstOrderStatusTime(String memberNo) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		String sqlQuery = "select max(CHECK_DATE) log_create_time from jpo_member_order a where a.order_type='1' and a.status='2'";
		sqlQuery += " and user_code='" + memberNo + "'";
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map=this.getFirstValue(sqlQuery);
		Object logCreateTime =map.get("log_create_time");
		if (logCreateTime==null) {
			sqlQuery = "select max(CHECK_DATE) check_date,max(CREATE_TIME) create_time from jmi_member m where m.user_code='"
					+ memberNo + "'";
			map = this.getFirstValue(sqlQuery);
			if (map.get("check_date")!=null) {
				return sf.format(map.get("check_date"));
			}else if(map.get("create_time")!=null){
				return sf.format(map.get("create_time"));
			}else{
				return "2007-05-01";
			}
		}
		return sf.format(logCreateTime);
	}
	
	/**
     * 查询首购单的审核时间
     * 
     * @param memberNo
     * @return
     */
    public String getMemberCheckDate(String memberNo) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        String sqlQuery = "select max(CHECK_DATE) log_create_time from jpo_member_order a where a.order_type='1' and a.status='2'";
        sqlQuery += " and user_code='" + memberNo + "'";
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map=this.getFirstValue(sqlQuery);
        Object logCreateTime =map.get("log_create_time");
        if (logCreateTime==null) {
           return "2007-05-01 00:00:00";
        }
        return sf.format(logCreateTime);
    }
    
	public HashMap getFirstValue(String sqlQuery)
	{
		HashMap<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> list=this.getJdbcTemplate().queryForList(sqlQuery);
		if(list!=null && list.size()>=1){
			map = (HashMap<String,Object>)list.get(0);
		}
		return map;
	}
	
	
	/**
	 * 会员编号查找
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder) {
		String hql = "from JpoMemberOrder jpoMemberOrder where sysUser.userCode='"
				+ jpoMemberOrder.getSysUser().getUserCode() + "'";

		String orderType = jpoMemberOrder.getOrderType();
		if (!StringUtils.isEmpty(orderType)) {
			hql += " and jpoMemberOrder.orderType = '" + orderType + "'";
		}
		String status = jpoMemberOrder.getStatus();
		if (!StringUtils.isEmpty(status)) {
			hql += " and jpoMemberOrder.status = '" + status + "'";
		}

		Query q=getSession().createQuery(hql);
		return q.list();
	}
	
	public List getMemberOrderNopay(String memberNo){
		Query q = getSession().createQuery("from JpoMemberOrder j where j.orderUserCode = ? and j.status = 1");
		q.setParameter(0, memberNo);
		return q.list();
	}
	
	/**
	 * Add By WuCF 20131209 
	 * 查询指定行数的数据
	 * @param memberNo
	 * @return
	 */
	public List getMemberOrderNopay(String memberNo,Integer startIndex,Integer endIndex){
		/*Query q = getSession().createQuery("from JpoMemberOrder j where j.orderUserCode = ? and j.status = 1" +
				" and rownum between "+startIndex+" and "+endIndex);
		q.setParameter(0, memberNo);
		return q.list();*/
		StringBuffer paramsBuf = new StringBuffer("");
		
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" SELECT J.PRODUCTFLAG,J.ORDER_USER_CODE ,J.USER_CODE, ");
		sqlBuf.append(" J.MO_ID,J.MEMBER_ORDER_NO,TO_CHAR(J.ORDER_TIME,'yyyy-MM-dd HH:mm:ss') ORDER_TIME FROM JPO_MEMBER_ORDER J WHERE  ");
		//sqlBuf.append(" J.MO_ID,J.MEMBER_ORDER_NO,J.ORDER_TIME FROM JPO_MEMBER_ORDER J WHERE  ");
		sqlBuf.append(" J.ORDER_USER_CODE=? AND J.STATUS='1' AND ROWNUM>=? AND ROWNUM<= ? ");
		
		paramsBuf.append(","+memberNo);
		paramsBuf.append(","+startIndex);
		paramsBuf.append(","+endIndex);
		
		//返回时调用分页的查询的方法 
	    Object[] parameters = paramsBuf.toString().substring(1).split(",");
		return jdbcTemplate.queryForList(sqlBuf.toString(),parameters);
	}
	
	/**
	 * 获取订单数量
	 * 
	 * @param orderType
	 * @param userCode
	 * @param status
	 * @return
	 */
	public List getJpoMemberOrdersByTCS(String orderType, String userCode,
			String status) {
		String hqlQuery = "from JpoMemberOrder where orderType='" + orderType
				+ "' and sysUser.userCode='" + userCode + "' and status = '"
				+ status + "'";
		
		Query q=getSession().createQuery(hqlQuery);
		return q.list();
		
	}
	
	/**
	 * 时间段内获取商品订购量
	 */
	@Override
	public int getProductsSum(String productNo, String startTime,
			String endTime, String payBy) {
		String sql = "Select nvl(Sum(Jmol.Qty),0) sumqty";
		sql += " From Jpo_Member_Order Jmo, Jpo_Member_Order_List Jmol";
		sql += " Where Jmo.Mo_Id = Jmol.Mo_Id";
		sql += " And jmo.check_time >= to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss')";
		sql += " And jmo.check_time <= to_date('" + endTime + "','yyyy-mm-dd hh24:mi:ss')";
		sql += " And Jmo.Status = 2";
		if(!StringUtil.isEmail(payBy)){
			if("byCoin".equals(payBy)){
				sql += " And Jmo.PAY_BY_COIN = 1";
			}
		}
		sql += " And exists(select 1 from jpm_product_sale_new t1,jpm_product_sale_team_type t2 ";
		sql += "where t1.uni_no=t2.uni_no and t1.company_code='CN' and t2.ptt_id=jmol.product_id";
		sql += " and t1.Product_No = '" + productNo + "')";
//		sql += " And Jmol.Product_Id In (Select Uni_No";
//		sql += " From Jpm_Product_Sale";
//		sql += " Where Product_No = '" + productNo + "'";
//		sql += " And Company_Code = 'CN')";
		List<Map<String, Object>> list = this.getJdbcTemplate3().queryForList(sql); 
		log.info("sumqty is -------------------------------------------"+list.get(0).toString());
		Map map = null;
        Map mapT = null;
		for(Object obj : list){
		    map = (Map)obj;
        }
		
//		Map map = (Map)this.jdbcTemplate.queryForMap(sql).get(0);
		Log.info("map is----------------------------------------------------------------------"+map);
		if(null != map.get("sumqty"))
		{
		    return Integer.parseInt(map.get("sumqty").toString());
		}
		return Integer.parseInt("0");
	}

	/**
	 * 按条件获取订单总金额
	 * 
	 * @param crm
	 * @return
	 */
	public BigDecimal getJpoMemberOrderStatics(CommonRecord crm){
		String sql = "select nvl(Sum(Jmo.pv_amt),0) pv from jpo_member_order jmo where 1=1";
		
		String stauts = crm.getString("stauts", "");
		if (!StringUtils.isEmpty(stauts)) {
			sql += " and jmo.status = '" + stauts + "'";
		}
		
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sql += " and jmo.user_code = '" + userCode + "'";
		}
		
		String orderType = crm.getString("orderType", "");
		if (!StringUtils.isEmpty(orderType)) {
			sql += " and jmo.order_type in (" + orderType + ")";
		}
		
		String startLogTime = crm.getString("startLogTime", "");
		if (!StringUtils.isEmpty(startLogTime)) {
			sql += " and jmo.check_time >=to_date('" + startLogTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		
		String endLogTime = crm.getString("endLogTime", "");
		if (!StringUtils.isEmpty(endLogTime)) {
			sql += " and jmo.check_time < to_date('" + startLogTime + "','yyyy-mm-dd hh24:mi:ss')";
		}
		Map map = (Map)this.getJdbcTemplate().queryForList(sql).get(0); 
		return new BigDecimal(map.get("pv").toString());
	}
	@Override
	public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo) {
		// TODO Auto-generated method stub
		//return (JpoMemberOrder)this.getObjectByHqlQuery("from JpoMemberOrder where memberOrderNo='"+memberOrderNo.trim()+"'");
		return (JpoMemberOrder)this.getObjectByHqlQuery("from JpoMemberOrder where memberOrderNo='"+memberOrderNo+"'");
	}
    @Override
    public void modifyOrderStatusByMoId(Map<String, String> map)
    {
        StringBuffer sql = new StringBuffer("update jpo_member_order set config_status = '" + map.get("status") + "' where mo_id = "+ map.get("moId"));
        this.jdbcTemplate.execute(sql.toString());
    }
    
	@Override
	public List<JpoMemberOrder> findOrderByUserCode(String userCode,String proNo) {
		String sql = "select o.* from jpo_member_order o ," +
				"jpo_member_order_list t,Jpm_Product_Sale_New s " +
				"where o.mo_id=t.mo_id and o.user_code=?  " +
				"and s.uni_no=t.product_id and s.product_no=? ";
		
		//String hql = "from JpoMemberOrder t where t.sysUser.userCode=?";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter(0, userCode);
		query.setParameter(1, proNo);
		return query.list();
	}
	
	/**
	 * 提供手机统计订单
	 * @param request   扩展查询条件        隐藏查询条件timeType 1:订购日期，2：审核日期:3：审核时间
	 * @param startCreateTime   开始时间
	 * @param endCreateTime		结束时间	
	 * @param pageNum			页面
	 * @param pageSize			每页显示数量
	 * @return
	 */
	public List<JpoMemberOrder> getJpoMemberOrderByMobile(HttpServletRequest request,String startCreateTime,String endCreateTime,int pageNum,int pageSize){
		String hql="from JpoMemberOrder po where 1=1";
		String userCode =request.getParameter("userCode");
		String companyCode =request.getParameter("companyCode");
		if(StringUtils.isNotEmpty(userCode)&&StringUtils.isNotEmpty(companyCode)){
			hql +=" and po.sysUser.userCode='" + userCode + "' and po.sysUser.companyCode='"+companyCode+"'";
		}
		String status =request.getParameter("status");
		if(StringUtils.isNotEmpty(status)){ //1.新订单 2.已核销 3.取消
			hql += " and po.status = '" + status + "'";
		}
		String orderType =request.getParameter("orderType");
		if(StringUtils.isNotEmpty(orderType)){
			hql += " and po.orderType = '" + orderType + "'";
		}
		String isMobile =request.getParameter("isMobile"); //1手机端 0否
		if(StringUtils.isNotEmpty(isMobile)){
			hql += " and po.isMobile = '" + isMobile + "'";
		}
		String payByCoin =request.getParameter("payByCoin"); //1积分 0否
		if(StringUtils.isNotEmpty(payByCoin)){
			hql += " and po.payByCoin = '" + payByCoin + "'";
		}
		String payByJj =request.getParameter("payByJj");//1基金 0否
		if(StringUtils.isNotEmpty(payByCoin)){
			hql += " and po.payByJj = '" + payByJj + "'";
		}
		String timeType=request.getParameter("timeType");
		String timeTypeStr="po.orderTime";
		if("1".equals(timeType)){
			timeTypeStr="po.orderTime";
		}else if("2".equals(timeType)){
			timeTypeStr="po.checkTime";
		}else if("3".equals(timeType)){
			timeTypeStr="po.checkDate";
		}
		if(StringUtils.isNotEmpty(startCreateTime)){//'yyyy-mm-dd hh24:mi:ss'
			hql += " and "+timeTypeStr+" >= to_date('" + startCreateTime+" 00:00:00" + "','yyyy-mm-dd hh24:mi:ss')";
		}

		if(StringUtils.isNotEmpty(endCreateTime)){
			hql += " and "+timeTypeStr+" <= to_date('" + endCreateTime+" 23:59:59" + "','yyyy-mm-dd hh24:mi:ss')";
		}
		hql += " order by "+ timeTypeStr +" desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult((pageNum-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	/**
	 * 得到指定的会员
	 * @param userCode
	 * @return
	 */
	public List<Map<String, Object>> getTatalPrice(String userCode){
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" select t1.order_type ORDER_TYPE,sum(t3.price*t2.qty)||'-'||sum(t3.pv*t2.qty) PRICE_PV  ");
		sqlBuf.append(" from jpo_shopping_cart_order t1, ");
		sqlBuf.append(" jpo_shopping_cart_order_list t2,jpm_product_sale_team_type t3 ");
		sqlBuf.append(" where t1.sc_id=t2.sc_id and t2.product_id=t3.ptt_id  ");
		sqlBuf.append(" and t1.user_code='");
		sqlBuf.append(userCode);
		sqlBuf.append("' and IS_CHECK='1' group by t1.order_type ORDER BY T1.ORDER_TYPE ");
		return this.getJdbcTemplate().queryForList(sqlBuf.toString());
		//return this.findObjectsBySQL(sqlQuery);
	}
	
	/**
	 * @Description:得到指定的会员的购物车总金额
	 * @author:			侯忻宇
	 * @date:		    2016-11-28
	 * @param userCode
	 * @param isCheck
	 * @return:
	 * @exception:
	 * @return:
	 */
	public List<Map<String, Object>> getTatalPrice(String userCode,String isCheck){
		StringBuffer sqlBuf = new StringBuffer("");
		sqlBuf.append(" select t1.order_type ORDER_TYPE,sum(t3.price*t2.qty)||'-'||sum(t3.pv*t2.qty) PRICE_PV  ");
		sqlBuf.append(" from jpo_shopping_cart_order t1, ");
		sqlBuf.append(" jpo_shopping_cart_order_list t2,jpm_product_sale_team_type t3 ");
		sqlBuf.append(" where t1.sc_id=t2.sc_id and t2.product_id=t3.ptt_id  ");
		sqlBuf.append(" and t1.user_code='");
		sqlBuf.append(userCode);
		sqlBuf.append("' and t1.is_check='");
		sqlBuf.append(isCheck);
		sqlBuf.append("' group by t1.order_type ORDER BY T1.ORDER_TYPE ");
		return this.getJdbcTemplate().queryForList(sqlBuf.toString());
		//return this.findObjectsBySQL(sqlQuery);
	}
	
	
	/**
     * 生态家套餐订单级联删除
     * @param order
     * @return
     */
	public boolean deleteOrderByMoids(String moids) {
		boolean result = true;
		try{
			String[] sqls = new String[3];
			
			//将首单标示设置为0
			String sql = "update jmi_member t1 set t1.not_first='0' where exists( "+
				  " select 1 from jpo_member_order t2 where t1.user_code=t2.user_code and t2.mo_id in("+moids+"))";
			sqls[0] = sql;
			
			//删除订单
			sql = "delete from jpo_member_order where mo_id in(" + moids+")";
			sqls[1] = sql;
			
			sql = "delete from jpo_member_order_list where mo_id in(" + moids+")";
			sqls[2] = sql;
			
			log.info("sqls:"+sqls);
			log.info(sqls[0]);
			log.info(sqls[1]);
			log.info(sqls[2]);
			int[] nums = this.getJdbcTemplate().batchUpdate(sqls);
		}catch(Exception e){
			result = false;
		}
		return result;
	}
	
	
	public List<JpoMemberOrder> getOrderByType(String type,String userCode){
		String sql = "from JpoMemberOrder t where t.orderType=? and t.sysUser.userCode=? ";
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, type);
		query.setParameter(1, userCode);
		
		return query.list();
	}
	
	public Integer callSTJFunction(String orderNo, Integer type) {
		/*SpringStoredProcedure spComp = new SpringStoredProcedure(this.dataSource2, "pro_jtc_com",true);

		spComp.setOutParameter("out_code", oracle.jdbc.OracleTypes.INTEGER);
		spComp.setParameter("nmo_id", java.sql.Types.VARCHAR);
		spComp.setParameter("njtc_type", java.sql.Types.INTEGER);

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("nmo_id", orderNo);
		inComp.put("njtc_type", type);
		
		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();
		return Integer.parseInt(resultComp.get("out_code").toString());*/
		return 1;
	}
	
	public DataSource getDataSource2() {
		return dataSource2;
	}
	public void setDataSource2(DataSource dataSource2) {
		this.dataSource2 = dataSource2;
	}
	
	/**
     * 更新订单经销商字段
     * @param orderId
     * @param saleNo
     */
	@Override
	public void updateJpoMemberOrderSaleNo(String orderId, String saleNo) {
		
		String sql = "update JPO_MEMBER_ORDER set SALE_NO='"+saleNo+"' where MO_ID='"+orderId+"'";
		
		this.getJdbcTemplate().execute(sql);
	}
	
	/**
     * Add By WuCF 20160805 修改订单支付来源标示
     */
	@Override
	public void updateJpoMemberOrderPaymentType(String moid,String paymentType) {
		String sql = "UPDATE JPO_MEMBER_ORDER SET PAYMENT_TYPE = '"+paymentType+"' WHERE MO_ID='" + moid + "'";
		this.getJdbcTemplate().execute(sql);
	}

	/**
	 * 删除订单，需要备份到临时表中
	 */
	@Override
	public void removeJpoMemberOrder(String moId) {
		String[] sqls = new String[4];
		StringBuffer sqlBuf = null;
		//事务处理数据
		
		//1.备份订单明细表数据
		sqlBuf = new StringBuffer("insert into jpo_member_order_list_temp select * from jpo_member_order_list where mo_id='");
		sqlBuf.append(moId);
		sqlBuf.append("'");
		sqls[0] = sqlBuf.toString();
		
		//2.备份订单表数据
		sqlBuf = new StringBuffer("insert into jpo_member_order_temp select * from jpo_member_order where mo_id='");
		sqlBuf.append(moId);
		sqlBuf.append("'");
		sqls[1] = sqlBuf.toString();
		
		//3.删除订单明细表数据
		sqlBuf = new StringBuffer("delete from jpo_member_order_list where mo_id='");
		sqlBuf.append(moId);
		sqlBuf.append("'");
		sqls[2] = sqlBuf.toString();
		
		//4.删除订单表数据
		sqlBuf = new StringBuffer("delete from jpo_member_order where mo_id='");
		sqlBuf.append(moId);
		sqlBuf.append("'");
		sqls[3] = sqlBuf.toString();
		
		this.jdbcTemplate.batchUpdate(sqls);
	}

	@Override
	public String getPaperNumber(String userCode) {
		String hql = "SELECT t.papernumber from JmiMember t where t.userCode=? ";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, userCode);
		
		
		 List list = query.list();
		 return (String) list.get(0);
		
	}

	@Override
	public List<JmiMember> getJmimemberList(String paperNumber) {
		String hql = " from JmiMember t where t.papernumber=? ";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, paperNumber);
		
		
		 return query.list();
	}
}
