package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalCompanyDao;
import com.joymain.ng.model.JalCompany;
import com.joymain.ng.service.JalCompanyManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jalCompanyManager")
@WebService(serviceName = "JalCompanyService", endpointInterface = "com.joymain.ng.service.JalCompanyManager")
public class JalCompanyManagerImpl extends GenericManagerImpl<JalCompany, Long> implements JalCompanyManager {
    JalCompanyDao jalCompanyDao;

    @Autowired
    public JalCompanyManagerImpl(JalCompanyDao jalCompanyDao) {
        super(jalCompanyDao);
        this.jalCompanyDao = jalCompanyDao;
    }

	@Override
	public JalCompany getAlCompanyByCode(String companyCode) {
		// TODO Auto-generated method stub
		return jalCompanyDao.getAlCompanyByCode(companyCode);
	}
}