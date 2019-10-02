package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmSendConsignmentDao;
import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JpmSendConsignment;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JpmSendConsignmentManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jpmSendConsignmentManager")
@WebService(serviceName = "JpmSendConsignmentService", endpointInterface = "com.joymain.ng.service.JpmSendConsignmentManager")
public class JpmSendConsignmentManagerImpl extends GenericManagerImpl<JpmSendConsignment, Long>
    implements JpmSendConsignmentManager
{
    JpmSendConsignmentDao jpmSendConsignmentDao;
    
    @Autowired
    JalCityManager jalCityManager;
    
    @Autowired
    JalStateProvinceManager jalStateProvinceManager;
    
    @Autowired
    JalDistrictManager jalDistrictManager;
    
    @Autowired
    public JpmSendConsignmentManagerImpl(JpmSendConsignmentDao jpmSendConsignmentDao)
    {
        super(jpmSendConsignmentDao);
        this.jpmSendConsignmentDao = jpmSendConsignmentDao;
    }
    
    public Pager<JpmSendConsignment> getPager(Collection<SearchBean> searchBeans,
        Collection<OrderBy> OrderBys, int currentPage, int pageSize)
    {
        // TODO Auto-generated method stub
        return jpmSendConsignmentDao.getPager(JpmSendConsignment.class,
            searchBeans,
            OrderBys,
            currentPage,
            pageSize);
    }
    
    @Override
    public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo)
    {
        return jpmSendConsignmentDao.getJpmSendConsignmentListBySpecNo(specNo);
    }
    
    @Override
    public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        jpmSendConsignmentDao.delJpmSendConsignmentByConsignmentNo(consignmentNo);
    }
    
    @Override
    public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo)
    {
        return jpmSendConsignmentDao.getJpmSendConsignmentByConsignmentNo(consignmentNo);
    }
    
    @Override
    public String getAddresByFabId(JmiAddrBook jmiAddrBook)
    {
        // 省
        JalStateProvince province = jalStateProvinceManager.get(jmiAddrBook.getProvince());
        // 市
        JalCity city = jalCityManager.get(jmiAddrBook.getCity());
        // 区
        JalDistrict district = new JalDistrict();
        if(StringUtils.isNotEmpty(jmiAddrBook.getDistrict()))
        {
            district = jalDistrictManager.get(jmiAddrBook.getDistrict());
        }
        
        StringBuffer address = new StringBuffer();
        if(StringUtils.isNotEmpty(province.getStateProvinceName()))
        {
            address.append(province.getStateProvinceName());
        }
        if (StringUtils.isNotEmpty(city.getCityName()))
        {
            address.append(city.getCityName());
        }
        if (StringUtils.isNotEmpty(district.getDistrictName()))
        {
            address.append(district.getDistrictName());
        }
        if (StringUtils.isNotEmpty(jmiAddrBook.getAddress()))
        {
            address.append(jmiAddrBook.getAddress());
        }
        if (StringUtils.isNotEmpty(jmiAddrBook.getFirstName()))
        {
            address.append("( ");
            address.append(jmiAddrBook.getFirstName());
            address.append(jmiAddrBook.getLastName());
            address.append(" )");
        }
        if (StringUtils.isNotEmpty(jmiAddrBook.getPhone()))
        {
            address.append(jmiAddrBook.getPhone());
        }
        
        return address.toString();
    }
    
}