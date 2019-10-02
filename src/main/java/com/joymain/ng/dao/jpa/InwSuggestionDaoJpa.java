package com.joymain.ng.dao.jpa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.InwSuggestionDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.InwSuggestion;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Repository("inwSuggestionDao")
public class InwSuggestionDaoJpa extends GenericDaoHibernate<InwSuggestion, Long> implements InwSuggestionDao {

    public InwSuggestionDaoJpa() {
        super(InwSuggestion.class);
    }
    /**
	 * 手机建议查看
	 * @param param ： 查询条件
	 * @return ： 手机建议结果集
	 */
	@SuppressWarnings("rawtypes")
	public List list( Map param,GroupPage page ){
		List<Map<String,String>> result = new LinkedList<Map<String,String>>();
		String lookStatus = (String)param.get("lookStatus");
		String userCode = (null != param.get("userCode") ? (String)param.get("userCode") : "");
		
		 String sqlQuery = "";
		//0代表未阅，1代表已阅，2全部（已阅＋未阅）
    	//全部-----需求名称干掉
    	if("2".equals(lookStatus)){
    		sqlQuery = " select subject,to_char(create_time,'yyyy-MM-dd HH24:MI:SS') create_time,user_code,id,demand_id  from inw_suggestion b  where 1=1 ";
    		
    	}
    	//1代表已阅
    	else if("1".equals(lookStatus)){
    		sqlQuery = " select b.subject,to_char(create_time,'yyyy-MM-dd HH24:MI:SS') create_time,b.user_code,b.id,b.demand_id  from inw_suggestion b,inw_viewPeople c " +
    				" where b.id = c.suggestionid and c.user_code = '"+userCode+"'";
    	}
    	//0代表未阅
    	else{
    		sqlQuery = " select subject,to_char(create_time,'yyyy-MM-dd HH24:MI:SS') create_time,user_code,id,demand_id  from (" +
    				" ( select subject, create_time,user_code,id,demand_id  from inw_suggestion b )" +
    				" minus ( select b.subject, create_time,b.user_code,b.id,b.demand_id  from inw_suggestion b,inw_viewPeople c " +
    				" where  b.id = c.suggestionid and c.user_code = '"+userCode+"' ) ) where 1=1 ";
    	}
	        	//建议的建议人
	 	        String suggestionUserCode = (String)param.get("suggestionUserCode");
	 	        if(!StringUtil.isEmpty(suggestionUserCode)){
	 	        	if("1".equals(lookStatus)){
	 	        		sqlQuery += " and b.user_code like '%"+suggestionUserCode+"%' ";
	 	        	}else{
	 	        		sqlQuery += " and user_code like '%"+suggestionUserCode+"%' ";
	 	        	}
	 	        	 
	 	        }
	 	        //建议主题
	 	        String subject = (String)param.get("subject");
	             if(!StringUtil.isEmpty(subject)){
	             	sqlQuery += " and subject like '%"+subject+"%' ";
	 	        }
	             //建议时间-开始
	 	        String createTimeBegin = (String)param.get("createTimeBegin");
	             if(!StringUtil.isEmpty(createTimeBegin)){
	             	sqlQuery += " and create_time >= to_date('"+createTimeBegin+" 00:00:00 ','yyyy-MM-dd hh24:mi:ss ') ";
	 	        }
	 	        //建议时间-结束
	 	        String createTimeEnd = (String)param.get("createTimeEnd");
	             if(!StringUtil.isEmpty(createTimeEnd)){
	             	sqlQuery += " and create_time <= to_date('"+createTimeEnd+" 23:59:59 ','yyyy-MM-dd hh24:mi:ss ') ";
	 	        }
	             sqlQuery += " and demand_id is null order by create_time desc";
	             
	           //  log.info("查看手机建议sql:" + sqlQuery);
	            List list =  this.findObjectsBySQL(sqlQuery, page);
	            if(null != list && list.size() > 0){
	            	Map<String, String> o = null;
	            	for(int i = 0; i < list.size(); i++){
	            		o =  new LinkedHashMap();
	            		Map<String,String> map = (Map<String,String>)list.get(i);
	            		
	            		o.put("subject", StringUtil.dealStr(map.get("SUBJECT")));
	            		o.put("createTime", StringUtil.dealStr(map.get("CREATE_TIME")));
	            		o.put("userCode", StringUtil.dealStr(map.get("USER_CODE")));
	            		o.put("id", map.get("ID"));
	            		
	            		result.add(o);
	            	}
	            }
	             
	           return  result;
	             
	}
    
	/**
	 * 去数据库表inw_viewPeople中查,看当前登录用户对该条建议是否已阅
	 * @author gw 2013-08-23
	 * @param id
	 * @param userCode
	 * @return List
	 */
	public List getInwViewpeopleIsExist(String id, String userCode) {
		String sql = " select * from inw_viewpeople where suggestionid = '"+id+"' and user_code = '"+userCode+"'";
		return this.findObjectsBySQL(sql,new GroupPage());
	}
    
    /**
	 * 创新共赢的录入意见之前的校验
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 * @param errors
	 * @return boolean
	 */
	public boolean getCheckPassing(InwSuggestion inwSuggestion,BindingResult errors) {
		String subject = inwSuggestion.getSubject();
		if(StringUtil.isEmpty(subject)){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "isNotNull", "subject", "inwSuggestion.suggestedTopics");
			return true;
		}
		String content = inwSuggestion.getContent();
		if(StringUtil.isEmpty(content)){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "isNotNull", "content", "inwSuggestion.proposalContent");
			return true;
		}
		
		String phone = inwSuggestion.getPhone();
		if((!StringUtil.isEmpty(phone))&&(this.getPattern("^[0-9]*",phone))){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "error.phone", "phone", "inwSuggestion.phone");
			return true;
		}
		
		String qq = inwSuggestion.getQq();
		if(!StringUtil.isEmpty(qq)&&(this.getPattern("^[0-9]*",qq))){
			//此处的提示语里面的参数　要在封装的类中加上去
			StringUtil.getErrorsFormat(errors, "error.qq", "qq", "inwSuggestion.qq");
			return true;
		}
		
		return false;
	}

	/**
	 * 创新共赢的保存操作
	 * @author gw 2013-08-14
	 * @param inwSuggestion
	 */
	public void saveInwSuggestion(InwSuggestion inwSuggestion) {
		this.save(inwSuggestion);
	}

	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * @author gw 2013-09-05  update   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	public List getInwSuggestionReply(String id, String userCode) {
		String hql = "from InwSuggestion where  userCode = '"+userCode+"'";
		if(!StringUtil.isEmpty(id)){
			hql += " and demandId = '"+id+"'";
		}
		
		return this.getSession().createQuery(hql).list();

	}
	
	/**
	 * 创新共赢的合作共赢的查看会员提的建议(包括公司对该建议的回复)(新需求)
	 * 分页
	 * @author WuCF 2013-11-29  add   2013-11-11 
	 * @param id
	 * @param userCode
	 * @return list
	 */
	public List getInwSuggestionReplyPage(GroupPage page,String id, String userCode) {
		String totalHql = "select count(*) from InwSuggestion where demandId is not null and userCode = '"+userCode+"'";
		if(!StringUtil.isEmpty(id)){
			totalHql += " and demandId = '"+id+"'";
		}
		
		String hql = "from InwSuggestion where demandId is not null and userCode = '"+userCode+"'";
		if(!StringUtil.isEmpty(id)){
			hql += " and demandId = '"+id+"'";
		}
		    hql += " order by createTime desc ";
		return findObjectsByHQL(totalHql, hql, page);
//		return this.getSession().createQuery(hql).list();

	}

	/**
     * 创新共赢的会员查看自己所提建议的详细(包括公司对该建议的回复)(新需求)
     * @author gw 2013-09-05  update 2013-11-11
     * @param id
     * @return string
     */
	public InwSuggestion getInwSuggestionById(String id) {
		String hql = " from InwSuggestion where id = '"+id+"'";
		return (InwSuggestion) this.getObjectByHqlQuery(hql);
	}
	
	/**
	 * 格式校验的方法－看相关字符串是不是纯数字，用了校验手机号码之类的数
	 * @author gw 2013-11-11
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

}
