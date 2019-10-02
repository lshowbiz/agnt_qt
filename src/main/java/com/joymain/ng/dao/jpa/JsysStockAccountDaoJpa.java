package com.joymain.ng.dao.jpa;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JsysStockAccountDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JsysStockAccount;

@Repository("jsysStockAccountDao")
public class JsysStockAccountDaoJpa extends GenericDaoHibernate<JsysStockAccount, Long> implements JsysStockAccountDao {

    public JsysStockAccountDaoJpa() {
        super(JsysStockAccount.class);
    }

    /**
	 * 根据id获取港股账号信息
	 * @param id
	 * @return
	 */
	public JsysStockAccount getJsysStockAccountById(Long id){
		JsysStockAccount jsysStockAccount = this.get(id);
		return jsysStockAccount;
	}

    /**
     * 根据会员编号获取港股账号信息
     * @param userCode
     * @return
     */
    public JsysStockAccount getJsysStockAccountByUserCode(String userCode){
    	String hql = "from JsysStockAccount where userCode='"+userCode+"'";
    	JsysStockAccount jsysStockAccount = (JsysStockAccount)this.getObjectByHqlQuery(hql);
    	if(jsysStockAccount==null||jsysStockAccount.getUserCode()==null||"".equals(jsysStockAccount.getUserCode())){
    		return null;
    	}
    	return jsysStockAccount;
    }
    
    /**
     * 保存或修改港股账号信息
     * @param jsysStockAccount
     */
    public void saveOrUpdate(JsysStockAccount jsysStockAccount){
    	Session sess = this.getSession();
    	sess.update(jsysStockAccount);
    }
}
