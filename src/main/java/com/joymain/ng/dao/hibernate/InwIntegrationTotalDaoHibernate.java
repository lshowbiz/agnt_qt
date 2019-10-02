package com.joymain.ng.dao.hibernate;

import java.util.Date;

import com.joymain.ng.model.InwIntegration;
import com.joymain.ng.model.InwIntegrationTotal;
import com.joymain.ng.dao.InwIntegrationDao;
import com.joymain.ng.dao.InwIntegrationTotalDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.joymain.ng.util.StringUtil;


@Repository("inwIntegrationTotalDao")
public class InwIntegrationTotalDaoHibernate extends GenericDaoHibernate<InwIntegrationTotal, Long> implements InwIntegrationTotalDao {

    public InwIntegrationTotalDaoHibernate() {
        super(InwIntegrationTotal.class);
    }

    @Autowired
    private InwIntegrationDao inwIntegrationDao;
    
    
    /**
     * 用户减掉积分的操作
     * @author yxzz  2014-06-09
     * @param userCode 会员编号
     * @param integratotal 积分
     * @param uniqueCode 唯一码
     * @return boolean
     */
	public boolean minusIntegrationTotal(String userCode, String integratotal,String uniqueCode) {
		//首先通过唯一标识,查看同一条请求减积分有没有在数据库中录入了
		boolean checkExist = inwIntegrationDao.getCheckExist(uniqueCode);
		if(checkExist){
				//首先查出用户所拥有的积分总数
				String hql = " from InwIntegrationTotal a where a.userCode='"+userCode+"' ";
				InwIntegrationTotal inwIntegrationTotal =  (InwIntegrationTotal) this.getObjectByHqlQuery(hql);
				if(null!=inwIntegrationTotal){
						if(!StringUtil.isEmpty(integratotal)){
							int total = Integer.parseInt(integratotal);
							String totalOriginal = inwIntegrationTotal.getTotalPoints();
							if(!StringUtil.isEmpty(totalOriginal)){
								int totalO = Integer.parseInt(totalOriginal);
								//总积分total0大于减掉的积分total
								if(totalO>=total){
									int a = totalO - total;
									String b = String.valueOf(a);
									inwIntegrationTotal.setTotalPoints(b);
									//更改创新积分的总积分
									this.save(inwIntegrationTotal);
									//同时,向创新积分的明细里面插入一条数据
									InwIntegration inwIntegration = new InwIntegration();
									inwIntegration.setIntegrationMinus(integratotal);
									inwIntegration.setIntegrationMinusTime(new Date());
									inwIntegration.setOther(uniqueCode);
									inwIntegration.setOperatorUserCode(userCode);
									inwIntegrationDao.save(inwIntegration);
									//减掉积分成功
									return true;
								}
							}
						}
				}
				//减掉积分失败
				return false;
		}else{
			//减掉积分失败
			return false;
		}
	}
	
	public InwIntegrationTotal getInwIntegrationTotal(String userCode){
		String hql = " from InwIntegrationTotal a where a.userCode='"+userCode+"' ";
		return (InwIntegrationTotal) this.getObjectByHqlQuery(hql);
	}
}
