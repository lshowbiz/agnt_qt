package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JbdMemberLinkCalcHist;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.dao.JmiRecommendRefDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("jmiRecommendRefDao")
public class JmiRecommendRefDaoJpa extends GenericDaoHibernate<JmiRecommendRef, String> implements JmiRecommendRefDao {

    public JmiRecommendRefDaoJpa() {
        super(JmiRecommendRef.class);
    }
    

    public List getJmiRecommendRefbyRecommendNo(String recommendNo,String orderByName,String sort){
    	return this.getSession().createQuery("select a from JmiRecommendRef a where recommendJmiMember.userCode = '"+recommendNo+"' order by "+orderByName+" "+sort).list();

    }
    public JmiRecommendRef getNewMiLinkRefManagersByRecommendNo(JmiRecommendRef jmiRecommendRef) {
		if (jmiRecommendRef == null) {
			return null;
		}
		JmiRecommendRef miNewRecommendRef = new JmiRecommendRef();
		
		List miRecommendRefs = getJmiRecommendRefbyRecommendNo(jmiRecommendRef.getUserCode(), "layerId,userCode", "");
		if (miRecommendRefs == null) {
			miNewRecommendRef.setTreeIndex(jmiRecommendRef.getTreeIndex() + "000");
		} else {
			// miNewRecommendRef.setTreeIndex(miRecommendRef.getTreeIndex() +
			// numToString(miRecommendRefs.size()));

			for (int i = 0; i < 46656; i++) {
				String treeIndex = jmiRecommendRef.getTreeIndex() + numToString(i);
				int m = 0;
				for (m = 0; m < miRecommendRefs.size(); m++) {
					JmiRecommendRef mL = (JmiRecommendRef) miRecommendRefs.get(m);
					if (treeIndex.equals(mL.getTreeIndex())) {
						break;
					}
				}
				if (m == miRecommendRefs.size()) {
					miNewRecommendRef.setTreeIndex(treeIndex);
					break;
				}
			}

		}
		miNewRecommendRef.setLayerId(jmiRecommendRef.getLayerId() + 1);
		miNewRecommendRef.setRecommendJmiMember(jmiRecommendRef.getJmiMember());
		return miNewRecommendRef;
	}

	public String numToString(Integer num) {
		String str = "000";
		for (int i = 0; i < num; i++) {
			str = getNextNo(0, str);
		}
		return str;
	}

	public String getNextNo(int sonFlag, String str) {
		str = str.toLowerCase();
		String strNext = "";
		if (sonFlag == 0) {
			String strTemp1 = str.substring(0, str.length() - 3);
			String strTemp2 = str.substring(str.length() - 3, str.length());
			String s1 = strTemp2.substring(0, 1);
			String s2 = strTemp2.substring(1, 2);
			String s3 = strTemp2.substring(2, 3);
			if ("z".equals(s3)) {
				strNext = strTemp1 + s1 + getNextChar(s2) + "0";
				if ("z".equals(s2)) {
					strNext = strTemp1 + getNextChar(s1) + "00";
					if ("z".equals(s1)) {
						strNext = "";
					} else {

						// strNext = strTemp1 + getNextChar(s1) + s2 + s3;
					}
				} else {

					// strNext = strTemp1 + s1 + getNextChar(s2) + s3;
				}
			} else {

				strNext = strTemp1 + s1 + s2 + getNextChar(s3);
			}
		} else {

			strNext = str + "000";
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
	
	/**
	 * 获取某推荐下的会员
	 * @param linkNo
	 */                                  
	public List getJmiRecommendRefsByRecommendNo(String recommendNo){
		String hqlQuery = "select m from JmiRecommendRef m where recommendJmiMember.userCode='" + recommendNo + "' order by m.treeIndex";
		return this.getSession().createQuery(hqlQuery).list();
	}
	
}
