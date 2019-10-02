package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpmMemberConfigDao;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JpmConfigDetailed;
import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JpmProductWineTemplateSub;
import com.joymain.ng.model.JpmSendConsignment;
import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.model.JpmWineOrderListInterface;
import com.joymain.ng.model.JpmWineSettingInf;
import com.joymain.ng.model.JpmWineSettingListInf;
import com.joymain.ng.model.JpmWineTemplatePicture;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JpmConfigDetailedManager;
import com.joymain.ng.service.JpmConfigSpecDetailedManager;
import com.joymain.ng.service.JpmMemberConfigManager;
import com.joymain.ng.service.JpmProductWineTemplateSubManager;
import com.joymain.ng.service.JpmSendConsignmentManager;
import com.joymain.ng.service.JpmWineTemplatePictureManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.WebContextFactoryUtil;
import com.joymain.ng.util.wine.WineInterfaceUtil;

@Service("jpmMemberConfigManager")
@WebService(serviceName = "JpmMemberConfigService", endpointInterface = "com.joymain.ng.service.JpmMemberConfigManager")
public class JpmMemberConfigManagerImpl extends GenericManagerImpl<JpmMemberConfig, Long> implements
    JpmMemberConfigManager
{
    JpmMemberConfigDao jpmMemberConfigDao;
    
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    
    @Autowired
    JpmConfigSpecDetailedManager specManager;
    
    @Autowired
    JpmConfigDetailedManager detailedManager;
    
    @Autowired
    JpmProductWineTemplateSubManager subManager;
    
    @Autowired
    JpmWineTemplatePictureManager jpmWineTemplatePictureManager;
    
    @Autowired
    JpmSendConsignmentManager jpmSendConsignmentManager;
    
    @Autowired
    JmiAddrBookManager jmiAddrBookManager;
    
    @Autowired
    JpoMemberOrderManager jpoMemberOrderManager;
    
    @Autowired
    public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager)
    {
        this.jfiBankbookJournalManager = jfiBankbookJournalManager;
    }
    
    @Autowired
    public JpmMemberConfigManagerImpl(JpmMemberConfigDao jpmMemberConfigDao)
    {
        super(jpmMemberConfigDao);
        this.jpmMemberConfigDao = jpmMemberConfigDao;
    }
    
    public Pager<JpmMemberConfig> getPager(Collection<SearchBean> searchBeans,
        Collection<OrderBy> OrderBys, int currentPage, int pageSize)
    {
        // TODO Auto-generated method stub
        return jpmMemberConfigDao.getPager(JpmMemberConfig.class,
            searchBeans,
            OrderBys,
            currentPage,
            pageSize);
    }
    
    @Override
    public Integer delJpmMemberConfig(Long configNo)
    {
        // TODO Auto-generated method stub
        return jpmMemberConfigDao.delJpmMemberConfig(configNo);
    }
    
    @Override
    public List<JpmMemberConfig> getJpmMemberConfigListByProductId(Map<String, String> map)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Integer addJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        // TODO Auto-generated method stub
        return jpmMemberConfigDao.addJpmMemberConfig(jpmMemberConfig);
    }
    
    @Override
    public Integer modifyJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        // TODO Auto-generated method stub
        return jpmMemberConfigDao.modifyJpmMemberConfig(jpmMemberConfig);
    }
    
    /**
     * 根据订单商品编号查询该商品的已配置列表
     * 
     * @param map
     * @return
     */
    @Override
    public List<JpmMemberConfig> getWineConfigByMolId(Map<String, Long> map)
    {
        return jpmMemberConfigDao.getWineConfigByMolId(map);
    }
    
    @Override
    public JpmMemberConfig getJpmMemberConfigByConfigNo(Long configNo)
    {
        return jpmMemberConfigDao.getJpmMemberConfigByconfigNo(configNo);
    }
    
    @Override
    public List<JpmMemberConfig> getNoStatusWineConfigByMolId(Map<String, Long> map)
    {
        return jpmMemberConfigDao.getNoStatusWineConfigByMolId(map);
    }
    
    @Override
    public void modifyJpmMemberConfigStatusByConfigNo(String configNo, String status,Long price)
    {
        jpmMemberConfigDao.modifyJpmMemberConfigStatusByConfigNo(configNo, status,price);
    }
    
    @Override
    public List<JpmMemberConfig> getAllConfigStatusByConfigNo(String configNo, String status)
    {
        return jpmMemberConfigDao.getAllConfigStatusByConfigNo(configNo, status);
    }
    
    @Override
    public String getProductNoByProductId(String productId)
    {
        return jpmMemberConfigDao.getProductNoByProductId(productId);
    }
    
    /**
     * 酒业配置单支付审核
     * 
     * @param configNo 订单号
     * @param jsysUser 操作用户
     */
    public void checkJpmMemberConfig(String configNo, JsysUser jsysUser) throws AppException
    {
        try
        {
            JpmMemberConfig jpmMemberConfig = this.get(new Long(configNo));
            //1.扣钱
            //公司编码
            String companyCode = jsysUser.getCompanyCode();
                   
            //资金类别
            Integer[] moneyType = new Integer[1];
            moneyType[0] = 100;//100代表审核酒业配置订单
                   
            //要扣的金额
            BigDecimal[] moneyArray = new BigDecimal[1];
            moneyArray[0] = jpmMemberConfig.getPrice();
                   
            //摘要
            String[] notes = new String[1];
            notes[0] = "酒业配置订单支付,订单号：" + jpmMemberConfig.getMoId();
                   
            //电子存折扣钱
            jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode,
            jsysUser, moneyType, moneyArray, jsysUser.getUserCode(),
            jsysUser.getUserName(), "JY"+jpmMemberConfig.getMoId(), notes,"1");
            //修改配置状态为2（支付成功）
            jpmMemberConfigDao.modifyJpmMemberConfigStatusByConfigNo(configNo, "2",null);
        }catch(AppException appE)
        {
        }
        catch(Exception e)
        {
        }
        // 2.
        JpmMemberConfig config = new JpmMemberConfig();
        config = this.get(Long.parseLong(configNo));
        // 查询订单配置下的所有规格
        List<JpmConfigSpecDetailed> specList =
            specManager.getJpmConfigSpecDetailedListByConfigNo(Long.parseLong(configNo));
        WineInterfaceUtil wineUtil = new WineInterfaceUtil();
        // 配置单推送
        for (JpmConfigSpecDetailed jpmConfigSpecDetailed : specList)
        {
            JpmWineSettingInf jpmWineSettingInf = new JpmWineSettingInf();
            jpmWineSettingInf.setProductId(jpmConfigSpecDetailed.getSpecNo());
            jpmWineSettingInf.setProductName(jpmConfigSpecDetailed.getProductTemplateName());
            jpmWineSettingInf.setProductQty(new Integer(1));
            List<JpmWineSettingListInf> infList = new ArrayList<JpmWineSettingListInf>();
            
            List<JpmConfigDetailed> detailedList =
                detailedManager.getJpmConfigDetailedBySpecNo(String.valueOf(jpmConfigSpecDetailed.getSpecNo()));
            for (JpmConfigDetailed jpmConfigDetailed : detailedList)
            {
                JpmProductWineTemplateSub sub = new JpmProductWineTemplateSub();
                sub = subManager.getJpmProductWineTemplateSubBySubNo(jpmConfigDetailed.getSubNo());
                JpmWineSettingListInf inf = new JpmWineSettingListInf();
                inf.setMaterialNo(sub.getProductNo());
                inf.setQty(jpmConfigDetailed.getSubAmount().intValue());
                inf.setLossRatio(sub.getLossRatio().floatValue());
                inf.setSdate("2013-1-1");
                inf.setEdate("9999-12-31");
                inf.setIsDelegateOut(sub.getIsDelegateOut());
                inf.setIsFeatureItem(sub.getIsFeatureItem());
                inf.setIsMainMaterial(sub.getIsMainMaterial());
                inf.setIsSendMaterial(sub.getIsSendMaterial());
                inf.setMemo(jpmConfigDetailed.getRemark());
                
                if (null != jpmConfigDetailed.getIdf())
                {
                    JpmWineTemplatePicture pic = new JpmWineTemplatePicture();
                    pic = jpmWineTemplatePictureManager.get(jpmConfigDetailed.getIdf());
                    inf.setPicName(pic.getPictureNameSrc());
                }
                infList.add(inf);
                inf.setJpmWineSettingInf(jpmWineSettingInf);
            }
            Set<JpmWineSettingListInf> jpmWineSettingListInfSet =
                new HashSet<JpmWineSettingListInf>();
            jpmWineSettingListInfSet.addAll(infList);
            jpmWineSettingInf.setJpmWineSettingListInfSet(jpmWineSettingListInfSet);
            wineUtil.saveAndSendSettingBill(jpmWineSettingInf, 0);
            
        }
        
        for (JpmConfigSpecDetailed jpmConfigSpecDetailed : specList)
        {
            // 查询发货单
            List<JpmSendConsignment> sendList =
                jpmSendConsignmentManager.getJpmSendConsignmentListBySpecNo(jpmConfigSpecDetailed.getSpecNo());
            Double price = jpmConfigSpecDetailed.getPrice().doubleValue();
            JpmConfigDetailed jpmConfigDetailed =
                detailedManager.getJpmConfigDetailedNumBySpecNo(String.valueOf(jpmConfigSpecDetailed.getSpecNo()));
            if (null != jpmConfigDetailed)
            {
                if (null != jpmConfigDetailed.getSubAmount())
                {
                    price = price / jpmConfigDetailed.getSubAmount();
                }
                else
                {
                    price = 0d;
                }
            }
            
            // 调用发货
            for (JpmSendConsignment jpmSendConsignment : sendList)
            {
                // 发货单推送
                JpmWineOrderInterface jpmWineOrderInterface = new JpmWineOrderInterface();
                jpmWineOrderInterface.setUserCode(jsysUser.getUserCode());
                // 发货单号
                jpmWineOrderInterface.setBillCode(String.valueOf(jpmSendConsignment.getConsignmentNo()));
                jpmWineOrderInterface.setCoperatorid(jsysUser.getUserCode());
                jpmWineOrderInterface.setCuscity(jsysUser.getJmiMember().getCity());
                jpmWineOrderInterface.setCusprovince(jsysUser.getJmiMember().getProvince());
                jpmWineOrderInterface.setAreaclCode(jsysUser.getJmiMember().getDistrict());
                jpmWineOrderInterface.setDbillDate(jpmSendConsignment.getSendDate().toString());
                jpmWineOrderInterface.setMemberaddress(jsysUser.getJmiMember().getAddress());
                jpmWineOrderInterface.setMemberid(null);
                jpmWineOrderInterface.setMembername(jsysUser.getFirstName()
                    + jsysUser.getLastName());
                jpmWineOrderInterface.setMembersex(jsysUser.getJmiMember().getSex());
                jpmWineOrderInterface.setMemberOrderNo(String.valueOf(jpmSendConsignment.getConsignmentNo()));
                jpmWineOrderInterface.setMemberphone(jsysUser.getJmiMember().getPhone());
                jpmWineOrderInterface.setPostalcode(jsysUser.getJmiMember().getPostalcode());
                
                // 根据发货单中的地址id查询发货地址信息
                JmiAddrBook jmiAddrBook = jmiAddrBookManager.get(jpmSendConsignment.getFabId());
                jpmWineOrderInterface.setReceiverAddress(jmiAddrBook.getAddress());
                jpmWineOrderInterface.setReceiverCode(String.valueOf(jpmSendConsignment.getConsignmentNo()));
                jpmWineOrderInterface.setReceiverCusCity(jmiAddrBook.getCity());
                jpmWineOrderInterface.setReceiverCusProvince(jmiAddrBook.getProvince());
                jpmWineOrderInterface.setReceiverAreaclcode(jmiAddrBook.getDistrict());
                jpmWineOrderInterface.setReceiverId(null);
                jpmWineOrderInterface.setReceiverName(jmiAddrBook.getFirstName()
                    + jmiAddrBook.getLastName());
                jpmWineOrderInterface.setReceiverPhone(jmiAddrBook.getPhone());
                jpmWineOrderInterface.setReceiverPostalCode(jmiAddrBook.getPostalcode());
                jpmWineOrderInterface.setReceiverSex(null);
                
                JpmWineOrderListInterface jpmWineOrderListInterface =
                    new JpmWineOrderListInterface();
                
                jpmWineOrderListInterface.setPrice(price);
                jpmWineOrderListInterface.setProductId(String.valueOf(jpmConfigSpecDetailed.getSpecNo()));
                jpmWineOrderListInterface.setDconsigndate(jpmSendConsignment.getSendDate()
                    .toString());
                jpmWineOrderListInterface.setQty(jpmSendConsignment.getConsignmenNum());
                jpmWineOrderListInterface.setVreceiveaddress(jmiAddrBook.getAddress());
                
                jpmWineOrderInterface.getJpmWineOrderListInterfaceSet()
                .add(jpmWineOrderListInterface);
                jpmWineOrderListInterface.setJpmWineOrderInterface(jpmWineOrderInterface);
                
                
                wineUtil.saveAndSendOrder(jpmWineOrderInterface, 0);
            }
        }
        
        // 推送成功后，查询订单下是否全部支付，支付完成则更改订单状态为配置完成
        List<JpmMemberConfig> configList = this.getAllConfigStatusByConfigNo(configNo, "");
        boolean bl = true;
        String moId = null;
        for (JpmMemberConfig jpmConfig : configList)
        {
            moId = String.valueOf(jpmConfig.getMoId());
            String jpmConfigNo = jpmConfig.getConfigNo().toString();
            // 配置完成状态'2'
            if (!"2".equals(jpmConfig.getStatus()))
            {
                if(!configNo.equals(jpmConfigNo) && configNo != jpmConfigNo)
                {
                    bl = false;
                    continue;
                }
            }
        }
        if (true == bl)
        {
            // 0为已配置状态
            Map<String, String> modMap = new HashMap<String, String>();
            modMap.put("status", "0");
            modMap.put("moId", moId);
            jpoMemberOrderManager.modifyOrderStatusByMoId(modMap);
        }
    }
}