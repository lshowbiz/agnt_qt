package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiAddrBookDao;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.model.FiCommonAddr;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiCommonAddrManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;

@Service("jmiAddrBookManager")
@WebService(serviceName = "JmiAddrBookService", endpointInterface = "com.joymain.ng.service.JmiAddrBookManager")
public class JmiAddrBookManagerImpl extends GenericManagerImpl<JmiAddrBook, Long> implements JmiAddrBookManager {
    JmiAddrBookDao jmiAddrBookDao;

    @Autowired
    public JmiAddrBookManagerImpl(JmiAddrBookDao jmiAddrBookDao) {
        super(jmiAddrBookDao);
        this.jmiAddrBookDao = jmiAddrBookDao;
    }
    @Autowired
    public JmiMemberDao jmiMemberDao;
    @Autowired
	private FiCommonAddrManager fiCommonAddrManager;
	public Pager<JmiAddrBook> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiAddrBookDao.getPager(JmiAddrBook.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public boolean getCheckJmiAddrBook(BindingResult errors,JmiAddrBook jmiAddrBook){
		boolean isNotPass = false;
		if (StringUtil.isEmpty(jmiAddrBook.getFirstName())) {
			getErrors(errors, "shippingFirstName.isNotNull", "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiAddrBook.getLastName())) {
			getErrors(errors, "shippingLastName.isNotNull", "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiAddrBook.getProvince())) {
			getErrors(errors, "shippingProvince.isNotNull", "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiAddrBook.getCity()) ) {
			getErrors(errors, "shippingCity.isNotNull", "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiAddrBook.getDistrict()) ) {
			getErrors(errors, "shippingDistrict.isNotNull", "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiAddrBook.getAddress())) {
			getErrors(errors, "shippingAddress.isNotNull", "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiAddrBook.getPostalcode())) {
			getErrors(errors, "shippingPostalcode.isNotNull", "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiAddrBook.getMobiletele())) {
			errors.reject("shippingMobiletele.isNotNull");
			isNotPass = true;
    	}else if(this.getPattern("^[0-9]*", jmiAddrBook.getMobiletele())){
			this.getErrorsFormat(errors, "errors.phone", "mobiletele", "miMember.mobiletele");
			isNotPass = true;
    	}
		return isNotPass;
	}

	private boolean getPattern(String expressions,String str){
		Pattern pattern = Pattern.compile(expressions);
		Matcher matcher = pattern.matcher(str);
		if(!matcher.matches()){
			return true;
		}
		return false;
	}
	private String getErrorsFormat(BindingResult errors,String msg,String field,String fieldMsg){
		String errorStr=MessageFormat.format(LocaleUtil.getLocalText(msg), new String[]{LocaleUtil.getLocalText(fieldMsg)});
		if(errors!=null){
			errors.rejectValue(field, msg,new Object[] { LocaleUtil.getLocalText(fieldMsg)}, "");
		}
		return errorStr;
	}
	private String getErrors(BindingResult errors,String msg,String field){
		String errorStr="";
		errorStr=LocaleUtil.getLocalText(msg);
		if(errors!=null){
			if(StringUtil.isEmpty(field)){
				errors.reject(errorStr);
			}else{
				errors.rejectValue(field,errorStr,errorStr);
			}
				
		}
		return errorStr;
	}

	public List getJmiAddrBookByUserCode(String userCode) {
		return jmiAddrBookDao.getJmiAddrBookByUserCode(userCode);
	}
	
	
	public JmiAddrBook getDefaultJmiAddrBookByUserCode(String userCode) {
		return jmiAddrBookDao.getDefaultJmiAddrBookByUserCode(userCode);
	}

	public void saveJmiAddrBookPc(JmiAddrBook jmiAddrBook) {
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(this.getCheckJmiAddrBook(null, jmiAddrBook)){
    		throw new RuntimeException("保存出错");
    	}
    	
    	JmiMember jmiMember=jmiMemberDao.get(defSysUser.getUserCode());
    	jmiAddrBook.setJmiMember(jmiMember);
    	JmiAddrBook resJmiAddrBook=jmiAddrBookDao.save(jmiAddrBook);
    	resetDefaultAddr(defSysUser, resJmiAddrBook,"");
    	
	}
	
	//add by lihao,回传保存地址的id给DRW调用
	@Override
	public Long saveJmiAddrBookPcByDwr(JmiAddrBook jmiAddrBook) {
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(this.getCheckJmiAddrBook(null, jmiAddrBook)){
    		throw new RuntimeException("保存出错");
    	}
    	
    	JmiMember jmiMember=jmiMemberDao.get(defSysUser.getUserCode());
    	jmiAddrBook.setJmiMember(jmiMember);
    	JmiAddrBook resJmiAddrBook=jmiAddrBookDao.save(jmiAddrBook);
    	resetDefaultAddr(defSysUser, resJmiAddrBook,"");
    	return resJmiAddrBook.getFabId();
    	
	}
	
	
	/**
	 * 手机端保存 
	 * @param jmiAddrBook
	 * @param user
	 */
	public void saveJmiAddrBook(JmiAddrBook jmiAddrBook,JsysUser user) {
		if(this.getCheckJmiAddrBook(null, jmiAddrBook)){
			throw new RuntimeException("保存出错");
		}
		
		JmiMember jmiMember=jmiMemberDao.get(user.getUserCode());
		jmiAddrBook.setJmiMember(jmiMember);
		JmiAddrBook resJmiAddrBook=jmiAddrBookDao.save(jmiAddrBook);
		resetDefaultAddr(user, resJmiAddrBook,"");
		
	}

	public List getJmiAddrBookByUserCodeBySql(String userCode) {
		return jmiAddrBookDao.getJmiAddrBookByUserCodeBySql(userCode);
	}
	
	public void saveDefaultJmiAddrBookPc(String fabId){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	JmiAddrBook jmiAddrBook=this.get(new Long(fabId));
    	jmiAddrBook.setIsDefault("1");
    	this.save(jmiAddrBook);
    	resetDefaultAddr(defSysUser, jmiAddrBook,"add");
		
	}
	/**
	 * 手机端设置 默认地址
	 * @param fabId
	 * @param user
	 */
	public void saveDefaultJmiAddrBook(String fabId,JsysUser user){
		JmiAddrBook jmiAddrBook=this.get(new Long(fabId));
		jmiAddrBook.setIsDefault("1");
		this.save(jmiAddrBook);
		resetDefaultAddr(user, jmiAddrBook,"add");
		
	}

	public void removeJmiAddrBookPc(String fabId) {
		JmiAddrBook jmiAddrBook=this.get(new Long(fabId));
		this.remove(new Long(fabId));
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		resetDefaultAddr(defSysUser, jmiAddrBook,"delete");
	}
	/**
	 * 手机端 删除一条地址
	 * @param fabId
	 * @param user
	 */
	public void removeJmiAddrBook(String fabId,JsysUser user) {
		JmiAddrBook jmiAddrBook=this.get(new Long(fabId));
		this.remove(new Long(fabId));
		resetDefaultAddr(user, jmiAddrBook,"delete");
	}
	
	private void resetDefaultAddr(JsysUser defSysUser,JmiAddrBook jmiAddrBook,String type){
		List<JmiAddrBook> list=jmiAddrBookDao.getJmiAddrBookByUserCode(defSysUser.getUserCode());
		if("1".equals(jmiAddrBook.getIsDefault())){
			for (int i = 0; i < list.size(); i++) {
				JmiAddrBook book=list.get(i);
				if(jmiAddrBook.getFabId()!=book.getFabId()){
					book.setIsDefault("");
					this.save(book);
				}
			}
			if("delete".equals(type)&&!list.isEmpty()){
				JmiAddrBook book=list.get(0);
				book.setIsDefault("1");
				this.save(book);
			}
		}
		List<JmiAddrBook> list1=jmiAddrBookDao.getJmiAddrBookByUserCode(defSysUser.getUserCode());
		if(!list1.isEmpty()){
			boolean flag=false;
			for (int i = 0; i < list1.size(); i++) {
				JmiAddrBook book=list1.get(i);
				if("1".equals(book.getIsDefault())){
					flag=true;
				}
			}
			if(!flag){
				JmiAddrBook book=list1.get(0);
				book.setIsDefault("1");
				this.save(book);
			}
		}
		
	}

	@Override
	public JmiAddrBook getDefaultJmiAddrBookByUserCodeHome(String userCode) {
		JmiAddrBook jmiAddrBook=jmiAddrBookDao.getDefaultJmiAddrBookByUserCode(userCode);
		if(jmiAddrBook!=null){
			String userCode1=jmiAddrBook.getJmiMember().getUserCode();
			JmiMember jmiMember=new JmiMember();
			jmiMember.setUserCode(userCode1);
			jmiAddrBook.setJmiMember(jmiMember);
		}
		
		return jmiAddrBook;
	}
	
	
	public void saveJmiAddrBoookAndFiAddr(JmiAddrBook jmiAddrBook){

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(fiCommonAddrManager.get(defSysUser.getUserCode())==null){
			if(jmiAddrBookDao.getDefaultJmiAddrBookByUserCode(defSysUser.getUserCode())==null){
				jmiAddrBook.setIsDefault("1");
				this.saveJmiAddrBookPc(jmiAddrBook);
			}
			FiCommonAddr fiCommonAddr=new FiCommonAddr();
			fiCommonAddr.setAddress(jmiAddrBook.getAddress());
			fiCommonAddr.setProvince(jmiAddrBook.getProvince());
			fiCommonAddr.setCity(jmiAddrBook.getCity());
			fiCommonAddr.setUserCode(defSysUser.getUserCode());
			fiCommonAddr.setDistrict(jmiAddrBook.getDistrict());
			fiCommonAddrManager.save(fiCommonAddr);
		}
		
	}
}