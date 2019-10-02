package com.joymain.ng.dao.hibernate;


import com.joymain.ng.model.PdShUrl;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.PdShUrlDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("pdShUrlDao")
public class PdShUrlDaoHibernate extends GenericDaoHibernate<PdShUrl, Long> implements PdShUrlDao {

    public PdShUrlDaoHibernate() {
        super(PdShUrl.class);
    }

    /**
	 * 根据物流公司编码获取物流公司链接地址
	 * @author gw 2014-07-11
	 * @param shNo 物流公司编码
	 * @return string 
	 */
	public String getShUrl(String shNo) {
		if(!StringUtil.isEmpty(shNo)){
			String hql = " from PdShUrl pdShUrl where pdShUrl.valueCode= '"+shNo+"'";
			Object object =  this.getObjectByHqlQuery(hql);
		    if((null!=object) && (!("".equals(object)))){
		    	PdShUrl pdShUrl = (PdShUrl) object;
		    	return pdShUrl.getShUrl();
		    }
		}
		return null;
	}
}
