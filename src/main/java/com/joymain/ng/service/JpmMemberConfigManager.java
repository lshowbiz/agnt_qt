package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JpmMemberConfigManager extends GenericManager<JpmMemberConfig, Long> {
    
	public Pager<JpmMemberConfig> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
	 * 根据商品编号查询商品配置信息
	 * @param map
	 * @return
	 */
	public List<JpmMemberConfig> getJpmMemberConfigListByProductId(Map<String,String> map);
	
	/**
	 * 添加配置信息 
	 * @return
	 */
	public Integer addJpmMemberConfig(JpmMemberConfig jpmMemberConfig);
	
	/**
	 * 修改配置信息
	 * @return
	 */
	public Integer modifyJpmMemberConfig(JpmMemberConfig jpmMemberConfig);
	
	/**
	 * 删除配置信息
	 * @param configNo
	 * @return
	 */
	public Integer delJpmMemberConfig(Long configNo);
	
	/**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map);
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * @param map
     * @return
     */
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map);
    
    public JpmMemberConfig getJpmMemberConfigByConfigNo(Long configNo);
    
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo,String status,Long price);
    
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo,String status);
    
    public String getProductNoByProductId(String productId);
    
    /**
     * 酒业配置单支付审核
     * @param configNo 订单号
     * @param jsysUser 操作用户
     */
    public void checkJpmMemberConfig(String configNo,JsysUser jsysUser) throws AppException;
}