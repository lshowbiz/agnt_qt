package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpmConfigDetailedDao;
import com.joymain.ng.dao.JpmConfigSpecDetailedDao;
import com.joymain.ng.dao.JpmMemberConfigDao;
import com.joymain.ng.dao.JpmProductWineTemplateDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.JpoMemberOrderListDao;
import com.joymain.ng.model.JpmConfigDetailed;
import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpmProductWineTemplate;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpmConfigDetailedManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jpmConfigDetailedManager")
@WebService(serviceName = "JpmConfigDetailedService", endpointInterface = "com.joymain.ng.service.JpmConfigDetailedManager")
public class JpmConfigDetailedManagerImpl extends GenericManagerImpl<JpmConfigDetailed, Long>
    implements JpmConfigDetailedManager
{
    JpmConfigDetailedDao jpmConfigDetailedDao;
    
    @Autowired
    JpmConfigSpecDetailedDao jpmConfigSpecDetailedDao;
    
    @Autowired
    JpmMemberConfigDao jpmMemberConfigDao;
    
    @Autowired
    JpoMemberOrderListDao jpoMemberOrderListDao;
    
    @Autowired
    JpmProductWineTemplateDao jpmProductWineTemplateDao;
    
    @Autowired
    JpoMemberOrderDao jpoMemberOrderDao;

    /** 保存全新订单 */
    private static final String SAVE_CONFIG = "0";
    
    /** 保存订单下新规格 */
    private static final String SAVE_SPEC_CONFIG = "1";
    
    /** 保存规格下详细信息 */
    private static final String SAVE_DETAILED = "2";
    @Autowired
    public JpmConfigDetailedManagerImpl(JpmConfigDetailedDao jpmConfigDetailedDao)
    {
        super(jpmConfigDetailedDao);
        this.jpmConfigDetailedDao = jpmConfigDetailedDao;
    }
    
    public Pager<JpmConfigDetailed> getPager(Collection<SearchBean> searchBeans,
        Collection<OrderBy> OrderBys, int currentPage, int pageSize)
    {
        // TODO Auto-generated method stub
        return jpmConfigDetailedDao.getPager(JpmConfigDetailed.class,
            searchBeans,
            OrderBys,
            currentPage,
            pageSize);
    }
    
    @Override
    public void addJpmConfigDetailed(HttpServletRequest request)
    {
        Long price = 0l;
        String subSize = request.getParameter("jpmConfigDetailedSize");
        int size = new Integer(subSize).intValue();
        List<JpmConfigDetailed> list = new ArrayList<JpmConfigDetailed>();
        //用户变更信息后更新配置规格信息
        String complateAmount = request.getParameter("chooseConfigNum");
        String complateWeight = request.getParameter("curWeight");
        //单个商品重量
        String weight = request.getParameter("weight");
        String productTemplateNo = request.getParameter("productTemplateNo");
        String productTemplateName = request.getParameter("productTemplateName");
        
        JpmConfigSpecDetailed jpmConfigSpecDetailed = new JpmConfigSpecDetailed();
        jpmConfigSpecDetailed.setProductTemplateNo(productTemplateNo);
        jpmConfigSpecDetailed.setProductTemplateName(productTemplateName);
        if (StringUtils.isNotEmpty(complateAmount))
        {
            jpmConfigSpecDetailed.setComplateAmount(Long.parseLong(complateAmount));
        }
        if (StringUtils.isNotEmpty(complateWeight))
        {
            jpmConfigSpecDetailed.setComplateWeight(Long.parseLong(complateWeight));
        }
        
        //产品信息 
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        
        //订单信息 
        String moId = request.getParameter("moId");
        String molId = request.getParameter("molId");
        String specNo = request.getParameter("specNo");
        //用户信息
        JsysUser jsysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String saveType = request.getParameter("saveType");
        JpmProductWineTemplate jpmProductWineTemplate = new JpmProductWineTemplate();
        //根据模版id查询模版名称
        if(StringUtils.isNotEmpty(productTemplateNo))
        {
            jpmProductWineTemplate =  jpmProductWineTemplateDao.getJpmProductWineTemplate(Long.parseLong(productTemplateNo));
        }
        
        // 全新添加订单
        if (SAVE_CONFIG.equals(saveType))
        {
            //第一步 begin
            //新增订单配置表
            JpmMemberConfig jpmMemberConfig = new JpmMemberConfig();
            jpmMemberConfig.setCompanyCode(jsysUser.getCompanyCode());
            jpmMemberConfig.setUserCode(jsysUser.getUserCode());
            jpmMemberConfig.setMolId(Long.parseLong(molId));
            jpmMemberConfig.setMoId(Long.parseLong(moId));
            //产品信息
            JpmProductSaleTeamType jpmProductSaleTeamType = new JpmProductSaleTeamType();
            jpmProductSaleTeamType.setPttId(Long.parseLong(productId));
            jpmMemberConfig.setJpmProductSaleTeamType(jpmProductSaleTeamType);
            jpmMemberConfig.setProductName(productName);
            jpmMemberConfig.setCreateTime(new Date());
            //数量：页面所填数量
            jpmMemberConfig.setAmount(Long.parseLong(complateAmount));
            
            Map<String,Long> map = new HashMap<String,Long>();
            map.put("molId", Long.parseLong(molId));
            JpoMemberOrderList jpoMemberOrderList = jpoMemberOrderListDao.getJpoMemberOrderListByMolId(map);
            //配置总重量：用户填写数量*单个商品重量
            jpmMemberConfig.setWeight(new BigDecimal(Long.parseLong(complateAmount) * jpoMemberOrderList.getWeight().longValue()));
            
            //0表示未支付
            jpmMemberConfig.setStatus("0");
            //将订单配置数据增加
            jpmMemberConfigDao.addJpmMemberConfig(jpmMemberConfig);
            //第一步 end
            
            //第二步 begin
            // 添加配置规格表数据
            jpmConfigSpecDetailed.setCreateTime(new Date());
            jpmConfigSpecDetailed.setJpmMemberConfig(jpmMemberConfig);
            
            //获取规格下所以配件总价格
            List ls = installJpmConfigDetailed(request, size, list,jpmConfigSpecDetailed);
            jpmConfigSpecDetailed.setPrice(Long.parseLong(ls.get(0).toString()));
            jpmConfigSpecDetailed.setProductNum(Long.parseLong(ls.get(1).toString()));
            if(StringUtils.isNotEmpty(jpmProductWineTemplate.getProductTemplateName()))
            {
                jpmConfigSpecDetailed.setProductTemplateName(jpmProductWineTemplate.getProductTemplateName());
            }
            jpmConfigSpecDetailedDao.insertJpmConfigSpecDetailed(jpmConfigSpecDetailed);
            for(JpmConfigDetailed jpmConfigDetailed : list)
            {
                jpmConfigDetailed.setSpecNo(jpmConfigSpecDetailed.getSpecNo());
            }
            //第二步 end
            
            //修改订单配置状态为部分配置
            Map<String,String> modMap = new HashMap<String,String>();
            modMap.put("status", "2");
            modMap.put("moId", moId);
            jpoMemberOrderDao.modifyOrderStatusByMoId(modMap);
        }
        else if(SAVE_SPEC_CONFIG.endsWith(saveType))
        {
            String configNo = request.getParameter("configNo");
            if(StringUtils.isNotEmpty(configNo))
            {
                JpmMemberConfig jpmMemberConfig = new JpmMemberConfig();
                jpmMemberConfig.setConfigNo(Long.parseLong(configNo));
                jpmConfigSpecDetailed.setJpmMemberConfig(jpmMemberConfig);
            }
            //新增规格
            jpmConfigSpecDetailed.setCreateTime(new Date());
            //修改规格重量
            List ls = installJpmConfigDetailed(request, size, list,jpmConfigSpecDetailed);
            jpmConfigSpecDetailed.setPrice(Long.parseLong(ls.get(0).toString()));
            jpmConfigSpecDetailed.setProductNum(Long.parseLong(ls.get(1).toString()));
            //修改价格
            if(StringUtils.isNotEmpty(jpmProductWineTemplate.getProductTemplateName()))
            {
                jpmConfigSpecDetailed.setProductTemplateName(jpmProductWineTemplate.getProductTemplateName());
            }
            jpmConfigSpecDetailedDao.insertJpmConfigSpecDetailed(jpmConfigSpecDetailed);
            for(JpmConfigDetailed jpmConfigDetailed : list)
            {
                jpmConfigDetailed.setSpecNo(jpmConfigSpecDetailed.getSpecNo());
            }
        }
        else
        {
          //修改价格
            if(StringUtils.isNotEmpty(jpmProductWineTemplate.getProductTemplateName()))
            {
                jpmConfigSpecDetailed.setProductTemplateName(jpmProductWineTemplate.getProductTemplateName());
            }
            String configNo = request.getParameter("configNo");
            if(StringUtils.isNotEmpty(configNo))
            {
                JpmMemberConfig jpmMemberConfig = new JpmMemberConfig();
                jpmMemberConfig.setConfigNo(Long.parseLong(configNo));
                jpmConfigSpecDetailed.setJpmMemberConfig(jpmMemberConfig);
            }
            //修改规格下明细
            jpmConfigSpecDetailed.setSpecNo(Long.parseLong(specNo));
            List ls = installJpmConfigDetailed(request, size, list,jpmConfigSpecDetailed);
            jpmConfigSpecDetailed.setPrice(Long.parseLong(ls.get(0).toString()));
            jpmConfigSpecDetailed.setProductNum(Long.parseLong(ls.get(1).toString()));
            //变更后相应更改规格表
            jpmConfigSpecDetailed.setSpecNo(Long.parseLong(specNo));
            jpmConfigSpecDetailedDao.modifyJpmConfigSpecDetailed(jpmConfigSpecDetailed);
        }
        //三种方式都需要保存或者修改详细表数据
        //先删除再重新保存
        if(StringUtils.isNotEmpty(specNo))
        {
            jpmConfigDetailedDao.delJpmConfigDetailedBySpecNo(Long.parseLong(specNo));
        }
        else if(null != jpmConfigSpecDetailed.getSpecNo())
        {
            jpmConfigDetailedDao.delJpmConfigDetailedBySpecNo(jpmConfigSpecDetailed.getSpecNo());
        }
        
        Set<JpmConfigDetailed> configSet = new HashSet<JpmConfigDetailed>(0);
        configSet.addAll(list);
        jpmConfigDetailedDao.saveJpmConfigDetailedList(configSet);
    }

    private List<Long> installJpmConfigDetailed(HttpServletRequest request, int size,
        List<JpmConfigDetailed> list,JpmConfigSpecDetailed jpmConfigSpecDetailed)
    {
        List<Long> ls = new ArrayList<Long>();
        Long priceCount = 0l;
        Long productNum = 0l;
        for (int i = 0; i < size; i++)
        {
            JpmConfigDetailed jpmConfigDetailed = new JpmConfigDetailed();
            String configdetailedNo =
                request.getParameter("jpmConfigDetailed[" + i + "].configdetailedNo");
            String subNo = request.getParameter("jpmConfigDetailed[" + i + "].subNo");
            String subName = request.getParameter("jpmConfigDetailed[" + i + "].subName");
            String price =
                StringUtils.defaultIfEmpty(request.getParameter("jpmConfigDetailed[" + i
                    + "].price"), "0");
            String isMustSelected =
                StringUtils.defaultIfEmpty(request.getParameter("jpmConfigDetailed[" + i
                    + "].isMustSelected"), "0");
            String isMainMaterial =
                StringUtils.defaultIfEmpty(request.getParameter("jpmConfigDetailed[" + i
                    + "].isMainMaterial"), "0");
            
            String subAmount = request.getParameter("jpmConfigDetailed[" + i + "].subAmount");
            //判断是否为主料
            if("1".equals(isMainMaterial))
            {
                if(StringUtils.isNotEmpty(subAmount))
                {
                    productNum = Long.parseLong(subAmount);
                }
            }
            String specNo = request.getParameter("jpmConfigDetailed[" + i + "].specNo");
            String idf = request.getParameter("jpmConfigDetailed[" + i + "].idf");
            String isCheck = request.getParameter("jpmConfigDetailed[" + i + "].isCheck");
            String remark = request.getParameter("jpmConfigDetailed[" + i + "].remark");
//            if(StringUtils.isNotEmpty(configdetailedNo)){
//                jpmConfigDetailed.setConfigdetailedNo(Long.parseLong(configdetailedNo));
//            }
            
            jpmConfigDetailed.setSubNo(subNo);
            jpmConfigDetailed.setSubName(subName);
            jpmConfigDetailed.setSubAmount(Long.parseLong(subAmount));
            jpmConfigDetailed.setIsMustSelected(isMustSelected);
            jpmConfigDetailed.setPrice(Long.parseLong(price));
            jpmConfigDetailed.setRemark(remark);
            jpmConfigDetailed.setIsMainMaterial(isMainMaterial);
            
            if(null != jpmConfigSpecDetailed)
            {
                jpmConfigDetailed.setSpecNo(jpmConfigSpecDetailed.getSpecNo());
            }
            else
            {
                specNo = request.getParameter("specNo");
                jpmConfigDetailed.setSpecNo(Long.parseLong(specNo));
            }
            if (StringUtils.isNotEmpty(idf))
            {
                if (idf.contains(","))
                {
                    idf = idf.split(",")[1];
                }
                jpmConfigDetailed.setIdf(Long.parseLong(idf));
            }
            if("0".equals(isMustSelected))
            {
                if("1".equals(isCheck))
                {
                    priceCount += Long.parseLong(price);
                    list.add(jpmConfigDetailed);
                }
            }
            else
            {
                priceCount += Long.parseLong(price);
                list.add(jpmConfigDetailed);
            }
        }
        ls.add(priceCount);
        ls.add(productNum);
        return ls;
    }
    
    @Override
    public Integer delJpmConfigDetailed(Long detailedId)
    {
        // TODO Auto-generated method stub
        return jpmConfigDetailedDao.delJpmConfigDetailed(detailedId);
    }
    
    @Override
    public Integer modifyJpmConfigDetailed(JpmConfigDetailed jpmConfigDetailed)
    {
        // TODO Auto-generated method stub
        return jpmConfigDetailedDao.modifyJpmConfigDetailed(jpmConfigDetailed);
    }
    
    @Override
    public List<JpmConfigDetailed> getJpmConfigDetailedBySpecNo(String specNo)
    {
        return jpmConfigDetailedDao.getJpmConfigDetailedBySpecNo(specNo);
    }

    @Override
    public JpmConfigDetailed getJpmConfigDetailedNumBySpecNo(String specNo)
    {
        return jpmConfigDetailedDao.getJpmConfigDetailedNumBySpecNo(specNo);
    }
    
}