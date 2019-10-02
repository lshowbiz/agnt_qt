package com.joymain.ng.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JmiLinkRefDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JmiLinkRef;
import com.joymain.ng.util.StringUtil;

@Repository("jmiLinkRefDao")
public class JmiLinkRefDaoJpa extends GenericDaoHibernate<JmiLinkRef, String> implements JmiLinkRefDao {

    public JmiLinkRefDaoJpa() {
        super(JmiLinkRef.class);
    }

    public List getLinkRefbyLinkNo(String linkNo,String orderByName,String sort){
    	return this.getSession().createQuery("select a from JmiLinkRef a where linkJmiMember.userCode = '"+linkNo+"' order by "+orderByName+" "+sort).list();

    }
    
    public List getJmiLinkRefByNo(String userCode){
    	return this.getSession().createQuery("select a from JmiLinkRef a where linkJmiMember.userCode ='" + userCode +"'").list();
    }
	/**
	 * 根据外部参数获取下一个接点对象
	 * 
	 * @param miLinkRef
	 * @return
	 */
	public JmiLinkRef getNewMiLinkRefManagersByLinkNo(JmiLinkRef miLinkRef, int maxLink) {
		if (miLinkRef == null) {
			return null;
		}
		JmiLinkRef miNewLinkRef = new JmiLinkRef();
		List miLinkRefs=this.getLinkRefbyLinkNo(miLinkRef.getUserCode(), "layerId,userCode", "");

		if (miLinkRefs == null) {
			miNewLinkRef.setTreeIndex(miLinkRef.getTreeIndex() + "00");
		} else if (miLinkRefs.size() >= maxLink) {
			return null;
		} else {
			for (int i = 1; i <= maxLink; i++) {
				String treeIndex = miLinkRef.getTreeIndex() + numToString(i);
				int m = 0;
				for (m = 0; m < miLinkRefs.size(); m++) {
					JmiLinkRef mL = (JmiLinkRef) miLinkRefs.get(m);
					if (treeIndex.equals(mL.getTreeIndex())) {
						break;
					}
				}
				if (m == miLinkRefs.size()) {
					miNewLinkRef.setTreeIndex(treeIndex);
					break;
				}
			}
		}
		if (StringUtil.isEmpty(miNewLinkRef.getTreeIndex())) {
			return null;
		}
		miNewLinkRef.setLayerId(miLinkRef.getLayerId() + 1);
		miNewLinkRef.setLinkJmiMember(miLinkRef.getJmiMember());
		return miNewLinkRef;
	}
	public String numToString(Integer num) {
		String str = "00";
		for (int i = 0; i < num; i++) {
			str = getNextNo(0, str);
		}
		return str;
	}

	public String getNextNo(int sonFlag, String str) {
		str = str.toLowerCase();
		String strNext = "";
		if (sonFlag == 0) {
			String strTemp1 = str.substring(0, str.length() - 2);
			String strTemp2 = str.substring(str.length() - 2, str.length());
			String s1 = strTemp2.substring(0, 1);
			String s2 = strTemp2.substring(1, 2);
			if ("z".equals(s2)) {
				strNext = strTemp1 + getNextChar(s1) + "0";
				if ("z".equals(s1)) {
					strNext = "";
				} else {

					//strNext = strTemp1 + getNextChar(s1) + s2;
				}
			} else {

				strNext = strTemp1 + s1 + getNextChar(s2);
			}
		} else {

			strNext = str + "00";
		}
		return strNext;
	}

	String getNextChar(String str) {
		String strNext = "";
		String strArray = "0123456789abcdefghijklmnopqrstuvwxyz";
		if ((strArray.length() - 1 > strArray.indexOf(str)) && (strArray.indexOf(str) >= 0)) {
			strNext = strArray.substring(strArray.indexOf(str) + 1, strArray.indexOf(str) + 2);
		}
		return strNext;
	}
}
