package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.Linkman;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("linkmanDao")
public class LinkmanDaoJpa extends GenericDaoHibernate<Linkman, Long> implements LinkmanDao {

    public LinkmanDaoJpa() {
        super(Linkman.class);
    }

    /**
	 * 客户维护－客户信息查询
	 * @author gw 2013-07-16
	 * @param name
	 * @param sex
	 * @param mobilePhone
	 * @param userCode
	 * @param lcId
	 * @param customerFocus
	 * @return
	 */
    public List getLinkmanBybaseInfo(String name, String sex, String mobilePhone,String userCode,String lcId,String customerFocus) {
        String sql = " select * from linkman a where user_code = '"+userCode+"' ";
        if(!StringUtil.isEmpty(name)){
        	sql += " and a.name like '%"+name+"%'";
        }
        //这里的性别主要传过来的是　男＼女，还是相关的ｃｏｄｅ　　如１和２，这个地方要主要区别　
        if(!StringUtil.isEmpty(sex)){
        	sql += " and a.sex = '"+sex+"'";
        }
    	if(!StringUtil.isEmpty(mobilePhone)){
    		sql += " and a.mobilephone like '%"+mobilePhone+"%' ";
    	}
    	if(!StringUtil.isEmpty(lcId)){
    		sql +=" and a.lc_id = '"+lcId+"'";
    	}
    	if(!StringUtil.isEmpty(customerFocus)){
    		sql +=" and a.customer_Focus = '"+customerFocus+"'";
    	}
    	
    	//返回时调用分页的查询的方法
    	return  this.jdbcTemplate.queryForList(sql);
    }
    
    /**
	 * 客户维护－客户信息查询
	 * Add By WuCF 2013-12-02
	 * 分页
	 * @param name
	 * @param sex
	 * @param mobilePhone
	 * @param lcId
	 * @param customerFocus
	 * @return
	 */
    public List getLinkmanBybaseInfoPage(GroupPage page,String name, String sex, String mobilePhone,String userCode,String lcId,String customerFocus) {
        String sql = " select * from linkman a where user_code = '"+userCode+"' ";
        if(!StringUtil.isEmpty(name)){
        	sql += " and a.name like '%"+name+"%'";
        }
        //这里的性别主要传过来的是　男＼女，还是相关的ｃｏｄｅ　　如１和２，这个地方要主要区别　
        if(!StringUtil.isEmpty(sex)){
        	sql += " and a.sex = '"+sex+"'";
        }
    	if(!StringUtil.isEmpty(mobilePhone)){
    		sql += " and a.mobilephone like '%"+mobilePhone+"%' ";
    	}
    	if(!StringUtil.isEmpty(lcId)){
    		sql +=" and a.lc_id = '"+lcId+"'";
    	}
    	if(!StringUtil.isEmpty(customerFocus)){
    		sql +=" and a.customer_Focus = '"+customerFocus+"'";
    	}
    	
    	//返回时调用分页的查询的方法
//    	return  this.jdbcTemplate.queryForList(sql);
    	return this.findObjectsBySQL(sql, page);
    }

    /**
	 * 客户维护－客户信息详细查询(客户详细信息修改的初始化方法)
	 * @author gw 2013-07-16
	 * @param id
	 * @return　linkman
	 */
	public Linkman getLinkmanDetail(String id) {
		if(!StringUtil.isEmpty(id)){
		    String hql = " from Linkman where id = '"+id+"'";
		    return (Linkman) this.getObjectByHqlQuery(hql);
		}else{
			return null;
		}
		
	}

	/**
	 * 客户维护－客户详细信息修改（或新增的方法）
	 * @author gw 2013-07-16
	 * @param linkman
	 * @return 
	 */
	public void updateOrAddLinkmanDetail(Linkman linkman) {
		this.save(linkman);
	}
	
	/**
	 * 客户维护－客户详细信息修改之前的校验方法
	 * @author gw 2013-07-17
	 * @param linkman
	 * @param errors
	 * @return boolean
	 */
	public boolean getlinkmanManagerMark(Linkman linkman,BindingResult errors){
		//定义一个校验的标志
		boolean isVerificationMark = false;
		//姓名不为空的校验
		if(StringUtil.isEmpty(linkman.getName())){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "isNotNull", "name", "linkman.name");
			isVerificationMark = true;
		}
		//性别不为空的校验
		if(StringUtil.isEmpty(linkman.getSex())){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "isNotNull", "sex", "linkman.sex");
			isVerificationMark = true;
		}
		//手机号码不为空的校验
		if(StringUtil.isEmpty(linkman.getMobilephone())){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "isNotNull", "mobilephone", "linkman.mobilephone");
			isVerificationMark = true;
		}
		//手机号码格式的校验－这里仅仅只做数字的校验
		if((!StringUtil.isEmpty(linkman.getMobilephone()))&&(this.getPattern("^[0-9]*", linkman.getMobilephone()))){
			StringUtil.getErrorsFormat(errors, "error.mobilephone", "mobilephone", "linkman.mobilephone");
			isVerificationMark = true;
		}
		//家庭电话格式的校验－这里仅仅只做数字的校验
		if((!StringUtil.isEmpty(linkman.getFamilyPhone()))&&(this.getPattern("^[0-9]*", linkman.getFamilyPhone()))){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "error.familyPhone", "familyPhone", "linkman.familyPhone");
			isVerificationMark = true;
		}
		//公司电话格式的校验－这里仅仅只做数字的校验
		if((!StringUtil.isEmpty(linkman.getCompanyPhone()))&&(this.getPattern("^[0-9]*", linkman.getCompanyPhone()))){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "error.companyPhone", "companyPhone", "linkman.companyPhone");
			isVerificationMark = true;
		}
		//邮箱格式的校验
		if((!StringUtil.isEmpty(linkman.getEmail()))&&(this.getPattern("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", linkman.getEmail()))){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "errors.email", "email", "subStore.email");
			isVerificationMark = true;
		}
		/*//ＱＱ纯数字的校验
		boolean qqIsNumber = (null==linkman.getQq())||"".equals(linkman.getQq());
		if(!qqIsNumber){
			if(this.getPattern("^[0-9]*", linkman.getQq().toString())){
				//此处的提示语里面的参数　要在封装的类中加上去
				StringUtil.getErrorsFormat(errors, "error.qq", "qq", "linkman.qq");
				isVerificationMark = true;
			}
		}
		//邮编纯数字的校验
		boolean postIsNumber = (null==linkman.getPost())||"".equals(linkman.getPost());
		if(!postIsNumber){
			if(this.getPattern("^[0-9]*", linkman.getPost().toString())){
				//此处的提示语里面的参数　要在封装的类中加上去
				StringUtil.getErrorsFormat(errors, "error.post", "post", "linkman.post");
				isVerificationMark = true;
			}
		}*/
		
		/*//对微信号的校验
		if(!StringUtil.isEmpty(linkman.getMicroMessage())){
			 //微信号,纯数字或者字母的校验
			
			
			
		}*/
		return isVerificationMark;
	}
	
	/**
	 * 格式校验的方法－看相关字符串是不是纯数字，用了校验手机号码之类的数
	 * @author gw 2013-07-17
	 * @param expressions
	 * @param str
	 * @return
	 */
	private boolean getPattern(String expressions,String str){
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}

	/**
	 * 客户维护（添加）－查询客户分类
	 * @author gw 2013-07-26
	 * @param userCode
	 * @return　list
	 */
	public List getLinkmanCategoryList(String userCode) {
		return this.getSession().createQuery(" select a from LinkmanCategory a where userCode = '"+userCode+"' ").list();
	}

	/**
	 * 客户分类中的类别删除过后,对应的客户的分类的类别就置为"未分组"
	 * @author gw 2013-09-24
	 * @param userCode
	 * @param id
	 */
	public void getLinkmanCategoryList(String userCode, String lcId) {
		//获取客户分类中已经删除的类别的客户姓名的集合
		String hql = " from Linkman where userCode = '"+userCode+"' and lcId='"+lcId+"'";
		List<Linkman> linkmanList = this.getSession().createQuery(hql).list();
		//将删除的客户的类别的对应客户的分类置为"未分组"
		for(Linkman linkman:linkmanList){
			linkman.setLcId("");
			this.save(linkman);
		}
	}

	/**
	 * 获取客户基本信息表的实体对象
	 * @author gw 2013-09-25
	 * @param linkmanId
	 * @return
	 */
	public Linkman getLinkmanById(String id) {
		if(!StringUtil.isEmpty(id)){
		    String hql = " from Linkman where id = '"+id+"'";
		    return (Linkman) this.getObjectByHqlQuery(hql);
		}else{
			return null;
		}
	}

	 /**
		 * 客户跟踪list查询
		 * Add By WuCF 2013-12-02
		 * 分页
		 * @param name
		 * @param sex
		 * @param mobilePhone
		 * @param lcId
		 * @param customerFocus
		 * @return
		 */
	    public List getLinkmanListBybaseInfoPage(GroupPage page,String name, String sex, String mobilePhone,String userCode,String lcId,String customerFocus) {
	    	
	        String sql = "select a.id,a.name,a.linkman_Status,a.mobilephone,a.CUSTOMER_FOCUS,a.family_Phone,a.company_Phone,a.CUSTOMER_SOURCE,b.name as q_name from linkman a left join LINKMAN_CATEGORY b on b.id=a.lc_id where a.user_code = '"+userCode+"' ";
	        	        
	        if(!StringUtil.isEmpty(name)){
	        	sql += " and a.name like '%"+name+"%'";
	        }
	        //这里的性别主要传过来的是　男＼女，还是相关的ｃｏｄｅ　　如１和２，这个地方要主要区别　
	        if(!StringUtil.isEmpty(sex)){
	        	sql += " and a.sex = '"+sex+"'";
	        }
	    	if(!StringUtil.isEmpty(mobilePhone)){
	    		sql += " and a.mobilephone like '%"+mobilePhone+"%' ";
	    	}
	    	if(!StringUtil.isEmpty(lcId)){
	    		sql +=" and a.lc_id = '"+lcId+"'";
	    	}
	    	if(!StringUtil.isEmpty(customerFocus)){
	    		sql +=" and a.customer_Focus = '"+customerFocus+"'";
	    	}
	    	
	    	return this.findObjectsBySQL(sql, page);
	    }
}
